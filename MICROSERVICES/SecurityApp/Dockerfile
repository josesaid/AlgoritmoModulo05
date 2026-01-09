# Etapa 1: Compilación con Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Establece el directorio de trabajo
WORKDIR /app

# Copia los archivos necesarios para compilar
COPY pom.xml .
COPY src ./src

# Ejecuta el build
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final más ligera con solo el .jar
FROM eclipse-temurin:17-jdk

# Copia el JAR desde la imagen de compilación
COPY --from=build /app/target/*.jar app.jar

# Ejecuta la aplicación
ENTRYPOINT ["java", "-jar", "/app.jar"]
