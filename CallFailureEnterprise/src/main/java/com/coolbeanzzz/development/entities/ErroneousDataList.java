package com.coolbeanzzz.development.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErroneousDataList implements Serializable {
	private Collection<ErroneousData> erroneousDataCollection;
	
	public Collection<ErroneousData> getErroneousDataCollection() {
        return erroneousDataCollection;
    }

    public void setErroneousDataCollection(Collection<ErroneousData> erroneousDataCollection) {
        this.erroneousDataCollection = erroneousDataCollection;
    }
}
