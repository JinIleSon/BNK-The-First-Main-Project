package kr.co.bnkfirst.controller;

import kr.co.bnkfirst.dto.PageRequestDTO;
import kr.co.bnkfirst.dto.admin.PageResponseAdminProductDTO;
import kr.co.bnkfirst.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/admin/main")
    public String main(){
        return "admin/admin_main";
    }
    @GetMapping("/admin/member")
    public String member(){
        return "admin/admin_member";
    }
    @GetMapping("/admin/env")
    public String env(){
        return "admin/admin_env";
    }
    @GetMapping("/admin/prod")
    public String prod(Model model, PageRequestDTO pageRequestDTO){

        log.info("pageRequestDTO={}", pageRequestDTO);
        PageResponseAdminProductDTO pageResponseDTO = adminService.selectAllProduct(pageRequestDTO);

        model.addAttribute("pageResponseDTO", pageResponseDTO);
        return "admin/admin_prod";
    }
    @GetMapping("/admin/prod/modify")
    public String prodmodify(){
        return "admin/admin_prodModify";
    }
    @GetMapping("/admin/prod/register")
    public String prodregister(){
        return "admin/admin_prodregister";
    }
    @GetMapping("/admin/cs")
    public String cs(){
        return "admin/admin_cs";
    }
    @GetMapping("/admin/cs/register")
    public String csregister(){
        return "admin/admin_csregister";
    }
    @GetMapping("/admin/cs/modify")
    public String csmodify(){
        return "admin/admin_csmodify";
    }
}
