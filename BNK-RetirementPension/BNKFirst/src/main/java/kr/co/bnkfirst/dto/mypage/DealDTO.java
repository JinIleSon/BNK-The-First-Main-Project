package kr.co.bnkfirst.dto.mypage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DealDTO {

    private int did;
    private String mid;
    private int dbalance;
    private String dwho;
    @CreationTimestamp
    private LocalDateTime ddate;
}
