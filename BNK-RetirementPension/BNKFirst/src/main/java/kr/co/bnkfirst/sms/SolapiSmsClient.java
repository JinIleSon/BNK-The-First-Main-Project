package kr.co.bnkfirst.sms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SolapiSmsClient implements SmsClient {

    @Override
    public boolean send(String to, String text) {
        log.info("Solapi 문자 전송: {}, {}", to, text);

        // 실제 Solapi API 호출 들어갈 자리
        return true;
    }
}