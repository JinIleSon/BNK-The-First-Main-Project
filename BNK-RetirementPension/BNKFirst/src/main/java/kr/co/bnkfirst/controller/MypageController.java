package kr.co.bnkfirst.controller;

import kr.co.bnkfirst.service.MypageService;
import kr.co.bnkfirst.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MypageController {

    private final MypageService mypageService;

    @GetMapping("/mypage/main")
    public String mainPage(Model model) {

        // a123은 나중에 로그인할 때 바꾸기
        model.addAttribute("usersList", mypageService.findById("a123"));
        model.addAttribute("dealList", mypageService.findByDeal("a123"));
        return "pages/mypage/mypage_main";
    }
    @GetMapping("/mypage/prod")
    public String Prod() {
        return "pages/mypage/mypage_prod";
    }
    @GetMapping("/mypage/prodcancel")
    public String ProdCancel() {
        return "pages/mypage/mypage_prodCancel";
    }
    @GetMapping("/mypage/setup")
    public String Setup() {
        return "pages/mypage/mypage_setup";
    }
}
