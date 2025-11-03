package TEST1;

import java.sql.*;
import java.util.Scanner;

public class CINE {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USER = "SYSTEM";
    private static final String PASS = "oracle";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el ID de la película: ");
        int idBuscado = sc.nextInt();

        String sql = "SELECT * FROM Pelicula WHERE ID_Pelicula = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, idBuscado);   // <-- Enviamos el ID

            try (ResultSet rs = pst.executeQuery()) {

                if (rs.next()) {
                    int id = rs.getInt("ID_Pelicula");
                    String titulo = rs.getString("Titulo");
                    String genero = rs.getString("Genero");
                    int duracion = rs.getInt("Duracion");
                    String clasificacion = rs.getString("Clasificacion");

                    System.out.println("----- Información de la Película -----");
                    System.out.println("ID: " + id);
                    System.out.println("Título: " + titulo);
                    System.out.println("Género: " + genero);
                    System.out.println("Duración: " + duracion + " min");
                    System.out.println("Clasificación: " + clasificacion);

                } else {
                    System.out.println("No existe una película con ID " + idBuscado);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        sc.close();
    }
}