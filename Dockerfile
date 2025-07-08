# Etapa 1: Construcción del JAR usando Maven
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN chmod +x mvnw && ./mvnw clean package -DskipTests

# Etapa 2: Imagen de ejecución usando OpenJDK
FROM openjdk:21-jdk-oracle
ENV TZ=America/Lima
WORKDIR /app

# Copia el JAR desde la etapa de compilación
COPY --from=build /app/target/trabajopelicula-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto por defecto
EXPOSE 8080

# Comando para ejecutar la app
ENTRYPOINT ["java", "-Xmx2048M", "-jar", "app.jar"]
