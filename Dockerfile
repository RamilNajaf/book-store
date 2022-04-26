FROM openjdk:11
WORKDIR /app
COPY target/*.jar book-store-app.jar
ENTRYPOINT ["java", "-jar", "book-store-app.jar"]
