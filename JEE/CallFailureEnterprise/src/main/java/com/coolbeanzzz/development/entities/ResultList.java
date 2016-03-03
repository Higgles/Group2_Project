package com.coolbeanzzz.development.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ResultList implements Serializable {
	protected Collection<FailureTable> dataCollection;
	
	public Collection<FailureTable> getDataCollection() {
        return dataCollection;
    }

    public void setDataCollection(Collection<FailureTable>dataCollection) {
        this.dataCollection = dataCollection;
    }
}
