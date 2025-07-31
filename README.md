# Mini DoorDash Clone

A full-stack food delivery application with React frontend and Spring Boot backend.

## Project Structure

- **Frontend**: React application (Create React App)
- **Backend**: Spring Boot REST API with PostgreSQL database
- **Database**: Supabase PostgreSQL

## Frontend (React)

This project was bootstrapped with [Create React App](https://github.com/facebook/create-react-app).

### Available Scripts

In the project directory, you can run:

#### `npm start`

Runs the app in the development mode.\
Open [http://localhost:3000](http://localhost:3000) to view it in your browser.

The page will reload when you make changes.\
You may also see any lint errors in the console.

#### `npm test`

Launches the test runner in the interactive watch mode.\
See the section about [running tests](https://facebook.github.io/create-react-app/docs/running-tests) for more information.

#### `npm run build`

Builds the app for production to the `build` folder.\
It correctly bundles React in production mode and optimizes the build for the best performance.

## Backend (Spring Boot)

### Running the Backend

1. Navigate to the backend directory:
   ```bash
   cd doordashbackend
   ```

2. Set environment variables:
   ```bash
   export SUPABASE_DB_URL="jdbc:postgresql://db.fbqinbkfgckofqokgevu.supabase.co:5432/postgres?sslmode=require"
   export SUPABASE_DB_USER="postgres"
   export SUPABASE_DB_PASSWORD="your_password"
   export JWT_SECRET="your_jwt_secret"
   export SUPABASE_ANON_KEY="your_anon_key"
   export SUPABASE_SERVICE_KEY="your_service_key"
   ```

3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

The backend will start on `http://localhost:8080`

### API Endpoints

- `GET /api/health` - Health check
- `GET /api/restaurants` - Get all restaurants
- `GET /api/restaurants/{id}` - Get restaurant by ID
- `GET /api/restaurants/{id}/menu` - Get restaurant menu
- `POST /api/users/login` - User login
- `POST /api/users/register` - User registration

## Database

The application uses Supabase PostgreSQL database with:
- Direct connection (port 5432) for Spring Boot compatibility
- SSL mode required
- JPA/Hibernate for ORM

## Deployment

### Railway Deployment

The backend is configured for Railway deployment with:
- Nixpacks build system
- Health check endpoint
- Environment variable configuration

See `doordashbackend/RAILWAY_DEPLOYMENT.md` for detailed deployment instructions.

## Learn More

- [React documentation](https://reactjs.org/)
- [Spring Boot documentation](https://spring.io/projects/spring-boot)
- [Supabase documentation](https://supabase.com/docs)
