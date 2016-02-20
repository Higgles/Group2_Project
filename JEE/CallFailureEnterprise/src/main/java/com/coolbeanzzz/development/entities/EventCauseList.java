package com.coolbeanzzz.development.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EventCauseList implements Serializable {
	private Collection<EventCause> eventCauseCollection;
	
	public Collection<EventCause> getEventCauseCollection() {
        return eventCauseCollection;
    }

    public void setEventCauseCollection(Collection<EventCause> eventCauseCollection) {
        this.eventCauseCollection = eventCauseCollection;
    }
}
