# Event Registration REST API

A Spring Boot RESTful web service for managing events with full CRUD functionality (`GET`, `POST`, `PUT`, `DELETE`), in-memory storage, input validation, and standard HTTP status codes.

## Tech Stack
- Java 17+
- Spring Boot (Spring Web)
- Gradle
- Postman (for API testing)

## Project Structure
```text
src/main/java/kz/iitu/registration/
├── controller/
│   └── RegistrationController.java   # REST endpoints and request validation
├── model/
│   └── EventInfo.java                # Event entity (id, title, location)
├── service/
│   └── EventService.java             # In-memory storage and CRUD business logic
└── EventRegistrationApiApplication.java