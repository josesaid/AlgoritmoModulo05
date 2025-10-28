package com.mx.development.sample07.without.spring;

public class ProductServiceImpl implements ProductServiceI {

    private ProductRepositoryI productRepository;

    // Constructor Injection for Repository
    public ProductServiceImpl(ProductRepositoryI repositorioDeProductos) {
        this.productRepository = repositorioDeProductos;
    }
    @Override
    public Product getProductById(int productId) {
        return productRepository.findById(productId);
    }

}
