package kr.co.bnkfirst.dto.product;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ProductDTO {
    private int id;
    private String pid;
    private String ptype;
    private String pname;
    private String pbirate;
    private String phirate;
    private String pcprd;
    private String pelgbl;
    private String prmthd;
    private String pprfcrt;
    private String pirinfo;
    private String pttitle;
    private String ptlink;
    private String pcond;
    private String pupdate;
}
