package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class DBQuery {
    
    private static Statement statement; // Statement reference
    
    // this is where we create the statement object
    public static void setStatement(Connection conn) throws SQLException {
    
        statement = conn.createStatement();

    }
    
    // this is where we return the statement object
    public static Statement getStatement() {
        
        return statement;
        
    }
    
}
