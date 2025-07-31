<<<<<<< HEAD
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
=======
# Getting Started with Create React App

This project was bootstrapped with [Create React App](https://github.com/facebook/create-react-app).

## Available Scripts

In the project directory, you can run:

### `npm start`

Runs the app in the development mode.\
Open [http://localhost:3000](http://localhost:3000) to view it in your browser.

The page will reload when you make changes.\
You may also see any lint errors in the console.

### `npm test`

Launches the test runner in the interactive watch mode.\
See the section about [running tests](https://facebook.github.io/create-react-app/docs/running-tests) for more information.

### `npm run build`

Builds the app for production to the `build` folder.\
It correctly bundles React in production mode and optimizes the build for the best performance.

The build is minified and the filenames include the hashes.\
Your app is ready to be deployed!

See the section about [deployment](https://facebook.github.io/create-react-app/docs/deployment) for more information.

### `npm run eject`

**Note: this is a one-way operation. Once you `eject`, you can't go back!**

If you aren't satisfied with the build tool and configuration choices, you can `eject` at any time. This command will remove the single build dependency from your project.

Instead, it will copy all the configuration files and the transitive dependencies (webpack, Babel, ESLint, etc) right into your project so you have full control over them. All of the commands except `eject` will still work, but they will point to the copied scripts so you can tweak them. At this point you're on your own.

You don't have to ever use `eject`. The curated feature set is suitable for small and middle deployments, and you shouldn't feel obligated to use this feature. However we understand that this tool wouldn't be useful if you couldn't customize it when you are ready for it.

## Learn More

You can learn more in the [Create React App documentation](https://facebook.github.io/create-react-app/docs/getting-started).

To learn React, check out the [React documentation](https://reactjs.org/).

### Code Splitting

This section has moved here: [https://facebook.github.io/create-react-app/docs/code-splitting](https://facebook.github.io/create-react-app/docs/code-splitting)

### Analyzing the Bundle Size

This section has moved here: [https://facebook.github.io/create-react-app/docs/analyzing-the-bundle-size](https://facebook.github.io/create-react-app/docs/analyzing-the-bundle-size)

### Making a Progressive Web App

This section has moved here: [https://facebook.github.io/create-react-app/docs/making-a-progressive-web-app](https://facebook.github.io/create-react-app/docs/making-a-progressive-web-app)

### Advanced Configuration

This section has moved here: [https://facebook.github.io/create-react-app/docs/advanced-configuration](https://facebook.github.io/create-react-app/docs/advanced-configuration)

### Deployment

This section has moved here: [https://facebook.github.io/create-react-app/docs/deployment](https://facebook.github.io/create-react-app/docs/deployment)

### `npm run build` fails to minify

This section has moved here: [https://facebook.github.io/create-react-app/docs/troubleshooting#npm-run-build-fails-to-minify](https://facebook.github.io/create-react-app/docs/troubleshooting#npm-run-build-fails-to-minify)
>>>>>>> bcb69fba41c7fb376c3a31774d36f6f9e6cf0a5d
