package com.kiit.daoauth.service;

import com.kiit.daoauth.model.AppUser;
import com.kiit.daoauth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository repo;
    @Override
    public UserDetails loadUserByUsername(String u) {
        AppUser au = repo.findByUsername(u)
                .orElseThrow(() -> new UsernameNotFoundException("User"));
        return User.withUsername(au.getUsername())
                .password(au.getPassword())
                .roles(au.getRole())
                .build();
    }
}

