package data;

import java.sql.*;

public class TOSCompoundDB {

	private Connection connection = null;

	public TOSCompoundDB() {
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:TOSCompoundArte.db");
			System.out.println("Connection established with local database.");
		} catch (Exception e) {
			System.err.println("An error occurred in TOSCompoundDB. Closing database connection.");
			e.printStackTrace();

			try {
				connection.close();
			} catch (Exception e2) {
				System.err.println("An error occurred while closing database connection.");
				e.printStackTrace();
			}
		}
	}

	public Connection getConnection() {
		return connection;
	}

}
