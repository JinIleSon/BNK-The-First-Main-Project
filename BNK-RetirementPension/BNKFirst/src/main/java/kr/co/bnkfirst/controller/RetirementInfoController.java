package kr.co.bnkfirst.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RetirementInfoController {

    @GetMapping("/retirement-renew/intro/why-pension")
    public String whyPension(){
        return "retirement-renew/intro/why-pension/why-pension";
    }

    @GetMapping("/retirement-renew/system/db")
    public String db(){
        return "retirement-renew/system/db/db";
    }


}
