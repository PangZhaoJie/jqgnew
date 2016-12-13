/**  
 * @Project: longpei
 * @Title: WapUserAction.java
 * @Package com.jqg.wap
 * @date 2015-7-21 下午4:18:01
 * @Copyright: 2015 
 */
package com.jqg.wap;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.jqg.pojo.Bankparameter;
import com.jqg.pojo.Basicinfor;
import com.jqg.pojo.Bonus;
import com.jqg.pojo.Certification;
import com.jqg.pojo.Inbox;
import com.jqg.pojo.Integralcategory;
import com.jqg.pojo.Integraldetail;
import com.jqg.pojo.Integralparameter;
import com.jqg.pojo.Internallettermodel;
import com.jqg.pojo.Moneycount;
import com.jqg.pojo.Moneyhistorydetail;
import com.jqg.pojo.Offlinerecharge;
import com.jqg.pojo.Personalbankinfor;
import com.jqg.pojo.Promrewardbypeople;
import com.jqg.pojo.Systemconf;
import com.jqg.pojo.Translate;
import com.jqg.pojo.UserLog;
import com.jqg.pojo.Uservip;
import com.jqg.pojo.Website;
import com.jqg.service.BankparameterService;
import com.jqg.service.BasicinforService;
import com.jqg.service.BonusService;
import com.jqg.service.CertificationService;
import com.jqg.service.CreditlevelService;
import com.jqg.service.InboxService;
import com.jqg.service.IntegralcategoryService;
import com.jqg.service.IntegraldetailService;
import com.jqg.service.IntegralparameterService;
import com.jqg.service.InternallettermodelService;
import com.jqg.service.MoneycountService;
import com.jqg.service.MoneyhistorydetailService;
import com.jqg.service.OfflinerechargeService;
import com.jqg.service.PersonalbankinforService;
import com.jqg.service.PromrewardbypeopleService;
import com.jqg.service.SystemconfService;
import com.jqg.service.TranslateService;
import com.jqg.service.UserLogService;
import com.jqg.service.UservipService;
import com.jqg.service.WebsiteService;
import com.jqg.service.impl.BankparameterServiceImpl;
import com.jqg.service.impl.BasicinforServiceImpl;
import com.jqg.service.impl.BonusServiceImpl;
import com.jqg.service.impl.CertificationServiceImpl;
import com.jqg.service.impl.CreditlevelServiceImpl;
import com.jqg.service.impl.InboxServiceImpl;
import com.jqg.service.impl.IntegralcategoryServiceImpl;
import com.jqg.service.impl.IntegraldetailServiceImpl;
import com.jqg.service.impl.IntegralparameterServiceImpl;
import com.jqg.service.impl.InternallettermodelServiceImpl;
import com.jqg.service.impl.MoneycountServiceImpl;
import com.jqg.service.impl.MoneyhistorydetailServiceImpl;
import com.jqg.service.impl.OfflinerechargeServiceImpl;
import com.jqg.service.impl.PersonalbankinforServiceImpl;
import com.jqg.service.impl.PromrewardbypeopleServiceImpl;
import com.jqg.service.impl.SystemconfServiceImpl;
import com.jqg.service.impl.TranslateServiceImpl;
import com.jqg.service.impl.UserLogServiceImpl;
import com.jqg.service.impl.UservipServiceImpl;
import com.jqg.service.impl.WebsiteServiceImpl;
import com.jqg.struts.BaseAction;
import com.jqg.util.Common;
import com.jqg.util.HttpClientUtil;
import com.jqg.util.LoanUtils;
import com.jqg.util.MD5Trust;
import com.jqg.util.MD5Util;
import com.jqg.util.RsaHelper;
import com.loan.model.LoanRegisterBindReturnBean;
import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * 类名：WapUserAction 功能： 详细： 作者：曹中德(caozhongde) 版本：1.0 日期：2015-7-21 下午4:18:01
 * 
 */
public class WapUserAction extends BaseAction {
	
	private static HttpServletRequest request;
	private String sessionId = "";
	private String ct;
	private Double chongzhiMoney;
	// 公共
	private String PlatformMoneymoremore = "";
	private String SubmitURL = "";
	private String SubmitURLPrefix = "http://218.4.234.150:88/main/";
	private String ReturnURL = "";
	private String NotifyURL = "";
	private String SignInfo = "";
	private String ResultCode = "";
	private String Message = "";
	private String ReturnTimes = "";
	private String verifySignature = "";
	private String RandomTimeStamp = "";
	private final int antistate = 0;
	private String Remark1 = "";//备注
	private String Remark2 = "";
	private String Remark3 = "";
	// 注册
	private String RegisterType = "";
	private String AccountType = "";
	private String AccountNumber = "";
	private String Mobile = "";
	private String Email = "";
	private String RealName = "";
	private String IdentificationNo = "";
	private String Image1 = "";
	private String Image2 = "";
	private String AuthFee = "";
	private String AuthState = "";
	// 授权
	private String AuthorizeType = "";
	private String AuthorizeTypeOpen = "";
	private String AuthorizeTypeClose = "";
	// 绑定
	private String LoanPlatformAccount = "";
	private String LoanMoneymoremore = "";
	private String MoneymoremoreId = "";
	
	private boolean istest= true;//true ：测试，false:正式
	
	private int userId;
	private String payPwd;
	private String key;
	private Double parvalue;
	private int bankPid;
	private Uservip uservip;
	private Double affectMoney;
	private Timestamp timestamp;
	private TranslateService translateService=new TranslateServiceImpl();
	private MoneycountService MoneycountService=new MoneycountServiceImpl();
	
	

	public int getBankPid() {
		return bankPid;
	}

	public void setBankPid(int bankPid) {
		this.bankPid = bankPid;
	}

	public Uservip getUservip() {
		return uservip;
	}

	public void setUservip(Uservip uservip) {
		this.uservip = uservip;
	}

	public Double getAffectMoney() {
		return affectMoney;
	}

	public void setAffectMoney(Double affectMoney) {
		this.affectMoney = affectMoney;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPayPwd() {
		return payPwd;
	}

	public void setPayPwd(String payPwd) {
		this.payPwd = payPwd;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}


	//````````````````````````````````````````````````````

	public Double getParvalue() {
		return parvalue;
	}

	public void setParvalue(Double parvalue) {
		this.parvalue = parvalue;
	}

	public String getPlatformMoneymoremore() {
		return PlatformMoneymoremore;
	}

	public void setPlatformMoneymoremore(String platformMoneymoremore) {
		PlatformMoneymoremore = platformMoneymoremore;
	}

	public String getSubmitURL() {
		return SubmitURL;
	}

	public void setSubmitURL(String submitURL) {
		SubmitURL = submitURL;
	}

	public String getSubmitURLPrefix() {
		return SubmitURLPrefix;
	}

	public void setSubmitURLPrefix(String submitURLPrefix) {
		SubmitURLPrefix = submitURLPrefix;
	}

	public String getReturnURL() {
		return ReturnURL;
	}

	public void setReturnURL(String returnURL) {
		ReturnURL = returnURL;
	}

	public String getNotifyURL() {
		return NotifyURL;
	}

	public void setNotifyURL(String notifyURL) {
		NotifyURL = notifyURL;
	}

	public String getSignInfo() {
		return SignInfo;
	}

	public void setSignInfo(String signInfo) {
		SignInfo = signInfo;
	}

	public String getResultCode() {
		return ResultCode;
	}

	public void setResultCode(String resultCode) {
		ResultCode = resultCode;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public String getReturnTimes() {
		return ReturnTimes;
	}

	public void setReturnTimes(String returnTimes) {
		ReturnTimes = returnTimes;
	}

	public String getVerifySignature() {
		return verifySignature;
	}

	public void setVerifySignature(String verifySignature) {
		this.verifySignature = verifySignature;
	}

	public String getRemark1() {
		return Remark1;
	}

	public void setRemark1(String remark1) {
		Remark1 = remark1;
	}

	public String getRemark2() {
		return Remark2;
	}

	public void setRemark2(String remark2) {
		Remark2 = remark2;
	}

	public String getRemark3() {
		return Remark3;
	}

	public void setRemark3(String remark3) {
		Remark3 = remark3;
	}

	public String getRegisterType() {
		return RegisterType;
	}

	public void setRegisterType(String registerType) {
		RegisterType = registerType;
	}

	public String getAccountType() {
		return AccountType;
	}

	public void setAccountType(String accountType) {
		AccountType = accountType;
	}

	public String getAccountNumber() {
		return AccountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		AccountNumber = accountNumber;
	}

	public String getMobile() {
		return Mobile;
	}

	public void setMobile(String mobile) {
		Mobile = mobile;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getRealName() {
		return RealName;
	}

	public void setRealName(String realName) {
		RealName = realName;
	}

	public String getIdentificationNo() {
		return IdentificationNo;
	}

	public void setIdentificationNo(String identificationNo) {
		IdentificationNo = identificationNo;
	}

	public String getImage1() {
		return Image1;
	}

	public void setImage1(String image1) {
		Image1 = image1;
	}

	public String getImage2() {
		return Image2;
	}

	public void setImage2(String image2) {
		Image2 = image2;
	}

	public String getAuthFee() {
		return AuthFee;
	}

	public void setAuthFee(String authFee) {
		AuthFee = authFee;
	}

	public String getAuthState() {
		return AuthState;
	}

	public void setAuthState(String authState) {
		AuthState = authState;
	}

	public String getAuthorizeType() {
		return AuthorizeType;
	}

	public void setAuthorizeType(String authorizeType) {
		AuthorizeType = authorizeType;
	}

	public String getAuthorizeTypeOpen() {
		return AuthorizeTypeOpen;
	}

	public void setAuthorizeTypeOpen(String authorizeTypeOpen) {
		AuthorizeTypeOpen = authorizeTypeOpen;
	}

	public String getAuthorizeTypeClose() {
		return AuthorizeTypeClose;
	}

	public void setAuthorizeTypeClose(String authorizeTypeClose) {
		AuthorizeTypeClose = authorizeTypeClose;
	}

	public String getLoanPlatformAccount() {
		return LoanPlatformAccount;
	}

	public void setLoanPlatformAccount(String loanPlatformAccount) {
		LoanPlatformAccount = loanPlatformAccount;
	}

	public String getLoanMoneymoremore() {
		return LoanMoneymoremore;
	}

	public void setLoanMoneymoremore(String loanMoneymoremore) {
		LoanMoneymoremore = loanMoneymoremore;
	}

	public String getMoneymoremoreId() {
		return MoneymoremoreId;
	}

	public void setMoneymoremoreId(String moneymoremoreId) {
		MoneymoremoreId = moneymoremoreId;
	}

	public boolean isIstest() {
		return istest;
	}

	public void setIstest(boolean istest) {
		this.istest = istest;
	}

	public int getAntistate() {
		return antistate;
	}

	public String getCt() {
		return ct;
	}

	public void setCt(String ct) {
		this.ct = ct;
	}

	public Offlinerecharge getOfflinerecharge() {
		return offlinerecharge;
	}

	public void setOfflinerecharge(Offlinerecharge offlinerecharge) {
		this.offlinerecharge = offlinerecharge;
	}

	public String getPostform() {
		return postform;
	}

	public void setPostform(String postform) {
		this.postform = postform;
	}

	public Double getChongzhiMoney() {
		return chongzhiMoney;
	}

	public void setChongzhiMoney(Double chongzhiMoney) {
		this.chongzhiMoney = chongzhiMoney;
	}

	private Offlinerecharge offlinerecharge;
	   private String postform;
	   
	   private OfflinerechargeService offlinerechargeService = new OfflinerechargeServiceImpl();
	MoneycountService moneycountService = new MoneycountServiceImpl();
	UserLogService userLogService=new UserLogServiceImpl(); 
	UservipService uservipService=new UservipServiceImpl();
	   
	MoneyhistorydetailService moneyhistorydetailService=new MoneyhistorydetailServiceImpl();
	PersonalbankinforService personalbankinforService=new PersonalbankinforServiceImpl();
	  List<Personalbankinfor> BankList=new ArrayList<Personalbankinfor>();
	public List<Personalbankinfor> getBankList() {
		return BankList;
	}

	public void setBankList(List<Personalbankinfor> bankList) {
		BankList = bankList;
	}

	BankparameterService bankparameterService=new BankparameterServiceImpl();
	BasicinforService basicinforService=new BasicinforServiceImpl();
	IntegralparameterService integralparameterService=new IntegralparameterServiceImpl();
	PromrewardbypeopleService promrewardbypeopleService=new PromrewardbypeopleServiceImpl();
	BonusService bonusService=new BonusServiceImpl(); 
	CreditlevelService creditlevelService=new CreditlevelServiceImpl();
	SystemconfService systemconfService=new SystemconfServiceImpl();
	IntegralcategoryService integralcategoryService=new IntegralcategoryServiceImpl();
	IntegraldetailService integraldetailService=new IntegraldetailServiceImpl();
//	ActivityConfigService activityConfigService=new ActivityConfigServiceImpl();
//	ActivityUsersService activityUsersService=new ActivityUsersServiceImpl();
	InternallettermodelService internallettermodelService=new InternallettermodelServiceImpl();
	WebsiteService websiteService=new WebsiteServiceImpl();
	InboxService inboxService=new InboxServiceImpl();
	
	/**
	 * 用户中心
	 * 
	 * @return
	 * @throws Exception
	 */
	public String findUsers() throws Exception {
		request = ServletActionContext.getRequest();
		this.sessionId = request.getSession().getId();
		Uservip uservip = (Uservip) ActionContext.getContext().getSession()
				.get("uservip" + sessionId);
		Moneycount moneycount = moneycountService
				.findMoneycountByuserId(uservip.getUserId());

		request.setAttribute("uservip", uservip);
		request.setAttribute("moneycount", moneycount);
		return "success";
	}
	
	  /**
	   * 
	   * 提现管理
	   */
	public String totixian() throws Exception {
		
		
		
		request = ServletActionContext.getRequest();
		this.sessionId = request.getSession().getId();
		Uservip uservip = (Uservip) ActionContext.getContext().getSession()
				.get("uservip" + sessionId);
		Moneycount moneycount = moneycountService
				.findMoneycountByuserId(uservip.getUserId());
		request.setAttribute("uservip", uservip);
		request.setAttribute("moneycount", moneycount);
		List<Personalbankinfor> personalbankinfors = personalbankinforService.findPersonalbankinforsByuser(uservip.getUserId());
		for(Personalbankinfor person:personalbankinfors){
			if(person.getAccountNum().length()>8){
				String number = person.getBankparameter().getBankPname()+person.getAccountNum().substring(0, 4) + "*** *** ***" +person.getAccountNum().substring(person.getAccountNum().length()-4, person.getAccountNum().length());
				person.setAccountNums(number);
			}else{
				person.setAccountNums(person.getAccountNum());
			}
			
			BankList.add(person);
		}
		
	    Website website = this.websiteService.findWebsiteBywebsiteId(1);
	    Basicinfor basicinfor = this.basicinforService.findBasicinforByUserId(uservip.getUserId().intValue());
	    if (website.getTranslateLimit().intValue() == 0)
	    {
	      ActionContext.getContext().getSession().put("translateLimit", "无限额");
	    }
	    else
	    {
	      ActionContext.getContext().getSession().put("translateLimit", "每日最高提现为" + website.getTranslateLimit() + "元");
	    }
	    SystemconfService systemconfService = new SystemconfServiceImpl();
	    Systemconf latefee = systemconfService.findSystemconfByParname("con_money_lowest");//最低提现金额  
	    ActionContext.getContext().getSession().put("bankbasicinfor", basicinfor);
	    ActionContext.getContext().getSession().put("limit", website.getTranslateLimit());
	    ActionContext.getContext().getSession().put("bankAvailableMoney", moneycount.getAvailableMoney());
	    ActionContext.getContext().put("latefee", latefee);
		return "success";
	}
	
	
	   /**
	    *  托管充值处理
	 * @throws Exception 
	    */
	   public String trustRecharge() throws Exception{
		   
		   request = ServletActionContext.getRequest();
			this.sessionId = request.getSession().getId();
			Uservip uservip = (Uservip) ActionContext.getContext().getSession()
					.get("uservip" + sessionId);
			System.out.println(request.getParameter("chongzhiMoney"));
			this.chongzhiMoney=Double.valueOf(request.getParameter("chongzhiMoney")).doubleValue();
			System.out.println(request.getParameter("ssa"));
			System.out.println(request.getParameter("ct"));
		   uservip = this.uservipService.findUservipByUserid(uservip.getUserId().intValue());
		   this.offlinerecharge = new Offlinerecharge();
		   this.offlinerecharge.setRechargeAmount(this.chongzhiMoney);
		   this.offlinerecharge.setRechargeTime(new Timestamp(new Date().getTime()));
		   this.offlinerecharge.setRechargeStatus(2);
		   this.offlinerecharge.setUservip(uservip);
		   this.offlinerecharge.setRecharge("托管支付");
		   this.offlinerechargeService.createOfflinerecharge(this.offlinerecharge);
		   WebsiteService websiteService = new WebsiteServiceImpl();
		   Website website = websiteService.findWebsiteBywebsiteId(1);
		   LoanUtils loanutils = new LoanUtils();
		   request = ServletActionContext.getRequest();
		   uservip.setUserNames("wap");
		   this.postform = loanutils.LoanRecharge(request, website, uservip, offlinerecharge,"quick");
		   return "success";
	   }

	/**
	 * 登录
	 * 
	 * @return
	 * @throws Exception 
	 */
	public String login() throws Exception {
		request = ServletActionContext.getRequest();

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
		
		String userName=request.getParameter("userName");
		String userPwd=request.getParameter("userPwd");
		String code=request.getParameter("code");
		
		this.sessionId = request.getSession().getId();
		String code1 = (String)ActionContext.getContext().getSession().get("code1"+this.sessionId);
		String yanzhengma = request.getParameter("yanzhengma1");
		if(!code1.equals(code)){
			
			this.setMessage("验证码错误，请重新输入", "/wap/login.jsp", "3");
			return "error";//验证码错误
		}

		Uservip uservip  = uservipService.findUservipByPasswordAndUserName(
				MD5Util.md5(userPwd), userName);
		
		if (uservip != null) {
		

			ActionContext.getContext().getSession()
					.put("uservip" + sessionId, uservip);
 
			UserLog userLog=new UserLog();
			userLog.setLoginTime(new Timestamp(new Date().getTime()));
			userLog.setUservip(uservip);
			userLog.setLoginIp(ip);
			userLog.setDetail("wap登录成功");
			
			userLogService.addUserLog(userLog);
			
			return "success";
		} else {
			this.setMessage("用户名或者密码错误,请重新输入", "/wap/login.jsp", "3");
			return "error";
		}

		
	}
	
	/**
	 * 总资产
	 * @return
	 * @throws Exception 
	 */
	public String toAssets() throws Exception{
		request = ServletActionContext.getRequest();
		this.sessionId = request.getSession().getId();
		Uservip uservip = (Uservip) ActionContext.getContext().getSession()
				.get("uservip" + sessionId);
		Moneycount moneycount=moneycountService.findMoneycountByuserId(uservip.getUserId());
		
		
		request.setAttribute("moneycount", moneycount);
		return "success";
	}
	/**
	 * 资金记录
	 * @return
	 * @throws Exception 
	 */
	public String fundhistory() throws Exception{
		request = ServletActionContext.getRequest();
		this.sessionId = request.getSession().getId();
		Uservip uservip = (Uservip) ActionContext.getContext().getSession()
				.get("uservip" + sessionId);
		
		String sql="select * from moneyhistorydetail where userid="+uservip.getUserId();
		List<Moneyhistorydetail> MHList=moneyhistorydetailService.findMoneyhistorydetailBySql(sql);
		
		request.setAttribute("MHList", MHList);
		
		return "success";
	}
	
	/**
	 * 我的银行卡
	 * @return
	 * @throws Exception 
	 */
	public String myCard() throws Exception{
		request = ServletActionContext.getRequest();
		this.sessionId = request.getSession().getId();
		Uservip uservip = (Uservip) ActionContext.getContext().getSession()
				.get("uservip" + sessionId);

		List<Personalbankinfor> personalbankinfors = this.personalbankinforService
				.findPersonalbankinforsByuser(uservip.getUserId()
						.intValue());
		
		List<Personalbankinfor> pbList=new ArrayList<Personalbankinfor>();
		for(Personalbankinfor pb:personalbankinfors){
			
			String account ="***"+ pb.getAccountNum().substring(
					pb.getAccountNum().length() - 4, pb.getAccountNum().length());// 截取银行卡的后四位
			
			pb.setAccountNums(account);
			pbList.add(pb);
		}
		
		
		request.setAttribute("pbList", pbList);
		return "success";
	}
	
	/**
	 * 跳转到添加银行卡界面
	 * @return
	 * @throws Exception 
	 */
	public String jumpCard() throws Exception{
		request = ServletActionContext.getRequest();
		this.sessionId = request.getSession().getId();
		Uservip uservip = (Uservip) ActionContext.getContext().getSession()
				.get("uservip" + sessionId);
		uservip =uservipService.findUservipByUserid(uservip.getUserId());
		List<Bankparameter> bpList=bankparameterService.findBankparameters();
		
		request.setAttribute("uservip", uservip);
		request.setAttribute("bpList", bpList);
		return "success";
	}
	/**
	 * 银行卡添加
	 * @return
	 * @throws Exception 
	 */
	public String addCard() throws Exception{
		request = ServletActionContext.getRequest();
		this.sessionId = request.getSession().getId();
		Uservip uservip = (Uservip) ActionContext.getContext().getSession()
				.get("uservip" + sessionId);
		String accountNum=request.getParameter("accountNum");
		String accountAddress=request.getParameter("accountAddress");
		String bankPid=request.getParameter("bankPid");
		Basicinfor basicinfor = basicinforService
				.findBasicinforByUserId(uservip.getUserId().intValue());
		Bankparameter bankparameter = this.bankparameterService
				.findBankparameterBybankPid(Integer.parseInt(bankPid));
		if (bankparameter == null) {
			this.setMessage("选择的银行不正确！", "/wap/addCard", "3");
			return "error";
		}
		if (accountNum == "" && accountNum.equals("")) {
			this.setMessage("银行账号不可以为空！", "/wap/addCard", "3");
			return "error";
		}

		if (accountAddress == "" && accountAddress.equals("")) {
			this.setMessage("开户支行名称不可以为空！", "/wap/addCard", "3");
			return "error";
		}
		Personalbankinfor personalbankinfor = new Personalbankinfor();
		personalbankinfor.setAccountAddress(accountAddress);
		personalbankinfor.setAccountName(basicinfor.getRealName());
		personalbankinfor.setAccountNum(accountNum);
		personalbankinfor.setUservip(uservip);
		personalbankinfor.setBankparameter(bankparameter);
		this.personalbankinforService.createPersonalbankinfor(personalbankinfor);
		return "success";
	}
	
	/**
	 * 删除用户的银行
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deleteCard() throws Exception {
		request = ServletActionContext.getRequest();
		String bankId=request.getParameter("bankId");
		Personalbankinfor personalbankinfor = this.personalbankinforService
				.findPersonalbankinforById(Integer.parseInt(bankId));
		this.personalbankinforService
				.deletePersonalbankinfor(personalbankinfor);
		
		return "success";
	}
	/**
	 * 修改密码
	 * @return
	 * @throws Exception
	 */
	public String updatePwd() throws Exception{
		request = ServletActionContext.getRequest();
		this.sessionId = request.getSession().getId();
		Uservip uservip = (Uservip) ActionContext.getContext().getSession()
				.get("uservip" + sessionId);
		String oldPwd=request.getParameter("oldPwd");
		String newPwd1=request.getParameter("newPwd1");
		String newPwd2=request.getParameter("newPwd2");
		
		uservip=uservipService.findUservipByUserid(uservip.getUserId());
		
		if(uservip.getPassword().equals(MD5Util.md5(oldPwd))){
			if(newPwd1.equals(newPwd2)){
				uservip.setPassword(MD5Util.md5(newPwd1));
			}else{
				this.setMessage("新密码和支付密码不一致", "/wap/user_updatePwd.jsp", "3");
			}
		}else{
			this.setMessage("旧密码不正确", "/wap/user_updatePwd.jsp", "3");
		}
		boolean boo=uservipService.updateUservip(uservip);
		if(boo){
			this.setMessage("密码修改成功", "/wap/user_updatePwd.jsp", "3");
		}else{
			this.setMessage("密码修改失败", "/wap/user_updatePwd.jsp", "3");
		}
		return "success";
	}
	/**
	 * 注册第一步跳转页
	 * @return
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	public String regJump() throws NumberFormatException, Exception{
		request = ServletActionContext.getRequest();
		String userName=request.getParameter("userName");
		String referId=request.getParameter("referId");
		String code=request.getParameter("code");
		
		this.sessionId = request.getSession().getId();
		String code1 = (String)ActionContext.getContext().getSession().get("code1"+this.sessionId);
		if(!code1.equals(code)){
			
			this.setMessage("验证码错误，请重新输入", "/wap/reg.jsp", "3");
			return "error";//验证码错误
		}
		
		if(referId!=null && !"".equals(referId)){
			Uservip uservip=uservipService.findUservipByUserid(Integer.parseInt(referId));
			if(uservip==null){
				this.setMessage("邀请码错误！","/wap/reg.jsp", "3");
				return "error";
			}
		}
		//验证用户名的唯一
		String uservip = this.uservipService.findUservipByUserName(userName.trim());
		if("1".equals(uservip)){
			this.setMessage("用户名已存在！","/wap/reg.jsp", "3");
			return "error";
		}
		
//		PhoneAction p=new PhoneAction();
//		String str=p.sendMessageZC(userName,9);
//		if("1".equals(str)){
//			this.setMessage("手机号不正确！","/wap/reg.jsp", "3");
//			return "error";
//		}else if("2".equals(str)){
//			this.setMessage("手机号已存在","/wap/reg.jsp", "3");
//			return "error";
//		}
		request.setAttribute("userName",userName );
		request.setAttribute("referId",referId );
		return "success";
	}
	/**
	 * 注册完成
	 * @return
	 * @throws Exception
	 */
	public String reg() throws Exception{

		request = ServletActionContext.getRequest();
		this.sessionId = request.getSession().getId();
		String ZCcode = (String) ActionContext.getContext().getSession()
				.get("ZCcode" + this.sessionId);
		String userName=request.getParameter("userName");
		String referId=request.getParameter("referId");
		String password=request.getParameter("password");
		String code=request.getParameter("code");
		String phoneNum=request.getParameter("phoneNum");

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
		
		
		
		request.setAttribute("userName", userName);
		request.setAttribute("referId", referId);
		Moneycount moneycount=new Moneycount();
		String flg = "0";// 手机邮箱判断
		if(phoneNum!=null && !"".equals(phoneNum)){
		if (code != null && !"".equals(code)
				&& (ZCcode != null && !"".equals(ZCcode))) {
			if (!ZCcode.equals(code)) {
				this.setMessage("手机验证码错误", "/wap/reg2.jsp", "3");
				return "error";
			} 
			flg = "1";
		}
		}
		String flag = null;
		flag = this.uservipService.findUservipByUserName(userName);
		if("1".equals(flag)){
			this.setMessage("用户名已存在", "/wap/reg2.jsp", "3");
			return "error";
		}else{
		
		Uservip uservip = new Uservip();
		uservip.setUserName(userName);
		uservip.setPassword(MD5Util.md5(password));
		uservip.setPayPwd(MD5Util.md5(password));
//		uservip.setRegIp(ip);
		
//		if(this.kefuId!=null && !"".equals(this.kefuId)){
//			ManagerService managerService = new ManagerServiceImpl();	
//			Manager man = managerService.findManagerByManagerId(this.kefuId.intValue());
//			uservip.setManager(man);
//		}
		if(phoneNum!=null && !"".equals(phoneNum)){
			uservip.setPhoneResult("1");
		}
		Integralparameter integralparameter222 = this.integralparameterService.findIntegralparameterByintegralPid(1);
		Integer integral = Integer.valueOf(0);
		if (uservip.getItegral() != null)
			integral = Integer.valueOf(uservip.getItegral().intValue()+ Integer.parseInt(integralparameter222.getIntegralPnumber()));
		else {
			integral = Integer.valueOf(Integer.parseInt(integralparameter222.getIntegralPnumber()));
		}
		uservip.setItegral(integral);
		
		if (referId != null && !"".equals(referId) ) {
			Uservip refer = this.uservipService.findUservipByUserid(Integer.parseInt(referId));
			uservip.setUservip(refer);
			if (referId != null) {
				String sql = "select * from Promrewardbypeople p";
				System.out.println(sql);
				List promrewardbypeopleList = this.promrewardbypeopleService
						.findPromrewardbySql(sql);
				Promrewardbypeople pp = new Promrewardbypeople();
				if ((promrewardbypeopleList != null)
						&& (promrewardbypeopleList.size() > 0)) {
					pp = (Promrewardbypeople) promrewardbypeopleList.get(0);

					sql = "select * from bonus b where b.userId="
							+ referId
							+ " and timestampdiff(DAY,DATE_FORMAT(b.bonusChangeTime,'%Y-%m-%d'),DATE_FORMAT(NOW(),'%Y-%m-%d')) >=0"
							+ " and timestampdiff(DAY,DATE_FORMAT(NOW(),'%Y-%m-%d'),DATE_FORMAT(b.bonusChangeTime,'%Y-%m-%d')) >=0";
					List bonusList = this.bonusService.findBonusBySql(sql);
					System.out.println(sql);
					Bonus bonus = new Bonus();
					// 推广奖励
					if ((bonusList != null) && (bonusList.size() > 0)) {
						bonus = (Bonus) bonusList.get(0);
						bonus.setNum(Integer
								.valueOf(bonus.getNum().intValue() + 1));
						bonus.setBonusChangeTime(new Timestamp(new Date()
								.getTime()));
					} else {
						bonus.setNum(Integer.valueOf(1));
						bonus.setUservip(this.uservipService
								.findUservipByUserid(Integer.parseInt(referId)));
						bonus.setStatus("0");
						bonus.setBonusChangeTime(new Timestamp(new Date()
								.getTime()));
						bonus.setBonusExamine("推广奖励");
					}
					for (int i = 0; i < promrewardbypeopleList.size(); i++) {
						pp = (Promrewardbypeople) promrewardbypeopleList.get(i);
						if (bonus.getNum() == pp.getPeopleNum()) {
							String a = Double.toString(pp.getReward()
									.doubleValue());
							bonus.setRquestBonus(Integer.valueOf(Integer
									.parseInt(a.substring(0, a.indexOf(".")))));
						}
					}
					if (bonus.getBonusId() != null) {
						this.bonusService.updateBonus(bonus);
					} else {
						this.bonusService.createBonus(bonus);
					}

				}

			}

		}

		uservip.setRegisterDate(new Timestamp(new Date().getTime()));
		uservip.setCreditlevel(this.creditlevelService
				.findCreditlevelBycreditLevelId(1));
		//注册时是否赠送vip会员
		Systemconf sysconf = this.systemconfService.findSystemconfByParname("con_vip_isGift");
		if("1".equals(sysconf.getParvalue())){
			Systemconf sysconf1 = this.systemconfService.findSystemconfByParname("con_vip_money");
			Calendar calender = Calendar.getInstance();
			calender.setTime(new Date());
			calender.add(Calendar.MONTH, Integer.valueOf(sysconf1.getParvalue()));
			long end=calender.getTimeInMillis();
			Timestamp scurrtest = new Timestamp(end);
			uservip.setIsVIP(Integer.valueOf(sysconf.getParvalue()));
			uservip.setVipMonthe(Integer.valueOf(sysconf1.getParvalue()));
			uservip.setVipEndTime(scurrtest);
		}
		
		Integer userId = this.uservipService.createUservip(uservip);
		//手机认证通过保存积分到积分表
		Uservip uservip3 = this.uservipService.findUservipByUserid(userId);
		if ("1".equals(uservip3.getPhoneResult())) {
			Integralparameter integralparameter = this.integralparameterService.findIntegralparameterByintegralPid(1);
//			Integer integral = Integer.valueOf(0);
//			if (uservip.getItegral() != null)
//				integral = Integer.valueOf(uservip.getItegral().intValue()+ Integer.parseInt(integralparameter.getIntegralPnumber()));
//			else {
//				integral = Integer.valueOf(Integer.parseInt(integralparameter.getIntegralPnumber()));
//			}
	        Integralcategory integralcategory = this.integralcategoryService.findIntegralcategoryByintCategoryId(Integer.valueOf(1));
			Integraldetail integraldetail = new Integraldetail();
			integraldetail.setUservip(uservip3);
			integraldetail.setIntegralcategory(integralcategory);
			integraldetail.setIntegralQuota(Double.valueOf(Integer.valueOf(Integer.parseInt(integralparameter.getIntegralPnumber()))));
			integraldetail.setIntegralTime(new Timestamp(new Date().getTime()));
			integraldetail.setIntegralReason(integralparameter.getIntegralPdescription());
			if(uservip.getInvestItegral()!=null && uservip.getItegral()!=null){
				integraldetail.setRemainIntegral(uservip.getInvestItegral() + uservip.getItegral() + Double.valueOf(Integer.valueOf(Integer.parseInt(integralparameter.getIntegralPnumber()))));
			}else if(uservip.getInvestItegral()==null && uservip.getItegral()!=null){
				integraldetail.setRemainIntegral(Double.valueOf(uservip.getItegral()));
			}else{
				integraldetail.setRemainIntegral(Double.valueOf(Integer.valueOf(Integer.parseInt(integralparameter.getIntegralPnumber()))));
			}
			this.integraldetailService.createIntegraldetail(integraldetail);
		}
		
		if (userId != null) {
			uservip.setUserId(userId);
			//注册活动相关处理
//			List<ActivityConfig> aclist = activityConfigService.findBytype(0, 1);
//			if(aclist!=null && aclist.size()>0){
//				for(int i=0;i<aclist.size();i++){
//					ActivityConfig activityConfig = aclist.get(i);
//					ActivityUses activityUses = new ActivityUses();
//					activityUses.setType(activityConfig.getActype());
//					String source = "";
//					if(activityConfig.getActype()==1){
//						source = "注册红包奖励";
//					}else if(activityConfig.getActype()==2){
//						source = "注册加息券奖励";
//					}else if(activityConfig.getActype()==3){
//						source = "注册抵现券奖励";
//					}
//					activityUses.setSource(source);
//					activityUses.setAcvalue(activityConfig.getAcvalue());
//					activityUses.setAddtime(new Timestamp(System.currentTimeMillis()));
//					
//					Calendar cal = Calendar.getInstance();
//					cal.add(Calendar.DATE, activityConfig.getPeriod());
//					activityUses.setEndtime(new Timestamp(cal.getTimeInMillis()));
//					
//					activityUses.setName(activityConfig.getName());
//					
//					if(activityConfig.getIsauto()==1){
//						activityUses.setStatus(1);
//						activityUses.setDrawtime(new Timestamp(System.currentTimeMillis()));
//					}else{
//						activityUses.setStatus(0);
//					}
//					activityUses.setUservip(uservip);
//					this.activityUsersService.addActivityUses(activityUses);
//				}
//			}
			
			
//			if (referId != null) {
//				List<ActivityConfig> reaclist = activityConfigService.findBytype(0, 3);
//				if(reaclist!=null && reaclist.size()>0){
//					for(int i=0;i<reaclist.size();i++){
//						ActivityConfig activityConfig = reaclist.get(i);
//						ActivityUses activityUses = new ActivityUses();
//						activityUses.setType(activityConfig.getActype());
//						String source = "";
//						if(activityConfig.getActype()==1){
//							source = "邀请注册红包奖励";
//						}else if(activityConfig.getActype()==2){
//							source = "邀请注册加息券奖励";
//						}else if(activityConfig.getActype()==3){
//							source = "邀请注册抵现券奖励";
//						}
//						activityUses.setSource(source);
//						activityUses.setAcvalue(activityConfig.getAcvalue());
//						activityUses.setAddtime(new Timestamp(System.currentTimeMillis()));
//						
//						Calendar cal = Calendar.getInstance();
//						cal.add(Calendar.DATE, activityConfig.getPeriod());
//						activityUses.setEndtime(new Timestamp(cal.getTimeInMillis()));
//						
//						activityUses.setName(activityConfig.getName());
//						
//						if(activityConfig.getIsauto()==1){
//							activityUses.setStatus(1);
//							activityUses.setDrawtime(new Timestamp(System.currentTimeMillis()));
//						}else{
//							activityUses.setStatus(0);
//						}
//						activityUses.setUservip(uservip.getUservip());
//						activityUses.setReuservip(uservip);
//						this.activityUsersService.addActivityUses(activityUses);
//					}
//				}
//			}
			
			ActionContext.getContext().getSession().put("uservip", uservip);
			Moneycount mc = this.moneycountService.findMoneycountByuserId(userId.intValue());
			
			if (mc == null) {
				Moneycount moneycount1 = new Moneycount();
				moneycount1.setUservip(uservip);
				
				// 注册时间比较如果小于则赠送1000体验金额
				Date timedate = new Date();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Systemconf syc = systemconfService.findSystemconfByParname("con_reg_reward_limittime");
				Date date2 = df.parse(syc.getParvalue());
				if (timedate.getTime() <= date2.getTime()) {
					Systemconf sycf = systemconfService.findSystemconfByParname("con_reg_reward");
					double experienceMoney = Double.parseDouble(sycf.getParvalue());
					moneycount1.setExperienceMoney(experienceMoney);
				}
				this.moneycountService.createMoneycount(moneycount1);
				moneycount = this.moneycountService
						.findMoneycountByuserId(userId.intValue());
			} else {
				moneycount = mc;
			}

			Basicinfor basicinfor1 = new Basicinfor();
			basicinfor1.setUservip(uservip);
			basicinfor1.setSex("0");
			// //如果邮箱不是空
			// if("1".equals(flg)){
			// basicinfor1.setPhoneNum(this.moblie);
			// }
			basicinfor1.setPhoneNum(phoneNum);
			basicinfor1.setMarryStatus("0");
			basicinfor1.setHighestEdu("0");
			basicinfor1.setMonthIncome("0");
			basicinfor1.setRelationOne("0");
			basicinfor1.setRelationTwo("0");
			basicinfor1.setRelationThree("0");
			basicinfor1.setWorkYear("0");
			basicinfor1.setHouseCondition("0");
			basicinfor1.setIfByCar("0");
			basicinfor1.setContributionStatus("0");
			basicinfor1.setCosignerOneRelation("0");
			basicinfor1.setCosignerTwoRelation("0");
			basicinfor1.setCount1(Integer.valueOf(0));
			basicinfor1.setCount2(Integer.valueOf(0));
			basicinfor1.setCount3(Integer.valueOf(0));
			basicinfor1.setCount4(Integer.valueOf(0));
			basicinfor1.setCount5(Integer.valueOf(0));
			basicinfor1.setCount6(Integer.valueOf(0));
			this.basicinforService.createBasicinfor(basicinfor1);

			Internallettermodel internallettermodel = this.internallettermodelService
					.findInternallettermodelByinterModelId(1);
			if (internallettermodel.getIsOpen() == 1) {
				Inbox inbox = new Inbox();
				inbox.setUservip(uservip);
				inbox.setContent(internallettermodel.getInterModelContent()
						.replace("'#UserName#'", uservip.getUserName()));
				inbox.setReceiveTime(new Timestamp(new Date().getTime()));
				Website website = this.websiteService.findWebsiteBywebsiteId(1);
				inbox.setSendName(website.getWebName());
				inbox.setTitle("注册成功");
				inbox.setStatus(Integer.valueOf(0));
				this.inboxService.createInbox(inbox);
			}

		}
		ActionContext.getContext().getSession().put("uservip" + sessionId, uservip);
		}

		
		return "success";
	}
	
	public String quit(){
		request = ServletActionContext.getRequest();
		this.sessionId = request.getSession().getId();
		Uservip uservip = new Uservip();
		request.getSession().removeAttribute("uservip" + sessionId);
		return "success";
	}
	/**
	 * 开通托管账号
	 * 
	 * @return
	 * @throws Exception
	 */
	public String registerbind() throws Exception {

		HttpServletRequest request = ServletActionContext.getRequest();
		request = ServletActionContext.getRequest();
		this.sessionId = request.getSession().getId();
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";
		// 判断是否符合条件(实名认证、手机认证)
		Uservip uservip = (Uservip) ActionContext.getContext().getSession()
				.get("uservip"+sessionId);
		uservip = uservipService.findUservipByUserid(uservip.getUserId());
		if (uservip.getNameResult() == null
				|| !uservip.getNameResult().equals("2")) {
			this.setMessage("实名认证之后再开通托管账号", "/wap/searchBasic", "3");
			return "message";
		}
		if (uservip.getPhoneResult() == null
				|| !uservip.getPhoneResult().equals("1")) {
			this.setMessage("手机认证之后再开通托管账号", "/wap/searchBasic", "3");
			return "message";
		}
		if (uservip.getEnable() == null || uservip.getEnable().intValue() != 1) {
			this.setMessage("邮箱认证之后再开通托管账号", "/wap/searchBasic", "3");
			return "message";
		}

		CertificationService certificationService = new CertificationServiceImpl();
		Certification certification = certificationService
				.findCertificationByUserId(uservip.getUserId().intValue());
		this.RealName = certification.getRealName();
		this.IdentificationNo = certification.getIdNum();

		Website website = this.websiteService.findWebsiteBywebsiteId(1);

		BasicinforService basicinforService = new BasicinforServiceImpl();
		Basicinfor basicinfor = basicinforService
				.findBasicinforByUserId(uservip.getUserId().intValue());
		this.Mobile = basicinfor.getPhoneNum();
		this.RegisterType = "2";
		this.AccountType = "";
		this.Email = uservip.getMail();
		this.LoanPlatformAccount = uservip.getUserName();
		this.PlatformMoneymoremore = website.getTrustAccount();

		this.Remark1 =  uservip.getUserName() + "开通托管账号";
		this.Remark2 = "wap";
		this.Remark3 = uservip.getUserId().toString();

		try {
			
			if(istest){
				this.SubmitURL = "http://218.4.234.150:88/main/loan/toloanregisterbind.action";
			}else{
				this.SubmitURL = "https://register.moneymoremore.com/loan/toloanregisterbind.action";
			}
//			SubmitURL = SubmitURLPrefix + "loan/toloanregisterbind.action";
			ReturnURL = basePath + "wap/registerbindreturn";
			NotifyURL = basePath + "wap/registerbindnotify";

			String privatekey = website.getPrivateKey();

			if (antistate == 1) {
				Date d = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
				RandomTimeStamp = Common.getRandomNum(2) + sdf.format(d);
			}

			String dataStr = RegisterType + AccountType + Mobile + Email
					+ RealName + IdentificationNo + Image1 + Image2
					+ LoanPlatformAccount + PlatformMoneymoremore
					+ RandomTimeStamp + Remark1 + Remark2 + Remark3 + ReturnURL
					+ NotifyURL;
			// 签名
			MD5Trust md5 = new MD5Trust();
			RsaHelper rsa = RsaHelper.getInstance();
			if (antistate == 1) {
				dataStr = md5.getMD5Info(dataStr);
			}
			SignInfo = rsa.signData(dataStr, privatekey);

			if (this.RegisterType.equals("1")) {
				Map<String, String> req = new TreeMap<String, String>();
				req.put("RegisterType", RegisterType);
				req.put("AccountType", AccountType);
				req.put("Mobile", Mobile);
				req.put("Email", Email);
				req.put("RealName", RealName);
				req.put("IdentificationNo", IdentificationNo);
				req.put("Image1", Image1);
				req.put("Image2", Image2);
				req.put("LoanPlatformAccount", LoanPlatformAccount);
				req.put("PlatformMoneymoremore", PlatformMoneymoremore);
				req.put("RandomTimeStamp", RandomTimeStamp);
				req.put("Remark1", Remark1);
				req.put("Remark2", Remark2);
				req.put("Remark3", Remark3);
				req.put("ReturnURL", ReturnURL);
				req.put("NotifyURL", NotifyURL);
				req.put("SignInfo", SignInfo);

				String[] resultarr = HttpClientUtil.doPostQueryCmd(SubmitURL,
						req);
				System.out.println(resultarr[1]);

				if (StringUtils.isNotBlank(resultarr[1])
						&& resultarr[1].startsWith("{")) {
					LoanRegisterBindReturnBean lrbrb = (LoanRegisterBindReturnBean) Common
							.JSONDecode(resultarr[1],
									LoanRegisterBindReturnBean.class);
					if (lrbrb != null) {
						String publickey = website.getPublicKey();

						dataStr = lrbrb.getAccountType()
								+ lrbrb.getAccountNumber() + lrbrb.getMobile()
								+ lrbrb.getEmail() + lrbrb.getRealName()
								+ lrbrb.getIdentificationNo()
								+ lrbrb.getLoanPlatformAccount()
								+ lrbrb.getMoneymoremoreId()
								+ lrbrb.getPlatformMoneymoremore()
								+ lrbrb.getAuthFee() + lrbrb.getAuthState()
								+ lrbrb.getRandomTimeStamp()
								+ lrbrb.getRemark1() + lrbrb.getRemark2()
								+ lrbrb.getRemark3() + lrbrb.getResultCode();
						if (antistate == 1) {
							dataStr = md5.getMD5Info(dataStr);
						}
						// System.out.println(dataStr);廥
						// 签名
						boolean verifySignature = rsa.verifySignature(
								lrbrb.getSignInfo(), dataStr, publickey);
						this.verifySignature = Boolean
								.toString(verifySignature);
						if (verifySignature && this.ResultCode.equals("88")) {// 处理逻辑业务
							int userid = Integer.valueOf(this.Remark3)
									.intValue();
							uservip = uservipService
									.findUservipByUserid(userid);
							uservip.setTrustStatus(1);
							uservip.setTrustAccount(this.MoneymoremoreId);
							uservipService.updateUservip(uservip);
						}

						System.out.println(this.verifySignature);
					}
				}
				return "message";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";

	}

	/**
	 * 接收开户页面返回信息
	 * 
	 * @return
	 */
	public String registerbindreturn() {
		try {
			Website website = this.websiteService.findWebsiteBywebsiteId(1);
			HttpServletRequest request = ServletActionContext.getRequest();
			request = ServletActionContext.getRequest();
			this.sessionId = request.getSession().getId();
			request.setCharacterEncoding("UTF-8");

			String publickey = website.getPublicKey();

			MD5Trust md5 = new MD5Trust();
			RsaHelper rsa = RsaHelper.getInstance();
			String dataStr = AccountType + AccountNumber + Mobile + Email
					+ RealName + IdentificationNo + LoanPlatformAccount
					+ MoneymoremoreId + PlatformMoneymoremore + AuthFee
					+ AuthState + RandomTimeStamp + Remark1 + Remark2 + Remark3
					+ ResultCode;
			if (antistate == 1) {
				dataStr = md5.getMD5Info(dataStr);
			}

			// 签名
			boolean verifySignature = rsa.verifySignature(SignInfo, dataStr,
					publickey);
			if (verifySignature && this.ResultCode.equals("88")) {// 处理逻辑业务
				int userid = Integer.valueOf(this.Remark3).intValue();
				Uservip uservip = uservipService.findUservipByUserid(userid);
				uservip.setTrustStatus(1);
				uservip.setTrustAccount(this.MoneymoremoreId);
				uservipService.updateUservip(uservip);
			}

			this.verifySignature = Boolean.toString(verifySignature);
			System.out.println("页面返回:" + this.verifySignature);
			System.out.println("返回码:" + ResultCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setMessage(this.Message, "/wap/index.action", "3");
		return "success";
	}

	/**
	 * 接收开户后台通知信息
	 * 
	 * @return
	 */
	public void registerbindnotify() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");
			request = ServletActionContext.getRequest();
			this.sessionId = request.getSession().getId();
			Website website = this.websiteService.findWebsiteBywebsiteId(1);
			String publickey = website.getPublicKey();

			MD5Trust md5 = new MD5Trust();
			RsaHelper rsa = RsaHelper.getInstance();
			String dataStr = AccountType + AccountNumber + Mobile + Email
					+ RealName + IdentificationNo + LoanPlatformAccount
					+ MoneymoremoreId + PlatformMoneymoremore + AuthFee
					+ AuthState + RandomTimeStamp + Remark1 + Remark2 + Remark3
					+ ResultCode;
			if (antistate == 1) {
				dataStr = md5.getMD5Info(dataStr);
			}

			// 签名
			boolean verifySignature = rsa.verifySignature(SignInfo, dataStr,
					publickey);
			System.out.println("后台通知:" + verifySignature);
			System.out.println("返回码:" + ResultCode);
			System.out.println("返回次数:" + ReturnTimes);

			if (verifySignature && this.ResultCode.equals("88")) {// 处理逻辑业务
				int userid = Integer.valueOf(this.Remark3).intValue();
				Uservip uservip = uservipService.findUservipByUserid(userid);
				uservip.setTrustStatus(1);
				uservip.setTrustAccount(this.MoneymoremoreId);
				uservipService.updateUservip(uservip);
			}

			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			response.getWriter().write("SUCCESS");
			response.getWriter().flush();
			response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 授权接口处理
	 * 
	 * @return
	 * @throws Exception
	 */
	public String loanAuthorize() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		request = ServletActionContext.getRequest();
		this.sessionId = request.getSession().getId();
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";
		// 判断是否符合条件(实名认证、手机认证)
		Uservip uservip = (Uservip) ActionContext.getContext().getSession()
				.get("uservip"+sessionId);
		uservip = uservipService.findUservipByUserid(uservip.getUserId());
		
		if (uservip.getTrustStatus()==0 || uservip.getTrustAccount()==null || uservip.getTrustAccount().equals("")) {
			this.setMessage("开通托管账号后才能授权", "/wap/index.action", "3");
			return "message";
		}
		try {
			
			if(istest){
				this.SubmitURL = "http://218.4.234.150:88/main/loan/toloanauthorize.action";
			}else{
				this.SubmitURL = "https://auth.moneymoremore.com/loan/toloanauthorize.action";
			}
			
//			SubmitURL = SubmitURLPrefix + "loan/toloanauthorize.action";
			ReturnURL = basePath + "wap/loanauthorizereturn.action";
			NotifyURL = basePath + "wap/loanauthorizenotify.action";
			Website website = this.websiteService.findWebsiteBywebsiteId(1);
			this.PlatformMoneymoremore = website.getTrustAccount();

			this.MoneymoremoreId = uservip.getTrustAccount();
			this.AuthorizeTypeOpen = "1,2,3";
			this.AuthorizeTypeClose = "";

			this.Remark1 = uservip.getUserName() + "授权操作";
			this.Remark3 = uservip.getUserId().toString();
			 this.Remark2 ="wap";
			String privatekey = website.getPrivateKey();
			if (antistate == 1) {
				Date d = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
				RandomTimeStamp = Common.getRandomNum(2) + sdf.format(d);
			}

			String dataStr = MoneymoremoreId + PlatformMoneymoremore
					+ AuthorizeTypeOpen + AuthorizeTypeClose + RandomTimeStamp
					+ Remark1 + Remark2 + Remark3 + ReturnURL + NotifyURL;
			// 签名
			MD5Trust md5 = new MD5Trust();
			RsaHelper rsa = RsaHelper.getInstance();
			if (antistate == 1) {
				dataStr = md5.getMD5Info(dataStr);
			}
			SignInfo = rsa.signData(dataStr, privatekey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * 接收授权页面返回信息
	 * 
	 * @return
	 */
	public String loanauthorizereturn() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");
			Website website = this.websiteService.findWebsiteBywebsiteId(1);
			String publickey = website.getPublicKey();

			MD5Trust md5 = new MD5Trust();
			RsaHelper rsa = RsaHelper.getInstance();
			String dataStr = MoneymoremoreId + PlatformMoneymoremore
					+ AuthorizeTypeOpen + AuthorizeTypeClose + AuthorizeType
					+ RandomTimeStamp + Remark1 + Remark2 + Remark3
					+ ResultCode;
			if (antistate == 1) {
				dataStr = md5.getMD5Info(dataStr);
			}

			// 签名
			boolean verifySignature = rsa.verifySignature(SignInfo, dataStr,
					publickey);
			this.verifySignature = Boolean.toString(verifySignature);
			if (verifySignature && this.ResultCode.equals("88")) {// 处理逻辑业务
				int userid = Integer.valueOf(this.Remark3).intValue();
				Uservip uservip = uservipService.findUservipByUserid(userid);
				uservip.setAuthorizeStatus(1);
				uservipService.updateUservip(uservip);
			}
			System.out.println("页面返回:" + this.verifySignature);
			System.out.println("返回码:" + ResultCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setMessage(this.Message, "/wap/index.action", "3");
		return "success";
	}

	/**
	 * 接收授权后台通知信息
	 * 
	 * @return
	 */
	public void loanauthorizenotify() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");
			Website website = this.websiteService.findWebsiteBywebsiteId(1);
			String publickey = website.getPublicKey();

			MD5Trust md5 = new MD5Trust();
			RsaHelper rsa = RsaHelper.getInstance();
			String dataStr = MoneymoremoreId + PlatformMoneymoremore
					+ AuthorizeTypeOpen + AuthorizeTypeClose + AuthorizeType
					+ RandomTimeStamp + Remark1 + Remark2 + Remark3
					+ ResultCode;
			if (antistate == 1) {
				dataStr = md5.getMD5Info(dataStr);
			}

			// 签名
			boolean verifySignature = rsa.verifySignature(SignInfo, dataStr,
					publickey);
			System.out.println("后台通知:" + verifySignature);
			System.out.println("返回码:" + ResultCode);
			System.out.println("返回次数:" + ReturnTimes);
			if (verifySignature && this.ResultCode.equals("88")) {//处理逻辑业务
				int userid = Integer.valueOf(this.Remark3).intValue();
				Uservip uservip = uservipService.findUservipByUserid(userid);
				uservip.setAuthorizeStatus(1);
				uservipService.updateUservip(uservip);
			}
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			response.getWriter().write("SUCCESS");
			response.getWriter().flush();
			response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 提现
	 * @return
	 * @throws Exception
	 */
	public String createtranslate() throws Exception
	  {
		this.uservip=uservipService.findUservipByUserid(this.userId);
		if(this.uservip==null){
			this.setMessage("用户不正确，请重新登录","/wap/totixian", "3");   
			return "jump";
		}
	    String keys = (String)ActionContext.getContext().getSession().get("key" + uservip.getUserId());
	    Uservip uservip1 = this.uservipService.findUservipByPayPwdAndUserName(MD5Util.md5(this.payPwd), uservip.getUserName());
	    Moneycount moneycout=moneycountService.findMoneycountByuserId(this.userId);
	    if (uservip1 == null)
	    {
	    	this.setMessage("用户支付密码不正确",
					"/wap/totixian", "3");
			return "jump";
	    }
	    else if (!keys.equals(this.key)) {
	    	this.setMessage("验证码不正确",
					"/wap/totixian", "3");
			return "jump";
	    }
	    else if( moneycout.getAvailableMoney()< Double.valueOf(this.affectMoney)){
	    	this.setMessage("提现金额大于可用余额",
					"/wap/totixian", "3");
			return "jump";
	    	//this.result = "lowest";//提现金额不能低于最低提现金额
	    }
	    else {
	      Personalbankinfor personalbankinfor = this.personalbankinforService.findPersonalbankinforById(this.bankPid);
	      Moneycount moneycount = this.moneycountService.findMoneycountByuserId(this.uservip.getUserId().intValue());

	      Translate translate = new Translate();
	      translate.setTranslateId(Integer.valueOf(1));
	      translate.setUservip(this.uservip);
	      translate.setAffectMoney(this.affectMoney);
	      translate.setBankNum(personalbankinfor.getBankparameter().getBankPname()+","+personalbankinfor.getAccountAddress()+","+personalbankinfor.getAccountNum());
	      translate.setOccurTime(this.timestamp);
	      translate.setAvailableBalance(Double.valueOf(moneycount.getAvailableMoney().doubleValue() - Double.valueOf(this.affectMoney).doubleValue()));
	      translate.setState(Integer.valueOf(0));
		  translate.setBanktypeId(personalbankinfor.getBankparameter().getBankPid());
		  translate.setBankCityId(personalbankinfor.getArea().getAreaId());
		  translate.setBankProvinceId(personalbankinfor.getArea().getParentid());
		  translate.setSerialnum("");	
	   
	      boolean flag = this.translateService.addTranslate(translate);
	      if (flag)
	      {
	    	LoanUtils loanUtils = new LoanUtils();
			HttpServletRequest request = ServletActionContext.getRequest();
			Website website = this.websiteService.findWebsiteBywebsiteId(1);
			Uservip cashuser = this.uservip;
			cashuser.setUserNames("wap");
		    this.postform =	loanUtils.loanWithdraws(request, website, translate, cashuser);
			return "loanjump";
	       
	      }else {
	    	  this.setMessage("提现失败","/wap/totixian", "3");
	  		return "jump";
	      }
	    }
	   
	  }
	
}
