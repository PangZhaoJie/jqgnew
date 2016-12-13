package com.jqg.struts;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.jqg.pojo.Basicinfor;
import com.jqg.pojo.Inbox;
import com.jqg.pojo.Lssuing;
import com.jqg.pojo.Moneycount;
import com.jqg.pojo.Moneyhistorydetail;
import com.jqg.pojo.Moneyuse;
import com.jqg.pojo.Periodday;
import com.jqg.pojo.Periodtime;
import com.jqg.pojo.Platcount;
import com.jqg.pojo.Record;
import com.jqg.pojo.Repaynote;
import com.jqg.pojo.Returnway;
import com.jqg.pojo.Systemconf;
import com.jqg.pojo.Tender;
import com.jqg.pojo.Uservip;
import com.jqg.pojo.Website;
import com.jqg.service.BasicinforService;
import com.jqg.service.InboxService;
import com.jqg.service.LssingphotoService;
import com.jqg.service.LssuingService;
import com.jqg.service.MoneycountService;
import com.jqg.service.MoneyhistorydetailService;
import com.jqg.service.MoneyuseService;
import com.jqg.service.PerioddayService;
import com.jqg.service.PeriodtimeService;
import com.jqg.service.PlatcountService;
import com.jqg.service.RecordService;
import com.jqg.service.RepaynoteService;
import com.jqg.service.ReturnwayService;
import com.jqg.service.SystemconfService;
import com.jqg.service.TenderService;
import com.jqg.service.UservipService;
import com.jqg.service.WebsiteService;
import com.jqg.service.impl.BasicinforServiceImpl;
import com.jqg.service.impl.InboxServiceImpl;
import com.jqg.service.impl.LssingphotoServiceImpl;
import com.jqg.service.impl.LssuingServiceImpl;
import com.jqg.service.impl.MoneycountServiceImpl;
import com.jqg.service.impl.MoneyhistorydetailServiceImpl;
import com.jqg.service.impl.MoneyuseServiceImpl;
import com.jqg.service.impl.PeriodDayServiceImpl;
import com.jqg.service.impl.PeriodtimeServiceImpl;
import com.jqg.service.impl.PlatcountServiceImpl;
import com.jqg.service.impl.RecordServiceImpl;
import com.jqg.service.impl.RepaynoteServiceImpl;
import com.jqg.service.impl.ReturnwayServiceImpl;
import com.jqg.service.impl.SystemconfServiceImpl;
import com.jqg.service.impl.TenderServiceImpl;
import com.jqg.service.impl.UservipServiceImpl;
import com.jqg.service.impl.WebsiteServiceImpl;
import com.jqg.util.LoanUtils;
import com.jqg.util.MD5Util;
import com.jqg.util.OverbueCom;
import com.opensymphony.xwork2.ActionContext;

/**
 * 投资
 * 
 * @author Administrator
 *
 */
public class TenderAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private int currentPage;
	private int pageSize = 10;
	private int totalPage;
	private List<Returnway> returnways;
	private List<Moneyuse> moneyuses;
	private List<Periodtime> periodtimes;
	private List<Periodday> perioddays;
	private String satate;
	private int tenderId;
	private int bugnum;// 投标份数
	private String lssuingId;
	private String money;
	private String userId;
	private String result;
	private Tender tender;
	private Lssuing lssuing;
	private String paypwd;
	private int lussingType;
	private List<Tender> tenders;
	private List<Tender> tenderspage;
	private List<Record> records;
	LssuingService lssuingService = new LssuingServiceImpl();
	TenderService tenderService = new TenderServiceImpl();
	ReturnwayService returnwayService = new ReturnwayServiceImpl();
	MoneyuseService moneyuseService = new MoneyuseServiceImpl();
	PeriodtimeService periodtimeService = new PeriodtimeServiceImpl();
	PerioddayService perioddayService = new PeriodDayServiceImpl();
	MoneycountService moneycountService = new MoneycountServiceImpl();
	LssingphotoService lssingphotoService = new LssingphotoServiceImpl();
	RecordService recordService = new RecordServiceImpl();
	BasicinforService basicinforService = new BasicinforServiceImpl();
	MoneyhistorydetailService moneyhistorydetailService = new MoneyhistorydetailServiceImpl();
	UservipService uservipService = new UservipServiceImpl();
	private WebsiteService websiteService = new WebsiteServiceImpl();
	private RepaynoteService repaynoteService = new RepaynoteServiceImpl();
	private SystemconfService systemconfService = new SystemconfServiceImpl();
	Uservip uservip = (Uservip) ActionContext.getContext().getSession()
			.get("uservip");
	List lssList = new ArrayList();
	private String postform;

	/**
	 * 还款详情
	 * 
	 * @throws Exception
	 */
	public void recordlist() throws Exception {
		List records1 = this.recordService.findRecordsBguserIdAntenderIdSize(
				this.uservip.getUserId().intValue(), this.tenderId);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowdate = df.format(new Date());
		Timestamp nowtime = new Timestamp(sdf.parse(nowdate + " 00:00:00")
				.getTime());
		int totalRecord = records1.size();
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
//vip会员逾期罚息
		SystemconfService systemconfService = new SystemconfServiceImpl();
		Uservip uservip = this.uservipService.findUservipByUserid(this.uservip.getUserId().intValue());
		Systemconf latefee1 = null;
		Systemconf latefee2 = null;
		Systemconf latefee3 = null;
		Systemconf latefee4 = null;
		if("1".equals(uservip.getIsVIP())){
			latefee1 = systemconfService.findSystemconfByParname("con_borrow_late_fee_3_2");
			latefee2 = systemconfService.findSystemconfByParname("con_borrow_late_fee_30_2");
			latefee3 = systemconfService.findSystemconfByParname("con_borrow_late_fee_90_2");
			latefee4 = systemconfService.findSystemconfByParname("con_borrow_late_fee_2");
		}else{
			latefee1 = systemconfService.findSystemconfByParname("con_borrow_late_fee_3");
			latefee2 = systemconfService.findSystemconfByParname("con_borrow_late_fee_30");
			latefee3 = systemconfService.findSystemconfByParname("con_borrow_late_fee_90");
			latefee4 = systemconfService.findSystemconfByParname("con_borrow_late_fee");
		}
		Double overfee1 = Double.valueOf(latefee1.getParvalue());
		Double overfee2 = Double.valueOf(latefee2.getParvalue());
		Double overfee3 = Double.valueOf(latefee3.getParvalue());
		Double overfee4 = Double.valueOf(latefee4.getParvalue());
		List records = this.recordService.findRecordsBguserIdAntenderIdpage(
				this.uservip.getUserId().intValue(), this.tenderId,
				(this.currentPage - 1) * this.pageSize, this.pageSize);
		StringBuffer str3 = new StringBuffer();
		str3.append("{\"totalPage\":\"" + this.totalPage + "\",");
		str3.append("\"currentPage\":\"" + this.currentPage + "\",");
		str3.append("\"jsonRoot\":[");
		for (int i = 0; i < records.size(); i++) {
			Record record = (Record) records.get(i);
			str3.append("{\"time\":\""
					+ record.getRecordDate().toString().substring(0, 10)
					+ "\",");
			str3.append("\"RecordMoney\":\"" + record.getRecordMoney() + "\",");
			str3.append("\"rate\":\"" + record.getRecordRate() + "\",");
			str3.append("\"RecordInterest\":\"" + record.getRecordInterest()
					+ "\",");
			boolean key = true;
			if (record.getRecordState().intValue() == 0) {
				str3.append("\"state\":\"待还款\",");
				if (record.getRecordDate().before(nowtime)) {
					key = false;
					record = OverbueCom.comOverInterest(record, overfee1,
							overfee2, overfee3, overfee4);
					str3.append("\"overdays\":\"" + record.getOverdays()
							+ "\",");
					str3.append("\"overInterest\":\""
							+ record.getOverInterest() + "\",");
				}
			}
			if (record.getRecordState().intValue() == 1) {
				str3.append("\"state\":\"已还款\",");
			}
			if (record.getRecordState().intValue() == 2) {
				key = false;
				str3.append("\"state\":\"平台垫付\",");
				str3.append("\"overdays\":\"" + record.getOverdays() + "\",");
				str3.append("\"overInterest\":\"" + record.getOverInterest()
						+ "\",");
			}
			if (key) {
				str3.append("\"overdays\":\"" + "0" + "\",");
				str3.append("\"overInterest\":\"" + "0" + "\",");
			}
			str3.append("\"size1\":\""
					+ (i + 1 + (this.currentPage - 1) * this.pageSize) + "\",");
			str3.append("\"size\":\"" + records1.size() + "\"},");
		}

		str3.deleteCharAt(str3.lastIndexOf(","));
		str3.append("]}");

		HttpServletResponse response3 = ServletActionContext.getResponse();
		response3.setCharacterEncoding("utf-8");
		try {
			response3.getWriter().print(str3);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 逾期
	 * 
	 * @throws Exception
	 */
	public void tenderlist1() throws Exception {
		SystemconfService systemconfService = new SystemconfServiceImpl();
		Systemconf sysconf = systemconfService
				.findSystemconfByParname("overdue_time");
		DecimalFormat numdf = new DecimalFormat("##.00");
		String parvalue = sysconf.getParvalue();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowdate = df.format(new Date());
		String overtime = nowdate + " " + parvalue;
		if (sdf.parse(overtime).getTime() >= new Date().getTime()) {
			overtime = nowdate + " 00:00:00";
		}
		String sql = "select distinct t.* from Record r,tender t , uservip u where u.userId=t.userId and  r.tenderId=t.tenderId";
		sql += " and r.recordDate<'" + overtime
				+ "' and recordState!=1 and t.userId="
				+ this.uservip.getUserId().intValue();

		sql = sql + " order by r.recordId desc ";
		List tenders3 = this.tenderService.findTenderBySql(sql);
		int totalRecord = tenders3.size();
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

		sql = sql + "limit " + (this.currentPage - 1) * this.pageSize + ","
				+ this.pageSize;
		System.out.println(sql);
		List<Tender> tendersPage3 = this.tenderService.findTenderBySql(sql);

		// List<Tender> tendersPage3 =
		// this.tenderService.findTendersByuserIdCategoryPage(this.uservip.getUserId().intValue(),
		// -1, (this.currentPage - 1) * this.pageSize, this.pageSize);
		// List tenders3 =
		// this.tenderService.findTendersByuserIdCategory(this.uservip.getUserId().intValue(),
		// -1);

		HttpServletRequest request = ServletActionContext.getRequest();
		String path = request.getContextPath();
		String basePath = "";
		if (request.getServerPort() == 80) {
			basePath = request.getScheme() + "://" + request.getServerName()
					+ path + "/";
		} else {
			basePath = request.getScheme() + "://" + request.getServerName()
					+ ":" + request.getServerPort() + path + "/";
		}

		StringBuffer str3 = new StringBuffer();
		str3.append("{\"totalPage\":\"" + this.totalPage + "\",");
		str3.append("\"currentPage\":\"" + this.currentPage + "\",");
		str3.append("\"jsonRoot\":[");
		for (Tender tender : tendersPage3) {
			String userName = "理财产品";
			if(tender.getLssuing().getUservip()!=null){
				userName =  tender.getLssuing().getUservip().getUserName();
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
			}
			String perTime = "";
			String lilv = "";
			double lixi;
			if (tender.getLssuing().getPeriodday() != null) {
				perTime = tender.getLssuing().getPeriodday().getPeriodDayName();
				lixi = Double.valueOf(numdf.format(tender.getMoney().intValue()
						* tender.getLssuing().getRate().doubleValue()
						* tender.getLssuing().getPeriodday().getPeriodDayId()
								.intValue() / 100.0D));
				lilv = numdf.format(tender.getLssuing().getRate() * 360);
			} else {
				perTime = tender.getLssuing().getPeriodtime()
						.getPeriodTimeName();
				lixi = Double.valueOf(numdf.format(tender.getMoney().intValue()
						* tender.getLssuing().getRate().doubleValue()
						* tender.getLssuing().getPeriodtime().getPeriodTimeId()
								.intValue() / 12.0D / 100.0D));
				lilv = numdf.format(tender.getLssuing().getRate());
			}

			str3.append("{\"lssuingNum\":\""
					+ tender.getLssuing().getLssuingNum() + "\",");
			str3.append("\"lssuingId\":\"" + tender.getLssuing().getLssuingId() + "\",");
			str3.append("\"userName\":\"" + userName + "\",");
			str3.append("\"rate\":\"" + lilv + "\",");
			str3.append("\"money\":\"" + tender.getMoney() + "\",");
			str3.append("\"remark\":\"" + "<a href='" + path
					+ "/tender/investContract?tenderId=" + tender.getTenderId()
					+ "' target='_blank'>合同</a>" + "\",");
			str3.append("\"lixi\":\"" + lixi + "\"},");
		}
		str3.deleteCharAt(str3.lastIndexOf(","));
		str3.append("]}");

		HttpServletResponse response3 = ServletActionContext.getResponse();
		response3.setCharacterEncoding("utf-8");
		try {
			response3.getWriter().print(str3);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 已完成
	 * 
	 * @throws Exception
	 */
	public void tenderlist2() throws Exception {
		List<Tender> tendersPage2 = this.tenderService.finTendersBystatePage(
				this.uservip.getUserId().intValue(), 2, (this.currentPage - 1)
						* this.pageSize, this.pageSize);
		List tenders2 = this.tenderService.finTendersBystate(this.uservip
				.getUserId().intValue(), 2);
		int totalRecord = tenders2.size();
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
		HttpServletRequest request = ServletActionContext.getRequest();
		String path = request.getContextPath();
		String basePath = "";
		if (request.getServerPort() == 80) {
			basePath = request.getScheme() + "://" + request.getServerName()
					+ path + "/";
		} else {
			basePath = request.getScheme() + "://" + request.getServerName()
					+ ":" + request.getServerPort() + path + "/";
		}

		StringBuffer str2 = new StringBuffer();
		str2.append("{\"totalPage\":\"" + this.totalPage + "\",");
		str2.append("\"currentPage\":\"" + this.currentPage + "\",");
		str2.append("\"jsonRoot\":[");
		DecimalFormat numdf = new DecimalFormat("##.00");
		for (Tender tender : tendersPage2) {
			String userName = "理财产品";
			if(tender.getLssuing().getUservip()!=null){
				userName =  tender.getLssuing().getUservip().getUserName();
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
			}
			String perTime = "";
			String lilv = "";
			double lixi;
			if (tender.getLssuing().getPeriodday() != null) {
				perTime = tender.getLssuing().getPeriodday().getPeriodDayName();
				lixi = tender.getMoney().intValue()
						* tender.getLssuing().getRate().doubleValue()
						* tender.getLssuing().getPeriodday().getPeriodDayId()
								.intValue() / 100.0D;
				lilv = numdf.format(tender.getLssuing().getRate() * 360);
			} else {
				perTime = tender.getLssuing().getPeriodtime()
						.getPeriodTimeName();
				lixi = tender.getMoney().intValue()
						* tender.getLssuing().getRate().doubleValue()
						* tender.getLssuing().getPeriodtime().getPeriodTimeId()
								.intValue() / 12.0D / 100.0D;
				lilv = numdf.format(tender.getLssuing().getRate());
			}

			str2.append("{\"lssuingNum\":\""
					+ tender.getLssuing().getLssuingNum() + "\",");
			str2.append("\"lssuingId\":\"" + tender.getLssuing().getLssuingId() + "\",");
			str2.append("\"userName\":\"" + userName + "\",");
			str2.append("\"rate\":\"" + lilv + "\",");
			str2.append("\"money\":\"" + tender.getMoney() + "\",");
			str2.append("\"remark\":\"" + "<a href='" + path
					+ "/tender/investContract?tenderId=" + tender.getTenderId()
					+ "' target='_blank'>合同</a>" + "\",");
			str2.append("\"lixi\":\"" + lixi + "\"},");
		}
		str2.deleteCharAt(str2.lastIndexOf(","));
		str2.append("]}");

		HttpServletResponse response2 = ServletActionContext.getResponse();
		response2.setCharacterEncoding("utf-8");
		try {
			response2.getWriter().print(str2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 投资合同
	 * 
	 * @throws Exception
	 */
	public String investContract() throws Exception {
		if (this.tenderId != 0) {
			this.tender = this.tenderService.findTenderById(this.tenderId);
			this.records = this.recordService.findRecordsBguserIdAntenderId(
					this.tender.getUservip().getUserId().intValue(),
					this.tenderId);
			this.lssuing = this.tender.getLssuing();
			int perTime = 0;
			double lixi = 0;
			String lilv = "";
			DecimalFormat df = new DecimalFormat("##.00");
			if (tender.getLssuing().getPeriodday() != null) {
				perTime = tender.getLssuing().getPeriodday().getPeriodDayId()
						.intValue();
				lixi = Double.valueOf(df.format(tender.getMoney().intValue()
						* tender.getLssuing().getRate().doubleValue() * perTime
						/ 100.0D));
				lilv = df.format(tender.getLssuing().getRate() * 360);
			} else {
				perTime = tender.getLssuing().getPeriodtime().getPeriodTimeId()
						.intValue();
				lixi = Double.valueOf(df.format(tender.getMoney().intValue()
						* tender.getLssuing().getRate().doubleValue() * perTime
						/ 12.0D / 100.0D));
				lilv = df.format(tender.getLssuing().getRate() * 360);
			}
			double total = tender.getMoney() + lixi;
			ActionContext.getContext().getSession()
					.put("totalMoney", Double.valueOf(total));
			ActionContext.getContext().getSession()
					.put("recordsSize", this.records.size());

		}
		return "success";
	}

	/**
	 * 正在还款
	 * 
	 * @throws Exception
	 */
	public void tenderlist3() throws Exception {
		List<Tender> tendersPage3 = this.tenderService.finTendersBystatePage(
				this.uservip.getUserId().intValue(), 1, (this.currentPage - 1)
						* this.pageSize, this.pageSize);
		List tenders3 = this.tenderService.finTendersBystate(this.uservip
				.getUserId().intValue(), 1);
		int totalRecord = tenders3.size();
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
		HttpServletRequest request = ServletActionContext.getRequest();
		String path = request.getContextPath();
		StringBuffer str1 = new StringBuffer();
		str1.append("{\"totalPage\":\"" + this.totalPage + "\",");
		str1.append("\"currentPage\":\"" + this.currentPage + "\",");
		str1.append("\"jsonRoot\":[");
		DecimalFormat df = new DecimalFormat("##.00");
		for (Tender tender : tendersPage3) {
			String userName = "理财产品";
			if(tender.getLssuing().getUservip()!=null){
				userName =  tender.getLssuing().getUservip().getUserName();
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
			}

			String lilv = "";

			if (tender.getLssuing().getPeriodday() != null) {
				lilv = df.format(tender.getLssuing().getRate() * 360);
			} else {
				lilv = df.format(tender.getLssuing().getRate());
			}

			List<Record> records = this.recordService
					.findRecordsBguserIdAntenderId(this.uservip.getUserId()
							.intValue(), tender.getTenderId().intValue());
			List records1 = this.recordService
					.findRecordsBguserIdAntenderIdSize(this.uservip.getUserId()
							.intValue(), tender.getTenderId().intValue());
			List<Record> record1 = this.recordService
					.findRecordsBguserIdAntenderIdlimit1(this.uservip
							.getUserId().intValue(), tender.getTenderId()
							.intValue());
			double recordInterest = 0.0D;
			for (Record record : records) {
				if(record.getRecordInterest()==null){
					record.setRecordInterest(record.getRecordMoney()+record.getRecordRate());
					recordService.updateRecord(record);
				}
				recordInterest += record.getRecordInterest().doubleValue();
			}

			str1.append("{\"lssuingNum\":\""
					+ tender.getLssuing().getLssuingNum() + "\",");
			str1.append("\"lssuingId\":\"" + tender.getLssuing().getLssuingId() + "\",");
			str1.append("\"userName\":\"" + userName + "\",");
			str1.append("\"rate\":\"" + lilv + "\",");
			str1.append("\"money\":\"" + tender.getMoney() + "\",");
			str1.append("\"recordInterest\":\"" + recordInterest + "\",");
			str1.append("\"records\":\"" + records.size() + "\",");
			str1.append("\"records1\":\"" + records1.size() + "\",");
			str1.append("\"href\":\"" + tender.getTenderId() + "\",");
			if (record1 != null && record1.size() > 0) {
				for (Record record : record1) {
					str1.append("\"time\":\""
							+ record.getRecordDate().toString()
									.substring(0, 10) + "\",");
				}
			} else {
				str1.append("\"time\":\"" + "\",");
			}
			str1.append("\"remark\":\"" + "<a href='" + path
					+ "/tender/investContract?tenderId=" + tender.getTenderId()
					+ "' target='_blank'>合同</a>" + "\"},");
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
	 * 正在投标
	 * 
	 * @throws Exception
	 */
	public void tenderlist4() throws Exception {
		List<Tender> tendersPage = this.tenderService
				.findTendersByuserIdCategoryPage(this.uservip.getUserId()
						.intValue(), 2, (this.currentPage - 1) * this.pageSize,
						this.pageSize);
		List tenders = this.tenderService.findTendersByuserIdCategory(
				this.uservip.getUserId().intValue(), 2);
		int totalRecord = tenders.size();
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
		StringBuffer str = new StringBuffer();
		str.append("{\"totalPage\":\"" + this.totalPage + "\",");
		str.append("\"currentPage\":\"" + this.currentPage + "\",");
		str.append("\"jsonRoot\":[");

		for (Tender tender : tendersPage) {
			String userName = "理财产品";
			if(tender.getLssuing().getUservip()!=null){
				userName =  tender.getLssuing().getUservip().getUserName();
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
			}
			
			String perTime = "";
			String lilv = "";
			DecimalFormat df1 = new DecimalFormat("0.00");
			double benxi;
			if (tender.getLssuing().getPeriodday() != null) {
				perTime = tender.getLssuing().getPeriodday().getPeriodDayName();
				benxi = tender.getMoney().intValue()
						* tender.getLssuing().getRate().doubleValue()
						* tender.getLssuing().getPeriodday().getPeriodDayId()
								.intValue() / 100.0D;
				lilv = df1.format(tender.getLssuing().getRate() * 360);
			} else {
				perTime = tender.getLssuing().getPeriodtime()
						.getPeriodTimeName();
				benxi = tender.getMoney().intValue()
						* tender.getLssuing().getRate().doubleValue()
						* tender.getLssuing().getPeriodtime().getPeriodTimeId()
								.intValue() / 12.0D / 100.0D;
				lilv = df1.format(tender.getLssuing().getRate());
			}

			String benxistr = df1.format(benxi);
			str.append("{\"lssuingNum\":\""
					+ tender.getLssuing().getLssuingNum() + "\",");
			str.append("\"lssuingId\":\"" + tender.getLssuing().getLssuingId() + "\",");
			str.append("\"userName\":\"" + userName + "\",");
			str.append("\"rate\":\"" + lilv + "\",");
			str.append("\"money\":\"" + tender.getMoney() + "\",");
			str.append("\"time\":\""
					+ tender.getTenderTime().toString().substring(0, 10)
					+ "\",");
			str.append("\"perTime\":\"" + perTime + "\",");
			str.append("\"borrowMoney\":\""
					+ tender.getLssuing().getBorrowMoney() + "\",");
			str.append("\"benxi\":\"" + benxistr + "\"},");
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
	 * 复审中的
	 * 
	 * @throws Exception
	 */
	public void tenderlist5() throws Exception {
		List<Tender> tendersPage = this.tenderService
				.findTendersByuserIdCategoryPage(this.uservip.getUserId()
						.intValue(), 1, (this.currentPage - 1) * this.pageSize,
						this.pageSize);
		List tenders = this.tenderService.findTendersByuserIdCategory(
				this.uservip.getUserId().intValue(), 1);
		int totalRecord = tenders.size();
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
		StringBuffer str = new StringBuffer();
		str.append("{\"totalPage\":\"" + this.totalPage + "\",");
		str.append("\"currentPage\":\"" + this.currentPage + "\",");
		str.append("\"jsonRoot\":[");

		for (Tender tender : tendersPage) {
			String userName = "理财产品";
			if(tender.getLssuing().getUservip()!=null){
				userName =  tender.getLssuing().getUservip().getUserName();
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
			}
			String perTime = "";
			String lilv = "";
			DecimalFormat df1 = new DecimalFormat("0.00");
			double benxi;
			if (tender.getLssuing().getPeriodday() != null) {
				perTime = tender.getLssuing().getPeriodday().getPeriodDayName();
				benxi = tender.getMoney().intValue()
						* tender.getLssuing().getRate().doubleValue()
						* tender.getLssuing().getPeriodday().getPeriodDayId()
								.intValue() / 100.0D;
				lilv = df1.format(tender.getLssuing().getRate() * 360);
			} else {
				perTime = tender.getLssuing().getPeriodtime()
						.getPeriodTimeName();
				benxi = tender.getMoney().intValue()
						* tender.getLssuing().getRate().doubleValue()
						* tender.getLssuing().getPeriodtime().getPeriodTimeId()
								.intValue() / 12.0D / 100.0D;
				lilv = df1.format(tender.getLssuing().getRate() * 360);
			}

			String benxistr = df1.format(benxi);
			str.append("{\"lssuingNum\":\""
					+ tender.getLssuing().getLssuingNum() + "\",");
			str.append("\"userName\":\"" + userName + "\",");
			str.append("\"rate\":\"" + lilv + "\",");
			str.append("\"money\":\"" + tender.getMoney() + "\",");
			str.append("\"time\":\""
					+ tender.getTenderTime().toString().substring(0, 10)
					+ "\",");
			str.append("\"perTime\":\"" + perTime + "\",");
			str.append("\"borrowMoney\":\""
					+ tender.getLssuing().getBorrowMoney() + "\",");
			str.append("\"benxi\":\"" + benxistr + "\"},");
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
	 * 总额
	 * 
	 * @return
	 * @throws Exception
	 */
	public String tenderlist() throws Exception {
		List<Tender> tenders = this.tenderService.findTendersByuserIdCategory(
				this.uservip.getUserId().intValue(), 2);
		List<Tender> tenders1 = this.tenderService.finTendersBystate(
				this.uservip.getUserId().intValue(), 1);
		List<Tender> tenders2 = this.tenderService.finTendersBystate(
				this.uservip.getUserId().intValue(), 2);

		SystemconfService systemconfService = new SystemconfServiceImpl();
		Systemconf sysconf = systemconfService
				.findSystemconfByParname("overdue_time");
		String parvalue = sysconf.getParvalue();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowdate = df.format(new Date());
		String overtime = nowdate + " " + parvalue;
		if (sdf.parse(overtime).getTime() >= new Date().getTime()) {
			overtime = nowdate + " 00:00:00";
		}
		String sql = "select distinct t.* from Record r,tender t , uservip u where u.userId=t.userId and  r.tenderId=t.tenderId";
		sql += " and r.recordDate<'" + overtime
				+ "' and recordState!=1 and t.userId="
				+ this.uservip.getUserId().intValue();
		System.out.println(sql);
		List<Tender> tenders3 = this.tenderService.findTenderBySql(sql);
		// List<Tender> tenders3 =
		// this.tenderService.findTendersByuserIdCategory(this.uservip.getUserId().intValue(),
		// -1);
		double money = 0.0D;
		for (Tender tender : tenders) {
			money += tender.getMoney().intValue();
		}

		double money1 = 0.0D;
		for (Tender tender : tenders1) {
			money1 = money1 + tender.getMoney().intValue();
		}
		double money2 = 0.0D;
		for (Tender tender : tenders2) {
			money2 = money2 + tender.getMoney().intValue();
		}
		double money3 = 0.0D;
		for (Tender tender : tenders3) {
			money3 = money3 + tender.getMoney().intValue();
		}
		ActionContext.getContext().getSession()
				.put("totalMoney", Double.valueOf(money));
		ActionContext.getContext().getSession()
				.put("totalMoney1", Double.valueOf(money1));
		ActionContext.getContext().getSession()
				.put("totalMoney2", Double.valueOf(money2));
		ActionContext.getContext().getSession()
				.put("totalMoney3", Double.valueOf(money3));
		ActionContext.getContext().getSession()
				.put("total", Double.valueOf(money + money1 + money2));
		ActionContext.getContext().getSession()
				.put("totalSize", Integer.valueOf(tenders.size()));
		ActionContext.getContext().getSession()
				.put("totalSize1", Integer.valueOf(tenders1.size()));
		ActionContext.getContext().getSession()
				.put("totalSize2", Integer.valueOf(tenders2.size()));
		ActionContext.getContext().getSession()
				.put("totalSize3", Integer.valueOf(tenders3.size()));
		ActionContext
				.getContext()
				.getSession()
				.put("totalSize4",
						Integer.valueOf(tenders.size() + tenders1.size()
								+ tenders2.size()));
		return "success";
	}

	/**
	 * 查找标投标记录
	 */
	public void list() {
		Lssuing lssuing = (Lssuing) ActionContext.getContext().getSession()
				.get("lssuing");
		List tenders = null;
		try {
			tenders = this.tenderService.findTendersBylssuingId(lssuing
					.getLssuingId().intValue());
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		int totalRecord = tenders.size();
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

		String sql = "SELECT * FROM tender t WHERE t.lssuingId = "
				+ lssuing.getLssuingId() + " ORDER BY t.tenderId DESC LIMIT "
				+ (this.currentPage - 1) * this.pageSize + "," + this.pageSize;
		List<Tender> tenderspage = null;
		try {
			tenderspage = this.tenderService.findTendersBylssuingIdPage(sql);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		String img = "<img src='../images/loantype/zhuangtai.png' />";
		StringBuffer str = new StringBuffer();
		str.append("{\"totalPage\":\"" + this.totalPage + "\",");
		str.append("\"currentPage\":\"" + this.currentPage + "\",");
		str.append("\"jsonRoot\":[");
		for (Tender student : tenderspage) {
			String userName = student.getUservip().getUserName();
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

			String rate = student.getLssuing().getRate().toString();
			str.append("{\"tenderId\":\"" + student.getTenderId() + "\",");
			str.append("\"userName\":\"" + userName + "\",");
			str.append("\"rate\":\"" + rate + "\",");
			str.append("\"money\":\"" + student.getMoney() + "\",");
			str.append("\"time\":\""
					+ student.getTenderTime().toString().substring(0, 19)
					+ "\",");
			str.append("\"state\":\"" + img + "\"},");
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
	 * 投标分页
	 * 
	 * @throws Exception
	 */
	public void lssuinglist() throws Exception {
		String sql;
		List lssuings;
		System.err.println(lussingType);
		if (this.lussingType >= 5) {
			if (this.satate.equals("null")) {
				lssuings = this.lssuingService.findLssuings();
				sql = "SELECT * FROM lssuing l WHERE  l.state<5 and l.lssuingType = "+lussingType+" ORDER BY l.lssuingId DESC LIMIT "
						+ (this.currentPage - 1)
						* this.pageSize
						+ ","
						+ this.pageSize;
			} else {
				String a = "SELECT * FROM lssuing l where l.lssuingType = "+lussingType+" and  l.state="
						+ this.satate;
				lssuings = this.lssuingService.findLssuingsBySearch(a);
				sql = "SELECT * FROM lssuing l where  l.lssuingType = "+lussingType+" and l.state="
						+ this.satate
						+ "  ORDER BY l.lssuingId DESC LIMIT "
						+ (this.currentPage - 1)
						* this.pageSize
						+ ","
						+ this.pageSize;
			}
		} else {
			if (this.satate.equals("null")) {
				lssuings = this.lssuingService.findLssuings();
				sql = "SELECT * FROM lssuing l WHERE  l.state<5 and l.lssuingType < 6 ORDER BY l.lssuingId DESC LIMIT "
						+ (this.currentPage - 1)
						* this.pageSize
						+ ","
						+ this.pageSize;
			} else {
				String a = "SELECT * FROM lssuing l where l.lssuingType < 6 and  l.state="
						+ this.satate;
				lssuings = this.lssuingService.findLssuingsBySearch(a);
				sql = "SELECT * FROM lssuing l where  l.lssuingType < 6 and l.state="
						+ this.satate
						+ "  ORDER BY l.lssuingId DESC LIMIT "
						+ (this.currentPage - 1)
						* this.pageSize
						+ ","
						+ this.pageSize;
			}
		}
		int totalRecord = lssuings.size();
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
		List<Lssuing> lssuingspage = this.lssuingService
				.findLssuingsBySearch(sql);

		StringBuffer str = new StringBuffer();
		str.append("{\"totalPage\":\"" + this.totalPage + "\",");
		str.append("\"currentPage\":\"" + this.currentPage + "\",");
		str.append("\"jsonRoot\":[");
		for (Lssuing lssuing : lssuingspage) {
			String wate = " ";
			String state = " ";
			String day = "";
			String time = "";
			String number = null;
			String stataclass = "";
			String picture = " ";
			String action = "#";
			List<Tender> tenders = this.tenderService
					.findTendersBylssuingId(lssuing.getLssuingId().intValue());

			int money = 0;
			if (tenders.size() == 0) {
				money = 0;
			} else {
				for (Tender tender : tenders) {
					money = tender.getMoney().intValue() + money;
				}
			}
			int BorrowMoney = Integer.valueOf(lssuing.getBorrowMoney())
					.intValue();
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

			if (lssuing.getLssuingType().intValue() == 1) {
				picture = "<img src='../images/loantype/credit.gif' />"
						+ picture;
			} else if (lssuing.getLssuingType().intValue() == 2) {
				picture = "<img src='../images/loantype/vouch.gif' />"
						+ picture;
			} else if (lssuing.getLssuingType().intValue() == 3) {
				picture = "<img src='../images/loantype/second.gif' />"
						+ picture;
			} else if (lssuing.getLssuingType().intValue() == 4) {
				picture = "<img src='../images/loantype/worth.gif' />"
						+ picture;
			} else if (lssuing.getLssuingType().intValue() == 5) {
				picture = "<img src='../images/loantype/pawn.gif' />" + picture;
			}

			if (lssuing.getIsAward().intValue() == 1) {
				picture = picture
						+ "<img src='../images/loantype/award.gif' />";
				if (lssuing.getAwardMoney() != null
						&& !"".equals(lssuing.getAwardMoney().trim())) {
					wate = lssuing.getAwardMoney().toString() + "";
				} else if (lssuing.getAwardRate() != null
						&& !"".equals(lssuing.getAwardRate().trim())) {
					wate = lssuing.getAwardRate() + "%";
				}
			} else if (lssuing.getIsAward().intValue() == 0) {
				wate = "无奖励";
			}

			if (lssuing.getState().intValue() == 0) {
				state = "审核中";
				stataclass = "check_bt";
			} else if (lssuing.getState().intValue() == 1) {
				state = "复审中";
				stataclass = "check_bt";
			} else if (lssuing.getState().intValue() == 2) {
				state = "立即投资";
				stataclass = "vest_bt";
				action = "totender.action?lssuingId=" + lssuing.getLssuingId();
			} else if (lssuing.getState().intValue() == 3) {
				state = "还款中";
				stataclass = "return_bt";
			} else if (lssuing.getState().intValue() == 4) {
				state = "已完成";
				stataclass = "off_bt";
			} else if (lssuing.getState().intValue() == -1) {
				state = "逾期";
				stataclass = "off_bt";
			}
			if (lssuing.getPeriodtime() != null) {
				time = lssuing.getPeriodtime().getPeriodTimeName();

				day = "年利率：" + lssuing.getRate();
			} else if (lssuing.getPeriodday() != null) {
				picture = picture + "<img src='../images/loantype/day.gif' />";
				time = lssuing.getPeriodday().getPeriodDayName();
				day = "日利率：" + lssuing.getRate();
			}
			if (lssuing.getIsOrient().intValue() == 1) {
				picture = picture + "<img src='../images/loantype/lock.gif' />";
			}
			String title;
			if (lssuing.getTitle().length() > 20) {
				title = lssuing.getTitle().substring(0, 20);
			} else {
				title = lssuing.getTitle();
			}
			String actions = "totender.action?lssuingId="
					+ lssuing.getLssuingId();
			str.append("{\"title\":\"" + title + "\",");
//			str.append("\"credit\":\""
//					+ lssuing.getUservip().getCreditlevel()
//							.getCreditLevelPicture() + "\",");
			str.append("\"rate\":\"" + day + "\",");
			str.append("\"picture\":\"" + picture + "\",");
			str.append("\"wate\":\"" + wate + "\",");
			str.append("\"money\":\"" + lssuing.getBorrowMoney() + "\",");
			str.append("\"time\":\"" + time + "\",");
			str.append("\"bear\":\"" + bear + "\",");
			str.append("\"action\":\"" + action + "\",");
			str.append("\"actions\":\"" + actions + "\",");
			str.append("\"number\":\"" + number + "\",");
			str.append("\"stataclass\":\"" + stataclass + "\",");
			str.append("\"state\":\"" + state + "\"},");
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
	 * 标详细页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String findLssuingByID() throws Exception {
		Lssuing lssuing = this.lssuingService.findLssuingById(Integer
				.valueOf(this.lssuingId));

		List lssingphotos = this.lssingphotoService
				.findLssingphotosBylssuing(lssuing.getLssuingId().intValue());

		int totalRecord = lssingphotos.size();
		int size;
		if (totalRecord % 3 == 0)
			size = totalRecord / 3;
		else {
			size = totalRecord / 3 + 1;
		}
		// List lssList = new ArrayList();
		for (int i = 0; i < size; i++) {
			List lssingphotos1 = this.lssingphotoService
					.findLssingphotosBylssuingPage(lssuing.getLssuingId()
							.intValue(), i * 3, 3);
			lssList.add(lssingphotos1);
		}

		// ActionContext.getContext().getSession().put("lssList", lssList);
		if(lssuing.getLssuingType()!=9){
			Basicinfor basicinfor = this.basicinforService
					.findBasicinforByUserId(lssuing.getUservip().getUserId()
							.intValue());

			ActionContext.getContext().getSession().put("basicinforTdener", basicinfor);
			ActionContext.getContext().getSession().put("lssingphoto", lssingphotos);
			List<Lssuing> lssuings = this.lssuingService
					.findLssuingsByUserId(lssuing.getUservip().getUserId()
							.intValue());
			List lssuings1 = this.lssuingService.findLssuingsByUserIdAndByState(
					lssuing.getUservip().getUserId().intValue(), 3);
			DecimalFormat df1 = new DecimalFormat("0.00");

			ActionContext.getContext().getSession()
					.put("lssuingTdener", Integer.valueOf(lssuings.size()));

			double zonge = 0.0D;
			for (Lssuing lssuing2 : lssuings) {
				zonge = Double.valueOf(lssuing2.getBorrowMoney()).doubleValue()
						+ zonge;
			}
			String zongenum = df1.format(zonge);
			ActionContext.getContext().getSession().put("zonge", zongenum);

			ActionContext.getContext().getSession()
					.put("lssuingTdener", Integer.valueOf(lssuings.size()));

			ActionContext.getContext().getSession()
					.put("lssuingTdenerDai", Integer.valueOf(lssuings1.size()));

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
			ActionContext.getContext().getSession().put("daihuan", daihuannum);
			
			List lssuings2 = this.lssuingService.findLssuingsByUserIdAndByState(
					lssuing.getUservip().getUserId().intValue(), -1);
			ActionContext.getContext().getSession()
					.put("yuqishu", Integer.valueOf(lssuings2.size()));

			List lssuings3 = this.lssuingService.findLssuingsByUserIdAndByState(
					lssuing.getUservip().getUserId().intValue(), 4);
			ActionContext.getContext().getSession()
			.put("yiwancheng", Integer.valueOf(lssuings3.size()));
		}
		
		
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

		if (lssuing.getState().intValue() == 0)
			state = "审核中";
		else if (lssuing.getState().intValue() == 1)
			state = "复审中";
		else if (lssuing.getState().intValue() == 2)
			state = "投资中";
		else if (lssuing.getState().intValue() == 3)
			state = "还款中";
		else if (lssuing.getState().intValue() == 4)
			state = "已完成";
		else if (lssuing.getState().intValue() == -1) {
			state = "逾期";
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

		if (lssuing.getLssuingType().intValue() == 1) {
			picture = "<img src='../images/loantype/credit.gif' />" + picture;
		}
		if (lssuing.getLssuingType().intValue() == 2) {
			picture = "<img src='../images/loantype/vouch.gif' />" + picture;
		}

		if (lssuing.getLssuingType().intValue() == 3) {
			picture = "<img src='../images/loantype/second.gif' />" + picture;
		}

		if (lssuing.getLssuingType().intValue() == 4) {
			picture = "<img src='../images/loantype/worth.gif' />" + picture;
		}

		if (lssuing.getLssuingType().intValue() == 5) {
			picture = "<img src='../images/loantype/pawn.gif' />" + picture;
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
			moneyMax = "0";
		}
		if (lssuing.getIsOrient().intValue() == 1) {
			picture = picture + "<img src='../images/loantype/lock.gif' />";
		}
		Uservip uservip = (Uservip) ActionContext.getContext().getSession()
				.get("uservip");
		if (uservip != null) {
			Moneycount moneycount = this.moneycountService.findMoneycountByuserId(uservip.getUserId().intValue());
			ActionContext.getContext().getSession().put("AvailableMoney", moneycount.getAvailableMoney());
			ActionContext.getContext().put("moneycount", moneycount);
			if (lssuing.getTenderLimit().intValue() == 1) {
				ActionContext.getContext().getSession()
						.put("moneyLimit", moneycount.getCollectTotalMoney());
			} else {
				ActionContext.getContext().getSession()
						.put("moneyLimit", Integer.valueOf(0));
			}
		}
		ActionContext
				.getContext()
				.getSession()
				.put("money",
						Integer.valueOf(Integer.valueOf(
								lssuing.getBorrowMoney()).intValue()
								- money));
		ActionContext.getContext().getSession()
				.put("interest", df.format(interest));
		ActionContext.getContext().getSession().put("picture", picture);
		ActionContext.getContext().getSession().put("away", away);
		ActionContext.getContext().getSession().put("state", state);
		ActionContext.getContext().getSession().put("lssuing", lssuing);
		ActionContext.getContext().getSession().put("number", number);
		ActionContext.getContext().getSession()
				.put("bear", Double.valueOf(bear));
		ActionContext.getContext().getSession().put("day", day);
		ActionContext.getContext().getSession().put("moneyMin", moneyMin);
		ActionContext.getContext().getSession().put("moneyMax", moneyMax);
		ActionContext.getContext().getSession().put("lssingphoto", lssingphotos);
		if (lssuing.getLssuingType() == 6) {
			return "firm";
		}else if(lssuing.getLssuingType() == 9){
			return "lc";
		}else if(lssuing.getLssuingType() == 8){
			return "prac";
		} else {  
			return "success";
		}
	}

	/**
	 * 查找所有表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String totenderList() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String transfer = request.getParameter("transfer");
		this.returnways = this.returnwayService.findReturnways();
		this.moneyuses = this.moneyuseService.findMoneyuses();
		this.periodtimes = this.periodtimeService.findPeriodtimes();
		this.perioddays = this.perioddayService.findPerioddays();
		if(transfer!=null && !"".equals(transfer)){
			return "tran";
		}else if (this.lussingType == 6) {
			return "firm";
		}else if(this.lussingType == 9){
			return "lc";
		}else if(this.lussingType == 8){
			return "prac";
		}
		else {
			return "success";
		}
	}

	/**
	 * 标的投资
	 * 
	 * @return
	 * @throws Exception
	 */
	public String tender() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String path = request.getContextPath();
		Uservip uservip = (Uservip) ActionContext.getContext().getSession()
				.get("uservip");
		UservipService uservipService = new UservipServiceImpl();
		Lssuing lssuing = this.lssuingService.findLssuingById(Integer.valueOf(this.lssuingId));
		if(lssuing.getState()!=2){
			this.setMessage("此标不是投资中的项目",  "/tender/totender.action?lssuingId=" + this.lssuingId, "3");
			return "errorPayPwd";
		}
		if (this.paypwd == null || "".equals(this.paypwd)) {
			this.setMessage("请输入支付密码",  "/tender/totender.action?lssuingId=" + this.lssuingId,
					"3");
			return "errorPayPwd";
		}
		if (!uservip.getPayPwd().equals(MD5Util.md5(this.paypwd))) {
			this.setMessage("您输入的支付密码不正确！","/tender/totender.action?lssuingId=" + this.lssuingId,
					"3");
			return "errorPayPwd";
		}
		Uservip user = uservipService.findUservipByPayPwdAndUserID(
				MD5Util.md5(this.paypwd), uservip.getUserId());
		if (user == null) {
			this.setMessage("您输入的支付密码不正确！", "/tender/totender.action?lssuingId=" + this.lssuingId,
					"3");
			return "errorPayPwd";
		}

		Moneycount moneycount = this.moneycountService
				.findMoneycountByuserId(uservip.getUserId().intValue());
		if(this.lussingType==8){
			if ((moneycount.getExperienceMoney().doubleValue() - Double.valueOf(
					this.money).doubleValue()) < 0) {
				this.setMessage("体验余额小于投标金额", "/tender/totender.action?lssuingId="+ this.lssuingId, "3");
				return "error";
			}
		}else{
			if ((moneycount.getAvailableMoney().doubleValue() - Double.valueOf(
					this.money).doubleValue()) < 0) {
				this.setMessage("账户余额小于投标金额", "/tender/totender.action?lssuingId="
						+ this.lssuingId, "3");
				return "error";
			}
		}
		if(this.lussingType==7){
			String sql="select * from tender where userId="+uservip.getUserId();
			List<Tender> tends=this.tenderService.findTenderBySql(sql);
			if(tends.size()>0 && tends!=null){
				this.setMessage("新手标只能投一次", "/tender/totender.action?lssuingId="+ this.lssuingId, "3");
				return "error";
			}
		}
		if(uservip.getTrustStatus()==1 && uservip.getTrustAccount()!=null && !uservip.getTrustAccount().equals("")){
			
		}else{
			this.setMessage("请先开通托管账号再进行操作", "/user/loginSearch.action", "3");
			return "error";
		}
		
		if(uservip.getAuthorizeStatus()==1){
			
		}else{
			this.setMessage("请先开通授权再进行操作", "/user/loginSearch.action", "3");
			return "error";
		}
		
		//判断秒标是否限制投标次数
		SystemconfService systemconfService = new SystemconfServiceImpl();
		Systemconf systemconf = systemconfService.findSystemconfByParname("con_seconds_borrow_limit");
		int pvalue = Integer.valueOf(systemconf.getParvalue());
		
		

		Tender tender = new Tender();
		tender.setLssuing(lssuing);
		tender.setUservip(uservip);
		tender.setMoney(Integer.valueOf(this.money));

		List<Tender> tenderss = this.tenderService
				.findTendersBylssuingId(Integer.valueOf(this.lssuingId));
		int mon = 0;
		if (tenderss.size() == 0) {
			mon = 0;
		} else {
			for (Tender ten : tenderss) {
				mon = ten.getMoney().intValue() + mon;
				//秒标判断用户是否已经投标
				if(pvalue==1 && ten.getUservip().getUserId()==uservip.getUserId() && lssuing.getLssuingType()==3){
					
					this.setMessage("此标您已经投资过了，不能进行二次投标！", "/tender/totender.action?lssuingId="
							+ this.lssuingId, "3");
					return "error";
				}
			}
		}
		if (Integer.valueOf(Integer.valueOf(
				tender.getLssuing().getBorrowMoney()).intValue()
				- mon) < Integer.parseInt(this.money)) {
			this.setMessage("您输入的金额大于剩余金额！", "/tender/totender.action?lssuingId=" + this.lssuingId,
					"3");
			return "errorMoney";
		}
		if(lssuing.getMoneymin().getMoneyMinName()>Integer.parseInt(this.money)){
			this.setMessage("您输入的金额不能低于最小投资金额！", "/tender/totender.action?lssuingId=" + this.lssuingId,"3");
			return "errorMoney";
		}
		if(lssuing.getMoneymax()!=null){
			if(lssuing.getMoneymax().getMoneyMaxName()!=0){
				if(lssuing.getMoneymax().getMoneyMaxName()<Integer.parseInt(this.money)){
					this.setMessage("您输入的金额不能大于最大投资金额！", "/tender/totender.action?lssuingId=" + this.lssuingId,"3");
					return "errorMoney";
				}
			}
		}
		LoanUtils loanUtils = new LoanUtils();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		tender.setTenderTime(timestamp);
		Systemconf managerconf = null;
		if("1".equals(lssuing.getUservip().getIsVIP())){
			managerconf = this.systemconfService.findSystemconfByParname("con_borrow_manage_fee_2");
			
		}else{
			managerconf = this.systemconfService.findSystemconfByParname("con_borrow_manage_fee");
		}
		Systemconf dealConf = null;
		if("1".equals(lssuing.getUservip().getIsVIP())){
			dealConf = this.systemconfService.findSystemconfByParname("con_borrow_success_fee_2");
		}else{
			dealConf = this.systemconfService.findSystemconfByParname("con_borrow_success_fee");
		}
		Website website = this.websiteService.findWebsiteBywebsiteId(1);
		postform = loanUtils.tender(uservip, tender, lssuing, managerconf, dealConf, website, request, false, false);

		
		return "tranjump";

//		int tMon = Integer.valueOf(this.money).intValue();
//		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//		tender.setTenderTime(timestamp);
//		tender.setState(0);
//		boolean flag = this.tenderService.addTender(tender);
//		if (flag) {
//			List<Tender> tenders = this.tenderService
//					.findTendersBylssuingId(Integer.valueOf(this.lssuingId)
//							.intValue());
//			int money = 0;
//			if (tenders.size() > 0) {
//				for (Tender tender3 : tenders) {
//					money = tender3.getMoney().intValue() + money;
//				}
//			}
//			Lssuing lssuing3 = this.lssuingService.findLssuingById(Integer
//					.valueOf(this.lssuingId));
//			int BorrowMoney = Integer.valueOf(lssuing3.getBorrowMoney())
//					.intValue();
//			if (BorrowMoney - money == 0) {
//				lssuing3.setState(Integer.valueOf(1));
//				this.lssuingService.updateLssuing(lssuing3);
//			}
//
//			Moneyhistorydetail moneyhistorydetail = new Moneyhistorydetail();
//			;
//			if(this.lussingType==8){
//				moneyhistorydetail.setAvailableBalance(Double.valueOf(moneycount.getAvailableMoney().doubleValue()));
//			}else{
//				moneyhistorydetail.setAvailableBalance(Double.valueOf(moneycount
//						.getAvailableMoney().doubleValue()
//						- Double.valueOf(tMon).doubleValue()));
//			}
//			moneyhistorydetail.setFrozenMoney(Double.valueOf(moneycount
//					.getFrozenMoney().doubleValue()
//					+ Double.valueOf(tMon).doubleValue()));
//			moneyhistorydetail.setAffectMoney(Double.valueOf(tMon));
//			moneyhistorydetail.setCollectMoney(moneycount.getCollectTotalMoney());
//			moneyhistorydetail.setIntroduction("投标成功，冻结投标金额");
//			moneyhistorydetail.setUservip(uservip);
//			moneyhistorydetail.setOccurTime(new Timestamp(new Date().getTime()));
//			this.moneyhistorydetailService.createMoneyhistorydetail(moneyhistorydetail);
//			
//			if(this.lussingType==8){
//				moneycount.setExperienceMoney(Double.valueOf(moneycount.getExperienceMoney().doubleValue()
//						-Double.valueOf(tMon).doubleValue()));
//			}else{
//				moneycount.setAvailableMoney(Double.valueOf(moneycount
//						.getAvailableMoney().doubleValue()
//						- Double.valueOf(tMon).doubleValue()));
//			}
//			moneycount.setFrozenMoney(Double.valueOf(moneycount
//					.getFrozenMoney().doubleValue()
//					+ Double.valueOf(tMon).doubleValue()));
//			boolean flag2 = this.moneycountService.updateMoneycount(moneycount);
//
//			if (flag2) {
//				this.setMessage("投标成功",
//						"/tender/totender.action?lssuingId="
//								+ this.lssuingId, "3");
//				return "success";
//			}
//
//		} else {
//			this.setMessage("投标失败","/tender/totender.action?lssuingId=" + this.lssuingId,
//					"3");
//			return "error";
//		}
//		return "success";
	}

	/**
	 * 理财宝投标
	 * 
	 * @return
	 * @throws Exception
	 */
	public String lcAddtender() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
//		String path = request.getContextPath();
		Uservip uservip = (Uservip) ActionContext.getContext().getSession().get("uservip");
		if (uservip != null && uservip.getUserId() != 0) {

		} else {
			this.setMessage("请登录后投资", "/login/login.jsp", "3");
			return "success";
		}
		Lssuing lssuing = this.lssuingService.findLssuingById(Integer.valueOf(this.lssuingId));
		UservipService uservipService = new UservipServiceImpl();
		if (this.money == null || "".equals(this.money)) {
			this.setMessage("请输入投标金额", "/tender/totender.action?lssuingId=" + this.lssuingId,"3");
			return "success";
		}
		if (this.paypwd == null || "".equals(this.paypwd)) {
			this.setMessage("请输入支付密码", "/tender/totender.action?lssuingId=" + this.lssuingId,
					"3");
			return "success";
		}
		if (!uservip.getPayPwd().equals(MD5Util.md5(this.paypwd))) {
			this.setMessage("您输入的支付密码不正确！", "/tender/totender.action?lssuingId=" + this.lssuingId,
					"3");
			return "success";
		}
		Uservip user = uservipService.findUservipByPayPwdAndUserID(
				MD5Util.md5(this.paypwd), uservip.getUserId());
		if (user == null) {
			this.setMessage("您输入的支付密码不正确！","/tender/totender.action?lssuingId=" + this.lssuingId,
					"3");
			return "success";
		}
		if(uservip.getTrustStatus()==1 && uservip.getTrustAccount()!=null && !uservip.getTrustAccount().equals("")){
			
		}else{
			this.setMessage("请先开通托管账号再进行操作", "/user/loginSearch.action", "3");
			return "success";
		}
		
		if(uservip.getAuthorizeStatus()==0){
			this.setMessage("请先开通托管授权再进行操作", "/user/loginSearch.action", "3");
			return "success";
		}
		List<Tender> tenderss = this.tenderService
				.findTendersBylssuingId(lssuing.getLssuingId().intValue());
		double inverstmoney = 0;
		int investnum = 0;
		if (tenderss != null && tenderss.size() > 0) {
			for (int i = 0; i < tenderss.size(); i++) {
				Tender tender = tenderss.get(i);
				inverstmoney += tender.getMoney();
				investnum += tender.getBuynum();
			}
		}

		Moneycount moneycount = this.moneycountService
				.findMoneycountByuserId(uservip.getUserId().intValue());
		double tendermoney = Double.valueOf(this.money);

		if (tendermoney > Double.valueOf(lssuing.getBorrowMoney())-inverstmoney) {
			this.setMessage("您输入的金额大于剩余金额", "/tender/totender.action?lssuingId=" + this.lssuingId,
					"3");
			return "success";
		}
//		if (this.bugnum + investnum > lssuing.getTotalUnit()) {
//			this.setMessage("您购买的份数大于剩余的份数，请重新购买", "/tender/totender.action?lssuingId=" + this.lssuingId,
//					"3");
//			return "success";
//		}
		if ((moneycount.getAvailableMoney().doubleValue() - Double.valueOf(
				tendermoney).doubleValue()) < 0) {
			this.setMessage("账户余额小于投标金额", "/tender/totender.action?lssuingId="
					+ this.lssuingId, "3");
			return "success";
		}
		
		
		
		Tender tender = new Tender();
		tender.setLssuing(lssuing);
		tender.setUservip(uservip);
		tender.setMoney(Double.valueOf(tendermoney).intValue());
		tender.setBuynum(this.bugnum);
		
		LoanUtils loanUtils = new LoanUtils();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		tender.setTenderTime(timestamp);
		tender.setState(0);
		Systemconf managerconf = null;
//		if("1".equals(lssuing.getUservip().getIsVIP())){
//			managerconf = this.systemconfService
//					.findSystemconfByParname("con_borrow_manage_fee_2");
//			
//		}else{
//			managerconf = this.systemconfService
//					.findSystemconfByParname("con_borrow_manage_fee");
//		}
		Systemconf dealConf = null;
//		if("1".equals(lssuing.getUservip().getIsVIP())){
//			dealConf = this.systemconfService
//					.findSystemconfByParname("con_borrow_success_fee_2");
//		}else{
//			dealConf = this.systemconfService
//					.findSystemconfByParname("con_borrow_success_fee");
//		}
		Website website = this.websiteService.findWebsiteBywebsiteId(1);
		postform = loanUtils.lctender(uservip, tender, lssuing, managerconf, dealConf, website, request, false, true);

		return "tranjump";
		
	}

	/**
	 * 企业标自动还款处理
	 * @return
	 * @throws Exception 
	 */
	public String autorepay() throws Exception{
		String strs = new SimpleDateFormat("yyyy-MM-dd").format(Calendar
				.getInstance().getTime());
		String starttime = strs+" 00:00:00";
		String endtime = strs+" 23:59:59";
		List<Repaynote> repaynotes = this.repaynoteService.findComRepay(starttime, endtime);
		//如果存在要还款的企业标，自动还款执行 
		SystemconfService systemconfService = new SystemconfServiceImpl();
		if(repaynotes!=null && repaynotes.size()>0){
			for(int i=0;i<repaynotes.size();i++){
				Repaynote repaynote = repaynotes.get(i);

				Moneycount moneycount = this.moneycountService
						.findMoneycountByuserId(repaynote.getUservip().getUserId().intValue());
				if ((moneycount != null)
						&& (repaynote.getMoneyFour().doubleValue() > moneycount
								.getAvailableMoney().doubleValue())) {
					//网站垫付
					Lssuing lssuing = this.lssuingService.findLssuingById(Integer
							.valueOf(repaynote.getLssuing().getLssuingId()));
					Website website = this.websiteService.findWebsiteBywebsiteId(1);
					
					PlatcountService platcountService = new PlatcountServiceImpl();
					Platcount platcount = new Platcount();
					
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					DecimalFormat df = new DecimalFormat("##.00");
					String nowdate = sdf.format(new Date());
					String repaydate = sdf.format(new Date(repaynote.getRepayDate().getTime()));
					//String nowtime = timedf.format(new Date());
					boolean repaykey = true;
					Double overfee1 =  0.0;
					Double overfee2 = 0.0;
					Double overfee3 = 0.0;
					Double overfee4 = 0.0;
					Uservip uservip = this.uservipService.findUservipByUserid(repaynote.getUservip().getUserId());
					if(repaydate.equals(nowdate)){
						repaynote.setOverdays(0);
						repaynote.setOverInterest(0.0);
					}else{
						if("1".equals(uservip.getIsVIP())){
							Systemconf latefee1 = systemconfService.findSystemconfByParname("con_borrow_late_fee_3_2");
							overfee1 = Double.valueOf(latefee1.getParvalue());
							
							Systemconf latefee2 = systemconfService.findSystemconfByParname("con_borrow_late_fee_30_2");
							overfee2 = Double.valueOf(latefee2.getParvalue());
							
							Systemconf latefee3 = systemconfService.findSystemconfByParname("con_borrow_late_fee_90_2");
							overfee3 = Double.valueOf(latefee3.getParvalue());
							
							Systemconf latefee4 = systemconfService.findSystemconfByParname("con_borrow_late_fee_2");
							overfee4 = Double.valueOf(latefee4.getParvalue());
						}else{
							Systemconf latefee1 = systemconfService.findSystemconfByParname("con_borrow_late_fee_3");
							overfee1 = Double.valueOf(latefee1.getParvalue());
							
							Systemconf latefee2 = systemconfService.findSystemconfByParname("con_borrow_late_fee_30");
							overfee2 = Double.valueOf(latefee2.getParvalue());
							
							Systemconf latefee3 = systemconfService.findSystemconfByParname("con_borrow_late_fee_90");
							overfee3 = Double.valueOf(latefee3.getParvalue());
							
							Systemconf latefee4 = systemconfService.findSystemconfByParname("con_borrow_late_fee");
							overfee4 = Double.valueOf(latefee4.getParvalue());
						}
						repaynote = OverbueCom.comoverInterest(repaynote, overfee1, overfee2, overfee3, overfee4);
//						repaynote.setMoneyFour(repaynote.getMoneyFour()+repaynote.getOverInterest());
						repaykey = false;
					}
					
					platcount.setExpond(Double.valueOf(df.format(website.getExpond()+repaynote.getMoneyFour()+repaynote.getOverInterest())));
					platcount.setIncome(Double.valueOf(df.format(website.getIncome())));
					platcount.setMoney(repaynote.getMoneyFour()+repaynote.getOverInterest());
					platcount.setOpertime(new Timestamp(new Date().getTime()));
					platcount.setProfit(Double.valueOf(df.format(website.getProfit()-(repaynote.getMoneyFour()+repaynote.getOverInterest()))));
					platcount.setRemark("标号:"+lssuing.getLssuingNum()+" 标题:"+lssuing.getTitle()+"逾期，网站垫付");
					platcountService.createPlatcount(platcount);
					
					website.setProfit(Double.valueOf(df.format(website.getProfit()-(repaynote.getMoneyFour()+repaynote.getOverInterest()))));
					website.setExpond(Double.valueOf(df.format(website.getExpond()+repaynote.getMoneyFour()+repaynote.getOverInterest())));
					website.setLoan(website.getLoan()+repaynote.getMoneyFour());
					this.websiteService.updateWebsite(website);
					
					Timestamp now = repaynote.getRepayDate();
					String t = now.toString().substring(0, 10);
					List records = new ArrayList();
					String sql = "select * from Record r  where  r.tenderId in (select tenderId from Tender t where t.lssuingId="
							+ lssuing.getLssuingId().intValue() + ")" + " and r.recordDate like '" + t + "%' and recordState=0";
					records = this.recordService.findRecordByRecordId(sql);
					if ((records != null) && (records.size() > 0)) {
						for (int j = 0; j < records.size(); j++) {
							Record record = (Record) records.get(j);
							record.setRecordState(Integer.valueOf(1));
											
							if(repaykey){
								record.setOverdays(0);
								record.setOverInterest(0.0);
							}else{
								record = OverbueCom.comOverInterest(record, overfee1, overfee2, overfee3, overfee4);
							}
							record.setRecordInterest(Double.valueOf(record.getRecordMoney()
									.doubleValue() + record.getRecordRate().doubleValue()+record.getOverInterest()));
							
							double collectmoney = record.getRecordMoney()
									.doubleValue() + record.getRecordRate().doubleValue();
							
							this.recordService.updateRecord(record);
							Moneycount moneycount2 = this.moneycountService
									.findMoneycountByuserId(record.getUservip().getUserId()
											.intValue());
							Moneyhistorydetail moneyhistorydetail1 = new Moneyhistorydetail();
							moneyhistorydetail1.setAffectMoney(Double.valueOf(record
									.getRecordMoney().doubleValue()
									+ record.getRecordRate().doubleValue()+record.getOverInterest().doubleValue()));
							moneyhistorydetail1.setAvailableBalance(Double
									.valueOf(moneycount2.getAvailableMoney().doubleValue()
											+ record.getRecordMoney().doubleValue()
											+ record.getRecordRate().doubleValue()+record.getOverInterest().doubleValue()));
							moneyhistorydetail1.setOccurTime(new Timestamp(new Date()
									.getTime()));
							moneyhistorydetail1.setUservip(record.getUservip());
							moneyhistorydetail1.setFrozenMoney(moneycount2.getFrozenMoney());
							moneyhistorydetail1.setCollectMoney(Double.valueOf(moneycount2
									.getCollectTotalMoney()
									-collectmoney));
							moneyhistorydetail1.setIntroduction("标号："+lssuing.getLssuingNum()+"平台垫付成功");
							this.moneyhistorydetailService
									.createMoneyhistorydetail(moneyhistorydetail1);

							moneycount2.setAvailableMoney(Double.valueOf(moneycount2
									.getAvailableMoney().doubleValue()
									+ record
									.getRecordMoney().doubleValue()
									+ record.getRecordRate().doubleValue()+record.getOverInterest().doubleValue()));
							
							moneycount2.setDueInMoney(Double.valueOf(moneycount2
									.getDueInMoney().doubleValue()
									- record.getRecordMoney()));
							moneycount2.setCollectTotalMoney(Double.valueOf(moneycount2
									.getCollectTotalMoney() - collectmoney));

							moneycount2.setCollectInterestTotalFee(moneycount2
									.getCollectInterestTotalFee().doubleValue()
									- record.getRecordRate());
							
							moneycount2.setNetEarnInterest(Double.valueOf(moneycount2
									.getNetEarnInterest().doubleValue()
									+ record.getRecordRate().doubleValue()+record.getOverInterest().doubleValue()));
							moneycount2.setAccuProfitAndLossMoney(Double
									.valueOf(moneycount2.getAccuProfitAndLossMoney()
											.doubleValue()
											+ record.getRecordRate().doubleValue()+record.getOverInterest().doubleValue()));
							moneycount2.setCollectInterestTotalFee(Double
									.valueOf(moneycount2.getCollectInterestTotalFee()
											.doubleValue()
											- record.getRecordRate().doubleValue()));
							this.moneycountService.updateMoneycount(moneycount2);

							sql = "select  * from record r where r.tenderId="
									+ record.getTender().getTenderId().intValue()
									+ " and r.recordState!=1";
							System.out.println(sql);
							List lt = this.recordService.findRecordBySql(sql);
							if ((lt != null) && (lt.size() == 0)){
								Tender tender = record.getTender();
								tender.setState(2);
								this.tenderService.updateTender(tender);
							}
							addInbox("资金变动", "您的回款已到账户，请注意查看", record.getUservip()
									.getUserId());
						}
					}

					

					
					
					repaynote.setRepayState(Integer.valueOf(2));
//					repaynote.setMoneyThree(repaynote.getMoneyFour());
					this.repaynoteService.updateRepaynote(repaynote);


					lssuing.setReturnMoney(Double.valueOf(
							lssuing.getReturnMoney().doubleValue()+repaynote.getMoneyOne().doubleValue()+repaynote.getMoneyTwo().doubleValue()
						).doubleValue());
					this.lssuingService.updateLssuing(lssuing);
					return "success";
				}
				if(repaynote.getRepayState()==1){
					return "success";
				}
				int lssuingId = repaynote.getLssuing().getLssuingId().intValue();
				this.lssuing = this.lssuingService.findLssuingById(Integer
						.valueOf(lssuingId));
				
				Systemconf sysconf = systemconfService.findSystemconfByParname("overdue_time");
				String parvalue = sysconf.getParvalue();
				SimpleDateFormat datedf = new SimpleDateFormat("yyyy-MM-dd");
				DecimalFormat df = new DecimalFormat("##.00");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String nowdate = datedf.format(new Date());
				String overtime = nowdate+" "+parvalue;
				if(sdf.parse(overtime).getTime()>=new Date().getTime()){
					overtime = nowdate+" 00:00:00";
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
				if(repaynote.getRepayDate().getTime()<nowtime.getTime()){
					if("1".equals(uservip.getIsVIP())){
						Systemconf latefee1 = systemconfService.findSystemconfByParname("con_borrow_late_fee_3_2");
						overfee1 = Double.valueOf(latefee1.getParvalue());
						
						Systemconf latefee2 = systemconfService.findSystemconfByParname("con_borrow_late_fee_30_2");
						overfee2 = Double.valueOf(latefee2.getParvalue());
						
						Systemconf latefee3 = systemconfService.findSystemconfByParname("con_borrow_late_fee_90_2");
						overfee3 = Double.valueOf(latefee3.getParvalue());
						
						Systemconf latefee4 = systemconfService.findSystemconfByParname("con_borrow_late_fee_2");
						overfee4 = Double.valueOf(latefee4.getParvalue());
					}else{
						Systemconf latefee1 = systemconfService.findSystemconfByParname("con_borrow_late_fee_3");
						overfee1 = Double.valueOf(latefee1.getParvalue());
						
						Systemconf latefee2 = systemconfService.findSystemconfByParname("con_borrow_late_fee_30");
						overfee2 = Double.valueOf(latefee2.getParvalue());
						
						Systemconf latefee3 = systemconfService.findSystemconfByParname("con_borrow_late_fee_90");
						overfee3 = Double.valueOf(latefee3.getParvalue());
						
						Systemconf latefee4 = systemconfService.findSystemconfByParname("con_borrow_late_fee");
						overfee4 = Double.valueOf(latefee4.getParvalue());
					}
					repaynote = OverbueCom.comoverInterest(repaynote, overfee1, overfee2, overfee3, overfee4);
//					repaynote.setMoneyFour(repaynote.getMoneyFour()+repaynote.getOverInterest());
					key = true;
				}
				
				
				Moneyhistorydetail moneyhistorydetail = new Moneyhistorydetail();
				moneyhistorydetail.setAffectMoney(repaynote.getMoneyFour()+repaynote.getOverInterest());
				moneyhistorydetail.setOccurTime(new Timestamp(new Date().getTime()));
				moneyhistorydetail.setAvailableBalance(Double.valueOf(moneycount
						.getAvailableMoney().doubleValue()
						- repaynote.getMoneyFour().doubleValue()-repaynote.getOverInterest()));
				moneyhistorydetail.setUservip(repaynote.getUservip());
				moneyhistorydetail.setCollectMoney(moneycount.getCollectTotalMoney());
				moneyhistorydetail.setFrozenMoney(moneycount.getFrozenMoney());
				moneyhistorydetail.setIntroduction("还款成功");
				if(key){
					moneyhistorydetail.setIntroduction("还款成功，逾期："+repaynote.getOverdays()+"天，逾期利息:"+repaynote.getOverInterest());
				}
				this.moneyhistorydetailService
						.createMoneyhistorydetail(moneyhistorydetail);

				moneycount.setAvailableMoney(Double.valueOf(moneycount
						.getAvailableMoney().doubleValue()
						- repaynote.getMoneyFour().doubleValue()-repaynote.getOverInterest()));
				moneycount.setTotalMoney(Double.valueOf(moneycount.getTotalMoney()
						.doubleValue() - repaynote.getMoneyFour().doubleValue()-repaynote.getOverInterest()));
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
				
				WebsiteService websiteService = new WebsiteServiceImpl();
				Website website = websiteService.findWebsiteBywebsiteId(1);
				if(repaynote.getRepayState()==2){//处理网站垫付的资金
					PlatcountService platcountService = new PlatcountServiceImpl();
					Platcount platcount = new Platcount();
					platcount.setExpond(Double.valueOf(df.format(website.getExpond())));
					platcount.setIncome(Double.valueOf(df.format(website.getIncome()+repaynote.getMoneyFour()+repaynote.getOverInterest())));
					platcount.setMoney(repaynote.getMoneyFour()+repaynote.getOverInterest());
					platcount.setOpertime(new Timestamp(new Date().getTime()));
					platcount.setProfit(Double.valueOf(df.format(website.getProfit()+repaynote.getMoneyFour()+repaynote.getOverInterest())));
					platcount.setRemark("标号:"+this.lssuing.getLssuingNum()+" 标题:"+this.lssuing.getTitle()+"逾期，网站垫付:"+webabccount+"元,逾期："+repaynote.getOverdays()+"天"+"逾期计息："+repaynote.getOverInterest());
					platcountService.createPlatcount(platcount);
					
					website.setIncome(Double.valueOf(df.format(website.getIncome()+repaynote.getMoneyFour()-repaynote.getOverInterest())));
					website.setProfit(Double.valueOf(df.format(website.getProfit()+repaynote.getMoneyFour()+repaynote.getOverInterest())));
					website.setLoan(website.getLoan()+repaynote.getMoneyFour());
				}else{
					Timestamp now = repaynote.getRepayDate();
					String t = now.toString().substring(0, 10);
					List<Record> records = new ArrayList<Record>();
					String sql = "select * from Record r  where  r.tenderId in (select tenderId from Tender t where t.lssuingId="
							+ lssuingId + ")" + " and r.recordDate like '" + t + "%' and recordState=0";
					records = this.recordService.findRecordByRecordId(sql);
					if ((records != null) && (records.size() > 0)) {
						for (int j = 0; j < records.size(); j++) {
							Record record = records.get(j);
							record.setRecordState(Integer.valueOf(1));
							if(key){
								record = OverbueCom.comOverInterest(record, overfee1, overfee2, overfee3, overfee4);					
							}else{
								record.setOverdays(0);
								record.setOverInterest(0.0);
							}
							record.setRecordInterest(Double.valueOf(record.getRecordMoney()
									.doubleValue() + record.getRecordRate().doubleValue()+record.getOverInterest()));
							
							double collectmoney = record.getRecordMoney()
									.doubleValue() + record.getRecordRate().doubleValue();
							
							this.recordService.updateRecord(record);
							Moneycount moneycount2 = this.moneycountService
									.findMoneycountByuserId(record.getUservip().getUserId()
											.intValue());
							Moneyhistorydetail moneyhistorydetail1 = new Moneyhistorydetail();
							moneyhistorydetail1.setAffectMoney(Double.valueOf(record
									.getRecordMoney().doubleValue()
									+ record.getRecordRate().doubleValue()+record.getOverInterest().doubleValue()));
							moneyhistorydetail1.setAvailableBalance(Double
									.valueOf(moneycount2.getAvailableMoney().doubleValue()
											+ record.getRecordMoney().doubleValue()
											+ record.getRecordRate().doubleValue()+record.getOverInterest().doubleValue()));
							moneyhistorydetail1.setOccurTime(new Timestamp(new Date()
									.getTime()));
							moneyhistorydetail1.setUservip(record.getUservip());
							moneyhistorydetail1.setFrozenMoney(moneycount2.getFrozenMoney());
							moneyhistorydetail1.setCollectMoney(Double.valueOf(moneycount2
									.getCollectTotalMoney()
									- collectmoney));
							moneyhistorydetail1.setIntroduction("标号："+this.lssuing.getLssuingNum()+"回款成功");
							if(key){
								moneyhistorydetail1.setIntroduction("标号："+this.lssuing.getLssuingNum()+"回款成功"+",逾期："+record.getOverdays()+"天，逾期利息："+record.getOverInterest());
							}
							this.moneyhistorydetailService
									.createMoneyhistorydetail(moneyhistorydetail1);
			
							moneycount2.setAvailableMoney(Double.valueOf(moneycount2
									.getAvailableMoney().doubleValue()
									+ record.getRecordMoney().doubleValue()
									+ record.getRecordRate().doubleValue()+record.getOverInterest().doubleValue()));
							
							moneycount2.setDueInMoney(Double.valueOf(moneycount2
									.getDueInMoney().doubleValue()
									- record.getRecordMoney()));
							moneycount2.setCollectTotalMoney(Double.valueOf(moneycount2
									.getCollectTotalMoney() - collectmoney));

							moneycount2.setCollectInterestTotalFee(moneycount2
									.getCollectInterestTotalFee().doubleValue()
									- record.getRecordRate());
							moneycount2.setNetEarnInterest(Double.valueOf(moneycount2
									.getNetEarnInterest().doubleValue()
									+ record.getRecordRate().doubleValue()+record.getOverInterest().doubleValue()));
							moneycount2.setAccuProfitAndLossMoney(Double
									.valueOf(moneycount2.getAccuProfitAndLossMoney()
											.doubleValue()
											+ record.getRecordRate().doubleValue()+record.getOverInterest().doubleValue()));
							this.moneycountService.updateMoneycount(moneycount2);
			
							sql = "select  * from record r where r.tenderId="
									+ record.getTender().getTenderId()
									+ " and r.recordState!=1";
							List lt = this.recordService.findRecordBySql(sql);
							if ((lt != null) && (lt.size() == 0)){
								Tender tender = record.getTender();
								tender.setState(2);
								this.tenderService.updateTender(tender);
							}
							addInbox("资金变动", "您的回款已到账户，请注意查看", record.getUservip()
									.getUserId());
						}
					}
				}
				

				int repaystate = repaynote.getRepayState();
				website.setLoan(website.getLoan()+repaynote.getMoneyFour());
			    websiteService.updateWebsite(website);
				
				repaynote.setRepayState(Integer.valueOf(1));
				repaynote.setMoneyThree(repaynote.getMoneyFour());
				this.repaynoteService.updateRepaynote(repaynote);

				String sql = "select * from(select  * from repaynote rp where rp.lssuingId="
						+ this.lssuingId
						+ " and rp.repayState=0) a order by a.repayDate  ";
				List lt = this.repaynoteService.findRepayBySql(sql);
				if ((lt != null) && (lt.size() == 0))
					this.lssuing.setState(Integer.valueOf(4));
				else {
					this.lssuing.setEveryReturnTime(((Repaynote) lt.get(0))
							.getRepayDate());
				}
				if(repaystate==2){
					
				}else{
					this.lssuing.setReturnMoney(Double.valueOf(new BigDecimal(this.lssuing
							.getReturnMoney().doubleValue()).add(
							new BigDecimal(repaynote.getMoneyOne().doubleValue()+repaynote.getMoneyTwo().doubleValue()))
							.doubleValue()));
				}
				
				this.lssuingService.updateLssuing(this.lssuing);
				addInbox("资金变动", "您的资金发生变动，请注意查看", Integer.valueOf(this.userId));
				addInbox("还款", "恭喜你还款成功！", Integer.valueOf(this.userId));
				
				return "success";
			
				
			}
		}
		
		return "";
	}
	
	private InboxService inboxService = new InboxServiceImpl();
	private void addInbox(String c, String a, Integer b) throws Exception {
		Inbox inbox = new Inbox();
		inbox.setSendName("平台通知");
		inbox.setTitle(c);
		inbox.setContent(a);
		inbox.setStatus(Integer.valueOf(0));
		inbox.setReceiveTime(new Timestamp(new Date().getTime()));
		inbox.setUservip(this.uservipService.findUservipByUserid(b.intValue()));
		this.inboxService.createInbox(inbox);
	}
	
	/**
	 * 企业标投标
	 * 
	 * @return
	 * @throws Exception
	 */
	public String comAddtender() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String path = request.getContextPath();
		Uservip uservip = (Uservip) ActionContext.getContext().getSession()
				.get("uservip");
		if (uservip != null && uservip.getUserId() != 0) {

		} else {
			this.setMessage("请登录后投标", "/login/login.jsp", "3");
			return "success";
		}
		Lssuing lssuing = this.lssuingService.findLssuingById(Integer
				.valueOf(this.lssuingId));
		UservipService uservipService = new UservipServiceImpl();
		if (this.paypwd == null || "".equals(this.paypwd)) {
			this.setMessage("请输入支付密码", "/tender/totender.action?lssuingId=" + this.lssuingId,
					"3");
			return "success";
		}
		if (!uservip.getPayPwd().equals(MD5Util.md5(this.paypwd))) {
			this.setMessage("您输入的支付密码不正确！", "/tender/totender.action?lssuingId=" + this.lssuingId,
					"3");
			return "success";
		}
		Uservip user = uservipService.findUservipByPayPwdAndUserID(
				MD5Util.md5(this.paypwd), uservip.getUserId());
		if (user == null) {
			this.setMessage("您输入的支付密码不正确！","/tender/totender.action?lssuingId=" + this.lssuingId,
					"3");
			return "success";
		}
		List<Tender> tenderss = this.tenderService
				.findTendersBylssuingId(lssuing.getLssuingId().intValue());
		double inverstmoney = 0;
		int investnum = 0;
		if (tenderss != null && tenderss.size() > 0) {
			for (int i = 0; i < tenderss.size(); i++) {
				Tender tender = tenderss.get(i);
				inverstmoney += tender.getMoney();
				investnum += tender.getBuynum();
			}
		}

		Moneycount moneycount = this.moneycountService
				.findMoneycountByuserId(uservip.getUserId().intValue());
		double tendermoney = this.bugnum * lssuing.getUnitmoney();

		if (this.bugnum + investnum > lssuing.getTotalUnit()) {
			this.setMessage("您购买的份数大于剩余的份数，请重新购买", "/tender/totender.action?lssuingId=" + this.lssuingId,
					"3");
			return "success";
		}

		if ((moneycount.getAvailableMoney().doubleValue() - Double.valueOf(
				tendermoney).doubleValue()) < 0) {
			this.setMessage("账户余额小于投标金额", "/tender/totender.action?lssuingId="
					+ this.lssuingId, "3");
			return "success";
		}

		Tender tender = new Tender();
		tender.setLssuing(lssuing);
		tender.setUservip(uservip);
		tender.setMoney(Double.valueOf(tendermoney).intValue());
		tender.setBuynum(this.bugnum);

		int tMon = Double.valueOf(tendermoney).intValue();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		tender.setTenderTime(timestamp);
		tender.setState(0);
		boolean flag = this.tenderService.addTender(tender);
		if (flag) {
			List<Tender> tenders = this.tenderService
					.findTendersBylssuingId(Integer.valueOf(this.lssuingId)
							.intValue());
			int money1 = 0;
			if (tenders.size() > 0) {
				for (Tender tender3 : tenders) {
					money1 = tender3.getMoney().intValue() + money1;
				}
			}
			Lssuing lssuing3 = this.lssuingService.findLssuingById(Integer
					.valueOf(this.lssuingId));
			int BorrowMoney = Integer.valueOf(lssuing3.getBorrowMoney())
					.intValue();
			if (BorrowMoney - money1 == 0) {
				lssuing3.setState(Integer.valueOf(1));
				this.lssuingService.updateLssuing(lssuing3);
			}

			if (true) {
				DecimalFormat df = new DecimalFormat("##.00");
				// 投标成功，生成还款计划
				Timestamp setVerify_time = new Timestamp(new Date().getTime());
				SimpleDateFormat format = new SimpleDateFormat(
						"yyyy-MM-dd HH-mm-ss");
				Calendar calender = Calendar.getInstance();
				calender.setTime(setVerify_time);

				format.format(calender.getTime());

				Repaynote repaynote = new Repaynote();
				Moneycount moneycount1 = new Moneycount();
				List tenderList = this.tenderService
						.findTendersBylssuingId(lssuing.getLssuingId()
								.intValue());
				Moneyhistorydetail moneyhistorydetail1 = new Moneyhistorydetail();

				repaynote.setUservip(this.uservipService
						.findUservipByUserid(lssuing.getUservip().getUserId()
								.intValue()));

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
									/ 360.0D
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
						.getMoneyOne().doubleValue()
						+ repaynote.getMoneyTwo().doubleValue())));
				repaynote.setRepayState(Integer.valueOf(0));
				repaynote.setLssuing(lssuing);

				moneycount1 = this.moneycountService
						.findMoneycountByuserId(lssuing.getUservip()
								.getUserId().intValue());
				moneyhistorydetail1 = new Moneyhistorydetail();
				moneyhistorydetail1.setAffectMoney(repaynote.getMoneyOne());
				moneyhistorydetail1.setOccurTime(new Timestamp(new Date()
						.getTime()));
				moneyhistorydetail1.setAvailableBalance(Double.valueOf(df
						.format(moneycount1.getAvailableMoney()
								+ repaynote.getMoneyOne())));
				moneyhistorydetail1.setCollectMoney(Double.valueOf(df
						.format(moneycount1.getCollectTotalMoney())));
				moneyhistorydetail1
						.setFrozenMoney(moneycount1.getFrozenMoney());
				moneyhistorydetail1.setUservip(lssuing.getUservip());
				moneyhistorydetail1.setIntroduction("企业标标号："
						+ lssuing.getLssuingNum() + "借款" + tender.getMoney()
						+ "元成功，开始还款");
				this.moneyhistorydetailService
						.createMoneyhistorydetail(moneyhistorydetail1);
				moneycount1.setAvailableMoney(Double.valueOf(moneycount1
						.getAvailableMoney().doubleValue()
						+ repaynote.getMoneyOne().doubleValue()));
				moneycount1.setTotalMoney(Double.valueOf(moneycount1
						.getTotalMoney().doubleValue()
						+ repaynote.getMoneyOne().doubleValue()));
				moneycount1.setPayInterestTotalFee(Double.valueOf(moneycount1
						.getPayInterestTotalFee().doubleValue()
						+ repaynote.getMoneyTwo().doubleValue()));
				moneycount1.setAccuBorrowMoney(Double.valueOf(moneycount1
						.getAccuBorrowMoney().doubleValue()
						+ repaynote.getMoneyOne().doubleValue()));
				this.moneycountService.updateMoneycount(moneycount1);
				SystemconfService systemconfService = new SystemconfServiceImpl();
				// 借款管理费处理(复审通过收取借款人借款管理费)
				
				Double parvalue = lssuing.getManageMoney();
				double fee = parvalue * repaynote.getMoneyOne() / 100.0;

				moneyhistorydetail1 = new Moneyhistorydetail();
				moneyhistorydetail1.setAffectMoney(fee);
				moneyhistorydetail1.setOccurTime(new Timestamp(new Date()
						.getTime()));
				moneyhistorydetail1.setAvailableBalance(Double.valueOf(df
						.format(moneycount1.getAvailableMoney() - fee)));
				moneyhistorydetail1.setUservip(lssuing.getUservip());
				moneyhistorydetail1
						.setFrozenMoney(moneycount1.getFrozenMoney());
				moneyhistorydetail1.setCollectMoney(Double.valueOf(df
						.format(moneycount1.getCollectTotalMoney())));
				moneyhistorydetail1.setIntroduction("企业标标号："
						+ lssuing.getLssuingNum() + "借款" + tender.getMoney()
						+ "元成功，扣除借款管理费");
				this.moneyhistorydetailService
						.createMoneyhistorydetail(moneyhistorydetail1);
				moneycount1.setAvailableMoney(Double.valueOf(df
						.format(moneycount1.getAvailableMoney() - fee)));
				moneycount1.setLoanManageMoney(Double.valueOf(df
						.format(moneycount1.getLoanManageMoney() + fee)));
				moneycount1
						.setAccuProfitAndLossMoney(Double.valueOf(df
								.format(moneycount1.getAccuProfitAndLossMoney()
										- fee)));
				moneycount1.setTotalMoney(moneycount1.getTotalMoney() - fee);
				this.moneycountService.updateMoneycount(moneycount1);

				
				

				// 投标奖励
				Double lsawardmoney;
				if ((lssuing.getAwardRate() != null)
						&& (!"".equals(lssuing.getAwardRate()))) {
					lsawardmoney = Double.valueOf(Double.valueOf(repaynote
							.getMoneyOne()
							* Double.valueOf(lssuing.getAwardRate()) / 100.0D));
				} else {
					if ((lssuing.getAwardMoney() != null)
							&& (!"".equals(lssuing.getAwardMoney())))
						lsawardmoney = Double.valueOf(Double.valueOf(lssuing
								.getAwardMoney())
								/ Double.valueOf(lssuing.getBorrowMoney()))
								* tender.getMoney();
					else
						lsawardmoney = Double.valueOf(0.0D);
				}
				if (lsawardmoney > 0) {
					moneyhistorydetail1 = new Moneyhistorydetail();
					moneyhistorydetail1.setAffectMoney(lsawardmoney);
					moneyhistorydetail1.setOccurTime(new Timestamp(new Date()
							.getTime()));
					moneyhistorydetail1.setAvailableBalance(Double.valueOf(df
							.format(moneycount1.getAvailableMoney()
									- lsawardmoney)));
					moneyhistorydetail1.setUservip(lssuing.getUservip());
					moneyhistorydetail1.setFrozenMoney(moneycount1
							.getFrozenMoney());
					moneyhistorydetail1.setCollectMoney(moneycount1
							.getCollectTotalMoney());
					moneyhistorydetail1.setIntroduction("企业标标号："
							+ lssuing.getLssuingNum() + "借款"
							+ tender.getMoney() + "元成功，扣除借款奖励");
					this.moneyhistorydetailService
							.createMoneyhistorydetail(moneyhistorydetail1);

					moneycount1.setAccuPayBidReward(moneycount1
							.getAccuPayBidReward() + lsawardmoney);
					moneycount1.setAvailableMoney(Double.valueOf(df
							.format(moneycount1.getAvailableMoney()
									- lsawardmoney)));
					moneycount1.setAccuProfitAndLossMoney(Double.valueOf(df
							.format(moneycount1.getAccuProfitAndLossMoney()
									- lsawardmoney)));
					moneycount1
							.setTotalMoney(Double.valueOf(df.format(moneycount1
									.getTotalMoney() - lsawardmoney)));
					this.moneycountService.updateMoneycount(moneycount1);
				}

				Website website = websiteService.findWebsiteBywebsiteId(1);
				website.setInvestment(website.getInvestment()
						+ Double.valueOf(tender.getMoney()));
				websiteService.updateWebsite(website);

				// 处理投资人资金情况
				String mon = "";
				Integer mon2 = 0;
				Double money = Double.valueOf(Double.parseDouble(df
						.format(Double.valueOf(tender.getMoney().intValue()))));
				Double money2 = 0.0;
				if (lssuing.getPeriodtime() != null) {
					mon = lssuing.getPeriodtime().getPeriodTimeName();
					mon2 = Integer.valueOf(Integer.parseInt(mon.substring(0,
							mon.indexOf('个'))));
					money2 = Double.valueOf(Double.parseDouble(df.format(Double
							.valueOf(tender.getMoney().intValue())
							.doubleValue()
							* lssuing.getRate().doubleValue()
							* mon2.intValue()
							/ 1200.0D)));
				}
				if (lssuing.getPeriodday() != null) {
					mon = lssuing.getPeriodday().getPeriodDayName();
					mon2 = Integer.valueOf(Integer.parseInt(mon.substring(0,
							mon.indexOf('天'))));
					money2 = Double.valueOf(Double.parseDouble(df.format(Double
							.valueOf(tender.getMoney().intValue())
							.doubleValue()
							* lssuing.getRate().doubleValue()
							* mon2.intValue()
							/ 100.0D / 360.0D)));
				}

				Double money3;
				if ((lssuing.getAwardRate() != null)
						&& (!"".equals(lssuing.getAwardRate()))) {
					money3 = Double.valueOf(Double.valueOf(
							tender.getMoney().intValue()).doubleValue()
							* Double.valueOf(lssuing.getAwardRate())
									.doubleValue() / 100.0D);
				} else {
					if ((lssuing.getAwardMoney() != null)
							&& (!"".equals(lssuing.getAwardMoney())))
						money3 = Double.valueOf(Double.valueOf(
								tender.getMoney().intValue()).doubleValue()
								/ Double.valueOf(lssuing.getBorrowMoney())
										.doubleValue()
								* Double.valueOf(lssuing.getAwardMoney())
										.doubleValue());
					else
						money3 = Double.valueOf(0.0D);
				}
				List recordList2 = this.recordService
						.findRecordByRecordId("select * from Record r where r.userId="
								+ tender.getUservip().getUserId()
								+ " order by recordId desc");
				Record record2 = new Record();
				Double backMoney = Double.valueOf(0.0D);
				if ((recordList2 != null) && (recordList2.size() > 0)) {
					record2 = (Record) recordList2.get(recordList2.size() - 1);

					String[] rate = website.getBackAward().split("\\|");
					Double money4 = Double.valueOf(0.0D);
					if ((record2.getRecordState().intValue() == 1)
							&& (lssuing.getPeriodtime() != null)) {
						Integer n = Integer.valueOf(Integer.parseInt(lssuing
								.getPeriodtime()
								.getPeriodTimeName()
								.substring(
										0,
										lssuing.getPeriodtime()
												.getPeriodTimeName()
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
				tendmoneycount.setAvailableMoney(Double.valueOf(tendmoneycount
						.getAvailableMoney().doubleValue()
						- Double.valueOf(money).doubleValue()));

				moneyhistorydetail1 = new Moneyhistorydetail();

				moneyhistorydetail1.setAvailableBalance(tendmoneycount
						.getAvailableMoney().doubleValue());
				moneyhistorydetail1.setAffectMoney(money);
				moneyhistorydetail1.setCollectMoney(Double.valueOf(df
						.format(tendmoneycount.getCollectTotalMoney()
								+ money.doubleValue() + money2.doubleValue())));
				moneyhistorydetail1.setAvailableBalance(tendmoneycount
						.getAvailableMoney());
				moneyhistorydetail1.setOccurTime(new Timestamp(new Date()
						.getTime()));
				moneyhistorydetail1.setUservip(tender.getUservip());
				moneyhistorydetail1.setIntroduction("标号："
						+ lssuing.getLssuingNum() + "投资成功，待收金额增加");
				this.moneyhistorydetailService
						.createMoneyhistorydetail(moneyhistorydetail1);
				// 投标奖励发放
				if (money3 > 0) {
					moneyhistorydetail1 = new Moneyhistorydetail();
					moneyhistorydetail1.setAffectMoney(money3);
					moneyhistorydetail1.setUservip(tender.getUservip());
					moneyhistorydetail1.setAvailableBalance(tendmoneycount
							.getAvailableMoney() + money3);
					moneyhistorydetail1.setCollectMoney(Double
							.valueOf(tendmoneycount.getCollectTotalMoney()
									+ money.doubleValue()
									+ money2.doubleValue()));
					moneyhistorydetail1.setOccurTime(new Timestamp(new Date()
							.getTime()));
					moneyhistorydetail1.setIntroduction("标号："
							+ lssuing.getLssuingNum() + "，投标奖励发放");
					this.moneyhistorydetailService
							.createMoneyhistorydetail(moneyhistorydetail1);
				}

				// 回款续投奖励
				if (backMoney > 0) {
					moneyhistorydetail1 = new Moneyhistorydetail();
					moneyhistorydetail1.setAffectMoney(backMoney);
					moneyhistorydetail1.setAvailableBalance(Double.valueOf(df
							.format(tendmoneycount.getAvailableMoney() + money3
									+ backMoney)));
					moneyhistorydetail1.setFrozenMoney(Double
							.valueOf(tendmoneycount.getFrozenMoney()
									.doubleValue() - money.doubleValue()));
					moneyhistorydetail1.setCollectMoney(Double
							.valueOf(tendmoneycount.getCollectTotalMoney()
									+ money.doubleValue()
									+ money2.doubleValue()));
					moneyhistorydetail1.setOccurTime(new Timestamp(new Date()
							.getTime()));
					moneyhistorydetail1.setUservip(tender.getUservip());
					moneyhistorydetail1.setIntroduction("标号："
							+ lssuing.getLssuingNum() + "回款续投奖励发放");
					this.moneyhistorydetailService
							.createMoneyhistorydetail(moneyhistorydetail1);
				}

				// 一级推荐投资奖励标准
				Systemconf auth_reward_sysconf = systemconfService
						.findSystemconfByParname("con_recommon_invest_reward");
				// 二级推荐投资奖励标准
				Systemconf auth_reward_sysconf2 = systemconfService
						.findSystemconfByParname("con_recommon_invest_reward2");
				// 三级推荐投资奖励标准
				Systemconf auth_reward_sysconf3 = systemconfService
						.findSystemconfByParname("con_recommon_invest_reward3");
				// 四级推荐投资奖励标准
				Systemconf auth_reward_sysconf4 = systemconfService
						.findSystemconfByParname("con_recommon_invest_reward4");
				// 五级推荐投资奖励标准
				Systemconf auth_reward_sysconf5 = systemconfService
						.findSystemconfByParname("con_recommon_invest_reward5");
				// 六级推荐投资奖励标准
				Systemconf auth_reward_sysconf6 = systemconfService
						.findSystemconfByParname("con_recommon_invest_reward6");
				// 六级以下推荐投资奖励标准
				Systemconf auth_reward_sysconf7 = systemconfService
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
					double auth_reward_money = Double.valueOf(df.format(tender
							.getMoney() * auth_reward_fee / 100.0D));
					Moneyhistorydetail suth_reward_moneyhistorydetail = new Moneyhistorydetail();
					suth_reward_moneyhistorydetail
							.setAffectMoney(auth_reward_money);
					suth_reward_moneyhistorydetail
							.setAvailableBalance(recmmonMoneycount
									.getAvailableMoney() + auth_reward_money);
					suth_reward_moneyhistorydetail
							.setFrozenMoney(recmmonMoneycount.getFrozenMoney());
					suth_reward_moneyhistorydetail
							.setCollectMoney(recmmonMoneycount.getCollectTotalMoney());
					suth_reward_moneyhistorydetail.setUservip(auth_reward_user);
					suth_reward_moneyhistorydetail.setOccurTime(new Timestamp(
							new Date().getTime()));
					suth_reward_moneyhistorydetail
							.setIntroduction("您推荐的一级推荐用户："
									+ tender.getUservip().getUserName()
									+ "投资了：" + tender.getMoney() + ",发放推荐投资奖励");
					this.moneyhistorydetailService
							.createMoneyhistorydetail(suth_reward_moneyhistorydetail);
					recmmonMoneycount.setAvailableMoney(recmmonMoneycount
							.getAvailableMoney() + auth_reward_money);
					recmmonMoneycount.setTotalMoney(recmmonMoneycount
							.getTotalMoney() + auth_reward_money);
					recmmonMoneycount.setAccuPromoteReward(recmmonMoneycount
							.getAccuPromoteReward() + auth_reward_money);
					recmmonMoneycount
							.setAccuProfitAndLossMoney(recmmonMoneycount
									.getAccuProfitAndLossMoney()
									+ auth_reward_money);
					this.moneycountService.updateMoneycount(recmmonMoneycount);
					addInbox("一级推荐投资奖励发放",
							"您推荐的一级推荐用户：" + tender.getUservip().getUserName()
									+ "投资了：" + tender.getMoney() + ",发放推荐投资奖励"
									+ auth_reward_money + "元("
									+ auth_reward_fee + "%)",
							auth_reward_user.getUserId());

				}

				// 处理二级推荐人奖励情况
				if (auth_reward_fee2 > 0
						&& auth_reward_user.getUservip() != null
						&& auth_reward_user.getUservip().getUserId() != null) {

					auth_reward_user2 = auth_reward_user.getUservip();
					Moneycount recmmonMoneycount = this.moneycountService
							.findMoneycountByuserId(auth_reward_user2
									.getUserId().intValue());
					double auth_reward_money = Double.valueOf(df.format(tender
							.getMoney() * auth_reward_fee2 / 100.0D));
					Moneyhistorydetail suth_reward_moneyhistorydetail = new Moneyhistorydetail();
					suth_reward_moneyhistorydetail
							.setAffectMoney(auth_reward_money);
					suth_reward_moneyhistorydetail
							.setAvailableBalance(recmmonMoneycount
									.getAvailableMoney() + auth_reward_money);
					suth_reward_moneyhistorydetail
							.setFrozenMoney(recmmonMoneycount.getFrozenMoney());
					suth_reward_moneyhistorydetail
							.setCollectMoney(recmmonMoneycount.getCollectTotalMoney());
					suth_reward_moneyhistorydetail
							.setUservip(auth_reward_user2);
					suth_reward_moneyhistorydetail.setOccurTime(new Timestamp(
							new Date().getTime()));
					suth_reward_moneyhistorydetail
							.setIntroduction("您推荐的二级推荐用户："
									+ tender.getUservip().getUserName()
									+ "投资了：" + tender.getMoney() + ",发放推荐投资奖励");
					this.moneyhistorydetailService
							.createMoneyhistorydetail(suth_reward_moneyhistorydetail);
					recmmonMoneycount.setAvailableMoney(recmmonMoneycount
							.getAvailableMoney() + auth_reward_money);
					recmmonMoneycount.setTotalMoney(recmmonMoneycount
							.getTotalMoney() + auth_reward_money);
					recmmonMoneycount.setAccuPromoteReward(recmmonMoneycount
							.getAccuPromoteReward() + auth_reward_money);
					recmmonMoneycount
							.setAccuProfitAndLossMoney(recmmonMoneycount
									.getAccuProfitAndLossMoney()
									+ auth_reward_money);
					this.moneycountService.updateMoneycount(recmmonMoneycount);
					addInbox("二级推荐投资奖励发放", "您推荐的二级推荐用户："
							+ tender.getUservip().getUserName() + "投资了："
							+ tender.getMoney() + ",发放推荐投资奖励"
							+ auth_reward_money + "元(" + auth_reward_fee2
							+ "%)", auth_reward_user2.getUserId());

				}
				// 处理三级推荐人奖励情况
				if (auth_reward_fee3 > 0
						&& auth_reward_user2.getUservip() != null
						&& auth_reward_user2.getUservip().getUserId() != null) {

					auth_reward_user3 = auth_reward_user2.getUservip();
					Moneycount recmmonMoneycount = this.moneycountService
							.findMoneycountByuserId(auth_reward_user3
									.getUserId().intValue());
					double auth_reward_money = Double.valueOf(df.format(tender
							.getMoney() * auth_reward_fee3 / 100.0D));
					Moneyhistorydetail suth_reward_moneyhistorydetail = new Moneyhistorydetail();
					suth_reward_moneyhistorydetail
							.setAffectMoney(auth_reward_money);
					suth_reward_moneyhistorydetail
							.setAvailableBalance(recmmonMoneycount
									.getAvailableMoney() + auth_reward_money);
					suth_reward_moneyhistorydetail
							.setFrozenMoney(recmmonMoneycount.getFrozenMoney());
					suth_reward_moneyhistorydetail
							.setCollectMoney(recmmonMoneycount.getCollectTotalMoney());
					suth_reward_moneyhistorydetail
							.setUservip(auth_reward_user3);
					suth_reward_moneyhistorydetail.setOccurTime(new Timestamp(
							new Date().getTime()));
					suth_reward_moneyhistorydetail
							.setIntroduction("您推荐的三级推荐用户："
									+ tender.getUservip().getUserName()
									+ "投资了：" + tender.getMoney() + ",发放推荐投资奖励");
					this.moneyhistorydetailService
							.createMoneyhistorydetail(suth_reward_moneyhistorydetail);
					recmmonMoneycount.setAvailableMoney(recmmonMoneycount
							.getAvailableMoney() + auth_reward_money);
					recmmonMoneycount.setTotalMoney(recmmonMoneycount
							.getTotalMoney() + auth_reward_money);
					recmmonMoneycount.setAccuPromoteReward(recmmonMoneycount
							.getAccuPromoteReward() + auth_reward_money);
					recmmonMoneycount
							.setAccuProfitAndLossMoney(recmmonMoneycount
									.getAccuProfitAndLossMoney()
									+ auth_reward_money);
					this.moneycountService.updateMoneycount(recmmonMoneycount);
					addInbox("三级推荐投资奖励发放", "您推荐的三级推荐用户："
							+ tender.getUservip().getUserName() + "投资了："
							+ tender.getMoney() + ",发放推荐投资奖励"
							+ auth_reward_money + "元(" + auth_reward_fee3
							+ "%)", auth_reward_user3.getUserId());

				}

				// 处理四级推荐人奖励情况
				if (auth_reward_fee4 > 0
						&& auth_reward_user3.getUservip() != null
						&& auth_reward_user3.getUservip().getUserId() != null) {

					auth_reward_user4 = auth_reward_user3.getUservip();
					Moneycount recmmonMoneycount = this.moneycountService
							.findMoneycountByuserId(auth_reward_user4
									.getUserId().intValue());
					double auth_reward_money = Double.valueOf(df.format(tender
							.getMoney() * auth_reward_fee4 / 100.0D));
					Moneyhistorydetail suth_reward_moneyhistorydetail = new Moneyhistorydetail();
					suth_reward_moneyhistorydetail
							.setAffectMoney(auth_reward_money);
					suth_reward_moneyhistorydetail
							.setAvailableBalance(recmmonMoneycount
									.getAvailableMoney() + auth_reward_money);
					suth_reward_moneyhistorydetail
							.setFrozenMoney(recmmonMoneycount.getFrozenMoney());
					suth_reward_moneyhistorydetail
							.setCollectMoney(recmmonMoneycount.getCollectTotalMoney());
					suth_reward_moneyhistorydetail
							.setUservip(auth_reward_user4);
					suth_reward_moneyhistorydetail.setOccurTime(new Timestamp(
							new Date().getTime()));
					suth_reward_moneyhistorydetail
							.setIntroduction("您推荐的四级推荐用户："
									+ tender.getUservip().getUserName()
									+ "投资了：" + tender.getMoney() + ",发放推荐投资奖励");
					this.moneyhistorydetailService
							.createMoneyhistorydetail(suth_reward_moneyhistorydetail);
					recmmonMoneycount.setAvailableMoney(recmmonMoneycount
							.getAvailableMoney() + auth_reward_money);
					recmmonMoneycount.setTotalMoney(recmmonMoneycount
							.getTotalMoney() + auth_reward_money);
					recmmonMoneycount.setAccuPromoteReward(recmmonMoneycount
							.getAccuPromoteReward() + auth_reward_money);
					recmmonMoneycount
							.setAccuProfitAndLossMoney(recmmonMoneycount
									.getAccuProfitAndLossMoney()
									+ auth_reward_money);
					this.moneycountService.updateMoneycount(recmmonMoneycount);
					addInbox("四级推荐投资奖励发放", "您推荐的四级推荐用户："
							+ tender.getUservip().getUserName() + "投资了："
							+ tender.getMoney() + ",发放推荐投资奖励"
							+ auth_reward_money + "元(" + auth_reward_fee4
							+ "%)", auth_reward_user4.getUserId());

				}
				// 处理五级推荐人奖励情况
				if (auth_reward_fee5 > 0
						&& auth_reward_user4.getUservip() != null
						&& auth_reward_user4.getUservip().getUserId() != null) {

					auth_reward_user5 = auth_reward_user4.getUservip();
					Moneycount recmmonMoneycount = this.moneycountService
							.findMoneycountByuserId(auth_reward_user5
									.getUserId().intValue());
					double auth_reward_money = Double.valueOf(df.format(tender
							.getMoney() * auth_reward_fee5 / 100.0D));
					Moneyhistorydetail suth_reward_moneyhistorydetail = new Moneyhistorydetail();
					suth_reward_moneyhistorydetail
							.setAffectMoney(auth_reward_money);
					suth_reward_moneyhistorydetail
							.setAvailableBalance(recmmonMoneycount
									.getAvailableMoney() + auth_reward_money);
					suth_reward_moneyhistorydetail
							.setFrozenMoney(recmmonMoneycount.getFrozenMoney());
					suth_reward_moneyhistorydetail
							.setCollectMoney(recmmonMoneycount.getCollectTotalMoney());
					suth_reward_moneyhistorydetail
							.setUservip(auth_reward_user5);
					suth_reward_moneyhistorydetail.setOccurTime(new Timestamp(
							new Date().getTime()));
					suth_reward_moneyhistorydetail
							.setIntroduction("您推荐的五级推荐用户："
									+ tender.getUservip().getUserName()
									+ "投资了：" + tender.getMoney() + ",发放推荐投资奖励");
					this.moneyhistorydetailService
							.createMoneyhistorydetail(suth_reward_moneyhistorydetail);
					recmmonMoneycount.setAvailableMoney(recmmonMoneycount
							.getAvailableMoney() + auth_reward_money);
					recmmonMoneycount.setTotalMoney(recmmonMoneycount
							.getTotalMoney() + auth_reward_money);
					recmmonMoneycount.setAccuPromoteReward(recmmonMoneycount
							.getAccuPromoteReward() + auth_reward_money);
					recmmonMoneycount
							.setAccuProfitAndLossMoney(recmmonMoneycount
									.getAccuProfitAndLossMoney()
									+ auth_reward_money);
					this.moneycountService.updateMoneycount(recmmonMoneycount);
					addInbox("五级推荐投资奖励发放", "您推荐的五级推荐用户："
							+ tender.getUservip().getUserName() + "投资了："
							+ tender.getMoney() + ",发放推荐投资奖励"
							+ auth_reward_money + "元(" + auth_reward_fee5
							+ "%)", auth_reward_user5.getUserId());

				}
				// 处理六级推荐人奖励情况
				if (auth_reward_fee6 > 0
						&& auth_reward_user5.getUservip() != null
						&& auth_reward_user5.getUservip().getUserId() != null) {

					auth_reward_user6 = auth_reward_user5.getUservip();
					Moneycount recmmonMoneycount = this.moneycountService
							.findMoneycountByuserId(auth_reward_user6
									.getUserId().intValue());
					double auth_reward_money = Double.valueOf(df.format(tender
							.getMoney() * auth_reward_fee6 / 100.0D));
					Moneyhistorydetail suth_reward_moneyhistorydetail = new Moneyhistorydetail();
					suth_reward_moneyhistorydetail
							.setAffectMoney(auth_reward_money);
					suth_reward_moneyhistorydetail
							.setAvailableBalance(recmmonMoneycount
									.getAvailableMoney() + auth_reward_money);
					suth_reward_moneyhistorydetail
							.setFrozenMoney(recmmonMoneycount.getFrozenMoney());
					suth_reward_moneyhistorydetail
							.setCollectMoney(recmmonMoneycount.getCollectTotalMoney());
					suth_reward_moneyhistorydetail
							.setUservip(auth_reward_user6);
					suth_reward_moneyhistorydetail.setOccurTime(new Timestamp(
							new Date().getTime()));
					suth_reward_moneyhistorydetail
							.setIntroduction("您推荐的六级推荐用户："
									+ tender.getUservip().getUserName()
									+ "投资了：" + tender.getMoney() + ",发放推荐投资奖励");
					this.moneyhistorydetailService
							.createMoneyhistorydetail(suth_reward_moneyhistorydetail);
					recmmonMoneycount.setAvailableMoney(recmmonMoneycount
							.getAvailableMoney() + auth_reward_money);
					recmmonMoneycount.setTotalMoney(recmmonMoneycount
							.getTotalMoney() + auth_reward_money);
					recmmonMoneycount.setAccuPromoteReward(recmmonMoneycount
							.getAccuPromoteReward() + auth_reward_money);
					recmmonMoneycount
							.setAccuProfitAndLossMoney(recmmonMoneycount
									.getAccuProfitAndLossMoney()
									+ auth_reward_money);
					this.moneycountService.updateMoneycount(recmmonMoneycount);
					addInbox("六级推荐投资奖励发放", "您推荐的六级级推荐用户："
							+ tender.getUservip().getUserName() + "投资了："
							+ tender.getMoney() + ",发放推荐投资奖励"
							+ auth_reward_money + "元(" + auth_reward_fee6
							+ "%)", auth_reward_user6.getUserId());

				}

				// 处理六级以上推荐人奖励情况
				if (auth_reward_fee7 > 0
						&& auth_reward_user6.getUservip() != null
						&& auth_reward_user6.getUservip().getUserId() != null) {
					// 可以得到推荐奖励的用户
					List<Uservip> userList = new ArrayList<Uservip>();
					// 当前用户的推荐人
					Uservip tempuservip = auth_reward_user6.getUservip();
					int num = 0;
					while (num == 0) {
						if (tempuservip.getUservip() != null
								&& !"".equals(tempuservip.getUservip())) {
							userList.add(tempuservip.getUservip());
							tempuservip = tempuservip.getUservip();
						} else {
							num = 1;
						}
					}
					for (Uservip user1 : userList) {
						Moneycount recmmonMoneycount = this.moneycountService
								.findMoneycountByuserId(user1.getUserId()
										.intValue());
						double auth_reward_money = Double.valueOf(df
								.format(tender.getMoney() * auth_reward_fee7
										/ 100.0D));
						Moneyhistorydetail suth_reward_moneyhistorydetail = new Moneyhistorydetail();
						suth_reward_moneyhistorydetail
								.setAffectMoney(auth_reward_money);
						suth_reward_moneyhistorydetail
								.setAvailableBalance(recmmonMoneycount
										.getAvailableMoney()
										+ auth_reward_money);
						suth_reward_moneyhistorydetail
								.setFrozenMoney(recmmonMoneycount
										.getFrozenMoney());
						suth_reward_moneyhistorydetail
								.setCollectMoney(recmmonMoneycount
										.getCollectTotalMoney());
						suth_reward_moneyhistorydetail.setUservip(user1);
						suth_reward_moneyhistorydetail
								.setOccurTime(new Timestamp(new Date()
										.getTime()));
						suth_reward_moneyhistorydetail
								.setIntroduction("您推荐的六级以下推荐用户："
										+ tender.getUservip().getUserName()
										+ "投资了：" + tender.getMoney()
										+ ",发放推荐投资奖励");
						this.moneyhistorydetailService
								.createMoneyhistorydetail(suth_reward_moneyhistorydetail);
						recmmonMoneycount.setAvailableMoney(recmmonMoneycount
								.getAvailableMoney() + auth_reward_money);
						recmmonMoneycount.setTotalMoney(recmmonMoneycount
								.getTotalMoney() + auth_reward_money);
						recmmonMoneycount
								.setAccuPromoteReward(recmmonMoneycount
										.getAccuPromoteReward()
										+ auth_reward_money);
						recmmonMoneycount
								.setAccuProfitAndLossMoney(recmmonMoneycount
										.getAccuProfitAndLossMoney()
										+ auth_reward_money);
						this.moneycountService
								.updateMoneycount(recmmonMoneycount);
						addInbox("六级以下推荐投资奖励发放", "您推荐的六级以下推荐用户："
								+ tender.getUservip().getUserName() + "投资了："
								+ tender.getMoney() + ",发放推荐投资奖励"
								+ auth_reward_money + "元(" + auth_reward_fee7
								+ "%)", user1.getUserId());
					}
				}

				tendmoneycount.setAvailableMoney(Double.valueOf(tendmoneycount
						.getAvailableMoney().doubleValue()
						+ money3.doubleValue() + backMoney.doubleValue()));
				
				tendmoneycount.setDueInMoney(Double
						.valueOf(tendmoneycount.getDueInMoney()
								.doubleValue() + money.doubleValue()));
				
				tendmoneycount.setCollectTotalMoney(tendmoneycount.getCollectTotalMoney()+money+money2);;
				
				tendmoneycount.setTotalMoney(Double.valueOf(tendmoneycount
						.getTotalMoney().doubleValue()
						+ money2.doubleValue()
						+ money3.doubleValue() + backMoney.doubleValue()));
				tendmoneycount.setAccuBidReward(Double.valueOf(tendmoneycount
						.getAccuBidReward().doubleValue()
						+ money3.doubleValue()));
				tendmoneycount.setAccuContinueBidReward(Double
						.valueOf(tendmoneycount.getAccuContinueBidReward()
								.doubleValue() + backMoney.doubleValue()));
				tendmoneycount.setAccuProfitAndLossMoney(Double
						.valueOf(tendmoneycount.getAccuProfitAndLossMoney()
								.doubleValue()
								+ money3.doubleValue()
								+ backMoney.doubleValue()));
				tendmoneycount.setAccuInvestMoney(Double.valueOf(tendmoneycount
						.getAccuInvestMoney().doubleValue()
						+ money.doubleValue()));
				tendmoneycount.setCollectInterestTotalFee(Double
						.valueOf(tendmoneycount.getCollectInterestTotalFee()
								.doubleValue() + money2.doubleValue()));
				this.moneycountService.updateMoneycount(tendmoneycount);

				tender.setState(1);
				tenderService.updateTender(tender);

				addInbox("资金变动", "您的资金发生变动，请注意查看", tender.getUservip()
						.getUserId());
				addInbox("投资", "恭喜你的投资成功，标号为" + lssuing.getLssuingNum() + "！",
						tender.getUservip().getUserId());

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
										* mon21.intValue() / 100.0D / 360.0D)));
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

				addInbox("资金变动", "您的资金发生变动，请注意查看", lssuing.getUservip()
						.getUserId());
				addInbox("借款", "恭喜你的借款成功，标号为" + lssuing.getLssuingNum() + "！",
						lssuing.getUservip().getUserId());

				if (BorrowMoney - money1 == 0) {
					lssuing3.setState(3);
					this.lssuingService.updateLssuing(lssuing3);
				}
				
			}
			this.setMessage("投标成功","/tender/totender.action?lssuingId="
							+ this.lssuingId, "3");
			return "success";
		} else {
			this.setMessage("投标失败", "/tender/totender.action?lssuingId=" + this.lssuingId,
					"3");
			return "success";
		}
	}
	public List<Returnway> getReturnways() {
		return this.returnways;
	}

	public void setReturnways(List<Returnway> returnways) {
		this.returnways = returnways;
	}

	public List<Moneyuse> getMoneyuses() {
		return this.moneyuses;
	}

	public void setMoneyuses(List<Moneyuse> moneyuses) {
		this.moneyuses = moneyuses;
	}

	public List<Periodtime> getPeriodtimes() {
		return this.periodtimes;
	}

	public void setPeriodtimes(List<Periodtime> periodtimes) {
		this.periodtimes = periodtimes;
	}

	public List<Periodday> getPerioddays() {
		return this.perioddays;
	}

	public void setPerioddays(List<Periodday> perioddays) {
		this.perioddays = perioddays;
	}

	public String getLssuingId() {
		return this.lssuingId;
	}

	public void setLssuingId(String lssuingId) {
		this.lssuingId = lssuingId;
	}

	public String getMoney() {
		return this.money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public List<Tender> getTenders() {
		return this.tenders;
	}

	public void setTenders(List<Tender> tenders) {
		this.tenders = tenders;
	}

	public List<Tender> getTenderspage() {
		return this.tenderspage;
	}

	public void setTenderspage(List<Tender> tenderspage) {
		this.tenderspage = tenderspage;
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

	public String getSatate() {
		return this.satate;
	}

	public void setSatate(String satate) {
		this.satate = satate;
	}

	public int getTenderId() {
		return this.tenderId;
	}

	public void setTenderId(int tenderId) {
		this.tenderId = tenderId;
	}

	public Tender getTender() {
		return tender;
	}

	public void setTender(Tender tender) {
		this.tender = tender;
	}

	public Lssuing getLssuing() {
		return lssuing;
	}

	public List<Record> getRecords() {
		return records;
	}

	public void setRecords(List<Record> records) {
		this.records = records;
	}

	public void setLssuing(Lssuing lssuing) {
		this.lssuing = lssuing;
	}

	public String getPaypwd() {
		return paypwd;
	}

	public void setPaypwd(String paypwd) {
		this.paypwd = paypwd;
	}

	public List getLssList() {
		return lssList;
	}

	public void setLssList(List lssList) {
		this.lssList = lssList;
	}

	public int getLussingType() {
		return lussingType;
	}

	public void setLussingType(int lussingType) {
		this.lussingType = lussingType;
	}

	public int getBugnum() {
		return bugnum;
	}

	public void setBugnum(int bugnum) {
		this.bugnum = bugnum;
	}

	public String getPostform() {
		return postform;
	}

	public void setPostform(String postform) {
		this.postform = postform;
	}
	
}