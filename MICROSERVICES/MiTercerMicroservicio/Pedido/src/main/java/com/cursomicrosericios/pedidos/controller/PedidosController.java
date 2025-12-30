package com.cursomicrosericios.pedidos.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cursomicrosericios.pedidos.IPedidoServicio;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author josesaidolanogarcia
 */
@RestController
@RequestMapping("/api/pedidos")
public class PedidosController {

	public final IPedidoServicio pedidoServicio;
	
    public PedidosController(IPedidoServicio pedido) {
		this.pedidoServicio = pedido;
	}

	@GetMapping("/{pedidoId}")
    public String getPedido(@PathVariable Long pedidoId) {
        return "El pedido solicitado es: " + pedidoId + "<---";
    }

	@GetMapping("/consultar/product/{productoId}")
    public String getProductos(@PathVariable Long productoId) {
        return "Consulta: " + pedidoServicio.buscarProducto(productoId);
    }
    
}
