/**
 * @author Coolbeanzzz
 */
package com.coolbeanzzz.development.entities;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity @Table(name="`Erroneous Data`")

@XmlRootElement
public class ErroneousData implements Serializable, FailureTable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="`Date/Time`") private String dateTime;
	@Column(name="`Event Id`") private int eventId;
	@Column(name="`Failure Class`") private String failureClass;
	@Column(name="`UE Type`") private int ueType;
	@Column(name="Market") private int market;
	@Column(name="Operator") private int operator;
	@Column(name="`Cell Id`") private String cellId;
	@Column(name="Duration") private String duration;
	@Column(name="`Cause Code`") private String causeCode;
	@Column(name="`NE Version`") private String neVersion;
	@Column(name="IMSI") private String imsi;
	@Column(name="HIER3_ID") private String hier3_Id;
	@Column(name="HIER32_ID") private String hier32_Id;
	@Column(name="HIER321_ID") private String hier321_Id;
	
	public ErroneousData() {}

	public ErroneousData(int id, String dateTime, int eventId,
			String failureClass, int ueType, int market, int operator,
			String cellId, String duration, String causeCode, String neVersion,
			String imsi, String hier3_Id, String hier32_Id, String hier321_Id) {
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
		this.hier321_Id = hier321_Id;
	}
	
	/**
	 * Retrieves the id of the row
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
	 * Gets the event id relating to the record
	 * @return the call failure event id
	 */
	public int getEventId() {
		return eventId;
	}

	/**
	 * Sets the record event id to a new value
	 * @param eventId new event id
	 */
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	/**
	 * Gets the record Failure class value
	 * @return a failureClass integer
	 */
	public String getFailureClass() {
		return failureClass;
	}

	/**
	 * Sets the record Failure class value to a new value
	 * @param failureClass new failure class value
	 */
	public void setFailureClass(String failureClass) {
		this.failureClass = failureClass;
	}

	/**
	 * Gets the record's UeType value
	 * @return ueType integer value
	 */
	public int getUeType() {
		return ueType;
	}

	/**
	 * Sets the ueType to a new value
	 * @param ueType new ueType value
	 */
	public void setUeType(int ueType) {
		this.ueType = ueType;
	}

	/**
	 * Gets the Market MCC value of the record
	 * @return an int market value of the record
	 */
	public int getMarket() {
		return market;
	}

	/**
	 * Sets the Market value to a new value 
	 * @param market
	 */
	public void setMarket(int market) {
		this.market = market;
	}

	/**
	 * Gets the operator value of the record
	 * @return an int operator value of the record
	 */
	public int getOperator() {
		return operator;
	}

	/**
	 * Sets the operator value of the record
	 * @param operator a new operator value
	 */
	public void setOperator(int operator) {
		this.operator = operator;
	}

	/**
	 * Gets the record's cause code value
	 * @return a string cause code value of the record
	 */
	public String getCauseCode() {
		return causeCode;
	}

	/**
	 * Sets the record's cause code to a new value
	 * @param causeCode new cause code value
	 */
	public void setCauseCode(String causeCode) {
		this.causeCode = causeCode;
	}
}