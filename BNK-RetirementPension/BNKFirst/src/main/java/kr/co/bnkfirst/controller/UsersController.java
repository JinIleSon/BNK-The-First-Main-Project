package kr.co.bnkfirst.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.co.bnkfirst.dto.UsersDTO;
import kr.co.bnkfirst.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
public class UsersController {

    private final UsersService usersService;

    // 로그인 처리
    @PostMapping("/main")
    public String login(@ModelAttribute UsersDTO userDTO, HttpSession session) {
        UsersDTO foundUser = usersService.login(userDTO.getMid(), userDTO.getMpw());

        if (foundUser == null) {
            log.warn("로그인 실패 - 아이디 또는 비밀번호 오류");
            return "redirect:/member/main";
        }

        // 로그인 성공 시 세션 저장 + 30분 유지
        session.setAttribute("loginUser", foundUser);
        session.setMaxInactiveInterval(1800);
        log.info("로그인 성공: {}", foundUser.getMid());

        return "redirect:/main/main";
    }

    // 로그인 메인 페이지
    @GetMapping("/main")
    public String memberMain(Model model, HttpSession session) {
        UsersDTO loginUser = (UsersDTO) session.getAttribute("loginUser");

        // 로그인 후 login 쪽에 머무를 수 없도록 설정
        if (loginUser != null) {
            return "redirect:/main/main";
        }

        // 로그인 안 한 경우에만 로그인 화면 보여줌
        model.addAttribute("userDTO", new UsersDTO());
        return "member/member_main";
    }

    // 로그아웃 기능(세션 삭제)
    @GetMapping("/logout")
    @ResponseBody
    public void logout(HttpSession session) {
        session.invalidate();
    }

    @GetMapping("/terms")
    public String memberTerms() {
        return "member/member_terms";
    }

    @GetMapping("/auth")
    public String memberAuth() {
        return "member/member_auth";
    }

    @GetMapping("/info")
    public String memberInfo() {
        return "member/member_info";
    }

    // 회원가입 데이터 저장
    @PostMapping("/insert")
    public String insert(@ModelAttribute UsersDTO usersDTO, HttpSession session) {
        boolean result = usersService.register(usersDTO);

        if (result) {
            UsersDTO savedUser = usersService.findByMid(usersDTO.getMid());

            session.setAttribute("newUser", savedUser);
            return "redirect:active";
        } else {
            return "redirect:info";
        }
    }

    @GetMapping("/id-check")
    @ResponseBody
    public boolean idCheck(@RequestParam String mid) {
        return usersService.existsByMid(mid);
    }

    @GetMapping("/active")
    public String memberActive(HttpSession session, Model model) {

        UsersDTO newUser = (UsersDTO) session.getAttribute("newUser");

        if (newUser == null) {
            return "redirect:/member/info";
        }

        // XML로 추가 정보만 전달 (SYSDATE, Family)
        String xml =
                "<extra>" +
                        "   <grade>Family</grade>" +
                        "   <joinDate>SYSDATE</joinDate>" +
                        "</extra>";

        model.addAttribute("member", newUser);  // DTO 그대로 전달
        model.addAttribute("xml", xml);         // XML 별도 전달

        return "member/member_active";
    }

    @GetMapping("/findid")
    public String memberFindid() {
        return "member/member_findid";
    }

    @GetMapping("/findpw")
    public String memberFindpw() {
        return "member/member_findpw";
    }

}
