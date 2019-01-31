package ua.golenko.security.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import ua.golenko.security.model.UserAccount;
import ua.golenko.security.config.SecurityConfig;

public class DataDAO {

	private static DataDAO DATA_DAO = null;

	private static String SQL_SELECT_ALL = "SELECT * FROM users;";

	private static final String FIELD_ID = "id";
	private static final String FIELD_USERNAME = "username";
	private static final String FIELD_PASSWORD = "password";
	private static final String FIELD_ROLES = "roles";

	private static final Map<String, UserAccount> mapUsers = new HashMap<String, UserAccount>();

	private DataDAO() {

	}

	public static DataDAO getInstance() {
		if (DATA_DAO == null) {
			DATA_DAO = new DataDAO();
		}
		return DATA_DAO;
	}

	// static {
	// new DataDAO().getInstance().initUsers();
	// }

	private Map<String, UserAccount> initUsers() {

		Connection con = null;

		try {
			con = MySqlConnection.getConnection();
			PreparedStatement st = con.prepareStatement(SQL_SELECT_ALL);
			ResultSet rs = st.executeQuery(SQL_SELECT_ALL);

			// while (rs.next()) {
			// String str = rs.getString("Username") + " : " + rs.getString("Password");
			// System.out.println("Username: " + str);
			// }

			while (rs.next()) {
				UserAccount temp = new UserAccount(rs.getString("Username"), rs.getString("Password"),
						rs.getString("roles"));
				mapUsers.put(temp.getUsername(), temp);
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			close(con);
		}
		return null;

		// This user has a role as User.
		//
		// UserAccount emp = new UserAccount("user", "123", SecurityConfig.ROLE_USER);
		//
		// mapUsers.put(emp.getUserName(), emp);

	}

	private void close(Connection connection) {
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	private static UserAccount extractUserAccount(ResultSet rs) throws SQLException {
		UserAccount item = new UserAccount();
		item.setId(rs.getLong(FIELD_ID));
		item.setUsername(rs.getString(FIELD_USERNAME));
		item.setPassword(rs.getString(FIELD_PASSWORD));
		item.setPassword(rs.getString(FIELD_ROLES));

		return item;
	}

	// Find a User by userName and password.
	public static UserAccount findUser(String userName, String password) {
		UserAccount u = mapUsers.get(userName);
		if (u != null && u.getPassword().equals(password)) {
			return u;
		}
		return null;
	}

}
