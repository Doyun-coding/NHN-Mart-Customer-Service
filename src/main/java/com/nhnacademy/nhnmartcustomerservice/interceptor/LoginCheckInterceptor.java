package com.nhnacademy.nhnmartcustomerservice.interceptor;

import com.nhnacademy.nhnmartcustomerservice.exception.NotLoginException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        boolean loginCheck = false;

        Cookie[] cookies = request.getCookies();
        for(int i = 0; i < cookies.length; i++) {
            if(cookies[i].getName().equals("SESSION")) {
                String value = cookies[i].getValue();

                if(session.getId().equals(value)) {
                    loginCheck = true;
                    break;
                }

            }
        }

        if(!loginCheck) {
            throw new NotLoginException("로그인을 먼저 해주세요!");
        }

        return loginCheck;
    }
}
