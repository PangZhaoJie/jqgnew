package com.jqg.struts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;

import com.jqg.pojo.Basicinfor;
import com.jqg.pojo.Certification;
import com.jqg.pojo.Creditlevel;
import com.jqg.pojo.EmailSendLog;
import com.jqg.pojo.Emailmodel;
import com.jqg.pojo.Inbox;
import com.jqg.pojo.Integralcategory;
import com.jqg.pojo.Integraldetail;
import com.jqg.pojo.Integralparameter;
import com.jqg.pojo.Matercertifi;
import com.jqg.pojo.Moneycount;
import com.jqg.pojo.Moneyhistorydetail;
import com.jqg.pojo.Question;
import com.jqg.pojo.Questionset;
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
import com.jqg.service.MatercertifiService;
import com.jqg.service.MoneycountService;
import com.jqg.service.MoneyhistorydetailService;
import com.jqg.service.QuestionService;
import com.jqg.service.QuestionsetService;
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
import com.jqg.service.impl.MatercertifiServiceImpl;
import com.jqg.service.impl.MoneycountServiceImpl;
import com.jqg.service.impl.MoneyhistorydetailServiceImpl;
import com.jqg.service.impl.QuestionServiceImpl;
import com.jqg.service.impl.QuestionsetServiceImpl;
import com.jqg.service.impl.SmtpServiceImpl;
import com.jqg.service.impl.SystemconfServiceImpl;
import com.jqg.service.impl.UservipServiceImpl;
import com.jqg.service.impl.WebsiteServiceImpl;
import com.jqg.util.MailSenderInfo;
import com.jqg.util.SendMail;
import com.jqg.util.SerialNumberUtil;
import com.jqg.util.UploadPhoto;
import com.opensymphony.xwork2.ActionContext;

public class MatercertifiAction extends BaseAction {
	private MatercertifiService matercertifiService = new MatercertifiServiceImpl();
	private CertificationService certificationService = new CertificationServiceImpl();
	private QuestionsetService questionsetService = new QuestionsetServiceImpl();
	private QuestionService questionService = new QuestionServiceImpl();
	private UservipService uservipService = new UservipServiceImpl();
	private IntegralparameterService integralparameterService = new IntegralparameterServiceImpl();
	private CreditlevelService creditlevelService = new CreditlevelServiceImpl();
	private BasicinforService basicinforService = new BasicinforServiceImpl();
	private WebsiteService websiteService = new WebsiteServiceImpl();
	private UservipService userService = new UservipServiceImpl();
	private SystemconfService systemconfService=new SystemconfServiceImpl();
	private String result;
	private Integer materCertifiId;
	private String key;
	private String address;
	private List<Question> questions;
	private Integer questionIdOne;
	private Integer questionIdTwo;
	private String answerOne;
	private String answerTwo;
	private String tip;
	private String realName;
	private String idNum;
	private String phoneNum;
	private String code;
	private File file;
	private String fileFileName;
	private String fileFileContentType;
	private String message = "你已成功上传文件";
	private File file2;
	private String file2FileName;
	private String file2FileContentType;
	private Uservip user;
	private String mark;
	private String mail;
	private EmailmodelService emailmodelService = new EmailmodelServiceImpl();
	private InboxService inboxService = new InboxServiceImpl();
	private InternallettermodelService interService = new InternallettermodelServiceImpl();
	private SmtpService smtpService = new SmtpServiceImpl();
	private IntegralcategoryService integralcategoryService = new IntegralcategoryServiceImpl();
	private IntegraldetailService integraldetailService = new IntegraldetailServiceImpl();
	private HttpServletRequest request;

	

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * 认证中心
	 * 
	 * @return
	 * @throws Exception
	 */
	public String search() throws Exception {
		Uservip uservip = (Uservip) ActionContext.getContext().getSession()
				.get("uservip");
		this.questions = this.questionService.findQuestions();

		this.user = this.uservipService.findUservipByUserid(uservip.getUserId()
				.intValue());
		if ((this.tip != null) && (!"".equals(this.tip.trim()))
				&& ("1".equals(this.tip))) {
			this.tip = "不能设置同样的安全问题";
		}

		return "success";
	}

	public String createMatercertifi() throws Exception {
		Uservip uservip = (Uservip) ActionContext.getContext().getSession()
				.get("uservip");

		Certification certification1 = this.certificationService
				.findCertificationByUserId(uservip.getUserId().intValue());
		Questionset questionset1 = this.questionsetService
				.findQuestionsetByUserId(uservip.getUserId().intValue());

		if (certification1 == null) {
			Certification certification = new Certification();
			certification.setUservip(uservip);
			this.certificationService.createCertification(certification);
		}
		if (questionset1 == null) {
			Questionset questionset = new Questionset();
			questionset.setUservip(uservip);
			this.questionsetService.createQuestionset(questionset);
		}

		return "success";
	}

	public String updateMatercertifi() throws Exception {
		Matercertifi matercertifi = this.matercertifiService
				.findMatercertifiByMaterCertifiId(this.materCertifiId
						.intValue());
		boolean flag = this.matercertifiService
				.updateMatercertifi(matercertifi);
		if (flag) {
			this.result = "success";
		} else {
			this.result = "error";
		}
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
		Uservip uservip = (Uservip) ActionContext.getContext().getSession().get("uservip");
		uservip=uservipService.findUservipByUserid(uservip.getUserId());
		
		String flag = null;
		flag = this.userService.findUservipByMail(this.address);
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
			mailInfo.setSubject(uservip.getUserName()+"您已成功注册，请激活邮箱");

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
			StringBuffer sb2 = new StringBuffer();
			sb2.append(MD5(""+uservip.getUserId())).append("-");
			sb2.append(MD5(this.address)).append("-");
			sb2.append(MD5(uservip.getUserName()));
			SerialNumberUtil snu=new SerialNumberUtil();
			sb.append(basePath + "user/activate?mail="+uservip.getUserId()+"//---"+sb2.toString());
		
			Uservip uservips = this.uservipService
					.findUservipByUserid(uservip.getUserId().intValue());
			uservips.setMail(this.address);
			this.uservipService.updateUservip(uservips);
			ActionContext.getContext().getSession()
					.put("uservip", uservips);
			
			
			String address="点击下面链接激活账号,请尽快激活！ <a href=\""+sb.toString()+"\" ></br>"+sb.toString()+"</a></br>(如果上面链接不能访问，请将该地址手工粘贴到浏览器地址栏再访问)";
			
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

	/**
	 * 问题认证
	 * 
	 * @return
	 * @throws Exception
	 */
	public String questionSet() throws Exception {
		Uservip uservip = (Uservip) ActionContext.getContext().getSession()
				.get("uservip");
		if (this.questionIdOne != this.questionIdTwo) {
			Questionset questionset1 = this.questionsetService
					.findQuestionsetByUserId(uservip.getUserId().intValue());
			Question question1 = this.questionService
					.findQuestionByQuestionId(this.questionIdOne.intValue());
			Question question2 = this.questionService
					.findQuestionByQuestionId(this.questionIdTwo.intValue());
			questionset1.setQuestionByQuestionOneId(question1);
			questionset1.setQuestionByQuestionTwoId(question2);
			this.questionsetService.updateQuestionset(questionset1);

			Uservip uservip1 = this.uservipService.findUservipByUserid(uservip
					.getUserId().intValue());
			uservip1.setQuestionresult(Integer.valueOf(1));

			Inbox inbox = new Inbox();
			inbox.setUservip(uservip);
			inbox.setContent("恭喜你，安全问题设置成功！");
			inbox.setReceiveTime(new Timestamp(new Date().getTime()));
			Website website = this.websiteService.findWebsiteBywebsiteId(1);
			inbox.setSendName(website.getWebName());
			inbox.setTitle("安全问题设置成功");
			inbox.setStatus(Integer.valueOf(0));
			this.inboxService.createInbox(inbox);

			Integralparameter integralparameter = this.integralparameterService
					.findIntegralparameterByintegralPid(6);
			Integer integral = Integer.valueOf(0);
			if (uservip1.getItegral() != null)
				integral = Integer.valueOf(uservip1.getItegral().intValue()
						+ Integer.parseInt(integralparameter
								.getIntegralPnumber()));
			else {
				integral = Integer.valueOf(Integer.parseInt(integralparameter
						.getIntegralPnumber()));
			}
			uservip1.setItegral(integral);
			List creditlevels = this.creditlevelService.findCreditlevels();
			for (int i = 0; i < creditlevels.size(); i++) {
				Creditlevel creditlevel1 = (Creditlevel) creditlevels.get(i);
				int start = Integer
						.parseInt(creditlevel1.getCreditLevelStart());
				int end = Integer.parseInt(creditlevel1.getCreditLevelEnd());
				if ((integral.intValue() >= start)
						&& (integral.intValue() <= end)) {
					uservip1.setCreditlevel(creditlevel1);
					break;
				}
			}
			this.uservipService.updateUservip(uservip1);
			ActionContext.getContext().getSession().put("uservip", uservip1);
			this.result = "success";
		} else {
			this.result = "error";

			this.tip = "1";
		}
		this.mark = "6";

		return this.result;
	}

	/**
	 * 视频认证
	 * 
	 * @return
	 * @throws Exception
	 */
	public String videoSet() throws Exception {
		Uservip uservip = (Uservip) ActionContext.getContext().getSession()
				.get("uservip");
		Uservip uservip1 = this.uservipService.findUservipByUserid(uservip
				.getUserId().intValue());
		uservip1.setVideoTime(new Timestamp(new Date().getTime()));
		uservip1.setVideoResult("0");
		this.uservipService.updateUservip(uservip1);
		ActionContext.getContext().getSession().put("uservip", uservip1);

		this.mark = "5";
		return "success";
	}

	/**
	 * 现场认证
	 * 
	 * @return
	 * @throws Exception
	 */
	public String sceneSet() throws Exception {
		Uservip uservip = (Uservip) ActionContext.getContext().getSession()
				.get("uservip");
		Uservip uservip1 = this.uservipService.findUservipByUserid(uservip
				.getUserId().intValue());
		uservip1.setSceneTime(new Timestamp(new Date().getTime()));
		uservip1.setSceneResult("0");
		this.uservipService.updateUservip(uservip1);
		ActionContext.getContext().getSession().put("uservip", uservip1);

		this.mark = "4";
		return "success";
	}

	/**
	 * 根据身份证号查找
	 * @return
	 * @throws Exception
	 */
	public String findBasicinforByIdNum() throws Exception {
		String flag = null;
		Basicinfor bf = this.basicinforService.findBasicinforByIdNum(this.idNum);
		if (bf==null)
			this.result = "{\"result\":\"0\"}";
		else {
			this.result = "{\"result\":\"1\"}";
		}
		return "success";
	}
	/**
	 * 认证中心
	 * 
	 * @return
	 * @throws Exception
	 */
	public String certification() throws Exception {
		Uservip uservip = (Uservip) ActionContext.getContext().getSession().get("uservip");
		Basicinfor bas = this.basicinforService.findBasicinforByUserId(uservip.getUserId());
		Basicinfor bf = this.basicinforService.findBasicinforByIdNum(this.idNum);
		if (bf!=null){
//			this.setMessage("身份证号已经被实名认证","/user/approve.jsp","3");
//			return "jump";
			this.mark="11";
		}else if(bas.getIdNum()!=null && !"".equals(bas.getIdNum()) && ("2".equals(uservip.getNameResult()) || "1".equals(uservip.getNameResult()))){
			this.mark="22";//已经实名认证或是实名认识已经提交 申请的都不能再进行认证提交
		}else{
		
		Certification certification = this.certificationService
				.findCertificationByUserId(uservip.getUserId().intValue());
		certification.setRealName(this.realName);
		certification.setIdNum(this.idNum);
		certification.setUpLoadTime(new Timestamp(new Date().getTime()));
		certification.setExamineStatus(Integer.valueOf(0));
		this.certificationService.updateCertification(certification);
		Uservip uservip1 = this.uservipService.findUservipByUserid(uservip
				.getUserId().intValue());
		uservip1.setNameResult("1");
		this.uservipService.updateUservip(uservip1);
		
		this.user = this.uservipService.findUservipByUserid(uservip.getUserId()
				.intValue());
		ActionContext.getContext().getSession().put("uservip", this.user);

		
		
		this.mark = "3";
		}
		return "success";
	}

	/**
	 * 图片ajax上传，无刷新
	 * 
	 * @return
	 * @throws Exception
	 */
	public String fileUploadAction() throws Exception {
		String path = ServletActionContext.getRequest().getRealPath("/images");
		try {
			File f = getFile();
			File f2 = getFile2();
			if (f != null) {
				if ((!getFileFileName().endsWith(".jpg"))
						&& (!getFileFileName().endsWith(".jpeg"))
						&& (!getFileFileName().endsWith(".bmp"))
						&& (!getFileFileName().endsWith(".gif"))
						&& (!getFileFileName().endsWith(".png"))) {
					this.message = "对不起,你上传的文件格式不允许!!!";
					return "error";
				}
			} else if ((f2 != null) && (!getFile2FileName().endsWith(".jpg"))
					&& (!getFile2FileName().endsWith(".jpeg"))
					&& (!getFile2FileName().endsWith(".bmp"))
					&& (!getFile2FileName().endsWith(".gif"))
					&& (!getFile2FileName().endsWith(".png"))) {
				this.message = "对不起,你上传的图片格式不允许!!!";
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

	/**
	 * 手机认证
	 */
	public String phoneSet() throws Exception {
		Uservip uservip = (Uservip) ActionContext.getContext().getSession()
				.get("uservip");
		String pcode = (String) ActionContext.getContext().getSession()
				.get("code" + uservip.getUserId());
		String phone=(String) ActionContext.getContext().getSession()
				.get("data");
		if (pcode.trim().equals(this.code.trim())) {
			Uservip user1 = this.uservipService.findUservipByUserid(uservip.getUserId().intValue());
			if("1".equals(user1.getPhoneResult())){
				this.result = "repeat";
				this.mark = "2";
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
				integraldetail.setRemainIntegral(uservip.getInvestItegral() + uservip.getItegral() + Double.valueOf(Integer.valueOf(Integer.parseInt(integralparameter.getIntegralPnumber()))));
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
			this.result = "success";
		} else {
			this.result = "error";
		}
		this.mark = "2";
		return this.result;
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
	   * 设置vip
	   */
	  public String updateUservip() throws Exception{
		  HttpServletRequest request=ServletActionContext.getRequest();
		  Integer vipMonth=(Integer.parseInt(request.getParameter("months")));
		  Double parvalue=Double.valueOf(request.getParameter("parvalue"));
		  Uservip uservip = (Uservip) ActionContext.getContext().getSession()
					.get("uservip");
		  MoneycountService moneycountService=new MoneycountServiceImpl();
		  Moneycount moneycount=null;
		  moneycount= moneycountService.findMoneycountByuserId(uservip.getUserId());
		  if((vipMonth*parvalue)>moneycount.getAvailableMoney()){
			  this.setMessage("申请金额大于余额，请充值后操作", "/user/user_vip.jsp", "3");
			  return "jump";
		  }
		  Uservip uservips = this.uservipService.findUservipByUserid(uservip.getUserId().intValue());
		  uservips.setIsApplyVIP(1);//设置为申请状态1
		  uservips.setVipMonthe(vipMonth);//vip购买月数
		  boolean flag =this.uservipService.updateUservip(uservips) ;
		  ActionContext.getContext().getSession().put("uservip", uservips);
		  if (flag)
		    {
		      this.result = "success";
		    }
		    else
		    {
		      this.result = "error";
		    }
		 return this.result;
	  }
	  /**
	   * 根据用户id查找信息
	   * @return user  
	   */
	  public String findUserInfo() throws Exception{
		  Uservip uservip = (Uservip) ActionContext.getContext().getSession()
					.get("uservip");
	      Uservip uservips = this.uservipService.findUservipByUserid(uservip.getUserId().intValue());
		  Systemconf systemconf=this.systemconfService.findSystemconfByParname("con_vip_buy");//vip购买金额
		  Systemconf systemconf1=this.systemconfService.findSystemconfByParname("con_vip_isGift");//是否赠送vip
		  Systemconf systemconf2=this.systemconfService.findSystemconfByParname("con_vip_money"); //赠送vip时间
	      ActionContext.getContext().getSession().put("uservip", uservips);
		  ActionContext.getContext().getSession().put("systemconf", systemconf);
		  ActionContext.getContext().getSession().put("systemconf1", systemconf1);
		  ActionContext.getContext().getSession().put("systemconf2", systemconf2);
		  return "success";		  
	  }
	  
	  
	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Integer getMaterCertifiId() {
		return this.materCertifiId;
	}

	public void setMaterCertifiId(Integer materCertifiId) {
		this.materCertifiId = materCertifiId;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Question> getQuestions() {
		return this.questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public Integer getQuestionIdOne() {
		return this.questionIdOne;
	}

	public void setQuestionIdOne(Integer questionIdOne) {
		this.questionIdOne = questionIdOne;
	}

	public Integer getQuestionIdTwo() {
		return this.questionIdTwo;
	}

	public void setQuestionIdTwo(Integer questionIdTwo) {
		this.questionIdTwo = questionIdTwo;
	}

	public String getAnswerOne() {
		return this.answerOne;
	}

	public void setAnswerOne(String answerOne) {
		this.answerOne = answerOne;
	}

	public String getAnswerTwo() {
		return this.answerTwo;
	}

	public void setAnswerTwo(String answerTwo) {
		this.answerTwo = answerTwo;
	}

	public String getTip() {
		return this.tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIdNum() {
		return this.idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
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

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
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

	@JSON(serialize = false)
	public Uservip getUser() {
		return this.user;
	}

	public void setUser(Uservip user) {
		this.user = user;
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

	@JSON(serialize = false)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
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