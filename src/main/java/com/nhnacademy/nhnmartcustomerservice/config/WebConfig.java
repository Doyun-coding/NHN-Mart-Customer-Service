package com.nhnacademy.nhnmartcustomerservice.config;

import com.nhnacademy.nhnmartcustomerservice.filter.AdminFilter;
import com.nhnacademy.nhnmartcustomerservice.filter.UserFilter;
import com.nhnacademy.nhnmartcustomerservice.interceptor.LoginCheckInterceptor;
import com.nhnacademy.nhnmartcustomerservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
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

//    @Bean
//    public MultipartResolver multipartResolver() {
//        return new StandardServletMultipartResolver();
//    }
//
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/upload").setViewName("upload");
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:/Users/kimdoyun/Downloads/");
    }


}
