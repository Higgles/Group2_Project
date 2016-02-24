package com.coolbeanzzz.development.entities;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity @Table(name="`Base Data`")

@XmlRootElement
public class BaseData implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private int id;

	@Column(name="`Date/Time`") private String dateTime;
	@Column(name="`Cell Id`") private String cellId;
	@Column(name="Duration") private String duration;
	@Column(name="`NE Version`") private String neVersion;
	@Column(name="IMSI") private String imsi;
	@Column(name="HIER3_ID") private String hier3_Id;
	@Column(name="HIER32_ID") private String hier32_Id;
	@Column(name="HIER321_ID") private String hier321_Id;
	
	@ManyToOne
	@JoinColumns({ 
		@JoinColumn(name="`Event Id`", referencedColumnName = "`Event Id`") ,
		@JoinColumn(name="`Cause Code`", referencedColumnName = "`Cause Code`")
	})
	private EventCause eventCause;
	
	@ManyToOne
	@JoinColumns({ 
		@JoinColumn(name="Market", referencedColumnName = "MCC") ,
		@JoinColumn(name="Operator", referencedColumnName = "MNC")
	})
	private MccMnc mccmnc;
	
	@ManyToOne
	@JoinColumn(name="`UE Type`", referencedColumnName = "TAC")
	private UETable ueTable;
	
	@ManyToOne
	@JoinColumn(name="`Failure Class`", referencedColumnName = "`Failure Class`")
	private FailureClass failureClass;
	
	public BaseData() {}
	
	public BaseData(int id, String dateTime, String cellId, String duration,
			String neVersion, String imsi, String hier3_Id, String hier32_Id,
			String hier321_Id, EventCause eventCause, MccMnc mccmnc,
			UETable ueTable, FailureClass failureClass) {
		this.id = id;
		this.dateTime = dateTime;
		this.cellId = cellId;
		this.duration = duration;
		this.neVersion = neVersion;
		this.imsi = imsi;
		this.hier3_Id = hier3_Id;
		this.hier32_Id = hier32_Id;
		this.hier321_Id = hier321_Id;
		this.eventCause = eventCause;
		this.mccmnc = mccmnc;
		this.ueTable = ueTable;
		this.failureClass = failureClass;
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

	public String getHier321_Id() {
		return hier321_Id;
	}

	public void setHier321_Id(String hier321_Id) {
		this.hier321_Id = hier321_Id;
	}

	public EventCause getEventCause() {
		return eventCause;
	}

	public void setEventCause(EventCause eventCause) {
		this.eventCause = eventCause;
	}

	public MccMnc getMccmnc() {
		return mccmnc;
	}

	public void setMccmnc(MccMnc mccmnc) {
		this.mccmnc = mccmnc;
	}

	public UETable getUeTable() {
		return ueTable;
	}

	public void setUeTable(UETable ueTable) {
		this.ueTable = ueTable;
	}

	public FailureClass getFailureClass() {
		return failureClass;
	}

	public void setFailureClass(FailureClass failureClass) {
		this.failureClass = failureClass;
	}
}