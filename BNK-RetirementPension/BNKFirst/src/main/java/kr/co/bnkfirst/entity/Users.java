package kr.co.bnkfirst.entity;

import jakarta.persistence.*;
import kr.co.bnkfirst.dto.UsersDTO;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uId;
    private String mId;
    private String mPw;
    private String mName;
    @CreationTimestamp
    private LocalDateTime mBirth;
    private String mGender;
    private String mCarrier;
    private String mEmail;
    private String mPhone;
    @CreationTimestamp
    private LocalDateTime mDate;
    private String mGrade;
    private String mJumin;
    private String mCond;
    private String mNum;
    @CreationTimestamp
    private LocalDateTime mAccess;
    private String mLimit;

    /*
        시간 남으면 DB 추가 후 출력 예정
        진행시 패널 형식x → 각 약관을 버튼으로 바꾼 후 누르면 모달창 형식으로 변경
        ↓ 원래 하드 코딩으로 예정
     */
    private String mTitle;
    private String mContent;

    public UsersDTO toDTO(){
        return UsersDTO.builder()
                .uId(uId)
                .mId(mId)
                .mPw(mPw)
                .mName(mName)
                .mBirth(mBirth)
                .mGender(mGender)
                .mCarrier(mCarrier)
                .mEmail(mEmail)
                .mPhone(mPhone)
                .mDate(mDate)
                .mGrade(mGrade)
                .mJumin(mJumin)
                .mCond(mCond)
                .mNum(mNum)
                .mAccess(mAccess)
                .mLimit(mLimit)
                .mTitle(mTitle)
                .mContent(mContent)
                .build();
    }

}
