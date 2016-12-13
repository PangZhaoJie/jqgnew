package com.jqg.pojo;

import java.io.Serializable;

public class Systemconf implements Serializable {
	private Integer sysId;
	private String parname;
	private String parvalue;
	private String pardesc;
	private int datatype;

	public int getDatatype() {
		return datatype;
	}

	public void setDatatype(int datatype) {
		this.datatype = datatype;
	}

	public Systemconf() {
		this.datatype = 1;
	}

	public Systemconf(String parname,String parvalue,String pardesc){
		this.parname=parname;
		this.parvalue=parvalue;
		this.pardesc = pardesc;
		this.datatype = 1;
	}
	
	
	public Integer getSysId() {
		return sysId;
	}

	public void setSysId(Integer sysId) {
		this.sysId = sysId;
	}

	public String getParname() {
		return parname;
	}

	public void setParname(String parname) {
		this.parname = parname;
	}

	public String getParvalue() {
		return parvalue;
	}

	public void setParvalue(String parvalue) {
		this.parvalue = parvalue;
	}

	public String getPardesc() {
		return pardesc;
	}

	public void setPardesc(String pardesc) {
		this.pardesc = pardesc;
	}

}
