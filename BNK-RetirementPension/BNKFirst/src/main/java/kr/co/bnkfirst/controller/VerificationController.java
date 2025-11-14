package kr.co.bnkfirst.controller;

import kr.co.bnkfirst.service.EmailService;
import kr.co.bnkfirst.service.SmsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/verification")
public class VerificationController {

    private final EmailService emailService;
    private final SmsService smsService;

    // ========== EMAIL ==========
    @PostMapping("/email/send")
    public ResponseEntity<Map<String, Object>> sendEmail(@RequestBody Map<String, String> json) {
        String email = json.get("email");
        log.info(">>> VerificationController.sendEmail(), email={}", email);

        try {
            emailService.sendCode(email);
            return ResponseEntity.ok(Map.of("send", true));
        } catch (Exception e) {
            log.error("메일 발송 실패", e);
            return ResponseEntity.status(500).body(Map.of("send", false, "error", e.getMessage()));
        }
    }

    @PostMapping("/email/verify")
    public ResponseEntity<Map<String, Boolean>> verifyEmail(@RequestBody Map<String, String> json) {
        String code = json.get("code");
        boolean matched = emailService.verifyCode(code);
        return ResponseEntity.ok(Map.of("matched", matched));
    }

    // ========== SMS ==========
    @PostMapping("/sms/send")
    public ResponseEntity<Map<String, Object>> sendSms(@RequestBody Map<String, String> json) {
        String phoneNumber = json.get("phoneNumber");
        log.info(">>> VerificationController.sendSms(), phone={}", phoneNumber);

        try {
            smsService.sendVerificationCode(phoneNumber);
            return ResponseEntity.ok(Map.of("send", true));
        } catch (Exception e) {
            log.error("SMS 발송 실패", e);
            return ResponseEntity.status(500).body(Map.of(
                    "send", false,
                    "error", e.getMessage()
            ));
        }
    }

    @PostMapping("/sms/verify")
    public ResponseEntity<Map<String, Object>> verifySms(@RequestBody Map<String, String> json) {
        String phoneNumber = json.get("phoneNumber");
        String code = json.get("code");

        Map<String, Object> result = smsService.verifyVerificationCode(phoneNumber, code);

        return ResponseEntity.ok(result);
    }
}
