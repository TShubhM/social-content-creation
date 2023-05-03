FROM openjdk:11-jre-slim
EXPOSE 8080
ADD target/content-share.jar content-share.jar
ENTRYPOINT ["java","-jar","content-share.jar"]