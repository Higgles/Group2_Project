/**
 * @author Coolbeanzzz
 */
package com.coolbeanzzz.development.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public class UsersList implements Serializable{
	
	private Collection<Users> usersCollection;
	
	/**
	 * Retrieves all rows from UsersTable
	 * @return Collection of UsersTable rows
	 */
	public Collection<Users> getUsersCollection() {
        return usersCollection;
    }
	
	/**
	 * Add a row to UsersTable
	 * @param Users object 
	 */
    public void setUsersCollection(Collection<Users> usersCollection) {
        this.usersCollection = usersCollection;
    }
  

}