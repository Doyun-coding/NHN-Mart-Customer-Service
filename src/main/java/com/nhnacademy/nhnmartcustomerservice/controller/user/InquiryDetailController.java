package com.nhnacademy.nhnmartcustomerservice.controller.user;

import com.nhnacademy.nhnmartcustomerservice.domain.Inquiry;
import com.nhnacademy.nhnmartcustomerservice.domain.request.IdInquiryIdRequest;
import com.nhnacademy.nhnmartcustomerservice.service.InquiryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/cs/inquiryDetail")
public class InquiryDetailController {

    @Autowired
    private InquiryService inquiryService;

    @GetMapping
    public String inquiryDetail(@ModelAttribute IdInquiryIdRequest idInquiryIdRequest, Model model) {
        String id = idInquiryIdRequest.getId();
        long inquiryId = Long.parseLong(idInquiryIdRequest.getInquiryId());

        log.info("id:{}", id);
        log.info("inquiryId:{}", inquiryId);

        Inquiry inquiry = inquiryService.getInquiry(id, inquiryId);
        model.addAttribute("inquiry", inquiry);

        return "inquiryDetailForm";
    }

}
