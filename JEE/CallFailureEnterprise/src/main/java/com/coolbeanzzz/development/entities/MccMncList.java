package com.coolbeanzzz.development.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MccMncList implements Serializable {
	private Collection<MccMnc> mccMncCollection;
	
	public Collection<MccMnc> getMccMncCollection() {
        return mccMncCollection;
    }

    public void setMccMncCollection(Collection<MccMnc> collection) {
        this.mccMncCollection = collection;
    }
}
