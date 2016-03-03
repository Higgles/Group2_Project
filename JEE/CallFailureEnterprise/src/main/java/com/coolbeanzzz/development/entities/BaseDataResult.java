package com.coolbeanzzz.development.entities;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BaseDataResult implements Serializable, FailureTable{
private static final long serialVersionUID = 1L;
	
	private int id;
	private String dateTime;
	private int eventId;
	private int failureClass;
	private int ueType;
	private int market;
	private int operator;
	private String cellId;
	private String duration;
	private int causeCode;
	private String neVersion;
	private String imsi;
	private String hier3_Id;
	private String hier32_Id;
	private String hier321_Id;
	
	public BaseDataResult() {}

	public BaseDataResult(int id, String dateTime, int eventId,
			int failureClass, int ueType, int market, int operator,
			String cellId, String duration, int causeCode, String neVersion,
			String imsi, String hier3_Id, String hier32_Id, String hier321_Id) {
		this.id = id;
		this.dateTime = dateTime;
		this.eventId = eventId;
		this.failureClass = failureClass;
		this.ueType = ueType;
		this.market = market;
		this.operator = operator;
		this.cellId = cellId;
		this.duration = duration;
		this.causeCode = causeCode;
		this.neVersion = neVersion;
		this.imsi = imsi;
		this.hier3_Id = hier3_Id;
		this.hier32_Id = hier32_Id;
		this.hier321_Id = hier321_Id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public int getFailureClass() {
		return failureClass;
	}

	public void setFailureClass(int failureClass) {
		this.failureClass = failureClass;
	}

	public int getUeType() {
		return ueType;
	}

	public void setUeType(int ueType) {
		this.ueType = ueType;
	}

	public int getMarket() {
		return market;
	}

	public void setMarket(int market) {
		this.market = market;
	}

	public int getOperator() {
		return operator;
	}

	public void setOperator(int operator) {
		this.operator = operator;
	}

	public String getCellId() {
		return cellId;
	}

	public void setCellId(String cellId) {
		this.cellId = cellId;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public int getCauseCode() {
		return causeCode;
	}

	public void setCauseCode(int causeCode) {
		this.causeCode = causeCode;
	}

	public String getNeVersion() {
		return neVersion;
	}

	public void setNeVersion(String neVersion) {
		this.neVersion = neVersion;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getHier3_Id() {
		return hier3_Id;
	}

	public void setHier3_Id(String hier3_Id) {
		this.hier3_Id = hier3_Id;
	}

	public String getHier32_Id() {
		return hier32_Id;
	}

	public void setHier32_Id(String hier32_Id) {
		this.hier32_Id = hier32_Id;
	}

	public String getHier321_Id() {
		return hier321_Id;
	}

	public void setHier321_Id(String hier321_Id) {
		this.hier321_Id = hier321_Id;
	}
}
