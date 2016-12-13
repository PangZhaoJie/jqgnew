package com.jqg.struts;

import com.jqg.pojo.Onlinemodel;
import com.jqg.service.OnlinemodelService;
import com.jqg.service.impl.OnlinemodelServiceImpl;

public class OnlinemodelAction extends BaseAction {
	private Integer onlineId;
	private String payCompany;
	private Integer enable;
	private Double payPoundage;
	private String accountNumber;
	private String accountPassword;
	private String other;
	private String payCompany1;
	private Integer enable1;
	private Double payPoundage1;
	private String accountNumber1;
	private String accountPassword1;
	private String other1;
	private Onlinemodel onlinemodel1;
	private Onlinemodel onlinemodel2;
	private Onlinemodel onlinemodel3;
	private Onlinemodel onlinemodel4;
	private Onlinemodel onlinemodel6;
	private String mark;
	private String bussIdB2B;
	private OnlinemodelService onlinemodelService = new OnlinemodelServiceImpl();

	/**
	 * 线上支付接口管理
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getOnlineModel() throws Exception {
		this.onlinemodel1 = this.onlinemodelService
				.findOnlinemodelByonlineId(2);
		this.onlinemodel2 = this.onlinemodelService
				.findOnlinemodelByonlineId(1);
		this.onlinemodel3 = this.onlinemodelService
				.findOnlinemodelByonlineId(3);
		this.onlinemodel4 = this.onlinemodelService
				.findOnlinemodelByonlineId(4);
		this.onlinemodel6 = this.onlinemodelService
				.findOnlinemodelByonlineId(6);
		return "success";
	}

	public String saveOnlineModel1() throws Exception {
		Onlinemodel onlinemodel = this.onlinemodelService
				.findOnlinemodelByonlineId(this.onlineId);
		onlinemodel.setEnable(this.enable);
		onlinemodel.setPayPoundage(this.payPoundage);
		onlinemodel.setAccountNumber(this.accountNumber);
		onlinemodel.setAccountPassword(this.accountPassword);
		this.onlinemodelService.updateOnlinemodel(onlinemodel);
		getOnlineModel();
		return "success";
	}

	public String saveOnlineModel2() throws Exception {
		Onlinemodel online = this.onlinemodelService
				.findOnlinemodelByonlineId(1);
		online.setEnable(this.enable1);
		online.setPayPoundage(this.payPoundage1);
		online.setAccountNumber(this.accountNumber1);
		online.setAccountPassword(this.accountPassword1);
		online.setOther(this.other1);
		this.onlinemodelService.updateOnlinemodel(online);
		getOnlineModel();
		this.mark = "2";
		return "success";
	}
	public String saveOnlineModel3() throws Exception {
		Onlinemodel online = this.onlinemodelService.findOnlinemodelByonlineId(6);
		online.setEnable(this.enable);
		online.setPayPoundage(this.payPoundage);
		online.setAccountNumber(this.accountNumber);
		online.setAccountPassword(this.accountPassword);
		online.setOther(this.other1);
		online.setBussIdB2B(this.bussIdB2B);
		this.onlinemodelService.updateOnlinemodel(online);
		getOnlineModel();
		this.mark = "4";
		return "success";
	}

	public Integer getOnlineId() {
		return this.onlineId;
	}

	public void setOnlineId(Integer onlineId) {
		this.onlineId = onlineId;
	}

	public String getPayCompany() {
		return this.payCompany;
	}

	public void setPayCompany(String payCompany) {
		this.payCompany = payCompany;
	}

	public Integer getEnable() {
		return this.enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	public Double getPayPoundage() {
		return this.payPoundage;
	}

	public void setPayPoundage(Double payPoundage) {
		this.payPoundage = payPoundage;
	}

	public String getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountPassword() {
		return this.accountPassword;
	}

	public void setAccountPassword(String accountPassword) {
		this.accountPassword = accountPassword;
	}

	public String getOther() {
		return this.other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public Onlinemodel getOnlinemodel1() {
		return this.onlinemodel1;
	}

	public void setOnlinemodel1(Onlinemodel onlinemodel1) {
		this.onlinemodel1 = onlinemodel1;
	}

	public Onlinemodel getOnlinemodel2() {
		return this.onlinemodel2;
	}

	public void setOnlinemodel2(Onlinemodel onlinemodel2) {
		this.onlinemodel2 = onlinemodel2;
	}

	public String getPayCompany1() {
		return this.payCompany1;
	}

	public void setPayCompany1(String payCompany1) {
		this.payCompany1 = payCompany1;
	}

	public Integer getEnable1() {
		return this.enable1;
	}

	public void setEnable1(Integer enable1) {
		this.enable1 = enable1;
	}

	public Double getPayPoundage1() {
		return this.payPoundage1;
	}

	public void setPayPoundage1(Double payPoundage1) {
		this.payPoundage1 = payPoundage1;
	}

	public String getAccountNumber1() {
		return this.accountNumber1;
	}

	public void setAccountNumber1(String accountNumber1) {
		this.accountNumber1 = accountNumber1;
	}

	public String getAccountPassword1() {
		return this.accountPassword1;
	}

	public void setAccountPassword1(String accountPassword1) {
		this.accountPassword1 = accountPassword1;
	}

	public String getOther1() {
		return this.other1;
	}

	public void setOther1(String other1) {
		this.other1 = other1;
	}

	public String getMark() {
		return this.mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public Onlinemodel getOnlinemodel3() {
		return onlinemodel3;
	}

	public void setOnlinemodel3(Onlinemodel onlinemodel3) {
		this.onlinemodel3 = onlinemodel3;
	}

	public Onlinemodel getOnlinemodel4() {
		return onlinemodel4;
	}

	public void setOnlinemodel4(Onlinemodel onlinemodel4) {
		this.onlinemodel4 = onlinemodel4;
	}

}