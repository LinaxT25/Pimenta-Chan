package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

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
            System.out.println(sqlException.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
