package erp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBaseConnection {

    private static Connection con = null;
    private static final String URL = "jdbc:mysql://localhost:3306/erp";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Dhiraj@123";
    private static final Logger LOGGER = Logger.getLogger(DataBaseConnection.class.getName());

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "MySQL JDBC driver not found.", e);
        }
    }

    public static Connection getConnection() {
        if (con == null) {
            try {
                con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                LOGGER.info("Database Connected");
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Failed to establish the connection.", e);
            }
        }
        return con;
    }

    public static boolean checkConnection() {
        try (Connection tempCon = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            LOGGER.info("Database Connected");
            return true;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to establish the connection.", e);
            return false;
        }
    }

    public static void closeConnection() {
        if (con != null) {
            try {
                con.close();
                con = null; // Set to null to indicate that the connection has been closed
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Failed to close the connection.", e);
            }
        }
    }
}
