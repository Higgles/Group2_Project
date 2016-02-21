package com.coolbeanzzz.development.entities;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Collection;

@XmlRootElement // this is required to enable JSON serialization
public class UETableList implements Serializable {

    public Collection<UETable> getUETableCollection() {
        return ueTableCollection;
    }

    public void setUETableCollection(Collection<UETable> ueTableCollection) {
        this.ueTableCollection = ueTableCollection;
    }


    private Collection<UETable> ueTableCollection;

}