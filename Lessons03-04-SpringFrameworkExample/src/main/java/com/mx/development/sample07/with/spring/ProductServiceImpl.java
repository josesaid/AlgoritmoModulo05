package com.mx.development.sample07.with.spring;

import org.springframework.stereotype.Service;

/**
 * @author josesaidolanogarcia
 */
@Service
public class ProductServiceImpl implements ProductServiceI {

    private ProductRepositoryI productRepository;

    public ProductServiceImpl(ProductRepositoryI repositorioDeProductos) {
        this.productRepository = repositorioDeProductos;
    }
    @Override
    public Product getProductById(int productId) {
        return productRepository.findById(productId);
    }

}
