# 🚀 ApiCliente — RESTful Service & Customer Management

[![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.0.1-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)](https://www.postgresql.org/)
[![Docker](https://img.shields.io/badge/Docker-Enabled-2496ED?style=for-the-badge&logo=docker&logoColor=white)](https://www.docker.com/)
[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg?style=for-the-badge)](LICENSE)

Backend RESTful diseñado para la gestión centralizada de clientes y contactos. Construido sobre **Spring Boot** y **Spring Data JPA**, implementa una arquitectura en capas limpia, desacoplada y orientada a la mantenibilidad, lista para ser desplegada en contenedores Docker y servicios cloud (PostgreSQL, Render, Railway, AWS).

---

## 📌 Tabla de Contenidos

- [Características Principales](#-características-principales)
- [Arquitectura de la Solución](#-arquitectura-de-la-solución)
- [Stack Tecnológico](#-stack-tecnológico)
- [Modelo de Datos](#-modelo-de-datos)
- [Documentación de la API (Endpoints)](#-documentación-de-la-api-endpoints)
- [Requisitos Previos](#-requisitos-previos)
- [Configuración y Variables de Entorno](#-configuración-y-variables-de-entorno)
- [Instalación y Ejecución](#-instalación-y-ejecución)
  - [Ejecución Local con Maven](#1-ejecución-local-con-maven)
  - [Ejecución con Docker](#2-ejecución-con-docker)
- [Estructura del Proyecto](#-estructura-del-proyecto)
- [Licencia](#-licencia)

---

## 🌟 Características Principales

- **Operaciones CRUD Completas:** Gestión total de entidades cliente (crear, listar, consultar por ID, actualizar y eliminar).
- **Arquitectura en Capas:** Clara separación de responsabilidades: *Controller*, *Service*, *Repository* y *Domain Entities*.
- **Persistencia Robusta:** Mapeo objeto-relacional (ORM) eficiente con **Hibernate / Spring Data JPA**.
- **Container Ready:** Empaquetado ligero y reproducible mediante **Docker multi-platform**.
- **Seguridad en Entornos:** Aislamiento total de credenciales y variables de configuración desacopladas por entorno.
- **Habilitación CORS:** Soporte para consumo desde aplicaciones frontend (Angular, React, Vue o Mobile).

---

## 🏛️ Arquitectura de la Solución

El proyecto sigue una arquitectura en capas (*Layered Architecture*) que garantiza un bajo acoplamiento y alta cohesión:

```
                  ┌────────────────────────┐
                  │    HTTP Client / UI    │
                  └───────────┬────────────┘
                              │ JSON / REST
                              ▼
┌──────────────────────────────────────────────────────────────┐
│                    API Cliente (Spring Boot)                 │
│                                                              │
│  [ Controller Layer ]   --> ClienteController (@RestController)
│           │                                                  │
│           ▼                                                  │
│  [ Service Layer ]      --> ClienteService & ClienteServiceImpl
│           │                                                  │
│           ▼                                                  │
│  [ Repository Layer ]   --> ClienteRepository (JpaRepository)│
└─────────────────────────────┬────────────────────────────────┘
                              │ SQL / JDBC
                              ▼
                  ┌────────────────────────┐
                  │ PostgreSQL / Cloud DB  │
                  └────────────────────────┘
```

---

## 🛠️ Stack Tecnológico

| Componente | Tecnología | Descripción |
| :--- | :--- | :--- |
| **Lenguaje** | Java 17 LTS | Rendimiento moderno y soporte a largo plazo |
| **Framework** | Spring Boot 4.x | Núcleo del backend y auto-configuración |
| **Persistencia** | Spring Data JPA / Hibernate | Abstracción de datos y ORM |
| **Bases de Datos** | PostgreSQL (driver incluido) | Motor relacional robusto para producción |
| **Productividad** | Project Lombok | Reducción de código repetitivo (*boilerplate*) |
| **Validaciones** | Spring Boot Starter Validation | Validación semántica de peticiones |
| **Contenedores** | Docker | Contenedorización de la aplicación |
| **Build Tool** | Apache Maven Wrapper | Compilación sin necesidad de instalación global |

---

## 📊 Modelo de Datos

Entidad `Cliente`:

| Campo | Tipo | Restricciones | Descripción |
| :--- | :--- | :--- | :--- |
| `idCliente` | `Long` | Primary Key, Auto-increment | Identificador único del cliente |
| `nombre` | `VARCHAR(50)` | NOT NULL | Nombre(s) de la persona |
| `paterno` | `VARCHAR(50)` | NOT NULL | Apellido paterno |
| `materno` | `VARCHAR(50)` | NOT NULL | Apellido materno |
| `email` | `VARCHAR(150)` | NOT NULL, UNIQUE | Correo electrónico de contacto |

---

## 📡 Documentación de la API (Endpoints)

Prefijo base: `/apiClientes`

| Método | Endpoint | Descripción | Código HTTP |
| :---: | :--- | :--- | :---: |
| `GET` | `/apiClientes/clientes` | Obtiene la lista de todos los clientes | `200 OK` |
| `GET` | `/apiClientes/clientes/{id}` | Busca y retorna un cliente por su identificador | `200 OK` |
| `POST` | `/apiClientes/clientes` | Registra un nuevo cliente | `201 Created` |
| `PUT` | `/apiClientes/clientes/{id}` | Actualiza datos de un cliente existente (o lo crea) | `200 OK` |
| `DELETE` | `/apiClientes/clientes/{id}` | Elimina el cliente correspondiente al ID | `204 No Content` |

### Ejemplos de Petición

#### 1. Crear un Cliente (`POST /apiClientes/clientes`)
```bash
curl -X POST http://localhost:8080/apiClientes/clientes \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Mauricio",
    "paterno": "Santiago",
    "materno": "De Luis",
    "email": "mauricio.santiago@ejemplo.com"
  }'
```

**Respuesta Exitosa (`201 Created`):**
```json
{
  "idCliente": 1,
  "nombre": "Mauricio",
  "paterno": "Santiago",
  "materno": "De Luis",
  "email": "mauricio.santiago@ejemplo.com"
}
```

#### 2. Consultar Cliente por ID (`GET /apiClientes/clientes/1`)
```bash
curl -X GET http://localhost:8080/apiClientes/clientes/1
```

---

## 📋 Requisitos Previos

- **JDK 17** o superior instalado localmente.
- **Docker** (opcional, para despliegue en contenedores).
- Instancia activa de **PostgreSQL** o MySQL.

---

## ⚙️ Configuración y Variables de Entorno

La aplicación utiliza variables de entorno para su configuración en entornos de producción, desarrollo o CI/CD.

1. Copia la plantilla de propiedades:
   ```bash
   cp src/main/resources/application.properties.example src/main/resources/application.properties
   ```
2. Variables de entorno admitidas:

| Variable | Valor por defecto | Descripción |
| :--- | :--- | :--- |
| `DB_URL` | `jdbc:postgresql://localhost:5432/apiclientes` | Cadena de conexión JDBC |
| `DB_USERNAME` | `postgres` | Usuario de la base de datos |
| `DB_PASSWORD` | *(vacío)* | Contraseña de la base de datos |
| `PORT` | `8080` | Puerto en el que escucha la API |

---

## 🚀 Instalación y Ejecución

### 1. Ejecución Local con Maven

Clona el repositorio e inicia el proyecto utilizando el Maven Wrapper incluido:

```bash
# Clonar el proyecto
git clone https://github.com/Maurisantiagol/ApiCliente.git
cd ApiCliente

# Ejecutar la aplicación
./mvnw spring-boot:run
```
*(En Windows PowerShell usa `.\mvnw.cmd spring-boot:run`)*

### 2. Ejecución con Docker

Puedes compilar y generar la imagen contenedor fácilmente:

```bash
# 1. Empaquetar el artefacto JAR
./mvnw clean package -DskipTests

# 2. Construir la imagen Docker
docker build -t apicliente:latest .

# 3. Correr el contenedor
docker run -d -p 8080:8080 \
  -e DB_URL="jdbc:postgresql://host.docker.internal:5432/apiclientes" \
  -e DB_USERNAME="postgres" \
  -e DB_PASSWORD="secretpassword" \
  --name apicliente-app apicliente:latest
```

La API estará lista y respondiendo en: `http://localhost:8080/apiClientes/clientes`.

---

## 📂 Estructura del Proyecto

```
ApiCliente/
├── src/
│   ├── main/
│   │   ├── java/com/ipn/mx/apiclientes/
│   │   │   ├── ApiClientesApplication.java    # Punto de entrada de la aplicación
│   │   │   ├── controller/                    # Controladores REST (Exposición de endpoints)
│   │   │   │   └── ClienteController.java
│   │   │   ├── domain/
│   │   │   │   ├── entities/                  # Entidades JPA (Mapeo de tablas)
│   │   │   │   │   └── Cliente.java
│   │   │   │   └── repository/                # Interfaces DAO / Repositorios JPA
│   │   │   │       └── ClienteRepository.java
│   │   │   └── service/                       # Lógica de negocio
│   │   │       ├── ClienteService.java
│   │   │       └── impl/
│   │   │           └── ClienteServiceImpl.java
│   │   └── resources/
│   │       └── application.properties.example # Plantilla de configuración segura
│   └── test/                                  # Pruebas unitarias y de integración
├── Dockerfile                                 # Configuración para contenedor Docker
├── pom.xml                                    # Dependencias del proyecto (Maven)
└── README.md
```

---

## 📄 Licencia

Este proyecto se encuentra bajo la licencia **MIT**. Para más detalles, consulta el archivo [LICENSE](LICENSE).
