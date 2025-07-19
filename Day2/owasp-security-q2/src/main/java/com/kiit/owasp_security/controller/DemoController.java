package com.kiit.owasp_security.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DemoController {

    @GetMapping("/public")
    public String publicApi() {
        return "This is a public API";
    }

    @GetMapping("/secure")
    public String secureApi() {
        return "This is a secured API";
    }

    @PostMapping("/xss")
    public String xssDemo(@RequestBody String input) {
        return "Echo: " + input.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
    }
}
