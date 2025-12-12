package com.mx.development.said.microservice.orders.service;

import com.mx.development.said.microservice.orders.dto.*;
import com.mx.development.said.microservice.orders.entity.OrderEntity;
import com.mx.development.said.microservice.orders.exception.ClientNotFoundException;
import com.mx.development.said.microservice.orders.exception.OrderNotFoundException;
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


        ResponseClient posibleClient = callClientsMicroservice(order.getClientId());
        if(posibleClient ==null){
            log.error("Client not found");
            throw new ClientNotFoundException("The client: " + order.getClientId() + "was not found in the Remote microservice clients DB");
        }


        //Antes de persistir, comprobamos que el producto existe.
        //1.-Necesitamos ir al microservicio de productos y consultarlo por el id.
        ResponseProduct product = callProductsMicroservice(order.getProductId());
        if (product !=null){

            OrderEntity orderEntity = OrderTools.requestOrderToOrderEntity(order);
            OrderEntity orderCreated = orderRepository.save(orderEntity);
            log.info("Order created: {}", responseOrder);

            responseOrder = OrderTools.createResponseOrder(orderCreated);
            log.info("Order in review: {}", responseOrder);


            //Llamamos al microservicio de inventarios para reducir la cantidad de productos disponibles.
            //Llamamos a kafka con un topic para compartir los valores necesarios:

            // 🔥 Aquí enviamos el evento a Kafka
            StoreMessageItem storeMessageItem = new StoreMessageItem(
                    String.valueOf(responseOrder.getId()),
                    responseOrder.getProductId(),
                    responseOrder.getClientId(),
                    responseOrder.getAddressId(),
                    responseOrder.getStatus(),
                    responseOrder.getQuantity()
            );
            inventoryKafkaProducer.sendInventoryEvent(storeMessageItem);

        }
        else{
            log.warn("Product not found");
        }
        return responseOrder;
    }

    private ResponseClient callClientsMicroservice(String clientId) {
        try{
            //String url = "http://ms-business-clients/api/v1/products/{productId}";
            String url = "http://localhost:8082/api/v1/clients/"+clientId;
            ResponseClient responseClient = restTemplate.getForObject(
                    url,
                    ResponseClient.class,
                    clientId
            );
            return responseClient;
        }catch (Exception e){
            throw new ClientNotFoundException("The client: " + clientId+ " does not exist in the Remote ms-business-clients");
        }

    }


    public ResponseProduct callProductsMicroservice(String productId) {
        //String url = "http://ms-business-products/api/v1/products/{productId}";
        String url = "http://localhost:8081/api/v1/products/"+productId;
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
