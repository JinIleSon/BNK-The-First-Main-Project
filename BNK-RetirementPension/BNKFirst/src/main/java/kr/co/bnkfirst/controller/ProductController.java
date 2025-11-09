package kr.co.bnkfirst.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {
    @GetMapping("/product/main")
    public String mainPage() {
        return "pages/product/product_main";
    }
}
