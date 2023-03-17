package cl.ips.pacs.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase que permite obtener una conexión de base de datos
 *
 * @author Red Hat
 * @version 1.0
 * @since 2020-12-15
 **/

public class JdbcSQLServerConnection {

	private static final String jdbcDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

	public static Connection getConnection(String dbURLC, String userC, String passC) {

		Connection conn = null;

		try {
			Class.forName(jdbcDriver).newInstance();
		} catch (Exception err) {
			System.err.println("Error loading JDBC driver");
			err.printStackTrace(System.err);
			//System.exit(0);
		}

		try {
			conn = DriverManager.getConnection(dbURLC, userC, passC);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return conn;
	}
}