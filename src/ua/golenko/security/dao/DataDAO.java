package ua.golenko.security.dao;

import java.util.HashMap;
import java.util.Map;

import ua.golenko.security.bean.UserAccount;
import ua.golenko.security.config.SecurityConfig;

public class DataDAO {

//	private static DataDAO DATA_DAO = null;

//	private static String SQL_SELECT_BY_Username = "SELECT * FROM games where Username = ?;";

	private static final Map<String, UserAccount> mapUsers = new HashMap<String, UserAccount>();

	// private DataDAO() {
	//
	// }
	//
	// public static DataDAO getInstance() {
	// if (DATA_DAO == null) {
	// DATA_DAO = new DataDAO();
	// }
	// return DATA_DAO;
	// }

	static {
		initUsers();
	}

	private static void initUsers() {

		// This user has a role as User.
		UserAccount emp = new UserAccount("user", "123", SecurityConfig.ROLE_USER);

		mapUsers.put(emp.getUserName(), emp);

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
