package com.jqg.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

public class UserLog implements Serializable {
	private Integer userLogId;
	private Uservip uservip;
	private Timestamp loginTime;
	private String loginIp;
	private String detail;

	public Integer getUserLogId() {
		return userLogId;
	}

	public void setUserLogId(Integer userLogId) {
		this.userLogId = userLogId;
	}

	public Uservip getUservip() {
		return uservip;
	}

	public void setUservip(Uservip uservip) {
		this.uservip = uservip;
	}

	public Timestamp getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Timestamp loginTime) {
		this.loginTime = loginTime;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

}
