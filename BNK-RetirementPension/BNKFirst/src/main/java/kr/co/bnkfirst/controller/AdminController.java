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
    @GetMapping("/admin/member")
    public String member(){
        return "pages/admin/admin_member";
    }
    @GetMapping("/admin/env")
    public String env(){
        return "pages/admin/admin_env";
    }
    @GetMapping("/admin/prod")
    public String prod(){
        return "pages/admin/admin_prod";
    }
    @GetMapping("/admin/prod/modify")
    public String prodmodify(){
        return "pages/admin/admin_prodModify";
    }
    @GetMapping("/admin/prod/register")
    public String prodregister(){
        return "pages/admin/admin_prodregister";
    }
    @GetMapping("/admin/cs")
    public String cs(){
        return "pages/admin/admin_cs";
    }
    @GetMapping("/admin/cs/register")
    public String csregister(){
        return "pages/admin/admin_csregister";
    }
    @GetMapping("/admin/cs/modify")
    public String csmodify(){
        return "pages/admin/admin_csmodify";
    }
}
