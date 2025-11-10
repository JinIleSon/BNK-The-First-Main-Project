package kr.co.bnkfirst.repository.product;

import kr.co.bnkfirst.entity.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    @Query("""
    select p
    from Product p
    where (:pelgbl is null or p.pelgbl = :pelgbl)
      and (:prmthd is null or p.prmthd = :prmthd)
      and (:pprfcrt is null or p.pprfcrt = :pprfcrt)
    """)
    Page<Product> findDynamicProducts(
            @Param("pelgbl") String pelgbl,
            @Param("prmthd") String prmthd,
            @Param("pprfcrt") String pprfcrt,
            Pageable pageable
    );

}
