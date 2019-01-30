package ua.golenko.security.dao;

import java.util.HashMap;
import java.util.Map;

import ua.golenko.security.bean.UserAccount;
import ua.golenko.security.config.SecurityConfig;

public class DataDAO {

	private static final Map<String, UserAccount> mapUsers = new HashMap<String, UserAccount>();

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
