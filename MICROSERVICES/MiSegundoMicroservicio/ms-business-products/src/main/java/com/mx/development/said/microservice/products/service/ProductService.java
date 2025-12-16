package com.mx.development.said.microservice.products.service;

import com.mx.development.said.microservice.products.dto.RequestProduct;
import com.mx.development.said.microservice.products.dto.ResponseProduct;
import com.mx.development.said.microservice.products.entity.ProductEntity;
import com.mx.development.said.microservice.products.exception.ProductNotFoundException;
import com.mx.development.said.microservice.products.repository.ProductRepository;
import com.mx.development.said.microservice.products.tool.ProductTools;
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


    public ResponseProduct createProduct(RequestProduct product){
        ProductEntity productEntity = ProductTools.requestProductToProductEntity(product);
        ProductEntity productCreated = productRepository.save(productEntity);
        ResponseProduct responseProduct = ProductTools.createResponseProduct(productCreated);
        log.info("Product created: {}", responseProduct);
        return responseProduct;
    }

    public List<ResponseProduct> findAll() {
        log.info("Listing products...");
        return productRepository.findAll()
                .stream()
                .map(ProductTools::createResponseProduct)
                .toList();
    }

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

    public ResponseProduct updateProduct(Long productId, RequestProduct newProductData) {
        log.info("Updating product with ID: {}", productId);
        Optional<ProductEntity> optionalProduct = productRepository.findById(productId);

        if (optionalProduct.isEmpty()) {
            throw new ProductNotFoundException(productId);
        }

        ProductEntity existingProduct = optionalProduct.get();
        ProductEntity updatedProduct = updateWithNewValues(existingProduct, newProductData);
        updatedProduct = productRepository.save(updatedProduct);
        ResponseProduct responseProduct = ProductTools.createResponseProduct(updatedProduct);
        log.info("Product updated: {}", responseProduct);

        return responseProduct;
    }

    private ProductEntity updateWithNewValues(ProductEntity existingProduct, RequestProduct newProductData) {
        existingProduct.setName(newProductData.getName());
        existingProduct.setDescription(newProductData.getDescription());
        existingProduct.setPrice(newProductData.getPrice());
        return existingProduct;
    }


    public void deleteProduct(Long productoId) {
        log.info("Deleting product with ID: {}", productoId);
        productRepository.deleteById(productoId);
    }

}
