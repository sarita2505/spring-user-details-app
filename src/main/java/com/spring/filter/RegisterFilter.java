package com.spring.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;


public class RegisterFilter {
@Qualifier("customAuthenticationFilter")
    @Autowired
    AuthenticationFilter filter;
    @Bean
    public FilterRegistrationBean<AuthenticationFilter> filterRegistrationBean(){
    FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
    filterRegistrationBean.setFilter(filter);
    filterRegistrationBean.addUrlPatterns("/login");
    return filterRegistrationBean;
}
}
