package com.coolbeanzzz.development.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BaseDataList implements Serializable {
	private Collection<BaseData> baseDataCollection;
	
	public Collection<BaseData> getBaseDataCollection() {
        return baseDataCollection;
    }

    public void setBaseDataCollection(Collection<BaseData> baseDataCollection) {
        this.baseDataCollection = baseDataCollection;
    }
}
