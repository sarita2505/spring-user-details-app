package com.spring.config;

import com.spring.filter.AuthenticationFilter;
import com.spring.handler.CustomAccessDeniedHandler;
import com.spring.handler.CustomAuthenticationFailureHandler;
import com.spring.handler.CustomAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//this inmemory security config will work in postman because i hear i disable the form login and instead of spring securities default
//login url i.e /login i provided /authenticate

//@EnableWebSecurity
public class InmemorySecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().
                withUser(User.withUsername("ram").password(passwordEncoder().encode("ram")).roles("admin")).
                withUser(User.withUsername("sita").password(passwordEncoder().encode("sita")).roles("user")).
                withUser(User.withUsername("xyz").password(passwordEncoder().encode("xyz")).roles("all"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable();
        http.authorizeRequests().
                antMatchers("/admin").hasRole("admin").
                antMatchers("/user").hasAnyRole("admin","user").
                antMatchers("/authenticate", "/", "/accessDenied", "/loginFailed").permitAll().anyRequest().authenticated().
                and().formLogin().disable().
                exceptionHandling().accessDeniedHandler(accessDeniedHandler());
        http.csrf().disable();
        http.addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean(name = "authenticationTokenFilter")
    public AuthenticationFilter authenticationTokenFilter() throws Exception {
        AuthenticationFilter filter = new AuthenticationFilter();
        filter.setAuthenticationManager(authenticationManager());
        filter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/authenticate", "POST"));
        filter.setAuthenticationSuccessHandler(new CustomAuthenticationSuccessHandler());
        filter.setAuthenticationFailureHandler(new CustomAuthenticationFailureHandler());
        return filter;

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }
}
