import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class VulnerableApp {
    public static void main(String[] args) {
        try {
            // Hardcoded database credentials (BAD PRACTICE)
            String dbUrl = "jdbc:mysql://localhost:3306/mydb";
            String username = "admin";
            String password = "password123";

            Connection conn = DriverManager.getConnection(dbUrl, username, password);

            // SQL Injection vulnerability
            String userInput = "' OR 1=1 --";  // Simulated malicious input
            String query = "SELECT * FROM users WHERE username = '" + userInput + "'";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Print results (for demonstration)
            while (rs.next()) {
                System.out.println("User: " + rs.getString("username"));
            }

            // Improper input validation
            if (args.length > 0) {
                String filePath = args[0]; // Accept file path from user input
                System.out.println("Reading file: " + filePath);
                java.nio.file.Files.readString(java.nio.file.Path.of(filePath)); // Possible file inclusion attack
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
