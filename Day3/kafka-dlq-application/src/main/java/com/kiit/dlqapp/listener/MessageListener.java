package com.kiit.dlqapp.listener;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "input-topic", groupId = "dlq-group")
    public void listen(ConsumerRecord<String, String> record) throws Exception {
        try {
            String value = record.value();
            JsonNode jsonNode = objectMapper.readTree(value);
            String userId = jsonNode.get("userId").asText();
            System.out.println("Valid message: userId = " + userId);
        } catch (Exception e) {
            System.err.println("Invalid message: " + record.value());
            throw e; // Force error handler to trigger DLQ
        }
    }
}
