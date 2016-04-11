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

@Entity @Table(name="`MCC-MNC`")

@XmlRootElement
@IdClass(MccMncPrimaryKey.class)
public class MccMnc implements Serializable, FailureTable{

	private static final long serialVersionUID = 1L;
	@Id @Column(name="MCC") private int mcc;
	@Id @Column(name="MNC") private int mnc;
	@Column(name="Country") private String country;
	@Column(name="Operator") private String operator;
	
	//Adding relationships
	@OneToMany(mappedBy="mccmnc")//, cascade={CascadeType.ALL})
	@JsonIgnore
	private Set<BaseData> baseData = new HashSet<BaseData>();
	//@XmlTransient
	
	public MccMnc(){}
	
	public MccMnc(int mcc, int mnc, String country, String operator){
		this.mcc = mcc;
		this.mnc = mnc;
		this.country = country;
		this.operator = operator;
	}
	
	/**
	 * Gets the records Mcc value
	 * @return mcc
	 */
	public int getMcc() {
		return mcc;
	}
	
	/**
	 * Sets the records mcc value to a new value
	 * @param mcc new mcc value
	 */
	public void setMcc(int mcc) {
		this.mcc = mcc;
	}
	
	/**
	 * Gets the records mnc value
	 * @return mnc
	 */
	public int getMnc() {
		return mnc;
	}
	
	/**
	 * Sets the records mnc value to a new value
	 * @param mnc new mnc value
	 */
	public void setMnc(int mnc) {
		this.mnc = mnc;
	}
	
	/**
	 * Gets the records country value
	 * @return country
	 */
	public String getCountry() {
		return country;
	}
	
	/**
	 * Sets the records country value to a new value
	 * @param country new country value
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	
	/**
	 * Gets the records operator value
	 * @return operator
	 */
	public String getOperator() {
		return operator;
	}
	
	/**
	 * Sets the records operator value to a new value
	 * @param operator new operator value
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	/**
	 * Gets the records relating base data record
	 * @return relating base data record
	 */
	@JsonIgnore
	public Set<BaseData> getBaseData() {
		return baseData;
	}
	
	/**
	 * Sets the records relating base data record to a new base data record
	 * @param mbaseData
	 */
	public void setBaseData(Set<BaseData> baseData) {
		this.baseData = baseData;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + mcc;
		result = prime * result + mnc;
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((operator == null) ? 0 : operator.hashCode());
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
		MccMnc other = (MccMnc) obj;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (operator == null) {
			if (other.operator != null)
				return false;
		} else if (!operator.equals(other.operator))
			return false;
		if (mcc != other.mcc)
			return false;
		if(mnc != other.mnc)
			return false;
		return true;
	}
}
