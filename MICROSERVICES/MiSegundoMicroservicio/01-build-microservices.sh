#!/bin/bash

# ===============================
# Build dinámico para microservicios Maven
# ===============================

BASE_DIR="/Users/josesaidolanogarcia/REPOSITORIOS/AlgoritmoModulo05/MICROSERVICES/MiSegundoMicroservicio"

# Validar parámetro
if [ -z "$BASE_DIR" ]; then
  echo "❌ ERROR: Debes enviar la ruta del directorio padre."
  echo "➡ Ejemplo: ./build-microservices.sh /path/to/microservices"
  exit 1
fi

# Validar que el directorio exista
if [ ! -d "$BASE_DIR" ]; then
  echo "❌ ERROR: El directorio '$BASE_DIR' no existe."
  exit 1
fi

echo "==============================="
echo "🛠  Ejecutando mvn clean install para microservicios en:"
echo "📁  $BASE_DIR"
echo "==============================="

# Recorrer subdirectorios
for dir in "$BASE_DIR"/*; do
  if [ -d "$dir" ] && [ -f "$dir/pom.xml" ]; then
    echo "----------------------------------------"
    echo "📦 Construyendo microservicio: $(basename "$dir")"
    echo "----------------------------------------"

    cd "$dir"
    mvn clean install

    if [ $? -ne 0 ]; then
      echo "❌ Error al construir: $(basename "$dir")"
      exit 1
    fi

    echo "✅ Finalizado: $(basename "$dir")"
    echo ""
  fi
done

echo "==============================="
echo "🎉 Todos los microservicios fueron construidos exitosamente"
echo "==============================="
