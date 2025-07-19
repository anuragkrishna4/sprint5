package com.nisum.authentication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/api/secure")
    public String secureEndpoint() {
        return "This is a secure endpoint. Authenticated access only!";
    }

    @GetMapping("/")
    public String home() {
        return "Welcome to the public home page!";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "Admin dashboard – For ADMIN role only!";
    }

    @GetMapping("/user")
    public String userPage() {
        return "User dashboard – For USER and ADMIN roles!";
    }
}
