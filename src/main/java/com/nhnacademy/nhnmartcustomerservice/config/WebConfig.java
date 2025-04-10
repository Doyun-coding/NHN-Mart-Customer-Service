package com.nhnacademy.nhnmartcustomerservice.config;

import com.nhnacademy.nhnmartcustomerservice.filter.AdminFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean<AdminFilter> adminFilter() {
        FilterRegistrationBean<AdminFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new AdminFilter());
        registrationBean.addUrlPatterns("/cs/admin");
        registrationBean.addUrlPatterns("/cs/admin/*");

        return registrationBean;
    }


}
