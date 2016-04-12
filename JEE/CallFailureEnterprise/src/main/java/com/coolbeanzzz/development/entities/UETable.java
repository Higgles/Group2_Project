/**
 * @author Coolbeanzzz
 */
package com.coolbeanzzz.development.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;


@Entity @Table(name="`UE Table`")

@XmlRootElement
public class UETable implements Serializable, FailureTable{
	
	private static final long serialVersionUID = 1L;
	@Id @Column(name="TAC") private int tac;
	@Column(name="`Marketing Name`") private String marketingName;
	@Column(name="Manufacturer") private String manufacturer;
	@Column(name="`Access Capability`") private String accessCapability;
	@Column(name="Model") private String model;
	@Column(name="`Vendor Name`") private String vendorName;
	@Column(name="`UE Type`") private String ueType;
	@Column(name="OS") private String os;
	@Column(name="`Input Mode`") private String inputMode;
	
	@OneToMany(mappedBy="ueTable")//, cascade={CascadeType.ALL})
	@JsonIgnore
	private Set<BaseData> baseData = new HashSet<BaseData>();
	
	public UETable() {}

	public UETable(int tac, String marketingName, String manufacturer,
			String accessCapability, String model, String vendorName,
			String ueType, String os, String inputMode) {
		this.tac = tac;
		this.marketingName = marketingName;
		this.manufacturer = manufacturer;
		this.accessCapability = accessCapability;
		this.model = model;
		this.vendorName = vendorName;
		this.ueType = ueType;
		this.os = os;
		this.inputMode = inputMode;
	}

	/**
	 * Gets the tac value
	 * @return tac
	 */
	public int getTac() {
		return tac;
	}

	/**
	 * Sets the tac value to a new value
	 * @param tac new tac value
	 */
	public void setTac(int tac) {
		this.tac = tac;
	}

	/**
	 * Gets the Marketing name value
	 * @return marketingName
	 */
	public String getMarketingName() {
		return marketingName;
	}

	/**
	 * Sets the Marketing name value to a new value
	 * @param marketingName new marketingName value
	 */
	public void setMarketingName(String marketingName) {
		this.marketingName = marketingName;
	}

	/**
	 * Gets the Manufacturer value
	 * @return manufacturer
	 */
	public String getManufacturer() {
		return manufacturer;
	}

	/**
	 * Sets the manufacturer value to a new value
	 * @param manufacturer new manufacturer value
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	/**
	 * Gets the access capability value
	 * @return accessCapability
	 */
	public String getAccessCapability() {
		return accessCapability;
	}

	/**
	 * Sets the access capability value to a new value
	 * @param accessCapability new access capability value
	 */
	public void setAccessCapability(String accessCapability) {
		this.accessCapability = accessCapability;
	}

	/**
	 * Gets the model value
	 * @return model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * Sets the model value to a new value
	 * @param model new model value
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * Gets the vendorName value
	 * @return vendorName
	 */
	public String getVendorName() {
		return vendorName;
	}

	/**
	 * Sets the vendor name value to a new value
	 * @param vendorName new vendor name
	 */
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	/**
	 * Gets the ue type value
	 * @return ueType
	 */
	public String getUeType() {
		return ueType;
	}

	/**
	 * Sets the ue type value to a new value
	 * @param ueType new ue type value
	 */
	public void setUeType(String ueType) {
		this.ueType = ueType;
	}

	/**
	 * Gets the OS value
	 * @return os
	 */
	public String getOs() {
		return os;
	}

	/**
	 * Sets the OS value to a new value
	 * @param os new os value
	 */
	public void setOs(String os) {
		this.os = os;
	}

	/**
	 * Gets the input mode value
	 * @return inputMode
	 */
	public String getInputMode() {
		return inputMode;
	}

	/**
	 * Sets the input mode value to a new value
	 * @param inputMode new input mode
	 */
	public void setInputMode(String inputMode) {
		this.inputMode = inputMode;
	}

	/**
	 * Gets the relating base data record to this record
	 * @return baseData
	 */
	@JsonIgnore
	public Set<BaseData> getBaseData() {
		return baseData;
	}

	/**
	 * Sets the relating base data record to a new record
	 * @param baseData new base data record
	 */
	public void setBaseData(Set<BaseData> baseData) {
		this.baseData = baseData;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + tac;
		result = prime * result + ((marketingName == null) ? 0 : marketingName.hashCode());
		result = prime * result + ((manufacturer == null) ? 0 : manufacturer.hashCode());
		result = prime * result + ((accessCapability == null) ? 0 : accessCapability.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((vendorName == null) ? 0 : vendorName.hashCode());
		result = prime * result + ((ueType == null) ? 0 : ueType.hashCode());
		result = prime * result + ((os == null) ? 0 : os.hashCode());
		result = prime * result + ((inputMode == null) ? 0 : inputMode.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UETable other = (UETable) obj;
		if (tac != other.getTac())
			return false;
		if (marketingName == null) {
			if (other.marketingName != null)
				return false;
		} else if (!marketingName.equals(other.marketingName))
			return false;
		if (manufacturer == null) {
			if (other.manufacturer != null)
				return false;
		} else if (!manufacturer.equals(other.manufacturer))
			return false;
		if (accessCapability == null) {
			if (other.accessCapability != null)
				return false;
		} else if (!accessCapability.equals(other.accessCapability))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (vendorName == null) {
			if (other.vendorName != null)
				return false;
		} else if (!vendorName.equals(other.vendorName))
			return false;
		if (ueType == null) {
			if (other.ueType != null)
				return false;
		} else if (!ueType.equals(other.ueType))
			return false;
		if (os == null) {
			if (other.os != null)
				return false;
		} else if (!os.equals(other.os))
			return false;
		if (inputMode == null) {
			if (other.inputMode != null)
				return false;
		} else if (!inputMode.equals(other.inputMode))
			return false;
		return true;
	}	
}
