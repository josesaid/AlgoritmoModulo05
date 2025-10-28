Shopping microservices example (Inventory, Notification, Order) - packaged for local build

How to use:
  1. On your machine ensure Java 17, Maven and Docker are installed.
  2. Build each project jar (optional if you want Docker to build):
     cd inventory-service && mvn clean package -DskipTests
     cd ../notification-service && mvn clean package -DskipTests
     cd ../order-service && mvn clean package -DskipTests
  3. From the root folder (where docker-compose.yml is):
     docker-compose up --build
  4. Endpoints:
     Inventory:  POST http://localhost:8081/inventory/decrease/{id}/{qty}
                 POST http://localhost:8081/inventory/products  (create product)
                 GET  http://localhost:8081/inventory/products
     Notification: POST http://localhost:8082/notifications/send?orderId=...&email=...&phone=...
     Orders: POST http://localhost:8080/orders   (body: productId,quantity,email,phone)
            GET  http://localhost:8080/orders
