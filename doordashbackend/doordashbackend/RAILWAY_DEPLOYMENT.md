# Railway Deployment Guide

## Prerequisites
1. Railway account (https://railway.app)
2. Railway CLI installed
3. Supabase database configured

## Deployment Steps

### 1. Login to Railway
```bash
railway login
```

### 2. Initialize Railway Project
```bash
railway init
```

### 3. Set Environment Variables
In Railway dashboard, set these environment variables:
- `SUPABASE_DB_URL`: Your Supabase database URL
- `SUPABASE_DB_USER`: Your Supabase database username
- `SUPABASE_DB_PASSWORD`: Your Supabase database password
- `SUPABASE_URL`: Your Supabase project URL
- `SUPABASE_ANON_KEY`: Your Supabase anonymous key
- `SUPABASE_SERVICE_KEY`: Your Supabase service key
- `JWT_SECRET`: A secure JWT secret key
- `ADMIN_USERNAME`: Admin username (optional)
- `ADMIN_PASSWORD`: Admin password (optional)

### 4. Deploy
```bash
railway up
```

### 5. Get Deployment URL
```bash
railway domain
```

## Configuration Files
- `railway.json`: Railway deployment configuration
- `nixpacks.toml`: Build configuration for Java/Maven
- `application-prod.yml`: Production environment configuration

## Health Check
The application includes a health check endpoint at `/api/health`

## Troubleshooting
- Check Railway logs: `railway logs`
- Verify environment variables are set correctly
- Ensure Supabase database is accessible 