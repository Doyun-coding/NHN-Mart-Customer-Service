package com.nhnacademy.nhnmartcustomerservice.controller.user;

import com.nhnacademy.nhnmartcustomerservice.domain.Inquiry;
import com.nhnacademy.nhnmartcustomerservice.domain.User;
import com.nhnacademy.nhnmartcustomerservice.service.InquiryService;
import com.nhnacademy.nhnmartcustomerservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("/cs")
public class CsController {

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
    public String cs(@RequestParam(value = "id") String id, Model model) {
        User user = (User) model.getAttribute("user");
        if(Objects.isNull(user)) {
            return "redirect:/login";
        }

        String userId = user.getId();
        List<Inquiry> inquiries = inquiryService.getInquiries(userId);
        model.addAttribute("inquiries", inquiries);
        model.addAttribute("userId", userId);

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

}
