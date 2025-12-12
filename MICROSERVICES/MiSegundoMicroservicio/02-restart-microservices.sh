#!/bin/bash

# ============================================
# Script para detener e iniciar microservicios
# ============================================

BASE_DIR="/Users/josesaidolanogarcia/REPOSITORIOS/AlgoritmoModulo05/MICROSERVICES/MiSegundoMicroservicio"

if [ -z "$BASE_DIR" ]; then
  echo "❌ ERROR: Debes enviar la ruta del directorio principal."
  echo "➡ Ejemplo: ./restart-microservices.sh /path/to/microservices"
  exit 1
fi

if [ ! -d "$BASE_DIR" ]; then
  echo "❌ ERROR: El directorio '$BASE_DIR' no existe."
  exit 1
fi

echo "============================================"
echo "🔄 Reiniciando microservicios en:"
echo "📁 $BASE_DIR"
echo "============================================"

for dir in "$BASE_DIR"/*; do
  if [ -d "$dir" ] && [ -f "$dir/pom.xml" ]; then
    SERVICE_NAME=$(basename "$dir")
    echo "---------------------------------------"
    echo "📦 Microservicio: $SERVICE_NAME"
    echo "---------------------------------------"

    # Buscar el JAR generado
    JAR_FILE=$(find "$dir/target" -maxdepth 1 -name "*.jar" ! -name "*sources.jar" ! -name "*javadoc.jar" | head -n 1)

    if [ -z "$JAR_FILE" ]; then
      echo "⚠ No se encontró un archivo JAR en $dir/target. Ejecuta mvn clean install primero."
      continue
    fi

    # Buscar proceso en ejecución
    PID=$(pgrep -f "$JAR_FILE")

    if [ -n "$PID" ]; then
      echo "🛑 Deteniendo proceso existente (PID: $PID)..."
      kill -9 "$PID"
      echo "✔ Detenido."
    else
      echo "➡ No estaba corriendo."
    fi

    echo "🚀 Iniciando microservicio..."
    nohup java -jar "$JAR_FILE" > "$dir/app.log" 2>&1 &

    NEW_PID=$!
    echo "✔ Iniciado con PID: $NEW_PID"
    echo "📄 Log: $dir/app.log"

    echo ""
  fi
done

echo "============================================"
echo "🎉 Todos los microservicios se reiniciaron correctamente."
echo "============================================"
