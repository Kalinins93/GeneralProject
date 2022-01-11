FROM openjdk:17-alpine
ARG JAR_FILE=build/libs/KafkaToPostreSql-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
COPY src/main/resources/application.yml application.yml
COPY ${JAR_FILE} app.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar","--spring.config.location=file:///application.yml"]