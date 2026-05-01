# 🏠 TP Foyer — University Dormitory Management System

A RESTful backend application built with Spring Boot for managing 
university dormitories, rooms, students and reservations.

## ✨ Features

- 🏫 **University & Foyer Management** — Link universities to dormitories
- 🧱 **Bloc Management** — Organize dormitory blocks and capacity
- 🛏️ **Room Management** — Manage rooms by type (Simple, Double, Triple)
- 🎓 **Student Management** — Register and manage students
- 📅 **Reservation System** — Handle room reservations per academic year
- ⏱️ **AOP Performance Monitoring** — Automatic execution time logging 
  for all service methods
- 🕐 **Scheduled Tasks** — Auto-check room availability every minute 
  & daily at midnight
- 📖 **Swagger UI** — Full API documentation auto-generated

## 🛠️ Tech Stack

| Technology | Usage |
|------------|-------|
| Java 17 | Core language |
| Spring Boot 4 | Framework |
| Spring Data JPA | Database layer |
| Spring MVC REST | REST API |
| Spring AOP | Performance monitoring |
| Spring Scheduler | Automated tasks |
| MapStruct | DTO mapping |
| Lombok | Code generation |
| MySQL | Database |
| Swagger / SpringDoc | API documentation |
| Maven | Build tool |

## 📐 Architecture
src/main/java/tn/esprit/tpfoyer/
├── config/         # AOP Performance Aspect
├── controller/     # REST Controllers
├── dto/            # Data Transfer Objects
├── entity/         # JPA Entities
├── mapper/         # MapStruct Mappers
├── repository/     # Spring Data JPA Repos
├── scheduler/      # Scheduled Tasks
└── service/        # Business Logic

## 📊 Data Model

- **Universite** → has one **Foyer**
- **Foyer** → has many **Blocs**
- **Bloc** → has many **Chambres**
- **Chambre** → has many **Reservations**
- **Etudiant** → has many **Reservations**
- **TypeChambre**: SIMPLE / DOUBLE / TRIPLE

## 🚀 Getting Started

### Prerequisites
- Java 17+
- Maven
- MySQL

### Installation
1. Clone the repository
```bash
   git clone YOUR_REPO_LINK_HERE
```
2. Configure `application.properties` with your MySQL credentials
3. Run the application
```bash
   mvn spring-boot:run
```
4. Access Swagger UI at:
http://localhost:8081/swagger-ui/index.html

## 🧪 Testing
JUnit & Mockito tests — coming soon.

## 👨‍💻 Developer
**Hafedh Chaibi** — Solo project
Esprit School of Engineering
