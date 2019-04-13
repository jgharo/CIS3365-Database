import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnect {
    public static Connection getConnection() throws SQLException {
        Connection connection= DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=test_2;integratedSecurity=true;");

        return connection;

    }
}
