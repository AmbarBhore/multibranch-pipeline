FROM openjdk:17-jdk
COPY target/legacy-agent.jar /app/legacy-agent.jar
ENTRYPOINT ["java", "-jar", "/app/legacy-agent.jar"]
