package kr.co.bnkfirst.repository.product;

import kr.co.bnkfirst.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
