/**
 * @author Coolbeanzzz
 */
package com.coolbeanzzz.development.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;


@Entity @Table(name="`Failure Class`")

@XmlRootElement
public class FailureClass implements Serializable, FailureTable{
	
	private static final long serialVersionUID = 1L;
	
	@Id @Column(name="`Failure Class`") private int failureClass;
	@Column(name="Description") private String description;
	
	@OneToMany(mappedBy="failureClass", cascade={CascadeType.ALL})
	@JsonIgnore
	private Set<BaseData> baseData = new HashSet<BaseData>();

	public FailureClass(){}
	
	public FailureClass(int failureClass, String description){
		this.failureClass = failureClass;
		this.description = description;
	}
	
	/**
	 * Gets the failure class key rating of the record
	 * @return failure class
	 */
	public int getFailureClass() {
		return failureClass;
	}
	
	/**
	 * Sets the record's failure class key value
	 * @param failureClass new failure class value
	 */
	public void setFailureClass(int failureClass) {
		this.failureClass = failureClass;
	}

	/**
	 * Gets the records failure class description
	 * @return description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Sets the record's failure class description to a new value
	 * @param description new description value
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the relating base data record to this record
	 * @return base data record
	 */
	@JsonIgnore
	public Set<BaseData> getBaseData() {
		return baseData;
	}

	/**
	 * Sets the elating base data record to a new record
	 * @param baseData
	 */
	public void setBaseData(Set<BaseData> baseData) {
		this.baseData = baseData;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + failureClass;
		result = prime * result + description.hashCode();
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		FailureClass other = (FailureClass) obj;
		if(failureClass != other.getFailureClass())
			return false;
		return true;
	}
}