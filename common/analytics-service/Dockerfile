FROM amazoncorretto:17
WORKDIR /app

COPY /target/*.jar /app/analystics-serivce.jar
ENTRYPOINT ["java", "-jar", "/app/analystics-serivce.jar"]