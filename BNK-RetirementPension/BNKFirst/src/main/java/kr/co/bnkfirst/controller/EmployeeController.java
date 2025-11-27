package kr.co.bnkfirst.controller;

import jakarta.validation.Valid;
import kr.co.bnkfirst.dto.corporate.employee.EmployeeDeleteDto;
import kr.co.bnkfirst.dto.corporate.employee.EmployeeUpdateDto;
import kr.co.bnkfirst.dto.corporate.employee.EmployeeListDto;
import kr.co.bnkfirst.service.EmployeeService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/corporate/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    /** =====================================
     *  직원 목록 + 검색
     *  ===================================== */
    @GetMapping("/list")
    public String list(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false, defaultValue = "ALL") String planType,
            Model model
    ) {

        boolean isSearch =
                (keyword != null && !keyword.isEmpty()) ||
                        (planType != null && !planType.equals("ALL"));

        if (isSearch) {
            model.addAttribute("employees", employeeService.search(keyword, planType));
        } else {
            model.addAttribute("employees", employeeService.getEmployeeList());
        }

        model.addAttribute("keyword", keyword);
        model.addAttribute("planType", planType);

        return "corporate/employee/list";
    }


    /** =====================================
     *  직원 상세 (기존 detail 화면)
     *  ===================================== */
    @GetMapping("/detail/{empId}")
    public String detail(@PathVariable Long empId, Model model) {

        model.addAttribute("employee",
                employeeService.getEmployeeDetail(empId));

        model.addAttribute("contributions",
                employeeService.getEmployeeContributions(empId));

        model.addAttribute("currentBalance",
                employeeService.getEmployeeCurrentBalance(empId));

        // 좌측 목록 (필수)
        model.addAttribute("employees",
                employeeService.getEmployeeList());

        return "corporate/employee/detail";
    }



    /** =====================================
     *  직원 수정 화면
     *  ===================================== */
    @GetMapping("/edit/{empId}")
    public String edit(@PathVariable Long empId, Model model) {

        model.addAttribute("employee",
                employeeService.getEmployeeDetail(empId));

        model.addAttribute("employeeUpdateDto", new EmployeeUpdateDto());

        return "corporate/employee/edit";
    }


    /** =====================================
     *  직원 수정 실행
     *  ===================================== */
    @PostMapping("/edit/{empId}")
    public String editAction(
            @PathVariable Long empId,
            @Valid @ModelAttribute("employeeUpdateDto") EmployeeUpdateDto dto,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("employee", employeeService.getEmployeeDetail(empId));
            return "corporate/employee/edit";
        }

        dto.setEmpId(empId);
        employeeService.updateEmployee(dto);

        return "redirect:/corporate/employee/detail/" + empId;
    }


    /** =====================================
     *  직원 삭제 확인
     *  ===================================== */
    @GetMapping("/delete/{empId}")
    public String deleteConfirm(@PathVariable Long empId, Model model) {
        model.addAttribute("employee",
                employeeService.getEmployeeDetail(empId));
        model.addAttribute("employeeDeleteDto", new EmployeeDeleteDto());
        return "corporate/employee/delete";
    }


    /** =====================================
     *  직원 삭제 실행
     *  ===================================== */
    @PostMapping("/delete/{empId}")
    public String deleteAction(
            @PathVariable Long empId,
            @ModelAttribute("employeeDeleteDto") EmployeeDeleteDto dto
    ) {
        employeeService.deleteEmployee(empId);
        return "redirect:/corporate/employee/list";
    }


    /** =====================================
     *  직원 월별 납입 내역 (2단 구조 페이지)
     *  ===================================== */
    @GetMapping("/contribution/{empId}")
    public String contribution(@PathVariable Long empId, Model model) {

        // 오른쪽 상세 뷰 데이터
        model.addAttribute("employee",
                employeeService.getEmployeeDetail(empId));

        model.addAttribute("contributions",
                employeeService.getEmployeeContributions(empId));

        model.addAttribute("currentBalance",
                employeeService.getEmployeeCurrentBalance(empId));

        // ⭐ 좌측 목록 패널용 (필수)
        model.addAttribute("employees",
                employeeService.getEmployeeList());

        // ⭐ 새로 만든 2단 구조 화면 파일명
        return "corporate/employee/contribution";
    }


    /** =====================================
     *  자동완성 API
     *  ===================================== */
    @GetMapping("/autocomplete")
    @ResponseBody
    public List<EmployeeListDto> autocomplete(@RequestParam String keyword) {
        return employeeService.autocomplete(keyword);
    }
}
