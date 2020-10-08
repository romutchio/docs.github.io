FROM openjdk:15-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=backend/target/*.jar
COPY ${JAR_FILE} backend/app.jar
ENTRYPOINT ["java","-jar","/backend/app.jar"]
