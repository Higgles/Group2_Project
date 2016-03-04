package com.coolbeanzzz.development.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement // this is required to enable JSON serialization
public class UsersList implements Serializable{

	public Collection<Users> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<Users> usersCollection) {
        this.usersCollection = usersCollection;
    }

    private Collection<Users> usersCollection;

}