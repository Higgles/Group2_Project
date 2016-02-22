package com.coolbeanzzz.development.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity @Table(name="`MCC-MNC`")

@XmlRootElement
@IdClass(MccMncPk.class)
public class MccMnc implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id @Column(name="MCC") private int mcc;
	@Id @Column(name="MNC") private int mnc;
	@Column(name="Country") private String country;
	@Column(name="Operator") private String operator;
	
	//Adding relationships
	@OneToMany(mappedBy="mccmnc", cascade={CascadeType.ALL})
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
	
	public int getMcc() {
		return mcc;
	}
	
	public void setMcc(int mcc) {
		this.mcc = mcc;
	}
	
	public int getMnc() {
		return mnc;
	}
	
	public void setMnc(int mnc) {
		this.mnc = mnc;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getOperator() {
		return operator;
	}
	
	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	@JsonIgnore
	public Set<BaseData> getBaseData() {
		return baseData;
	}
	
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
