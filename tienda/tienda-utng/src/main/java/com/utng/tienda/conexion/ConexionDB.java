package com.utng.tienda.conexion;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Administra la conexion JDBC hacia la base de datos MySQL utng_tienda.
 * Las credenciales NUNCA se escriben en el codigo fuente: se leen desde
 * db.properties, un archivo que no se sube al repositorio (ver .gitignore).
 */
public class ConexionDB {

    private static final String ARCHIVO_CONFIG = "db.properties";

    /**
     * Abre y retorna una nueva conexion a la base de datos.
     *
     * @return Connection lista para usarse
     * @throws SQLException si la conexion falla
     */
    public static Connection obtenerConexion() throws SQLException {
        Properties props = cargarPropiedades();

        String url = props.getProperty("db.url", "jdbc:mysql://localhost:3306/utng_tienda");
        String usuario = props.getProperty("db.usuario");
        String password = props.getProperty("db.password");

        return DriverManager.getConnection(url, usuario, password);
    }

    private static Properties cargarPropiedades() {
        Properties props = new Properties();
        try (InputStream input = ConexionDB.class.getClassLoader()
                .getResourceAsStream(ARCHIVO_CONFIG)) {

            if (input == null) {
                throw new RuntimeException(
                        "No se encontro " + ARCHIVO_CONFIG
                                + ". Copia db.properties.example y completa tus credenciales locales.");
            }
            props.load(input);

        } catch (IOException e) {
            throw new RuntimeException("Error al leer " + ARCHIVO_CONFIG, e);
        }
        return props;
    }
}