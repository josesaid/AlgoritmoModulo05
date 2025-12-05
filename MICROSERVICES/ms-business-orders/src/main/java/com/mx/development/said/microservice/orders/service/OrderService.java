package com.mx.development.said.microservice.orders.service;

import com.mx.development.said.microservice.orders.dto.InventoryEvent;
import com.mx.development.said.microservice.orders.dto.RequestOrder;
import com.mx.development.said.microservice.orders.dto.ResponseOrder;
import com.mx.development.said.microservice.orders.dto.ResponseProduct;
import com.mx.development.said.microservice.orders.entity.OrderEntity;
import com.mx.development.said.microservice.orders.exception.OrderNotFoundException;
import com.mx.development.said.microservice.orders.exception.ProductNotFoundException;
import com.mx.development.said.microservice.orders.repository.OrderRepository;
import com.mx.development.said.microservice.orders.service.kafka.InventoryKafkaProducer;
import com.mx.development.said.microservice.orders.tool.OrderTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderService {

    private final RestTemplate restTemplate;
    private final InventoryKafkaProducer inventoryKafkaProducer;
    private final OrderRepository orderRepository;

    public OrderService(RestTemplate restTemplate, InventoryKafkaProducer inventoryKafkaProducer, OrderRepository orderRepository) {
        this.restTemplate = restTemplate;
        this.inventoryKafkaProducer = inventoryKafkaProducer;
        this.orderRepository = orderRepository;
        log.info("OrderService instance created");
    }

    public ResponseOrder createOrder(RequestOrder order) {
        ResponseOrder responseOrder = new ResponseOrder();
        //Antes de persistir, comprobamos que el producto existe.
        //1.-Necesitamos ir al microservicio de productos y consultarlo por el id.
        ResponseProduct product = callProductsMicroservice(order.getProductId());
        if (product !=null){
            //Llamamos al microservicio de inventarios para reducir la cantidad de productos disponibles.
            //Llamamos a kafka con un topic para compartir 2 valores:
            //1.- el productId
            //2.-el quantity
            // 🔥 Aquí enviamos el evento a Kafka
            InventoryEvent event = new InventoryEvent(order.getProductId(), Integer.valueOf(order.getQuantity()));
            inventoryKafkaProducer.sendInventoryEvent(event);



            OrderEntity orderEntity = OrderTools.requestOrderToOrderEntity(order);
            OrderEntity orderCreated = orderRepository.save(orderEntity);

            responseOrder = OrderTools.createResponseOrder(orderCreated);
            log.info("Order created: {}", responseOrder);
        }
        else{
            log.warn("Product not found");
        }
        return responseOrder;
    }

    public ResponseProduct callProductsMicroservice(String productId) {

        //String url = "http://ms-business-products/api/v1/products/{productId}";
        String url = "http://localhost:8080/api/v1/products/"+productId;

        ResponseProduct responseProduct = restTemplate.getForObject(
                url,
                ResponseProduct.class,
                productId
        );

        return responseProduct;
    }

    public List<ResponseOrder> findAll() {
        log.info("Listing orders...");
        List<OrderEntity> orderEntityList = orderRepository.findAll();
        List<ResponseOrder> responseOrderList = orderEntityList
                .stream()
                .map(OrderTools::createResponseOrder).toList();
        return responseOrderList;
    }

/*
    public Optional<ResponseProduct> getProductById(Long productoId) {
        return productRepository.findById(productoId)
                .map(OrderTools::createResponseProduct);
    }
*/
    public ResponseOrder getOrderById(Long orderId) {
        Optional<OrderEntity> optionalOrderEntity = orderRepository.findById(orderId);

        if(optionalOrderEntity.isEmpty()){
            throw new OrderNotFoundException(orderId);
        }

        return OrderTools.createResponseOrder(optionalOrderEntity.get());

    }


    public List<ResponseOrder> findByStatus(String status) {
        log.info("clase: {}", OrderService.class.getClass().getSimpleName());
        List<OrderEntity> orderEntityList = orderRepository.findByStatus(status);
        log.info("Encontró {} ordenes con el estatus: {}", orderEntityList.size(), status);

        return orderEntityList.stream()
                .map(OrderTools::createResponseOrder)
                .toList();
    }


}
