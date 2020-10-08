FROM node as frontend
WORKDIR /frontend
COPY frontend .
RUN npm ci
RUN npm run-script build

FROM maven:3.6.3-jdk-11 as backend
WORKDIR /backend
COPY backend .
RUN mkdir -p src/main/resources/static
COPY --from=frontend /frontend/build src/main/resources/static

FROM openjdk:15-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
EXPOSE 8080
USER spring:spring
ARG JAR_FILE=backend/target/*.jar
COPY ${JAR_FILE} backend/app.jar
ENTRYPOINT ["java","-jar","/backend/app.jar"]
