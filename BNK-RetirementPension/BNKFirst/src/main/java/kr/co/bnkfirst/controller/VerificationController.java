package kr.co.bnkfirst.controller;

import kr.co.bnkfirst.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<Map<String, Object>> send(@RequestBody Map<String, String> json) {
        String email = json.get("email");
        log.info(">>> EmailController.send(), email={}", email);

        try {
            emailService.sendCode(email);
            return ResponseEntity.ok(Map.of("send", true));
        } catch (Exception e) {
            log.error("메일 발송 실패", e);
            return ResponseEntity.status(500).body(Map.of("send", false, "error", e.getMessage()));
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<Map<String, Boolean>> verify(@RequestBody Map<String, String> json) {
        String code = json.get("code");
        boolean matched = emailService.verifyCode(code);

        return ResponseEntity.ok(Map.of("matched", matched));
    }
}
