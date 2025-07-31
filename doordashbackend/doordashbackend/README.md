# Mini DoorDash Backend

This folder contains the Spring Boot backend implementation for the Mini DoorDash application.

## Tech Stack

- **Backend Framework**: Spring Boot 3.x
- **Database**: Supabase (PostgreSQL)
- **Authentication**: Supabase Auth + JWT
- **API Documentation**: Swagger/OpenAPI
- **Build Tool**: Maven
- **Java Version**: 17+

## Planned Features

- User authentication and authorization
- Restaurant and menu data management
- Order processing and tracking
- Payment integration
- Real-time order updates
- User profile management

## Project Structure

```
doordashbackend/
├── src/
│   ├── main/
│   │   ├── java/com/minidoordash/
│   │   │   ├── controllers/     # REST API endpoints
│   │   │   ├── services/        # Business logic
│   │   │   ├── models/          # Entity classes
│   │   │   ├── repositories/    # Data access layer
│   │   │   ├── config/          # Configuration classes
│   │   │   ├── security/        # Security configuration
│   │   │   └── utils/           # Utility classes
│   │   └── resources/
│   │       ├── application.yml  # Application configuration
│   │       └── db/              # Database scripts
│   └── test/                    # Unit and integration tests
├── pom.xml                      # Maven dependencies
└── README.md                    # This file
```

## Database Schema (Supabase)

### Tables
- `users` - User profiles and authentication
- `restaurants` - Restaurant information
- `menu_items` - Food items with prices
- `orders` - Order details
- `order_items` - Items in each order
- `payments` - Payment information

## API Endpoints

### Authentication
- `POST /api/auth/register` - User registration
- `POST /api/auth/login` - User login
- `POST /api/auth/logout` - User logout

### Restaurants
- `GET /api/restaurants` - Get all restaurants
- `GET /api/restaurants/{id}` - Get restaurant by ID
- `GET /api/restaurants/{id}/menu` - Get restaurant menu

### Orders
- `POST /api/orders` - Create new order
- `GET /api/orders` - Get user orders
- `GET /api/orders/{id}` - Get order details
- `PUT /api/orders/{id}/status` - Update order status

## Getting Started

1. Install Java 17+ and Maven
2. Set up Supabase project and get connection details
3. Configure `application.yml` with Supabase credentials
4. Run `mvn spring-boot:run` to start the application

## Environment Variables

```yaml
spring:
  datasource:
    url: ${SUPABASE_DB_URL}
    username: ${SUPABASE_DB_USER}
    password: ${SUPABASE_DB_PASSWORD}
  jpa:
    hibernate:
      ddl-auto: update
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect

supabase:
  url: ${SUPABASE_URL}
  key: ${SUPABASE_ANON_KEY}
  service-key: ${SUPABASE_SERVICE_KEY}
``` 