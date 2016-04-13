/**
 * @author Coolbeanzzz
 */
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
	@Column(name="userType") private String userType;

	public Users() {}
	
	public Users(int id, String username, String passkey, String userType) {
		super();
		this.id = id;
		this.username = username;
		this.passkey = passkey;
		this.userType = userType;
	}
	
	public Users(String username, String passkey, String userType) {
		this.username = username;
		this.passkey = passkey;
		this.userType = userType;
	}
	
	/**
	 * Retrieves user id
	 * @return int id 
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set user id
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Retrieves username 
	 * @return string username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Set username  
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Retrieve passkey 
	 * @return String passkey
	 */
	public String getPasskey() {
		return passkey;
	}

	/**
	 * Set pass key
	 * @param passkey
	 */
	public void setPasskey(String passkey) {
		this.passkey = passkey;
	}

	/**
	 * Retrieve String user type
	 * @return string user type
	 */
	public String getUserType() {
		return userType;
	}
	
	/**
	 * Set user type
	 * @param userType
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}
		
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Users other = (Users) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (passkey == null) {
			if (other.passkey != null)
				return false;
		} else if (!passkey.equals(other.passkey))
			return false;
		if (userType == null) {
			if (other.userType != null)
				return false;
		} else if (!userType.equals(other.userType))
			return false;
		return true;
	}	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		result = prime * result + ((passkey == null) ? 0 : passkey.hashCode());
		result = prime * result + ((userType == null) ? 0 : userType.hashCode());
		return result;
	}
	
}
