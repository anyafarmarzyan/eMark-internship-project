import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class ExportCutCodes {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost";
        String user = "your_user";
        String password = "your_password";

        String query =
                "SELECT \"full_code\", \"cut_code\", \"status\", \"product_group\" \n" +
                        "FROM datamatrix_codes \n" +
                        "LIMIT 4800;";

        try (
                Connection conn = DriverManager.getConnection(url, user, password);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                FileWriter writer = new FileWriter("output.csv")
        ) {
            String[] headers = { "full_code", "cut_code", "status", "product_group" };
            writer.write(String.join(",", headers) + "\n");

            while (rs.next()) {
                StringBuilder row = new StringBuilder();
                for (int i = 1; i <= headers.length; i++) {
                    String value = rs.getString(i);
                    if (value == null) value = "";
                    else {
                        value = value.replace("\"", "\"\"");
                        if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
                            value = "\"" + value + "\"";
                        }
                    }
                    row.append(value);
                    if (i < headers.length) row.append(",");
                }
                writer.write(row + "\n");
            }

            System.out.println("Complete");

        } catch (SQLException | IOException e) {
            System.err.println("Error");
            e.printStackTrace();
        }
    }
}
