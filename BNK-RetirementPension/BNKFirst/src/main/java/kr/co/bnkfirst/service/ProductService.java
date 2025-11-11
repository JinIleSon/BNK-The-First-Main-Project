package kr.co.bnkfirst.service;

import kr.co.bnkfirst.dto.product.ProductDTO;
import kr.co.bnkfirst.entity.product.Product;
import kr.co.bnkfirst.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public Page<ProductDTO> findProducts(String sort,
                                         int page,
                                         int pageSize,
                                         String target,
                                         String join,
                                         String tax,
                                         String keyword) {
        Page<Product> products = null;
        Pageable pageable = null;
        switch (sort) {
            case "join_internet" -> {
                pageable = PageRequest.of(page - 1, pageSize);
                products = productRepository.findPrefSorted(keyword, "인터넷", target, join, tax, pageable);
                log.info("products = {}", products);
                return products.map(Product::toDTO);
            }
            case "rate_desc" -> {
                pageable = PageRequest.of(page - 1, pageSize, Sort.by("phirate").descending());
                products = productRepository.findDynamicProducts(target, join, tax, pageable);
                log.info("products = {}", products);
                return products.map(Product::toDTO);
            }
            case "release_desc" -> {
                pageable = PageRequest.of(page - 1, pageSize, Sort.by("pupdate").descending());
                products = productRepository.findDynamicProducts(target, join, tax, pageable);
                log.info("products = {}", products);
                return products.map(Product::toDTO);
            }
            default -> {
                return null;
            }
        }
    }
}