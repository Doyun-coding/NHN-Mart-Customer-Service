package com.nhnacademy.nhnmartcustomerservice.controller.admin;

import com.nhnacademy.nhnmartcustomerservice.domain.Inquiry;
import com.nhnacademy.nhnmartcustomerservice.domain.User;
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

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/cs/admin")
public class CsAdminController {

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
    public String csAdminController(@RequestParam("id") String id, Model model) {
        User admin = (User) model.getAttribute("admin");

        List<Inquiry> inquiries = inquiryService.getNotAnsweredInquiries();
        model.addAttribute("inquiries", inquiries);
        model.addAttribute("adminId", id);

        return "admin/csAdminForm";
    }

}
