package com.jqg.wap;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

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
import com.jqg.struts.BaseAction;
import com.jqg.util.Client;
import com.jqg.util.LoanUtils;
import com.jqg.util.MailSenderInfo;
import com.jqg.util.SendMail;
import com.jqg.util.UploadPhoto;
import com.opensymphony.xwork2.ActionContext;
import com.ruanwei.interfacej.SmsClientSend;

/**
 * 实名认证
 * @author 徐斌
 *
 */
public class WapCertificationAction extends BaseAction{
	//身份证名称
	private String realName;
	//身份证号码
	private String idNum;
	//邮箱地址 0:失败 1：重复 2：成功
	private String mail;
	//发送状态
	private String state;
	//返回信息 
	private String message;
	//返回结果
	private String result;
	//邮箱地址
	private String address;
	//获取uservip
	private String sessionId;
	//创建上传图片的file实例
	private File file;
	private String fileFileName;
	private String fileFileContentType;
	//创建上传图片的file实例
	private String fileMessage = "你已成功上传文件";
	private File file2;
	private String file2FileName;
	private String file2FileContentType;
	
	//验证所需手机号
	private String mobiles;
	//信息模版id
	private int messModelId;
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
	
	private String mark;
	
	private String phoneNum;
	
	private String code;
	
	private Basicinfor basicinfor;
	
	private Certification certifi;
	
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
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	public File getFile() {
		return this.file;
	}

	public void setFile(String file) {
		File f=new File(file);
		this.file = f;
	}

	public String getFileFileName() {
		return this.fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileFileContentType() {
		return this.fileFileContentType;
	}

	public void setFileFileContentType(String fileFileContentType) {
		this.fileFileContentType = fileFileContentType;
	}

	public File getFile2() {
		return this.file2;
	}

	public void setFile2(String file2) {
		File f=new File(file2);
		this.file2 = f;
	}

	public String getFile2FileName() {
		return this.file2FileName;
	}

	public void setFile2FileName(String file2FileName) {
		this.file2FileName = file2FileName;
	}

	public String getFile2FileContentType() {
		return this.file2FileContentType;
	}

	public void setFile2FileContentType(String file2FileContentType) {
		this.file2FileContentType = file2FileContentType;
	}
	
	public String getMobiles() {
		return mobiles;
	}

	public void setMobiles(String mobiles) {
		this.mobiles = mobiles;
	}

	public int getMessModelId() {
		return messModelId;
	}

	public void setMessModelId(int messModelId) {
		this.messModelId = messModelId;
	}

	public String getUservip() {
		return uservip;
	}

	public void setUservip(String uservip) {
		this.uservip = uservip;
	}
	
	public String getMark() {
		return this.mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getPhoneNum() {
		return this.phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public Basicinfor getBasicinfor() {
		return basicinfor;
	}

	public void setBasicinfor(Basicinfor basicinfor) {
		this.basicinfor = basicinfor;
	}

	public Certification getCertifi() {
		return certifi;
	}

	public void setCertifi(Certification certifi) {
		this.certifi = certifi;
	}
	
	public Uservip getUser() {
		return user;
	}

	public void setUser(Uservip user) {
		this.user = user;
	}
	
	/**
	 * 认证中心
	 * 
	 * @return
	 * @throws Exception
	 */
	public String IDCardCertification() throws Exception {
		Certification certification = new Certification();
		request = ServletActionContext.getRequest();
		this.sessionId = request.getSession().getId();
		Uservip uservip = (Uservip) ActionContext.getContext().getSession().get("uservip" + sessionId);
		Basicinfor bas = this.basicinforService.findBasicinforByUserId(uservip.getUserId());
		Basicinfor bf = this.basicinforService.findBasicinforByIdNum(this.idNum);
		if (bf!=null){
			this.mark="11";
			this.setMessage("身份证号已被使用！！", "wap/wapRealName.jsp", "3");
			return "error";
		}else if(bas.getIdNum()!=null && !"".equals(bas.getIdNum()) && ("2".equals(uservip.getNameResult()) || "1".equals(uservip.getNameResult()))){
			this.mark="22";//已经实名认证或是实名认识已经提交 申请的都不能再进行认证提交
			this.setMessage("已实名认证！！", "wap/wapRealName.jsp", "3");
			System.out.println(this.mark+this.message);
			return "error";
		}else{
		certification = this.certificationService.findCertificationByUserId(uservip.getUserId().intValue());
		
		System.out.println(certification.getRealName());
		certification.setUservip(uservip);
		certification.setRealName(this.realName);
		certification.setIdNum(this.idNum);
		certification.setUpLoadTime(new Timestamp(new Date().getTime()));
		certification.setExamineStatus(Integer.valueOf(0));
		
		
		if (certification == null || certification.getUservip()==null) {
			System.out.println("创建实名信息");
			this.certificationService.createCertification(certification);
		}else{
			System.out.println("update实名信息");
			this.certificationService.updateCertification(certification);
		}
		
		System.out.println("uservip ID is"+uservip.getUserId().intValue());
		Uservip uservip1 = this.uservipService.findUservipByUserid(uservip
				.getUserId().intValue());
		uservip1.setNameResult("1");
		this.uservipService.updateUservip(uservip1);
		
		
		
		this.mark = "3";
		}
		this.setMessage("已经发出实名认证申请！！！", "wap/wapCertification.jsp", "3");
		return "success";
	}
	/**
	 * 发送邮件
	 * 
	 * @return
	 * @throws Exception
	 */
	public String sendMail() throws Exception {
		Smtp smtp = this.smtpService.findSmtpBysmtpid(1);
		Emailmodel emailmodel = this.emailmodelService.findEmailmodelByemailModelId(1);
		request = ServletActionContext.getRequest();
		this.sessionId = request.getSession().getId();
		Uservip uservip = (Uservip) ActionContext.getContext().getSession().get("uservip" + sessionId);
		String flag = null;
		flag = this.uservipService.findUservipByMail(this.address);
		String states="";
		if(uservip.getMail()!=null && !"".equals(uservip.getMail()) && uservip.getEnable()!=null){
			if(uservip.getEnable()==1){
				states="1";
			}
		}
		if("1".equals(flag)){
			this.mark="12";
		}else if("1".equals(states)){
				this.mark="13";
		}else{
		if (emailmodel.getIsOpen() == 1) {
			MailSenderInfo mailInfo = new MailSenderInfo();
			mailInfo.setMailServerHost(smtp.getSmtpsever());
			mailInfo.setMailServerPort("25");
			mailInfo.setValidate(true);

			mailInfo.setUserName(smtp.getSmtpemail());

			mailInfo.setPassword(smtp.getSmtppassword());

			mailInfo.setFromAddress(smtp.getSmtpemail());

			mailInfo.setToAddress(this.address);
			mailInfo.setSubject("邮箱激活");

			String code = "" + System.currentTimeMillis();
			String str = emailmodel.getEmailModelContent();
			str = str.replace("'#UserName#'", uservip.getUserName());
			StringBuffer sb = new StringBuffer();
			this.request = ServletActionContext.getRequest();
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
			sb.append(basePath + "user/activate?action=activate&email=");

			sb.append(this.address);
			Uservip uservips = this.uservipService
					.findUservipByUserid(uservip.getUserId().intValue());
			uservips.setMail(this.address);
			this.uservipService.updateUservip(uservips);
			ActionContext.getContext().getSession()
					.put("uservip", uservips);
			sb.append("&validateCode=" + code);
			sb.append("&userId=" + uservip.getUserId());
			String address="点击下面链接激活账号,请尽快激活！ <a href=\""+sb.toString()+"\" >"+sb.toString()+"</a>";
			str = str.replace("#LINK#", address);
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
			EmailSendLogService eslService = new EmailSendLogServiceImpl();
			eslService.createEmailSendLog(esl);
		}
	}
		this.mark = "1";
		return "success";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 图片ajax上传，无刷新
	 * 
	 * @return
	 * @throws Exception
	 */
	public String fileUploadAction() throws Exception {
		String path = ServletActionContext.getServletContext().getRealPath("/images");
//		String path = ServletActionContext.getRequest().getRealPath("/images");
		try {
			File f = getFile();
			File f2 = getFile2();
			if (f != null) {
				if ((!getFileFileName().endsWith(".jpg"))
						&& (!getFileFileName().endsWith(".jpeg"))
						&& (!getFileFileName().endsWith(".bmp"))
						&& (!getFileFileName().endsWith(".gif"))
						&& (!getFileFileName().endsWith(".png"))) {
					this.fileMessage = "对不起,你上传的文件格式不允许!!!";
					return "error";
				}
			} else if ((f2 != null) && (!getFile2FileName().endsWith(".jpg"))
					&& (!getFile2FileName().endsWith(".jpeg"))
					&& (!getFile2FileName().endsWith(".bmp"))
					&& (!getFile2FileName().endsWith(".gif"))
					&& (!getFile2FileName().endsWith(".png"))) {
				this.fileMessage = "对不起,你上传的图片格式不允许!!!";
				return "error";
			}

			Uservip uservip = (Uservip) ActionContext.getContext().getSession()
					.get("uservip");
			Certification certification = this.certificationService
					.findCertificationByUserId(uservip.getUserId().intValue());
			FileInputStream inputStream = null;
			FileOutputStream outputStream = null;
			if (f != null) {
				inputStream = new FileInputStream(f);
				if (inputStream.available() / 1000000 > 20) {
					this.message = "对不起,你上传的图片大于20m!!!";
					return "error";
				}
				String urlStr = UploadPhoto.LoadImageToServer(getFileFileName(),
						f);
				certification.setFrontImage(urlStr);
			} else if (f2 != null) {
				inputStream = new FileInputStream(f2);
				if (inputStream.available() / 1000000 > 20) {
					this.message = "对不起,你上传的图片大于20m!!!";
					return "error";
				}
				String urlStr = UploadPhoto.LoadImageToServer(getFile2FileName(),
						f2);
				certification.setNegativeImage(urlStr);
			}
			this.certificationService.updateCertification(certification);
		} catch (Exception e) {
			e.printStackTrace();
			this.message = "对不起,文件上传失败了!!!!";
		}
		return "success";
	}
	
	//根据手机号查找用户
		public String findBasicinforByphone() throws Exception {
			basicinfor = this.basicinforService.findBasicinforByPhone(this.phoneNum);
			System.out.println(this.phoneNum);
			if (basicinfor==null){
				this.result = "0";
			}else {
				this.result = "1";
			}
			return "success";
		}
	/**
	 * 手机认证
	 */
	public String wapPhoneSet() throws Exception {
		request = ServletActionContext.getRequest();
		this.sessionId = request.getSession().getId();
		Uservip uservip = (Uservip) ActionContext.getContext().getSession().get("uservip" + sessionId);
		String pcode = (String) ActionContext.getContext().getSession()
				.get("code" + uservip.getUserId());
		String phone=(String) ActionContext.getContext().getSession()
				.get("data");
		System.out.println("pcode"+pcode+"phone"+phone);
		if (pcode.trim().equals(this.code.trim())) {
			Uservip user1 = this.uservipService.findUservipByUserid(uservip.getUserId().intValue());
			if("1".equals(user1.getPhoneResult())){
				this.result = "repeat";
				this.mark = "2";
				this.setMessage("手机已认证过不能重复认证！", "wap/wapPhoneCheck.jsp", "3");
				return this.result;
			}
			
			user1.setPhoneResult("1");
			Basicinfor basic = this.basicinforService
					.findBasicinforByUserId(uservip.getUserId().intValue());
			basic.setPhoneNum(this.phoneNum);
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
			integraldetail.setUservip(uservip);
			integraldetail.setIntegralcategory(integralcategory);
			integraldetail.setIntegralQuota(Double.valueOf(Integer.valueOf(Integer.parseInt(integralparameter.getIntegralPnumber()))));
			integraldetail.setIntegralTime(new Timestamp(new Date().getTime()));
			integraldetail.setIntegralReason(integralparameter.getIntegralPdescription());
			if(uservip.getInvestItegral()!=null && uservip.getItegral()!=null){
				integraldetail.setRemainIntegral(uservip.getInvestItegral() + uservip.getItegral() + Double.valueOf(Integer.valueOf(integralparameter.getIntegralPnumber())));
			}else if(uservip.getInvestItegral()==null && uservip.getItegral()!=null){
				integraldetail.setRemainIntegral(Double.valueOf(uservip.getItegral() + Double.valueOf(Integer.valueOf(Integer.parseInt(integralparameter.getIntegralPnumber())))));
			}else if(uservip.getInvestItegral()!=null && uservip.getItegral()==null){
				integraldetail.setRemainIntegral(uservip.getInvestItegral() + Double.valueOf(Integer.valueOf(Integer.parseInt(integralparameter.getIntegralPnumber()))));
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
			this.authentic_reward(uservip);
			this.user = user1;
			ActionContext.getContext().getSession().put("uservip", user1);
			this.setMessage("认证成功！", "wap/index.jsp", "3");
			this.result = "success";
		} else {
			this.setMessage("手机已认证过不能重复认证！", "wap/wapPhoneCheck.jsp", "3");
			this.result = "error";
		}
		this.mark = "2";
		System.out.println("手机认证result结果是："+this.result);
//		 this.result = "result:"+this.result;
//		System.out.println("短信发送result结果是："+this.result);
		return this.result;
	}
	/**
	 * 短信
	 * 
	 * @return
	 * @throws Exception
	 */
	public String wapSendMessage() throws Exception {
		Phone phone = this.phoneService.findPhoneByOpen();
		Messagemodel messagemodel = this.messagemodelService
				.findMessagemodelByintegralPid(this.messModelId);
		String contents = messagemodel.getMessModelContent();
		System.out.println(contents);
		if (contents.indexOf("#USERNAME#") != -1) {
			contents = contents.replaceAll("#USERNAME#",
					user.getUserName());
		}
		if (contents.indexOf("#CODE#") != -1) {
			String code = "";
			while (code.length() < 6) {
				code = code + (int) (Math.random() * 10.0D);
			}
			contents = contents.replaceAll("#CODE#", code);

			ActionContext.getContext().getSession()
					.put("code" + user.getUserId(), code);
		}
		boolean key = false;
		if (phone.getPhoneId().intValue() == 1) {
			Client client = new Client(phone.getPhoneName(),
					phone.getPhonePassword());
			String result_mt = client.mdSmsSend_u(this.mobiles, contents, "",
					"", "");
			System.out.println(this.mobiles);
			if ((result_mt.startsWith("-")) || ("".equals(result_mt))) {
				System.out.print("发送失败！返回值为：" + result_mt
						+ "请查看webservice返回值对照表");
				this.result = "error";
			} else {
				System.out.print("发送成功，返回值为：" + result_mt);
				this.result = "success";
			}
			System.out.println(phone.getPhoneName() + phone.getPhonePassword()
					+ this.mobiles + contents);
			key = true;
		}
		if (phone.getPhoneId().intValue() == 2) {
			String url = "http://115.29.242.32:8888/sms.aspx";
			SmsClientSend.sendSms(url, phone.getPhoneuserId(), phone.getPhoneName(), phone.getPhonePassword(), this.mobiles, contents);
//			
			this.result = "success";
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
			ssl.setUservip(user);
			ssl.setStatus(1);
			SmsSendLogService sslService = new SmsSendLogServiceImpl();
			sslService.createSmsSendLog(ssl);
		}
		System.out.println("短信发送result结果是："+this.result);
//		this.result = "{\"result\":\""+this.result+"\"}";
//		System.out.println("短信发送result结果是："+this.result);
		return this.result;
		
	}
	/**
	 * 根据邮箱查找用户
	 * 
	 * @return
	 * @throws Exception
	 */
	public String findUservipByMail() throws Exception {
		String flag = null;
		flag = this.uservipService.findUservipByMail(this.address);
		System.out.println(this.address);
		if ("0".equals(flag))
			this.result = "0";
		else {
			this.result = "1";
		}
		System.out.println(flag);
		return "success";
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
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
	   * 用户基本资料 
	   * @return
	   * @throws Exception
	   */
	    public String search()
	      throws Exception
	    {
	    	request = ServletActionContext.getRequest();
			this.sessionId = request.getSession().getId();
			Uservip uservip = (Uservip) ActionContext.getContext().getSession().get("uservip" + sessionId);
	      this.basicinfor = this.basicinforService.findBasicinforByUserId(uservip.getUserId().intValue());
	      this.certifi = this.certificationService.findCertificationByUserId(uservip.getUserId().intValue());
	      this.user = this.uservipService.findUservipByUserid(uservip.getUserId().intValue());
	      return "success";
	    }
}
