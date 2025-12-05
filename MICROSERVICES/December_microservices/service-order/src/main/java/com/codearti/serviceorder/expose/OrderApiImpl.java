package com.codearti.serviceorder.expose;

import com.codearti.serviceorder.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author josesaidolanogarcia
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class OrderApiImpl implements com.codearti.serviceorder.api.OrdenesApiDelegate {
    private final OrderService orderService;

    @Override
    public Mono<com.codearti.serviceorder.dto.OrderResponse> createOrder(Mono<com.codearti.serviceorder.dto.OrderRequest> orderRequest,
                                                                         ServerWebExchange exchange) {
        return orderRequest.flatMap(orderService::createOrder);
    }

    @Override
    public Mono<com.codearti.serviceorder.dto.OrderResponse> getOrder(Integer orderId,
                                                                      ServerWebExchange exchange) {
        return orderService.getOrderById(orderId);
    }

    @Override
    public Flux<com.codearti.serviceorder.dto.OrderResponse> listOrders(ServerWebExchange exchange) {
        return orderService.getListOrders();
    }

    @Override
    public Mono<Void> updateOrder(Integer orderId,
                                  ServerWebExchange exchange) {
        return orderService.updateOrder(orderId)
                .then();
    }
}

