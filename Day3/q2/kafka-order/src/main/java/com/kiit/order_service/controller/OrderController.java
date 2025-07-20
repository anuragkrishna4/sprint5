package com.kiit.order_service.controller;

import com.kiit.order_service.kafka.OrderProducer;
import com.kiit.order_service.model.Order;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderProducer orderProducer;

    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @PostMapping
    public String createOrder(@RequestBody Order order) {
        orderProducer.sendOrder(order);
        return "Order created and sent to Kafka!";
    }
}
