FROM maven:3.8.3-openjdk-17-slim

WORKDIR /workspace/cluster-task
COPY . .
RUN mvn clean install -DskipTests
EXPOSE 8080
CMD mvn spring-boot:run


