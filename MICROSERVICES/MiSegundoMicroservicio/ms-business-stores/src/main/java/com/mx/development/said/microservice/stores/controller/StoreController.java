package com.mx.development.said.microservice.stores.controller;

import com.mx.development.said.microservice.stores.dto.RequestStore;
import com.mx.development.said.microservice.stores.dto.ResponseStore;
import com.mx.development.said.microservice.stores.service.StoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/stores")
@Slf4j
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping("/")
    public ResponseEntity<ResponseStore> createRequestStore(@RequestBody RequestStore venta){
        log.info("Creating venta: {}", venta);
        ResponseStore response = storeService.createStoreSell(venta);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
