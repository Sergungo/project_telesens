package ua.golenko.security.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserAccount implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String username;
	private String password;

	private List<String> roles;

	public UserAccount() {

	}

	public UserAccount(String username, String password, String... roles) {
//		this.id = id;
		this.username = username;
		this.password = password;

		this.roles = new ArrayList<String>();
		if (roles != null) {
			for (String r : roles) {
				this.roles.add(r);
			}
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	// Object overrides
	// ---------------------------------------------------------------------------

	/**
	 * The user ID is unique for each User. So this should compare User by ID only.
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object other) {
		return (other instanceof UserAccount) && (id != null) ? id.equals(((UserAccount) other).id) : (other == this);
	}

	/**
	 * The user ID is unique for each User. So User with same ID should return same  hashcode.
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return (id != null) ? (this.getClass().hashCode() + id.hashCode()) : super.hashCode();
	}

	/**
	 * Returns the String representation of this User. Not required, it just pleases reading logs.
	  * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("User[id=%d,username=%s,password=%s,roles=%s]", id, username, password, roles);
	}
}
