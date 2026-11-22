
# Capitec Appointment Booking – Backend

## 1. Project Overview

Capitec Appointment Booking is a Spring Boot REST API application that provides authentication and appointment management functionality for customers.

The application uses JWT-based authentication to secure REST APIs and MySQL as the relational database.

### Key Features

- Customer authentication using email and password.
- JWT token generation and validation.
- Secure REST APIs using Spring Security.
- Appointment booking and management.
- Customer information management.
- Department information management.
- MySQL database integration.
- Spring Data JPA for database operations.

## 2. Technology Stack

| Technology | Version |
|---|---|
| Java | 21 |
| Spring Boot | 4.1.2 |
| Spring Security | Spring Boot managed |
| Spring Data JPA | Spring Boot managed |
| Spring Web MVC | Spring Boot managed |
| MySQL | 8.x |
| Maven | 3.9+ |
| JWT (JJWT) | 0.12.6 |

### Dependencies

The application uses the following Maven dependencies:

- spring-boot-starter-data-jpa
- spring-boot-starter-security
- spring-boot-starter-webmvc
- mysql-connector-j
- spring-security-crypto
- jjwt-api
- jjwt-impl
- jjwt-jackson

## 3. Prerequisites

Ensure the following software is installed:

- JDK 21
- Apache Maven 3.9+
- MySQL Server 8.x
- IDE (IntelliJ IDEA / Eclipse / Spring Tool Suite)

Verify installations:

```bash
java -version
mvn -version
mysql --version
```

## 4. Database Configuration

### Database Details

| Property | Value |
|---|---|
| Database | MySQL |
| Schema Name | capitec |
| Database Engine | InnoDB |
| Character Set | utf8mb4 |
| Collation | utf8mb4_0900_ai_ci |

### Database Schema

The application currently uses three database tables.

#### 1. user

Stores customer authentication and profile information.

| Column | Data Type | Description |
|---|---|---|
| ID | INT | Primary Key |
| name | VARCHAR(255) | Customer Name |
| PASSWORD | VARCHAR(255) | Encrypted Password |
| email | VARCHAR(255) | Customer Email |
| mobile_number | INT | Mobile Number |
| CREATED_DATE | DATETIME | Record Creation Date |
| created_by | VARCHAR(255) | Created By |
| UPDATED_DATE | DATETIME | Last Updated Date |
| updated_by | VARCHAR(255) | Updated By |

Constraints:
- ID is the primary key.
- Email must be unique.
- Mobile number must be unique.

#### 2. appointment

Stores customer appointment information.

| Column | Data Type | Description |
|---|---|---|
| ID | INT | Primary Key |
| appointment_type | VARCHAR(255) | Appointment Purpose |
| USER_ID | INT | Customer Reference |
| APPOINTMENT_DATE_TIME | DATETIME | Appointment Date and Time |
| CREATED_DATE | DATETIME | Record Creation Date |
| created_by | VARCHAR(255) | Created By |
| updated_by | VARCHAR(255) | Updated By |
| UPDATED_DATE | DATETIME | Last Updated Date |

Constraints:
- ID is the primary key.
- USER_ID references user.ID.

#### 3. department

Stores department information.

| Column | Data Type | Description |
|---|---|---|
| ID | INT | Primary Key |
| NAME | VARCHAR(45) | Department Name |
| ADDRESS | VARCHAR(45) | Department Address |
| CREATED_DATE | DATETIME | Record Creation Date |
| CREATED_BY | VARCHAR(45) | Created By |
| UPDATED_DATE | DATETIME | Last Updated Date |
| UPDATED_BY | VARCHAR(45) | Updated By |

## 5. Database Setup

### Step 1: Create Database

Execute the following SQL:

```sql
CREATE DATABASE capitec;

USE capitec;
```

### Step 2: Create Tables

Execute the supplied SQL scripts for:

1. user
2. department
3. appointment

Ensure the tables are created in the above order because appointment references user.

### Step 3: Insert Initial Customer

```sql
INSERT INTO capitec.user
(
    ID,
    name,
    PASSWORD,
    email,
    mobile_number,
    CREATED_DATE,
    created_by
)
VALUES
(
    1,
    'customer',
    '$10$uB8BXBnCo40RUPhgMmIth./RUnc6LI65y6yzdLntSrDlcKRvtqZB.',
    'admin@gmail.com',
    9876544,
    '2026-11-18 18:16:52',
    'SYSTEM'
);
```

The password value is a pre-generated password hash. Use the corresponding original password when testing authentication.

## 6. Application Configuration

Configure the MySQL connection in:

`src/main/resources/application.properties`

```properties
# Application Configuration
spring.application.name=deportment
server.port=8080

# MySQL Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/capitec
spring.datasource.username=${DB_USERNAME:root}
spring.datasource.password=${DB_PASSWORD}

# JPA Configuration
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

Note:
- Update database credentials according to your local MySQL configuration.
- Keep database passwords outside source control.
- `ddl-auto=validate` verifies that entity mappings match the existing database schema without automatically modifying tables.

## 7. Authentication – JWT

The application uses Spring Security and JSON Web Tokens (JWT) for customer authentication.

### Login API

**Endpoint:**

```http
POST /api/auth/login
```

**Request:**

```json
{
  "email": "admin@gmail.com",
  "password": "your-password"
}
```

**Successful Response:**

```json
{
  "token": "<JWT_TOKEN>",
  "user": {
    "email": "admin@gmail.com"
  }
}
```

The JWT token is returned to the Angular frontend and stored in browser local storage.

### JWT Authorization

Protected APIs require the following HTTP header:

```http
Authorization: Bearer <JWT_TOKEN>
```

Spring Security validates the JWT before allowing access to protected endpoints.

## 8. Appointment Booking API

### Create Appointment

**Endpoint:**

```http
POST /api/appointments
```

**Authorization:**

```http
Authorization: Bearer <JWT_TOKEN>
```

**Content-Type:**

```http
Content-Type: application/json
```

The API accepts appointment information and associates the appointment with the authenticated customer.

The backend should derive the customer identity from the authenticated JWT rather than trusting a USER_ID supplied by the frontend.

## 9. Running the Application

### Step 1: Clone Repository

```bash
git clone <repository-url>
```

### Step 2: Navigate to Project

```bash
cd deportment
```

### Step 3: Configure Database

- Start MySQL Server.
- Create the capitec schema.
- Execute the database creation scripts.
- Configure database credentials.

### Step 4: Build Application

```bash
mvn clean install
```

### Step 5: Start Spring Boot Application

```bash
mvn spring-boot:run
```

The application will start at:

http://localhost:8080

### Step 6: Verify Application

Use Postman or the Angular frontend to test the REST APIs.

## 10. Maven Commands

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
java -jar target/deportment-0.0.1-SNAPSHOT.jar
```

## 11. Frontend Integration

The backend is designed to integrate with the Angular 20 frontend.

| Application | URL |
|---|---|
| Angular Frontend | http://localhost:4200 |
| Spring Boot Backend | http://localhost:8080 |
| Login API | /api/auth/login |
| Appointment API | /api/appointments |

The Angular application communicates with the backend through HTTP REST APIs.

During local development, Angular Proxy Configuration can be used to forward `/api` requests to the Spring Boot backend.

## 12. Security Considerations

- Passwords must be stored using a secure password hashing algorithm such as BCrypt.
- Never store plain-text passwords.
- JWT signing secrets must be stored securely outside source control.
- Validate JWT signature and expiration.
- Protect appointment APIs using Spring Security.
- Derive customer identity from the authenticated principal.
- Use HTTPS in production.
- Never expose database credentials in Git repositories.
- Do not expose sensitive customer information in API responses.

## 13. Future Enhancements

Potential enhancements include:

- Appointment rescheduling and cancellation.
- Appointment history.
- Department and branch management.
- Email appointment confirmation.
- Role-based authorization.
- Refresh token implementation.
- Centralized exception handling.
- API documentation using OpenAPI / Swagger.
- Unit and integration testing.

---

**Project:** Capitec Appointment Booking  
**Artifact:** deportment  
**Framework:** Spring Boot 4.1.2  
**Language:** Java 21  
**Database:** MySQL  
**Security:** Spring Security + JWT  
**Frontend:** Angular 20
