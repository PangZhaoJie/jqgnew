package com.jqg.app;

import java.security.MessageDigest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.jqg.app.session.MySessionContext;
import com.jqg.pojo.Basicinfor;
import com.jqg.pojo.Certification;
import com.jqg.pojo.Creditlevel;
import com.jqg.pojo.EmailSendLog;
import com.jqg.pojo.Emailmodel;
import com.jqg.pojo.Inbox;
import com.jqg.pojo.Integralcategory;
import com.jqg.pojo.Integraldetail;
import com.jqg.pojo.Integralparameter;
import com.jqg.pojo.Messagemodel;
import com.jqg.pojo.Moneycount;
import com.jqg.pojo.Moneyhistorydetail;
import com.jqg.pojo.Phone;
import com.jqg.pojo.SmsSendLog;
import com.jqg.pojo.Smtp;
import com.jqg.pojo.Systemconf;
import com.jqg.pojo.Uservip;
import com.jqg.pojo.Website;
import com.jqg.service.BasicinforService;
import com.jqg.service.CertificationService;
import com.jqg.service.CreditlevelService;
import com.jqg.service.EmailSendLogService;
import com.jqg.service.EmailmodelService;
import com.jqg.service.InboxService;
import com.jqg.service.IntegralcategoryService;
import com.jqg.service.IntegraldetailService;
import com.jqg.service.IntegralparameterService;
import com.jqg.service.InternallettermodelService;
import com.jqg.service.MessagemodelService;
import com.jqg.service.MoneycountService;
import com.jqg.service.MoneyhistorydetailService;
import com.jqg.service.PhoneService;
import com.jqg.service.SmsSendLogService;
import com.jqg.service.SmtpService;
import com.jqg.service.SystemconfService;
import com.jqg.service.UservipService;
import com.jqg.service.WebsiteService;
import com.jqg.service.impl.BasicinforServiceImpl;
import com.jqg.service.impl.CertificationServiceImpl;
import com.jqg.service.impl.CreditlevelServiceImpl;
import com.jqg.service.impl.EmailSendLogServiceImpl;
import com.jqg.service.impl.EmailmodelServiceImpl;
import com.jqg.service.impl.InboxServiceImpl;
import com.jqg.service.impl.IntegralcategoryServiceImpl;
import com.jqg.service.impl.IntegraldetailServiceImpl;
import com.jqg.service.impl.IntegralparameterServiceImpl;
import com.jqg.service.impl.InternallettermodelServiceImpl;
import com.jqg.service.impl.MessagemodelServiceImpl;
import com.jqg.service.impl.MoneycountServiceImpl;
import com.jqg.service.impl.MoneyhistorydetailServiceImpl;
import com.jqg.service.impl.PhoneServiceImpl;
import com.jqg.service.impl.SmsSendLogServiceImpl;
import com.jqg.service.impl.SmtpServiceImpl;
import com.jqg.service.impl.SystemconfServiceImpl;
import com.jqg.service.impl.UservipServiceImpl;
import com.jqg.service.impl.WebsiteServiceImpl;
import com.jqg.util.Client;
import com.jqg.util.MailSenderInfo;
import com.jqg.util.SendMail;
import com.jqg.util.SerialNumberUtil;
import com.opensymphony.xwork2.ActionContext;
import com.ruanwei.interfacej.SmsClientSend;

/**
 * 实名认证
 * @author 徐斌
 *
 */
public class AppCertificationAction extends AppBaseAction{
	//身份证名称
	private String realName;
	//身份证号码
	private String idNum;
	//邮箱地址 0:失败 1：重复 2：成功
	private String mail;
	//验证所需手机号
	private String mobiles;
	//手机验证所需短信验证码
	private String code;
	//发送状态
	private String state;
	//返回信息 
	private String message;
	//返回结果
	private String result;
	//获取uservip
	private String sessionId;
	// 转出资金需要的短信模版ID
	private static final Integer MESSAGEMODELID = 9;
	// 声明request
	private HttpServletRequest request;
	// 声明response
	private HttpServletResponse response;
	//验证需要的正则表达式
	private Pattern p;
 
	private Matcher m;
	
	private BasicinforService basicinforService = new BasicinforServiceImpl();
	
	private CertificationService certificationService = new CertificationServiceImpl();
	
	private UservipService uservipService = new UservipServiceImpl();
	
	private EmailmodelService emailmodelService = new EmailmodelServiceImpl();
	
	private SmtpService smtpService = new SmtpServiceImpl();
	
	private IntegralparameterService integralparameterService = new IntegralparameterServiceImpl();
	
	private WebsiteService websiteService = new WebsiteServiceImpl();
	
	private InboxService inboxService = new InboxServiceImpl();
	
	private InternallettermodelService interService = new InternallettermodelServiceImpl();
	
	private IntegralcategoryService integralcategoryService = new IntegralcategoryServiceImpl();
	
	private IntegraldetailService integraldetailService = new IntegraldetailServiceImpl();
	
	private CreditlevelService creditlevelService = new CreditlevelServiceImpl();
	
	private PhoneService phoneService = new PhoneServiceImpl();
	
	private MessagemodelService messagemodelService = new MessagemodelServiceImpl();
	
	private Uservip user;
	
	private String uservip;
	
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getMobiles() {
		return mobiles;
	}

	public void setMobiles(String mobiles) {
		this.mobiles = mobiles;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	public String getUservip() {
		return uservip;
	}

	public void setUservip(String uservip) {
		this.uservip = uservip;
	}


	/**
	 * 实名认证身份证
	 * @throws Exception
	 */
	public void IDCardCertification() throws Exception{


			this.response = ServletActionContext.getResponse();
			//正则表达式验证身份证号
//			String reg = "^(\\d{6})(\\d{4})(\\d{2})(\\d{2})(\\d{3})([0-9]|xX)$";
			p = Pattern.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{4}$");
			m = p.matcher(this.idNum);
			Uservip uservip = getUservip1();
			Basicinfor bas = this.basicinforService.findBasicinforByUserId(uservip.getUserId());
			System.out.println("//n身份证号是："+this.idNum);
			Basicinfor bf = this.basicinforService.findBasicinforByIdNum(this.idNum);
			System.out.println("身份证姓名是："+this.realName+"//nbf是："+bf);
			if(bf!=null&&m.matches()){
					//身份证号已经被实名认证
					this.state="1";
					this.message = "身份证号已经被实名认证！！";

			}else if(bas.getIdNum()!=null && !"".equals(bas.getIdNum()) && ("2".equals(uservip.getNameResult()) || "1".equals(uservip.getNameResult()))){
					this.state="0";
					this.message = "验证失败！！";
			}else {	
					Certification certification = this.certificationService
						.findCertificationByUserId(uservip.getUserId().intValue());
					System.out.println(uservip);
					boolean con= certification==null; 
					System.out.println(realName);
					System.out.println(con);
					certification=con?new Certification():certification;
					try{
					 certification.setIdNum(this.idNum);
					 certification.setRealName(this.realName);
					 certification.setUpLoadTime(new Timestamp(new Date().getTime()));
					 certification.setExamineStatus(Integer.valueOf(0));
					 certification.setUservip(uservip);
					}catch(Exception e){
						e.printStackTrace();
					}
					

					this.certificationService.updateCertification(certification);
					Uservip uservip1 = this.uservipService.findUservipByUserid(uservip
						.getUserId().intValue());
					uservip1.setNameResult("1");
					this.uservipService.updateUservip(uservip1);
					this.user = this.uservipService.findUservipByUserid(uservip.getUserId()
						.intValue());
					ActionContext.getContext().getSession().put("uservip", this.user);
					this.state="2";
					this.message = "验证成功！！";

					
			}
		
				this.result="{\"state\":\"" + this.state + "\","+"\"message\":\"" + this.message + "\"}";
				System.out.println(this.result);
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().write(this.result);
				
	
	}
	/**
	 * 实名认证邮箱
	 * @throws Exception
	 */
	public void mailCertification()throws Exception{
		this.request = ServletActionContext.getRequest();
		this.response = ServletActionContext.getResponse();
		//正则表达式验证邮箱地址
		p = Pattern.compile("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$");
		m = p.matcher(this.mail);
		Smtp smtp = this.smtpService.findSmtpBysmtpid(1);
		Emailmodel emailmodel = this.emailmodelService.findEmailmodelByemailModelId(1);
		Uservip uservip = getUservip1();
		String flag = null;
		flag = this.uservipService.findUservipByMail(this.mail);
		String states="";
		if(uservip.getMail()!=null && !"".equals(uservip.getMail()) && uservip.getEnable()!=null){
			if(uservip.getEnable()==1){
				states="1";
			}
		}
		if("1".equals(flag)&&m.matches()){
			this.state="1";
			this.message = "此邮箱已被认证过。";
		}else if("1".equals(states)&&m.matches()){
				this.state="0";
				this.message = "邮箱已认证，不能重复认证。";
		}else if(m.matches()){
		if (emailmodel.getIsOpen() == 1) {
			MailSenderInfo mailInfo = new MailSenderInfo();
			mailInfo.setMailServerHost(smtp.getSmtpsever());
			mailInfo.setMailServerPort("25");
			mailInfo.setValidate(true);

			mailInfo.setUserName(smtp.getSmtpemail());

			mailInfo.setPassword(smtp.getSmtppassword());

			mailInfo.setFromAddress(smtp.getSmtpemail());

			mailInfo.setToAddress(this.mail);
			mailInfo.setSubject("邮箱激活");

			String code = "" + System.currentTimeMillis();
			String str = emailmodel.getEmailModelContent();
			str = str.replace("'#UserName#'", uservip.getUserName());
			StringBuffer sb = new StringBuffer();
			String path = request.getContextPath();
			if (path != null && !"".equals(path)) {
				path = path + "/";
			}else{
				path = "/";
			}
			String serverport = request.getServerPort()==80 ? "":":"+request.getServerPort();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + serverport
					+ path;
			StringBuffer sb2 = new StringBuffer(); 
			sb2.append(MD5(""+uservip.getUserId())).append("-"); 
			sb2.append(MD5(this.mail)).append("-");
			sb2.append(MD5(uservip.getUserName()));
			Uservip uservips = this.uservipService.findUservipByUserid(uservip.getUserId().intValue());
			uservips.setMail(this.mail);
			this.uservipService.updateUservip(uservips);
			SerialNumberUtil snu=new SerialNumberUtil();
			sb.append(basePath + "user/activate?mail="+uservip.getUserId()+"//---"+sb2.toString());
			String address="点击下面链接激活账号,请尽快激活！ <a href=\""+sb.toString()+"\" >"+sb.toString()+"</a>";
			str = str.replace("#LINK#", address);
			System.out.println("App邮箱验证str字符串是："+str);
			mailInfo.setContent(str);
			System.err.println("~~~~~~~~~~~~~~~smtp开始~~~~~~~~~~~~~~~~~~~~~");
			System.err.println(smtp.getSmtpemail());
			System.err.println(smtp.getSmtppassword());
			System.err.println(smtp.getSmtpemail());
			System.err.println("~~~~~~~~~~~~~~~smtp结束~~~~~~~~~~~~~~~~~~~~~");
			System.err.println("~~~~~~~~~~~~~~~mailInfo开始~~~~~~~~~~~~~~~~~~~~~");
			System.err.println(mailInfo.getUserName());
			System.err.println(mailInfo.getPassword());
			System.err.println(mailInfo.getFromAddress());
			System.err.println("~~~~~~~~~~~~~~~mailInfo结束~~~~~~~~~~~~~~~~~~~~~");
			SendMail sms = new SendMail();

			boolean row = sms.sendTextMail(mailInfo);
			
			EmailSendLog esl = new EmailSendLog();
			esl.setContent(str);
			esl.setFromemail(mailInfo.getFromAddress());
			esl.setToemail(mailInfo.getToAddress());
			esl.setSendTime(new Timestamp(new Date().getTime()));
			esl.setTitle(mailInfo.getSubject());
			esl.setUservip(uservip);
			if (!row){
				esl.setStatus(2);
				System.out.println("邮箱发送失败");
			}else {
				esl.setStatus(1);
				System.out.println("成功");
			}
			uservip.setMail(this.mail);
			uservipService.updateUservip(uservip);
			EmailSendLogService eslService = new EmailSendLogServiceImpl();
			eslService.createEmailSendLog(esl);
			this.state = "2";
			this.message = "邮件已发送，请注意查收。";
			}
			}else{
			this.state = "0";
			this.message = "邮箱地址不正确";
			}
			this.result="{\"state\":\"" + this.state + "\","+"\"message\":\"" + this.message + "\"}";
			System.out.println(this.result);
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(this.result);
	}
	/**
	 * 手机认证
	 */
	public void phoneSet() throws Exception {
		Uservip uservip1 = getUservip1();
		this.response = ServletActionContext.getResponse();
		MySessionContext myc = MySessionContext.getInstance();
		String pcode = (String)myc.getSession(this.sessionId).getAttribute(
				"code" + uservip1.getUserId());
//		String pcode = (String) ActionContext.getContext().getSession().get("code" + uservip1.getUserId());
		System.out.println("pcode"+pcode+uservip1.getUserId());
		if (pcode.trim().equals(this.code.trim())) {
			Uservip user1 = this.uservipService.findUservipByUserid(uservip1.getUserId().intValue());
			if("1".equals(user1.getPhoneResult())){
				this.state = "2";
				this.message = "手机已认证";
			}
			
			user1.setPhoneResult("1");
			Basicinfor basic = this.basicinforService
					.findBasicinforByUserId(uservip1.getUserId().intValue());
			basic.setPhoneNum(this.mobiles);
			this.basicinforService.updateBasicinfor(basic);

			Inbox inbox = new Inbox();
			inbox.setUservip(user1);
			inbox.setContent("恭喜你，手机认证成功！");
			inbox.setReceiveTime(new Timestamp(new Date().getTime()));
			Website website = this.websiteService.findWebsiteBywebsiteId(1);
			inbox.setSendName(website.getWebName());
			inbox.setTitle("手机认证成功");
			inbox.setStatus(Integer.valueOf(0));
			this.inboxService.createInbox(inbox);

			Integralparameter integralparameter = this.integralparameterService.findIntegralparameterByintegralPid(1);
			//手机认证通过保存积分到积分表
	        Integralcategory integralcategory = this.integralcategoryService.findIntegralcategoryByintCategoryId(Integer.valueOf(1));
			Integraldetail integraldetail = new Integraldetail();
			integraldetail.setUservip(uservip1);
			integraldetail.setIntegralcategory(integralcategory);
			integraldetail.setIntegralQuota(Double.valueOf(Integer.valueOf(Integer.parseInt(integralparameter.getIntegralPnumber()))));
			integraldetail.setIntegralTime(new Timestamp(new Date().getTime()));
			integraldetail.setIntegralReason(integralparameter.getIntegralPdescription());
			if(uservip1.getInvestItegral()!=null && uservip1.getItegral()!=null){
				integraldetail.setRemainIntegral(uservip1.getInvestItegral() + uservip1.getItegral() + Double.valueOf(Integer.valueOf(Integer.parseInt(integralparameter.getIntegralPnumber()))));
			}else if(uservip1.getInvestItegral()==null && uservip1.getItegral()!=null){
				integraldetail.setRemainIntegral(Double.valueOf(uservip1.getItegral() + Double.valueOf(Integer.valueOf(Integer.parseInt(integralparameter.getIntegralPnumber())))));
			}else if(uservip1.getInvestItegral()!=null && uservip1.getItegral()==null){
				integraldetail.setRemainIntegral(uservip1.getInvestItegral() + Double.valueOf(Integer.valueOf(Integer.parseInt(integralparameter.getIntegralPnumber()))));
			}else{
				integraldetail.setRemainIntegral(Double.valueOf(Integer.valueOf(Integer.parseInt(integralparameter.getIntegralPnumber()))));
			}
			this.integraldetailService.createIntegraldetail(integraldetail);
			
			Integer integral = Integer.valueOf(0);
			if (user1.getItegral() != null)
				integral = Integer.valueOf(user1.getItegral().intValue()
						+ Integer.parseInt(integralparameter
								.getIntegralPnumber()));
			else {
				integral = Integer.valueOf(Integer.parseInt(integralparameter
						.getIntegralPnumber()));
			}
			user1.setItegral(integral);
			List creditlevels = this.creditlevelService.findCreditlevels();
			Creditlevel creditlevel = null;
			for (int i = 0; i < creditlevels.size(); i++) {
				Creditlevel creditlevel1 = (Creditlevel) creditlevels.get(i);
				int start = Integer
						.parseInt(creditlevel1.getCreditLevelStart());
				int end = Integer.parseInt(creditlevel1.getCreditLevelEnd());
				if ((integral.intValue() >= start)
						&& (integral.intValue() <= end)) {
					creditlevel = creditlevel1;
					break;
				}
			}
			user1.setCreditlevel(creditlevel);
			this.uservipService.updateUservip(user1);
			this.authentic_reward(uservip1);
			this.user = user1;
			ActionContext.getContext().getSession().put("uservip", user1);
			this.state = "1";
			this.message = "认证成功";
		} else {
			this.state = "0";
			this.message = "认证失败";
		}
		System.out.println("手机认证result结果是："+this.result);
		this.result="{\"state\":\"" + this.state + "\","+"\"message\":\"" + this.message + "\"}";
		System.out.println(this.result);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(this.result);
		
	}
	/**
	 * 发送短信验证码
	 * 
	 * @return
	 * @throws Exception
	 */
	public void phoneMessage() throws Exception {
		this.request = ServletActionContext.getRequest();
		this.response = ServletActionContext.getResponse();
		Uservip uservip1 = getUservip1();
		System.out.println("userid是：" + uservip1.getUserId());
		Basicinfor basicinfor = this.basicinforService
				.findBasicinforByUserId(uservip1.getUserId().intValue());
		System.out.println("BasicInforId是：" + basicinfor.getBasicInforId());
		Phone phone = this.phoneService.findPhoneByOpen();
		System.out.println("phone是：" + phone.getPhoneId());
		Messagemodel messagemodel = this.messagemodelService
				.findMessagemodelByintegralPid(MESSAGEMODELID.intValue());
		String contents = messagemodel.getMessModelContent();
		System.out.println(contents);
		if (contents.indexOf("#USERNAME#") != -1) {
			contents = contents
					.replaceAll("#USERNAME#", uservip1.getUserName());
		}
		if (contents.indexOf("#CODE#") != -1) {
			String code = "";
			while (code.length() < 6) {
				code = code + (int) (Math.random() * 10.0D);
			}
			contents = contents.replaceAll("#CODE#", code);
			MySessionContext myc = MySessionContext.getInstance();
			myc.getSession(this.sessionId).setAttribute(
					"code" + uservip1.getUserId(), code);
		}
		boolean key = false;
//		this.mobiles = basicinfor.getPhoneNum();
		if (phone.getPhoneId().intValue() == 1) {

			Client client = new Client(phone.getPhoneName(),
					phone.getPhonePassword());
			String result_mt = client.mdSmsSend_u(this.mobiles, contents, "",
					"", "");
			System.out.println("这个mobiles是：" + this.mobiles);
			if ((result_mt.startsWith("-")) || ("".equals(result_mt))) {
				System.out.print("发送失败！返回值为：" + result_mt
						+ "请查看webservice返回值对照表");
				this.message = "发送失败";
				this.state = "0";
			} else {
				System.out.print("发送成功，返回值为：" + result_mt);
				this.message = "发送成功";
				this.state = "1";
			}
			System.out.println(phone.getPhoneName() + phone.getPhonePassword()
					+ this.mobiles + contents);
			key = true;
		}
		if (phone.getPhoneId().intValue() == 2) {
			String url = "http://115.29.242.32:8888/sms.aspx";
			SmsClientSend.sendSms(url, phone.getPhoneuserId(),
					phone.getPhoneName(), phone.getPhonePassword(),
					this.mobiles, contents);
			this.message = "发送成功";
			this.state = "1";
			System.out.println(phone.getPhoneName() + phone.getPhonePassword()
					+ this.mobiles + contents);
			key = true;
		}
		if (key) {
			SmsSendLog ssl = new SmsSendLog();
			ssl.setContent(contents);
			ssl.setSendTime(new Timestamp(new Date().getTime()));
			ssl.setTophone(mobiles);
			ssl.setTitle("");
			ssl.setUservip(uservip1);
			ssl.setStatus(1);
			SmsSendLogService sslService = new SmsSendLogServiceImpl();
			sslService.createSmsSendLog(ssl);
		}
		this.message = "{\"state\":\"" + this.state + "\"," + "\"message\":\""
				+ this.message + "\"}";
		System.out.println(this.message);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(this.message);
	}
	/**
	   * 处理认证奖励
	   * @param uservip
	 * @throws Exception 
	   */
	  public void authentic_reward(Uservip uservip) throws Exception{
		  if(uservip!=null && uservip.getUserId()!=null && uservip.getUserId()>0 
				  && uservip.getNameResult()!=null && "2".equals(uservip.getNameResult()) 
				  && uservip.getVideoResult()!=null && "1".equals(uservip.getVideoResult())
				  && uservip.getEnable()!=null && uservip.getEnable().intValue()==1
				  && uservip.getSceneResult()!=null && "1".equals(uservip.getSceneResult())
				  && uservip.getPhoneResult()!=null && "1".equals(uservip.getPhoneResult())
				  ){
			  SystemconfService systemconfService = new SystemconfServiceImpl();
			  Systemconf sysconf= systemconfService.findSystemconfByParname("con_authentic_reward");
			  if(sysconf!=null && sysconf.getParvalue()!=null && !"".equals(sysconf)){
				  MoneyhistorydetailService moneyhistorydetailService = new MoneyhistorydetailServiceImpl();
				  double authreward = Double.valueOf(sysconf.getParvalue());
				  MoneycountService moneycountService = new MoneycountServiceImpl();
				  Moneycount moneycount = moneycountService.findMoneycountByuserId(uservip.getUserId());
				  Moneyhistorydetail moneyhistorydetail = new Moneyhistorydetail();
				  moneyhistorydetail.setAffectMoney(authreward);
				  moneyhistorydetail.setAvailableBalance(moneycount.getAvailableMoney()+authreward);
				  moneyhistorydetail.setCollectMoney(moneycount.getCollectTotalMoney());
				  moneyhistorydetail.setOccurTime(new Timestamp(new Date()
					.getTime()));
				  moneyhistorydetail.setFrozenMoney(moneycount.getFrozenMoney());
				  moneyhistorydetail.setUservip(uservip);
				  moneyhistorydetail.setIntroduction("所有认证成功，发放认证成功奖励");
				  moneyhistorydetailService
						.createMoneyhistorydetail(moneyhistorydetail);
				  moneycount.setAvailableMoney(moneycount.getAvailableMoney()+authreward);
				  moneycount.setTotalMoney(moneycount.getTotalMoney()+authreward);
				  moneycountService.updateMoneycount(moneycount);
			  }
		  }
	  }
	/**
	 * 根据sessionId获取Uservip对象
	 * @return
	 */
	public Uservip getUservip1(){
		MySessionContext myc= MySessionContext.getInstance();
		HttpSession sess = myc.getSession(this.sessionId);
		Uservip uservip = (Uservip) sess.getAttribute(this.uservip);
		return uservip;
	}
	
private  static String MD5(String s) {
		
		if (s==null||s.length()==0){
			return null;
		}
		char hexDigits[] = { 'A', '1', 'B', '3', 'C', '5', 'D', '7', 'E','9', 'F', '0', 'G', '2', 'H', '4' };
		try {
			byte[] btInput = s.getBytes();
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}
}
