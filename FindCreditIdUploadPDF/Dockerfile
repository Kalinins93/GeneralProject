FROM openjdk:17-alpine
ARG JAR_FILE=build/libs/FindCreditIdUploadPDF-1.0-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
COPY src/main/resources/application.yml application.yml
COPY ${JAR_FILE} app.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8083
ENTRYPOINT ["java","-jar","/app.jar","--spring.config.location=file:///application.yml"]