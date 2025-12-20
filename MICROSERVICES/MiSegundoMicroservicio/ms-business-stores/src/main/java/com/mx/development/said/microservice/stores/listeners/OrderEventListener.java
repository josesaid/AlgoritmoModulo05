package com.mx.development.said.microservice.stores.listeners;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mx.development.said.microservice.stores.dto.RequestStore;
import com.mx.development.said.microservice.stores.dto.ResponseStore;
import com.mx.development.said.microservice.stores.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author josesaidolanogarcia
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class OrderEventListener {

    private final StoreService storeService;

    @KafkaListener(topics = "orders-pending-topic", groupId = "micro-order")
    public ResponseStore listen(String message) throws JsonProcessingException {
        log.info("OrderEventListener - Order received: {}", message);

        ObjectMapper mapper = new ObjectMapper();
        RequestStore venta = mapper.readValue(message, RequestStore.class);
        log.info("Venta realizada: {}", venta);

        //status = ORDER_IN_REVIEW
        //System.out.println(venta.getStatus()==null?"SAID ES NULO" : "----->>"+venta.getStatus()+"<<<-----");

        ResponseStore ventaSalida = storeService.createStoreSell(venta);
        if(ventaSalida.getId()!=null){
            log.info("ventaSalida - status: {}", ventaSalida.getStatus());
            if(ventaSalida.getStatus().equalsIgnoreCase("ORDER_DELIVERED")){
                ventaSalida.setStatus("ORDER_COMPLETED");
                log.info("La orden ha sido pagada y entregada, por lo tanto cerramos la venta: {}", ventaSalida);
            }
        }
        return ventaSalida;

    }

}
