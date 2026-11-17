package com.bytetech.configuracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Administra la conexión JDBC con MySQL.
 * Se pueden configurar las credenciales mediante variables de entorno:
 * DB_URL, DB_USER y DB_PASSWORD.
 */
public final class ConexionBD {
    private static final String URL = System.getenv().getOrDefault(
            "DB_URL", "jdbc:mysql://localhost:3306/byte_tech?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true");
    private static final String USUARIO = System.getenv().getOrDefault("DB_USER", "root");
    private static final String CLAVE = System.getenv().getOrDefault("DB_PASSWORD", "51589");

    private ConexionBD() {
    }

    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CLAVE);
    }
}
