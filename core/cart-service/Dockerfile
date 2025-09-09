FROM amazoncorretto:17
WORKDIR /app

COPY /target/*.jar /app/cart-serivce.jar
ENTRYPOINT ["java", "-jar", "/app/cart-serivce.jar"]