package kr.co.bnkfirst.mapper;

import kr.co.bnkfirst.dto.UsersDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UsersMapper {
    UsersDTO findUserById(@Param("mid") String mid);

    int insertUser(UsersDTO user); // 회원가입용 insert
}
