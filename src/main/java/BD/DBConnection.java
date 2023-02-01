package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

public class DBConnection {
    private Connection connection;

    public DBConnection()  {
        try {
            Properties properties = new Properties();
            properties.setProperty("user", "postgres");
            properties.setProperty("password", "postgres");
            String urlDB = "jdbc:postgresql://localhost/Discord";
            connection = DriverManager.getConnection(urlDB, properties);
        } catch (SQLException sqlException) {
            Logger logger = Logger.getLogger(DBConnection.class.getName());
            logger.severe(sqlException.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
