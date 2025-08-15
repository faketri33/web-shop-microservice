FROM amazoncorretto:17

LABEL authors="faket"

COPY /target/*.jar product-service.jar
ENTRYPOINT ["java", "-jar", "/product-service.jar"]