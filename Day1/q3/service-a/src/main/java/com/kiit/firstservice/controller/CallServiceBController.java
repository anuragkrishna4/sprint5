package com.kiit.firstservice.controller;

import com.kiit.firstservice.service.ServiceBClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/call")
public class CallServiceBController {

    private final ServiceBClient serviceBClient;

    public CallServiceBController(ServiceBClient serviceBClient) {
        this.serviceBClient = serviceBClient;
    }

    @GetMapping("/serviceb")
    public ResponseEntity<String> callServiceB() {
        String result = serviceBClient.callProtectedApi();
        return ResponseEntity.ok(result);
    }
}
