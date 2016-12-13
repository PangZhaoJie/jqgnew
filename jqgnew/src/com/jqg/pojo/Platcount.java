package com.jqg.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

public class Platcount implements Serializable {
	private Integer countid;
	private Double money;
	private Double income;
	private Double expond;
	private Double profit;
	private String remark;
	private Timestamp opertime;

	public Platcount() {
		
	}

	public Platcount(Integer countid) {
		this.countid = countid;
	}

	public Integer getCountid() {
		return countid;
	}

	public void setCountid(Integer countid) {
		this.countid = countid;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public Double getExpond() {
		return expond;
	}

	public void setExpond(Double expond) {
		this.expond = expond;
	}

	public Double getProfit() {
		return profit;
	}

	public void setProfit(Double profit) {
		this.profit = profit;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Timestamp getOpertime() {
		return opertime;
	}

	public void setOpertime(Timestamp opertime) {
		this.opertime = opertime;
	}

}
