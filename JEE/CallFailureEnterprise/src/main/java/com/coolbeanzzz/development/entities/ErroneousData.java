package com.coolbeanzzz.development.entities;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity @Table(name="`Erroneous Data`")

@XmlRootElement
public class ErroneousData implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="`Date/Time`") private String dateTime;
	@Column(name="`Event Id`") private int eventId;
	@Column(name="`Failure Class`") private int failureClass;
	@Column(name="`UE Type`") private int ueType;
	@Column(name="Market") private int market;
	@Column(name="Operator") private int operator;
	@Column(name="`Cell Id`") private String cellId;
	@Column(name="Duration") private String duration;
	@Column(name="`Cause Code`") private int causeCode;
	@Column(name="`NE Version`") private String neVersion;
	@Column(name="IMSI") private String imsi;
	@Column(name="HIER3_ID") private String hier3_Id;
	@Column(name="HIER32_ID") private String hier32_Id;
	@Column(name="HIER321_ID") private String her321_Id;
	
	public ErroneousData() {}

	public ErroneousData(int id, String dateTime, int eventId,
			int failureClass, int ueType, int market, int operator,
			String cellId, String duration, int causeCode, String neVersion,
			String imsi, String hier3_Id, String hier32_Id, String her321_Id) {
		super();
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
		this.her321_Id = her321_Id;
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

	public String getHer321_Id() {
		return her321_Id;
	}

	public void setHer321_Id(String her321_Id) {
		this.her321_Id = her321_Id;
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

	public int getCauseCode() {
		return causeCode;
	}

	public void setCauseCode(int causeCode) {
		this.causeCode = causeCode;
	}
}