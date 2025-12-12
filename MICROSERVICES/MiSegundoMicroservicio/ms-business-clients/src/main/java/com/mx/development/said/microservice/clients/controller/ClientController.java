package com.mx.development.said.microservice.clients.controller;

import com.mx.development.said.microservice.clients.dto.ResponseClient;
import com.mx.development.said.microservice.clients.service.ClientService;
import com.mx.development.said.microservice.clients.dto.RequestClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
@Slf4j
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ResponseClient>> getAllClients() {
        List<ResponseClient> clients = clientService.findAll();

        if (clients == null || clients.isEmpty()) {
            log.warn("No clients found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        log.info("Number of clients found: {}", clients.size());
        return ResponseEntity.ok(clients);
    }

    @PostMapping("/")
    public ResponseEntity<ResponseClient> createClient(@RequestBody RequestClient client){
        log.info("Creating client: {}", client);
        ResponseClient response = clientService.createClient(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{clientId}")
    public ResponseClient getClientById(@PathVariable Long clientId) {
        return clientService.getClientById(clientId);
    }

}
