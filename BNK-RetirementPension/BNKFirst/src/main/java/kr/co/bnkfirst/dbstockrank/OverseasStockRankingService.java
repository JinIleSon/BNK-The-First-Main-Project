package kr.co.bnkfirst.dbstockrank;

import jakarta.annotation.PostConstruct;
import kr.co.bnkfirst.dbstock.DbApiClient;
import kr.co.bnkfirst.kiwoomRank.StockRankDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OverseasStockRankingService {

    private final DbApiClient dbApiClient;

    // 대상 종목 리스트 (나스닥 전체 or 일부 등)
    private List<OverseasStockInfo> targetStocks = new ArrayList<>();

    private final AtomicReference<List<StockRankDTO>> cache =
            new AtomicReference<>(List.of());

    /** 단일현재가로 세부 조회할 종목 개수 (상위 n개 정도만) */
    @Value("${db.overseas.detail-count:200}")
    private int detailCount;

    public List<StockRankDTO> getCachedRanks() {
        return cache.get();
    }

    @PostConstruct
    public void init() {
        try {
            targetStocks = dbApiClient.getOverseasStockCodes("NA");
            log.info("init 완료.");

            // 👇 여기! 서버 부팅과 분리
            CompletableFuture.runAsync(() -> {
                try {
                    refreshRanking();
                } catch (Exception e) {
                    log.error("초기 랭킹 생성 실패", e);
                }
            });

        } catch (Exception e) {
            log.error("해외 종목 초기화 실패", e);
        }
    }

    /** 1분마다 전체 랭킹 갱신 (멀티현재가 + 단일현재가 조합) */
    @Scheduled(fixedDelay = 60000)
    public void refreshRanking() {
        if (targetStocks.isEmpty()) return;

        try {
            // 1) 멀티현재가로 전체 시세/등락률 가져오기
            List<DbOverseasPriceDto> prices = new ArrayList<>();

            for (int i = 0; i < targetStocks.size(); i += 50) {
                List<OverseasStockInfo> slice =
                        targetStocks.subList(i, Math.min(i + 50, targetStocks.size()));

                List<DbOverseasPriceDto> part = dbApiClient.getMultiPrice(slice);
                prices.addAll(part);

                // 멀티현재가 TR 초당 1건 → 1초 딜레이
                Thread.sleep(1000);
            }

            // 코드 기준으로 매핑
            Map<String, DbOverseasPriceDto> priceMap = prices.stream()
                    .collect(Collectors.toMap(
                            DbOverseasPriceDto::getCode,
                            p -> p,
                            (a, b) -> a
                    ));

            // 2) 단일현재가(FSTKPRICE)로 상위 detailCount 종목만 거래대금 보정
            int limit = Math.min(detailCount, targetStocks.size());
            for (int i = 0; i < limit; i++) {
                OverseasStockInfo info = targetStocks.get(i);

                try {
                    DbOverseasPriceDto detail = dbApiClient.getSinglePrice(info);

                    DbOverseasPriceDto base = priceMap.getOrDefault(detail.getCode(), detail);
                    if (detail.getPrice() > 0) {
                        base.setPrice(detail.getPrice());
                    }
                    if (detail.getChangeRate() != 0.0) {
                        base.setChangeRate(detail.getChangeRate());
                    }
                    if (detail.getAmount() > 0) {
                        base.setAmount(detail.getAmount());
                    }
                    priceMap.put(base.getCode(), base);

                } catch (Exception ex) {
                    log.warn("단일현재가 조회 실패 code={}", info.getCode(), ex);
                }

                try {
                    Thread.sleep(600);   // 600ms ≒ 초당 1.6건 정도
                } catch (InterruptedException ignored) {}
            }

            // 3) 리스트로 다시 모아서 거래대금 기준 TOP100 뽑기
            List<DbOverseasPriceDto> merged = new ArrayList<>(priceMap.values());

            List<DbOverseasPriceDto> top = merged.stream()
                    .sorted(Comparator.comparingLong(DbOverseasPriceDto::getAmount).reversed())
                    .limit(100)
                    .collect(Collectors.toList());

            // 4) StockRankDTO로 변환 (여기서 long 으로 캐스팅)
            List<StockRankDTO> ranks = new ArrayList<>();
            int rank = 1;
            for (DbOverseasPriceDto p : top) {
                StockRankDTO dto = StockRankDTO.builder()
                        .rank(rank++)
                        .code(p.getCode())
                        .name(p.getName())
                        .price((long) p.getPrice())    // ← 여기서 long으로 변환
                        .changeRate(p.getChangeRate())
                        .amount(p.getAmount())          // 이미 long
                        .build();
                ranks.add(dto);
            }

            cache.set(ranks);
            log.info("🌍 해외 거래대금 TOP100 갱신완료 size={}", ranks.size());

        } catch (Exception e) {
            log.error("해외 랭킹 갱신 실패", e);
        }
    }
}
