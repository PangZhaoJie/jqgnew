package com.jqg.app;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.jqg.app.session.MySessionContext;
import com.jqg.pojo.Basicinfor;
import com.jqg.pojo.Certification;
import com.jqg.pojo.Latestnews;
import com.jqg.pojo.Lssingphoto;
import com.jqg.pojo.Lssuing;
import com.jqg.pojo.Lssuingherald;
import com.jqg.pojo.Messagemodel;
import com.jqg.pojo.Moneycount;
import com.jqg.pojo.Moneyuse;
import com.jqg.pojo.Offlinerecharge;
import com.jqg.pojo.Personalbankinfor;
import com.jqg.pojo.Phone;
import com.jqg.pojo.Returnway;
import com.jqg.pojo.SmsSendLog;
import com.jqg.pojo.Systemconf;
import com.jqg.pojo.Tender;
import com.jqg.pojo.Transfer;
import com.jqg.pojo.Translate;
import com.jqg.pojo.Uservip;
import com.jqg.pojo.Website;
import com.jqg.service.BasicinforService;
import com.jqg.service.CertificationService;
import com.jqg.service.LatestnewsService;
import com.jqg.service.LssingphotoService;
import com.jqg.service.LssuingService;
import com.jqg.service.LssuingheraldService;
import com.jqg.service.MessagemodelService;
import com.jqg.service.MoneycountService;
import com.jqg.service.MoneyhistorydetailService;
import com.jqg.service.MoneymaxService;
import com.jqg.service.MoneyminService;
import com.jqg.service.OfflinerechargeService;
import com.jqg.service.PersonalbankinforService;
import com.jqg.service.PhoneService;
import com.jqg.service.RecordService;
import com.jqg.service.SmsSendLogService;
import com.jqg.service.SystemconfService;
import com.jqg.service.TenderService;
import com.jqg.service.TransferService;
import com.jqg.service.TranslateService;
import com.jqg.service.UservipService;
import com.jqg.service.WebsiteService;
import com.jqg.service.impl.BasicinforServiceImpl;
import com.jqg.service.impl.CertificationServiceImpl;
import com.jqg.service.impl.LatestnewsServiceImpl;
import com.jqg.service.impl.LssingphotoServiceImpl;
import com.jqg.service.impl.LssuingServiceImpl;
import com.jqg.service.impl.LssuingheraldServiceImpl;
import com.jqg.service.impl.MessagemodelServiceImpl;
import com.jqg.service.impl.MoneycountServiceImpl;
import com.jqg.service.impl.MoneyhistorydetailServiceImpl;
import com.jqg.service.impl.MoneymaxServiceImpl;
import com.jqg.service.impl.MoneyminServiceImpl;
import com.jqg.service.impl.OfflinerechargeServiceImpl;
import com.jqg.service.impl.PersonalbankinforServiceImpl;
import com.jqg.service.impl.PhoneServiceImpl;
import com.jqg.service.impl.RecordServiceImpl;
import com.jqg.service.impl.SmsSendLogServiceImpl;
import com.jqg.service.impl.SystemconfServiceImpl;
import com.jqg.service.impl.TenderServiceImpl;
import com.jqg.service.impl.TransferServiceImpl;
import com.jqg.service.impl.TranslateServiceImpl;
import com.jqg.service.impl.UservipServiceImpl;
import com.jqg.service.impl.WebsiteServiceImpl;
import com.jqg.util.Client;
import com.jqg.util.Common;
import com.jqg.util.LoanUtils;
import com.jqg.util.MD5Trust;
import com.jqg.util.RsaHelper;
import com.opensymphony.xwork2.ActionContext;
import com.ruanwei.interfacej.SmsClientSend;

public class IndexAction extends AppBaseAction {
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;// 声明request
	private HttpServletResponse response;// 声明response

	private LssuingService lssuingService = new LssuingServiceImpl();// 投资人列表
	private LssingphotoService lssingphotoService = new LssingphotoServiceImpl();
	private TenderService tenderService = new TenderServiceImpl();
	private MoneycountService moneycountService = new MoneycountServiceImpl();
	private LssuingheraldService lssuingheraldService = new LssuingheraldServiceImpl();// 新标预告时用到
	private MoneyhistorydetailService moneyhistorydetailService = new MoneyhistorydetailServiceImpl();
	private UservipService uservipService = new UservipServiceImpl();
	private LatestnewsService latestnewsService = new LatestnewsServiceImpl();
	private MoneyminService moneyminService = new MoneyminServiceImpl();
	private MoneymaxService moneymaxService = new MoneymaxServiceImpl();
	private SystemconfService systemconfService = new SystemconfServiceImpl();
	private WebsiteService websiteService = new WebsiteServiceImpl();
	private OfflinerechargeService offlinerechargeService = new OfflinerechargeServiceImpl();
	private BasicinforService basicinforService = new BasicinforServiceImpl();
	private PhoneService phoneService = new PhoneServiceImpl();
	private MessagemodelService messagemodelService = new MessagemodelServiceImpl();
	private PersonalbankinforService personalbankinforService =  new PersonalbankinforServiceImpl();
	private TranslateService translateService = new TranslateServiceImpl();
	private TransferService transferService = new TransferServiceImpl();
	private RecordService recordService = new RecordServiceImpl();
	private String message;//消息
	private String status;//状态
	private String RealName = "";
	private String RegisterType = "";
	private String AccountType = "";
	private String SignInfo = "";
	private String AccountNumber = "";
	private String Mobile = "";
	private String IdentificationNo = "";
	private String LoanPlatformAccount;
	private String PlatformMoneymoremore;
	private String Remark1;
	private String Remark2;
	private String Remark3;
	private String SubmitURL;
	private String ReturnURL;
	private String NotifyURL;
	private int antistate=0;
	private String RandomTimeStamp;
	private String Email = "";
	private String Image1 = "";
	private String Image2 = "";
	private String AuthFee = "";
	private String AuthState = "";
	private boolean istest = true;// true ：测试，false:正式

	/**
	 * 投资列表
	 * 
	 * @return 返回 投资列表集合 请求状态
	 * @throws Exception
	 */
	public void GetInvestmentList() throws Exception {
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();

//		MySessionContext myc = MySessionContext.getInstance();
//		HttpSession sess = myc.getSession(request.getParameter("sessionId"));
//		Uservip uservip = (Uservip) sess.getAttribute(request.getParameter("uservip"));
		String lssuingType=request.getParameter("lssuingType");
		String state = "0";// 请求状态 0：请求失败 1：请求成功

		// 获取pageSize 和currentPage
		Integer pageSize = (Integer.parseInt(request.getParameter("pageSize")));// 每夜显示的记录数
		Integer currentPage = (Integer.parseInt(request.getParameter("currentPage")));// 当前页
		String sql;
		if("9".equals(lssuingType)){
			sql = " select * from Lssuing where state<5 and lssuingType= 9 order by borrowTime desc"
					+ " limit " + (currentPage - 1) * pageSize + "," + pageSize;
		}else{
			sql = " select * from Lssuing where state<5 and lssuingType < 6 order by borrowTime desc"
				+ " limit " + (currentPage - 1) * pageSize + "," + pageSize;
		}
		// 投资人列表a
		List<Lssuing> lssuingss = lssuingService.findLssuingsBySearch(sql);
		List<Lssuing> lssuings = new ArrayList<Lssuing>();
		for (Lssuing l : lssuingss) {
			Double money = 0.00;
			Set<Tender> set = l.getTenders();
			for (Tender t : set) {
				money = money + t.getMoney();
			}
			int BorrowMoney = Integer.valueOf(l.getBorrowMoney());// 借款金额
			DecimalFormat df = new DecimalFormat();
			df.setMaximumFractionDigits(2);
			df.setMinimumFractionDigits(2);
			String k = df.format(money * 100.00 / BorrowMoney);
			double scale = Double.parseDouble(k);// 年利率
			l.setScale(scale);// 借款进度
			if (l.getPeriodday() != null
					&& l.getPeriodday().getPeriodDayId() != null) {
				l.setRates(Double.valueOf(l.getRate() * 360));
			} else {
				l.setRates(l.getRate());
			}
			// 还款方式
			Returnway returnway = l.getReturnway();
			l.setReturnway(returnway);
			// 借款id
			l.setLssuingId(l.getLssuingId());
			lssuings.add(l);
			state = "1";// 1 请求成功
		}
		/**
		 * 返回数据
		 */
		StringBuffer str = new StringBuffer();
		// 投资列表
		str.append("{\"info_list\":[");
		for (int i = 0; i < lssuings.size(); i++) {
			str.append("{\"lssuingId\":\"" + lssuings.get(i).getLssuingId()
					+ "\",");// 借款id
			str.append("\"lssuingType\":\"" + lssuings.get(i).getLssuingType()
					+ "\",");// 标的类型
			str.append("\"periodday\":\"" + lssuings.get(i).getPeriodday()
					+ "\",");// 是否为天标
			str.append("\"isOrient\":\"" + lssuings.get(i).getIsOrient()
					+ "\",");// 是否为天标
			str.append("\"isAward\":\"" + lssuings.get(i).getIsAward() + "\",");// 是否有投标奖励
																				// 0无奖励
																				// 1
																				// 有奖励
			str.append("\"lssState\":\""+lssuings.get(i).getState()+"\",");//标状态
			str.append("\"title\":\"" + lssuings.get(i).getTitle() + "\","); // 标题
			str.append("\"borrowMoney\":\"" + lssuings.get(i).getBorrowMoney()
					+ "\",");// 借款金额
			
			
			if (lssuings.get(i).getPeriodtime() != null) {
				str.append("\"rate\":\"" + lssuings.get(i).getRate() + "\","); // 年利率/日利率
				str.append("\"periodTime\":\""
						+ lssuings.get(i).getPeriodtime().getPeriodTimeName()
						+ "\","); // 借款期限
			} else {
				str.append("\"rate\":\"" + lssuings.get(i).getRate()*360 + "\","); // 年利率/日利率
				str.append("\"periodTime\":\""
						+ lssuings.get(i).getPeriodday().getPeriodDayName()
						+ "\","); // 借款期限
			}
			str.append("\"scale\":\"" + lssuings.get(i).getScale() + "\","); // 借款进度
			str.append("\"returnWay\":\""
					+ lssuings.get(i).getReturnway().getReturnWayName() + "\"");// 还款方式
			str.append("}");
			if (i != lssuings.size() - 1) {
				str.append(",");
			}
		}

		str.append("],\r\n");
		str.append("\"get_list_info_state\":\"" + state + "\",");// 请求状态̬
		str.deleteCharAt(str.lastIndexOf(","));
		str.append("}");
		// response.setContentType("text/html charset=UTF-8") ;
		response.setContentType("text/json;charset=UTF-8");
		try {
			response.getWriter().print(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 可转让债权列表
	 * 
	 * @throws Exception
	 */
	public void transferlistPage() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String returnWay = request.getParameter("returnway");//还款方式
		String yongtu = request.getParameter("yongtu");//借款用途
		String time = request.getParameter("time");//借款期限（月）
		String periodday = request.getParameter("periodday");//借款期限（天）
		String bid = request.getParameter("bid");//项目状态
		String startMoney = request.getParameter("startMoney");//金额起始值
		String endMoney = request.getParameter("endMoney");//金额结束值
		Integer currentPage = Integer.valueOf(request.getParameter("currentPage")).intValue();//金额起始值
		Integer pageSize = Integer.valueOf(request.getParameter("pageSize")).intValue();//金额结束值
//		String sql ="select * from Tender t ,lssuing l where  t.lssuingId=l.lssuingId and t.transfer is NOT NULL";
		String sql = "from Tender t where (t.transfer=5 or t.transfer=3)";
		if (!"0".equals(returnWay) && returnWay!=null)
	    {
	      sql = sql + " AND  t.lssuing.returnWay.returnWayId = " + returnWay;
	    }

	    if (!"0".equals(yongtu) && yongtu!=null)
	    {
	      sql = sql + " AND t.lssuing.moneyuse.moneyUseId = " + yongtu;
	    }
	    if (!"0".equals(time) && time!=null)
	    {
	      sql = sql + " AND t.lssuing.periodtime.periodTimeId = " + time;
	    }
	    if (!"0".equals(periodday) && periodday!=null)
	    {
	      sql = sql + " AND t.lssuing.periodday.periodDayId = " + periodday;
	    }
	    if(!"0".equals(bid) && bid!=null){
	    	sql = sql + " AND t.lssuing.state ="+bid;
	    }
		if(startMoney!=null && endMoney!=null){
			sql = sql + " AND t.lssuing.borrowMoney< " + endMoney + " and t.lssuing.borrowMoney>startMoney";
		}
		
		List tenders3 = this.tenderService.findTenderByHql(sql,-1,-1);
		int totalRecord = tenders3.size();
		int totalPage;
		if (totalRecord % pageSize == 0)
			totalPage = (totalRecord / pageSize);
		else {
			totalPage = (totalRecord / pageSize + 1);
		}
		if (currentPage >= totalPage) {
			currentPage = totalPage;
		}
		if (currentPage <= 1) {
			currentPage = 1;
		}
//		sql = sql +" ORDER BY t.tenderId DESC LIMIT " +(this.currentPage - 1) * this.pageSize + "," + this.pageSize;;
		List<Tender> tendersPage3 = this.tenderService.findTenderByHql(sql,(currentPage - 1) * pageSize,pageSize);
		String path = request.getContextPath();
		StringBuffer str1 = new StringBuffer();
		str1.append("{\"totalPage\":\"" + totalPage + "\",");
		str1.append("\"currentPage\":\"" + currentPage + "\",");
		str1.append("\"jsonRoot\":[");
		DecimalFormat df1 = new DecimalFormat("0.00");
		for (Tender tender : tendersPage3) {
			double benxi;
			String lilv = "";
			if (tender.getLssuing().getPeriodday() != null) {
				lilv = df1.format(tender.getLssuing().getRate() * 360);
			} else {
				lilv = df1.format(tender.getLssuing().getRate());
			}
//			String actions = path+"/transfer/findTransferByID.action?tenderId="+ tender.getTenderId();
			String state = "";
			if(tender.getTransfer()==3){
				state = "已完成";
			}else{
				state = "立即投资";
			}
			System.out.println(tender.getTenderId());
			Transfer transfer = this.transferService.findTransferByTenderId(tender.getTenderId());
			str1.append("{\"title\":\""+ tender.getLssuing().getTitle() + "\",");//标号
			str1.append("\"rate\":\"" + lilv + "\",");//利率
			str1.append("\"benxi\":\"" + transfer.getProfit() + "\",");//待收本息（债权总值）
			str1.append("\"transferMoney\":\"" + transfer.getMoney() + "\",");//转让价格
			str1.append("\"state\":\"" + state + "\",");//状态
//			str1.append("\"actions\":\"" + actions + "\"},");
		}
		str1.deleteCharAt(str1.lastIndexOf(","));
		str1.append("]}");

		HttpServletResponse response1 = ServletActionContext.getResponse();
		response1.setCharacterEncoding("utf-8");
		try {
			response1.getWriter().print(str1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 撤消债权转让
	 * */	
		public void cancelTransfer()throws Exception{
			request = ServletActionContext.getRequest();
			response = ServletActionContext.getResponse();
			MySessionContext myc = MySessionContext.getInstance();
			String tenderId = request.getParameter("tenderId");
			Tender tender = this.tenderService.findTenderById(Integer.valueOf(tenderId));
			tender.setTransfer(Integer.valueOf(4));//撤消转让
			this.tenderService.updateTender(tender);
			Transfer transfer = this.transferService.findTransferByTenderId1(Integer.valueOf(tenderId));
			transfer.setCancelNum(transfer.getCancelNum()+1);
			transfer.setCancelTime(new Timestamp(new Date().getTime()));
			transfer.setIsTransfer(Integer.valueOf(3));//撤消转让
			StringBuffer str = new StringBuffer();
			this.status ="1" ;//撤销成功
			this.message = "撤销成功";
			str.append("{\"status\":\"" + this.status + "\","+"\"message\":\"" + this.message + "\"}");
			response.setContentType("text/json;charset=UTF-8");
			response.getWriter().write(str.toString());
		}
		/**
		 * 债权转让的详细页面
		 * 
		 * @return
		 * @throws Exception
		 */
		public void findTransferByID() throws Exception {
			
			String tenderId = request.getParameter("tenderId");
			Tender tender1 = this.tenderService.findTenderById(Integer.valueOf(tenderId));
			Transfer transfer = this.transferService.findTransferByTenderId1(Integer.valueOf(tenderId));
			Uservip uservip1 =this.uservipService.findUservipByUserid(tender1.getUservip().getUserId());
			Map map = new LinkedHashMap();
			net.sf.json.JSONObject json = new net.sf.json.JSONObject();
			double benxi;
			DecimalFormat df1 = new DecimalFormat("0.00");
			if (tender1.getLssuing().getPeriodday() != null) {
				benxi = tender1.getMoney().intValue()
						* tender1.getLssuing().getRate().doubleValue()
						* tender1.getLssuing().getPeriodday().getPeriodDayId()
								.intValue() / 100.0D;
			} else {
				benxi = tender1.getMoney().intValue()
						* tender1.getLssuing().getRate().doubleValue()
						* tender1.getLssuing().getPeriodtime().getPeriodTimeId()
								.intValue() / 12.0D / 100.0D;
			}
			String benxistr = df1.format(benxi+tender1.getMoney().doubleValue());
			map.put("benxistr", benxistr);//本息
			map.put("transferMoney", transfer.getMoney());//认购价格
			map.put("username", uservip1.getUserName());//持有人
			map.put("tenderId", tender1.getTenderId());//tenderId
			map.put("transfer", tender1.getTransfer());
			
			Lssuing lssuing = this.lssuingService.findLssuingById(tender1.getLssuing().getLssuingId());

			List lssingphotos = this.lssingphotoService.findLssingphotosBylssuing(lssuing.getLssuingId().intValue());

			int totalRecord = lssingphotos.size();
			int size;
			List lssList = new ArrayList();
			if (totalRecord % 3 == 0)
				size = totalRecord / 3;
			else {
				size = totalRecord / 3 + 1;
			}
			for (int i = 0; i < size; i++) {
				List lssingphotos1 = this.lssingphotoService
						.findLssingphotosBylssuingPage(lssuing.getLssuingId()
						.intValue(), i * 3, 3);
				lssList.add(lssingphotos1);
			}
//			Basicinfor basicinfor = this.basicinforService.findBasicinforByUserId(lssuing.getUservip().getUserId().intValue());
//
//			ActionContext.getContext().getSession().put("basicinforTdener", basicinfor);
			List<Lssuing> lssuings = this.lssuingService.findLssuingsByUserId(lssuing.getUservip().getUserId().intValue());
			List lssuings1 = this.lssuingService.findLssuingsByUserIdAndByState(lssuing.getUservip().getUserId().intValue(), 3);

			map.put("lssuingTdener", Integer.valueOf(lssuings.size()));

			double zonge = 0.0D;
			for (Lssuing lssuing2 : lssuings) {
				zonge = Double.valueOf(lssuing2.getBorrowMoney()).doubleValue()+ zonge;
			}
			String zongenum = df1.format(zonge);
			map.put("zonge", zongenum);
			map.put("lssuingTdener", Integer.valueOf(lssuings.size()));
			map.put("lssuingTdenerDai", Integer.valueOf(lssuings1.size()));

			double daihuan = 0.0D;

			for (Lssuing lssuing2 : lssuings) {
				if (lssuing2.getState().intValue() == 4) {

					if (lssuing2.getPeriodtime() != null) {
						daihuan = Integer.valueOf(lssuing2.getBorrowMoney())
								.intValue()
								+ Integer.valueOf(lssuing2.getBorrowMoney())
										.intValue()
								* lssuing2.getRate().doubleValue()
								/ 100.0D
								/ 12.0D
								* lssuing2.getPeriodtime().getPeriodTimeId()
										.intValue()
								+ daihuan
								- (Integer.valueOf(lssuing2.getBorrowMoney())
										.intValue() + Integer.valueOf(
										lssuing2.getBorrowMoney()).intValue()
										* lssuing2.getRate().doubleValue()
										/ 100.0D
										/ 12.0D
										* lssuing2.getPeriodtime()
												.getPeriodTimeId().intValue());
					} else if (lssuing2.getPeriodday() != null) {
						daihuan = Integer.valueOf(lssuing2.getBorrowMoney())
								.intValue()
								+ Integer.valueOf(lssuing2.getBorrowMoney())
										.intValue()
								* lssuing2.getRate().doubleValue()
								* lssuing2.getPeriodday().getPeriodDayId()
										.intValue()
								/ 100.00D
								+ daihuan
								- (Integer.valueOf(lssuing2.getBorrowMoney())
										.intValue() + Integer.valueOf(
										lssuing2.getBorrowMoney()).intValue()
										* lssuing2.getRate().doubleValue()
										* lssuing2.getPeriodday().getPeriodDayId()
												.intValue() / 100.00D);
					}
				} else {
					if (lssuing2.getPeriodtime() != null) {
						daihuan = Integer.valueOf(lssuing2.getBorrowMoney())
								.intValue()
								+ Integer.valueOf(lssuing2.getBorrowMoney())
										.intValue()
								* lssuing2.getRate().doubleValue()
								/ 100.0D
								/ 12.0D
								* lssuing2.getPeriodtime().getPeriodTimeId()
										.intValue() + daihuan;
					} else if (lssuing2.getPeriodday() != null) {
						daihuan = Integer.valueOf(lssuing2.getBorrowMoney())
								.intValue()
								+ Integer.valueOf(lssuing2.getBorrowMoney())
										.intValue()
								* lssuing2.getRate().doubleValue()
								* lssuing2.getPeriodday().getPeriodDayId()
										.intValue() / 100.00D + daihuan;
					}
				}
			}

			String daihuannum = df1.format(daihuan);
			map.put("daihuan", daihuannum);

			List lssuings2 = this.lssuingService.findLssuingsByUserIdAndByState(lssuing.getUservip().getUserId().intValue(), -1);
			map.put("yuqishu", Integer.valueOf(lssuings2.size()));

			List lssuings3 = this.lssuingService.findLssuingsByUserIdAndByState(
					lssuing.getUservip().getUserId().intValue(), 4);
			map.put("yiwancheng", Integer.valueOf(lssuings3.size()));
			List<Tender> tenders = this.tenderService
					.findTendersBylssuingId(lssuing.getLssuingId().intValue());

			double interest = 0.0D;
			String picture = " ";
			String away = null;
			String state = null;
			String number = null;
			String day = "";

			String moneyMin = lssuing.getMoneymin().getMoneyMinName().toString();
			String moneyMax = "";
			int money = 0;
			if (tenders.size() == 0) {
				money = 0;
			} else {
				for (Tender tender : tenders) {
					money = tender.getMoney().intValue() + money;
				}
			}
			int BorrowMoney = Integer.valueOf(lssuing.getBorrowMoney()).intValue();
			DecimalFormat df = new DecimalFormat();
			df.setMaximumFractionDigits(2);
			df.setMinimumFractionDigits(2);
			String k = df.format(money * 100.0D / BorrowMoney);
			double bear = Double.parseDouble(k);

			if (tenders.size() == 0)
				number = "0";
			else if (bear < 10.0D)
				number = "1";
			else if ((10.0D <= bear) && (bear < 20.0D))
				number = "2";
			else if ((20.0D <= bear) && (bear < 30.0D))
				number = "3";
			else if ((30.0D <= bear) && (bear < 40.0D))
				number = "4";
			else if ((40.0D <= bear) && (bear < 50.0D))
				number = "5";
			else if ((50.0D <= bear) && (bear < 60.0D))
				number = "6";
			else if ((60.0D <= bear) && (bear < 70.0D))
				number = "7";
			else if ((70.0D <= bear) && (bear < 80.0D))
				number = "8";
			else if ((80.0D <= bear) && (bear < 90.0D))
				number = "9";
			else if ((90.0D <= bear) && (bear < 100.0D))
				number = "10";
			else {
				number = "11";
			}

			if (lssuing.getIsAward().intValue() == 0) {
				away = "无奖励";
			} else {
				picture = "<img src='../images/loantype/award.gif' />" + picture;
				if (lssuing.getAwardRate() != null) {
					away = "按投标金额比例奖励" + lssuing.getAwardRate() + "%";
				} else if (lssuing.getAwardMoney() != null) {
					away = "按固定金额分摊奖励" + lssuing.getAwardMoney();
				}

			}

			if (lssuing.getPeriodtime() != null) {
				interest = Integer.valueOf(lssuing.getBorrowMoney()).intValue()
						+ Integer.valueOf(lssuing.getBorrowMoney()).intValue()
						* lssuing.getRate().doubleValue() / 100.0D / 12.0D
						* lssuing.getPeriodtime().getPeriodTimeId().intValue();
				day = "年";
			} else if (lssuing.getPeriodday() != null) {
				interest = Integer.valueOf(lssuing.getBorrowMoney()).intValue()
						+ Integer.valueOf(lssuing.getBorrowMoney()).intValue()
						* lssuing.getRate().doubleValue()
						* lssuing.getPeriodday().getPeriodDayId().intValue()
						/ 100.00D;
				picture = "<img src='../images/loantype/day.gif' />" + picture;
				day = "日";
			}
			if (lssuing.getMoneymax() != null)
				moneyMax = lssuing.getMoneymax().getMoneyMaxName().toString();
			else {
				moneyMax = "无限制";
			}
			MySessionContext myc = MySessionContext.getInstance();
			HttpSession sess = myc.getSession(request.getParameter("sessionId"));
			Uservip uservip = (Uservip) sess.getAttribute(request.getParameter("uservip"));
			if (uservip != null) {

				System.err.println(uservip.getUserId());
				Moneycount moneycount = this.moneycountService.findMoneycountByuserId(uservip.getUserId());
				map.put("AvailableMoney", moneycount.getAvailableMoney());
				map.put("moneycount", moneycount);
				if (lssuing.getTenderLimit().intValue() == 1) {
					map.put("moneyLimit", moneycount.getCollectTotalMoney());
				} else {
					map.put("moneyLimit", Integer.valueOf(0));
				}
			}
			map.put("money",Integer.valueOf(Integer.valueOf(lssuing.getBorrowMoney()).intValue()- money));
			map.put("interest", df.format(interest));
			map.put("picture", picture);
			map.put("away", away);
			map.put("lssuing", lssuing);
			map.put("number", number);
			map.put("bear", Double.valueOf(bear));
			map.put("day", day);
			map.put("moneyMin", moneyMin);
			map.put("moneyMax", moneyMax);
			map.put("lssList", lssList);
			json=json.fromObject(map);
			response.setContentType("text/json;charset=UTF-8");
			try {
				response.getWriter().print(json);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
	/**
	 * 投资列表详情
	 * 
	 * @param 获取
	 *            lssuingId
	 * @return 投资列表 借款详情列表 投资记录列表
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public void findLssuingById() throws Exception {
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		// 获取借款信息id
		Integer lssuingId = (Integer.parseInt(request.getParameter("lssuingId")));// 接收类型
		MySessionContext myc = MySessionContext.getInstance();
		HttpSession sess = myc.getSession(request.getParameter("sessionId"));
		Uservip uservip = (Uservip) sess.getAttribute(request
				.getParameter("uservip"));

		String state = "0";// 定义state 接收请求状态
		// 根据lssuingId 查询借款信息
		Lssuing lssuings = lssuingService.findLssuingById(lssuingId);
		Integer isAward = lssuings.getIsAward();
		lssuings.setIsAward(isAward); // 是否有奖励
		Timestamp borrowTime = lssuings.getBorrowTime();
		lssuings.setBorrowTime(borrowTime);// 借款时间
		
		if(lssuings.getLssuingType()!=9){
			Moneyuse moneyuse = lssuings.getMoneyuse();// 借款用途
			lssuings.setMoneyuse(moneyuse);
		}
		String explains = lssuings.getExplains();
		Integer lssState=lssuings.getState();
		lssuings.setExplains(explains);// 借款详细介绍

		Moneycount moneycount = moneycountService
				.findMoneycountByuserId(uservip.getUserId().intValue());// 根据用户id查找用户账户可用余额
		Double availMoney = moneycount.getAvailableMoney();

		String moneyMin = lssuings.getMoneymin().getMoneyMinName().toString();// 最低投标金额
		String moneyMax = "";
		if (lssuings.getMoneymax() != null)
			moneyMax = lssuings.getMoneymax().getMoneyMaxName().toString();// 最高投标金额
		else {
			moneyMax = "无限制";
		}

		String away = "";
		String picture = "";
		if (lssuings.getIsAward().intValue() == 0) {
			away = "无奖励";
		} else {
			picture = "<img src='../images/loantype/award.gif' />" + picture;
			if (lssuings.getAwardRate() != null) {
				away = "按投标金额比例奖励" + lssuings.getAwardRate() + "%";
			} else if (lssuings.getAwardMoney() != null) {
				away = "按固定金额分摊奖励" + lssuings.getAwardMoney();
			}
		}

		// 查找图片
		List<Lssingphoto> lssingphotos = lssingphotoService
				.findLssingphotosBylssuing(lssuingId);

		int totalRecord = lssingphotos.size();
		int size;
		if (totalRecord % 3 == 0)
			size = totalRecord / 3;
		else {
			size = totalRecord / 3 + 1;
		}
		List lssList = new ArrayList();
		for (int i = 0; i < size; i++) {
			List lssingphotos1 = lssingphotoService
					.findLssingphotosBylssuingPage(lssuingId, i * 3, 3);
			lssList.add(lssingphotos1);
		}

		// 图片的获取路径
		String path = request.getContextPath();
		String basePath = "";
		if (request.getServerPort() == 80) {
			basePath = request.getScheme() + "://" + request.getServerName()
					+ path + "/";
		} else {
			basePath = request.getScheme() + "://" + request.getServerName()
					+ ":" + request.getServerPort() + path + "/";
		}
		Lssuing lssuing = (Lssuing) ActionContext.getContext().getSession()
				.get("lssuing");
		List tenders = null;
		try {
			tenders = tenderService.findTendersBylssuingId(lssuingId);
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		String sql = "SELECT * FROM tender t WHERE t.lssuingId = " + lssuingId
				+ " ORDER BY t.tenderId DESC";
		List<Tender> tenderspage = null;
		try {
			tenderspage = tenderService.findTendersBylssuingIdPage(sql);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		String img = "<img src='../images/loantype/zhuangtai.png' />";
		// 返回借款信息
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		net.sf.json.JSONObject json = new net.sf.json.JSONObject();
		List list1 = new ArrayList();
		List list2 = new ArrayList();
		Map map = new LinkedHashMap();
		map.put("away", away);// 是否有投资奖励
		map.put("borrowTime", sdf.format(borrowTime));// 借款时间
		if(lssuings.getLssuingType()!=9){
			map.put("moneyuse", lssuings.getMoneyuse().getMoneyUseName());// 借款用途
		}
		map.put("lssuingType", lssuings.getLssuingType());//标类型
		map.put("availMoney", availMoney);// 可用余额
		map.put("moneyMax", moneyMax);//最高投标金额 
		map.put("moneyMin", moneyMin);//最低投标金额
		map.put("explains", explains);// 借款详细介绍
		map.put("lssState", lssState);//状态
		// 返回投资基本信息
		for (int i = 0; i < lssList.size(); i++) {
			List<Lssingphoto> list = (List<Lssingphoto>) lssList.get(i);
			for (Lssingphoto photo : list) {
				Map map1 = new HashMap();
				map1.put("photo", basePath + photo.getPhoto());// 图片
				list1.add(map1);
				
			}
		}
		map.put("photo_list", list1);
		// 返回 投资记录 列表
		for (int j = 0; j < tenderspage.size(); j++) {
			Tender student = tenderspage.get(j);
			String userName = tenderspage.get(j).getUservip().getUserName();
			if (userName.length() < 4) {
				userName = userName.substring(0, 1)
						+ "****"
						+ userName.substring(userName.length() - 1,
								userName.length());
			} else {
				userName = userName.substring(0, 2)
						+ "****"
						+ userName.substring(userName.length() - 2,
								userName.length());
			}
			state = "1";
			Map map2 = new HashMap();
			map2.put("userName", userName);// 投标人
			map2.put("money", student.getMoney());
			map2.put("time", student.getTenderTime().toString().substring(0, 10));// 投标金额
			list2.add(map2);// 投标时间
		}
		map.put("record_list", list2);
		map.put("state", state);// 请求状态 0 请求失败 1请求成功
		json=json.fromObject(map);
		response.setContentType("text/json;charset=UTF-8");
		try {
			response.getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询很多的投标记录
	 */
	public void getLssuingRecord() throws Exception {
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		// 获取借款信息id
		Integer lssuingId = (Integer
				.parseInt(request.getParameter("lssuingId")));// 接收类型
		MySessionContext myc = MySessionContext.getInstance();
		HttpSession sess = myc.getSession(request.getParameter("sessionId"));
		Uservip uservip = (Uservip) sess.getAttribute(request
				.getParameter("uservip"));

		String state = "0";// 定义state 接收请求状态
		// 获取pageSize 和currentPage
		Integer pageSize = (Integer.parseInt(request.getParameter("pageSize")));// 每夜显示的记录数
		Integer currentPage = (Integer.parseInt(request
				.getParameter("currentPage")));// 当前页

		String sql = "SELECT * FROM tender t WHERE t.lssuingId = " + lssuingId
				+ " and t.userId=" + uservip.getUserId()
				+ " ORDER BY t.tenderId DESC LIMIT " + (currentPage - 1)
				* pageSize + "," + pageSize;
		List<Tender> tenderspage = null;
		try {
			tenderspage = tenderService.findTendersBylssuingIdPage(sql);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		// 返回 投资记录 列表
		StringBuffer str = new StringBuffer();
		str.append("{\"record_list\":[");
		for (int j = 0; j < tenderspage.size(); j++) {
			Tender student = tenderspage.get(j);
			String userName = tenderspage.get(j).getUservip().getUserName();
			if (userName.length() < 4) {
				userName = userName.substring(0, 1)
						+ "****"
						+ userName.substring(userName.length() - 1,
								userName.length());
			} else {
				userName = userName.substring(0, 2)
						+ "****"
						+ userName.substring(userName.length() - 2,
								userName.length());
			}
			state = "1";
			str.append("{\"userName\":\"" + userName + "\","); // 投标人
			str.append("\"money\":\"" + student.getMoney() + "\","); // 投标金额
			str.append("\"time\":\""
					+ student.getTenderTime().toString().substring(0, 10)
					+ "\"");// 投标时间
			str.append("}");
			if (j != tenderspage.size() - 1) {
				str.append(",");
			}

		}
		str.append("],");
		str.append("\"state\":\"" + state + "\",");// 请求状态 0 请求失败 1请求成功
		str.deleteCharAt(str.lastIndexOf(","));
		str.append("}");
		response.setContentType("text/json;charset=UTF-8");
		try {
			response.getWriter().print(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 新标预告
	 * 
	 * @prama
	 * @return
	 */
	public void Recommend() throws Exception {
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();

		String state = "0";// 请求状态 0 代表请求失败 1代表请求成功
		// 根据索引查询发布的预警信息
		Lssuingherald lssuingherald = lssuingheraldService
				.findLssuingheraldByindex();
		state = "1";
		// 返回新标预告信息
		StringBuffer str = new StringBuffer();
		str.append("{\"new_info\":");
		str.append("{\"lssuingherald\":\"" + lssuingherald.getLssuingHeraldId()
				+ "\",");// 新标id
		str.append("\"rate\":\"" + lssuingherald.getRate() + "\",");// 年华收益
		str.append("\"borrowMoney\":\"" + lssuingherald.getMoney() + "\",");// 债券总额
		str.append("\"time\":\"" + lssuingherald.getTime() + "\","); // 借款期限
		str.append("\"heraldTime\":\"" + lssuingherald.getHeraldTime() + "\",");// 发布时间
		str.append("\"returnWay\":\""
				+ lssuingherald.getReturnway().getReturnWayName() + "\"");// 还款方式
		str.append("},");
		str.append("\"get_info_state\":\"" + state + "\"");
		str.append("}");
		response.setContentType("text/json;charset=UTF-8");
		try {
			response.getWriter().print(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 投标 (支付接口)
	 * 
	 * @param 获取
	 *            用户帐号 投标的标志 购买金额 预计收益 支付密码 等保存
	 * @return 返回请求状态
	 */
	public void Buy() throws Exception {
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		response.setContentType("text/json;charset=UTF-8");
		MySessionContext myc = MySessionContext.getInstance();
		HttpSession sess = myc.getSession(request.getParameter("sessionId"));
		Uservip uservip = (Uservip) sess.getAttribute(request
				.getParameter("uservip"));

		int keystatus = 1;
		String message = "";
		Map map = new HashMap();

		// 获取投资金额
		Integer lssuingId = (Integer
				.parseInt(request.getParameter("lssuingId")));// 标的标识
		Integer purchaseMoney = Integer.parseInt(request
				.getParameter("purchaseMoney"));// 购买金额
		String paymentPassword = request.getParameter("paymentPassword");// 支付密码

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");

		UservipService uservipService = new UservipServiceImpl();
		if (paymentPassword == null || "".equals(paymentPassword)) {
			message = "请输入支付密码";
			keystatus = 0;
			map.put("state", "0");
			map.put("message", message);
			net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject
					.fromObject(map);
			try {
				response.getWriter().print(jsonObject.toString());
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (!uservip.getPayPwd().equals(paymentPassword)) {
			message = "您输入的支付密码不正确！";
			keystatus = 0;
			map.put("state", "0");
			map.put("message", message);
			net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject
					.fromObject(map);
			try {
				response.getWriter().print(jsonObject.toString());
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Uservip user = uservipService.findUservipByPayPwdAndUserID(
				paymentPassword, uservip.getUserId());
		if (user == null) {
			message = "您输入的支付密码不正确！";
			keystatus = 0;
			map.put("state", "0");
			map.put("message", message);
			net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject
					.fromObject(map);
			try {
				response.getWriter().print(jsonObject.toString());
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		Moneycount moneycount = this.moneycountService
				.findMoneycountByuserId(uservip.getUserId().intValue());
		if ((moneycount.getAvailableMoney().doubleValue() - Double.valueOf(
				purchaseMoney).doubleValue()) < 0) {
			message = "账户余额小于投标金额";
			map.put("state", "0");
			map.put("message", message);
			net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject
					.fromObject(map);
			try {
				response.getWriter().print(jsonObject.toString());
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		if (uservip.getTrustStatus() == 1 && uservip.getTrustAccount() != null
				&& !uservip.getTrustAccount().equals("")) {

		} else {
			message = "请先开通托管账号再进行操作";
			map.put("state", "0");
			map.put("message", message);
			net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject
					.fromObject(map);
			try {
				response.getWriter().print(jsonObject.toString());
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (uservip.getAuthorizeStatus() == 0) {
			message = "请先开通托管授权再进行操作";
			map.put("state", "0");
			map.put("message", message);
			net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject
					.fromObject(map);
			try {
				response.getWriter().print(jsonObject.toString());
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// 判断秒标是否限制投标次数
		SystemconfService systemconfService = new SystemconfServiceImpl();
		Systemconf systemconf = systemconfService
				.findSystemconfByParname("con_seconds_borrow_limit");
		int pvalue = Integer.valueOf(systemconf.getParvalue());

		Tender tender = new Tender();
		Lssuing lssuing = this.lssuingService.findLssuingById(Integer
				.valueOf(lssuingId));
		tender.setLssuing(lssuing);
		tender.setUservip(uservip);
		tender.setMoney(Integer.valueOf(purchaseMoney));

		List<Tender> tenderss = this.tenderService
				.findTendersBylssuingId(Integer.valueOf(lssuingId));
		int mon = 0;
		if (tenderss.size() == 0) {
			mon = 0;
		} else {
			for (Tender ten : tenderss) {
				mon = ten.getMoney().intValue() + mon;
				// 秒标判断用户是否已经投标
				if (pvalue == 1
						&& ten.getUservip().getUserId() == uservip.getUserId()
						&& lssuing.getLssuingType() == 3) {

					message = "此标您已经投资过了，不能进行二次投标！";
					map.put("state", "0");
					map.put("message", message);
					net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject
							.fromObject(map);
					try {
						response.getWriter().print(jsonObject.toString());
						return;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		if (Integer.valueOf(Integer.valueOf(
				tender.getLssuing().getBorrowMoney()).intValue()
				- mon) < purchaseMoney) {
			message = "您输入的金额大于剩余金额！";
			map.put("state", "0");
			map.put("message", message);
			net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject
					.fromObject(map);
			try {
				response.getWriter().print(jsonObject.toString());
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		LoanUtils loanUtils = new LoanUtils();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		tender.setTenderTime(timestamp);
		Systemconf managerconf = null;
		if ("1".equals(lssuing.getUservip().getIsVIP())) {
			managerconf = this.systemconfService
					.findSystemconfByParname("con_borrow_manage_fee_2");

		} else {
			managerconf = this.systemconfService
					.findSystemconfByParname("con_borrow_manage_fee");
		}
		Systemconf dealConf = null;
		if ("1".equals(lssuing.getUservip().getIsVIP())) {
			dealConf = this.systemconfService
					.findSystemconfByParname("con_borrow_success_fee_2");
		} else {
			dealConf = this.systemconfService
					.findSystemconfByParname("con_borrow_success_fee");
		}
		Website website = this.websiteService.findWebsiteBywebsiteId(1);
		Map tempmap = loanUtils.tgtender(uservip, tender, lssuing, managerconf,
				dealConf, website, request, false, false);

		tempmap.put("state", "1");

		net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject
				.fromObject(tempmap);
		try {
			response.getWriter().print(jsonObject.toString());
			return;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * 投标 (债权转让接口)
	 * 
	 * @param 获取
	 *            用户帐号 投标的标志 购买金额 预计收益 支付密码 等保存
	 * @return 返回请求状态
	 */
	public void transferBuy() throws Exception {
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		response.setContentType("text/json;charset=UTF-8");
		MySessionContext myc = MySessionContext.getInstance();
		HttpSession sess = myc.getSession(request.getParameter("sessionId"));
		Uservip uservip = (Uservip) sess.getAttribute(request
				.getParameter("uservip"));
		String tenderId = request.getParameter("tenderId");//转让人的投资记录id
		Transfer transfer  = this.transferService.findTransferByTenderId1(Integer.valueOf(tenderId));//根据购买id查找对象
		int keystatus = 1;
		String message = "";
		Map map = new HashMap();

		// 获取投资金额
		Integer lssuingId = (Integer
				.parseInt(request.getParameter("lssuingId")));// 标的标识
		Integer purchaseMoney = Integer.parseInt(request
				.getParameter("purchaseMoney"));// 购买金额
		String paymentPassword = request.getParameter("paymentPassword");// 支付密码

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");

		UservipService uservipService = new UservipServiceImpl();
		if (paymentPassword == null || "".equals(paymentPassword)) {
			message = "请输入支付密码";
			keystatus = 0;
			map.put("state", "0");
			map.put("message", message);
			net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject
					.fromObject(map);
			try {
				response.getWriter().print(jsonObject.toString());
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (!uservip.getPayPwd().equals(paymentPassword)) {
			message = "您输入的支付密码不正确！";
			keystatus = 0;
			map.put("state", "0");
			map.put("message", message);
			net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject
					.fromObject(map);
			try {
				response.getWriter().print(jsonObject.toString());
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Uservip user = uservipService.findUservipByPayPwdAndUserID(
				paymentPassword, uservip.getUserId());
		if (user == null) {
			message = "您输入的支付密码不正确！";
			keystatus = 0;
			map.put("state", "0");
			map.put("message", message);
			net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject
					.fromObject(map);
			try {
				response.getWriter().print(jsonObject.toString());
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		Moneycount moneycount = this.moneycountService
				.findMoneycountByuserId(uservip.getUserId().intValue());
		if ((moneycount.getAvailableMoney().doubleValue() - Double.valueOf(
				purchaseMoney).doubleValue()) < 0) {
			message = "账户余额小于投资金额";
			map.put("state", "0");
			map.put("message", message);
			net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject
					.fromObject(map);
			try {
				response.getWriter().print(jsonObject.toString());
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		Tender tender1 = this.tenderService.findTenderById(Integer.valueOf(tenderId));
		int uservipUserId = uservip.getUserId();
		int tenderUserId = 	tender1.getUservip().getUserId();
		if(uservipUserId==tenderUserId){
			message = "自己不能购买自己的债权";
			map.put("state", "0");
			map.put("message", message);
			net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject
					.fromObject(map);
			try {
				response.getWriter().print(jsonObject.toString());
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (uservip.getTrustStatus() == 1 && uservip.getTrustAccount() != null
				&& !uservip.getTrustAccount().equals("")) {

		} else {
			message = "请先开通托管账号再进行操作";
			map.put("state", "0");
			map.put("message", message);
			net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject
					.fromObject(map);
			try {
				response.getWriter().print(jsonObject.toString());
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (uservip.getAuthorizeStatus() == 0) {
			message = "请先开通托管授权再进行操作";
			map.put("state", "0");
			map.put("message", message);
			net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject
					.fromObject(map);
			try {
				response.getWriter().print(jsonObject.toString());
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


		Tender tender = new Tender();
		Lssuing lssuing = this.lssuingService.findLssuingById(Integer
				.valueOf(lssuingId));
		tender.setLssuing(lssuing);
		tender.setUservip(uservip);
		tender.setMoney(Integer.valueOf(purchaseMoney));

		List<Tender> tenderss = this.tenderService
				.findTendersBylssuingId(Integer.valueOf(lssuingId));
		int mon = 0;
		LoanUtils loanUtils = new LoanUtils();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		tender.setTenderTime(timestamp);
		Systemconf bugConf = null;
		
		bugConf = this.systemconfService.findSystemconfByParname("con_bug_fee");//认购手续费
		Website website = this.websiteService.findWebsiteBywebsiteId(1);
		Map tempmap = loanUtils.transfertgBuy(uservip, tender, transfer,lssuing, bugConf, website, request, false, false);
		tempmap.put("state", "1");

		net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject
				.fromObject(tempmap);
		try {
			response.getWriter().print(jsonObject.toString());
			return;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 投标 (理财接口)
	 * 
	 * @param 获取
	 *            用户帐号 投标的标志 购买金额 预计收益 支付密码 等保存
	 * @return 返回请求状态
	 */
	public void lcBuy() throws Exception {
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		response.setContentType("text/json;charset=UTF-8");
		MySessionContext myc = MySessionContext.getInstance();
		HttpSession sess = myc.getSession(request.getParameter("sessionId"));
		Uservip uservip = (Uservip) sess.getAttribute(request
				.getParameter("uservip"));

		int keystatus = 1;
		String message = "";
		Map map = new HashMap();

		// 获取投资金额
		Integer lssuingId = (Integer
				.parseInt(request.getParameter("lssuingId")));// 标的标识
		Integer purchaseMoney = Integer.parseInt(request
				.getParameter("purchaseMoney"));// 购买金额
		String paymentPassword = request.getParameter("paymentPassword");// 支付密码

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");

		UservipService uservipService = new UservipServiceImpl();
		if (paymentPassword == null || "".equals(paymentPassword)) {
			message = "请输入支付密码";
			keystatus = 0;
			map.put("state", "0");
			map.put("message", message);
			net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject
					.fromObject(map);
			try {
				response.getWriter().print(jsonObject.toString());
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (!uservip.getPayPwd().equals(paymentPassword)) {
			message = "您输入的支付密码不正确！";
			keystatus = 0;
			map.put("state", "0");
			map.put("message", message);
			net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject
					.fromObject(map);
			try {
				response.getWriter().print(jsonObject.toString());
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Uservip user = uservipService.findUservipByPayPwdAndUserID(
				paymentPassword, uservip.getUserId());
		if (user == null) {
			message = "您输入的支付密码不正确！";
			keystatus = 0;
			map.put("state", "0");
			map.put("message", message);
			net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject
					.fromObject(map);
			try {
				response.getWriter().print(jsonObject.toString());
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		Moneycount moneycount = this.moneycountService
				.findMoneycountByuserId(uservip.getUserId().intValue());
		if ((moneycount.getAvailableMoney().doubleValue() - Double.valueOf(
				purchaseMoney).doubleValue()) < 0) {
			message = "账户余额小于投标金额";
			map.put("state", "0");
			map.put("message", message);
			net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject
					.fromObject(map);
			try {
				response.getWriter().print(jsonObject.toString());
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		if (uservip.getTrustStatus() == 1 && uservip.getTrustAccount() != null
				&& !uservip.getTrustAccount().equals("")) {

		} else {
			message = "请先开通托管账号再进行操作";
			map.put("state", "0");
			map.put("message", message);
			net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject
					.fromObject(map);
			try {
				response.getWriter().print(jsonObject.toString());
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (uservip.getAuthorizeStatus() == 0) {
			message = "请先开通托管授权再进行操作";
			map.put("state", "0");
			map.put("message", message);
			net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject
					.fromObject(map);
			try {
				response.getWriter().print(jsonObject.toString());
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// 判断秒标是否限制投标次数
		SystemconfService systemconfService = new SystemconfServiceImpl();
		Systemconf systemconf = systemconfService
				.findSystemconfByParname("con_seconds_borrow_limit");
		int pvalue = Integer.valueOf(systemconf.getParvalue());

		Tender tender = new Tender();
		Lssuing lssuing = this.lssuingService.findLssuingById(Integer
				.valueOf(lssuingId));
		tender.setLssuing(lssuing);
		tender.setUservip(uservip);
		tender.setMoney(Integer.valueOf(purchaseMoney));

		List<Tender> tenderss = this.tenderService
				.findTendersBylssuingId(Integer.valueOf(lssuingId));
		int mon = 0;
		if (tenderss.size() == 0) {
			mon = 0;
		} else {
			for (Tender ten : tenderss) {
				mon = ten.getMoney().intValue() + mon;
				// 秒标判断用户是否已经投标
				if (pvalue == 1
						&& ten.getUservip().getUserId() == uservip.getUserId()
						&& lssuing.getLssuingType() == 3) {

					message = "此标您已经投资过了，不能进行二次投标！";
					map.put("state", "0");
					map.put("message", message);
					net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject
							.fromObject(map);
					try {
						response.getWriter().print(jsonObject.toString());
						return;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		if (Integer.valueOf(Integer.valueOf(
				tender.getLssuing().getBorrowMoney()).intValue()
				- mon) < purchaseMoney) {
			message = "您输入的金额大于剩余金额！";
			map.put("state", "0");
			map.put("message", message);
			net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject
					.fromObject(map);
			try {
				response.getWriter().print(jsonObject.toString());
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		LoanUtils loanUtils = new LoanUtils();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		tender.setTenderTime(timestamp);
		Systemconf managerconf = null;
		
		Systemconf dealConf = null;
		
		Website website = this.websiteService.findWebsiteBywebsiteId(1);
		Map tempmap = loanUtils.lctgtender(uservip, tender, lssuing, managerconf, dealConf, website, request, false, true);
		
		tempmap.put("state", "1");

		net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject
				.fromObject(tempmap);
		try {
			response.getWriter().print(jsonObject.toString());
			return;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 开通账号
	 * 
	 * @throws Exception
	 */
	public void openUser() throws Exception {

		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		response.setContentType("text/json;charset=UTF-8");
		MySessionContext myc = MySessionContext.getInstance();
		HttpSession sess = myc.getSession(request.getParameter("sessionId"));
		Uservip uservip = (Uservip) sess.getAttribute(request
				.getParameter("uservip"));

		int keystatus = 1;
		String message = "";
		Map map = new HashMap();

		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";
		// 判断是否符合条件(实名认证、手机认证)

		uservip = uservipService.findUservipByUserid(uservip.getUserId());
		if (uservip.getNameResult() == null
				|| !uservip.getNameResult().equals("2")) {
			message = "实名认证之后再开通托管账号";
			map.put("state", "0");
			map.put("message", message);
			net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject
					.fromObject(map);
			try {
				response.getWriter().print(jsonObject.toString());
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (uservip.getPhoneResult() == null
				|| !uservip.getPhoneResult().equals("1")) {
			message = "手机认证之后再开通托管账号";
			map.put("state", "0");
			map.put("message", message);
			net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject
					.fromObject(map);
			try {
				response.getWriter().print(jsonObject.toString());
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (uservip.getEnable() == null || uservip.getEnable().intValue() != 1) {
			message = "邮箱认证之后再开通托管账号";
			map.put("state", "0");
			map.put("message", message);
			net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject
					.fromObject(map);
			try {
				response.getWriter().print(jsonObject.toString());
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		CertificationService certificationService = new CertificationServiceImpl();
		Certification certification = certificationService
				.findCertificationByUserId(uservip.getUserId().intValue());
		String RealName = certification.getRealName();
		String IdentificationNo = certification.getIdNum();

		Website website = this.websiteService.findWebsiteBywebsiteId(1);

		BasicinforService basicinforService = new BasicinforServiceImpl();
		Basicinfor basicinfor = basicinforService
				.findBasicinforByUserId(uservip.getUserId().intValue());
		String Mobile = basicinfor.getPhoneNum();
		String RegisterType = "2";
		String AccountType = "";
		String Email = uservip.getMail();
		String LoanPlatformAccount = uservip.getUserName();
		String PlatformMoneymoremore = website.getTrustAccount();

		String Remark1 = uservip.getUserName() + "开通托管账号";
		String Remark2 = Remark1;
		String Remark3 = uservip.getUserId().toString();

		String ReturnURL = basePath + "user/registerbindreturn.action";
		String NotifyURL = basePath + "user/registerbindnotify.action";

		String privatekey = website.getPrivateKey();
		String RandomTimeStamp = "";
		String Image1 = "";
		String Image2 = "";
		int antistate = 0;
		if (antistate == 1) {
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			RandomTimeStamp = Common.getRandomNum(2) + sdf.format(d);
		}

		String dataStr = RegisterType + AccountType + Mobile + Email + RealName
				+ IdentificationNo + Image1 + Image2 + LoanPlatformAccount
				+ PlatformMoneymoremore + RandomTimeStamp + Remark1 + Remark2
				+ Remark3 + NotifyURL;
		// 签名
		MD5Trust md5 = new MD5Trust();
		RsaHelper rsa = RsaHelper.getInstance();
		if (antistate == 1) {
			dataStr = md5.getMD5Info(dataStr);
		}
		String SignInfo = rsa.signData(dataStr, privatekey);
		map.put("RegisterType", RegisterType);
		map.put("AccountType", AccountType);
		map.put("Image2", Image2);
		map.put("Mobile", Mobile);
		map.put("Image1", Image1);
		map.put("Email", Email);
		map.put("RealName", RealName);
		map.put("IdentificationNo", IdentificationNo);
		map.put("LoanPlatformAccount", LoanPlatformAccount);
		map.put("PlatformMoneymoremore", PlatformMoneymoremore);
		map.put("RandomTimeStamp", RandomTimeStamp);
		map.put("Remark1", Remark1);
		map.put("Remark2", Remark2);
		map.put("Remark3", Remark3);
		map.put("NotifyURL", NotifyURL);
		map.put("SignInfo", SignInfo);
		map.put("state", "1");

		net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject
				.fromObject(map);
		try {
			response.getWriter().print(jsonObject.toString());
			return;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 授权
	 * 
	 * @throws Exception
	 */
	public void authorize() throws Exception {
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		response.setContentType("text/json;charset=UTF-8");
		MySessionContext myc = MySessionContext.getInstance();
		HttpSession sess = myc.getSession(request.getParameter("sessionId"));
		Uservip uservip = (Uservip) sess.getAttribute(request.getParameter("uservip"));
		
		String message = "";
		Map map = new HashMap();
		
		if (uservip.getTrustStatus()==0 || uservip.getTrustAccount()==null || uservip.getTrustAccount().equals("")){
			message="开通托管账号后才能授权";
			map.put("state", "0");
			map.put("message", message);
			net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(map);
			try {
				response.getWriter().print(jsonObject.toString());
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()+ ":" + request.getServerPort() + path + "/";
			
		String	ReturnURL = basePath + "user/loanauthorizereturn.action";
		String	NotifyURL = basePath + "user/loanauthorizenotify.action";
		Website website = this.websiteService.findWebsiteBywebsiteId(1);
		String PlatformMoneymoremore = website.getTrustAccount();

		String MoneymoremoreId = uservip.getTrustAccount();
		String AuthorizeTypeOpen = "1,2,3";
		String AuthorizeTypeClose = "";

		String Remark1 = uservip.getUserName() + "授权操作";
		String Remark2 = Remark1;
		String Remark3 = uservip.getUserId().toString();

		String privatekey = website.getPrivateKey();
		String RandomTimeStamp = "";
		if (antistate == 1) {
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			RandomTimeStamp = Common.getRandomNum(2) + sdf.format(d);
		}

		String dataStr = MoneymoremoreId + PlatformMoneymoremore
				+ AuthorizeTypeOpen + AuthorizeTypeClose + RandomTimeStamp
				+ Remark1 + Remark2 + Remark3 + NotifyURL;
		// 签名
		MD5Trust md5 = new MD5Trust();
		RsaHelper rsa = RsaHelper.getInstance();
		if (antistate == 1) {
			dataStr = md5.getMD5Info(dataStr);
		}
		SignInfo = rsa.signData(dataStr, privatekey);
		map.put("MoneymoremoreId", MoneymoremoreId);
		map.put("PlatformMoneymoremore", PlatformMoneymoremore);
		map.put("AuthorizeTypeOpen", AuthorizeTypeOpen);
		map.put("AuthorizeTypeClose", AuthorizeTypeClose);
		map.put("RandomTimeStamp", RandomTimeStamp);
		map.put("Remark1", Remark1);
		map.put("Remark2", Remark2);
		map.put("Remark3", Remark3);
		map.put("NotifyURL", NotifyURL);
		map.put("SignInfo", SignInfo);
		map.put("state", "1");

		net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(map);
		try {
			response.getWriter().print(jsonObject.toString());
			return;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 充值
	 * 
	 * @throws Exception
	 */
	public void recharge() throws Exception {
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		response.setContentType("text/json;charset=UTF-8");
		MySessionContext myc= MySessionContext.getInstance();
		HttpSession sess = myc.getSession(request.getParameter("sessionId"));
		Uservip uservip = (Uservip) sess.getAttribute(request.getParameter("uservip"));
		
		
		double chargemoney = Double.valueOf(request.getParameter("chargemoney"));// 购买金额
		
		int keystatus = 1;
		String message = "";
		Map map = new HashMap();
		if (uservip.getTrustStatus() == 1 && uservip.getTrustAccount() != null
				&& !uservip.getTrustAccount().equals("")) {

		} else {
			message = "请先开通托管账号再进行操作";
			map.put("state", "0");
			map.put("message", message);
			net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject
					.fromObject(map);
			try {
				response.getWriter().print(jsonObject.toString());
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (uservip.getAuthorizeStatus() == 0) {
			message = "请先开通托管授权再进行操作";
			map.put("state", "0");
			map.put("message", message);
			net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject
					.fromObject(map);
			try {
				response.getWriter().print(jsonObject.toString());
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		uservip = this.uservipService.findUservipByUserid(uservip.getUserId()
				.intValue());
		Offlinerecharge offlinerecharge = new Offlinerecharge();
		offlinerecharge.setRechargeAmount(chargemoney);
		offlinerecharge
				.setRechargeTime(new Timestamp(new Date().getTime()));
		offlinerecharge.setRechargeStatus(2);
		offlinerecharge.setUservip(uservip);
		offlinerecharge.setRecharge("托管支付");
		offlinerechargeService.createOfflinerecharge(offlinerecharge);
		WebsiteService websiteService = new WebsiteServiceImpl();
		Website website = websiteService.findWebsiteBywebsiteId(1);
		LoanUtils loanutils = new LoanUtils();
		request = ServletActionContext.getRequest();
		map = loanutils.appLoanRecharge(request, website, uservip,
				offlinerecharge);
		map.put("state", "1");
		net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject
				.fromObject(map);
		try {
			response.getWriter().print(jsonObject.toString());
			return;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 验证用户状态
	 * @throws Exception
	 */
	public void checkUserState() throws Exception {
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		response.setContentType("text/json;charset=UTF-8");
		MySessionContext myc= MySessionContext.getInstance();
		HttpSession sess = myc.getSession(request.getParameter("sessionId"));
		Uservip uservip = (Uservip) sess.getAttribute(request.getParameter("uservip"));
		String message = "";
		Map map = new HashMap();
		if (uservip.getTrustStatus() == 1 && uservip.getTrustAccount() != null
				&& !uservip.getTrustAccount().equals("")) {

		} else {
			message = "请先开通托管账号再进行操作";
			map.put("state", "0");
			map.put("message", message);
			net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject
					.fromObject(map);
			try {
				response.getWriter().print(jsonObject.toString());
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (uservip.getAuthorizeStatus() == 0) {
			message = "请先开通托管授权再进行操作";
			map.put("state", "0");
			map.put("message", message);
			net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject
					.fromObject(map);
			try {
				response.getWriter().print(jsonObject.toString());
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		map.put("state", "1");
		net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject
				.fromObject(map);
		try {
			response.getWriter().print(jsonObject.toString());
			return;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 发送手机提现验证码
	 * @throws Exception
	 */
	public void sendMessage()throws Exception{
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		response.setContentType("text/json;charset=UTF-8");
		MySessionContext myc= MySessionContext.getInstance();
		HttpSession sess = myc.getSession(request.getParameter("sessionId"));
		Uservip uservip = (Uservip) sess.getAttribute(request.getParameter("uservip"));
		
		String message = "";
		Map map = new HashMap();
		if (uservip.getTrustStatus() == 1 && uservip.getTrustAccount() != null
				&& !uservip.getTrustAccount().equals("")) {

		} else {
			message = "请先开通托管账号再进行操作";
			map.put("state", "0");
			map.put("message", message);
			net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject
					.fromObject(map);
			try {
				response.getWriter().print(jsonObject.toString());
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (uservip.getAuthorizeStatus() == 0) {
			message = "请先开通托管授权再进行操作";
			map.put("state", "0");
			map.put("message", message);
			net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject
					.fromObject(map);
			try {
				response.getWriter().print(jsonObject.toString());
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Basicinfor basicinfor = this.basicinforService
				.findBasicinforByUserId(uservip.getUserId().intValue());
		if(basicinfor.getPhoneNum()==null || basicinfor.getPhoneNum().equals("")){
			message = "手机还没认证，请先认证手机";
			map.put("state", "0");
			map.put("message", message);
			net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject
					.fromObject(map);
			try {
				response.getWriter().print(jsonObject.toString());
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		Phone phone = this.phoneService.findPhoneByOpen();
		Messagemodel messagemodel = this.messagemodelService
				.findMessagemodelByintegralPid(14);
		String contents = messagemodel.getMessModelContent();
		if (contents.indexOf("#USERNAME#") != -1) {
			contents = contents.replaceAll("#USERNAME#",
					uservip.getUserName());
		}
		if (contents.indexOf("#KEY#") != -1) {
			String key = "";
			while (key.length() < 6) {
				key = key + (int) (Math.random() * 10.0D);
			}
			contents = contents.replaceAll("#KEY#", key);

			ActionContext.getContext().getSession()
					.put("key" + uservip.getUserId(), key);
			System.err.println("key:" + key);
		}

		boolean key = false;
		if (phone.getPhoneId().intValue() == 1) {
			Client client = new Client(phone.getPhoneName(),
					phone.getPhonePassword());
			String result_mt = client.mdSmsSend_u(basicinfor.getPhoneNum(), contents, "",
					"", "");
			System.out.println(basicinfor.getPhoneNum());
			if ((result_mt.startsWith("-")) || ("".equals(result_mt))) {
				System.out.print("发送失败！返回值为：" + result_mt
						+ "请查看webservice返回值对照表");

			} else {
				System.out.print("发送成功，返回值为：" + result_mt);
		
			}
			key = true;
			System.out.print("发送成功，返回值为：" + result_mt);

			System.out.println(phone.getPhoneName() + "   "
					+ phone.getPhonePassword() + "   " + basicinfor.getPhoneNum() + "   "
					+ contents);
		}
		if (phone.getPhoneId().intValue() == 2) {
			String url = "http://115.29.242.32:8888/sms.aspx";
			SmsClientSend.sendSms(url, phone.getPhoneuserId(), phone.getPhoneName(), phone.getPhonePassword(), basicinfor.getPhoneNum(), contents);
//			
			System.out.println(phone.getPhoneName() + phone.getPhonePassword()
					+ basicinfor.getPhoneNum() + contents);

			key = true;
		}
		if (key) {
			SmsSendLog ssl = new SmsSendLog();
			ssl.setContent(contents);
			ssl.setSendTime(new Timestamp(new Date().getTime()));
			ssl.setTophone(basicinfor.getPhoneNum());
			ssl.setTitle("");
			ssl.setUservip(uservip);
			ssl.setStatus(1);
			SmsSendLogService sslService = new SmsSendLogServiceImpl();
			sslService.createSmsSendLog(ssl);
			message = "发送成功";
			map.put("state", "1");
			map.put("message", message);
			net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject
					.fromObject(map);
			try {
				response.getWriter().print(jsonObject.toString());
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			message = "发送失败";
			map.put("state", "0");
			map.put("message", message);
			net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject
					.fromObject(map);
			try {
				response.getWriter().print(jsonObject.toString());
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void withdraw() throws Exception {
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		response.setContentType("text/json;charset=UTF-8");
		MySessionContext myc= MySessionContext.getInstance();
		HttpSession sess = myc.getSession(request.getParameter("sessionId"));
		Uservip uservip = (Uservip) sess.getAttribute(request.getParameter("uservip"));

		int keystatus = 1;
		String message = "";
		Map map = new HashMap();
		
		double cashAmount = Double.valueOf(request
				.getParameter("cashAmount"));// 购买金额
		String paymentPassword = request.getParameter("paymentPassword");// 支付密码
		String cashcode = request.getParameter("code");
		int bankPid = Integer.valueOf(request.getParameter("bankPid"));
		
		Uservip uservip1 = this.uservipService.findUservipByPayPwdAndUserName(paymentPassword, uservip.getUserName());
		String keys = (String) ActionContext.getContext().getSession()
				.get("key" + uservip.getUserId());
		if (uservip1 == null) {
			message = "您输入的支付密码错误";
			map.put("state", "0");
			map.put("message", message);
			net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject
					.fromObject(map);
			try {
				response.getWriter().print(jsonObject.toString());
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (!keys.equals(cashcode)) {
			message = "手机验证码错误";
			map.put("state", "0");
			map.put("message", message);
			net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject
					.fromObject(map);
			try {
				response.getWriter().print(jsonObject.toString());
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			Moneycount moneycount = this.moneycountService
					.findMoneycountByuserId(uservip.getUserId().intValue());
			if(moneycount.getAvailableMoney()<cashAmount){
				message = "提现金额大于余额，请重新输入提现金额";
				map.put("state", "0");
				map.put("message", message);
				net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject
						.fromObject(map);
				try {
					response.getWriter().print(jsonObject.toString());
					return;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			Personalbankinfor personalbankinfor = this.personalbankinforService
					.findPersonalbankinforById(bankPid);
			
			Translate translate = new Translate();
			translate.setTranslateId(Integer.valueOf(1));
			translate.setUservip(uservip);
			translate.setAffectMoney(cashAmount);
			translate.setBankNum(personalbankinfor.getBankparameter()
					.getBankPname()
					+ ","
					+ personalbankinfor.getAccountAddress()
					+ ","
					+ personalbankinfor.getAccountNum());
			translate.setOccurTime(timestamp);
			translate.setAvailableBalance(Double.valueOf(moneycount
					.getAvailableMoney().doubleValue()
					- Double.valueOf(cashAmount)
							.doubleValue()));
			translate.setState(Integer.valueOf(0));
			translate.setBanktypeId(personalbankinfor.getBankparameter().getBankPid());
			translate.setBankCityId(personalbankinfor.getArea().getAreaId());
			translate.setBankProvinceId(personalbankinfor.getArea().getParentid());
			translate.setSerialnum("");			

			boolean flag = this.translateService.addTranslate(translate);
			if (flag) {
				LoanUtils loanUtils = new LoanUtils();
				HttpServletRequest request = ServletActionContext.getRequest();
				Website website = this.websiteService.findWebsiteBywebsiteId(1);
				Uservip cashuser = uservip;
			    Map tempmap =	loanUtils.apploanWithdraws(request, website, translate, cashuser);
			    tempmap.put("state", "1");
			    tempmap.put("bankcard", personalbankinfor.getAccountNum());
			    net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject
						.fromObject(tempmap);
				try {
					response.getWriter().print(jsonObject.toString());
					return;
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				message = "提现失败，请重新申请";
				map.put("state", "0");
				map.put("message", message);
				net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject
						.fromObject(map);
				try {
					response.getWriter().print(jsonObject.toString());
					return;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	/**
	 * 最新活动接口
	 */
	public void GetActivity() throws Exception {
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		Integer newsId = Integer.parseInt(request.getParameter("newsId"));
		String state = "0";
		net.sf.json.JSONObject json = new net.sf.json.JSONObject();
		Map map = new HashMap();
		List list = new ArrayList();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 当有newsId的时候根据id查找文章内容
		if (newsId != 0 && !"".equals(newsId)) {
			Latestnews latestnews = latestnewsService
					.findLatestnewsByLatestnewsId(newsId);
			state = "1";
			map.put("publishTime", sdf.format(latestnews.getPublishTime()));
			map.put("content", latestnews.getContent());
			map.put("get_activity_state", state);
		} else {// 当没有userId的时候查找所有文章
			List<Latestnews> latestnewss = latestnewsService
					.findLatestnewssByfrontMenuId(4);
			state = "1";
			for (int i = 0; i < latestnewss.size(); i++) {
				Map map1 = new HashMap();
				map1.put("newsId", latestnewss.get(i).getNewsId());
				map1.put("title", latestnewss.get(i).getTitle());// 文章标题
				map1.put("publishTime", sdf.format(latestnewss.get(i).getPublishTime()));// 发布时间
				list.add(map1);
			}
			map.put("advertisemen_list", list);
			map.put("get_activity_state", state);// 请求状态 0// 请求失败 1
		}
		json=json.fromObject(map);
		response.setContentType("text/json;charset=UTF-8");
		try {
			response.getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 借款记录接口
	 * 
	 * @return borrow_list集合
	 */
	public void lssuing() throws Exception {
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		String state = "0";// 0请求失败 1请求成功
		MySessionContext myc = MySessionContext.getInstance();
		HttpSession sess = myc.getSession(request.getParameter("sessionId"));
		Uservip uservip = (Uservip) sess.getAttribute(request
				.getParameter("uservip"));
		// 获取pageSize 和currentPage
		Integer pageSize = (Integer.parseInt(request.getParameter("pageSize")));// 每夜显示的记录数
		Integer currentPage = (Integer.parseInt(request
				.getParameter("currentPage")));// 当前页
		String sql = "select * from Lssuing l where  l.userId="
				+ uservip.getUserId() + " order by l.borrowTime desc "
				+ "limit " + (currentPage - 1) * pageSize + "," + pageSize;
		List<Lssuing> lssuingList = lssuingService.findLssuingsBySearch(sql);
		state = "1";// 请求成功
		/**
		 * 返回数据
		 */
		StringBuffer str = new StringBuffer();
		// 投资列表
		str.append("{\"borrow_list\":[");
		for (int i = 0; i < lssuingList.size(); i++) {
			str.append("{\"lssuingNum\":\""
					+ lssuingList.get(i).getLssuingNum() + "\",");// 借款id
			str.append("\"returnWay\":\""
					+ lssuingList.get(i).getReturnway().getReturnWayName()
					+ "\","); // 还款方式
			str.append("\"borrowMoney\":\""
					+ lssuingList.get(i).getBorrowMoney() + "\",");// 借款金额
			str.append("\"scale\":\"" + lssuingList.get(i).getScale() + "\","); // 借款进度
			str.append("\"borrowTime\":\"" + lssuingList.get(i).getBorrowTime()
					+ "\"");// 借款时间
			str.append("}");
			if (i != lssuingList.size() - 1) {
				str.append(",");
			}
		}
		str.append("],\r\n");
		str.append("\"borrow_state\":\"" + state + "\",");// 请求状态 0 失败 成功
		str.deleteCharAt(str.lastIndexOf(","));
		str.append("}");
		// response.setContentType("text/html charset=UTF-8") ;
		response.setContentType("text/json;charset=UTF-8");
		try {
			response.getWriter().print(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 开通托管账号
	 * 
	 * @return
	 * @throws Exception
	 */
	public void registerbind() throws Exception {
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		String state = "0";// 0请求失败 1请求成功
		String message="";
		MySessionContext myc = MySessionContext.getInstance();
		HttpSession sess = myc.getSession(request.getParameter("sessionId"));
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";
		// 判断是否符合条件(实名认证、手机认证)
		Uservip uservip = (Uservip) ActionContext.getContext().getSession()
				.get("uservip");
		uservip = uservipService.findUservipByUserid(uservip.getUserId());
		if (uservip.getNameResult() == null
				|| !uservip.getNameResult().equals("2")) {
 
			state="0";
			message="实名认证之后再开通托管账号";
 
		}
		/**
              
		 */
		if (uservip.getPhoneResult() == null
				|| !uservip.getPhoneResult().equals("1")) {
	 
			state="0";
			message="手机认证之后再开通托管账号";
		}
		if (uservip.getEnable() == null || uservip.getEnable().intValue() != 1) {
			 
			state="0";
			message="邮箱认证之后再开通托管账号";
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
		StringBuffer str = new StringBuffer();
		this.LoanPlatformAccount = uservip.getUserName();
		this.PlatformMoneymoremore = website.getTrustAccount();

		this.Remark1 = this.Remark2 = uservip.getUserName() + "开通托管账号";
		this.Remark3 = uservip.getUserId().toString();
		try {
			if (istest) {
			
			 
				this.SubmitURL = "http://218.4.234.150:88/main/loan/toloanregisterbind.action";
			} else {
				this.SubmitURL = "https://register.moneymoremore.com/loan/toloanregisterbind.action";
			}
			// SubmitURL = SubmitURLPrefix + "loan/toloanregisterbind.action";
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
			Map<String, String> req = new TreeMap<String, String>();
			if (this.RegisterType.equals("1")) {
				str.append("{\"borrow_list\":[");
 				str.append("\"RegisterType\":\"" + RegisterType + "\",");  
 				str.append("\"AccountType\":\"" + AccountType + "\",");  		 
				str.append("\"Mobile\":\"" + Mobile + "\","); 	 
				str.append("\"Email\":\"" + Email + "\",");  	 
				str.append("\"RealName\":\"" + RealName + "\",");  	 
				str.append("\"IdentificationNo\":\"" + IdentificationNo + "\",");  	 
				str.append("\"Image1\":\"" + Image1 + "\",");  		 
				str.append("\"Image2\":\"" + Image2 + "\",");  			 
				str.append("\"LoanPlatformAccount\":\"" + LoanPlatformAccount + "\",");  		 
				str.append("\"PlatformMoneymoremore\":\"" + PlatformMoneymoremore + "\",");  			 
				str.append("\"RandomTimeStamp\":\"" + RandomTimeStamp + "\","); 		 
				str.append("\"Remark1\":\"" + Remark1 + "\",");  			 
				str.append("\"Remark2\":\"" + Remark2 + "\",");  			 
				str.append("\"Remark3\":\"" + Remark3 + "\",");  			 
				str.append("\"ReturnURL\":\"" + ReturnURL + "\",");  		 
				str.append("\"NotifyURL\":\"" + NotifyURL + "\",");  
				str.append("\"SignInfo\":\"" + SignInfo + "\",");   
				str.append("],\r\n");
				state="1";
				message="成功";
			}else{
				state="0";
				message="失败";
				}
			
		}catch(Exception e ){
			e.printStackTrace();
		}
		
		 
		
 

		str.append("\"state\":\"" + state + "\",");// 请求状态 0 失败 成功
		str.append("\"message\":\"" + message + "\",");// 请求状态 0 失败 成功
		str.deleteCharAt(str.lastIndexOf(","));
		str.append("}");
		// response.setContentType("text/html charset=UTF-8") ;
		response.setContentType("text/json;charset=UTF-8");
		try {
			response.getWriter().print(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 封装方法
	 * 
	 * @return
	 */
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

	private void setMessage(String string, String string2, String string3) {
	}
}
