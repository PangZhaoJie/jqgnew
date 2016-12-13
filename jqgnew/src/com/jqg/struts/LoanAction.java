package com.jqg.struts;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.SessionFactory;

import com.jqg.pojo.Basicinfor;
import com.jqg.pojo.Certification;
import com.jqg.pojo.Inbox;
import com.jqg.pojo.Integralcategory;
import com.jqg.pojo.Integraldetail;
import com.jqg.pojo.Lssuing;
import com.jqg.pojo.Manager;
import com.jqg.pojo.Moneycount;
import com.jqg.pojo.Moneyhistorydetail;
import com.jqg.pojo.Oerationlog;
import com.jqg.pojo.Offlinerecharge;
import com.jqg.pojo.Platcount;
import com.jqg.pojo.Record;
import com.jqg.pojo.Repaynote;
import com.jqg.pojo.Systemconf;
import com.jqg.pojo.Tender;
import com.jqg.pojo.Transfer;
import com.jqg.pojo.Translate;
import com.jqg.pojo.Uservip;
import com.jqg.pojo.Website;
import com.jqg.service.BasicinforService;
import com.jqg.service.CertificationService;
import com.jqg.service.InboxService;
import com.jqg.service.IntegralcategoryService;
import com.jqg.service.IntegraldetailService;
import com.jqg.service.LssuingService;
import com.jqg.service.ManagerService;
import com.jqg.service.MoneycountService;
import com.jqg.service.MoneyhistorydetailService;
import com.jqg.service.OerationlogService;
import com.jqg.service.OfflinerechargeService;
import com.jqg.service.PlatcountService;
import com.jqg.service.RecordService;
import com.jqg.service.RepaynoteService;
import com.jqg.service.SystemconfService;
import com.jqg.service.TenderService;
import com.jqg.service.TransferService;
import com.jqg.service.TranslateService;
import com.jqg.service.UservipService;
import com.jqg.service.WebsiteService;
import com.jqg.service.impl.BasicinforServiceImpl;
import com.jqg.service.impl.CertificationServiceImpl;
import com.jqg.service.impl.InboxServiceImpl;
import com.jqg.service.impl.IntegralcategoryServiceImpl;
import com.jqg.service.impl.IntegraldetailServiceImpl;
import com.jqg.service.impl.LssuingServiceImpl;
import com.jqg.service.impl.ManagerServiceImpl;
import com.jqg.service.impl.MoneycountServiceImpl;
import com.jqg.service.impl.MoneyhistorydetailServiceImpl;
import com.jqg.service.impl.OerationlogServiceImpl;
import com.jqg.service.impl.OfflinerechargeServiceImpl;
import com.jqg.service.impl.PlatcountServiceImpl;
import com.jqg.service.impl.RecordServiceImpl;
import com.jqg.service.impl.RepaynoteServiceImpl;
import com.jqg.service.impl.SystemconfServiceImpl;
import com.jqg.service.impl.TenderServiceImpl;
import com.jqg.service.impl.TransferServiceImpl;
import com.jqg.service.impl.TranslateServiceImpl;
import com.jqg.service.impl.UservipServiceImpl;
import com.jqg.service.impl.WebsiteServiceImpl;
import com.jqg.session.factory.HibernateSessionFactory;
import com.jqg.util.Common;
import com.jqg.util.HttpClientUtil;
import com.jqg.util.LoanUtils;
import com.jqg.util.MD5Trust;
import com.jqg.util.RsaHelper;
import com.loan.model.IDCardBean;
import com.loan.model.LoanInfoSecondaryBean;
import com.loan.model.LoanRegisterBindReturnBean;
import com.loan.model.LoanReturnInfoBean;
import com.loan.model.LoanTransferReturnBean;
import com.opensymphony.xwork2.ActionContext;

public class LoanAction extends BaseAction {

	private WebsiteService websiteService = new WebsiteServiceImpl();
	private UservipService uservipService = new UservipServiceImpl();
	private TenderService tenderService = new TenderServiceImpl();
	private LssuingService lssuingService = new LssuingServiceImpl();
	private MoneycountService moneycountService = new MoneycountServiceImpl();
	private SystemconfService systemconfService = new SystemconfServiceImpl();
	// LssingphotoService lssingphotoService = new LssingphotoServiceImpl();
	// RecordService recordService = new RecordServiceImpl();
	// BasicinforService basicinforService = new BasicinforServiceImpl();
	private RepaynoteService repaynoteService = new RepaynoteServiceImpl();
	private RecordService recordService = new RecordServiceImpl();
	private MoneyhistorydetailService moneyhistorydetailService = new MoneyhistorydetailServiceImpl();
	private IntegralcategoryService integralcategoryService = new IntegralcategoryServiceImpl();
	private IntegraldetailService integraldetailService = new IntegraldetailServiceImpl();
	private InboxService inboxService = new InboxServiceImpl();
	private TranslateService translateService = new TranslateServiceImpl();
	private ManagerService managerService = new ManagerServiceImpl();
	private OerationlogService oerationlogService = new OerationlogServiceImpl();
	private TransferService transferService = new TransferServiceImpl();
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

	// 绑定
	private String LoanPlatformAccount = "";
	private String LoanMoneymoremore = "";
	private String MoneymoremoreId = "";

	// 转账
	private String LoanOutMoneymoremore1 = "";
	private String LoanInMoneymoremore1 = "";
	private String OrderNo1 = "";
	private String BatchNo1 = "";
	private String ExchangeBatchNo1 = "";
	private String AdvanceBatchNo1 = "";
	private String Amount1 = "";
	private String FullAmount1 = "";
	private String TransferName1 = "";
	private String MainRemark1 = "";
	private String Remark1 = "";
	private String LoanInMoneymoremore2 = "";
	private String OrderNo2 = "";
	private String BatchNo2 = "";
	private String Amount2 = "";
	private String FullAmount2 = "";
	private String TransferName2 = "";
	private String MainRemark2 = "";
	private String Remark2 = "";
	private String LoanInMoneymoremore3 = "";
	private String OrderNo3 = "";
	private String BatchNo3 = "";
	private String Amount3 = "";
	private String FullAmount3 = "";
	private String TransferName3 = "";
	private String MainRemark3 = "";
	private String Remark3 = "";

	private String SLoanInMoneymoremore1 = "";
	private String SAmount1 = "";
	private String STransferName1 = "";
	private String SRemark1 = "";
	private String SLoanInMoneymoremore2 = "";
	private String SAmount2 = "";
	private String STransferName2 = "";
	private String SRemark2 = "";

	private String LoanJsonList = "";
	private String TransferAction = "";
	private String Action = "";
	private String TransferType = "";
	private String NeedAudit = "";

	// 余额查询
	private String PlatformId = "";
	private String PlatformType = "";

	// 充值
	private String RechargeMoneymoremore = "";
	private String OrderNo = "";
	private String Amount = "";
	private String FeePlatform = "";
	private String RechargeType = "";
	private String FeeType = "";
	private String CardNoList = "";

	// 提现
	private String WithdrawMoneymoremore = "";
	private String FeePercent = "";
	private String Fee = "";
	private String FreeLimit = "";
	private String FeeMax = "";
	private String FeeWithdraws = "";
	private String FeeRate = "";
	private String FeeSplitting = "";
	private String CardNo = "";
	private String CardType = "";
	private String BankCode = "";
	private String BranchBankName = "";
	private String Province = "";
	private String City = "";

	// 对账
	private String LoanNo = "";
	private String BatchNo = "";
	private String BeginTime = "";
	private String EndTime = "";

	// 授权
	private String AuthorizeType = "";
	private String AuthorizeTypeOpen = "";
	private String AuthorizeTypeClose = "";

	// 释放
	private String ReleaseType = "";

	// 审核
	private String LoanNoList = "";
	private String LoanNoListFail = "";
	private String AuditType = "";

	// 三合一
	private String WithholdBeginDate = "";
	private String WithholdEndDate = "";
	private String SingleWithholdLimit = "";
	private String TotalWithholdLimit = "";

	// 验证码发送
	private String SendAccount = "";
	private String MobileType = "";

	// 姓名匹配
	private String IdentityJsonList = "";
	private String IdentityFailJsonList = "";
	private String RealName2 = "";
	private String IdentificationNo2 = "";
	  
	private boolean istest= false;//true ：测试，false:正式

	
	
	/**
	 * 姓名匹配处理
	 * @return
	 * @throws Exception
	 */
	public String nameAuditnotify() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		
		request.setCharacterEncoding("UTF-8");
		
		IdentityJsonList = Common.UrlDecoder(IdentityJsonList, "utf-8");
		IdentityFailJsonList = Common.UrlDecoder(IdentityFailJsonList, "utf-8");
		
		Website website = this.websiteService.findWebsiteBywebsiteId(1);
		String publickey = website.getPublicKey();

		MD5Trust md5 = new MD5Trust();
		RsaHelper rsa = RsaHelper.getInstance();
		String dataStr = PlatformMoneymoremore  + IdentityJsonList  + IdentityFailJsonList  + RandomTimeStamp 
				+ Amount  + ResultCode;
		if (antistate == 1) {
			dataStr = md5.getMD5Info(dataStr);
		}

		// 签名
		boolean verifySignature = rsa.verifySignature(SignInfo, dataStr,
				publickey);
		System.out.println("后台通知:" + verifySignature);
		System.out.println("返回码:" + ResultCode);
		System.out.println("返回次数:" + ReturnTimes);
		boolean key = true;
		if (verifySignature && this.ResultCode.equals("88")) {// 处理逻辑业务
			CertificationService certificationService = new CertificationServiceImpl();
			if(IdentityJsonList!=null && !IdentityJsonList.equals("")){
				List<Object> Identitylist = Common.JSONDecodeList(IdentityJsonList,
						IDCardBean.class);
				
				System.out.println(Identitylist.size());
				if (Identitylist != null && Identitylist.size() > 0) {
					BasicinforService basicinforService = new BasicinforServiceImpl();
					for (int j = 0; j < Identitylist.size(); j++) {
						if (Identitylist.get(j) instanceof IDCardBean) {
							IDCardBean idcard = (IDCardBean) Identitylist.get(j);
							String sql = "SELECT * FROM certification c WHERE IdNum = '"+idcard.getIdentificationNo()+"'";
							List<Certification> certifications = certificationService
									.findCertificationsBysql(sql);
							
							System.out.println(certifications.size());
							for(int i=0;i<certifications.size();i++){
								Certification certification = certifications.get(i);
								System.out.println(certification.getRealName()+"========"+idcard.getRealName()+"++++++++"+idcard.getState());
								if(certification.getRealName().equals(idcard.getRealName())){
									key = false;
									if(idcard.getState().equals("1")){
										System.out.println("验证通过" + certification.getCertificationId());
										certification.setExamineStatus(Integer.valueOf(1));
										certificationService.updateCertification(certification);
										Uservip uservip = this.uservipService.findUservipByUserid(certification.getUservip().getUserId());
										uservip.setNameResult("2");
										uservip.setRealName(certification.getRealName());
										
										Basicinfor basicinfor = basicinforService.findBasicinforByUserId(uservip.getUserId());
										basicinfor.setRealName(certification.getRealName());
										basicinfor.setIdNum(certification.getIdNum());
										boolean flag = basicinforService.updateBasicinfor(basicinfor);
										boolean flag1 = this.uservipService.updateUservip(uservip);
										break;
									}else{
										System.out.println("验证不通过" + certification.getCertificationId());
										certification.setExamineStatus(Integer.valueOf(2));
										certificationService.updateCertification(certification);
										Uservip uservip = this.uservipService.findUservipByUserid(certification.getUservip().getUserId());
										uservip.setNameResult("0");
										boolean flag1 = this.uservipService.updateUservip(uservip);
										break;
									}
								}
							}
						}
					}
				}
			}
			
			if(IdentityFailJsonList!=null && !IdentityFailJsonList.equals("")){
				List<Object> Identitylist = Common.JSONDecodeList(IdentityFailJsonList,
						IDCardBean.class);
				if (Identitylist != null && Identitylist.size() > 0) {
					
					for (int j = 0; j < Identitylist.size(); j++) {
						if (Identitylist.get(j) instanceof IDCardBean) {
							IDCardBean idcard = (IDCardBean) Identitylist.get(j);
							String sql = "SELECT * FROM certification c WHERE IdNum = '"+idcard.getIdentificationNo()+"'";
							List<Certification> certifications = certificationService
									.findCertificationsBysql(sql);
							
							for(int i=0;i<certifications.size();i++){
								Certification certification = certifications.get(i);
								if(certification.getRealName().equals(idcard.getRealName())){
									key = false;
									certification.setExamineStatus(Integer.valueOf(2));
									certificationService.updateCertification(certification);
									Uservip uservip = this.uservipService.findUservipByUserid(certification.getUservip().getUserId());
									uservip.setNameResult("0");
									boolean flag1 = this.uservipService.updateUservip(uservip);
									break;
								}
							}
						}
					}
				}
			}		
			
		}else if(verifySignature && this.ResultCode.equals("05")){
			CertificationService certificationService = new CertificationServiceImpl();
			if(IdentityJsonList!=null && !IdentityJsonList.equals("")){
				List<Object> Identitylist = Common.JSONDecodeList(IdentityJsonList,
						IDCardBean.class);
				
				System.out.println(Identitylist.size());
				if (Identitylist != null && Identitylist.size() > 0) {  
			BasicinforService basicinforService = new BasicinforServiceImpl();
			for (int j = 0; j < Identitylist.size(); j++) {
				if (Identitylist.get(j) instanceof IDCardBean) {
					IDCardBean idcard = (IDCardBean) Identitylist.get(j);
					String sql = "SELECT * FROM certification c WHERE IdNum = '"+idcard.getIdentificationNo()+"'";
					List<Certification> certifications = certificationService
							.findCertificationsBysql(sql);
					
					System.out.println(certifications.size());
					for(int i=0;i<certifications.size();i++){
						Certification certification = certifications.get(i);
						System.out.println(certification.getRealName()+"========"+idcard.getRealName()+"++++++++"+idcard.getState());
						if(certification.getRealName().equals(idcard.getRealName())){
							key = false;
						
								System.out.println("验证不通过" + certification.getCertificationId());
								certification.setExamineStatus(Integer.valueOf(2));
								certificationService.updateCertification(certification);
								Uservip uservip = this.uservipService.findUservipByUserid(certification.getUservip().getUserId());
								uservip.setNameResult("0");
								boolean flag1 = this.uservipService.updateUservip(uservip);
							}
					}
					}
				}
			}
		}
		}
		System.out.println("返回次数:" + ReturnTimes);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/plain;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		if(key){
			response.getWriter().write("error");
		}else{
			response.getWriter().write("SUCCESS");
		}
		
		response.getWriter().flush();
		response.getWriter().close();
		System.out.println("返回次数:" + ReturnTimes);
		return null;
	}
	
	
	/**
	 * 开通托管账号
	 * 
	 * @return
	 * @throws Exception
	 */
	public String registerbind() throws Exception {

		HttpServletRequest request = ServletActionContext.getRequest();
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";
		// 判断是否符合条件(实名认证、手机认证)
		Uservip uservip = (Uservip) ActionContext.getContext().getSession()
				.get("uservip");
		uservip = uservipService.findUservipByUserid(uservip.getUserId());
		if (uservip.getNameResult() == null
				|| !uservip.getNameResult().equals("2")) {
			this.setMessage("实名认证之后再开通托管账号", "/user/create?mark=3", "3");
			return "message";
		}
		if (uservip.getPhoneResult() == null
				|| !uservip.getPhoneResult().equals("1")) {
			this.setMessage("手机认证之后再开通托管账号", "/user/create?mark=2", "3");
			return "message";
		}
		if (uservip.getEnable() == null || uservip.getEnable().intValue() != 1) {
			this.setMessage("邮箱认证之后再开通托管账号", "/user/create?mark=111", "3");
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

		this.Remark1 = this.Remark2 = uservip.getUserName() + "开通托管账号";
		this.Remark3 = uservip.getUserId().toString();

		try {
			
			if(istest){
				this.SubmitURL = "http://218.4.234.150:88/main/loan/toloanregisterbind.action";
			}else{
				this.SubmitURL = "https://register.moneymoremore.com/loan/toloanregisterbind.action";
			}
//			SubmitURL = SubmitURLPrefix + "loan/toloanregisterbind.action";
			ReturnURL = basePath + "user/registerbindreturn.action";
			NotifyURL = basePath + "user/registerbindnotify.action";

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
			if (verifySignature && (this.ResultCode.equals("88")||this.ResultCode.equals("16"))) {// 处理逻辑业务
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
		this.setMessage(this.Message, "/user/loginSearch.action", "3");
		if("wap".equals(Remark2)){
			
			this.setMessage(this.Message, "/wap/findUsers", "3");
			return "mobile";
		}
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

			if (verifySignature && (this.ResultCode.equals("88")||this.ResultCode.equals("16"))) {// 处理逻辑业务
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
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";
		// 判断是否符合条件(实名认证、手机认证)
		Uservip uservip = (Uservip) ActionContext.getContext().getSession()
				.get("uservip");
		uservip = uservipService.findUservipByUserid(uservip.getUserId());
		
		if (uservip.getTrustStatus()==0 || uservip.getTrustAccount()==null || uservip.getTrustAccount().equals("")) {
			this.setMessage("开通托管账号后才能授权", "/user/loginSearch.action", "3");
			return "message";
		}
		try {
			
			if(istest){
				this.SubmitURL = "http://218.4.234.150:88/main/loan/toloanauthorize.action";
			}else{
				this.SubmitURL = "https://auth.moneymoremore.com/loan/toloanauthorize.action";
			}
			
//			SubmitURL = SubmitURLPrefix + "loan/toloanauthorize.action";
			ReturnURL = basePath + "user/loanauthorizereturn.action";
			NotifyURL = basePath + "user/loanauthorizenotify.action";
			Website website = this.websiteService.findWebsiteBywebsiteId(1);
			this.PlatformMoneymoremore = website.getTrustAccount();

			this.MoneymoremoreId = uservip.getTrustAccount();
			this.AuthorizeTypeOpen = "1,2,3";
			this.AuthorizeTypeClose = "";

			this.Remark1 = this.Remark2 = uservip.getUserName() + "授权操作";
			this.Remark3 = uservip.getUserId().toString();

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
			if (verifySignature && (this.ResultCode.equals("88")||this.ResultCode.equals("08"))) {// 处理逻辑业务
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
		this.setMessage(this.Message, "/user/loginSearch.action", "3");
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
			if (verifySignature && (this.ResultCode.equals("88")||this.ResultCode.equals("08"))) {//处理逻辑业务
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
	 * 投标返回处理
	 * 
	 * @return
	 * @throws IOException 
	 */
	public String loantenderreturn() throws IOException {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");
			Website website = this.websiteService.findWebsiteBywebsiteId(1);
			String publickey = website.getPublicKey();
			LoanJsonList = Common.UrlDecoder(LoanJsonList, "utf-8");
			MD5Trust md5 = new MD5Trust();
			RsaHelper rsa = RsaHelper.getInstance();
			String dataStr = LoanJsonList + PlatformMoneymoremore + Action + RandomTimeStamp + Remark1 + Remark2 + Remark3 + ResultCode;
//			String dataStr = LoanNoList + LoanNoListFail
//					+ PlatformMoneymoremore + AuditType + RandomTimeStamp
//					+ Remark1 + Remark2 + Remark3 + ResultCode;
			if (antistate == 1) {
				dataStr = md5.getMD5Info(dataStr);
			}

			// 签名
			boolean verifySignature = rsa.verifySignature(SignInfo, dataStr,
					publickey);
			System.out.println("后台通知:" + verifySignature);
			System.out.println("返回码:" + ResultCode);
			String lssuingId = "";

			List<Object> loaninfolist = Common.JSONDecodeList(LoanJsonList,
					LoanReturnInfoBean.class);
			if (loaninfolist != null && loaninfolist.size() > 0) {
				for (int j = 0; j < loaninfolist.size(); j++) {
					if (loaninfolist.get(j) instanceof LoanReturnInfoBean) {
						LoanReturnInfoBean lrib = (LoanReturnInfoBean) loaninfolist
								.get(j);

						String orderno = lrib.getOrderNo();

						String[] orderarray = orderno.split("_");

						lssuingId = orderarray[1];
						break;
					}
				}
			}
			// 如果成功，处理相关记录
			if (verifySignature && ResultCode.equals("88")) {
				if("app".equals(Remark2)){
					this.setMessage("投资成功", "/wap/tenderList", "3");
				}else if("wap".equals(Remark2)){
					this.setMessage(this.Message, "/wap/toInverst", "3");
					return "mobile";
				}else{
					this.setMessage("投资成功", "/tender/tenderlist", "3");
				}
				
				return "success";
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setMessage(this.Message, "/tender/tenderlist", "3");
		if("wap".equals(Remark2)){
			this.setMessage(this.Message, "/wap/toInverst", "3");
			return "mobile";
		}
		return "success";
	}

	
	/**
	 * 投标异步返回通知处理
	 * 
	 * @return
	 */
	public void loantendernotify() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");

			Website website = this.websiteService.findWebsiteBywebsiteId(1);
			String publickey = website.getPublicKey();

			LoanJsonList = Common.UrlDecoder(LoanJsonList, "utf-8");
			MD5Trust md5 = new MD5Trust();
			RsaHelper rsa = RsaHelper.getInstance();
			String dataStr = LoanJsonList + PlatformMoneymoremore + Action + RandomTimeStamp + Remark1 + Remark2 + Remark3 + ResultCode;
//			
			if (antistate == 1) {
				dataStr = md5.getMD5Info(dataStr);
			}

			// 签名
			boolean verifySignature = rsa.verifySignature(SignInfo, dataStr,
					publickey);
			System.out.println("后台通知:" + verifySignature);
			System.out.println("返回码:" + ResultCode);
			System.out.println("返回次数:" + ReturnTimes);

			List<Object> loaninfolist = Common.JSONDecodeList(LoanJsonList,
					LoanReturnInfoBean.class);
			if (verifySignature && ResultCode.equals("88")) {
				if (loaninfolist != null && loaninfolist.size() > 0) {
					for (int j = 0; j < loaninfolist.size(); j++) {
						if (loaninfolist.get(j) instanceof LoanReturnInfoBean) {
							LoanReturnInfoBean lrib = (LoanReturnInfoBean) loaninfolist
									.get(j);

							String orderno = lrib.getOrderNo();
							String loanno = lrib.getLoanNo();
							String sql = "select  * from tender where serialnum='"
									+ loanno + "'";
							List list = tenderService.findTenderBySql(sql);
							if (list != null && list.size() > 0) {
								continue;
							}
							this.tenderlogic(lrib);

						}
					}
				}

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
	 * 债权转让异步返回通知处理
	 * 
	 * @return
	 */
	public void transferBuyNotify() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");

			Website website = this.websiteService.findWebsiteBywebsiteId(1);
			String publickey = website.getPublicKey();

			LoanJsonList = Common.UrlDecoder(LoanJsonList, "utf-8");
			MD5Trust md5 = new MD5Trust();
			RsaHelper rsa = RsaHelper.getInstance();
			String dataStr = LoanJsonList + PlatformMoneymoremore + Action + RandomTimeStamp + Remark1 + Remark2 + Remark3 + ResultCode;
//			
			if (antistate == 1) {
				dataStr = md5.getMD5Info(dataStr);
			}

			// 签名
			boolean verifySignature = rsa.verifySignature(SignInfo, dataStr,
					publickey);
			System.out.println("后台通知:" + verifySignature);
			System.out.println("返回码:" + ResultCode);
			System.out.println("返回次数:" + ReturnTimes);
			System.out.println("债权转让到这里了啊"+LoanJsonList);

			List<Object> loaninfolist = Common.JSONDecodeList(LoanJsonList,LoanReturnInfoBean.class);
			if (verifySignature && ResultCode.equals("88")) {
				if (loaninfolist != null && loaninfolist.size() > 0) {
					for (int j = 0; j < loaninfolist.size(); j++) {
						if (loaninfolist.get(j) instanceof LoanReturnInfoBean) {
							LoanReturnInfoBean lrib = (LoanReturnInfoBean) loaninfolist
									.get(j);

							String orderno = lrib.getOrderNo();
							String loanno = lrib.getLoanNo();
							String[] orderarray = orderno.split("_");
							String userid = orderarray[0];
							String tenderId = orderarray[2];
							Uservip uservip = this.uservipService.findUservipByUserid(Integer.valueOf(userid).intValue());
							Tender tender = this.tenderService.findTenderById(Integer.valueOf(tenderId).intValue());
							Transfer transfer  = this.transferService.findTransferByTenderId1(Integer.valueOf(tenderId));//根据购买id查找对象
							tender.setState(2);
							tender.setTransfer(Integer.valueOf(3));//已转让
							this.tenderService.updateTender(tender);
							double money = transfer.getProfit();//应收益
							//转让人
							Systemconf conf = systemconfService.findSystemconfByParname("con_bug_fee");//认购手续费
							Moneycount moneycount = this.moneycountService.findMoneycountByuserId(tender.getUservip().getUserId());//根据转让人id查找	
							//购买人
							Moneycount moneycount1 = this.moneycountService.findMoneycountByuserId(uservip.getUserId());//根据id查找
							//手续费
							double zr_fee = transfer.getMoney()*Double.valueOf(conf.getParvalue())/100.0D;
							Uservip changeUser = uservipService.findUservipByUserid(tender.getUservip().getUserId());
							String  sql="select * from Record where recordState=0 and tenderId="+tender.getTenderId();
							List<Record> record = this.recordService.findRecordByRecordId(sql);
							double benjin=0.0;
							double lixi = 0.0;
							for(Record re:record){
								benjin=benjin+re.getRecordMoney();
								lixi=lixi+re.getRecordRate();
								re.setUservip(uservip);
								this.recordService.updateRecord(re);
							}
							
							moneycount.setDueInMoney(Double.valueOf(moneycount
									.getDueInMoney().doubleValue()
									- benjin));

							moneycount.setCollectInterestTotalFee(moneycount
									.getCollectInterestTotalFee().doubleValue()
									- lixi);
							moneycount.setCollectTotalMoney(moneycount.getCollectTotalMoney()-money);//转让人的待收金额
							Moneyhistorydetail 	moneyhistorydetail = new Moneyhistorydetail();// 资金历史明细
							moneyhistorydetail.setAffectMoney(transfer.getMoney());// 影响金额
							moneyhistorydetail
									.setOccurTime(new Timestamp(new Date().getTime()));// 修改时间
							moneyhistorydetail.setAvailableBalance(moneycount.getAvailableMoney()+transfer.getMoney());// 可用余额
							moneyhistorydetail.setCollectMoney(moneycount.getCollectTotalMoney());// 待收金额
							moneyhistorydetail.setFrozenMoney(moneycount.getFrozenMoney());// 冻结金额
							moneyhistorydetail.setUservip(changeUser);// 借款人
							
							moneyhistorydetail.setIntroduction("标号：" + tender.getLssuing().getLssuingNum()
									+ "债权转让出售成功");
							this.moneyhistorydetailService
									.createMoneyhistorydetail(moneyhistorydetail);// 创建资金历史明细
							Moneyhistorydetail 	mhd = new Moneyhistorydetail();// 资金历史明细
							mhd.setAffectMoney(zr_fee);// 影响金额
							mhd.setOccurTime(new Timestamp(new Date().getTime()));// 修改时间
							mhd.setAvailableBalance(moneycount.getAvailableMoney()+transfer.getMoney()-zr_fee);// 可用余额
							mhd.setCollectMoney(moneycount.getCollectTotalMoney());// 待收金额
							mhd.setFrozenMoney(moneycount.getFrozenMoney());// 冻结金额
							mhd.setUservip(changeUser);// 借款人
							
							mhd.setIntroduction("标号：" + tender.getLssuing().getLssuingNum()
									+ "债权转让出售成功，认购手续费扣除");
							this.moneyhistorydetailService
									.createMoneyhistorydetail(mhd);// 创建资金历史明细
							
							moneycount.setAvailableMoney(moneycount.getAvailableMoney()+transfer.getMoney()-zr_fee);//转让人的余额
							this.moneycountService.updateMoneycount(moneycount);
							
							addInbox("债权转让", "您的资金发生变动，请注意查看", tender.getUservip().getUserId());
							//受让人
							
							moneycount1.setAvailableMoney(moneycount1.getAvailableMoney()-transfer.getMoney());//余额
							moneycount1.setCollectTotalMoney(moneycount1.getCollectTotalMoney()+money);//待收总金额
//							moneycount1.setDueInMoney(moneycount1.getDueInMoney()+benjin);//待收金额
//							moneycount1.setCollectInterestTotalFee(moneycount1.getCollectInterestTotalFee()+lixi);//待收利息总额
							
							addInbox("债权转让", "您的资金发生变动，请注意查看", uservip.getUserId());
							
							
							Moneyhistorydetail 	mh = new Moneyhistorydetail();// 资金历史明细
							mh.setAffectMoney(transfer.getMoney());// 影响金额
							mh.setOccurTime(new Timestamp(new Date().getTime()));// 修改时间
							mh.setAvailableBalance(moneycount1.getAvailableMoney());// 可用余额
							mh.setCollectMoney(moneycount1.getCollectTotalMoney());// 待收金额
							mh.setFrozenMoney(moneycount1.getFrozenMoney());// 冻结金额
							mh.setUservip(uservip);// 购买人
							
							mh.setIntroduction("标号：" + tender.getLssuing().getLssuingNum()
									+ "债权购买成功");
							this.moneyhistorydetailService
									.createMoneyhistorydetail(mh);// 创建资金历史明细
							transfer.setBuyUser(uservip);
							transfer.setBuyTime(new Timestamp(new Date().getTime()));
							transfer.setIsTransfer(Integer.valueOf(2));
							this.transferService.updateTransfer(transfer);

						}
					}
				}
				
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
	 * 投标业务逻辑处理
	 * 
	 * @param loanReturnInfoBean
	 *            返回处理的订单信息
	 * @throws Exception
	 * @throws NumberFormatException
	 */
	public void tenderlogic(LoanReturnInfoBean loanReturnInfoBean)
			throws NumberFormatException, Exception {
		String orderno = loanReturnInfoBean.getOrderNo();
		String loanno = loanReturnInfoBean.getLoanNo();
		String[] orderarray = orderno.split("_");

		String lssuingId = orderarray[1];
		String userid = orderarray[0];
		Tender tender = new Tender();
		Uservip uservip = this.uservipService.findUservipByUserid(Integer
				.valueOf(userid).intValue());
		Lssuing lssuing = this.lssuingService.findLssuingById(Integer.valueOf(
				lssuingId).intValue());
		tender.setBuynum(0);
		tender.setLssuing(lssuing);
		tender.setUservip(uservip);
		tender.setMoney((int)Double.valueOf(loanReturnInfoBean.getAmount()).doubleValue());
		tender.setSerialnum(loanno);
		tender.setTransferAudit(0);
		tender.setState(0);
		if(lssuing.getLssuingType()==9){
			tender.setState(1);
		}
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		tender.setTenderTime(timestamp);
		this.tenderService.addTender(tender);

		List<Tender> tenders = this.tenderService
				.findTendersBylssuingId(Integer.valueOf(lssuingId).intValue());
		int money = 0;
		if (tenders.size() > 0) {
			for (Tender tender3 : tenders) {
				money = tender3.getMoney().intValue() + money;
			}
		}
		Lssuing lssuing3 = this.lssuingService.findLssuingById(Integer
				.valueOf(lssuingId));
		int BorrowMoney = Integer.valueOf(lssuing3.getBorrowMoney()).intValue();
		if (BorrowMoney - money == 0) {
			lssuing3.setState(Integer.valueOf(1));
			if(lssuing3.getLssuingType()==9){//如果是理财标
				lssuing3.setState(Integer.valueOf(3));
			}else{
				lssuing3.setState(Integer.valueOf(1));
			}
			this.lssuingService.updateLssuing(lssuing3);			
		}
		if(lssuing3.getLssuingType()==9){//如果是理财标
			DecimalFormat df = new DecimalFormat("##.00");
			// 投标成功，生成还款计划
			Timestamp setVerify_time = new Timestamp(new Date().getTime());
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
			Calendar calender = Calendar.getInstance();
			calender.setTime(setVerify_time);

			format.format(calender.getTime());
			Repaynote repaynote;
			
			repaynote = new Repaynote();
			repaynote.setMoneyTwo(0.0);
			repaynote.setMoneyOne(0.0);
			Moneycount moneycount1 = new Moneycount();
			List tenderList = this.tenderService
					.findTendersBylssuingId(lssuing.getLssuingId()
							.intValue());
			Moneyhistorydetail moneyhistorydetail1 = new Moneyhistorydetail();
			if (lssuing.getPeriodday() != null) {
				String a = lssuing.getPeriodday().getPeriodDayName();
				calender.add(5,
						Integer.parseInt(a.substring(0, a.indexOf('天'))));
				repaynote.setRepayDate(new Timestamp(calender.getTime()
						.getTime()));
				repaynote.setMoneyTwo(Double.valueOf(Double.parseDouble(df
						.format(Double.valueOf(tender.getMoney())
								.doubleValue()
								* lssuing.getRate().doubleValue()
								/ 100.0D
								* Integer.parseInt(a.substring(0,
										a.indexOf('天')))))));
			} else {
				String a = lssuing.getPeriodtime().getPeriodTimeName();
				calender.add(2,
						Integer.parseInt(a.substring(0, a.indexOf('个'))));
				repaynote.setRepayDate(new Timestamp(calender.getTime()
						.getTime()));
				repaynote.setMoneyTwo(Double.valueOf(Double.parseDouble(df
						.format(Double.valueOf(tender.getMoney())
								.doubleValue()
								* lssuing.getRate().doubleValue()
								/ 1200.0D
								* Integer.parseInt(a.substring(0,
										a.indexOf('个')))))));
			}

			repaynote.setMoneyOne(Double.valueOf(tender.getMoney()));
			repaynote.setMoneyFour(Double.valueOf(df.format(repaynote
					.getMoneyOne().doubleValue()+repaynote.getMoneyTwo())));
			repaynote.setRepayState(Integer.valueOf(0));
			repaynote.setLssuing(lssuing);
			
			
			Moneycount tendmoneycount = this.moneycountService
					.findMoneycountByuserId(tender.getUservip().getUserId()
							.intValue());
			tendmoneycount.setAvailableMoney(Double.valueOf(tendmoneycount
					.getAvailableMoney().doubleValue()
					- Double.valueOf(tender.getMoney()).doubleValue()));

			moneyhistorydetail1 = new Moneyhistorydetail();

			moneyhistorydetail1.setAvailableBalance(tendmoneycount
					.getAvailableMoney().doubleValue());
			moneyhistorydetail1.setAffectMoney(Double.valueOf(tender.getMoney()));
			moneyhistorydetail1.setCollectMoney(Double.valueOf(df
					.format(tendmoneycount.getCollectTotalMoney()
							+ repaynote.getMoneyFour())));
			moneyhistorydetail1.setAvailableBalance(tendmoneycount
					.getAvailableMoney());
			moneyhistorydetail1.setOccurTime(new Timestamp(new Date()
					.getTime()));
			moneyhistorydetail1.setUservip(tender.getUservip());
			moneyhistorydetail1.setIntroduction("标号："
					+ lssuing.getLssuingNum() + "投资成功，待收金额增加");
			this.moneyhistorydetailService
					.createMoneyhistorydetail(moneyhistorydetail1);
			
//			tendmoneycount.setAvailableMoney(Double.valueOf(tendmoneycount
//					.getAvailableMoney().doubleValue()
//					- Double.valueOf(tender.getMoney()).doubleValue()));
			tendmoneycount.setDueInMoney(Double
					.valueOf(tendmoneycount.getDueInMoney()
							.doubleValue() +tender.getMoney()));
			
			tendmoneycount.setCollectTotalMoney(tendmoneycount.getCollectTotalMoney()+repaynote.getMoneyFour().doubleValue());;
			
			tendmoneycount.setTotalMoney(Double.valueOf(tendmoneycount
					.getTotalMoney().doubleValue()
					+ repaynote.getMoneyTwo().doubleValue()));
			tendmoneycount.setAccuInvestMoney(Double.valueOf(tendmoneycount
					.getAccuInvestMoney().doubleValue()
					+ repaynote.getMoneyOne().doubleValue()));
			tendmoneycount.setCollectInterestTotalFee(Double
					.valueOf(tendmoneycount.getCollectInterestTotalFee()
							.doubleValue() + repaynote.getMoneyTwo().doubleValue()));
			this.moneycountService.updateMoneycount(tendmoneycount);

			if ((lssuing.getReturnway().getReturnWayId().intValue() == 1)
					|| (lssuing.getReturnway().getReturnWayId().intValue() == 2)) {
				this.repaynoteService.addRepaynote(repaynote);

				Record record = new Record();
				Double tendmoney = Double.valueOf(tender.getMoney()
						.intValue());
				Double money21 = 0.0;
				if (lssuing.getPeriodtime() != null) {
					Integer mon21 = lssuing.getPeriodtime()
							.getPeriodTimeId();
					money21 = Double.valueOf(Double.parseDouble(df
							.format(Double.valueOf(
									tender.getMoney().intValue())
									.doubleValue()
									* lssuing.getRate().doubleValue()
									* mon21.intValue() / 1200.0D)));
				}
				if (lssuing.getPeriodday() != null) {
					Integer mon21 = lssuing.getPeriodday().getPeriodDayId();
					money21 = Double.valueOf(Double.parseDouble(df
							.format(Double.valueOf(
									tender.getMoney().intValue())
									.doubleValue()
									* lssuing.getRate().doubleValue()
									* mon21.intValue() / 100.0D)));
				}

				record.setRecordDate(repaynote.getRepayDate());
				record.setRecordMoney(tendmoney);
				record.setRecordRate(money21);
				record.setUservip(this.uservipService
						.findUservipByUserid(tender.getUservip()
								.getUserId().intValue()));
				record.setTender(tender);
				record.setRecordState(Integer.valueOf(0));

				this.recordService.addRecord(record);

			} else if (lssuing.getReturnway().getReturnWayId().intValue() == 3) {
				String mon1 = lssuing.getPeriodtime().getPeriodTimeName();
				String mon = lssuing.getPeriodtime().getPeriodTimeName();
				Integer mon21 = Integer.valueOf(Integer.parseInt(mon1
						.substring(0, mon.indexOf('个'))));
				repaynote = new Repaynote();
				repaynote.setMoneyOne(Double.valueOf(Double.parseDouble(df
						.format(Double.valueOf(tender.getMoney())
								.doubleValue() / Double.valueOf(mon21)))));
				double moneytwo = Double.valueOf(Double.parseDouble(df
						.format(Double.valueOf(tender.getMoney())
								.doubleValue()
								* lssuing.getRate().doubleValue()
								* Double.valueOf(mon21) / 1200.0D)));

				repaynote
						.setMoneyTwo(Double.valueOf(Double.parseDouble(df
								.format(Double.valueOf(tender.getMoney())
										.doubleValue()
										* lssuing.getRate().doubleValue()
										/ 1200.0D))));

				repaynote.setMoneyFour(Double.valueOf(repaynote
						.getMoneyOne().doubleValue()
						+ repaynote.getMoneyTwo().doubleValue()));
				repaynote.setUservip(this.uservipService
						.findUservipByUserid(lssuing.getUservip()
								.getUserId().intValue()));
				repaynote.setLssuing(lssuing);
				repaynote.setRepayState(Integer.valueOf(0));

				calender.setTime(setVerify_time);
				double tempmoneytwo = 0;
				double tempmoneyone = 0;
				Map<Integer, Double[]> hashMap = new HashMap<Integer, Double[]>();

				for (int i = 1; i < mon21.intValue() + 1; i++) {
					calender.add(2, 1);
					Repaynote temrepaynote = new Repaynote();
					temrepaynote.setMoneyOne(repaynote.getMoneyOne());
					temrepaynote.setMoneyTwo(repaynote.getMoneyTwo());
					temrepaynote.setMoneyFour(repaynote.getMoneyFour());
					temrepaynote.setUservip(repaynote.getUservip());
					temrepaynote.setLssuing(repaynote.getLssuing());
					temrepaynote.setRepayState(repaynote.getRepayState());

					if (i == mon21) {
						if ((tempmoneytwo + repaynote.getMoneyTwo()) != moneytwo) {
							temrepaynote.setMoneyTwo(moneytwo
									- tempmoneytwo);
						}
						if (tempmoneyone + repaynote.getMoneyOne() != Double
								.valueOf(lssuing.getBorrowMoney())) {
							temrepaynote.setMoneyOne(Double.valueOf(lssuing
									.getBorrowMoney()) - tempmoneyone);
						}
						temrepaynote.setMoneyFour(Double.valueOf(repaynote
								.getMoneyOne().doubleValue()
								+ repaynote.getMoneyTwo().doubleValue()));
					}

					tempmoneyone += repaynote.getMoneyOne();
					tempmoneytwo += repaynote.getMoneyTwo();

					temrepaynote.setRepayDate(new Timestamp(calender
							.getTime().getTime()));
					this.repaynoteService.addRepaynote(temrepaynote);

					Record record = new Record();
					record.setRecordMoney(temrepaynote.getMoneyOne());
					record.setRecordRate(temrepaynote.getMoneyTwo());

					record.setRecordDate(temrepaynote.getRepayDate());
					record.setUservip(this.uservipService
							.findUservipByUserid(tender.getUservip()
									.getUserId().intValue()));
					record.setTender(tender);
					record.setRecordState(Integer.valueOf(0));

					this.recordService.addRecord(record);

				}
			} else if (lssuing.getReturnway().getReturnWayId().intValue() == 4) {
				String mon1 = lssuing.getPeriodtime().getPeriodTimeName();
				String mon = lssuing.getPeriodtime().getPeriodTimeName();
				Integer mon21 = Integer.valueOf(Integer.parseInt(mon1
						.substring(0, mon.indexOf('个'))));
				repaynote = new Repaynote();
				repaynote.setMoneyOne(Double.valueOf(0.0D));
				repaynote
						.setMoneyTwo(Double.valueOf(Double.parseDouble(df
								.format(Double.valueOf(tender.getMoney())
										.doubleValue()
										* lssuing.getRate().doubleValue()
										/ 1200.0D))));
				double moneytwo = Double.valueOf(Double.parseDouble(df
						.format(Double.valueOf(tender.getMoney())
								.doubleValue()
								* lssuing.getRate().doubleValue()
								* Double.valueOf(mon21) / 1200.0D)));
				repaynote.setMoneyFour(Double.valueOf(Double.parseDouble(df
						.format(repaynote.getMoneyOne().doubleValue()
								+ repaynote.getMoneyTwo().doubleValue()))));
				repaynote.setUservip(this.uservipService
						.findUservipByUserid(lssuing.getUservip()
								.getUserId().intValue()));
				repaynote.setLssuing(lssuing);
				repaynote.setRepayState(Integer.valueOf(0));

				calender.setTime(setVerify_time);
				double tempmoneytwo = 0;
				Map<Integer, Double> hashMap = new HashMap<Integer, Double>();
				for (int i = 1; i < mon21.intValue(); i++) {
					calender.add(2, 1);
					Repaynote temrepaynote = new Repaynote();
					temrepaynote.setMoneyOne(repaynote.getMoneyOne());
					temrepaynote.setMoneyTwo(repaynote.getMoneyTwo());
					temrepaynote.setMoneyFour(repaynote.getMoneyFour());
					temrepaynote.setUservip(repaynote.getUservip());
					temrepaynote.setLssuing(repaynote.getLssuing());
					temrepaynote.setRepayState(repaynote.getRepayState());
					tempmoneytwo += repaynote.getMoneyTwo();

					temrepaynote.setRepayDate(new Timestamp(calender
							.getTime().getTime()));
					this.repaynoteService.addRepaynote(temrepaynote);

					Record record = new Record();
					record.setRecordDate(temrepaynote.getRepayDate());
					record.setRecordMoney(Double.valueOf(0.0D));
					record.setRecordRate(temrepaynote.getMoneyTwo());
					record.setUservip(this.uservipService
							.findUservipByUserid(tender.getUservip()
									.getUserId().intValue()));
					record.setTender(tender);
					record.setRecordState(Integer.valueOf(0));

					this.recordService.addRecord(record);

				}

				Repaynote temrepaynote = new Repaynote();
				temrepaynote.setMoneyOne(repaynote.getMoneyOne());
				temrepaynote.setMoneyTwo(repaynote.getMoneyTwo());
				temrepaynote.setMoneyFour(repaynote.getMoneyFour());
				temrepaynote.setUservip(repaynote.getUservip());
				temrepaynote.setLssuing(repaynote.getLssuing());
				temrepaynote.setRepayState(repaynote.getRepayState());

				if ((tempmoneytwo + temrepaynote.getMoneyTwo()) != moneytwo) {
					temrepaynote.setMoneyTwo(moneytwo - tempmoneytwo);
				}

				temrepaynote.setMoneyOne(Double.valueOf(lssuing
						.getBorrowMoney()));
				temrepaynote.setMoneyFour(Double.valueOf(temrepaynote
						.getMoneyOne().doubleValue()
						+ temrepaynote.getMoneyTwo().doubleValue()));
				calender.setTime(setVerify_time);
				calender.add(2, mon21.intValue());
				temrepaynote.setRepayDate(new Timestamp(calender.getTime()
						.getTime()));
				this.repaynoteService.addRepaynote(temrepaynote);

				Record record = new Record();
				record.setRecordRate(temrepaynote.getMoneyTwo());

				record.setRecordDate(temrepaynote.getRepayDate());
				record.setRecordMoney(Double.valueOf(tender.getMoney()
						.intValue()));

				record.setUservip(this.uservipService
						.findUservipByUserid(tender.getUservip()
								.getUserId().intValue()));
				record.setTender(tender);
				record.setRecordState(Integer.valueOf(0));
				this.recordService.addRecord(record);

			}
			

			addInbox("资金变动", "您的资金发生变动，请注意查看", tender.getUservip()
					.getUserId());
			addInbox("投资", "恭喜你的投资成功，标号为" + lssuing.getLssuingNum() + "！",
					tender.getUservip().getUserId());
			
		}else{
			Moneycount moneycount = this.moneycountService
					.findMoneycountByuserId(uservip.getUserId().intValue());
			Moneyhistorydetail moneyhistorydetail = new Moneyhistorydetail();
			;
			moneyhistorydetail.setAvailableBalance(Double.valueOf(moneycount
					.getAvailableMoney().doubleValue()
					- Double.valueOf(tender.getMoney()).doubleValue()));
			moneyhistorydetail.setFrozenMoney(Double.valueOf(moneycount
					.getFrozenMoney().doubleValue()
					+ Double.valueOf(tender.getMoney()).doubleValue()));
			moneyhistorydetail.setAffectMoney(Double.valueOf(tender.getMoney()));
			moneyhistorydetail.setCollectMoney(moneycount.getCollectTotalMoney());
			moneyhistorydetail.setIntroduction("投标成功，冻结投标金额");
			moneyhistorydetail.setUservip(uservip);
			moneyhistorydetail.setOccurTime(new Timestamp(new Date().getTime()));
			this.moneyhistorydetailService
					.createMoneyhistorydetail(moneyhistorydetail);

			moneycount.setAvailableMoney(Double.valueOf(moneycount
					.getAvailableMoney().doubleValue()
					- Double.valueOf(tender.getMoney()).doubleValue()));
			moneycount.setFrozenMoney(Double.valueOf(moneycount.getFrozenMoney()
					.doubleValue()
					+ Double.valueOf(tender.getMoney()).doubleValue()));
			this.moneycountService.updateMoneycount(moneycount);
		}
	}

	
	public String sdlctender() throws NumberFormatException, Exception{	
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		
		String tenderId = request.getParameter("tenderId");
		
		Tender tender = this.tenderService.findTenderById(Integer.valueOf(tenderId));

		int lssuingId = tender.getLssuing().getLssuingId();
		
		Lssuing lssuing = this.lssuingService.findLssuingById(Integer
				.valueOf(lssuingId));
		
		if(lssuing.getLssuingType()==9){//如果是理财标
			DecimalFormat df = new DecimalFormat("##.00");
			// 投标成功，生成还款计划
			Timestamp setVerify_time = new Timestamp(new Date().getTime());
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
			Calendar calender = Calendar.getInstance();
			calender.setTime(setVerify_time);

			format.format(calender.getTime());
			Repaynote repaynote;
			
			repaynote = new Repaynote();
			repaynote.setMoneyTwo(0.0);
			repaynote.setMoneyOne(0.0);
			if (lssuing.getPeriodday() != null) {
				String a = lssuing.getPeriodday().getPeriodDayName();
				calender.add(5,
						Integer.parseInt(a.substring(0, a.indexOf('天'))));
				repaynote.setRepayDate(new Timestamp(calender.getTime()
						.getTime()));
				repaynote.setMoneyTwo(Double.valueOf(Double.parseDouble(df
						.format(Double.valueOf(tender.getMoney())
								.doubleValue()
								* lssuing.getRate().doubleValue()
								/ 100.0D
								* Integer.parseInt(a.substring(0,
										a.indexOf('天')))))));
			} else {
				String a = lssuing.getPeriodtime().getPeriodTimeName();
				calender.add(2,
						Integer.parseInt(a.substring(0, a.indexOf('个'))));
				repaynote.setRepayDate(new Timestamp(calender.getTime()
						.getTime()));
				repaynote.setMoneyTwo(Double.valueOf(Double.parseDouble(df
						.format(Double.valueOf(tender.getMoney())
								.doubleValue()
								* lssuing.getRate().doubleValue()
								/ 1200.0D
								* Integer.parseInt(a.substring(0,
										a.indexOf('个')))))));
			}

			repaynote.setMoneyOne(Double.valueOf(tender.getMoney()));
			repaynote.setMoneyFour(Double.valueOf(df.format(repaynote
					.getMoneyOne().doubleValue()+repaynote.getMoneyTwo())));
			repaynote.setRepayState(Integer.valueOf(0));
			repaynote.setLssuing(lssuing);
		response.getWriter().write(lssuing.getReturnway().getReturnWayId().intValue());
		if ((lssuing.getReturnway().getReturnWayId().intValue() == 1)
				|| (lssuing.getReturnway().getReturnWayId().intValue() == 2)) {
			this.repaynoteService.addRepaynote(repaynote);

			Record record = new Record();
			Double tendmoney = Double.valueOf(tender.getMoney()
					.intValue());
			Double money21 = 0.0;
			if (lssuing.getPeriodtime() != null) {
				Integer mon21 = lssuing.getPeriodtime()
						.getPeriodTimeId();
				money21 = Double.valueOf(Double.parseDouble(df
						.format(Double.valueOf(
								tender.getMoney().intValue())
								.doubleValue()
								* lssuing.getRate().doubleValue()
								* mon21.intValue() / 1200.0D)));
			}
			if (lssuing.getPeriodday() != null) {
				Integer mon21 = lssuing.getPeriodday().getPeriodDayId();
				money21 = Double.valueOf(Double.parseDouble(df
						.format(Double.valueOf(
								tender.getMoney().intValue())
								.doubleValue()
								* lssuing.getRate().doubleValue()
								* mon21.intValue() / 100.0D)));
			}

			record.setRecordDate(repaynote.getRepayDate());
			record.setRecordMoney(tendmoney);
			record.setRecordRate(money21);
			record.setUservip(this.uservipService
					.findUservipByUserid(tender.getUservip()
							.getUserId().intValue()));
			record.setTender(tender);
			record.setRecordState(Integer.valueOf(0));

			this.recordService.addRecord(record);

		} else if (lssuing.getReturnway().getReturnWayId().intValue() == 3) {
			String mon1 = lssuing.getPeriodtime().getPeriodTimeName();
			String mon = lssuing.getPeriodtime().getPeriodTimeName();
			Integer mon21 = Integer.valueOf(Integer.parseInt(mon1
					.substring(0, mon.indexOf('个'))));
			repaynote = new Repaynote();
			repaynote.setMoneyOne(Double.valueOf(Double.parseDouble(df
					.format(Double.valueOf(tender.getMoney())
							.doubleValue() / Double.valueOf(mon21)))));
			double moneytwo = Double.valueOf(Double.parseDouble(df
					.format(Double.valueOf(tender.getMoney())
							.doubleValue()
							* lssuing.getRate().doubleValue()
							* Double.valueOf(mon21) / 1200.0D)));

			repaynote
					.setMoneyTwo(Double.valueOf(Double.parseDouble(df
							.format(Double.valueOf(tender.getMoney())
									.doubleValue()
									* lssuing.getRate().doubleValue()
									/ 1200.0D))));

			repaynote.setMoneyFour(Double.valueOf(repaynote
					.getMoneyOne().doubleValue()
					+ repaynote.getMoneyTwo().doubleValue()));
			repaynote.setUservip(this.uservipService
					.findUservipByUserid(lssuing.getUservip()
							.getUserId().intValue()));
			repaynote.setLssuing(lssuing);
			repaynote.setRepayState(Integer.valueOf(0));

			calender.setTime(setVerify_time);
			double tempmoneytwo = 0;
			double tempmoneyone = 0;
			Map<Integer, Double[]> hashMap = new HashMap<Integer, Double[]>();

			for (int i = 1; i < mon21.intValue() + 1; i++) {
				calender.add(2, 1);
				Repaynote temrepaynote = new Repaynote();
				temrepaynote.setMoneyOne(repaynote.getMoneyOne());
				temrepaynote.setMoneyTwo(repaynote.getMoneyTwo());
				temrepaynote.setMoneyFour(repaynote.getMoneyFour());
				temrepaynote.setUservip(repaynote.getUservip());
				temrepaynote.setLssuing(repaynote.getLssuing());
				temrepaynote.setRepayState(repaynote.getRepayState());

				if (i == mon21) {
					if ((tempmoneytwo + repaynote.getMoneyTwo()) != moneytwo) {
						temrepaynote.setMoneyTwo(moneytwo
								- tempmoneytwo);
					}
					if (tempmoneyone + repaynote.getMoneyOne() != Double
							.valueOf(lssuing.getBorrowMoney())) {
						temrepaynote.setMoneyOne(Double.valueOf(lssuing
								.getBorrowMoney()) - tempmoneyone);
					}
					temrepaynote.setMoneyFour(Double.valueOf(repaynote
							.getMoneyOne().doubleValue()
							+ repaynote.getMoneyTwo().doubleValue()));
				}

				tempmoneyone += repaynote.getMoneyOne();
				tempmoneytwo += repaynote.getMoneyTwo();

				temrepaynote.setRepayDate(new Timestamp(calender
						.getTime().getTime()));
				this.repaynoteService.addRepaynote(temrepaynote);

				Record record = new Record();
				record.setRecordMoney(temrepaynote.getMoneyOne());
				record.setRecordRate(temrepaynote.getMoneyTwo());

				record.setRecordDate(temrepaynote.getRepayDate());
				record.setUservip(this.uservipService
						.findUservipByUserid(tender.getUservip()
								.getUserId().intValue()));
				record.setTender(tender);
				record.setRecordState(Integer.valueOf(0));

				this.recordService.addRecord(record);

			}
		} else if (lssuing.getReturnway().getReturnWayId().intValue() == 4) {
			
			
			String mon1 = lssuing.getPeriodtime().getPeriodTimeName();
			String mon = lssuing.getPeriodtime().getPeriodTimeName();
			Integer mon21 = Integer.valueOf(Integer.parseInt(mon1
					.substring(0, mon.indexOf('个'))));
			response.getWriter().write(mon1);
			repaynote = new Repaynote();
			repaynote.setMoneyOne(Double.valueOf(0.0D));
			repaynote
					.setMoneyTwo(Double.valueOf(Double.parseDouble(df
							.format(Double.valueOf(tender.getMoney())
									.doubleValue()
									* lssuing.getRate().doubleValue()
									/ 1200.0D))));
			double moneytwo = Double.valueOf(Double.parseDouble(df
					.format(Double.valueOf(tender.getMoney())
							.doubleValue()
							* lssuing.getRate().doubleValue()
							* Double.valueOf(mon21) / 1200.0D)));
			repaynote.setMoneyFour(Double.valueOf(Double.parseDouble(df
					.format(repaynote.getMoneyOne().doubleValue()
							+ repaynote.getMoneyTwo().doubleValue()))));
			repaynote.setUservip(this.uservipService
					.findUservipByUserid(lssuing.getUservip()
							.getUserId().intValue()));
			repaynote.setLssuing(lssuing);
			repaynote.setRepayState(Integer.valueOf(0));

			calender.setTime(setVerify_time);
			double tempmoneytwo = 0;
			Map<Integer, Double> hashMap = new HashMap<Integer, Double>();
			for (int i = 1; i < mon21.intValue(); i++) {
				calender.add(2, 1);
				Repaynote temrepaynote = new Repaynote();
				temrepaynote.setMoneyOne(repaynote.getMoneyOne());
				temrepaynote.setMoneyTwo(repaynote.getMoneyTwo());
				temrepaynote.setMoneyFour(repaynote.getMoneyFour());
				temrepaynote.setUservip(repaynote.getUservip());
				temrepaynote.setLssuing(repaynote.getLssuing());
				temrepaynote.setRepayState(repaynote.getRepayState());
				tempmoneytwo += repaynote.getMoneyTwo();

				temrepaynote.setRepayDate(new Timestamp(calender
						.getTime().getTime()));
				this.repaynoteService.addRepaynote(temrepaynote);

				Record record = new Record();
				record.setRecordDate(temrepaynote.getRepayDate());
				record.setRecordMoney(Double.valueOf(0.0D));
				record.setRecordRate(temrepaynote.getMoneyTwo());
				record.setUservip(this.uservipService
						.findUservipByUserid(tender.getUservip()
								.getUserId().intValue()));
				record.setTender(tender);
				record.setRecordState(Integer.valueOf(0));

				this.recordService.addRecord(record);

			}

			Repaynote temrepaynote = new Repaynote();
			temrepaynote.setMoneyOne(repaynote.getMoneyOne());
			temrepaynote.setMoneyTwo(repaynote.getMoneyTwo());
			temrepaynote.setMoneyFour(repaynote.getMoneyFour());
			temrepaynote.setUservip(repaynote.getUservip());
			temrepaynote.setLssuing(repaynote.getLssuing());
			temrepaynote.setRepayState(repaynote.getRepayState());

			if ((tempmoneytwo + temrepaynote.getMoneyTwo()) != moneytwo) {
				temrepaynote.setMoneyTwo(moneytwo - tempmoneytwo);
			}

			temrepaynote.setMoneyOne(Double.valueOf(lssuing
					.getBorrowMoney()));
			temrepaynote.setMoneyFour(Double.valueOf(temrepaynote
					.getMoneyOne().doubleValue()
					+ temrepaynote.getMoneyTwo().doubleValue()));
			calender.setTime(setVerify_time);
			calender.add(2, mon21.intValue());
			temrepaynote.setRepayDate(new Timestamp(calender.getTime()
					.getTime()));
			this.repaynoteService.addRepaynote(temrepaynote);

			Record record = new Record();
			record.setRecordRate(temrepaynote.getMoneyTwo());

			record.setRecordDate(temrepaynote.getRepayDate());
			record.setRecordMoney(Double.valueOf(tender.getMoney()
					.intValue()));

			record.setUservip(this.uservipService
					.findUservipByUserid(tender.getUservip()
							.getUserId().intValue()));
			record.setTender(tender);
			record.setRecordState(Integer.valueOf(0));
			this.recordService.addRecord(record);

		}
		}
		
		response.getWriter().write("SUCCESS");
		response.getWriter().flush();
		response.getWriter().close();
		return null;
	}
	
	
	/**
	 * 投标复审返回处理
	 * 
	 * @return
	 */
	public String loantenderauditreturn() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");
			Website website = this.websiteService.findWebsiteBywebsiteId(1);
			String publickey = website.getPublicKey();

			MD5Trust md5 = new MD5Trust();
			RsaHelper rsa = RsaHelper.getInstance();
			String dataStr = LoanNoList + LoanNoListFail
					+ PlatformMoneymoremore + AuditType + RandomTimeStamp
					+ Remark1 + Remark2 + Remark3 + ResultCode;
			if (antistate == 1) {
				dataStr = md5.getMD5Info(dataStr);
			}

			// 签名
			boolean verifySignature = rsa.verifySignature(SignInfo, dataStr,
					publickey);
			System.out.println("后台通知:" + verifySignature);
			System.out.println("返回码:" + ResultCode);
			System.out.println("返回次数:" + ReturnTimes);

			if (verifySignature && ResultCode.equals("88")) {// 业务逻辑处理
				this.setMessage("操作成功",
						"/borrow/toBackLssuing?queryFlag=1", "3");
				return "success";
			} else {
				this.setMessage(this.Message,
						"/borrow/toBackLssuing?queryFlag=1", "3");
				return "success";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setMessage(this.Message, "/borrow/toBackLssuing?queryFlag=1",
				"3");
		return "success";
	}

	
	public String loanFailedreturn(){
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");
			Website website = this.websiteService.findWebsiteBywebsiteId(1);
			String publickey = website.getPublicKey();

			MD5Trust md5 = new MD5Trust();
			RsaHelper rsa = RsaHelper.getInstance();
			String dataStr = LoanNoList + LoanNoListFail
					+ PlatformMoneymoremore + AuditType + RandomTimeStamp
					+ Remark1 + Remark2 + Remark3 + ResultCode;
			if (antistate == 1) {
				dataStr = md5.getMD5Info(dataStr);
			}

			// 签名
			boolean verifySignature = rsa.verifySignature(SignInfo, dataStr,
					publickey);
			System.out.println("后台通知:" + verifySignature);
			System.out.println("返回码:" + ResultCode);
			System.out.println("返回次数:" + ReturnTimes);

			if (verifySignature && ResultCode.equals("88")) {// 业务逻辑处理
				borrowFail(this.Remark1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setMessage(this.Message, "/borrow/toBackLssuing?queryFlag=2",
				"3");
		return "success";
	}
	
	
	/**
	 * 投标复审异步通知处理
	 */
	public void loanFailednotify() {

		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");

			Website website = this.websiteService.findWebsiteBywebsiteId(1);
			String publickey = website.getPublicKey();

			MD5Trust md5 = new MD5Trust();
			RsaHelper rsa = RsaHelper.getInstance();
			String dataStr = LoanNoList + LoanNoListFail
					+ PlatformMoneymoremore + AuditType + RandomTimeStamp
					+ Remark1 + Remark2 + Remark3 + ResultCode;
			if (antistate == 1) {
				dataStr = md5.getMD5Info(dataStr);
			}

			// 签名
			boolean verifySignature = rsa.verifySignature(SignInfo, dataStr,
					publickey);
			System.out.println("后台通知:" + verifySignature);
			System.out.println("返回码:" + ResultCode);
			System.out.println("返回次数:" + ReturnTimes);

			if (verifySignature && ResultCode.equals("88")) {// 业务逻辑处理
				borrowFail(this.Remark1);
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
	 * 投标复审异步通知处理
	 */
	public void loantenderauditnotify() {
		System.out.println("复审异步进入..");
		SessionFactory sf = HibernateSessionFactory.getSessionFactory();

		sf.getCurrentSession().beginTransaction();
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");

			Website website = this.websiteService.findWebsiteBywebsiteId(1);
			String publickey = website.getPublicKey();

			MD5Trust md5 = new MD5Trust();
			RsaHelper rsa = RsaHelper.getInstance();
			String dataStr = LoanNoList + LoanNoListFail
					+ PlatformMoneymoremore + AuditType + RandomTimeStamp
					+ Remark1 + Remark2 + Remark3 + ResultCode;
			if (antistate == 1) {
				dataStr = md5.getMD5Info(dataStr);
			}

			// 签名
			boolean verifySignature = rsa.verifySignature(SignInfo, dataStr,
					publickey);
			System.out.println("后台通知:" + verifySignature);
			System.out.println("返回码:" + ResultCode);
			System.out.println("返回次数:" + ReturnTimes);

			if (verifySignature && ResultCode.equals("88")) {// 业务逻辑处理
				Lssuing ls = this.lssuingService.findLssuingById(Integer.valueOf(
						this.Remark1));

				if (ls.getState() != 3 || ls.getState() != 6) {
					if (AuditType.equals("1")) {// 复审通过，处理资金和业务
						borrowReauditSuccess(this.Remark1);
					} else if (AuditType.equals("2")) {// 复审不通过，处理资金和业务
						borrowReauditFail(this.Remark1);
					}
				}else{
					System.err.println("================================复审的标已处理===================================");
				}
				
				
			}

			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			response.getWriter().write("SUCCESS");
			response.getWriter().flush();
			response.getWriter().close();
			
			
			
			Lssuing lss = this.lssuingService.findLssuingById(Integer.valueOf(this.Remark1));
			if (lss.getState() == 3 || lss.getState() == 6) {
				System.err.println("===================复审多次=================："+lss.getLssuingId());
				sf.getCurrentSession().getTransaction().rollback();
			}else{
				lss.setState(3);
				sf.getCurrentSession().getTransaction().commit();
			}
			} catch (Exception e) {
				e.printStackTrace();
				try {
					System.err.println("出现错误开始回滚数据");
					// 如果发生异常，让事务回滚。
					if (sf.getCurrentSession().getTransaction().isActive()) {
						sf.getCurrentSession().getTransaction().rollback();
					}
				} catch (Throwable rbEx) {
					System.err.println(rbEx.toString());
				}
			} finally {
				sf.getCurrentSession().close();
			}

	}
	
	public String toBorrowReauditSuccess() throws Exception{

		borrowReauditSuccess(this.Remark1);
		Lssuing lss = this.lssuingService.findLssuingById(Integer.valueOf(this.Remark1));
		lss.setState(3);
		return "success";
	}
	
	/**
	 * 复审通过处理
	 * 
	 * @return
	 * @throws Exception
	 */
	public void borrowReauditSuccess(String lssuingId) throws Exception {
		
			
		
		Lssuing ls = this.lssuingService.findLssuingById(Integer.valueOf(
				lssuingId).intValue());

		if (ls.getState() == 3 || ls.getState() == 6) {
			return;
		}
		if(ls.getState()!=1){
			return;
		}
		
		if(ls.getState()==4){
			return;
		}
		
		
//		this.lssuingService.updateLssuing(ls);
		DecimalFormat df = new DecimalFormat("##.00");
		ls.setVerify_time(new Timestamp(new Date().getTime()));
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		Calendar calender = Calendar.getInstance();
		calender.setTime(ls.getVerify_time());
		long start = calender.getTimeInMillis();

		format.format(calender.getTime());
		Repaynote repaynote = new Repaynote();
		Moneycount moneycount = new Moneycount();
		List<Tender> tenderList = this.tenderService.findTendersBylssuingId(ls
				.getLssuingId().intValue());
		Moneyhistorydetail moneyhistorydetail = new Moneyhistorydetail();

		
		double borrowmoney = 0;//借款金额
		for(int i=0;i<tenderList.size();i++){
			Tender tender = tenderList.get(i);
			borrowmoney += tender.getMoney(); 
		}
		
		Uservip uservip1 = this.uservipService.findUservipByUserid(ls
				.getUservip().getUserId().intValue());
		repaynote.setUservip(uservip1);
		if (ls.getPeriodday() != null) {
			String a = ls.getPeriodday().getPeriodDayName();
			calender.add(5, Integer.parseInt(a.substring(0, a.indexOf('天'))));
			repaynote.setRepayDate(new Timestamp(calender.getTime().getTime()));
			repaynote
					.setMoneyTwo(Double.valueOf(Double.parseDouble(df
							.format(Double.valueOf(borrowmoney)
									.doubleValue()
									* ls.getRate().doubleValue()
									/ 100.0D
									* Integer.parseInt(a.substring(0,
											a.indexOf('天')))))));
		} else {
			String a = ls.getPeriodtime().getPeriodTimeName();
			calender.add(2, Integer.parseInt(a.substring(0, a.indexOf('个'))));
			repaynote.setRepayDate(new Timestamp(calender.getTime().getTime()));
			repaynote
					.setMoneyTwo(Double.valueOf(Double.parseDouble(df
							.format(Double.valueOf(borrowmoney)
									.doubleValue()
									* ls.getRate().doubleValue()
									/ 1200.0D
									* Integer.parseInt(a.substring(0,
											a.indexOf('个')))))));
		}

		repaynote.setMoneyOne(Double.valueOf(borrowmoney));
		repaynote.setMoneyFour(Double.valueOf(df.format(repaynote.getMoneyOne()
				.doubleValue() + repaynote.getMoneyTwo().doubleValue())));
		repaynote.setRepayState(Integer.valueOf(0));
		repaynote.setLssuing(ls);
		//添加还款时间2016-01-14
		String sql3 = "select * from(select  * from repaynote rp where rp.lssuingId="
				+ ls.getLssuingId()
				+ " and rp.repayState=0) a order by a.repayDate  ";
		List lt = this.repaynoteService.findRepayBySql(sql3);
		if ((lt != null) && (lt.size() > 0)) {
			ls.setEveryReturnTime(((Repaynote) lt.get(0)).getRepayDate());
		}
		this.lssuingService.updateLssuing(ls);
		
		moneycount = this.moneycountService.findMoneycountByuserId(ls
				.getUservip().getUserId().intValue());
		moneyhistorydetail = new Moneyhistorydetail();
		moneyhistorydetail.setAffectMoney(repaynote.getMoneyOne());
		moneyhistorydetail.setOccurTime(new Timestamp(new Date().getTime()));
		moneyhistorydetail.setAvailableBalance(Double.valueOf(df
				.format(moneycount.getAvailableMoney()
						+ repaynote.getMoneyOne())));
		moneyhistorydetail.setCollectMoney(Double.valueOf(df.format(moneycount.getCollectTotalMoney())));
		moneyhistorydetail.setFrozenMoney(moneycount.getFrozenMoney());
		moneyhistorydetail.setUservip(ls.getUservip());
		moneyhistorydetail.setIntroduction("标号：" + ls.getLssuingNum()
				+ "借款成功，开始还款");
		this.moneyhistorydetailService
				.createMoneyhistorydetail(moneyhistorydetail);
		moneycount.setAvailableMoney(Double.valueOf(moneycount
				.getAvailableMoney().doubleValue()
				+ repaynote.getMoneyOne().doubleValue()));
		moneycount.setTotalMoney(Double.valueOf(moneycount.getTotalMoney()
				.doubleValue() + repaynote.getMoneyOne().doubleValue()));
		moneycount.setPayInterestTotalFee(Double.valueOf(moneycount
				.getPayInterestTotalFee().doubleValue()
				+ repaynote.getMoneyTwo().doubleValue()));
		moneycount.setAccuBorrowMoney(Double.valueOf(moneycount
				.getAccuBorrowMoney().doubleValue()
				+ repaynote.getMoneyOne().doubleValue()));
		this.moneycountService.updateMoneycount(moneycount);

		// 借款管理费处理(复审通过收取借款人借款管理费)
		Systemconf sysconf = null;
		if ("1".equals(uservip1.getIsVIP())) {
			sysconf = this.systemconfService
					.findSystemconfByParname("con_borrow_manage_fee_2");

		} else {
			sysconf = this.systemconfService
					.findSystemconfByParname("con_borrow_manage_fee");
		}
		Double parvalue = Double.valueOf(sysconf.getParvalue());
		double fee = parvalue * repaynote.getMoneyOne() / 100.0;

		moneyhistorydetail = new Moneyhistorydetail();
		moneyhistorydetail.setAffectMoney(fee);
		moneyhistorydetail.setOccurTime(new Timestamp(new Date().getTime()));
		moneyhistorydetail.setAvailableBalance(Double.valueOf(df
				.format(moneycount.getAvailableMoney() - fee)));
		moneyhistorydetail.setUservip(ls.getUservip());
		moneyhistorydetail.setFrozenMoney(moneycount.getFrozenMoney());
		moneyhistorydetail.setCollectMoney(Double.valueOf(df.format(moneycount.getCollectTotalMoney())));
		moneyhistorydetail.setIntroduction("标号：" + ls.getLssuingNum()
				+ "借款成功，扣除借款管理费");
		this.moneyhistorydetailService
				.createMoneyhistorydetail(moneyhistorydetail);
		moneycount.setAvailableMoney(Double.valueOf(df.format(moneycount
				.getAvailableMoney() - fee)));
		moneycount.setLoanManageMoney(Double.valueOf(df.format(moneycount
				.getLoanManageMoney() + fee)));
		moneycount.setAccuProfitAndLossMoney(Double.valueOf(df
				.format(moneycount.getAccuProfitAndLossMoney() - fee)));
		moneycount.setTotalMoney(moneycount.getTotalMoney() - fee);
		this.moneycountService.updateMoneycount(moneycount);

		// 借款成交费(复审通过收取借款人借款成交费)
		if ("1".equals(uservip1.getIsVIP())) {
			sysconf = this.systemconfService
					.findSystemconfByParname("con_borrow_success_fee_2");
		} else {
			sysconf = this.systemconfService
					.findSystemconfByParname("con_borrow_success_fee");
		}
		parvalue = Double.valueOf(sysconf.getParvalue());
		fee = parvalue * repaynote.getMoneyOne() / 100.0;
		if (fee > 0) {
			moneyhistorydetail = new Moneyhistorydetail();
			moneyhistorydetail.setAffectMoney(fee);
			moneyhistorydetail
					.setOccurTime(new Timestamp(new Date().getTime()));
			moneyhistorydetail.setAvailableBalance(Double.valueOf(df
					.format(moneycount.getAvailableMoney() - fee)));
			moneyhistorydetail.setUservip(ls.getUservip());
			moneyhistorydetail.setFrozenMoney(moneycount.getFrozenMoney());
			moneyhistorydetail.setCollectMoney(Double.valueOf(df
					.format(moneycount.getCollectTotalMoney())));
			moneyhistorydetail.setIntroduction("标号：" + ls.getLssuingNum()
					+ "借款成功，扣除借款成交费");
			this.moneyhistorydetailService
					.createMoneyhistorydetail(moneyhistorydetail);
			moneycount.setAvailableMoney(Double.valueOf(df.format(moneycount
					.getAvailableMoney() - fee)));
			moneycount.setLoanManageMoney(Double.valueOf(df.format(moneycount
					.getLoanManageMoney() + fee)));
			moneycount.setAccuProfitAndLossMoney(Double.valueOf(df
					.format(moneycount.getAccuProfitAndLossMoney() - fee)));
			moneycount.setTotalMoney(moneycount.getTotalMoney() - fee);
			this.moneycountService.updateMoneycount(moneycount);
		}

		// 投标奖励
		Double lsawardmoney;
		if ((ls.getAwardRate() != null) && (!"".equals(ls.getAwardRate()))) {
			lsawardmoney = Double
					.valueOf(Double.valueOf(repaynote.getMoneyOne()
							* Double.valueOf(ls.getAwardRate()) / 100.0D));
		} else {
			if ((ls.getAwardMoney() != null)
					&& (!"".equals(ls.getAwardMoney())))
				lsawardmoney = Double.valueOf(ls.getAwardMoney());
			else
				lsawardmoney = Double.valueOf(0.0D);
		}
		if (lsawardmoney > 0) {
			moneyhistorydetail = new Moneyhistorydetail();
			moneyhistorydetail.setAffectMoney(lsawardmoney);
			moneyhistorydetail
					.setOccurTime(new Timestamp(new Date().getTime()));
			moneyhistorydetail.setAvailableBalance(Double.valueOf(df
					.format(moneycount.getAvailableMoney() - lsawardmoney)));
			moneyhistorydetail.setUservip(ls.getUservip());
			moneyhistorydetail.setFrozenMoney(moneycount.getFrozenMoney());
			moneyhistorydetail.setCollectMoney(moneycount.getCollectTotalMoney());
			moneyhistorydetail.setIntroduction("标号：" + ls.getLssuingNum()
					+ "借款成功，扣除借款奖励");
			this.moneyhistorydetailService
					.createMoneyhistorydetail(moneyhistorydetail);

			moneycount.setAccuPayBidReward(moneycount.getAccuPayBidReward()
					+ lsawardmoney);
			moneycount.setAvailableMoney(Double.valueOf(df.format(moneycount
					.getAvailableMoney() - lsawardmoney)));
			moneycount.setAccuProfitAndLossMoney(Double.valueOf(df
					.format(moneycount.getAccuProfitAndLossMoney()
							- lsawardmoney)));
			moneycount.setTotalMoney(Double.valueOf(df.format(moneycount
					.getTotalMoney() - lsawardmoney)));
			this.moneycountService.updateMoneycount(moneycount);
		}

		Website website = websiteService.findWebsiteBywebsiteId(1);
		website.setInvestment(website.getInvestment()
				+ Double.valueOf(ls.getBorrowMoney()));
		websiteService.updateWebsite(website);

		// 处理投资积分和借款积分
		double quota = 0;
		double itegral = 0;

		if (ls.getPeriodday() != null) {

			// 借款人积分
			quota = Double.valueOf(ls.getBorrowMoney())
					* Double.valueOf(ls
							.getPeriodday()
							.getPeriodDayName()
							.substring(
									0,
									ls.getPeriodday().getPeriodDayName()
											.indexOf('天')))
					* website.getInvestIntegral() / 1000;
			// 投资人积分
			for (Tender tender : tenderList) {

				itegral = Double.valueOf(tender.getMoney())
						* Double.valueOf(ls
								.getPeriodday()
								.getPeriodDayName()
								.substring(
										0,
										ls.getPeriodday().getPeriodDayName()
												.indexOf('天')))
						* website.getInvestIntegral() / 1000;

				Uservip userv = uservipService.findUservipByUserid(tender
						.getUservip().getUserId());
				Integralcategory integralcategory = this.integralcategoryService
						.findIntegralcategoryByintCategoryId(Integer.valueOf(2));
				Integraldetail integral = new Integraldetail();
				integral.setUservip(userv);
				integral.setIntegralcategory(integralcategory);
				integral.setIntegralQuota(itegral);
				integral.setIntegralTime(new Timestamp(new Date().getTime()));
				integral.setIntegralReason(integralcategory
						.getIntCategoryName());
				if (userv.getInvestItegral() != null
						&& userv.getItegral() != null) {
					integral.setRemainIntegral(userv.getInvestItegral()
							+ userv.getItegral() + itegral);
				} else if (userv.getInvestItegral() == null
						&& userv.getItegral() != null) {
					integral.setRemainIntegral(userv.getItegral() + itegral);
				} else if (userv.getInvestItegral() != null
						&& userv.getItegral() == null) {
					integral.setRemainIntegral(userv.getInvestItegral()
							+ itegral);
				} else {
					integral.setRemainIntegral(itegral);
				}
				integraldetailService.createIntegraldetail(integral);
				if (userv.getInvestItegral() != null) {
					userv.setInvestItegral(userv.getInvestItegral() + itegral);
				} else {
					userv.setInvestItegral(itegral);
				}
				uservipService.updateUservip(userv);
			}
		} else {

			// 借款人积分
			long end = calender.getTimeInMillis();
			int day = (int) ((end - start) / (3600 * 24 * 1000));// 积分
			quota = Double.valueOf(ls.getBorrowMoney()) / 1000 * day
					* website.getBorrowIntegral();

			// 投资人积分
			for (Tender tender : tenderList) {

				itegral = Double.valueOf(tender.getMoney()) / 1000 * day
						* website.getBorrowIntegral();

				Uservip userv = uservipService.findUservipByUserid(tender
						.getUservip().getUserId());
				Integralcategory integralcategory = this.integralcategoryService
						.findIntegralcategoryByintCategoryId(Integer.valueOf(2));
				Integraldetail integral = new Integraldetail();
				integral.setUservip(userv);
				integral.setIntegralcategory(integralcategory);
				integral.setIntegralQuota(itegral);
				integral.setIntegralTime(new Timestamp(new Date().getTime()));
				integral.setIntegralReason(integralcategory
						.getIntCategoryName());
				if (userv.getInvestItegral() != null
						&& userv.getItegral() != null) {
					integral.setRemainIntegral(userv.getInvestItegral()
							+ userv.getItegral() + itegral);
				} else if (userv.getInvestItegral() == null
						&& userv.getItegral() != null) {
					integral.setRemainIntegral(userv.getItegral() + itegral);
				} else if (userv.getInvestItegral() != null
						&& userv.getItegral() == null) {
					integral.setRemainIntegral(userv.getInvestItegral()
							+ itegral);
				} else {
					integral.setRemainIntegral(itegral);
				}
				integraldetailService.createIntegraldetail(integral);
				if (userv.getInvestItegral() != null) {
					userv.setInvestItegral(userv.getInvestItegral() + itegral);
				} else {
					userv.setInvestItegral(itegral);
				}
				uservipService.updateUservip(userv);
			}
		}

		// 借款人复审通过借款积分
		Uservip userv = uservipService.findUservipByUserid(ls.getUservip()
				.getUserId());
		Integralcategory integralcategory = this.integralcategoryService
				.findIntegralcategoryByintCategoryId(Integer.valueOf(3));
		Integraldetail integral = new Integraldetail();
		integral.setUservip(userv);
		integral.setIntegralcategory(integralcategory);
		integral.setIntegralQuota(quota);
		integral.setIntegralTime(new Timestamp(new Date().getTime()));
		integral.setIntegralReason(integralcategory.getIntCategoryName());
		if (userv.getInvestItegral() != null && userv.getItegral() != null) {
			integral.setRemainIntegral(userv.getInvestItegral()
					+ userv.getItegral() + itegral);
		} else if (userv.getInvestItegral() == null
				&& userv.getItegral() != null) {
			integral.setRemainIntegral(userv.getItegral() + itegral);
		} else if (userv.getInvestItegral() != null
				&& userv.getItegral() == null) {
			integral.setRemainIntegral(userv.getInvestItegral() + itegral);
		} else {
			integral.setRemainIntegral(itegral);
		}
		integraldetailService.createIntegraldetail(integral);
		if (userv.getInvestItegral() != null) {
			userv.setInvestItegral(userv.getInvestItegral() + itegral);
		} else {
			userv.setInvestItegral(itegral);
		}
		uservipService.updateUservip(userv);

		// 处理投资人资金情况
		if ((tenderList != null) && (tenderList.size() > 0)) {
			for (int j = 0; j < tenderList.size(); j++) {
				Tender tender = (Tender) tenderList.get(j);
				String mon = "";
				Integer mon2 = 0;
				Double money = Double.valueOf(Double.parseDouble(df
						.format(Double.valueOf(tender.getMoney().intValue()))));
				Double money2 = 0.0;
				if (ls.getPeriodtime() != null) {
					mon = ls.getPeriodtime().getPeriodTimeName();
					mon2 = Integer.valueOf(Integer.parseInt(mon.substring(0,
							mon.indexOf('个'))));
					money2 = Double.valueOf(Double.parseDouble(df.format(Double
							.valueOf(tender.getMoney().intValue())
							.doubleValue()
							* ls.getRate().doubleValue()
							* mon2.intValue()
							/ 1200.0D)));
				}
				if (ls.getPeriodday() != null) {
					mon = ls.getPeriodday().getPeriodDayName();
					mon2 = Integer.valueOf(Integer.parseInt(mon.substring(0,
							mon.indexOf('天'))));
					money2 = Double.valueOf(Double.parseDouble(df.format(Double
							.valueOf(tender.getMoney().intValue())
							.doubleValue()
							* ls.getRate().doubleValue()
							* mon2.intValue()
							/ 100.0D)));
				}

				Double money3;
				if ((ls.getAwardRate() != null)
						&& (!"".equals(ls.getAwardRate()))) {
					money3 = Double.valueOf(Double.valueOf(
							tender.getMoney().intValue()).doubleValue()
							* Double.valueOf(ls.getAwardRate()).doubleValue()
							/ 100.0D);
				} else {
					if ((ls.getAwardMoney() != null)
							&& (!"".equals(ls.getAwardMoney())))
						money3 = Double.valueOf(Double.valueOf(
								tender.getMoney().intValue()).doubleValue()
								/ Double.valueOf(ls.getBorrowMoney())
										.doubleValue()
								* Double.valueOf(ls.getAwardMoney())
										.doubleValue());
					else
						money3 = Double.valueOf(0.0D);
				}
				Uservip tenderuser = tender.getUservip();

				List recordList2 = this.recordService
						.findRecordByRecordId("select * from Record r where r.userId="
								+ tenderuser.getUserId()
								+ " and recordState=1 order by recordId desc");
				Record record2 = new Record();
				Double backMoney = Double.valueOf(0.0D);
				if ((recordList2 != null) && (recordList2.size() > 0)) {
					record2 = (Record) recordList2.get(0);

					String[] rate = website.getBackAward().split("\\|");
					Double money4 = Double.valueOf(0.0D);
					if ((ls.getPeriodtime() != null)
							&& (record2.getIspayward() == 0)) {
						Integer n = Integer.valueOf(Integer.parseInt(ls
								.getPeriodtime()
								.getPeriodTimeName()
								.substring(
										0,
										ls.getPeriodtime().getPeriodTimeName()
												.indexOf('个'))));
						if (record2.getRecordMoney().doubleValue() >= tender
								.getMoney().intValue())
							money4 = Double.valueOf(tender.getMoney()
									.intValue());
						else {
							money4 = record2.getRecordMoney();
						}
						if (n.intValue() == 1)
							backMoney = Double.valueOf(money4.doubleValue()
									* Double.valueOf(rate[0]).doubleValue()
									* 0.01D);
						else if (n.intValue() == 2)
							backMoney = Double.valueOf(money4.doubleValue()
									* Double.valueOf(rate[1]).doubleValue()
									* 0.01D);
						else {
							backMoney = Double.valueOf(money4.doubleValue()
									* Double.valueOf(rate[2]).doubleValue()
									* 0.01D);
						}
					}

				}

				Moneycount tendmoneycount = this.moneycountService
						.findMoneycountByuserId(tender.getUservip().getUserId()
								.intValue());
				moneyhistorydetail = new Moneyhistorydetail();
				moneyhistorydetail.setAffectMoney(money);
				moneyhistorydetail.setFrozenMoney(Double.valueOf(tendmoneycount
						.getFrozenMoney().doubleValue() - money.doubleValue()));
				moneyhistorydetail.setCollectMoney(Double.valueOf(df
						.format(tendmoneycount.getCollectTotalMoney()
								+ money.doubleValue() + money2.doubleValue())));
				moneyhistorydetail.setAvailableBalance(tendmoneycount
						.getAvailableMoney());
				moneyhistorydetail.setOccurTime(new Timestamp(new Date()
						.getTime()));
				moneyhistorydetail.setUservip(tender.getUservip());
				moneyhistorydetail.setIntroduction("标号：" + ls.getLssuingNum()
						+ "投资成功，待收金额增加");
				this.moneyhistorydetailService
						.createMoneyhistorydetail(moneyhistorydetail);
				// 投标奖励发放
				if (money3 > 0) {
					moneyhistorydetail = new Moneyhistorydetail();
					moneyhistorydetail.setAffectMoney(money3);
					moneyhistorydetail.setUservip(tender.getUservip());
					moneyhistorydetail.setAvailableBalance(tendmoneycount
							.getAvailableMoney() + money3);
					moneyhistorydetail.setFrozenMoney(Double
							.valueOf(tendmoneycount.getFrozenMoney()
									.doubleValue() - money.doubleValue()));
					moneyhistorydetail.setCollectMoney(Double
							.valueOf(tendmoneycount.getCollectTotalMoney()
									+ money.doubleValue()
									+ money2.doubleValue()));
					moneyhistorydetail.setOccurTime(new Timestamp(new Date()
							.getTime()));
					moneyhistorydetail.setIntroduction("标号："
							+ ls.getLssuingNum() + "，投标奖励发放");
					this.moneyhistorydetailService
							.createMoneyhistorydetail(moneyhistorydetail);
				}
				
				tendmoneycount.setAvailableMoney(Double.valueOf(tendmoneycount
						.getAvailableMoney().doubleValue()
						+ money3.doubleValue()));
				tendmoneycount.setDueInMoney(Double.valueOf(tendmoneycount
						.getDueInMoney().doubleValue()
						+ money.doubleValue()));
				tendmoneycount.setFrozenMoney(Double.valueOf(tendmoneycount
						.getFrozenMoney().doubleValue() - money.doubleValue()));
				tendmoneycount.setTotalMoney(Double.valueOf(tendmoneycount
						.getTotalMoney().doubleValue()
						+ money2.doubleValue()
						+ money3.doubleValue()));
				tendmoneycount.setAccuBidReward(Double.valueOf(tendmoneycount
						.getAccuBidReward().doubleValue()
						+ money3.doubleValue()));
				tendmoneycount.setAccuContinueBidReward(Double
						.valueOf(tendmoneycount.getAccuContinueBidReward()
								.doubleValue()));
				tendmoneycount.setAccuProfitAndLossMoney(Double
						.valueOf(tendmoneycount.getAccuProfitAndLossMoney()
								.doubleValue()
								+ money3.doubleValue()));
				tendmoneycount.setAccuInvestMoney(Double.valueOf(tendmoneycount
						.getAccuInvestMoney().doubleValue()
						+ money.doubleValue()));
				tendmoneycount.setCollectTotalMoney(tendmoneycount.getCollectTotalMoney()+money+money2);
				tendmoneycount.setCollectInterestTotalFee(Double
						.valueOf(tendmoneycount.getCollectInterestTotalFee()
								.doubleValue() + money2.doubleValue()));
				this.moneycountService.updateMoneycount(tendmoneycount);

				// 回款续投奖励
				if (backMoney > 0) {
					LoanUtils loanUtils = new LoanUtils();
					HttpServletRequest request = ServletActionContext
							.getRequest();
					String returnstr = loanUtils.loantraansfer(request,
							website, tender, 3, 2, "1", backMoney, ls,
							tenderuser, "returnInvest",
							"标号：" + ls.getLssuingNum() + "回款续投奖励发放");

					
					if (backMoney > 0) {}

				}
				//处理投资奖励
				//多层推荐奖励开始
				// 一级推荐投资奖励标准
				Systemconf auth_reward_sysconf = this.systemconfService
						.findSystemconfByParname("con_recommon_invest_reward");
				// 二级推荐投资奖励标准
				Systemconf auth_reward_sysconf2 = this.systemconfService
						.findSystemconfByParname("con_recommon_invest_reward2");
				// 三级推荐投资奖励标准
				Systemconf auth_reward_sysconf3 = this.systemconfService
						.findSystemconfByParname("con_recommon_invest_reward3");
				// 四级推荐投资奖励标准
				Systemconf auth_reward_sysconf4 = this.systemconfService
						.findSystemconfByParname("con_recommon_invest_reward4");
				// 五级推荐投资奖励标准
				Systemconf auth_reward_sysconf5 = this.systemconfService
						.findSystemconfByParname("con_recommon_invest_reward5");
				// 六级推荐投资奖励标准
				Systemconf auth_reward_sysconf6 = this.systemconfService
						.findSystemconfByParname("con_recommon_invest_reward6");
				// 六级以下推荐投资奖励标准
				Systemconf auth_reward_sysconf7 = this.systemconfService
						.findSystemconfByParname("con_recommon_invest_reward7");

				// 一级推荐人推荐奖励
				double auth_reward_fee = 0.0;
				// 二级推荐人推荐奖励
				double auth_reward_fee2 = 0.0;
				// 三级推荐人推荐奖励
				double auth_reward_fee3 = 0.0;
				// 四级推荐人推荐奖励
				double auth_reward_fee4 = 0.0;
				// 五级推荐人推荐奖励
				double auth_reward_fee5 = 0.0;
				// 六级推荐人推荐奖励
				double auth_reward_fee6 = 0.0;
				// 六级以下推荐人推荐奖励
				double auth_reward_fee7 = 0.0;

				// 一级推荐人
				Uservip auth_reward_user = new Uservip();
				// 二级推荐人
				Uservip auth_reward_user2 = new Uservip();
				// 三级推荐人
				Uservip auth_reward_user3 = new Uservip();
				// 四级推荐人
				Uservip auth_reward_user4 = new Uservip();
				// 五级推荐人
				Uservip auth_reward_user5 = new Uservip();
				// 六级推荐人
				Uservip auth_reward_user6 = new Uservip();
				// 六级以下推荐人
				Uservip auth_reward_user7 = new Uservip();

				if (auth_reward_sysconf != null
						&& auth_reward_sysconf.getParvalue() != null
						&& !"".equals(auth_reward_sysconf.getParvalue())) {
					auth_reward_fee = Double.valueOf(auth_reward_sysconf
							.getParvalue());
				}
				if (auth_reward_sysconf2 != null
						&& auth_reward_sysconf2.getParvalue() != null
						&& !"".equals(auth_reward_sysconf2.getParvalue())) {
					auth_reward_fee2 = Double.valueOf(auth_reward_sysconf2
							.getParvalue());
				}
				if (auth_reward_sysconf3 != null
						&& auth_reward_sysconf3.getParvalue() != null
						&& !"".equals(auth_reward_sysconf3.getParvalue())) {
					auth_reward_fee3 = Double.valueOf(auth_reward_sysconf3
							.getParvalue());
				}
				if (auth_reward_sysconf4 != null
						&& auth_reward_sysconf4.getParvalue() != null
						&& !"".equals(auth_reward_sysconf4.getParvalue())) {
					auth_reward_fee4 = Double.valueOf(auth_reward_sysconf4
							.getParvalue());
				}
				if (auth_reward_sysconf5 != null
						&& auth_reward_sysconf5.getParvalue() != null
						&& !"".equals(auth_reward_sysconf5.getParvalue())) {
					auth_reward_fee5 = Double.valueOf(auth_reward_sysconf5
							.getParvalue());
				}
				if (auth_reward_sysconf6 != null
						&& auth_reward_sysconf6.getParvalue() != null
						&& !"".equals(auth_reward_sysconf6.getParvalue())) {
					auth_reward_fee6 = Double.valueOf(auth_reward_sysconf6
							.getParvalue());
				}
				if (auth_reward_sysconf7 != null
						&& auth_reward_sysconf7.getParvalue() != null
						&& !"".equals(auth_reward_sysconf7.getParvalue())) {
					auth_reward_fee7 = Double.valueOf(auth_reward_sysconf7
							.getParvalue());
				}

				// 处理一级推荐人奖励情况
				if (auth_reward_fee > 0
						&& tender.getUservip().getUservip() != null
						&& tender.getUservip().getUservip().getUserId() != null) {

					auth_reward_user = tender.getUservip().getUservip();
					Moneycount recmmonMoneycount = this.moneycountService
							.findMoneycountByuserId(auth_reward_user
									.getUserId().intValue());
					double auth_reward_money = Double.valueOf(df
							.format(tender.getMoney() * auth_reward_fee
									/ 100.0D));

					LoanUtils loanUtils = new LoanUtils();
					HttpServletRequest request = ServletActionContext
							.getRequest();
					String returnstr = loanUtils.loantraansfer(request,
							website, tender, 3, 2, "1", auth_reward_money, ls,
							auth_reward_user, "InvestmentIncentives",
							"您推荐的一级推荐用户："
									+ tender.getUservip().getUserName()
									+ "投资了：" + tender.getMoney()
									+ ",发放推荐投资奖励");

					//auth_reward_money = this.returnInvest(returnstr, website);
					
					if(auth_reward_money>0){}

				}

				// 处理二级推荐人奖励情况
				if (auth_reward_fee2 > 0
						&& auth_reward_user.getUservip() != null
						&& auth_reward_user.getUservip().getUserId() != null) {

					auth_reward_user2 = auth_reward_user.getUservip();
					Moneycount recmmonMoneycount = this.moneycountService
							.findMoneycountByuserId(auth_reward_user2
									.getUserId().intValue());
					double auth_reward_money = Double.valueOf(df
							.format(tender.getMoney() * auth_reward_fee2
									/ 100.0D));
					
					LoanUtils loanUtils = new LoanUtils();
					HttpServletRequest request = ServletActionContext
							.getRequest();
					String returnstr = loanUtils.loantraansfer(request,
							website, tender, 3, 2, "1", auth_reward_money, ls,
							auth_reward_user2, "InvestmentIncentives",
							"您推荐的二级推荐用户："
									+ tender.getUservip().getUserName()
									+ "投资了：" + tender.getMoney()
									+ ",发放推荐投资奖励");

					//auth_reward_money = this.returnInvest(returnstr, website);
					
					if(auth_reward_money>0){}
				}
				// 处理三级推荐人奖励情况
				if (auth_reward_fee3 > 0
						&& auth_reward_user2.getUservip() != null
						&& auth_reward_user2.getUservip().getUserId() != null) {

					auth_reward_user3 = auth_reward_user2.getUservip();
					Moneycount recmmonMoneycount = this.moneycountService
							.findMoneycountByuserId(auth_reward_user3
									.getUserId().intValue());
					double auth_reward_money = Double.valueOf(df
							.format(tender.getMoney() * auth_reward_fee3
									/ 100.0D));
					
					LoanUtils loanUtils = new LoanUtils();
					HttpServletRequest request = ServletActionContext
							.getRequest();
					String returnstr = loanUtils.loantraansfer(request,
							website, tender, 3, 2, "1", auth_reward_money, ls,
							auth_reward_user3, "InvestmentIncentives",
							"您推荐的三级推荐用户："
									+ tender.getUservip().getUserName()
									+ "投资了：" + tender.getMoney()
									+ ",发放推荐投资奖励");

					//auth_reward_money = this.returnInvest(returnstr, website);
					
					if(auth_reward_money>0){}
				}

				// 处理四级推荐人奖励情况
				if (auth_reward_fee4 > 0
						&& auth_reward_user3.getUservip() != null
						&& auth_reward_user3.getUservip().getUserId() != null) {

					auth_reward_user4 = auth_reward_user3.getUservip();
					Moneycount recmmonMoneycount = this.moneycountService
							.findMoneycountByuserId(auth_reward_user4
									.getUserId().intValue());
					double auth_reward_money = Double.valueOf(df
							.format(tender.getMoney() * auth_reward_fee4
									/ 100.0D));
					LoanUtils loanUtils = new LoanUtils();
					HttpServletRequest request = ServletActionContext
							.getRequest();
					String returnstr = loanUtils.loantraansfer(request,
							website, tender, 3, 2, "1", auth_reward_money, ls,
							auth_reward_user4, "InvestmentIncentives",
							"您推荐的四级推荐用户："
									+ tender.getUservip().getUserName()
									+ "投资了：" + tender.getMoney()
									+ ",发放推荐投资奖励");

					//auth_reward_money = this.returnInvest(returnstr, website);
					
					if(auth_reward_money>0){}
				}
				// 处理五级推荐人奖励情况
				if (auth_reward_fee5 > 0
						&& auth_reward_user4.getUservip() != null
						&& auth_reward_user4.getUservip().getUserId() != null) {

					auth_reward_user5 = auth_reward_user4.getUservip();
					Moneycount recmmonMoneycount = this.moneycountService
							.findMoneycountByuserId(auth_reward_user5
									.getUserId().intValue());
					double auth_reward_money = Double.valueOf(df
							.format(tender.getMoney() * auth_reward_fee5
									/ 100.0D));
					LoanUtils loanUtils = new LoanUtils();
					HttpServletRequest request = ServletActionContext
							.getRequest();
					String returnstr = loanUtils.loantraansfer(request,
							website, tender, 3, 2, "1", auth_reward_money, ls,
							auth_reward_user5, "InvestmentIncentives",
							"您推荐的五级推荐用户："
									+ tender.getUservip().getUserName()
									+ "投资了：" + tender.getMoney()
									+ ",发放推荐投资奖励");

					//auth_reward_money = this.returnInvest(returnstr, website);
					
					if(auth_reward_money>0){}
				}
				// 处理六级推荐人奖励情况
				if (auth_reward_fee6 > 0
						&& auth_reward_user5.getUservip() != null
						&& auth_reward_user5.getUservip().getUserId() != null) {

					auth_reward_user6 = auth_reward_user5.getUservip();
					Moneycount recmmonMoneycount = this.moneycountService
							.findMoneycountByuserId(auth_reward_user6
									.getUserId().intValue());
					double auth_reward_money = Double.valueOf(df
							.format(tender.getMoney() * auth_reward_fee6
									/ 100.0D));
					LoanUtils loanUtils = new LoanUtils();
					HttpServletRequest request = ServletActionContext
							.getRequest();
					String returnstr = loanUtils.loantraansfer(request,
							website, tender, 3, 2, "1", auth_reward_money, ls,
							auth_reward_user6, "InvestmentIncentives",
							"您推荐的六级推荐用户："
									+ tender.getUservip().getUserName()
									+ "投资了：" + tender.getMoney()
									+ ",发放推荐投资奖励");

					//auth_reward_money = this.returnInvest(returnstr, website);
					
					if(auth_reward_money>0){}
				}

				//多层推荐结束
				
				
				
				

				tender.setTransferAudit(1);
				tender.setState(1);
				tenderService.updateTender(tender);

				addInbox("资金变动", "您的资金发生变动，请注意查看", tender.getUservip()
						.getUserId());
				addInbox("投资", "恭喜你的投资成功，标号为" + ls.getLssuingNum() + "！",
						tender.getUservip().getUserId());
			}
		}
		if ((ls.getReturnway().getReturnWayId().intValue() == 1)
				|| (ls.getReturnway().getReturnWayId().intValue() == 2)) {
			this.repaynoteService.addRepaynote(repaynote);

			if ((tenderList != null) && (tenderList.size() > 0))
				for (int i = 0; i < tenderList.size(); i++) {
					Tender tender = (Tender) tenderList.get(i);

					Record record = new Record();
					Double money = Double.valueOf(tender.getMoney().intValue());
					Double money2 = 0.0;
					if (ls.getPeriodtime() != null) {
						Integer mon2 = ls.getPeriodtime().getPeriodTimeId();
						money2 = Double.valueOf(Double.parseDouble(df
								.format(Double.valueOf(
										tender.getMoney().intValue())
										.doubleValue()
										* ls.getRate().doubleValue()
										* mon2.intValue() / 1200.0D)));
					}
					if (ls.getPeriodday() != null) {
						Integer mon2 = ls.getPeriodday().getPeriodDayId();
						money2 = Double.valueOf(Double.parseDouble(df
								.format(Double.valueOf(
										tender.getMoney().intValue())
										.doubleValue()
										* ls.getRate().doubleValue()
										* mon2.intValue() / 100.0D)));
					}

					record.setRecordDate(repaynote.getRepayDate());
					record.setRecordMoney(money);
					record.setRecordRate(money2);
					record.setUservip(this.uservipService
							.findUservipByUserid(tender.getUservip()
									.getUserId().intValue()));
					record.setTender(tender);
					record.setRecordState(Integer.valueOf(0));

					this.recordService.addRecord(record);
					System.err.println("========================="+record.getRecordId());
				}
		} else if (ls.getReturnway().getReturnWayId().intValue() == 3) {
			String mon = ls.getPeriodtime().getPeriodTimeName();
			Integer mon2 = Integer.valueOf(Integer.parseInt(mon.substring(0,
					mon.indexOf('个'))));
			repaynote = new Repaynote();
			repaynote.setMoneyOne(Double.valueOf(Double.parseDouble(df
					.format(Double.valueOf(borrowmoney).doubleValue()
							/ Double.valueOf(mon2)))));
			double moneytwo = Double.valueOf(Double.parseDouble(df
					.format(Double.valueOf(borrowmoney).doubleValue()
							* ls.getRate().doubleValue() * Double.valueOf(mon2)
							/ 1200.0D)));

			repaynote.setMoneyTwo(Double.valueOf(Double.parseDouble(df
					.format(Double.valueOf(borrowmoney).doubleValue()
							* ls.getRate().doubleValue() / 1200.0D))));

			repaynote.setMoneyFour(Double.valueOf(repaynote.getMoneyOne()
					.doubleValue() + repaynote.getMoneyTwo().doubleValue()));
			repaynote.setUservip(this.uservipService.findUservipByUserid(ls
					.getUservip().getUserId().intValue()));
			repaynote.setLssuing(ls);
			repaynote.setRepayState(Integer.valueOf(0));

			calender.setTime(ls.getVerify_time());
			double tempmoneytwo = 0;
			double tempmoneyone = 0;
			Map<Integer, Double[]> hashMap = new HashMap<Integer, Double[]>();

			for (int i = 1; i < mon2.intValue() + 1; i++) {
				calender.add(2, 1);
				Repaynote temrepaynote = new Repaynote();
				temrepaynote.setMoneyOne(repaynote.getMoneyOne());
				temrepaynote.setMoneyTwo(repaynote.getMoneyTwo());
				temrepaynote.setMoneyFour(repaynote.getMoneyFour());
				temrepaynote.setUservip(repaynote.getUservip());
				temrepaynote.setLssuing(repaynote.getLssuing());
				temrepaynote.setRepayState(repaynote.getRepayState());

				if (i == mon2) {
					if ((tempmoneytwo + repaynote.getMoneyTwo()) != moneytwo) {
						temrepaynote.setMoneyTwo(moneytwo - tempmoneytwo);
					}
					if (tempmoneyone + repaynote.getMoneyOne() != Double
							.valueOf(ls.getBorrowMoney())) {
						temrepaynote.setMoneyOne(Double.valueOf(borrowmoney) - tempmoneyone);
					}
					temrepaynote.setMoneyFour(Double.valueOf(repaynote
							.getMoneyOne().doubleValue()
							+ repaynote.getMoneyTwo().doubleValue()));
				}

				tempmoneyone += repaynote.getMoneyOne();
				tempmoneytwo += repaynote.getMoneyTwo();

				temrepaynote.setRepayDate(new Timestamp(calender.getTime()
						.getTime()));
				this.repaynoteService.addRepaynote(temrepaynote);
				if ((tenderList != null) && (tenderList.size() > 0)) {
					for (int j = 0; j < tenderList.size(); j++) {
						Tender tender = (Tender) tenderList.get(j);

						Double money = Double.valueOf(Double.parseDouble(df
								.format(Double.valueOf(
										tender.getMoney().intValue())
										.doubleValue()
										/ Double.valueOf(mon2.intValue()))));
						Double money2 = Double.valueOf(df.format(Double
								.valueOf(tender.getMoney().intValue())
								.doubleValue()
								* ls.getRate() / 1200.0D));

						Record record = new Record();
						record.setRecordMoney(money);
						record.setRecordRate(money2);
						if (i == mon2) {
							double money2total = Double.valueOf(df
									.format(Double.valueOf(
											tender.getMoney().intValue())
											.doubleValue()
											* ls.getRate()
											* Double.valueOf(mon2) / 1200.0D));
							;
							if (hashMap.get(tender.getTenderId()) != null) {
								Double[] temp = hashMap.get(tender
										.getTenderId());
								if (temp[0] + money != tender.getMoney()) {
									record.setRecordMoney(tender.getMoney()
											- temp[0]);
								}
								if (temp[1] + money2 != money2total) {
									record.setRecordRate(money2total - temp[1]);
								}
							}
						}
						if (hashMap.get(tender.getTenderId()) != null) {
							Double[] temp = hashMap.get(tender.getTenderId());
							temp[0] += money;
							temp[1] += money2;
							hashMap.put(tender.getTenderId(), temp);
						} else {
							Double[] temp = new Double[2];
							temp[0] = money;
							temp[1] = money2;
							hashMap.put(tender.getTenderId(), temp);
						}
						record.setRecordDate(temrepaynote.getRepayDate());
						record.setUservip(this.uservipService
								.findUservipByUserid(tender.getUservip()
										.getUserId().intValue()));
						record.setTender(tender);
						record.setRecordState(Integer.valueOf(0));

						this.recordService.addRecord(record);
					}
				}
			}
		} else if (ls.getReturnway().getReturnWayId().intValue() == 4) {
			String mon = ls.getPeriodtime().getPeriodTimeName();
			Integer mon2 = Integer.valueOf(Integer.parseInt(mon.substring(0,
					mon.indexOf('个'))));
			repaynote = new Repaynote();
			repaynote.setMoneyOne(Double.valueOf(0.0D));
			repaynote.setMoneyTwo(Double.valueOf(Double.parseDouble(df
					.format(Double.valueOf(borrowmoney).doubleValue()
							* ls.getRate().doubleValue() / 1200.0D))));
			double moneytwo = Double.valueOf(Double.parseDouble(df
					.format(Double.valueOf(borrowmoney).doubleValue()
							* ls.getRate().doubleValue() * Double.valueOf(mon2)
							/ 1200.0D)));
			repaynote.setMoneyFour(Double.valueOf(Double.parseDouble(df
					.format(repaynote.getMoneyOne().doubleValue()
							+ repaynote.getMoneyTwo().doubleValue()))));
			repaynote.setUservip(this.uservipService.findUservipByUserid(ls
					.getUservip().getUserId().intValue()));
			repaynote.setLssuing(ls);
			repaynote.setRepayState(Integer.valueOf(0));

			calender.setTime(ls.getVerify_time());
			double tempmoneytwo = 0;
			Map<Integer, Double> hashMap = new HashMap<Integer, Double>();
			for (int i = 1; i < mon2.intValue(); i++) {
				calender.add(2, 1);
				Repaynote temrepaynote = new Repaynote();
				temrepaynote.setMoneyOne(repaynote.getMoneyOne());
				temrepaynote.setMoneyTwo(repaynote.getMoneyTwo());
				temrepaynote.setMoneyFour(repaynote.getMoneyFour());
				temrepaynote.setUservip(repaynote.getUservip());
				temrepaynote.setLssuing(repaynote.getLssuing());
				temrepaynote.setRepayState(repaynote.getRepayState());
				tempmoneytwo += repaynote.getMoneyTwo();

				temrepaynote.setRepayDate(new Timestamp(calender.getTime()
						.getTime()));
				this.repaynoteService.addRepaynote(temrepaynote);
				if ((tenderList != null) && (tenderList.size() > 0)) {
					for (int j = 0; j < tenderList.size(); j++) {
						Tender tender = (Tender) tenderList.get(j);

						Double money2 = Double.valueOf(df.format(Double
								.valueOf(tender.getMoney().intValue())
								.doubleValue()
								* ls.getRate() / 1200.0D));
						if (hashMap.get(tender.getTenderId()) != null) {
							Double temp = hashMap.get(tender.getTenderId());
							temp += money2;
							hashMap.put(tender.getTenderId(), temp);
						} else {
							Double temp = money2;
							hashMap.put(tender.getTenderId(), temp);
						}

						Record record = new Record();
						record.setRecordDate(temrepaynote.getRepayDate());
						record.setRecordMoney(Double.valueOf(0.0D));
						record.setRecordRate(money2);
						record.setUservip(this.uservipService
								.findUservipByUserid(tender.getUservip()
										.getUserId().intValue()));
						record.setTender(tender);
						record.setRecordState(Integer.valueOf(0));

						this.recordService.addRecord(record);
					}
				}

			}

			Repaynote temrepaynote = new Repaynote();
			temrepaynote.setMoneyOne(repaynote.getMoneyOne());
			temrepaynote.setMoneyTwo(repaynote.getMoneyTwo());
			temrepaynote.setMoneyFour(repaynote.getMoneyFour());
			temrepaynote.setUservip(repaynote.getUservip());
			temrepaynote.setLssuing(repaynote.getLssuing());
			temrepaynote.setRepayState(repaynote.getRepayState());

			if ((tempmoneytwo + temrepaynote.getMoneyTwo()) != moneytwo) {
				temrepaynote.setMoneyTwo(moneytwo - tempmoneytwo);
			}

			temrepaynote.setMoneyOne(Double.valueOf(borrowmoney));
			temrepaynote.setMoneyFour(Double.valueOf(temrepaynote.getMoneyOne()
					.doubleValue() + temrepaynote.getMoneyTwo().doubleValue()));
			calender.setTime(ls.getVerify_time());
			calender.add(2, mon2.intValue());
			temrepaynote.setRepayDate(new Timestamp(calender.getTime()
					.getTime()));
			this.repaynoteService.addRepaynote(temrepaynote);

			if ((tenderList != null) && (tenderList.size() > 0)) {
				for (int j = 0; j < tenderList.size(); j++) {
					Tender tender = (Tender) tenderList.get(j);

					Double money2 = Double.valueOf(df.format(Double.valueOf(
							tender.getMoney().intValue()).doubleValue()
							* ls.getRate() / 1200.0D));
					double money2total = Double.valueOf(df.format(Double
							.valueOf(tender.getMoney().intValue())
							.doubleValue()
							* ls.getRate() * Double.valueOf(mon2) / 1200.0D));
					;
					Double temp = 0.0;
					if (hashMap.get(tender.getTenderId()) != null) {
						temp = hashMap.get(tender.getTenderId());
					}

					Record record = new Record();
					record.setRecordRate(money2);
					if (temp + money2 != money2total) {
						record.setRecordRate(money2total - temp);
					}

					record.setRecordDate(temrepaynote.getRepayDate());
					record.setRecordMoney(Double.valueOf(tender.getMoney()
							.intValue()));

					record.setUservip(this.uservipService
							.findUservipByUserid(tender.getUservip()
									.getUserId().intValue()));
					record.setTender(tender);
					record.setRecordState(Integer.valueOf(0));
					this.recordService.addRecord(record);
				}
			}

		}

		addInbox("资金变动", "您的资金发生变动，请注意查看", userv.getUserId());
		addInbox("借款", "恭喜你的借款成功，标号为" + ls.getLssuingNum() + "！",
				userv.getUserId());

		// 如果是秒标、自动还款
		if (ls.getLssuingType().intValue() == 3 && ls.getState() == 3) {
			//autorepay(ls);
		}
		
		
		
	}

	/**
	 * 秒标自动还款
	 * 
	 * @param lssuing
	 * @throws Exception
	 */
	private void autorepay(Lssuing lssuing) throws Exception {

		int lssuingId = lssuing.getLssuingId();
		Repaynote repaynote = new Repaynote();
		if ((this.repaynoteService.findRepaynoteBylssuing(lssuingId) != null)
				&& (this.repaynoteService.findRepaynoteBylssuing(lssuingId)
						.size() > 0)) {
			repaynote = (Repaynote) this.repaynoteService
					.findRepaynoteBylssuing(lssuingId).get(0);
			repaynote.setRepayDate(new Timestamp(new Date().getTime()));
			repaynote.setMoneyThree(repaynote.getMoneyFour());
			repaynote.setRepayState(Integer.valueOf(1));
			this.repaynoteService.updateRepaynote(repaynote);

			HttpServletRequest request = ServletActionContext.getRequest();

			String sql4 = "select * from Record r where r.tenderId in (select tenderId from Tender t where t.lssuingId="
					+ lssuing.getLssuingId() + ") and recordState=0";
			List<Record> recordList4 = this.recordService
					.findRecordByRecordId(sql4);
			Website website = websiteService.findWebsiteBywebsiteId(1);
			LoanUtils loanUtils = new LoanUtils();
			// 提交托管
			String resultstr = loanUtils.borrowRepay(repaynote, true, website,
					recordList4, request, lssuing, null, null);
			boolean key = false;
			// 转账
			List<Object> loanobjectlist = Common.JSONDecodeList(resultstr,
					LoanTransferReturnBean.class);
			if (loanobjectlist != null && loanobjectlist.size() > 0) {
				MD5Trust md5 = new MD5Trust();
				RsaHelper rsa = RsaHelper.getInstance();
				for (int i = 0; i < loanobjectlist.size(); i++) {
					if (loanobjectlist.get(i) instanceof LoanTransferReturnBean) {
						LoanTransferReturnBean ltrb = (LoanTransferReturnBean) loanobjectlist
								.get(i);
						System.out.println(ltrb);

						ltrb.setLoanJsonList(Common.UrlDecoder(
								ltrb.getLoanJsonList(), "utf-8"));

						String publickey = website.getPublicKey();

						String dataStr = ltrb.getLoanJsonList()
								+ ltrb.getPlatformMoneymoremore()
								+ ltrb.getAction() + ltrb.getRandomTimeStamp()
								+ ltrb.getRemark1() + ltrb.getRemark2()
								+ ltrb.getRemark3() + ltrb.getResultCode();
						if (antistate == 1) {
							dataStr = md5.getMD5Info(dataStr);
						}

						// 签名
						boolean verifySignature = rsa.verifySignature(
								ltrb.getSignInfo(), dataStr, publickey);
						this.verifySignature = Boolean
								.toString(verifySignature);
						System.out.println(this.verifySignature);

						if (verifySignature
								&& ltrb.getResultCode().equals("88")) {
							key = true;
						}
					}
				}
			}

			if (key) {// 如果托管还款执行成功
				Moneycount moneycount5 = this.moneycountService
						.findMoneycountByuserId(lssuing.getUservip()
								.getUserId().intValue());
				Moneyhistorydetail md = new Moneyhistorydetail();
				md.setOccurTime(new Timestamp(new Date().getTime()));
				md.setAffectMoney(repaynote.getMoneyThree());
				md.setAvailableBalance(Double.valueOf(moneycount5
						.getAvailableMoney().doubleValue()
						- repaynote.getMoneyThree().doubleValue()));
				md.setCollectMoney(moneycount5.getCollectTotalMoney());
				md.setFrozenMoney(moneycount5.getFrozenMoney());
				md.setUservip(lssuing.getUservip());
				md.setIntroduction("还款成功");
				this.moneyhistorydetailService.updateMoneyhistorydetail(md);

				moneycount5.setAvailableMoney(Double.valueOf(moneycount5
						.getAvailableMoney().doubleValue()
						- repaynote.getMoneyThree().doubleValue()));
				moneycount5.setTotalMoney(Double.valueOf(moneycount5
						.getTotalMoney().doubleValue()
						- repaynote.getMoneyThree().doubleValue()));
				moneycount5.setNetPayInterest(Double.valueOf(moneycount5
						.getNetPayInterest().doubleValue()
						+ repaynote.getMoneyTwo().doubleValue()));
				moneycount5.setAccuProfitAndLossMoney(Double
						.valueOf(moneycount5.getAccuProfitAndLossMoney()
								.doubleValue()
								- repaynote.getMoneyTwo().doubleValue()));
				moneycount5.setPayInterestTotalFee(Double.valueOf(moneycount5
						.getPayInterestTotalFee().doubleValue()
						- repaynote.getMoneyTwo().doubleValue()));
				this.moneycountService.updateMoneycount(moneycount5);

				// 处理还款，网站已还金额

				website.setLoan(website.getLoan() + repaynote.getMoneyFour());
				websiteService.updateWebsite(website);

				lssuing.setState(Integer.valueOf(4));
				lssuing.setReturnMoney(repaynote.getMoneyFour());
				lssuing.setEveryReturnTime(repaynote.getRepayDate());
				this.lssuingService.updateLssuing(lssuing);
				addInbox("资金变动", "您的资金发生变动，请注意查看", lssuing.getUservip()
						.getUserId());
				addInbox("还款", "恭喜你还款成功！", lssuing.getUservip().getUserId());
				if ((recordList4 != null) && (recordList4.size() > 0)) {
					for (int i = 0; i < recordList4.size(); i++) {
						Record record = (Record) recordList4.get(i);
						record.setRecordInterest(Double.valueOf(record
								.getRecordMoney().doubleValue()
								+ record.getRecordRate().doubleValue()));
						record.setRecordDate(new Timestamp(new Date().getTime()));
						record.setRecordState(Integer.valueOf(1));
						this.recordService.updateRecord(record);

						Moneycount moneycount4 = this.moneycountService
								.findMoneycountByuserId(record.getUservip()
										.getUserId().intValue());
						Moneyhistorydetail md1 = new Moneyhistorydetail();
						md1.setOccurTime(new Timestamp(new Date().getTime()));
						md1.setAffectMoney(record.getRecordInterest());
						md1.setAvailableBalance(Double.valueOf(moneycount4
								.getAvailableMoney().doubleValue()
								+ record.getRecordInterest().doubleValue()));
						md1.setCollectMoney(Double.valueOf(moneycount4
								.getCollectTotalMoney()
								- record.getRecordInterest().doubleValue()));
						md1.setFrozenMoney(moneycount4.getFrozenMoney());
						md1.setUservip(record.getUservip());
						md1.setIntroduction("投资" + lssuing.getLssuingNum()
								+ "回款成功");
						this.moneyhistorydetailService
								.updateMoneyhistorydetail(md1);

						moneycount4.setAvailableMoney(Double
								.valueOf(moneycount4.getAvailableMoney()
										.doubleValue()
										+ record.getRecordMoney()
												.doubleValue()));
						
						moneycount4.setCollectTotalMoney(moneycount4.getCollectTotalMoney()-record.getRecordMoney()-record.getRecordRate());
						
						moneycount4.setDueInMoney(Double.valueOf(moneycount4
								.getDueInMoney().doubleValue()
								- record.getRecordInterest().doubleValue()));
						moneycount4
								.setNetEarnInterest(Double.valueOf(moneycount4
										.getNetEarnInterest().doubleValue()
										+ record.getRecordRate().doubleValue()));
						moneycount4
								.setAccuProfitAndLossMoney(Double
										.valueOf(moneycount4
												.getAccuProfitAndLossMoney()
												.doubleValue()
												+ record.getRecordRate()
														.doubleValue()));
						moneycount4
								.setCollectInterestTotalFee(Double
										.valueOf(moneycount4
												.getCollectInterestTotalFee()
												.doubleValue()
												- record.getRecordRate()
														.doubleValue()));
						this.moneycountService.updateMoneycount(moneycount4);

						String sql = "select  * from record r where r.tenderId='"
								+ record.getTender().getTenderId()
								+ "' and r.recordState!=1 ";
						List recordss = this.recordService.findRecordBySql(sql);
						if ((recordss != null) && (recordss.size() == 0)) {
							Tender tender = record.getTender();
							tender.setState(2);
							this.tenderService.updateTender(tender);
						}

						addInbox("资金变动", "您的回款已到账户，请注意查看", record.getUservip()
								.getUserId());
					}
				}
			}

		}
	}

	/**
	 * 添加用户站内信信息
	 * 
	 * @param c
	 *            标题
	 * @param a
	 *            内容
	 * @param b
	 *            用户Id
	 * @throws Exception
	 */
	private void addInbox(String c, String a, Integer b) throws Exception {
		Website website = this.websiteService.findWebsiteBywebsiteId(1);
		Inbox inbox = new Inbox();
		inbox.setSendName(website.getWebName());
		inbox.setTitle(c);
		inbox.setContent(a);
		inbox.setStatus(Integer.valueOf(0));
		inbox.setReceiveTime(new Timestamp(new Date().getTime()));
		inbox.setUservip(this.uservipService.findUservipByUserid(b.intValue()));
		this.inboxService.createInbox(inbox);
	}

	
	/**
	 * 流标业务处理
	 * 
	 * @return
	 * @throws Exception
	 * @throws NumberFormatException
	 */
	public void borrowFail(String lssuingId)
			throws NumberFormatException, Exception {
		Lssuing ls = this.lssuingService.findLssuingById(Integer.valueOf(
				lssuingId).intValue());
		if (ls.getState() == 3 || ls.getState() == 6) {
			return;
		}
		
		List tenderList = this.tenderService.findTendersBylssuingId(ls
				.getLssuingId().intValue());
		if ((tenderList != null) && (tenderList.size() > 0)){
			for (int j = 0; j < tenderList.size(); j++) {
				Tender tender = (Tender) tenderList.get(j);
				Moneycount moneycount = this.moneycountService
						.findMoneycountByuserId(tender.getUservip().getUserId()
								.intValue());
				Moneyhistorydetail moneyhistorydetail = new Moneyhistorydetail();
				moneyhistorydetail.setAffectMoney(Double.valueOf(tender
						.getMoney().intValue()));
				moneyhistorydetail.setAvailableBalance(Double
						.valueOf(moneycount.getAvailableMoney().doubleValue()
								+ tender.getMoney().intValue()));
				moneyhistorydetail.setFrozenMoney(Double.valueOf(moneycount
						.getFrozenMoney().doubleValue()
						- tender.getMoney().intValue()));
				moneyhistorydetail.setCollectMoney(moneycount.getCollectTotalMoney());
				moneyhistorydetail.setOccurTime(new Timestamp(new Date()
						.getTime()));
				moneyhistorydetail.setIntroduction("所投的标标号："
						+ ls.getLssuingNum() + "流标，投资金额退回");
				moneyhistorydetail.setUservip(tender.getUservip());
				this.moneyhistorydetailService
						.createMoneyhistorydetail(moneyhistorydetail);

				moneycount.setAvailableMoney(Double.valueOf(moneycount
						.getAvailableMoney().doubleValue()
						+ tender.getMoney().intValue()));
				moneycount.setFrozenMoney(Double.valueOf(moneycount
						.getFrozenMoney().doubleValue()
						- tender.getMoney().intValue()));
				this.moneycountService.updateMoneycount(moneycount);

				addInbox("资金变动", "您的资金发生变动，请注意查看", tender.getUservip()
						.getUserId());
				addInbox("投资", "对不起，你的投资失败！", tender.getUservip().getUserId());
				this.tenderService.deleteTender(tender);
			}
		}
		ls.setState(6);
		this.lssuingService.updateLssuing(ls);
		addInbox("借款", "对不起，你的借款标号为" + ls.getLssuingNum() + "的标流标", ls
				.getUservip().getUserId());
	}
	
	/**
	 * 复审不通过处理
	 * 
	 * @return
	 * @throws Exception
	 * @throws NumberFormatException
	 */
	public void borrowReauditFail(String lssuingId)
			throws NumberFormatException, Exception {
		Lssuing ls = this.lssuingService.findLssuingById(Integer.valueOf(
				lssuingId).intValue());
		if (ls.getState() == 3 || ls.getState() == 6) {
			return;
		}
		if(ls.getState()!=1){
			return;
		}
		ls.setState(6);
		this.lssuingService.updateLssuing(ls);
		List tenderList = this.tenderService.findTendersBylssuingId(ls
				.getLssuingId().intValue());
		if ((tenderList != null) && (tenderList.size() > 0))
			for (int j = 0; j < tenderList.size(); j++) {
				Tender tender = (Tender) tenderList.get(j);
				Moneycount moneycount = this.moneycountService
						.findMoneycountByuserId(tender.getUservip().getUserId()
								.intValue());
				Moneyhistorydetail moneyhistorydetail = new Moneyhistorydetail();
				moneyhistorydetail.setAffectMoney(Double.valueOf(tender
						.getMoney().intValue()));
				moneyhistorydetail.setAvailableBalance(Double
						.valueOf(moneycount.getAvailableMoney().doubleValue()
								+ tender.getMoney().intValue()));
				moneyhistorydetail.setFrozenMoney(Double.valueOf(moneycount
						.getFrozenMoney().doubleValue()
						- tender.getMoney().intValue()));
				moneyhistorydetail.setCollectMoney(moneycount.getCollectTotalMoney());
				moneyhistorydetail.setOccurTime(new Timestamp(new Date()
						.getTime()));
				moneyhistorydetail.setIntroduction("所投的标复审核未通过");
				moneyhistorydetail.setUservip(tender.getUservip());
				this.moneyhistorydetailService
						.createMoneyhistorydetail(moneyhistorydetail);

				moneycount.setAvailableMoney(Double.valueOf(moneycount
						.getAvailableMoney().doubleValue()
						+ tender.getMoney().intValue()));
				moneycount.setFrozenMoney(Double.valueOf(moneycount
						.getFrozenMoney().doubleValue()
						- tender.getMoney().intValue()));
				this.moneycountService.updateMoneycount(moneycount);

				addInbox("资金变动", "您的资金发生变动，请注意查看", tender.getUservip()
						.getUserId());
				addInbox("投资", "对不起，你的投资失败！", tender.getUservip().getUserId());
				this.tenderService.deleteTender(tender);
			}

	}

	/**
	 * 回款续投处理(投资奖励发放)
	 * 
	 * @return
	 */
	public double returnInvest(String resultstr, Website website) {
		if(resultstr==null || resultstr.equals("")){
			return 0;
		}
		// 转账
		List<Object> loanobjectlist = Common.JSONDecodeList(resultstr,
				LoanTransferReturnBean.class);
		if (loanobjectlist != null && loanobjectlist.size() > 0) {
			MD5Trust md5 = new MD5Trust();
			RsaHelper rsa = RsaHelper.getInstance();
			for (int i = 0; i < loanobjectlist.size(); i++) {
				if (loanobjectlist.get(i) instanceof LoanTransferReturnBean) {
					LoanTransferReturnBean ltrb = (LoanTransferReturnBean) loanobjectlist
							.get(i);
					System.out.println(ltrb);

					ltrb.setLoanJsonList(Common.UrlDecoder(
							ltrb.getLoanJsonList(), "utf-8"));

					String publickey = website.getPublicKey();

					String dataStr = ltrb.getLoanJsonList()
							+ ltrb.getPlatformMoneymoremore()
							+ ltrb.getAction() + ltrb.getRandomTimeStamp()
							+ ltrb.getRemark1() + ltrb.getRemark2()
							+ ltrb.getRemark3() + ltrb.getResultCode();
					if (antistate == 1) {
						dataStr = md5.getMD5Info(dataStr);
					}

					// 签名
					boolean verifySignature = rsa.verifySignature(
							ltrb.getSignInfo(), dataStr, publickey);
					this.verifySignature = Boolean.toString(verifySignature);
					System.out.println(this.verifySignature);

					if (verifySignature && ltrb.getResultCode().equals("88")) {
						// 转账列表
						if (StringUtils.isNotBlank(ltrb.getLoanJsonList())) {
							List<Object> loaninfolist = Common.JSONDecodeList(
									ltrb.getLoanJsonList(),
									LoanReturnInfoBean.class);
							if (loaninfolist != null && loaninfolist.size() > 0) {
								for (int j = 0; j < loaninfolist.size(); j++) {
									if (loaninfolist.get(j) instanceof LoanReturnInfoBean) {
										LoanReturnInfoBean lrib = (LoanReturnInfoBean) loaninfolist
												.get(j);
										double money = Double.valueOf(lrib
												.getAmount());
										return money;
									}
								}
							}
						}
					}
				}
			}
		}

		return 0.0;
	}
	
	/**
	 * 理财还款返回处理
	 * @return
	 */
	public String lcrepayreturn(){

		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");
			Website website = this.websiteService.findWebsiteBywebsiteId(1);
			String publickey = website.getPublicKey();
			LoanJsonList = Common.UrlDecoder(LoanJsonList, "utf-8");
			MD5Trust md5 = new MD5Trust();
			RsaHelper rsa = RsaHelper.getInstance();
			String dataStr = LoanJsonList + PlatformMoneymoremore + Action + RandomTimeStamp + Remark1 + Remark2 + Remark3 + ResultCode;
//			
			if (antistate == 1) {
				dataStr = md5.getMD5Info(dataStr);
			}

			// 签名
			boolean verifySignature = rsa.verifySignature(SignInfo, dataStr,
					publickey);
			System.out.println("后台通知:" + verifySignature);
			System.out.println("返回码:" + ResultCode);

			List<Object> loaninfolist = Common.JSONDecodeList(LoanJsonList,
					LoanReturnInfoBean.class);
			// 如果成功，处理相关记录
			if (verifySignature && ResultCode.equals("88")) {
				int repynoteId = Integer.valueOf(this.Remark2);
				this.setMessage("还款成功",
						"borrow/toBackLssuing?queryFlag=2&lssuingType=9", "3");
				return "success";
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setMessage(this.Message,
				"borrow/toBackLssuing?queryFlag=2&lssuingType=9", "3");
		return "success";
	
	}
	
	/**
	 * 理财处理异步通知
	 */
	public void lcrepaynotify(){
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");
			Website website = this.websiteService.findWebsiteBywebsiteId(1);
			String publickey = website.getPublicKey();

			LoanJsonList = Common.UrlDecoder(LoanJsonList, "utf-8");
			MD5Trust md5 = new MD5Trust();
			RsaHelper rsa = RsaHelper.getInstance();
			String dataStr = LoanJsonList + PlatformMoneymoremore + Action + RandomTimeStamp + Remark1 + Remark2 + Remark3 + ResultCode;
//			
			if (antistate == 1) {
				dataStr = md5.getMD5Info(dataStr);
			}

			// 签名
			boolean verifySignature = rsa.verifySignature(SignInfo, dataStr,
					publickey);
			System.out.println("后台通知:" + verifySignature);
			System.out.println("返回码:" + ResultCode);

			List<Object> loaninfolist = Common.JSONDecodeList(LoanJsonList,
					LoanReturnInfoBean.class);
			// 如果成功，处理相关记录
			if (verifySignature && ResultCode.equals("88")) {
					int lssuingId = Integer.valueOf(this.Remark3);
					Lssuing lssuing = this.lssuingService.findLssuingById(lssuingId);
					
					String[] repynoteIds = this.Remark2.split("_");
					double loantotal = 0;
					if(repynoteIds!=null &&repynoteIds.length>0){
						for(int i=0;i<repynoteIds.length;i++){
							int repynoteId = Integer.valueOf(repynoteIds[i]);
							Repaynote repaynote = this.repaynoteService
									.findRepaynoteByRepaynoteId(repynoteId);
							if(repaynote.getRepayState()==1){
								continue;
							}
							repaynote.setRepayState(1);
							repaynote.setMoneyThree(repaynote.getMoneyFour());
							this.repaynoteService.updateRepaynote(repaynote);
							loantotal += repaynote.getMoneyFour();
						}
					}
					
					website.setLoan(website.getLoan()+ loantotal);
					websiteService.updateWebsite(website);
					
					String sql = "select * from(select  * from repaynote rp where rp.lssuingId="
							+ lssuingId
							+ " and rp.repayState=0) a order by a.repayDate  ";
					List lt = this.repaynoteService.findRepayBySql(sql);
					if ((lt != null) && (lt.size() == 0))
						lssuing.setState(Integer.valueOf(4));
					else {
						lssuing.setEveryReturnTime(((Repaynote) lt.get(0))
								.getRepayDate());
					}
					lssuing.setReturnMoney(Double
							.valueOf(new BigDecimal(lssuing
									.getReturnMoney().doubleValue()).add(
									new BigDecimal(loantotal))
									.doubleValue()));
					
					if (loaninfolist != null && loaninfolist.size() > 0) {
						for (int j = 0; j < loaninfolist.size(); j++) {
							if (loaninfolist.get(j) instanceof LoanReturnInfoBean) {
								LoanReturnInfoBean lrib = (LoanReturnInfoBean) loaninfolist
										.get(j);
								String orderno = lrib.getOrderNo();
								String[] ordernoarr = orderno.split("_");
								int recordId = Integer.valueOf(ordernoarr[1]);
								Record record = this.recordService.findRecordByRecordId(recordId);
								if(record.getRecordState()==1){
									continue;
								}
								double affectmoney = Double.valueOf(lrib.getAmount());
								
								record.setRecordState(1);
								record.setRecordInterest(record.getRecordMoney()+record.getRecordRate()+record.getOverInterest());
								Uservip uservip1 = this.uservipService.findUservipByUserid(record
										.getUservip().getUserId());
								// 查询用户的资金帐户表
								Moneycount moneycount2 = this.moneycountService
										.findMoneycountByuserId(uservip1.getUserId().intValue());
								// 资金历史明细表
								Moneyhistorydetail moneyhistorydetail1 = new Moneyhistorydetail();
								// 影响金额
								moneyhistorydetail1.setAffectMoney(Double.valueOf(record
										.getRecordMoney().doubleValue()
										+ record.getRecordRate().doubleValue()
										+ record.getOverInterest().doubleValue()));
								// 可用余额
								moneyhistorydetail1.setAvailableBalance(Double.valueOf(moneycount2
										.getAvailableMoney().doubleValue()
										+ record.getRecordMoney().doubleValue()
										+ record.getRecordRate().doubleValue()
										+ record.getOverInterest().doubleValue()));
								moneyhistorydetail1
										.setOccurTime(new Timestamp(new Date().getTime()));
								moneyhistorydetail1.setUservip(uservip1);
								// 冻结金额
								moneyhistorydetail1.setFrozenMoney(moneycount2.getFrozenMoney());
								// 待收金额
								moneyhistorydetail1
										.setCollectMoney(Double.valueOf(moneycount2.getCollectTotalMoney()
												-affectmoney));
								moneyhistorydetail1.setIntroduction("标号：" + lssuing.getLssuingNum()
										+ "回款成功");
								if (record.getOverInterest() > 0) {
									moneyhistorydetail1.setIntroduction("标号："
											+ lssuing.getLssuingNum() + "回款成功" + ",逾期："
											+ record.getOverdays() + "天，逾期利息："
											+ record.getOverInterest());
								}
								this.moneyhistorydetailService
										.createMoneyhistorydetail(moneyhistorydetail1);

								moneycount2.setAvailableMoney(Double.valueOf(moneycount2
										.getAvailableMoney().doubleValue()
										+ record.getRecordMoney().doubleValue()
										+ record.getRecordRate().doubleValue()
										+ record.getOverInterest().doubleValue()));
								moneycount2.setDueInMoney(Double.valueOf(moneycount2
										.getDueInMoney().doubleValue()
										- record.getRecordMoney()));
								
								moneycount2.setCollectTotalMoney(moneycount2.getCollectTotalMoney()-record.getRecordMoney()-record.getRecordRate());
								
								moneycount2.setNetEarnInterest(Double.valueOf(moneycount2
										.getNetEarnInterest().doubleValue()
										+ record.getRecordRate().doubleValue()
										+ record.getOverInterest().doubleValue()));
								moneycount2.setAccuProfitAndLossMoney(Double.valueOf(moneycount2
										.getAccuProfitAndLossMoney().doubleValue()
										+ record.getRecordRate().doubleValue()
										+ record.getOverInterest().doubleValue()));
								moneycount2.setCollectInterestTotalFee(Double.valueOf(moneycount2
										.getCollectInterestTotalFee().doubleValue()
										- record.getRecordRate().doubleValue()));
								this.moneycountService.updateMoneycount(moneycount2);
								addInbox("资金变动", "您的回款已到账户，请注意查看", uservip1.getUserId());
								// 查询用户的资金帐户表
								Moneycount moneycount3 = this.moneycountService
										.findMoneycountByuserId(uservip1.getUserId().intValue());

								if (StringUtils.isNotBlank(lrib.getSecondaryJsonList())) {
									List<Object> loansecondarylist = Common.JSONDecodeList(
											lrib.getSecondaryJsonList(),
											LoanInfoSecondaryBean.class);
									if (loansecondarylist != null && loansecondarylist.size() > 0) {
										for (int k = 0; k < loansecondarylist.size(); k++) {
											if (loansecondarylist.get(k) instanceof LoanInfoSecondaryBean) {
												LoanInfoSecondaryBean lisb = (LoanInfoSecondaryBean) loansecondarylist
														.get(k);

												Moneyhistorydetail moneyhistorydetail2 = new Moneyhistorydetail();

												double money = Double.valueOf(lisb.getAmount());
												// 影响金额
												moneyhistorydetail2.setAffectMoney(money);
												// 可用余额
												moneyhistorydetail2.setAvailableBalance(Double
														.valueOf(moneycount3.getAvailableMoney()
																.doubleValue() - (money)));
												moneyhistorydetail2.setOccurTime(new Timestamp(
														new Date().getTime()));
												moneyhistorydetail2.setUservip(record.getUservip());
												// 冻结金额
												moneyhistorydetail2.setFrozenMoney(moneycount3
														.getFrozenMoney());
												// 待收金额
												moneyhistorydetail2.setCollectMoney(Double
														.valueOf(moneycount3.getCollectTotalMoney()));
												moneyhistorydetail2.setIntroduction("标号："
														+ lssuing.getLssuingNum() + "利息管理费已扣除");
												this.moneyhistorydetailService
														.createMoneyhistorydetail(moneyhistorydetail2);

												moneycount3.setAvailableMoney(Double
														.valueOf(moneycount3.getAvailableMoney()
																.doubleValue() - (money)));
												this.moneycountService
														.updateMoneycount(moneycount3);
												addInbox("利息管理费", "您的利息管理费已扣除，请注意查看", record
														.getUservip().getUserId());

											}
										}
									}
								}
								this.recordService.updateRecord(record);
								sql = "select  * from record r where r.tenderId='"
										+ record.getTender().getTenderId()
										+ "' and r.recordState!=1";
								List list = this.recordService.findRecordBySql(sql);
								if ((lt != null) && (lt.size() == 0)) {
									Tender tender = record.getTender();
									tender.setState(2);
									this.tenderService.updateTender(tender);
								}
							}
						}
					}
					this.lssuingService.updateLssuing(lssuing);
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
	 * 借款标还款返回处理
	 * 
	 * @return
	 */
	public String borrowrepayreturn() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");
			Website website = this.websiteService.findWebsiteBywebsiteId(1);
			String publickey = website.getPublicKey();
			LoanJsonList = Common.UrlDecoder(LoanJsonList, "utf-8");
			MD5Trust md5 = new MD5Trust();
			RsaHelper rsa = RsaHelper.getInstance();
			String dataStr = LoanJsonList + PlatformMoneymoremore + Action + RandomTimeStamp + Remark1 + Remark2 + Remark3 + ResultCode;
//			
			if (antistate == 1) {
				dataStr = md5.getMD5Info(dataStr);
			}

			// 签名
			boolean verifySignature = rsa.verifySignature(SignInfo, dataStr,
					publickey);
			System.out.println("后台通知:" + verifySignature);
			System.out.println("返回码:" + ResultCode);

			List<Object> loaninfolist = Common.JSONDecodeList(LoanJsonList,
					LoanReturnInfoBean.class);
			// 如果成功，处理相关记录
			if (verifySignature && ResultCode.equals("88")) {
				int repynoteId = Integer.valueOf(this.Remark2);
				this.setMessage("还款成功",
						"/borrow/toLssuingList?queryFlag=3&gotoFlag=1", "3");
				return "success";
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setMessage(this.Message,
				"/borrow/toLssuingList?queryFlag=3&gotoFlag=1", "3");
		return "success";
	}
	
	
	//对账
	public void systemChecke() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		int userid = 0;
		if(request.getParameter("userId")!=null && !request.getParameter("userId").equals("")){
			userid = Integer.valueOf(request.getParameter("userId"));
		}
		boolean autokey = false;
		if(request.getParameter("autokey")!=null && !request.getParameter("autokey").equals("")){
			String key = request.getParameter("autokey");
			if(key.equals("auto")){
				autokey = true;
			}
		}
		
		String limit = "";
		if(request.getParameter("start")!=null && !request.getParameter("start").equals("")){
			limit += " limit "+request.getParameter("start").trim()+",";
			if(request.getParameter("num")!=null && !request.getParameter("num").equals("")){
				limit += request.getParameter("num").trim();
			}else{
				limit += "10";
			}
		}else{
			limit = " limit 1";
		}
		
		String sql = "SELECT * FROM uservip u WHERE 1=1 ";
		if(userid !=0){
			sql += " and userId="+userid;
		}
		sql += " and trustStatus=1"+limit;
		Website website = this.websiteService.findWebsiteBywebsiteId(1);
		List<Uservip> uservips = this.uservipService.findUservipsBysql(sql);
		LoanUtils loanUtil = new LoanUtils();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		if(uservips!=null && uservips.size()>0){
			for(int i=0;i<uservips.size();i++){
				Uservip uservip = uservips.get(i);
				String[] str = loanUtil.querybalance(website, uservip);
				String[] moneys = str[1].split("\\|");
				Moneycount moneycount = this.moneycountService.findMoneycountByuserId(uservip.getUserId());
				response.getWriter().write("<br/>*****用户开始******<br/>");
				if(moneycount!=null){
					response.getWriter().write(uservip.getUserName()+"_"+uservip.getRealName()+"平台金额：");
					response.getWriter().write("可用："+moneycount.getAvailableMoney());
					response.getWriter().write("冻结："+moneycount.getFrozenMoney());
					response.getWriter().write("待收："+moneycount.getCollectTotalMoney());
					response.getWriter().write("总额："+moneycount.getTotalMoney());
				}
				response.getWriter().write("<br/>");
				response.getWriter().write("托管金额：");
				response.getWriter().write("可用："+moneys[0]);
				response.getWriter().write("冻结："+moneys[2]);
				response.getWriter().write("<br/>*****用户结束******<br/>");
				
				if(autokey){
					//可用余额和冻结不一致的时候
					if(moneycount!=null){
						if(moneycount.getAvailableMoney().doubleValue()!=Double.valueOf(moneys[0]).doubleValue() ||
								moneycount.getFrozenMoney().doubleValue()!=Double.valueOf(moneys[2]).doubleValue()	){
							moneycount.setAvailableMoney(Double.valueOf(moneys[0]).doubleValue());
							moneycount.setFrozenMoney(Double.valueOf(moneys[2]).doubleValue());
							moneycount.setTotalMoney(moneycount.getAvailableMoney()+moneycount.getFrozenMoney()+moneycount.getCollectTotalMoney());
							this.moneycountService.updateMoneycount(moneycount);
						}
					}
					
				}
			}
		}

		response.getWriter().flush();
		response.getWriter().close();
		
	}
	
	
	

	/**
	 * 处理还款后的业务逻辑
	 * 
	 * @param lrib
	 * @param repaynote
	 * @throws Exception
	 */
	public void borrowRepay(LoanReturnInfoBean lrib, Repaynote repaynote,
			int repayuserId, Lssuing lssuing) throws Exception {
		Website website = this.websiteService.findWebsiteBywebsiteId(1);
		double affectmoney = Double.valueOf(lrib.getAmount());
		DecimalFormat df = new DecimalFormat("##.00");
		if (lrib.getTransferName().equals("web_repay")) {
			Moneycount moneycount = this.moneycountService
					.findMoneycountByuserId(repayuserId);
			Moneyhistorydetail moneyhistorydetail = new Moneyhistorydetail();
			moneyhistorydetail.setAffectMoney(affectmoney);
			moneyhistorydetail
					.setOccurTime(new Timestamp(new Date().getTime()));
			moneyhistorydetail.setAvailableBalance(Double.valueOf(moneycount
					.getAvailableMoney().doubleValue() - affectmoney));
			moneyhistorydetail.setUservip(repaynote.getUservip());
			moneyhistorydetail.setCollectMoney(moneycount.getCollectTotalMoney());
			moneyhistorydetail.setFrozenMoney(moneycount.getFrozenMoney());
			moneyhistorydetail.setIntroduction("还款成功,已垫付");
			if (repaynote.getOverInterest() > 0) {
				moneyhistorydetail.setIntroduction("还款成功，逾期："
						+ repaynote.getOverdays() + "天，逾期利息:"
						+ repaynote.getOverInterest());
			}
			this.moneyhistorydetailService
					.createMoneyhistorydetail(moneyhistorydetail);
			moneycount.setAvailableMoney(Double.valueOf(moneycount
					.getAvailableMoney().doubleValue()
					-affectmoney));
			moneycount.setTotalMoney(Double.valueOf(moneycount.getTotalMoney()
					.doubleValue()
					-affectmoney));
			moneycount.setNetPayInterest(Double.valueOf(moneycount
					.getNetPayInterest().doubleValue()
					+ repaynote.getMoneyTwo().doubleValue()));
			moneycount.setAccuProfitAndLossMoney(Double.valueOf(moneycount
					.getAccuProfitAndLossMoney().doubleValue()
					- repaynote.getMoneyTwo().doubleValue()));
			moneycount.setPayInterestTotalFee(Double.valueOf(moneycount
					.getPayInterestTotalFee().doubleValue()
					- repaynote.getMoneyTwo().doubleValue()));
			this.moneycountService.updateMoneycount(moneycount);

			PlatcountService platcountService = new PlatcountServiceImpl();
			Platcount platcount = new Platcount();
			platcount.setExpond(Double.valueOf(df.format(website.getExpond())));
			platcount.setIncome(Double.valueOf(df.format(website.getIncome()
					+ repaynote.getMoneyFour() + repaynote.getOverInterest())));
			platcount.setMoney(repaynote.getMoneyFour()
					+ repaynote.getOverInterest());
			platcount.setOpertime(new Timestamp(new Date().getTime()));
			platcount.setProfit(Double.valueOf(df.format(website.getProfit()
					+ repaynote.getMoneyFour() + repaynote.getOverInterest())));
			double webabccount = repaynote.getMoneyFour();
			platcount.setRemark("标号:" + lssuing.getLssuingNum() + " 标题:"
					+ lssuing.getTitle() + "逾期，网站垫付:" + webabccount + "元,逾期："
					+ repaynote.getOverdays() + "天" + "逾期计息："
					+ repaynote.getOverInterest());
			platcountService.createPlatcount(platcount);

			website.setIncome(Double.valueOf(df.format(website.getIncome()
					+ repaynote.getMoneyFour() - repaynote.getOverInterest())));
			website.setProfit(Double.valueOf(df.format(website.getProfit()
					+ repaynote.getMoneyFour() + repaynote.getOverInterest())));
			website.setLoan(website.getLoan() + repaynote.getMoneyFour());
			websiteService.updateWebsite(website);
		} else if (lrib.getTransferName().equals("repay")) {

			String orderno = lrib.getOrderNo();
			String[] ordernoarr = orderno.split("_");
			int recordId = Integer.valueOf(ordernoarr[2]);
			Record record = this.recordService.findRecordByRecordId(recordId);
			if(record.getRecordState()==1){
				
			}else{
				record.setRecordState(1);
				record.setRecordInterest(record.getRecordMoney()+record.getRecordRate()+record.getOverInterest());
				Moneycount moneycount = this.moneycountService
						.findMoneycountByuserId(repayuserId);
				Moneyhistorydetail moneyhistorydetail = new Moneyhistorydetail();
				moneyhistorydetail.setAffectMoney(affectmoney);
				moneyhistorydetail
						.setOccurTime(new Timestamp(new Date().getTime()));
				moneyhistorydetail.setAvailableBalance(Double.valueOf(moneycount
						.getAvailableMoney().doubleValue() - affectmoney));
				moneyhistorydetail.setUservip(repaynote.getUservip());
				moneyhistorydetail.setCollectMoney(moneycount.getCollectTotalMoney());
				moneyhistorydetail.setFrozenMoney(moneycount.getFrozenMoney());
				moneyhistorydetail.setIntroduction("还款成功");
				if (record.getOverInterest() > 0) {
					moneyhistorydetail.setIntroduction("还款成功，逾期："
							+ repaynote.getOverdays() + "天，逾期利息:"
							+ record.getOverInterest());
				}
				this.moneyhistorydetailService
						.createMoneyhistorydetail(moneyhistorydetail);
				moneycount.setAvailableMoney(Double.valueOf(moneycount
						.getAvailableMoney().doubleValue()
						-affectmoney));
				moneycount.setTotalMoney(Double.valueOf(moneycount.getTotalMoney()
						.doubleValue()
						- affectmoney));
				moneycount.setNetPayInterest(Double.valueOf(moneycount
						.getNetPayInterest().doubleValue()
						+ repaynote.getMoneyTwo().doubleValue()));
				moneycount.setAccuProfitAndLossMoney(Double.valueOf(moneycount
						.getAccuProfitAndLossMoney().doubleValue()
						- repaynote.getMoneyTwo().doubleValue()));
				moneycount.setPayInterestTotalFee(Double.valueOf(moneycount
						.getPayInterestTotalFee().doubleValue()
						- repaynote.getMoneyTwo().doubleValue()));
				this.moneycountService.updateMoneycount(moneycount);

				Uservip uservip1 = this.uservipService.findUservipByUserid(record
						.getUservip().getUserId());
				// 查询用户的资金帐户表
				Moneycount moneycount2 = this.moneycountService
						.findMoneycountByuserId(uservip1.getUserId().intValue());
				// 资金历史明细表
				Moneyhistorydetail moneyhistorydetail1 = new Moneyhistorydetail();
				// 影响金额
				moneyhistorydetail1.setAffectMoney(Double.valueOf(record
						.getRecordMoney().doubleValue()
						+ record.getRecordRate().doubleValue()
						+ record.getOverInterest().doubleValue()));
				// 可用余额
				moneyhistorydetail1.setAvailableBalance(Double.valueOf(moneycount2
						.getAvailableMoney().doubleValue()
						+ record.getRecordMoney().doubleValue()
						+ record.getRecordRate().doubleValue()
						+ record.getOverInterest().doubleValue()));
				moneyhistorydetail1
						.setOccurTime(new Timestamp(new Date().getTime()));
				moneyhistorydetail1.setUservip(uservip1);
				// 冻结金额
				moneyhistorydetail1.setFrozenMoney(moneycount2.getFrozenMoney());
				// 待收金额
				moneyhistorydetail1
						.setCollectMoney(Double.valueOf(moneycount2.getCollectTotalMoney()
								-affectmoney));
				moneyhistorydetail1.setIntroduction("标号：" + lssuing.getLssuingNum()
						+ "回款成功");
				if (record.getOverInterest() > 0) {
					moneyhistorydetail1.setIntroduction("标号："
							+ lssuing.getLssuingNum() + "回款成功" + ",逾期："
							+ record.getOverdays() + "天，逾期利息："
							+ record.getOverInterest());
				}
				this.moneyhistorydetailService
						.createMoneyhistorydetail(moneyhistorydetail1);

				moneycount2.setAvailableMoney(Double.valueOf(moneycount2
						.getAvailableMoney().doubleValue()
						+ record.getRecordMoney().doubleValue()
						+ record.getRecordRate().doubleValue()
						+ record.getOverInterest().doubleValue()));
				moneycount2.setDueInMoney(Double.valueOf(moneycount2
						.getDueInMoney().doubleValue()
						- record.getRecordMoney()));
				moneycount2.setNetEarnInterest(Double.valueOf(moneycount2
						.getNetEarnInterest().doubleValue()
						+ record.getRecordRate().doubleValue()
						+ record.getOverInterest().doubleValue()));
				moneycount2.setAccuProfitAndLossMoney(Double.valueOf(moneycount2
						.getAccuProfitAndLossMoney().doubleValue()
						+ record.getRecordRate().doubleValue()
						+ record.getOverInterest().doubleValue()));
				moneycount2.setCollectInterestTotalFee(Double.valueOf(moneycount2
						.getCollectInterestTotalFee().doubleValue()
						- record.getRecordRate().doubleValue()));
				
				moneycount2.setCollectTotalMoney(moneycount2.getCollectTotalMoney()- record.getRecordMoney()- record.getRecordRate());
				this.moneycountService.updateMoneycount(moneycount2);
				addInbox("资金变动", "您的回款已到账户，请注意查看", uservip1.getUserId());

				// 查询用户的资金帐户表
				Moneycount moneycount3 = this.moneycountService
						.findMoneycountByuserId(uservip1.getUserId().intValue());

				if (StringUtils.isNotBlank(lrib.getSecondaryJsonList())) {
					List<Object> loansecondarylist = Common.JSONDecodeList(
							lrib.getSecondaryJsonList(),
							LoanInfoSecondaryBean.class);
					if (loansecondarylist != null && loansecondarylist.size() > 0) {
						for (int k = 0; k < loansecondarylist.size(); k++) {
							if (loansecondarylist.get(k) instanceof LoanInfoSecondaryBean) {
								LoanInfoSecondaryBean lisb = (LoanInfoSecondaryBean) loansecondarylist
										.get(k);

								Moneyhistorydetail moneyhistorydetail2 = new Moneyhistorydetail();

								double money = Double.valueOf(lisb.getAmount());
								// 影响金额
								moneyhistorydetail2.setAffectMoney(money);
								// 可用余额
								moneyhistorydetail2.setAvailableBalance(Double
										.valueOf(moneycount3.getAvailableMoney()
												.doubleValue() - (money)));
								moneyhistorydetail2.setOccurTime(new Timestamp(
										new Date().getTime()));
								moneyhistorydetail2.setUservip(record.getUservip());
								// 冻结金额
								moneyhistorydetail2.setFrozenMoney(moneycount3
										.getFrozenMoney());
								// 待收金额
								moneyhistorydetail2.setCollectMoney(Double
										.valueOf(moneycount3.getCollectTotalMoney()));
								moneyhistorydetail2.setIntroduction("标号："
										+ lssuing.getLssuingNum() + "利息管理费已扣除");
								this.moneyhistorydetailService
										.createMoneyhistorydetail(moneyhistorydetail2);

								moneycount3.setAvailableMoney(Double
										.valueOf(moneycount3.getAvailableMoney()
												.doubleValue() - (money)));
								this.moneycountService
										.updateMoneycount(moneycount3);
								addInbox("利息管理费", "您的利息管理费已扣除，请注意查看", record
										.getUservip().getUserId());

							}
						}
					}
				}
				this.recordService.updateRecord(record);
				String sql = "select  * from record r where r.tenderId='"
						+ record.getTender().getTenderId()
						+ "' and r.recordState!=1";
				List lt = this.recordService.findRecordBySql(sql);
				if ((lt != null) && (lt.size() == 0)) {
					Tender tender = record.getTender();
					tender.setState(2);
					this.tenderService.updateTender(tender);
				}
			}
			
		} else if (lrib.getTransferName().equals("web_advance_repay")) {
			String orderno = lrib.getOrderNo();
			String[] ordernoarr = orderno.split("_");
			int recordId = Integer.valueOf(ordernoarr[2]);
			Record record = this.recordService.findRecordByRecordId(recordId);
			record.setRecordState(1);
			record.setRecordInterest(record.getRecordMoney()+record.getRecordRate()+record.getOverInterest());
			
			this.recordService.updateRecord(record);
			Moneycount moneycount2 = this.moneycountService
					.findMoneycountByuserId(record.getUservip().getUserId()
							.intValue());
			Moneyhistorydetail moneyhistorydetail1 = new Moneyhistorydetail();
			moneyhistorydetail1.setAffectMoney(Double.valueOf(record
					.getRecordMoney().doubleValue()
					+ record.getRecordRate().doubleValue()
					+ record.getOverInterest().doubleValue()));
			moneyhistorydetail1.setAvailableBalance(Double.valueOf(moneycount2
					.getAvailableMoney().doubleValue()
					+ record.getRecordMoney().doubleValue()
					+ record.getRecordRate().doubleValue()
					+ record.getOverInterest().doubleValue()));
			moneyhistorydetail1
					.setOccurTime(new Timestamp(new Date().getTime()));
			moneyhistorydetail1.setUservip(record.getUservip());
			moneyhistorydetail1.setFrozenMoney(moneycount2.getFrozenMoney());
			moneyhistorydetail1.setCollectMoney(Double.valueOf(moneycount2
					.getCollectTotalMoney()
					- record.getRecordInterest().doubleValue()
					+ record.getOverInterest()));
			moneyhistorydetail1.setIntroduction("标号：" + lssuing.getLssuingNum()
					+ "平台垫付成功");
			this.moneyhistorydetailService
					.createMoneyhistorydetail(moneyhistorydetail1);

			moneycount2.setAvailableMoney(Double.valueOf(moneycount2
					.getAvailableMoney().doubleValue()
					+ record.getRecordMoney().doubleValue()
					+ record.getRecordRate().doubleValue()
					+ record.getOverInterest().doubleValue()));
			moneycount2.setDueInMoney(Double.valueOf(moneycount2
					.getDueInMoney().doubleValue()
					- record.getRecordMoney()));
			moneycount2.setNetEarnInterest(Double.valueOf(moneycount2
					.getNetEarnInterest().doubleValue()
					+ record.getRecordRate().doubleValue()
					+ record.getOverInterest().doubleValue()));
			moneycount2.setAccuProfitAndLossMoney(Double.valueOf(moneycount2
					.getAccuProfitAndLossMoney().doubleValue()
					+ record.getRecordRate().doubleValue()
					+ record.getOverInterest().doubleValue()));
			moneycount2.setCollectInterestTotalFee(Double.valueOf(moneycount2
					.getCollectInterestTotalFee().doubleValue()
					- record.getRecordRate().doubleValue()));
			moneycount2.setCollectTotalMoney(moneycount2.getCollectTotalMoney()- record.getRecordMoney()- record.getRecordRate());
			
			this.moneycountService.updateMoneycount(moneycount2);

			String sql = "select  * from record r where r.tenderId='"
					+ record.getTender().getTenderId()
					+ "' and r.recordState!=1";
			System.out.println(sql);
			List lt = this.recordService.findRecordBySql(sql);
			if ((lt != null) && (lt.size() == 0)) {
				Tender tender = record.getTender();
				tender.setState(2);
				this.tenderService.updateTender(tender);
			}
			addInbox("资金变动", "您的回款已到账户，请注意查看", record.getUservip().getUserId());
		}

	}

	/**
	 * 借款标还款异步通知接口处理
	 */
	public void borrowrepaynotify() {

		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");
			Website website = this.websiteService.findWebsiteBywebsiteId(1);
			String publickey = website.getPublicKey();

			LoanJsonList = Common.UrlDecoder(LoanJsonList, "utf-8");
			MD5Trust md5 = new MD5Trust();
			RsaHelper rsa = RsaHelper.getInstance();
			String dataStr = LoanJsonList + PlatformMoneymoremore + Action + RandomTimeStamp + Remark1 + Remark2 + Remark3 + ResultCode;
//			
			if (antistate == 1) {
				dataStr = md5.getMD5Info(dataStr);
			}

			// 签名
			boolean verifySignature = rsa.verifySignature(SignInfo, dataStr,
					publickey);
			System.out.println("后台通知:" + verifySignature);
			System.out.println("返回码:" + ResultCode);

			List<Object> loaninfolist = Common.JSONDecodeList(LoanJsonList,
					LoanReturnInfoBean.class);
			// 如果成功，处理相关记录
			if (verifySignature && ResultCode.equals("88")) {
				int repynoteId = Integer.valueOf(this.Remark2);
				Repaynote repaynote = this.repaynoteService
						.findRepaynoteByRepaynoteId(repynoteId);
				if (repaynote.getRepayState() == 1) {

				} else {
					int lssuingId = Integer.valueOf(this.Remark3);
					Lssuing lssuing = this.lssuingService
							.findLssuingById(lssuingId);

					int repaystate = repaynote.getRepayState();
					website.setLoan(website.getLoan()
							+ repaynote.getMoneyFour());
					websiteService.updateWebsite(website);
					

					repaynote.setRepayState(Integer.valueOf(1));
					repaynote.setMoneyThree(repaynote.getMoneyFour());
					this.repaynoteService.updateRepaynote(repaynote);

					String sql = "select * from(select  * from repaynote rp where rp.lssuingId="
							+ lssuingId
							+ " and rp.repayState=0) a order by a.repayDate  ";
					List lt = this.repaynoteService.findRepayBySql(sql);
					if ((lt != null) && (lt.size() == 0))
						lssuing.setState(Integer.valueOf(4));
					else {
						lssuing.setEveryReturnTime(((Repaynote) lt.get(0))
								.getRepayDate());
					}
					if (repaystate == 2) {

					} else {
						lssuing.setReturnMoney(Double
								.valueOf(new BigDecimal(lssuing
										.getReturnMoney().doubleValue()).add(
										new BigDecimal(repaynote.getMoneyOne()
												.doubleValue()
												+ repaynote.getMoneyTwo()
														.doubleValue()))
										.doubleValue()));
					}
					
					if (loaninfolist != null && loaninfolist.size() > 0) {
						for (int j = 0; j < loaninfolist.size(); j++) {
							if (loaninfolist.get(j) instanceof LoanReturnInfoBean) {
								LoanReturnInfoBean lrib = (LoanReturnInfoBean) loaninfolist
										.get(j);
								String orderno = lrib.getOrderNo();
								String loanno = lrib.getLoanNo();
								String[] ordernoarr = orderno.split("_");
								int repayuserid = Integer
										.valueOf(ordernoarr[0]);
								this.borrowRepay(lrib, repaynote, repayuserid,
										lssuing);
								;
							}
						}
					}

					

					

					this.lssuingService.updateLssuing(lssuing);
					addInbox("资金变动", "您的资金发生变动，请注意查看", repaynote.getUservip()
							.getUserId());
					addInbox("还款", "恭喜你还款成功！", repaynote.getUservip()
							.getUserId());
				}

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
	 * 借款平台垫付返回处理
	 * 
	 * @return
	 */
	public String borrowwebrepayreturn() {
		try {
			Website website = this.websiteService.findWebsiteBywebsiteId(1);
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");

			String publickey = website.getPublicKey();

			LoanJsonList = Common.UrlDecoder(LoanJsonList, "utf-8");
			MD5Trust md5 = new MD5Trust();
			RsaHelper rsa = RsaHelper.getInstance();
			String dataStr = LoanJsonList + PlatformMoneymoremore + Action + RandomTimeStamp + Remark1 + Remark2 + Remark3 + ResultCode;
//			
			if (antistate == 1) {
				dataStr = md5.getMD5Info(dataStr);
			}

			// 签名
			boolean verifySignature = rsa.verifySignature(SignInfo, dataStr,
					publickey);
			System.out.println("后台通知:" + verifySignature);
			System.out.println("返回码:" + ResultCode);
			System.out.println("返回次数:" + ReturnTimes);

			if (verifySignature && this.ResultCode.equals("88")) {
				this.setMessage("垫付成功", "/borrow/overdueLassuing", "3");
				return "success";

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setMessage(this.Message, "/borrow/overdueLassuing", "3");
		return "success";
	}

	/**
	 * 借款凭条垫付异步通知处理
	 */
	public void borrowwebrepaynotify() {
		try {
			Website website = this.websiteService.findWebsiteBywebsiteId(1);
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");

			String publickey = website.getPublicKey();

			LoanJsonList = Common.UrlDecoder(LoanJsonList, "utf-8");
			MD5Trust md5 = new MD5Trust();
			RsaHelper rsa = RsaHelper.getInstance();
			String dataStr = LoanJsonList + PlatformMoneymoremore + Action + RandomTimeStamp + Remark1 + Remark2 + Remark3 + ResultCode;
//			
			if (antistate == 1) {
				dataStr = md5.getMD5Info(dataStr);
			}

			// 签名
			boolean verifySignature = rsa.verifySignature(SignInfo, dataStr,
					publickey);
			
			List<Object> loaninfolist = Common.JSONDecodeList(LoanJsonList,
					LoanReturnInfoBean.class);
			// 如果成功，处理相关记录
			if (verifySignature && ResultCode.equals("88")) {
				int repynoteId = Integer.valueOf(this.Remark2);
				Repaynote repaynote = this.repaynoteService
						.findRepaynoteByRepaynoteId(repynoteId);
				if (repaynote.getRepayState() == 1) {

				} else {
					int lssuingId = Integer.valueOf(this.Remark3);
					Lssuing lssuing = this.lssuingService
							.findLssuingById(lssuingId);

					int repaystate = repaynote.getRepayState();
					website.setLoan(website.getLoan()
							+ repaynote.getMoneyFour());
					websiteService.updateWebsite(website);
					
					repaynote.setRepayState(2);
					repaynote.setMoneyThree(repaynote.getMoneyFour());
					this.repaynoteService.updateRepaynote(repaynote);
					
					if (loaninfolist != null && loaninfolist.size() > 0) {
						for (int j = 0; j < loaninfolist.size(); j++) {
							if (loaninfolist.get(j) instanceof LoanReturnInfoBean) {
								LoanReturnInfoBean lrib = (LoanReturnInfoBean) loaninfolist
										.get(j);
								String orderno = lrib.getOrderNo();
								String loanno = lrib.getLoanNo();
								String[] ordernoarr = orderno.split("_");
								int repayuserid = Integer
										.valueOf(ordernoarr[0]);
								this.borrowRepay(lrib, repaynote, repayuserid,
										lssuing);
								;
							}
						}
					}

					addInbox("资金变动", "您的资金发生变动，请注意查看", repaynote.getUservip()
							.getUserId());
					addInbox("还款", "恭喜你还款成功！", repaynote.getUservip()
							.getUserId());
				}

			}
			
			System.out.println("后台通知:" + verifySignature);
			System.out.println("返回码:" + ResultCode);
			System.out.println("返回次数:" + ReturnTimes);

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

	public void loantraansfernotify() {

		try {
			Website website = this.websiteService.findWebsiteBywebsiteId(1);
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");

			String publickey = website.getPublicKey();

			LoanJsonList = Common.UrlDecoder(LoanJsonList, "utf-8");
			MD5Trust md5 = new MD5Trust();
			RsaHelper rsa = RsaHelper.getInstance();
			String dataStr = LoanJsonList + PlatformMoneymoremore + Action + RandomTimeStamp + Remark1 + Remark2 + Remark3 + ResultCode;
//			
			if (antistate == 1) {
				dataStr = md5.getMD5Info(dataStr);
			}

			// 签名
			boolean verifySignature = rsa.verifySignature(SignInfo, dataStr,
					publickey);
			if (verifySignature && this.ResultCode.equals("88")) {
				List<Object> loaninfolist = Common.JSONDecodeList(LoanJsonList,
						LoanReturnInfoBean.class);
				if (loaninfolist != null && loaninfolist.size() > 0) {
					for (int j = 0; j < loaninfolist.size(); j++) {
						if (loaninfolist.get(j) instanceof LoanReturnInfoBean) {
							LoanReturnInfoBean lrib = (LoanReturnInfoBean) loaninfolist
									.get(j);
							String orderno = lrib.getOrderNo();
							String loanno = lrib.getLoanNo();
							double money  = Double.valueOf(lrib.getAmount());

							String sql = "";
							List list = moneyhistorydetailService.findMoneyhistorydetailBySql("select * from moneyhistorydetail where loanno='"+loanno+"'");
							if(list!=null && list.size()>0){
								continue;
							}
							String[] ordernoarr = orderno.split("_");

							String userid = ordernoarr[0];
							
							Uservip uservip = this.uservipService.findUservipByUserid(Integer
									.valueOf(userid).intValue());
							
							Moneycount moneycount = this.moneycountService
									.findMoneycountByuserId(Integer.valueOf(userid).intValue());

							moneycount.setAvailableMoney(Double.valueOf(moneycount
									.getAvailableMoney().doubleValue()
									+ Double.valueOf(money).doubleValue()));

							moneycount.setTotalMoney(Double.valueOf(moneycount.getTotalMoney()
									.doubleValue() + Double.valueOf(money).doubleValue()));
							
							if(lrib.getTransferName().equals("returnInvest")){//回款续投奖励
								moneycount.setAccuContinueBidReward(moneycount.getAccuContinueBidReward()+money);
								moneycount.setAccuProfitAndLossMoney(moneycount.getAccuProfitAndLossMoney()+money);
							}else if(lrib.getTransferName().equals("InvestmentIncentives")){
								moneycount.setAccuPromoteReward(moneycount.getAccuPromoteReward()+money);
								moneycount.setAccuProfitAndLossMoney(moneycount.getAccuProfitAndLossMoney()+money);
							}
							
						
							boolean flag = this.moneycountService.updateMoneycount(moneycount);
							Timestamp timestamp = new Timestamp(System.currentTimeMillis());
							Moneyhistorydetail moneyhistorydetail = new Moneyhistorydetail();
							moneyhistorydetail.setAffectMoney(Double.valueOf(money));
							moneyhistorydetail.setAvailableBalance(moneycount
									.getAvailableMoney());
							moneyhistorydetail.setCollectMoney(moneycount.getCollectTotalMoney());
							moneyhistorydetail.setFrozenMoney(moneycount.getFrozenMoney());
							moneyhistorydetail.setOccurTime(timestamp);
							moneyhistorydetail.setUservip(uservip);
							moneyhistorydetail.setIntroduction(lrib.getRemark());
							moneyhistorydetail.setLoanno(loanno);
							boolean bool1 = this.moneyhistorydetailService
									.createMoneyhistorydetail(moneyhistorydetail);
						}
					}
				}
			}
			System.out.println("后台通知:" + verifySignature);
			System.out.println("返回码:" + ResultCode);
			System.out.println("返回次数:" + ReturnTimes);

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
	 * 充值返回处理
	 * 
	 * @return
	 */
	public String loanrechargereturn() {

		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");

			Website website = this.websiteService.findWebsiteBywebsiteId(1);
			String publickey = website.getPublicKey();
			String privatekey = website.getPrivateKey();

			MD5Trust md5 = new MD5Trust();
			RsaHelper rsa = RsaHelper.getInstance();

			if (StringUtils.isNotBlank(CardNoList)) {
				CardNoList = rsa.decryptData(CardNoList, privatekey);
				if (StringUtils.isBlank(CardNoList)) {
					CardNoList = "";
				}
			}
			String dataStr = RechargeMoneymoremore + PlatformMoneymoremore
					+ LoanNo + OrderNo + Amount + Fee + FeePlatform
					+ RechargeType + FeeType + CardNoList + RandomTimeStamp
					+ Remark1 + Remark2 + Remark3 + ResultCode;
			if (antistate == 1) {
				dataStr = md5.getMD5Info(dataStr);
			}

			// 签名
			boolean verifySignature = rsa.verifySignature(SignInfo, dataStr,
					publickey);
			System.out.println("后台通知:" + verifySignature);
			System.out.println("返回码:" + ResultCode);
			System.out.println("返回次数:" + ReturnTimes);

			if (verifySignature && this.ResultCode.equals("88")) {// 成功处理充值记录
				

				this.setMessage("充值成功", "/borrow/toNote", "3");
				return "success";
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.setMessage(this.Message, "/borrow/toNote", "3");
		if("wap".equals(Remark2)){
			this.setMessage(this.Message, "/wap/findUsers", "3");
			return "mobile";
		}
		return "success";
	}

	/**
	 * 充值异步通知处理
	 */
	public void loanrechargenotify() {

		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");
			Website website = this.websiteService.findWebsiteBywebsiteId(1);
			String publickey = website.getPublicKey();
			String privatekey = website.getPrivateKey();

			MD5Trust md5 = new MD5Trust();
			RsaHelper rsa = RsaHelper.getInstance();

			if (StringUtils.isNotBlank(CardNoList)) {
				CardNoList = rsa.decryptData(CardNoList, privatekey);
				if (StringUtils.isBlank(CardNoList)) {
					CardNoList = "";
				}
			}
			String dataStr = RechargeMoneymoremore + PlatformMoneymoremore
					+ LoanNo + OrderNo + Amount + Fee + FeePlatform
					+ RechargeType + FeeType + CardNoList + RandomTimeStamp
					+ Remark1 + Remark2 + Remark3 + ResultCode;
			if (antistate == 1) {
				dataStr = md5.getMD5Info(dataStr);
			}

			// 签名
			boolean verifySignature = rsa.verifySignature(SignInfo, dataStr,
					publickey);
			System.out.println("后台通知:" + verifySignature);
			System.out.println("返回码:" + ResultCode);
			System.out.println("返回次数:" + ReturnTimes);

			if (verifySignature && this.ResultCode.equals("88")) {// 成功处理充值记录
				DecimalFormat df = new DecimalFormat("##.00");
				String[] ordernoarr = this.OrderNo.split("_");

				String offlinerechargeId = ordernoarr[1];

				this.rechargeHandle(offlinerechargeId);

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

	public void rechargeHandle(String rechargeId) throws NumberFormatException,
			Exception {
		DecimalFormat df = new DecimalFormat("##.00");
		OfflinerechargeService offlinerechargeService = new OfflinerechargeServiceImpl();
		Offlinerecharge offlinerecharge = offlinerechargeService
				.findOfflinerechargeById(Integer.parseInt(rechargeId));
		if (offlinerecharge.getRechargeStatus() != 1) {
			offlinerecharge.setCheckOrderNum(this.LoanNo);
			offlinerecharge.setRechargeStatus(1);
			offlinerechargeService.updateOfflinerecharge(offlinerecharge);

			MoneycountService moneycountService = new MoneycountServiceImpl();
			Moneycount moneycount = moneycountService
					.findMoneycountByuserId(offlinerecharge.getUservip()
							.getUserId());
			UservipService uservipService = new UservipServiceImpl();
			Uservip uservip = uservipService
					.findUservipByUserid(offlinerecharge.getUservip()
							.getUserId());
			// 线上充值成功资金记录处理
			MoneyhistorydetailService moneyhistorydetailService = new MoneyhistorydetailServiceImpl();
			// Systemconf sysconf = null;
			// if("1".equals(uservip.getIsVIP())){
			// sysconf =
			// systemconfService.findSystemconfByParname("con_online_recharge_fee_2");
			// }else{
			// sysconf =
			// systemconfService.findSystemconfByParname("con_online_recharge_fee");
			// }
			// double recharge_fee =
			// Double.valueOf(df.format(offlinerecharge.getRechargeAmount()*Double.valueOf(sysconf.getParvalue())));
			Moneyhistorydetail moneyhistorydetail = new Moneyhistorydetail();
			moneyhistorydetail.setUservip(offlinerecharge.getUservip());
			moneyhistorydetail.setAffectMoney(offlinerecharge
					.getRechargeAmount());
			moneyhistorydetail.setAvailableBalance(Double.valueOf(df
					.format(moneycount.getAvailableMoney()
							+ offlinerecharge.getRechargeAmount())));
			moneyhistorydetail.setFrozenMoney(moneycount.getFrozenMoney());
			moneyhistorydetail.setCollectMoney(moneycount.getCollectTotalMoney());
			moneyhistorydetail.setIntroduction("充值成功");
			moneyhistorydetail
					.setOccurTime(new Timestamp(new Date().getTime()));
			moneyhistorydetailService
					.createMoneyhistorydetail(moneyhistorydetail);// 资金历史明细
			double chargefee = 0;
			if(Fee!=null && !Fee.equals("") && Double.valueOf(Fee)>0){
				chargefee = Double.valueOf(Fee);
				 moneyhistorydetail= new Moneyhistorydetail();
				 moneyhistorydetail.setUservip(offlinerecharge.getUservip());
				 moneyhistorydetail.setAffectMoney(Double.valueOf(Fee));
				 moneyhistorydetail.setAvailableBalance(Double.valueOf(df.format(moneycount.getAvailableMoney()+offlinerecharge.getRechargeAmount()-Double.valueOf(Fee))));
				 moneyhistorydetail.setFrozenMoney(moneycount.getFrozenMoney());
				 moneyhistorydetail.setCollectMoney(moneycount.getCollectTotalMoney());
				 moneyhistorydetail.setIntroduction("快捷充值手续费");
				 moneyhistorydetail.setOccurTime(new Timestamp(new
				 Date().getTime()));
				 moneyhistorydetailService.createMoneyhistorydetail(moneyhistorydetail);//资金历史明细
			}
			

			moneycount.setAvailableMoney(moneycount.getAvailableMoney()
					+ offlinerecharge.getRechargeAmount()-chargefee);
			moneycount.setTotalMoney(Double.valueOf(df.format(moneycount
					.getTotalMoney() + offlinerecharge.getRechargeAmount()-chargefee)));
			moneycount.setAccuRechargeMoney(Double.valueOf(df.format(moneycount
					.getAccuRechargeMoney()
					+ offlinerecharge.getRechargeAmount())));
			moneycount.setAccuRechargeFee(Double.valueOf(df.format(moneycount
					.getAccuRechargeFee())));
			moneycountService.updateMoneycount(moneycount);

			this.addInbox("充值成功", "您的资金发生变动，请注意查看", uservip.getUserId());
		}
	}

	/**
	 * 提现申请返回处理
	 * 
	 * @return
	 */
	public String loanwithdrawsreturn() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");
			Website website = this.websiteService.findWebsiteBywebsiteId(1);
			String publickey = website.getPublicKey();

			MD5Trust md5 = new MD5Trust();
			RsaHelper rsa = RsaHelper.getInstance();
			String dataStr = WithdrawMoneymoremore + PlatformMoneymoremore
					+ LoanNo + OrderNo + Amount + FeeMax + FeeWithdraws
					+ FeePercent + Fee + FreeLimit + FeeRate + FeeSplitting
					+ RandomTimeStamp + Remark1 + Remark2 + Remark3
					+ ResultCode;
			if (antistate == 1) {
				dataStr = md5.getMD5Info(dataStr);
			}

			// 签名
			boolean verifySignature = rsa.verifySignature(SignInfo, dataStr,
					publickey);
			

			this.verifySignature = Boolean.toString(verifySignature);
			System.out.println("页面返回:" + this.verifySignature);
			System.out.println("返回码:" + ResultCode);

		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setMessage(this.Message, "/userbank/showTranslate", "3");
		if("wap".equals(Remark2)){
			this.setMessage(this.Message, "/wap/findUsers", "3");
			return "mobile";
		}
		return "success";
	}

	/**
	 * 提现申请异步通知处理
	 */
	public void loanwithdrawsnotify() {

		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");

			Website website = this.websiteService.findWebsiteBywebsiteId(1);
			String publickey = website.getPublicKey();

			MD5Trust md5 = new MD5Trust();
			RsaHelper rsa = RsaHelper.getInstance();
			String dataStr = WithdrawMoneymoremore + PlatformMoneymoremore
					+ LoanNo + OrderNo + Amount + FeeMax + FeeWithdraws
					+ FeePercent + Fee + FreeLimit + FeeRate + FeeSplitting
					+ RandomTimeStamp + Remark1 + Remark2 + Remark3
					+ ResultCode;
			if (antistate == 1) {
				dataStr = md5.getMD5Info(dataStr);
			}

			// 签名
			boolean verifySignature = rsa.verifySignature(SignInfo, dataStr,
					publickey);

			if (verifySignature) {
				this.withdrawsHandle();
			}
			System.out.println("后台通知:" + verifySignature);
			System.out.println("返回码:" + ResultCode);
			System.out.println("返回次数:" + ReturnTimes);

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
	 * 提现申请返回处理
	 * 
	 * @throws Exception
	 */
	public void withdrawsHandle() throws Exception {
		String[] orders = this.OrderNo.split("_");
		int translateId = Integer.valueOf(orders[0]);
		Translate translate = this.translateService
				.findTranslateById(translateId);

		if (translate.getSerialnum() != null && !translate.getSerialnum().equals("") && !this.ResultCode.equals("89")) {
			return;
		}

		int uservipId = Integer.valueOf(orders[1]);
		Uservip uservip = this.uservipService.findUservipByUserid(uservipId);

		double affectMoney = Double.valueOf(this.Amount);

		Moneycount moneycount = this.moneycountService
				.findMoneycountByuserId(uservip.getUserId().intValue());

		if (this.ResultCode.equals("88")) {// 提现成功资金记录
			DecimalFormat df = new DecimalFormat("##.00");
			translate.setState(2);
			translate.setTexplain(this.Message);
			translate.setFee(Double.valueOf(this.FeeWithdraws));
			translate.setSerialnum(this.LoanNo);

			Moneyhistorydetail moneys = new Moneyhistorydetail();
			moneys.setOccurTime(new Timestamp(new Date().getTime()));
			moneys.setAffectMoney(affectMoney);
			moneys.setAvailableBalance(Double.valueOf(moneycount
					.getAvailableMoney() - affectMoney));
			moneys.setFrozenMoney(Double.valueOf(df.format(moneycount
					.getFrozenMoney().doubleValue())));
			moneys.setIntroduction("提现成功");
			moneys.setUservip(uservip);
			moneys.setCollectMoney(moneycount.getCollectTotalMoney());
			moneys.setOccurTime(new Timestamp(new Date().getTime()));
			this.moneyhistorydetailService.createMoneyhistorydetail(moneys);

			moneys = new Moneyhistorydetail();
			moneys.setOccurTime(new Timestamp(new Date().getTime()));
			moneys.setAffectMoney(translate.getFee());
			moneys.setAvailableBalance(Double.valueOf(moneycount
					.getAvailableMoney()- affectMoney));
			moneys.setFrozenMoney(Double.valueOf(moneycount.getFrozenMoney()
					.doubleValue()));
			moneys.setIntroduction("提现成功，扣除手续费");
			moneys.setUservip(uservip);
			moneys.setCollectMoney(moneycount.getCollectTotalMoney());
			moneys.setOccurTime(new Timestamp(new Date().getTime()));
			this.moneyhistorydetailService.createMoneyhistorydetail(moneys);

			moneycount.setTotalMoney(Double.valueOf(df.format(moneycount
					.getTotalMoney().doubleValue() - affectMoney)));
			moneycount.setAvailableMoney(Double.valueOf(df.format(moneycount
					.getAvailableMoney().doubleValue()
					- Double.valueOf(affectMoney).doubleValue())));
			moneycount.setFrozenMoney(Double.valueOf(df.format(moneycount
					.getFrozenMoney().doubleValue())));
			moneycount.setAccuWidthdrawMoney(Double.valueOf(df
					.format(moneycount.getAccuWidthdrawMoney().doubleValue()
							+ affectMoney)));

			moneycount.setAccuWithdrawalMoney(Double.valueOf(df
					.format(moneycount.getAccuWithdrawalMoney().doubleValue()
							+ translate.getFee().doubleValue())));
			moneycount
					.setAccuProfitAndLossMoney(Double.valueOf(df
							.format(moneycount.getAccuProfitAndLossMoney()
									.doubleValue()
									- translate.getFee().doubleValue())));

			this.moneycountService.updateMoneycount(moneycount);
		} else if (this.ResultCode.equals("90")) {// 提现冻结资金记录
			DecimalFormat df = new DecimalFormat("##.00");
			MoneyhistorydetailService moneyhistorydetailService = new MoneyhistorydetailServiceImpl();
			Moneyhistorydetail moneyhistorydetail = new Moneyhistorydetail();
			moneyhistorydetail.setAffectMoney(affectMoney);
			moneyhistorydetail.setAvailableBalance(Double.valueOf(df
					.format(moneycount.getAvailableMoney() - affectMoney)));
			moneyhistorydetail.setFrozenMoney(Double.valueOf(df
					.format(moneycount.getFrozenMoney() + affectMoney)));
			moneyhistorydetail.setCollectMoney(moneycount.getCollectTotalMoney());
			moneyhistorydetail.setUservip(uservip);
			moneyhistorydetail
					.setOccurTime(new Timestamp(new Date().getTime()));
			moneyhistorydetail.setIntroduction("提现，冻结提现金额");
			moneyhistorydetailService
					.createMoneyhistorydetail(moneyhistorydetail);

			moneycount.setAvailableMoney(Double.valueOf(df.format(moneycount
					.getAvailableMoney().doubleValue()
					- Double.valueOf(affectMoney).doubleValue())));
			moneycount.setFrozenMoney(Double.valueOf(df.format(moneycount
					.getFrozenMoney().doubleValue()
					+ Double.valueOf(affectMoney).doubleValue())));
			this.moneycountService.updateMoneycount(moneycount);
			translate.setSerialnum(this.LoanNo);
			translate.setFee(Double.valueOf(this.FeeWithdraws));
		} else if (this.ResultCode.equals("89")) {// 提现冻结资金记录
			DecimalFormat df = new DecimalFormat("##.00");
			MoneyhistorydetailService moneyhistorydetailService = new MoneyhistorydetailServiceImpl();
			Moneyhistorydetail moneyhistorydetail = new Moneyhistorydetail();
			moneyhistorydetail.setAffectMoney(affectMoney);
			moneyhistorydetail.setAvailableBalance(Double.valueOf(df
					.format(moneycount.getAvailableMoney() + affectMoney)));
			moneyhistorydetail.setFrozenMoney(Double.valueOf(df
					.format(moneycount.getFrozenMoney())));
			moneyhistorydetail.setCollectMoney(moneycount.getCollectTotalMoney());
			moneyhistorydetail.setUservip(uservip);
			moneyhistorydetail
					.setOccurTime(new Timestamp(new Date().getTime()));
			moneyhistorydetail.setIntroduction("提现退回，增加金额，请查看银行卡信息是否正确");
			moneyhistorydetailService
					.createMoneyhistorydetail(moneyhistorydetail);

			moneycount.setAvailableMoney(Double.valueOf(df.format(moneycount
					.getAvailableMoney().doubleValue()
					+ Double.valueOf(affectMoney).doubleValue())));
			this.moneycountService.updateMoneycount(moneycount);
		} else {// 提现失败
			translate.setState(3);
			translate.setDealTime(new Timestamp(new Date().getTime()));
			translate.setTexplain(this.Message);
		}
		this.translateService.updateTranslate(translate);
	}

	/**
	 * 提现审核返回处理
	 * 
	 * @return
	 */
	public String withdrawsAuditreturn() {

		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");

			Website website = this.websiteService.findWebsiteBywebsiteId(1);
			String publickey = website.getPublicKey();

			MD5Trust md5 = new MD5Trust();
			RsaHelper rsa = RsaHelper.getInstance();
			String dataStr = LoanNoList + LoanNoListFail
					+ PlatformMoneymoremore + AuditType + RandomTimeStamp
					+ Remark1 + Remark2 + Remark3 + ResultCode;
			if (antistate == 1) {
				dataStr = md5.getMD5Info(dataStr);
			}

			// 签名
			boolean verifySignature = rsa.verifySignature(SignInfo, dataStr,
					publickey);
			System.out.println("后台通知:" + verifySignature);
			System.out.println("返回码:" + ResultCode);
			System.out.println("返回次数:" + ReturnTimes);

			if (verifySignature && ResultCode.equals("88")) {// 业务逻辑处理
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setMessage(this.Message, "/back/translate2", "3");
		return "success";
	}

	/**
	 * 提现审核异步通知处理
	 */
	public void withdrawsAuditnotify() {

		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");

			Website website = this.websiteService.findWebsiteBywebsiteId(1);
			String publickey = website.getPublicKey();

			MD5Trust md5 = new MD5Trust();
			RsaHelper rsa = RsaHelper.getInstance();
			String dataStr = LoanNoList + LoanNoListFail
					+ PlatformMoneymoremore + AuditType + RandomTimeStamp
					+ Remark1 + Remark2 + Remark3 + ResultCode;
			if (antistate == 1) {
				dataStr = md5.getMD5Info(dataStr);
			}

			// 签名
			boolean verifySignature = rsa.verifySignature(SignInfo, dataStr,
					publickey);
			System.out.println("后台通知:" + verifySignature);
			System.out.println("返回码:" + ResultCode);
			System.out.println("返回次数:" + ReturnTimes);

			if (verifySignature && ResultCode.equals("88")) {// 业务逻辑处理
				this.withdrawsAuditHandle();
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
	 * 提现审核业务处理
	 * 
	 * @throws Exception
	 */
	public void withdrawsAuditHandle() throws Exception {
		int translateid = Integer.valueOf(this.Remark1);
		Translate translate = this.translateService
				.findTranslateById(translateid);
		if (translate.getState().equals("2")
				|| translate.getState().equals("3")) {
			return;
		}
		
		
		
		Uservip uservip = this.uservipService.findUservipByUserid(translate
				.getUservip().getUserId().intValue());

		String remark3 = this.Remark3;
		String[] remark = remark3.split("_");
		int managerId = Integer.valueOf(remark[0]);
		Manager mana = this.managerService.findManagerByManagerId(managerId);

		translate.setDealUser(mana.getManagerName());
		translate.setDealTime(new Timestamp(new Date().getTime()));
		
		Oerationlog oerationlog1 = new Oerationlog();
		oerationlog1.setManager(mana);
		oerationlog1.setOerationChangeTime(new Timestamp(new Date().getTime()));
		oerationlog1.setOrationIp(remark[1]);
		if (this.AuditType.equals("5")) {
			translate.setState(2);
			translate.setTexplain(this.Remark2);
			
			DecimalFormat df = new DecimalFormat("##.00");
			Double affectMoney = translate.getAffectMoney();

			Moneycount money = this.moneycountService
					.findMoneycountByuserId(uservip.getUserId());

			Moneyhistorydetail moneys = new Moneyhistorydetail();
			moneys.setOccurTime(new Timestamp(new Date().getTime()));
			moneys.setAffectMoney(affectMoney);
			moneys.setAvailableBalance(Double.valueOf(money.getAvailableMoney()));
			moneys.setFrozenMoney(Double.valueOf(df.format(money
					.getFrozenMoney().doubleValue() - affectMoney.doubleValue())));
			moneys.setIntroduction("提现成功");
			moneys.setUservip(translate.getUservip());
			moneys.setCollectMoney(money.getCollectTotalMoney());
			moneys.setOccurTime(new Timestamp(new Date().getTime()));
			this.moneyhistorydetailService.createMoneyhistorydetail(moneys);

			moneys = new Moneyhistorydetail();
			moneys.setOccurTime(new Timestamp(new Date().getTime()));
			moneys.setAffectMoney(translate.getFee());
			moneys.setAvailableBalance(Double.valueOf(money.getAvailableMoney()));
			moneys.setFrozenMoney(Double.valueOf(money.getFrozenMoney()
					.doubleValue() - affectMoney.doubleValue()));
			moneys.setIntroduction("提现成功，扣除手续费");
			moneys.setUservip(uservip);
			moneys.setCollectMoney(money.getCollectTotalMoney());
			moneys.setOccurTime(new Timestamp(new Date().getTime()));
			this.moneyhistorydetailService.createMoneyhistorydetail(moneys);

			money.setAvailableMoney(Double.valueOf(money.getAvailableMoney()
					.doubleValue()));
			money.setTotalMoney(Double.valueOf(df.format(money.getTotalMoney()
					.doubleValue() - affectMoney.doubleValue())));
			money.setFrozenMoney(Double.valueOf(df.format(money
					.getFrozenMoney().doubleValue() - affectMoney.doubleValue())));
			money.setAccuWidthdrawMoney(Double.valueOf(df.format(money
					.getAccuWidthdrawMoney().doubleValue()
					+ affectMoney.doubleValue())));
			// List tender =
			// this.tenderService.findTendersByuser(this.userId.intValue());
			// if ((tender == null) || (tender.size() == 0)) {
			money.setAccuWithdrawalMoney(Double.valueOf(df.format(money
					.getAccuWithdrawalMoney().doubleValue()
					+ translate.getFee().doubleValue())));
			money.setAccuProfitAndLossMoney(Double.valueOf(df.format(money
					.getAccuProfitAndLossMoney().doubleValue()
					- translate.getFee().doubleValue())));
			// }
			this.moneycountService.updateMoneycount(money);

			oerationlog1.setOerationCategory("tranSuccess");
			oerationlog1.setOerationRemaks("提现成功");

			Inbox inbox = new Inbox();
			inbox.setUservip(uservip);
			inbox.setContent("尊重的用户你好，恭喜你，你已提现成功！");
			inbox.setReceiveTime(new Timestamp(new Date().getTime()));
			inbox.setSendName("后台管理员");
			inbox.setTitle("提现成功");
			inbox.setStatus(Integer.valueOf(0));
			this.inboxService.createInbox(inbox);
		} else if (this.AuditType.equals("6")) {
			translate.setState(3);
			translate.setTexplain(this.Remark2);
			
			
			oerationlog1.setOerationCategory("tranError");
			oerationlog1.setOerationRemaks("审核未通过");
			DecimalFormat df = new DecimalFormat("##.00");
			Double affectMoney = translate.getAffectMoney();
			Moneycount money = this.moneycountService
					.findMoneycountByuserId(uservip.getUserId());

			Moneyhistorydetail moneys = new Moneyhistorydetail();
			moneys.setOccurTime(new Timestamp(new Date().getTime()));
			moneys.setAffectMoney(affectMoney);
			moneys.setAvailableBalance(Double.valueOf(df.format(money
					.getAvailableMoney() + affectMoney)));
			moneys.setFrozenMoney(Double.valueOf(money.getFrozenMoney()
					.doubleValue() - affectMoney.doubleValue()));
			moneys.setIntroduction("提现失败，冻结解除");
			moneys.setUservip(uservip);
			moneys.setCollectMoney(money.getCollectTotalMoney());
			moneys.setOccurTime(new Timestamp(new Date().getTime()));
			this.moneyhistorydetailService.createMoneyhistorydetail(moneys);

			money.setAvailableMoney(Double.valueOf(df.format(money
					.getAvailableMoney() + affectMoney)));
			money.setFrozenMoney(Double.valueOf(df.format(money
					.getFrozenMoney() - affectMoney)));

			Inbox inbox = new Inbox();
			inbox.setUservip(uservip);
			inbox.setContent("尊重的用户你好，你的提现审核未通过，如有疑问，请联系客服或者管理员！");
			inbox.setReceiveTime(new Timestamp(new Date().getTime()));
			inbox.setSendName("后台管理员");
			inbox.setTitle("提现失败");
			inbox.setStatus(Integer.valueOf(0));
			this.inboxService.createInbox(inbox);
		}
		this.oerationlogService.createOerationlog(oerationlog1);
		this.translateService.updateTranslate(translate);

	}
	/**
	 * 理财投标返回处理
	 * 
	 * @return
	 */
	public String loantenderlcreturn() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");
			Website website = this.websiteService.findWebsiteBywebsiteId(1);
			String publickey = website.getPublicKey();

			MD5Trust md5 = new MD5Trust();
			RsaHelper rsa = RsaHelper.getInstance();
			String dataStr = LoanNoList + LoanNoListFail
					+ PlatformMoneymoremore + AuditType + RandomTimeStamp
					+ Remark1 + Remark2 + Remark3 + ResultCode;
			if (antistate == 1) {
				dataStr = md5.getMD5Info(dataStr);
			}

			// 签名
			boolean verifySignature = rsa.verifySignature(SignInfo, dataStr,
					publickey);
			System.out.println("后台通知:" + verifySignature);
			System.out.println("返回码:" + ResultCode);
			System.out.println("返回次数:" + ReturnTimes);

			if (verifySignature && ResultCode.equals("88")) {// 业务逻辑处理
				this.setMessage("操作成功","/tender/tenderlist?lussingType=9", "3");
				if("wap".equals(Remark2)){
					this.setMessage("操作成功", "/wap/toInverst", "3");
					return "mobile";
				}
				return "success";
			} else {
				this.setMessage(this.Message,"/tender/tenderlist?lussingType=9", "3");
				if("wap".equals(Remark2)){
					this.setMessage("操作成功", "/wap/toInverst", "3");
					return "mobile";
				}
				return "success";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setMessage(this.Message, "/tender/tenderlist?lussingType=9","3");
		if("wap".equals(Remark2)){
			this.setMessage("操作成功", "/wap/toInverst", "3");
			return "mobile";
		}
		return "success";
	}
	/**
	 * 投标复审异步通知处理
	 */
	public void loantenderlcnotify() {

		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");

			Website website = this.websiteService.findWebsiteBywebsiteId(1);
			String publickey = website.getPublicKey();

			MD5Trust md5 = new MD5Trust();
			RsaHelper rsa = RsaHelper.getInstance();
			String dataStr = LoanNoList + LoanNoListFail
					+ PlatformMoneymoremore + AuditType + RandomTimeStamp
					+ Remark1 + Remark2 + Remark3 + ResultCode;
			if (antistate == 1) {
				dataStr = md5.getMD5Info(dataStr);
			}

			// 签名
			boolean verifySignature = rsa.verifySignature(SignInfo, dataStr,
					publickey);
			System.out.println("后台通知:" + verifySignature);
			System.out.println("返回码:" + ResultCode);
			System.out.println("返回次数:" + ReturnTimes);

			if (verifySignature && ResultCode.equals("88")) {// 业务逻辑处理
					borrowReauditSuccess(this.Remark1);
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
	
	
	public String platTransferreturn(){
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");
			Website website = this.websiteService.findWebsiteBywebsiteId(1);
			String publickey = website.getPublicKey();
			LoanJsonList = Common.UrlDecoder(LoanJsonList, "utf-8");
			MD5Trust md5 = new MD5Trust();
			RsaHelper rsa = RsaHelper.getInstance();
			String dataStr = LoanJsonList + PlatformMoneymoremore + Action + RandomTimeStamp + Remark1 + Remark2 + Remark3 + ResultCode;
//			String dataStr = LoanNoList + LoanNoListFail
//					+ PlatformMoneymoremore + AuditType + RandomTimeStamp
//					+ Remark1 + Remark2 + Remark3 + ResultCode;
			if (antistate == 1) {
				dataStr = md5.getMD5Info(dataStr);
			}

			// 签名
			boolean verifySignature = rsa.verifySignature(SignInfo, dataStr,
					publickey);
			System.out.println("后台通知:" + verifySignature);
			System.out.println("返回码:" + ResultCode);
			
			// 如果成功，处理相关记录
			if (verifySignature && ResultCode.equals("88")) {
				this.setMessage("转账成功", "/back/3_list.jsp", "3");
				return "success";
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setMessage(this.Message, "/back/3_list.jsp", "3");
		return "success";
	
	}
	
	public void platTransfernotify(){
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");

			Website website = this.websiteService.findWebsiteBywebsiteId(1);
			String publickey = website.getPublicKey();

			LoanJsonList = Common.UrlDecoder(LoanJsonList, "utf-8");
			MD5Trust md5 = new MD5Trust();
			RsaHelper rsa = RsaHelper.getInstance();
			String dataStr = LoanJsonList + PlatformMoneymoremore + Action + RandomTimeStamp + Remark1 + Remark2 + Remark3 + ResultCode;
//			
			if (antistate == 1) {
				dataStr = md5.getMD5Info(dataStr);
			}

			// 签名
			boolean verifySignature = rsa.verifySignature(SignInfo, dataStr,
					publickey);
			System.out.println("后台通知:" + verifySignature);
			System.out.println("返回码:" + ResultCode);
			System.out.println("返回次数:" + ReturnTimes);

			List<Object> loaninfolist = Common.JSONDecodeList(LoanJsonList,
					LoanReturnInfoBean.class);
			if (verifySignature && ResultCode.equals("88")) {
				if (loaninfolist != null && loaninfolist.size() > 0) {
					for (int j = 0; j < loaninfolist.size(); j++) {
						if (loaninfolist.get(j) instanceof LoanReturnInfoBean) {
							LoanReturnInfoBean lrib = (LoanReturnInfoBean) loaninfolist
									.get(j);
							String userid = lrib.getRemark();
							String orderno = lrib.getOrderNo();
							String loanno = lrib.getLoanNo();
							double money  = Double.valueOf(lrib.getAmount());

							String sql = "";
							List list = moneyhistorydetailService.findMoneyhistorydetailBySql("select * from moneyhistorydetail where loanno='"+loanno+"'");
							if(list!=null && list.size()>0){
								continue;
							}
							
							Uservip uservip = this.uservipService.findUservipByUserid(Integer
									.valueOf(userid).intValue());
							
							Moneycount moneycount = this.moneycountService
									.findMoneycountByuserId(Integer.valueOf(userid).intValue());
							if(lrib.getTransferName().equals("plattoparty")){
								moneycount.setAvailableMoney(Double.valueOf(moneycount
										.getAvailableMoney().doubleValue()
										+ Double.valueOf(money).doubleValue()));

								moneycount.setTotalMoney(Double.valueOf(moneycount.getTotalMoney()
										.doubleValue() + Double.valueOf(money).doubleValue()));
							}else if(lrib.getTransferName().equals("partytoplat")){
								moneycount.setAvailableMoney(Double.valueOf(moneycount
										.getAvailableMoney().doubleValue()
										- Double.valueOf(money).doubleValue()));

								moneycount.setTotalMoney(Double.valueOf(moneycount.getTotalMoney()
										.doubleValue() - Double.valueOf(money).doubleValue()));
							}
							boolean flag = this.moneycountService.updateMoneycount(moneycount);
							Timestamp timestamp = new Timestamp(System.currentTimeMillis());
							Inbox inbox = new Inbox();
							inbox.setContent(this.Remark1);
							inbox.setReceiveTime(timestamp);
							inbox.setSendName(website.getWebName());
							inbox.setTitle("修改余额");
							inbox.setStatus(Integer.valueOf(0));
							inbox.setUservip(uservip);
							boolean flag2 = this.inboxService.createInbox(inbox);
							Moneyhistorydetail moneyhistorydetail = new Moneyhistorydetail();
							moneyhistorydetail.setAffectMoney(Double.valueOf(money));
							moneyhistorydetail.setAvailableBalance(moneycount
									.getAvailableMoney());
							moneyhistorydetail.setCollectMoney(moneycount.getCollectTotalMoney());
							moneyhistorydetail.setFrozenMoney(moneycount.getFrozenMoney());
							moneyhistorydetail.setOccurTime(timestamp);
							moneyhistorydetail.setUservip(uservip);
							moneyhistorydetail.setIntroduction(Remark1);
							moneyhistorydetail.setLoanno(loanno);
							boolean bool1 = this.moneyhistorydetailService
									.createMoneyhistorydetail(moneyhistorydetail);
						}
					}
				}
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

	/******************** 测试 结束 ********************/

	/******************** 分隔 ********************/

	public String getPlatformMoneymoremore() {
		return PlatformMoneymoremore;
	}

	public void setPlatformMoneymoremore(String platformMoneymoremore) {
		PlatformMoneymoremore = platformMoneymoremore;
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

	public String getRegisterType() {
		return RegisterType;
	}

	public void setRegisterType(String registerType) {
		RegisterType = registerType;
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

	public String getLoanOutMoneymoremore1() {
		return LoanOutMoneymoremore1;
	}

	public void setLoanOutMoneymoremore1(String loanOutMoneymoremore1) {
		LoanOutMoneymoremore1 = loanOutMoneymoremore1;
	}

	public String getLoanInMoneymoremore1() {
		return LoanInMoneymoremore1;
	}

	public void setLoanInMoneymoremore1(String loanInMoneymoremore1) {
		LoanInMoneymoremore1 = loanInMoneymoremore1;
	}

	public String getOrderNo1() {
		return OrderNo1;
	}

	public void setOrderNo1(String orderNo1) {
		OrderNo1 = orderNo1;
	}

	public String getBatchNo1() {
		return BatchNo1;
	}

	public void setBatchNo1(String batchNo1) {
		BatchNo1 = batchNo1;
	}

	public String getAmount1() {
		return Amount1;
	}

	public void setAmount1(String amount1) {
		Amount1 = amount1;
	}

	public String getTransferName1() {
		return TransferName1;
	}

	public void setTransferName1(String transferName1) {
		TransferName1 = transferName1;
	}

	public String getRemark1() {
		return Remark1;
	}

	public void setRemark1(String remark1) {
		Remark1 = remark1;
	}

	public String getLoanInMoneymoremore2() {
		return LoanInMoneymoremore2;
	}

	public void setLoanInMoneymoremore2(String loanInMoneymoremore2) {
		LoanInMoneymoremore2 = loanInMoneymoremore2;
	}

	public String getOrderNo2() {
		return OrderNo2;
	}

	public void setOrderNo2(String orderNo2) {
		OrderNo2 = orderNo2;
	}

	public String getBatchNo2() {
		return BatchNo2;
	}

	public void setBatchNo2(String batchNo2) {
		BatchNo2 = batchNo2;
	}

	public String getAmount2() {
		return Amount2;
	}

	public void setAmount2(String amount2) {
		Amount2 = amount2;
	}

	public String getTransferName2() {
		return TransferName2;
	}

	public void setTransferName2(String transferName2) {
		TransferName2 = transferName2;
	}

	public String getRemark2() {
		return Remark2;
	}

	public void setRemark2(String remark2) {
		Remark2 = remark2;
	}

	public String getLoanJsonList() {
		return LoanJsonList;
	}

	public void setLoanJsonList(String loanJsonList) {
		LoanJsonList = loanJsonList;
	}

	public String getTransferAction() {
		return TransferAction;
	}

	public void setTransferAction(String transferAction) {
		TransferAction = transferAction;
	}

	public String getAction() {
		return Action;
	}

	public void setAction(String action) {
		Action = action;
	}

	public String getTransferType() {
		return TransferType;
	}

	public void setTransferType(String transferType) {
		TransferType = transferType;
	}

	public String getPlatformId() {
		return PlatformId;
	}

	public void setPlatformId(String platformId) {
		PlatformId = platformId;
	}

	public String getPlatformType() {
		return PlatformType;
	}

	public void setPlatformType(String platformType) {
		PlatformType = platformType;
	}

	public String getRechargeMoneymoremore() {
		return RechargeMoneymoremore;
	}

	public void setRechargeMoneymoremore(String rechargeMoneymoremore) {
		RechargeMoneymoremore = rechargeMoneymoremore;
	}

	public String getOrderNo() {
		return OrderNo;
	}

	public void setOrderNo(String orderNo) {
		OrderNo = orderNo;
	}

	public String getAmount() {
		return Amount;
	}

	public void setAmount(String amount) {
		Amount = amount;
	}

	public String getWithdrawMoneymoremore() {
		return WithdrawMoneymoremore;
	}

	public void setWithdrawMoneymoremore(String withdrawMoneymoremore) {
		WithdrawMoneymoremore = withdrawMoneymoremore;
	}

	public String getFeePercent() {
		return FeePercent;
	}

	public void setFeePercent(String feePercent) {
		FeePercent = feePercent;
	}

	public String getCardNo() {
		return CardNo;
	}

	public void setCardNo(String cardNo) {
		CardNo = cardNo;
	}

	public String getCardType() {
		return CardType;
	}

	public void setCardType(String cardType) {
		CardType = cardType;
	}

	public String getBankCode() {
		return BankCode;
	}

	public void setBankCode(String bankCode) {
		BankCode = bankCode;
	}

	public String getBranchBankName() {
		return BranchBankName;
	}

	public void setBranchBankName(String branchBankName) {
		BranchBankName = branchBankName;
	}

	public String getProvince() {
		return Province;
	}

	public void setProvince(String province) {
		Province = province;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getLoanNo() {
		return LoanNo;
	}

	public void setLoanNo(String loanNo) {
		LoanNo = loanNo;
	}

	public String getBatchNo() {
		return BatchNo;
	}

	public void setBatchNo(String batchNo) {
		BatchNo = batchNo;
	}

	public String getBeginTime() {
		return BeginTime;
	}

	public void setBeginTime(String beginTime) {
		BeginTime = beginTime;
	}

	public String getEndTime() {
		return EndTime;
	}

	public void setEndTime(String endTime) {
		EndTime = endTime;
	}

	public String getFullAmount1() {
		return FullAmount1;
	}

	public void setFullAmount1(String fullAmount1) {
		FullAmount1 = fullAmount1;
	}

	public String getFullAmount2() {
		return FullAmount2;
	}

	public void setFullAmount2(String fullAmount2) {
		FullAmount2 = fullAmount2;
	}

	public String getSLoanInMoneymoremore1() {
		return SLoanInMoneymoremore1;
	}

	public void setSLoanInMoneymoremore1(String sLoanInMoneymoremore1) {
		SLoanInMoneymoremore1 = sLoanInMoneymoremore1;
	}

	public String getSAmount1() {
		return SAmount1;
	}

	public void setSAmount1(String sAmount1) {
		SAmount1 = sAmount1;
	}

	public String getSTransferName1() {
		return STransferName1;
	}

	public void setSTransferName1(String sTransferName1) {
		STransferName1 = sTransferName1;
	}

	public String getSRemark1() {
		return SRemark1;
	}

	public void setSRemark1(String sRemark1) {
		SRemark1 = sRemark1;
	}

	public String getSLoanInMoneymoremore2() {
		return SLoanInMoneymoremore2;
	}

	public void setSLoanInMoneymoremore2(String sLoanInMoneymoremore2) {
		SLoanInMoneymoremore2 = sLoanInMoneymoremore2;
	}

	public String getSAmount2() {
		return SAmount2;
	}

	public void setSAmount2(String sAmount2) {
		SAmount2 = sAmount2;
	}

	public String getSTransferName2() {
		return STransferName2;
	}

	public void setSTransferName2(String sTransferName2) {
		STransferName2 = sTransferName2;
	}

	public String getSRemark2() {
		return SRemark2;
	}

	public void setSRemark2(String sRemark2) {
		SRemark2 = sRemark2;
	}

	public String getVerifySignature() {
		return verifySignature;
	}

	public void setVerifySignature(String verifySignature) {
		this.verifySignature = verifySignature;
	}

	public String getAccountNumber() {
		return AccountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		AccountNumber = accountNumber;
	}

	public String getMoneymoremoreId() {
		return MoneymoremoreId;
	}

	public void setMoneymoremoreId(String moneymoremoreId) {
		MoneymoremoreId = moneymoremoreId;
	}

	public String getResultCode() {
		return ResultCode;
	}

	public void setResultCode(String resultCode) {
		ResultCode = resultCode;
	}

	public String getFee() {
		return Fee;
	}

	public void setFee(String fee) {
		Fee = fee;
	}

	public String getFreeLimit() {
		return FreeLimit;
	}

	public void setFreeLimit(String freeLimit) {
		FreeLimit = freeLimit;
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

	public String getLoanInMoneymoremore3() {
		return LoanInMoneymoremore3;
	}

	public void setLoanInMoneymoremore3(String loanInMoneymoremore3) {
		LoanInMoneymoremore3 = loanInMoneymoremore3;
	}

	public String getOrderNo3() {
		return OrderNo3;
	}

	public void setOrderNo3(String orderNo3) {
		OrderNo3 = orderNo3;
	}

	public String getBatchNo3() {
		return BatchNo3;
	}

	public void setBatchNo3(String batchNo3) {
		BatchNo3 = batchNo3;
	}

	public String getAmount3() {
		return Amount3;
	}

	public void setAmount3(String amount3) {
		Amount3 = amount3;
	}

	public String getFullAmount3() {
		return FullAmount3;
	}

	public void setFullAmount3(String fullAmount3) {
		FullAmount3 = fullAmount3;
	}

	public String getTransferName3() {
		return TransferName3;
	}

	public void setTransferName3(String transferName3) {
		TransferName3 = transferName3;
	}

	public String getRemark3() {
		return Remark3;
	}

	public void setRemark3(String remark3) {
		Remark3 = remark3;
	}

	public String getNeedAudit() {
		return NeedAudit;
	}

	public void setNeedAudit(String needAudit) {
		NeedAudit = needAudit;
	}

	public String getRechargeType() {
		return RechargeType;
	}

	public void setRechargeType(String rechargeType) {
		RechargeType = rechargeType;
	}

	public String getCardNoList() {
		return CardNoList;
	}

	public void setCardNoList(String cardNoList) {
		CardNoList = cardNoList;
	}

	public String getAccountType() {
		return AccountType;
	}

	public void setAccountType(String accountType) {
		AccountType = accountType;
	}

	public String getRandomTimeStamp() {
		return RandomTimeStamp;
	}

	public void setRandomTimeStamp(String randomTimeStamp) {
		RandomTimeStamp = randomTimeStamp;
	}

	public String getAuditType() {
		return AuditType;
	}

	public void setAuditType(String auditType) {
		AuditType = auditType;
	}

	public String getLoanNoList() {
		return LoanNoList;
	}

	public void setLoanNoList(String loanNoList) {
		LoanNoList = loanNoList;
	}

	public String getLoanNoListFail() {
		return LoanNoListFail;
	}

	public void setLoanNoListFail(String loanNoListFail) {
		LoanNoListFail = loanNoListFail;
	}

	public String getWithholdBeginDate() {
		return WithholdBeginDate;
	}

	public void setWithholdBeginDate(String withholdBeginDate) {
		WithholdBeginDate = withholdBeginDate;
	}

	public String getWithholdEndDate() {
		return WithholdEndDate;
	}

	public void setWithholdEndDate(String withholdEndDate) {
		WithholdEndDate = withholdEndDate;
	}

	public String getSingleWithholdLimit() {
		return SingleWithholdLimit;
	}

	public void setSingleWithholdLimit(String singleWithholdLimit) {
		SingleWithholdLimit = singleWithholdLimit;
	}

	public String getTotalWithholdLimit() {
		return TotalWithholdLimit;
	}

	public void setTotalWithholdLimit(String totalWithholdLimit) {
		TotalWithholdLimit = totalWithholdLimit;
	}

	public String getFeeMax() {
		return FeeMax;
	}

	public void setFeeMax(String feeMax) {
		FeeMax = feeMax;
	}

	public String getFeeWithdraws() {
		return FeeWithdraws;
	}

	public void setFeeWithdraws(String feeWithdraws) {
		FeeWithdraws = feeWithdraws;
	}

	public String getMainRemark1() {
		return MainRemark1;
	}

	public void setMainRemark1(String mainRemark1) {
		MainRemark1 = mainRemark1;
	}

	public String getMainRemark2() {
		return MainRemark2;
	}

	public void setMainRemark2(String mainRemark2) {
		MainRemark2 = mainRemark2;
	}

	public String getMainRemark3() {
		return MainRemark3;
	}

	public void setMainRemark3(String mainRemark3) {
		MainRemark3 = mainRemark3;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public String getExchangeBatchNo1() {
		return ExchangeBatchNo1;
	}

	public void setExchangeBatchNo1(String exchangeBatchNo1) {
		ExchangeBatchNo1 = exchangeBatchNo1;
	}

	public String getAdvanceBatchNo1() {
		return AdvanceBatchNo1;
	}

	public void setAdvanceBatchNo1(String advanceBatchNo1) {
		AdvanceBatchNo1 = advanceBatchNo1;
	}

	public String getReturnTimes() {
		return ReturnTimes;
	}

	public void setReturnTimes(String returnTimes) {
		ReturnTimes = returnTimes;
	}

	public String getFeeType() {
		return FeeType;
	}

	public void setFeeType(String feeType) {
		FeeType = feeType;
	}

	public String getFeeRate() {
		return FeeRate;
	}

	public void setFeeRate(String feeRate) {
		FeeRate = feeRate;
	}

	public String getFeeSplitting() {
		return FeeSplitting;
	}

	public void setFeeSplitting(String feeSplitting) {
		FeeSplitting = feeSplitting;
	}

	public String getFeePlatform() {
		return FeePlatform;
	}

	public void setFeePlatform(String feePlatform) {
		FeePlatform = feePlatform;
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

	public String getReleaseType() {
		return ReleaseType;
	}

	public void setReleaseType(String releaseType) {
		ReleaseType = releaseType;
	}

	public String getSendAccount() {
		return SendAccount;
	}

	public void setSendAccount(String sendAccount) {
		SendAccount = sendAccount;
	}

	public String getMobileType() {
		return MobileType;
	}

	public void setMobileType(String mobileType) {
		MobileType = mobileType;
	}

	public String getIdentityJsonList() {
		return IdentityJsonList;
	}

	public void setIdentityJsonList(String identityJsonList) {
		IdentityJsonList = identityJsonList;
	}

	public String getIdentityFailJsonList() {
		return IdentityFailJsonList;
	}

	public void setIdentityFailJsonList(String identityFailJsonList) {
		IdentityFailJsonList = identityFailJsonList;
	}

	public String getRealName2() {
		return RealName2;
	}

	public void setRealName2(String realName2) {
		RealName2 = realName2;
	}

	public String getIdentificationNo2() {
		return IdentificationNo2;
	}

	public void setIdentificationNo2(String identificationNo2) {
		IdentificationNo2 = identificationNo2;
	}
}
