/**
 * Temporary class, Can be removed once Base Data Retrieval is completed
 * @author Coolbeanzzz
 */
package com.coolbeanzzz.development.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BaseDataList extends ResultList{	

	private static final long serialVersionUID = 1L;
	
	@Override
	public void setDataCollection(Collection<FailureTable> dataCollection) {
    	Iterator<FailureTable> basedata=dataCollection.iterator();
    			
        this.dataCollection = new ArrayList<FailureTable>();
        while(basedata.hasNext()){
        	BaseData c=(BaseData) basedata.next();
	        this.dataCollection.add(new BaseDataResult(
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
