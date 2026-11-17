# BYTE TECH – Sistema de gestión de inventario

**Evidencia:** AA2_EV01  
**Tecnologías:** Java 21, Swing, JDBC, MySQL 8.4 LTS, Maven y Git.

## 1. Descripción

BYTE TECH es una empresa académica de comercialización de computadores y accesorios. Este proyecto implementa un módulo de inventario de escritorio para registrar, consultar, actualizar y eliminar productos mediante una conexión JDBC con MySQL.

## 2. Requisitos

- Windows 10/11 de 64 bits.
- JDK 21.
- MySQL Server 8.4 LTS iniciado.
- Visual Studio Code con Extension Pack for Java.
- Maven (VS Code puede utilizar el Maven del proyecto o uno instalado).
- MySQL Workbench (opcional, recomendado).

## 3. Preparar la base de datos

1. Abre MySQL Workbench y conéctate al servidor local.
2. Abre el archivo `database/byte_tech.sql`.
3. Ejecuta el script completo (icono del rayo).
4. Confirma que existe la base de datos `byte_tech` y la tabla `productos`.

También puedes ejecutar el script desde MySQL Command Line Client.

## 4. Configurar la conexión JDBC

Abre `src/main/java/com/bytetech/configuracion/ConexionBD.java`.

Por seguridad, el proyecto permite configurar credenciales con variables de entorno:
- `DB_URL`: por defecto `jdbc:mysql://localhost:3306/byte_tech?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true`
- `DB_USER`: por defecto `root`
- `DB_PASSWORD`: contraseña definida durante la instalación de MySQL.

En Windows PowerShell, antes de ejecutar el proyecto, puedes definir:

```powershell
$env:DB_USER="root"
$env:DB_PASSWORD="TU_CLAVE_DE_MYSQL"
```

No publiques contraseñas reales en GitHub. Si prefieres configurar el valor directamente en el archivo para una prueba local, reemplaza `CAMBIA_ESTA_CLAVE` por tu contraseña, pero no subas esa modificación con la clave al repositorio.

## 5. Ejecutar desde VS Code

1. Abre VS Code.
2. Selecciona Archivo > Abrir carpeta y abre `BYTE_TECH_JDBC`.
3. Espera a que Java y Maven reconozcan el proyecto.
4. Abre la terminal integrada: Terminal > Nueva terminal.
5. Ejecuta:

```bash
mvn clean compile
mvn exec:java
```

La primera ejecución necesita internet para descargar MySQL Connector/J y los complementos de Maven.

## 6. Funcionalidades CRUD

- **CREATE / Insertar:** completa los campos y pulsa REGISTRAR PRODUCTO.
- **READ / Consultar:** la tabla presenta los registros; el buscador filtra por nombre, categoría o marca.
- **UPDATE / Actualizar:** selecciona una fila, modifica los campos y pulsa ACTUALIZAR.
- **DELETE / Eliminar:** selecciona una fila y pulsa ELIMINAR SELECCIONADO; confirma la operación.

## 7. Estructura del código

- `configuracion`: conexión JDBC.
- `modelo`: clase de entidad Producto.
- `dao`: operaciones SQL con PreparedStatement.
- `vista`: interfaz gráfica Swing y estilos.
- `principal`: punto de entrada del programa.

## 8. Versionamiento con Git y GitHub

En la terminal, desde la carpeta del proyecto:

```bash
git init
git add .
git commit -m "Versión inicial del sistema BYTE TECH JDBC"
```

Crea un repositorio vacío en https://github.com/new. Luego ejecuta los comandos que GitHub muestra para conectar el repositorio remoto. Normalmente serán:

```bash
git branch -M main
git remote add origin https://github.com/TU-USUARIO/TU-REPOSITORIO.git
git push -u origin main
```

Crea `ENLACE_REPOSITORIO.txt` en la carpeta principal y pega allí la URL real del repositorio. No dejes el texto de ejemplo.

## 9. Pruebas sugeridas

1. Registrar un portátil con precio y stock válidos.
2. Buscarlo por nombre.
3. Seleccionarlo, cambiar el precio y actualizar.
4. Seleccionarlo y eliminarlo.
5. Verificar en Workbench que las operaciones se reflejen en `byte_tech.productos`.

## 10. Consideraciones

Este es un prototipo académico funcional. Para un despliegue real se deben agregar autenticación, perfiles de usuario, copias de seguridad, auditoría y manejo seguro de secretos.
