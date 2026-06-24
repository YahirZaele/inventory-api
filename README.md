# 📦 Inventory API — REST API con Java 8 + Spring Boot

API REST para gestión de inventario desarrollada con Java 8, Spring Boot y MySQL.

## 🛠️ Stack Tecnológico

| Tecnología | Versión | Uso |
|---|---|---|
| Java | 8 (1.8) | Lenguaje principal |
| Spring Boot | 2.7.18 | Framework backend |
| Spring Data JPA | - | Persistencia de datos |
| MySQL | 8.x | Base de datos |
| Maven | 3.x | Build y dependencias |
| JUnit 5 | - | Pruebas unitarias |
| Mockito | - | Mocks para testing |
| OpenAPI / Swagger | 1.7.0 | Documentación de API |
| GitHub Actions | - | Pipeline CI/CD |

## 🚀 Cómo correr el proyecto

### Requisitos previos
- Java 8 instalado
- MySQL 8 corriendo en localhost:3306
- Maven instalado (o usar el wrapper)

### 1. Clonar el repositorio
```bash
git clone https://github.com/tu-usuario/inventory-api.git
cd inventory-api
```

### 2. Crear la base de datos en MySQL
```sql
CREATE DATABASE inventory_db;
```

### 3. Configurar credenciales
Edita `src/main/resources/application.properties`:
```properties
spring.datasource.username=root
spring.datasource.password=tu_password
```

### 4. Correr la aplicación
```bash
mvn spring-boot:run
```

### 5. Ver documentación Swagger
Abre en el navegador:
```
http://localhost:8080/swagger-ui.html
```

## 🧪 Correr pruebas unitarias
```bash
mvn test
```

## 📋 Endpoints disponibles

| Método | Endpoint | Descripción | Status |
|---|---|---|---|
| GET | `/api/products` | Obtener todos los productos | 200 |
| GET | `/api/products/{id}` | Obtener producto por ID | 200 / 404 |
| POST | `/api/products` | Crear nuevo producto | 201 |
| PUT | `/api/products/{id}` | Actualizar producto | 200 / 404 |
| DELETE | `/api/products/{id}` | Eliminar producto (soft delete) | 204 / 404 |
| GET | `/api/products/search?name=` | Buscar por nombre | 200 |
| GET | `/api/products/low-stock?threshold=` | Stock bajo | 200 |

## 🏗️ Arquitectura del proyecto
```
src/
├── main/java/com/yahir/inventory/
│   ├── controller/     # Capa de presentación (REST endpoints)
│   ├── service/        # Capa de lógica de negocio (@Service)
│   ├── repository/     # Capa de persistencia (@Repository)
│   ├── model/          # Entidades JPA
│   ├── dto/            # Data Transfer Objects
│   └── exception/      # Manejo centralizado de errores
└── test/               # Pruebas unitarias con JUnit 5 + Mockito
```

## 👨‍💻 Autor
**Yahir Zaele Medina Rioja** — Java Backend Developer Junior  
[linkedin.com/in/yahir-zaele-medina-rioja](https://linkedin.com/in/yahir-zaele-medina-rioja)
