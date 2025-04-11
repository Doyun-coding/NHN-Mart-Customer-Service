package com.nhnacademy.nhnmartcustomerservice.advice;

import com.nhnacademy.nhnmartcustomerservice.exception.NotAdminException;
import com.nhnacademy.nhnmartcustomerservice.exception.NotFoundUserException;
import com.nhnacademy.nhnmartcustomerservice.exception.NotLoginException;
import com.nhnacademy.nhnmartcustomerservice.exception.NotUserException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WebControllerAdvice {

    @ExceptionHandler(NotFoundUserException.class)
    public String notFoundUserException(Exception ex, Model model) {
        model.addAttribute("exception", ex);

        return "error";
    }

    @ExceptionHandler(NotLoginException.class)
    public String notLoginException(Exception ex, Model model) {
        model.addAttribute("exception", ex);

        return "error";
    }

    @ExceptionHandler(NotAdminException.class)
    public String notAdminException(Exception ex, Model model) {
        model.addAttribute("exception", ex);

        return "error";
    }

    @ExceptionHandler(NotUserException.class)
    public String notUserException(Exception ex, Model model) {
        model.addAttribute("exception", ex);

        return "error";
    }

}
