FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/hoover-instructor-0.0.1.jar .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "hoover-instructor-0.0.1.jar"]