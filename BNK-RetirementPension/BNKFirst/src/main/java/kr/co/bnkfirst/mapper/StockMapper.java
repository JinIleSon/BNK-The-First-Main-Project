package kr.co.bnkfirst.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.RequestScope;

@Mapper
public interface StockMapper {

    // 주식 구매 프로세스
    public void buyStock(@RequestParam("pcuid") String pcuid,
                         @RequestParam("pstock") Integer pstock,
                         @RequestParam("pprice") Integer pprice,
                         @RequestParam("psum") Integer psum,
                         @RequestParam("pname") String pname,
                         @RequestParam("pacc") String pacc);

    public void downBalance(@RequestParam("psum") Integer psum,
                            @RequestParam("pacc") String pacc);
    // 주식 구매 프로세스

    // 주식 판매 프로세스

    public void sellStock();

}
