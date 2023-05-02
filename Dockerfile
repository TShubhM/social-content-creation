FROM openjdk:11-jre-slim
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} content.jar
ENTRYPOINT ["java","-jar","content.jar"]