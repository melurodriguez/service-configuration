# API de Gestión de Clientes y Productos

Proyecto desarrollado con **Spring Boot** utilizando una arquitectura de **microservicios**. La aplicación permite administrar clientes y productos, donde cada producto pertenece a un único cliente.

Los servicios se comunican mediante **OpenFeign**, utilizan **Eureka** para el descubrimiento de servicios y **Spring Cloud Config** para centralizar la configuración.

---

# Arquitectura

El proyecto está compuesto por los siguientes microservicios:

```
service-configuration
│
├── config-server
├── eureka-server
├── customer-service
├── product-service
└── config-data
```

Cada microservicio posee responsabilidades independientes y su propia base de datos PostgreSQL.

## Componentes

| Servicio | Descripción | Puerto |
|----------|-------------|---------|
| Config Server | Centraliza la configuración de todos los microservicios | 8888 |
| Eureka Server | Registro y descubrimiento de servicios | 8761 |
| Customer Service | Gestión de clientes y administración de productos asociados | 8082 |
| Product Service | Gestión de productos | 8083 |

---

# Tecnologías utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- Spring Cloud Config
- Spring Cloud Eureka
- Spring Cloud OpenFeign
- PostgreSQL
- Docker
- Maven
- Swagger / OpenAPI

---

# Requisitos

- Java 21
- Maven
- Docker Desktop

---

# Base de datos

El proyecto utiliza **dos instancias independientes de PostgreSQL**, una para cada microservicio.

| Contenedor | Base de datos | Puerto |
|------------|---------------|---------|
| customer-db | customer_db | 5432 |
| product-db | product_db | 5433 |

Las bases de datos se crean automáticamente al iniciar los contenedores gracias a la variable `POSTGRES_DB`.

Las tablas son generadas automáticamente por Hibernate/JPA mediante la propiedad:

```properties
spring.jpa.hibernate.ddl-auto=update
```

No es necesario ejecutar scripts SQL manualmente.

---

# Configuración

La configuración de los microservicios se encuentra centralizada mediante **Spring Cloud Config**.

El proyecto `config-data` contiene los archivos de configuración utilizados por el Config Server, por lo que debe estar disponible antes de iniciar los servicios.

---

# Ejecución del proyecto

## 1. Iniciar las bases de datos

Desde la raíz del proyecto ejecutar:

```bash
docker compose up -d
```

Esto iniciará las dos bases de datos PostgreSQL necesarias para la aplicación.

---

## 2. Iniciar Config Server

```bash
cd config-server
mvn spring-boot:run
```

Disponible en:

```
http://localhost:8888
```

---

## 3. Iniciar Eureka Server

```bash
cd eureka-server
mvn spring-boot:run
```

Disponible en:

```
http://localhost:8761
```

---

## 4. Iniciar Product Service

```bash
cd product-service
mvn spring-boot:run
```

Disponible en:

```
http://localhost:8081
```

---

## 5. Iniciar Customer Service

```bash
cd customer-service
mvn spring-boot:run "-Dspring-boot.run.jvmArguments=-Duser.timezone=UTC"

```
http://localhost:8082
```

---
## Ejecución de la aplicación

Si utilizás IntelliJ IDEA y la aplicación no inicia mostrando el siguiente error:

```text
FATAL: invalid value for parameter "TimeZone": "America/Buenos_Aires"
```

configurá la JVM para utilizar la zona horaria UTC.

### IntelliJ IDEA

1. Ir a **Run → Edit Configurations...**
2. Seleccionar la aplicación Spring Boot que se desea ejecutar.
3. En **VM options**, agregar:

```text
-Duser.timezone=UTC
```

4. Guardar la configuración y volver a ejecutar la aplicación.


---
# Documentación de la API

Una vez iniciados los servicios, la documentación puede consultarse mediante Swagger.

### Customer Service

```
http://localhost:8082/swagger-ui.html
```

### Product Service

```
http://localhost:8081/swagger-ui.html
```

---

# Funcionalidades

## Customer Service

- Crear clientes.
- Obtener todos los clientes.
- Obtener un cliente junto con sus productos.
- Actualizar clientes.
- Eliminar clientes.
- Crear productos asociados a un cliente.
- Consultar un producto perteneciente a un cliente.
- Actualizar productos pertenecientes a un cliente.
- Eliminar productos pertenecientes a un cliente.

## Product Service

- Crear productos.
- Obtener todos los productos.
- Obtener un producto por ID.
- Actualizar productos.
- Eliminar productos.
- Obtener todos los productos de un cliente.

---

# Comunicación entre servicios

La aplicación implementa los siguientes componentes:

- **Spring Cloud Config** para centralizar la configuración.
- **Eureka Server** para el descubrimiento automático de servicios.
- **OpenFeign** para la comunicación entre Customer Service y Product Service.
- **PostgreSQL** como base de datos relacional independiente para cada microservicio.

---

# Decisiones de diseño

- Arquitectura basada en microservicios.
- Separación de responsabilidades entre Customer Service y Product Service.
- Cada microservicio posee su propia base de datos.
- Configuración centralizada mediante Spring Cloud Config.
- Descubrimiento automático de servicios mediante Eureka.
- Comunicación entre microservicios utilizando OpenFeign.
- Customer Service valida que un producto pertenezca al cliente antes de permitir su consulta, modificación o eliminación.
- PostgreSQL se ejecuta en contenedores Docker para facilitar la instalación y la portabilidad del proyecto.

---

# Detener el proyecto

Para detener únicamente los contenedores de PostgreSQL:

```bash
docker compose down
```

Si además se desea eliminar completamente los datos almacenados:

```bash
docker compose down -v
```

---

# Mejoras futuras

- Implementar autenticación y autorización mediante JWT.
- Contenerizar todos los microservicios mediante Docker.
- Incorporar pruebas de integración.
