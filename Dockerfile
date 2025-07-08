# the base image
FROM openjdk:21-jdk-oracle
# Establece la variable de entorno para la zona horaria (ajusta según tu necesidad)
ENV TZ=America/Lima

RUN ./mvnw clean install

# the JAR file path
ARG JAR_FILE=target/trabajopelicula-0.0.1-SNAPSHOT.jar

# Copy the JAR file from the build context into the Docker image
COPY ${JAR_FILE} trabajopelicula-0.0.1-SNAPSHOT.jar

CMD apt-get update -y

# Exponer el puerto HTTP
EXPOSE 8080

# Set the default command to run the Java application
ENTRYPOINT ["java", "-Xmx2048M", "-jar", "/trabajopelicula-0.0.1-SNAPSHOT.jar"]
