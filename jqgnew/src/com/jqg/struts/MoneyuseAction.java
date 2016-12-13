package com.jqg.struts;

import java.net.InetAddress;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.jqg.pojo.Bankparameter;
import com.jqg.pojo.Findmoney;
import com.jqg.pojo.Integralparameter;
import com.jqg.pojo.Manager;
import com.jqg.pojo.Moneymax;
import com.jqg.pojo.Moneymin;
import com.jqg.pojo.Moneyuse;
import com.jqg.pojo.Oerationlog;
import com.jqg.pojo.Periodday;
import com.jqg.pojo.Periodtime;
import com.jqg.pojo.Systemconf;
import com.jqg.pojo.Validtime;
import com.jqg.service.BankparameterService;
import com.jqg.service.FindmoneyService;
import com.jqg.service.IntegralparameterService;
import com.jqg.service.MoneymaxService;
import com.jqg.service.MoneyminService;
import com.jqg.service.MoneyuseService;
import com.jqg.service.OerationlogService;
import com.jqg.service.PerioddayService;
import com.jqg.service.PeriodtimeService;
import com.jqg.service.SystemconfService;
import com.jqg.service.ValidtimeService;
import com.jqg.service.impl.BankparameterServiceImpl;
import com.jqg.service.impl.FindmoneyServiceImpl;
import com.jqg.service.impl.IntegralparameterServiceImpl;
import com.jqg.service.impl.MoneymaxServiceImpl;
import com.jqg.service.impl.MoneyminServiceImpl;
import com.jqg.service.impl.MoneyuseServiceImpl;
import com.jqg.service.impl.OerationlogServiceImpl;
import com.jqg.service.impl.PeriodDayServiceImpl;
import com.jqg.service.impl.PeriodtimeServiceImpl;
import com.jqg.service.impl.SystemconfServiceImpl;
import com.jqg.service.impl.ValidtimeServiceImpl;
import com.opensymphony.xwork2.ActionContext;

public class MoneyuseAction extends BaseAction {
	private MoneyuseService moneyuseService = new MoneyuseServiceImpl();
	private MoneyminService moneyminService = new MoneyminServiceImpl();
	private MoneymaxService moneymaxService = new MoneymaxServiceImpl();
	private ValidtimeService validtimeService = new ValidtimeServiceImpl();
	private FindmoneyService findmoneyService = new FindmoneyServiceImpl();
	private BankparameterService bankparameterService = new BankparameterServiceImpl();
	private IntegralparameterService integralparameterService = new IntegralparameterServiceImpl();
	private OerationlogService oerationlogService = new OerationlogServiceImpl();
	private SystemconfService systemconfService = new SystemconfServiceImpl();
	private PerioddayService  perioddayService =new PeriodDayServiceImpl();
	private PeriodtimeService  periodtimeService =new PeriodtimeServiceImpl();
	private List<Moneyuse> moneyuses;
	private List<Moneymin> moneymins;
	private List<Moneymax> moneymaxs;
	private List<Validtime> validtimes;
	private List<Findmoney> findmoneys;
	private List<Bankparameter> bankparameters;
	private List<Integralparameter> integralparameters;
	private List<Systemconf> systemconfs;
	private List<Periodday> perioddays;
	private List<Periodtime> periodtimes;
	
	public List<Periodday> getPerioddays() {
		return perioddays;
	}

	public void setPerioddays(List<Periodday> perioddays) {
		this.perioddays = perioddays;
	}

	public List<Periodtime> getPeriodtimes() {
		return periodtimes;
	}

	public void setPeriodtimes(List<Periodtime> periodtimes) {
		this.periodtimes = periodtimes;
	}

	public List<Systemconf> getSystemconfs() {
		return systemconfs;
	}

	public void setSystemconfs(List<Systemconf> systemconfs) {
		this.systemconfs = systemconfs;
	}

	private String mark = "0";
	private Integer moneyUseId;
	private String moneyUseName;
	private String open = "0";
	private Integer moneyMinId;
	private Integer moneyMinName;
	private String open1 = "0";
	private Integer moneyMaxId;
	private Integer moneyMaxName;
	private String open2 = "0";
	private Integer validTimeId;
	private String validTimeName;
	private String open3 = "0";
	private Integer findMoneyId;
	private String findMoneyName;
	private String findMoneyNumber;
	private String open4 = "0";
	private Integer bankPid;
	private String bankPname;
	private String bankPnumber;
	private String open5 = "0";
	private Integer integralPid;
	private String integralPname;
	private String integralPnumber;
	private String integralPdescription;
	private String open6 = "0";
	private Integer sysId;
	private String parname;
	private String parvalue;
	private String pardesc;
	
	private String open8="0";
	private String open9="0";
	private Integer periodTimeId;
	private String periodTimeName;
	private Integer periodDayId;
	private String periodDayName;
	
	
	private int datatype;
	public int getDatatype() {
		return datatype;
	}

	public void setDatatype(int datatype) {
		this.datatype = datatype;
	}

	private String open7 = "0";

	public String searchAll() throws Exception {
		this.moneyuses = this.moneyuseService.findMoneyuses();
		this.moneymins = this.moneyminService.findMoneymins();
		this.moneymaxs = this.moneymaxService.findMoneymaxs();
		this.validtimes = this.validtimeService.findValidtimes();
		this.findmoneys = this.findmoneyService.findFindmoneys();
		this.bankparameters = this.bankparameterService.findBankparameters();
		this.integralparameters = this.integralparameterService
				.findIntegralparameters();
		this.systemconfs = this.systemconfService.findSystemconfs();
		this.perioddays=this.perioddayService.findPerioddays();
		this.periodtimes=this.periodtimeService.findPeriodtimes();
		return "success";
	}
	/**
	 * 添加借款期限（天）
	 * @return
	 * @throws Exception
	 */
	 
	public String  createDay()throws Exception{
		Periodday periodday = new Periodday();
		if(this.periodDayId!=null){
			periodday.setPeriodDayId(this.periodDayId);
			periodday.setPeriodDayName(this.periodDayName);
			this.perioddayService.updatePeriodday(periodday);
			this.periodDayId=null;
			this.periodDayName=null;
			
			InetAddress in = InetAddress.getLocalHost();
			Manager manager = (Manager) ActionContext.getContext().getSession()
					.get("manager");
			Oerationlog oerationlog = new Oerationlog();
			oerationlog.setManager(manager);
			oerationlog.setOerationCategory("updatePeriodday");
			oerationlog.setOerationChangeTime(new Timestamp(new Date()
					.getTime()));
			oerationlog.setOerationRemaks("编辑借款期限（天）");
			oerationlog.setOrationIp(in.getHostAddress().toString());
			this.oerationlogService.createOerationlog(oerationlog);
			
		}else{
			if(this.getPeriodDayName()!=null){
				periodday.setPeriodDayName(this.getPeriodDayName());
				this.perioddayService.addPeriodday(periodday);
				this.periodDayId=null;
				this.periodDayName=null;
				
				InetAddress in = InetAddress.getLocalHost();
				Manager manager = (Manager) ActionContext.getContext().getSession()
						.get("manager");
				Oerationlog oerationlog = new Oerationlog();
				oerationlog.setManager(manager);
				oerationlog.setOerationCategory("addPeriodday");
				oerationlog.setOerationChangeTime(new Timestamp(new Date()
						.getTime()));
				oerationlog.setOerationRemaks("添加借款期限（天）");
				oerationlog.setOrationIp(in.getHostAddress().toString());
				this.oerationlogService.createOerationlog(oerationlog);
			}
		}
		searchAll();
		return "success";
	}
	/**
	 * 编辑借款期限（天）
	 * @return
	 * @throws Exception
	 */
	public String editDay() throws Exception {
		Periodday periodday = this.perioddayService.findPerioddayByPerioddayId(this.periodDayId);
		this.periodDayId = periodday.getPeriodDayId();
		this.periodDayName = periodday.getPeriodDayName();
		searchAll();
		this.mark = "9";
		this.open8 = "1";
		return "success";
	}
	/**
	 * 删除借款期限（天）
	 * @return
	 * @throws Exception
	 */
	public String delDay() throws Exception {
		Periodday periodday = this.perioddayService.findPerioddayByPerioddayId(this.periodDayId);
		this.perioddayService.deletePeriodday(periodday);
		this.moneyUseId = null;

		InetAddress in = InetAddress.getLocalHost();
		Manager manager = (Manager) ActionContext.getContext().getSession()
				.get("manager");
		Oerationlog oerationlog = new Oerationlog();
		oerationlog.setManager(manager);
		oerationlog.setOerationCategory("deletePeriodday");
		oerationlog.setOerationChangeTime(new Timestamp(new Date().getTime()));
		oerationlog.setOerationRemaks("删除借款期限（天）");
		oerationlog.setOrationIp(in.getHostAddress().toString());
		this.oerationlogService.createOerationlog(oerationlog);
		searchAll();
		return "success";
	}
	
	/**
	 * 添加借款期限（月）
	 * @return
	 * @throws Exception
	 */
	 
	public String  createTime()throws Exception{
		Periodtime periodtime = new Periodtime();
		if(this.periodDayId!=null){
			periodtime.setPeriodTimeId(this.periodDayId);
			periodtime.setPeriodTimeName(this.periodDayName);
			this.periodtimeService.updatePeriodtime(periodtime);
			this.periodTimeId=null;
			this.periodTimeName=null;
			
			InetAddress in = InetAddress.getLocalHost();
			Manager manager = (Manager) ActionContext.getContext().getSession()
					.get("manager");
			Oerationlog oerationlog = new Oerationlog();
			oerationlog.setManager(manager);
			oerationlog.setOerationCategory("updatePeriodtime");
			oerationlog.setOerationChangeTime(new Timestamp(new Date()
					.getTime()));
			oerationlog.setOerationRemaks("编辑借款期限（天）");
			oerationlog.setOrationIp(in.getHostAddress().toString());
			this.oerationlogService.createOerationlog(oerationlog);
			
		}else{
			if(this.getPeriodTimeName()!=null){
				periodtime.setPeriodTimeName(this.getPeriodTimeName());
				this.periodtimeService.addPeriodtime(periodtime);
				this.periodTimeId=null;
				this.periodTimeName=null;
				
				InetAddress in = InetAddress.getLocalHost();
				Manager manager = (Manager) ActionContext.getContext().getSession()
						.get("manager");
				Oerationlog oerationlog = new Oerationlog();
				oerationlog.setManager(manager);
				oerationlog.setOerationCategory("addPeriodtime");
				oerationlog.setOerationChangeTime(new Timestamp(new Date()
						.getTime()));
				oerationlog.setOerationRemaks("添加借款期限（月）");
				oerationlog.setOrationIp(in.getHostAddress().toString());
				this.oerationlogService.createOerationlog(oerationlog);
			}
		}
		searchAll();
		return "success";
	}
	/**
	 * 编辑借款期限（月）
	 * @return
	 * @throws Exception
	 */
	public String editTime() throws Exception {
		Periodtime periodtime = this.periodtimeService.findPeriodtimeByPeriodtimeId(this.periodTimeId);
		this.periodTimeId = periodtime.getPeriodTimeId();
		this.periodTimeName = periodtime.getPeriodTimeName();
		searchAll();
		this.mark = "10";
		this.open9 = "1";
		return "success";
	}
	/**
	 * 删除借款期限（月）
	 * @return
	 * @throws Exception
	 */
	public String delTime() throws Exception {
		Periodtime periodtime = this.periodtimeService.findPeriodtimeByPeriodtimeId(this.periodTimeId);
		this.periodtimeService.deletePeriodtime(periodtime);
		this.moneyUseId = null;

		InetAddress in = InetAddress.getLocalHost();
		Manager manager = (Manager) ActionContext.getContext().getSession()
				.get("manager");
		Oerationlog oerationlog = new Oerationlog();
		oerationlog.setManager(manager);
		oerationlog.setOerationCategory("deletePeriodtime");
		oerationlog.setOerationChangeTime(new Timestamp(new Date().getTime()));
		oerationlog.setOerationRemaks("删除借款期限（月）");
		oerationlog.setOrationIp(in.getHostAddress().toString());
		this.oerationlogService.createOerationlog(oerationlog);
		searchAll();
		return "success";
	}
	
	
	/**
	 * 借款用途表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String createMu() throws Exception {
		if (this.moneyUseId != null) {
			Moneyuse cus = this.moneyuseService
					.findMoneyuseById(this.moneyUseId);
			cus.setMoneyUseName(this.moneyUseName);
			this.moneyuseService.updateMoneyuse(cus);
			this.moneyUseId = null;
			this.moneyUseName = null;

			InetAddress in = InetAddress.getLocalHost();
			Manager manager = (Manager) ActionContext.getContext().getSession()
					.get("manager");
			Oerationlog oerationlog = new Oerationlog();
			oerationlog.setManager(manager);
			oerationlog.setOerationCategory("saveMoneyUse");
			oerationlog.setOerationChangeTime(new Timestamp(new Date()
					.getTime()));
			oerationlog.setOerationRemaks("编辑借款用途");
			oerationlog.setOrationIp(in.getHostAddress().toString());
			this.oerationlogService.createOerationlog(oerationlog);
		} else {
			Moneyuse cus = new Moneyuse();
			cus.setMoneyUseName(this.moneyUseName);
			this.moneyuseService.addMoneyuse(cus);

			InetAddress in = InetAddress.getLocalHost();
			Manager manager = (Manager) ActionContext.getContext().getSession()
					.get("manager");
			Oerationlog oerationlog = new Oerationlog();
			oerationlog.setManager(manager);
			oerationlog.setOerationCategory("addMoneyUse");
			oerationlog.setOerationChangeTime(new Timestamp(new Date()
					.getTime()));
			oerationlog.setOerationRemaks("新增借款用途");
			oerationlog.setOrationIp(in.getHostAddress().toString());
			this.oerationlogService.createOerationlog(oerationlog);
		}
		searchAll();
		return "success";
	}

	public String delMu() throws Exception {
		Moneyuse cus = this.moneyuseService.findMoneyuseById(this.moneyUseId);
		this.moneyuseService.deleteMoneyuse(cus);
		this.moneyUseId = null;

		InetAddress in = InetAddress.getLocalHost();
		Manager manager = (Manager) ActionContext.getContext().getSession()
				.get("manager");
		Oerationlog oerationlog = new Oerationlog();
		oerationlog.setManager(manager);
		oerationlog.setOerationCategory("delMoneyUse");
		oerationlog.setOerationChangeTime(new Timestamp(new Date().getTime()));
		oerationlog.setOerationRemaks("删除借款用途");
		oerationlog.setOrationIp(in.getHostAddress().toString());
		this.oerationlogService.createOerationlog(oerationlog);
		searchAll();
		return "success";
	}

	public String editMu() throws Exception {
		Moneyuse cus = this.moneyuseService.findMoneyuseById(this.moneyUseId);
		this.moneyUseId = cus.getMoneyUseId();
		this.moneyUseName = cus.getMoneyUseName();
		searchAll();
		this.open = "1";
		return "success";
	}

	/**
	 * 最小金额
	 * 
	 * @return
	 * @throws Exception
	 */
	public String createMmin() throws Exception {
		if (this.moneyMinId != null) {
			Moneymin cus = this.moneyminService
					.findMoneyminById(this.moneyMinId);
			cus.setMoneyMinName(this.moneyMinName);
			this.moneyminService.updateMoneymin(cus);
			this.moneyMinId = null;
			this.moneyMinName = null;

			InetAddress in = InetAddress.getLocalHost();
			Manager manager = (Manager) ActionContext.getContext().getSession()
					.get("manager");
			Oerationlog oerationlog = new Oerationlog();
			oerationlog.setManager(manager);
			oerationlog.setOerationCategory("saveMoneyMin");
			oerationlog.setOerationChangeTime(new Timestamp(new Date()
					.getTime()));
			oerationlog.setOerationRemaks("编辑最小金额");
			oerationlog.setOrationIp(in.getHostAddress().toString());
			this.oerationlogService.createOerationlog(oerationlog);
		} else {
			Moneymin cus = new Moneymin();
			cus.setMoneyMinName(this.moneyMinName);
			this.moneyminService.addMoneymin(cus);

			InetAddress in = InetAddress.getLocalHost();
			Manager manager = (Manager) ActionContext.getContext().getSession()
					.get("manager");
			Oerationlog oerationlog = new Oerationlog();
			oerationlog.setManager(manager);
			oerationlog.setOerationCategory("addMoneyMin");
			oerationlog.setOerationChangeTime(new Timestamp(new Date()
					.getTime()));
			oerationlog.setOerationRemaks("新增最小金额");
			oerationlog.setOrationIp(in.getHostAddress().toString());
			this.oerationlogService.createOerationlog(oerationlog);
		}
		searchAll();
		this.mark = "2";
		return "success";
	}

	public String delMmin() throws Exception {
		Moneymin cus = this.moneyminService.findMoneyminById(this.moneyMinId);
		this.moneyminService.deleteMoneymin(cus);
		this.moneyMinId = null;
		searchAll();
		this.mark = "2";

		InetAddress in = InetAddress.getLocalHost();
		Manager manager = (Manager) ActionContext.getContext().getSession()
				.get("manager");
		Oerationlog oerationlog = new Oerationlog();
		oerationlog.setManager(manager);
		oerationlog.setOerationCategory("delMoneyMin");
		oerationlog.setOerationChangeTime(new Timestamp(new Date().getTime()));
		oerationlog.setOerationRemaks("删除最小金额");
		oerationlog.setOrationIp(in.getHostAddress().toString());
		this.oerationlogService.createOerationlog(oerationlog);
		return "success";
	}

	public String editMmin() throws Exception {
		Moneymin cus = this.moneyminService.findMoneyminById(this.moneyMinId);
		this.moneyMinId = cus.getMoneyMinId();
		this.moneyMinName = cus.getMoneyMinName();
		searchAll();
		this.mark = "2";
		this.open1 = "1";
		return "success";
	}

	/**
	 * 最大金额
	 * 
	 * @return
	 * @throws Exception
	 */
	public String createMmax() throws Exception {
		if (this.moneyMaxId != null) {
			Moneymax cus = this.moneymaxService
					.findMoneymaxById(this.moneyMaxId);
			cus.setMoneyMaxName(this.moneyMaxName);
			this.moneymaxService.updateMoneymax(cus);
			this.moneyMaxId = null;
			this.moneyMaxName = null;

			InetAddress in = InetAddress.getLocalHost();
			Manager manager = (Manager) ActionContext.getContext().getSession()
					.get("manager");
			Oerationlog oerationlog = new Oerationlog();
			oerationlog.setManager(manager);
			oerationlog.setOerationCategory("saveMoneyMax");
			oerationlog.setOerationChangeTime(new Timestamp(new Date()
					.getTime()));
			oerationlog.setOerationRemaks("编辑最大金额");
			oerationlog.setOrationIp(in.getHostAddress().toString());
			this.oerationlogService.createOerationlog(oerationlog);
		} else {
			Moneymax cus = new Moneymax();
			cus.setMoneyMaxName(this.moneyMaxName);
			this.moneymaxService.addMoneymax(cus);

			InetAddress in = InetAddress.getLocalHost();
			Manager manager = (Manager) ActionContext.getContext().getSession()
					.get("manager");
			Oerationlog oerationlog = new Oerationlog();
			oerationlog.setManager(manager);
			oerationlog.setOerationCategory("addMoneyMax");
			oerationlog.setOerationChangeTime(new Timestamp(new Date()
					.getTime()));
			oerationlog.setOerationRemaks("新增最大金额");
			oerationlog.setOrationIp(in.getHostAddress().toString());
			this.oerationlogService.createOerationlog(oerationlog);
		}
		searchAll();
		this.mark = "3";
		return "success";
	}

	public String delMmax() throws Exception {
		Moneymax cus = this.moneymaxService.findMoneymaxById(this.moneyMaxId);
		this.moneymaxService.deleteMoneymax(cus);
		this.moneyMaxId = null;
		searchAll();
		this.mark = "3";

		InetAddress in = InetAddress.getLocalHost();
		Manager manager = (Manager) ActionContext.getContext().getSession()
				.get("manager");
		Oerationlog oerationlog = new Oerationlog();
		oerationlog.setManager(manager);
		oerationlog.setOerationCategory("delMoneyMax");
		oerationlog.setOerationChangeTime(new Timestamp(new Date().getTime()));
		oerationlog.setOerationRemaks("删除最大金额");
		oerationlog.setOrationIp(in.getHostAddress().toString());
		this.oerationlogService.createOerationlog(oerationlog);
		return "success";
	}

	public String editMmax() throws Exception {
		Moneymax cus = this.moneymaxService.findMoneymaxById(this.moneyMaxId);
		this.moneyMaxId = cus.getMoneyMaxId();
		this.moneyMaxName = cus.getMoneyMaxName();
		searchAll();
		this.mark = "3";
		this.open2 = "1";
		return "success";
	}

	public String createVt() throws Exception {
		if (this.validTimeId != null) {
			Validtime cus = this.validtimeService
					.findValidtimeById(this.validTimeId);
			cus.setValidTimeName(this.validTimeName);
			this.validtimeService.updateValidtime(cus);
			this.validTimeId = null;
			this.validTimeName = null;

			InetAddress in = InetAddress.getLocalHost();
			Manager manager = (Manager) ActionContext.getContext().getSession()
					.get("manager");
			Oerationlog oerationlog = new Oerationlog();
			oerationlog.setManager(manager);
			oerationlog.setOerationCategory("saveValidTime");
			oerationlog.setOerationChangeTime(new Timestamp(new Date()
					.getTime()));
			oerationlog.setOerationRemaks("编辑募资时间");
			oerationlog.setOrationIp(in.getHostAddress().toString());
			this.oerationlogService.createOerationlog(oerationlog);
		} else {
			Validtime cus = new Validtime();
			cus.setValidTimeName(this.validTimeName);
			this.validtimeService.addValidtime(cus);

			InetAddress in = InetAddress.getLocalHost();
			Manager manager = (Manager) ActionContext.getContext().getSession()
					.get("manager");
			Oerationlog oerationlog = new Oerationlog();
			oerationlog.setManager(manager);
			oerationlog.setOerationCategory("addValidTime");
			oerationlog.setOerationChangeTime(new Timestamp(new Date()
					.getTime()));
			oerationlog.setOerationRemaks("新增募资时间");
			oerationlog.setOrationIp(in.getHostAddress().toString());
			this.oerationlogService.createOerationlog(oerationlog);
		}
		searchAll();
		this.mark = "4";
		return "success";
	}

	public String delVt() throws Exception {
		Validtime cus = this.validtimeService
				.findValidtimeById(this.validTimeId);
		this.validtimeService.deleteValidtime(cus);
		this.validTimeId = null;
		searchAll();
		this.mark = "4";

		InetAddress in = InetAddress.getLocalHost();
		Manager manager = (Manager) ActionContext.getContext().getSession()
				.get("manager");
		Oerationlog oerationlog = new Oerationlog();
		oerationlog.setManager(manager);
		oerationlog.setOerationCategory("delValidTime");
		oerationlog.setOerationChangeTime(new Timestamp(new Date().getTime()));
		oerationlog.setOerationRemaks("删除募资时间");
		oerationlog.setOrationIp(in.getHostAddress().toString());
		this.oerationlogService.createOerationlog(oerationlog);
		return "success";
	}

	public String editVt() throws Exception {
		Validtime cus = this.validtimeService
				.findValidtimeById(this.validTimeId);
		this.validTimeId = cus.getValidTimeId();
		this.validTimeName = cus.getValidTimeName();
		searchAll();
		this.open3 = "1";
		this.mark = "4";
		return "success";
	}

	/**
	 * 募资时间
	 * 
	 * @return
	 * @throws Exception
	 */
	public String createFm() throws Exception {
		if (this.findMoneyId != null) {
			Findmoney cus = this.findmoneyService
					.findFindmoneyByfindMoneyId(this.findMoneyId.intValue());
			cus.setFindMoneyName(this.findMoneyName);
			cus.setFindMoneyNumber(this.findMoneyNumber);
			this.findmoneyService.updateFindmoney(cus);
			this.findMoneyId = null;
			this.findMoneyName = null;
			this.findMoneyNumber = null;

			InetAddress in = InetAddress.getLocalHost();
			Manager manager = (Manager) ActionContext.getContext().getSession()
					.get("manager");
			Oerationlog oerationlog = new Oerationlog();
			oerationlog.setManager(manager);
			oerationlog.setOerationCategory("saveFindmoney");
			oerationlog.setOerationChangeTime(new Timestamp(new Date()
					.getTime()));
			oerationlog.setOerationRemaks("编辑查询金额");
			oerationlog.setOrationIp(in.getHostAddress().toString());
			this.oerationlogService.createOerationlog(oerationlog);
		} else {
			Findmoney cus = new Findmoney();
			cus.setFindMoneyName(this.findMoneyName);
			cus.setFindMoneyNumber(this.findMoneyNumber);
			this.findmoneyService.createFindmoney(cus);

			InetAddress in = InetAddress.getLocalHost();
			Manager manager = (Manager) ActionContext.getContext().getSession()
					.get("manager");
			Oerationlog oerationlog = new Oerationlog();
			oerationlog.setManager(manager);
			oerationlog.setOerationCategory("addFindmoney");
			oerationlog.setOerationChangeTime(new Timestamp(new Date()
					.getTime()));
			oerationlog.setOerationRemaks("新增查询金额");
			oerationlog.setOrationIp(in.getHostAddress().toString());
			this.oerationlogService.createOerationlog(oerationlog);
		}
		searchAll();
		this.mark = "5";
		return "success";
	}

	public String delFm() throws Exception {
		Findmoney cus = this.findmoneyService
				.findFindmoneyByfindMoneyId(this.findMoneyId.intValue());
		this.findmoneyService.deleteFindmoney(cus);
		this.findMoneyId = null;
		searchAll();
		this.mark = "5";

		InetAddress in = InetAddress.getLocalHost();
		Manager manager = (Manager) ActionContext.getContext().getSession()
				.get("manager");
		Oerationlog oerationlog = new Oerationlog();
		oerationlog.setManager(manager);
		oerationlog.setOerationCategory("delFindmoney");
		oerationlog.setOerationChangeTime(new Timestamp(new Date().getTime()));
		oerationlog.setOerationRemaks("删除查询金额");
		oerationlog.setOrationIp(in.getHostAddress().toString());
		this.oerationlogService.createOerationlog(oerationlog);
		return "success";
	}

	public String editFm() throws Exception {
		Findmoney cus = this.findmoneyService
				.findFindmoneyByfindMoneyId(this.findMoneyId.intValue());
		this.findMoneyId = cus.getFindMoneyId();
		this.findMoneyName = cus.getFindMoneyName();
		this.findMoneyNumber = cus.getFindMoneyNumber();
		searchAll();
		this.open4 = "1";
		this.mark = "5";
		return "success";
	}

	/**
	 * 提现银行
	 * 
	 * @return
	 * @throws Exception
	 */
	public String createBp() throws Exception {
		if (this.bankPid != null) {
			Bankparameter cus = this.bankparameterService
					.findBankparameterBybankPid(this.bankPid.intValue());
			cus.setBankPname(this.bankPname);
			cus.setBankPnumber(this.bankPnumber);
			this.bankparameterService.updateBankparameter(cus);
			this.bankPid = null;
			this.bankPname = null;
			this.bankPnumber = null;

			InetAddress in = InetAddress.getLocalHost();
			Manager manager = (Manager) ActionContext.getContext().getSession()
					.get("manager");
			Oerationlog oerationlog = new Oerationlog();
			oerationlog.setManager(manager);
			oerationlog.setOerationCategory("saveBankparameter");
			oerationlog.setOerationChangeTime(new Timestamp(new Date()
					.getTime()));
			oerationlog.setOerationRemaks("编辑提现银行");
			oerationlog.setOrationIp(in.getHostAddress().toString());
			this.oerationlogService.createOerationlog(oerationlog);
		} else {
			Bankparameter cus = new Bankparameter();
			cus.setBankPname(this.bankPname);
			cus.setBankPnumber(this.bankPnumber);
			this.bankparameterService.updateBankparameter(cus);

			InetAddress in = InetAddress.getLocalHost();
			Manager manager = (Manager) ActionContext.getContext().getSession()
					.get("manager");
			Oerationlog oerationlog = new Oerationlog();
			oerationlog.setManager(manager);
			oerationlog.setOerationCategory("addBankparameter");
			oerationlog.setOerationChangeTime(new Timestamp(new Date()
					.getTime()));
			oerationlog.setOerationRemaks("新增提现银行");
			oerationlog.setOrationIp(in.getHostAddress().toString());
			this.oerationlogService.createOerationlog(oerationlog);
		}
		searchAll();
		this.mark = "6";
		return "success";
	}

	public String delBp() throws Exception {
		Bankparameter cus = this.bankparameterService
				.findBankparameterBybankPid(this.bankPid.intValue());
		this.bankparameterService.deleteBankparameter(cus);
		this.bankPid = null;
		searchAll();
		this.mark = "6";

		InetAddress in = InetAddress.getLocalHost();
		Manager manager = (Manager) ActionContext.getContext().getSession()
				.get("manager");
		Oerationlog oerationlog = new Oerationlog();
		oerationlog.setManager(manager);
		oerationlog.setOerationCategory("delBankparameter");
		oerationlog.setOerationChangeTime(new Timestamp(new Date().getTime()));
		oerationlog.setOerationRemaks("删除提现银行");
		oerationlog.setOrationIp(in.getHostAddress().toString());
		this.oerationlogService.createOerationlog(oerationlog);
		return "success";
	}

	public String editBp() throws Exception {
		Bankparameter cus = this.bankparameterService
				.findBankparameterBybankPid(this.bankPid.intValue());
		this.bankPid = cus.getBankPid();
		this.bankPname = cus.getBankPname();
		this.bankPnumber = cus.getBankPnumber();
		searchAll();
		this.open5 = "1";
		this.mark = "6";
		return "success";
	}

	/**
	 * 积分参数
	 * 
	 * @return
	 * @throws Exception
	 */
	public String createIp() throws Exception {
		if (this.integralPid != null) {
			Integralparameter cus = this.integralparameterService
					.findIntegralparameterByintegralPid(this.integralPid
							.intValue());
			cus.setIntegralPname(this.integralPname);
			cus.setIntegralPnumber(this.integralPnumber);
			cus.setIntegralPdescription(this.integralPdescription);
			this.integralparameterService.updateIntegralparameter(cus);
			this.integralPid = null;
			this.integralPname = null;
			this.integralPnumber = null;
			this.integralPdescription = null;

			InetAddress in = InetAddress.getLocalHost();
			Manager manager = (Manager) ActionContext.getContext().getSession()
					.get("manager");
			Oerationlog oerationlog = new Oerationlog();
			oerationlog.setManager(manager);
			oerationlog.setOerationCategory("saveIntegralparameter");
			oerationlog.setOerationChangeTime(new Timestamp(new Date()
					.getTime()));
			oerationlog.setOerationRemaks("编辑积分参数");
			oerationlog.setOrationIp(in.getHostAddress().toString());
			this.oerationlogService.createOerationlog(oerationlog);
		} else {
			Integralparameter cus = new Integralparameter();
			cus.setIntegralPname(this.integralPname);
			cus.setIntegralPnumber(this.integralPnumber);
			cus.setIntegralPdescription(this.integralPdescription);
			this.integralparameterService.createIntegralparameter(cus);

			InetAddress in = InetAddress.getLocalHost();
			Manager manager = (Manager) ActionContext.getContext().getSession()
					.get("manager");
			Oerationlog oerationlog = new Oerationlog();
			oerationlog.setManager(manager);
			oerationlog.setOerationCategory("addIntegralparameter");
			oerationlog.setOerationChangeTime(new Timestamp(new Date()
					.getTime()));
			oerationlog.setOerationRemaks("新增积分参数");
			oerationlog.setOrationIp(in.getHostAddress().toString());
			this.oerationlogService.createOerationlog(oerationlog);
		}
		searchAll();
		this.mark = "7";
		return "success";
	}

	public String delIp() throws Exception {
		Integralparameter cus = this.integralparameterService
				.findIntegralparameterByintegralPid(this.integralPid.intValue());
		this.integralparameterService.deleteIntegralparameter(cus);
		this.integralPid = null;
		searchAll();
		this.mark = "7";

		InetAddress in = InetAddress.getLocalHost();
		Manager manager = (Manager) ActionContext.getContext().getSession()
				.get("manager");
		Oerationlog oerationlog = new Oerationlog();
		oerationlog.setManager(manager);
		oerationlog.setOerationCategory("delIntegralparameter");
		oerationlog.setOerationChangeTime(new Timestamp(new Date().getTime()));
		oerationlog.setOerationRemaks("删除积分参数");
		oerationlog.setOrationIp(in.getHostAddress().toString());
		this.oerationlogService.createOerationlog(oerationlog);
		return "success";
	}

	public String editIp() throws Exception {
		Integralparameter cus = this.integralparameterService
				.findIntegralparameterByintegralPid(this.integralPid.intValue());
		this.integralPid = cus.getIntegralPid();
		this.integralPname = cus.getIntegralPname();
		this.integralPnumber = cus.getIntegralPnumber();
		this.integralPdescription = cus.getIntegralPdescription();
		searchAll();
		this.open6 = "1";
		this.mark = "7";
		return "success";
	}

	/**
	 * 费用参数
	 * 
	 * @return
	 * @throws Exception
	 */
	public String createSysconf() throws Exception {
		if (this.sysId != null) {
			Systemconf sysconf = this.systemconfService.findSystemconfBySysId(this.sysId.intValue());
			sysconf.setParname(this.parname);
			sysconf.setPardesc(this.pardesc);
			sysconf.setParvalue(this.parvalue);
			this.systemconfService.updateSystemconf(sysconf);
			this.sysId = null;
			this.parname = null;
			this.pardesc = null;
			this.parvalue = null;

			InetAddress in = InetAddress.getLocalHost();
			Manager manager = (Manager) ActionContext.getContext().getSession()
					.get("manager");
			Oerationlog oerationlog = new Oerationlog();
			oerationlog.setManager(manager);
			oerationlog.setOerationCategory("saveSystemconf");
			oerationlog.setOerationChangeTime(new Timestamp(new Date()
					.getTime()));
			oerationlog.setOerationRemaks("编辑费用参数");
			oerationlog.setOrationIp(in.getHostAddress().toString());
			this.oerationlogService.createOerationlog(oerationlog);
		} else {
			Systemconf sysconf = new Systemconf();
			sysconf.setParname(this.parname);
			sysconf.setPardesc(this.pardesc);
			sysconf.setParvalue(this.parvalue);
			this.systemconfService.createSystemconf(sysconf);

			InetAddress in = InetAddress.getLocalHost();
			Manager manager = (Manager) ActionContext.getContext().getSession()
					.get("manager");
			Oerationlog oerationlog = new Oerationlog();
			oerationlog.setManager(manager);
			oerationlog.setOerationCategory("addSystemconf");
			oerationlog.setOerationChangeTime(new Timestamp(new Date()
					.getTime()));
			oerationlog.setOerationRemaks("新增费用参数");
			oerationlog.setOrationIp(in.getHostAddress().toString());
			this.oerationlogService.createOerationlog(oerationlog);
		}
		searchAll();
		this.mark = "8";
		return "success";
	}
	
	public String editSysconf() throws Exception{
		Systemconf sysconf = this.systemconfService.findSystemconfBySysId(this.sysId.intValue());
				
		this.sysId = sysconf.getSysId();
		this.parname = sysconf.getParname();
		this.parvalue = sysconf.getParvalue();
		this.pardesc = sysconf.getPardesc();
		this.datatype = sysconf.getDatatype();
		searchAll();
		this.open7 = "1";
		this.mark = "8";
		return "success";
	}

	public List<Moneyuse> getMoneyuses() {
		return this.moneyuses;
	}

	public void setMoneyuser(List<Moneyuse> moneyuses) {
		this.moneyuses = moneyuses;
	}

	public List<Moneymin> getMoneymins() {
		return this.moneymins;
	}

	public void setMoneymins(List<Moneymin> moneymins) {
		this.moneymins = moneymins;
	}

	public List<Moneymax> getMoneymaxs() {
		return this.moneymaxs;
	}

	public void setMoneymaxs(List<Moneymax> moneymaxs) {
		this.moneymaxs = moneymaxs;
	}

	public List<Validtime> getValidtimes() {
		return this.validtimes;
	}

	public void setValidtimes(List<Validtime> validtimes) {
		this.validtimes = validtimes;
	}

	public List<Findmoney> getFindmoneys() {
		return this.findmoneys;
	}

	public void setFindmoneys(List<Findmoney> findmoneys) {
		this.findmoneys = findmoneys;
	}

	public List<Bankparameter> getBankparameters() {
		return this.bankparameters;
	}

	public void setBankparameters(List<Bankparameter> bankparameters) {
		this.bankparameters = bankparameters;
	}

	public List<Integralparameter> getIntegralparameters() {
		return this.integralparameters;
	}

	public void setIntegralparameters(List<Integralparameter> integralparameters) {
		this.integralparameters = integralparameters;
	}

	public Integer getMoneyUseId() {
		return this.moneyUseId;
	}

	public void setMoneyUseId(Integer moneyUseId) {
		this.moneyUseId = moneyUseId;
	}

	public String getMoneyUseName() {
		return this.moneyUseName;
	}

	public void setMoneyUseName(String moneyUseName) {
		this.moneyUseName = moneyUseName;
	}

	public String getOpen() {
		return this.open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public Integer getMoneyMinId() {
		return this.moneyMinId;
	}

	public void setMoneyMinId(Integer moneyMinId) {
		this.moneyMinId = moneyMinId;
	}

	public Integer getMoneyMinName() {
		return this.moneyMinName;
	}

	public void setMoneyMinName(Integer moneyMinName) {
		this.moneyMinName = moneyMinName;
	}

	public String getOpen1() {
		return this.open1;
	}

	public void setOpen1(String open1) {
		this.open1 = open1;
	}

	public Integer getMoneyMaxId() {
		return this.moneyMaxId;
	}

	public void setMoneyMaxId(Integer moneyMaxId) {
		this.moneyMaxId = moneyMaxId;
	}

	public Integer getMoneyMaxName() {
		return this.moneyMaxName;
	}

	public void setMoneyMaxName(Integer moneyMaxName) {
		this.moneyMaxName = moneyMaxName;
	}

	public String getOpen2() {
		return this.open2;
	}

	public void setOpen2(String open2) {
		this.open2 = open2;
	}

	public Integer getValidTimeId() {
		return this.validTimeId;
	}

	public void setValidTimeId(Integer validTimeId) {
		this.validTimeId = validTimeId;
	}

	public String getValidTimeName() {
		return this.validTimeName;
	}

	public void setValidTimeName(String validTimeName) {
		this.validTimeName = validTimeName;
	}

	public String getOpen3() {
		return this.open3;
	}

	public void setOpen3(String open3) {
		this.open3 = open3;
	}

	public Integer getFindMoneyId() {
		return this.findMoneyId;
	}

	public void setFindMoneyId(Integer findMoneyId) {
		this.findMoneyId = findMoneyId;
	}

	public String getFindMoneyName() {
		return this.findMoneyName;
	}

	public void setFindMoneyName(String findMoneyName) {
		this.findMoneyName = findMoneyName;
	}

	public String getFindMoneyNumber() {
		return this.findMoneyNumber;
	}

	public void setFindMoneyNumber(String findMoneyNumber) {
		this.findMoneyNumber = findMoneyNumber;
	}

	public String getOpen4() {
		return this.open4;
	}

	public void setOpen4(String open4) {
		this.open4 = open4;
	}

	public Integer getBankPid() {
		return this.bankPid;
	}

	public void setBankPid(Integer bankPid) {
		this.bankPid = bankPid;
	}

	public String getBankPname() {
		return this.bankPname;
	}

	public void setBankPname(String bankPname) {
		this.bankPname = bankPname;
	}

	public String getBankPnumber() {
		return this.bankPnumber;
	}

	public void setBankPnumber(String bankPnumber) {
		this.bankPnumber = bankPnumber;
	}

	public String getOpen5() {
		return this.open5;
	}

	public void setOpen5(String open5) {
		this.open5 = open5;
	}

	public Integer getIntegralPid() {
		return this.integralPid;
	}

	public void setIntegralPid(Integer integralPid) {
		this.integralPid = integralPid;
	}

	public String getIntegralPname() {
		return this.integralPname;
	}

	public void setIntegralPname(String integralPname) {
		this.integralPname = integralPname;
	}

	public String getIntegralPnumber() {
		return this.integralPnumber;
	}

	public void setIntegralPnumber(String integralPnumber) {
		this.integralPnumber = integralPnumber;
	}

	public String getIntegralPdescription() {
		return this.integralPdescription;
	}

	public void setIntegralPdescription(String integralPdescription) {
		this.integralPdescription = integralPdescription;
	}

	public String getOpen6() {
		return this.open6;
	}

	public void setOpen6(String open6) {
		this.open6 = open6;
	}

	public String getMark() {
		return this.mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getOpen7() {
		return this.open7;
	}

	public void setOpen7(String open7) {
		this.open7 = open7;
	}
	
	public Integer getSysId() {
		return sysId;
	}

	public void setSysId(Integer sysId) {
		this.sysId = sysId;
	}

	public String getParname() {
		return parname;
	}

	public void setParname(String parname) {
		this.parname = parname;
	}

	public String getParvalue() {
		return parvalue;
	}

	public void setParvalue(String parvalue) {
		this.parvalue = parvalue;
	}

	public String getPardesc() {
		return pardesc;
	}

	public void setPardesc(String pardesc) {
		this.pardesc = pardesc;
	}

	public Integer getPeriodTimeId() {
		return periodTimeId;
	}

	public void setPeriodTimeId(Integer periodTimeId) {
		this.periodTimeId = periodTimeId;
	}

	public String getPeriodTimeName() {
		return periodTimeName;
	}

	public void setPeriodTimeName(String periodTimeName) {
		this.periodTimeName = periodTimeName;
	}

	public Integer getPeriodDayId() {
		return periodDayId;
	}

	public void setPeriodDayId(Integer periodDayId) {
		this.periodDayId = periodDayId;
	}

	public String getPeriodDayName() {
		return periodDayName;
	}

	public void setPeriodDayName(String periodDayName) {
		this.periodDayName = periodDayName;
	}

	
	
}