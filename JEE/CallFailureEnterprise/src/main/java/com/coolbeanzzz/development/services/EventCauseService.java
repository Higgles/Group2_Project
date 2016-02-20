package com.coolbeanzzz.development.services;

import java.util.Collection;

import javax.ejb.Local;
import com.coolbeanzzz.development.entities.EventCause;

@Local
public interface EventCauseService {
	public Collection<EventCause> getCatalog() ;
}
