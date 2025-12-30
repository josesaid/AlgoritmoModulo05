package com.cursomicrosericios.productos.controller;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/productos")
public class ProductosController {
	private final Environment environment;
	
	public ProductosController(Environment environment) {
		this.environment=environment;
	}
	
    @GetMapping("/{productoid}")
    public String getMethodName(@PathVariable Long productoid) {
        return "El producto solicitado es: " + productoid +
                " puerto:" + environment.getProperty("local.server.port");
    }
}
