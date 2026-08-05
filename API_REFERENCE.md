# API Reference

This document covers all REST APIs in the backend except `HealthController`.

Base path: `/api`

Authentication rules:

- `POST /api/auth/**` is public
- all other endpoints require authentication
- endpoints marked `Admin only` also require the `ADMIN` role

---

## Auth API

### Register user

`POST /api/auth/register`

Public endpoint.

Request body: `RegisterRequest`

```json
{
  "firstName": "Milos",
  "lastName": "Petrovic",
  "email": "milos@example.com",
  "password": "secret123"
}
```

Validation:

- `firstName` is required
- `lastName` is required
- `email` is required and must be a valid email
- `password` must be at least 6 characters

Response body: `UserResponse`

```json
{
  "id": 1,
  "firstName": "Milos",
  "lastName": "Petrovic",
  "email": "milos@example.com",
  "roles": ["USER"]
}
```

Status code: `200 OK`

---

### Login

`POST /api/auth/login`

Public endpoint.

Request body: `LoginRequest`

```json
{
  "email": "milos@example.com",
  "password": "secret123"
}
```

Validation:

- `email` is required and must be a valid email
- `password` must be at least 6 characters

Response body: `LoginResponse`

```json
{
  "token": "jwt-token-here",
  "user": {
    "id": 1,
    "firstName": "Milos",
    "lastName": "Petrovic",
    "email": "milos@example.com",
    "roles": ["USER"]
  }
}
```

Status code: `200 OK`

---

## Events API

### Get all events

`GET /api/events`

Authenticated endpoint.

Response body: list of `EventResponse`

```json
[
  {
    "id": 1,
    "title": "Concert Night",
    "description": "Live music event",
    "location": "Belgrade Arena",
    "dateTime": "2026-09-01T20:00:00",
    "price": 49.99,
    "availableTickets": 500,
    "imageUrl": "https://example.com/event.jpg"
  }
]
```

Status code: `200 OK`

---

### Create event

`POST /api/events`

Admin only.

Request body: `EventRequest`

```json
{
  "title": "Concert Night",
  "description": "Live music event",
  "location": "Belgrade Arena",
  "dateTime": "2026-09-01T20:00:00",
  "price": 49.99,
  "capacity": 500,
  "imageUrl": "https://example.com/event.jpg"
}
```

Validation:

- `title` is required and must not be blank
- `description` is required and must not be blank
- `location` is required and must not be blank
- `dateTime` is required
- `price` is required
- `capacity` must be at least `1`
- `imageUrl` is optional

Response body: `EventResponse`

Status code: `200 OK`

---

### Get event by id

`GET /api/events/{id}`

Authenticated endpoint.

Path parameter:

- `id` - event id

Response body: `EventResponse`

Status code: `200 OK`

---

### Update event

`PUT /api/events/{id}`

Admin only.

Request body: `EventUpdateRequest`

```json
{
  "title": "Updated Concert Night",
  "capacity": 50
}
```

Behavior:

- only provided fields are updated
- blank string values are ignored
- `capacity` is additive, not a replacement

Response body: `EventResponse`

Status code: `200 OK`

---

### Delete event

`DELETE /api/events/{id}`

Admin only.

Path parameter:

- `id` - event id

Successful response:

```text
Event deleted successfully
```

Status code: `200 OK`

Deletion rule:

- the event cannot be deleted if any tickets already exist for it

---

### Search events

`GET /api/events/search`

Authenticated endpoint.

Query parameters:

- `title` - optional, partial match, case-insensitive
- `location` - optional, exact match
- `date` - optional, exact date-time match
- `page`, `size`, `sort` - standard Spring Data pageable parameters

Example:

`GET /api/events/search?title=concert&location=Belgrade&page=0&size=10&sort=dateTime,desc`

Response body: paginated `EventResponse`

Status code: `200 OK`

Notes:

- `title` is wrapped in `%...%` before filtering
- the query returns a Spring `Page`

---

## Users API

### Current user profile

`GET /api/users/me`

Authenticated endpoint.

Response body: `UserResponse`

```json
{
  "id": 1,
  "firstName": "Milos",
  "lastName": "Petrovic",
  "email": "milos@example.com",
  "roles": ["USER"]
}
```

Status code: `200 OK`

---

### Update current user profile

`PUT /api/users/me`

Authenticated endpoint.

Request body: `UpdateUserRequest`

```json
{
  "firstName": "Milos",
  "lastName": "Petrovic"
}
```

Behavior:

- only provided fields are updated
- blank string values are ignored

Response body: `UserResponse`

Status code: `200 OK`

---

### Change current user password

`PUT /api/users/me/password`

Authenticated endpoint.

Request body: `PasswordChangeRequest`

```json
{
  "oldPassword": "secret123",
  "newPassword": "newsecret123"
}
```

Validation:

- `oldPassword` is required
- `newPassword` is required and must be at least 6 characters

Behavior:

- the new password must be different from the old password
- the old password must match the current password

Response body: plain string

```text
Password changed successfully
```

Status code: `200 OK`

---

### Get all users

`GET /api/users`

Admin only.

Response body: list of `UserResponse`

Status code: `200 OK`

---

### Get user by id

`GET /api/users/{id}`

Admin only.

Path parameter:

- `id` - user id

Response body: `UserResponse`

Status code: `200 OK`

---

### Change user role

`PUT /api/users/{id}/role`

Admin only.

Path parameter:

- `id` - user id

Request body: `ChangeRoleRequest`

```json
{
  "role": "ADMIN"
}
```

Response body: `UserResponse`

Status code: `200 OK`

Note:

- the current admin user cannot change their own role through this endpoint

---

### Get tickets for a user

`GET /api/users/{id}/tickets`

Admin only.

Path parameter:

- `id` - user id

Response body: list of `TicketResponse`

Status code: `200 OK`

---

## Tickets API

### Purchase ticket

`POST /api/tickets/purchase/{eventId}`

Authenticated endpoint.

Path parameter:

- `eventId` - event id

Response body: `TicketResponse`

```json
{
  "id": 1,
  "eventName": "Concert Night",
  "purchaseDate": "2026-08-05T12:30:00",
  "status": "PURCHASED",
  "price": 49.99
}
```

Status code: `200 OK`

Behavior:

- increments the event's sold ticket count
- fails with a conflict when the event is sold out

---

### Get my tickets

`GET /api/tickets/my`

Authenticated endpoint.

Response body: list of `TicketResponse`

Status code: `200 OK`

---

### Get all tickets

`GET /api/tickets`

Admin only.

Response body: list of `TicketResponse`

Status code: `200 OK`

---

### Cancel ticket

`PUT /api/tickets`

Admin only.

Request body: `CancelTicketRequest`

```json
{
  "ticketId": 1
}
```

Response body: `TicketResponse`

Status code: `200 OK`

Behavior:

- sets the ticket status to `CANCELLED`
- decrements the sold ticket count for the related event

---

## Common DTOs

### `UserResponse`

```json
{
  "id": 1,
  "firstName": "Milos",
  "lastName": "Petrovic",
  "email": "milos@example.com",
  "roles": ["USER"]
}
```

### `TicketResponse`

```json
{
  "id": 1,
  "eventName": "Concert Night",
  "purchaseDate": "2026-08-05T12:30:00",
  "status": "PURCHASED",
  "price": 49.99
}
```

### `ErrorResponse`

```json
{
  "timestamp": "2026-08-05 12:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Event with id 10 not found",
  "path": "/api/events/10"
}
```

Common statuses:

- `400 Bad Request` - validation errors
- `401 Unauthorized` - missing or invalid authentication
- `403 Forbidden` - authenticated but missing required role
- `404 Not Found` - resource not found
- `409 Conflict` - business rule conflict, such as sold out events

---

## Notes

- `availableTickets` is calculated as `capacity - soldTickets`
- event search returns a paginated result, not a plain list
- `HealthController` is intentionally excluded from this document
