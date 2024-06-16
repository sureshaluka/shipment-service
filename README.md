# Shipment Service

This is a SaaS solution where customers can create shipments, and delivery partners can see and update the status of their assigned shipments. The application uses Spring Boot, OAuth2 for security, PostgreSQL for the database, and Swagger for API documentation.

## Features

- Customer management
- Delivery partner management
- Shipment management 
- PostgreSQL database integration
- Swagger API documentation

## Prerequisites

- Java 11 or later
- Maven 3.6.3 or later
- PostgreSQL 12 or later

## Getting Started

### Clone the repository
 
git clone https://github.com/sureshaluka/shipment-service.git
cd shipment-service


###  Configure PostgreSQL
Create a PostgreSQL database and update the database configurations in src/main/resources/application.properties
- spring.datasource.url=jdbc:postgresql://localhost:5432/yourdb
- spring.datasource.username=yourusername
- spring.datasource.password=yourpassword
- spring.jpa.hibernate.ddl-auto=update
- spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

### Build and Run the Application 
- mvn clean install
- mvn spring-boot:run
The application will start at http://localhost:8080.

### Swagger UI
Access the Swagger UI for API documentation at 
- http://localhost:8080/swagger-ui/index.html

### API Endpoints
## Customer Endpoints
- POST /customers - Add a new customer
- GET /customers - Get all customers
- DELETE /customers/{id} - Delete a customer by ID

## Delivery Partner Endpoints
- POST /delivery-partners - Add a new delivery partner
- GET /delivery-partners - Get all delivery partners
- DELETE /delivery-partners/{id} - Delete a delivery partner by ID

## Shipment Endpoints
- POST /shipments - Create a new shipment
- GET /shipments/delivery-partner/{id} - Get all shipments for a delivery partner by ID
- PUT /shipments/{id}/status - Update the status of a shipment by ID

### Running Tests
To run the unit tests, use the following command: 
- mvn test

 
