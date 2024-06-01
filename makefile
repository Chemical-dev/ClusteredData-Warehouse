# Makefile for FX Deals Application

# Variables
DOCKER_COMPOSE_FILE = docker-compose.yml
DOCKER_COMPOSE = docker-compose
MAVEN = mvn
SPRING_BOOT_RUN = spring-boot:run

# Targets
.PHONY: start-db build run stop clean

# Start the MYSQL database using Docker Compose
start-db:
	$(DOCKER_COMPOSE) up -d

# Build the application using Maven
build:
	$(MAVEN) clean install

# Run the application using Spring Boot
run:
	$(MAVEN) $(SPRING_BOOT_RUN)

# Stop the application and the MYSQL database
stop:
	$(DOCKER_COMPOSE) down

# Clean up generated files
clean:
	$(MAVEN) clean
