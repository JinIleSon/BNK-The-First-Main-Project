package kr.co.bnkfirst.sms;

public interface SmsClient {
    /**
     * 실제 문자 전송
     * @param to 전화번호
     * @param text 메시지 내용
     * @return 성공 여부
     */
    boolean send(String to, String text);
}
