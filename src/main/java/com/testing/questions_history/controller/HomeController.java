package com.testing.questions_history.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping
    public String firstPage(){
        return "index";
    }

    @GetMapping("/home")
    public String home(){
        return "home-admin";
    }

//    @PreAuthorize("hasRole('ADMIN')")
//    @GetMapping("/home")
//    public String homeAdmin(){
//        return "home-admin";
//    }
//
//    @PreAuthorize("hasRole('USER')")
//    @GetMapping("/home")
//    public String homeUser(){
//        return "home-user";
//    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/error")
    public String error(){
        return "error";
    }
}
