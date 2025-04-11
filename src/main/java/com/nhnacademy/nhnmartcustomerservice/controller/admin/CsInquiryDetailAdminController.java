package com.nhnacademy.nhnmartcustomerservice.controller.admin;

import com.nhnacademy.nhnmartcustomerservice.domain.User;
import com.nhnacademy.nhnmartcustomerservice.domain.request.IdInquiryIdRequest;
import com.nhnacademy.nhnmartcustomerservice.exception.NotFoundInquiryException;
import com.nhnacademy.nhnmartcustomerservice.exception.NotFoundParamException;
import com.nhnacademy.nhnmartcustomerservice.exception.NotFoundUserException;
import com.nhnacademy.nhnmartcustomerservice.service.InquiryService;
import com.nhnacademy.nhnmartcustomerservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
@RequestMapping("/cs/admin/answer")
public class CsInquiryDetailAdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private InquiryService inquiryService;

    @ModelAttribute("idInquiryId")
    public IdInquiryIdRequest getIdInquiryIdRequest(@RequestParam("id") String id,
                                                    @RequestParam("inquiryId") String inquiryId) {
        if(Objects.isNull(id) || Objects.isNull(inquiryId)) {
            throw new NotFoundParamException("param 값이 정확하지 않습니다");
        }

        return new IdInquiryIdRequest(id, inquiryId);
    }

    @GetMapping("{id}, {inquiryId}")
    public String inquiryDetail(@RequestParam("id") String id,
                                @RequestParam("inquiryId") String inquiryId,
                                Model model) {
        IdInquiryIdRequest idInquiryIdRequest = (IdInquiryIdRequest) model.getAttribute("idInquiryId");
        if(Objects.isNull(idInquiryIdRequest.getId()))  {
            throw new NotFoundUserException("param ID 값에 해당하는 ADMIN이 존재하지 않습니다.");
        }
        if(Objects.isNull(idInquiryIdRequest.getInquiryId())) {
            throw new NotFoundInquiryException("param ID 값에 해당하는 Inquiry가 존재하지 않습니다.");
        }

        model.addAttribute("inquiry", inquiryService.getInquiries(inquiryId));

        return "admin/inquiryDetailAdmin";
    }

}
