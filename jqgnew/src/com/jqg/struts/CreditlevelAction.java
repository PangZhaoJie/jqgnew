package com.jqg.struts;

import com.jqg.pojo.Creditlevel;
import com.jqg.pojo.Investmentlevel;
import com.jqg.pojo.Manager;
import com.jqg.pojo.Oerationlog;
import com.jqg.service.CreditlevelService;
import com.jqg.service.InvestmentlevelService;
import com.jqg.service.OerationlogService;
import com.jqg.service.impl.CreditlevelServiceImpl;
import com.jqg.service.impl.InvestmentlevelServiceImpl;
import com.jqg.service.impl.OerationlogServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.net.InetAddress;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CreditlevelAction extends BaseAction
{
  private CreditlevelService creditlevelService = new CreditlevelServiceImpl();
  private InvestmentlevelService investlevelService = new InvestmentlevelServiceImpl();
  private OerationlogService oerationlogService = new OerationlogServiceImpl();
  private List<Investmentlevel> investmentlevels;
  private List<Creditlevel> creditlevels;
  private String result;
  private Integer creditLevelId;
  private String creditLevelName;
  private String creditLevelStart;
  private String creditLevelEnd;
  private String creditLevelPicture;
  private String open;
  private Integer investmentLevelId;
  private String investmentLevelName;
  private String investmentLevelStart;
  private String investmentLevelEnd;
  private String investmentLevelPicture;
  private String open1;
/**
 * 信用级别管理
 * @return
 * @throws Exception
 */
  public String searchClevel()
    throws Exception
  {
    this.creditlevels = this.creditlevelService.findCreditlevels();
    return "success";
  }
  public String createClevel() throws Exception {
    if (this.creditLevelId != null) {
      Creditlevel cus = this.creditlevelService.findCreditlevelBycreditLevelId(this.creditLevelId.intValue());
      cus.setCreditLevelEnd(this.creditLevelEnd);
      cus.setCreditLevelName(this.creditLevelName);
      cus.setCreditLevelStart(this.creditLevelStart);
      cus.setCreditLevelPicture(this.creditLevelPicture);
      this.creditlevelService.updateCreditlevel(cus);
      this.creditLevelEnd = null;
      this.creditLevelName = null;
      this.creditLevelStart = null;
      this.creditLevelPicture = null;
      this.creditLevelId = null;

      InetAddress in = InetAddress.getLocalHost();
      Manager manager = (Manager)ActionContext.getContext().getSession().get("manager");
      Oerationlog oerationlog = new Oerationlog();
      oerationlog.setManager(manager);
      oerationlog.setOerationCategory("saveCreditlevel");
      oerationlog.setOerationChangeTime(new Timestamp(new Date().getTime()));
      oerationlog.setOerationRemaks("保存信用级别");
      oerationlog.setOrationIp(in.getHostAddress().toString());
      this.oerationlogService.createOerationlog(oerationlog);
    } else {
      Creditlevel cus = new Creditlevel();
      cus.setCreditLevelEnd(this.creditLevelEnd);
      cus.setCreditLevelName(this.creditLevelName);
      cus.setCreditLevelStart(this.creditLevelStart);
      cus.setCreditLevelPicture(this.creditLevelPicture);
      this.creditlevelService.createCreditlevel(cus);

      InetAddress in = InetAddress.getLocalHost();
      Manager manager = (Manager)ActionContext.getContext().getSession().get("manager");
      Oerationlog oerationlog = new Oerationlog();
      oerationlog.setManager(manager);
      oerationlog.setOerationCategory("addCreditlevel");
      oerationlog.setOerationChangeTime(new Timestamp(new Date().getTime()));
      oerationlog.setOerationRemaks("新增信用级别");
      oerationlog.setOrationIp(in.getHostAddress().toString());
      this.oerationlogService.createOerationlog(oerationlog);
    }
    searchClevel();
    return "success";
  }
  public String delClevel() throws Exception {
    Creditlevel cus = this.creditlevelService.findCreditlevelBycreditLevelId(this.creditLevelId.intValue());
    this.creditlevelService.deleteCreditlevel(cus);
    this.creditLevelId = null;

    InetAddress in = InetAddress.getLocalHost();
    Manager manager = (Manager)ActionContext.getContext().getSession().get("manager");
    Oerationlog oerationlog = new Oerationlog();
    oerationlog.setManager(manager);
    oerationlog.setOerationCategory("delCreditlevel");
    oerationlog.setOerationChangeTime(new Timestamp(new Date().getTime()));
    oerationlog.setOerationRemaks("删除信用级别");
    oerationlog.setOrationIp(in.getHostAddress().toString());
    this.oerationlogService.createOerationlog(oerationlog);
    searchClevel();
    return "success";
  }
  public String editClevel() throws Exception {
    Creditlevel cus = this.creditlevelService.findCreditlevelBycreditLevelId(this.creditLevelId.intValue());
    this.creditLevelId = cus.getCreditLevelId();
    this.creditLevelStart = cus.getCreditLevelStart();
    this.creditLevelEnd = cus.getCreditLevelEnd();
    this.creditLevelName = cus.getCreditLevelName();
    this.creditLevelPicture = cus.getCreditLevelPicture();
    searchClevel();
    this.open = "1";
    return "success";
  }
/**
 * 投资级别管理 
 * @return
 * @throws Exception
 */
  public String searchIlevel() throws Exception {
    this.investmentlevels = this.investlevelService.findInvestmentlevels();
    return "success";
  }
  public String createIlevel() throws Exception {
    if (this.investmentLevelId != null) {
      Investmentlevel cus = this.investlevelService.findInvestmentlevelByinvestmentLevelId(this.investmentLevelId.intValue());
      cus.setInvestmentLevelEnd(this.investmentLevelEnd);
      cus.setInvestmentLevelStart(this.investmentLevelStart);
      cus.setInvestmentLevelName(this.investmentLevelName);
      cus.setInvestmentLevelPicture(this.investmentLevelPicture);
      this.investlevelService.updateInvestmentlevel(cus);
      this.investmentLevelId = null;
      this.investmentLevelEnd = null;
      this.investmentLevelStart = null;
      this.investmentLevelName = null;
      this.investmentLevelPicture = null;

      InetAddress in = InetAddress.getLocalHost();
      Manager manager = (Manager)ActionContext.getContext().getSession().get("manager");
      Oerationlog oerationlog = new Oerationlog();
      oerationlog.setManager(manager);
      oerationlog.setOerationCategory("saveInvestmentlevel");
      oerationlog.setOerationChangeTime(new Timestamp(new Date().getTime()));
      oerationlog.setOerationRemaks("保存投资级别");
      oerationlog.setOrationIp(in.getHostAddress().toString());
      this.oerationlogService.createOerationlog(oerationlog);
    } else {
      Investmentlevel cus = new Investmentlevel();
      cus.setInvestmentLevelEnd(this.investmentLevelEnd);
      cus.setInvestmentLevelStart(this.investmentLevelStart);
      cus.setInvestmentLevelName(this.investmentLevelName);
      cus.setInvestmentLevelPicture(this.investmentLevelPicture);
      this.investlevelService.createInvestmentlevel(cus);

      InetAddress in = InetAddress.getLocalHost();
      Manager manager = (Manager)ActionContext.getContext().getSession().get("manager");
      Oerationlog oerationlog = new Oerationlog();
      oerationlog.setManager(manager);
      oerationlog.setOerationCategory("addInvestmentlevel");
      oerationlog.setOerationChangeTime(new Timestamp(new Date().getTime()));
      oerationlog.setOerationRemaks("新增投资级别");
      oerationlog.setOrationIp(in.getHostAddress().toString());
      this.oerationlogService.createOerationlog(oerationlog);
    }
    searchIlevel();
    return "success";
  }
  public String delIlevel() throws Exception {
    Investmentlevel cus = this.investlevelService.findInvestmentlevelByinvestmentLevelId(this.investmentLevelId.intValue());
    this.investlevelService.deleteInvestmentlevel(cus);
    this.investmentLevelId = null;

    InetAddress in = InetAddress.getLocalHost();
    Manager manager = (Manager)ActionContext.getContext().getSession().get("manager");
    Oerationlog oerationlog = new Oerationlog();
    oerationlog.setManager(manager);
    oerationlog.setOerationCategory("delInvestmentlevel");
    oerationlog.setOerationChangeTime(new Timestamp(new Date().getTime()));
    oerationlog.setOerationRemaks("删除投资级别");
    oerationlog.setOrationIp(in.getHostAddress().toString());
    this.oerationlogService.createOerationlog(oerationlog);
    searchIlevel();
    return "success";
  }
  public String editIlevel() throws Exception {
    Investmentlevel cus = this.investlevelService.findInvestmentlevelByinvestmentLevelId(this.investmentLevelId.intValue());
    this.investmentLevelId = cus.getInvestmentLevelId();
    this.investmentLevelEnd = cus.getInvestmentLevelEnd();
    this.investmentLevelStart = cus.getInvestmentLevelStart();
    this.investmentLevelPicture = cus.getInvestmentLevelPicture();
    this.investmentLevelName = cus.getInvestmentLevelName();
    searchIlevel();
    this.open1 = "1";
    return "success";
  }
  public List<Creditlevel> getCreditlevels() {
    return this.creditlevels;
  }
  public void setCreditlevels(List<Creditlevel> creditlevels) {
    this.creditlevels = creditlevels;
  }
  public String getResult() {
    return this.result;
  }
  public void setResult(String result) {
    this.result = result;
  }
  public Integer getCreditLevelId() {
    return this.creditLevelId;
  }
  public void setCreditLevelId(Integer creditLevelId) {
    this.creditLevelId = creditLevelId;
  }
  public String getCreditLevelName() {
    return this.creditLevelName;
  }
  public void setCreditLevelName(String creditLevelName) {
    this.creditLevelName = creditLevelName;
  }
  public String getCreditLevelStart() {
    return this.creditLevelStart;
  }
  public void setCreditLevelStart(String creditLevelStart) {
    this.creditLevelStart = creditLevelStart;
  }
  public String getCreditLevelEnd() {
    return this.creditLevelEnd;
  }
  public void setCreditLevelEnd(String creditLevelEnd) {
    this.creditLevelEnd = creditLevelEnd;
  }
  public String getCreditLevelPicture() {
    return this.creditLevelPicture;
  }
  public void setCreditLevelPicture(String creditLevelPicture) {
    this.creditLevelPicture = creditLevelPicture;
  }
  public String getOpen() {
    return this.open;
  }
  public void setOpen(String open) {
    this.open = open;
  }
  public List<Investmentlevel> getInvestmentlevels() {
    return this.investmentlevels;
  }
  public void setInvestmentlevels(List<Investmentlevel> investmentlevels) {
    this.investmentlevels = investmentlevels;
  }
  public Integer getInvestmentLevelId() {
    return this.investmentLevelId;
  }
  public void setInvestmentLevelId(Integer investmentLevelId) {
    this.investmentLevelId = investmentLevelId;
  }
  public String getInvestmentLevelName() {
    return this.investmentLevelName;
  }
  public void setInvestmentLevelName(String investmentLevelName) {
    this.investmentLevelName = investmentLevelName;
  }
  public String getInvestmentLevelStart() {
    return this.investmentLevelStart;
  }
  public void setInvestmentLevelStart(String investmentLevelStart) {
    this.investmentLevelStart = investmentLevelStart;
  }
  public String getInvestmentLevelEnd() {
    return this.investmentLevelEnd;
  }
  public void setInvestmentLevelEnd(String investmentLevelEnd) {
    this.investmentLevelEnd = investmentLevelEnd;
  }
  public String getInvestmentLevelPicture() {
    return this.investmentLevelPicture;
  }
  public void setInvestmentLevelPicture(String investmentLevelPicture) {
    this.investmentLevelPicture = investmentLevelPicture;
  }
  public String getOpen1() {
    return this.open1;
  }
  public void setOpen1(String open1) {
    this.open1 = open1;
  }
}