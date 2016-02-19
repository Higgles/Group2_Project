package com.coolbeanzzz.development.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;


@Entity @Table(name="Failure Class")

@XmlRootElement
public class FailureClass implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id @Column(name="Class") private int failureClass;
	@Column(name="Description") private String description;
	
	@OneToMany(mappedBy="failureClass", cascade={CascadeType.ALL})
	@JsonIgnore
	private Set<BaseData> baseData = new HashSet<BaseData>();

	public FailureClass(){
		
	}
	
	public FailureClass(int failureClass, String description){
		this.failureClass = failureClass;
		this.description = description;
	}
	
	public int getFailureClass() {
		return failureClass;
	}

	public void setFailureClass(int failureClass) {
		this.failureClass = failureClass;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@JsonIgnore
	public Set<BaseData> getBaseData() {
		return baseData;
	}

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
