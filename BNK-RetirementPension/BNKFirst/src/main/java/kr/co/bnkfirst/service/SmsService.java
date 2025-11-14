package kr.co.bnkfirst.service;

import kr.co.bnkfirst.sms.SmsClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class SmsService {

    private static final long CODE_TTL_SECONDS = 300; // 5분

    private final ConcurrentHashMap<String, CodeEntry> codeStorage = new ConcurrentHashMap<>();

    private record CodeEntry(String code, Instant expiresAt) {}

    private final SmsClient smsClient;

    public SmsService(SmsClient smsClient) {
        this.smsClient = smsClient;
    }

    private String normalizePhone(String p) {
        return p == null ? null : p.replaceAll("\\D", "");
    }

    // 인증번호 전송
    public void sendVerificationCode(String phoneRaw) {
        String phone = normalizePhone(phoneRaw);

        if (phone == null || !phone.matches("^01\\d{8,9}$")) {
            throw new IllegalArgumentException("휴대폰 번호 형식이 올바르지 않습니다.");
        }

        String code = createVerificationCode();
        smsClient.send(phone, "[BNK] 인증번호는 " + code + " 입니다.");

        codeStorage.put(phone, new CodeEntry(code, Instant.now().plusSeconds(CODE_TTL_SECONDS)));
        log.debug("SMS 코드 저장: phone={}, code={}", phone, code);
    }

    // 인증번호 검증
    public Map<String, Object> verifyVerificationCode(String phoneRaw, String inputCode) {
        String phone = normalizePhone(phoneRaw);
        CodeEntry entry = codeStorage.get(phone);

        if (entry == null) {
            return Map.of("ok", false, "message", "인증번호를 먼저 요청해 주세요.");
        }

        if (Instant.now().isAfter(entry.expiresAt())) {
            codeStorage.remove(phone);
            return Map.of("ok", false, "message", "인증번호가 만료되었습니다.");
        }

        if (!entry.code().equals(inputCode)) {
            return Map.of("ok", false, "message", "인증번호가 일치하지 않습니다.");
        }

        codeStorage.remove(phone);
        String token = "sms-" + phone + "-" + Instant.now().toEpochMilli();

        return Map.of(
                "ok", true,
                "message", "인증 성공",
                "verificationToken", token
        );
    }

    private String createVerificationCode() {
        int num = (int)(Math.random() * 900000) + 100000;
        return String.valueOf(num);
    }
}
