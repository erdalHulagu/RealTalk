FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/user-service.war /app/user-service.war
EXPOSE 8080
CMD ["java", "-jar", "user-service.war"]