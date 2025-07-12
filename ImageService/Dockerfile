FROM amazoncorretto:17

LABEL authors="faket"

COPY /target/*.jar image-service.jar
ENTRYPOINT ["java", "-jar", "/image-service.jar"]