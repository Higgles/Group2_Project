package com.coolbeanzzz.development.entities;

import java.io.Serializable;

public class MccMncPrimaryKey implements Serializable{
	private static final long serialVersionUID = 1L;
	private int mcc;
	private int mnc;
	
	public MccMncPrimaryKey() {}
	
	public MccMncPrimaryKey(int mcc, int mnc) {
		super();
		this.mcc = mcc;
		this.mnc = mnc;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MccMncPrimaryKey other = (MccMncPrimaryKey) obj;
		if (mcc != other.mcc)
			return false;
		if(mnc != other.mnc)
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + mcc;
		result = prime * result + mnc;
		
		return result;
	}
}
