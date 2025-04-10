import com.mysql.cj.jdbc.exceptions.SQLError;
import java.sql.*;
public class SQLDatabase {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test_schema", "root", "CS_3560_MySQLServer!!!");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM test_database_schema.test_table;");

            while (resultSet.next()) {
                System.out.println("Name: " + resultSet.getString("String1") + "\nCourse: " + resultSet.getString("String2"));
                System.out.println();
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
