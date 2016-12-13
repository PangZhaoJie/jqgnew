package com.jqg.struts;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.jqg.pojo.Basicinfor;
import com.jqg.pojo.Bonus;
import com.jqg.pojo.Creditlevel;
import com.jqg.pojo.Inbox;
import com.jqg.pojo.Integralcategory;
import com.jqg.pojo.Integraldetail;
import com.jqg.pojo.Integralparameter;
import com.jqg.pojo.Internallettermodel;
import com.jqg.pojo.Manager;
import com.jqg.pojo.Moneycount;
import com.jqg.pojo.Moneyhistorydetail;
import com.jqg.pojo.Promrewardbypeople;
import com.jqg.pojo.Systemconf;
import com.jqg.pojo.Tender;
import com.jqg.pojo.UserLog;
import com.jqg.pojo.Uservip;
import com.jqg.pojo.Website;
import com.jqg.service.BasicinforService;
import com.jqg.service.BonusService;
import com.jqg.service.CertificationService;
import com.jqg.service.CreditlevelService;
import com.jqg.service.EmailmodelService;
import com.jqg.service.InboxService;
import com.jqg.service.IntegralcategoryService;
import com.jqg.service.IntegraldetailService;
import com.jqg.service.IntegralparameterService;
import com.jqg.service.InternallettermodelService;
import com.jqg.service.LatestnewsService;
import com.jqg.service.LssuingService;
import com.jqg.service.ManagerService;
import com.jqg.service.MoneycountService;
import com.jqg.service.MoneyhistorydetailService;
import com.jqg.service.PersonalbankinforService;
import com.jqg.service.PromrewardbypeopleService;
import com.jqg.service.QuestionsetService;
import com.jqg.service.RecordService;
import com.jqg.service.RepaynoteService;
import com.jqg.service.SmtpService;
import com.jqg.service.SystemconfService;
import com.jqg.service.TenderService;
import com.jqg.service.UserLogService;
import com.jqg.service.UservipService;
import com.jqg.service.UservipnoteService;
import com.jqg.service.WebsiteService;
import com.jqg.service.impl.BasicinforServiceImpl;
import com.jqg.service.impl.BonusServiceImpl;
import com.jqg.service.impl.CertificationServiceImpl;
import com.jqg.service.impl.CreditlevelServiceImpl;
import com.jqg.service.impl.EmailmodelServiceImpl;
import com.jqg.service.impl.InboxServiceImpl;
import com.jqg.service.impl.IntegralcategoryServiceImpl;
import com.jqg.service.impl.IntegraldetailServiceImpl;
import com.jqg.service.impl.IntegralparameterServiceImpl;
import com.jqg.service.impl.InternallettermodelServiceImpl;
import com.jqg.service.impl.LatestnewsServiceImpl;
import com.jqg.service.impl.LssuingServiceImpl;
import com.jqg.service.impl.ManagerServiceImpl;
import com.jqg.service.impl.MoneycountServiceImpl;
import com.jqg.service.impl.MoneyhistorydetailServiceImpl;
import com.jqg.service.impl.PersonalbankinforServiceImpl;
import com.jqg.service.impl.PromrewardbypeopleServiceImpl;
import com.jqg.service.impl.QuestionsetServiceImpl;
import com.jqg.service.impl.RecordServiceImpl;
import com.jqg.service.impl.RepaynoteServiceImpl;
import com.jqg.service.impl.SmtpServiceImpl;
import com.jqg.service.impl.SystemconfServiceImpl;
import com.jqg.service.impl.TenderServiceImpl;
import com.jqg.service.impl.UserLogServiceImpl;
import com.jqg.service.impl.UservipServiceImpl;
import com.jqg.service.impl.UservipnoteServiceImpl;
import com.jqg.service.impl.WebsiteServiceImpl;
import com.jqg.util.MD5Util;
import com.opensymphony.xwork2.ActionContext;

public class UservipAction extends BaseAction {
	private Integer userId;
	private String userName;
	private String userName1;
	
	public String getUserName1() {
		return userName1;
	}

	public void setUserName1(String userName1) {
		this.userName1 = userName1;
	}
	private String password;
	private String mail;
	private String resultString = "-1";
	private String resultPay = "-1";
	private String newpwd;
	private String payPwd;
	private String PhoneCode;
	private String newPayPwd;
	private String sessionId;
	private String moblie;
	private String mark = "-1";
	private Moneycount moneycount;
	private InboxService inboxService = new InboxServiceImpl();
	private MoneycountService moneycountService = new MoneycountServiceImpl();
	private IntegralparameterService integralparameterService = new IntegralparameterServiceImpl();
	private CreditlevelService creditlevelService = new CreditlevelServiceImpl();
	private InternallettermodelService internallettermodelService = new InternallettermodelServiceImpl();
	private EmailmodelService emailmodelService = new EmailmodelServiceImpl();
	private SmtpService smtpService = new SmtpServiceImpl();
	private UservipService userService = new UservipServiceImpl();
	private RepaynoteService repaynoteService = new RepaynoteServiceImpl();
	private LssuingService lssuingService = new LssuingServiceImpl();
	private RecordService recordService = new RecordServiceImpl();
	private PromrewardbypeopleService promrewardbypeopleService = new PromrewardbypeopleServiceImpl();
	private BonusService bonusService = new BonusServiceImpl();
	private BasicinforService basicinforService = new BasicinforServiceImpl();
	private PersonalbankinforService personalbankinforService = new PersonalbankinforServiceImpl();
	private WebsiteService websiteService = new WebsiteServiceImpl();
	private TenderService tenderService = new TenderServiceImpl();
	private UservipnoteService uservipnoteService = new UservipnoteServiceImpl();
	private CertificationService certificationService = new CertificationServiceImpl();
	private QuestionsetService questionsetService = new QuestionsetServiceImpl();
	private SystemconfService systemconfService = new SystemconfServiceImpl();
	private IntegralcategoryService integralcategoryService = new IntegralcategoryServiceImpl();
	private IntegraldetailService integraldetailService = new IntegraldetailServiceImpl();
	private LatestnewsService latestnewsService = new LatestnewsServiceImpl();
	private UserLogService userLogService = new UserLogServiceImpl();

	private String result;
	private Uservip user;
	private Creditlevel creditlevel;
	private Integer unreadInbox = Integer.valueOf(0);
	private Integer overTimePay = Integer.valueOf(0);
	private Integer waitCount = Integer.valueOf(0);
	private Integer currentCount;
	private Integer currentGetCount;
	private Integer referId;
	private int currentPage;
	private int pageSize = 5;
	private int totalPage;
	private int current;
	private int total;
	private Integer totalMoney = Integer.valueOf(0);
	private Integer basicCheck;
	private Integer bankNum;
	private OperateImage image;
	private File pic;
	private String picFileName;
	private String phoneOne;
	private String phoneTwo;
	private int flag;
	private String code;
	private HttpServletRequest request;
	private String phoneNum;
	private Integer kefuId;

	public Integer getKefuId() {
		return kefuId;
	}

	public void setKefuId(Integer kefuId) {
		this.kefuId = kefuId;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public int getFlag() {
		return this.flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getPhoneOne() {
		return this.phoneOne;
	}

	public void setPhoneOne(String phoneOne) {
		this.phoneOne = phoneOne;
	}

	public String getPhoneTwo() {
		return this.phoneTwo;
	}

	public void setPhoneTwo(String phoneTwo) {
		this.phoneTwo = phoneTwo;
	}

	public OperateImage getImage() {
		return this.image;
	}

	public void setImage(OperateImage image) {
		this.image = image;
	}

	public File getPic() {
		return this.pic;
	}

	public void setPic(File pic) {
		this.pic = pic;
	}

	public String getPicFileName() {
		return this.picFileName;
	}

	public void setPicFileName(String picFileName) {
		this.picFileName = picFileName;
	}

	public String getMark() {
		return this.mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getNewpwd() {
		return this.newpwd;
	}

	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
	}

	public String getResultString() {
		return this.resultString;
	}

	public void setResultString(String resultString) {
		this.resultString = resultString;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPayPwd() {
		return this.payPwd;
	}

	public void setPayPwd(String payPwd) {
		this.payPwd = payPwd;
	}

	public String getNewPayPwd() {
		return this.newPayPwd;
	}

	public void setNewPayPwd(String newPayPwd) {
		this.newPayPwd = newPayPwd;
	}

	public String getResultPay() {
		return this.resultPay;
	}

	public void setResultPay(String resultPay) {
		this.resultPay = resultPay;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getPhoneCode() {
		return PhoneCode;
	}

	public void setPhoneCode(String phoneCode) {
		PhoneCode = phoneCode;
	}

	public String getMoblie() {
		return moblie;
	}

	public void setMoblie(String moblie) {
		this.moblie = moblie;
	}

	/**
	 * 用户登录
	 * 
	 * @return
	 */
	public String toJspPage() {
		if (this.flag == 1)
			return "success1";
		if (this.flag == 2)
			return "success2";
		if (this.flag == 3)
			return "success3";
		if (this.flag == 4)
			return "success4";
		if (this.flag == 5) {
			return "success5";
		}
		return "error";
	}

	/**
	 * 图片
	 * 
	 * @return
	 * @throws Exception
	 */
	public String picture() throws Exception {
		Uservip uservip1 = this.userService.findUservipByUserid(this.userId
				.intValue());
		ActionContext.getContext().getSession().put("uservip", uservip1);
		return "success";
	}

	/**
	 * 用户注册
	 * 
	 * @return
	 * @throws Exception
	 */
	public String createUservip() throws Exception { 
		request = ServletActionContext.getRequest();
		this.sessionId = request.getSession().getId();
		String ZCcode = (String) ActionContext.getContext().getSession().get("ZCcode" + this.sessionId);
		String flg = "0";// 手机邮箱判断
		if (this.code != null && !"".equals(this.code)
				&& (ZCcode != null && !"".equals(ZCcode))) {
			if (!ZCcode.equals(this.code)) {
				this.result = "error";
				
				return  "error";
			} 
//			else {
//				this.result = "success";
//			}
			flg = "1";
		}
		String flag = null;
		flag = this.userService.findUservipByUserName(this.userName);
		if ("1".equals(flag)) {
			this.result = "userError";
			return "userError";
		} else {
			Uservip uservip = new Uservip();
			uservip.setUserName(this.userName);
			uservip.setPassword(MD5Util.md5(this.password));
			uservip.setPayPwd(MD5Util.md5(this.password));
			if (this.kefuId != null && !"".equals(this.kefuId)) {
				ManagerService managerService = new ManagerServiceImpl();
				Manager man = managerService.findManagerByManagerId(this.kefuId
						.intValue());
				uservip.setManager(man);
			}
			// if(this.mail!=null&&!"".equals(this.mail)){
			// flg="2";
			// uservip.setMail(this.mail);
			// }
			if ("1".equals(flg)) {
				uservip.setPhoneResult("1");
				Integralparameter integralparameter = this.integralparameterService
						.findIntegralparameterByintegralPid(1);
				Integer integral = Integer.valueOf(0);
				if (uservip.getItegral() != null)
					integral = Integer.valueOf(uservip.getItegral().intValue()
							+ Integer.parseInt(integralparameter
									.getIntegralPnumber()));
				else {
					integral = Integer.valueOf(Integer
							.parseInt(integralparameter.getIntegralPnumber()));
				}
				uservip.setItegral(integral);
			}
			if (this.referId != null) {
				Uservip refer = this.userService
						.findUservipByUserid(this.referId.intValue());
				uservip.setUservip(refer);
				if (this.referId != null) {
					String sql = "select * from Promrewardbypeople p";
					System.out.println(sql);
					List promrewardbypeopleList = this.promrewardbypeopleService
							.findPromrewardbySql(sql);
					Promrewardbypeople pp = new Promrewardbypeople();
					if ((promrewardbypeopleList != null)
							&& (promrewardbypeopleList.size() > 0)) {
						pp = (Promrewardbypeople) promrewardbypeopleList.get(0);

						sql = "select * from bonus b where b.userId="
								+ this.referId
								+ " and timestampdiff(DAY,DATE_FORMAT(b.bonusChangeTime,'%Y-%m-%d'),DATE_FORMAT(NOW(),'%Y-%m-%d')) >=0"
								+ " and timestampdiff(DAY,DATE_FORMAT(NOW(),'%Y-%m-%d'),DATE_FORMAT(b.bonusChangeTime,'%Y-%m-%d')) >=0";
						List bonusList = this.bonusService.findBonusBySql(sql);
						System.out.println(sql);
						Bonus bonus = new Bonus();
						// 推广奖励
						if ((bonusList != null) && (bonusList.size() > 0)) {
							bonus = (Bonus) bonusList.get(0);
							bonus.setNum(Integer.valueOf(bonus.getNum()
									.intValue() + 1));
							bonus.setBonusChangeTime(new Timestamp(new Date()
									.getTime()));
						} else {
							bonus.setNum(Integer.valueOf(1));
							bonus.setUservip(this.userService
									.findUservipByUserid(this.referId
											.intValue()));
							bonus.setStatus("0");
							bonus.setBonusChangeTime(new Timestamp(new Date()
									.getTime()));
							bonus.setBonusExamine("推广奖励");
						}
						for (int i = 0; i < promrewardbypeopleList.size(); i++) {
							pp = (Promrewardbypeople) promrewardbypeopleList
									.get(i);
							if (bonus.getNum() == pp.getPeopleNum()) {
								String a = Double.toString(pp.getReward()
										.doubleValue());
								bonus.setRquestBonus(Integer.valueOf(Integer.parseInt(a
										.substring(0, a.indexOf(".")))));
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
			// 注册时是否赠送vip会员
			Systemconf sysconf = this.systemconfService
					.findSystemconfByParname("con_vip_isGift");
			if ("1".equals(sysconf.getParvalue())) {
				Systemconf sysconf1 = this.systemconfService
						.findSystemconfByParname("con_vip_money");
				Calendar calender = Calendar.getInstance();
				calender.setTime(new Date());
				calender.add(Calendar.MONTH,
						Integer.valueOf(sysconf1.getParvalue()));
				long end = calender.getTimeInMillis();
				Timestamp scurrtest = new Timestamp(end);
				uservip.setIsVIP(Integer.valueOf(sysconf.getParvalue()));
				uservip.setVipMonthe(Integer.valueOf(sysconf1.getParvalue()));
				uservip.setVipEndTime(scurrtest);
			}
			uservip.setSceneResult("1");
			uservip.setVideoResult("1");
			Integer userId = this.userService.createUservip(uservip);
			// 手机认证通过保存积分到积分表
			Uservip uservip3 = this.userService.findUservipByUserid(userId);
			if ("1".equals(uservip3.getPhoneResult())) {
				Integralparameter integralparameter = this.integralparameterService
						.findIntegralparameterByintegralPid(1);
				// Integer integral = Integer.valueOf(0);
				// if (uservip.getItegral() != null)
				// integral = Integer.valueOf(uservip.getItegral().intValue()+
				// Integer.parseInt(integralparameter.getIntegralPnumber()));
				// else {
				// integral =
				// Integer.valueOf(Integer.parseInt(integralparameter.getIntegralPnumber()));
				// }
				Integralcategory integralcategory = this.integralcategoryService
						.findIntegralcategoryByintCategoryId(Integer.valueOf(1));
				Integraldetail integraldetail = new Integraldetail();
				integraldetail.setUservip(uservip3);
				integraldetail.setIntegralcategory(integralcategory);
				integraldetail.setIntegralQuota(Double.valueOf(Integer
						.valueOf(Integer.parseInt(integralparameter
								.getIntegralPnumber()))));
				integraldetail.setIntegralTime(new Timestamp(new Date()
						.getTime()));
				integraldetail.setIntegralReason(integralparameter
						.getIntegralPdescription());
				if (uservip.getInvestItegral() != null
						&& uservip.getItegral() != null) {
					integraldetail.setRemainIntegral(uservip.getInvestItegral()
							+ uservip.getItegral()
							+ Double.valueOf(Integer.valueOf(Integer
									.parseInt(integralparameter
											.getIntegralPnumber()))));
				} else if (uservip.getInvestItegral() == null
						&& uservip.getItegral() != null) {
					integraldetail.setRemainIntegral(Double.valueOf(uservip
							.getItegral()));
				} else {
					integraldetail.setRemainIntegral(Double.valueOf(Integer
							.valueOf(Integer.parseInt(integralparameter
									.getIntegralPnumber()))));
				}
				this.integraldetailService.createIntegraldetail(integraldetail);
			}

			if (userId != null) {
				uservip.setUserId(userId);
				ActionContext.getContext().getSession().put("uservip", uservip);
				Moneycount mc = this.moneycountService
						.findMoneycountByuserId(userId.intValue());

				if (mc == null) {
					Moneycount moneycount1 = new Moneycount();
					moneycount1.setUservip(uservip);

					// 注册时间比较如果小于则赠送1000体验金额
					Date timedate = new Date();
					SimpleDateFormat df = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					Systemconf syc = systemconfService
							.findSystemconfByParname("con_reg_reward_limittime");
					Date date2 = df.parse(syc.getParvalue());
					if (timedate.getTime() <= date2.getTime()) {
						Systemconf sycf = systemconfService
								.findSystemconfByParname("con_reg_reward");
						double experienceMoney = Double.parseDouble(sycf
								.getParvalue());
						moneycount1.setExperienceMoney(experienceMoney);
					}
					this.moneycountService.createMoneycount(moneycount1);
					this.moneycount = this.moneycountService
							.findMoneycountByuserId(userId.intValue());
				} else {
					this.moneycount = mc;
				}

				Basicinfor basicinfor1 = new Basicinfor();
				basicinfor1.setUservip(uservip);
				basicinfor1.setSex("0");
				// //如果邮箱不是空
				// if("1".equals(flg)){
				// basicinfor1.setPhoneNum(this.moblie);
				// }
				basicinfor1.setPhoneNum(this.phoneNum);
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
					Website website = this.websiteService
							.findWebsiteBywebsiteId(1);
					inbox.setSendName(website.getWebName());
					inbox.setTitle("注册成功");
					inbox.setStatus(Integer.valueOf(0));
					this.inboxService.createInbox(inbox);
				}

			}
			this.result = "success";
		}
		return this.result;

	}

	// public String register() throws Exception {
	// this.userName =
	// ((String)ActionContext.getContext().getSession().get("userName"));
	// this.password =
	// ((String)ActionContext.getContext().getSession().get("password"));
	// this.referId =
	// ((Integer)ActionContext.getContext().getSession().get("referId"));
	// Uservip uservip = new Uservip();
	// uservip.setUserName(this.userName);
	// uservip.setPassword(MD5Util.md5(this.password));
	// uservip.setPayPwd(MD5Util.md5(this.password));
	// uservip.setIsVip(Integer.valueOf(0));
	// uservip.setIsModify(Integer.valueOf(0));
	//
	// uservip.setPhotoOne("/images/mr_tx.jpg");
	// String code1 = (String)ActionContext.getContext().getSession().get("code"
	// + this.userName);
	// if (!this.code.equals(code1)) {
	// this.result = "error";
	// return this.result;
	// }
	//
	// ActionContext.getContext().getSession().remove("userName");
	// ActionContext.getContext().getSession().remove("password");
	// ActionContext.getContext().getSession().remove("referId");
	// ActionContext.getContext().getSession().remove("code" + this.userName);
	//
	// uservip.setPhoneResult("1");
	//
	// Integralparameter integralparameter =
	// this.integralparameterService.findIntegralparameterByintegralPid(1);
	// uservip.setItegral(Integer.valueOf(Integer.parseInt(integralparameter.getIntegralPnumber())));
	//
	// uservip.setPhotoThree(integralparameter.getIntegralPnumber());
	// if (this.referId != null) {
	// Uservip refer =
	// this.userService.findUservipByUserid(this.referId.intValue());
	// uservip.setUservip(refer);
	// if (this.referId != null) {
	// String sql =
	// "select * from Promrewardbypeople p where timestampdiff(DAY,DATE_FORMAT(p.startTime,'%Y-%m-%d'),DATE_FORMAT(NOW(),'%Y-%m-%d')) >=0 and timestampdiff(DAY,DATE_FORMAT(NOW(),'%Y-%m-%d'),DATE_FORMAT(p.endTime,'%Y-%m-%d')) >=0";
	//
	// List promrewardbypeopleList =
	// this.promrewardbypeopleService.findPromrewardbySql(sql);
	// Promrewardbypeople pp = new Promrewardbypeople();
	// if ((promrewardbypeopleList != null) && (promrewardbypeopleList.size() >
	// 0)) {
	// pp = (Promrewardbypeople)promrewardbypeopleList.get(0);
	//
	// sql = "select * from bonus b where b.userId=" + this.referId +
	// " and timestampdiff(DAY,DATE_FORMAT(b.bonusChangeTime,'%Y-%m-%d'),DATE_FORMAT(NOW(),'%Y-%m-%d')) >=0"
	// +
	// " and timestampdiff(DAY,DATE_FORMAT(NOW(),'%Y-%m-%d'),DATE_FORMAT(b.bonusChangeTime,'%Y-%m-%d')) >=0";
	// List bonusList = this.bonusService.findBonusBySql(sql);
	// Bonus bonus = new Bonus();
	// if ((bonusList != null) && (bonusList.size() > 0)) {
	// bonus = (Bonus)bonusList.get(0);
	// bonus.setNum(Integer.valueOf(bonus.getNum().intValue() + 1));
	// bonus.setBonusChangeTime(new Timestamp(new Date().getTime()));
	// } else {
	// bonus.setNum(Integer.valueOf(1));
	// bonus.setUservip(this.userService.findUservipByUserid(this.referId.intValue()));
	// bonus.setStatus("0");
	// bonus.setBonusChangeTime(new Timestamp(new Date().getTime()));
	// bonus.setBonusExamine("推广奖励");
	// }
	// for (int i = 0; i < promrewardbypeopleList.size(); i++) {
	// pp = (Promrewardbypeople)promrewardbypeopleList.get(i);
	// if (bonus.getNum() == pp.getPeopleNum()) {
	// String a = Double.toString(pp.getReward().doubleValue());
	// bonus.setRquestBonus(Integer.valueOf(Integer.parseInt(a.substring(0,
	// a.indexOf(".")))));
	// }
	// }
	// if (bonus.getBonusId() != null)
	// this.bonusService.updateBonus(bonus);
	// else {
	// this.bonusService.createBonus(bonus);
	// }
	//
	// }
	//
	// }
	//
	// }
	//
	// uservip.setRegisterDate(new Timestamp(new Date().getTime()));
	// Integer userId = this.userService.createUservip(uservip);
	// if (userId != null)
	// {
	// Uservipnote uservipnote1 =
	// this.uservipnoteService.findUservipnoteByUserid(userId.intValue());
	// String ip = "";
	// if (this.request.getHeader("x-forwarded-for") == null)
	// ip = this.request.getRemoteAddr();
	// else {
	// ip = this.request.getHeader("x-forwarded-for");
	// }
	// if (uservipnote1 == null) {
	// Uservipnote uservipnote = new Uservipnote();
	// uservipnote.setLoginDate(new Timestamp(new Date().getTime()));
	// uservipnote.setUservip(uservip);
	// uservipnote.setIp(ip);
	// this.uservipnoteService.updateUservipnote(uservipnote);
	// ActionContext.getContext().getSession().put("uservipnote", uservipnote);
	// } else {
	// uservipnote1.setLoginDate(new Timestamp(new Date().getTime()));
	// uservipnote1.setIp(ip);
	// this.uservipnoteService.updateUservipnote(uservipnote1);
	// ActionContext.getContext().getSession().put("uservipnote", uservipnote1);
	// }
	//
	// uservip.setUserId(userId);
	// ActionContext.getContext().getSession().put("uservip", uservip);
	//
	// Moneycount mc =
	// this.moneycountService.findMoneycountByuserId(userId.intValue());
	// if (mc == null) {
	// Moneycount moneycount1 = new Moneycount();
	// moneycount1.setUservip(uservip);
	// this.moneycountService.createMoneycount(moneycount1);
	// }
	//
	// Basicinfor basicinfor1 = new Basicinfor();
	// basicinfor1.setPhoneNum(this.phoneNum);
	// basicinfor1.setUservip(uservip);
	// basicinfor1.setSex("0");
	// basicinfor1.setMarryStatus("0");
	// basicinfor1.setHighestEdu("0");
	// basicinfor1.setMonthIncome("0");
	// basicinfor1.setRelationOne("0");
	// basicinfor1.setRelationTwo("0");
	// basicinfor1.setRelationThree("0");
	// basicinfor1.setWorkYear("0");
	// basicinfor1.setHouseCondition("0");
	// basicinfor1.setIfByCar("0");
	// basicinfor1.setContributionStatus("0");
	// basicinfor1.setCosignerOneRelation("0");
	// basicinfor1.setCosignerTwoRelation("0");
	// basicinfor1.setCount1(Integer.valueOf(0));
	// basicinfor1.setCount2(Integer.valueOf(0));
	// basicinfor1.setCount3(Integer.valueOf(0));
	// basicinfor1.setCount4(Integer.valueOf(0));
	// basicinfor1.setCount5(Integer.valueOf(0));
	// basicinfor1.setCount6(Integer.valueOf(0));
	// this.basicinforService.createBasicinfor(basicinfor1);
	//
	// for (int i = 1; i < 4; i++) {
	// Informcontent informcontent = new Informcontent();
	// informcontent.setUservip(uservip);
	// informcontent.setInformtype(this.informtypeService.findInformtypeInformtypeId(i));
	// if (i == 2) {
	// informcontent.setGetMessage(Integer.valueOf(1));
	// informcontent.setChongzhiSuccess(Integer.valueOf(1));
	// informcontent.setLoanSuccess(Integer.valueOf(1));
	// informcontent.setMoneyChange(Integer.valueOf(1));
	// informcontent.setTranslateSuccess(Integer.valueOf(1));
	// } else {
	// informcontent.setGetMessage(Integer.valueOf(1));
	// informcontent.setChongzhiSuccess(Integer.valueOf(1));
	// informcontent.setLoanSuccess(Integer.valueOf(1));
	// informcontent.setMoneyChange(Integer.valueOf(1));
	// informcontent.setTranslateSuccess(Integer.valueOf(1));
	// }
	// this.informcontentService.addInformcontent(informcontent);
	// }
	//
	// Certification certification = new Certification();
	// certification.setUservip(uservip);
	// this.certificationService.createCertification(certification);
	// Questionset questionset = new Questionset();
	// questionset.setUservip(uservip);
	// this.questionsetService.createQuestionset(questionset);
	//
	// Internallettermodel internallettermodel =
	// this.internallettermodelService.findInternallettermodelByinterModelId(1);
	// if (internallettermodel.getIsOpen() == 1) {
	// toSenderUtil1.addInbox("注册成功",
	// internallettermodel.getInterModelContent().replace("#UserName#",
	// uservip.getUserName()), uservip.getUserId());
	// Internallettermodel inter = new
	// InternallettermodelServiceImpl().findInternallettermodelByinterModelId(7);
	// toSenderUtil1.addInbox("手机认证成功",
	// inter.getInterModelContent().replace("#UserName#",
	// uservip.getUserName()), uservip.getUserId());
	// }
	// this.result = "success";
	// }
	//
	// return this.result;
	// }

	/**
	 * 激活
	 * 
	 * @return
	 * @throws Exception
	 */
	public String activate() throws Exception {
		
		if(this.mail!=null&&!"".equals(this.mail)){
			String[] sp=this.mail.split("//---");
			Uservip uservip=this.userService.findUservipByUserid(Integer.parseInt(sp[0]));
		if(uservip!=null){
			StringBuffer sb2 = new StringBuffer();
			sb2.append(MD5(""+uservip.getUserId())).append("-");
			sb2.append(MD5(uservip.getMail())).append("-");
			sb2.append(MD5(uservip.getUserName()));
		if(sb2.toString().equals(sp[1])){
		if ((uservip.getEnable() != null)
				&& (uservip.getEnable().intValue() == 1)) {
			System.out.println("已经认证");
		} else {
			uservip.setEnable(Integer.valueOf(1));

			Integralparameter integralparameter = this.integralparameterService
					.findIntegralparameterByintegralPid(2);
			// 邮箱认证通过保存积分到积分表
			Integralcategory integralcategory = this.integralcategoryService
					.findIntegralcategoryByintCategoryId(Integer.valueOf(1));
			Integraldetail integraldetail = new Integraldetail();
			integraldetail.setUservip(uservip);
			integraldetail.setIntegralcategory(integralcategory);
			integraldetail.setIntegralQuota(Double.valueOf(Integer
					.valueOf(Integer.parseInt(integralparameter
							.getIntegralPnumber()))));
			integraldetail.setIntegralTime(new Timestamp(new Date().getTime()));
			integraldetail.setIntegralReason(integralparameter
					.getIntegralPdescription());
			if (uservip.getInvestItegral() != null
					&& uservip.getItegral() != null) {
				integraldetail.setRemainIntegral(uservip.getInvestItegral()
						+ uservip.getItegral()
						+ Double.valueOf(Integer.valueOf(Integer
								.parseInt(integralparameter
										.getIntegralPnumber()))));
			} else if (uservip.getInvestItegral() == null
					&& uservip.getItegral() != null) {
				integraldetail.setRemainIntegral(Double.valueOf(uservip
						.getItegral()
						+ Double.valueOf(Integer.valueOf(Integer
								.parseInt(integralparameter
										.getIntegralPnumber())))));
			} else if (uservip.getInvestItegral() != null
					&& uservip.getItegral() == null) {
				integraldetail.setRemainIntegral(uservip.getInvestItegral()
						+ Double.valueOf(Integer.valueOf(Integer
								.parseInt(integralparameter
										.getIntegralPnumber()))));
			} else {
				integraldetail.setRemainIntegral(Double.valueOf(Integer
						.valueOf(Integer.parseInt(integralparameter
								.getIntegralPnumber()))));
			}
			this.integraldetailService.createIntegraldetail(integraldetail);
			Integer integral = Integer.valueOf(0);
			if (uservip.getItegral() != null)
				integral = Integer.valueOf(uservip.getItegral().intValue()
						+ Integer.parseInt(integralparameter
								.getIntegralPnumber()));
			else {
				integral = Integer.valueOf(Integer.parseInt(integralparameter
						.getIntegralPnumber()));
			}
			uservip.setItegral(integral);
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
			uservip.setCreditlevel(creditlevel);
			Inbox inbox = new Inbox();
			inbox.setUservip(uservip);
			inbox.setContent("恭喜你，邮箱认证成功！");
			inbox.setReceiveTime(new Timestamp(new Date().getTime()));
			Website website = this.websiteService.findWebsiteBywebsiteId(1);
			inbox.setSendName(website.getWebName());
			inbox.setTitle("邮箱认证成功");
			inbox.setStatus(Integer.valueOf(0));
			this.inboxService.createInbox(inbox);

			boolean flag = this.userService.updateUservip(uservip);
			// 调用认证奖励
			this.authentic_reward(uservip);
			if (flag) {
				ActionContext.getContext().getSession().put("uservip", uservip);
				this.result = "success";
			} else {
				this.result = "error";
			}
		}

		return "success";
		}else{
			this.setMessage("用户不正确", "/user/search.action?mark=1", "3");
			return "jump";
		}
	}else{
		this.setMessage("加密参数不正确", "/user/search.action?mark=1", "3");
		return "jump";
	}
	}else{
		this.setMessage("参数不正确", "/user/search.action?mark=1", "3");
		return "jump";
	}
	
}

	/**
	 * 处理认证奖励
	 * 
	 * @param uservip
	 * @throws Exception
	 */
	public void authentic_reward(Uservip uservip) throws Exception {
		if (uservip != null && uservip.getUserId() != null
				&& uservip.getUserId() > 0 && uservip.getNameResult() != null
				&& "2".equals(uservip.getNameResult())
				&& uservip.getVideoResult() != null
				&& "1".equals(uservip.getVideoResult())
				&& uservip.getEnable() != null
				&& uservip.getEnable().intValue() == 1
				&& uservip.getSceneResult() != null
				&& "1".equals(uservip.getSceneResult())
				&& uservip.getPhoneResult() != null
				&& "1".equals(uservip.getPhoneResult())) {
			Systemconf sysconf = systemconfService
					.findSystemconfByParname("con_authentic_reward");
			if (sysconf != null && sysconf.getParvalue() != null
					&& !"".equals(sysconf)) {
				MoneyhistorydetailService moneyhistorydetailService = new MoneyhistorydetailServiceImpl();
				double authreward = Double.valueOf(sysconf.getParvalue());
				Moneycount moneycount = this.moneycountService
						.findMoneycountByuserId(uservip.getUserId());
				Moneyhistorydetail moneyhistorydetail = new Moneyhistorydetail();
				moneyhistorydetail.setAffectMoney(authreward);
				moneyhistorydetail.setAvailableBalance(moneycount
						.getAvailableMoney() + authreward);
				moneyhistorydetail.setCollectMoney(moneycount
						.getCollectTotalMoney());
				moneyhistorydetail.setOccurTime(new Timestamp(new Date()
						.getTime()));
				moneyhistorydetail.setFrozenMoney(moneycount.getFrozenMoney());
				moneyhistorydetail.setUservip(uservip);
				moneyhistorydetail.setIntroduction("所有认证成功，发放认证成功奖励");
				moneyhistorydetailService
						.createMoneyhistorydetail(moneyhistorydetail);
				moneycount.setAvailableMoney(moneycount.getAvailableMoney()
						+ authreward);
				moneycount.setTotalMoney(moneycount.getTotalMoney()
						+ authreward);
				this.moneycountService.updateMoneycount(moneycount);
			}
		}
	}

	public String updateUservip() throws Exception {
		Uservip uservip = this.userService.findUservipByUserid(this.userId
				.intValue());

		boolean flag = this.userService.updateUservip(uservip);
		if (flag) {
			this.result = "success";
		} else {
			this.result = "error";
		}
		return this.result;
	}

	public String deleteUservip() throws Exception {
		Uservip uservip = this.userService.findUservipByUserid(this.userId
				.intValue());

		if (uservip != null) {
			this.result = "success";
		} else {
			this.result = "error";
		}
		return this.result;
	}

	public String findUservipByUserid(int userid) throws Exception {
		Uservip uservip = this.userService.findUservipByUserid(userid);

		if (uservip != null) {
			this.result = "success";
		} else {
			this.result = "error";
		}
		return this.result;
	}

	public String findUservips() throws Exception {
		List<Uservip> uservips = this.userService.findUservips();
		for (Uservip uservip : uservips) {
			if (uservip != null) {
				this.result = "success";
			} else {
				this.result = "error";
			}
		}

		return this.result;
	}

	/**
	 * 登录查询
	 * 
	 * @return
	 * @throws Exception
	 */
	public String loginSearch() throws Exception {
		Uservip uservip = (Uservip) ActionContext.getContext().getSession()
				.get("uservip");

		this.moneycount = this.moneycountService.findMoneycountByuserId(uservip
				.getUserId().intValue());
		this.user = this.userService.findUservipByUserid(uservip.getUserId()
				.intValue());
//		if (this.user.getCreditlevel() != null) {
//			this.creditlevel = this.creditlevelService
//					.findCreditlevelBycreditLevelId(this.user.getCreditlevel()
//							.getCreditLevelId().intValue());
//			this.creditlevel.getCreditLevelPicture();
//		}
		if (this.user.getInvestItegral() != null) {
			if(this.user.getInvestItegral()<1000){
				this.creditlevel = this.creditlevelService.findCreditlevelBycreditLevelId(Integer.valueOf(1));
			}else if(this.user.getInvestItegral()>=1000 && this.user.getInvestItegral()<4999){
				this.creditlevel = this.creditlevelService.findCreditlevelBycreditLevelId(Integer.valueOf(2));
			}else if(this.user.getInvestItegral()>=5000 && this.user.getInvestItegral()<9999){
				this.creditlevel = this.creditlevelService.findCreditlevelBycreditLevelId(Integer.valueOf(3));
			}else{
				this.creditlevel = this.creditlevelService.findCreditlevelBycreditLevelId(Integer.valueOf(4));
			}
		}else{
			this.creditlevel = this.creditlevelService.findCreditlevelBycreditLevelId(Integer.valueOf(1));
		}
		

		List<Inbox> list = this.inboxService.findInboxsByUserId(this.user
				.getUserId().intValue());
		if ((list != null) && (list.size() > 0)) {
			for (Inbox inbox : list) {
				if (inbox.getStatus() != null) {
					if (inbox.getStatus().intValue() != 1) {
						this.unreadInbox = Integer.valueOf(this.unreadInbox
								.intValue() + 1);
					}
				} else
					this.unreadInbox = Integer.valueOf(this.unreadInbox
							.intValue() + 1);
			}
		}

		List repays = this.repaynoteService.findRepaynoteByUserId(this.user
				.getUserId().intValue());
		if ((repays != null) && (repays.size() > 0)) {
			this.overTimePay = Integer.valueOf(repays.size());
		}
		List lssus = this.lssuingService.findLssuingsByUserIdAndState(this.user
				.getUserId().intValue());
		if ((lssus != null) && (lssus.size() > 0)) {
			this.waitCount = Integer.valueOf(lssus.size());
		}
		List repayss = this.repaynoteService
				.findRepaynoteByUserIdAndTime(this.user.getUserId().intValue());
		if ((repayss != null) && (repayss.size() > 0)) {
			this.currentCount = Integer.valueOf(repayss.size());
		}
		List recs = this.recordService.findRecordByUserIdAndTime(this.user
				.getUserId().intValue());
		if ((recs != null) && (recs.size() > 0)) {
			this.currentGetCount = Integer.valueOf(recs.size());
		}
		Basicinfor bas = this.basicinforService.findBasicinforByUserId(uservip
				.getUserId().intValue());
		if (bas == null) {
			this.basicCheck = Integer.valueOf(0);
		} else {
			int i = 0;
			if (bas.getCount1() != null) {
				i += bas.getCount1().intValue();
			}
			if (bas.getCount2() != null) {
				i += bas.getCount2().intValue();
			}
			if (bas.getCount3() != null) {
				i += bas.getCount3().intValue();
			}
			if (bas.getCount4() != null) {
				i += bas.getCount4().intValue();
			}
			if (bas.getCount5() != null) {
				i += bas.getCount5().intValue();
			}
			if (bas.getCount6() != null) {
				i += bas.getCount6().intValue();
			}
			this.basicCheck = Integer.valueOf(i);
		}
		List personalbankinfors = this.personalbankinforService
				.findPersonalbankinforsByuser(uservip.getUserId().intValue());
		if ((personalbankinfors != null) && (personalbankinfors.size() > 0))
			this.bankNum = Integer.valueOf(personalbankinfors.size());
		else {
			this.bankNum = Integer.valueOf(0);
		}
		return "success";
	}

	/**
	 * 根据密码和用户名查找用户
	 * 
	 * @return
	 * @throws Exception
	 */
	public String findUservipByPasswordAndUserName() throws Exception {
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

		this.sessionId = request.getSession().getId();
		String code1 = (String) ActionContext.getContext().getSession()
				.get("code1" + this.sessionId);
		if (!this.code.equals(code1)) {
			this.setMessage("验证码错误", "/login/login.jsp", "3");
			return "jump";
		} else {
			Uservip uservip = null;
			if ((this.userName.indexOf("@") >= 0)
					&& (this.userName.indexOf(".com") >= 0))
				uservip = this.userService.findUservipByMailAndPassword(
						this.userName, MD5Util.md5(this.password));
			else {
				uservip = this.userService.findUservipByPasswordAndUserName(
						MD5Util.md5(this.password), this.userName);
			}
			if (uservip != null) {
				int ite = 0;
				if (uservip.getItegral() != null) {
					ite = uservip.getItegral().intValue();
				}
				List creditlevels = this.creditlevelService.findCreditlevels();
				for (int i = 0; i < creditlevels.size(); i++) {
					Creditlevel creditlevel1 = (Creditlevel) creditlevels
							.get(i);
					int start = Integer.parseInt(creditlevel1
							.getCreditLevelStart());
					int end = Integer
							.parseInt(creditlevel1.getCreditLevelEnd());
					if ((ite >= start) && (ite <= end)) {
						uservip.setCreditlevel(creditlevel1);
						break;
					}
				}
				this.userService.updateUservip(uservip);

				ActionContext.getContext().getSession().put("uservip", uservip);

				Moneycount mc = this.moneycountService
						.findMoneycountByuserId(uservip.getUserId().intValue());
				if (mc == null) {
					Moneycount moneycount1 = new Moneycount();
					moneycount1.setUservip(uservip);
					this.moneycountService.createMoneycount(moneycount1);
					this.moneycount = this.moneycountService
							.findMoneycountByuserId(uservip.getUserId()
									.intValue());
				} else {
					this.moneycount = mc;
				}

				UserLog userLog = new UserLog();
				userLog.setLoginTime(new Timestamp(new Date().getTime()));
				userLog.setUservip(uservip);
				userLog.setLoginIp(ip);
				userLog.setDetail("pc登录成功");

				userLogService.addUserLog(userLog);

				this.result = "success";
			} else {
				this.resultString = "用户名或者密码错误";
				this.result = "error";
			}

			return this.result;
		}
	}

	/**
	 * 登录
	 * 
	 * @return
	 * @throws Exception
	 */
	public String login() throws Exception {
		Uservip use = (Uservip) ActionContext.getContext().getSession()
				.get("uservip");
		Uservip uservip = this.userService.findUservipByUserid(use.getUserId()
				.intValue());
		if (uservip != null) {
			int ite = 0;
			if (uservip.getItegral() != null) {
				ite = uservip.getItegral().intValue();
			}
			List creditlevels = this.creditlevelService.findCreditlevels();
			for (int i = 0; i < creditlevels.size(); i++) {
				Creditlevel creditlevel1 = (Creditlevel) creditlevels.get(i);
				int start = Integer
						.parseInt(creditlevel1.getCreditLevelStart());
				int end = Integer.parseInt(creditlevel1.getCreditLevelEnd());
				if ((ite >= start) && (ite <= end)) {
					uservip.setCreditlevel(creditlevel1);
					break;
				}
			}
			this.userService.updateUservip(uservip);

			this.result = "success";
		} else {
			this.result = "error";
		}
		return this.result;
	}

	/**
	 * 根据用户名查找用户
	 * 
	 * @return
	 * @throws Exception
	 */
	public String findUservipByUserName() throws Exception {
		String flag = null;
		// userName = URLDecoder.decode(URLDecoder.decode(this.userName,
		// "utf-8"),"gb2312");
		String username = new String(this.userName.getBytes("ISO-8859-1"),
				"UTF-8");
		flag = this.userService.findUservipByUserName(username);
		if ("0".equals(flag))
			this.result = "{\"result\":\"0\"}";
		else {
			this.result = "{\"result\":\"1\"}";
		}
		return "success";
	}

	/**
	 * 根据邮箱查找用户
	 * 
	 * @return
	 * @throws Exception
	 */
	public String findUservipByMail() throws Exception {
		String flag = null;
		flag = this.userService.findUservipByMail(this.mail);
		if ("0".equals(flag))
			this.result = "{\"result\":\"0\"}";
		else {
			this.result = "{\"result\":\"1\"}";
		}
		return "success";
	}

	/**
	 * 登出
	 * 
	 * @return
	 */
	public String loginOut() {
		ActionContext.getContext().getSession().remove("uservip");
		return "success";
	}

	/**
	 * 用户头像上传和密码修改
	 * 
	 * @return
	 */
	public String searchPassword() {
		return "success";
	}

	/**
	 * 忘记密码,验证
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public String check() throws UnsupportedEncodingException {
		this.request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
		String oldMobiles = request.getParameter("oldMobiles");
		String name = request.getParameter("name");
//		name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
		System.err.println(id);
		String pwdCode = (String) ActionContext.getContext().getSession()
				.get("pwdCode" + id);
		if (pwdCode.equals(request.getParameter("pwdCode"))) {
			ActionContext.getContext().put("name", name);
			ActionContext.getContext().put("id", id);
			ActionContext.getContext().put("oldMobiles", oldMobiles);
			return "success";
		} else {
			this.setMessage("验证码错误，请核对后输入", "/r_pwd_2.jsp", "3");
			return "jump";
		}
	}

	/**
	 * 忘记密码 修改密码
	 * 
	 * @return
	 * @throws Exception
	 */
	public String upwd() throws Exception {
		this.request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
		String oldMobiles = request.getParameter("oldMobiles");
		String name = request.getParameter("name");
//		String state = request.getParameter("state");
		Uservip uservip = this.userService.findUservipByIdAndUserNameAndEmail(
				Integer.parseInt(id), name);
		Basicinfor basicinfor=basicinforService.findBasicinforByUserId(uservip.getUserId());
		if(oldMobiles.indexOf("@")==-1){
			if(basicinfor.getPhoneNum()!=null && !basicinfor.getPhoneNum().equals(oldMobiles)){
				 setMessage("这个漏洞已修复", "/r_pwd_1.jsp", "3");
			     return "jump";
			}
			if(basicinfor.getPhoneNum()==null || "".equals(basicinfor.getPhoneNum())){
				setMessage("手机号为空", "/r_pwd_1.jsp", "3");
			     return "jump";
			}
		}else{
			if(uservip.getMail()!=null && !uservip.getMail().equals(oldMobiles)){
				 setMessage("这个漏洞已修复", "/r_pwd_1.jsp", "3");
			     return "jump";
			}
			if(uservip.getMail()==null || "".equals(uservip.getMail())){
				 setMessage("邮箱为空", "/r_pwd_1.jsp", "3");
			     return "jump";
			}
		}
		
//		if(basicinfor.getPhoneNum()!=null && uservip.getMail()!=null){
//		    if (!basicinfor.getPhoneNum().equals(oldMobiles) && !uservip.getMail().equals(oldMobiles)) {
//		      setMessage("这个漏洞已修复", "/r_pwd_1.jsp", "3");
//		      return "jump";
//		    }
//	    }else{
//	    	setMessage("这个漏洞已修复", "/r_pwd_1.jsp", "3");
//		    return "jump";
//	    }
		if(this.newPayPwd!=null && !"".equals(this.newPayPwd) && this.newpwd!=null  && !"".equals(this.newpwd)){
			uservip.setPayPwd(MD5Util.md5(this.newPayPwd));
			uservip.setPassword(MD5Util.md5(this.newpwd));
		}else{
			if(this.newPayPwd!=null && !"".equals(this.newPayPwd)){
				uservip.setPayPwd(MD5Util.md5(this.newPayPwd));
			}else if(this.newpwd!=null && !"".equals(this.newpwd)){
				uservip.setPassword(MD5Util.md5(this.newpwd));
			}else{
				this.setMessage("登陆密码或支付密码不能为空", "/r_pwd_1.jsp", "3");
				return "jump";
			}
		}
		boolean boo = this.userService.updateUservip(uservip);
		if (boo == true) {
			this.setMessage("修改成功！", "/login/login.jsp", "3");
		} else {
			this.setMessage("修改失败，请稍后重试！", "/r_pwd_1.jsp", "3");
		}
		return "jump";

	}

	/**
	 * 修改密码
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updatepwd() throws Exception {
		Uservip uservip = (Uservip) ActionContext.getContext().getSession()
				.get("uservip");

		Uservip uservip1 = this.userService.findUservipByPasswordAndUserName(
				MD5Util.md5(this.password), uservip.getUserName());
		if (uservip1 != null) {
			this.resultString = "密码修改成功，重新登录！";
			uservip1.setPassword(MD5Util.md5(this.newpwd));
			this.userService.updateUservip(uservip1);
			ActionContext.getContext().getSession().put("uservip", uservip1);
			this.result = "success";

			Inbox inbox = new Inbox();
			inbox.setUservip(uservip);
			inbox.setContent("尊敬的用户你好，你的登录密码已经修改成功！");
			inbox.setReceiveTime(new Timestamp(new Date().getTime()));
			Website website = this.websiteService.findWebsiteBywebsiteId(1);
			inbox.setSendName(website.getWebName());
			inbox.setTitle("登录密码修改成功");
			inbox.setStatus(Integer.valueOf(0));
			this.inboxService.createInbox(inbox);

			ActionContext.getContext().getSession().remove("uservip");
		} else {
			this.resultString = "0";
			this.result = "error";
		}
		this.mark = "2";
		return this.result;
	}

	/**
	 * 修改支付密码
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updatePaypwd() throws Exception {
		Uservip uservip = (Uservip) ActionContext.getContext().getSession()
				.get("uservip");

		System.out.println("paypassword==" + this.payPwd);
		Uservip uservip1 = this.userService.findUservipByPayPwdAndUserName(
				MD5Util.md5(this.payPwd), uservip.getUserName());
		if (uservip1 != null) {
			this.resultPay = "1";
			uservip1.setPayPwd(MD5Util.md5(this.newPayPwd));
			this.userService.updateUservip(uservip1);
			ActionContext.getContext().getSession().put("uservip", uservip1);
			this.result = "success";

			Inbox inbox = new Inbox();
			inbox.setUservip(uservip);
			inbox.setContent("尊敬的用户你好，你的支付密码已经修改成功！");
			inbox.setReceiveTime(new Timestamp(new Date().getTime()));
			Website website = this.websiteService.findWebsiteBywebsiteId(1);
			inbox.setSendName(website.getWebName());
			inbox.setTitle("支付密码修改成功");
			inbox.setStatus(Integer.valueOf(0));
			this.inboxService.createInbox(inbox);
		} else {
			this.resultPay = "0";
			this.result = "error";
		}
		this.mark = "3";
		return this.result;
	}

	/**
	 * 上传图片
	 * 
	 * @return
	 */

	public String uploadPic() {
		String[] str = { ".jpg", ".jpeg", ".bmp", ".gif" };
		System.out.println("pic==" + this.pic);

		Uservip uservip = (Uservip) ActionContext.getContext().getSession()
				.get("uservip");
		String curruser = uservip.getUserName();

		if ((this.pic == null) || (this.pic.length() > 4194304L)) {
			return "error";
		}
		for (String s : str) {
			if (this.picFileName.endsWith(s)) {
				System.out.println("pic==" + this.picFileName);
				this.picFileName = (System.currentTimeMillis() + s);
				uservip.setPhotoOne("/images/" + this.picFileName);
				ActionContext.getContext().getSession().put("uservip", uservip);
				try {
					this.userService.updateUservip(uservip);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				String realPath = ServletActionContext.getServletContext()
						.getRealPath("/images");
				File saveFile = new File(new File(realPath), this.picFileName);

				if (!saveFile.getParentFile().exists()) {
					saveFile.getParentFile().mkdirs();
				}

				try {
					if (!saveFile.exists())
						FileUtils.copyFile(this.pic, saveFile);
				} catch (IOException e) {
					return "imageError";
				}
			}
		}
		return "success";
	}

	/**
	 * 用于图片裁剪，暂时不用
	 * 
	 * @return
	 */
	public String cutPic() {
		Uservip uservip = (Uservip) ActionContext.getContext().getSession()
				.get("uservip");
		String url = uservip.getPhotoOne().replace("../", "");
		String name = ServletActionContext.getServletContext().getRealPath(url);
		System.out.println("name=" + name);
		this.image.setSrcpath(name);
		this.image.setSubpath(ServletActionContext.getServletContext()
				.getRealPath("images/b.jpg"));
		try {
			this.image.cut();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "success";
	}

//	/**
//	 * 好友邀请
//	 * 
//	 * @throws Exception
//	 */
//	public void list() throws Exception {
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Uservip uservip = (Uservip) ActionContext.getContext().getSession()
//				.get("uservip");
//		List us = this.userService.findUservips(uservip.getUserId().intValue());
//		int totalRecord = us.size();
//		if (totalRecord % this.pageSize == 0)
//			this.totalPage = (totalRecord / this.pageSize);
//		else {
//			this.totalPage = (totalRecord / this.pageSize + 1);
//		}
//		if (this.currentPage >= this.totalPage) {
//			this.currentPage = this.totalPage;
//		}
//		if (this.currentPage <= 1) {
//			this.currentPage = 1;
//		}
//		// 查找推荐的用户
//		List<Uservip> uspage = this.userService.findUservipsByFunPage(uservip
//				.getUserId().intValue(),
//				(this.currentPage - 1) * this.pageSize, this.pageSize);
//
//		// 存储所有推荐用户
//		List<Uservip> list = new ArrayList<Uservip>();
//		for (Uservip uservip1 : uspage) {
//			if (uservip.getUserId().equals(uservip1.getUservip().getUserId())) {
//				uservip1.setUserNames("一级用户：" + uservip1.getUserName());
//				list.add(uservip1);
//			} else if (uservip.getUserId().equals(
//					uservip1.getUservip().getUservip().getUserId())) {
//				uservip1.setUserNames("二级用户：" + uservip1.getUserName());
//				list.add(uservip1);
//			} else if (uservip.getUserId()
//					.equals(uservip1.getUservip().getUservip().getUservip()
//							.getUserId())) {
//				uservip1.setUserNames("三级用户：" + uservip1.getUserName());
//				list.add(uservip1);
//			} else if (uservip.getUserId().equals(
//					uservip1.getUservip().getUservip().getUservip()
//							.getUservip().getUserId())) {
//				uservip1.setUserNames("四级用户：" + uservip1.getUserName());
//				list.add(uservip1);
//			} else if (uservip.getUserId().equals(
//					uservip1.getUservip().getUservip().getUservip()
//							.getUservip().getUservip().getUserId())) {
//				uservip1.setUserNames("五级用户：" + uservip1.getUserName());
//				list.add(uservip1);
//			} else if (uservip
//					.getUserId()
//					.equals(uservip1.getUservip().getUservip().getUservip()
//							.getUservip().getUservip().getUservip().getUserId())) {
//				uservip1.setUserNames("六级用户：" + uservip1.getUserName());
//				list.add(uservip1);
//			} else {
//				uservip1.setUserNames("六级以下用户：" + uservip1.getUserName());
//				list.add(uservip1);
//			}
//		}
//
//		StringBuffer str = new StringBuffer();
//		str.append("{\"totalPage\":\"" + this.totalPage + "\",");
//		str.append("\"currentPage\":\"" + this.currentPage + "\",");
//		str.append("\"jsonRoots\":[");
//		for (Uservip user : list) {
//			str.append("{\"userId\":\"" + user.getUserId() + "\",");
//			str.append("\"time\":\"" + df.format(user.getRegisterDate())
//					+ "\",");
//			str.append("\"userName\":\"" + user.getUserNames() + "\"},");
//		}
//		str.deleteCharAt(str.lastIndexOf(","));
//		str.append("]}");
//		HttpServletResponse response = ServletActionContext.getResponse();
//		response.setCharacterEncoding("utf-8");
//		try {
//			response.getWriter().print(str);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * 推荐投资记录
//	 * 
//	 * @throws Exception
//	 */
//	public void reward() throws Exception {
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		Uservip uservip = (Uservip) ActionContext.getContext().getSession()
//				.get("uservip");
//		List tenderlist = this.tenderService.findTendersByID(uservip
//				.getUserId());
//		if ((tenderlist != null) && (tenderlist.size() > 0)) {
//			for (int i = 0; i < tenderlist.size(); i++) {
//				Tender p = (Tender) tenderlist.get(i);
//				this.totalMoney = Integer.valueOf(this.totalMoney.intValue()
//						+ p.getMoney().intValue());
//			}
//		}
//		int totalRecord = tenderlist.size();
//		if (totalRecord % this.pageSize == 0)
//			this.total = (totalRecord / this.pageSize);
//		else {
//			this.total = (totalRecord / this.pageSize + 1);
//		}
//		if (this.currentPage >= this.total) {
//			this.currentPage = this.total;
//		}
//		if (this.currentPage <= 1) {
//			this.currentPage = 1;
//		}
//
//		List<Tender> tenderpagelist = this.tenderService.findTendersByFunPage(
//				uservip.getUserId(), (this.currentPage - 1) * this.pageSize,
//				this.pageSize);
//
//		// 存储所有推荐用户
//		List<Tender> list = new ArrayList<Tender>();
//		for (Tender tender : tenderpagelist) {
//			if (uservip.getUserId().equals(
//					tender.getUservip().getUservip().getUserId())) {
//				tender.getUservip().setUserNames(
//						"一级用户：" + tender.getUservip().getUserName());
//				list.add(tender);
//			} else if (uservip.getUserId().equals(
//					tender.getUservip().getUservip().getUservip().getUserId())) {
//				tender.getUservip().setUserNames(
//						"二级用户：" + tender.getUservip().getUserName());
//				list.add(tender);
//			} else if (uservip.getUserId().equals(
//					tender.getUservip().getUservip().getUservip().getUservip()
//							.getUserId())) {
//				tender.getUservip().setUserNames(
//						"三级用户：" + tender.getUservip().getUserName());
//				list.add(tender);
//			} else if (uservip.getUserId().equals(
//					tender.getUservip().getUservip().getUservip().getUservip()
//							.getUservip().getUserId())) {
//				tender.getUservip().setUserNames(
//						"四级用户：" + tender.getUservip().getUserName());
//				list.add(tender);
//			} else if (uservip.getUserId().equals(
//					tender.getUservip().getUservip().getUservip().getUservip()
//							.getUservip().getUservip().getUserId())) {
//				tender.getUservip().setUserNames(
//						"五级用户：" + tender.getUservip().getUserName());
//				list.add(tender);
//			} else if (uservip.getUserId()
//					.equals(tender.getUservip().getUservip().getUservip()
//							.getUservip().getUservip().getUservip()
//							.getUservip().getUserId())) {
//				tender.getUservip().setUserNames(
//						"六级用户：" + tender.getUservip().getUserName());
//				list.add(tender);
//			} else {
//				tender.getUservip().setUserNames(
//						"六级以下用户：" + tender.getUservip().getUserName());
//				list.add(tender);
//			}
//		}
//
//		StringBuffer str = new StringBuffer();
//		str.append("{\"total\":\"" + this.total + "\",");
//		str.append("\"current\":\"" + this.currentPage + "\",");
//		str.append("\"totalMoney\":\"" + this.totalMoney + "\",");
//		str.append("\"jsonRoots\":[");
//		for (Tender tender : tenderpagelist) {
//			str.append("{\"tenderId\":\"" + tender.getTenderId() + "\",");
//			str.append("\"lssuingNum\":\""
//					+ tender.getLssuing().getLssuingNum() + "\",");
//			str.append("\"time\":\"" + df.format(tender.getTenderTime())
//					+ "\",");
//			str.append("\"money\":\"" + tender.getMoney() + "\",");
//			str.append("\"username\":\"" + tender.getUservip().getUserNames()
//					+ "\"},");
//		}
//		str.deleteCharAt(str.lastIndexOf(","));
//		str.append("]}");
//		HttpServletResponse response = ServletActionContext.getResponse();
//		response.setCharacterEncoding("utf-8");
//		try {
//			response.getWriter().print(str);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	/**
	 * 好友邀请
	 * 
	 * @throws Exception
	 */
	public void list() throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Uservip uservip = (Uservip) ActionContext.getContext().getSession()
				.get("uservip");
		String sql="select count(*) from uservip where retereeUserId="+uservip.getUserId();
		Object obj=userService.findCount(sql);
		int totalRecord =Integer.valueOf(obj.toString());
		if (totalRecord % this.pageSize == 0)
			this.totalPage = (totalRecord / this.pageSize);
		else {
			this.totalPage = (totalRecord / this.pageSize + 1);
		}
		if (this.currentPage >= this.totalPage) {
			this.currentPage = this.totalPage;
		}
		if (this.currentPage <= 1) {
			this.currentPage = 1;
		}
		List<Uservip> list=this.userService.findUservipsByUserIdPage(uservip.getUserId().intValue(), (this.currentPage - 1)
						* this.pageSize, this.pageSize);
		StringBuffer str = new StringBuffer();
		str.append("{\"totalPage\":\"" + this.totalPage + "\",");
		str.append("\"currentPage\":\"" + this.currentPage + "\",");
		str.append("\"jsonRoots\":[");
		for (Uservip user : list) {
			str.append("{\"userId\":\"" + user.getUserId() + "\",");
			str.append("\"time\":\"" + df.format(user.getRegisterDate())
					+ "\",");
			str.append("\"userName\":\"" + user.getUserName() + "\"},");
		}
		str.deleteCharAt(str.lastIndexOf(","));
		str.append("]}");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {
			response.getWriter().print(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 推荐投资记录
	 * 
	 * @throws Exception
	 */
	public void reward() throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Uservip uservip = (Uservip) ActionContext.getContext().getSession()
				.get("uservip");
		String sql = "select * from Tender t,Uservip u,Uservip uv where t.userId=u.userId and u.retereeUserId=uv.userId and u.retereeUserId="+uservip.getUserId();
		List tenderlist = this.tenderService.findTendersBylssuingIdPage(sql);
		
		if ((tenderlist != null) && (tenderlist.size() > 0)) {
			for (int i = 0; i < tenderlist.size(); i++) {
				Tender p = (Tender) tenderlist.get(i);
				this.totalMoney = Integer.valueOf(this.totalMoney.intValue()
						+ p.getMoney().intValue());
			}
		}
		int totalRecord = tenderlist.size();
		if (totalRecord % this.pageSize == 0)
			this.total = (totalRecord / this.pageSize);
		else {
			this.total = (totalRecord / this.pageSize + 1);
		}
		if (this.currentPage >= this.total) {
			this.currentPage = this.total;
		}
		if (this.currentPage <= 1) {
			this.currentPage = 1;
		}
		sql = sql + " LIMIT " + (this.currentPage - 1) * this.pageSize+ "," + this.pageSize;
		List<Tender> list  = this.tenderService.findTendersBylssuingIdPage(sql);
	    

		StringBuffer str = new StringBuffer();
		str.append("{\"total\":\"" + this.total + "\",");
		str.append("\"current\":\"" + this.currentPage + "\",");
		str.append("\"totalMoney\":\"" + this.totalMoney + "\",");
		str.append("\"jsonRoots\":[");
		for (Tender tender : list) {
			str.append("{\"tenderId\":\"" + tender.getTenderId() + "\",");
			str.append("\"lssuingNum\":\""
					+ tender.getLssuing().getLssuingNum() + "\",");
			str.append("\"time\":\"" + df.format(tender.getTenderTime())
					+ "\",");
			str.append("\"money\":\"" + tender.getMoney() + "\",");
			str.append("\"username\":\"" + tender.getUservip().getUserName()
					+ "\"},");
		}
		str.deleteCharAt(str.lastIndexOf(","));
		str.append("]}");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {
			response.getWriter().print(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 注册
	 * 
	 * @throws Exception
	 */
	public String reg1() throws Exception {
		request = ServletActionContext.getRequest();
		this.sessionId = request.getSession().getId();
		String code1 = (String) ActionContext.getContext().getSession()
				.get("code1" + this.sessionId);
		if (!this.code.equals(code1)) {
			this.setMessage("验证码错误", "/login/reg1.jsp", "3");
			return "jump";
		} else {
			// ActionContext.getContext().getSession().put("userName",
			// this.userName);
			// ActionContext.getContext().getSession().put("password",
			// this.password);
			// ActionContext.getContext().getSession().put("referId",
			// this.referId);
			request.setAttribute("userName", this.userName);
			request.setAttribute("password", this.password);
			request.setAttribute("referId", this.referId);// 推荐人id
			request.setAttribute("kefuId", this.kefuId);
			return "success";
		}
	}

	/**
	 * 查找专属客服
	 * 
	 * @throws Exception
	 */
	public String searchKefu() throws Exception {
		Uservip uservip = (Uservip) ActionContext.getContext().getSession()
				.get("uservip");
		Uservip uservip1 = this.userService.findUservipByUserid(uservip
				.getUserId());
		ActionContext.getContext().put("uservip1", uservip1);
		return "success";
	}

	/**
	 * 修改专属客服
	 * 
	 * @throws Exception
	 */
	public String updateKefu() throws Exception {
		request = ServletActionContext.getRequest();
		String kefuId = request.getParameter("kefuId");
		ManagerService managerService = new ManagerServiceImpl();
		if (kefuId == null || "".equals(kefuId)) {
			this.setMessage("请选择客服！", "/user/searchKefu", "3");
		} else {
			Manager manager = managerService.findManagerByManagerId(Integer
					.valueOf(kefuId));
			Uservip uservip = (Uservip) ActionContext.getContext().getSession()
					.get("uservip");
			Uservip uservip1 = this.userService.findUservipByUserid(uservip
					.getUserId());
			uservip1.setManager(manager);
			this.setMessage("客服修改完成！", "/user/searchKefu", "3");
		}
		return "success";
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public InboxService getInboxService() {
		return this.inboxService;
	}

	public void setInboxService(InboxService inboxService) {
		this.inboxService = inboxService;
	}

	public Moneycount getMoneycount() {
		return this.moneycount;
	}

	public void setMoneycount(Moneycount moneycount) {
		this.moneycount = moneycount;
	}

	public Uservip getUser() {
		return this.user;
	}

	public void setUser(Uservip user) {
		this.user = user;
	}

	public Creditlevel getCreditlevel() {
		return this.creditlevel;
	}

	public void setCreditlevel(Creditlevel creditlevel) {
		this.creditlevel = creditlevel;
	}

	public Integer getUnreadInbox() {
		return this.unreadInbox;
	}

	public void setUnreadInbox(Integer unreadInbox) {
		this.unreadInbox = unreadInbox;
	}

	public Integer getOverTimePay() {
		return this.overTimePay;
	}

	public void setOverTimePay(Integer overTimePay) {
		this.overTimePay = overTimePay;
	}

	public Integer getWaitCount() {
		return this.waitCount;
	}

	public void setWaitCount(Integer waitCount) {
		this.waitCount = waitCount;
	}

	public Integer getCurrentCount() {
		return this.currentCount;
	}

	public void setCurrentCount(Integer currentCount) {
		this.currentCount = currentCount;
	}

	public Integer getCurrentGetCount() {
		return this.currentGetCount;
	}

	public void setCurrentGetCount(Integer currentGetCount) {
		this.currentGetCount = currentGetCount;
	}

	public Integer getReferId() {
		return this.referId;
	}

	public void setReferId(Integer referId) {
		this.referId = referId;
	}

	public int getCurrentPage() {
		return this.currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return this.totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrent() {
		return this.current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public int getTotal() {
		return this.total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Integer getTotalMoney() {
		return this.totalMoney;
	}

	public void setTotalMoney(Integer totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Integer getBasicCheck() {
		return this.basicCheck;
	}

	public void setBasicCheck(Integer basicCheck) {
		this.basicCheck = basicCheck;
	}

	public Integer getBankNum() {
		return this.bankNum;
	}

	public void setBankNum(Integer bankNum) {
		this.bankNum = bankNum;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
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