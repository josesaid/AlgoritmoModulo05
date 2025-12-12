package com.mx.development.said.microservice.clients.config;

import com.mx.development.said.microservice.clients.entity.ClientEntity;
import com.mx.development.said.microservice.clients.entity.ClientStatus;
import com.mx.development.said.microservice.clients.repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class InsertIntoDBRowsCommandLineRunner implements CommandLineRunner{

    private ClientRepository clientRepository;

    public InsertIntoDBRowsCommandLineRunner(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        ClientEntity clientEntity01 = new ClientEntity();
        clientEntity01.setName("Diego Rada");
        clientEntity01.setEmail("diegorada@gmail.com");
        clientEntity01.setPhoneNumber("+34612345678");
        clientEntity01.setStatus(ClientStatus.SUSPENDED.name());
        ClientEntity clientCreated01 = clientRepository.save(clientEntity01);
        log.info("clientCreated1: {}", clientCreated01);

        ClientEntity clientEntity02 = new ClientEntity();
        clientEntity02.setName("Haggy Gorgorita");
        clientEntity02.setEmail("hagyhernandez@codegym.com");
        clientEntity02.setPhoneNumber("+593991234567");
        clientEntity02.setStatus(ClientStatus.ACTIVE.name());
        ClientEntity clientCreated02 = clientRepository.save(clientEntity02);
        log.info("clientCreated2: {}", clientCreated02);

        ClientEntity clientEntity03 = new ClientEntity();
        clientEntity03.setName("Saul Echeverri");
        clientEntity03.setEmail("saulecheverri@codegym.com");
        clientEntity03.setPhoneNumber("+51912345678");
        clientEntity03.setStatus(ClientStatus.INACTIVE.name());
        ClientEntity clientCreated03 = clientRepository.save(clientEntity03);
        log.info("clientCreated3: {}", clientCreated03);

        ClientEntity clientEntity04 = new ClientEntity();
        clientEntity04.setName("Miguel Roca");
        clientEntity04.setEmail("miguelroca@codegym.com");
        clientEntity04.setPhoneNumber("+51912345679");
        clientEntity04.setStatus(ClientStatus.EXPIRED.name());
        ClientEntity clientCreated04 = clientRepository.save(clientEntity04);
        log.info("clientCreated4: {}", clientCreated04);

        ClientEntity clientEntity05 = new ClientEntity();
        clientEntity05.setName("Daleyma Quispe");
        clientEntity05.setEmail("daleymaquispe@codegym.com");
        clientEntity05.setPhoneNumber("+59164934564");
        clientEntity05.setStatus(ClientStatus.ARCHIVED.name());
        ClientEntity clientCreated05 = clientRepository.save(clientEntity05);
        log.info("clientCreated4: {}", clientCreated05);

        log.info("Now at the database are: {} clients saved", clientRepository.findAll().size());
    }

}
