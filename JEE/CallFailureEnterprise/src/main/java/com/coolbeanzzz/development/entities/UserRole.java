package com.coolbeanzzz.development.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class UserRole implements Serializable {
  /**
   * 
   */
  private static final long serialVersionUID = 8432414340180447723L;
 
  @Id
  @GeneratedValue
  private Integer id;
   
  @Column
  private String roleName;

  /**
   * Get user ID
   * @return id
   */
  public Integer getId() {
	  return id;
  }

  /**
   * Set user ID
   * @param id
   */
  public void setId(Integer id) {
	  this.id = id;
  }

  /**
   * Get user role name
   * @return roleName
   */
  public String getRoleName() {
	  return roleName;
  }

  /**
   * Set user role name
   * @param roleName
   */
  public void setRoleName(String roleName) {
	  this.roleName = roleName;
  }
 
}