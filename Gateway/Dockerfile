FROM amazoncorretto:17

LABEL authors="faket"

COPY /target/*.jar gateway-service.jar
ENTRYPOINT ["java", "-jar", "/gateway-service.jar"]