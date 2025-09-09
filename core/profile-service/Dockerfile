FROM amazoncorretto:17
WORKDIR /app

COPY /target/*.jar /app/profile-serivce.jar
ENTRYPOINT ["java", "-jar", "/app/profile-serivce.jar"]