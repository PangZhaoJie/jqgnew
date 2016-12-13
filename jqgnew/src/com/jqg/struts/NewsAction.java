package com.jqg.struts;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.jqg.pojo.Frontmenu;
import com.jqg.pojo.Latestnews;
import com.jqg.pojo.Lssuingherald;
import com.jqg.pojo.Manager;
import com.jqg.pojo.Oerationlog;
import com.jqg.pojo.Periodtime;
import com.jqg.pojo.Returnway;
import com.jqg.service.FrontmenuService;
import com.jqg.service.LatestnewsService;
import com.jqg.service.LssuingheraldService;
import com.jqg.service.OerationlogService;
import com.jqg.service.PeriodtimeService;
import com.jqg.service.ReturnwayService;
import com.jqg.service.impl.FrontmenuServiceImpl;
import com.jqg.service.impl.LatestnewsServiceImpl;
import com.jqg.service.impl.LssuingheraldServiceImpl;
import com.jqg.service.impl.OerationlogServiceImpl;
import com.jqg.service.impl.PeriodtimeServiceImpl;
import com.jqg.service.impl.ReturnwayServiceImpl;
import com.opensymphony.xwork2.ActionContext;
/**
 * 文章
 * @author Administrator
 *
 */
public class NewsAction extends BaseAction
{
  private String ip;
  private static final long serialVersionUID = 1L;
  private int currentPage;
  private int pageSize = 10;
  private int totalPage;
  private int newsId;
  private String flag;
  private String result;
  private Lssuingherald lssuingherald;
  private Integer lssuingHeraldId;
  private int periodtimeId;
  private int returnwayId;
  private String awaed;
  private String money;
  private String rate;
  private String heraldTime;
  
  private String time;
  private String lssuingName;
  private List<Periodtime> periodtimes;
  private List<Returnway> returnways;
  FrontmenuService frontmenuService = new FrontmenuServiceImpl();
  LatestnewsService latestnewsService = new LatestnewsServiceImpl();
  LssuingheraldService lssuingheraldService = new LssuingheraldServiceImpl();
  PeriodtimeService periodtimeService = new PeriodtimeServiceImpl();
  ReturnwayService returnwayService = new ReturnwayServiceImpl();
  Timestamp timestamp = new Timestamp(System.currentTimeMillis());
  Manager manager = (Manager)ActionContext.getContext().getSession().get("manager");
  OerationlogService oerationlogService = new OerationlogServiceImpl();
/**
 * 查找首页新标预告
 * @return
 * @throws Exception
 */
  public String findlssh() throws Exception {
    this.lssuingherald = this.lssuingheraldService.findLssuingheraldByLssuingheraldId(1);
    this.periodtimes = this.periodtimeService.findPeriodtimes();
    this.returnways = this.returnwayService.findReturnways();
    return "success";
  }
/**
 * 更新首页新标预告
 * @return
 * @throws Exception
 */
  public String uptlssh() throws Exception {
	  
	 HttpServletRequest request = ServletActionContext.getRequest();
	 String ip = request.getHeader("X-Forwarded-For"); 
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
          ip = request.getHeader("Proxy-Client-IP"); 
      } 
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
          ip = request.getHeader("WL-Proxy-Client-IP"); 
      } 
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
          ip = request.getHeader("HTTP_CLIENT_IP"); 
      } 
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
          ip = request.getHeader("HTTP_X_FORWARDED_FOR"); 
      } 
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
          ip = request.getRemoteAddr(); 
      }  
	 
    this.lssuingherald = this.lssuingheraldService.findLssuingheraldByLssuingheraldId(1);
    this.lssuingherald.setAwaed(Double.valueOf(this.awaed));
    this.lssuingherald.setIsShow(Integer.valueOf(1));
    this.lssuingherald.setHeraldTime(Timestamp.valueOf(this.heraldTime));
    this.lssuingherald.setLssuingName(this.lssuingName);
    this.lssuingherald.setMoney(this.money);
    this.lssuingherald.setTime(this.time);
    this.lssuingherald.setPeriodtime(this.periodtimeService.findPeriodtimeByPeriodtimeId(this.periodtimeId));
    this.lssuingherald.setRate(Double.valueOf(this.rate));
    this.lssuingherald.setReturnway(this.returnwayService.findReturnwayById(Integer.valueOf(this.returnwayId)));
    boolean flag = this.lssuingheraldService.updateLssuingherald(this.lssuingherald);
    Oerationlog oerationlog = new Oerationlog();
    oerationlog.setManager(this.manager);
    oerationlog.setOerationCategory("uptlssh");
    oerationlog.setOerationChangeTime(this.timestamp);
    oerationlog.setOerationRemaks("更新首页新标预告");
    oerationlog.setOrationIp(ip);
    boolean flag1 = this.oerationlogService.createOerationlog(oerationlog);
    this.periodtimes = this.periodtimeService.findPeriodtimes();
    this.returnways = this.returnwayService.findReturnways();
    return "success";
  }
/**
 * 文章分类查找
 * @return
 * @throws Exception
 */
  public String indexnew() throws Exception {
    Frontmenu frontmenu = this.frontmenuService.findFrontmenuByCode(this.flag);
    Frontmenu frontmenu2 = this.frontmenuService.findFrontmenuById(frontmenu.getFatherId());
    List<Frontmenu> frontmenus =  new ArrayList<Frontmenu>();
    if(frontmenu.getFatherId().intValue()==0){
    }else{
    	frontmenus = this.frontmenuService.findFrontmenusBy(frontmenu.getFatherId().intValue());
    }
   
    ActionContext.getContext().put("frontmenufather", frontmenu2);
    ActionContext.getContext().put("frontmenu", frontmenu);
    ActionContext.getContext().put("frontmenus", frontmenus);
    if (frontmenu.getNature().intValue() == 1)
    {
      List latestnewss = this.latestnewsService.findLatestnewssByfrontMenuId(frontmenu.getFrontMenuId().intValue());
      ActionContext.getContext().put("latestnews", latestnewss);
      if("provision".equals(flag)){
      	return "provision";
      }else{
      return "error";
    }
    }
    return "success";
  }
/**
 * 文章分页
 * @throws Exception
 */
  public void indexpage() throws Exception {
    Frontmenu frontmenu = this.frontmenuService.findFrontmenuByCode(this.flag);
    List latestnewss = this.latestnewsService.findLatestnewssByfrontMenuId(frontmenu.getFrontMenuId().intValue());
    List<Latestnews> latestnewss2 = this.latestnewsService.findLatestnewssByfrontMenuIdPage(frontmenu.getFrontMenuId().intValue(), (this.currentPage - 1) * this.pageSize, this.pageSize);
    int totalRecord = latestnewss.size();
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
    for (Latestnews latestnews : latestnewss2) {
      String time = (String)latestnews.getPublishTime().toString().subSequence(0, 10);
      str.append("{\"newsId\":\"" + latestnews.getNewsId() + "\",");
      str.append("\"title\":\"" + latestnews.getTitle() + "\",");
      str.append("\"time\":\"" + time + "\"},");
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
 * 文章详细
 * @return
 * @throws Exception
 */
  public String newdetail() throws Exception
  {
    Latestnews latestnews = this.latestnewsService.findLatestnewsByLatestnewsId(Integer.valueOf(this.newsId));
    Frontmenu frontmenu = this.frontmenuService.findFrontmenuById(latestnews.getFrontmenu().getFrontMenuId());
    Frontmenu frontmenu2 = this.frontmenuService.findFrontmenuById(frontmenu.getFatherId());
    List frontmenus = this.frontmenuService.findFrontmenusBy(frontmenu.getFatherId().intValue());
    Latestnews latestnewsOnPage = this.latestnewsService.findLatestnewsByonPage(Integer.valueOf(this.newsId), frontmenu.getFrontMenuId().intValue());
    Latestnews latestnewsDownPage = this.latestnewsService.findLatestnewsBydownPage(Integer.valueOf(this.newsId), frontmenu.getFrontMenuId().intValue());
    ActionContext.getContext().put("latestnewsDownPage", latestnewsDownPage);
    ActionContext.getContext().put("latestnewsOnPage", latestnewsOnPage);
    ActionContext.getContext().put("frontmenufather", frontmenu2);
    ActionContext.getContext().put("latestnewsdetail", latestnews);
    ActionContext.getContext().put("frontmenus", frontmenus);
    return "success";
  }

  public String cooperation() throws Exception
  {
    List frontmenus = this.frontmenuService.findFrontmenusBy(23);
    Frontmenu frontmenu;
    if (this.flag.equals("warrants"))
    {
      frontmenu = this.frontmenuService.findFrontmenuByCode("warrant");
    }
    else
    {
      frontmenu = this.frontmenuService.findFrontmenuByCode(this.flag);
    }
    List latestnewss = this.latestnewsService.findLatestnewssByfrontMenuId(frontmenu.getFrontMenuId().intValue());

    ActionContext.getContext().put("frontmenu", frontmenu);
    ActionContext.getContext().put("frontmenus", frontmenus);
    ActionContext.getContext().put("latestnewss", latestnewss);

    return "success";
  }
  public String getFlag() {
    return this.flag;
  }
  public void setFlag(String flag) {
    this.flag = flag;
  }
  public String getResult() {
    return this.result;
  }
  public void setResult(String result) {
    this.result = result;
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
  public int getNewsId() {
    return this.newsId;
  }
  public void setNewsId(int newsId) {
    this.newsId = newsId;
  }
  public Integer getLssuingHeraldId() {
    return this.lssuingHeraldId;
  }
  public void setLssuingHeraldId(Integer lssuingHeraldId) {
    this.lssuingHeraldId = lssuingHeraldId;
  }
  public int getReturnwayId() {
    return this.returnwayId;
  }
  public void setReturnwayId(int returnwayId) {
    this.returnwayId = returnwayId;
  }
  public String getAwaed() {
    return this.awaed;
  }
  public void setAwaed(String awaed) {
    this.awaed = awaed;
  }
  public String getMoney() {
    return this.money;
  }
  public void setMoney(String money) {
    this.money = money;
  }
  public String getRate() {
    return this.rate;
  }
  public void setRate(String rate) {
    this.rate = rate;
  }
  public String getHeraldTime() {
    return this.heraldTime;
  }
  public void setHeraldTime(String heraldTime) {
    this.heraldTime = heraldTime;
  }
  public String getLssuingName() {
    return this.lssuingName;
  }
  public void setLssuingName(String lssuingName) {
    this.lssuingName = lssuingName;
  }
  public Lssuingherald getLssuingherald() {
    return this.lssuingherald;
  }
  public void setLssuingherald(Lssuingherald lssuingherald) {
    this.lssuingherald = lssuingherald;
  }
  public int getPeriodtimeId() {
    return this.periodtimeId;
  }
  public void setPeriodtimeId(int periodtimeId) {
    this.periodtimeId = periodtimeId;
  }
  public String getIp() {
    return this.ip;
  }
  public void setIp(String ip) {
    this.ip = ip;
  }
  public List<Periodtime> getPeriodtimes() {
    return this.periodtimes;
  }
  public void setPeriodtimes(List<Periodtime> periodtimes) {
    this.periodtimes = periodtimes;
  }
  public List<Returnway> getReturnways() {
    return this.returnways;
  }
  public void setReturnways(List<Returnway> returnways) {
    this.returnways = returnways;
  }
public String getTime() {
	return time;
}
public void setTime(String time) {
	this.time = time;
}
  
}