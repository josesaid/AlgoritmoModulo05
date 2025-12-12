package com.mx.development.said.microservice.clients.service;

import com.mx.development.said.microservice.clients.dto.RequestClient;
import com.mx.development.said.microservice.clients.dto.ResponseClient;
import com.mx.development.said.microservice.clients.entity.ClientEntity;
import com.mx.development.said.microservice.clients.exception.ClientNotFoundException;
import com.mx.development.said.microservice.clients.repository.ClientRepository;
import com.mx.development.said.microservice.clients.tool.ClientTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        log.info("ClientService instance created");
        this.clientRepository = clientRepository;
    }

    public List<ResponseClient> findAll() {
        log.info("Listing clients...");
        List<ClientEntity> clientsEntityList = clientRepository.findAll();
        List<ResponseClient> responseClientList = clientsEntityList
                .stream()
                .map(ClientTools::createResponseClient)
                .toList();

        return responseClientList;
    }

    public ResponseClient createClient(RequestClient client){
        ClientEntity clientEntity = ClientTools.requestClientToClientEntity(client);
        ClientEntity clientCreated = clientRepository.save(clientEntity);
        ResponseClient responseClient = ClientTools.createResponseClient(clientCreated);
        log.info("Client created: {}", responseClient);
        return responseClient;
    }


    public ResponseClient getClientById(Long clientId) {
        Optional<ClientEntity> optionalClientEntity = clientRepository.findById(clientId);

        if(optionalClientEntity.isEmpty()){
            throw new ClientNotFoundException(clientId);
        }

        return ClientTools.createResponseClient(optionalClientEntity.get());

    }


    public List<ResponseClient> findByStatus(String status) {
        log.info("clase: {}", ClientService.class.getClass().getSimpleName());
        List<ClientEntity> clientEntityList = clientRepository.findByStatus(status);
        log.info("Encontró {} clientes con el estatus: {}", clientEntityList.size(), status);

        return clientEntityList.stream()
                .map(ClientTools::createResponseClient)
                .toList();
    }

}
