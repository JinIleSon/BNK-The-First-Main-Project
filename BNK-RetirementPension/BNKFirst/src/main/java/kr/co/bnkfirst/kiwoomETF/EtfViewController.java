package kr.co.bnkfirst.kiwoomETF;

import kr.co.bnkfirst.fx.FxService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class EtfViewController {

    private final EtfService etfService;
    private final FxService fxService;   // 이미 usdKrw 내려주는 서비스 있다고 가정

    @GetMapping("/stock/mainEtf")
    public String etfMain(Model model) {

        // ✅ tickerBar + 초기 리스트용 ETF 데이터
        List<EtfQuoteDTO> etfs = etfService.getCachedEtfs();
        model.addAttribute("etfs", etfs);

        // ✅ 환율 (기존 주식 페이지와 동일 로직)
        double usdKrw = fxService.getUsdKrwRateToday();
        model.addAttribute("usdKrw", usdKrw);

        return "stock/ETF_main";  // 지금 쓰고 있는 템플릿 이름
    }

    @GetMapping("/stock/orderEtf")
    public String stockOrder(@RequestParam("code") String code,
                             @RequestParam(value = "name", required = false) String name,
                             Model model) {

        // name을 안 넘겨줬으면 code를 그냥 이름처럼 보여주도록 임시 처리
        String stockName = (name != null && !name.isBlank()) ? name : code;

        // 🔥 ETF 랭킹 캐시에서 해당 코드 하나 찾기
        EtfQuoteDTO snap = etfService.findByCode(code);
        model.addAttribute("etfSnap", snap);

        model.addAttribute("code", code);
        model.addAttribute("stockName", stockName);

        return "stock/stock_orderETF";   // 템플릿 경로에 맞게
    }
}