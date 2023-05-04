FROM eclipse-temurin:17-jdk-alpine
MAINTAINER hadadelian
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

