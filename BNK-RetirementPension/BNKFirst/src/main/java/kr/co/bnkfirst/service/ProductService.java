package kr.co.bnkfirst.service;

import kr.co.bnkfirst.dto.product.ProductDTO;
import kr.co.bnkfirst.entity.product.Product;
import kr.co.bnkfirst.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public Page<ProductDTO> findProducts(String pelgbl, String prmthd, String pprfcrt, Pageable pageable) {
        Page<Product> products = productRepository.findDynamicProducts(pelgbl, prmthd, pprfcrt, pageable);
        log.info("products = {}", products);
        return products.map(Product::toDTO);
    }
}