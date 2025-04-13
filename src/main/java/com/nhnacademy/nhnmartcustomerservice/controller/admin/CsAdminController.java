package com.nhnacademy.nhnmartcustomerservice.controller.admin;

import com.nhnacademy.nhnmartcustomerservice.domain.Inquiry;
import com.nhnacademy.nhnmartcustomerservice.domain.User;
import com.nhnacademy.nhnmartcustomerservice.domain.request.IdCategoryRequest;
import com.nhnacademy.nhnmartcustomerservice.exception.NotFoundUserException;
import com.nhnacademy.nhnmartcustomerservice.exception.ValidationFailedException;
import com.nhnacademy.nhnmartcustomerservice.service.InquiryService;
import com.nhnacademy.nhnmartcustomerservice.service.UserService;
import com.nhnacademy.nhnmartcustomerservice.validator.IdCategoryRequestValidator;
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

@Controller
@RequestMapping("/cs/admin")
public class CsAdminController {

    @Autowired
    private IdCategoryRequestValidator validator;

    @Autowired
    private UserService userService;

    @Autowired
    private InquiryService inquiryService;

    @ModelAttribute("admin")
    public User getUser(@RequestParam("id") String id) {
        User user = userService.getUser(id);
        if(Objects.isNull(user)) {
            throw new NotFoundUserException("ID에 해당하는 유저가 없습니다");
        }

        return user;
    }

    @GetMapping
    public String csAdminController(@Validated @ModelAttribute IdCategoryRequest idCategoryRequest,
                                    BindingResult bindingResult,
                                    Model model) {
        if(bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        User admin = (User) model.getAttribute("admin");
        String adminId = idCategoryRequest.getId();
        model.addAttribute("adminId", adminId);

        String category = idCategoryRequest.getCategory();
        if(Objects.isNull(category) || category.equals("전체보기")) {
            List<Inquiry> inquiries = inquiryService.getNotAnsweredInquiries();
            model.addAttribute("inquiries", inquiries);
        }
        else {
            List<Inquiry> inquiries = inquiryService.getNotAnsweredInquiresByCategory(category);
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

        return "admin/csAdminForm";
    }

    @InitBinder("idCategoryRequest")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

}
