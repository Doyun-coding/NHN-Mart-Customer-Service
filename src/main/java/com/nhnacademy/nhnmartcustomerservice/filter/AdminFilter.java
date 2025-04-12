package com.nhnacademy.nhnmartcustomerservice.filter;

import com.nhnacademy.nhnmartcustomerservice.domain.User;
import com.nhnacademy.nhnmartcustomerservice.domain.auth.Auth;
import com.nhnacademy.nhnmartcustomerservice.exception.NotAdminException;
import com.nhnacademy.nhnmartcustomerservice.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
public class AdminFilter implements Filter {

    private final UserService userService;

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if(Objects.nonNull(req.getParameter("id"))) {
            String id = req.getParameter("id");

            log.info("AdminFilter");

            if(Objects.nonNull(userService.getUser(id))) {
                User user = userService.getUser(id);

                if(user.getAuth().equals(Auth.ADMIN)) {
                    filterChain.doFilter(request, response);
                    return;
                }

            }
        }
        log.info("throw AdminFilter");

        throw new NotAdminException("Admin 계정이 아닙니다");
    }

}
