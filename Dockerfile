FROM maven:3.8.5-openjdk-17 AS build

COPY . .

RUN mvn clean package

FROM openjdk:17

COPY --from=build /target/search-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8088

ENTRYPOINT ["java", "-jar", "/app.jar"]
