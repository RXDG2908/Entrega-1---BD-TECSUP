package TEST1;

import java.sql.*;
import java.util.Scanner;

public class CINE2 {

    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USER = "SYSTEM";
    private static final String PASS = "oracle";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n===== MENÚ CINE =====");
            System.out.println("1. Insertar Película");
            System.out.println("2. Modificar Película");
            System.out.println("3. Eliminar Película");
            System.out.println("4. Buscar Película");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1 -> insertarPelicula(sc);
                case 2 -> modificarPelicula(sc);
                case 3 -> eliminarPelicula(sc);
                case 4 -> buscarPelicula(sc);
                case 5 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida.");
            }

        } while (opcion != 5);

        sc.close();
    }

    // -----------------------------------------------------------
    // INSERTAR
    // -----------------------------------------------------------
    public static void insertarPelicula(Scanner sc) {

        System.out.print("ID Película: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Título: ");
        String titulo = sc.nextLine();

        System.out.print("Género: ");
        String genero = sc.nextLine();

        System.out.print("Duración (min): ");
        int duracion = sc.nextInt();
        sc.nextLine();

        System.out.print("Clasificación: ");
        String clasificacion = sc.nextLine();

        String sql = "INSERT INTO Pelicula (id_pelicula, titulo, genero, duracion, clasificacion) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, id);
            pst.setString(2, titulo);
            pst.setString(3, genero);
            pst.setInt(4, duracion);
            pst.setString(5, clasificacion);

            pst.executeUpdate();
            System.out.println("Película insertada correctamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // -----------------------------------------------------------
    // MODIFICAR
    // -----------------------------------------------------------
    public static void modificarPelicula(Scanner sc) {

        System.out.print("Ingrese el ID de la película a modificar: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Nuevo título: ");
        String titulo = sc.nextLine();

        System.out.print("Nuevo género: ");
        String genero = sc.nextLine();

        System.out.print("Nueva duración (min): ");
        int duracion = sc.nextInt();
        sc.nextLine();

        System.out.print("Nueva clasificación: ");
        String clasificacion = sc.nextLine();

        String sql = "UPDATE Pelicula SET titulo=?, genero=?, duracion=?, clasificacion=? WHERE id_pelicula=?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, titulo);
            pst.setString(2, genero);
            pst.setInt(3, duracion);
            pst.setString(4, clasificacion);
            pst.setInt(5, id);

            int filas = pst.executeUpdate();

            if (filas > 0) {
                System.out.println("Película modificada correctamente.");
            } else {
                System.out.println("No existe una película con ese ID.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // -----------------------------------------------------------
    // ELIMINAR
    // -----------------------------------------------------------
    public static void eliminarPelicula(Scanner sc) {

        System.out.print("Ingrese el ID de la película a eliminar: ");
        int id = sc.nextInt();

        String sql = "DELETE FROM Pelicula WHERE id_pelicula=?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, id);

            int filas = pst.executeUpdate();

            if (filas > 0) {
                System.out.println("Película eliminada correctamente.");
            } else {
                System.out.println("No existe una película con ese ID.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // -----------------------------------------------------------
    // BUSCAR
    // -----------------------------------------------------------
    public static void buscarPelicula(Scanner sc) {

        System.out.print("Ingrese el ID de la película: ");
        int id = sc.nextInt();

        String sql = "SELECT * FROM Pelicula WHERE id_pelicula=?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, id);

            try (ResultSet rs = pst.executeQuery()) {

                if (rs.next()) {
                    System.out.println("\n----- Información de la Película -----");
                    System.out.println("ID: " + rs.getInt("id_pelicula"));
                    System.out.println("Título: " + rs.getString("titulo"));
                    System.out.println("Género: " + rs.getString("genero"));
                    System.out.println("Duración: " + rs.getInt("duracion"));
                    System.out.println("Clasificación: " + rs.getString("clasificacion"));
                } else {
                    System.out.println("No se encontró la película.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
