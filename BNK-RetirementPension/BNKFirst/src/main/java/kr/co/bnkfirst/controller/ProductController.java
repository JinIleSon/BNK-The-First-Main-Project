package kr.co.bnkfirst.controller;

import jakarta.annotation.security.PermitAll;
import kr.co.bnkfirst.dto.product.ProductDTO;
import kr.co.bnkfirst.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/product/main")
    public String mainPage() {
        return "product/product_main";
    }

    @GetMapping("/product/list")
    public String listPage() {
        return "product/product_list";
    }

    @PermitAll
    @ResponseBody
    @GetMapping("/product/items")
    public List<ProductDTO> getItems() {
        List<ProductDTO> products = productService.findAll();
        return products;
    }

    @GetMapping("/product/view")
    public String viewPage() {
        return "product/product_view";
    }

    @GetMapping("/product/insertInfo")
    public String insertInfoPage() {
        return "product/product_insert_info";
    }

    @GetMapping("/product/subCmpl")
    public String subCmplPage() {
        return "product/product_sub_cmpl";
    }
}
