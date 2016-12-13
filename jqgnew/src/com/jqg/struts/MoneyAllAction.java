package com.jqg.struts;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.text.DecimalFormat;
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
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.jqg.pojo.Lssuing;
import com.jqg.pojo.Moneycount;
import com.jqg.pojo.Moneyhistorydetail;
import com.jqg.pojo.Offlinerecharge;
import com.jqg.pojo.Tender;
import com.jqg.pojo.Translate;
import com.jqg.pojo.Uservip;
import com.jqg.service.LssuingService;
import com.jqg.service.MoneycountService;
import com.jqg.service.MoneyhistorydetailService;
import com.jqg.service.OfflinerechargeService;
import com.jqg.service.TenderService;
import com.jqg.service.TranslateService;
import com.jqg.service.UservipService;
import com.jqg.service.impl.LssuingServiceImpl;
import com.jqg.service.impl.MoneycountServiceImpl;
import com.jqg.service.impl.MoneyhistorydetailServiceImpl;
import com.jqg.service.impl.OfflinerechargeServiceImpl;
import com.jqg.service.impl.TenderServiceImpl;
import com.jqg.service.impl.TranslateServiceImpl;
import com.jqg.service.impl.UservipServiceImpl;
import com.jqg.util.ExcelDownload;
import com.jqg.util.Test;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class MoneyAllAction extends BaseAction
  implements ServletRequestAware, ServletResponseAware
{
  private String result;
  private List<Moneycount> moneycountList;
  private Moneycount moneycount;
  private List<Moneyhistorydetail> moneyhistorydetailList;
  private List<Tender> tenderlist;
  private List<Uservip> uservipList;
  private List<Lssuing> lssuingList;
  private Integer userId;
  private int managerId;
  private int mark;
  private int queryFlag;
  private int updateFlag;
  private Integer page = Integer.valueOf(0);
  private Integer pageSize = Integer.valueOf(10);
  private Integer pageCount;
  private Integer total = Integer.valueOf(0);
  private File imgFile;
  private String picFileName;
  private MoneycountService moneycountService = new MoneycountServiceImpl();
  private MoneyhistorydetailService moneyhistorydetailService = new MoneyhistorydetailServiceImpl();
  private UservipService uservipService = new UservipServiceImpl();
  private OfflinerechargeService offlinerechargeService = new OfflinerechargeServiceImpl();
  private TranslateService translateService = new TranslateServiceImpl();
  private LssuingService lssuingService = new LssuingServiceImpl();
  private TenderService tenderService = new TenderServiceImpl();
  private String userNameQuery;
  private String realName;
  private Double moneyQuery;
  private String title;

private Timestamp startTimeQuery;
  private Timestamp endTimeQuery;
  private int updown;
  private String urlstr;
  private int num1 = 0;
  private int num2 = 0;
  private int num3 = 0;
  private int num4 = 0;
  private String money1 = "0";
  private String money2 = "0";
  private String money3 = "0";
  private String money4 = "0";
  private String borrow1 = "0";
  private String borrow2 = "0";
  private String borrow3 = "0";
  private String borrow4 = "0";
  private String invest1 = "0";
  private String invest2 = "0";
  private String invest3 = "0";
  private String invest4 = "0";
  private String invest5 = "0";
  private String invest6 = "0";
  private HttpServletRequest request;
  private HttpServletResponse response;

  public String toUserMoney()
    throws Exception
  {
    String sql = "select m.* from moneycount m, Uservip u  where  m.userId=u.userId   ";
    if ((this.userNameQuery != null) && (!"".equals(this.userNameQuery))) {
      sql = sql + " and  u.userName like '%" + this.userNameQuery + "%' ";
    }
    if ((this.realName != null) && (!"".equals(this.realName))) {
      sql = sql + " and  u.realName like '%" + this.realName + "%' ";
    }
    if ((this.moneyQuery != null) && (!"".equals(this.moneyQuery))) {
      if (this.mark == 1)
        sql = sql + " and m.availableMoney >=" + this.moneyQuery;
      else if (this.mark == 2)
        sql = sql + " and m.frozenMoney >=" + this.moneyQuery;
      else if (this.mark == 3) {
        sql = sql + " and m.dueInMoney >=" + this.moneyQuery;
      }
    }

    sql = sql + " order by m.moneyCountId desc ";
    List list = this.moneycountService.findMoneycountBySql(sql);
    this.total = Integer.valueOf(list.size());
    if (this.page.intValue() == 0) {
      this.page = Integer.valueOf(1);
    }
    if (this.total.intValue() == 0)
      this.pageCount = Integer.valueOf(1);
    else if (this.total.intValue() % this.pageSize.intValue() == 0)
      this.pageCount = Integer.valueOf(this.total.intValue() / this.pageSize.intValue());
    else {
      this.pageCount = Integer.valueOf(this.total.intValue() / this.pageSize.intValue() + 1);
    }
    sql = sql + 
      "limit " + (this.page.intValue() - 1) * this.pageSize.intValue() + "," + this.pageSize;
    this.moneycountList = this.moneycountService.findMoneycountBySql(sql);
    if (this.updown == 1) {
      this.urlstr = URLDecoder.decode(this.urlstr, "UTF-8");
      Test.upLoad(this.urlstr, this.moneycountList);
      ExcelDownload.download(this.request, this.response, this.urlstr, "userNote.xls");
      this.updown = 2;
    }
    return "success";
  }
/**
 * 导出会员帐户
 * */
  public String upLoadUserMoney()throws Exception{
		    String sql = "select m.* from moneycount m, Uservip u  where  m.userId=u.userId   ";
		    if ((this.userNameQuery != null) && (!"".equals(this.userNameQuery))) {
		      sql = sql + " and  u.userName like '%" + this.userNameQuery + "%' ";
		    }
		    if ((this.realName != null) && (!"".equals(this.realName))) {
		      sql = sql + " and  u.realName like '%" + this.realName + "%' ";
		    }
		    if ((this.moneyQuery != null) && (!"".equals(this.moneyQuery))) {
		      if (this.mark == 1)
		        sql = sql + " and m.availableMoney >=" + this.moneyQuery;
		      else if (this.mark == 2)
		        sql = sql + " and m.frozenMoney >=" + this.moneyQuery;
		      else if (this.mark == 3) {
		        sql = sql + " and m.dueInMoney >=" + this.moneyQuery;
		      }
		    }

		    sql = sql + " order by m.moneyCountId desc ";
		    this.moneycountList = this.moneycountService.findMoneycountBySql(sql);
		    if (this.updown == 1) {
		      this.urlstr = URLDecoder.decode(this.urlstr, "UTF-8");
		      Test.upLoad(this.urlstr, this.moneycountList);
		      ExcelDownload.download(this.request, this.response, this.urlstr, "userNote.xls");
		      this.updown = 2;
		    }
		    return "success";
		  }
  
  
  
  /**
   * 推荐投资奖励列表
   * @return
 * @throws Exception 
   */
  public String recommondTenderList() throws Exception{  

	    String sql = "select * from Tender t,Uservip u,Uservip uv where t.userId=u.userId and u.retereeUserId=uv.userId ";
	    if ((this.userNameQuery != null) && (!"".equals(this.userNameQuery))) {
	      sql = sql + " and  uv.userName like '%" + this.userNameQuery + "%' ";
	    }

	    

	    if (this.startTimeQuery != null) {
	      sql = sql + "  and timestampdiff(DAY,DATE_FORMAT('" + this.startTimeQuery + "','%Y-%m-%d'),DATE_FORMAT(t.tenderTime,'%Y-%m-%d')) >=0";
	    }
	    if (this.endTimeQuery != null) {
	      sql = sql + "  and timestampdiff(DAY,DATE_FORMAT(t.tenderTime,'%Y-%m-%d'),DATE_FORMAT('" + this.endTimeQuery + "','%Y-%m-%d')) >=0";
	    }

	    sql = sql + " order by t.tenderId desc ";
	    List list = this.tenderService.findTendersBylssuingIdPage(sql);
	    this.total = Integer.valueOf(list.size());
	    if (this.page.intValue() == 0) {
	      this.page = Integer.valueOf(1);
	    }
	    if (this.total.intValue() == 0)
	      this.pageCount = Integer.valueOf(1);
	    else if (this.total.intValue() % this.pageSize.intValue() == 0)
	      this.pageCount = Integer.valueOf(this.total.intValue() / this.pageSize.intValue());
	    else {
	      this.pageCount = Integer.valueOf(this.total.intValue() / this.pageSize.intValue() + 1);
	    }
	    sql = sql + " LIMIT " + (this.page.intValue() - 1) * this.pageSize.intValue() + "," + this.pageSize;
	    this.tenderlist = this.tenderService.findTendersBylssuingIdPage(sql);
	    
	    return "success";
	  
  }
  
  public String toMoneyhistorydetail() throws Exception {
    String sql = "select * from Moneyhistorydetail m,Uservip u where m.userId=u.userId ";
    if(this.userId !=null && !"".equals(this.userId)){
    	sql = sql + " and m.userId=" + this.userId;
    }
    if ((this.userNameQuery != null) && (!"".equals(this.userNameQuery))) {
      sql = sql + " and  u.userName like '%" + this.userNameQuery + "%' ";
    }

    if ((this.moneyQuery != null) && (!"".equals(this.moneyQuery))) {
      if (this.mark == 1)
        sql = sql + " and m.affectMoney >" + this.moneyQuery;
      else if (this.mark == 2)
        sql = sql + " and m.affectMoney =" + this.moneyQuery;
      else if (this.mark == 3) {
        sql = sql + " and m.affectMoney <" + this.moneyQuery;
      }
    }

    if (this.startTimeQuery != null) {
      sql = sql + "  and timestampdiff(DAY,DATE_FORMAT('" + this.startTimeQuery + "','%Y-%m-%d'),DATE_FORMAT(m.occurTime,'%Y-%m-%d')) >=0";
    }
    if (this.endTimeQuery != null) {
      sql = sql + "  and timestampdiff(DAY,DATE_FORMAT(m.occurTime,'%Y-%m-%d'),DATE_FORMAT('" + this.endTimeQuery + "','%Y-%m-%d')) >=0";
    }

    sql = sql + " order by m.moneyHistoryDetailId desc ";
    List list = this.moneyhistorydetailService
      .findMoneyhistorydetailBySql(sql);
    this.total = Integer.valueOf(list.size());
    if (this.page.intValue() == 0) {
      this.page = Integer.valueOf(1);
    }
    if (this.total.intValue() == 0)
      this.pageCount = Integer.valueOf(1);
    else if (this.total.intValue() % this.pageSize.intValue() == 0)
      this.pageCount = Integer.valueOf(this.total.intValue() / this.pageSize.intValue());
    else {
      this.pageCount = Integer.valueOf(this.total.intValue() / this.pageSize.intValue() + 1);
    }
    sql = sql + " LIMIT " + (this.page.intValue() - 1) * this.pageSize.intValue() + "," + this.pageSize;
    this.moneyhistorydetailList = this.moneyhistorydetailService.findMoneyhistorydetailBySql(sql);
    //以前的用户正在使用这个地方2015年4月16日 以后的用户不用这个地方了
    if (this.updown == 1) {
      this.urlstr = URLDecoder.decode(this.urlstr, "UTF-8");
      downMoneyhistorydetail(this.urlstr, this.moneyhistorydetailList);
      ExcelDownload.download(this.request, this.response, this.urlstr, "moneyNote.xls");
      this.updown = 2;
    }
    return "success";
  }

  public String toExcel() throws Exception {
	    String sql = "select * from Moneyhistorydetail m,Uservip u where m.userId=u.userId ";
	    if ((this.userNameQuery != null) && (!"".equals(this.userNameQuery))) {
	      sql = sql + " and  u.userName like '%" + this.userNameQuery + "%' ";
	    }

	    if ((this.moneyQuery != null) && (!"".equals(this.moneyQuery))) {
	      if (this.mark == 1)
	        sql = sql + " and m.affectMoney >" + this.moneyQuery;
	      else if (this.mark == 2)
	        sql = sql + " and m.affectMoney =" + this.moneyQuery;
	      else if (this.mark == 3) {
	        sql = sql + " and m.affectMoney <" + this.moneyQuery;
	      }
	    }

	    if (this.startTimeQuery != null) {
	      sql = sql + "  and timestampdiff(DAY,DATE_FORMAT('" + this.startTimeQuery + "','%Y-%m-%d'),DATE_FORMAT(m.occurTime,'%Y-%m-%d')) >=0";
	    }
	    if (this.endTimeQuery != null) {
	      sql = sql + "  and timestampdiff(DAY,DATE_FORMAT(m.occurTime,'%Y-%m-%d'),DATE_FORMAT('" + this.endTimeQuery + "','%Y-%m-%d')) >=0";
	    }

	    sql = sql + " order by m.moneyHistoryDetailId desc ";
	    
	   
	    this.moneyhistorydetailList = this.moneyhistorydetailService.findMoneyhistorydetailBySql(sql);
	    if (this.updown == 1) {
	      this.urlstr = URLDecoder.decode(this.urlstr, "UTF-8");
	      downMoneyhistorydetail(this.urlstr, this.moneyhistorydetailList);
	      ExcelDownload.download(this.request, this.response, this.urlstr, "moneyNote.xls");
	      this.updown = 2;
	    }
	    return "success";
	  }
  
  
  
  public String toMoneyTotal()
    throws Exception
  {
    DecimalFormat df = new DecimalFormat("#.00");

    this.uservipList = this.uservipService.findUservips();
    Uservip uservip = new Uservip();
    if ((this.uservipList != null) && (this.uservipList.size() > 0)) {
      for (int i = 0; i < this.uservipList.size(); i++) {
        uservip = (Uservip)this.uservipList.get(i);
        if ((uservip.getNameResult() != null) && (!"".equals(uservip.getNameResult())) && (Integer.parseInt(uservip.getNameResult()) == 2)) this.num1 += 1;
        if ((uservip.getPhoneResult() != null) && (!"".equals(uservip.getPhoneResult())) && (Integer.parseInt(uservip.getPhoneResult()) == 1)) this.num2 += 1;
        if ((uservip.getSceneResult() != null) && (!"".equals(uservip.getSceneResult())) && (Integer.parseInt(uservip.getSceneResult()) == 1)) this.num3 += 1;
        if ((uservip.getVideoResult() != null) && (!"".equals(uservip.getVideoResult())) && (Integer.parseInt(uservip.getVideoResult()) == 1)) this.num4 += 1;
      }
    }
    Offlinerecharge offlinerecharge = new Offlinerecharge();
    List offlinerechargeList = this.offlinerechargeService.findOfflinerecharges();
    if ((offlinerechargeList != null) && (offlinerechargeList.size() > 0)) {
      for (int i = 0; i < offlinerechargeList.size(); i++) {
        offlinerecharge = (Offlinerecharge)offlinerechargeList.get(i);
        if(offlinerecharge.getRechargeStatus().equals(1)){
        	if (!"线下充值".equals(offlinerecharge.getRecharge())) {
  	          if (offlinerecharge.getRechargeAmount() != null)
  	          {
  	        	  money1 = df.format(new BigDecimal(offlinerecharge.getRechargeAmount().doubleValue()).add(new BigDecimal(money1)));
  	          }
  	        }
  	        else if (offlinerecharge.getRechargeAmount() != null)
  	        {
  	          money2 = df.format(new BigDecimal(offlinerecharge.getRechargeAmount().doubleValue()).add(new BigDecimal(money2)));
  	        }
        }
      }
    }

    List translateList = this.translateService.findTranslates();
    Translate translate = new Translate();
    if ((translateList != null) && (translateList.size() > 0)) {
      for (int i = 0; i < translateList.size(); i++) {
        translate = (Translate)translateList.get(i);
        if (translate.getState().intValue() == 2) {
          this.money3 = df.format(new BigDecimal(translate.getAffectMoney().doubleValue()).add(new BigDecimal(this.money3)));
        }
      }
    }

    this.money4 = df.format(new BigDecimal(this.money1).add(new BigDecimal(this.money2).add(new BigDecimal(this.money3))));
    String sql3 = "select * from Lssuing l where (l.state=3 or l.state=4) ";
    if (this.startTimeQuery != null) {
      sql3 = sql3 + "  and timestampdiff(DAY,DATE_FORMAT('" + this.startTimeQuery + "','%Y-%m-%d'),DATE_FORMAT(l.borrowTime,'%Y-%m-%d')) >=0";
    }
    if (this.endTimeQuery != null) {
      sql3 = sql3 + "  and timestampdiff(DAY,DATE_FORMAT(l.borrowTime,'%Y-%m-%d'),DATE_FORMAT('" + this.endTimeQuery + "','%Y-%m-%d')) >=0";
    }
    this.lssuingList = this.lssuingService.findLssuingsBySearch(sql3);
    if ((this.lssuingList != null) && (this.lssuingList.size() > 0)) {
      for (int i = 0; i < this.lssuingList.size(); i++) {
        Lssuing ls = (Lssuing)this.lssuingList.get(i);
        this.borrow1 = df.format(new BigDecimal(ls.getBorrowMoney()).add(new BigDecimal(this.borrow1)));
        this.borrow2 = df.format(new BigDecimal(ls.getReturnMoney().doubleValue()).add(new BigDecimal(this.borrow2)));
        this.borrow3 = df.format(new BigDecimal(ls.getBorrowMoney()).subtract(new BigDecimal(ls.getReturnMoney().doubleValue())).add(new BigDecimal(this.borrow3)));
      }
    }

    this.moneycountList = this.moneycountService.findMoneycounts();
    if ((this.moneycountList != null) && (this.moneycountList.size() > 0)) {
      for (int i = 0; i < this.moneycountList.size(); i++) {
        this.moneycount = ((Moneycount)this.moneycountList.get(i));
        this.invest1 = df.format(new BigDecimal(this.moneycount.getNetEarnInterest().doubleValue()).add(new BigDecimal(this.moneycount.getPayInterestTotalFee().doubleValue())).add(new BigDecimal(this.invest1)));
        this.invest2 = df.format(new BigDecimal(this.moneycount.getAccuBidReward().doubleValue()).add(new BigDecimal(this.invest2)));
        this.invest3 = df.format(new BigDecimal(this.moneycount.getAccuPromoteReward().doubleValue()).add(new BigDecimal(this.invest3)));
        this.invest4 = df.format(new BigDecimal(this.moneycount.getAccuOfflineRechargeReward().doubleValue()).add(new BigDecimal(this.invest4)));
        this.invest6 = df.format(new BigDecimal(this.moneycount.getCollectTotalMoney()).add(new BigDecimal(this.invest6)));
      }
      this.invest5 = df.format(new BigDecimal(this.invest1).add(new BigDecimal(this.invest2).add(new BigDecimal(this.invest3).add(new BigDecimal(this.invest4)))));
    }
    return "success";
  }
  /**
   * 查找所有的投标记录
   * @return
   */
 public String findRecord() throws Exception{
	 String sql = "select * from tender t,uservip u ,lssuing l where t.userId=u.userId and t.lssuingId=l.lssuingId";
	 if(this.userId!=null && !"".equals(this.userId)){
	    	sql = sql + " and t.userId=" + this.userId;
	    }
	  if ((this.userNameQuery != null) && (!"".equals(this.userNameQuery))) {
	      sql = sql + " and  u.userName like '%" + this.userNameQuery + "%' ";
	    }
      if (this.title!= null && !"".equals(this.title)) {
        sql = sql + " and l.title like '%" + this.title + "%'";
      }
     if (this.startTimeQuery != null) {
      sql = sql + "  and timestampdiff(DAY,DATE_FORMAT('" + this.startTimeQuery + "','%Y-%m-%d'),DATE_FORMAT(t.tenderTime,'%Y-%m-%d')) >=0";
     }
	    sql = sql + " order by t.tenderId  ";
	    List  tender=this.tenderService.findTenderBySql(sql);
	    this.total = Integer.valueOf(tender.size());
	    if (this.page.intValue() == 0) {
	      this.page = Integer.valueOf(1);
	    }
	    if (this.total.intValue() == 0)
	      this.pageCount = Integer.valueOf(1);
	    else if (this.total.intValue() % this.pageSize.intValue() == 0)
	      this.pageCount = Integer.valueOf(this.total.intValue() / this.pageSize.intValue());
	    else {
	      this.pageCount = Integer.valueOf(this.total.intValue() / this.pageSize.intValue() + 1);
	    }
	    sql = sql + " LIMIT " + (this.page.intValue() - 1) * this.pageSize.intValue() + "," + this.pageSize;
	    List recordList = this.tenderService.findTenderBySql(sql);
	    ActionContext.getContext().put("recordList", recordList);
//	    if (this.updown == 1) {
//	        this.urlstr = URLDecoder.decode(this.urlstr, "UTF-8");
//	        Test.upLoad(this.urlstr, this.moneycountList);
//	        ExcelDownload.download(this.request, this.response, this.urlstr, "userNote.xls");
//	        this.updown = 2;
//	      }
	    
	  return "success";
 }

  public List<Lssuing> getLssuingList() {
    return this.lssuingList;
  }

  public void setLssuingList(List<Lssuing> lssuingList) {
    this.lssuingList = lssuingList;
  }

  public String getBorrow1()
  {
    return this.borrow1;
  }

  public void setBorrow1(String borrow1) {
    this.borrow1 = borrow1;
  }

  public String getBorrow2() {
    return this.borrow2;
  }

  public void setBorrow2(String borrow2) {
    this.borrow2 = borrow2;
  }

  public String getBorrow3() {
    return this.borrow3;
  }

  public void setBorrow3(String borrow3) {
    this.borrow3 = borrow3;
  }

  public String getBorrow4() {
    return this.borrow4;
  }

  public void setBorrow4(String borrow4) {
    this.borrow4 = borrow4;
  }

  public String getInvest1() {
    return this.invest1;
  }

  public void setInvest1(String invest1) {
    this.invest1 = invest1;
  }

  public String getInvest2() {
    return this.invest2;
  }

  public void setInvest2(String invest2) {
    this.invest2 = invest2;
  }

  public String getInvest3() {
    return this.invest3;
  }

  public void setInvest3(String invest3) {
    this.invest3 = invest3;
  }

  public String getInvest4() {
    return this.invest4;
  }

  public void setInvest4(String invest4) {
    this.invest4 = invest4;
  }

  public String getInvest5() {
    return this.invest5;
  }

  public void setInvest5(String invest5) {
    this.invest5 = invest5;
  }

  public String getInvest6() {
    return this.invest6;
  }

  public void setInvest6(String invest6) {
    this.invest6 = invest6;
  }

  public String getMoney1() {
    return this.money1;
  }

  public void setMoney1(String money1) {
    this.money1 = money1;
  }

  public String getMoney2()
  {
    return this.money2;
  }

  public void setMoney2(String money2) {
    this.money2 = money2;
  }

  public String getMoney3() {
    return this.money3;
  }

  public void setMoney3(String money3) {
    this.money3 = money3;
  }

  public String getMoney4() {
    return this.money4;
  }

  public void setMoney4(String money4) {
    this.money4 = money4;
  }

  public List<Uservip> getUservipList() {
    return this.uservipList;
  }

  public void setUservipList(List<Uservip> uservipList) {
    this.uservipList = uservipList;
  }

  public int getNum1() {
    return this.num1;
  }

  public void setNum1(int num1) {
    this.num1 = num1;
  }

  public int getNum2() {
    return this.num2;
  }

  public void setNum2(int num2) {
    this.num2 = num2;
  }

  public int getNum3() {
    return this.num3;
  }

  public void setNum3(int num3) {
    this.num3 = num3;
  }

  public int getNum4() {
    return this.num4;
  }

  public void setNum4(int num4) {
    this.num4 = num4;
  }

  public String getUrlstr() {
    return this.urlstr;
  }

  public void setUrlstr(String urlstr)
  {
    this.urlstr = urlstr;
  }

  public int getUpdown()
  {
    return this.updown;
  }

  public void setUpdown(int updown)
  {
    this.updown = updown;
  }

  public String getPicFileName()
  {
    return this.picFileName;
  }

  public void setPicFileName(String picFileName) {
    this.picFileName = picFileName;
  }

  public int getManagerId()
  {
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

  public Integer getUserId() {
	return userId;
}
public void setUserId(Integer userId) {
	this.userId = userId;
}
public String getResult()
  {
    return this.result;
  }
  public void setResult(String result) {
    this.result = result;
  }

  public int getMark() {
    return this.mark;
  }
  public void setMark(int mark) {
    this.mark = mark;
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

  public int getUpdateFlag() {
    return this.updateFlag;
  }

  public void setUpdateFlag(int updateFlag) {
    this.updateFlag = updateFlag;
  }

  public File getImgFile() {
    return this.imgFile;
  }

  public void setImgFile(File imgFile) {
    this.imgFile = imgFile;
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

  public List<Moneycount> getMoneycountList() {
    return this.moneycountList;
  }

  public void setMoneycountList(List<Moneycount> moneycountList) {
    this.moneycountList = moneycountList;
  }

  public Moneycount getMoneycount() {
    return this.moneycount;
  }

  public void setMoneycount(Moneycount moneycount) {
    this.moneycount = moneycount;
  }

  public String getRealName() {
    return this.realName;
  }

  public void setRealName(String realName) {
    this.realName = realName;
  }
  public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}

  public List<Moneyhistorydetail> getMoneyhistorydetailList() {
    return this.moneyhistorydetailList;
  }

  public void setMoneyhistorydetailList(List<Moneyhistorydetail> moneyhistorydetailList)
  {
    this.moneyhistorydetailList = moneyhistorydetailList;
  }

  private static int downMoneyhistorydetail(String str, List<Moneyhistorydetail> moneyhistoryList) throws IOException, RowsExceededException, WriteException
  {
    OutputStream os = new FileOutputStream(str);

    WritableWorkbook workbook = Workbook.createWorkbook(os);
    WritableSheet sheet = workbook.createSheet("TestCreateExcel", 0);

    Label l = null;
    Number n = null;
    DateTime d = null;

    WritableFont headerFont = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLUE);
    WritableCellFormat headerFormat = new WritableCellFormat(headerFont);

    WritableFont titleFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.RED);
    WritableCellFormat titleFormat = new WritableCellFormat(titleFont);

    WritableFont detFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
    WritableCellFormat detFormat = new WritableCellFormat(detFont);

    NumberFormat nf = new NumberFormat("0.00000");
    WritableCellFormat priceFormat = new WritableCellFormat(detFont, nf);

    DateFormat df = new DateFormat("yyyy-MM-dd");
    WritableCellFormat dateFormat = new WritableCellFormat(detFont, df);

    l = new Label(0, 0, "资金变动记录", headerFormat);
    sheet.addCell(l);

    int column = 0;
    sheet.addCell(new Label(column++, 2, "ID", titleFormat));
    sheet.addCell(new Label(column++, 2, "用户名", titleFormat));
    sheet.addCell(new Label(column++, 2, "影响金额", titleFormat));
    sheet.addCell(new Label(column++, 2, "可用余额", titleFormat));
    sheet.addCell(new Label(column++, 2, "冻结金额", titleFormat));
    sheet.addCell(new Label(column++, 2, "待收金额", titleFormat));
    sheet.addCell(new Label(column++, 2, "发生时间", titleFormat));
    sheet.addCell(new Label(column++, 2, "备注", titleFormat));

    for (int i = 0; i < moneyhistoryList.size(); i++) {
      Moneyhistorydetail moneyhistorydetail = (Moneyhistorydetail)moneyhistoryList.get(i);
      column = 0;

      sheet.addCell(new Number(column++, i + 3, moneyhistorydetail.getMoneyHistoryDetailId().intValue(), detFormat));
      sheet.addCell(new Label(column++, i + 3, moneyhistorydetail.getUservip().getUserName(), detFormat));
      sheet.addCell(new Number(column++, i + 3, moneyhistorydetail.getAffectMoney().doubleValue(), detFormat));
      sheet.addCell(new Number(column++, i + 3, moneyhistorydetail.getAvailableBalance().doubleValue(), detFormat));
      sheet.addCell(new Number(column++, i + 3, moneyhistorydetail.getFrozenMoney().doubleValue(), detFormat));
      sheet.addCell(new Number(column++, i + 3, moneyhistorydetail.getCollectMoney().doubleValue(), detFormat));
      sheet.addCell(new DateTime(column++, i + 3, moneyhistorydetail.getOccurTime(), detFormat));
      sheet.addCell(new Label(column++, i + 3, moneyhistorydetail.getIntroduction(), detFormat));
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

    workbook.write();
    os.flush();

    workbook.close();
    os.close();
    return 3;
  }

  public List<Tender> getTenderlist() {
	  return tenderlist;
  }

  public void setTenderlist(List<Tender> tenderlist) {
	  this.tenderlist = tenderlist;
  }
  
  public void setServletResponse(HttpServletResponse response)
  {
    this.response = response;
  }

  public void setServletRequest(HttpServletRequest request)
  {
    this.request = request;
  }
}