package com.coolbeanzzz.development.entities;

import java.io.Serializable;

import javax.persistence.*;

public class EventCausePk implements Serializable{
	
	private static final long serialVersionUID = 1L;
	protected int eventId;
	protected int causeCode;
	
	public EventCausePk() {}

	public EventCausePk(int eventId, int causeCode) {
		super();
		this.eventId = eventId;
		this.causeCode = causeCode;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventCausePk other = (EventCausePk) obj;
		if (eventId != other.eventId)
			return false;
		if(causeCode != other.causeCode)
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + eventId;
		result = prime * result + causeCode;
		
		return result;
	}
}
