package com.nhnacademy.nhnmartcustomerservice.controller.user;

import com.nhnacademy.nhnmartcustomerservice.domain.Inquiry;
import com.nhnacademy.nhnmartcustomerservice.domain.User;
import com.nhnacademy.nhnmartcustomerservice.domain.request.InquiryRequest;
import com.nhnacademy.nhnmartcustomerservice.service.InquiryService;
import com.nhnacademy.nhnmartcustomerservice.service.UserService;
import com.nhnacademy.nhnmartcustomerservice.service.impl.InquiryServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("/cs/inquiry")
public class InquiryController {

    @Autowired
    private UserService userService;

    @Autowired
    private InquiryService inquiryService;

    @ModelAttribute("user")
    public User getUser(@RequestParam(value = "id") String id, Model model) {
        if(Objects.isNull(id)) {
            return null;
        }

        return userService.getUser(id);
    }

    @GetMapping
    public String inquiry(@RequestParam(value = "id") String id, Model model) {
        model.addAttribute("userId", id);

        return "inquiryForm";
    }

    @PostMapping
    public String postInquiry(@RequestParam(value = "id") String id,
                                    @ModelAttribute InquiryRequest inquiryRequest, Model model) {

        long inquiryId = InquiryServiceImpl.atomicLong.getAndIncrement();

        String title = inquiryRequest.getTitle();
        String category = inquiryRequest.getCategory();
        String content = inquiryRequest.getContent();
        LocalDateTime createdTime = LocalDateTime.now();
        String writer = userService.getUser(id).getId();

        List<String> files = inquiryRequest.getFilePath();
        boolean answered = false;

        log.info("title:{}", title);
        log.info("category:{}", category);
        log.info("content:{}", content);

        List<Inquiry> inquiries = inquiryService.getInquiries(id);
        inquiries.add(new Inquiry(inquiryId, title, category, content, createdTime, writer, files, answered));

        return "redirect:/cs?id=" + id;
    }



}
