package com.nhnacademy.nhnmartcustomerservice.controller.user;

import com.nhnacademy.nhnmartcustomerservice.domain.Inquiry;
import com.nhnacademy.nhnmartcustomerservice.domain.User;
import com.nhnacademy.nhnmartcustomerservice.domain.request.IdCategoryRequest;
import com.nhnacademy.nhnmartcustomerservice.exception.ValidationFailedException;
import com.nhnacademy.nhnmartcustomerservice.service.InquiryService;
import com.nhnacademy.nhnmartcustomerservice.service.UserService;
import com.nhnacademy.nhnmartcustomerservice.validator.IdCategoryRequestValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("/cs")
public class CsController {

    @Autowired
    private IdCategoryRequestValidator validator;

    @Autowired
    private UserService userService;
    @Autowired
    private InquiryService inquiryService;

    @ModelAttribute("user")
    public User getUser(@RequestParam(value = "id") String id) {
        if(Objects.isNull(id)) {
            return null;
        }
        return userService.getUser(id);
    }

    @GetMapping
    public String cs(@Validated @ModelAttribute IdCategoryRequest idCategoryRequest,
                     BindingResult bindingResult,
                     Model model) {
        if(bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        User user = (User) model.getAttribute("user");
        if(Objects.isNull(user)) {
            return "redirect:/login";
        }

        String id = idCategoryRequest.getId();
        String category = idCategoryRequest.getCategory();

        String userId = user.getId();
        model.addAttribute("userId", userId);

        if(Objects.isNull(category) || category.equals("전체보기")) {
            List<Inquiry> inquiries = inquiryService.getInquiries(userId);
            model.addAttribute("inquiries", inquiries);
        }
        else {
            List<Inquiry> inquiries = inquiryService.getInquiryByInquiryIdCategory(id, category);
            model.addAttribute("inquiries", inquiries);
        }

        List<String> categories = new ArrayList<>();
        categories.add("전체보기");
        categories.add("불만접수");
        categories.add("제안");
        categories.add("환불/교환");
        categories.add("칭찬해요");
        categories.add("기타문의");

        model.addAttribute("categories", categories);

        return "user/csForm";
    }

    @PostMapping
    public String postInquiryBtn(@RequestParam(value = "id") String id, Model model) {
        User user = (User) model.getAttribute("user");
        if(Objects.isNull(user)) {
            return "redirect:/login";
        }

        String userId = user.getId();

        return "redirect:/cs/inquiry?id=" + userId;
    }

    @InitBinder("idCategoryRequest")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

}
