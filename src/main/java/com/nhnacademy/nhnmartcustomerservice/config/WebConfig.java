package com.nhnacademy.nhnmartcustomerservice.config;

import com.nhnacademy.nhnmartcustomerservice.filter.AdminFilter;
import com.nhnacademy.nhnmartcustomerservice.filter.UserFilter;
import com.nhnacademy.nhnmartcustomerservice.interceptor.LoginCheckInterceptor;
import com.nhnacademy.nhnmartcustomerservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private UserService userService;

    @Bean
    public FilterRegistrationBean<AdminFilter> adminFilter() {
        FilterRegistrationBean<AdminFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new AdminFilter(userService));
        registrationBean.addUrlPatterns("/cs/admin");
        registrationBean.addUrlPatterns("/cs/admin/*");

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<UserFilter> userFilter() {
        FilterRegistrationBean<UserFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new UserFilter(userService));
        registrationBean.addUrlPatterns("/cs");
        registrationBean.addUrlPatterns("/cs/inquiry");
        registrationBean.addUrlPatterns("/cs/inquiryDetail");

        return registrationBean;
    }

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .excludePathPatterns("/", "/error", "/login", "/index");
    }


}
