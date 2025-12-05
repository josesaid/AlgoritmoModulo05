package com.codearti.servicenotify.listeners;

import com.codearti.servicenotify.models.dto.EventNotify;
import com.codearti.servicenotify.services.NotifyOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderEventListerner {
    private final NotifyOrderService orderService;

    @KafkaListener(topics = "order-topic", groupId = "micro-order")
    public Mono<Void> listen(String message) {
        log.info("Order received: {}", message);

        var data = new EventNotify(
                0, message, true
        );

        return orderService.saveNotify(data)
                .doOnSubscribe(p -> log.info("Saving order notification..."))
                .doOnSuccess(p -> log.info("Order notification saved successfully."))
                .doOnError(e -> log.error("Error saving order notification: {}", e.getMessage()))
                .then();
    }

}
