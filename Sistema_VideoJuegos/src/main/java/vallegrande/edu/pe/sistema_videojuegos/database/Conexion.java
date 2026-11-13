package vallegrande.edu.pe.sistema_videojuegos.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL =
            "jdbc:mysql://127.0.0.1:3306/tienda_videojuegos";

    private static final String USER = "root";

    public static Connection conectar() throws SQLException {

        String password = System.getenv("DB_PASSWORD");

        if (password == null) {
            throw new SQLException(
                    "No se configuró la variable DB_PASSWORD"
            );
        }

        return DriverManager.getConnection(
                URL,
                USER,
                password
        );
    }
}

