package it.unical.ingsw.splitMyExpense.technicalServices.dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private static final String DRIVER = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306";
	public static final String DB = "splitmyexpense";
	public static final String USER = "root";
	public static final String PASSWORD = "root";

	private static boolean inited = false;

	private static Connection connection = null;

	public static void init() {
		if (!inited) {
			try {
				Class.forName(DRIVER);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			inited = true;

		}
	}

	public static Connection getConnection() {
		init();
		try {
			if (connection == null) {
				connection = DriverManager.getConnection(URL + "/" + DB, USER,
						PASSWORD);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

}
