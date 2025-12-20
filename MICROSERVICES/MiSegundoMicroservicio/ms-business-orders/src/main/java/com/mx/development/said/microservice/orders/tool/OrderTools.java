package com.mx.development.said.microservice.orders.tool;

import com.mx.development.said.microservice.orders.dto.RequestOrder;
import com.mx.development.said.microservice.orders.dto.ResponseOrder;
import com.mx.development.said.microservice.orders.entity.OrderEntity;

import java.time.LocalDateTime;

/**
 * @author josesaidolanogarcia
 */
public class OrderTools {

    public static OrderEntity requestOrderToOrderEntity(RequestOrder order) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setProductId(order.getProductId());
        orderEntity.setClientId(order.getClientId());
        orderEntity.setAddressId(order.getAddressId());
        orderEntity.setStatus("ORDER_CREATED");
        orderEntity.setQuantity(order.getQuantity());
        orderEntity.setCreatedAt(LocalDateTime.now().toString());
        orderEntity.setUpdatedAt(LocalDateTime.now().plusHours(5).toString());
        return orderEntity;
    }

    public static ResponseOrder createResponseOrder(OrderEntity orderCreated) {
        ResponseOrder responseOrder = new ResponseOrder();
        responseOrder.setId(orderCreated.getId());
        responseOrder.setProductId(orderCreated.getProductId());
        responseOrder.setClientId(orderCreated.getClientId());
        responseOrder.setAddressId(orderCreated.getAddressId());
        responseOrder.setStatus("ORDER_IN_REVIEW");
        responseOrder.setCreatedAt(LocalDateTime.now().plusDays(1).toString());
        responseOrder.setUpdatedAt(LocalDateTime.now().plusDays(1).plusHours(3).toString());
        responseOrder.setQuantity(orderCreated.getQuantity());
        return responseOrder;
    }

}
