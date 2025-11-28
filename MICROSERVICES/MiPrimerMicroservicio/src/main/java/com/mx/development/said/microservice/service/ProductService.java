package com.mx.development.said.microservice.service;

import com.mx.development.said.microservice.controller.ProductController;
import com.mx.development.said.microservice.dto.RequestProduct;
import com.mx.development.said.microservice.dto.ResponseProduct;
import com.mx.development.said.microservice.entity.ProductEntity;
import com.mx.development.said.microservice.exception.ProductNotFoundException;
import com.mx.development.said.microservice.repository.ProductRepository;
import com.mx.development.said.microservice.tool.ProductTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        log.info("ProductService instance created");
        this.productRepository = productRepository;
    }

    public List<ResponseProduct> findAll() {
        log.info("Listing products...");
        List<ProductEntity> productEntityList = productRepository.findAll();
        List<ResponseProduct> responseProductList = productEntityList.stream().map(ProductTools::createResponseProduct).toList();
        return responseProductList;
    }

    public ResponseProduct createProduct(RequestProduct product){
        ProductEntity productEntity = ProductTools.requestProductToProductEntity(product);
        ProductEntity productCreated = productRepository.save(productEntity);
        ResponseProduct responseProduct = ProductTools.createResponseProduct(productCreated);
        log.info("Product created: {}", responseProduct);
        return responseProduct;
    }


/*
    public Optional<ResponseProduct> getProductById(Long productoId) {
        return productRepository.findById(productoId)
                .map(ProductTools::createResponseProduct);
    }
*/
    public ResponseProduct getProductById(Long productoId) {
        Optional<ProductEntity> optionalProductEntity = productRepository.findById(productoId);

        if(optionalProductEntity.isEmpty()){
            throw new ProductNotFoundException(productoId);
        }

        return ProductTools.createResponseProduct(optionalProductEntity.get());

    }


    public List<ResponseProduct> findByStatus(String status) {
        log.info("clase: {}", ProductService.class.getClass().getSimpleName());
        List<ProductEntity> productEntityList = productRepository.findByStatus(status);
        log.info("Encontró {} productos con el estatus: {}", productEntityList.size(), status);

        return productEntityList.stream()
                .map(ProductTools::createResponseProduct)
                .toList();
    }

}
