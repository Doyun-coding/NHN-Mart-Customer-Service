package com.nhnacademy.nhnmartcustomerservice.controller;

import com.nhnacademy.nhnmartcustomerservice.domain.User;
import com.nhnacademy.nhnmartcustomerservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("/cs/inquiry")
public class InquiryController {

    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public User getUser(@PathVariable(value = "id") String id, Model model) {
        if(Objects.isNull(id)) {
            return null;
        }

        return userService.getUser(id);
    }

    @GetMapping("/{id}")
    public String inquiry(@PathVariable(value = "id") String id, Model model) {
        User user = userService.getUser(id);
        if(Objects.isNull(user)) {
            return "redirect:/login";
        }

        String userId = user.getId();


        return "inquiryForm";
    }


}
