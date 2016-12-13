package com.jqg.struts;

import java.net.InetAddress;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.jianzhou.sdk.BusinessService;
import com.jqg.pojo.Bankparameter;
import com.jqg.pojo.Companybankinfor;
import com.jqg.pojo.Customerphone;
import com.jqg.pojo.Customerqqs;
import com.jqg.pojo.EmailSendLog;
import com.jqg.pojo.Emailmodel;
import com.jqg.pojo.Internallettermodel;
import com.jqg.pojo.Manager;
import com.jqg.pojo.Messagemodel;
import com.jqg.pojo.Oerationlog;
import com.jqg.pojo.Phone;
import com.jqg.pojo.Role;
import com.jqg.pojo.SmsSendLog;
import com.jqg.pojo.Smtp;
import com.jqg.pojo.Uservip;
import com.jqg.service.BankparameterService;
import com.jqg.service.CompanybankinforService;
import com.jqg.service.CustomerphoneService;
import com.jqg.service.CustomerqqsService;
import com.jqg.service.EmailSendLogService;
import com.jqg.service.EmailmodelService;
import com.jqg.service.InternallettermodelService;
import com.jqg.service.ManagerService;
import com.jqg.service.MessagemodelService;
import com.jqg.service.OerationlogService;
import com.jqg.service.PhoneService;
import com.jqg.service.RoleService;
import com.jqg.service.SmsSendLogService;
import com.jqg.service.SmtpService;
import com.jqg.service.UservipService;
import com.jqg.service.impl.BankparameterServiceImpl;
import com.jqg.service.impl.CompanybankinforServiceImpl;
import com.jqg.service.impl.CustomerphoneServiceImpl;
import com.jqg.service.impl.CustomerqqsServiceImpl;
import com.jqg.service.impl.EmailSendLogServiceImpl;
import com.jqg.service.impl.EmailmodelServiceImpl;
import com.jqg.service.impl.InternallettermodelServiceImpl;
import com.jqg.service.impl.ManagerServiceImpl;
import com.jqg.service.impl.MessagemodelServiceImpl;
import com.jqg.service.impl.OerationlogServiceImpl;
import com.jqg.service.impl.PhoneServiceImpl;
import com.jqg.service.impl.RoleServiceImpl;
import com.jqg.service.impl.SmsSendLogServiceImpl;
import com.jqg.service.impl.SmtpServiceImpl;
import com.jqg.service.impl.UservipServiceImpl;
import com.jqg.util.Client;
import com.jqg.util.MD5Util;
import com.opensymphony.xwork2.ActionContext;

public class MessagemodelAction extends BaseAction implements
		ServletRequestAware, ServletResponseAware {

	private String result;
	private String ip;
	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	Manager manager = (Manager) ActionContext.getContext().getSession()
			.get("manager");
	OerationlogService oerationlogService = new OerationlogServiceImpl();
	private PhoneService phoneService = new PhoneServiceImpl();
	private MessagemodelService messageService = new MessagemodelServiceImpl();
	private EmailmodelService emailmodelService = new EmailmodelServiceImpl();
	private InternallettermodelService interService = new InternallettermodelServiceImpl();
	private SmtpService smtpService = new SmtpServiceImpl();
	private CustomerqqsService customerqqsService = new CustomerqqsServiceImpl();
	private CustomerphoneService customerphoneService = new CustomerphoneServiceImpl();
	private ManagerService managerService = new ManagerServiceImpl();
	private CompanybankinforService companybankinforService = new CompanybankinforServiceImpl();
	private SmsSendLogService sslService = new SmsSendLogServiceImpl();
	private EmailSendLogService eslService = new EmailSendLogServiceImpl();
	private List<Companybankinfor> companybankinfors;
	private List<Bankparameter> bankparameters;
	private BankparameterService bankparameterService = new BankparameterServiceImpl();
	private RoleService roleService = new RoleServiceImpl();
	private UservipService userService = new UservipServiceImpl();
	private List<Manager> managers;
	private List<Customerphone> customerphones;
	private List<Customerqqs> customerqqss;
	private String email1;
	private String email4;
	private String email6;
	private String interletter1;
	private String interletter2;
	private String interletter3;
	private String interletter4;
	private String interletter5;
	private String interletter6;
	private String interletter7;
	private Messagemodel messagemodel1;
	private Messagemodel messagemodel2;
	private Messagemodel messagemodel3;
	private Messagemodel messagemodel4;
	private Messagemodel messagemodel5;
	private Messagemodel messagemodel6;
	private Messagemodel messagemodel7;
	private Messagemodel messagemodel8;
	private Messagemodel messagemodel9;
	private Messagemodel messagemodel10;
	private Messagemodel messagemodel11;
	private Messagemodel messagemodel12;
	private Messagemodel messagemodel13;
	private Messagemodel messagemodel14;
	private Messagemodel messagemodel15;
	
	private String mark;
	private Smtp smtp;
	private Phone phone;
	private Phone phone1;
	private Phone phone2;
	private String num1;
	private String num2;
	private int phoneId;
	private String phonename;
	private String phonepassword;
	private String phoneuserId;
	private Integer customerQqsid;
	private String customerQqsname;
	private String customerQqsnumber;
	private Integer display;
	private String open;
	private Integer customerphoneId;
	private String customerphoneName;
	private String customerphoneNumber;
	private Integer display1;
	private String open1;
	private Integer managerId;
	private String managerName;
	private String qq;
	private Integer display2;
	private String open2;
	private Integer companyBankInforId;
	private Integer bankId;
	private String accountName;
	private String accountNum;
	private String accountAddress;
	private String open3;
	private String pathMages;

	private String password;
	private String passwordWord;
	private String realName;
	private String telephone;
	private Integer roleId;
	private List<Role> roles;
	private Integer page = Integer.valueOf(0);
	private Integer pageSize = Integer.valueOf(10);
	private Integer pageCount;
	private Integer total;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordWord() {
		return passwordWord;
	}

	public void setPasswordWord(String passwordWord) {
		this.passwordWord = passwordWord;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public List<SmsSendLog> getSmsSendLogs() {
		return smsSendLogs;
	}

	public void setSmsSendLogs(List<SmsSendLog> smsSendLogs) {
		this.smsSendLogs = smsSendLogs;
	}

	public List<EmailSendLog> getEmailSendLogs() {
		return emailSendLogs;
	}

	public void setEmailSendLogs(List<EmailSendLog> emailSendLogs) {
		this.emailSendLogs = emailSendLogs;
	}

	private List<SmsSendLog> smsSendLogs = new ArrayList();
	private List<EmailSendLog> emailSendLogs = new ArrayList();

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	/**
	 * 邮箱，站内信，信息模板
	 * 
	 * @return
	 * @throws Exception
	 */
	public String search() throws Exception {
		this.email1 = this.emailmodelService.findEmailmodelByemailModelId(1)
				.getEmailModelContent();
		this.email4 = this.emailmodelService.findEmailmodelByemailModelId(4)
				.getEmailModelContent();
		this.email6 = this.emailmodelService.findEmailmodelByemailModelId(6)
				.getEmailModelContent();
		this.interletter1 = this.interService
				.findInternallettermodelByinterModelId(1)
				.getInterModelContent();
		this.interletter2 = this.interService
				.findInternallettermodelByinterModelId(2)
				.getInterModelContent();
		this.interletter3 = this.interService
				.findInternallettermodelByinterModelId(3)
				.getInterModelContent();
		this.interletter4 = this.interService
				.findInternallettermodelByinterModelId(4)
				.getInterModelContent();
		this.interletter5 = this.interService
				.findInternallettermodelByinterModelId(5)
				.getInterModelContent();
		this.interletter6 = this.interService
				.findInternallettermodelByinterModelId(6)
				.getInterModelContent();
		this.interletter7 = this.interService
				.findInternallettermodelByinterModelId(7)
				.getInterModelContent();

		this.messagemodel1 = this.messageService
				.findMessagemodelByintegralPid(1);
		this.messagemodel2 = this.messageService
				.findMessagemodelByintegralPid(2);
		this.messagemodel3 = this.messageService
				.findMessagemodelByintegralPid(3);
		this.messagemodel4 = this.messageService
				.findMessagemodelByintegralPid(4);
		this.messagemodel5 = this.messageService
				.findMessagemodelByintegralPid(5);
		this.messagemodel6 = this.messageService
				.findMessagemodelByintegralPid(6);
		this.messagemodel7 = this.messageService
				.findMessagemodelByintegralPid(7);
		this.messagemodel8 = this.messageService
				.findMessagemodelByintegralPid(8);
		this.messagemodel9 = this.messageService
				.findMessagemodelByintegralPid(9);
		this.messagemodel10 = this.messageService
				.findMessagemodelByintegralPid(10);
		this.messagemodel11 = this.messageService
				.findMessagemodelByintegralPid(11);
		this.messagemodel12 = this.messageService
				.findMessagemodelByintegralPid(12);
		this.messagemodel13 = this.messageService
				.findMessagemodelByintegralPid(13);
		this.messagemodel14 = this.messageService
				.findMessagemodelByintegralPid(14);
		this.messagemodel15 = this.messageService
				.findMessagemodelByintegralPid(15);
		return "success";
	}

	public String save1() throws Exception {
		Emailmodel emails1 = this.emailmodelService
				.findEmailmodelByemailModelId(1);
		Emailmodel emails4 = this.emailmodelService
				.findEmailmodelByemailModelId(4);
		Emailmodel emails6 = this.emailmodelService
				.findEmailmodelByemailModelId(6);
		emails1.setEmailModelContent(this.email1);
		emails4.setEmailModelContent(this.email4);
		emails6.setEmailModelContent(this.email6);
		this.emailmodelService.updateEmailmodel(emails1);
		this.emailmodelService.updateEmailmodel(emails4);
		this.emailmodelService.updateEmailmodel(emails6);

		InetAddress in = InetAddress.getLocalHost();
		Manager manager = (Manager) ActionContext.getContext().getSession()
				.get("manager");
		Oerationlog oerationlog = new Oerationlog();
		oerationlog.setManager(manager);
		oerationlog.setOerationCategory("saveEmailmodel");
		oerationlog.setOerationChangeTime(new Timestamp(new Date().getTime()));
		oerationlog.setOerationRemaks("保存邮件模板");
		oerationlog.setOrationIp(in.getHostAddress().toString());
		this.oerationlogService.createOerationlog(oerationlog);
		search();
		this.mark = "2";
		return "success";
	}

	public String save2() throws Exception {
		Messagemodel messagemodels1 = this.messageService
				.findMessagemodelByintegralPid(1);
		Messagemodel messagemodels2 = this.messageService
				.findMessagemodelByintegralPid(2);
		Messagemodel messagemodels3 = this.messageService
				.findMessagemodelByintegralPid(3);
		Messagemodel messagemodels4 = this.messageService
				.findMessagemodelByintegralPid(4);
		Messagemodel messagemodels5 = this.messageService
				.findMessagemodelByintegralPid(5);
		Messagemodel messagemodels6 = this.messageService
				.findMessagemodelByintegralPid(6);
		Messagemodel messagemodels7 = this.messageService
				.findMessagemodelByintegralPid(7);
		Messagemodel messagemodels8 = this.messageService
				.findMessagemodelByintegralPid(8);
		Messagemodel messagemodels9 = this.messageService
				.findMessagemodelByintegralPid(9);
		Messagemodel messagemodels10 = this.messageService
				.findMessagemodelByintegralPid(10);
		Messagemodel messagemodels11 = this.messageService
				.findMessagemodelByintegralPid(11);
		Messagemodel messagemodels12 = this.messageService
				.findMessagemodelByintegralPid(12);
		Messagemodel messagemodels13 = this.messageService
				.findMessagemodelByintegralPid(13);
		Messagemodel messagemodels14 = this.messageService
				.findMessagemodelByintegralPid(14);
		Messagemodel messagemodels15 = this.messageService
				.findMessagemodelByintegralPid(15);
		
		messagemodels1.setMessModelContent(this.messagemodel1
				.getMessModelContent());
		messagemodels2.setMessModelContent(this.messagemodel2
				.getMessModelContent());
		messagemodels3.setMessModelContent(this.messagemodel3
				.getMessModelContent());
		messagemodels4.setMessModelContent(this.messagemodel4
				.getMessModelContent());
		messagemodels5.setMessModelContent(this.messagemodel5
				.getMessModelContent());
		messagemodels6.setMessModelContent(this.messagemodel6
				.getMessModelContent());
		messagemodels7.setMessModelContent(this.messagemodel7
				.getMessModelContent());
		messagemodels8.setMessModelContent(this.messagemodel8
				.getMessModelContent());
		messagemodels9.setMessModelContent(this.messagemodel9
				.getMessModelContent());
		messagemodels10.setMessModelContent(this.messagemodel10
				.getMessModelContent());
		messagemodels11.setMessModelContent(this.messagemodel11
				.getMessModelContent());
		messagemodels12.setMessModelContent(this.messagemodel12
				.getMessModelContent());
		messagemodels13.setMessModelContent(this.messagemodel13
				.getMessModelContent());
		messagemodels14.setMessModelContent(this.messagemodel14
				.getMessModelContent());
		messagemodels15.setMessModelContent(this.messagemodel15
				.getMessModelContent());
		
//		messagemodels1.setIsOpen(this.messagemodel1.getIsOpen());
//		messagemodels2.setIsOpen(this.messagemodel2.getIsOpen());
//		messagemodels3.setIsOpen(this.messagemodel3.getIsOpen());
//		messagemodels4.setIsOpen(this.messagemodel4.getIsOpen());
//		messagemodels5.setIsOpen(this.messagemodel5.getIsOpen());
//		messagemodels6.setIsOpen(this.messagemodel6.getIsOpen());
//		messagemodels7.setIsOpen(this.messagemodel7.getIsOpen());
//		messagemodels8.setIsOpen(this.messagemodel8.getIsOpen());
//		messagemodels9.setIsOpen(this.messagemodel9.getIsOpen());
//		messagemodels10.setIsOpen(this.messagemodel10.getIsOpen());
//		messagemodels11.setIsOpen(this.messagemodel11.getIsOpen());
//		messagemodels12.setIsOpen(this.messagemodel12.getIsOpen());
//		messagemodels13.setIsOpen(this.messagemodel13.getIsOpen());
//		messagemodels14.setIsOpen(this.messagemodel14.getIsOpen());
//		messagemodels15.setIsOpen(this.messagemodel15.getIsOpen());

		this.messageService.updateMessagemodel(messagemodels1);
		this.messageService.updateMessagemodel(messagemodels2);
		this.messageService.updateMessagemodel(messagemodels3);
		this.messageService.updateMessagemodel(messagemodels4);
		this.messageService.updateMessagemodel(messagemodels5);
		this.messageService.updateMessagemodel(messagemodels6);
		this.messageService.updateMessagemodel(messagemodels7);
		this.messageService.updateMessagemodel(messagemodels8);
		this.messageService.updateMessagemodel(messagemodels9);
		this.messageService.updateMessagemodel(messagemodels10);
		this.messageService.updateMessagemodel(messagemodels11);
		this.messageService.updateMessagemodel(messagemodels12);
		this.messageService.updateMessagemodel(messagemodels13);
		this.messageService.updateMessagemodel(messagemodels14);
		this.messageService.updateMessagemodel(messagemodels15);
		search();
		this.mark = "3";

		InetAddress in = InetAddress.getLocalHost();
		Manager manager = (Manager) ActionContext.getContext().getSession()
				.get("manager");
		Oerationlog oerationlog = new Oerationlog();
		oerationlog.setManager(manager);
		oerationlog.setOerationCategory("saveMessagemodel");
		oerationlog.setOerationChangeTime(new Timestamp(new Date().getTime()));
		oerationlog.setOerationRemaks("保存短信模板");
		oerationlog.setOrationIp(in.getHostAddress().toString());
		this.oerationlogService.createOerationlog(oerationlog);
		return "success";
	}

	public String save3() throws Exception {
		Internallettermodel inter1 = this.interService
				.findInternallettermodelByinterModelId(1);
		Internallettermodel inter2 = this.interService
				.findInternallettermodelByinterModelId(2);
		Internallettermodel inter3 = this.interService
				.findInternallettermodelByinterModelId(3);
		Internallettermodel inter4 = this.interService
				.findInternallettermodelByinterModelId(4);
		Internallettermodel inter5 = this.interService
				.findInternallettermodelByinterModelId(5);
		Internallettermodel inter6 = this.interService
				.findInternallettermodelByinterModelId(6);
		Internallettermodel inter7 = this.interService
				.findInternallettermodelByinterModelId(7);
		inter1.setInterModelContent(this.interletter1);
		inter2.setInterModelContent(this.interletter2);
		inter3.setInterModelContent(this.interletter3);
		inter4.setInterModelContent(this.interletter4);
		inter5.setInterModelContent(this.interletter5);
		inter6.setInterModelContent(this.interletter6);
		inter7.setInterModelContent(this.interletter7);
		this.interService.updateInternallettermodel(inter1);
		this.interService.updateInternallettermodel(inter2);
		this.interService.updateInternallettermodel(inter3);
		this.interService.updateInternallettermodel(inter4);
		this.interService.updateInternallettermodel(inter5);
		this.interService.updateInternallettermodel(inter6);
		this.interService.updateInternallettermodel(inter7);
		search();
		this.mark = "1";

		InetAddress in = InetAddress.getLocalHost();
		Manager manager = (Manager) ActionContext.getContext().getSession()
				.get("manager");
		Oerationlog oerationlog = new Oerationlog();
		oerationlog.setManager(manager);
		oerationlog.setOerationCategory("saveInternallettermodel");
		oerationlog.setOerationChangeTime(new Timestamp(new Date().getTime()));
		oerationlog.setOerationRemaks("保存站内信模板");
		oerationlog.setOrationIp(in.getHostAddress().toString());
		this.oerationlogService.createOerationlog(oerationlog);
		return "success";
	}

	/**
	 * 更新手机参数
	 * 
	 * @return
	 * @throws Exception
	 */
	public String uptPhone() throws Exception {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String ip = request.getHeader("X-Forwarded-For"); 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("Proxy-Client-IP"); 
        } 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("WL-Proxy-Client-IP"); 
        } 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("HTTP_CLIENT_IP"); 
        } 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("HTTP_X_FORWARDED_FOR"); 
        } 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getRemoteAddr(); 
        } 
		
		
		this.smtp = this.smtpService.findSmtpBysmtpid(1);
		Phone phone4 = this.phoneService.findPhoneByphoneId(this.phoneId);
		phone4.setPhoneName(this.phonename);
		phone4.setPhonePassword(this.phonepassword);
		phone4.setIsOpen(1);
		phone4.setPhoneCategory(phone4.getPhoneCategory());
		if (this.phoneId == 2) {
			phone4.setPhoneuserId(this.phoneuserId);
		}
			
		boolean flag = this.phoneService.updatePhone(phone4);
		boolean flag1;
		if (this.phoneId == 1) {
			Phone phone3 = this.phoneService.findPhoneByphoneId(2);
			phone3.setIsOpen(0);

			flag1 = this.phoneService.updatePhone(phone3);
		} else {
			Phone phone3 = this.phoneService.findPhoneByphoneId(1);

			phone3.setIsOpen(0);

			flag1 = this.phoneService.updatePhone(phone3);
		}
		this.mark = "2";
		this.phone = this.phoneService.findPhoneByOpen();
		this.phone1 = this.phoneService.findPhoneByphoneId(1);
		this.phone2 = this.phoneService.findPhoneByphoneId(2);
		BusinessService bs = new BusinessService();
		int value2 = bs.validateUser(this.phone2.getPhoneName(),
				this.phone2.getPhonePassword());
		if (value2 == 1) {
			this.num2 = bs.getUserInfo(this.phone2.getPhoneName(),
					this.phone2.getPhonePassword());
			if (this.num2.lastIndexOf("<remainFee>") > 0
					&& this.num2.lastIndexOf("</remainFee>") > 0) {
				this.num2 = this.num2.substring(
						this.num2.lastIndexOf("<remainFee>") + 1,
						this.num2.lastIndexOf("</remainFee>"));
				this.num2 = this.num2.substring(11);
			}
		} else {
			this.num2 = "用户名或密码错误";
		}

		Client client = new Client(this.phone1.getPhoneName(),
				this.phone1.getPhonePassword());
		this.num1 = client.getBalance();
		Oerationlog oerationlog = new Oerationlog();
		oerationlog.setManager(this.manager);
		oerationlog.setOerationCategory("uptPhone");
		oerationlog.setOerationChangeTime(this.timestamp);
		oerationlog.setOerationRemaks("更新手机参数");
		oerationlog.setOrationIp(ip);
		flag1 = this.oerationlogService.createOerationlog(oerationlog);
		return "success";
	}

	/**
	 * 邮箱信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String searchSmtp() throws Exception {
		this.smtp = this.smtpService.findSmtpBysmtpid(1);
		this.phone = this.phoneService.findPhoneByOpen();
		this.phone1 = this.phoneService.findPhoneByphoneId(1);
		this.phone2 = this.phoneService.findPhoneByphoneId(2);
		BusinessService bs = new BusinessService();

//		int value2 = bs.validateUser(this.phone2.getPhoneName(),
//				this.phone2.getPhonePassword());
//		if (value2 == 1) {
//			this.num2 = bs.getUserInfo(this.phone2.getPhoneName(),
//					this.phone2.getPhonePassword());
//			if (this.num2.indexOf("文件提前结束") != -1) {
//				this.num2 = "0";
//			} else {
//				if (this.num2.lastIndexOf("<remainFee>") > 0
//						&& this.num2.lastIndexOf("</remainFee>") > 0) {
//					this.num2 = this.num2.substring(
//							this.num2.lastIndexOf("<remainFee>") + 1,
//							this.num2.lastIndexOf("</remainFee>"));
//					this.num2 = this.num2.substring(11);
//				}
//			}
//		} else {
//			this.num2 = "用户名或密码错误";
//		}

		Client client = new Client(this.phone1.getPhoneName(),
				this.phone1.getPhonePassword());
		this.num1 = client.getBalance();
		ActionContext.getContext().getSession().put("phoneopen", this.phone);
		return "success";
	}

	public String saveSmtp() throws Exception {
		Smtp smtps = this.smtpService.findSmtpBysmtpid(this.smtp.getSmtpid()
				.intValue());
		smtps.setSmtpemail(this.smtp.getSmtpemail());
		smtps.setSmtppassword(this.smtp.getSmtppassword());
		smtps.setSmtpsever(this.smtp.getSmtpsever());
		this.smtpService.updateSmtp(smtps);
		searchSmtp();

		InetAddress in = InetAddress.getLocalHost();
		Manager manager = (Manager) ActionContext.getContext().getSession()
				.get("manager");
		Oerationlog oerationlog = new Oerationlog();
		oerationlog.setManager(manager);
		oerationlog.setOerationCategory("saveSmtp");
		oerationlog.setOerationChangeTime(new Timestamp(new Date().getTime()));
		oerationlog.setOerationRemaks("保存邮箱信息");
		oerationlog.setOrationIp(in.getHostAddress().toString());
		this.oerationlogService.createOerationlog(oerationlog);
		return "success";
	}

	/**
	 * qq群管理
	 * 
	 * @return
	 * @throws Exception
	 */
	public String searchKf() throws Exception {
		this.customerqqss = this.customerqqsService.findCustomerqqss();
		return "success";
	}

	public String createKf() throws Exception {
		if (this.customerQqsid != null) {
			Customerqqs cus = this.customerqqsService
					.findCustomerqqsBycustomerQqsid(this.customerQqsid
							.intValue());
			cus.setCustomerQqsname(this.customerQqsname);
			cus.setCustomerQqsnumber(this.customerQqsnumber);
			cus.setDisplay(this.display);
			this.customerqqsService.updateCustomerqqs(cus);
			this.customerQqsname = null;
			this.customerQqsnumber = null;
			this.display = null;
			this.customerQqsid = null;

			InetAddress in = InetAddress.getLocalHost();
			Manager manager = (Manager) ActionContext.getContext().getSession()
					.get("manager");
			Oerationlog oerationlog = new Oerationlog();
			oerationlog.setManager(manager);
			oerationlog.setOerationCategory("saveCustomerqqs");
			oerationlog.setOerationChangeTime(new Timestamp(new Date()
					.getTime()));
			oerationlog.setOerationRemaks("保存qq群");
			oerationlog.setOrationIp(in.getHostAddress().toString());
			this.oerationlogService.createOerationlog(oerationlog);
		} else {
			Customerqqs cus = new Customerqqs();
			cus.setCustomerQqsname(this.customerQqsname);
			cus.setCustomerQqsnumber(this.customerQqsnumber);
			cus.setDisplay(this.display);
			this.customerqqsService.createCustomerqqs(cus);

			InetAddress in = InetAddress.getLocalHost();
			Manager manager = (Manager) ActionContext.getContext().getSession()
					.get("manager");
			Oerationlog oerationlog = new Oerationlog();
			oerationlog.setManager(manager);
			oerationlog.setOerationCategory("addCustomerqqs");
			oerationlog.setOerationChangeTime(new Timestamp(new Date()
					.getTime()));
			oerationlog.setOerationRemaks("新增qq群");
			oerationlog.setOrationIp(in.getHostAddress().toString());
			this.oerationlogService.createOerationlog(oerationlog);
		}
		searchKf();
		return "success";
	}

	public String delKf() throws Exception {
		Customerqqs cus = this.customerqqsService
				.findCustomerqqsBycustomerQqsid(this.customerQqsid.intValue());
		this.customerqqsService.deleteCustomerqqs(cus);
		searchKf();

		InetAddress in = InetAddress.getLocalHost();
		Manager manager = (Manager) ActionContext.getContext().getSession()
				.get("manager");
		this.customerQqsid = null;
		Oerationlog oerationlog = new Oerationlog();
		oerationlog.setManager(manager);
		oerationlog.setOerationCategory("delCustomerqqs");
		oerationlog.setOerationChangeTime(new Timestamp(new Date().getTime()));
		oerationlog.setOerationRemaks("删除qq群");
		oerationlog.setOrationIp(in.getHostAddress().toString());
		this.oerationlogService.createOerationlog(oerationlog);
		return "success";
	}

	public String editKf() throws Exception {
		Customerqqs cus = this.customerqqsService
				.findCustomerqqsBycustomerQqsid(this.customerQqsid.intValue());
		this.customerQqsid = cus.getCustomerQqsid();
		this.customerQqsname = cus.getCustomerQqsname();
		this.customerQqsnumber = cus.getCustomerQqsnumber();
		this.display = cus.getDisplay();
		searchKf();
		this.open = "1";
		return "success";
	}

	/**
	 * 判断客服名字是否存在
	 * 
	 * @return
	 * @throws Exception
	 */
	public String checkManager() throws Exception {
		if (this.managerId == null) {
			this.managerId = Integer.valueOf(0);
		}
		List managers1 = this.managerService
				.findManagerBySql("select * from manager where isBan=1 and isCustomer=1 and managerName='"
						+ this.managerName
						+ "' and managerId!="
						+ this.managerId);
		if ((managers1 == null) || (managers1.size() == 0))
			this.result = "success";
		else {
			this.result = "error";
		}
		return "success";
	}

	/**
	 * 客服电话
	 * 
	 * @return
	 * @throws Exception
	 */
	public String searchPhone() throws Exception {
		this.customerphones = this.customerphoneService.findCustomerphones();
		return "success";
	}

	public String createPhone() throws Exception {
		if (this.customerphoneId != null) {
			Customerphone cus = this.customerphoneService
					.findCustomerphoneBycustomerphoneId(this.customerphoneId
							.intValue());
			cus.setCustomerphoneName(this.customerphoneName);
			cus.setCustomerphoneNumber(this.customerphoneNumber);
			cus.setDisplay(this.display1);
			this.customerphoneService.updateCustomerphone(cus);
			this.customerphoneName = null;
			this.customerphoneNumber = null;
			this.display1 = null;
			this.customerphoneId = null;

			InetAddress in = InetAddress.getLocalHost();
			Manager manager = (Manager) ActionContext.getContext().getSession()
					.get("manager");
			Oerationlog oerationlog = new Oerationlog();
			oerationlog.setManager(manager);
			oerationlog.setOerationCategory("saveCustomerphone");
			oerationlog.setOerationChangeTime(new Timestamp(new Date()
					.getTime()));
			oerationlog.setOerationRemaks("保存客服电话");
			oerationlog.setOrationIp(in.getHostAddress().toString());
			this.oerationlogService.createOerationlog(oerationlog);
		} else {
			Customerphone cus = new Customerphone();
			cus.setCustomerphoneName(this.customerphoneName);
			cus.setCustomerphoneNumber(this.customerphoneNumber);
			cus.setDisplay(this.display1);
			this.customerphoneService.updateCustomerphone(cus);

			InetAddress in = InetAddress.getLocalHost();
			Manager manager = (Manager) ActionContext.getContext().getSession()
					.get("manager");
			Oerationlog oerationlog = new Oerationlog();
			oerationlog.setManager(manager);
			oerationlog.setOerationCategory("addCustomerphone");
			oerationlog.setOerationChangeTime(new Timestamp(new Date()
					.getTime()));
			oerationlog.setOerationRemaks("新增客服电话");
			oerationlog.setOrationIp(in.getHostAddress().toString());
			this.oerationlogService.createOerationlog(oerationlog);
		}
		searchPhone();
		return "success";
	}

	public String delPhone() throws Exception {
		Customerphone cus = this.customerphoneService
				.findCustomerphoneBycustomerphoneId(this.customerphoneId
						.intValue());
		this.customerphoneService.deleteCustomerphone(cus);
		this.customerphoneId = null;
		searchPhone();

		InetAddress in = InetAddress.getLocalHost();
		Manager manager = (Manager) ActionContext.getContext().getSession()
				.get("manager");
		Oerationlog oerationlog = new Oerationlog();
		oerationlog.setManager(manager);
		oerationlog.setOerationCategory("delCustomerphone");
		oerationlog.setOerationChangeTime(new Timestamp(new Date().getTime()));
		oerationlog.setOerationRemaks("删除客服电话");
		oerationlog.setOrationIp(in.getHostAddress().toString());
		this.oerationlogService.createOerationlog(oerationlog);
		return "success";
	}

	public String editPhone() throws Exception {
		Customerphone cus = this.customerphoneService
				.findCustomerphoneBycustomerphoneId(this.customerphoneId
						.intValue());
		this.customerphoneId = cus.getCustomerphoneId();
		this.customerphoneName = cus.getCustomerphoneName();
		this.customerphoneNumber = cus.getCustomerphoneNumber();
		this.display1 = cus.getDisplay();
		searchPhone();
		this.open1 = "1";
		return "success";
	}

	/**
	 * 短信发送记录
	 * 
	 * @return
	 * @throws Exception
	 */
	public String smsSendLog() throws Exception {
		String sql = "";
		sql = sql + "select distinct ss.* from SmsSendLog ss where 1=1";
		sql = sql + " order by ss.logId desc ";
		System.out.println(sql);
		List smsSendLogs = this.sslService.findSmsSendLogsByUserIdPage(sql);
		this.total = Integer.valueOf(smsSendLogs.size());
		if (this.page.intValue() == 0) {
			this.page = Integer.valueOf(1);
		}
		if (this.total.intValue() == 0)
			this.pageCount = Integer.valueOf(1);
		else if (this.total.intValue() % this.pageSize.intValue() == 0) {
			this.pageCount = Integer.valueOf(this.total.intValue()
					/ this.pageSize.intValue());
		} else {
			this.pageCount = Integer.valueOf(this.total.intValue()
					/ this.pageSize.intValue() + 1);
		}
		sql = sql + "LIMIT " + (this.page.intValue() - 1)
				* this.pageSize.intValue() + "," + this.pageSize;
		this.smsSendLogs = this.sslService.findSmsSendLogsByUserIdPage(sql);

		return "success";
	}

	/**
	 * 邮件发送记录
	 * 
	 * @return
	 * @throws Exception
	 */
	public String emailSendLog() throws Exception {
		String sql = "";
		sql = sql
				+ "select distinct esl.* from EmailSendLog esl,Uservip u  where  esl.userId=u.userId and 1=1";
		sql = sql + " order by esl.logId desc ";
		System.out.println(sql);
		List smsSendLogs = this.eslService.findEmailSendLogsByUserIdPage(sql);
		this.total = Integer.valueOf(smsSendLogs.size());
		if (this.page.intValue() == 0) {
			this.page = Integer.valueOf(1);
		}
		if (this.total.intValue() == 0)
			this.pageCount = Integer.valueOf(1);
		else if (this.total.intValue() % this.pageSize.intValue() == 0) {
			this.pageCount = Integer.valueOf(this.total.intValue()
					/ this.pageSize.intValue());
		} else {
			this.pageCount = Integer.valueOf(this.total.intValue()
					/ this.pageSize.intValue() + 1);
		}
		sql = sql + "LIMIT " + (this.page.intValue() - 1)
				* this.pageSize.intValue() + "," + this.pageSize;
		this.emailSendLogs = this.eslService.findEmailSendLogsByUserIdPage(sql);

		return "success";
	}

	/**
	 * qq客服管理
	 * 
	 * @return
	 * @throws Exception
	 */
	public String searchManager() throws Exception {
		this.managers = this.managerService
				.findManagerBySql("select m.* from Manager m where m.isBan=1 and m.isCustomer=1");
		List<Manager> man = new ArrayList<Manager>();
		for(Manager manager:managers){
			List<Uservip> user = this.userService.findUservipsBysql("select * from Uservip u where u.managerId="+manager.getManagerId());
			manager.setCount(user.size());
			man.add(manager);
		}
		ActionContext.getContext().put("man", man);
		
		this.roles = this.roleService.findRolesBysql("select r.* from Role r");
		return "success";
	}

	public String createManager() throws Exception {
		if (this.managerId != null) {
			Manager cus = this.managerService
					.findManagerByManagerId(this.managerId);
			cus.setManagerName(this.managerName);
			if(!cus.getPassword().equals(this.password)){
				cus.setPassword(MD5Util.md5(this.password));
			}
			cus.setPasswordWord(this.passwordWord);
			cus.setRealName(this.realName);
			cus.setTelephone(this.telephone);
			cus.setIsBan(Integer.valueOf(1));
			cus.setIsCustomer(Integer.valueOf(1));
			cus.setQq(this.qq);
			cus.setDisplay(this.display2);
			cus.setRole(this.roleService.findRoleByRoleId(this.roleId));
			if(this.pathMages==null || "".equals(this.pathMages)){
				cus.setPathMages("0");
			}else{
				cus.setPathMages("/images/new/" + this.pathMages);
			}
			boolean boo = this.managerService.updateManager(cus);
			if(boo){
				Manager manager = (Manager) ActionContext.getContext().getSession()
						.get("manager");
				Oerationlog oerationlog = new Oerationlog();
				oerationlog.setManager(manager);
				oerationlog.setOerationCategory("saveManager");
				oerationlog.setOerationChangeTime(new Timestamp(new Date()
						.getTime()));
				oerationlog.setOerationRemaks("保存qq客服");
				String ips = "";
				if (this.request.getHeader("x-forwarded-for") == null)
					ips = this.request.getRemoteAddr();
				else {
					ips = this.request.getHeader("x-forwarded-for");
				}
				oerationlog.setOrationIp(ips);
				this.oerationlogService.createOerationlog(oerationlog);
				this.result = "success";
			}else{
				this.result = "error";
			}
		} else {
			Manager cus = new Manager();
			cus.setManagerName(this.managerName);
			cus.setPassword(MD5Util.md5(this.password));
			cus.setPasswordWord(this.passwordWord);
			cus.setRealName(this.realName);
			cus.setTelephone(this.telephone);
			cus.setIsBan(Integer.valueOf(1));
			cus.setIsCustomer(Integer.valueOf(1));
			cus.setQq(this.qq);
			cus.setDisplay(this.display2);
			cus.setRole(this.roleService.findRoleByRoleId(this.roleId));
			if(this.pathMages==null || "".equals(this.pathMages)){
				cus.setPathMages("0");
			}else{
				cus.setPathMages("/images/new/" + this.pathMages);
			}
			boolean boo = this.managerService.addManager(cus);
			if(boo){
				Manager manager = (Manager) ActionContext.getContext().getSession()
						.get("manager");
				Oerationlog oerationlog = new Oerationlog();
				oerationlog.setManager(manager);
				oerationlog.setOerationCategory("addManager");
				oerationlog.setOerationChangeTime(new Timestamp(new Date()
						.getTime()));
				oerationlog.setOerationRemaks("新增qq客服");
				String ips = "";
				if (this.request.getHeader("x-forwarded-for") == null)
					ips = this.request.getRemoteAddr();
				else {
					ips = this.request.getHeader("x-forwarded-for");
				}
				oerationlog.setOrationIp(ips);
				this.oerationlogService.createOerationlog(oerationlog);
				this.result = "success";
			}else{
				this.result = "error";
			}
		}
		this.managerName = null;
		this.password = null;
		this.passwordWord = null;
		this.realName = null;
		this.telephone = null;
		this.qq = null;
		this.display2 = null;
		this.managerId = null;
		this.roleId = null;
		searchManager();
		return "success";
	}

	public String delManager() throws Exception {
		
		Manager cus = this.managerService
				.findManagerByManagerId(this.managerId);
		List<Oerationlog> list=this.oerationlogService.findByManager(cus.getManagerId());
		if(list.size()>0){
			this.mark="1";
			searchManager();
			return "success";
		}

		
		String sql="SELECT * FROM uservip where managerId="+cus.getManagerId();
		List<Uservip> list2= this.userService.findUservipsBysql(sql);
		if(list2.size()>0){
			this.mark="1";
			searchManager();
			return "success";
		}
		this.managerService.deleteManager(cus);
		this.managerId = null;
		searchManager();

		InetAddress in = InetAddress.getLocalHost();
		Manager manager = (Manager) ActionContext.getContext().getSession()
				.get("manager");
		Oerationlog oerationlog = new Oerationlog();
		oerationlog.setManager(manager);
		oerationlog.setOerationCategory("delManager");
		oerationlog.setOerationChangeTime(new Timestamp(new Date().getTime()));
		oerationlog.setOerationRemaks("删除qq客服");
		oerationlog.setOrationIp(in.getHostAddress().toString());
		this.oerationlogService.createOerationlog(oerationlog);
		return "success";
	}

	public String editManager() throws Exception {
		// Manager cus =
		// this.managerService.findManagerByManagerId(this.managerId);
		// this.managerId = cus.getManagerId();
		// this.managerName = cus.getManagerName();
		// this.qq = cus.getQq();
		// this.display2 = cus.getDisplay();
		// searchManager();
		// this.open2 = "1";
		// return "success";

		Manager cus = this.managerService
				.findManagerByManagerId(this.managerId);
		ActionContext.getContext().getSession().put("cus", cus);
		this.managerId = cus.getManagerId();
		this.managerName = cus.getManagerName();
		this.password = cus.getPassword();
		this.passwordWord = cus.getPasswordWord();
		this.realName = cus.getRealName();
		this.telephone = cus.getTelephone();
		this.qq = cus.getQq();
		this.display2 = cus.getDisplay();
		this.roleId = cus.getRole().getRoleId();
		searchManager();
		this.open2 = "1";
		return "success";
	}

	/**
	 * 线下支付管理
	 * 
	 * @return
	 * @throws Exception
	 */
	public String searchBank() throws Exception {
		this.bankparameters =bankparameterService.findBankparameters();
		
		this.companybankinfors = this.companybankinforService
				.findCompanybankinfors();
		return "success";
	}

	public String createBank() throws Exception {
		if (this.companyBankInforId != null) {
			Companybankinfor cus = this.companybankinforService
					.findCompanybankinforById(this.companyBankInforId
							.intValue());
			cus.setAccountName(this.accountName);
			cus.setAccountNum(this.accountNum);
			cus.setAccountAddress(this.accountAddress);
			cus.setBankparameter(this.bankparameterService
					.findBankparameterBybankPid(this.bankId.intValue()));
			this.companybankinforService.updateCompanybankinfor(cus);
			this.accountName = null;
			this.accountNum = null;
			this.accountAddress = null;
			this.companyBankInforId = null;
			this.bankId = null;

			InetAddress in = InetAddress.getLocalHost();
			Manager manager = (Manager) ActionContext.getContext().getSession()
					.get("manager");
			Oerationlog oerationlog = new Oerationlog();
			oerationlog.setManager(manager);
			oerationlog.setOerationCategory("saveCompanybankinfor");
			oerationlog.setOerationChangeTime(new Timestamp(new Date()
					.getTime()));
			oerationlog.setOerationRemaks("保存线下支付接口");
			oerationlog.setOrationIp(in.getHostAddress().toString());
			this.oerationlogService.createOerationlog(oerationlog);
		} else {
			Companybankinfor cus = new Companybankinfor();
			cus.setAccountName(this.accountName);
			cus.setAccountNum(this.accountNum);
			cus.setAccountAddress(this.accountAddress);
			cus.setBankparameter(this.bankparameterService
					.findBankparameterBybankPid(this.bankId.intValue()));
			this.companybankinforService.createCompanybankinfor(cus);

			InetAddress in = InetAddress.getLocalHost();
			Manager manager = (Manager) ActionContext.getContext().getSession()
					.get("manager");
			Oerationlog oerationlog = new Oerationlog();
			oerationlog.setManager(manager);
			oerationlog.setOerationCategory("addCompanybankinfor");
			oerationlog.setOerationChangeTime(new Timestamp(new Date()
					.getTime()));
			oerationlog.setOerationRemaks("新增线下支付接口");
			oerationlog.setOrationIp(in.getHostAddress().toString());
			this.oerationlogService.createOerationlog(oerationlog);
		}
		searchBank();
		return "success";
	}

	public String delBank() throws Exception {
		Companybankinfor cus = this.companybankinforService
				.findCompanybankinforById(this.companyBankInforId.intValue());
		this.companybankinforService.deleteCompanybankinfor(cus);
		this.companyBankInforId = null;
		searchBank();

		InetAddress in = InetAddress.getLocalHost();
		Manager manager = (Manager) ActionContext.getContext().getSession()
				.get("manager");
		Oerationlog oerationlog = new Oerationlog();
		oerationlog.setManager(manager);
		oerationlog.setOerationCategory("delCompanybankinfor");
		oerationlog.setOerationChangeTime(new Timestamp(new Date().getTime()));
		oerationlog.setOerationRemaks("删除线下支付接口");
		oerationlog.setOrationIp(in.getHostAddress().toString());
		this.oerationlogService.createOerationlog(oerationlog);
		return "success";
	}

	public String editBank() throws Exception {
		Companybankinfor cus = this.companybankinforService
				.findCompanybankinforById(this.companyBankInforId.intValue());
		this.companyBankInforId = cus.getCompanyBankInforId();
		this.accountName = cus.getAccountName();
		this.accountNum = cus.getAccountNum();
		this.accountAddress = cus.getAccountAddress();
		this.bankId = cus.getBankparameter().getBankPid();
		searchBank();
		this.open3 = "1";
		return "success";
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getEmail1() {
		return this.email1;
	}

	public void setEmail1(String email1) {
		this.email1 = email1;
	}

	public String getEmail4() {
		return this.email4;
	}

	public void setEmail4(String email4) {
		this.email4 = email4;
	}

	public String getEmail6() {
		return this.email6;
	}

	public void setEmail6(String email6) {
		this.email6 = email6;
	}

	public String getInterletter1() {
		return this.interletter1;
	}

	public void setInterletter1(String interletter1) {
		this.interletter1 = interletter1;
	}

	public String getInterletter2() {
		return this.interletter2;
	}

	public void setInterletter2(String interletter2) {
		this.interletter2 = interletter2;
	}

	public Messagemodel getMessagemodel1() {
		return this.messagemodel1;
	}

	public void setMessagemodel1(Messagemodel messagemodel1) {
		this.messagemodel1 = messagemodel1;
	}

	public Messagemodel getMessagemodel2() {
		return this.messagemodel2;
	}

	public void setMessagemodel2(Messagemodel messagemodel2) {
		this.messagemodel2 = messagemodel2;
	}

	public Messagemodel getMessagemodel3() {
		return this.messagemodel3;
	}

	public void setMessagemodel3(Messagemodel messagemodel3) {
		this.messagemodel3 = messagemodel3;
	}

	public Messagemodel getMessagemodel4() {
		return this.messagemodel4;
	}

	public void setMessagemodel4(Messagemodel messagemodel4) {
		this.messagemodel4 = messagemodel4;
	}

	public Messagemodel getMessagemodel5() {
		return this.messagemodel5;
	}

	public void setMessagemodel5(Messagemodel messagemodel5) {
		this.messagemodel5 = messagemodel5;
	}

	public Messagemodel getMessagemodel6() {
		return this.messagemodel6;
	}

	public void setMessagemodel6(Messagemodel messagemodel6) {
		this.messagemodel6 = messagemodel6;
	}

	public Messagemodel getMessagemodel7() {
		return messagemodel7;
	}

	public void setMessagemodel7(Messagemodel messagemodel7) {
		this.messagemodel7 = messagemodel7;
	}

	public Messagemodel getMessagemodel8() {
		return messagemodel8;
	}

	public void setMessagemodel8(Messagemodel messagemodel8) {
		this.messagemodel8 = messagemodel8;
	}

	public Messagemodel getMessagemodel9() {
		return messagemodel9;
	}

	public void setMessagemodel9(Messagemodel messagemodel9) {
		this.messagemodel9 = messagemodel9;
	}

	public Messagemodel getMessagemodel10() {
		return messagemodel10;
	}

	public void setMessagemodel10(Messagemodel messagemodel10) {
		this.messagemodel10 = messagemodel10;
	}

	public Messagemodel getMessagemodel11() {
		return messagemodel11;
	}

	public void setMessagemodel11(Messagemodel messagemodel11) {
		this.messagemodel11 = messagemodel11;
	}

	public Messagemodel getMessagemodel12() {
		return messagemodel12;
	}

	public void setMessagemodel12(Messagemodel messagemodel12) {
		this.messagemodel12 = messagemodel12;
	}

	public Messagemodel getMessagemodel13() {
		return messagemodel13;
	}

	public void setMessagemodel13(Messagemodel messagemodel13) {
		this.messagemodel13 = messagemodel13;
	}

	public Messagemodel getMessagemodel14() {
		return messagemodel14;
	}

	public void setMessagemodel14(Messagemodel messagemodel14) {
		this.messagemodel14 = messagemodel14;
	}

	public Messagemodel getMessagemodel15() {
		return messagemodel15;
	}

	public void setMessagemodel15(Messagemodel messagemodel15) {
		this.messagemodel15 = messagemodel15;
	}

	public String getInterletter3() {
		return this.interletter3;
	}

	public void setInterletter3(String interletter3) {
		this.interletter3 = interletter3;
	}

	public String getInterletter4() {
		return this.interletter4;
	}

	public void setInterletter4(String interletter4) {
		this.interletter4 = interletter4;
	}

	public String getInterletter5() {
		return this.interletter5;
	}

	public void setInterletter5(String interletter5) {
		this.interletter5 = interletter5;
	}

	public String getInterletter6() {
		return this.interletter6;
	}

	public void setInterletter6(String interletter6) {
		this.interletter6 = interletter6;
	}

	public String getInterletter7() {
		return this.interletter7;
	}

	public void setInterletter7(String interletter7) {
		this.interletter7 = interletter7;
	}

	public String getMark() {
		return this.mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public Smtp getSmtp() {
		return this.smtp;
	}

	public void setSmtp(Smtp smtp) {
		this.smtp = smtp;
	}

	public List<Customerqqs> getCustomerqqss() {
		return this.customerqqss;
	}

	public void setCustomerqqss(List<Customerqqs> customerqqss) {
		this.customerqqss = customerqqss;
	}

	public String getCustomerQqsname() {
		return this.customerQqsname;
	}

	public void setCustomerQqsname(String customerQqsname) {
		this.customerQqsname = customerQqsname;
	}

	public String getCustomerQqsnumber() {
		return this.customerQqsnumber;
	}

	public void setCustomerQqsnumber(String customerQqsnumber) {
		this.customerQqsnumber = customerQqsnumber;
	}

	public Integer getDisplay() {
		return this.display;
	}

	public void setDisplay(Integer display) {
		this.display = display;
	}

	public Integer getCustomerQqsid() {
		return this.customerQqsid;
	}

	public void setCustomerQqsid(Integer customerQqsid) {
		this.customerQqsid = customerQqsid;
	}

	public String getOpen() {
		return this.open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public List<Customerphone> getCustomerphones() {
		return this.customerphones;
	}

	public void setCustomerphones(List<Customerphone> customerphones) {
		this.customerphones = customerphones;
	}

	public Integer getCustomerphoneId() {
		return this.customerphoneId;
	}

	public void setCustomerphoneId(Integer customerphoneId) {
		this.customerphoneId = customerphoneId;
	}

	public String getCustomerphoneName() {
		return this.customerphoneName;
	}

	public void setCustomerphoneName(String customerphoneName) {
		this.customerphoneName = customerphoneName;
	}

	public String getCustomerphoneNumber() {
		return this.customerphoneNumber;
	}

	public void setCustomerphoneNumber(String customerphoneNumber) {
		this.customerphoneNumber = customerphoneNumber;
	}

	public String getOpen1() {
		return this.open1;
	}

	public void setOpen1(String open1) {
		this.open1 = open1;
	}

	public Integer getDisplay1() {
		return this.display1;
	}

	public void setDisplay1(Integer display1) {
		this.display1 = display1;
	}

	public List<Manager> getManagers() {
		return this.managers;
	}

	public void setManagers(List<Manager> managers) {
		this.managers = managers;
	}

	public Integer getManagerId() {
		return this.managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public String getManagerName() {
		return this.managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public Integer getDisplay2() {
		return this.display2;
	}

	public void setDisplay2(Integer display2) {
		this.display2 = display2;
	}

	public String getOpen2() {
		return this.open2;
	}

	public void setOpen2(String open2) {
		this.open2 = open2;
	}

	public Integer getCompanyBankInforId() {
		return this.companyBankInforId;
	}

	public void setCompanyBankInforId(Integer companyBankInforId) {
		this.companyBankInforId = companyBankInforId;
	}

	public String getAccountName() {
		return this.accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountNum() {
		return this.accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public String getAccountAddress() {
		return this.accountAddress;
	}

	public void setAccountAddress(String accountAddress) {
		this.accountAddress = accountAddress;
	}

	public String getOpen3() {
		return this.open3;
	}

	public void setOpen3(String open3) {
		this.open3 = open3;
	}

	public Integer getBankId() {
		return this.bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

	public List<Companybankinfor> getCompanybankinfors() {
		return this.companybankinfors;
	}

	public void setCompanybankinfors(List<Companybankinfor> companybankinfors) {
		this.companybankinfors = companybankinfors;
	}

	public Phone getPhone() {
		return this.phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}

	public Phone getPhone1() {
		return this.phone1;
	}

	public void setPhone1(Phone phone1) {
		this.phone1 = phone1;
	}

	public Phone getPhone2() {
		return this.phone2;
	}

	public void setPhone2(Phone phone2) {
		this.phone2 = phone2;
	}

	public String getNum1() {
		return this.num1;
	}

	public void setNum1(String num1) {
		this.num1 = num1;
	}

	public String getNum2() {
		return this.num2;
	}

	public void setNum2(String num2) {
		this.num2 = num2;
	}

	public int getPhoneId() {
		return this.phoneId;
	}

	public void setPhoneId(int phoneId) {
		this.phoneId = phoneId;
	}

	public String getPhonename() {
		return this.phonename;
	}

	public void setPhonename(String phonename) {
		this.phonename = phonename;
	}

	public String getPhonepassword() {
		return this.phonepassword;
	}

	public void setPhonepassword(String phonepassword) {
		this.phonepassword = phonepassword;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	

	public String getPhoneuserId() {
		return phoneuserId;
	}

	public void setPhoneuserId(String phoneuserId) {
		this.phoneuserId = phoneuserId;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getPathMages() {
		return pathMages;
	}

	public void setPathMages(String pathMages) {
		this.pathMages = pathMages;
	}

	public List<Bankparameter> getBankparameters() {
		return bankparameters;
	}

	public void setBankparameters(List<Bankparameter> bankparameters) {
		this.bankparameters = bankparameters;
	}
	
}