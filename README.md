# Smart Clinic Management System

## Overview
A comprehensive clinic management system built with Spring Boot, MySQL, and Docker. This project handles doctor availability, patient appointments, and medical prescriptions.

## Features
- **Doctor Portal**: Manage availability and view appointments.
- **Patient Portal**: Search for doctors and book appointments.
- **Admin Portal**: Add doctors and manage system settings.
- **REST APIs**: Secure endpoints for all clinic operations.
- **JWT Authentication**: Secure token-based access.

## Tech Stack
- **Backend**: Java 17, Spring Boot 3.2, JPA/Hibernate
- **Database**: MySQL
- **DevOps**: Docker, GitHub Actions
- **Frontend**: HTML5, CSS3, JavaScript (Vanilla)

## Getting Started
### Docker
Run the application using Docker:
```bash
docker build -t smart-clinic .
docker run -p 8080:8080 smart-clinic
```

### Local Development
Run with Maven:
```bash
mvn spring-boot:run
```
