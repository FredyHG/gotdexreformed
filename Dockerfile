FROM java:17
FROM maven:3.8.4-openjdk-17

WORKDIR /app
COPY . /app

ENTRYPOINT ["mvn", "spring-boot:run"]