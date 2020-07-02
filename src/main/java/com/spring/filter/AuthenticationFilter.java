package com.spring.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private User user;
    private static ObjectMapper mapper = new ObjectMapper();

    public AuthenticationFilter() {

    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        User loginUser = null;

        try {
            user = mapper.readValue(request.getReader(), User.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return super.attemptAuthentication(request, response);
    }

    @Override
    protected String obtainPassword(HttpServletRequest request) {
        return user.getPassword();
    }

    @Override
    protected String obtainUsername(HttpServletRequest request) {
        return user.getUsername();
    }
}
