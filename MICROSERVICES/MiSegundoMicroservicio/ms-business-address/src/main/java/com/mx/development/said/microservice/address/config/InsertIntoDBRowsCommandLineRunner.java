package com.mx.development.said.microservice.address.config;

import com.mx.development.said.microservice.address.entity.AddressEntity;
import com.mx.development.said.microservice.address.entity.AddressStatus;
import com.mx.development.said.microservice.address.repository.AddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class InsertIntoDBRowsCommandLineRunner implements CommandLineRunner{

    private AddressRepository addressRepository;

    public InsertIntoDBRowsCommandLineRunner(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        AddressEntity addressEntity01 = new AddressEntity();
        addressEntity01.setStreet("Calle de Alcalá, 44");
        addressEntity01.setCity("Madrid");
        addressEntity01.setState("Madrid");
        addressEntity01.setCountry("España");
        addressEntity01.setPostalCode("28014");
        addressEntity01.setStatus(AddressStatus.ACTIVE.name());
        AddressEntity addressCreated1 = addressRepository.save(addressEntity01);
        log.info("addressCreated: {}", addressCreated1);

        AddressEntity addressEntity02 = new AddressEntity();
        addressEntity02.setStreet("Av. Amazonas N21-147");
        addressEntity02.setCity("Quito");
        addressEntity02.setState("Pichincha");
        addressEntity02.setCountry("Ecuador");
        addressEntity02.setPostalCode("170517");
        addressEntity02.setStatus(AddressStatus.ACTIVE.name());
        AddressEntity addressCreated2 = addressRepository.save(addressEntity02);
        log.info("addressCreated2: {}", addressCreated2);

        AddressEntity addressEntity03 = new AddressEntity();
        addressEntity03.setStreet("Av. José Larco 630");
        addressEntity03.setCity("Miraflores");
        addressEntity03.setState("Lima");
        addressEntity03.setCountry("Perú");
        addressEntity03.setPostalCode("15074");
        addressEntity03.setStatus(AddressStatus.PENDING.name());
        AddressEntity addressCreated3 = addressRepository.save(addressEntity03);
        log.info("addressCreated3: {}", addressCreated3);

        AddressEntity addressEntity04 = new AddressEntity();
        addressEntity04.setStreet("Calle Santa Catalina 101");
        addressEntity04.setCity("Arequipa");
        addressEntity04.setState("Arequipa");
        addressEntity04.setCountry("Perú");
        addressEntity04.setPostalCode("04001");
        addressEntity04.setStatus(AddressStatus.DELETED.name());
        AddressEntity addressCreated4 = addressRepository.save(addressEntity04);
        log.info("addressCreated4: {}", addressCreated4);

        AddressEntity addressEntity05 = new AddressEntity();
        addressEntity05.setStreet("Av. 6 de Agosto 3125");
        addressEntity05.setCity("El Alto");
        addressEntity05.setState("La Paz");
        addressEntity05.setCountry("Bolivia");
        addressEntity05.setPostalCode("0002");
        addressEntity05.setStatus(AddressStatus.INACTIVE.name());
        AddressEntity addressCreated5 = addressRepository.save(addressEntity05);
        log.info("addressCreated5: {}", addressCreated5);

        log.info("Now at the database are: {} products saved", addressRepository.findAll().size());
    }

}
