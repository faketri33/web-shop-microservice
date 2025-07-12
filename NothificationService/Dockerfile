FROM amazoncorretto:17

LABEL authors="faket"

COPY /target/*.jar notification-service.jar
ENTRYPOINT ["java", "-jar", "/notification-service.jar"]