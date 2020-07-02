package com.spring.utils;

import org.springframework.context.annotation.Bean;

import javax.servlet.http.HttpServletRequest;

public class UserContextClass {
    @Bean
    public String getToken(HttpServletRequest request,String key){
        String token=request.getHeader(key);
        return token;
    }


}
