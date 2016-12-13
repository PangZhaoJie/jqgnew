package com.jqg.struts;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.cvicse.inforsuite.common.util.DateUtil;
import com.jqg.pojo.Avert;
import com.jqg.pojo.Friendlink;
import com.jqg.pojo.Lssuing;
import com.jqg.pojo.Manager;
import com.jqg.pojo.Moneycount;
import com.jqg.pojo.Oerationlog;
import com.jqg.pojo.Offlinerecharge;
import com.jqg.pojo.Translate;
import com.jqg.pojo.Uservip;
import com.jqg.pojo.Website;
import com.jqg.service.AvertService;
import com.jqg.service.CertificationService;
import com.jqg.service.FriendlinkService;
import com.jqg.service.LatestnewsService;
import com.jqg.service.LssuingService;
import com.jqg.service.ManagerService;
import com.jqg.service.MoneycountService;
import com.jqg.service.OerationlogService;
import com.jqg.service.OfflinerechargeService;
import com.jqg.service.RequestquotaService;
import com.jqg.service.TranslateService;
import com.jqg.service.UservipService;
import com.jqg.service.WebsiteService;
import com.jqg.service.impl.AvertServiceImpl;
import com.jqg.service.impl.CertificationServiceImpl;
import com.jqg.service.impl.FriendlinkServiceImpl;
import com.jqg.service.impl.LatestnewsServiceImpl;
import com.jqg.service.impl.LssuingServiceImpl;
import com.jqg.service.impl.ManagerServiceImpl;
import com.jqg.service.impl.MoneycountServiceImpl;
import com.jqg.service.impl.OerationlogServiceImpl;
import com.jqg.service.impl.OfflinerechargeServiceImpl;
import com.jqg.service.impl.RequestquotaServiceImpl;
import com.jqg.service.impl.TranslateServiceImpl;
import com.jqg.service.impl.UservipServiceImpl;
import com.jqg.service.impl.WebsiteServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class OverallAction extends BaseAction {
	private String avertId;
	private String avertContent;
	private String avertAdress;
	private String websiteId;
	private String webName;
	private String homeTitle;
	private String webKeyword;
	private String webDeescription;
	private String webfoot;
	private String beoverdueCost;
	HttpServletRequest request;
	private String withdrawals;
	private String backAward;
	private String lineReward;
	private String investIntegral;
	private String borrowIntegral;
	private String investment;
	private String loan;
	private String translateLimit;
	private String line;
	private String result;
	private String ip;
	private static final long serialVersionUID = 1L;
	private int currentPage;
	private int pageSize = 10;
	private int totalPage;
	private String friendLinkId;
	private String link;
	private String place;
	private String name;
	private String isDisplay;
	private String starttime;
	private String endtime;
	private String photo;
	private String trustAccount;
	private String publicKey;
	private String privateKey;
	Manager manager = (Manager) ActionContext.getContext().getSession()
			.get("manager");
	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	AvertService avertService = new AvertServiceImpl();
	WebsiteService websiteService = new WebsiteServiceImpl();
	OerationlogService oerationlogService = new OerationlogServiceImpl();
	LatestnewsService latestnewsService = new LatestnewsServiceImpl();
	FriendlinkService friendlinkService = new FriendlinkServiceImpl();
	ManagerService service = new ManagerServiceImpl();
	LssuingService lssuingService = new LssuingServiceImpl();
	UservipService uservipService = new UservipServiceImpl();
	RequestquotaService requestquotaService = new RequestquotaServiceImpl();
	CertificationService certificationService = new CertificationServiceImpl();
	OfflinerechargeService offlinerechargeService = new OfflinerechargeServiceImpl();
	TranslateService translateService = new TranslateServiceImpl();

	/**
	 * 根据搜索条件查找所有操作日志
	 * 
	 * @throws Exception
	 */
	public void searchOerationlogs() throws Exception {
		String sql = "SELECT * FROM oerationlog o WHERE 1=1 ";
		if ((!"".equals(this.ip)) && (this.ip != null)) {
			sql = sql + " AND  o.orationIP  = '" + this.ip + "'";
		}

		if ((!"".equals(URLDecoder.decode(this.name, "UTF-8")))
				&& (URLDecoder.decode(this.name, "UTF-8") != null)) {
			Manager manager = this.service.findManagerByname(URLDecoder.decode(
					this.name, "UTF-8"));
			if (manager != null) {
				sql = sql + " AND o.managerId = " + manager.getManagerId();
			}
		}
		if ((!"".equals(this.starttime)) && (this.starttime != null)
				&& (!"-请选择时间-".equals(this.starttime))) {
			sql = sql + " AND o.oerationChangeTime > '" + this.starttime
					+ " 00:00:00'";
		}
		if ((!"".equals(this.endtime)) && (this.endtime != null)
				&& (!"-请选择时间-".equals(this.starttime))) {
			sql = sql + " AND o.oerationChangeTime< '" + this.endtime
					+ " 23:59:59'";
		}

		List oerationlogs = this.oerationlogService.findOerationlogsBysql(sql);

		int totalRecord = oerationlogs.size();
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
		String sqls = sql + " ORDER BY o.oerationLogId DESC LIMIT "
				+ (this.currentPage - 1) * this.pageSize + "," + this.pageSize;
		List<Oerationlog> oerationlogspage = this.oerationlogService
				.findOerationlogsBysql(sqls);
		System.out.println(sql);
		StringBuffer str = new StringBuffer();
		str.append("{\"totalPage\":\"" + this.totalPage + "\",");
		str.append("\"currentPage\":\"" + this.currentPage + "\",");
		str.append("\"jsonRoot\":[");
		for (Oerationlog oerationlog : oerationlogspage) {
			str.append("{\"id\":\"" + oerationlog.getOerationLogId() + "\",");
			str.append("\"category\":\"" + oerationlog.getOerationCategory()
					+ "\",");
			str.append("\"remaks\":\"" + oerationlog.getOerationRemaks()
					+ "\",");
			str.append("\"ip\":\"" + oerationlog.getOrationIp() + "\",");
			str.append("\"time\":\"" + oerationlog.getOerationChangeTime()
					+ "\",");
			str.append("\"name\":\""
					+ oerationlog.getManager().getManagerName() + "\"},");
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
	 * 查找所有操作日志
	 * 
	 * @throws Exception
	 */
	public void findOerationlog() throws Exception {
		List oerationlogs = this.oerationlogService.findOerationlogs();
		int totalRecord = oerationlogs.size();
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
		List<Oerationlog> oerationlogspage = this.oerationlogService
				.findOerationlogsPage((this.currentPage - 1) * this.pageSize,
						this.pageSize);

		StringBuffer str = new StringBuffer();
		str.append("{\"totalPage\":\"" + this.totalPage + "\",");
		str.append("\"currentPage\":\"" + this.currentPage + "\",");
		str.append("\"jsonRoot\":[");

		for (Oerationlog oerationlog : oerationlogspage) {
			str.append("{\"id\":\"" + oerationlog.getOerationLogId() + "\",");
			str.append("\"category\":\"" + oerationlog.getOerationCategory()
					+ "\",");
			str.append("\"remaks\":\"" + oerationlog.getOerationRemaks()
					+ "\",");
			str.append("\"ip\":\"" + oerationlog.getOrationIp() + "\",");
			str.append("\"time\":\"" + oerationlog.getOerationChangeTime()
					+ "\",");
			str.append("\"name\":\""
					+ oerationlog.getManager().getManagerName() + "\"},");
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
	 * 添加友情链接
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addFriendlink() throws Exception {
		Friendlink friendlink = new Friendlink();
		friendlink.setIsDisplay(Integer.valueOf(this.isDisplay));
		friendlink.setLink(this.link);
		friendlink.setName(this.name);
		friendlink.setPlace(Integer.valueOf(this.place));
		friendlink.setPhoto(this.photo);
		boolean flag = this.friendlinkService.addFriendlink(friendlink);
		if (flag) {
			Oerationlog oerationlog = new Oerationlog();
			oerationlog.setManager(this.manager);
			oerationlog.setOerationCategory("updateWebsite");
			oerationlog.setOerationChangeTime(this.timestamp);
			oerationlog.setOerationRemaks("修改网站参数");
			oerationlog.setOrationIp(this.ip);
			boolean flag1 = this.oerationlogService
					.createOerationlog(oerationlog);
			return "success";
		}

		return "error";
	}

	/**
	 * 查找所有友情链接
	 * 
	 * @return
	 * @throws Exception
	 */
	public String findFriendlinkOne() throws Exception {
		Friendlink friendlink = this.friendlinkService
				.findFriendlinkById(Integer.valueOf(this.friendLinkId));
		ActionContext.getContext().getSession()
				.put("friendlinkone", friendlink);
		return "success";
	}

	/**
	 * 更新友情链接
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateFriendlink() throws Exception {
		Friendlink friendlink = this.friendlinkService
				.findFriendlinkById(Integer.valueOf(this.friendLinkId));
		friendlink.setIsDisplay(Integer.valueOf(this.isDisplay));
		friendlink.setLink(this.link);
		friendlink.setName(this.name);
		friendlink.setPlace(Integer.valueOf(this.place));
		friendlink.setPhoto(this.photo);
		boolean flag = this.friendlinkService.updateFriendlink(friendlink);
		if (flag) {
			Oerationlog oerationlog = new Oerationlog();
			oerationlog.setManager(this.manager);
			oerationlog.setOerationCategory("updateWebsite");
			oerationlog.setOerationChangeTime(this.timestamp);
			oerationlog.setOerationRemaks("修改网站参数");
			oerationlog.setOrationIp(this.ip);
			boolean flag1 = this.oerationlogService
					.createOerationlog(oerationlog);
			return "success";
		}

		return "error";
	}

	/**
	 * 删除友情链接
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deleteFriendlink() throws Exception {
		Friendlink friendlink = this.friendlinkService
				.findFriendlinkById(Integer.valueOf(this.friendLinkId));
		boolean flag = this.friendlinkService.deleteFriendlink(friendlink);
		if (flag) {
			Oerationlog oerationlog = new Oerationlog();
			oerationlog.setManager(this.manager);
			oerationlog.setOerationCategory("updateWebsite");
			oerationlog.setOerationChangeTime(this.timestamp);
			oerationlog.setOerationRemaks("修改网站参数");
			oerationlog.setOrationIp(this.ip);
			boolean flag1 = this.oerationlogService
					.createOerationlog(oerationlog);
			return "success";
		}

		return "error";
	}

	public void findFriendlink() throws Exception {
		List friendlinks = this.friendlinkService.findFriendlinks();
		int totalRecord = friendlinks.size();
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
		List<Friendlink> friendlinkspage = this.friendlinkService
				.findFriendlinkspage((this.currentPage - 1) * this.pageSize,
						this.pageSize);
		StringBuffer str = new StringBuffer();
		str.append("{\"totalPage\":\"" + this.totalPage + "\",");
		str.append("\"currentPage\":\"" + this.currentPage + "\",");
		str.append("\"jsonRoot\":[");
		for (Friendlink friendlink : friendlinkspage) {
			String a = "";
			if (friendlink.getIsDisplay().intValue() == 1)
				a = "是";
			else {
				a = "否";
			}
			str.append("{\"id\":\"" + friendlink.getFriendLinkId() + "\",");
			str.append("\"name\":\"" + friendlink.getName() + "\",");
			str.append("\"display\":\"" + a + "\",");
			str.append("\"place\":\"" + friendlink.getPlace() + "\",");
			str.append("\"link\":\"" + friendlink.getLink() + "\"},");
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
	 * 后台首页所需数据查找
	 * 
	 * @return
	 * @throws Exception
	 */
	public String index() throws Exception {
		List gonggaos = this.latestnewsService
				.findLatestnewssByfrontMenuIdPage(3, 0, 7);
		ActionContext.getContext().getSession().put("gonggaos", gonggaos);

		List yugaos = this.latestnewsService.findLatestnewssByfrontMenuIdPage(
				8, 0, 7);
		ActionContext.getContext().getSession().put("yugaos", yugaos);

		List lssuings = this.lssuingService
				.findLssuingsBySearch("SELECT * FROM lssuing l where l.lssuingType != 6 and   l.state=0");
		ActionContext.getContext().getSession()
				.put("chushen", Integer.valueOf(lssuings.size()));

		List lssuings1 = this.lssuingService
				.findLssuingsBySearch("SELECT * FROM lssuing l where l.lssuingType != 6 and  l.state=1");
		ActionContext.getContext().getSession()
				.put("fushen", Integer.valueOf(lssuings1.size()));

		String sql = "SELECT * FROM requestquota r WHERE r.examine = 0";
		List requestquotas = this.requestquotaService
				.findRequestquotasByUserIdPage(sql);
		ActionContext.getContext().getSession()
				.put("edushenqing", Integer.valueOf(requestquotas.size()));

		String sql1 = "SELECT * FROM uservip u WHERE u.sceneResult is NOT NULL  and  u.sceneResult =0";

		List uservips = this.uservipService.findUservipsBysql(sql1);
		ActionContext.getContext().getSession()
				.put("xianchangrenzheng", Integer.valueOf(uservips.size()));

		String sql2 = "SELECT * FROM uservip u WHERE u.videoResult  is NOT NULL  and  u.videoResult  =0";

		List uservips1 = this.uservipService.findUservipsBysql(sql2);
		ActionContext.getContext().getSession()
				.put("shipinrenzheng", Integer.valueOf(uservips1.size()));

		List translates = this.translateService
				.findTranslateBySql("SELECT * FROM translate t where t.state = 0 and serialnum<>''");
		ActionContext.getContext().getSession()
				.put("tixianshenqing", Integer.valueOf(translates.size()));

		String sql3 = "SELECT * FROM certification c WHERE c.ExamineStatus = 0";
		List certifications = this.certificationService
				.findCertificationsBysql(sql3);
		ActionContext.getContext().getSession()
				.put("shimingrenzheng", Integer.valueOf(certifications.size()));

		List offlinerecharges = this.offlinerechargeService
				.findOfflinerechargeBySql("SELECT * FROM offlinerecharge o WHERE  o.recharge='线下充值' and ISNULL(o.vipName)  ");
		ActionContext
				.getContext()
				.getSession()
				.put("chongzhishenhe", Integer.valueOf(offlinerecharges.size()));

		String sql4 = "SELECT * FROM uservip u WHERE u.isApplyVIP  is NOT NULL  and  u.isApplyVIP  =1";
		List<Uservip> uservips4 = this.uservipService.findUservipsBysql(sql4);
		ActionContext.getContext().getSession()
				.put("viprenzheng", Integer.valueOf(uservips4.size()));

		// 日统计投资查询
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String endtime = sdf.format(c.getTime());
		String starttime = DateUtil.getHBDate(endtime, 10);
		String wheresql = " and tenderTime>='" + starttime
				+ "' and tenderTime<'" + endtime + "'";
		// wheresql ="";
		String tendersql = "select sum(money) as accountmoney,tenderTime from tender where 1=1 "
				+ wheresql + " group by DATE_FORMAT(tenderTime,'%Y-%m-%d')";
		System.out.println(tendersql);

		List tenderlist = this.offlinerechargeService.findListbysqsl(tendersql);
		wheresql = " and borrowTime>='" + starttime + "' and borrowTime<'"
				+ endtime + "'";
		String borrowsql = "select sum(borrowMoney) as accountmoney,borrowTime from lssuing where state<>'1' "
				+ wheresql + " group by DATE_FORMAT(borrowTime,'%Y-%m-%d')";
		System.out.println(borrowsql);
		List blist = this.offlinerechargeService.findListbysqsl(borrowsql);
		List xlist = new ArrayList();
		List tendlist = new ArrayList();
		List borrowlist = new ArrayList();
		int k = 10;
		for (int j = k; k > 0; k--) {
			String time = DateUtil.getHBDate(endtime, k);
			boolean key = false;
			boolean key1 = true;
			for (int i = 0; i < tenderlist.size(); i++) {
				Object[] obj = (Object[]) tenderlist.get(i);
				Timestamp tendtime = (Timestamp) obj[1];
				Date date = tendtime;
				String temptime = sdf.format(date);
				double count = Double.valueOf(obj[0].toString());
				if (time.equals(temptime)) {
					tendlist.add(count);
					xlist.add(temptime);
					key = true;
					break;
				}
			}
			for (int i = 0; i < blist.size(); i++) {
				Object[] obj = (Object[]) blist.get(i);
				Timestamp tendtime = (Timestamp) obj[1];
				Date date = tendtime;
				String temptime = sdf.format(date);
				double count = Double.valueOf(obj[0].toString());
				if (time.equals(temptime)) {
					borrowlist.add(count);
					key1 = false;
					break;
				}
			}
			if (!key) {
				tendlist.add(0);
				xlist.add(time);
			}
			if (key1) {
				borrowlist.add(0);
			}
		}

		// JSONObject jsonobj = JSONObject.fromObject(tenderlist);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("xlist", xlist);
		map.put("tendlist", tendlist);
		map.put("borrowlist", borrowlist);
		JSONObject jsonobj = JSONObject.fromObject(map);
		ServletActionContext.getRequest().setAttribute("tenderlist", jsonobj);
		// 日统计借款查询
		DecimalFormat df = new DecimalFormat("#.00");
		String money1 = "0";
		String money2 = "0";
		String money3 = "0";
		String money4 = "0";
		String borrow1 = "0";
		String borrow2 = "0";
		String borrow3 = "0";
		Offlinerecharge offlinerecharge = new Offlinerecharge();
		List offlinerechargeList = this.offlinerechargeService
				.findOfflinerecharges();
		if ((offlinerechargeList != null) && (offlinerechargeList.size() > 0)) {
			for (int i = 0; i < offlinerechargeList.size(); i++) {
				offlinerecharge = (Offlinerecharge) offlinerechargeList.get(i);
				if (offlinerecharge.getRechargeStatus().equals(1)) {
					if (!"线下充值".equals(offlinerecharge.getRecharge())) {
						if (offlinerecharge.getRechargeAmount() != null) {
							money1 = df.format(new BigDecimal(offlinerecharge
									.getRechargeAmount().doubleValue())
									.add(new BigDecimal(money1)));
						}
					} else if (offlinerecharge.getRechargeAmount() != null) {
						money2 = df.format(new BigDecimal(offlinerecharge
								.getRechargeAmount().doubleValue())
								.add(new BigDecimal(money2)));
					}
				}

			}
		}

		List translateList = this.translateService.findTranslates();
		Translate translate = new Translate();
		if ((translateList != null) && (translateList.size() > 0)) {
			for (int i = 0; i < translateList.size(); i++) {
				translate = (Translate) translateList.get(i);
				if (translate.getState().intValue() == 2) {
					money3 = df.format(new BigDecimal(translate
							.getAffectMoney().doubleValue())
							.add(new BigDecimal(money3)));
				}
			}
		}

		money4 = df.format(new BigDecimal(money1).add(new BigDecimal(money2)
				.add(new BigDecimal(money3))));
		String sql31 = "select * from Lssuing l where (l.state=3 or l.state=4) and l.lssuingType != 6 ";

		List lssuingList = this.lssuingService.findLssuingsBySearch(sql31);
		if ((lssuingList != null) && (lssuingList.size() > 0)) {
			for (int i = 0; i < lssuingList.size(); i++) {
				Lssuing ls = (Lssuing) lssuingList.get(i);
				borrow1 = df.format(new BigDecimal(ls.getBorrowMoney())
						.add(new BigDecimal(borrow1)));
				borrow2 = df.format(new BigDecimal(ls.getReturnMoney()
						.doubleValue()).add(new BigDecimal(borrow2)));
				borrow3 = df.format(new BigDecimal(ls.getBorrowMoney())
						.subtract(
								new BigDecimal(ls.getReturnMoney()
										.doubleValue())).add(
								new BigDecimal(borrow3)));
			}
		}

		List zjtjlist = new ArrayList();
		zjtjlist.add(Double.valueOf(money1));
		zjtjlist.add(Double.valueOf(money2));
		zjtjlist.add(Double.valueOf(money3));
		zjtjlist.add(Double.valueOf(borrow1));
		zjtjlist.add(Double.valueOf(borrow2));
		map = new HashMap<String, Object>();
		map.put("zjtjlist", zjtjlist);
		jsonobj = JSONObject.fromObject(map);
		ServletActionContext.getRequest().setAttribute("zjtjlist", jsonobj);

		return "success";
	}

	/**
	 * 查找网站设置
	 * 
	 * @return
	 * @throws Exception
	 */
	public String findWebsite() throws Exception {
		Website website = this.websiteService.findWebsiteBywebsiteId(1);
		ActionContext.getContext().getSession().put("website", website);
		return "success";
	}

	/**
	 * 更新网站设置
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateWebsite() throws Exception {
		Website website = this.websiteService.findWebsiteBywebsiteId(Integer
				.valueOf(this.websiteId).intValue());
		website.setWebDeescription(this.webDeescription);
		website.setWebfoot(this.webfoot);
		website.setWebKeyword(this.webKeyword);
		website.setWebName(this.webName);
		website.setWithdrawals(this.withdrawals);
		website.setBackAward(this.backAward);
		website.setBeoverdueCost(this.beoverdueCost);
		website.setBorrowIntegral(Integer.valueOf(this.borrowIntegral));
		website.setHomeTitle(this.homeTitle);
		website.setInvestIntegral(Integer.valueOf(this.investIntegral));
		website.setLine(this.line);
		website.setLineReward(Integer.valueOf(this.lineReward));
		website.setTranslateLimit(Integer.valueOf(this.translateLimit));
		website.setTrustAccount(this.trustAccount);
		website.setPublicKey(this.publicKey);
		website.setPrivateKey(this.privateKey);

		boolean flag = this.websiteService.updateWebsite(website);
		if (flag) {
			Oerationlog oerationlog = new Oerationlog();
			oerationlog.setManager(this.manager);
			oerationlog.setOerationCategory("updateWebsite");
			oerationlog.setOerationChangeTime(this.timestamp);
			oerationlog.setOerationRemaks("修改网站参数");
			oerationlog.setOrationIp(this.ip);
			boolean flag1 = this.oerationlogService
					.createOerationlog(oerationlog);
			return "success";
		}
		return "error";
	}

	/**
	 * 查找所有广告
	 * 
	 * @return
	 * @throws Exception
	 */
	public String findAverts() throws Exception {
		List averts = this.avertService.findAverts();
		ActionContext.getContext().getSession().put("averts", averts);
		return "success";
	}

	/**
	 * 查找单个广告
	 * 
	 * @return
	 * @throws Exception
	 */
	public String findAvert() throws Exception {
		Avert avert = this.avertService.findAvertByavertId(Integer.valueOf(
				this.avertId).intValue());
		ActionContext.getContext().getSession().put("avert", avert);
		return "success";
	}

	/**
	 * 更新广告
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateAvert() throws Exception {
		Avert avert = this.avertService.findAvertByavertId(Integer.valueOf(
				this.avertId).intValue());
		avert.setAvertTime(this.timestamp);
		avert.setAvertAdress(this.avertAdress);
		avert.setAvertContent(this.avertContent);

		boolean flag = this.avertService.updateAvert(avert);
		if (flag) {
			Oerationlog oerationlog = new Oerationlog();
			oerationlog.setManager(this.manager);
			oerationlog.setOerationCategory("updateAvert");
			oerationlog.setOerationChangeTime(this.timestamp);
			oerationlog.setOerationRemaks("更新广告");
			oerationlog.setOrationIp(this.ip);
			boolean flag1 = this.oerationlogService
					.createOerationlog(oerationlog);
			return "success";
		}
		return "error";
	}

	public String getWebName() {
		return this.webName;
	}

	public void setWebName(String webName) {
		this.webName = webName;
	}

	public String getHomeTitle() {
		return this.homeTitle;
	}

	public void setHomeTitle(String homeTitle) {
		this.homeTitle = homeTitle;
	}

	public String getWebKeyword() {
		return this.webKeyword;
	}

	public void setWebKeyword(String webKeyword) {
		this.webKeyword = webKeyword;
	}

	public String getWebDeescription() {
		return this.webDeescription;
	}

	public void setWebDeescription(String webDeescription) {
		this.webDeescription = webDeescription;
	}

	public String getWebfoot() {
		return this.webfoot;
	}

	public void setWebfoot(String webfoot) {
		this.webfoot = webfoot;
	}

	public String getBeoverdueCost() {
		return this.beoverdueCost;
	}

	public void setBeoverdueCost(String beoverdueCost) {
		this.beoverdueCost = beoverdueCost;
	}

	public String getWithdrawals() {
		return this.withdrawals;
	}

	public void setWithdrawals(String withdrawals) {
		this.withdrawals = withdrawals;
	}

	public String getBackAward() {
		return this.backAward;
	}

	public void setBackAward(String backAward) {
		this.backAward = backAward;
	}

	public String getLineReward() {
		return this.lineReward;
	}

	public void setLineReward(String lineReward) {
		this.lineReward = lineReward;
	}

	public String getInvestIntegral() {
		return this.investIntegral;
	}

	public void setInvestIntegral(String investIntegral) {
		this.investIntegral = investIntegral;
	}

	public String getBorrowIntegral() {
		return this.borrowIntegral;
	}

	public void setBorrowIntegral(String borrowIntegral) {
		this.borrowIntegral = borrowIntegral;
	}

	public String getInvestment() {
		return this.investment;
	}

	public void setInvestment(String investment) {
		this.investment = investment;
	}

	public String getLoan() {
		return this.loan;
	}

	public void setLoan(String loan) {
		this.loan = loan;
	}

	public String getTranslateLimit() {
		return this.translateLimit;
	}

	public void setTranslateLimit(String translateLimit) {
		this.translateLimit = translateLimit;
	}

	public String getLine() {
		return this.line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getAvertId() {
		return this.avertId;
	}

	public void setAvertId(String avertId) {
		this.avertId = avertId;
	}

	public String getAvertContent() {
		return this.avertContent;
	}

	public void setAvertContent(String avertContent) {
		this.avertContent = avertContent;
	}

	public String getAvertAdress() {
		return this.avertAdress;
	}

	public void setAvertAdress(String avertAdress) {
		this.avertAdress = avertAdress;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getWebsiteId() {
		return this.websiteId;
	}

	public void setWebsiteId(String websiteId) {
		this.websiteId = websiteId;
	}

	public HttpServletRequest getRequest() {
		return this.request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
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

	public String getFriendLinkId() {
		return this.friendLinkId;
	}

	public void setFriendLinkId(String friendLinkId) {
		this.friendLinkId = friendLinkId;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPlace() {
		return this.place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsDisplay() {
		return this.isDisplay;
	}

	public void setIsDisplay(String isDisplay) {
		this.isDisplay = isDisplay;
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

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getTrustAccount() {
		return trustAccount;
	}

	public void setTrustAccount(String trustAccount) {
		this.trustAccount = trustAccount;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

}