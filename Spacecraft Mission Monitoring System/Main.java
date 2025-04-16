import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws SQLException, IOException{
        //set up fields
        String url  = "jdbc:mysql://localhost:3306/?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String pass = "root";
        String sqlFile = "spacecraft_database.sql";

        //connect to MySQL server
        Connection conn = DriverManager.getConnection(url, user, pass);
        Statement  stmt = conn.createStatement();

        //read SQL schema dump into string
        String sql = new String(
            Files.readAllBytes(Paths.get(sqlFile)),
            StandardCharsets.UTF_8
        );

        //split by semicolons so we execute each statement properly
        for (String statement : sql.split("(?<=;)\\s*")) {
            statement = statement.trim();
            if (statement.isEmpty()) continue;
            stmt.execute(statement);
        }

        System.out.println("Database and tables created successfully (or already exist).");
    }
}
