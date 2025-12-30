package com.cursomicrosericios.pedidos;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author josesaidolanogarcia
 */
@Service
public class PedidoServicio implements IPedidoServicio {

	private final RestTemplate restTemplate;
	private final String baseUrl = "http://ms-productos";

	public PedidoServicio(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public void guardar(String pedido) {
		System.out.println("Pedido: " + pedido);
	}

	@Override
	public String buscar(Long pedidoId) {
		return "Pedido encontrado: " + pedidoId;
	}

	@Override
	public String buscarProducto(Long producto) {
		String response = restTemplate.getForObject(baseUrl + "/api/productos/" + producto, String.class);
		return response;
	}

}