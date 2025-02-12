package com.example.scheduledevelopproject.config;

import com.example.scheduledevelopproject.filter.LoggingFilter;
import jakarta.servlet.*;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {
    @Bean
    public FilterRegistrationBean loggingFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LoggingFilter());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/*");

        return filterRegistrationBean;
    }



//    @Bean
//    public FilterRegistrationBean loginFilter() {
//        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
//        filterRegistrationBean.setFilter(new LoginFilter());
//        filterRegistrationBean.setOrder(2);
//        filterRegistrationBean.addUrlPatterns("/*");
//
//        return filterRegistrationBean;
//    }
}
