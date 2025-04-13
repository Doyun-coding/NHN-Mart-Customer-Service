package com.nhnacademy.nhnmartcustomerservice.controller.admin;

import com.nhnacademy.nhnmartcustomerservice.domain.Answer;
import com.nhnacademy.nhnmartcustomerservice.domain.Inquiry;
import com.nhnacademy.nhnmartcustomerservice.domain.User;
import com.nhnacademy.nhnmartcustomerservice.domain.request.AnswerRequest;
import com.nhnacademy.nhnmartcustomerservice.domain.request.IdInquiryIdRequest;
import com.nhnacademy.nhnmartcustomerservice.exception.NotFoundInquiryException;
import com.nhnacademy.nhnmartcustomerservice.exception.NotFoundParamException;
import com.nhnacademy.nhnmartcustomerservice.exception.NotFoundUserException;
import com.nhnacademy.nhnmartcustomerservice.exception.ValidationFailedException;
import com.nhnacademy.nhnmartcustomerservice.service.AnswerService;
import com.nhnacademy.nhnmartcustomerservice.service.InquiryService;
import com.nhnacademy.nhnmartcustomerservice.service.UserService;
import com.nhnacademy.nhnmartcustomerservice.validator.AnswerRequestValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("/cs/admin/answer")
@AllArgsConstructor
public class CsInquiryDetailAdminController {

    @Autowired
    private AnswerRequestValidator validator;

    @Autowired
    private UserService userService;
    @Autowired
    private InquiryService inquiryService;
    @Autowired
    private AnswerService answerService;

    @ModelAttribute("idInquiryId")
    public IdInquiryIdRequest getIdInquiryIdRequest(@RequestParam("id") String id,
                                                    @RequestParam("inquiryId") long inquiryId) {
        IdInquiryIdRequest inquiryIdRequest = new IdInquiryIdRequest(id, inquiryId);

        if(Objects.isNull(id) || id.isEmpty() || inquiryId <= 0) {
            throw new NotFoundParamException("param 값이 정확하지 않습니다");
        }

        log.info("InquiryRequest!!");

        return inquiryIdRequest;
    }

    @GetMapping
    public String inquiryDetail(@RequestParam("id") String id,
                                @RequestParam("inquiryId") long inquiryId,
                                Model model) {
        IdInquiryIdRequest idInquiryIdRequest = (IdInquiryIdRequest) model.getAttribute("idInquiryId");
        if(Objects.isNull(idInquiryIdRequest.getId()))  {
            throw new NotFoundUserException("param ID 값에 해당하는 ADMIN이 존재하지 않습니다.");
        }
        if(Objects.isNull(idInquiryIdRequest.getInquiryId())) {
            throw new NotFoundInquiryException("param ID 값에 해당하는 Inquiry가 존재하지 않습니다.");
        }

        log.info("Inquiry Id:{}", idInquiryIdRequest.getId());
        log.info("Inquiry InquiryId:{}", idInquiryIdRequest.getInquiryId());

        Inquiry inquiry = inquiryService.getInquiryByInquiryId(inquiryId);
        model.addAttribute("inquiry", inquiry);

        model.addAttribute("adminId", id);
        model.addAttribute("inquiryId", inquiryId);

        log.info("inquiry title:{}", inquiry.getTitle());

        return "admin/inquiryDetailAdmin";
    }

    @PostMapping
    public String postInquiryDetail(@RequestParam("id") String id,
                                    @RequestParam("inquiryId") long inquiryId,
                                    @Validated @ModelAttribute AnswerRequest answerRequest,
                                    BindingResult bindingResult,
                                    Model model) {
        if(bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        long inqId = answerRequest.getInquiryId();
        String answerContent = answerRequest.getAnswerContent();

        log.info("inquiryId:{}", inqId);
        log.info("answerContent:{}", answerContent);

        User admin = userService.getUser(id);

        Answer answer = new Answer(inqId, answerContent, LocalDateTime.now(), admin.getName());
        answerService.registerAnswer(inqId, answer);

        Inquiry inquiry = inquiryService.getInquiryByInquiryId(inquiryId);
        String userId = inquiry.getId();

        Inquiry updateInquiry = new Inquiry(userId, inquiry.getInquiryId(), inquiry.getTitle(), inquiry.getCategory(), inquiry.getContent(), inquiry.getCreatedTime(), inquiry.getWriter(), inquiry.getFilePath(), true);
        inquiryService.updateInquiry(userId, updateInquiry);

        return "redirect:/cs/admin?id=" + id;
    }

    @InitBinder("answerContent")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

}
