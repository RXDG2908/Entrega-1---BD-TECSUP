package cine;

import java.sql.*;

public class Extra {

    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USER = "SYSTEM";
    private static final String PASS = "oracle";

    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    // =============================
    // MOSTRAR VISTA CARTELERA
    // =============================
    public String mostrarVistaCartelera() {
        String sql = "SELECT * FROM V_INFO_CARTELERA";

        try (Connection conn = conectar();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            StringBuilder sb = new StringBuilder("=== CARTELERA ===\n");

            while (rs.next()) {
                sb.append("\nFunción: ").append(rs.getInt("id_funcion"))
                        .append("\nTítulo: ").append(rs.getString("titulo"))
                        .append("\nSala: ").append(rs.getInt("sala"))
                        .append("\nTipo: ").append(rs.getString("tipo"))
                        .append("\nFecha: ").append(rs.getDate("fecha"))
                        .append("\nHora: ").append(rs.getString("hora"))
                        .append("\nPrecio: ").append(rs.getDouble("precio"))
                        .append("\nAsientos disponibles: ").append(rs.getInt("asientos_disponibles"))
                        .append("\n----------------------");
            }

            return sb.toString();

        } catch (SQLException e) {
            return "ERROR vista: " + e.getMessage();
        }
    }

    // =============================
    // CONSULTAR TRIGGER (solo texto)
    // =============================
    public String mostrarTriggerCliente() {
        return """
                TRIGGER TRG_FORMATO_CLIENTE:
                BEFORE INSERT OR UPDATE ON Cliente
                - Convierte email a minúsculas
                - Convierte nombre a MAYÚSCULAS
                """;
    }

    // =============================
    // PROCESO ALMACENADO
    // =============================
    public String venderBoleto(int idFuncion, int idCliente, int asiento) {

        String sql = "{ call SP_VENDER_BOLETO(?, ?, ?, ?) }";

        try (Connection conn = conectar();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, idFuncion);
            cs.setInt(2, idCliente);
            cs.setInt(3, asiento);
            cs.registerOutParameter(4, Types.VARCHAR);

            cs.execute();

            return cs.getString(4);

        } catch (SQLException e) {
            return "ERROR: " + e.getMessage();
        }
    }
}
