FROM maven:3.9.7-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean install -DskipTests

FROM eclipse-temurin:21-jre

COPY --from=build /app/target/tgid-teste-fat.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]