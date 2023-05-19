# Utiliza una imagen base con Java y Maven para construir tu aplicación
FROM maven3.8.4-openjdk-17 AS build
WORKDIR app
COPY pom.xml .
RUN mvn dependencygo-offline

COPY src .src
RUN mvn package -DskipTests

# Crea una nueva imagen con la aplicación compilada
FROM openjdk17
WORKDIR app
COPY --from=build apptargetantonativa-0.0.1-SNAPSHOT.jar .app.jar

# Expone el puerto en el que tu aplicación escucha
EXPOSE 8080

# Comando para ejecutar la aplicación Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]