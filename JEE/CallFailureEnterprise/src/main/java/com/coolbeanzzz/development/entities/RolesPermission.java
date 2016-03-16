package com.coolbeanzzz.development.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class RolesPermission implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2070357513686679164L;

	@Id
	@GeneratedValue
	private Integer id;

	@Column
	private String permission;

	@Column
	private String roleName;

	/**
	 * Get ID
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Set role ID
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Get role permission
	 * @return permission
	 */
	public String getPermission() {
		return permission;
	}

	/**
	 * Set role permission
	 * @param permission
	 */
	public void setPermission(String permission) {
		this.permission = permission;
	}

	/**
	 * Get name of role
	 * @return roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * Set name of role
	 * @param roleName
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}
