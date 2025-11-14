package kr.co.bnkfirst.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Setter
@Getter
@Data
@Component
@SessionScope
public class EmailDTO {

    // 이메일
    private String code;
    private long codeTimestamp;
    private boolean verified = false;

    // SMS 코드 현재 미구현
    /*
    private String smsCode;
    private Long smsCodeTimestamp;
    private boolean smsVerified = false;
     */

}
