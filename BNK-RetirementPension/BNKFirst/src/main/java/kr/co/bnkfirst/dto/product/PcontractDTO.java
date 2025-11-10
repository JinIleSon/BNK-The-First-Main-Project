package kr.co.bnkfirst.dto.product;

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
public class PcontractDTO {
    private String pcUid;
    private String pcPid;
    private String pcCptp;
    private String pcCprd;
    private String pcWcat;
    private String pcWdac;
    private int pcMdps;
    private int pcGamn;
    private String pcAtapp;
    private int pcAtdt;
    private String pcAtac;
    private String pcCns;
    private String pcNtcs;
    private String pcNapw;
    private String pAcc;
    @CreationTimestamp
    private LocalDateTime pNew;
    @CreationTimestamp
    private LocalDateTime pEnd;
    private int pBalance;

}
