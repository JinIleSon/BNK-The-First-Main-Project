package kr.co.bnkfirst.controller;

import jakarta.servlet.http.HttpSession;
import kr.co.bnkfirst.dto.UsersDTO;
import kr.co.bnkfirst.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
public class UsersController {

    private final UsersService usersService;

    @PostMapping("/main")
    public String login(@ModelAttribute UsersDTO userDTO, HttpSession session) {
        UsersDTO foundUser = usersService.login(userDTO.getMid(), userDTO.getMpw());
        if (foundUser == null) return "redirect:/member/login?error";
        session.setAttribute("loginUser", foundUser);
        return "redirect:/member/main";
    }

    @GetMapping("/main")
    public String memberMain(Model model){
        model.addAttribute("userDTO", new UsersDTO());
        return "member/member_main";
    }
    /*
    @GetMapping("/login")
    public String memberLogin(Model model){
        model.addAttribute("userDTO", new UsersDTO());
        return "member/member_login";
    }
     */

    @GetMapping("/terms")
    public String memberTerms(){
        return "member/member_terms";
    }

    @GetMapping("/info")
    public String memberInfo(){
        return "member/member_info";
    }

    @GetMapping("/auth")
    public String memberAuth(){
        return "member/member_auth";
    }

    @GetMapping("/active")
    public String memberActive(){
        return "member/member_active";
    }

    @GetMapping("/findid")
    public String memberFindid(){
        return "member/member_findid";
    }

    @GetMapping("/findpw")
    public String memberFindpw(){
        return "member/member_findpw";
    }
}
