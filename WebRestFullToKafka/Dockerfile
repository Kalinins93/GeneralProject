FROM openjdk:17-alpine
ARG JAR_FILE=build/libs/WebRestFullToKafka-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
COPY src/main/resources/application.yml application.yml
COPY ${JAR_FILE} app.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/app.jar","--spring.config.location=file:///application.yml"]