# Etapa 1: Construcción de la aplicación con Maven
FROM openjdk:17-slim      AS builder
WORKDIR /app
# Copia el archivo pom.xml y descarga las dependencias para aprovechar la caché
COPY pom.xml .
RUN mvn dependency:go-offline -B
# Copia el código fuente y compila la aplicación
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final para ejecutar la aplicación
FROM openjdk:17-slim
WORKDIR /app
# Copia el JAR compilado desde la etapa de construcción
COPY --from=builder /app/target/LETMALAGAAPP-0.0.1-SNAPSHOT.jar app.jar
# Expone el puerto en el que la aplicación escucha
EXPOSE 8080
# Define el comando para iniciar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
