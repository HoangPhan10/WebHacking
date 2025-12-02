FROM maven:4.0.0-rc-5-eclipse-temurin-17 AS builder

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline -B

COPY src ./src

RUN mvn clean package

FROM openjdk:26-ea-21-jdk AS runner

WORKDIR /app

COPY --from=builder ./app/target/owasp10_springboot-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 4001

ENTRYPOINT ["java", "-jar", "app.jar"]