package com.spring.filter;

import com.spring.utils.SecurityTokenUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebFilter("/")
public class TokenValidationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("token validation initialized");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Boolean isTokenExist = false;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String key = request.getHeader("authentication");
        if (key != null) {
            isTokenExist = true;
            Authentication authentication = SecurityTokenUtil.get(key);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            authentication.setAuthenticated(true);
        }
        else {
            response.getWriter().write("header is invalid");
        }
    }
}
