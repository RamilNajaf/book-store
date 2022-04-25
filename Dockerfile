FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package


FROM openjdk:11
WORKDIR /app
COPY target/*.jar book-store-app.jar
ENTRYPOINT ["java", "-jar", "book-store-app.jar"]