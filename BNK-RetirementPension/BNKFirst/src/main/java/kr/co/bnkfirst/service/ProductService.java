package kr.co.bnkfirst.service;

import kr.co.bnkfirst.dto.product.ProductDTO;
import kr.co.bnkfirst.entity.product.Product;
import kr.co.bnkfirst.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public List<ProductDTO> findAll() {
        List<Product> products = productRepository.findAll();
        log.info("products = {}", products);
        return products.stream().map(Product::toDTO).toList();
    }
}