package kr.co.bnkfirst.dto;

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

    private int dId;
    private String mId;
    private int dBalance;
    private String dWho;
    @CreationTimestamp
    private LocalDateTime dDate;
}
