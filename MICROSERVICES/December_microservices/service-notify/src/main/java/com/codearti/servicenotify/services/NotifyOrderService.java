package com.codearti.servicenotify.services;

import com.codearti.servicenotify.dto.NotifyResponse;
import com.codearti.servicenotify.models.dto.EventNotify;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface NotifyOrderService {
    Mono<Boolean> saveNotify(EventNotify eventNotify);
    Mono<NotifyResponse> getNotify(String id);
    Flux<NotifyResponse> getAllNotify();
}

