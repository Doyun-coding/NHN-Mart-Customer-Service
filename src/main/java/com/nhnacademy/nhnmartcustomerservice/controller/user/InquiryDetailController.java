package com.nhnacademy.nhnmartcustomerservice.controller.user;

import com.nhnacademy.nhnmartcustomerservice.domain.Answer;
import com.nhnacademy.nhnmartcustomerservice.domain.Inquiry;
import com.nhnacademy.nhnmartcustomerservice.domain.request.IdInquiryIdRequest;
import com.nhnacademy.nhnmartcustomerservice.exception.ValidationFailedException;
import com.nhnacademy.nhnmartcustomerservice.service.AnswerService;
import com.nhnacademy.nhnmartcustomerservice.service.InquiryService;
import com.nhnacademy.nhnmartcustomerservice.validator.IdInquiryIdRequestValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/cs/inquiryDetail")
@AllArgsConstructor
public class InquiryDetailController {

    @Autowired
    private IdInquiryIdRequestValidator validator;

    @Autowired
    private InquiryService inquiryService;
    @Autowired
    private AnswerService answerService;

    @GetMapping
    public String inquiryDetail(@Validated @ModelAttribute IdInquiryIdRequest idInquiryIdRequest,
                                BindingResult bindingResult,
                                Model model) {
        if(bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        String id = idInquiryIdRequest.getId();
        long inquiryId = idInquiryIdRequest.getInquiryId();

        log.info("id:{}", id);
        log.info("inquiryId:{}", inquiryId);

        Inquiry inquiry = inquiryService.getInquiry(id, inquiryId);
        model.addAttribute("inquiry", inquiry);

        if(inquiry.isAnswered()) {
            Answer answer = answerService.getAnswerByInquiryId(inquiryId);
            log.info("answerContent:{}", answer.getAnswerContent());
            log.info("answerCreatedTime:{}", answer.getAnswerCreatedTime());
            log.info("answerAdminName:{}", answer.getAnswerAdminName());

            model.addAttribute("answer", answer);
        }

        return "user/inquiryDetailForm";
    }

    @InitBinder("idInquiryIdRequest")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

}
