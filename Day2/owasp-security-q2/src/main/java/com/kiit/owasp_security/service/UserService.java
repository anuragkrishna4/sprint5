package com.kiit.owasp_security.service;

import com.kiit.owasp_security.entity.User;
import com.kiit.owasp_security.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    public User register(String username, String password) {
        User user = User.builder()
                .username(username)
                .password(encoder.encode(password))
                .role("USER")
                .build();
        return repo.save(user);
    }

    public User getByUsername(String username) {
        return repo.findByUsername(username).orElse(null);
    }
}
