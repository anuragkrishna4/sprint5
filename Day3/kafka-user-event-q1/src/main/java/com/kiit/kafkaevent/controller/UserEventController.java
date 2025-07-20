package com.kiit.kafkaevent.controller;

import com.kiit.kafkaevent.kafka.KafkaProducerService;
import com.kiit.kafkaevent.model.UserEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
public class UserEventController {

    private final KafkaProducerService producerService;

    public UserEventController(KafkaProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping
    public ResponseEntity<String> publishEvent(@RequestBody UserEvent event) {
        producerService.sendUserEvent(event);
        return ResponseEntity.ok("Event published to Kafka");
    }
}
