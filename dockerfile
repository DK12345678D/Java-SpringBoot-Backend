FROM eclipse-temurin:17-jdk-alpine
 
COPY target/docker-app.jar  /user/app/
WORKDIR /user/app
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "docker-app.jar"]


