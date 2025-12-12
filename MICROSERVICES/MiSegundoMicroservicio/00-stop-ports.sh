#!/bin/bash

# Puertos a liberar
PORTS=("8081" "8081" "8082")

echo "==============================="
echo "🛑 Eliminando procesos en puertos 8081, 8081 y 8082"
echo "==============================="

for PORT in "${PORTS[@]}"; do
  echo "🔍 Buscando procesos en el puerto $PORT..."

  PID=$(lsof -t -i :$PORT)

  if [ -n "$PID" ]; then
    echo "   ✔ Proceso encontrado (PID: $PID). Eliminando..."
    kill -9 $PID
    echo "   ✔ Puerto $PORT liberado."
  else
    echo "   ➡ No hay procesos usando el puerto $PORT."
  fi

  echo ""
done

echo "==============================="
echo "🎉 Puertos liberados correctamente."
echo "==============================="
