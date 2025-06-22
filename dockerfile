FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/softwaredevelopmentassignments-0.0.1-SNAPSHOT.jar /app/softwaredevelopmentassignments.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "softwaredevelopmentassignments.jar"]


