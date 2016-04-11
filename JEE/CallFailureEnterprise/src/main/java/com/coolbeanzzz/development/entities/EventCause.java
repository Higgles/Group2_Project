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


@Entity @Table(name="`Event Cause`")

@XmlRootElement
@IdClass(EventCausePrimaryKey.class)
public class EventCause implements Serializable, FailureTable{

	private static final long serialVersionUID = 1L;
	@Id @Column(name="`Cause Code`") private int causeCode;
	@Id @Column(name="`Event Id`") private int eventId;
	@Column(name="Description") private String description;
	
	//Adding relationships
	@OneToMany(mappedBy="eventCause")//, cascade={CascadeType.ALL})
	@JsonIgnore
	private Set<BaseData> baseData = new HashSet<BaseData>();
	//@XmlTransient
	
	public EventCause(){}
	
	public EventCause(int causeCode, int eventId, String description){
		this.causeCode = causeCode;
		this.eventId = eventId;
		this.description = description;
		
	}

	/**
	 * Gets the record's cause code value
	 * @return cause code value
	 */
	public int getCauseCode() {
		return causeCode;
	}

	/**
	 * Sets the ecord's cause code value
	 * @param causeCode new cause code
	 */
	public void setCauseCode(int causeCode) {
		this.causeCode = causeCode;
	}

	/**
	 * Gets the record's Event Id
	 * @return event id
	 */
	public int getEventId() {
		return eventId;
	}

	/**
	 * Sets the records event id
	 * @param eventId new event id
	 */
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	/**
	 * Gets the description relating to the event cause
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the record's description to a new value
	 * @param description new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Gets the relating base data record to this recod
	 * @return relating base data record
	 */
	@JsonIgnore
	public Set<BaseData> getBaseData() {
		return baseData;
	}

	/**
	 * Sets the relating base data record
	 * @param baseData new base data record relation
	 */
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
