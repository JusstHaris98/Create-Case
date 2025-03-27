# Bank Complaint Authentication Service

A Spring Boot microservice for handling bank complaint authentication.

## Features

- Account authentication using sort code and account number
- Swagger UI documentation
- RESTful API endpoints
- Error handling and logging

## Prerequisites

- Java 17
- Maven
- Docker (optional, for containerization)

## Building the Application

```bash
# Build with Maven
mvn clean install

# Run locally
mvn spring-boot:run
```

## Docker Build

```bash
# Build the container
docker build -t bank-complaint .

# Run the container
docker run -p 8080:8080 bank-complaint
```

## API Endpoints

### Authentication

**POST** `/api/auth/authenticate`

Authenticates a user using their bank account credentials.

Request:
```json
{
    "sortCode": "123456",
    "accountNumber": "98765432"
}
```

Response (Success):
```json
{
    "status": "success",
    "message": "Authentication successful"
}
```

Response (Error):
```json
{
    "status": "error",
    "message": "Invalid sort code or account number"
}
```

## Testing

### Valid Test Credentials

1. Sort Code: "123456", Account Number: "98765432"
2. Sort Code: "654321", Account Number: "12345678"

### Example cURL Commands

```bash
# Test with valid credentials
curl -X POST http://localhost:8080/api/auth/authenticate \
-H "Content-Type: application/json" \
-d '{
    "sortCode": "123456",
    "accountNumber": "98765432"
}'

# Test with invalid credentials
curl -X POST http://localhost:8080/api/auth/authenticate \
-H "Content-Type: application/json" \
-d '{
    "sortCode": "111111",
    "accountNumber": "22222222"
}'
```

## Swagger Documentation

Access the Swagger UI at:
```
http://localhost:8080/swagger-ui.html
```

## Deployment

### Cloud Run Deployment

```bash
# Deploy to Cloud Run
gcloud run deploy bank-complaints \
  --image gcr.io/create-case-hack/bank-complaint \
  --project create-case-hack \
  --platform managed \
  --region europe-west2 \
  --allow-unauthenticated \
  --port 8080
```

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── example/
│   │           └── bankcomplaint/
│   │               ├── BankComplaintApplication.java
│   │               ├── config/
│   │               │   └── OpenApiConfig.java
│   │               └── auth/
│   │                   ├── controller/
│   │                   ├── exception/
│   │                   ├── model/
│   │                   └── service/
│   └── resources/
│       ├── application.properties
│       └── valid_accounts.json
└── pom.xml
```

## Configuration

The application can be configured through `application.properties`:

```properties
# Server configuration
server.port=${PORT:8080}

# Logging configuration
logging.level.com.example.bankcomplaint=INFO

# OpenAPI Configuration
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details. 