# SecuritySpring App 

Este proyecto es una implementación robusta de un **Ecosistema de Seguridad** para aplicaciones Java, desarrollado con **Spring Boot 3.4.3**. El objetivo principal es proporcionar una base sólida y escalable para la autenticación y autorización de usuarios en APIs REST.

## Tecnologías Principales
* **Java 17**
* **Spring Boot 3.x**
* **Spring Security**: Manejo de filtros y control de acceso.
* **JSON Web Token (JWT)**: Autenticación segura y sin estado (Stateless).
* **Spring Data JPA**: Persistencia de datos con MySQL.
* **SpringDoc / Swagger**: Documentación interactiva de la API (OpenAPI 3.0).
* **Lombok**: Optimización y limpieza de código.

##  Características Técnicas
* **Autenticación basada en JWT**: Generación, validación y filtros de tokens para proteger endpoints.
* **Arquitectura Limpia**: Organización por capas (Controller, Service, DTO, Entity, Repository).
* **Gestión de Roles**: Control de acceso granular para diferentes tipos de usuarios.
* **Configuración Externa**: Uso de variables de entorno y archivos `.env` para proteger credenciales sensibles.
* **Manejo Global de Excepciones**: Respuestas de error estandarizadas para una mejor experiencia de integración.

##  Estructura del Proyecto
```text
src/main/java/com/odiaz/security/
├── config/       # Configuración de Beans y SecurityFilterChain
├── controller/   # Endpoints de la API (Auth, User, Product)
├── dtos/         # Objetos de Transferencia de Datos
├── entities/     # Modelos de base de datos
├── exception/    # Manejadores de errores personalizados
├── jwt/          # Lógica de filtros y utilidades de Token
├── repositories/ # Interfaces de acceso a datos
└── services/     # Lógica de negocio e implementación
