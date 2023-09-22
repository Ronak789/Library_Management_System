package DatabaseConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseHelper {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/library_management_system";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    
    private static Connection con = null;
    
    public static Connection connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            
            if (con != null) {
                System.out.println("Connected to the database");
            } else {
                System.out.println("Failed to connect to the database");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Error: MySQL JDBC driver not found");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error: Unable to connect to the database");
            e.printStackTrace();
        }
        
        return con;
    }
}
