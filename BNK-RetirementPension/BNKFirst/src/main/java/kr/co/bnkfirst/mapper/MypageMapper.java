package kr.co.bnkfirst.mapper;

import kr.co.bnkfirst.dto.mypage.DealDTO;
import kr.co.bnkfirst.dto.UsersDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MypageMapper {
    UsersDTO findById(String mId);
    DealDTO findByDeal(String mId);
}
