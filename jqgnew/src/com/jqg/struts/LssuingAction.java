package com.jqg.struts;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.jqg.pojo.Automaticbid;
import com.jqg.pojo.Inbox;
import com.jqg.pojo.Lssingphoto;
import com.jqg.pojo.Lssuing;
import com.jqg.pojo.LssuingComp;
import com.jqg.pojo.Manager;
import com.jqg.pojo.Moneycount;
import com.jqg.pojo.Moneyhistorydetail;
import com.jqg.pojo.Moneymax;
import com.jqg.pojo.Moneymin;
import com.jqg.pojo.Moneyuse;
import com.jqg.pojo.Periodday;
import com.jqg.pojo.Periodtime;
import com.jqg.pojo.Platcount;
import com.jqg.pojo.Record;
import com.jqg.pojo.Repaynote;
import com.jqg.pojo.Returnway;
import com.jqg.pojo.Systemconf;
import com.jqg.pojo.Tender;
import com.jqg.pojo.Transfer;
import com.jqg.pojo.Uservip;
import com.jqg.pojo.Validtime;
import com.jqg.pojo.Website;
import com.jqg.service.AutomaticbidService;
import com.jqg.service.EventtypeService;
import com.jqg.service.InboxService;
import com.jqg.service.LssingphotoService;
import com.jqg.service.LssuingService;
import com.jqg.service.ManagerService;
import com.jqg.service.MoneycountService;
import com.jqg.service.MoneyhistorydetailService;
import com.jqg.service.MoneymaxService;
import com.jqg.service.MoneyminService;
import com.jqg.service.MoneyuseService;
import com.jqg.service.OerationlogService;
import com.jqg.service.PerioddayService;
import com.jqg.service.PeriodtimeService;
import com.jqg.service.PlatcountService;
import com.jqg.service.RecordService;
import com.jqg.service.RepaynoteService;
import com.jqg.service.ReturnwayService;
import com.jqg.service.SystemconfService;
import com.jqg.service.TenderService;
import com.jqg.service.TransferService;
import com.jqg.service.UservipService;
import com.jqg.service.ValidtimeService;
import com.jqg.service.WebsiteService;
import com.jqg.service.impl.AutomaticbidServiceImpl;
import com.jqg.service.impl.EventtypeServiceImpl;
import com.jqg.service.impl.InboxServiceImpl;
import com.jqg.service.impl.LssingphotoServiceImpl;
import com.jqg.service.impl.LssuingServiceImpl;
import com.jqg.service.impl.MoneycountServiceImpl;
import com.jqg.service.impl.MoneyhistorydetailServiceImpl;
import com.jqg.service.impl.MoneymaxServiceImpl;
import com.jqg.service.impl.MoneyminServiceImpl;
import com.jqg.service.impl.MoneyuseServiceImpl;
import com.jqg.service.impl.PeriodDayServiceImpl;
import com.jqg.service.impl.PeriodtimeServiceImpl;
import com.jqg.service.impl.PlatcountServiceImpl;
import com.jqg.service.impl.RecordServiceImpl;
import com.jqg.service.impl.RepaynoteServiceImpl;
import com.jqg.service.impl.ReturnwayServiceImpl;
import com.jqg.service.impl.SystemconfServiceImpl;
import com.jqg.service.impl.TenderServiceImpl;
import com.jqg.service.impl.TransferServiceImpl;
import com.jqg.service.impl.UservipServiceImpl;
import com.jqg.service.impl.ValidtimeServiceImpl;
import com.jqg.service.impl.WebsiteServiceImpl;
import com.jqg.util.LoanUtils;
import com.jqg.util.OverbueCom;
import com.opensymphony.xwork2.ActionContext;

public class LssuingAction extends BaseAction {
	private String result;
	private Lssuing lssuing;
	private int lssuingId;
	private List<Lssuing> lssuingList;
	private List<Moneyuse> moneyuseList;
	private List<Moneymin> moneyminList;
	private List<Moneymax> moneymaxList;
	private List<Validtime> validtimeList;
	private List<Returnway> returnwayList;
	private List<Periodtime> periodtimeList;
	private List<Periodday> perioddayList;
	private List<Uservip> uservipList;
	private List<Lssingphoto> lssuingphotoList;
	private int moneyUseId;
	private int periodtime;
	private int moneymin;
	private int moneymax;
	private int validtime;
	private int returnway;
	private int periodday;
	private int isUpdate = 0;
	private Automaticbid automaticbid;
	private int automaticBidId;
	private int automaticBidNumberFlag;
	private List<Automaticbid> automaticbidList;
	private int userId;
	private int mark;
	private int queryFlag;
	private String phoneStr = "";
	private List<Repaynote> repaynoteList;
	private LssuingService lssuingService = new LssuingServiceImpl();
	private MoneyuseService moneyuseService = new MoneyuseServiceImpl();
	private MoneyminService moneyminService = new MoneyminServiceImpl();
	private MoneymaxService moneymaxService = new MoneymaxServiceImpl();
	private ValidtimeService validtimeService = new ValidtimeServiceImpl();
	private ReturnwayService returnwayService = new ReturnwayServiceImpl();
	private PeriodtimeService periodtimeService = new PeriodtimeServiceImpl();
	private PerioddayService perioddayService = new PeriodDayServiceImpl();
	private AutomaticbidService automaticbidService = new AutomaticbidServiceImpl();
	private UservipService uservipService = new UservipServiceImpl();
	private RepaynoteService repaynoteService = new RepaynoteServiceImpl();
	private RecordService recordService = new RecordServiceImpl();
	private MoneycountService moneycountService = new MoneycountServiceImpl();
	private MoneyhistorydetailService moneyhistorydetailService = new MoneyhistorydetailServiceImpl();
	private EventtypeService eventtypeService = new EventtypeServiceImpl();
	private TenderService tenderService = new TenderServiceImpl();
	private InboxService inboxService = new InboxServiceImpl();
	private LssingphotoService lssingphotoService = new LssingphotoServiceImpl();
	private TransferService transferService = new TransferServiceImpl();
	private String oneMon;
	private String twoMon;
	private String threeMon;
	private String fourMon;
	private Integer page = Integer.valueOf(0);
	private Integer pageSize = Integer.valueOf(5);
	private Integer pageCount;
	private int gotoFlag = 0;
	private BigDecimal needReturnMon;
	private Integer repayNoteId;
	private int resultReturn;
	private int updateFlag;
	private String postform;//托管提交form表单信息

	public int getUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(int updateFlag) {
		this.updateFlag = updateFlag;
	}

	private Integer allMoney = Integer.valueOf(0);

	/**
	 * 发标页面初始化
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toLssuing() throws Exception {
		this.moneyuseList = this.moneyuseService.findMoneyuses();
		this.moneymaxList = this.moneymaxService.findMoneymaxs();
		this.moneyminList = this.moneyminService.findMoneymins();
		this.periodtimeList = this.periodtimeService.findPeriodtimes();
		this.returnwayList = this.returnwayService.findReturnways();
		this.validtimeList = this.validtimeService.findValidtimes();
		this.perioddayList = this.perioddayService.findPerioddays();
		this.uservipList = this.uservipService.findUservips();
		this.lssuing = new Lssuing();
		this.lssuing.setLssuingNum(new LssuingAction().getNum());

		return "success";
	}

	/**
	 * 保存发标
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveLssuing() throws Exception {
        this.lssuing.setRecommend(0);
		if (this.lssuing.getBorrowMoney() == null
				|| "".equals(this.lssuing.getBorrowMoney())
				|| !StringUtils.isNumeric(this.lssuing.getBorrowMoney())) {
			this.setMessage("请输入正确的借款金额", "borrow/bid.jsp", "3");
			return "success";
		}
		double borrowMoney = Double.valueOf(this.lssuing.getBorrowMoney());
		if (borrowMoney <= 0 || borrowMoney % 50 != 0) {
			this.setMessage("借款金额必须是50的倍数", "borrow/bid.jsp", "3");
			return "success";
		}

		if (this.moneyUseId == 0) {
			this.setMessage("请选择借款用途", "borrow/bid.jsp", "3");
			return "success";
		}
		if (this.lssuing.getTitle() == null
				|| "".equals(this.lssuing.getTitle())) {
			this.setMessage("借款标题不能为空", "borrow/bid.jsp", "3");
			return "success";
		}
		if (this.lssuing.getExplains() == null
				|| "".equals(this.lssuing.getExplains())) {
			this.setMessage("借款说明不能为空", "borrow/bid.jsp", "3");
			return "success";
		}
		if (this.lssuing.getIsOrient() != null
				&& this.lssuing.getIsOrient() == 1) {
			if (this.lssuing.getOrientPassword() == null
					|| "".equals(this.lssuing.getOrientPassword())) {
				this.setMessage("定向标请输入投标密码", "borrow/bid.jsp", "3");
				return "success";
			}
		}

		if (this.periodtime != 0) {
			this.lssuing.setPeriodtime(this.periodtimeService
					.findPeriodtimeByPeriodtimeId(this.periodtime));
			if (this.returnway == 2) {
				this.setMessage("月标不能按天到期还款", "borrow/bid.jsp", "3");
				return "success";
			}
		}
		Uservip uservip = (Uservip) ActionContext.getContext().getSession()
				.get("uservip");
		if (uservip == null || uservip.getUserId() == null || this.userId == 0) {
			this.setMessage("请登录之后操作", "borrow/bid.jsp", "3");
			return "success";
		} else {
			this.userId = uservip.getUserId();
		}
		if (uservip.getQuota() < borrowMoney || uservip.getQuota() == null
				|| "".equals(uservip.getQuota())) {
			this.setMessage("信用额度不足", "borrow/bid.jsp", "3");
			return "success";
		}

		if (this.periodday == 0 && this.periodtime == 0) {
			this.setMessage("请选择借款期限", "borrow/bid.jsp", "3");
			return "success";
		}
		if (this.returnway == 0) {
			this.setMessage("请选择还款方式", "borrow/bid.jsp", "3");
			return "success";
		}
		if (this.lssuing.getRate() == null || this.lssuing.getRate() <= 0) {
			this.setMessage("请输入正确的利率", "borrow/bid.jsp", "3");
			return "success";
		}
		if (this.validtime == 0) {
			this.setMessage("请选择正确的投标有效时间", "borrow/bid.jsp", "3");
			return "success";
		}

		this.lssuing.setMoneymin(this.moneyminService.findMoneyminById(Integer
				.valueOf(this.moneymin)));
		this.lssuing.setMoneymax(this.moneymaxService.findMoneymaxById(Integer
				.valueOf(this.moneymax)));
		this.lssuing.setValidtime(this.validtimeService
				.findValidtimeById(Integer.valueOf(this.validtime)));
		this.lssuing.setReturnway(this.returnwayService
				.findReturnwayById(Integer.valueOf(this.returnway)));
		if (this.periodday != 0) {
			this.lssuing.setPeriodday(this.perioddayService
					.findPerioddayByPerioddayId(this.periodday));
			this.lssuing.setRate(Double.valueOf(this.lssuing.getRate()
					.doubleValue()));
			if (this.returnway == 3 || this.returnway == 4) {
				this.setMessage("天标不能按月还款", "borrow/bid.jsp", "3");
				return "success";
			}
		}
		this.lssuing.setMoneyuse(this.moneyuseService.findMoneyuseById(Integer
				.valueOf(this.moneyUseId)));

		this.lssuing.setReturnMoney(Double.valueOf(0.0D));
		this.lssuing.setBorrowTime(new Timestamp(new Date().getTime()));
		this.lssuing.setUservip(this.uservipService
				.findUservipByUserid(this.userId));
		this.lssuing.setLssuingType(Integer.valueOf(this.mark));
		this.lssuing.setState(Integer.valueOf(0));
		this.lssuing.setIsInvest(0);
		this.lssuing.setReturnMoney(Double.valueOf(0.0D));
		Boolean flag = Boolean.valueOf(this.lssuingService
				.addLssuing(this.lssuing));
		if (flag.booleanValue()) {

			this.setMessage("发标成功，等待审核!", "borrow/bid.jsp", "3");

			return "success";
		}

		this.setMessage("借款发布失败!", "borrow/bid.jsp", "3");
		return "success";
	}
	
	/**
	 * 保存体验发标
	 * 
	 * @return
	 * @throws Exception
	 */
	public String savePracticeLssuing() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String userId = request.getParameter("user");
		if (this.lssuing.getBorrowMoney() == null
				|| "".equals(this.lssuing.getBorrowMoney())
				|| !StringUtils.isNumeric(this.lssuing.getBorrowMoney())) {
			this.setMessage("请输入正确的借款金额", "/borrow/toLssuing2", "3");
			return "success";
		}
        this.lssuing.setRecommend(0);
		double borrowMoney = Double.valueOf(this.lssuing.getBorrowMoney());
		if (borrowMoney <= 0 || borrowMoney % 50 != 0) {
			this.setMessage("借款金额必须是50的倍数", "/borrow/toLssuing2", "3");
			return "success";
		}
		if(userId==null || "".equals(userId)){
			this.setMessage("请输入借款人", "/borrow/toLssuing2", "3");
			return "success";
		}else{
			Uservip uservip = this.uservipService.findUservipByUserid(Integer.valueOf(userId));
			if(Double.valueOf(String.valueOf(uservip.getQuota()))< borrowMoney){
				this.setMessage("此借款人的信用额度不足", "/borrow/toLssuing2", "3");
				return "success";
			}
		}

		if (this.moneyUseId == 0) {
			this.setMessage("请选择借款用途", "/borrow/toLssuing2", "3");
			return "success";
		}
		if (this.lssuing.getTitle() == null
				|| "".equals(this.lssuing.getTitle())) {
			this.setMessage("借款标题不能为空", "/borrow/toLssuing2", "3");
			return "success";
		}
		if (this.lssuing.getExplains() == null
				|| "".equals(this.lssuing.getExplains())) {
			this.setMessage("借款说明不能为空", "/borrow/toLssuing2", "3");
			return "success";
		}

		if (this.periodtime != 0) {
			this.lssuing.setPeriodtime(this.periodtimeService
					.findPeriodtimeByPeriodtimeId(this.periodtime));
			if (this.returnway == 2) {
				this.setMessage("月标不能按天到期还款", "/borrow/toLssuing2", "3");
				return "success";
			}
		}
//		Uservip uservip = (Uservip) ActionContext.getContext().getSession()
//				.get("uservip");
//		if (uservip == null || uservip.getUserId() == null || this.userId == 0) {
//			this.setMessage("请登录之后操作", "borrow/bid.jsp", "3");
//			return "success";
//		} else {
//			this.userId = uservip.getUserId();
//		}
//		if (uservip.getQuota() < borrowMoney || uservip.getQuota() == null
//				|| "".equals(uservip.getQuota())) {
//			this.setMessage("信用额度不足", "borrow/bid.jsp", "3");
//			return "success";
//		}

		if (this.periodday == 0 && this.periodtime == 0) {
			this.setMessage("请选择借款期限", "/borrow/toLssuing2", "3");
			return "success";
		}
		if (this.returnway == 0) {
			this.setMessage("请选择还款方式", "/borrow/toLssuing2", "3");
			return "success";
		}
		if (this.lssuing.getRate() == null || this.lssuing.getRate() <= 0) {
			this.setMessage("请输入正确的利率", "/borrow/toLssuing2", "3");
			return "success";
		}
		if (this.validtime == 0) {
			this.setMessage("请选择正确的投标有效时间", "/borrow/toLssuing2", "3");
			return "success";
		}

		this.lssuing.setMoneymin(this.moneyminService.findMoneyminById(Integer
				.valueOf(this.moneymin)));
		this.lssuing.setMoneymax(this.moneymaxService.findMoneymaxById(Integer
				.valueOf(this.moneymax)));
		this.lssuing.setValidtime(this.validtimeService
				.findValidtimeById(Integer.valueOf(this.validtime)));
		this.lssuing.setReturnway(this.returnwayService
				.findReturnwayById(Integer.valueOf(this.returnway)));
		if (this.periodday != 0) {
			this.lssuing.setPeriodday(this.perioddayService
					.findPerioddayByPerioddayId(this.periodday));
			this.lssuing.setRate(Double.valueOf(this.lssuing.getRate()
					.doubleValue()));
			if (this.returnway == 3 || this.returnway == 4) {
				this.setMessage("天标不能按月还款", "/borrow/toLssuing2", "3");
				return "success";
			}
		}
		this.lssuing.setMoneyuse(this.moneyuseService.findMoneyuseById(Integer.valueOf(this.moneyUseId)));
		this.lssuing.setReturnMoney(Double.valueOf(0.0D));
		this.lssuing.setBorrowTime(new Timestamp(new Date().getTime()));
		this.lssuing.setUservip(this.uservipService.findUservipByUserid(Integer.valueOf(userId)));
		this.lssuing.setLssuingType(Integer.valueOf(8));
		this.lssuing.setState(Integer.valueOf(2));
		this.lssuing.setIsInvest(0);
		this.lssuing.setReturnMoney(Double.valueOf(0.0D));
		this.lssuing.setIsAward(Integer.valueOf(0));
		this.lssuing.setTenderLimit(Integer.valueOf(0));
		this.lssuing.setIsOrient(Integer.valueOf(0));
		Boolean flag = Boolean.valueOf(this.lssuingService.addLssuing(this.lssuing));
		if (flag.booleanValue()) {

			this.setMessage("发标成功，等待审核!", "/borrow/toLssuing2", "3");

			return "success";
		}

		this.setMessage("借款发布失败!", "/borrow/toLssuing2", "3");
		return "success";
	}

	public String getNum() throws Exception {
		Timestamp now = new Timestamp(new Date().getTime());
		String t = now.toString().substring(0, 10);
		this.lssuingList = this.lssuingService
				.findLssuingsBySearch("select * from Lssuing l where l.lssuingType != 6 and  l.borrowTime like '"
						+ t + "%'");
		String a = "";
		if (this.lssuingList != null) {
			if (this.lssuingList.size() == 0)
				a = t.replace("-", "") + "A";
			if (this.lssuingList.size() == 1)
				a = t.replace("-", "") + "B";
			if (this.lssuingList.size() == 2)
				a = t.replace("-", "") + "C";
			if (this.lssuingList.size() == 3)
				a = t.replace("-", "") + "D";
			if (this.lssuingList.size() == 4)
				a = t.replace("-", "") + "E";
			if (this.lssuingList.size() == 5)
				a = t.replace("-", "") + "F";
			if (this.lssuingList.size() == 6)
				a = t.replace("-", "") + "G";
			if (this.lssuingList.size() == 7)
				a = t.replace("-", "") + "H";
			if (this.lssuingList.size() == 8)
				a = t.replace("-", "") + "I";
			if (this.lssuingList.size() == 9)
				a = t.replace("-", "") + "J";
			if (this.lssuingList.size() == 10)
				a = t.replace("-", "") + "K";
			if (this.lssuingList.size() == 11)
				a = t.replace("-", "") + "L";
			if (this.lssuingList.size() == 12)
				a = t.replace("-", "") + "M";
			if (this.lssuingList.size() == 13)
				a = t.replace("-", "") + "N";
			if (this.lssuingList.size() == 14)
				a = t.replace("-", "") + "O";
			if (this.lssuingList.size() == 15)
				a = t.replace("-", "") + "P";
			if (this.lssuingList.size() == 16)
				a = t.replace("-", "") + "Q";
			if (this.lssuingList.size() == 17)
				a = t.replace("-", "") + "R";
			if (this.lssuingList.size() == 18)
				a = t.replace("-", "") + "S";
			if (this.lssuingList.size() == 19)
				a = t.replace("-", "") + "T";
			if (this.lssuingList.size() == 20)
				a = t.replace("-", "") + "U";
			if (this.lssuingList.size() == 21)
				a = t.replace("-", "") + "V";
			if (this.lssuingList.size() == 22)
				a = t.replace("-", "") + "W";
			if (this.lssuingList.size() == 23)
				a = t.replace("-", "") + "X";
			if (this.lssuingList.size() == 24)
				a = t.replace("-", "") + "Y";
			if (this.lssuingList.size() == 25)
				a = t.replace("-", "") + "Z";
			if (this.lssuingList.size() == 26)
				a = t.replace("-", "") + "AA";
			if (this.lssuingList.size() == 27)
				a = t.replace("-", "") + "AB";
			if (this.lssuingList.size() == 28)
				a = t.replace("-", "") + "AC";
			if (this.lssuingList.size() == 29)
				a = t.replace("-", "") + "AD";
			if (this.lssuingList.size() == 30)
				a = t.replace("-", "") + "AE";
			if (this.lssuingList.size() == 30)
				a = t.replace("-", "") + "AF";
		}
		return a;
	}

	public static void main(String[] args) {
		String a = "2014-09-02";
		System.out.println(a.replace("-", ""));
	}

	/**
	 * 自动投标页面初始化
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toAutomaticbid() throws Exception {
		this.returnwayList = this.returnwayService.findReturnways();
		this.periodtimeList = this.periodtimeService.findPeriodtimes();
		String autoSQl = "select * from Automaticbid a where a.userId="
				+ this.userId + " order by a.automaticBidId";
		this.automaticbidList = this.automaticbidService
				.findAutomaticbidBySql(autoSQl);
		this.mark = this.automaticbidService.queryCountAutomaticbid();
		this.automaticbid = new Automaticbid();
		this.isUpdate = 0;
		this.automaticbid.setIsType(Integer.valueOf(1));
		List automaticbids = this.automaticbidService
				.findAutomaticbidBySql("select * from Automaticbid a where a.enable=1 order by a.automaticBidId");
		if ((automaticbids != null) && (automaticbids.size() > 0)) {
			for (int i = 0; i < automaticbids.size(); i++) {
				Moneycount moneycount = this.moneycountService
						.findMoneycountByuserId(((Automaticbid) automaticbids
								.get(i)).getUservip().getUserId().intValue());
				if (!"任意值".equals(((Automaticbid) automaticbids.get(i))
						.getAutomaticBidNumber()))
					this.allMoney = Integer.valueOf(this.allMoney.intValue()
							+ Integer.parseInt(((Automaticbid) automaticbids
									.get(i)).getAutomaticBidNumber()));
				else {
					this.allMoney = Integer.valueOf(this.allMoney.intValue()
							+ (int) moneycount.getAvailableMoney()
									.doubleValue());
				}
			}
		}

		return "success";
	}

	/**
	 * 自动投标页面-新增
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addAutomaticbid() throws Exception {
		this.automaticbid.setReturnway(this.returnwayService
				.findReturnwayById(Integer.valueOf(this.returnway)));
		this.automaticbid.setUservip(this.uservipService
				.findUservipByUserid(this.userId));
		if (this.queryFlag != 2) {
			this.automaticbid.setPeriodtime(this.periodtimeService
					.findPeriodtimeByPeriodtimeId(this.periodtime));
		}
		this.automaticbid.setEndTime(new Timestamp(new Date().getTime()));
		this.automaticbid.setIsType(Integer
				.valueOf(this.automaticBidNumberFlag));
		List ads = this.automaticbidService
				.findAutomaticbidBySql("select * from Automaticbid a where a.enable=1 order by a.ordernum desc");
		if (this.automaticbid.getEnable().intValue() == 1) {
			if (ads.size() > 0)
				this.automaticbid.setOrderNum(Integer
						.valueOf(((Automaticbid) ads.get(0)).getOrderNum()
								.intValue() + 1));
			else
				this.automaticbid.setOrderNum(Integer.valueOf(1));
		} else {
			this.automaticbid.setOrderNum(Integer.valueOf(0));
		}
		if (this.isUpdate == 0) {
			this.automaticbidService.createAutomaticbid(this.automaticbid);
		} else
			this.automaticbidService.updateAutomaticbid(this.automaticbid);

		return "success";
	}

	/**
	 * 自动投标页面-修改页面初始化
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toUpdateAutomaticbid() throws Exception {
		this.periodtimeList = this.periodtimeService.findPeriodtimes();
		this.returnwayList = this.returnwayService.findReturnways();
		this.automaticbidList = this.automaticbidService.findAutomaticbids();
		this.mark = this.automaticbidService.queryCountAutomaticbid();
		this.automaticbid = this.automaticbidService
				.findAutomaticbidByautomaticbidId(this.automaticBidId);

		String autoSQl = "select * from Automaticbid a where a.userId="
				+ this.userId + " order by a.automaticBidId";
		this.automaticbidList = this.automaticbidService
				.findAutomaticbidBySql(autoSQl);
		this.isUpdate = 1;
		return "success";
	}

	/**
	 * 自动投标页面-删除
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toDelete() throws Exception {
		Automaticbid automaticbid2 = new Automaticbid();
		automaticbid2.setAutomaticBidId(Integer.valueOf(this.automaticBidId));
		this.automaticbidService.deleteAutomaticbid(automaticbid2);

		this.returnwayList = this.returnwayService.findReturnways();
		String autoSQl = "select * from Automaticbid a where a.userId="
				+ this.userId;
		this.automaticbidList = this.automaticbidService
				.findAutomaticbidBySql(autoSQl);
		this.mark = this.automaticbidService.queryCountAutomaticbid();
		return "success";
	}

	/**
	 * 借款总表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toBorrowTotal() throws Exception {
		this.mark = 1;
		BigDecimal moneyOne = new BigDecimal("0");
		BigDecimal moneyTwo = new BigDecimal("0");
		BigDecimal moneyThree = new BigDecimal("0");
		int num1 = 0;
		int num2 = 0;
		int num3 = 0;
		String sql = "select * from Lssuing l where l.lssuingType != 6 and  l.userId="
				+ this.userId;
		this.lssuingList = this.lssuingService.findLssuingsBySearch(sql);
		if ((this.lssuingList != null) && (this.lssuingList.size() > 0)) {
			for (int i = 0; i < this.lssuingList.size(); i++) {
				Lssuing ls = (Lssuing) this.lssuingList.get(i);
				if (ls.getState().intValue() == 2) {
					moneyOne = moneyOne
							.add(new BigDecimal(ls.getBorrowMoney()));
					num1++;
				} else if (ls.getState().intValue() == 3) {
					moneyTwo = moneyTwo
							.add(new BigDecimal(ls.getBorrowMoney()));
					num2++;
				} else if (ls.getState().intValue() == 4) {
					moneyThree = moneyThree.add(new BigDecimal(ls
							.getBorrowMoney()));
					num3++;
				}
			}
		}

		this.oneMon = (moneyOne + "/" + num1);
		this.twoMon = (moneyTwo + "/" + num2);
		this.threeMon = (moneyThree + "/" + num3);
		this.fourMon = (moneyTwo.add(moneyThree) + "/" + (num2 + num3));
		return "success";
	}

	/**
	 * 发布列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toLssuingList() throws Exception {
		String sql = "";
		int stateFlag = 0;
		if (this.queryFlag == 2) {
			stateFlag = 2;
			this.mark = 2;
		} else if (this.queryFlag == 3) {
			stateFlag = 3;
			this.mark = 3;
		} else if (this.queryFlag == 4) {
			stateFlag = 5;
			this.mark = 4;
		} else if (this.queryFlag == 5) {
			stateFlag = 6;
			this.mark = 5;
		} else if (this.queryFlag == 6) {
			stateFlag = 4;
			this.mark = 6;
		}
		sql = "select * from Lssuing l where  l.userId=" + this.userId
				+ " and l.state=" + stateFlag + " order by borrowTime desc";
		List list = this.lssuingService.findLssuingsBySearch(sql);
		Integer total = Integer.valueOf(list.size());
		if (this.page.intValue() == 0) {
			this.page = Integer.valueOf(1);
		}
		if (total.intValue() == 0)
			this.pageCount = Integer.valueOf(1);
		else if (total.intValue() % this.pageSize.intValue() == 0)
			this.pageCount = Integer.valueOf(total.intValue()
					/ this.pageSize.intValue());
		else {
			this.pageCount = Integer.valueOf(total.intValue()
					/ this.pageSize.intValue() + 1);
		}
		sql = "select * from Lssuing l where  l.userId=" + this.userId
				+ " and l.state=" + stateFlag + " order by l.borrowTime desc "
				+ "limit " + (this.page.intValue() - 1)
				* this.pageSize.intValue() + "," + this.pageSize;
		this.lssuingList = this.lssuingService.findLssuingsBySearch(sql);
		List<Lssuing> lssuings=new ArrayList<Lssuing>();
		for(Lssuing lss:lssuingList){
			String sqll="select * from repaynote r where r.repayState=0 and r.lssuingId="+lss.getLssuingId()+" order by r.repayNoteId asc";
			List<Repaynote> repaynotes=this.repaynoteService.findRepayBySql(sqll);
			if(repaynotes.size()>0){
				for(int i=0;i<repaynotes.size();i++){
					if(lss.getEveryReturnTime()!=null &&!"".equals(lss.getEveryReturnTime()) ){
						
					}else{
						lss.setEveryReturnTime(repaynotes.get(0).getRepayDate());
					}
				}
			} 
			lssuings.add(lss);
		}
		ActionContext.getContext().put("lssuings", lssuings);
		if (this.gotoFlag == 1) {
			return "toReturn";
		}
		return "success";
	}

	/**
	 * 还款列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toRePayList() throws Exception {
		List<Repaynote> temprepaynoteList = this.repaynoteService
				.findRepaynoteBylssuing(this.lssuingId);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowdate = df.format(new Date());
		Timestamp nowtime = new Timestamp(sdf.parse(nowdate + " 00:00:00")
				.getTime());
		// vip会员的逾期罚息
		Lssuing lssuing = this.lssuingService.findLssuingById(this.lssuingId);
		double overfee1=0.0;
		double overfee2=0.0;
		double overfee3=0.0;
		double overfee4=0.0;
		if(lssuing.getLssuingType()!=9){
		Uservip uservip = this.uservipService.findUservipByUserid(lssuing
				.getUservip().getUserId());
		Systemconf latefee1 = null;
		Systemconf latefee2 = null;
		Systemconf latefee3 = null;
		Systemconf latefee4 = null;
		SystemconfService systemconfService = new SystemconfServiceImpl();
		if ("1".equals(uservip.getIsVIP())) {// vip会员
			latefee1 = systemconfService
					.findSystemconfByParname("con_borrow_late_fee_3_2");
			latefee2 = systemconfService
					.findSystemconfByParname("con_borrow_late_fee_30_2");
			latefee3 = systemconfService
					.findSystemconfByParname("con_borrow_late_fee_90_2");
			latefee4 = systemconfService
					.findSystemconfByParname("con_borrow_late_fee_2");
		} else {
			latefee1 = systemconfService
					.findSystemconfByParname("con_borrow_late_fee_3");
			latefee2 = systemconfService
					.findSystemconfByParname("con_borrow_late_fee_30");
			latefee3 = systemconfService
					.findSystemconfByParname("con_borrow_late_fee_90");
			latefee4 = systemconfService
					.findSystemconfByParname("con_borrow_late_fee");
		}
		 overfee1 = Double.valueOf(latefee1.getParvalue());
		 overfee2 = Double.valueOf(latefee2.getParvalue());
		 overfee3 = Double.valueOf(latefee3.getParvalue());
		 overfee4 = Double.valueOf(latefee4.getParvalue());
		}
		this.repaynoteList = new ArrayList<Repaynote>();
		if (temprepaynoteList != null && temprepaynoteList.size() > 0) {
			for (int i = 0; i < temprepaynoteList.size(); i++) {
				Repaynote repaynote = temprepaynoteList.get(i);
				if (repaynote.getRepayState() == 0) {
					if (repaynote.getRepayDate().after(nowtime)) {
						repaynote.setOverdays(0);
						repaynote.setOverInterest(0.0);
					} else {
						repaynote = OverbueCom.comoverInterest(repaynote,
								overfee1, overfee2, overfee3, overfee4);
						// repaynote.setMoneyFour(repaynote.getMoneyFour()+repaynote.getOverInterest());
					}
				}
				if (repaynote.getRepayState() == 2) {
					repaynote = OverbueCom.comoverInterest(repaynote, overfee1,
							overfee2, overfee3, overfee4);
					repaynote.setMoneyThree(0.0);
					// repaynote.setMoneyFour(repaynote.getMoneyFour()+repaynote.getOverInterest());
				}
				this.repaynoteList.add(repaynote);
			}
		}

		if ((this.repaynoteList != null) && (this.repaynoteList.size() > 0)) {
			this.result = ((Repaynote) this.repaynoteList.get(0)).getLssuing()
					.getTitle();
		}
		if (this.resultReturn == 1)
			this.mark = 22;
		else if (this.resultReturn == 2)
			this.mark = 222;
		else {
			this.mark = 2;
		}

		return "success";
	}

	public String toFenMoney() throws Exception {
		Repaynote repaynote = this.repaynoteService
				.findRepaynoteByRepaynoteId(this.repayNoteId.intValue());
		Moneycount moneycount = this.moneycountService
				.findMoneycountByuserId(this.userId);
		if ((moneycount != null)
				&& (repaynote.getMoneyFour().doubleValue() > moneycount
						.getAvailableMoney().doubleValue())) {
			this.resultReturn = 2;
			return "success";
		}
		if (repaynote.getRepayState() == 1) {
			return "success";
		}

		this.lssuing = this.lssuingService.findLssuingById(Integer
				.valueOf(this.lssuingId));
		SystemconfService systemconfService = new SystemconfServiceImpl();
		Systemconf sysconf = systemconfService
				.findSystemconfByParname("overdue_time");
		String parvalue = sysconf.getParvalue();
		SimpleDateFormat datedf = new SimpleDateFormat("yyyy-MM-dd");
		DecimalFormat df = new DecimalFormat("##.00");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowdate = datedf.format(new Date());
		String overtime = nowdate + " " + parvalue;
		if (sdf.parse(overtime).getTime() >= new Date().getTime()) {
			overtime = nowdate + " 00:00:00";
		}
		double webabccount = repaynote.getMoneyFour();
		Timestamp nowtime = new Timestamp(sdf.parse(overtime).getTime());
		repaynote.setOverdays(0);
		repaynote.setOverInterest(0.0);
		boolean key = false;
		double overfee1 = 0.0;
		double overfee2 = 0.0;
		double overfee3 = 0.0;
		double overfee4 = 0.0;
		if (repaynote.getRepayDate().getTime() < nowtime.getTime()) {
			Uservip uservip = this.uservipService
					.findUservipByUserid(this.lssuing.getUservip().getUserId());
			if ("1".equals(uservip.getIsVIP())) {
				Systemconf latefee1 = systemconfService
						.findSystemconfByParname("con_borrow_late_fee_3_2");
				overfee1 = Double.valueOf(latefee1.getParvalue());

				Systemconf latefee2 = systemconfService
						.findSystemconfByParname("con_borrow_late_fee_30_2");
				overfee2 = Double.valueOf(latefee2.getParvalue());

				Systemconf latefee3 = systemconfService
						.findSystemconfByParname("con_borrow_late_fee_90_2");
				overfee3 = Double.valueOf(latefee3.getParvalue());

				Systemconf latefee4 = systemconfService
						.findSystemconfByParname("con_borrow_late_fee_2");
				overfee4 = Double.valueOf(latefee4.getParvalue());
			} else {

				Systemconf latefee1 = systemconfService
						.findSystemconfByParname("con_borrow_late_fee_3");
				overfee1 = Double.valueOf(latefee1.getParvalue());

				Systemconf latefee2 = systemconfService
						.findSystemconfByParname("con_borrow_late_fee_30");
				overfee2 = Double.valueOf(latefee2.getParvalue());

				Systemconf latefee3 = systemconfService
						.findSystemconfByParname("con_borrow_late_fee_90");
				overfee3 = Double.valueOf(latefee3.getParvalue());

				Systemconf latefee4 = systemconfService
						.findSystemconfByParname("con_borrow_late_fee");
				overfee4 = Double.valueOf(latefee4.getParvalue());
			}

			repaynote = OverbueCom.comoverInterest(repaynote, overfee1,
					overfee2, overfee3, overfee4);
			// repaynote.setMoneyFour(repaynote.getMoneyFour()+repaynote.getOverInterest());
			key = true;
		}

		WebsiteService websiteService = new WebsiteServiceImpl();
		Website website = websiteService.findWebsiteBywebsiteId(1);
		HttpServletRequest request = ServletActionContext.getRequest();
		this.repaynoteService.updateRepaynote(repaynote);
		LoanUtils loanUtils = new LoanUtils();
		if (repaynote.getRepayState() == 2) {// 处理网站垫付的资金
			this.postform = loanUtils.borrowRepay(repaynote, false, website, null, request, lssuing,null,null);
		} else {
			Timestamp now = repaynote.getRepayDate();
			String t = now.toString().substring(0, 10);
			List<Record> records = new ArrayList<Record>();
			String sql = "select * from Record r  where  r.tenderId in (select tenderId from Tender t where t.lssuingId="
					+ this.lssuingId
					+ ")"
					+ " and r.recordDate like '"
					+ t
					+ "%' and recordState=0";
			records = this.recordService.findRecordByRecordId(sql);
			List<Record> recordlist = new ArrayList<Record>();
			// 遍历第个标的投资用户
			if ((records != null) && (records.size() > 0)) {
				for (int i = 0; i < records.size(); i++) {
					Record record = records.get(i);
//					record.setRecordState(Integer.valueOf(1));
					if (key) {
						record = OverbueCom.comOverInterest(record, overfee1,
								overfee2, overfee3, overfee4);
					} else {
						record.setOverdays(0);
						record.setOverInterest(0.0);
						
					}
					record.setRepaymentDate(Timestamp.valueOf(sdf.format(new Date())));//新添加字段还款时间
//					record.setRecordInterest(Double.valueOf(record
//							.getRecordMoney().doubleValue()
//							+ record.getRecordRate().doubleValue()
//							+ record.getOverInterest()));

					this.recordService.updateRecord(record);
					recordlist.add(record);
					
				
//					Transfer transfer = transferService.findTransferByTenderId1(record.getTender().getTenderId());
//					
//					if(transfer!=null){
//						if(transfer.getIsTransfer()==0 || transfer.getIsTransfer()==1){
//							transfer.setIsTransfer(3);
//							transferService.updateTransfer(transfer);
//						}
//					}
//					boolean istransfer = true;
//					if(transfer!=null && transfer.getIsTransfer()==2){//已经转出
//					}

				
				}
			}
			Systemconf sysconf1 = systemconfService
					.findSystemconfByParname("con_vip_rate_money");
			Systemconf sysconf2 = systemconfService
					.findSystemconfByParname("con_rate_money");
			this.postform = loanUtils.borrowRepay(repaynote, false, website, recordlist, request, lssuing,sysconf1,sysconf2);
		}

		return "loanjump";
	}

	private void addInbox(String c, String a, Integer b) throws Exception {
		Website website = (Website) ActionContext.getContext().getSession()
				.get("websiteinfo");
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
	 * 保存企业标
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveFirmLssuing() throws Exception {

		if (this.lssuing.getBorrowMoney() == null
				|| "".equals(this.lssuing.getBorrowMoney())
				|| !StringUtils.isNumeric(this.lssuing.getBorrowMoney())) {
			this.setMessage("请输入正确的借款金额", "/borrow/toFirmView", "5");
			return "success";
		}

		if (this.lssuing.getUservip().getUserId() == null
				|| "".equals(this.lssuing.getUservip().getUserId())) {
			this.setMessage("投标人不能为空", "/borrow/toFirmView", "5");
			return "success";
		}

		if (this.lssuing.getTitle() == null
				|| "".equals(this.lssuing.getTitle())) {
			this.setMessage("借款标题不能为空", "/borrow/toFirmView", "5");
			return "success";
		}

		if (lssuing.getPeriodday().getPeriodDayId() != 0
				|| lssuing.getPeriodtime().getPeriodTimeId() != 0) {
			if (lssuing.getPeriodday().getPeriodDayId() != 0) {

				if (lssuing.getReturnway().getReturnWayId() == 3
						|| lssuing.getReturnway().getReturnWayId() == 4) {
					this.setMessage("天标不能按月还款", "/borrow/toFirmView", "5");
					return "success";
				}
			} else {
				this.lssuing.setPeriodday(null);
			}
			if (lssuing.getPeriodtime().getPeriodTimeId() == 0) {
				this.lssuing.setPeriodtime(null);
			}
		} else {
			this.setMessage("请填写借款期限", "/borrow/toFirmView", "5");
			return "success";
		}
		Moneymin moneymin = this.moneyminService.findMoneyminById(lssuing.getMoneymin().getMoneyMinId());
		this.lssuing.setUnitmoney(Double.valueOf(String.valueOf(moneymin.getMoneyMinName())));
		this.lssuing.setTenderLimit(Integer.valueOf(0));
		this.lssuing.setLssuingType(6);// 企业直投
		this.lssuing.setReturnMoney(Double.valueOf(0.0D));
		this.lssuing.setBorrowTime(new Timestamp(new Date().getTime()));
		this.lssuing.setManager((Manager) ActionContext.getContext()
				.getSession().get("manager"));
		this.lssuing.setState(Integer.valueOf(2));
		this.lssuing.setExplainOne("企业直投初审自动通过");
		this.lssuing.setIsInvest(0);
		this.lssuing.setIsAward(0);
		this.lssuing.setIsOrient(0);
        this.lssuing.setRecommend(0);
		this.lssuing.setDealTime(new Timestamp(new Date().getTime()));
		this.lssuing.setVerify_time(new Timestamp(new Date().getTime()));
		Date date = new Date(System.currentTimeMillis());
		String strDate = new SimpleDateFormat("yyyyMMddhhmmss").format(date);
		this.lssuing.setLssuingNum("QY" + strDate);
		this.lssuing.setReturnMoney(Double.valueOf(0.0D));
		Boolean flag = Boolean.valueOf(this.lssuingService
				.addLssuing(this.lssuing));
		if (flag.booleanValue()) {
			Lssingphoto lp;
			if (this.phoneStr != null && !"".equals(this.phoneStr)
					&& !",".equals(this.phoneStr)) {
				String[] str = this.phoneStr.split(",");
				for (int i = 0; i < str.length; i++) {
					lp = new Lssingphoto();
					lp.setLssuing(this.lssuing);
					lp.setPhoto(str[i]);
					lssingphotoService.addLssingphoto(lp);
				}
			}

			this.setMessage("发标成功!", "/borrow/toFirmView", "3");
			return "success";
		}

		this.setMessage("借款发布失败!", "/borrow/toFirmView", "3");
		return "success";
	}
	/**
	 * 保存理财宝
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveLcLssuing() throws Exception {

		if (this.lssuing.getBorrowMoney() == null
				|| "".equals(this.lssuing.getBorrowMoney())
				|| !StringUtils.isNumeric(this.lssuing.getBorrowMoney())) {
			this.setMessage("请输入正确的借款金额", "/borrow/toLcView", "3");
			return "success";
		}

//		if (this.lssuing.getUservip().getUserId() == null
//				|| "".equals(this.lssuing.getUservip().getUserId())) {
//			this.setMessage("投标人不能为空", "/borrow/toLcView", "3");
//			return "success";
//		}

		if (this.lssuing.getTitle() == null
				|| "".equals(this.lssuing.getTitle())) {
			this.setMessage("标题不能为空", "/borrow/toLcView", "3");
			return "success";
		}

		if (lssuing.getPeriodday().getPeriodDayId() != 0
				|| lssuing.getPeriodtime().getPeriodTimeId() != 0) {
			if (lssuing.getPeriodday().getPeriodDayId() != 0) {

				if (lssuing.getReturnway().getReturnWayId() == 3
						|| lssuing.getReturnway().getReturnWayId() == 4) {
					this.setMessage("天标不能按月还款", "/borrow/toLcView", "3");
					return "success";
				}
			} else {
				this.lssuing.setPeriodday(null);
			}
			if (lssuing.getPeriodtime().getPeriodTimeId() == 0) {
				this.lssuing.setPeriodtime(null);
			}
		} else {
			this.setMessage("请填写期限", "/borrow/toLcView", "3");
			return "success";
		}
		this.lssuing.setLssuingType(9);// 理财宝
		this.lssuing.setReturnMoney(Double.valueOf(0.0D));
		this.lssuing.setBorrowTime(new Timestamp(new Date().getTime()));
		this.lssuing.setManager((Manager) ActionContext.getContext().getSession().get("manager"));
		this.lssuing.setState(Integer.valueOf(2));
		this.lssuing.setExplainOne("理财宝初审自动通过");
		this.lssuing.setIsInvest(0);
		this.lssuing.setIsAward(0);
		this.lssuing.setIsOrient(0);
        this.lssuing.setRecommend(0);
		this.lssuing.setDealTime(new Timestamp(new Date().getTime()));
		this.lssuing.setVerify_time(new Timestamp(new Date().getTime()));
		Date date = new Date(System.currentTimeMillis());
		String strDate = new SimpleDateFormat("yyyyMMddhhmmss").format(date);
		this.lssuing.setLssuingNum("LC" + strDate);
		this.lssuing.setReturnMoney(Double.valueOf(0.0D));
		Boolean flag = Boolean.valueOf(this.lssuingService
				.addLssuing(this.lssuing));
		if (flag.booleanValue()) {
			Lssingphoto lp;
			if (this.phoneStr != null && !"".equals(this.phoneStr)
					&& !",".equals(this.phoneStr)) {
				String[] str = this.phoneStr.split(",");
				for (int i = 0; i < str.length; i++) {
					lp = new Lssingphoto();
					lp.setLssuing(this.lssuing);
					lp.setPhoto(str[i]);
					lssingphotoService.addLssingphoto(lp);
				}
			}

			this.setMessage("发布成功!", "/borrow/toLcView", "3");
			return "success";
		}

		this.setMessage("发布失败!", "/borrow/toLcView", "3");
		return "success";
	}
	/**
	 * 根据借款id查找企业直投信息
	 * 
	 * @return
	 */
	public String findFirmLssuing() throws Exception {
		this.uservipList = this.uservipService.findUservips();
		this.moneyminList = this.moneyminService.findMoneymins();
		this.periodtimeList = this.periodtimeService.findPeriodtimes();
		this.returnwayList = this.returnwayService.findReturnways();
		this.validtimeList = this.validtimeService.findValidtimes();
		this.perioddayList = this.perioddayService.findPerioddays();
		this.moneymaxList = this.moneymaxService.findMoneymaxs();
		this.lssuingList = lssuingService
				.findLssuingsBySearch("select * from lssuing l,lssuingComp lc where l.compId=lc.compId and l.lssuingType= 6 and l.lssuingId="
						+ this.lssuingId + ";");
		this.lssuingphotoList = lssingphotoService
				.findLssingphotosBylssuing(this.lssuingId);
		ActionContext.getContext().put("uservipList", uservipList);
		ActionContext.getContext().put("moneyminList", moneyminList);
		ActionContext.getContext().put("periodtimeList", periodtimeList);
		ActionContext.getContext().put("returnwayList", returnwayList);
		ActionContext.getContext().put("validtimeList", validtimeList);
		ActionContext.getContext().put("perioddayList", perioddayList);
		ActionContext.getContext().put("moneymaxList", moneymaxList);
		ActionContext.getContext().put("lssuingList", lssuingList);
		ActionContext.getContext().put("lssuingphotoList", lssuingphotoList);
		return "success";
	}

	/**
	 * 修改企业直投信息
	 * 
	 * @return
	 */
	public String updateFirmLssuing() throws Exception {
		if (this.lssuing.getBorrowMoney() == null
				|| "".equals(this.lssuing.getBorrowMoney())
				|| !StringUtils.isNumeric(this.lssuing.getBorrowMoney())) {
			this.setMessage("请输入正确的借款金额", "back/2_updateFirm.jsp", "5");
			return "success";
		}
		if (this.lssuing.getTitle() == null
				|| "".equals(this.lssuing.getTitle())) {
			this.setMessage("借款标题不能为空", "back/2_updateFirm.jsp", "5");
			return "success";
		}
		if (lssuing.getPeriodday().getPeriodDayId() != 0
				|| lssuing.getPeriodtime().getPeriodTimeId() != 0) {
			if (lssuing.getPeriodday().getPeriodDayId() != 0) {

				if (lssuing.getReturnway().getReturnWayId() == 3
						|| lssuing.getReturnway().getReturnWayId() == 4) {
					this.setMessage("天标不能按月还款", "back/2_updateFirm.jsp", "5");
					return "success";
				}
			} else {
				this.lssuing.setPeriodday(null);
			}
			if (lssuing.getPeriodtime().getPeriodTimeId() == 0) {
				this.lssuing.setPeriodtime(null);
			}
		} else {
			this.setMessage("请填写借款期限", "back/2_updateFirm.jsp", "5");
			return "success";
		}
		Lssuing ls = this.lssuingService.findLssuingById(this.lssuing
				.getLssuingId());
		if (this.updateFlag == 1) {
			ls.setTitle(this.lssuing.getTitle());
			ls.setReturnway(this.returnwayService.findReturnwayById(lssuing
					.getReturnway().getReturnWayId()));
			ls.setUservip(this.lssuing.getUservip());
			ls.setBorrowMoney(this.lssuing.getBorrowMoney());
			ls.setMoneymin(this.moneyminService.findMoneyminById(lssuing
					.getMoneymin().getMoneyMinId()));
			ls.setTotalUnit(this.lssuing.getTotalUnit());
			ls.setRate(this.lssuing.getRate());
			if (lssuing.getPeriodday().getPeriodDayId() != null) {
				ls.setPeriodday(this.perioddayService
						.findPerioddayByPerioddayId(lssuing.getPeriodday()
								.getPeriodDayId()));
				ls.setPeriodtime(null);
			} else {
				ls.setPeriodday(null);
				ls.setPeriodtime(this.periodtimeService
						.findPeriodtimeByPeriodtimeId(lssuing.getPeriodtime()
								.getPeriodTimeId()));
			}
			ls.setAward(this.lssuing.getAward());
			ls.setCopies(this.lssuing.getCopies());
			ls.setManageMoney(this.lssuing.getManageMoney());
			ls.setLssingphotos(this.lssuing.getLssingphotos());
			// ls.setLssuingComp(this.lssuing.getLssuingComp());
			LssuingComp lssuingComp = this.lssuing.getLssuingComp();
			// 根据借款方id查找借款方信息
			LssuingComp lComp = this.lssuingService
					.findLssuingCompById(lssuingComp.getCompId());
			lComp.setCompInfo(lssuingComp.getCompInfo());
			lComp.setShowImg(lssuingComp.getShowImg());
			lComp.setBorrowUse(lssuingComp.getBorrowUse());
			lComp.setCompFund(lssuingComp.getCompFund());
			lComp.setCompWinCon(lssuingComp.getCompWinCon());
			ls.setLssuingComp(lComp);
			Boolean fla = Boolean.valueOf(this.lssuingService
					.updateLssuingComp(lComp));// 更新lssuingComp表
			Boolean flag = Boolean.valueOf(this.lssuingService
					.updateLssuing(ls));// 更新lssuing表
			if (flag.booleanValue() && fla.booleanValue()) {
				Lssingphoto lp;
				if (this.phoneStr != null && !"".equals(this.phoneStr)
						&& !",".equals(this.phoneStr)) {
					String[] str = this.phoneStr.split(",");
					for (int i = 0; i < str.length; i++) {
						lp = new Lssingphoto();
						lp.setLssuing(this.lssuing);
						lp.setPhoto(str[i]);
						lssingphotoService.updateLssingphoto(lp);
					}
				}
			}
			this.queryFlag = queryFlag;
		}
		return "success";

	}

	/**
	 * 根据借款id 查找招标中借款
	 * 
	 * @return
	 */
	public String findLssuingById() throws Exception {
		this.periodtimeList = this.periodtimeService.findPeriodtimes();
		this.perioddayList = this.perioddayService.findPerioddays();
		this.returnwayList = this.returnwayService.findReturnways();
		this.moneymaxList = this.moneymaxService.findMoneymaxs();
		Lssuing lssuing = lssuingService.findLssuingById(this.lssuingId);
		ActionContext.getContext().put("periodtimeList", periodtimeList);
		ActionContext.getContext().put("perioddayList", perioddayList);
		ActionContext.getContext().put("returnwayList", returnwayList);
		ActionContext.getContext().put("lssuing", lssuing);
		return "success";
	}

	/**
	 * 修改招标中的借款信息
	 * 
	 * @return
	 */
	public String updateLssuing() throws Exception {
		Lssuing ls = this.lssuingService.findLssuingById(Integer
				.valueOf(this.lssuingId));
		if (this.updateFlag == 1) {
			ls.setExplains(this.lssuing.getExplains());
			ls.setMoneymax(this.moneymaxService.findMoneymaxById(Integer
					.valueOf(this.moneymax)));

			this.lssuingService.updateLssuing(ls);// 更新lssuing表
		}
		return "success";
	}
	/**
	 * 保存新手发标
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveNewHandLssuing() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String userId = request.getParameter("user");
		if (this.lssuing.getBorrowMoney() == null
				|| "".equals(this.lssuing.getBorrowMoney())
				|| !StringUtils.isNumeric(this.lssuing.getBorrowMoney())) {
			this.setMessage("请输入正确的借款金额", "/borrow/toLssuing2", "3");
			return "success";
		}
		double borrowMoney = Double.valueOf(this.lssuing.getBorrowMoney());
		if (borrowMoney <= 0 || borrowMoney % 50 != 0) {
			this.setMessage("借款金额必须是50的倍数", "/borrow/toLssuing2", "3");
			return "success";
		}
		if(userId==null || "".equals(userId)){
			this.setMessage("请输入借款人", "/borrow/toLssuing2", "3");
			return "success";
		}else{
			Uservip uservip = this.uservipService.findUservipByUserid(Integer.valueOf(userId));
			if(uservip.getQuota()!=null){
				if(Double.valueOf(String.valueOf(uservip.getQuota()))< borrowMoney){
					this.setMessage("此借款人的信用额度不足", "/borrow/toLssuing2", "3");
					return "success";
				}
			}else{
				this.setMessage("此借款人的信用额度不足", "/borrow/toLssuing2", "3");
				return "success";
			}
			
		}

		if (this.moneyUseId == 0) {
			this.setMessage("请选择借款用途", "/borrow/toLssuing2", "3");
			return "success";
		}
		if (this.lssuing.getTitle() == null
				|| "".equals(this.lssuing.getTitle())) {
			this.setMessage("借款标题不能为空", "/borrow/toLssuing2", "3");
			return "success";
		}
		if (this.lssuing.getExplains() == null
				|| "".equals(this.lssuing.getExplains())) {
			this.setMessage("借款说明不能为空", "/borrow/toLssuing2", "3");
			return "success";
		}

		if (this.periodtime != 0) {
			this.lssuing.setPeriodtime(this.periodtimeService
					.findPeriodtimeByPeriodtimeId(this.periodtime));
			if (this.returnway == 2) {
				this.setMessage("月标不能按天到期还款", "/borrow/toLssuing2", "3");
				return "success";
			}
		}
		if (this.periodday == 0 && this.periodtime == 0) {
			this.setMessage("请选择借款期限", "/borrow/toLssuing2", "3");
			return "success";
		}
		if (this.returnway == 0) {
			this.setMessage("请选择还款方式", "/borrow/toLssuing2", "3");
			return "success";
		}
		if (this.lssuing.getRate() == null || this.lssuing.getRate() <= 0) {
			this.setMessage("请输入正确的利率", "/borrow/toLssuing2", "3");
			return "success";
		}
		if (this.validtime == 0) {
			this.setMessage("请选择正确的投标有效时间", "/borrow/toLssuing2", "3");
			return "success";
		}

		this.lssuing.setMoneymin(this.moneyminService.findMoneyminById(Integer
				.valueOf(this.moneymin)));
		this.lssuing.setMoneymax(this.moneymaxService.findMoneymaxById(Integer
				.valueOf(this.moneymax)));
		this.lssuing.setValidtime(this.validtimeService
				.findValidtimeById(Integer.valueOf(this.validtime)));
		this.lssuing.setReturnway(this.returnwayService
				.findReturnwayById(Integer.valueOf(this.returnway)));
		if (this.periodday != 0) {
			this.lssuing.setPeriodday(this.perioddayService
					.findPerioddayByPerioddayId(this.periodday));
			this.lssuing.setRate(Double.valueOf(this.lssuing.getRate()
					.doubleValue()));
			if (this.returnway == 3 || this.returnway == 4) {
				this.setMessage("天标不能按月还款", "/borrow/toLssuing2", "3");
				return "success";
			}
		}
		this.lssuing.setMoneyuse(this.moneyuseService.findMoneyuseById(Integer.valueOf(this.moneyUseId)));
		this.lssuing.setReturnMoney(Double.valueOf(0.0D));
		this.lssuing.setBorrowTime(new Timestamp(new Date().getTime()));
		this.lssuing.setUservip(this.uservipService.findUservipByUserid(Integer.valueOf(userId)));
		this.lssuing.setLssuingType(Integer.valueOf(7));
		this.lssuing.setState(Integer.valueOf(0));
		this.lssuing.setIsInvest(0);
		this.lssuing.setReturnMoney(Double.valueOf(0.0D));
		this.lssuing.setIsAward(Integer.valueOf(0));
		this.lssuing.setTenderLimit(Integer.valueOf(0));
		this.lssuing.setIsOrient(Integer.valueOf(0));
        this.lssuing.setRecommend(0);
		Boolean flag = Boolean.valueOf(this.lssuingService.addLssuing(this.lssuing));
		if (flag.booleanValue()) {

			this.setMessage("发标成功，等待审核!", "/borrow/toLssuingNew", "3");

			return "success";
		}

		this.setMessage("借款发布失败!", "/borrow/toLssuingNew", "3");
		return "success";
	}

	public int getResultReturn() {
		return this.resultReturn;
	}

	public void setResultReturn(int resultReturn) {
		this.resultReturn = resultReturn;
	}

	public BigDecimal getNeedReturnMon() {
		return this.needReturnMon;
	}

	public void setNeedReturnMon(BigDecimal needReturnMon) {
		this.needReturnMon = needReturnMon;
	}

	public Integer getRepayNoteId() {
		return this.repayNoteId;
	}

	public void setRepayNoteId(Integer repayNoteId) {
		this.repayNoteId = repayNoteId;
	}

	public int getLssuingId() {
		return this.lssuingId;
	}

	public void setLssuingId(int lssuingId) {
		this.lssuingId = lssuingId;
	}

	public List<Repaynote> getRepaynoteList() {
		return this.repaynoteList;
	}

	public void setRepaynoteList(List<Repaynote> repaynoteList) {
		this.repaynoteList = repaynoteList;
	}

	public int getGotoFlag() {
		return this.gotoFlag;
	}

	public void setGotoFlag(int gotoFlag) {
		this.gotoFlag = gotoFlag;
	}

	public int getQueryFlag() {
		return this.queryFlag;
	}

	public void setQueryFlag(int queryFlag) {
		this.queryFlag = queryFlag;
	}

	public List<Lssuing> getLssuingList() {
		return this.lssuingList;
	}

	public void setLssuingList(List<Lssuing> lssuingList) {
		this.lssuingList = lssuingList;
	}

	public String getFourMon() {
		return this.fourMon;
	}

	public void setFourMon(String fourMon) {
		this.fourMon = fourMon;
	}

	public String getOneMon() {
		return this.oneMon;
	}

	public void setOneMon(String oneMon) {
		this.oneMon = oneMon;
	}

	public String getTwoMon() {
		return this.twoMon;
	}

	public void setTwoMon(String twoMon) {
		this.twoMon = twoMon;
	}

	public String getThreeMon() {
		return this.threeMon;
	}

	public void setThreeMon(String threeMon) {
		this.threeMon = threeMon;
	}

	public int getIsUpdate() {
		return this.isUpdate;
	}

	public void setIsUpdate(int isUpdate) {
		this.isUpdate = isUpdate;
	}

	public int getAutomaticBidId() {
		return this.automaticBidId;
	}

	public void setAutomaticBidId(int automaticBidId) {
		this.automaticBidId = automaticBidId;
	}

	public int getAutomaticBidNumberFlag() {
		return this.automaticBidNumberFlag;
	}

	public void setAutomaticBidNumberFlag(int automaticBidNumberFlag) {
		this.automaticBidNumberFlag = automaticBidNumberFlag;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<Automaticbid> getAutomaticbidList() {
		return this.automaticbidList;
	}

	public void setAutomaticbidList(List<Automaticbid> automaticbidList) {
		this.automaticbidList = automaticbidList;
	}

	public int getPeriodday() {
		return this.periodday;
	}

	public void setPeriodday(int periodday) {
		this.periodday = periodday;
	}

	public List<Periodday> getPerioddayList() {
		return this.perioddayList;
	}

	public void setPerioddayList(List<Periodday> perioddayList) {
		this.perioddayList = perioddayList;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Lssuing getLssuing() {
		return this.lssuing;
	}

	public void setLssuing(Lssuing lssuing) {
		this.lssuing = lssuing;
	}

	public int getMark() {
		return this.mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public List<Moneyuse> getMoneyuseList() {
		return this.moneyuseList;
	}

	public void setMoneyuseList(List<Moneyuse> moneyuseList) {
		this.moneyuseList = moneyuseList;
	}

	public List<Moneymin> getMoneyminList() {
		return this.moneyminList;
	}

	public void setMoneyminList(List<Moneymin> moneyminList) {
		this.moneyminList = moneyminList;
	}

	public List<Moneymax> getMoneymaxList() {
		return this.moneymaxList;
	}

	public void setMoneymaxList(List<Moneymax> moneymaxList) {
		this.moneymaxList = moneymaxList;
	}

	public List<Validtime> getValidtimeList() {
		return this.validtimeList;
	}

	public void setValidtimeList(List<Validtime> validtimeList) {
		this.validtimeList = validtimeList;
	}

	public List<Returnway> getReturnwayList() {
		return this.returnwayList;
	}

	public void setReturnwayList(List<Returnway> returnwayList) {
		this.returnwayList = returnwayList;
	}

	public List<Periodtime> getPeriodtimeList() {
		return this.periodtimeList;
	}

	public void setPeriodtimeList(List<Periodtime> periodtimeList) {
		this.periodtimeList = periodtimeList;
	}

	public int getMoneyUseId() {
		return this.moneyUseId;
	}

	public void setMoneyUseId(int moneyUseId) {
		this.moneyUseId = moneyUseId;
	}

	public int getPeriodtime() {
		return this.periodtime;
	}

	public void setPeriodtime(int periodtime) {
		this.periodtime = periodtime;
	}

	public int getMoneymin() {
		return this.moneymin;
	}

	public void setMoneymin(int moneymin) {
		this.moneymin = moneymin;
	}

	public int getMoneymax() {
		return this.moneymax;
	}

	public void setMoneymax(int moneymax) {
		this.moneymax = moneymax;
	}

	public int getValidtime() {
		return this.validtime;
	}

	public void setValidtime(int validtime) {
		this.validtime = validtime;
	}

	public int getReturnway() {
		return this.returnway;
	}

	public void setReturnway(int returnway) {
		this.returnway = returnway;
	}

	public Automaticbid getAutomaticbid() {
		return this.automaticbid;
	}

	public void setAutomaticbid(Automaticbid automaticbid) {
		this.automaticbid = automaticbid;
	}

	public Integer getPage() {
		return this.page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageCount() {
		return this.pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getAllMoney() {
		return allMoney;
	}

	public void setAllMoney(Integer allMoney) {
		this.allMoney = allMoney;
	}

	public String getPhoneStr() {
		return phoneStr;
	}

	public void setPhoneStr(String phoneStr) {
		this.phoneStr = phoneStr;
	}

	public List<Uservip> getUservipList() {
		return uservipList;
	}

	public void setUservipList(List<Uservip> uservipList) {
		this.uservipList = uservipList;
	}

	public String getPostform() {
		return postform;
	}

	public void setPostform(String postform) {
		this.postform = postform;
	}
	
}