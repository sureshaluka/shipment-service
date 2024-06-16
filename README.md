# SaaS Solution Spring Boot Application

This is a SaaS solution where customers can create shipments, and delivery partners can see and update the status of their assigned shipments. The application uses Spring Boot, OAuth2 for security, PostgreSQL for the database, and Swagger for API documentation.

## Features

- Customer management
- Delivery partner management
- Shipment management
- OAuth2 security
- PostgreSQL database integration
- Swagger API documentation

## Prerequisites

- Java 11 or later
- Maven 3.6.3 or later
- PostgreSQL 12 or later

## Getting Started

### Clone the repository

```bash
git clone https://github.com/sureshaluka/shipment-service.git
cd shipment-service

###  Configure PostgreSQL
Create a PostgreSQL database and update the database configurations in src/main/resources/application.properties
spring.datasource.url=jdbc:postgresql://localhost:5432/yourdb
spring.datasource.username=yourusername
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect



