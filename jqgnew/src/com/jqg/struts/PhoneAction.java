package com.jqg.struts;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.jianzhou.sdk.BusinessService;
import com.jqg.pojo.Basicinfor;
import com.jqg.pojo.EmailSendLog;
import com.jqg.pojo.Emailmodel;
import com.jqg.pojo.Messagemodel;
import com.jqg.pojo.Phone;
import com.jqg.pojo.SmsSendLog;
import com.jqg.pojo.Smtp;
import com.jqg.pojo.Uservip;
import com.jqg.service.BasicinforService;
import com.jqg.service.EmailSendLogService;
import com.jqg.service.EmailmodelService;
import com.jqg.service.MessagemodelService;
import com.jqg.service.PhoneService;
import com.jqg.service.SmsSendLogService;
import com.jqg.service.SmtpService;
import com.jqg.service.UservipService;
import com.jqg.service.impl.BasicinforServiceImpl;
import com.jqg.service.impl.EmailSendLogServiceImpl;
import com.jqg.service.impl.EmailmodelServiceImpl;
import com.jqg.service.impl.MessagemodelServiceImpl;
import com.jqg.service.impl.PhoneServiceImpl;
import com.jqg.service.impl.SmsSendLogServiceImpl;
import com.jqg.service.impl.SmtpServiceImpl;
import com.jqg.service.impl.UservipServiceImpl;
import com.jqg.util.AccessValidation;
import com.jqg.util.Client;
import com.jqg.util.MailSenderInfo;
import com.jqg.util.SendMail;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.ruanwei.interfacej.SmsClientSend;

public class PhoneAction extends BaseAction {
	private Integer PhoneId;
	private String PhoneName;
	private String PhonePassword;
	private String PhoneCategory;
	private String mobiles;
	private int isOpen;
	private String result;
	private int messModelId;
	private String sessionId;
	private PhoneService phoneService = new PhoneServiceImpl();
	private MessagemodelService messagemodelService = new MessagemodelServiceImpl();
	private Integer userId;
	private String yanzhengma;
 

	private UservipService uservipService = new UservipServiceImpl();
	Uservip uservip = (Uservip) ActionContext.getContext().getSession()
			.get("uservip");
	private HttpServletRequest request;

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String sendMessage() throws Exception {
		if(this.uservip==null){
			this.uservip=this.uservipService.findUservipByUserid(userId);
		}
		Phone phone = this.phoneService.findPhoneByOpen();
		Messagemodel messagemodel = this.messagemodelService
				.findMessagemodelByintegralPid(this.messModelId);
		String contents = messagemodel.getMessModelContent();
		if (contents.indexOf("#USERNAME#") != -1) {
			contents = contents.replaceAll("#USERNAME#",
					this.uservip.getUserName());
		}
		if (contents.indexOf("#KEY#") != -1) {
			String key = "";
			while (key.length() < 6) {
				key = key + (int) (Math.random() * 10.0D);
			}
			contents = contents.replaceAll("#KEY#", key);

			ActionContext.getContext().getSession()
					.put("key" + this.uservip.getUserId(), key);
			System.err.println("key:" + key);
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
			key = true;
			System.out.print("发送成功，返回值为：" + result_mt);

			System.out.println(phone.getPhoneName() + "   "
					+ phone.getPhonePassword() + "   " + this.mobiles + "   "
					+ contents);
		}
		if (phone.getPhoneId().intValue() == 2) {
			String url = "http://115.29.242.32:8888/sms.aspx";
			SmsClientSend.sendSms(url, phone.getPhoneuserId(), phone.getPhoneName(), phone.getPhonePassword(), this.mobiles, contents);
//			
			System.out.println(phone.getPhoneName() + phone.getPhonePassword()
					+ this.mobiles + contents);
			this.result = "success";
			key = true;
		}
		if (key) {
			SmsSendLog ssl = new SmsSendLog();
			ssl.setContent(contents);
			ssl.setSendTime(new Timestamp(new Date().getTime()));
			ssl.setTophone(mobiles);
			ssl.setTitle("");
			ssl.setUservip(uservip);
			ssl.setStatus(1);
			SmsSendLogService sslService = new SmsSendLogServiceImpl();
			sslService.createSmsSendLog(ssl);
		}
		return "success";
	}

	/**
	 * 短信
	 * 
	 * @return
	 * @throws Exception
	 */
	public String sendMessage1() throws Exception {
		Phone phone = this.phoneService.findPhoneByOpen();
		Messagemodel messagemodel = this.messagemodelService
				.findMessagemodelByintegralPid(this.messModelId);
		String contents = messagemodel.getMessModelContent();
		System.out.println(contents);
		if (contents.indexOf("#USERNAME#") != -1) {
			contents = contents.replaceAll("#USERNAME#",
					this.uservip.getUserName());
		}
		if (contents.indexOf("#CODE#") != -1) {
			String code = "";
			while (code.length() < 6) {
				code = code + (int) (Math.random() * 10.0D);
			}
			contents = contents.replaceAll("#CODE#", code);

			ActionContext.getContext().getSession()
					.put("code" + this.uservip.getUserId(), code);
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
			ssl.setUservip(uservip);
			ssl.setStatus(1);
			SmsSendLogService sslService = new SmsSendLogServiceImpl();
			sslService.createSmsSendLog(ssl);
		}
		return "success";
	}

	/**
	 * 密码找回
	 * @return
	 * @throws Exception
	 */
	public String send() throws Exception {
		request=ServletActionContext.getRequest();
		this.sessionId=request.getSession().getId();
		String code1 = (String)ActionContext.getContext().getSession().get("code1"+this.sessionId);
		String flg=request.getParameter("flg");
		
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String times=sd.format(new Date());
		
		String timession=(String)ActionContext.getContext().getSession().get("time"+this.sessionId);
		if(timession!=null && !"".equals(timession)){
			long time2=(sd.parse(times).getTime()-sd.parse(timession).getTime())/(60*1000);
			if(time2<1){
				this.result = "timeout";  //发送时间不到60秒
				return "success";
			}else{
				ActionContext.getContext().getSession().put("time"+this.sessionId, times);
			}
		}else{
			ActionContext.getContext().getSession().put("time"+this.sessionId, times);
		}
		
		
		if (!code1.equals(this.yanzhengma)) {
			if("1".equals(flg)){
				this.result="imgNumberError";
				return "success";
			}else{
		       this.setMessage("验证码错误", "/r_pwd_1.jsp", "3");
		       return "jump";
			}
		     }else{
		Phone phone = this.phoneService.findPhoneByOpen();
		this.request = ServletActionContext.getRequest();
		// String Iphone="";
		// String email="";
		String mobiles = this.mobiles;
		String oldMobiles=mobiles;
		String newMobiles="";
		String name="";
		String id="";
		System.err.println(mobiles);
		Messagemodel messagemodel = this.messagemodelService
				.findMessagemodelByintegralPid(15);
		if (mobiles.indexOf("@") != -1) {
			UservipService service = new UservipServiceImpl();
			try {
				uservip = service.findUservipByEmail(mobiles.trim());
			} catch (Exception e) {
				this.result = "error";// 邮箱不正确
				return "success";
			}
			
			if (uservip ==null) {
				this.result = "error";// 邮箱不正确
				return "success";
			} else {
				name=uservip.getUserName();
				id=""+uservip.getUserId();
				newMobiles=mobiles.substring(0,mobiles.indexOf("@"));
				String mobiles2=mobiles.substring(mobiles.indexOf("@")+1,mobiles.length());
//				for(int i=1;i<(newMobiles.length()-1);i++){
//					newMobiles=newMobiles.replace(newMobiles.charAt(i),'*');
//				}
				newMobiles=newMobiles.charAt(0)+"***"+newMobiles.charAt(newMobiles.length()-1)+"@"+mobiles2;
				SmtpService smtpService = new SmtpServiceImpl();
				EmailmodelService emailmodelService = new EmailmodelServiceImpl();
				Smtp smtp = smtpService.findSmtpBysmtpid(1);
				Emailmodel emailmodel = emailmodelService
						.findEmailmodelByemailModelId(1);
				String pwdCode = "";
				if (emailmodel.getIsOpen() == 1) {
					MailSenderInfo mailInfo = new MailSenderInfo();
					mailInfo.setMailServerHost(smtp.getSmtpsever());
					mailInfo.setMailServerPort("25");
					mailInfo.setValidate(true);

					mailInfo.setUserName(smtp.getSmtpemail());

					mailInfo.setPassword(smtp.getSmtppassword());

					mailInfo.setFromAddress(smtp.getSmtpemail());

					mailInfo.setToAddress(mobiles);

					mailInfo.setSubject("密码重置");

					String code = "" + System.currentTimeMillis();
//					String str = emailmodel.getEmailModelContent();
//					str = str.replace("'#UserName#'", uservip.getUserName());

					String contents = messagemodel.getMessModelContent();
					System.out.println(contents);
					if (contents.indexOf("#USERNAME#") != -1) {
						contents = contents.replaceAll("#USERNAME#",
								this.uservip.getUserName());
					}
					if (contents.indexOf("#CODE#") != -1) {
						// 生成验证吗
						while (pwdCode.length() < 6) {
							pwdCode = pwdCode + (int) (Math.random() * 10.0D);
						}
						contents = contents.replaceAll("#CODE#", pwdCode);

						ActionContext
								.getContext()
								.getSession()
								.put("pwdCode" + id,
										pwdCode);
						System.err.println("pwdCode:" + pwdCode);
					}
					System.err.println(contents);
//					str = str.replace("#LINK#", contents + pwdCode);
//					str = str.replace("#LINK#", "验证码：" + pwdCode);
					
//					String str="以下是为您重置密码的代码:"+pwdCode;
					
//					mailInfo.setSubject("邮箱激活");
//
//					String code = "" + System.currentTimeMillis();
//					String str = emailmodel.getEmailModelContent();
//					str = str.replace("'#UserName#'", uservip.getUserName());
//					StringBuffer sb = new StringBuffer("点击下面链接激活账号,请尽快激活！");
//					this.request = ServletActionContext.getRequest();
//					String path = request.getContextPath();
//					if(path!=null && !path.equals("")){
//						path = path+"/";
//					}
//					String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
//					sb.append(basePath+"user/activate?action=activate&email=");
//					sb.append(mobiles);
//					sb.append("&validateCode=" + code);
//					sb.append("&userId=" + uservip.getUserId());
//
//					str = str.replace("#LINK#", sb.toString());
//					
//					
					
					
//					~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~```
					
					mailInfo.setContent(contents);
					SendMail sms = new SendMail();

					boolean row = sms.sendTextMail(mailInfo);

					EmailSendLog esl = new EmailSendLog();
					esl.setContent(contents);
					esl.setFromemail(mailInfo.getFromAddress());
					esl.setToemail(mailInfo.getToAddress());
					esl.setSendTime(new Timestamp(new Date().getTime()));
					esl.setTitle(mailInfo.getSubject());
					esl.setUservip(uservip);
					if (!row) {
						esl.setStatus(2);
						System.out.println("邮箱发送失败");
					} else {
						esl.setStatus(1);
						System.out.println("成功");
					}
					EmailSendLogService eslService = new EmailSendLogServiceImpl();
					eslService.createEmailSendLog(esl);
					if (!row)
						System.out.println("邮箱发送失败");
					else {
						System.out.println("邮箱发送成功");
					}

				}

			}
		} else if (mobiles.trim().length() == 11) {

			// 验证手机
			BasicinforService service = new BasicinforServiceImpl();
			Basicinfor basicinfor =new Basicinfor();
			try {
				basicinfor= service.findBasicinforByPhone(mobiles.trim());
			} catch (Exception e) {
				this.result ="error";// 手机号不正确
				return "success";
			}
			
			uservip=basicinfor.getUservip();
			if (uservip==null) {
				this.result ="error";// 手机号不正确
				return "success";
			} else {
				name=uservip.getUserName();
				id=""+uservip.getUserId();
				//替换指定代码
				mobiles=mobiles.replace(mobiles.charAt(3),'*');
				mobiles=mobiles.replace(mobiles.charAt(4),'*');
				mobiles=mobiles.replace(mobiles.charAt(5),'*');
				mobiles=mobiles.replace(mobiles.charAt(6),'*');
				newMobiles=mobiles;
				
				String contents = messagemodel.getMessModelContent();
				System.out.println(contents);
				if (contents.indexOf("#USERNAME#") != -1) {
					contents = contents.replaceAll("#USERNAME#",
							this.uservip.getUserName());
				}
				if (contents.indexOf("#CODE#") != -1) {
					String code = "";
					// 生成验证吗
					while (code.length() < 6) {
						code = code + (int) (Math.random() * 10.0D);
					}
					contents = contents.replaceAll("#CODE#", code);

					ActionContext
							.getContext()
							.getSession()
							.put("pwdCode" + id, code);
					System.err.println("pwdCode:" + code);
				}
				boolean key = false;
				if (phone.getPhoneId().intValue() == 1) {
					Client client = new Client(phone.getPhoneName(),
							phone.getPhonePassword());
					String result_mt = client.mdSmsSend_u(this.mobiles,
							contents, "", "", "");
					System.out.println(this.mobiles);
					if ((result_mt.startsWith("-")) || ("".equals(result_mt))) {
						System.out.print("发送失败！返回值为：" + result_mt
								+ "请查看webservice返回值对照表");
						this.result = "error";
					} else {
						System.out.print("发送成功，返回值为：" + result_mt);
						this.result = "success";
					}
				}
				if (phone.getPhoneId().intValue() == 2) {
					String url = "http://115.29.242.32:8888/sms.aspx";
					String sendSms = SmsClientSend.sendSms(url, phone.getPhoneuserId(), phone.getPhoneName(), phone.getPhonePassword(), this.mobiles, contents);
					System.out.println(phone.getPhoneName()
							+ phone.getPhonePassword() + this.mobiles
							+ contents);
					if (sendSms.indexOf("Faild") != -1) {
						this.result = "success";
					}else{
						this.result = "error";
					}
					
					key = true;
				}
				if (key) {
					SmsSendLog ssl = new SmsSendLog();
					ssl.setContent(contents);
					ssl.setSendTime(new Timestamp(new Date().getTime()));
					ssl.setTophone(oldMobiles);
					ssl.setTitle("");
					ssl.setUservip(uservip);
					ssl.setStatus(1);
					SmsSendLogService sslService = new SmsSendLogServiceImpl();
					sslService.createSmsSendLog(ssl);
				}
			}
		} else {
			this.result = "error";// 输入的内容不正确
			return "success";
		}
//		没有替换的 信息
		ActionContext.getContext().put("oldMobiles",oldMobiles );
//		替换后的信息
		ActionContext.getContext().put("newMobiles",newMobiles );
		ActionContext.getContext().put("name",name);
		ActionContext.getContext().put("id",id);
		return "success";
		     }
	}

	/**
	 * 注册短信
	 * 
	 * @return
	 * @throws Exception
	 */
	public String sendMessageZC() throws Exception {
		request = ServletActionContext.getRequest();
		String code222=request.getParameter("code");
		this.sessionId = request.getSession().getId();
		
		
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String times=sd.format(new Date());
		
		String timession=(String)ActionContext.getContext().getSession().get("time"+this.sessionId);
		if(timession!=null && !"".equals(timession)){
			long time2=(sd.parse(times).getTime()-sd.parse(timession).getTime())/(60*1000);
			if(time2<1){
				this.result = "timeout";  //发送时间不到60秒
				return "error";
			}else{
				ActionContext.getContext().getSession().put("time"+this.sessionId, times);
			}
		}else{
			ActionContext.getContext().getSession().put("time"+this.sessionId, times);
		}
		
		AccessValidation av=new AccessValidation();
		boolean boo=av.isMobileDevice(request);
		System.err.println("========:"+boo);
		if(boo==false){
			String code1 = (String) ActionContext.getContext().getSession()
				.get("code1" + this.sessionId);
			if(code1!=null&&!"".equals("")){
				if (!code1.equals(code222)) {
					this.result = "errorCode"; //图片验证码错误
					return "error";
				}
			}
		}
		
		// 验证手机
		BasicinforService service = new BasicinforServiceImpl();
		Basicinfor basicinfor =new Basicinfor();
		try {
			basicinfor= service.findBasicinforByPhone(mobiles.trim());
		} catch (Exception e) {
			this.result ="error";// 手机号不正确
			return "error";
		}
		
		
		if (basicinfor!=null) {
			this.result ="ycz";// 手机号已存在
			return "error";
		} 
		Phone phone = this.phoneService.findPhoneByOpen();
		Messagemodel messagemodel = this.messagemodelService
				.findMessagemodelByintegralPid(this.messModelId);
		String contents = messagemodel.getMessModelContent();
		System.out.println(contents);
		if (contents.indexOf("#USERNAME#") != -1) {
			contents = contents.replaceAll("#USERNAME#",
					"");
		}
		if (contents.indexOf("#CODE#") != -1) {
			String code = "";
			while (code.length() < 6) {
				code = code + (int) (Math.random() * 10.0D);
			}
			contents = contents.replaceAll("#CODE#", code);

			ActionContext.getContext().getSession()
					.put("ZCcode" +this.sessionId , code);
			System.err.println("ZCcode:"+code);
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
			
			System.out.println(phone.getPhoneName() + phone.getPhonePassword()
					+ this.mobiles + contents);
			this.result = "success";
			key = true;
		}
		if (key) {
			SmsSendLog ssl = new SmsSendLog();
			ssl.setContent(contents);
			ssl.setSendTime(new Timestamp(new Date().getTime()));
			ssl.setTophone(mobiles);
			ssl.setTitle("");
			ssl.setUservip(uservip);
			ssl.setStatus(1);
			SmsSendLogService sslService = new SmsSendLogServiceImpl();
			sslService.createSmsSendLog(ssl);
		}
		return this.result;
	}
	
	
	public String createPhone() throws Exception {
		Phone phone = new Phone();
		phone.setPhoneName(this.PhoneName);
		phone.setPhonePassword(this.PhonePassword);
		phone.setPhoneCategory(this.PhoneCategory);

		boolean flag = this.phoneService.createPhone(phone);
		if (flag) {
			this.result = "success";
		} else {
			this.result = "error";
		}
		return "success";
	}

	public String updatePhone() throws Exception {
		Phone phone = this.phoneService.findPhoneByphoneId(this.PhoneId
				.intValue());
		phone.setPhoneName(this.PhoneName);
		phone.setPhonePassword(this.PhonePassword);
		phone.setPhoneCategory(this.PhoneCategory);

		boolean flag = this.phoneService.updatePhone(phone);
		if (flag) {
			this.result = "success";
		} else {
			this.result = "error";
		}
		return "success";
	}

	public String deletePhone() throws Exception {
		Phone phone = this.phoneService.findPhoneByphoneId(this.PhoneId
				.intValue());

		if (phone != null) {
			this.result = "success";
		} else {
			this.result = "error";
		}
		return "success";
	}

	public String findPhoneByPhoneId(int PhoneId) throws Exception {
		Phone phone = this.phoneService.findPhoneByphoneId(PhoneId);

		if (phone != null) {
			this.result = "success";
		} else {
			this.result = "error";
		}
		return "success";
	}

	public String findPhones() throws Exception {
		List<Phone> phones = this.phoneService.findPhones();
		for (Phone phone : phones) {
			if (phone != null) {
				this.result = "success";
			} else {
				this.result = "error";
			}
		}

		return "success";
	}

	public int getMessModelId() {
		return this.messModelId;
	}

	public void setMessModelId(int messModelId) {
		this.messModelId = messModelId;
	}

	public Integer getPhoneId() {
		return this.PhoneId;
	}

	public void setPhoneId(Integer PhoneId) {
		this.PhoneId = PhoneId;
	}

	public String getPhoneName() {
		return this.PhoneName;
	}

	public void setPhoneName(String PhoneName) {
		this.PhoneName = PhoneName;
	}

	public String getPhonePassword() {
		return this.PhonePassword;
	}

	public void setPhonePassword(String phonePassword) {
		this.PhonePassword = phonePassword;
	}

	public String getPhoneCategory() {
		return this.PhoneCategory;
	}

	public void setPhoneCategory(String phoneCategory) {
		this.PhoneCategory = phoneCategory;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMobiles() {
		return this.mobiles;
	}

	public void setMobiles(String mobiles) {
		this.mobiles = mobiles;
	}

	public int getIsOpen() {
		return this.isOpen;
	}

	public void setIsOpen(int isOpen) {
		this.isOpen = isOpen;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getYanzhengma() {
		return yanzhengma;
	}

	public void setYanzhengma(String yanzhengma) {
		this.yanzhengma = yanzhengma;
	}


	
}