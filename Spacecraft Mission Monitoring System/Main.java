import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        // Database URL format: jdbc:mysql://hostname:port/databaseName
        String url = "jdbc:mysql://localhost:3306/mydatabase"; // Replace "mydatabase" with your database name
        String username = "root"; // e.g., "root"
        String password = "root"; // your MySQL password

        try {
            // Load the MySQL JDBC driver (optional in recent Java versions)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database.");

            // Create a Statement object to execute SQL queries
            Statement statement = connection.createStatement();

            // Create the table if it doesn't exist (optional if you already created it)
            String createTableSQL = "CREATE TABLE IF NOT EXISTS users ("
                    + "id INT PRIMARY KEY AUTO_INCREMENT, "
                    + "name VARCHAR(100) NOT NULL, "
                    + "email VARCHAR(100) NOT NULL)";
            statement.executeUpdate(createTableSQL);
            System.out.println("Table created or already exists.");

            // Insert a record
            String insertSQL = "INSERT INTO users (name, email) VALUES ('Alice Smith', 'alice.smith@example.com')";
            statement.executeUpdate(insertSQL);
            System.out.println("Inserted a record into the table.");

            // Query the table
            String querySQL = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(querySQL);

            // Process the ResultSet
            System.out.println("Data in 'users' table:");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email);
            }

            // Close resources
            resultSet.close();
            statement.close();
            connection.close();
            System.out.println("Connection closed.");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found. Please include it in your project’s classpath.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("An SQL error occurred:");
            e.printStackTrace();
        }
    }
}
