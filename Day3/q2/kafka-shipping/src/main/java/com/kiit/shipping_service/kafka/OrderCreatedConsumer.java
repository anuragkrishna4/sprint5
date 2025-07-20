package com.kiit.shipping_service.kafka;

import com.kiit.shipping_service.model.Order;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderCreatedConsumer {

    @KafkaListener(topics = "order.created", groupId = "shipping-group", containerFactory = "orderKafkaListenerFactory")
    public void handleOrderCreated(ConsumerRecord<String, Order> record) {
        Order order = record.value();
        System.out.println("ShippingService: Received order -> " + order);
        System.out.println("ShippingService: Reserving stock and confirming shipment for Order ID: " + order.getOrderId());
    }
}
