package kr.co.bnkfirst.mapper;

import kr.co.bnkfirst.dto.product.FundDTO;
import kr.co.bnkfirst.dto.product.PcontractDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {

    public PcontractDTO resultPcontract(String mid, String pcpid);
    public List<FundDTO> selectFund();
    public FundDTO selectFundDetail(@Param("fid") String fid);

    // 2025.11.27. 강민철 : 계좌 찾기
    public PcontractDTO selectAllByUidAndType(@Param("pcuid") String pcuid, @Param("type") String type);
    // 2025.11.27. 강민철 : 비밀번호 검증을 위한 계좌 가져오기
    public PcontractDTO selectByAccAndUid(@Param("pacc") String pacc, @Param("pcuid") String pcuid);
    // 2025.11.27. 강민철 : 구매 상품 등록하기
    public int savePcontract(PcontractDTO pcontract);
}
