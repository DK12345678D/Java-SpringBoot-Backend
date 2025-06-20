FROM openjdk:17-jdk-alpine
COPY target/courses-api.jar courses-api.jar
ENTRYPOINT ["java", "-jar", "courses-api.jar"]
