package kr.co.bnkfirst.mapper;

import kr.co.bnkfirst.dto.DocumentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DocumentMapper {

    //전체조회
    @Select("SELECT * FROM DOCUMENT WHERE DOCTYPE = '필요서류' ORDER BY DOCID ASC")
    List<DocumentDTO> selectAllDocuments();

    //검색기능 (제목 + 내용)
    @Select("""
    SELECT * FROM DOCUMENT
    WHERE DOCTYPE = '필요서류'
    AND (LOWER(DOCTITLE) LIKE '%' || LOWER(#{keyword}) || '%'
        OR LOWER(DOCCONTECT) LIKE '%' || LOWER(#{keyword}) || '%')
        ORDER BY DOCID ASC
    """)
    List<DocumentDTO> searchDocuments(String keyword);

}
