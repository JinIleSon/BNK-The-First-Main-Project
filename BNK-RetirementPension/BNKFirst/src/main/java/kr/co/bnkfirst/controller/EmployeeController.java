package kr.co.bnkfirst.controller;

import kr.co.bnkfirst.service.EmployeeService;
import kr.co.bnkfirst.service.ContributionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/corporate/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final ContributionService contributionService;

    @GetMapping("/list")
    public String list() {
        return "corporate/employee/list";
    }

    @GetMapping("/detail/{empId}")
    public String detail(@PathVariable Long empId, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeById(empId));
        return "corporate/employee/detail";
    }

    @GetMapping("/edit/{empId}")
    public String edit(@PathVariable Long empId) {
        return "corporate/employee/edit";
    }

    @GetMapping("/delete/{empId}")
    public String delete(@PathVariable Long empId) {
        return "corporate/employee/delete";
    }

    @GetMapping("/contribution/{empId}")
    public String contribution(@PathVariable Long empId, Model model) {

        model.addAttribute("employee", employeeService.getEmployeeById(empId));
        model.addAttribute("contributions", contributionService.getContributionByEmpId(empId));
        model.addAttribute("currentBalance", contributionService.getCurrentBalance(empId));

        return "corporate/employee/contribution_list";
    }
}
