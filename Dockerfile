FROM openjdk:8-alpine
ADD target/*.jar siret-app.jar
ENTRYPOINT ["java","-jar","app.jar"]