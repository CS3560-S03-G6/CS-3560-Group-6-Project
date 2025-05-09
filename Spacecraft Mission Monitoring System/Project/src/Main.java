import java.sql.SQLException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {

        SystemInterface system = new SystemInterface();
        system.showLoginFrame();

    }
}