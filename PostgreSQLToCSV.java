import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class PostgreSQLToCSV {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost";
        String user = "";
        String password = "";

        String query = "SELECT \"code\", \"package_type\", \"GTIN\", \"brand\", \"emitted_hvhh\", " +
                "\"emitted_date\", \"emitted_time\", \"seller_hvhh\", \"sale_date\", " +
                "\"sale_time\", \"sale_doc_Id\", \"product_group_name\" " +
                "FROM datamatrix_codes;";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query);
             FileWriter csvWriter = new FileWriter("output.csv")) {

            String[] headers = {
                    "code", "Package Type", "GTIN", "Brand", "emitted hvhh",
                    "emitted Date", "emitted Time", "seller hvhh", "sale date",
                    "sale time", "sale doc. Id", "product_group_name"
            };

            for (int i = 0; i < headers.length; i++) {
                csvWriter.append(headers[i]);
                if (i < headers.length - 1) csvWriter.append(",");
            }
            csvWriter.append("\n");

            while (rs.next()) {
                for (int i = 1; i <= headers.length; i++) {
                    String value = rs.getString(i);
                    if (value != null) {
                        value = value.replace("\"", "\"\"");
                        if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
                            value = "\"" + value + "\"";
                        }
                    } else {
                        value = "";
                    }
                    csvWriter.append(value);
                    if (i < headers.length) csvWriter.append(",");
                }
                csvWriter.append("\n");
            }

            csvWriter.flush();
            csvWriter.close();
            System.out.println("Complete");

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}