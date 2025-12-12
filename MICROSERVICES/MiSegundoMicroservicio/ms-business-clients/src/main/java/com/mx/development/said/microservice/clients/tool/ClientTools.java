package com.mx.development.said.microservice.clients.tool;

import com.mx.development.said.microservice.clients.dto.RequestClient;
import com.mx.development.said.microservice.clients.dto.ResponseClient;
import com.mx.development.said.microservice.clients.entity.ClientEntity;

import java.time.LocalDateTime;

public class ClientTools {
    static public ClientEntity requestClientToClientEntity(RequestClient client) {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setName(client.getName());
        clientEntity.setEmail(client.getEmail());
        clientEntity.setPhoneNumber(client.getPhoneNumber());
        clientEntity.setStatus(client.getStatus());
        return clientEntity;
    }

    public static ResponseClient createResponseClient(ClientEntity clientEntityCreated) {
        ResponseClient responseClient = new ResponseClient();
        responseClient.setId(clientEntityCreated.getId());
        responseClient.setName(clientEntityCreated.getName());
        responseClient.setEmail(clientEntityCreated.getEmail());
        responseClient.setCreatedAt(LocalDateTime.now().toString());
        responseClient.setUpdatedAt(LocalDateTime.now().toString());
        responseClient.setStatus(clientEntityCreated.getStatus());
        return responseClient;
    }

}
