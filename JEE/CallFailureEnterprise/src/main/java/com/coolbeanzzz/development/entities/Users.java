package com.coolbeanzzz.development.entities;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity @Table(name="Users")

@XmlRootElement		
public class Users implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="username") private String username;
	@Column(name="passkey") private String passkey;
	@Column(name="userType") private UserType userType;

	public Users() {}
	
	public Users(int id, String username, String passkey, UserType userType) {
		super();
		this.id = id;
		this.username = username;
		this.passkey = passkey;
		this.userType = userType;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasskey() {
		return passkey;
	}

	public void setPasskey(String passkey) {
		this.passkey = passkey;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	
}
