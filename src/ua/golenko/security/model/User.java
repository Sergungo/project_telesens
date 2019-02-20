package ua.golenko.security.model;

public class User {

	private Long id;
	private String username;
	private String password;
	private String name;

	public User() {

	}

	public User(String username, String password, String name) {

		this.username = username;
		this.password = password;
		this.name = name;
	}

	public User(Long id, String username, String password, String name) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// Object overrides
	// ---------------------------------------------------------------------------

	/**
	 * The user ID is unique for each User. So this should compare User by ID only.
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object other) {
		return (other instanceof User) && (id != null) ? id.equals(((User) other).id) : (other == this);
	}

	/**
	 * The user ID is unique for each User. So User with same ID should return same
	 * hashcode.
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return (id != null) ? (this.getClass().hashCode() + id.hashCode()) : super.hashCode();
	}

	/**
	 * Returns the String representation of this User. Not required, it just pleases
	 * reading logs.
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("User[id=%d,username=%s,password=%s,name=%s]", id, username, password, name);
	}
}
