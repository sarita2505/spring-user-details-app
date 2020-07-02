package com.spring.controller;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;


@RestController

public class HelloController {
    @GetMapping("/hello")
    public String getData() {
        return ("<h1> helloworld </h1>");
    }

    @GetMapping("/user")
    public String getUser() {
        return ("<h1> hello user </h1>"+ SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    @GetMapping("/admin")
    public String getAdmin() {
        return ("<h1> hello admin </h1>");
    }

    @GetMapping("/")
    public String defaultPage() {
        return ("<h1> hello all </h1>");
    }

    @GetMapping("/accessDenied")
    public String accessDenied() {
        return ("<h1> accessDenied </h1>");
    }

    @GetMapping("/loginFailed")
    public String loginFailed() {
        return ("<h1> loginFailed</h1>");
    }
}