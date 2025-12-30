package com.cursomicrosericios.pedidos;

/**
 * @author josesaidolanogarcia
 */
public interface IPedidoServicio {
    public void guardar(String pedido);
    public String buscar(Long pedidoId);
    public String buscarProducto(Long productoId);
}
