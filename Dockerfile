
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/shipment-0.0.1-SNAPSHOT.jar /app/shipment-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "shipment-0.0.1-SNAPSHOT.jar"]
