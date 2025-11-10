package kr.co.bnkfirst.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
public class UsersController {

    @GetMapping("/main")
    public String memberMain(){
        return "pages/member/member_main";
    }

    @GetMapping("/terms")
    public String memberTerms(){
        return "pages/member/member_terms";
    }

    @GetMapping("/info")
    public String memberInfo(){
        return "pages/member/member_info";
    }

    @GetMapping("/auth")
    public String memberAuth(){
        return "pages/member/member_auth";
    }

    @GetMapping("/active")
    public String memberActive(){
        return "pages/member/member_active";
    }

    @GetMapping("/findid")
    public String memberFindid(){
        return "pages/member/member_findid";
    }

    @GetMapping("/findpw")
    public String memberFindpw(){
        return "pages/member/member_findpw";
    }

}
