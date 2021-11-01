package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DBConnection is used to create the necessary connection and connection string for all queries within the application. Uses MySQL 8.0.25 driver
 */
public class DBConnection {
    // JDBC URL parts
    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "client_schedule";
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; // LOCAL
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference; version 8.0.25 is in use
    private static final String userName = "sqlUser"; // Username
    private static String password = "Passw0rd!"; // Password
    public static Connection connection;  // Connection Interface

    /**
     * this method starts the database connection which remains open until the application is closed
     *
     * @return
     */
    public static Connection startConnection() {

        try{
            
            Class.forName(driver);
            connection = DriverManager.getConnection(jdbcUrl, userName, password);
            System.out.println("Connection was successful.");
                        
        }
        
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        System.out.println(connection);
        return connection;
    }

    /**
     * This method closes the database connection and is used on application exit.
     */
    public static void closeConnection() {
        
        try{
        connection.close();
        System.out.println("Connection closed.");
        }
        
        catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * This method returns the connection to the database
     *
     * @return
     */
    public static Connection getConnection() {
        return connection;
    }
}
