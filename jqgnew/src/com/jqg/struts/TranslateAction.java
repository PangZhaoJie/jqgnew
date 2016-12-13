package com.jqg.struts;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.text.DecimalFormat;
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
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.jqg.pojo.Basicinfor;
import com.jqg.pojo.Inbox;
import com.jqg.pojo.Manager;
import com.jqg.pojo.Messagemodel;
import com.jqg.pojo.Moneycount;
import com.jqg.pojo.Moneyhistorydetail;
import com.jqg.pojo.Oerationlog;
import com.jqg.pojo.Phone;
import com.jqg.pojo.Record;
import com.jqg.pojo.SmsSendLog;
import com.jqg.pojo.Translate;
import com.jqg.pojo.Uservip;
import com.jqg.pojo.Website;
import com.jqg.service.BasicinforService;
import com.jqg.service.InboxService;
import com.jqg.service.MessagemodelService;
import com.jqg.service.MoneycountService;
import com.jqg.service.MoneyhistorydetailService;
import com.jqg.service.OerationlogService;
import com.jqg.service.PhoneService;
import com.jqg.service.RecordService;
import com.jqg.service.SmsSendLogService;
import com.jqg.service.TenderService;
import com.jqg.service.TranslateService;
import com.jqg.service.UservipService;
import com.jqg.service.WebsiteService;
import com.jqg.service.impl.BasicinforServiceImpl;
import com.jqg.service.impl.InboxServiceImpl;
import com.jqg.service.impl.MessagemodelServiceImpl;
import com.jqg.service.impl.MoneycountServiceImpl;
import com.jqg.service.impl.MoneyhistorydetailServiceImpl;
import com.jqg.service.impl.OerationlogServiceImpl;
import com.jqg.service.impl.PhoneServiceImpl;
import com.jqg.service.impl.RecordServiceImpl;
import com.jqg.service.impl.SmsSendLogServiceImpl;
import com.jqg.service.impl.TenderServiceImpl;
import com.jqg.service.impl.TranslateServiceImpl;
import com.jqg.service.impl.UservipServiceImpl;
import com.jqg.service.impl.WebsiteServiceImpl;
import com.jqg.util.Client;
import com.jqg.util.ExcelDownload;
import com.jqg.util.LoanUtils;
import com.opensymphony.xwork2.ActionContext;
import com.ruanwei.interfacej.SmsClientSend;

public class TranslateAction extends BaseAction
  implements ServletRequestAware, ServletResponseAware
{
  private Integer page = Integer.valueOf(0);
  private Integer pageSize = Integer.valueOf(10);
  private Integer pageCount;
  private String result;
  private String flag;
  private TranslateService translateService = new TranslateServiceImpl();
  private MoneycountService moneycountService = new MoneycountServiceImpl();
  private MoneyhistorydetailService moneyhistorydetailService = new MoneyhistorydetailServiceImpl();
  private WebsiteService websiteService = new WebsiteServiceImpl();
  private OerationlogService oerationlogService = new OerationlogServiceImpl();
  private TenderService tenderService = new TenderServiceImpl();
  private UservipService uservipService = new UservipServiceImpl();
  private InboxService inboxService = new InboxServiceImpl();
  private RecordService recordService=new RecordServiceImpl();
  private PhoneService phoneService = new PhoneServiceImpl();
  private MessagemodelService messagemodelService = new MessagemodelServiceImpl();
  private List<Translate> translates;
  private String userName;
  private String startTime;
  private String endTime;
  private String lessMoney;
  private String mostMoney;
  private String state;
  private String state1;
  private String dealUser;
  private String mark;
  private Integer total;
  private String ok;
  private Integer translateId;
  private String texplain;
  private Integer userId;
  private Integer updown = Integer.valueOf(3);
  private String urlstr;
  private HttpServletRequest request;
  private HttpServletResponse response;
  private String postform;
/**
 * 待审核提现
 * @return
 * @throws Exception
 */
  public String translateList()
    throws Exception
  {
    Translate tran = null;
    Uservip uservip = null;
	uservip = (Uservip)ActionContext.getContext().getSession().get("uservip");

    if (this.ok != null) {
    	System.out.println("ok");
        tran = this.translateService.findTranslateById(this.translateId);
        Manager mana = (Manager)ActionContext.getContext().getSession().get("manager");
        tran.setDealUser(mana.getManagerName());
        tran.setDealTime(new Timestamp(new Date().getTime()));
        String comment = URLDecoder.decode(this.texplain, "UTF-8");
        tran.setTexplain(comment.trim());
        this.translateService.updateTranslate(tran);

        InetAddress in1 = InetAddress.getLocalHost();
        Oerationlog oerationlog1 = new Oerationlog();
        oerationlog1.setManager(mana);
        oerationlog1.setOerationChangeTime(new Timestamp(new Date().getTime()));
        oerationlog1.setOrationIp(in1.getHostAddress().toString());
        
        
        String auditType = "";
        if ("1".equals(this.state1.trim())){
      	  auditType = "5";
        }else if ("3".equals(this.state1.trim())) {
      	  auditType = "6";
        } 
        LoanUtils loanUtils = new LoanUtils();
        Website website = this.websiteService.findWebsiteBywebsiteId(1);
        HttpServletRequest request = ServletActionContext.getRequest();
        this.postform = loanUtils.withdrawsAudit(tran.getSerialnum(), website, auditType, request, tran, mana, oerationlog1);
        return "loanjump";
    }
    String sql = "";
    sql = sql + "select distinct o.* from Translate o,Uservip u  where  o.userId=u.userId and o.state=0";
    if ((this.mark != null) && (!"".equals(this.mark.trim()))) {
      sql = "";
      sql = sql + "select distinct o.* from Translate o,Uservip u  where  o.userId=u.userId and 1=1";
      if ((this.userName != null) && (!"".equals(this.userName.trim()))) {
        sql = sql + " and u.userName like '%" + this.userName + "%'";
      }
      if ((this.dealUser != null) && (!"".equals(this.dealUser.trim()))) {
        sql = sql + " and o.dealUser like '%" + this.dealUser + "%'";
      }
      if ((this.state != null) && (!"-请选择-".equals(this.state)) && (!"".equals(this.state))) {
        sql = sql + " and o.state=" + Integer.parseInt(this.state);
      }
      if ((this.startTime != null) && (!"".equals(this.startTime))) {
        sql = sql + " and o.occurTime>='" + this.startTime + "'";
      }
      if ((this.endTime != null) && (!"".equals(this.endTime))) {
        sql = sql + " and o.occurTime<='" + this.endTime + " 24:00:00'";
      }
      if ((this.lessMoney != null) && (!"".equals(this.lessMoney))) {
        sql = sql + " and o.affectMoney>=" + Double.parseDouble(this.lessMoney);
      }
      if ((this.mostMoney != null) && (!"".equals(this.mostMoney))) {
        sql = sql + " and o.affectMoney<=" + Double.parseDouble(this.mostMoney);
      }
    }
    sql += "   and serialnum<>''";
    List translate = this.translateService.findTranslateBySql(sql);
    this.total = Integer.valueOf(translate.size());
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
    sql = sql + " order by o.translateId desc LIMIT " + 
      (this.page.intValue() - 1) * this.pageSize.intValue() + "," + this.pageSize;
    this.translates = this.translateService.findTranslateBySql(sql);
    System.out.println("sqp=" + sql);
    return "success";
  }
  /**
   * 审核通过，处理中
   * @return
   * @throws Exception
   */
  public String translateList2() throws Exception {
    Translate tran = null;

    if (this.ok != null) {
        tran = this.translateService.findTranslateById(this.translateId);
        Manager mana = (Manager)ActionContext.getContext().getSession().get("manager");
        tran.setDealUser(mana.getManagerName());
        tran.setDealTime(new Timestamp(new Date().getTime()));
        String comment = URLDecoder.decode(this.texplain, "UTF-8");
        tran.setTexplain(comment.trim());
//        tran.setState(Integer.valueOf(Integer.parseInt(this.state1)));

        Oerationlog oerationlog1 = new Oerationlog();
        oerationlog1.setManager(mana);
        oerationlog1.setOerationChangeTime(new Timestamp(new Date().getTime()));
        InetAddress in1 = InetAddress.getLocalHost();
        oerationlog1.setOrationIp(in1.getHostAddress().toString());
        String auditType = "";
        if ("2".equals(this.state1.trim())){
      	  auditType = "5";
        }else if ("3".equals(this.state1.trim())) {
      	  auditType = "6";
        } 
        LoanUtils loanUtils = new LoanUtils();
        Website website = this.websiteService.findWebsiteBywebsiteId(1);
        HttpServletRequest request = ServletActionContext.getRequest();
        this.postform = loanUtils.withdrawsAudit(tran.getSerialnum(), website, auditType, request, tran, mana, oerationlog1);
        return "loanjump";
      }
    String sql = "";
    sql = sql + "select distinct o.* from Translate o,Uservip u  where  o.userId=u.userId and o.state=1";
    if ((this.mark != null) && (!"".equals(this.mark.trim()))) {
      sql = "";
      sql = sql + "select distinct o.* from Translate o,Uservip u  where  o.userId=u.userId and 1=1";
      if ((this.userName != null) && (!"".equals(this.userName.trim()))) {
        sql = sql + " and u.userName like '%" + this.userName + "%'";
      }
      if ((this.dealUser != null) && (!"".equals(this.dealUser.trim()))) {
        sql = sql + " and o.dealUser like '%" + this.dealUser + "%'";
      }
      if ((this.state != null) && (!"-请选择-".equals(this.state)) && (!"".equals(this.state))) {
        sql = sql + " and o.state=" + Integer.parseInt(this.state);
      }
      if ((this.startTime != null) && (!"".equals(this.startTime))) {
        sql = sql + " and o.occurTime>='" + this.startTime + "'";
      }
      if ((this.endTime != null) && (!"".equals(this.endTime))) {
        sql = sql + " and o.occurTime<='" + this.endTime + " 24:00:00'";
      }
      if ((this.lessMoney != null) && (!"".equals(this.lessMoney))) {
        sql = sql + " and o.affectMoney>=" + Double.parseDouble(this.lessMoney);
      }
      if ((this.mostMoney != null) && (!"".equals(this.mostMoney))) {
        sql = sql + " and o.affectMoney<=" + Double.parseDouble(this.mostMoney);
      }
    }
    sql += "  and serialnum<>''";
    List translate = this.translateService.findTranslateBySql(sql);
    this.total = Integer.valueOf(translate.size());
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
    sql = sql + " order by o.translateId desc LIMIT " + 
      (this.page.intValue() - 1) * this.pageSize.intValue() + "," + this.pageSize;
    this.translates = this.translateService.findTranslateBySql(sql);
    return "success";
  }
  /**
   * 已提现
   * @return
   * @throws Exception
   */
  public String translateList3() throws Exception {
    String sql = "";
    sql = sql + "select distinct o.* from Translate o,Uservip u  where  o.userId=u.userId and o.state=2";
    if ((this.mark != null) && (!"".equals(this.mark.trim()))) {
      sql = "";
      sql = sql + "select distinct o.* from Translate o,Uservip u  where  o.userId=u.userId and 1=1";
      if ((this.userName != null) && (!"".equals(this.userName.trim()))) {
        sql = sql + " and u.userName like '%" + this.userName + "%'";
      }
      if ((this.dealUser != null) && (!"".equals(this.dealUser.trim()))) {
        sql = sql + " and o.dealUser like '%" + this.dealUser + "%'";
      }
      if ((this.state != null) && (!"-请选择-".equals(this.state)) && (!"".equals(this.state))) {
        sql = sql + " and o.state=" + Integer.parseInt(this.state);
      }
      if ((this.startTime != null) && (!"".equals(this.startTime))) {
        sql = sql + " and o.occurTime>='" + this.startTime + "'";
      }
      if ((this.endTime != null) && (!"".equals(this.endTime))) {
        sql = sql + " and o.occurTime<='" + this.endTime + " 24:00:00'";
      }
      if ((this.lessMoney != null) && (!"".equals(this.lessMoney))) {
        sql = sql + " and o.affectMoney>=" + Double.parseDouble(this.lessMoney);
      }
      if ((this.mostMoney != null) && (!"".equals(this.mostMoney))) {
        sql = sql + " and o.affectMoney<=" + Double.parseDouble(this.mostMoney);
      }
    }
    sql += "  and serialnum<>''";
    List translate = this.translateService.findTranslateBySql(sql);
    this.total = Integer.valueOf(translate.size());
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
    sql = sql + " order by o.translateId desc LIMIT " + 
      (this.page.intValue() - 1) * this.pageSize.intValue() + "," + this.pageSize;
    this.translates = this.translateService.findTranslateBySql(sql);
    return "success";
  }
  /**
   * 审核未通过
   * @return
   * @throws Exception
   */
  public String translateList4() throws Exception {
    String sql = "";
    sql = sql + "select distinct o.* from Translate o,Uservip u  where  o.userId=u.userId and o.state=3";
    if ((this.mark != null) && (!"".equals(this.mark.trim()))) {
      sql = "";
      sql = sql + "select distinct o.* from Translate o,Uservip u  where  o.userId=u.userId and 1=1";
      if ((this.userName != null) && (!"".equals(this.userName.trim())) && (this.userName.equals("null"))) {
        sql = sql + " and u.userName like '%" + this.userName + "%'";
      }
      if ((this.dealUser != null) && (!"".equals(this.dealUser.trim()))) {
        sql = sql + " and o.dealUser like '%" + this.dealUser + "%'";
      }
      if ((this.state != null) && (!"-请选择-".equals(this.state)) && (!"".equals(this.state))) {
        sql = sql + " and o.state=" + Integer.parseInt(this.state);
      }
      if ((this.startTime != null) && (!"".equals(this.startTime))) {
        sql = sql + " and o.occurTime>='" + this.startTime + "'";
      }
      if ((this.endTime != null) && (!"".equals(this.endTime))) {
        sql = sql + " and o.occurTime<='" + this.endTime + " 24:00:00'";
      }
      if ((this.lessMoney != null) && (!"".equals(this.lessMoney))) {
        sql = sql + " and o.affectMoney>=" + Double.parseDouble(this.lessMoney);
      }
      if ((this.mostMoney != null) && (!"".equals(this.mostMoney))) {
        sql = sql + " and o.affectMoney<=" + Double.parseDouble(this.mostMoney);
      }
    }
    sql += "   and serialnum<>''";
    List translate = this.translateService.findTranslateBySql(sql);
    this.total = Integer.valueOf(translate.size());
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
    sql = sql + " order by o.translateId desc LIMIT " + 
      (this.page.intValue() - 1) * this.pageSize.intValue() + "," + this.pageSize;
    this.translates = this.translateService.findTranslateBySql(sql);
    return "success";
  }
  /**
   * 提现申请总列表
   * @return
   * @throws Exception
   */
  public String translateList5() throws Exception {
    String sql = "";
    sql = sql + "select distinct o.* from Translate o,Uservip u  where  o.userId=u.userId and 1=1";
    if(this.userId!=null && !"".equals(this.userId)){
    	sql = sql + " and o.userId=" + this.userId;
    }
    if ((this.mark != null) && (!"".equals(this.mark.trim()))) {
      sql = "";
      sql = sql + "select distinct o.* from Translate o,Uservip u  where  o.userId=u.userId and 1=1";
      if ((this.userName != null) && (!"".equals(this.userName.trim()))) {
        sql = sql + " and u.userName like '%" + this.userName + "%'";
      }
      if ((this.dealUser != null) && (!"".equals(this.dealUser.trim()))) {
        sql = sql + " and o.dealUser like '%" + this.dealUser + "%'";
      }
      if ((this.state != null) && (!"-请选择-".equals(this.state)) && (!"".equals(this.state))) {
        sql = sql + " and o.state=" + Integer.parseInt(this.state);
      }
      if ((this.startTime != null) && (!"".equals(this.startTime))) {
        sql = sql + " and o.occurTime>='" + this.startTime + "'";
      }
      if ((this.endTime != null) && (!"".equals(this.endTime))) {
        sql = sql + " and o.occurTime<='" + this.endTime + " 24:00:00'";
      }
      if ((this.lessMoney != null) && (!"".equals(this.lessMoney))) {
        sql = sql + " and o.affectMoney>=" + Double.parseDouble(this.lessMoney);
      }
      if ((this.mostMoney != null) && (!"".equals(this.mostMoney))) {
        sql = sql + " and o.affectMoney<=" + Double.parseDouble(this.mostMoney);
      }
    }
    sql += " and  serialnum<>''  ";
    List translate = this.translateService.findTranslateBySql(sql);
    this.total = Integer.valueOf(translate.size());
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
    sql = sql + " order by o.translateId desc LIMIT " + 
      (this.page.intValue() - 1) * this.pageSize.intValue() + "," + this.pageSize;
    this.translates = this.translateService.findTranslateBySql(sql);
    if (this.updown.intValue() == 1)
    {
      String urlstr1 = URLDecoder.decode(this.urlstr, "UTF-8");
      upLoad(urlstr1, this.translates);
      ExcelDownload.download(this.request, this.response, urlstr1, "userTranslate.xls");
      this.updown = Integer.valueOf(2);
    }
    return "success";
  }
  /**
   * 导出提现申请总列表
   * @return
   * @throws Exception
   */
  public String upLoadTranslate5() throws Exception {
    String sql = "";
    sql = sql + "select distinct o.* from Translate o,Uservip u  where  o.userId=u.userId and 1=1";
    if ((this.mark != null) && (!"".equals(this.mark.trim()))) {
      sql = "";
      sql = sql + "select distinct o.* from Translate o,Uservip u  where  o.userId=u.userId and 1=1";
      if ((this.userName != null) && (!"".equals(this.userName.trim()))) {
        sql = sql + " and u.userName like '%" + this.userName + "%'";
      }
      if ((this.dealUser != null) && (!"".equals(this.dealUser.trim()))) {
        sql = sql + " and o.dealUser like '%" + this.dealUser + "%'";
      }
      if ((this.state != null) && (!"-请选择-".equals(this.state)) && (!"".equals(this.state))) {
        sql = sql + " and o.state=" + Integer.parseInt(this.state);
      }
      if ((this.startTime != null) && (!"".equals(this.startTime))) {
        sql = sql + " and o.occurTime>='" + this.startTime + "'";
      }
      if ((this.endTime != null) && (!"".equals(this.endTime))) {
        sql = sql + " and o.occurTime<='" + this.endTime + " 24:00:00'";
      }
      if ((this.lessMoney != null) && (!"".equals(this.lessMoney))) {
        sql = sql + " and o.affectMoney>=" + Double.parseDouble(this.lessMoney);
      }
      if ((this.mostMoney != null) && (!"".equals(this.mostMoney))) {
        sql = sql + " and o.affectMoney<=" + Double.parseDouble(this.mostMoney);
      }
    }
    sql = sql + " order by o.translateId desc " ;
    this.translates = this.translateService.findTranslateBySql(sql);
    if (this.updown.intValue() == 1)
    {
      String urlstr1 = URLDecoder.decode(this.urlstr, "UTF-8");
      upLoad(urlstr1, this.translates);
      ExcelDownload.download(this.request, this.response, urlstr1, "userTranslate.xls");
      this.updown = Integer.valueOf(2);
    }
    return "success";
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
  public String getResult() {
    return this.result;
  }
  public void setResult(String result) {
    this.result = result;
  }
  public String getFlag() {
    return this.flag;
  }
  public void setFlag(String flag) {
    this.flag = flag;
  }
  public List<Translate> getTranslates() {
    return this.translates;
  }
  public void setTranslates(List<Translate> translates) {
    this.translates = translates;
  }
  public String getUserName() {
    return this.userName;
  }
  public void setUserName(String userName) {
    this.userName = userName;
  }
  public String getStartTime() {
    return this.startTime;
  }
  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }
  public String getEndTime() {
    return this.endTime;
  }
  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }
  public String getLessMoney() {
    return this.lessMoney;
  }
  public void setLessMoney(String lessMoney) {
    this.lessMoney = lessMoney;
  }
  public String getMostMoney() {
    return this.mostMoney;
  }
  public void setMostMoney(String mostMoney) {
    this.mostMoney = mostMoney;
  }
  public String getState() {
    return this.state;
  }
  public void setState(String state) {
    this.state = state;
  }
  public String getDealUser() {
    return this.dealUser;
  }
  public void setDealUser(String dealUser) {
    this.dealUser = dealUser;
  }
  public String getMark() {
    return this.mark;
  }
  public void setMark(String mark) {
    this.mark = mark;
  }
  public Integer getTotal() {
    return this.total;
  }
  public void setTotal(Integer total) {
    this.total = total;
  }
  public String getState1() {
    return this.state1;
  }
  public void setState1(String state1) {
    this.state1 = state1;
  }
  public String getOk() {
    return this.ok;
  }
  public void setOk(String ok) {
    this.ok = ok;
  }
  public Integer getTranslateId() {
    return this.translateId;
  }
  public void setTranslateId(Integer translateId) {
    this.translateId = translateId;
  }
  public String getTexplain() {
    return this.texplain;
  }
  public void setTexplain(String texplain) {
    this.texplain = texplain;
  }
  public Integer getUserId() {
    return this.userId;
  }
  public void setUserId(Integer userId) {
    this.userId = userId;
  }
  public Integer getUpdown() {
    return this.updown;
  }
  public void setUpdown(Integer updown) {
    this.updown = updown;
  }
  public String getUrlstr() {
    return this.urlstr;
  }
  public void setUrlstr(String urlstr) {
    this.urlstr = urlstr;
  }

  public int upLoad(String str, List<Translate> tranList) throws IOException, RowsExceededException, WriteException {
    OutputStream os = new FileOutputStream(str);

    WritableWorkbook workbook = Workbook.createWorkbook(os);
    WritableSheet sheet = workbook.createSheet("CreateExcelInfor", 0);

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

    l = new Label(0, 0, "提现信息", headerFormat);
    sheet.addCell(l);

    int column = 0;
    sheet.addCell(new Label(column++, 2, "ID", titleFormat));
    sheet.addCell(new Label(column++, 2, "会员名", titleFormat));
    sheet.addCell(new Label(column++, 2, "真实姓名", titleFormat));
    sheet.addCell(new Label(column++, 2, "提现金额", titleFormat));
    sheet.addCell(new Label(column++, 2, "提现手续费", titleFormat));
    sheet.addCell(new Label(column++, 2, "应到帐金额", titleFormat));
    sheet.addCell(new Label(column++, 2, "提现时间", titleFormat));
    sheet.addCell(new Label(column++, 2, "提现状态", titleFormat));
    sheet.addCell(new Label(column++, 2, "处理人", titleFormat));
    sheet.addCell(new Label(column++, 2, "处理时间", titleFormat));
    sheet.addCell(new Label(column++, 2, "处理说明", titleFormat));
    for (int i = 0; i < tranList.size(); i++) {
      Translate tran = (Translate)tranList.get(i);
      column = 0;

      sheet.addCell(new Number(column++, i + 3, tran.getTranslateId().intValue(), detFormat));
      sheet.addCell(new Label(column++, i + 3, tran.getUservip().getUserName(), detFormat));
      sheet.addCell(new Label(column++, i + 3, tran.getUservip().getRealName(), detFormat));
      sheet.addCell(new Number(column++, i + 3, tran.getAffectMoney().doubleValue(), detFormat));
      Double fee = Double.valueOf(0.0D);
      if (tran.getFee() != null) {
        fee = tran.getFee();
      }
      sheet.addCell(new Number(column++, i + 3, fee.doubleValue(), detFormat));
      Double mount = Double.valueOf(tran.getAffectMoney().doubleValue() - fee.doubleValue());
      sheet.addCell(new Number(column++, i + 3, mount.doubleValue(), detFormat));
      sheet.addCell(new DateTime(column++, i + 3, tran.getOccurTime(), dateFormat));
      int state = tran.getState().intValue();
      String strs = "待审核";
      if (state == 1)
        strs = "审核通过，处理中";
      else if (state == 2)
        strs = "已提现";
      else if (state == 3) {
        strs = "审核未通过";
      }
      sheet.addCell(new Label(column++, i + 3, strs, detFormat));
      sheet.addCell(new Label(column++, i + 3, tran.getDealUser(), detFormat));
      if(tran.getDealTime()!=null){
          sheet.addCell(new DateTime(column++, i + 3, tran.getDealTime(), dateFormat));
      }else{
          sheet.addCell(new Label(column++, i + 3, "", detFormat));
      }
      sheet.addCell(new Label(column++, i + 3, tran.getTexplain(), detFormat));
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
    return 3;
  }

  public void setServletResponse(HttpServletResponse response)
  {
    this.response = response;
  }

  public void setServletRequest(HttpServletRequest request)
  {
    this.request = request;
  }
public String getPostform() {
	return postform;
}
public void setPostform(String postform) {
	this.postform = postform;
}
  
  
}