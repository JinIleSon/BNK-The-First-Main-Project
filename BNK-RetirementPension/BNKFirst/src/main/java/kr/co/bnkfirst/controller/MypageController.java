package kr.co.bnkfirst.controller;

import kr.co.bnkfirst.dto.mypage.DealDTO;
import kr.co.bnkfirst.service.MypageService;
import kr.co.bnkfirst.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
        model.addAttribute("balance", mypageService.findByBalance("a123"));
        model.addAttribute("contractList", mypageService.findByContract("a123"));
        model.addAttribute("documentList", mypageService.findByDocumentList("a123"));
        return "mypage/mypage_main";
    }
    @GetMapping("/mypage/prod")
    public String Prod(Model model) {
        model.addAttribute("contractList", mypageService.findByContract("a123"));
        log.info(mypageService.findByContract("a123").toString());
        model.addAttribute("balance", mypageService.findByBalance("a123"));
        return "mypage/mypage_prod";
    }

    @PostMapping("/mypage/prod")
    public String Prod(int dbalance, String dwho, String myAcc, String yourAcc){
        log.info("dbalance="+dbalance+"dwho="+dwho+"  myAcc="+myAcc+"  yourAcc="+yourAcc);
        // a123은 현재 로그인된 아이디로 할 것
        mypageService.transfer("a123", dbalance, dwho, myAcc, yourAcc);

        return "redirect:/mypage/prod";
    }

    @GetMapping("/mypage/prod/cancel")
    public String ProdCancel() {
        return "mypage/mypage_prodCancel";
    }
    @GetMapping("/mypage/setup")
    public String Setup() {
        return "mypage/mypage_setup";
    }
}
