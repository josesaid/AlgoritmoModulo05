#!/bin/bash

# ------------------------------------------------------------------------------------
# 📂 Crear carpeta de logs (una sola vez)
echo "📁 Creando carpeta de logs..."
mkdir -p logs

# ------------------------------------------------------------------------------------
# 🧭 Inicia Service Registry (Eureka)
SERVICE_REGISTRY_JAR="./ServiceRegistry/target/ServiceRegistry-0.0.1.jar"

echo "🧭 Iniciando Service Registry en puerto 8761..."
nohup java -Dserver.port=8761 -jar $SERVICE_REGISTRY_JAR > logs/service-registry-8761.log 2>&1 &

echo "⏳ Service Registry iniciado. Esperando 10 segundos..."
sleep 10

# ------------------------------------------------------------------------------------
# 🌐 Inicia API Gateway
GATEWAY_JAR="./Gateway/target/Gateway-0.0.1-SNAPSHOT.jar"

echo "🌐 Iniciando API Gateway en puerto 9010..."
nohup java -Dserver.port=9010 -jar $GATEWAY_JAR > logs/gateway-9010.log 2>&1 &

echo "⏳ Gateway iniciado. Esperando 5 segundos..."
sleep 5


# ------------------------------------------------------------------------------------
# 📦 Inicia ms-pedidos con 2 instancias
PEDIDOS_JAR="./Pedido/target/Pedido-0.0.1.jar"

echo "📦 Iniciando ms-pedido instancia 1 en puerto 8081..."
nohup java -Dserver.port=8081 -jar $PEDIDOS_JAR > logs/ms-pedido-8081.log 2>&1 &

echo "📦 Iniciando ms-pedido instancia 2 en puerto 8082..."
nohup java -Dserver.port=8082 -jar $PEDIDOS_JAR > logs/ms-pedido-8082.log 2>&1 &

echo "✅ Instancias de ms-pedido iniciadas."

# ------------------------------------------------------------------------------------
# 💳 Inicia ms-pagos con 2 instancias
PAGOS_JAR="./Pagos/target/Pagos-0.0.1-SNAPSHOT.jar"

echo "💳 Iniciando ms-pagos instancia 1 en puerto 8091..."
nohup java -Dserver.port=8091 -jar $PAGOS_JAR > logs/ms-pagos-8091.log 2>&1 &

echo "💳 Iniciando ms-pagos instancia 2 en puerto 8092..."
nohup java -Dserver.port=8092 -jar $PAGOS_JAR > logs/ms-pagos-8092.log 2>&1 &

echo "✅ Instancias de ms-pagos iniciadas."

# ------------------------------------------------------------------------------------
# 🛒 Inicia ms-productos con 2 instancias
PRODUCTOS_JAR="./Productos/target/Productos-0.0.1-SNAPSHOT.jar"

echo "🛒 Iniciando ms-productos instancia 1 en puerto 8101..."
nohup java -Dserver.port=8101 -jar $PRODUCTOS_JAR > logs/ms-productos-8101.log 2>&1 &

echo "🛒 Iniciando ms-productos instancia 2 en puerto 8102..."
nohup java -Dserver.port=8102 -jar $PRODUCTOS_JAR > logs/ms-productos-8102.log 2>&1 &

echo "✅ Instancias de ms-productos iniciadas."

# ------------------------------------------------------------------------------------
echo "🎉 Todos los microservicios han sido iniciados correctamente."
echo "📄 Revisa los logs en la carpeta 'logs/'."
