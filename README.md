# BLITZ-TIX

BLITZ-TIX is a Spring Boot backend for an event ticketing platform built as a portfolio-ready job-search project. It provides secure authentication, role-based access, event browsing and management, and ticket purchasing workflows backed by PostgreSQL.

Frontend repository: [BLITZ-TIX Frontend](https://github.com/milos-7a/BLITZ-TIX-Frontend)

## Current State

The backend is implemented as a layered REST API and already covers the core product flow:

- JWT authentication with register and login endpoints
- USER and ADMIN roles with protected routes
- Event creation, update, deletion, listing, and search
- Ticket purchase, cancellation, and user ticket history
- Current user profile and password management
- PostgreSQL persistence with JPA/Hibernate
- Docker support for local development
- Postman collection for testing the API

## Tech Stack

- Java 21
- Spring Boot 4.0.6
- Spring Web MVC
- Spring Security
- Spring Data JPA
- PostgreSQL
- JWT
- Maven
- Docker

## Why This Project Stands Out

- Real-world domain logic for reservations and ticket availability
- Clean controller, service, repository, DTO, and security separation
- Stateless authentication with JWT
- Admin and user flows in the same backend
- Frontend-ready API design for integration with the separate React app

## Project Structure

- `controller` - REST endpoints
- `service` - business logic
- `repository` - database access
- `entity` - persistence models
- `dto` - request and response contracts
- `security` - JWT and Spring Security configuration
- `config` - application bootstrap and data initialization

## API Highlights

Authentication:

- `POST /api/auth/register`
- `POST /api/auth/login`

Events:

- `GET /api/events`
- `GET /api/events/{id}`
- `GET /api/events/search`
- `POST /api/events` `ADMIN`
- `PUT /api/events/{id}` `ADMIN`
- `DELETE /api/events/{id}` `ADMIN`

Users:

- `GET /api/users/me`
- `PUT /api/users/me`
- `PUT /api/users/me/password`
- `GET /api/users` `ADMIN`
- `GET /api/users/{id}` `ADMIN`
- `PUT /api/users/{id}/role` `ADMIN`

Tickets:

- `POST /api/tickets/purchase/{eventId}`
- `GET /api/tickets/my`
- `GET /api/tickets` `ADMIN`
- `PUT /api/tickets` `ADMIN`

Health:

- `GET /api/health`

For the full contract, see [API_REFERENCE.md](API_REFERENCE.md).

## Local Setup

### 1. Requirements

- Java 21
- Maven
- PostgreSQL 15+

### 2. Configure the application

Copy `src/main/resources/application.properties.example` to `src/main/resources/application.properties` and fill in your local values.

Typical settings:

- `spring.datasource.url`
- `spring.datasource.username`
- `spring.datasource.password`
- `jwt.secret`

### 3. Run the backend

```bash
./mvnw spring-boot:run
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

The API will start on `http://localhost:8080`.

## Docker Setup

The repository includes `docker-compose.yml` and `docker-compose.example.yml` for running the backend with PostgreSQL.

Typical flow:

1. Fill in the environment values in `docker-compose.yml`
2. Start the stack with Docker Compose
3. Connect the frontend or Postman collection to the backend at `http://localhost:8080`

## Testing the API

- Postman collection: `docs/postman/BLITZTIX.postman_collection.json`
- OpenAPI support is included through `springdoc-openapi`

## Default Development Data

The application seeds an initial admin user when the database is empty. This is useful for local testing and for checking the admin-only routes.

Demo admin credentials:

- Email: `admin@blitztix.com`
- Password: `admin123`

## Notes

- `availableTickets` is derived from `capacity - soldTickets`
- Event search supports title, location, date, and pagination
- The frontend lives in a separate repository and consumes this backend API

## Portfolio Summary

BLITZ-TIX demonstrates a complete backend product story: authentication, authorization, relational modeling, business rules, deployment support, and frontend integration. It is structured to be easy to review in a job search context and to communicate practical Spring Boot experience quickly.
