FROM maven:3.8.4-openjdk-17 as builder
WORKDIR /app
COPY . /app/.
RUN mvn -f /app/pom.xml clean package -Dmaven.test.skip=true

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar /app/Simple_Rest.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/Simple_Rest.jar"]