package com.coolbeanzzz.development.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Collection;

@XmlRootElement // this is required to enable JSON serialization
public class FailureClassList implements Serializable {

    public Collection<FailureClass> getFailureClassCollection() {
        return failureClassCollection;
    }

    public void setFailureClassCollection(Collection<FailureClass> failureClassCollection) {
        this.failureClassCollection = failureClassCollection;
    }


    private Collection<FailureClass> failureClassCollection;

}