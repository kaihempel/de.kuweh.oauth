package de.kuweh.oauth.model.view;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {

	private long id;
	
	@NotNull
	@Size(min=3, max=255)
	private String email;
	
	@NotNull
	@Size(min=6, max=255)
	private String password;

	public User() {}
	
	public User(long id) {
		this.id = id;
	}
	
	public User(String email, String password) {
		setEmail(email);
		setPassword(password);
	}
	
	public long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
