package cl.gob.ips.poc.test_drl_poc_ips;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
 
/**
 * This program demonstrates how to establish database connection to Microsoft
 * SQL Server.
 * @author www.codejava.net
 *
 */
public class JdbcSQLServerConnection {
 
	private static final String jdbcDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	
    public static Connection getConnection() {
 
        Connection conn = null;
 
        try
        {
           Class.forName(jdbcDriver).newInstance();
           System.out.println("JDBC driver loaded");
        }
        catch (Exception err)
        {
           System.err.println("Error loading JDBC driver");
           err.printStackTrace(System.err);
           System.exit(0);
        }
        
        try {
 
            String dbURL = "jdbc:sqlserver://10.88.3.133:1433;databasename=dbPacs";
            String user = "APP_PACS";
            String pass = "Dev2020P.-";
            conn = DriverManager.getConnection(dbURL, user, pass);
            
            
            
            
            
            
            
            
//            if (conn != null) {
//                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
//                System.out.println("Driver name: " + dm.getDriverName());
//                System.out.println("Driver version: " + dm.getDriverVersion());
//                System.out.println("Product name: " + dm.getDatabaseProductName());
//                System.out.println("Product version: " + dm.getDatabaseProductVersion());
//            }
 
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
		return conn;
    }
}