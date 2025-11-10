package kr.co.bnkfirst.mapper;

import kr.co.bnkfirst.dto.UsersDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UsersMapper {
    UsersDTO findUserById(String mId);
}
