package ua.golenko.security.dao;


import java.sql.Connection;
import java.sql.DriverManager;


public class MySqlConnection {
	private static Connection CONNECTION;
	private static String URL = "jdbc:mysql://localhost:3306/users?useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static String USERNAME = "root";
	private static String PASSWORD = "usbw";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
          System.out.println("Connection succesfull!");;

		} catch (Exception e) {
			 System.out.println("Connection failed!");; 	
			System.out.println(e.getMessage());
			
		}

	}

	private MySqlConnection() {

	}

	public static Connection getConnection() {
		try {
			if (CONNECTION == null || CONNECTION.isClosed()) {
				CONNECTION = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return CONNECTION;
	}
}

