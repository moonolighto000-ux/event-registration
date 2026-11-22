# Event Registration REST API

A Spring Boot REST API for managing events, migrated from in-memory storage to persistent PostgreSQL storage using Spring Data JPA.

## Tech Stack
- **Java** & **Spring Boot** (Web MVC, Spring Data JPA)
- **PostgreSQL** (`event_db`)
- **Gradle**

## Project Structure
```text
src/main/java/kz/iitu/registration/
├── controller/
│   └── RegistrationController.java   # REST endpoints & request validation
├── model/
│   └── Event.java                    # JPA entity mapped to the "events" table
├── repository/
│   └── EventRepository.java          # Spring Data JPA repository
├── service/
│   └── EventService.java             # Business logic & persistence operations
└── EventRegistrationApiApplication.java