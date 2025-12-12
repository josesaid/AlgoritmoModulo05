package com.mx.development.said.microservice.orders.service.kafka;

import com.mx.development.said.microservice.orders.dto.StoreMessageItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InventoryKafkaProducer {

    private final KafkaTemplate<String, StoreMessageItem> kafkaTemplate;

    @Value("${topics.inventory}")
    private String inventoryTopic;

    public InventoryKafkaProducer(KafkaTemplate<String, StoreMessageItem> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendInventoryEvent(StoreMessageItem storeMessageItem) {
        log.info("Sending event to Kafka: {}", storeMessageItem);
        kafkaTemplate.send(inventoryTopic, storeMessageItem.getOrderId(), storeMessageItem);
    }

}

