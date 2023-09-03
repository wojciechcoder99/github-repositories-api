package com.example.githubrepositories.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public FilterRegistrationBean<AcceptHeaderFilter> acceptHeaderFilter() {
        FilterRegistrationBean<AcceptHeaderFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AcceptHeaderFilter());
        registrationBean.addUrlPatterns("/api/github/*");
        return registrationBean;
    }
}
