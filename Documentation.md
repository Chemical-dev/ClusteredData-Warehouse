# Project Overview

The FX Deals Data Warehouse project involves developing a system to accept and store details of foreign exchange (FX) deals into a database. This project is part of a Scrum team's efforts to build a data warehouse for Bloomberg.

## Installation Instructions:

### Prerequisites:
- Java Development Kit (JDK) installed on your system
- Maven installed on your system
- MySQL database (local or cloud-based)

### Step-by-step instructions for installing and setting up the project locally:

1. **Install Java Development Kit (JDK):**
   - Download and install the JDK appropriate for your operating system from the official Oracle website or adoptopenjdk.net.

2. **Install Maven:**
   - Download and install Maven from the official Apache Maven website: [Maven Downloads](https://maven.apache.org/download.cgi)

3. **Set up MySQL Database:**
   - Ensure you have access to a MySQL database, either locally installed or hosted in the cloud.
   - Create a new database schema for the FX Deals application.

4. **Clone the Project:**
   - Clone the FX Deals project repository from the version control system (e.g., GitHub) to your local machine.

5. **Configure Database Connection:**
   - Navigate to the project directory.
   - Edit the `application.properties` file located in `src/main/resources` directory.
   - Update the database connection properties with your MySQL database credentials and schema name.

6. **Build the Project:**
   - Open a terminal/command prompt and navigate to the project directory.
   - Run the following Maven command to build the project:
     ```
     mvn clean package
     ```

7. **Run the Application:**
   - After a successful build, navigate to the `target` directory within the project.
   - Execute the following command to start the application:
     ```
     java -jar assessment-0.0.1-SNAPSHOT.jar
     ```

8. **Access the Application:**
   - Once the application is running, you can access it through your web browser or REST client.
   - By default, the application will be accessible at [http://localhost:8080](http://localhost:8080).

## Usage Guide:

### Running the Project Locally:
1. **Start the Application:**
   - Ensure that you have followed the installation instructions to set up the project locally.
   - Navigate to the project directory.
   - Run the following command to start the application:
     ```
     java -jar assessment-0.0.1-SNAPSHOT.jar
     ```

### Configuration Options:
- Database Connection:
   - Modify the database connection properties in the `application.properties` file located in the `src/main/resources` directory to configure the database connection settings.

### Interacting with the Project:
- **API Endpoints:**
   - The project exposes the following endpoints:

      - **GET /api/v1/deals:**
         - Description: Checks if the application is up and running.
         - Authorization: No authorization required.
         - Response: Returns a string message indicating the status of the application.

      - **POST /api/v1/deals:**
         - Description: Saves FX deal details into the database.
         - Authorization: No authorization required.
         - Request Body: The payload should be a JSON object with the following fields:
            - `dealUniqueId`: String (Unique identifier for the FX deal)
            - `orderingCurrencyISOCode`: String (ISO code for the ordering currency)
            - `toCurrencyISOCode`: String (ISO code for the target currency)
            - `amount`: BigDecimal (Amount of the FX deal)
         - Response: Returns a ResponseEntity with a BaseResponse containing the result of the operation. If successful, returns a success message; otherwise, returns validation errors or exceptions caught during processing.

### Sample Request Payload:
```json
{
    "dealUniqueId": "SM_061",
    "orderingCurrencyISOCode": "GBP",
    "toCurrencyISOCode": "EUR",
    "amount": 2045876.35
}
```
## Testing Instructions:

### Description of Testing Tools and Frameworks:
- The project uses JUnit and Mockito for writing and executing automated tests.
   - **JUnit:** JUnit is a popular Java testing framework used for writing unit tests.
   - **Mockito:** Mockito is a mocking framework that allows for the creation of mock objects to simulate dependencies during testing.

### Instructions for Running Automated Tests:
1. **Navigate to the Project Directory:**
   - Open a terminal or command prompt.
   - Change directory to the root directory of the project.

2. **Run the Tests:**
   - Execute the following command to run the automated tests:
     ```
     mvn test
     ```

3. **View Test Results:**
   - After running the tests, review the test results displayed in the terminal.
   - Any failures or errors encountered during the test execution will be highlighted, along with the corresponding stack trace.

### Example Test Cases:
- Below are some example test cases written for the project:

   1. **testSaveDeals_UniqueDeal_Success:**
      - Description: Tests the successful saving of a unique FX deal.
      - Expected Outcome: The deal should be saved successfully, and the response should indicate success with the appropriate data.

   2. **testSaveDeals_DuplicateDeal_Failure:**
      - Description: Tests the scenario where a duplicate FX deal is encountered.
      - Expected Outcome: The operation should fail, and the response should indicate the presence of a duplicate deal.

   3. **testSaveDeals_InternalError_Failure:**
      - Description: Tests the scenario where an internal error occurs during the saving of an FX deal.
      - Expected Outcome: The operation should fail, and the response should indicate an internal error with an appropriate error message.