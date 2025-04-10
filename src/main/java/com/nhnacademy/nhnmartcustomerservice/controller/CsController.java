package com.nhnacademy.nhnmartcustomerservice.controller;

import com.nhnacademy.nhnmartcustomerservice.domain.Inquiry;
import com.nhnacademy.nhnmartcustomerservice.domain.User;
import com.nhnacademy.nhnmartcustomerservice.service.InquiryService;
import com.nhnacademy.nhnmartcustomerservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public User getUser(@PathVariable(value = "id") String id) {
        if(Objects.isNull(id)) {
            return null;
        }

        return userService.getUser(id);
    }

    @GetMapping("/{id}")
    public String cs(@PathVariable(value = "id") String id, Model model) {
        User user = (User) model.getAttribute("user");
        if(Objects.isNull(user)) {
            return "redirect:/login";
        }

        String userId = user.getId();
        ArrayList<Inquiry> inquiries = inquiryService.getInquiries(userId);
        model.addAttribute("inquiries", inquiries);
        model.addAttribute("userId", userId);

        return "csForm";
    }



}
