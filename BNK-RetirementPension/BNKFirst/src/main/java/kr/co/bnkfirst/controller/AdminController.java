package kr.co.bnkfirst.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class AdminController {

    @GetMapping("/admin/main")
    public String main(){
        return "pages/admin/admin_main";
    }
}
