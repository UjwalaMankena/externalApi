
# Spring BOOT REST API  creation to use it or call in the other spring boot module   

## 1. Project Overview

External Api is a Spring Boot REST API application that provides mockdata to use it in other springboot application .

### Key Features

- MockDataService provides mock data
- fetch credit transactions 
- fetch cheque transactions 
- fetch aggregate transactions 
- GlobalException Handling covered
- Custom Exception covered 
- ApplicationTest covered 
- Dockerfile included  to create docker image 


## 2. Technology Stack

| Technology | Version |
|---|---|
| Java | 21 |
| Spring Boot | 4.2.0-M2 |
| Spring Web MVC | Spring Boot managed |
| Maven | 3.10.0 |


### Dependencies

The application uses the following Maven dependencies:

- spring-boot-starter-web
- spring-boot-starter-webmvc
- springdoc-openapi-starter-webmvc-ui
- spring-boot-devtools
- spring-boot-starter-actuator
- org.projectlombok.lombok
- spring-boot-webtestclient

## 3. Prerequisites

Ensure the following software is installed:

- JDK 21
- Apache Maven 3.9+
- IDE (IntelliJ IDEA / Eclipse / Spring Tool Suite)

Verify installations:

```bash
java -version
mvn -version

## 4. Database Configuration

### Database Details

No Database used

### Database Schema

MockDataService.java class provides the sample data required


 5. Application Configuration


`src/main/resources/application.properties`

```properties
# Application Configuration
spring.application.name=externalApi
server.port=8086
                       
```                  
## 6. Swagger UI 
                   
Url : http://localhost:8086/swagger-ui.html

Swagger can be used to test the API

RequestParam : accountNumber 
Type : String 
input example : "1234567890"

## 7. Running the Application

### Step 1: Clone Repository

```bash
git clone <repository-url>
```

### Step 2: Navigate to Project

```bash
cd externalApi                  
```

### Step 4: Build Application

```bash
mvn clean install
```

### Step 5: Start Spring Boot Application

```bash
mvn spring-boot:run

or 

In IDE run spring configuration class with main method : ExternalApiApplication.java 
```

The application will start at:

http://localhost:8086

### Step 6: Verify Application

Use Postman or swagger-ui to test the REST APIs.

Url : http://localhost:8086/swagger-ui.html

Swagger can be used to test the API

RequestParam : accountNumber 
Type : String 
input example : "1234567890"

## 7. Maven Commands

| Command | Description |
|---|---|
| `mvn clean` | Removes previous build output |
| `mvn compile` | Compiles source code |
| `mvn test` | Executes unit tests |
| `mvn clean install` | Builds and installs the application |
| `mvn spring-boot:run` | Starts Spring Boot application |
| `mvn package` | Generates executable JAR |

### Run Executable JAR

```bash
java -jar target/externalApi-0.0.1-SNAPSHOT.jar
```
8 . Docker commands 

Prerequistes: 
1. mvn clean install used for creation of application jar  file in target folder 
2. docker desktop running 

image creation : docker build -t externalapi .
running the  image : docker run -p 8086:8086 externalapi

---
              
**Project:**  External Api
**Artifact:** externalApi  
**Framework:** Spring Boot 4.2.0-M2  
**Language:** Java 21  


