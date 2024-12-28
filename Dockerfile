FROM openjdk:17-jdk-slim

WORKDIR /app

COPY build/libs/NoviSign-1.0-SNAPSHOT.jar app.jar

EXPOSE 8080

# Command to run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]