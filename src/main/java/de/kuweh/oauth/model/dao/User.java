package de.kuweh.oauth.model.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotNull
	@Size(min=3, max=255)
	private String email;
	
	@NotNull
	@Size(min=6, max=255)
	private String password;
	
	public User() {}
	
	public User(long id)
	{
		this.id = id;
	}
	
	public User(String email, String password) 
	{
		this.email = email;
		this.password = password;
	}

	public long getId() 
	{
		return id;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}
}
