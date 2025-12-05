package com.mx.development.said.microservice.products.tool;

import com.mx.development.said.microservice.products.dto.RequestProduct;
import com.mx.development.said.microservice.products.dto.ResponseProduct;
import com.mx.development.said.microservice.products.entity.ProductEntity;

import java.time.LocalDateTime;

public class ProductTools {
    static public ProductEntity requestProductToProductEntity(RequestProduct product) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(product.getName());
        productEntity.setDescription(product.getDescription());
        productEntity.setPrice(product.getPrice());
        productEntity.setStatus(product.getStatus());
        return productEntity;
    }

    public static ResponseProduct createResponseProduct(ProductEntity productEntityCreated) {
        ResponseProduct responseProduct = new ResponseProduct();
        responseProduct.setId(productEntityCreated.getId());
        responseProduct.setName(productEntityCreated.getName());
        responseProduct.setDescription(productEntityCreated.getDescription());
        responseProduct.setCreatedAt(LocalDateTime.now().toString());
        responseProduct.setUpdatedAt(LocalDateTime.now().toString());
        responseProduct.setStatus(productEntityCreated.getStatus());
        responseProduct.setPrice(productEntityCreated.getPrice());
        return responseProduct;
    }

}
