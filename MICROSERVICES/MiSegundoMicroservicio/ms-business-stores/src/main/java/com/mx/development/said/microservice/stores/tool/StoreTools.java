package com.mx.development.said.microservice.stores.tool;

import com.mx.development.said.microservice.stores.dto.RequestStore;
import com.mx.development.said.microservice.stores.dto.ResponseOrder;
import com.mx.development.said.microservice.stores.dto.ResponseStore;
import com.mx.development.said.microservice.stores.entity.StoreEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Slf4j
public class StoreTools {

    private final RestTemplate restTemplate;

    public StoreTools(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public static StoreEntity requestProductToProductEntity(RequestStore venta) {
        StoreEntity storeEntity = new StoreEntity();
        if (venta.getOrderId() != null && !venta.getOrderId().isBlank()) {
            try {
                storeEntity.setOrderId(Long.parseLong(venta.getOrderId()));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid orderId: " + venta.getOrderId(), e);
            }
        }

        if (venta.getProductId() != null && !venta.getProductId().isBlank()) {
            try {
                storeEntity.setProductId(Long.parseLong(venta.getProductId()));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid productId: " + venta.getProductId(), e);
            }
        }

        storeEntity.setClientId(Long.parseLong(venta.getClientId()));
        storeEntity.setAddressId(Long.parseLong(venta.getAddressId()));
        storeEntity.setDescription(venta.toString());
        storeEntity.setQuantity(Long.parseLong(venta.getQuantity()));

        String orderStatus = new StoreTools(new RestTemplate()).getOrderStatus(venta.getOrderId());
        if (orderStatus != null) {
            storeEntity.setDescription(orderStatus);
        }

        return storeEntity;
    }

    public static ResponseStore createResponseStore(StoreEntity ventaCreated) {
        ResponseStore responseStore = new ResponseStore();
        responseStore.setId(ventaCreated.getId());
        responseStore.setDescription(ventaCreated.getDescription());
        responseStore.setStatus("ORDER_DELIVERED");
        log.info("Order STATUS: {}", responseStore.getStatus());
        String now = LocalDateTime.now().toString();
        responseStore.setCreatedAt(now);
        responseStore.setUpdatedAt(now);
        responseStore.setQuantity(String.valueOf(ventaCreated.getQuantity()));
        return responseStore;
    }

    private String getOrderStatus(String orderId) {
        if (orderId == null || orderId.isBlank()) {
            return "NO_STATUS_DEFINED";
        }

        String url = String.format("http://localhost:8084/api/v1/orders/%s", orderId);

        try{
            ResponseOrder responseOrder = restTemplate.getForObject(url, ResponseOrder.class);
            if (responseOrder.getStatus().equalsIgnoreCase("ORDER_IN_REVIEW")){
                return "ORDER_PAID";
            }else{
                return "ORDER_STOCK_VERIFICATION";
            }
        }catch (Exception e){
            return "ORDER_ERROR";
        }
    }
}
