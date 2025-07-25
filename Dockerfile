#Stage 1 - Build the JAR
FROM maven:3.9.6-eclipse-temurin-17-alpine AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests && ls -l /app/target

# Stage 2 - Use JRE-only base image
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/legacy-agent.jar /app/legacy-agent.jar
ENTRYPOINT ["java", "-jar", "/app/legacy-agent.jar"]
