package com.kiit.githubclient.controller;

import com.kiit.githubclient.service.GitHubClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/github")
public class GitHubController {

    @Autowired
    private GitHubClientService gitHubClientService;

    @GetMapping("/{username}")
    public Mono<String> getUser(@PathVariable String username) {
        return gitHubClientService.fetchGitHubUser(username);
    }
}
