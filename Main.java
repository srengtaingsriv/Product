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

            String sqlQuery = "SELECT * FROM Product";
            ResultSet rs = stmt.executeQuery(sqlQuery);

            System.out.println("Product List:");
            System.out.println("ID | Name       | Price per Unit | Active for Sell");
            System.out.println("-----------------------------------------------");

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double pricePerUnit = rs.getDouble("price_per_unit");
                boolean activeForSell = rs.getBoolean("active_for_sell");

                System.out.printf("%2d | %-10s | %-14.2f | %-14b\n", id, name, pricePerUnit, activeForSell);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}