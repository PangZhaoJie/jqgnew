package com.jqg.struts;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.DateFormat;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import sun.org.mozilla.javascript.internal.ContextAction;

import com.jqg.pojo.Basicinfor;
import com.jqg.pojo.Certification;
import com.jqg.pojo.Creditlevel;
import com.jqg.pojo.Inbox;
import com.jqg.pojo.Integralcategory;
import com.jqg.pojo.Integraldetail;
import com.jqg.pojo.Integralparameter;
import com.jqg.pojo.Manager;
import com.jqg.pojo.Moneycount;
import com.jqg.pojo.Moneyhistorydetail;
import com.jqg.pojo.Oerationlog;
import com.jqg.pojo.Requestquota;
import com.jqg.pojo.Systemconf;
import com.jqg.pojo.Uservip;
import com.jqg.pojo.Website;
import com.jqg.service.BasicinforService;
import com.jqg.service.CertificationService;
import com.jqg.service.CreditlevelService;
import com.jqg.service.InboxService;
import com.jqg.service.IntegralcategoryService;
import com.jqg.service.IntegraldetailService;
import com.jqg.service.IntegralparameterService;
import com.jqg.service.ManagerService;
import com.jqg.service.MoneycountService;
import com.jqg.service.MoneyhistorydetailService;
import com.jqg.service.OerationlogService;
import com.jqg.service.RequestquotaService;
import com.jqg.service.SystemconfService;
import com.jqg.service.UservipService;
import com.jqg.service.WebsiteService;
import com.jqg.service.impl.BasicinforServiceImpl;
import com.jqg.service.impl.CertificationServiceImpl;
import com.jqg.service.impl.CreditlevelServiceImpl;
import com.jqg.service.impl.InboxServiceImpl;
import com.jqg.service.impl.IntegralcategoryServiceImpl;
import com.jqg.service.impl.IntegraldetailServiceImpl;
import com.jqg.service.impl.IntegralparameterServiceImpl;
import com.jqg.service.impl.ManagerServiceImpl;
import com.jqg.service.impl.MoneycountServiceImpl;
import com.jqg.service.impl.MoneyhistorydetailServiceImpl;
import com.jqg.service.impl.OerationlogServiceImpl;
import com.jqg.service.impl.RequestquotaServiceImpl;
import com.jqg.service.impl.SystemconfServiceImpl;
import com.jqg.service.impl.UservipServiceImpl;
import com.jqg.service.impl.WebsiteServiceImpl;
import com.jqg.util.ExcelDownload;
import com.jqg.util.LoanUtils;
import com.jqg.util.MD5Util;
import com.opensymphony.xwork2.ActionContext;

public class UserMangerAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private int currentPage;
	private int pageSize = 10;
	private int totalPage;
	private String username;
	private String starttime;
	private String endtime;
	private String ziduan;
	private String userId;
	private String password;
	private String paypassword;
	private String ip;
	private String amoney;
	private String message;
	private String radio;
	private String quotaId;
	private int userType;
	private String managerName;
	private String vipRadio;
	private String urlstr;
	private IntegralparameterService integralparameterService = new IntegralparameterServiceImpl();
	private CreditlevelService creditlevelService = new CreditlevelServiceImpl();
	private ManagerService managerService = new ManagerServiceImpl();
	private IntegralcategoryService integralcategoryService = new IntegralcategoryServiceImpl();
	private IntegraldetailService integraldetailService = new IntegraldetailServiceImpl();

	UservipService uservipService = new UservipServiceImpl();
	BasicinforService basicinforService = new BasicinforServiceImpl();
	MoneycountService moneycountService = new MoneycountServiceImpl();
	Manager manager = (Manager) ActionContext.getContext().getSession()
			.get("manager");
	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	OerationlogService oerationlogService = new OerationlogServiceImpl();
	InboxService inboxService = new InboxServiceImpl();
	MoneyhistorydetailService moneyhistorydetailService = new MoneyhistorydetailServiceImpl();
	RequestquotaService requestquotaService = new RequestquotaServiceImpl();
	CertificationService certificationService = new CertificationServiceImpl();
	WebsiteService websiteService = new WebsiteServiceImpl();
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String postform;

	public String getPostform() {
		return postform;
	}

	public void setPostform(String postform) {
		this.postform = postform;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	/**
	 * 实名认证
	 * 
	 * @return
	 * @throws Exception
	 */
	public String realname() throws Exception {
		Website website = this.websiteService.findWebsiteBywebsiteId(1);
		Uservip uservip = this.uservipService.findUservipByUserid(Integer
				.valueOf(this.userId).intValue());
		Certification certification = this.certificationService
				.findCertificationByCertificationId(Integer.valueOf(
						this.quotaId).intValue());
		Basicinfor basicinfor = this.basicinforService
				.findBasicinforByUserId(Integer.valueOf(this.userId).intValue());
		if ("2".equals(uservip.getNameResult())) {
			return "success";
		}
		if ("2".equals(this.radio)) {
			HttpServletRequest request = ServletActionContext.getRequest();
			LoanUtils loanutil = new LoanUtils();
			loanutil.nameaudit(website, certification, request);
		}
		if ("0".equals(this.radio)) {
			uservip.setNameResult(this.radio);

			boolean flag1 = this.uservipService.updateUservip(uservip);
			certification.setExamineStatus(Integer.valueOf(2));
		}
		// 调用认证奖励

		boolean flag3 = this.certificationService
				.updateCertification(certification);

		Oerationlog oerationlog = new Oerationlog();
		oerationlog.setManager(this.manager);
		oerationlog.setOerationCategory("realname");
		oerationlog.setOerationChangeTime(this.timestamp);
		oerationlog.setOerationRemaks("审核实名认证");
		oerationlog.setOrationIp(this.ip);
		boolean flag1 = this.oerationlogService.createOerationlog(oerationlog);
		Inbox inbox = new Inbox();
		inbox.setContent(this.message);
		inbox.setReceiveTime(this.timestamp);
		inbox.setSendName(website.getWebName());
		inbox.setTitle("实名认证");
		inbox.setStatus(Integer.valueOf(0));
		inbox.setUservip(uservip);
		boolean flag2 = this.inboxService.createInbox(inbox);
		return "success";
	}

	/**
	 * 审核信用额度申请
	 * 
	 * @return
	 * @throws Exception
	 */
	public String quota() throws Exception {
		Website website = this.websiteService.findWebsiteBywebsiteId(1);
		Requestquota requestquota = this.requestquotaService
				.findRequestquotaByrequestQuotaId(Integer.valueOf(this.quotaId)
						.intValue());
		boolean flag1;
		boolean flag2;
		if ("1".equals(this.radio)) {
			requestquota.setOpinion(this.message);
			requestquota.setExamine(Integer.valueOf(this.radio));
			requestquota.setThroughQuota(Integer.valueOf(this.amoney));
			boolean flag = this.requestquotaService
					.updateRequestquota(requestquota);
			Uservip uservip = requestquota.getUservip();

			uservip.setQuota(Integer.valueOf(this.amoney));
			boolean flag3 = this.uservipService.updateUservip(uservip);

			Oerationlog oerationlog = new Oerationlog();
			oerationlog.setManager(this.manager);
			oerationlog.setOerationCategory("quota");
			oerationlog.setOerationChangeTime(this.timestamp);
			oerationlog.setOerationRemaks("审核信用额度申请");
			oerationlog.setOrationIp(this.ip);
			flag1 = this.oerationlogService.createOerationlog(oerationlog);
			Inbox inbox = new Inbox();
			inbox.setContent(this.message);
			inbox.setReceiveTime(this.timestamp);
			inbox.setSendName(this.manager.getManagerName());
			inbox.setTitle("信用额度申请成功");
			inbox.setStatus(Integer.valueOf(0));
			inbox.setUservip(uservip);
			flag2 = this.inboxService.createInbox(inbox);
		} else {
			requestquota.setOpinion(this.message);
			requestquota.setExamine(Integer.valueOf(this.radio));
			requestquota.setThroughQuota(Integer.valueOf(this.amoney));
			boolean flag = this.requestquotaService
					.updateRequestquota(requestquota);
			Oerationlog oerationlog = new Oerationlog();
			oerationlog.setManager(this.manager);
			oerationlog.setOerationCategory("quota");
			oerationlog.setOerationChangeTime(this.timestamp);
			oerationlog.setOerationRemaks("审核信用额度申请");
			oerationlog.setOrationIp(this.ip);
			flag1 = this.oerationlogService.createOerationlog(oerationlog);
			Inbox inbox = new Inbox();
			inbox.setContent(this.message);
			inbox.setReceiveTime(this.timestamp);
			inbox.setSendName(website.getWebName());
			inbox.setTitle("信用额度申请失败");
			inbox.setStatus(Integer.valueOf(0));
			inbox.setUservip(requestquota.getUservip());
			flag1 = this.inboxService.createInbox(inbox);
		}
		return "success";
	}

	/**
	 * 查找待审核实名认证的客户
	 * 
	 * @throws Exception
	 */
	public void findcertification() throws Exception {
		String sql = "SELECT * FROM certification c WHERE c.ExamineStatus = 0";
		List certifications = this.certificationService
				.findCertificationsBysql(sql);
		int totalRecord = certifications.size();
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
		String sqls = sql + " ORDER BY c.certificationId DESC LIMIT "
				+ (this.currentPage - 1) * this.pageSize + "," + this.pageSize;
		List<Certification> certifications1 = this.certificationService
				.findCertificationsBysql(sqls);
		StringBuffer str = new StringBuffer();
		str.append("{\"totalPage\":\"" + this.totalPage + "\",");
		str.append("\"currentPage\":\"" + this.currentPage + "\",");
		str.append("\"jsonRoot\":[");
		for (Certification certification : certifications1) {
			str.append("{\"id\":\"" + certification.getUservip().getUserId()
					+ "\",");
			str.append("\"cid\":\"" + certification.getCertificationId()
					+ "\",");
			str.append("\"name\":\"" + certification.getUservip().getUserName()
					+ "\",");
			str.append("\"realname\":\"" + certification.getRealName() + "\",");
			str.append("\"idnum\":\"" + certification.getIdNum() + "\",");
			str.append("\"fimage\":\"" + certification.getFrontImage() + "\",");
			str.append("\"nimage\":\"" + certification.getNegativeImage()
					+ "\",");
			str.append("\"time\":\"" + certification.getUpLoadTime() + "\"},");
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
	 * 查找待审核额度申请的用户
	 * 
	 * @throws Exception
	 */
	public void findquota() throws Exception {
		String sql = "SELECT * FROM requestquota r WHERE r.examine = 0";
		List requestquotas = this.requestquotaService
				.findRequestquotasByUserIdPage(sql);
		int totalRecord = requestquotas.size();
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
		String sqls = sql + " ORDER BY r.requestQuotaId DESC LIMIT "
				+ (this.currentPage - 1) * this.pageSize + "," + this.pageSize;

		List<Requestquota> requestquotas1 = this.requestquotaService
				.findRequestquotasByUserIdPage(sqls);
		StringBuffer str = new StringBuffer();
		str.append("{\"totalPage\":\"" + this.totalPage + "\",");
		str.append("\"currentPage\":\"" + this.currentPage + "\",");
		str.append("\"jsonRoot\":[");

		for (Requestquota requestquota : requestquotas1) {
			String a = "";
			String b = "";
			String c = "";
			String d = "";
			String f = "";
			if (requestquota.getUservip().getEnable() == null) {
				a = "未认证";
			} else if (requestquota.getUservip().getEnable().intValue() == 1) {
				a = "已认证";
			} else {
				a = "未认证";
			}

			if ((requestquota.getUservip().getNameResult() == null)
					|| (requestquota.getUservip().getNameResult().length() <= 0)) {
				b = "未认证";
			} else if ("2".equals(requestquota.getUservip().getNameResult())) {
				b = "已认证";
			} else {
				b = "未认证";
			}
			if ((requestquota.getUservip().getPhoneResult() == null)
					|| (requestquota.getUservip().getPhoneResult().length() <= 0)) {
				c = "未认证";
			} else if ("1".equals(requestquota.getUservip().getPhoneResult())) {
				c = "已认证";
			} else {
				c = "未认证";
			}
			if ((requestquota.getUservip().getVideoResult() == null)
					|| (requestquota.getUservip().getVideoResult().length() <= 0)) {
				d = "未认证";
			} else if ("1".equals(requestquota.getUservip().getVideoResult())) {
				d = "已认证";
			} else {
				d = "未认证";
			}
			if ((requestquota.getUservip().getSceneResult() == null)
					|| (requestquota.getUservip().getSceneResult().length() <= 0)) {
				f = "未认证";
			} else if ("1".equals(requestquota.getUservip().getSceneResult())) {
				f = "已认证";
			} else {
				f = "未认证";
			}

			str.append("{\"id\":\"" + requestquota.getRequestQuotaId() + "\",");
			str.append("\"name\":\"" + requestquota.getUservip().getUserName()
					+ "\",");
			str.append("\"enable\":\"" + a + "\",");
			str.append("\"nameresult\":\"" + b + "\",");
			str.append("\"phoneresult\":\"" + c + "\",");
			str.append("\"vodepresult\":\"" + d + "\",");
			str.append("\"sceneresult\":\"" + f + "\",");
			str.append("\"time\":\"" + requestquota.getSubmitTime() + "\"},");
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
	 * 现场认证
	 * 
	 * @return
	 * @throws Exception
	 */
	public String scene() throws Exception {
		Website website = this.websiteService.findWebsiteBywebsiteId(1);
		Uservip uservip = this.uservipService.findUservipByUserid(Integer
				.valueOf(this.userId).intValue());
		if ("1".equals(uservip.getSceneResult())) {
			return "success";
		}
		uservip.setSceneResult(this.radio);
		if ("1".equals(this.radio)) {
			Integralparameter integralparameter = this.integralparameterService
					.findIntegralparameterByintegralPid(4);

			// 现场认证通过保存积分到积分表
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
		}

		// 调用认证奖励
		this.authentic_reward(uservip);

		boolean flag = this.uservipService.updateUservip(uservip);
		Oerationlog oerationlog = new Oerationlog();
		oerationlog.setManager(this.manager);
		oerationlog.setOerationCategory("scene");
		oerationlog.setOerationChangeTime(this.timestamp);
		oerationlog.setOerationRemaks("审核现场认证");
		oerationlog.setOrationIp(this.ip);
		boolean flag1 = this.oerationlogService.createOerationlog(oerationlog);
		Inbox inbox = new Inbox();
		inbox.setContent(this.message);
		inbox.setReceiveTime(this.timestamp);
		inbox.setSendName(website.getWebName());
		inbox.setTitle("现场认证");
		inbox.setStatus(Integer.valueOf(0));
		inbox.setUservip(uservip);
		boolean flag2 = this.inboxService.createInbox(inbox);
		return "success";
	}

	/**
	 * 视频认证
	 * 
	 * @return
	 * @throws Exception
	 */
	public String vidio() throws Exception {
		Website website = this.websiteService.findWebsiteBywebsiteId(1);
		Uservip uservip = this.uservipService.findUservipByUserid(Integer
				.valueOf(this.userId).intValue());

		if ("1".equals(uservip.getVideoResult())) {
			return "success";
		}

		uservip.setVideoResult(this.radio);
		if ("1".equals(this.radio)) {
			Integralparameter integralparameter = this.integralparameterService
					.findIntegralparameterByintegralPid(3);
			// 视频认证通过保存积分到积分表
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
		}
		boolean flag = this.uservipService.updateUservip(uservip);

		// 调用认证奖励
		this.authentic_reward(uservip);

		Oerationlog oerationlog = new Oerationlog();
		oerationlog.setManager(this.manager);
		oerationlog.setOerationCategory("vidio");
		oerationlog.setOerationChangeTime(this.timestamp);
		oerationlog.setOerationRemaks("审核视频认证");
		oerationlog.setOrationIp(this.ip);
		boolean flag1 = this.oerationlogService.createOerationlog(oerationlog);
		Inbox inbox = new Inbox();
		inbox.setContent(this.message);
		inbox.setReceiveTime(this.timestamp);
		inbox.setSendName(website.getWebName());
		inbox.setTitle("视频认证");
		inbox.setStatus(Integer.valueOf(0));
		inbox.setUservip(uservip);
		boolean flag2 = this.inboxService.createInbox(inbox);
		return "success";
	}

	/**
	 * 查找所有用户
	 * 
	 * @throws Exception
	 */
	public void findUsersByvidio() throws Exception {
		String sql = "SELECT * FROM uservip u WHERE u." + this.ziduan
				+ "  is NOT NULL" + "  and  u." + this.ziduan + "=0";

		List uservips = this.uservipService.findUservipsBysql(sql);

		int totalRecord = uservips.size();
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
		String sqls = sql + " ORDER BY u.userId DESC LIMIT "
				+ (this.currentPage - 1) * this.pageSize + "," + this.pageSize;
		List<Uservip> uservips1 = this.uservipService.findUservipsBysql(sqls);
		StringBuffer str = new StringBuffer();
		str.append("{\"totalPage\":\"" + this.totalPage + "\",");
		str.append("\"currentPage\":\"" + this.currentPage + "\",");
		str.append("\"jsonRoot\":[");
		for (Uservip uservip : uservips1) {
			String a = "";
			String b = "";

			if (uservip.getNameResult() == null) {
				a = "无";
			} else if (!Character.valueOf('2').equals(uservip.getNameResult())) {
				a = "无";
			} else {
				a = uservip.getRealName();
			}
			if (uservip.getUservip() == null) {
				b = "无推荐人";
			} else {
				b = uservip.getUservip().getUserName();
			}
			Moneycount moneycount = this.moneycountService
					.findMoneycountByuserId(uservip.getUserId().intValue());

			str.append("{\"id\":\"" + uservip.getUserId() + "\",");
			str.append("\"name\":\"" + uservip.getUserName() + "\",");
			str.append("\"userType\":\"" + uservip.getUserType() + "\",");
			str.append("\"realname\":\"" + a + "\",");
			str.append("\"recomname\":\"" + b + "\",");
			str.append("\"amoney\":\"" + moneycount.getAvailableMoney() + "\",");
			str.append("\"fmoney\":\"" + moneycount.getFrozenMoney() + "\",");
			str.append("\"dmoney\":\"" + moneycount.getCollectTotalMoney() + "\",");
			str.append("\"time\":\"" + uservip.getRegisterDate() + "\"},");
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
	 * 查找vip会员所有用户
	 * 
	 * @throws Exception
	 */
	public void findUsersByVip() throws Exception {
		String sql = "SELECT * FROM uservip u WHERE u." + this.ziduan
				+ "  is NOT NULL" + "  and  u." + this.ziduan + "=1";

		List uservips = this.uservipService.findUservipsBysql(sql);

		int totalRecord = uservips.size();
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
		String sqls = sql + " ORDER BY u.userId DESC LIMIT "
				+ (this.currentPage - 1) * this.pageSize + "," + this.pageSize;
		List<Uservip> uservips1 = this.uservipService.findUservipsBysql(sqls);
		StringBuffer str = new StringBuffer();
		str.append("{\"totalPage\":\"" + this.totalPage + "\",");
		str.append("\"currentPage\":\"" + this.currentPage + "\",");
		str.append("\"jsonRoot\":[");
		for (Uservip uservip : uservips1) {
			String a = "";
			String b = "";

			if (uservip.getNameResult() == null) {
				a = "无";
			} else if (!Character.valueOf('2').equals(uservip.getNameResult())) {
				a = "无";
			} else {
				a = uservip.getRealName();
			}
			if (uservip.getUservip() == null) {
				b = "无推荐人";
			} else {
				b = uservip.getUservip().getUserName();
			}
			Moneycount moneycount = this.moneycountService
					.findMoneycountByuserId(uservip.getUserId().intValue());

			str.append("{\"id\":\"" + uservip.getUserId() + "\",");
			str.append("\"name\":\"" + uservip.getUserName() + "\",");// 用户名
			str.append("\"userType\":\"" + uservip.getUserType() + "\",");
			str.append("\"realname\":\"" + a + "\",");// 真实姓名
			str.append("\"recomname\":\"" + b + "\",");// 推荐人
			str.append("\"amoney\":\"" + moneycount.getAvailableMoney() + "\",");// 可用余额
			str.append("\"fmoney\":\"" + moneycount.getFrozenMoney() + "\",");// 冻结金额
			str.append("\"dmoney\":\"" + moneycount.getCollectTotalMoney() + "\",");// 待收金额
			str.append("\"time\":\"" + uservip.getVipMonthe() + "\"},");// vip会员使用期限
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
	 * vip会员认证
	 * 
	 * @return
	 * @throws Exception
	 */
	public String vipMember() throws Exception {
		Website website = this.websiteService.findWebsiteBywebsiteId(1);
		Uservip uservip = this.uservipService.findUservipByUserid(Integer
				.valueOf(this.userId).intValue());
		if ("1".equals(uservip.getIsVIP())) {
			return "success";
		}
		SystemconfService systemconfService = new SystemconfServiceImpl();
		Calendar calender = Calendar.getInstance();
		if ("1".equals(this.radio)) {
			Systemconf latefee1 = systemconfService
					.findSystemconfByParname("con_vip_buy");
			Moneycount moneycount = this.moneycountService
					.findMoneycountByuserId(uservip.getUserId());
			if (moneycount.getAvailableMoney() < Double.valueOf(latefee1
					.getParvalue())) {
				this.setMessage("余额不足，请及时充值！", "/back/3_vipList.jsp", "3");
				return "jump";
			}
			Double availableMoney = moneycount.getAvailableMoney()
					- Double.valueOf(latefee1.getParvalue());
			moneycount.setAvailableMoney(availableMoney);
			this.moneycountService.updateMoneycount(moneycount);

			Moneyhistorydetail moneyhistorydetail = new Moneyhistorydetail();
			moneyhistorydetail.setAffectMoney(Double.valueOf(latefee1
					.getParvalue()));// 影响金额
			moneyhistorydetail.setAvailableBalance(availableMoney);// 可用余额
			moneyhistorydetail
					.setOccurTime(new Timestamp(new Date().getTime()));
			moneyhistorydetail.setIntroduction("申请vip会员");
			moneyhistorydetail.setUservip(uservip);
			this.moneyhistorydetailService
					.createMoneyhistorydetail(moneyhistorydetail);
			if (uservip.getVipEndTime() != null
					&& !"".equals(uservip.getVipEndTime())) {
				calender.setTime(uservip.getVipEndTime());
			} else {
				calender.setTime(new Date());
			}
			calender.add(Calendar.MONTH,
					Integer.valueOf(uservip.getVipMonthe()));
			long end = calender.getTimeInMillis();
			Timestamp scurrtest = new Timestamp(end);
			uservip.setVipEndTime(scurrtest);
			uservip.setIsVIP(Integer.valueOf(this.radio));
		} else {
			if (uservip.getIsVIP() != null) {
				uservip.setVipEndTime(uservip.getVipEndTime());
				uservip.setIsVIP(Integer.valueOf(uservip.getIsVIP()));
			}
		}
		uservip.setIsApplyVIP(Integer.valueOf(0));
		this.uservipService.updateUservip(uservip);

		Oerationlog oerationlog = new Oerationlog();
		oerationlog.setManager(this.manager);
		oerationlog.setOerationCategory("scene");
		oerationlog.setOerationChangeTime(this.timestamp);
		oerationlog.setOerationRemaks("审核vip会员");
		oerationlog.setOrationIp(this.ip);
		this.oerationlogService.createOerationlog(oerationlog);
		Inbox inbox = new Inbox();
		inbox.setContent(this.message);
		inbox.setReceiveTime(this.timestamp);
		inbox.setSendName(website.getWebName());
		inbox.setTitle("vip会员申请");
		inbox.setStatus(Integer.valueOf(0));
		inbox.setUservip(uservip);
		this.inboxService.createInbox(inbox);
		return "success";
	}

	/**
	 * 修改信用额度
	 * 
	 * @return
	 * @throws Exception
	 */
	public String uptcredit() throws Exception {
		Website website = this.websiteService.findWebsiteBywebsiteId(1);
		Uservip uservip = this.uservipService.findUservipByUserid(Integer
				.valueOf(this.userId).intValue());
		if (uservip.getQuota() != null) {
			uservip.setQuota(Integer.valueOf(Integer.valueOf(this.amoney)
					.intValue() + uservip.getQuota().intValue()));
		} else {
			uservip.setQuota(Integer.valueOf(this.amoney));
		}
		if (this.amoney != null) {
			boolean flag = this.uservipService.updateUservip(uservip);
			Oerationlog oerationlog = new Oerationlog();
			oerationlog.setManager(this.manager);
			oerationlog.setOerationCategory("uptcredit");
			oerationlog.setOerationChangeTime(this.timestamp);
			oerationlog.setOerationRemaks("修改用户信用额度");
			oerationlog.setOrationIp(this.ip);
			boolean flag1 = this.oerationlogService
					.createOerationlog(oerationlog);
			Inbox inbox = new Inbox();
			inbox.setContent(this.message);
			inbox.setReceiveTime(this.timestamp);
			inbox.setSendName(website.getWebName());
			inbox.setTitle("修改信用额度");

			inbox.setStatus(Integer.valueOf(0));
			inbox.setUservip(uservip);
			boolean bool1 = this.inboxService.createInbox(inbox);
		}

		return "success";
	}

	/**
	 * 修改用户余额
	 * 
	 * @return
	 * @throws Exception
	 */
	public String uptmoney() throws Exception {
		if(this.amoney!=null && Double.valueOf(this.amoney).doubleValue()!=0){
			this.request = ServletActionContext.getRequest();
			Uservip uservip = this.uservipService.findUservipByUserid(Integer
					.valueOf(this.userId).intValue());
			if(uservip.getTrustStatus()!=1){
				this.setMessage("用户还没有开通托管，请先开通托管再操作资金", "/back/3_list.jsp", "3");
				return "jump";
			}else{
				Website website = this.websiteService.findWebsiteBywebsiteId(1);
				LoanUtils loanUtils = new LoanUtils();
				this.postform = loanUtils.plat_transfer(website, uservip, Double.valueOf(this.amoney).doubleValue(), this.message, request);
				return "loanjump";
			}			
		}
		return "success";
		
	}

	/**
	 * 修改用户密码
	 * 
	 * @return
	 * @throws Exception
	 */
	public String uptpwd() throws Exception {
		this.request = ServletActionContext.getRequest();
		String basicInforId = request.getParameter("basicInforId");
		String realName = request.getParameter("realName");
		String idNum = request.getParameter("idNum");
		String phoneNum = request.getParameter("phoneNum");
		String mail = request.getParameter("mail");

		Basicinfor bas = basicinforService.findBasicinforByBasicInforId(Integer
				.parseInt(basicInforId));
		bas.setRealName(realName);
		bas.setPhoneNum(phoneNum);
		bas.setIdNum(idNum);
		boolean b = basicinforService.updateBasicinfor(bas);

		Website website = this.websiteService.findWebsiteBywebsiteId(1);
		Uservip uservip = this.uservipService.findUservipByUserid(Integer
				.valueOf(this.userId).intValue());
		uservip.setUserType(Integer.valueOf(this.userType));
		boolean bool1 = false;
		uservip.setMail(mail);
		uservip.setRealName(realName);
		if (!"".equals(this.password)) {
			uservip.setPassword(MD5Util.md5(this.password));

			boolean flag = this.uservipService.updateUservip(uservip);
			if (flag) {
				Oerationlog oerationlog = new Oerationlog();
				oerationlog.setManager(this.manager);
				oerationlog.setOerationCategory("uptpwd");
				oerationlog.setOerationChangeTime(this.timestamp);
				oerationlog.setOerationRemaks("修改用户密码");
				oerationlog.setOrationIp(this.ip);
				boolean flag1 = this.oerationlogService
						.createOerationlog(oerationlog);
				Inbox inbox = new Inbox();
				inbox.setContent(this.message);
				inbox.setReceiveTime(this.timestamp);
				inbox.setSendName(website.getWebName());
				inbox.setTitle("修改登录密码");
				inbox.setContent("管理员修改登录密码为" + this.password);
				;
				inbox.setStatus(Integer.valueOf(0));
				inbox.setUservip(uservip);
				bool1 = this.inboxService.createInbox(inbox);
			}
		}
		if (!"".equals(this.paypassword)) {
			uservip.setPayPwd(MD5Util.md5(this.paypassword));
			boolean flag2 = this.uservipService.updateUservip(uservip);
			if (flag2) {
				Oerationlog oerationlog = new Oerationlog();
				oerationlog.setManager(this.manager);
				oerationlog.setOerationCategory("uptpwd");
				oerationlog.setOerationChangeTime(this.timestamp);
				oerationlog.setOerationRemaks("修改用户支付密码");
				oerationlog.setOrationIp(this.ip);
				boolean flag3 = this.oerationlogService
						.createOerationlog(oerationlog);
				Inbox inbox = new Inbox();
				inbox.setContent(this.message);
				inbox.setReceiveTime(this.timestamp);
				inbox.setSendName(website.getWebName());
				inbox.setTitle("修改支付密码");

				inbox.setContent("管理员修改支付为" + this.paypassword);
				;
				inbox.setStatus(Integer.valueOf(0));
				inbox.setUservip(uservip);
				bool1 = this.inboxService.createInbox(inbox);
			}
		}
		if ("".equals(this.paypassword) && "".equals(this.password)) {
			boolean flag = this.uservipService.updateUservip(uservip);
			if (flag) {
				Oerationlog oerationlog = new Oerationlog();
				oerationlog.setManager(this.manager);
				oerationlog.setOerationCategory("用户信息");
				oerationlog.setOerationChangeTime(this.timestamp);
				oerationlog.setOerationRemaks("修改用户信息");
				oerationlog.setOrationIp(this.ip);
				boolean flag3 = this.oerationlogService
						.createOerationlog(oerationlog);
				Inbox inbox = new Inbox();
				inbox.setContent(this.message);
				inbox.setReceiveTime(this.timestamp);
				inbox.setSendName(website.getWebName());
				inbox.setTitle("修改用户信息");

				inbox.setContent("管理员修改用户信息");
				;
				inbox.setStatus(Integer.valueOf(0));
				inbox.setUservip(uservip);
				bool1 = this.inboxService.createInbox(inbox);
			}

		}
		if (!bool1) {
			boolean flag = this.uservipService.updateUservip(uservip);
			if (flag) {
				Oerationlog oerationlog = new Oerationlog();
				oerationlog.setManager(this.manager);
				oerationlog.setOerationCategory("uptusertype");
				oerationlog.setOerationChangeTime(this.timestamp);
				oerationlog.setOerationRemaks("修改用户类型是否内部投标人");
				oerationlog.setOrationIp(this.ip);
				boolean flag1 = this.oerationlogService
						.createOerationlog(oerationlog);
				Inbox inbox = new Inbox();
				inbox.setContent(this.message);
				inbox.setReceiveTime(this.timestamp);
				inbox.setSendName(website.getWebName());
				inbox.setTitle("修改用户类型是否内部投标人");
				if (this.userType == 1) {
					inbox.setContent("管理员编辑会员类型为内部投标人");
					;
				} else {
					inbox.setContent("管理员编辑会员类型为非内部投标人");
					;
				}

				inbox.setStatus(Integer.valueOf(0));
				inbox.setUservip(uservip);
				bool1 = this.inboxService.createInbox(inbox);
			}
		}

		return "success";
	}

	/**
	 * 查找为进行邮箱验证的用户
	 * 
	 * @throws Exception
	 */
	public void findUsersByemail() throws Exception {
		URLDecoder.decode(this.username, "UTF-8");

		String sql = "SELECT * FROM uservip u WHERE ISNULL(u." + this.ziduan
				+ ") ";
		if ((!"".equals(URLDecoder.decode(this.username, "UTF-8")))
				&& (URLDecoder.decode(this.username, "UTF-8") != null)) {
			sql = sql + " AND  u.userName = '"
					+ URLDecoder.decode(this.username, "UTF-8") + "'";
		}

		if ((!"".equals(this.starttime)) && (this.starttime != null)
				&& (!"-请选择时间-".equals(this.starttime))) {
			sql = sql + " AND u.registerDate > '" + this.starttime
					+ " 00:00:00'";
		}
		if ((!"".equals(this.endtime)) && (this.endtime != null)
				&& (!"-请选择时间-".equals(this.starttime))) {
			sql = sql + " AND u.registerDate< '" + this.endtime + "23:59:59'";
		}

		List uservips = this.uservipService.findUservipsBysql(sql);

		int totalRecord = uservips.size();
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
		String sqls = sql + " ORDER BY u.userId DESC LIMIT "
				+ (this.currentPage - 1) * this.pageSize + "," + this.pageSize;
		List<Uservip> uservips1 = this.uservipService.findUservipsBysql(sqls);

		StringBuffer str = new StringBuffer();
		str.append("{\"totalPage\":\"" + this.totalPage + "\",");
		str.append("\"currentPage\":\"" + this.currentPage + "\",");
		str.append("\"jsonRoot\":[");
		for (Uservip uservip : uservips1) {
			String a = "";
			String b = "";

			if (uservip.getNameResult() == null) {
				a = "无";
			} else if (!Character.valueOf('2').equals(uservip.getNameResult())) {
				a = "无";
			} else {
				a = uservip.getRealName();
			}
			if (uservip.getUservip() == null) {
				b = "无推荐人";
			} else {
				b = uservip.getUserName();
			}

			Moneycount moneycount = this.moneycountService
					.findMoneycountByuserId(uservip.getUserId().intValue());

			str.append("{\"id\":\"" + uservip.getUserId() + "\",");
			str.append("\"name\":\"" + uservip.getUserName() + "\",");
			str.append("\"userType\":\"" + uservip.getUserType() + "\",");
			str.append("\"realname\":\"" + a + "\",");
			str.append("\"recomname\":\"" + b + "\",");
			str.append("\"amoney\":\"" + moneycount.getAvailableMoney() + "\",");
			str.append("\"fmoney\":\"" + moneycount.getFrozenMoney() + "\",");
			str.append("\"dmoney\":\"" + moneycount.getCollectTotalMoney() + "\",");
			str.append("\"time\":\"" + uservip.getRegisterDate() + "\"},");
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
	 * 根据条件搜索用户
	 * 
	 * @throws Exception
	 */
	public void searchUsers() throws Exception {
		URLDecoder.decode(this.username, "UTF-8");

		String sql = "SELECT * FROM uservip u WHERE 1=1 ";
		if ((!"".equals(URLDecoder.decode(this.username, "UTF-8")))
				&& (URLDecoder.decode(this.username, "UTF-8") != null)) {
			sql = sql + " AND  u.userName = '"
					+ URLDecoder.decode(this.username, "UTF-8") + "'";
		}
		if (this.managerName != null && !"".equals(this.managerName)) {
			String name = new String(this.managerName.getBytes("ISO-8859-1"),
					"UTF-8");
			if (!"".equals(name) && name != null) {
				Manager manager = this.managerService.findManagerByname(name);
				if(manager!=null){
					sql = sql + "AND u.managerId = '" + manager.getManagerId()
							+ "'";
				}
			}
		}
		if (!"".equals(this.vipRadio) && this.vipRadio != null) {
			sql = sql + " AND u.isVIP = " + Integer.valueOf(this.vipRadio);
		}

		if ((!this.starttime.equals("")) && (this.starttime != null)
				&& (!"-请选择时间-".equals(this.starttime))) {
			sql = sql + " AND u.registerDate > '" + this.starttime
					+ " 00:00:00'";
		}
		if ((!"".equals(this.endtime)) && (this.endtime != null)
				&& (!"-请选择时间-".equals(this.starttime))) {
			sql = sql + " AND u.registerDate< '" + this.endtime + "23:59:59'";
		}

		List uservips = this.uservipService.findUservipsBysql(sql);

		int totalRecord = uservips.size();
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
		String sqls = sql + " ORDER BY u.userId DESC LIMIT "
				+ (this.currentPage - 1) * this.pageSize + "," + this.pageSize;
		List<Uservip> uservips1 = this.uservipService.findUservipsBysql(sqls);
		StringBuffer str = new StringBuffer();
		str.append("{\"totalPage\":\"" + this.totalPage + "\",");
		str.append("\"currentPage\":\"" + this.currentPage + "\",");
		str.append("\"jsonRoot\":[");
		for (Uservip uservip : uservips1) {
			String a = "";
			String b = "";
			String c = "";

			if (uservip.getNameResult() == null) {
				a = "未认证";
			} else if ("2".equals(uservip.getNameResult())) {
				a = uservip.getRealName();
			} else {
				a = "未认证";
			}
			if (uservip.getUservip() == null) {
				b = "无推荐人";
			} else {
				b = uservip.getUservip().getUserName();
			}

			if (uservip.getUserType() == null) {
				c = "否";
			} else if (uservip.getUserType().intValue() == 1) {
				c = "是";
			} else {
				c = "否";
			}
			Moneycount moneycount = this.moneycountService
					.findMoneycountByuserId(uservip.getUserId().intValue());
			Certification certifications = certificationService
					.findCertificationByUserId(uservip.getUserId().intValue());

			str.append("{\"id\":\"" + uservip.getUserId() + "\",");
			str.append("\"name\":\"" + uservip.getUserName() + "\",");
			str.append("\"userType\":\"" + uservip.getUserType() + "\",");
			str.append("\"realname\":\"" + a + "\",");
			str.append("\"recomname\":\"" + b + "\",");
			str.append("\"userType\":\"" + c + "\",");
			str.append("\"question\":\"" + uservip.getQuota() + "\",");
			str.append("\"amoney\":\"" + moneycount.getAvailableMoney() + "\",");
			str.append("\"fmoney\":\"" + moneycount.getFrozenMoney() + "\",");
			str.append("\"dmoney\":\"" + moneycount.getCollectTotalMoney() + "\",");
			if (uservip.getManager() != null
					&& !"".equals(uservip.getManager())) {
				Manager manager = this.managerService
						.findManagerByManagerId(uservip.getManager()
								.getManagerId());
				str.append("\"managerName\":\"" + manager.getManagerName()
						+ "\",");
			} else {
				str.append("\"managerName\":\"\",");
			}
			if (certifications == null) {
				str.append("\"idnum\":\"\",");// 身份证号
				str.append("\"fimage\":\"\",");// 身份证正面
				str.append("\"nimage\":\"\",");// 身份证反面
			} else {
				str.append("\"idnum\":\"" + certifications.getIdNum() + "\",");// 身份证号
				str.append("\"fimage\":\"" + certifications.getFrontImage()
						+ "\",");// 身份证正面
				str.append("\"nimage\":\"" + certifications.getNegativeImage()
						+ "\",");// 身份证反面
			}
			str.append("\"time\":\"" + uservip.getRegisterDate() + "\"},");
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

	public void findUsers() throws Exception {
		List uservips = this.uservipService.findUservips();

		int totalRecord = uservips.size();
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

		List<Uservip> uservips1 = this.uservipService.findUservipsPage(
				(this.currentPage - 1) * this.pageSize, this.pageSize);
		// Uservip uservip2 =new Uservip();
		// for(int i=0;i<uservips1.size();i++){
		// uservip2 =
		// this.uservipService.findUservipByUserid(uservips1.get(i).getUservip().getUserId());
		// System.err.println(uservips1.get(i).getUservip().getUserId());
		// }

		StringBuffer str = new StringBuffer();
		str.append("{\"totalPage\":\"" + this.totalPage + "\",");
		str.append("\"currentPage\":\"" + this.currentPage + "\",");
		str.append("\"jsonRoot\":[");
		for (Uservip uservip : uservips1) {
			String a = "";
			String b = "";
			String c = "";
			String isvip = "";
			// Uservip uservip2 =new Uservip();
			// uservip2 =
			// this.uservipService.findUservipByUserid(uservip.getUservip().getUserId());
			if (uservip.getNameResult() == null) {
				a = "未认证";
			} else if ("2".equals(uservip.getNameResult())) {
				a = uservip.getRealName();
			} else {
				a = "未认证";
			}
			if (uservip.getUservip() == null) {
				b = "无推荐人";
			} else {
				b = uservip.getUservip().getUserName();
			}
			if (uservip.getUserType() == null) {
				c = "否";
			} else if (uservip.getUserType().intValue() == 1) {
				c = "是";
			} else {
				c = "否";
			}
			if(uservip.getIsVIP() == null){
				isvip = "否";
			}else if(uservip.getIsVIP().intValue() == 1){
				isvip = "是";
			}else{
				isvip = "否";
			}
			Moneycount moneycount = this.moneycountService
					.findMoneycountByuserId(uservip.getUserId().intValue());
			Certification certifications = certificationService
					.findCertificationByUserId(uservip.getUserId().intValue());

			str.append("{\"id\":\"" + uservip.getUserId() + "\",");
			str.append("\"name\":\"" + uservip.getUserName() + "\",");
			str.append("\"userType\":\"" + uservip.getUserType() + "\",");
			str.append("\"realname\":\"" + a + "\",");
			str.append("\"recomname\":\"" + b + "\",");
			str.append("\"userType\":\"" + c + "\",");
			str.append("\"question\":\"" + uservip.getQuota() + "\",");
			str.append("\"amoney\":\"" + moneycount.getAvailableMoney() + "\",");
			str.append("\"fmoney\":\"" + moneycount.getFrozenMoney() + "\",");
			str.append("\"dmoney\":\"" + moneycount.getCollectTotalMoney() + "\",");
			str.append("\"isVip\":\"" + isvip + "\",");// 是否vip会员
			str.append("\"endTime\":\"" + uservip.getVipEndTime() + "\",");// vip到期使用时间
			if (uservip.getManager() != null
					&& !"".equals(uservip.getManager())) {
				Manager manager = this.managerService
						.findManagerByManagerId(uservip.getManager()
								.getManagerId());
				str.append("\"managerName\":\"" + manager.getManagerName()
						+ "\",");
			} else {
				str.append("\"managerName\":\"\",");
			}
			if (certifications == null || !"2".equals(uservip.getNameResult())) {
				str.append("\"idnum\":\"\",");// 身份证号
				str.append("\"fimage\":\"\",");// 身份证正面
				str.append("\"nimage\":\"\",");// 身份证反面
			} else {
				str.append("\"idnum\":\"" + certifications.getIdNum() + "\",");// 身份证号
				str.append("\"fimage\":\"" + certifications.getFrontImage()
						+ "\",");// 身份证正面
				str.append("\"nimage\":\"" + certifications.getNegativeImage()
						+ "\",");// 身份证反面
			}

			str.append("\"time\":\"" + uservip.getRegisterDate() + "\"},");
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
			SystemconfService systemconfService = new SystemconfServiceImpl();
			Systemconf sysconf = systemconfService
					.findSystemconfByParname("con_authentic_reward");
			if (sysconf != null && sysconf.getParvalue() != null
					&& !"".equals(sysconf)) {
				double authreward = Double.valueOf(sysconf.getParvalue());
				Moneycount moneycount = this.moneycountService
						.findMoneycountByuserId(uservip.getUserId());
				Moneyhistorydetail moneyhistorydetail = new Moneyhistorydetail();
				moneyhistorydetail.setAffectMoney(authreward);
				moneyhistorydetail.setAvailableBalance(moneycount
						.getAvailableMoney() + authreward);
				moneyhistorydetail.setCollectMoney(moneycount.getCollectTotalMoney());
				moneyhistorydetail.setOccurTime(new Timestamp(new Date()
						.getTime()));
				moneyhistorydetail.setFrozenMoney(moneycount.getFrozenMoney());
				moneyhistorydetail.setUservip(uservip);
				moneyhistorydetail.setIntroduction("所有认证成功，发放认证成功奖励");
				this.moneyhistorydetailService
						.createMoneyhistorydetail(moneyhistorydetail);
				moneycount.setAvailableMoney(moneycount.getAvailableMoney()
						+ authreward);
				moneycount.setTotalMoney(moneycount.getTotalMoney()
						+ authreward);
				this.moneycountService.updateMoneycount(moneycount);
			}
		}
	}

	// 会员EXCEL导出 开始
	public void findUsersExcel() throws Exception {
		this.request = ServletActionContext.getRequest();
		this.response = ServletActionContext.getResponse();
		List<Uservip> uservipsList = this.uservipService.findUservips();

		OutputStream os = new FileOutputStream(URLDecoder.decode(this.urlstr,
				"UTF-8"));

		WritableWorkbook workbook = Workbook.createWorkbook(os);
		WritableSheet sheet = workbook.createSheet("CreateExcel", 0);

		Label l = null;
		Number n = null;
		DateTime d = null;

		WritableFont headerFont = new WritableFont(WritableFont.ARIAL, 12,
				WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
				Colour.BLUE);
		WritableCellFormat headerFormat = new WritableCellFormat(headerFont);

		WritableFont titleFont = new WritableFont(WritableFont.ARIAL, 10,
				WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
				Colour.RED);
		WritableCellFormat titleFormat = new WritableCellFormat(titleFont);

		WritableFont detFont = new WritableFont(WritableFont.ARIAL, 10,
				WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
				Colour.BLACK);
		WritableCellFormat detFormat = new WritableCellFormat(detFont);

		NumberFormat nf = new NumberFormat("0.00000");
		WritableCellFormat priceFormat = new WritableCellFormat(detFont, nf);

		DateFormat df = new DateFormat("yyyy-MM-dd");
		WritableCellFormat dateFormat = new WritableCellFormat(detFont, df);

		l = new Label(0, 0, "会员信息", headerFormat);
		sheet.addCell(l);

		int column = 0;
		sheet.addCell(new Label(column++, 2, "ID", titleFormat));
		sheet.addCell(new Label(column++, 2, "用户名", titleFormat));
		sheet.addCell(new Label(column++, 2, "内部发标人", titleFormat));
		sheet.addCell(new Label(column++, 2, "真实姓名", titleFormat));
		sheet.addCell(new Label(column++, 2, "身份证号", titleFormat));
		sheet.addCell(new Label(column++, 2, "推荐人", titleFormat));
		sheet.addCell(new Label(column++, 2, "可用金额", titleFormat));
		sheet.addCell(new Label(column++, 2, "冻结金额", titleFormat));
		sheet.addCell(new Label(column++, 2, "待收金额", titleFormat));
		sheet.addCell(new Label(column++, 2, "信用额度", titleFormat));
		sheet.addCell(new Label(column++, 2, "专属客服", titleFormat));
		sheet.addCell(new Label(column++, 2, "是否vip", titleFormat));
		sheet.addCell(new Label(column++, 2, "vip到期时间", titleFormat));
		sheet.addCell(new Label(column++, 2, "注册时间", titleFormat));
		for (int i = 0; i < uservipsList.size(); i++) {
			Uservip uservip = uservipsList.get(i);
			column = 0;
			Moneycount moneycount = this.moneycountService
					.findMoneycountByuserId(uservip.getUserId().intValue());
			Certification certifications = certificationService
					.findCertificationByUserId(uservip.getUserId().intValue());
			sheet.addCell(new Number(column++, i + 3, uservip.getUserId()
					.intValue(), detFormat));
			sheet.addCell(new Label(column++, i + 3, uservip.getUserName(),
					detFormat));

			if (uservip.getUserType() == null) {
				sheet.addCell(new Label(column++, i + 3, "否", detFormat));
			} else {
				sheet.addCell(new Label(column++, i + 3, "是", detFormat));
			}

			if (uservip.getNameResult() == null) {
				sheet.addCell(new Label(column++, i + 3, "未认证", detFormat));
			} else if ("2".equals(uservip.getNameResult())) {
				sheet.addCell(new Label(column++, i + 3, uservip.getRealName(),
						detFormat));
			}else{
				sheet.addCell(new Label(column++, i + 3, "未认证", detFormat));
			}
			if (certifications == null || !"2".equals(uservip.getNameResult())) {
				sheet.addCell(new Label(column++, i + 3, "", detFormat));
			} else {
				sheet.addCell(new Label(column++, i + 3, certifications
						.getIdNum(), detFormat));
			}
			if (uservip.getUservip() == null) {
				sheet.addCell(new Label(column++, i + 3, "无推荐人", detFormat));
			} else {
				sheet.addCell(new Label(column++, i + 3, uservip.getUservip()
						.getUserName(), detFormat));
			}

			sheet.addCell(new Label(column++, i + 3, "" + uservip.getQuota(),
					detFormat));
			sheet.addCell(new Label(column++, i + 3, ""
					+ moneycount.getAvailableMoney(), detFormat));
			sheet.addCell(new Label(column++, i + 3, ""
					+ moneycount.getFrozenMoney(), detFormat));
			sheet.addCell(new Label(column++, i + 3, ""
					+ moneycount.getCollectTotalMoney(), detFormat));

			if (uservip.getManager() != null
					&& !"".equals(uservip.getManager())) {
				Manager manager = this.managerService
						.findManagerByManagerId(uservip.getManager()
								.getManagerId());
				sheet.addCell(new Label(column++, i + 3, manager
						.getManagerName(), detFormat));
			} else {
				sheet.addCell(new Label(column++, i + 3, "", detFormat));

			}
			if (uservip.getIsVIP() == null) {
				sheet.addCell(new Label(column++, i + 3, "否", detFormat));
			} else if (uservip.getIsVIP() == 1) {
				sheet.addCell(new Label(column++, i + 3, "是", detFormat));
			} else {
				sheet.addCell(new Label(column++, i + 3, "否", detFormat));
			}

			sheet.addCell(new Label(column++, i + 3, ""
					+ uservip.getVipEndTime(), detFormat));
			sheet.addCell(new Label(column++, i + 3, ""
					+ uservip.getRegisterDate(), detFormat));

		}

		column = 0;
		sheet.setColumnView(column++, 20);
		sheet.setColumnView(column++, 20);
		sheet.setColumnView(column++, 20);
		sheet.setColumnView(column++, 20);
		sheet.setColumnView(column++, 20);
		sheet.setColumnView(column++, 20);
		sheet.setColumnView(column++, 20);
		sheet.setColumnView(column++, 20);
		sheet.setColumnView(column++, 20);
		sheet.setColumnView(column++, 20);
		sheet.setColumnView(column++, 20);
		sheet.setColumnView(column++, 20);
		sheet.setColumnView(column++, 20);
		sheet.setColumnView(column++, 20);
		sheet.setColumnView(column++, 20);
		sheet.setColumnView(column++, 20);
		sheet.setColumnView(column++, 20);
		sheet.setColumnView(column++, 20);
		sheet.setColumnView(column++, 20);
		sheet.setColumnView(column++, 20);

		workbook.write();
		os.flush();

		workbook.close();
		os.close();
		System.out.print("<h2>已生成   .xls文件</div>");
		ExcelDownload.download(this.request, this.response,
				URLDecoder.decode(this.urlstr, "UTF-8"), "uservips.xls");
		// return 3;

	}

	// excel导出结束
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

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getStarttime() {
		return this.starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return this.endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getZiduan() {
		return this.ziduan;
	}

	public void setZiduan(String ziduan) {
		this.ziduan = ziduan;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPaypassword() {
		return this.paypassword;
	}

	public void setPaypassword(String paypassword) {
		this.paypassword = paypassword;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAmoney() {
		return this.amoney;
	}

	public void setAmoney(String amoney) {
		this.amoney = amoney;
	}

	public String getRadio() {
		return this.radio;
	}

	public void setRadio(String radio) {
		this.radio = radio;
	}

	public String getQuotaId() {
		return this.quotaId;
	}

	public void setQuotaId(String quotaId) {
		this.quotaId = quotaId;
	}

	public int getUserType() {
		return this.userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getVipRadio() {
		return vipRadio;
	}

	public void setVipRadio(String vipRadio) {
		this.vipRadio = vipRadio;
	}

	public String getUrlstr() {
		return urlstr;
	}

	public void setUrlstr(String urlstr) {
		this.urlstr = urlstr;
	}

}