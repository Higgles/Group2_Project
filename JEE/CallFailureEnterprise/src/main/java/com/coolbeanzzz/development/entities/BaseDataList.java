package com.coolbeanzzz.development.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BaseDataList implements Serializable {
	private Collection<BaseDataResult> baseDataCollection;
	
	public Collection<BaseDataResult> getBaseDataCollection() {
        return baseDataCollection;
    }

    public void setBaseDataCollection(Collection<BaseData> baseDataCollection) {
    	Iterator<BaseData> basedata=baseDataCollection.iterator();
    			
        this.baseDataCollection = new ArrayList<BaseDataResult>();
        while(basedata.hasNext()){
        	BaseData c=basedata.next();
	        this.baseDataCollection.add(new BaseDataResult(
	        	c.getId(),
	        	c.getDateTime(),
	        	c.getEventCause().getEventId(),
	        	c.getFailureClass().getFailureClass(),
	        	c.getUeTable().getTac(),
	        	c.getMccmnc().getMcc(),
	        	c.getMccmnc().getMnc(),
	        	c.getCellId(),
	        	c.getDuration(),
	        	c.getEventCause().getCauseCode(),
	        	c.getNeVersion(),
	        	c.getImsi(),
	        	c.getHier3_Id(),
	        	c.getHier32_Id(),
	        	c.getHier321_Id()
	        		));
        }
    }
}
