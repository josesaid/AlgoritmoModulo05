package com.mx.development.said.microservice.stores.service;

import com.mx.development.said.microservice.stores.dto.RequestStore;
import com.mx.development.said.microservice.stores.dto.ResponseStore;
import com.mx.development.said.microservice.stores.entity.StoreEntity;
import com.mx.development.said.microservice.stores.repository.StoreRepository;
import com.mx.development.said.microservice.stores.tool.StoreTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StoreService {

    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public ResponseStore createStoreSell(RequestStore venta){
        StoreEntity storeEntity = StoreTools.requestProductToProductEntity(venta);
        StoreEntity storeProductSold = storeRepository.save(storeEntity);
        ResponseStore responseProductSold = StoreTools.createResponseStore(storeProductSold);
        log.info("Producto vendido: {}", responseProductSold);
        return responseProductSold;
    }


}
