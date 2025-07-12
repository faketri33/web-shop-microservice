FROM amazoncorretto:17

LABEL authors="faket"

COPY /target/*.jar auth-service.jar
ENTRYPOINT ["java", "-jar", "/auth-service.jar"]