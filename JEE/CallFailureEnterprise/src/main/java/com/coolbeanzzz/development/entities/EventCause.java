package com.coolbeanzzz.development.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;


@Entity @Table(name="event cause")

@XmlRootElement
public class EventCause implements Serializable{
	
//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	@Column(name="id")
//	private int id;

	private static final long serialVersionUID = 1L;
	@Column(name="CauseCode") private int causeCode;
	@Column(name="Event Id") private int eventId;
	@Column(name="Description") private String description;
	
	//Adding relationships
	@OneToMany(mappedBy="eventcause", cascade={CascadeType.ALL})
	@JsonIgnore
	private Set<BaseData> baseData = new HashSet<BaseData>();
	//@XmlTransient
	
	public EventCause(int causeCode, int eventId, String description){
		this.causeCode = causeCode;
		this.eventId = eventId;
		this.description = description;
		
	}

	public int getCauseCode() {
		return causeCode;
	}

	public void setCauseCode(int causeCode) {
		this.causeCode = causeCode;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
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
		result = prime * result + causeCode;
		result = prime * result + eventId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventCause other = (EventCause) obj;
		if (causeCode != other.causeCode)
			return false;
		if(eventId != other.eventId)
			return false;
		return true;
	}
}
