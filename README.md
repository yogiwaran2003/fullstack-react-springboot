# EazyStore

A full-stack sticker e-commerce application built with React and Spring Boot.

## Project Structure

```
fullstack-react-springboot/
├── stickers/          # Spring Boot backend
└── eazystore-ui/      # React frontend
```

## Tech Stack

**Frontend**
- React 19 + Vite
- Tailwind CSS 4
- React Router v7
- Axios
- Stripe.js (`@stripe/react-stripe-js`)
- React Toastify
- FontAwesome icons

**Backend**
- Spring Boot 4.0.1 (Java 21)
- Spring Security + JWT authentication
- Spring Data JPA
- H2 database (file-based, persisted)
- Stripe Java SDK
- Caffeine cache
- SpringDoc OpenAPI (Swagger UI)
- Spring Boot Actuator

## Features

- Product listings with search and filtering
- Shopping cart
- Checkout with Stripe payments
- Order history
- User registration, login, and profile management
- Contact form
- Admin panel (order management, messages)
- Protected routes with role-based access (USER / ADMIN)

## Getting Started

### Prerequisites

- Java 21+
- Node.js 18+
- A [Stripe](https://stripe.com) account (test keys are fine)

### Backend

```bash
cd stickers
./mvnw spring-boot:run
```

The API runs on `http://localhost:8080`.

Set the Stripe secret key via environment variable:

```bash
STRIPE_API_KEY=sk_test_... ./mvnw spring-boot:run
```

- H2 Console: `http://localhost:8080/h2-console`
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- Actuator: `http://localhost:8080/eazystore/actuator`

Database is stored at `~/eazystore` (H2 file mode). SQL schema and seed data can be placed at:
- `src/main/resources/sql/schema.sql`
- `src/main/resources/sql/data.sql`

### Frontend

```bash
cd eazystore-ui
npm install
npm run dev
```

The app runs on `http://localhost:5173`.

Create a `.env` file in `eazystore-ui/` (one already exists):

```env
VITE_APP_BASE_URL="http://localhost:8080/api/v1"
VITE_STRIPE_PUBLISHABLE_KEY="pk_test_..."
```

## API

All endpoints are prefixed with `/api/v1`. Authentication uses JWT tokens. Public routes (login, register, products) do not require a token.

## Environment Variables

| Variable | Description |
|---|---|
| `STRIPE_API_KEY` | Stripe secret key (backend) |
| `VITE_STRIPE_PUBLISHABLE_KEY` | Stripe publishable key (frontend) |
| `VITE_APP_BASE_URL` | Backend base URL (frontend) |
| `LOG_LEVEL` | Logging level, default `INFO` |
| `LOG_FILE_NAME` | Log output file path |
