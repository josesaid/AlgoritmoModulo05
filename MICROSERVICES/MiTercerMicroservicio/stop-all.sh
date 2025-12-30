#!/bin/bash

echo "Buscando procesos de los microservicios service-discovery(eureka), api-gateway, ms-pedidos, ms-pagos y ms-productos..."

# Array con nombres de los JARs involucrados
declare -a jars=("Pedido-0.0.1.jar" "Pagos-0.0.1-SNAPSHOT.jar" "Productos-0.0.1-SNAPSHOT.jar" "ServiceRegistry-0.0.1.jar" "Gateway-0.0.1-SNAPSHOT.jar")

found_any=false

for jar in "${jars[@]}"; do
  echo "Buscando procesos para: $jar"
  PIDS=$(ps aux | grep "$jar" | grep -v grep | awk '{print $2}')

  if [ -n "$PIDS" ]; then
    found_any=true
    echo "Procesos encontrados para $jar: $PIDS"
    kill $PIDS
    echo "Procesos detenidos para $jar."
  else
    echo "No se encontraron procesos para $jar."
  fi
done

if ! $found_any; then
  echo "No se encontraron procesos relacionados con los microservicios."
else
  echo "Todos los procesos detenidos."
fi
