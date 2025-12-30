1.- ejecutar
    ./start-all_also_service_registry.sh
2.- Correr Endpoints de Pagos:
    http://192.168.0.81:8091/api/pagos/1234
    http://192.168.0.81:8092/api/pagos/12345
3.- Correr Endpoints de Productos:
    http://192.168.0.81:8101/api/productos/321
    http://192.168.0.81:8102/api/productos/963
4.- Correr Endpoints de Ordenes(pedidos):
    http://192.168.0.81:8081/api/pedidos/1020
    http://192.168.0.81:8082/api/pedidos/3040
    Internamente Ordenes(Pedidos) llama a productos:
    http://192.168.0.81:8081/api/pedidos/consultar/product/2468
    http://192.168.0.81:8082/api/pedidos/consultar/product/3579