# Book My Show

Welcome to the Book My Show project! This project is a Spring Boot-based web application that allows users to book movie tickets online. The application leverages various technologies and tools to provide a seamless and efficient booking experience. Notably, it is designed to handle concurrent seat booking requests gracefully, ensuring data integrity and preventing overbooking through robust concurrency control mechanisms.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Architecture](#architecture)
- [Getting Started](#getting-started)
- [Configuration](#configuration)
- [API Documentation](#api-documentation)
- [Contributing](#contributing)
- [License](#license)

## Features

- Movie listings and showtimes
- Seat selection and booking
- Caching for improved performance
- Retries for OptimisticLockExceptions
- Event-driven architecture using Kafka
- Email notifications for booking confirmation
- RESTful APIs for integration with external services

## Technologies Used

- **Java**: Core programming language
- **Spring Boot**: Framework for building the application
- **Spring Data JPA**: Data access layer
- **Spring Security**: Authentication and authorization
- **Hibernate**: ORM framework
- **Kafka**: Event streaming platform
- **Ehcache**: Caching solution
- **Spring Mail**: Sending email notifications
- **MySQL**: Relational database
- **Thymeleaf**: Template engine for server-side rendering
- **Maven**: Build and dependency management

## Architecture

The application follows a microservices architecture with the following key components:

1. **Movie Service**: Handles movie listings and  showtimes.
2. **Booking Service**: Manages user bookings for selected seats.
3. **Notification Service**: Sends email notifications for booking confirmations.
4. **Kafka Broker**: Manages event streaming between services.

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven
- MySQL
- Kafka

### Clone the Repository

```bash
git clone https://github.com/saiteja623/book-my-show.git
cd book-my-show
```

### Build the Project

```bash
mvn clean install
```

### Run the Services

1. Start the Kafka broker.
2. Start the MySQL database.
3. Run the Spring Boot application:

```bash
mvn spring-boot:run
```

## Configuration

### Application Properties

Configure the application properties in `application.properties` or `application.yml` file:

```properties
# Database configuration
spring.datasource.url=jdbc:mysql://localhost:3306/bookmyshow
spring.datasource.username=root
spring.datasource.password=root

# Kafka configuration
spring.kafka.bootstrap-servers=localhost:9092

# Mail configuration
spring.mail.host=smtp.example.com
spring.mail.port=587
spring.mail.username=your-email@example.com
spring.mail.password=your-email-password
```

## API Documentation

### 1. Search shows

**Endpoint:** `/shows`

**Method:** `GET`

**Query Parameters:**
- `movie` : Filter by movie title.
- `location`: Filter by location.

**Response:**
- `200 OK`: Returns a list of shows matching the search criteria.

<img width="916" alt="image" src="https://github.com/user-attachments/assets/7c6bc4d6-a838-4801-932f-32866969de74" />


### 2. Get Available Seats

**Endpoint:** `/available-seats`

**Method:** `GET`

**Query Parameters:**
- `showId` (required): ID of the show.

**Response:**
- `200 OK`: Returns a list of seats available for specific show
  
<img width="911" alt="image" src="https://github.com/user-attachments/assets/6124f1ea-090f-42ac-ba00-6d15b64e4cc7" />


### 3. Book Ticket

**Endpoint:** `/book-seats`

**Method:** `POST`

**Request Body:**
```json
{
  "userId": "string",
  "showtimeId": "string",
  "seats": ["string"]
}
```

**Response:**
- `201 CREATED`: Returns the details of Booking.
  
  <img width="925" alt="image" src="https://github.com/user-attachments/assets/0b13567c-56f9-4a8d-a355-b0408dc040c1" />


- `409 Conflict`: Seat unavailable.
  
  <img width="921" alt="image" src="https://github.com/user-attachments/assets/d867feeb-8c74-4d48-90ba-d5fad6408ad4" />



### 4. Get booking confirmation on email

![WhatsApp Image 2025-03-03 at 17 53 25_a8743cef](https://github.com/user-attachments/assets/a55c3125-fa37-4c3d-b3a0-4eed55bcc613)




## Contributing

Contributions are welcome! Please read the [contributing guidelines](CONTRIBUTING.md) for more details.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
