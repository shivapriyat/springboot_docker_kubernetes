FROM openjdk:8-jdk-alpine
#RUN addgroup -S spring && adduser -S spring -G spring
#USER spring:spring
ARG JAR_FILE=target/kubernetes01-1.jar
COPY ${JAR_FILE} kubernetes01-1.jar
ENTRYPOINT ["java","-jar","/kubernetes01-1.jar"]
