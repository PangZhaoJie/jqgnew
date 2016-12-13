package com.jqg.struts;

import java.io.File;
import java.net.InetAddress;
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

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.SessionFactory;

import com.jqg.pojo.Automaticbid;
import com.jqg.pojo.Inbox;
import com.jqg.pojo.Integralcategory;
import com.jqg.pojo.Integraldetail;
import com.jqg.pojo.Lssingphoto;
import com.jqg.pojo.Lssuing;
import com.jqg.pojo.Manager;
import com.jqg.pojo.Moneycount;
import com.jqg.pojo.Moneyhistorydetail;
import com.jqg.pojo.Moneymax;
import com.jqg.pojo.Moneymin;
import com.jqg.pojo.Oerationlog;
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
import com.jqg.service.InboxService;
import com.jqg.service.IntegralcategoryService;
import com.jqg.service.IntegraldetailService;
import com.jqg.service.InternallettermodelService;
import com.jqg.service.LssingphotoService;
import com.jqg.service.LssuingService;
import com.jqg.service.ManagerService;
import com.jqg.service.MoneycountService;
import com.jqg.service.MoneyhistorydetailService;
import com.jqg.service.MoneymaxService;
import com.jqg.service.MoneyminService;
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
import com.jqg.service.impl.InboxServiceImpl;
import com.jqg.service.impl.IntegralcategoryServiceImpl;
import com.jqg.service.impl.IntegraldetailServiceImpl;
import com.jqg.service.impl.InternallettermodelServiceImpl;
import com.jqg.service.impl.LssingphotoServiceImpl;
import com.jqg.service.impl.LssuingServiceImpl;
import com.jqg.service.impl.ManagerServiceImpl;
import com.jqg.service.impl.MoneycountServiceImpl;
import com.jqg.service.impl.MoneyhistorydetailServiceImpl;
import com.jqg.service.impl.MoneymaxServiceImpl;
import com.jqg.service.impl.MoneyminServiceImpl;
import com.jqg.service.impl.OerationlogServiceImpl;
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
import com.jqg.session.factory.HibernateSessionFactory;
import com.jqg.util.Common;
import com.jqg.util.LoanUtils;
import com.jqg.util.MD5Trust;
import com.jqg.util.OverbueCom;
import com.jqg.util.RsaHelper;
import com.jqg.util.UploadPhoto;
import com.loan.model.LoanReturnInfoBean;
import com.loan.model.LoanTransferReturnBean;
import com.opensymphony.xwork2.ActionContext;

public class BackLssuingAction extends BaseAction {
	private String result;
	private Lssuing lssuing; 
	private Lssingphoto lssingphoto;
	private int lssuingId;
	private int photoId;
	private List<Lssuing> lssuingList;
	private List<Validtime> validtimeList;
	private List<Returnway> returnwayList;
	private List<Periodtime> periodtimeList;
	private List<Periodday> perioddayList;
	private List<Moneymax> moneymaxList;
	private List<Lssingphoto> lssingphotoList;
	private List<Repaynote> repaynoteList;
	private List<Tender> tenderList;
	private List<Moneymin> moneyminList;
	private List<Uservip> uservipList;
	private int validtime;
	private int returnway;
	private int periodday;
	private int periodtime;
	private int moneymax;
	private int userId;
	private int managerId;
	private int mark;
	private int queryFlag;
	private int updateFlag;
	private Integer page = Integer.valueOf(0);
	private Integer pageSize = Integer.valueOf(10);
	private Integer pageCount;
	private Integer total = Integer.valueOf(0);
	private File file;
	private File file2;
	private String fileFileName;
	private String file2FileName;
	private String urlStr;
	private String message;
	private LssuingService lssuingService = new LssuingServiceImpl();
	private MoneymaxService moneymaxService = new MoneymaxServiceImpl();
	private ValidtimeService validtimeService = new ValidtimeServiceImpl();
	private ReturnwayService returnwayService = new ReturnwayServiceImpl();
	private PeriodtimeService periodtimeService = new PeriodtimeServiceImpl();
	private PerioddayService perioddayService = new PeriodDayServiceImpl();
	private UservipService uservipService = new UservipServiceImpl();
	private RepaynoteService repaynoteService = new RepaynoteServiceImpl();
	private RecordService recordService = new RecordServiceImpl();
	private MoneycountService moneycountService = new MoneycountServiceImpl();
	private MoneyhistorydetailService moneyhistorydetailService = new MoneyhistorydetailServiceImpl();
	private TenderService tenderService = new TenderServiceImpl();
	private LssingphotoService lssingphotoService = new LssingphotoServiceImpl();
	private ManagerService managerService = new ManagerServiceImpl();
	private OerationlogService oerationlogService = new OerationlogServiceImpl();
	private WebsiteService websiteService = new WebsiteServiceImpl();
	private InternallettermodelService internallettermodelService = new InternallettermodelServiceImpl();
	private InboxService inboxService = new InboxServiceImpl();
	private AutomaticbidService automaticbidService = new AutomaticbidServiceImpl();
	private SystemconfService systemconfService = new SystemconfServiceImpl();
	private MoneyminService moneyminService = new MoneyminServiceImpl();
	private IntegralcategoryService integralcategoryService = new IntegralcategoryServiceImpl();
	private IntegraldetailService integraldetailService = new IntegraldetailServiceImpl();
	private TransferService transferService = new TransferServiceImpl();
	private String userNameQuery;
	private Double moneyQuery;
	private Timestamp startTimeQuery;
	private Timestamp endTimeQuery;
	String urls;
	String urlIds;
	private String lssuingType;
	private String postform;
	private int repayNoteId;

	public String investList() throws Exception {
		this.tenderList = this.tenderService
				.findTendersBylssuingId(this.lssuingId);
		return "success";
	}
	
	/**
	 * ��������
	 * @return
	 * @throws Exception
	 */
	public String tobackFenMoney() throws Exception {
		
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
		boolean key = false;
		double overfee1 = 0.0;
		double overfee2 = 0.0;
		double overfee3 = 0.0;
		double overfee4 = 0.0;
		

		WebsiteService websiteService = new WebsiteServiceImpl();
		Website website = websiteService.findWebsiteBywebsiteId(1);
		HttpServletRequest request = ServletActionContext.getRequest();
			Timestamp now = new Timestamp(new Date().getTime());
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
			// �����ڸ����Ͷ���û�
			if ((records != null) && (records.size() > 0)) {
				for (int i = 0; i < records.size(); i++) {
					Record record = records.get(i);
					if (key) {
						record = OverbueCom.comOverInterest(record, overfee1,
								overfee2, overfee3, overfee4);
					} else {
						record.setOverdays(0);
						record.setOverInterest(0.0);
						
					}
					record.setRepaymentDate(Timestamp.valueOf(sdf.format(new Date())));//������ֶλ���ʱ��

					this.recordService.updateRecord(record);
					recordlist.add(record);
					
	
				}
			}
			sql = "select * from repaynote where lssuingId="+this.lssuingId+" and repayDate like '"+t+"%' and repayState=0";
			
			List<Repaynote> repnoteList = this.repaynoteService.findRepayBySql(sql);
			if(repnoteList!=null && repnoteList.size()>0){
				Systemconf sysconf1 = systemconfService
						.findSystemconfByParname("con_vip_rate_money");
				Systemconf sysconf2 = systemconfService
						.findSystemconfByParname("con_rate_money");
				LoanUtils loanutils = new LoanUtils();
				this.postform = loanutils.lcRepay(false, website, recordlist, request, lssuing,sysconf1,sysconf2,repnoteList);
				return "loanjump";
			}else{
				this.setMessage("����Ʋ�Ʒû�н���Ҫ���Ŀ�", "/borrow/toBackLssuing?queryFlag=2&lssuingType=9", "3");
				return "success";
			}
			
	}
	
	/**
	 * ��̨�����б�
	 * @return
	 * @throws Exception
	 */
	public String rePayList()throws Exception{
		List<Repaynote> temprepaynoteList = this.repaynoteService
				.findRepaynoteBylssuing(this.lssuingId);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowdate = df.format(new Date());
		Timestamp nowtime = new Timestamp(sdf.parse(nowdate + " 00:00:00")
				.getTime());
		// vip��Ա�����ڷ�Ϣ
		Lssuing lssuing = this.lssuingService.findLssuingById(this.lssuingId);
		
		
		
		
		Systemconf latefee1 = null;
		Systemconf latefee2 = null;
		Systemconf latefee3 = null;
		Systemconf latefee4 = null;
		double overfee1 = 0;
		double overfee2 = 0;
		double overfee3 = 0;
		double overfee4 = 0;
		
		if(lssuing.getUservip()!=null){
			Uservip uservip = this.uservipService.findUservipByUserid(lssuing
					.getUservip().getUserId());
			SystemconfService systemconfService = new SystemconfServiceImpl();
			if ("1".equals(uservip.getIsVIP())) {// vip��Ա
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
		return "success";
	}
	

	/**
	 * ����
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toBackLssuing() throws Exception {
		String sql = "";
		int stateFlag = 0;

		if (this.queryFlag == 0) {
			stateFlag = 0;
			this.mark = 0;
		} else if (this.queryFlag == 1) {
			stateFlag = 1;
		} else if (this.queryFlag == 2) {
			stateFlag = 2;
		} else if (this.queryFlag == 3) {
			stateFlag = 3;
		} else if (this.queryFlag == 4) {
			stateFlag = 4;
		} else if (this.queryFlag == 5) {
			stateFlag = 5;
		} else if (this.queryFlag == 6) {
			stateFlag = 6;
		} else if (this.queryFlag == 7) {
			stateFlag = 7;
		} else if (this.queryFlag == -1) {
			stateFlag = -1;
		}
		if (lssuingType == null||"".equals(lssuingType)) {
			lssuingType = "0";
		}
		if(Integer.parseInt(lssuingType)==9){
			sql = "select * from Lssuing l  where l.lssuingType = "
					+ lssuingType + " and  l.state="+ stateFlag;
		}else if (Integer.parseInt(lssuingType) >= 6 && Integer.parseInt(lssuingType)!=9) {
			sql = "select * from Lssuing l , uservip u where l.lssuingType = "
					+ lssuingType + " and  u.userId=l.userId  and  l.state="
					+ stateFlag;
		} else {
			sql = "select * from Lssuing l , uservip u where l.lssuingType < 6 and  u.userId=l.userId   and  l.state="
					+ stateFlag;
		}
		if ((this.userNameQuery != null) && (!"".equals(this.userNameQuery))) {
			sql = sql + " and  u.userName like '%" + this.userNameQuery + "%' ";
		}
		if (this.moneyQuery != null) {
			sql = sql + " and  l.borrowMoney >" + this.moneyQuery;
		}
		if (this.startTimeQuery != null) {
			sql = sql + "  and timestampdiff(DAY,DATE_FORMAT('"
					+ this.startTimeQuery
					+ "','%Y-%m-%d'),DATE_FORMAT(l.borrowTime,'%Y-%m-%d')) >=0";
		}
		if (this.endTimeQuery != null) {
			sql = sql
					+ "  and timestampdiff(DAY,DATE_FORMAT(l.borrowTime,'%Y-%m-%d'),DATE_FORMAT('"
					+ this.endTimeQuery + "','%Y-%m-%d')) >=0";
		}

		sql = sql + " order by borrowTime desc ";
		List list = this.lssuingService.findLssuingsBySearch(sql);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.total = Integer.valueOf(list.size());
		if (this.page.intValue() == 0) {
			this.page = Integer.valueOf(1);
		}
		if (this.total.intValue() == 0)
			this.pageCount = Integer.valueOf(1);
		else if (this.total.intValue() % this.pageSize.intValue() == 0)
			this.pageCount = Integer.valueOf(this.total.intValue()
					/ this.pageSize.intValue());
		else {
			this.pageCount = Integer.valueOf(this.total.intValue()
					/ this.pageSize.intValue() + 1);
		}
		sql = sql + "limit " + (this.page.intValue() - 1)
				* this.pageSize.intValue() + "," + this.pageSize;
		List<Lssuing> lssuingLists = this.lssuingService
				.findLssuingsBySearch(sql);
		this.lssuingList = new ArrayList<Lssuing>();
		if (lssuingLists != null && lssuingLists.size() > 0) {
			for (Lssuing lssuing : lssuingLists) {
				if (lssuing.getState() == 2) {
					long nowtime = df.parse(df.format(new Date())).getTime();
					if (Integer.parseInt(lssuingType) < 6) {
						long validtime = df.parse(
								lssuing.getVerify_time().toString()).getTime()
								+ (lssuing.getValidtime().getValidTimeId()
										.intValue() * 86400000);
						if (nowtime > validtime) {
							lssuing.setValidtimestate(true);
						}
					}
				}
				this.lssuingList.add(lssuing);
			}
		}

		return "success";
	}

	/**
	 * ������Ϣ��ѯ�������Զ�����
	 * 
	 * @return
	 * @throws Exception
	 */
	public String overdueLassuing() throws Exception {
		Systemconf sysconf = this.systemconfService
				.findSystemconfByParname("overdue_time");
		String parvalue = sysconf.getParvalue();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowdate = df.format(new Date());
		String overtime = nowdate + " " + parvalue;
		if (sdf.parse(overtime).getTime() >= new Date().getTime()) {
			overtime = nowdate + " 00:00:00";
		}
		String sql = "select * from Repaynote r,Lssuing l , uservip u where u.userId=l.userId and  r.lssuingId=l.lssuingId";
		sql += " and r.repayDate<'" + overtime + "' and r.repayState=0";
		if ((this.userNameQuery != null) && (!"".equals(this.userNameQuery))) {
			sql = sql + " and  u.userName like '%" + this.userNameQuery + "%' ";
		}
		if (this.moneyQuery != null) {
			sql = sql + " and  l.borrowMoney >" + this.moneyQuery;
		}
		if (this.startTimeQuery != null) {
			sql = sql + "  and timestampdiff(DAY,DATE_FORMAT('"
					+ this.startTimeQuery
					+ "','%Y-%m-%d'),DATE_FORMAT(l.borrowTime,'%Y-%m-%d')) >=0";
		}
		if (this.endTimeQuery != null) {
			sql = sql
					+ "  and timestampdiff(DAY,DATE_FORMAT(l.borrowTime,'%Y-%m-%d'),DATE_FORMAT('"
					+ this.endTimeQuery + "','%Y-%m-%d')) >=0";
		}

		sql = sql + " order by r.repayDate desc ";
		List list = this.repaynoteService.findRepayBySql(sql);
		this.total = Integer.valueOf(list.size());
		if (this.page.intValue() == 0) {
			this.page = Integer.valueOf(1);
		}
		if (this.total.intValue() == 0)
			this.pageCount = Integer.valueOf(1);
		else if (this.total.intValue() % this.pageSize.intValue() == 0)
			this.pageCount = Integer.valueOf(this.total.intValue()
					/ this.pageSize.intValue());
		else {
			this.pageCount = Integer.valueOf(this.total.intValue()
					/ this.pageSize.intValue() + 1);
		}
		sql = sql + "limit " + (this.page.intValue() - 1)
				* this.pageSize.intValue() + "," + this.pageSize;
		List<Repaynote> repaylist = this.repaynoteService.findRepayBySql(sql);
		this.repaynoteList = new ArrayList<Repaynote>();
		Uservip uservip = null;
		Systemconf latefee1 = null;
		Systemconf latefee2 = null;
		Systemconf latefee3 = null;
		Systemconf latefee4 = null;
		for (Repaynote repaynote : repaylist) {
			uservip = this.uservipService.findUservipByUserid(repaynote
					.getUservip().getUserId());
			if ("1".equals(uservip.getIsVIP())) {
				latefee1 = this.systemconfService
						.findSystemconfByParname("con_borrow_late_fee_3_2");
				latefee2 = this.systemconfService
						.findSystemconfByParname("con_borrow_late_fee_30_2");
				latefee3 = this.systemconfService
						.findSystemconfByParname("con_borrow_late_fee_90_2");
				latefee4 = this.systemconfService
						.findSystemconfByParname("con_borrow_late_fee_2");
			} else {
				latefee1 = this.systemconfService
						.findSystemconfByParname("con_borrow_late_fee_3");
				latefee2 = this.systemconfService
						.findSystemconfByParname("con_borrow_late_fee_30");
				latefee3 = this.systemconfService
						.findSystemconfByParname("con_borrow_late_fee_90");
				latefee4 = this.systemconfService
						.findSystemconfByParname("con_borrow_late_fee");
			}
			Double overfee1 = Double.valueOf(latefee1.getParvalue());
			Double overfee2 = Double.valueOf(latefee2.getParvalue());
			Double overfee3 = Double.valueOf(latefee3.getParvalue());
			Double overfee4 = Double.valueOf(latefee4.getParvalue());
			repaynote = OverbueCom.comoverInterest(repaynote, overfee1,
					overfee2, overfee3, overfee4);
			repaynoteList.add(repaynote);
		}
		return "success";
	}

	/**
	 * ��վ�渶
	 * 
	 * @return
	 * @throws Exception
	 */
	public String webAdvance() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String path = request.getContextPath();
		if (path != null && !"".equals(path)) {
			path = path + "/";
		} else {
			path = "/";
		}
//		String serverport = request.getServerPort() == 80 ? "" : ":"
//				+ request.getServerPort();
//		String basePath = request.getScheme() + "://" + request.getServerName()
//				+ serverport + path;
		if (request.getParameter("webAdvance") != null) {
			return "success";
		}
		String reId = request.getParameter("repaynoteId").trim();
		Integer repaynoteId = 0;
		if (reId != null && !"".equals(reId)) {
			repaynoteId = Integer.valueOf(reId);
		}

		if (repaynoteId <= 0) {
			return "success";
		}

		Repaynote repaynote = this.repaynoteService
				.findRepaynoteByRepaynoteId(repaynoteId);
		if (repaynote.getRepayState() != 0) {
			return "success";
		}

		if (repaynote.getRepayState() == 1) {
			return "success";
		}

		Lssuing lssuing = this.lssuingService.findLssuingById(Integer
				.valueOf(repaynote.getLssuing().getLssuingId()));
		Website website = this.websiteService.findWebsiteBywebsiteId(1);

//		PlatcountService platcountService = new PlatcountServiceImpl();
//		Platcount platcount = new Platcount();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		DecimalFormat df = new DecimalFormat("##.00");
		String nowdate = sdf.format(new Date());
		String repaydate = sdf.format(new Date(repaynote.getRepayDate()
				.getTime()));
		// String nowtime = timedf.format(new Date());
		boolean repaykey = true;
		Double overfee1 = 0.0;
		Double overfee2 = 0.0;
		Double overfee3 = 0.0;
		Double overfee4 = 0.0;
		if (repaydate.equals(nowdate)) {
			repaynote.setOverdays(0);
			repaynote.setOverInterest(0.0);
		} else {
			Uservip uservip = this.uservipService.findUservipByUserid(repaynote
					.getUservip().getUserId());
			if ("1".equals(uservip.getIsVIP())) {
				Systemconf latefee1 = this.systemconfService
						.findSystemconfByParname("con_borrow_late_fee_3_2");
				overfee1 = Double.valueOf(latefee1.getParvalue());

				Systemconf latefee2 = this.systemconfService
						.findSystemconfByParname("con_borrow_late_fee_30_2");
				overfee2 = Double.valueOf(latefee2.getParvalue());

				Systemconf latefee3 = this.systemconfService
						.findSystemconfByParname("con_borrow_late_fee_90_2");
				overfee3 = Double.valueOf(latefee3.getParvalue());

				Systemconf latefee4 = this.systemconfService
						.findSystemconfByParname("con_borrow_late_fee_2");
				overfee4 = Double.valueOf(latefee4.getParvalue());
			} else {
				Systemconf latefee1 = this.systemconfService
						.findSystemconfByParname("con_borrow_late_fee_3");
				overfee1 = Double.valueOf(latefee1.getParvalue());

				Systemconf latefee2 = this.systemconfService
						.findSystemconfByParname("con_borrow_late_fee_30");
				overfee2 = Double.valueOf(latefee2.getParvalue());

				Systemconf latefee3 = this.systemconfService
						.findSystemconfByParname("con_borrow_late_fee_90");
				overfee3 = Double.valueOf(latefee3.getParvalue());

				Systemconf latefee4 = this.systemconfService
						.findSystemconfByParname("con_borrow_late_fee");
				overfee4 = Double.valueOf(latefee4.getParvalue());
			}
			repaynote = OverbueCom.comoverInterest(repaynote, overfee1,
					overfee2, overfee3, overfee4);
			// repaynote.setMoneyFour(repaynote.getMoneyFour()+repaynote.getOverInterest());
			repaykey = false;
		}


		Timestamp now = repaynote.getRepayDate();
		String t = now.toString().substring(0, 10);
		List records = new ArrayList();
		List<Record> recordlist = new ArrayList<Record>();
		String sql = "select * from Record r  where  r.tenderId in (select tenderId from Tender t where t.lssuingId="
				+ lssuing.getLssuingId().intValue()
				+ ")"
				+ " and r.recordDate like '" + t + "%' and recordState=0";
		records = this.recordService.findRecordByRecordId(sql);
		if ((records != null) && (records.size() > 0)) {
			for (int i = 0; i < records.size(); i++) {
				Record record = (Record) records.get(i);
//				record.setRecordState(Integer.valueOf(1));

				if (repaykey) {
					record.setOverdays(0);
					record.setOverInterest(0.0);
				} else {
					record = OverbueCom.comOverInterest(record, overfee1,
							overfee2, overfee3, overfee4);
				}
//				record.setRecordInterest(Double.valueOf(record.getRecordMoney()
//						.doubleValue()
//						+ record.getRecordRate().doubleValue()
//						+ record.getOverInterest()));
				recordlist.add(record);
				this.recordService.updateRecord(record);

//				double collectmoney = record.getRecordMoney().doubleValue()
//						+ record.getRecordRate().doubleValue();
//
//				// ��ѯ�Ƿ����ծȨת��
//				Transfer transfer = transferService
//						.findTransferByTenderId1(record.getTender()
//								.getTenderId());
//
//				if (transfer != null) {
//					if (transfer.getIsTransfer() == 0
//							|| transfer.getIsTransfer() == 1) {
//						transfer.setIsTransfer(3);
//						transferService.updateTransfer(transfer);
//					}
//				}

//				if (transfer != null && transfer.getIsTransfer() == 2) {// �Ѿ�ת��
//					Moneycount moneycount2 = this.moneycountService
//							.findMoneycountByuserId(transfer.getBuyUser()
//									.getUserId().intValue());
//					Moneyhistorydetail moneyhistorydetail1 = new Moneyhistorydetail();
//					moneyhistorydetail1.setAffectMoney(Double.valueOf(record
//							.getRecordMoney().doubleValue()
//							+ record.getRecordRate().doubleValue()
//							+ record.getOverInterest().doubleValue()));
//					moneyhistorydetail1.setAvailableBalance(Double
//							.valueOf(moneycount2.getAvailableMoney()
//									.doubleValue()
//									+ record.getRecordMoney().doubleValue()
//									+ record.getRecordRate().doubleValue()
//									+ record.getOverInterest().doubleValue()));
//					moneyhistorydetail1.setOccurTime(new Timestamp(new Date()
//							.getTime()));
//					moneyhistorydetail1.setUservip(transfer.getBuyUser());
//					moneyhistorydetail1.setFrozenMoney(moneycount2
//							.getFrozenMoney());
//					moneyhistorydetail1.setCollectMoney(Double
//							.valueOf(moneycount2.getCollectTotalMoney()
//									- collectmoney));
//					moneyhistorydetail1.setIntroduction("��ţ�"
//							+ lssuing.getLssuingNum() + "ƽ̨�渶�ɹ�������ծȨ����");
//					this.moneyhistorydetailService
//							.createMoneyhistorydetail(moneyhistorydetail1);
//
//					moneycount2.setAvailableMoney(Double.valueOf(moneycount2
//							.getAvailableMoney().doubleValue()
//							+ record.getRecordMoney().doubleValue()
//							+ record.getRecordRate().doubleValue()
//							+ record.getOverInterest().doubleValue()));
//					moneycount2.setDueInMoney(Double.valueOf(moneycount2
//							.getDueInMoney().doubleValue()
//							- record.getRecordMoney()));
//					moneycount2.setCollectTotalMoney(Double.valueOf(moneycount2
//							.getCollectTotalMoney() - collectmoney));
//
//					moneycount2.setCollectInterestTotalFee(moneycount2
//							.getCollectInterestTotalFee().doubleValue()
//							- record.getRecordRate());
//
//					moneycount2.setAccuProfitAndLossMoney(Double
//							.valueOf(moneycount2.getAccuProfitAndLossMoney()
//									.doubleValue()
//									+ record.getRecordRate().doubleValue()
//									+ record.getOverInterest().doubleValue()));
//					this.moneycountService.updateMoneycount(moneycount2);
//					addInbox("�ʽ�䶯", "���Ļؿ��ѵ��˻�����ע��鿴", transfer.getBuyUser()
//							.getUserId());
//				} else {
//					Moneycount moneycount2 = this.moneycountService
//							.findMoneycountByuserId(record.getUservip()
//									.getUserId().intValue());
//					Moneyhistorydetail moneyhistorydetail1 = new Moneyhistorydetail();
//					moneyhistorydetail1.setAffectMoney(Double.valueOf(record
//							.getRecordMoney().doubleValue()
//							+ record.getRecordRate().doubleValue()
//							+ record.getOverInterest().doubleValue()));
//					moneyhistorydetail1.setAvailableBalance(Double
//							.valueOf(moneycount2.getAvailableMoney()
//									.doubleValue()
//									+ record.getRecordMoney().doubleValue()
//									+ record.getRecordRate().doubleValue()
//									+ record.getOverInterest().doubleValue()));
//					moneyhistorydetail1.setOccurTime(new Timestamp(new Date()
//							.getTime()));
//					moneyhistorydetail1.setUservip(record.getUservip());
//					moneyhistorydetail1.setFrozenMoney(moneycount2
//							.getFrozenMoney());
//					moneyhistorydetail1.setCollectMoney(Double
//							.valueOf(moneycount2.getCollectTotalMoney()
//									- collectmoney));
//					moneyhistorydetail1.setIntroduction("��ţ�"
//							+ lssuing.getLssuingNum() + "ƽ̨�渶�ɹ�");
//					this.moneyhistorydetailService
//							.createMoneyhistorydetail(moneyhistorydetail1);
//
//					moneycount2.setAvailableMoney(Double.valueOf(moneycount2
//							.getAvailableMoney().doubleValue()
//							+ record.getRecordMoney().doubleValue()
//							+ record.getRecordRate().doubleValue()
//							+ record.getOverInterest().doubleValue()));
//					moneycount2.setDueInMoney(Double.valueOf(moneycount2
//							.getDueInMoney().doubleValue()
//							- record.getRecordMoney()));
//					moneycount2.setCollectTotalMoney(Double.valueOf(moneycount2
//							.getCollectTotalMoney() - collectmoney));
//
//					moneycount2.setCollectInterestTotalFee(moneycount2
//							.getCollectInterestTotalFee().doubleValue()
//							- record.getRecordRate());
//					moneycount2.setNetEarnInterest(Double.valueOf(moneycount2
//							.getNetEarnInterest().doubleValue()
//							+ record.getRecordRate().doubleValue()
//							+ record.getOverInterest().doubleValue()));
//					moneycount2.setAccuProfitAndLossMoney(Double
//							.valueOf(moneycount2.getAccuProfitAndLossMoney()
//									.doubleValue()
//									+ record.getRecordRate().doubleValue()
//									+ record.getOverInterest().doubleValue()));
//					moneycount2.setCollectInterestTotalFee(Double
//							.valueOf(moneycount2.getCollectInterestTotalFee()
//									.doubleValue()
//									- record.getRecordRate().doubleValue()));
//					this.moneycountService.updateMoneycount(moneycount2);
//					addInbox("�ʽ�䶯", "���Ļؿ��ѵ��˻�����ע��鿴", record.getUservip()
//							.getUserId());
//				}

			}
		}

		this.repaynoteService.updateRepaynote(repaynote);
		LoanUtils loanutils = new LoanUtils();
		this.postform = loanutils.borrowWebRepay(repaynote, false, website, recordlist, request, lssuing, null, null);
		return "loanjump";
	}

	/**
	 * ������ -��̨������ͼ
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toBackLssuingView() throws Exception {
		this.lssuing = this.lssuingService.findLssuingById(Integer
				.valueOf(this.lssuingId));
		this.periodtimeList = this.periodtimeService.findPeriodtimes();
		this.returnwayList = this.returnwayService.findReturnways();
		this.validtimeList = this.validtimeService.findValidtimes();
		this.perioddayList = this.perioddayService.findPerioddays();
		this.moneymaxList = this.moneymaxService.findMoneymaxs();
		return "success";
	}

	/**
	 * ������-���·���
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toUpdateLssuing() throws Exception {
		SessionFactory sf = HibernateSessionFactory.getSessionFactory();
		sf.getCurrentSession().beginTransaction();
		try {
			DecimalFormat df = new DecimalFormat("##.00");
			Oerationlog oerationlog = new Oerationlog();
			oerationlog.setManager(this.managerService
					.findManagerByManagerId(Integer.valueOf(this.managerId)));
			oerationlog.setOerationCategory("updateLssuing");
			oerationlog.setOerationChangeTime(new Timestamp(new Date()
					.getTime()));
			oerationlog.setOrationIp(InetAddress.getLocalHost()
					.getHostAddress());

			Lssuing ls = this.lssuingService.findLssuingById(Integer
					.valueOf(this.lssuingId));
			if (this.updateFlag == 1) {
				ls.setTitle(this.lssuing.getTitle());
				ls.setReturnway(this.returnwayService.findReturnwayById(Integer
						.valueOf(this.returnway)));
				ls.setBorrowMoney(this.lssuing.getBorrowMoney());
				ls.setRate(this.lssuing.getRate());
				if (this.periodday != 0) {
					ls.setPeriodday(this.perioddayService
							.findPerioddayByPerioddayId(this.periodday));
					ls.setPeriodtime(null);
				}
				if (this.periodtime != 0) {
					ls.setPeriodday(null);
					ls.setPeriodtime(this.periodtimeService
							.findPeriodtimeByPeriodtimeId(this.periodtime));
				}
				ls.setExplains(this.lssuing.getExplains());
				Moneymax moneymax=this.moneymaxService.findMoneymaxById(this.moneymax);
				ls.setMoneymax(moneymax);
				ls.setMoneyLimit(this.lssuing.getMoneyLimit());
				if(this.lssuing.getMoneyLimit()!=null&&!"".equals(this.lssuing.getMoneyLimit())&&Double.parseDouble(this.lssuing.getMoneyLimit())>0){
					ls.setTenderLimit(1);
				}else{
					ls.setTenderLimit(0);
				}
				ls.setRecommend(lssuing.getRecommend());
				this.lssuingService.updateLssuing(ls);
				this.lssuingId = ls.getLssuingId().intValue();
				oerationlog.setOerationRemaks("�޸ı����Ϣ");
				this.oerationlogService.createOerationlog(oerationlog);
				this.queryFlag = this.queryFlag;
				this.lssuingType=""+ls.getLssuingType();
				sf.getCurrentSession().getTransaction().commit();
				if("1".equals(result)){
					sf.getCurrentSession().getTransaction().commit();
					return "success2";
				}else{
					sf.getCurrentSession().getTransaction().commit();
					return "success";
				}
			}
			List internallettermodelList = this.internallettermodelService
					.findInternallettermodels();
			oerationlog.setOerationRemaks("��˱�");
			this.oerationlogService.createOerationlog(oerationlog);
			System.out.println("����ع���lssuing�� \t\n"+ls.getLssuingId()+ls.getManageMoney());
			ls.setManageMoney(this.lssuing.getManageMoney());
			ls.setValidtime(this.validtimeService.findValidtimeById(Integer
					.valueOf(this.validtime)));
			ls.setMoneymax(this.moneymaxService.findMoneymaxById(Integer
					.valueOf(this.moneymax)));
			if ((this.lssuing.getMoneyLimit() != null)
					&& (!"".equals(this.lssuing.getMoneyLimit()))
					&& (!"0.00".equals(this.lssuing.getMoneyLimit()))) {
				ls.setMoneyLimit(this.lssuing.getMoneyLimit());
				ls.setTenderLimit(Integer.valueOf(1));
			}
			// �긴��ͨ��
			if (this.lssuing.getState().intValue() == 3) {
				if (ls.getState() == 3 || ls.getState() == 6) {
					sf.getCurrentSession().getTransaction().commit();
					return "other";
				}

				ls.setVerify_time(new Timestamp(new Date().getTime()));
				SimpleDateFormat format = new SimpleDateFormat(
						"yyyy-MM-dd HH-mm-ss");
				Calendar calender = Calendar.getInstance();
				calender.setTime(ls.getVerify_time());
				long start = calender.getTimeInMillis();

				format.format(calender.getTime());
				Repaynote repaynote = new Repaynote();
				List<Tender> tenderList = this.tenderService
						.findTendersBylssuingId(ls.getLssuingId().intValue());

				Uservip uservip1 = this.uservipService.findUservipByUserid(ls
						.getUservip().getUserId().intValue());
				repaynote.setUservip(uservip1);
				double borrowmoney = 0;
				for(int i=0;i<tenderList.size();i++){
					Tender tender = tenderList.get(i);
					borrowmoney += tender.getMoney(); 
				}
				
				if (ls.getPeriodday() != null) {
					String a = ls.getPeriodday().getPeriodDayName();
					calender.add(5,
							Integer.parseInt(a.substring(0, a.indexOf('��'))));
					repaynote.setRepayDate(new Timestamp(calender.getTime()
							.getTime()));
					repaynote.setMoneyTwo(Double.valueOf(Double.parseDouble(df
							.format(Double.valueOf(borrowmoney)
									.doubleValue()
									* ls.getRate().doubleValue()
									/ 100.0D
									* Integer.parseInt(a.substring(0,
											a.indexOf('��')))))));
				} else {
					String a = ls.getPeriodtime().getPeriodTimeName();
					calender.add(2,
							Integer.parseInt(a.substring(0, a.indexOf('��'))));
					repaynote.setRepayDate(new Timestamp(calender.getTime()
							.getTime()));
					repaynote.setMoneyTwo(Double.valueOf(Double.parseDouble(df
							.format(Double.valueOf(borrowmoney)
									.doubleValue()
									* ls.getRate().doubleValue()
									/ 1200.0D
									* Integer.parseInt(a.substring(0,
											a.indexOf('��')))))));
				}

				if (ls.getLssuingType().intValue() == 3) {
					Moneycount moneycount2 = this.moneycountService
							.findMoneycountByuserId(ls.getUservip().getUserId()
									.intValue());
					if (repaynote.getMoneyTwo().doubleValue() > moneycount2
							.getAvailableMoney().doubleValue()) {
						this.mark = 2;
						sf.getCurrentSession().getTransaction().commit();
						return "success";
					}

				}

			} else if ((this.lssuing.getState().intValue() == 6)|| (this.lssuing.getState().intValue() == 5)) {}

			if (this.lssuing.getState().intValue() == 2) {
				Website website = websiteService.findWebsiteBywebsiteId(1);
				website.setAccrual(Double.valueOf(df.format(Double
						.valueOf(String.valueOf(Math.round(ls.getRate()))))));
				if (ls.getPeriodday() != null
						&& ls.getPeriodday().getPeriodDayId() != null) {
					website.setAccrual(Double.valueOf(df.format(Double.valueOf(
							String.valueOf(Math.round(ls.getRate())))
							.doubleValue() * 360.0D)));
				}
				websiteService.updateWebsite(website);
				ls.setVerify_time(new Timestamp(new Date().getTime()));
				ls.setRecommend(lssuing.getRecommend());
				addInbox("���", "��ϲ��Ľ����Ϊ" + ls.getLssuingNum() + "�ĳ����ͨ����", ls
						.getUservip().getUserId());
			} else if (this.lssuing.getState().intValue() == 5)
				addInbox("���", "�Բ�����Ľ����Ϊ" + ls.getLssuingNum() + "�ĳ����δͨ����",
						ls.getUservip().getUserId());
			else if (this.lssuing.getState().intValue() == 6) {
				addInbox("���", "�Բ�����Ľ����Ϊ" + ls.getLssuingNum() + "�ĸ����δͨ��",
						ls.getUservip().getUserId());
			}
			// ����ͨ�������Զ�Ͷ���
			if ((this.lssuing.getState().intValue() == 2)
					&& (this.lssuing.getIsInvest() == 1)) {
				// �����Զ�Ͷ�����ط���
				boolean key = this.autoTender(ls);
				if (key) {
					this.lssuing.setState(1);
					ls.setState(this.lssuing.getState());
				}
			}
			if(this.lssuing.getState()!=3 && this.lssuing.getState()!=6){
				ls.setState(this.lssuing.getState());
			}
			ls.setIsInvest(this.lssuing.getIsInvest());
			if (this.queryFlag == 0)
				ls.setExplainOne(this.lssuing.getExplainOne());
			else if (this.queryFlag == 1) {
				ls.setExplianTwo(this.lssuing.getExplianTwo());
			}

//			String sql3 = "select * from(select  * from repaynote rp where rp.lssuingId="
//					+ ls.getLssuingId()
//					+ " and rp.repayState=0) a order by a.repayDate  ";
//			List lt = this.repaynoteService.findRepayBySql(sql3);
//			if ((lt != null) && (lt.size() > 0)) {
//				ls.setEveryReturnTime(((Repaynote) lt.get(0)).getRepayDate());
//			}

			ls.setManager(this.managerService.findManagerByManagerId(Integer
					.valueOf(this.managerId)));
			ls.setDealTime(new Timestamp(new Date().getTime()));
			this.lssuingService.updateLssuing(ls);
			this.lssuingId = ls.getLssuingId().intValue();

			if(this.lssuing.getState()==3 || this.lssuing.getState()==6){//�������й����ҵ������ 
				this.postform = this.borrowTenderVerVerify(ls,this.lssuing.getState());
				return "loanjump";
			}
			sf.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				System.err.println("���ִ���ʼ�ع�����");
				// ��������쳣��������ع���
				if (sf.getCurrentSession().getTransaction().isActive()) {
					sf.getCurrentSession().getTransaction().rollback();
				}
			} catch (Throwable rbEx) {
				System.err.println(rbEx.toString());
			}
		} finally {
			sf.getCurrentSession().close();
		}
		return "other";
	}
	/**
	 * ����Ͷ�긴��Ĵ���
	 * @param lssuing ������Ϣ
	 * @param state  ״̬���� 3��ͨ����6����ͨ��
	 * @return
	 * @throws Exception 
	 */
	public String borrowTenderVerVerify(Lssuing lssuing,int state) throws Exception{
		System.out.println("����Ͷ�긴��Ĵ���ʼ.........");
		HttpServletRequest request = ServletActionContext.getRequest();
		
		Website website = this.websiteService.findWebsiteBywebsiteId(1);
		
		//Ͷ���¼
		List<Tender> tenderlist = this.tenderService.findTendersBylssuingId(lssuing.getLssuingId().intValue());
		String orderliststr = "";
		if(tenderlist!=null && tenderlist.size()>0){
			for(int i=0;i<tenderlist.size();i++){
				Tender tender = tenderlist.get(i);
				if(i==tenderlist.size()-1){
					orderliststr += tender.getSerialnum();
				}else{
					orderliststr += tender.getSerialnum()+",";
				}
			}
			LoanUtils loanUtils = new LoanUtils();
			String auditType = "";
			if(state==3){
				auditType = "1";
			}
			if(state==6){
				auditType = "2";
			}
			String  postform = loanUtils.tenderaudit(orderliststr, website, auditType, request,lssuing);
			System.out.println("����Ͷ�긴��Ĵ������...");
			return postform;
		}
		return null;
	}
	public String miscarryTender() throws Exception {

		if (this.lssuingId == 0) {
			return "other";
		}
		if (ActionContext.getContext().getSession().get("manager") != null) {
			Manager manager = (Manager) ActionContext.getContext().getSession()
					.get("manager");
			this.managerId = manager.getManagerId();
		} else {
			return "other";
		}
		Lssuing ls = this.lssuingService.findLssuingById(this.lssuingId);

		List tenderList = this.tenderService.findTendersBylssuingId(ls
				.getLssuingId().intValue());
		//Ͷ���¼
		String orderliststr = "";
		if ((tenderList != null) && (tenderList.size() > 0)){

			HttpServletRequest request = ServletActionContext.getRequest();
			
			Website website = this.websiteService.findWebsiteBywebsiteId(1);
			for (int i = 0; i < tenderList.size(); i++) {
				Tender tender = (Tender)tenderList.get(i);
				if(i==tenderList.size()-1){
					orderliststr += tender.getSerialnum();
				}else{
					orderliststr += tender.getSerialnum()+",";
				}
			}
			LoanUtils loanUtils = new LoanUtils();
			String auditType = "";
			auditType = "2";
			this.postform = loanUtils.tenderfailaudit(orderliststr, website, auditType, request,ls);
			return "loanjump";
			
		}
		
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
				moneyhistorydetail.setIntroduction("��Ͷ�ı��ţ�"
						+ ls.getLssuingNum() + "���꣬Ͷ�ʽ���˻�");
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

				addInbox("�ʽ�䶯", "�����ʽ����䶯����ע��鿴", tender.getUservip()
						.getUserId());
				addInbox("Ͷ��", "�Բ������Ͷ��ʧ�ܣ�", tender.getUservip().getUserId());
				this.tenderService.deleteTender(tender);
			}
		ls.setState(6);
		this.lssuingService.updateLssuing(ls);
		addInbox("���", "�Բ�����Ľ����Ϊ" + ls.getLssuingNum() + "�ı�����", ls
				.getUservip().getUserId());
		return "other";
	}

	/**
	 * �Զ�Ͷ�괦��
	 * 
	 * @throws Exception
	 */
	public boolean autoTender(Lssuing ls) throws Exception {

		if (ls.getIsOrient() != null && ls.getIsOrient().intValue() == 1) {
			return false;
		}
		DecimalFormat df = new DecimalFormat("##.00");
		String autoSQl = "select * from Automaticbid a where a.enable=1 order by a.endTime";
		List automaticbidList = this.automaticbidService
				.findAutomaticbidBySql(autoSQl);

		if ((automaticbidList != null) && (automaticbidList.size() > 0)) {
			double startMoney = 0;
			// ��Ͷ��������
			Systemconf sysconf = this.systemconfService
					.findSystemconfByParname("con_auto_tender_total_limit");
			double totalLimitMoney = Double.valueOf(ls.getBorrowMoney());
			double temp = totalLimitMoney;
			if (sysconf != null && sysconf.getParvalue() != null
					&& !"".equals(sysconf.getParvalue().trim())) {
				totalLimitMoney = Double.valueOf(df.format(Double.valueOf(ls
						.getBorrowMoney())
						* Double.valueOf(sysconf.getParvalue()) / 100.0D));
				if (totalLimitMoney == 0) {
					totalLimitMoney = temp;
				}
				if (totalLimitMoney > temp) {
					totalLimitMoney = temp;
				}
			}

			// �����Զ�Ͷ��������
			Systemconf limiesysconf = this.systemconfService
					.findSystemconfByParname("con_auto_tender_limit");
			double limitMoney = Double.valueOf(ls.getBorrowMoney());
			if (limiesysconf != null && limiesysconf.getParvalue() != null
					&& !"".equals(limiesysconf.getParvalue().trim())) {
				limitMoney = Double.valueOf(df.format(Double.valueOf(ls
						.getBorrowMoney())
						* Double.valueOf(limiesysconf.getParvalue()) / 100.0D));
				if (limitMoney > temp) {
					limitMoney = temp;
				}
			}
			for (int i = 0; i < automaticbidList.size(); i++) {
				Automaticbid automaticbid = (Automaticbid) automaticbidList
						.get(i);
				boolean key = true;
				int money = Integer.valueOf(automaticbid
						.getAutomaticBidNumber());
				// �Լ�����Ͷ�Լ��ı�
				if (automaticbid.getUservip().getUserId().intValue() == ls
						.getUservip().getUserId().intValue()) {
					continue;
				}
				// ���ʽ��֤
				if (automaticbid.getReturnway() != null
						&& automaticbid.getReturnway().getReturnWayId() != ls
								.getReturnway().getReturnWayId()) {
					continue;
				}
				// ����������֤
				if (automaticbid.getLssuingType() != null
						&& automaticbid.getLssuingType().intValue() != ls
								.getLssuingType().intValue()) {
					continue;
				}
				// ���ʷ�Χ
				if (automaticbid.getStartMoneyRate() != null
						&& automaticbid.getStartMoneyRate().doubleValue() > ls
								.getRate()) {
					continue;
				}
				if (automaticbid.getEndMoneyRate() != null
						&& automaticbid.getEndMoneyRate().doubleValue() < ls
								.getRate()) {
					continue;
				}
				// �������
				if (automaticbid.getPeriodtime() != null
						&& (ls.getPeriodtime() == null || (ls.getPeriodtime()
								.getPeriodTimeId().intValue() != automaticbid
								.getPeriodtime().getPeriodTimeId().intValue()))) {
					continue;
				}

				// ��֤Ͷ����
				if (ls.getMoneymin() != null
						&& ls.getMoneymin().getMoneyMinName().intValue() > money) {
					continue;// ��СͶ��������Զ�Ͷ�����õĽ��
				}

				if (ls.getMoneymax() != null && ls.getMoneymax().getMoneyMaxName().intValue()!=0
						&& ls.getMoneymax().getMoneyMaxName().intValue() < money) {
					money = ls.getMoneymax().getMoneyMaxName().intValue();// ���Ͷ���޶�С���Զ�Ͷ�����ý��ʱ�������Ͷ����Ϊ׼
				}
				if (money > limitMoney) {
					money = (int) limitMoney;// ��������ڵ���Ͷ�����ý�Ͷ����������ƽ��
				}

				if ((startMoney + money) > totalLimitMoney) {
					continue;// ����ܵ�Ͷ�ʽ�������Ͷ�ʽ������
				}

				Uservip uservip = automaticbid.getUservip();
				Moneycount moneycount = this.moneycountService
						.findMoneycountByuserId(uservip.getUserId().intValue());
				if (ls.getMoneyLimit() != null
						&& !ls.getMoneyLimit().equals("")) {
					if (moneycount.getCollectTotalMoney() < Double.valueOf(ls
							.getMoneyLimit())) {
						continue;// �û��Ĵ��ս��С�ڱ�Ĵ��ս��
					}
				}

				// ���Ͷ�����Լ��Ͷ����������
				if (moneycount.getAvailableMoney().doubleValue() < money) {
					money = (int) moneycount.getAvailableMoney().doubleValue() / 50 * 50;
				}
				if (money > 0) {
					Tender tender = new Tender();
					tender.setLssuing(ls);
					tender.setUservip(uservip);
					tender.setState(0);
					tender.setMoney(money);
					tender.setTenderTime(new Timestamp(new Date().getTime()));
					
					Systemconf managerconf = null;
					if("1".equals(ls.getUservip().getIsVIP())){
						managerconf = this.systemconfService
								.findSystemconfByParname("con_borrow_manage_fee_2");
						
					}else{
						managerconf = this.systemconfService
								.findSystemconfByParname("con_borrow_manage_fee");
					}
					Systemconf dealConf = null;
					if("1".equals(ls.getUservip().getIsVIP())){
						dealConf = this.systemconfService
								.findSystemconfByParname("con_borrow_success_fee_2");
					}else{
						dealConf = this.systemconfService
								.findSystemconfByParname("con_borrow_success_fee");
					}
					Website website = this.websiteService.findWebsiteBywebsiteId(1);
					
					LoanUtils loanUtils = new LoanUtils();
					HttpServletRequest request = ServletActionContext.getRequest();
					String restr = loanUtils.tender(uservip, tender, ls, managerconf, dealConf, website, request, true, false);
					
					if(restr!=null && !restr.equals("")){
						List<Object> loanobjectlist = Common.JSONDecodeList(restr, LoanTransferReturnBean.class);
						if (loanobjectlist != null && loanobjectlist.size() > 0){
							for (int k = 0; k < loanobjectlist.size(); k++){
								if (loanobjectlist.get(k) instanceof LoanTransferReturnBean){
									LoanTransferReturnBean ltrb = (LoanTransferReturnBean) loanobjectlist.get(k);
									System.out.println(ltrb);
									
									ltrb.setLoanJsonList(Common.UrlDecoder(ltrb.getLoanJsonList(), "utf-8"));
									
									String publickey = website.getPublicKey();
									
									String dataStr = ltrb.getLoanJsonList() + ltrb.getPlatformMoneymoremore() + ltrb.getAction() + ltrb.getRandomTimeStamp() + ltrb.getRemark1() + ltrb.getRemark2() + ltrb.getRemark3() + ltrb.getResultCode();
									
									MD5Trust md5 = new MD5Trust();
									RsaHelper rsa = RsaHelper.getInstance();
									// ǩ��
									boolean verifySignature = rsa.verifySignature(ltrb.getSignInfo(), dataStr, publickey);
								
									if (verifySignature && ltrb.getResultCode().equals("88")){
										// ת���б�
										if (StringUtils.isNotBlank(ltrb.getLoanJsonList())){
											List<Object> loaninfolist = Common.JSONDecodeList(ltrb.getLoanJsonList(), LoanReturnInfoBean.class);
											if (loaninfolist != null && loaninfolist.size() > 0){
												for (int j = 0; j < loaninfolist.size(); j++){
													if (loaninfolist.get(j) instanceof LoanReturnInfoBean){
														LoanReturnInfoBean lrib = (LoanReturnInfoBean) loaninfolist.get(j);
														this.tenderlogic(lrib);
														startMoney += Double.valueOf(lrib.getAmount()).intValue();													
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			if (startMoney == temp) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Ͷ��ҵ���߼�����
	 * @param loanReturnInfoBean  ���ش���Ķ�����Ϣ
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	public void tenderlogic(LoanReturnInfoBean loanReturnInfoBean) throws NumberFormatException, Exception{
		String orderno = loanReturnInfoBean.getOrderNo();
		String loanno = loanReturnInfoBean.getLoanNo();
		String[] orderarray = orderno.split("_");
		
		String lssuingId = orderarray[1];
		String userid = orderarray[0];
		Tender tender = new Tender();
		Uservip uservip = this.uservipService.findUservipByUserid(Integer.valueOf(userid).intValue());
		Lssuing lssuing = this.lssuingService.findLssuingById(Integer.valueOf(lssuingId).intValue());
		tender.setBuynum(0);
		tender.setLssuing(lssuing);
		tender.setUservip(uservip);
		int m=Double.valueOf(loanReturnInfoBean.getAmount()).intValue();
		tender.setMoney(m);
		tender.setSerialnum(loanno);
		tender.setTransferAudit(0);
		tender.setState(0);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		tender.setTenderTime(timestamp);
		this.tenderService.addTender(tender);
		
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
		moneyhistorydetail.setIntroduction("�Զ�Ͷ��ɹ���������");
		moneyhistorydetail.setUservip(uservip);
		moneyhistorydetail
				.setOccurTime(new Timestamp(new Date().getTime()));
		this.moneyhistorydetailService
				.createMoneyhistorydetail(moneyhistorydetail);

		moneycount.setAvailableMoney(Double.valueOf(moneycount
				.getAvailableMoney().doubleValue()
				- Double.valueOf(tender.getMoney()).doubleValue()));
		moneycount.setFrozenMoney(Double.valueOf(moneycount
				.getFrozenMoney().doubleValue()
				+ Double.valueOf(tender.getMoney()).doubleValue()));
		this.moneycountService.updateMoneycount(moneycount);
	}
	

	private void addInbox(String c, String a, Integer b) throws Exception {
		Inbox inbox = new Inbox();
		inbox.setSendName(this.managerService.findManagerByManagerId(
				Integer.valueOf(this.managerId)).getManagerName());
		inbox.setTitle(c);
		inbox.setContent(a);
		inbox.setStatus(Integer.valueOf(0));
		inbox.setReceiveTime(new Timestamp(new Date().getTime()));
		inbox.setUservip(this.uservipService.findUservipByUserid(b.intValue()));
		this.inboxService.createInbox(inbox);
	}

	/**
	 * ��ҵ������ -��̨������ͼ
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toFirmView() throws Exception {
		this.uservipList = this.uservipService.findUservips();
		this.moneyminList = this.moneyminService.findMoneymins();
		this.periodtimeList = this.periodtimeService.findPeriodtimes();
		this.returnwayList = this.returnwayService.findReturnways();
		this.validtimeList = this.validtimeService.findValidtimes();
		this.perioddayList = this.perioddayService.findPerioddays();
		this.moneymaxList = this.moneymaxService.findMoneymaxs();
		return "success";
	}

	/**
	 * ��Ʊ� -��̨������ͼ
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toLcView() throws Exception {
		this.uservipList = this.uservipService.findUservips();
		this.moneyminList = this.moneyminService.findMoneymins();
		this.periodtimeList = this.periodtimeService.findPeriodtimes();
		this.returnwayList = this.returnwayService.findReturnways();
		this.validtimeList = this.validtimeService.findValidtimes();
		this.perioddayList = this.perioddayService.findPerioddays();
		this.moneymaxList = this.moneymaxService.findMoneymaxs();
		return "success";
	}

	public String getUrls() {
		return this.urls;
	}

	public void setUrls(String urls) {
		this.urls = urls;
	}

	public String getUrlIds() {
		return this.urlIds;
	}

	public void setUrlIds(String urlIds) {
		this.urlIds = urlIds;
	}

	/**
	 * ������===���=��ͼƬ
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toSavePhoto() throws Exception {
		if ((".jpg".equals(this.fileFileName.substring(this.fileFileName
				.indexOf("."))))
				|| (".bmp".equals(this.fileFileName.substring(this.fileFileName
						.indexOf("."))))
				|| (".gif".equals(this.fileFileName.substring(this.fileFileName
						.indexOf("."))))
				|| (".png".equals(this.fileFileName.substring(this.fileFileName
						.indexOf("."))))) {
			this.urlStr = UploadPhoto.LoadImageToServer(this.fileFileName,
					this.file);
			if ((this.urlStr != null) && (!"".equals(this.urlStr))) {
				this.message = "���ѳɹ��ϴ��ļ�";
				Lssingphoto lp = new Lssingphoto();
				lp.setPhoto(this.urlStr);
				lp.setLssuing(this.lssuingService.findLssuingById(Integer
						.valueOf(this.lssuingId)));
				this.lssingphotoService.addLssingphoto(lp);
				List lpl = this.lssingphotoService
						.findLssingphotosBylssuing(this.lssuingId);
				if ((lpl != null) && (lpl.size() > 0)) {
					this.urls = ((Lssingphoto) lpl.get(0)).getPhoto();
					this.urlIds = "" + ((Lssingphoto) lpl.get(0)).getPhotoId();
					for (int i = 1; i < lpl.size(); i++) {
						this.urls = (this.urls + "-" + ((Lssingphoto) lpl
								.get(i)).getPhoto());
						this.urlIds = (this.urlIds + "-" + ((Lssingphoto) lpl
								.get(i)).getPhotoId());
					}
				}
				return "success";
			}
			this.message = "�ϴ��ļ�ʧ��";
			return "error";
		}

		this.message = "�ϴ��ļ�ʧ��";
		return "error";
	}

	/**
	 * ������===���=ɾ����ͼƬ
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toDeleteP() throws Exception {
		this.lssingphoto = new Lssingphoto();
		this.lssingphoto.setPhotoId(Integer.valueOf(this.photoId));
		this.lssingphotoService.deleteLssingphoto(this.lssingphoto);
		this.message = "���ѳɹ�ɾ��";
		return "success";
	}

	/**
	 * ��ҵ������===���=����ͼƬ
	 * 
	 * @return
	 * @throws Exception
	 */
	public String SavePhoto() throws Exception {
		File f = getFile();
		File f2 = getFile2();

		if (f != null) {
			if ((".jpg".equals(this.fileFileName.substring(this.fileFileName
					.indexOf("."))))
					|| (".bmp".equals(this.fileFileName
							.substring(this.fileFileName.indexOf("."))))
					|| (".gif".equals(this.fileFileName
							.substring(this.fileFileName.indexOf("."))))
					|| (".png".equals(this.fileFileName
							.substring(this.fileFileName.indexOf("."))))) {
				this.urlStr = UploadPhoto.LoadImageToServer(this.fileFileName,
						this.file);
			}
		} else {
			if ((".jpg".equals(this.file2FileName.substring(this.file2FileName
					.indexOf("."))))
					|| (".bmp".equals(this.file2FileName
							.substring(this.file2FileName.indexOf("."))))
					|| (".gif".equals(this.file2FileName
							.substring(this.file2FileName.indexOf("."))))
					|| (".png".equals(this.file2FileName
							.substring(this.file2FileName.indexOf("."))))) {
				this.urlStr = UploadPhoto.LoadImageToServer(this.file2FileName,
						this.file2);
			}
		}
		return "success";
	}

	/**
	 * ���������-���
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toTiroLssuing() throws Exception {
		DecimalFormat df = new DecimalFormat("##.00");
		Oerationlog oerationlog = new Oerationlog();
		oerationlog.setManager(this.managerService
				.findManagerByManagerId(Integer.valueOf(this.managerId)));
		oerationlog.setOerationCategory("updateLssuing");
		oerationlog.setOerationChangeTime(new Timestamp(new Date().getTime()));
		oerationlog.setOrationIp(InetAddress.getLocalHost().getHostAddress());

		Lssuing ls = this.lssuingService.findLssuingById(Integer
				.valueOf(this.lssuingId));
		if (this.updateFlag == 1) {
			ls.setTitle(this.lssuing.getTitle());
			ls.setReturnway(this.returnwayService.findReturnwayById(Integer
					.valueOf(this.returnway)));
			ls.setBorrowMoney(this.lssuing.getBorrowMoney());
			ls.setRate(this.lssuing.getRate());
			if (this.periodday != 0) {
				ls.setPeriodday(this.perioddayService
						.findPerioddayByPerioddayId(this.periodday));
				ls.setPeriodtime(null);
			}
			if (this.periodtime != 0) {
				ls.setPeriodday(null);
				ls.setPeriodtime(this.periodtimeService
						.findPeriodtimeByPeriodtimeId(this.periodtime));
			}
			ls.setExplains(this.lssuing.getExplains());
			this.lssuingService.updateLssuing(ls);
			this.lssuingId = ls.getLssuingId().intValue();
			oerationlog.setOerationRemaks("�޸ı����Ϣ");
			this.oerationlogService.createOerationlog(oerationlog);
			this.queryFlag = this.queryFlag;
			return "success";
		}
		List internallettermodelList = this.internallettermodelService
				.findInternallettermodels();
		oerationlog.setOerationRemaks("��˱�");
		this.oerationlogService.createOerationlog(oerationlog);
		ls.setManageMoney(this.lssuing.getManageMoney());
		ls.setValidtime(this.validtimeService.findValidtimeById(Integer
				.valueOf(this.validtime)));
		ls.setMoneymax(this.moneymaxService.findMoneymaxById(Integer
				.valueOf(this.moneymax)));
		if ((this.lssuing.getMoneyLimit() != null)
				&& (!"".equals(this.lssuing.getMoneyLimit()))
				&& (!"0.00".equals(this.lssuing.getMoneyLimit()))) {
			ls.setMoneyLimit(this.lssuing.getMoneyLimit());
			ls.setTenderLimit(Integer.valueOf(1));
		}
		// �긴��ͨ��
		if (this.lssuing.getState().intValue() == 3) {
			if (ls.getState() == 3 || ls.getState() == 6) {
				return "other";
			}

			ls.setVerify_time(new Timestamp(new Date().getTime()));
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd HH-mm-ss");
			Calendar calender = Calendar.getInstance();
			calender.setTime(ls.getVerify_time());
			long start = calender.getTimeInMillis();

			format.format(calender.getTime());
			Repaynote repaynote = new Repaynote();
			Moneycount moneycount = new Moneycount();
			List<Tender> tenderList = this.tenderService
					.findTendersBylssuingId(ls.getLssuingId().intValue());
			Moneyhistorydetail moneyhistorydetail = new Moneyhistorydetail();

			Uservip uservip1 = this.uservipService.findUservipByUserid(ls
					.getUservip().getUserId().intValue());
			repaynote.setUservip(uservip1);
			if (ls.getPeriodday() != null) {
				String a = ls.getPeriodday().getPeriodDayName();
				calender.add(5,
						Integer.parseInt(a.substring(0, a.indexOf('��'))));
				repaynote.setRepayDate(new Timestamp(calender.getTime()
						.getTime()));
				repaynote.setMoneyTwo(Double.valueOf(Double.parseDouble(df
						.format(Double.valueOf(ls.getBorrowMoney())
								.doubleValue()
								* ls.getRate().doubleValue()
								/ 100.0D
								* Integer.parseInt(a.substring(0,
										a.indexOf('��')))))));
			} else {
				String a = ls.getPeriodtime().getPeriodTimeName();
				calender.add(2,
						Integer.parseInt(a.substring(0, a.indexOf('��'))));
				repaynote.setRepayDate(new Timestamp(calender.getTime()
						.getTime()));
				repaynote.setMoneyTwo(Double.valueOf(Double.parseDouble(df
						.format(Double.valueOf(ls.getBorrowMoney())
								.doubleValue()
								* ls.getRate().doubleValue()
								/ 1200.0D
								* Integer.parseInt(a.substring(0,
										a.indexOf('��')))))));
			}

			if (ls.getLssuingType().intValue() == 3) {
				Moneycount moneycount2 = this.moneycountService
						.findMoneycountByuserId(ls.getUservip().getUserId()
								.intValue());
				if (repaynote.getMoneyTwo().doubleValue() > moneycount2
						.getAvailableMoney().doubleValue()) {
					this.mark = 2;
					return "success";
				}

			}

			repaynote.setMoneyOne(Double.valueOf(ls.getBorrowMoney()));
			repaynote.setMoneyFour(Double.valueOf(df.format(repaynote
					.getMoneyOne().doubleValue()
					+ repaynote.getMoneyTwo().doubleValue())));
			repaynote.setRepayState(Integer.valueOf(0));
			repaynote.setLssuing(ls);

			moneycount = this.moneycountService.findMoneycountByuserId(ls
					.getUservip().getUserId().intValue());
			moneyhistorydetail = new Moneyhistorydetail();
			moneyhistorydetail.setAffectMoney(repaynote.getMoneyOne());
			moneyhistorydetail
					.setOccurTime(new Timestamp(new Date().getTime()));
			moneyhistorydetail.setAvailableBalance(Double.valueOf(df
					.format(moneycount.getAvailableMoney()
							+ repaynote.getMoneyOne())));
			moneyhistorydetail.setCollectMoney(Double.valueOf(df
					.format(moneycount.getCollectTotalMoney())));
			moneyhistorydetail.setFrozenMoney(moneycount.getFrozenMoney());
			moneyhistorydetail.setUservip(ls.getUservip());
			moneyhistorydetail.setIntroduction("��ţ�" + ls.getLssuingNum()
					+ "���ɹ�����ʼ����");
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

			Website website = websiteService.findWebsiteBywebsiteId(1);
			website.setInvestment(website.getInvestment()
					+ Double.valueOf(ls.getBorrowMoney()));
			websiteService.updateWebsite(website);

			// ����Ͷ�����ʽ����
			if ((tenderList != null) && (tenderList.size() > 0)) {
				for (int j = 0; j < tenderList.size(); j++) {
					Tender tender = (Tender) tenderList.get(j);
					String mon = "";
					Integer mon2 = 0;
					Double money = Double.valueOf(Double.parseDouble(df // Ͷ����
							.format(Double
									.valueOf(tender.getMoney().intValue()))));
					Double money2 = 0.0;// ��Ϣ
					if (ls.getPeriodtime() != null) {
						mon = ls.getPeriodtime().getPeriodTimeName();
						mon2 = Integer.valueOf(Integer.parseInt(mon.substring(
								0, mon.indexOf('��'))));
						money2 = Double.valueOf(Double.parseDouble(df
								.format(Double.valueOf(
										tender.getMoney().intValue())
										.doubleValue()
										* ls.getRate().doubleValue()
										* mon2.intValue() / 1200.0D)));
					}
					if (ls.getPeriodday() != null) {
						mon = ls.getPeriodday().getPeriodDayName();
						mon2 = Integer.valueOf(Integer.parseInt(mon.substring(
								0, mon.indexOf('��'))));
						money2 = Double.valueOf(Double.parseDouble(df
								.format(Double.valueOf(
										tender.getMoney().intValue())
										.doubleValue()
										* ls.getRate().doubleValue()
										* mon2.intValue() / 100.0D)));
					}

					Double money3 = 0.0;// Ͷ�꽱��

					// �ʽ�ͳ�Ʊ�
					Moneycount tendmoneycount = this.moneycountService
							.findMoneycountByuserId(tender.getUservip()
									.getUserId().intValue());
					moneyhistorydetail = new Moneyhistorydetail();// �ʽ���ʷ��ϸ
					moneyhistorydetail.setAffectMoney(money); // Ӱ����
					moneyhistorydetail.setFrozenMoney(Double // ������
							.valueOf(tendmoneycount.getFrozenMoney()
									.doubleValue() - money.doubleValue()));
					moneyhistorydetail.setCollectMoney(Double.valueOf(df // ���ս��
							.format(tendmoneycount.getCollectTotalMoney()
							// + money.doubleValue() ��������ֻ����Ϣ
									+ money2.doubleValue())));
					moneyhistorydetail.setAvailableBalance(tendmoneycount // �������
							.getAvailableMoney());
					moneyhistorydetail.setOccurTime(new Timestamp(new Date()
							.getTime()));
					moneyhistorydetail.setUservip(tender.getUservip());
					moneyhistorydetail.setIntroduction("����������ţ�"
							+ ls.getLssuingNum() + "Ͷ�ʳɹ������ս������");
					this.moneyhistorydetailService
							.createMoneyhistorydetail(moneyhistorydetail);

					tendmoneycount.setAvailableMoney(Double // �������
							.valueOf(tendmoneycount.getAvailableMoney()
									.doubleValue() + money3.doubleValue()));
					tendmoneycount.setDueInMoney(Double.valueOf(tendmoneycount // ���ձ���
							.getDueInMoney().doubleValue()));
					tendmoneycount.setCollectTotalMoney(Double
							.valueOf(tendmoneycount.getCollectTotalMoney()
									+ money2.doubleValue()));

					tendmoneycount.setFrozenMoney(Double.valueOf(tendmoneycount // ������
							.getFrozenMoney().doubleValue()
							- money.doubleValue()));
					tendmoneycount.setTotalMoney(Double.valueOf(tendmoneycount // ���ʲ�
							.getTotalMoney().doubleValue()
							+ money2.doubleValue() + money3.doubleValue()));
					tendmoneycount.setAccuBidReward(Double // �ۼ�Ͷ�꽱��
							.valueOf(tendmoneycount.getAccuBidReward()
									.doubleValue() + money3.doubleValue()));
					tendmoneycount.setAccuContinueBidReward(Double // �ۼ���Ͷ����
							.valueOf(tendmoneycount.getAccuContinueBidReward()
									.doubleValue()));
					tendmoneycount.setAccuProfitAndLossMoney(Double
							.valueOf(tendmoneycount.getAccuProfitAndLossMoney()
									.doubleValue() + money3.doubleValue()));
					tendmoneycount.setAccuInvestMoney(Double
							.valueOf(tendmoneycount.getAccuInvestMoney()
									.doubleValue()));
					tendmoneycount.setCollectInterestTotalFee(Double
							.valueOf(tendmoneycount
									.getCollectInterestTotalFee().doubleValue()
									+ money2.doubleValue()));

					// tendmoneycount.setExperienceMoney(tendmoneycount.getExperienceMoney()-money);//���������
					this.moneycountService.updateMoneycount(tendmoneycount);

					tender.setState(1);
					tenderService.updateTender(tender);

					addInbox("�ʽ�䶯", "�����ʽ����䶯����ע��鿴", tender.getUservip()
							.getUserId());
					addInbox("���������Ͷ��",
							"��ϲ������������Ͷ�ʳɹ������Ϊ" + ls.getLssuingNum() + "��",
							tender.getUservip().getUserId());
				}
			}
			if (ls.getReturnway().getReturnWayId().intValue() == 1) {
				this.repaynoteService.addRepaynote(repaynote);

				if ((tenderList != null) && (tenderList.size() > 0))
					for (int i = 0; i < tenderList.size(); i++) {
						Tender tender = (Tender) tenderList.get(i);

						Record record = new Record();// Ͷ�ʼ�¼��
						Double money = Double.valueOf(tender.getMoney() // Ͷ�ʽ��
								.intValue());
						Double money2 = 0.0;// ��Ϣ
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
						record.setRecordMoney(0.00);
						record.setRecordRate(money2);
						// 333
						// record.setRecordInterest();
						record.setUservip(this.uservipService
								.findUservipByUserid(tender.getUservip()
										.getUserId().intValue()));
						record.setTender(tender);
						record.setRecordState(Integer.valueOf(0));

						this.recordService.addRecord(record);
					}
			}

			addInbox("�ʽ�䶯", "�����ʽ����䶯����ע��鿴", ls.getUservip().getUserId());
			addInbox("���", "��ϲ��Ľ��ɹ������Ϊ" + ls.getLssuingNum() + "��", ls
					.getUservip().getUserId());
		} else if ((this.lssuing.getState().intValue() == 6)
				|| (this.lssuing.getState().intValue() == 5)) {
			if (ls.getState() == 3 || ls.getState() == 6) {
				return "other";
			}
			// ��˲�ͨ�������
			List tenderList = this.tenderService.findTendersBylssuingId(ls
					.getLssuingId().intValue());
			if ((tenderList != null) && (tenderList.size() > 0))
				for (int j = 0; j < tenderList.size(); j++) {
					Tender tender = (Tender) tenderList.get(j);
					Moneycount moneycount = this.moneycountService
							.findMoneycountByuserId(tender.getUservip()
									.getUserId().intValue());
					Moneyhistorydetail moneyhistorydetail = new Moneyhistorydetail();
					moneyhistorydetail.setAffectMoney(Double.valueOf(tender // Ӱ����
							.getMoney().intValue()));
					moneyhistorydetail.setAvailableBalance(Double // �������
							.valueOf(moneycount.getAvailableMoney()
									.doubleValue()
							// + tender.getMoney().intValue()
							));
					moneyhistorydetail.setFrozenMoney(Double.valueOf(moneycount // ������
							.getFrozenMoney().doubleValue()
							- tender.getMoney().intValue()));
					moneyhistorydetail.setCollectMoney(moneycount
							.getCollectTotalMoney());
					moneyhistorydetail.setOccurTime(new Timestamp(new Date()
							.getTime()));
					moneyhistorydetail.setIntroduction("��Ͷ�ı긴���δͨ��");
					moneyhistorydetail.setUservip(tender.getUservip());
					this.moneyhistorydetailService
							.createMoneyhistorydetail(moneyhistorydetail);

					moneycount.setAvailableMoney(Double.valueOf(moneycount // �������
							.getAvailableMoney().doubleValue()
					// + tender.getMoney().intValue()
							));
					moneycount.setFrozenMoney(Double.valueOf(moneycount // ������
							.getFrozenMoney().doubleValue()
							- tender.getMoney().intValue()));

					moneycount.setExperienceMoney(moneycount
							.getExperienceMoney() + tender.getMoney());
					this.moneycountService.updateMoneycount(moneycount);

					addInbox("�ʽ�䶯", "�����ʽ����䶯����ע��鿴", tender.getUservip()
							.getUserId());
					addInbox("Ͷ��", "�Բ������Ͷ��ʧ�ܣ�", tender.getUservip()
							.getUserId());
					this.tenderService.deleteTender(tender);
				}
		}

		if (this.lssuing.getState().intValue() == 2) {
			Website website = websiteService.findWebsiteBywebsiteId(1);
			website.setAccrual(Double.valueOf(df.format(Double.valueOf(String
					.valueOf(Math.round(ls.getRate()))))));
			if (ls.getPeriodday() != null
					&& ls.getPeriodday().getPeriodDayId() != null) {
				website.setAccrual(Double.valueOf(df
						.format(Double.valueOf(
								String.valueOf(Math.round(ls.getRate())))
								.doubleValue() * 360.0D)));
			}
			websiteService.updateWebsite(website);
			ls.setVerify_time(new Timestamp(new Date().getTime()));
			addInbox("���", "��ϲ��Ľ����Ϊ" + ls.getLssuingNum() + "�ĳ����ͨ����", ls
					.getUservip().getUserId());
		} else if (this.lssuing.getState().intValue() == 5)
			addInbox("���", "�Բ�����Ľ����Ϊ" + ls.getLssuingNum() + "�ĳ����δͨ����", ls
					.getUservip().getUserId());
		else if (this.lssuing.getState().intValue() == 6) {
			addInbox("���", "�Բ�����Ľ����Ϊ" + ls.getLssuingNum() + "�ĸ����δͨ��", ls
					.getUservip().getUserId());
		}
		// ����ͨ�������Զ�Ͷ���
		if ((this.lssuing.getState().intValue() == 2)
				&& (this.lssuing.getIsInvest() == 1)) {
			// �����Զ�Ͷ�����ط���
			boolean key = this.autoTender(ls);
			if (key) {
				this.lssuing.setState(1);
			}
		}

		ls.setState(this.lssuing.getState());
		ls.setIsInvest(this.lssuing.getIsInvest());
		if (this.queryFlag == 0)
			ls.setExplainOne(this.lssuing.getExplainOne());
		else if (this.queryFlag == 1) {
			ls.setExplianTwo(this.lssuing.getExplianTwo());
		}

		String sql3 = "select * from(select  * from repaynote rp where rp.lssuingId="
				+ ls.getLssuingId()
				+ " and rp.repayState=0) a order by a.repayDate  ";
		List lt = this.repaynoteService.findRepayBySql(sql3);
		if ((lt != null) && (lt.size() > 0)) {
			ls.setEveryReturnTime(((Repaynote) lt.get(0)).getRepayDate());
		}

		ls.setManager(this.managerService.findManagerByManagerId(Integer
				.valueOf(this.managerId)));
		ls.setDealTime(new Timestamp(new Date().getTime()));
		this.lssuingService.updateLssuing(ls);
		this.lssuingId = ls.getLssuingId().intValue();

		if (ls.getLssuingType().intValue() == 3) {

			Repaynote repaynote = new Repaynote();
			if ((this.repaynoteService.findRepaynoteBylssuing(this.lssuingId) != null)
					&& (this.repaynoteService.findRepaynoteBylssuing(
							this.lssuingId).size() > 0)) {
				repaynote = (Repaynote) this.repaynoteService
						.findRepaynoteBylssuing(this.lssuingId).get(0);
				repaynote.setRepayDate(new Timestamp(new Date().getTime()));
				repaynote.setMoneyThree(repaynote.getMoneyFour());
				repaynote.setRepayState(Integer.valueOf(1));
				this.repaynoteService.updateRepaynote(repaynote);

				Moneycount moneycount5 = this.moneycountService
						.findMoneycountByuserId(ls.getUservip().getUserId()
								.intValue());
				Moneyhistorydetail md = new Moneyhistorydetail();
				md.setOccurTime(new Timestamp(new Date().getTime()));
				md.setAffectMoney(repaynote.getMoneyThree());
				md.setAvailableBalance(Double.valueOf(moneycount5
						.getAvailableMoney().doubleValue()
						- repaynote.getMoneyThree().doubleValue()));
				md.setCollectMoney(moneycount5.getCollectTotalMoney());
				md.setFrozenMoney(moneycount5.getFrozenMoney());
				md.setUservip(ls.getUservip());
				md.setIntroduction("����ɹ�");
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

				// �������վ�ѻ����
				Website website = websiteService.findWebsiteBywebsiteId(1);
				website.setLoan(website.getLoan() + repaynote.getMoneyFour());
				websiteService.updateWebsite(website);

				ls.setState(Integer.valueOf(4));
				ls.setReturnMoney(repaynote.getMoneyFour());
				ls.setEveryReturnTime(repaynote.getRepayDate());
				this.lssuingService.updateLssuing(ls);
				addInbox("�ʽ�䶯", "�����ʽ����䶯����ע��鿴", ls.getUservip().getUserId());
				addInbox("����", "��ϲ�㻹��ɹ���", ls.getUservip().getUserId());
			}

			String sql4 = "select * from Record r where r.tenderId in (select tenderId from Tender t where t.lssuingId="
					+ ls.getLssuingId() + ") and recordState=0";
			List recordList4 = this.recordService.findRecordByRecordId(sql4);
			if ((recordList4 != null) && (recordList4.size() > 0)) {
				for (int i = 0; i < recordList4.size(); i++) {
					Record record = (Record) recordList4.get(i);
					record.setRecordInterest(Double.valueOf(record
							.getRecordMoney().doubleValue()
							+ record.getRecordRate().doubleValue()));

					double collectmoney = Double.valueOf(
							record.getRecordMoney().doubleValue()
									+ record.getRecordRate().doubleValue())
							.doubleValue();

					record.setRecordDate(new Timestamp(new Date().getTime()));
					record.setRecordState(Integer.valueOf(0));
					this.recordService.updateRecord(record);

					Moneycount moneycount4 = this.moneycountService
							.findMoneycountByuserId(record.getUservip()
									.getUserId().intValue());

					Moneyhistorydetail md = new Moneyhistorydetail();
					md.setOccurTime(new Timestamp(new Date().getTime()));
					md.setAffectMoney(record.getRecordInterest());
					md.setAvailableBalance(Double.valueOf(moneycount4
							.getAvailableMoney().doubleValue()
							+ record.getRecordInterest().doubleValue()));
					md.setCollectMoney(Double.valueOf(moneycount4
							.getCollectTotalMoney() - collectmoney));
					md.setFrozenMoney(moneycount4.getFrozenMoney());
					md.setUservip(record.getUservip());
					md.setIntroduction("Ͷ�ʻؿ�ɹ�");
					this.moneyhistorydetailService.updateMoneyhistorydetail(md);

					moneycount4.setAvailableMoney(Double.valueOf(moneycount4
							.getAvailableMoney().doubleValue()
							+ record.getRecordInterest().doubleValue()));
					moneycount4.setDueInMoney(Double.valueOf(moneycount4
							.getDueInMoney().doubleValue()));
					moneycount4.setCollectTotalMoney(Double.valueOf(moneycount4
							.getCollectTotalMoney() - collectmoney));
					moneycount4.setNetEarnInterest(Double.valueOf(moneycount4
							.getNetEarnInterest().doubleValue()
							+ record.getRecordRate().doubleValue()));
					moneycount4.setAccuProfitAndLossMoney(Double
							.valueOf(moneycount4.getAccuProfitAndLossMoney()
									.doubleValue()
									+ record.getRecordRate().doubleValue()));
					moneycount4.setCollectInterestTotalFee(Double
							.valueOf(moneycount4.getCollectInterestTotalFee()
									.doubleValue()
									- record.getRecordRate().doubleValue()));
					this.moneycountService.updateMoneycount(moneycount4);

					String sql = "select  * from record r where r.tenderId="
							+ record.getTender().getTenderId()
							+ " and r.recordState!=1 ";
					List recordss = this.recordService.findRecordBySql(sql);
					if ((recordss != null) && (recordss.size() == 0)) {
						Tender tender = record.getTender();
						tender.setState(2);
						this.tenderService.updateTender(tender);
					}

					addInbox("�ʽ�䶯", "���Ļؿ��ѵ��˻�����ע��鿴", record.getUservip()
							.getUserId());
				}
			}

		}

		return "other";
	}

	public int getLssuingId() {
		return this.lssuingId;
	}

	public void setLssuingId(int lssuingId) {
		this.lssuingId = lssuingId;
	}

	public int getManagerId() {
		return this.managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
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

	public List<Repaynote> getRepaynoteList() {
		return repaynoteList;
	}

	public void setRepaynoteList(List<Repaynote> repaynoteList) {
		this.repaynoteList = repaynoteList;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public List<Moneymax> getMoneymaxList() {
		return this.moneymaxList;
	}

	public void setMoneymaxList(List<Moneymax> moneymaxList) {
		this.moneymaxList = moneymaxList;
	}

	public List<Lssingphoto> getLssingphotoList() {
		return this.lssingphotoList;
	}

	public void setLssingphotoList(List<Lssingphoto> lssingphotoList) {
		this.lssingphotoList = lssingphotoList;
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

	public int getPeriodday() {
		return this.periodday;
	}

	public void setPeriodday(int periodday) {
		this.periodday = periodday;
	}

	public int getPeriodtime() {
		return this.periodtime;
	}

	public void setPeriodtime(int periodtime) {
		this.periodtime = periodtime;
	}

	public int getMoneymax() {
		return this.moneymax;
	}

	public void setMoneymax(int moneymax) {
		this.moneymax = moneymax;
	}

	public int getUpdateFlag() {
		return this.updateFlag;
	}

	public void setUpdateFlag(int updateFlag) {
		this.updateFlag = updateFlag;
	}

	public Lssingphoto getLssingphoto() {
		return this.lssingphoto;
	}

	public void setLssingphoto(Lssingphoto lssingphoto) {
		this.lssingphoto = lssingphoto;
	}

	public int getPhotoId() {
		return this.photoId;
	}

	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}

	public String getUserNameQuery() {
		return this.userNameQuery;
	}

	public void setUserNameQuery(String userNameQuery) {
		this.userNameQuery = userNameQuery;
	}

	public Double getMoneyQuery() {
		return this.moneyQuery;
	}

	public void setMoneyQuery(Double moneyQuery) {
		this.moneyQuery = moneyQuery;
	}

	public Timestamp getStartTimeQuery() {
		return this.startTimeQuery;
	}

	public void setStartTimeQuery(Timestamp startTimeQuery) {
		this.startTimeQuery = startTimeQuery;
	}

	public Timestamp getEndTimeQuery() {
		return this.endTimeQuery;
	}

	public void setEndTimeQuery(Timestamp endTimeQuery) {
		this.endTimeQuery = endTimeQuery;
	}

	public Integer getTotal() {
		return this.total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<Tender> getTenderList() {
		return this.tenderList;
	}

	public void setTenderList(List<Tender> tenderList) {
		this.tenderList = tenderList;
	}

	public File getFile() {
		return this.file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return this.fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getUrlStr() {
		return this.urlStr;
	}

	public void setUrlStr(String urlStr) {
		this.urlStr = urlStr;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Moneymin> getMoneyminList() {
		return moneyminList;
	}

	public void setMoneyminList(List<Moneymin> moneyminList) {
		this.moneyminList = moneyminList;
	}

	public List<Uservip> getUservipList() {
		return uservipList;
	}

	public void setUservipList(List<Uservip> uservipList) {
		this.uservipList = uservipList;
	}

	public String getFile2FileName() {
		return file2FileName;
	}

	public void setFile2FileName(String file2FileName) {
		this.file2FileName = file2FileName;
	}

	public File getFile2() {
		return file2;
	}

	public void setFile2(File file2) {
		this.file2 = file2;
	}

	public String getLssuingType() {
		return lssuingType;
	}

	public void setLssuingType(String lssuingType) {
		this.lssuingType = lssuingType;
	}

	public String getPostform() {
		return postform;
	}

	public void setPostform(String postform) {
		this.postform = postform;
	}
	
}