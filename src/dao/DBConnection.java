package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // JDBC URL parts
    private static final String protocol = "jdbc";
    private static final String vendorName = ":mysql:";
    private static final String ipAddress = "//wgudb.ucertify.com/U04jTC";
    
    // JDBC URL
    private static final String jdbcURL = protocol + vendorName + ipAddress;
    
    // Driver and Connection Interface Reference
    private static final String MYSQLJDBCDriver = "com.mysql.jdbc.Driver";
    private static Connection conn;
    
    private static final String username = "U04jTC"; // Username
    
    private static final String password = "53688261646"; // Password
    
    public static Connection startConnection() {
        
        try{
            
            Class.forName(MYSQLJDBCDriver);
            conn = DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("Connection was successful.");
                        
        }
        
        catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(conn);
        return conn;
    }
    
    public static void closeConnection() {
        
        try{
        conn.close();
        System.out.println("Connection closed.");
        }
        
        catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
