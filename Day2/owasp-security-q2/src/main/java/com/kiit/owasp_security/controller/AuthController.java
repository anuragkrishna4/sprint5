package com.kiit.owasp_security.controller;

import com.kiit.owasp_security.dto.LoginRequest;
import com.kiit.owasp_security.dto.SignupRequest;
import com.kiit.owasp_security.entity.User;
import com.kiit.owasp_security.security.JwtUtils;
import com.kiit.owasp_security.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService service;
    private final JwtUtils jwtUtils;

    public AuthController(UserService service, JwtUtils jwtUtils) {
        this.service = service;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody SignupRequest req) {
        User user = service.register(req.getUsername(), req.getPassword());
        return ResponseEntity.ok("User created with ID: " + user.getId());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        User user = service.getByUsername(req.getUsername());
        if (user != null && jwtUtils.passwordMatch(req.getPassword(), user.getPassword())) {
            String token = jwtUtils.generateToken(user.getUsername());
            return ResponseEntity.ok("Bearer " + token);
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }
}
