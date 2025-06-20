# 🏨 CheckinTech

> Sistema integral de gestión hotelera desarrollado con Spring Boot, orientado a mejorar la operatividad de un hotel mediante un backend seguro, escalable y documentado.

---

## 📋 Índice

- [🎯 Objetivo del proyecto](#-objetivo-del-proyecto)
- [🛠️ Tecnologías utilizadas](#-tecnologías-utilizadas)
- [🏗️ Arquitectura general](#-arquitectura-general)
- [🧩 Funcionalidades](#-funcionalidades)
- [🛡️ Seguridad y roles](#-seguridad-y-roles)
- [📦 Endpoints principales](#-endpoints-principales)
- [🎓 Información académica](#-información-académica)
- [👥 Autores](#-autores)

---

## 🎯 Objetivo del proyecto

CheckinTech busca digitalizar y automatizar los procesos internos de un hotel, brindando un sistema backend RESTful para gestionar:

- Reservas
- Habitaciones
- Pasajeros
- Empleados
- Servicios adicionales
- Facturación automática

Incluye roles diferenciados, autenticación segura y una arquitectura basada en buenas prácticas.

---
## 🛠️ Tecnologías utilizadas

| Tecnología               | Uso principal                                                   |
|--------------------------|-----------------------------------------------------------------|
| Java 17 / 21 / 23         | Lenguaje de programación utilizado para construir la aplicación |
| Spring Boot 3.4.5         | Framework principal para el backend y configuración automática  |
| Spring Data JPA           | Abstracción para la persistencia de datos con Hibernate         |
| Spring Security + JWT     | Seguridad: autenticación y autorización con tokens              |
| Springdoc OpenAPI 2.3.0   | Documentación interactiva de la API con Swagger UI              |
| Maven                     | Gestión de dependencias y ciclo de vida del proyecto            |
| MySQL / PostgreSQL        | Bases de datos para el almacenamiento persistente               |
| Lombok                    | Eliminación de código repetitivo (getters, setters, etc.)        |



---

## 🏗️ Arquitectura general

```
com.example.demo
├── auth                      → Seguridad y login
│   ├── controller            → Login y registro
│   ├── dto                   → Tokens y credenciales
│   └── service               → JWT y validaciones
├── controller                → Endpoints REST
├── dto                       → Objetos de transferencia
│   └── crear                 → DTOs para altas
├── mapper                   → Conversores entre entidades y DTOs
├── model                    → Entidades JPA
├── repository               → Repositorios JPA
├── service                  → Lógica de negocio
├── util                     → Utilidades (cálculo de costos, etc.)
└── config                   → Configuración de seguridad y Swagger
```

---

## 🧩 Funcionalidades

🔹 Gestión completa de pasajeros y empleados  
🔹 Registro y seguimiento de reservas  
🔹 Asignación y disponibilidad de habitaciones  
🔹 Facturación con desglose de servicios  
🔹 Configuración dinámica de tarifas  
🔹 Visualización y exportación de datos  
🔹 Registro y login con tokens JWT  
🔹 Seguridad con control de roles  

---


## 🛡️ Seguridad y roles

| Rol         | Permisos principales                                                                 |
|--------------|---------------------------------------------------------------------------------------|
| `ADMIN`      | Crear, editar y eliminar empleados, habitaciones, precios y servicios                |
| `EMPLEADO`   | Crear reservas, asignar habitaciones, facturar servicios                             |
| `PASAJERO`   | Ver reservas propias, solicitar servicios, gestionar su estadía                      |

---

## 📦 Endpoints principales

| Método | Endpoint                      | Descripción                                     | Rol requerido  |
|--------|-------------------------------|------------------------------------------------|----------------|
| POST   | `/api/auth/register`          | Registro de nuevos usuarios                    | Libre acceso   |
| POST   | `/api/auth/login`             | Autenticación y obtención de JWT               | Libre acceso   |
| GET    | `/api/habitaciones`           | Listar habitaciones disponibles                | Todos          |
| POST   | `/api/reservas`               | Crear una nueva reserva                        | EMPLEADO       |
| GET    | `/api/pasajeros`              | Listar todos los pasajeros                     | ADMIN/EMPLEADO |
| POST   | `/api/factura/generar`        | Generar factura con servicios y detalles       | EMPLEADO       |

---

## 📚 Documentación Swagger

Al ejecutar el proyecto, accedé a la documentación completa desde:

```
http://localhost:8080/swagger-ui.html
```

Incluye:
- Todos los endpoints categorizados
- Modelos y esquemas de datos
- Detalle de parámetros y roles requeridos
- Pruebas de login con JWT directamente desde la interfaz


---


## 🎓 Información académica

Este proyecto fue desarrollado como **Trabajo Final Integrador** de la materia **Programación III**, cumpliendo con los siguientes requisitos académicos:

- Aplicación funcional
- Integración con base de datos
- Seguridad y control de acceso
- Documentación Swagger
- Uso de Java moderno y patrón de capas

- --
### Probador PHP (consumo de API)

En la carpeta `php-client/` se incluye un script PHP de prueba (`probador.php`) que permite enviar peticiones a la API para validar endpoints y login. Solo debe tener PHP instalado o usar XAMPP/WAMP/Laragon.

---

## 👥 Autores

- **Gabriela Garcia Retamar - Emiliano Barrientos - Malagutti Agustin**
- Universidad Tecnológica Nacional (UTN)
- Materia: **Programación III**
- Profesor: **Daniel Diaz**
- Fecha: **Junio 2025**

---
