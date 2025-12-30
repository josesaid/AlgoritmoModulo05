package com.cursomicrosericios.pagos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author josesaidolanogarcia
 */
@RestController
@RequestMapping("/api/pagos")
public class PagosController {

    @GetMapping("/{pagodolares}")
    public String getMethodName(@PathVariable Long pagodolares) {
        return "El pago realizado fue de: " + pagodolares;
    }
}
