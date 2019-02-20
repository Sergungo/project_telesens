package ua.golenko.security.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ua.golenko.security.model.User;

public class UserDAO {

	private static String SQL_SELECT_BY_USERNAME = "SELECT * FROM users WHERE username = ?;";
	private static UserDAO USER_DAO = null;

	private static final String FIELD_ID = "id";
	private static final String FIELD_USERNAME = "username";
	private static final String FIELD_PASSWORD = "password";
	 private static final String FIELD_NAME = "name";

	private UserDAO() {

	}

	public static UserDAO getInstance() {
		if (USER_DAO == null) {
			USER_DAO = new UserDAO();
		}
		return USER_DAO;
	}

	private static void close(Connection connection) {
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	private static User extractUserAccount(ResultSet rs) throws SQLException {
		User item = new User();
		item.setId(rs.getLong(FIELD_ID));
		item.setUsername(rs.getString(FIELD_USERNAME));
		item.setPassword(rs.getString(FIELD_PASSWORD));
		item.setName(rs.getString(FIELD_NAME));

		return item;
	}

	// Find a User by username and password.
	public static User findUser(String username, String password) {

		Connection con = null;
		User u = null;

		try {
			con = MySqlConnection.getConnection();

			PreparedStatement st = con.prepareStatement(SQL_SELECT_BY_USERNAME);

			st.setString(1, username);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				u = extractUserAccount(rs);
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			close(con);
		}

		if (u != null && u.getPassword().equals(password)) {
			
			
			
			return u;

		}

		return null;
	}

}
