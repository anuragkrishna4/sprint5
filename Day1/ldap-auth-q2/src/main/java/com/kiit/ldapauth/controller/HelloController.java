package com.kiit.ldapauth.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/")
    public String home(){
        return "Public";
    }

    @GetMapping("/secure")
    public String secure(@AuthenticationPrincipal UserDetails u){
        return "Hello " + u.getUsername() + " " + u.getAuthorities();
    }
}
