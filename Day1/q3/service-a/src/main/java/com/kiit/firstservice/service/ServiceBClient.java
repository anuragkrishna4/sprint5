package com.kiit.firstservice.service;

import com.kiit.firstservice.util.JwtUtil;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ServiceBClient {

    private final RestTemplate restTemplate;
    private final JwtUtil jwtUtil;

    public ServiceBClient(JwtUtil jwtUtil) {
        this.restTemplate = new RestTemplate();
        this.jwtUtil = jwtUtil;
    }

    public String callProtectedApi() {
        String jwt = jwtUtil.generateToken("service-a", List.of("ROLE_SERVICE_A"));

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(jwt);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:8082/api/secure-data",
                HttpMethod.GET,
                requestEntity,
                String.class
        );

        return response.getBody();
    }
}
