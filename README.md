# ApiCliente

API REST para la gestión de clientes, construida con Spring Boot. Expone operaciones CRUD sobre clientes y se conecta a una base de datos relacional con Spring Data JPA.

## Qué hace

- CRUD completo de clientes: listar todos, consultar por id, crear, actualizar y eliminar.
- Validación de datos con Spring Validation.
- CORS abierto a cualquier origen (`@CrossOrigin(origins = "*")`).

Un cliente tiene `idCliente`, `nombre`, `paterno`, `materno` y `email`.

## Stack

- Java: `java.version` 17 en el `pom.xml` (el compilador Maven está fijado en 21; ver pendientes)
- Spring Boot 4.0.1: Spring Data JPA, Spring WebMVC, Spring Validation
- Bases de datos: dependencias de MySQL y de PostgreSQL; la configuración activa usa PostgreSQL
- Lombok y Spring Boot DevTools
- Maven Wrapper (`mvnw`) y Docker

## Endpoints

Base: `/apiClientes`

| Método | Endpoint | Descripción | Respuesta |
| ------ | -------- | ----------- | --------- |
| GET | `/apiClientes/clientes` | Lista todos los clientes | 200 OK |
| GET | `/apiClientes/clientes/{id}` | Obtiene un cliente por su id | 200 OK |
| POST | `/apiClientes/clientes` | Crea un cliente (el `idCliente` enviado se ignora) | 201 Created |
| PUT | `/apiClientes/clientes/{id}` | Actualiza `nombre`, `paterno`, `materno` y `email`; si el id no existe, crea el cliente | 200 OK |
| DELETE | `/apiClientes/clientes/{id}` | Elimina un cliente | 204 No Content |

Ejemplo de cuerpo para crear o actualizar un cliente:

```json
{
  "nombre": "Mauricio",
  "paterno": "Santiago",
  "materno": "De Luis",
  "email": "mauricio@ejemplo.com"
}
```

## Configuración

La configuración está en `src/main/resources/application.properties`:

- Puerto del servidor: `8080`.
- Base de datos: PostgreSQL (YugabyteDB en la nube), con JPA/Hibernate (`ddl-auto=update`, `show-sql=true`).

> ⚠️ **Seguridad:** hoy la URL, el usuario y la contraseña de la base de datos están escritos en texto plano en `application.properties`, dentro del repositorio. Antes de compartir o desplegar este proyecto, muévelos a variables de entorno y rota la contraseña.

## Cómo correr

Con Maven (usa el wrapper incluido, no necesitas Maven instalado):

```bash
./mvnw spring-boot:run
```

O compila el jar y ejecútalo:

```bash
./mvnw clean package
java -jar target/*.jar
```

### Con Docker

El `Dockerfile` usa `eclipse-temurin:17-jdk-alpine`, copia el jar de `target/` y expone el puerto `8080`:

```bash
./mvnw clean package
docker build -t apiclientes .
docker run -p 8080:8080 apiclientes
```

La API queda disponible en `http://localhost:8080/apiClientes/clientes`.

## Estructura

```
ApiCliente/
├── src/main/java/com/ipn/mx/apiclientes/
│   ├── ApiClientesApplication.java
│   ├── controller/
│   │   └── ClienteController.java
│   ├── domain/
│   │   ├── entities/      # Entidad Cliente
│   │   └── repository/    # Repositorio JPA
│   └── service/
│       ├── ClienteService.java
│       └── impl/          # Implementación del servicio
├── src/main/resources/
│   └── application.properties
├── src/test/java/com/ipn/mx/apiclientes/
├── Dockerfile
├── pom.xml
├── mvnw / mvnw.cmd
└── target/                # Artefactos compilados (ver pendientes)
```

## Qué le falta / pendientes

- Sacar las credenciales de la base de datos de `application.properties` y pasarlas a variables de entorno; rotar la contraseña que hoy está en el historial del repositorio.
- Quitar la carpeta `target/` del control de versiones y asegurarse de que `.gitignore` la ignore (hoy está commiteada; borrarla desde la web no es seguro).
- Unificar la versión de Java: el `pom.xml` declara Java 17 pero el compilador está en 21, y el `Dockerfile` usa JDK 17.
- Añadir un `.env.example` con las variables necesarias, sin valores reales.

## Licencia

MIT. Ver [LICENSE](LICENSE).
