package com.mx.development.said.microservice.service;

import com.mx.development.said.microservice.dto.RequestProduct;
import com.mx.development.said.microservice.dto.ResponseProduct;
import com.mx.development.said.microservice.entity.ProductEntity;
import com.mx.development.said.microservice.repository.ProductRepository;
import com.mx.development.said.microservice.tool.ProductTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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



    public RequestProduct getProduct(){
        log.info("Getting product...");
        return new RequestProduct("Producto ABC", "Descripcion del producto ABC*");
    }

}
