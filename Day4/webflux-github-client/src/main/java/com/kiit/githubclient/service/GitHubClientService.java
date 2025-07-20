package com.kiit.githubclient.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class GitHubClientService {

    private final WebClient webClient;

    public GitHubClientService(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("https://api.github.com").build();
    }

    public Mono<String> fetchGitHubUser(String username) {
        return webClient
                .get()
                .uri("/users/{username}", username)
                .retrieve()
                .bodyToMono(String.class); // Return raw JSON as string
    }
}
