FROM eclipse-temurin:21-jdk

WORKDIR /app

# target/ms-0.0.1-SNAPSHOT.jar app.jar
COPY target/*.jar app.jar 

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]