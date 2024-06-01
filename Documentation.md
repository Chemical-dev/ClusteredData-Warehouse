# FX Deals Application

This application is designed to accept, validate, and store deal details. The application leverages a MySQL database for storage and uses Java 17 with the Spring Boot framework for backend development. Maven is used for dependency management and building the project. Docker is used to containerize the PostgreSQL database to ensure a consistent and easily deployable development environment.

## Prerequisites

To run this application, ensure you have the following installed on your system:

- **Docker:** Docker is required to run the MySQL database in a container.
- **Java 17:** The application is built using Java 11, so you need to have it installed.
- **Maven:** Maven is used for building the application and managing dependencies.

## Running the Application

Follow these steps to set up and run the Clustered Data application:

### 1. Start the MySQL Database Using Docker Compose

First, you need to start the MySQL database. This can be done easily using Docker Compose, which ensures that the database runs in a containerized environment, eliminating issues related to database setup and configuration.

1. Ensure Docker is running on your system.
2. Navigate to the root directory of the project where the `docker-compose.yml` file is located.
3. Run the following command to start the PostgreSQL database in detached mode (in the background):

   ```sh
   docker-compose up -d
