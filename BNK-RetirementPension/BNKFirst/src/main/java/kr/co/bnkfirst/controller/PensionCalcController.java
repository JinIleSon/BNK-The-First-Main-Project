package kr.co.bnkfirst.controller;

import kr.co.bnkfirst.dto.PensionMethodRequest;
import kr.co.bnkfirst.dto.PensionMethodResult;
import kr.co.bnkfirst.service.PensionMethodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class PensionCalcController {

    private final PensionMethodService pensionMethodService;

    @GetMapping("/mypage/calc/pension")
    public String calcPension(@ModelAttribute("form") PensionMethodRequest form, Model model) {

        // 연금부리이율만 기본값 1.0 설정
        if (form.getAnnualRatePercent() == null) {
            form.setAnnualRatePercent(1.0);
        }

        model.addAttribute("form", form);
        return "mypage/mypage_calcPension";
    }

    @PostMapping("/mypage/calc/pension")
    public String calcPensionMethod(@ModelAttribute("form") PensionMethodRequest form, Model model) {

        PensionMethodResult result = pensionMethodService.calculate(form);

        model.addAttribute("form", form);
        model.addAttribute("result", result);
        return "mypage/mypage_calcPensionMethodResult";
    }
}
