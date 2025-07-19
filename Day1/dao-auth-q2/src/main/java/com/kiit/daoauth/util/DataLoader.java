package com.kiit.daoauth.util;

import com.kiit.daoauth.model.AppUser;
import com.kiit.daoauth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final UserRepository repo;
    private final PasswordEncoder pe;
    @Override public void run(String... args){
        if(repo.count()==0){
            repo.save(new AppUser(null,"john",pe.encode("john123"),"USER"));
            repo.save(new AppUser(null,"admin",pe.encode("admin123"),"ADMIN"));
        }
    }
}

