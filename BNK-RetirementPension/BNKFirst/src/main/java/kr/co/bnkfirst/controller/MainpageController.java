package kr.co.bnkfirst.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class MainpageController {

    @GetMapping("/main/mainpage")
    public String mainpage() {
        return "pages/main/main";
    }

}
