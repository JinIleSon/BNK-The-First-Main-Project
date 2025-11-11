package kr.co.bnkfirst.mapper;

import kr.co.bnkfirst.dto.PageRequestDTO;
import kr.co.bnkfirst.dto.product.ProductDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminMapper {

    public List<ProductDTO> selectAllProduct(@Param("pageRequestDTO") PageRequestDTO pageRequestDTO);




    public int selectCountTotal(@Param("pageRequestDTO") PageRequestDTO pageRequestDTO);


}
