package vallegrande.edu.pe.sistema_videojuegos.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PruebaConexion {

    public static void main(String[] args) {

        String sql = "SELECT id_producto, titulo FROM producto";

        try (
                Connection conexion = Conexion.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            System.out.println("Conexion exitosa a MySQL");
            System.out.println("Base de datos: tienda_videojuegos");
            System.out.println("----------------------------");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id_producto") + " - " +
                                rs.getString("titulo")
                );
            }

        } catch (SQLException e) {
            System.err.println(
                    "Error de conexion: " + e.getMessage()
            );
        }
    }
}

