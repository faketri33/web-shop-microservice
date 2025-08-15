FROM amazoncorretto:17

LABEL authors="faket"

COPY /target/*.jar user-service.jar
ENTRYPOINT ["java", "-jar", "/user-service.jar"]