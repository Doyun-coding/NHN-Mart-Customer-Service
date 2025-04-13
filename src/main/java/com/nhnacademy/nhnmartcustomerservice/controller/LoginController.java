package com.nhnacademy.nhnmartcustomerservice.controller;

import com.nhnacademy.nhnmartcustomerservice.domain.User;
import com.nhnacademy.nhnmartcustomerservice.domain.auth.Auth;
import com.nhnacademy.nhnmartcustomerservice.domain.request.UserRequest;
import com.nhnacademy.nhnmartcustomerservice.exception.NotMatchesIdPasswordException;
import com.nhnacademy.nhnmartcustomerservice.exception.ValidationFailedException;
import com.nhnacademy.nhnmartcustomerservice.service.UserService;
import com.nhnacademy.nhnmartcustomerservice.validator.UserRequestValidator;
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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.Objects;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserRequestValidator validator;

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

            if(Objects.nonNull(currentUser) && cookie.getValue().equals(session.getId())) {
                if(currentUser.getAuth().equals(Auth.ADMIN)) {
                    return "redirect:/cs/admin?id=" + currentUser.getId();
                }

                return "redirect:/cs?id=" + currentUser.getId();
            }
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
            throw new ValidationFailedException(bindingResult);
        }

        String id = userRequest.getId();
        String password = userRequest.getPassword();

        if(!userService.isMatches(id, password)) {
            throw new NotMatchesIdPasswordException("입력하신 ID와 Password에 일치하는 계정이 존재하지 않습니다.");
        }

        HttpSession session = request.getSession();

        Cookie cookie = new Cookie("SESSION", session.getId());
        response.addCookie(cookie);

        this.currentUser = userService.getUser(id);

        if(currentUser.getAuth().equals(Auth.ADMIN)) {
            return "redirect:/cs/admin?id=" + id;
        }

        return "redirect:/cs?id=" + id;
    }

    @InitBinder("userRequest")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

}
