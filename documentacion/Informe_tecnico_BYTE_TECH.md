# INFORME TÉCNICO – BYTE TECH

## Portada
**Proyecto:** BYTE TECH – Sistema de gestión de inventario  
**Evidencia:** AA2_EV01 – Codificación de módulo con conexión JDBC  
**Aprendiz:** Brayan Stewer Baquero Medina  
**Programa:** Análisis y Desarrollo de Software – SENA  
**Tecnologías:** Java 21, Swing, JDBC, MySQL 8.4, Maven y Git.

## 1. Introducción
El presente informe describe la construcción de un módulo de inventario para BYTE TECH, empresa académica dedicada a la comercialización de computadores y accesorios. El módulo permite administrar la información de los productos mediante operaciones de inserción, consulta, actualización y eliminación, utilizando Java y JDBC para comunicarse con una base de datos MySQL.

## 2. Objetivo general
Desarrollar un módulo de inventario que permita gestionar los productos de BYTE TECH de forma organizada, aplicando conexión a bases de datos mediante JDBC y buenas prácticas de codificación.

## 3. Objetivos específicos
- Diseñar una estructura de clases organizada por responsabilidades.
- Establecer conexión entre Java y MySQL mediante JDBC.
- Implementar operaciones CRUD con consultas parametrizadas.
- Construir una interfaz gráfica sencilla para administrar productos.
- Aplicar control de versiones con Git y GitHub.

## 4. Alcance
El módulo administra productos con los campos: identificador, nombre, categoría, marca, precio, stock, descripción y fecha de registro. No incluye facturación, pagos, gestión de clientes ni autenticación de usuarios.

## 5. Requisitos funcionales
- RF01: El sistema debe registrar productos.
- RF02: El sistema debe consultar y listar productos.
- RF03: El sistema debe buscar productos por nombre, categoría o marca.
- RF04: El sistema debe actualizar los datos de un producto seleccionado.
- RF05: El sistema debe eliminar un producto previa confirmación.

## 6. Requisitos no funcionales
- RNF01: El código debe estar organizado en paquetes.
- RNF02: La comunicación con MySQL debe realizarse mediante JDBC.
- RNF03: Las operaciones SQL deben utilizar PreparedStatement.
- RNF04: La interfaz debe presentar mensajes de confirmación y error.
- RNF05: Los nombres de clases deben usar PascalCase; variables y métodos, camelCase; paquetes, minúsculas.

## 7. Arquitectura
Se utiliza una organización por capas:
- Modelo: representa la entidad Producto.
- DAO: concentra las consultas SQL y operaciones CRUD.
- Configuración: administra la conexión JDBC.
- Vista: presenta la interfaz Swing.
- Principal: inicia la aplicación.

## 8. Tecnologías seleccionadas
- Java 21: lenguaje de programación.
- Swing: interfaz gráfica de escritorio.
- JDBC: API de conectividad entre Java y bases de datos.
- MySQL 8.4: sistema gestor de base de datos.
- Maven: administración de dependencias y compilación.
- Git/GitHub: control de versiones y repositorio remoto.
- Visual Studio Code: entorno de desarrollo.

## 9. Plan de trabajo
| Fase | Actividad | Resultado |
|---|---|---|
| 1 | Análisis de requisitos | Requisitos funcionales y alcance |
| 2 | Diseño | Modelo de clases y estructura de paquetes |
| 3 | Base de datos | Script SQL y tabla productos |
| 4 | Codificación | Modelo, conexión y DAO |
| 5 | Interfaz | Ventana Swing para inventario |
| 6 | Pruebas | Validación de operaciones CRUD |
| 7 | Versionamiento | Repositorio GitHub y entrega ZIP |

## 10. Pruebas
Se deben ejecutar pruebas de registro, consulta, búsqueda, actualización y eliminación. También se debe comprobar el comportamiento cuando MySQL está detenido, cuando faltan datos obligatorios y cuando precio o stock tienen valores no válidos.

## 11. Conclusión
El módulo propuesto integra Java con MySQL por medio de JDBC y aplica una separación de responsabilidades entre modelo, acceso a datos, configuración y vista. Las operaciones CRUD permiten administrar el inventario básico de BYTE TECH y constituyen una base para futuras ampliaciones del sistema.
