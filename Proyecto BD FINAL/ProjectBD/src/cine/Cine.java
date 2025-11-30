package cine;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Cine {

    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USER = "SYSTEM";
    private static final String PASS = "oracle";

    public Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    // ======================
    // INSERTAR PELÍCULA
    // ======================
    public String insertarPelicula(int id, String titulo, String genero, int duracion, String clasificacion) {
        String sql = """
                INSERT INTO Pelicula (id_pelicula, titulo, genero, duracion, clasificacion)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection conn = conectar();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, id);
            pst.setString(2, titulo);
            pst.setString(3, genero);
            pst.setInt(4, duracion);
            pst.setString(5, clasificacion);

            pst.executeUpdate();
            return "✔ Película insertada correctamente.";

        } catch (SQLException e) {
            return "❌ ERROR: " + e.getMessage();
        }
    }

    // ======================
    // MODIFICAR PELÍCULA
    // ======================
    public String modificarPelicula(int id, String titulo, String genero, int duracion, String clasificacion) {
        String sql = """
                UPDATE Pelicula SET titulo=?, genero=?, duracion=?, clasificacion=?
                WHERE id_pelicula=?
                """;

        try (Connection conn = conectar();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, titulo);
            pst.setString(2, genero);
            pst.setInt(3, duracion);
            pst.setString(4, clasificacion);
            pst.setInt(5, id);

            int filas = pst.executeUpdate();
            return filas > 0 ? "✔ Película modificada." : "⚠ No existe ese ID.";

        } catch (SQLException e) {
            return "❌ ERROR: " + e.getMessage();
        }
    }

    // ======================
    // ELIMINAR PELÍCULA
    // ======================
    public String eliminarPelicula(int id) {
        String sql = "DELETE FROM Pelicula WHERE id_pelicula=?";

        try (Connection conn = conectar();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, id);
            int filas = pst.executeUpdate();

            return filas > 0 ? "✔ Película eliminada." : "⚠ No existe ese ID.";

        } catch (SQLException e) {
            return "❌ ERROR: " + e.getMessage();
        }
    }

    // ======================
    // BUSCAR PELÍCULA
    // ======================
    public String buscarPelicula(int id) {
        String sql = "SELECT * FROM Pelicula WHERE id_pelicula=?";

        try (Connection conn = conectar();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, id);

            try (ResultSet rs = pst.executeQuery()) {

                if (rs.next()) {
                    return "\n--- PELÍCULA ---\n" +
                            "ID: " + rs.getInt(1) + "\n" +
                            "Título: " + rs.getString(2) + "\n" +
                            "Género: " + rs.getString(3) + "\n" +
                            "Duración: " + rs.getInt(4) + "\n" +
                            "Clasificación: " + rs.getString(5);
                } else {
                    return "⚠ No se encontró película.";
                }
            }

        } catch (SQLException e) {
            return "❌ ERROR: " + e.getMessage();
        }
    }
}
