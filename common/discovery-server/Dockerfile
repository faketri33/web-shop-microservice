FROM amazoncorretto:17
WORKDIR /app

COPY /target/*.jar /app/discovery-serivce.jar
ENTRYPOINT ["java", "-jar", "/app/discovery-serivce.jar"]