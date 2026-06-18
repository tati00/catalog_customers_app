# Catalog Customers Application

Aplicación full stack para la gestión de clientes, compuesta por:

* Backend desarrollado con Spring Boot.
* Frontend desarrollado con React y Vite.
* Base de datos PostgreSQL ejecutada mediante Docker Compose.
* Configuración basada en variables de entorno para facilitar el despliegue en distintos entornos.
* Documentación técnica necesaria con Swagger
* Autenticación con JWT Bearer Token.

---

## Arquitectura del Proyecto

```text
catalog_customers_app/
├── catalog_customers/
│   ├── mvn/
│   ├── src/
│   ├── docker-compose.yml
│   ├── .env.example
│   └── pom.xml
│
├── frontend/
│   ├── src/
│   ├── public/
│   ├── package.json
│   └── vite.config.js
├── LICENSE
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
* JWT Bearer

### Frontend

* React 19.2.6
* Vite 8.0.12
* JavaScript
* Axios

### Infraestructura

* Docker
* Docker Compose

### IA utilizada

* Gemini
* Claude

---

## Requisitos Previos

Instalar:

* Java 21o superior
* Node.js 18 o superior
* Docker
* Docker Compose

---
## Ejecutar el Backend
---

Crear el archivo de variables de entorno (similar a .env.example):

```bash
cp .env.example .env
```

### Levantar la Base de Datos

Eliminar contenedores y volúmenes previos:

```bash
docker-compose down -v
```

Iniciar PostgreSQL:

```bash
docker-compose up -d
```

### Levantar Backend
---
Ingresar al directorio backend:

```bash
cd catalog_customers_app/catalog_customers/
```

Ejecutar la aplicación:

```bash
mvn clean spring-boot:run
```

Ingresar a backend:

```text
http://localhost:8080
```
>Considerar que JWT exige una autenticación por lo cual por lo cual retorna "acceso prohibido"

### Pruebas en Swagger

Ingresar a Swagger:

```text
http://localhost:8080/swagger-ui/index.html
```

Realizar la prueba con las siguientes credenciales de prueba: 

```bash
  "username": "admin",
  "password": "admin123"
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

---

## Estado Actual

* [x] Configuración inicial del backend.
* [x] Configuración de PostgreSQL mediante Docker.
* [x] Configuración de JWT
* [x] Configuración de Swagger
* [x] Configuración inicial del frontend con React + Vite.
* [ ] Implementación de funcionalidades en REACT.

---
## Decisiones técnicas

- Uso JPA/Hibernate para el mapeo objeto-relacional en PostgreSQL permite robustecer la integridad de la BD
- Implementación BCryptPasswordEncoder para evitar la visualización de contraseñas, aún si la base de datos llega a estar comprometida.
- Manejo de excepciones con ResponseEntity para evitar la captura y visualización de errores internos que vulneren el sistema.
- Creación de endopoint de testing para validar el Hash de entrega de BCrypt.
- Documentación de cada endpoint con @Operation, @Tag y @ApiResponse que alerte al usuario sobre los parámetros CRUD.

## Autor
Erika Anrrango Pastillo
