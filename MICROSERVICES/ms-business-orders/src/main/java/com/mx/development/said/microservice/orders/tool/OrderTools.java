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
        orderEntity.setStatus(order.getStatus());
        orderEntity.setQuantity(order.getQuantity());
        return orderEntity;
    }

    public static ResponseOrder createResponseOrder(OrderEntity orderCreated) {
        ResponseOrder responseOrder = new ResponseOrder();
        responseOrder.setId(orderCreated.getId());
        responseOrder.setProductId(orderCreated.getProductId());
        responseOrder.setStatus(orderCreated.getStatus());
        responseOrder.setCreatedAt(LocalDateTime.now());
        responseOrder.setUpdatedAt(LocalDateTime.now());
        return responseOrder;
    }

}
