package be.intecbrussel.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {

    private Connection connection;

    private static ConnectionProvider instance = new ConnectionProvider();

    public Connection getConnection() throws SQLException {
        if (connection == null){
            connection =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/company_db",
                    "root","intec123");
        }
        return connection;
    }

    public static ConnectionProvider getInstance() {
        return instance;
    }
}
