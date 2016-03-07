/**
 * @author Coolbeanzzz
 */
package com.coolbeanzzz.development.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ResultList implements Serializable {
	
	private static final long serialVersionUID = 1L;
	protected Collection<FailureTable> dataCollection;
	
	/**
	 * Gets the containing data collection
	 * @return data collection
	 */
	public Collection<FailureTable> getDataCollection() {
        return dataCollection;
    }

	/**
	 * Sets the containing data collection
	 * @param dataCollection new data collection
	 */
    public void setDataCollection(Collection<FailureTable>dataCollection) {
        this.dataCollection = dataCollection;
    }
}
