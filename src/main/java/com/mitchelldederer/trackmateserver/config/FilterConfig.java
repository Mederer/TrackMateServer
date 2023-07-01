package com.mitchelldederer.trackmateserver.config;


import com.mitchelldederer.trackmateserver.filters.RequestLoggingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<RequestLoggingFilter> loggingFilter() {
        FilterRegistrationBean<RequestLoggingFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new RequestLoggingFilter());
        registrationBean.addUrlPatterns("/jobs");
        registrationBean.setOrder(1);

        return registrationBean;
    }
}
