BLITZ-TIX
==========

BLITZ-TIX is a backend system for event ticket booking and reservation, built with Java Spring Boot. The project demonstrates production-style backend development with REST APIs, secure authentication, and relational database design.

------------------------------------------------------------

TECH STACK
------------------------------------------------------------

- Java 17+
- Spring Boot
- Spring Web (REST API)
- Spring Security + JWT Authentication
- Spring Data JPA / Hibernate
- PostgreSQL
- Maven

------------------------------------------------------------

KEY FEATURES
------------------------------------------------------------

- Secure user authentication and authorization (JWT)
- Role-based access control (USER / ADMIN)
- Event management (create, update, delete, browse)
- Ticket reservation system with availability control
- User-specific reservation tracking
- RESTful API design following layered architecture principles

------------------------------------------------------------

ARCHITECTURE
------------------------------------------------------------

- Controller layer – REST endpoints
- Service layer – business logic
- Repository layer – database access (JPA)
- Model layer – database entities
- DTO layer – request/response models
- Security layer – JWT + Spring Security configuration

------------------------------------------------------------

DATABASE
------------------------------------------------------------

PostgreSQL relational schema including:

- users
- roles
- events
- tickets (reservations)

Relationships:
- Many-to-Many: users ↔ roles
- One-to-Many: users → tickets
- One-to-Many: events → tickets

------------------------------------------------------------

SECURITY
------------------------------------------------------------

- JWT-based stateless authentication
- Spring Security filter chain
- Protected endpoints with role-based authorization
- Separation of public and admin endpoints

------------------------------------------------------------

PROJECT HIGHLIGHTS
------------------------------------------------------------

- Clean layered architecture following Spring Boot best practices
- Fully RESTful backend suitable for frontend integration
- Real-world booking system logic (seat availability handling)
- Scalable foundation for production-like applications

------------------------------------------------------------

FUTURE IMPROVEMENTS
------------------------------------------------------------

- Frontend integration (React)
- Email notifications for reservations
- Docker deployment
- Unit & integration testing
- Pagination, filtering, and caching optimization

------------------------------------------------------------

BLITZ-TIX serves as a portfolio-ready backend project demonstrating modern Java development practices and scalable system design.