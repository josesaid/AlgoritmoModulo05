package com.mx.development.said.microservice.orders.service.kafka;

import com.mx.development.said.microservice.orders.dto.InventoryEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InventoryKafkaProducer {

    private final KafkaTemplate<String, InventoryEvent> kafkaTemplate;

    @Value("${topics.inventory}")
    private String inventoryTopic;

    public InventoryKafkaProducer(KafkaTemplate<String, InventoryEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendInventoryEvent(InventoryEvent event) {
        log.info("Sending event to Kafka: {}", event);
        kafkaTemplate.send(inventoryTopic, event.getProductId(), event);
    }
}

