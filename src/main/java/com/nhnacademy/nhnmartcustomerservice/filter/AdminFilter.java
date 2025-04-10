package com.nhnacademy.nhnmartcustomerservice.filter;

import com.nhnacademy.nhnmartcustomerservice.domain.User;
import com.nhnacademy.nhnmartcustomerservice.domain.auth.Auth;
import com.nhnacademy.nhnmartcustomerservice.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

@Component
public class AdminFilter implements Filter {

    @Autowired
    private UserService userService;

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if(Objects.nonNull(req.getParameter("id"))) {
            String id = req.getParameter("id");

            if(Objects.nonNull(userService.getUser(id))) {
                User user = userService.getUser(id);

                if(user.getAuth().equals(Auth.ADMIN)) {
                    filterChain.doFilter(request, response);
                }

            }

        }

    }

}
