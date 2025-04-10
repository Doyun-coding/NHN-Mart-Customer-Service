package com.nhnacademy.nhnmartcustomerservice.controller;

import com.nhnacademy.nhnmartcustomerservice.domain.User;
import com.nhnacademy.nhnmartcustomerservice.domain.request.UserRequest;
import com.nhnacademy.nhnmartcustomerservice.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.Objects;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @Getter
    @Setter
    User currentUser;

    @GetMapping
    public String getLogin(@CookieValue(value = "SESSION", required = false) Cookie cookie,
                           HttpServletRequest request) {

        if(Objects.nonNull(cookie) && Objects.nonNull(cookie.getValue()) && StringUtils.hasText(cookie.getValue())) {
            HttpSession session = request.getSession();

            if(Objects.nonNull(currentUser) && cookie.getValue().equals(session.getId()))
                return "redirect:/inquiry/" + currentUser.getId();
        }

        return "loginForm";
    }

    @PostMapping
    public String postLogin(@Valid @ModelAttribute UserRequest userRequest,
                            BindingResult bindingResult,
                            HttpServletRequest request,
                            HttpServletResponse response,
                            Model model) {
        if(bindingResult.hasErrors()) {
            throw new RuntimeException();
        }

        String id = userRequest.getId();
        String password = userRequest.getPassword();

        if(!userService.isMatches(id, password)) {
            return "redirect:/login";
        }

        HttpSession session = request.getSession();

        Cookie cookie = new Cookie("SESSION", session.getId());
        response.addCookie(cookie);

        this.currentUser = userService.getUser(id);

        return "redirect:/cs?id=" + id;
    }

}
