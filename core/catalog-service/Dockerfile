FROM amazoncorretto:17
WORKDIR /app

COPY /target/*.jar /app/catalog-serivce.jar
ENTRYPOINT ["java", "-jar", "/app/catalog-serivce.jar"]