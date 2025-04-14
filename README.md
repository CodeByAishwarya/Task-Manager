## Backend - Task Manager (Spring Boot)

### Tech Stack:
- **Spring Boot 2.7**
- **Java 11**
- **Maven** for project management
- **PostgreSQL** as the database
- **Spring Data JPA** (Hibernate) for ORM
- **JWT (JSON Web Token)** for authentication and authorization
- **Spring Security** for securing APIs
- **Lombok** to reduce boilerplate
- **ModelMapper** for DTO mapping
- **RESTful APIs** with standard HTTP methods

### Features Implemented:

- **User Registration & Login** using `/auth/register` and `/auth/login`
- **JWT Token Generation** after login
- **JWT Authentication Filter** to validate requests
- **Spring Security Config** to protect endpoints and allow whitelisted routes
- **Task CRUD APIs**:
  - `GET /tasks` – fetch all tasks
  - `POST /tasks` – create new task
  - `PUT /tasks/{id}` – update task
  - `DELETE /tasks/{id}` – delete task
- **DTOs** for secure data transfer (UserDTO, AuthResponse, TaskDTO, etc.)
- **Error handling** with meaningful HTTP responses

### Security: 
- Token-based stateless authentication with JWT
- Secured all endpoints except `/auth/*` for public access
- Passwords stored in encoded format using BCrypt

### Notes:
- CORS policy issues prevented full frontend-backend JWT integration in StackBlitz
- Frontend currently uses static data due to local environment limitation
- Backend APIs are tested and ready to integrate with any frontend using proper CORS settings

