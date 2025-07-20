package com.kiit.kafkaevent.kafka;

import com.kiit.kafkaevent.model.UserEvent;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "user-events", groupId = "user-event-group")
    public void consume(ConsumerRecord<String, UserEvent> record) {
        System.out.println("Consumed event: " + record.value());
    }
}
