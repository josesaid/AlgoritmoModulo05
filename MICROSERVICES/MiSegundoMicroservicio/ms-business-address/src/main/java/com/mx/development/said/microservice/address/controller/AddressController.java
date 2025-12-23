package com.mx.development.said.microservice.address.controller;

import com.mx.development.said.microservice.address.dto.ResponseAddress;
import com.mx.development.said.microservice.address.service.AddressService;
import com.mx.development.said.microservice.address.dto.RequestAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/address")
@Slf4j
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ResponseAddress>> getAllAddress() {
        List<ResponseAddress> address = addressService.findAll();

        if (address == null || address.isEmpty()) {
            log.warn("No address found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        log.info("Number of address found: {}", address.size());
        return ResponseEntity.ok(address);
    }

    @PostMapping("/")
    public ResponseEntity<ResponseAddress> createAddress(@RequestBody RequestAddress address){
        log.info("Creating address: {}", address);
        ResponseAddress response = addressService.createAddress(address);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /*@GetMapping("/{productoId}")
    public ResponseEntity<ResponseProduct> getProductById(@PathVariable Long productoId) {
        Optional<ResponseProduct> responseOptional = productService.getProductById(productoId);

        return responseOptional
                .map(response -> ResponseEntity.ok(response))
                .orElseGet(() -> ResponseEntity.notFound().build());

    }*/
    @GetMapping("/{addressId}")
    public ResponseAddress getAddressById(@PathVariable Long addressId) {
        return addressService.getAddressById(addressId);
    }


    @GetMapping("/query")
    public ResponseEntity<List<ResponseAddress>> getAddressByStatus(@RequestParam String status) {

        log.info("clase: {}", AddressController.class.getClass().getSimpleName());
        List<ResponseAddress> addressServiceByStatus = addressService.findByStatus(status);

        if (addressServiceByStatus == null || addressServiceByStatus.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(addressServiceByStatus);
    }
}
