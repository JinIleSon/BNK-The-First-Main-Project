package kr.co.bnkfirst.mapper;

import kr.co.bnkfirst.dto.DocumentDTO;
import kr.co.bnkfirst.dto.mypage.DealDTO;
import kr.co.bnkfirst.dto.UsersDTO;
import kr.co.bnkfirst.dto.product.PcontractDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MypageMapper {
    UsersDTO findById(String mid);
    DealDTO findByDeal(String mid);
    int findByBalance(String mid);
    List<PcontractDTO> findByContract(String mid);
    List<DocumentDTO> findByDocumentList(String mid);
}
