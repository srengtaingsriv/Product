import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://127.0.0.1/java";
        String username = "root";
        String password = "pwd1234";

        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
             Statement stmt = conn.createStatement()) {

            String sqlQuery = "SELECT id, name, price_per_unit, active_for_sell, created_at FROM Product";
            ResultSet rs = stmt.executeQuery(sqlQuery);

            System.out.println("Product Inventory:");
            System.out.println("ID | Name       | Price | Active | Created At");
            System.out.println("-----------------------------------------------------");

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double pricePerUnit = rs.getDouble("price_per_unit");
                boolean activeForSell = rs.getBoolean("active_for_sell");
                String createdAt = rs.getString("created_at");

                System.out.printf("%2d | %-10s | %7.2f | %-6b | %-19s\n", id, name, pricePerUnit, activeForSell, createdAt);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
