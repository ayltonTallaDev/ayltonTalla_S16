package vallegrande.edu.pe.sistema_videojuegos.dao;

import vallegrande.edu.pe.sistema_videojuegos.database.Conexion;
import vallegrande.edu.pe.sistema_videojuegos.model.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    // CREATE - Registrar videojuego
    public boolean registrar(Producto producto) {

        String sql = """
            INSERT INTO producto
            (titulo, plataforma, genero, clasificacion,
             precio, stock, fecha_lanzamiento)
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;

        try (Connection conexion = Conexion.conectar();
             PreparedStatement ps =
                     conexion.prepareStatement(sql)) {

            ps.setString(1, producto.getTitulo());
            ps.setString(2, producto.getPlataforma());
            ps.setString(3, producto.getGenero());
            ps.setString(4, producto.getClasificacion());
            ps.setDouble(5, producto.getPrecio());
            ps.setInt(6, producto.getStock());
            ps.setDate(7, Date.valueOf(
                    producto.getFechaLanzamiento()));

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al registrar: "
                    + e.getMessage());
            return false;
        }
    }

    // READ - Consultar videojuegos
    public List<Producto> listar() {

        List<Producto> productos = new ArrayList<>();

        String sql = "SELECT * FROM producto ORDER BY id_producto";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement ps =
                     conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Producto producto = new Producto(
                        rs.getInt("id_producto"),
                        rs.getString("titulo"),
                        rs.getString("plataforma"),
                        rs.getString("genero"),
                        rs.getString("clasificacion"),
                        rs.getDouble("precio"),
                        rs.getInt("stock"),
                        rs.getDate("fecha_lanzamiento")
                                .toLocalDate()
                );

                productos.add(producto);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar: "
                    + e.getMessage());
        }

        return productos;
    }

    // UPDATE - Actualizar videojuego
    public boolean actualizar(Producto producto) {

        String sql = """
            UPDATE producto SET
                titulo = ?,
                plataforma = ?,
                genero = ?,
                clasificacion = ?,
                precio = ?,
                stock = ?,
                fecha_lanzamiento = ?
            WHERE id_producto = ?
            """;

        try (Connection conexion = Conexion.conectar();
             PreparedStatement ps =
                     conexion.prepareStatement(sql)) {

            ps.setString(1, producto.getTitulo());
            ps.setString(2, producto.getPlataforma());
            ps.setString(3, producto.getGenero());
            ps.setString(4, producto.getClasificacion());
            ps.setDouble(5, producto.getPrecio());
            ps.setInt(6, producto.getStock());
            ps.setDate(7, Date.valueOf(
                    producto.getFechaLanzamiento()));
            ps.setInt(8, producto.getIdProducto());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar: "
                    + e.getMessage());
            return false;
        }
    }

    // DELETE - Eliminar videojuego
    public boolean eliminar(int idProducto) {

        String sql =
                "DELETE FROM producto WHERE id_producto = ?";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement ps =
                     conexion.prepareStatement(sql)) {

            ps.setInt(1, idProducto);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar: "
                    + e.getMessage());
            return false;
        }
    }
}

