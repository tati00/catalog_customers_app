# Catalog Customers Application

Aplicación full stack para la gestión de clientes, compuesta por:

* Frontend desarrollado con React y Vite.
* Backend desarrollado con Spring Boot.
* Base de datos PostgreSQL ejecutada mediante Docker.
* Configuración basada en variables de entorno para facilitar el despliegue en distintos entornos.

---

## Arquitectura del Proyecto

```text
catalog_customers_app/
├── catalog_customers/
│   ├── mvn/
│   ├── src
│   ├── docker-compose.yml
│   ├── .env.example
│   └── pom.xml
│
├── frontend/
│   ├── src/
│   ├── public/
│   ├── package.json
│   └── vite.config.js
└── README.md
```

---

## Tecnologías Utilizadas

### Backend

* Java 21
* Spring Boot 3.5.15
* Spring Data JPA
* Hibernate
* PostgreSQL
* Maven

### Frontend

* React 19.2.6
* Vite 8.0.12
* JavaScript
* Axios

### Infraestructura

* Docker
* Docker Compose

---

## Requisitos Previos

Instalar:

* Java 17 o superior
* Node.js 18 o superior
* Docker
* Docker Compose

Verificar instalación:

```bash
java -version
node -v
npm -v
docker --version
docker compose version
```

---

## Configuración Inicial

Crear el archivo de variables de entorno:

```bash
cp .env.example .env
```

Ejemplo:

```env
DB_PORT=5434
DB_NAME=catalog_customers_db
DB_USER=postgres_user
DB_PASSWORD=p0stgr3s_psw
DB_CONTAINER_NAME=catalog-customers-db

APP_NAME=catalog_customers
```

---

## Levantar la Base de Datos

Eliminar contenedores y volúmenes previos:

```bash
docker-compose down -v
```

Iniciar PostgreSQL:

```bash
docker-compose up -d
```

Deberá mostrarse:

```text
database system is ready to accept connections
```

---

## Ejecutar el Backend

Ingresar al directorio backend:

```bash
cd catalog_customers_app/catalog_customers/
```

Ejecutar la aplicación:

```bash
mvn clean spring-boot:run
```

El backend estará disponible en:

```text
http://localhost:8080
```

---

## Ejecutar el Frontend

Abrir una nueva terminal:

```bash
cd catalog_customers_app/frontend
```

Instalar dependencias:

```bash
npm install
```

Iniciar el servidor de desarrollo:

```bash
npm run dev
```

El frontend estará disponible en:

```text
http://localhost:5173
```

---

## Flujo de Ejecución Completo

1. Crear archivo `.env`.
2. Levantar PostgreSQL mediante Docker.
3. Ejecutar el backend Spring Boot.
4. Ejecutar el frontend React.
5. Acceder desde el navegador a:

```text
http://localhost:5173
```

---

## Estado Actual

* [x] Configuración inicial del backend.
* [x] Configuración de PostgreSQL mediante Docker.
* [x] Configuración inicial del frontend con React + Vite.
* [ ] Implementación de funcionalidades de negocio.

---

## Autor

Erika Anrrango Pastillo
