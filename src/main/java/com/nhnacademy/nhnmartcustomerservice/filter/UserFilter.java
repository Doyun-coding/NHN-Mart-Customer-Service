package com.nhnacademy.nhnmartcustomerservice.filter;

import com.nhnacademy.nhnmartcustomerservice.domain.User;
import com.nhnacademy.nhnmartcustomerservice.domain.auth.Auth;
import com.nhnacademy.nhnmartcustomerservice.exception.NotUserException;
import com.nhnacademy.nhnmartcustomerservice.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Objects;

@RequiredArgsConstructor
public class UserFilter implements Filter {

    private final UserService userService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if(Objects.nonNull(req.getParameter("id"))) {
            String id = req.getParameter("id");

            if(Objects.nonNull(userService.getUser(id))) {
                User user = userService.getUser(id);

                if(user.getAuth().equals(Auth.USER)) {
                    filterChain.doFilter(request, response);
                    return;
                }

            }

        }

        throw new NotUserException("User 계정이 아닙니다");
    }
}
