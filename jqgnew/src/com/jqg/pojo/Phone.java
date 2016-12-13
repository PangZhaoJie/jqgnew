package com.jqg.pojo;

import java.io.Serializable;

public class Phone implements Serializable {
	private Integer phoneId;
	private String phoneName;
	private String phonePassword;
	private String phoneCategory;
	private int isOpen;
	private String phoneuserId;

	public Phone() {
	}

	public Phone(Integer phoneId) {
		this.phoneId = phoneId;
	}

	public Phone(Integer phoneId, String phoneName, String phonePassword,
			String phoneCategory, int isOpen) {
		this.phoneId = phoneId;
		this.phoneName = phoneName;
		this.phonePassword = phonePassword;
		this.phoneCategory = phoneCategory;
		this.isOpen = isOpen;
	}

	public String getPhoneuserId() {
		return phoneuserId;
	}

	public void setPhoneuserId(String phoneuserId) {
		this.phoneuserId = phoneuserId;
	}

	public Integer getPhoneId() {
		return this.phoneId;
	}

	public void setPhoneId(Integer phoneId) {
		this.phoneId = phoneId;
	}

	public String getPhoneName() {
		return this.phoneName;
	}

	public void setPhoneName(String phoneName) {
		this.phoneName = phoneName;
	}

	public String getPhonePassword() {
		return this.phonePassword;
	}

	public void setPhonePassword(String phonePassword) {
		this.phonePassword = phonePassword;
	}

	public String getPhoneCategory() {
		return this.phoneCategory;
	}

	public void setPhoneCategory(String phoneCategory) {
		this.phoneCategory = phoneCategory;
	}

	public int getIsOpen() {
		return this.isOpen;
	}

	public void setIsOpen(int isOpen) {
		this.isOpen = isOpen;
	}
}
