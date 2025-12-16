package com.mx.development.said.microservice.products.config;

import com.mx.development.said.microservice.products.entity.ProductEntity;
import com.mx.development.said.microservice.products.entity.ProductStatus;
import com.mx.development.said.microservice.products.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class InsertIntoDBRowsCommandLineRunner implements CommandLineRunner{

    private ProductRepository productRepository;

    public InsertIntoDBRowsCommandLineRunner(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        ProductEntity productEntity01 = new ProductEntity();
        productEntity01.setName("Tennis Jordan 34 rojo");
        productEntity01.setDescription("Tennis Jordan 34 Blanco con Rojo");
        productEntity01.setPrice("USD$100");
        productEntity01.setStatus(ProductStatus.ACTIVE.name());
        ProductEntity productCreated1 = productRepository.save(productEntity01);
        log.info("productCreated: {}", productCreated1);

        ProductEntity productEntity02 = new ProductEntity();
        productEntity02.setName("Tennis Jordan 34 negro");
        productEntity02.setDescription("Tennis Jordan 34 Blanco con Negro");
        productEntity02.setPrice("USD$110");
        productEntity02.setStatus(ProductStatus.INACTIVE.name());
        ProductEntity productCreated2 = productRepository.save(productEntity02);
        log.info("productCreated2: {}", productCreated2);

        ProductEntity productEntity03 = new ProductEntity();
        productEntity03.setName("Tennis Jordan 34 amarillo");
        productEntity03.setDescription("Tennis Jordan 34 Blanco con Amarillo");
        productEntity03.setPrice("USD$120");
        productEntity03.setStatus(ProductStatus.INACTIVE.name());
        ProductEntity productCreated3 = productRepository.save(productEntity03);
        log.info("productCreated3: {}", productCreated3);

        log.info("Now at the database are: {} products saved", productRepository.findAll().size());
    }

}
