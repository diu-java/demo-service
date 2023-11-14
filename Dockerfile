FROM openjdk:17-jdk-slim
COPY target/demo-service-1.0.0.jar /app/demo-service-1.0.0.jar
COPY target/../custom.java.security /app/custom.java.security

WORKDIR /app

CMD ["java", "-jar", "-Djava.security.properties=custom.java.security", "demo-service-1.0.0.jar"]
