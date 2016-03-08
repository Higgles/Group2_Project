/**
 * @author Coolbeanzzz
 */
package com.coolbeanzzz.development.entities;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity @Table(name="`Base Data`")

@XmlRootElement
public class BaseData implements Serializable, FailureTable{

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
	
	/**
	 * Retrieves the id of the base data row
	 * @return row id primary key
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the primary key id to the input value
	 * @param id The chosen id to set the primary key of the row to
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Retrieves the date time entry of the row
	 * @return a string containing the formatted date time entry
	 */
	public String getDateTime() {
		return dateTime;
	}
	
	/**
	 * Sets the date time entry of the row to the input value
	 * @param dateTime a string detailing the date time value to set the row to
	 */
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	/**
	 * Retrieves the cell id value from the base data row 
	 * @return a string containing the cellId value from the base data row
	 */
	public String getCellId() {
		return cellId;
	}
	
	/**
	 * Sets the cellId value of the row
	 * @param cellId the chosen new cellId
	 */
	public void setCellId(String cellId) {
		this.cellId = cellId;
	}

	/**
	 * Retrieves the error record call duration
	 * @return a String detailing the duration of the call
	 */
	public String getDuration() {
		return duration;
	}
	
	/**
	 * Sets the record call duration
	 * @param duration new call duration
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}

	/**
	 * Retrieves the record's Ne version
	 * @return a string detailing the call failire's ne version
	 */
	public String getNeVersion() {
		return neVersion;
	}

	/**
	 * Sets the record's ne version to a new value
	 * @param neVersion the new value to set the ne version to
	 */
	public void setNeVersion(String neVersion) {
		this.neVersion = neVersion;
	}

	/**
	 * Retrieves the record's IMSI value 
	 * @return a string containing the record's IMSI
	 */
	public String getImsi() {
		return imsi;
	}

	/**
	 * Set's the record's IMSI value
	 * @param imsi New IMSI to set the record to 
	 */
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	/**
	 * Gets the record's Hier3_Id value
	 * @return a string detailing the Hier3_Id value
	 */
	public String getHier3_Id() {
		return hier3_Id;
	}

	/**
	 * Sets the record's Hier3_Id to a new value
	 * @param hier3_Id new Hier3_id value
	 */
	public void setHier3_Id(String hier3_Id) {
		this.hier3_Id = hier3_Id;
	}

	/**
	 * Gets the record's Hier32_Id value
	 * @return a string detailing the Hier32_Id value
	 */
	public String getHier32_Id() {
		return hier32_Id;
	}
	
	/**
	 * Sets the record's Hier32_Id to a new value
	 * @param hier32_Id new Hier32_id value
	 */
	public void setHier32_Id(String hier32_Id) {
		this.hier32_Id = hier32_Id;
	}

	/**
	 * Gets the record's Hier321_Id value
	 * @return a string detailing the Hier321_Id value
	 */
	public String getHier321_Id() {
		return hier321_Id;
	}

	/**
	 * Sets the record's Hier321_Id to a new value
	 * @param hier321_Id new Hier321_id value
	 */
	public void setHier321_Id(String hier321_Id) {
		this.hier321_Id = hier321_Id;
	}

	/**
	 * Gets the record's relating EventCause record
	 * @return an EventCause record, cannot be null
	 */
	public EventCause getEventCause() {
		return eventCause;
	}

	/**
	 * Sets the record's relating EventCause record
	 * @param eventCause new EventCause record
	 */
	public void setEventCause(EventCause eventCause) {
		this.eventCause = eventCause;
	}

	/**
	 * Gets the record's relating MccMnc record
	 * @return an MccMnc record, cannot be null
	 */
	public MccMnc getMccmnc() {
		return mccmnc;
	}

	/**
	 * Sets the record's relating MccMnc record
	 * @param mccmnc new MccMnc record
	 */
	public void setMccmnc(MccMnc mccmnc) {
		this.mccmnc = mccmnc;
	}

	/**
	 * Gets the record's relating UETable record
	 * @return a UETable record, cannot be null
	 */
	public UETable getUeTable() {
		return ueTable;
	}
	
	/**
	 * Sets the record's relating UETable record
	 * @param ueTable new UeTable record
	 */
	public void setUeTable(UETable ueTable) {
		this.ueTable = ueTable;
	}

	/**
	 * Gets the record's relating FailureClass record
	 * @return a FailureClass record, cannot be null
	 */
	public FailureClass getFailureClass() {
		return failureClass;
	}

	/**
	 * Sets the record's relating FailureClass record
	 * @param failureClass new FailureClass record
	 */
	public void setFailureClass(FailureClass failureClass) {
		this.failureClass = failureClass;
	}
}