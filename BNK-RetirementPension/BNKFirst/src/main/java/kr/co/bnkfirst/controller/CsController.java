package kr.co.bnkfirst.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class CsController {
    //consultation
    @GetMapping("cs/consultation/document")
    public String cs_document() {
        return "pages/cs/consultation/document/cs_document";
    }
    @GetMapping("cs/consultation/FAQ")
    public String cs_faq() {
        return "pages/cs/consultation/FAQ/cs_faq";
    }
    @GetMapping("cs/consultation/findStore")
    public String cs_findstore() {
        return "pages/cs/consultation/findStore/cs_findstore";
    }
    @GetMapping("cs/consultation/Q&A")
    public String cs_qna() {
        return "pages/cs/consultation/Q&A/cs_qna";
    }

    //data room
    @GetMapping("cs/data_room/deposit")
    public String deposit() {
        return "pages/cs/data room/deposit/deposit";
    }
    @GetMapping("cs/data_room/fund")
    public String fund() {
        return "pages/cs/data room/fund/fund";
    }
    @GetMapping("cs/data_room/loan")
    public String loan() {
        return "pages/cs/data room/loan/loan";
    }
    @GetMapping("cs/data_room/trust")
    public String trust() {
        return "pages/cs/data room/trust/trust";
    }

    //consumerProtection
    @GetMapping("cs/consumerProtection")
    public String consumerProtection() {
        return "pages/cs/consumerProtection/cs_consumerProtection";
    }

    //howUse
    @GetMapping("cs/howUse/feeInfo")
    public String feeInfo() {
        return "pages/cs/howUse/feeinfo/feeInfo";
    }
    @GetMapping("cs/howUse/pwInfo")
    public String pwInfo() {
        return "pages/cs/howUse/pwInfo/pwInfo";
    }
    @GetMapping("cs/howUse/regProcess")
    public String regProcess() {
        return "pages/cs/howUse/regProcess/regProcess";
    }
    @GetMapping("cs/howUse/serviceTime")
    public String serviceTime() {
        return "pages/cs/howUse/serviceTime/serviceTime";
    }

    //infoProtect
    @GetMapping("cs/infoProtect/agencyGuide")
    public String agencyGuide() {
        return "pages/cs/infoProtect/agencyGuide/agencyGuide";
    }
    @GetMapping("cs/infoProtect/complaintProcessing")
    public String complaintProcessing() {
        return "pages/cs/infoProtect/complaintProcessing/complaintProcessing";
    }
    @GetMapping("cs/infoProtect/financialProtection")
    public String financialProtection() {
        return "pages/cs/infoProtect/financialProtection/financialProtection";
    }
    @GetMapping("cs/infoProtect/protectionInformation")
    public String protectionInformation() {
        return "pages/cs/infoProtect/protectionInformation/protectionInformation";
    }

    //main
    @GetMapping("cs/main")
    public String cs_main() {
        return "pages/cs/main/cs_main";
    }

    //productsRoom
    @GetMapping("cs/productsRoom/AnyProduct")
    public String AnyProduct() {
        return "pages/cs/productsRoom/AnyProduct/AnyProduct";
    }
    @GetMapping("cs/productsRoom/bankManual")
    public String bankManual() {
        return "pages/cs/productsRoom/bankManual/bankManual";
    }
    @GetMapping("cs/productsRoom/depositProduct")
    public String depositProduct() {
        return "pages/cs/productsRoom/depositProduct/depositProduct";
    }
    @GetMapping("cs/productsRoom/serviceFee")
    public String serviceFee() {
        return "pages/cs/productsRoom/serviceFee/serviceFee";
    }
}
