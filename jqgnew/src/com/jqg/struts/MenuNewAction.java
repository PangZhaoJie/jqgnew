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
import com.jqg.pojo.Manager;
import com.jqg.pojo.Oerationlog;
import com.jqg.service.FrontmenuService;
import com.jqg.service.LatestnewsService;
import com.jqg.service.OerationlogService;
import com.jqg.service.impl.FrontmenuServiceImpl;
import com.jqg.service.impl.LatestnewsServiceImpl;
import com.jqg.service.impl.OerationlogServiceImpl;
import com.opensymphony.xwork2.ActionContext;

public class MenuNewAction extends BaseAction
{
  private String newsId;
  private String title;
  private String importWord;
  private String synopsis;
  private String photo;
  private String content;
  private String publishTime;
  private String frontMenuId;
  private static final long serialVersionUID = 1L;
  private int currentPage;
  private int pageSize = 10;
  private int totalPage;
  private String ip;
  private String result;
  private String menuname;
  private String isDisplay;
  private String url;
  private Timestamp startTimeQuery;
  private Timestamp endTimeQuery;
  private String userNameQuery;
  Timestamp timestamp = new Timestamp(System.currentTimeMillis());
  Manager manager = (Manager)ActionContext.getContext().getSession().get("manager");
  LatestnewsService latestnewsService = new LatestnewsServiceImpl();
  FrontmenuService frontmenuService = new FrontmenuServiceImpl();
  OerationlogService oerationlogService = new OerationlogServiceImpl();
  List<Frontmenu> frontmenus = new ArrayList<Frontmenu>();
  List<Frontmenu> frontmenus1 = new ArrayList<Frontmenu>();
  
  /**
	 * 查找菜单
	 * 
	 * @return
	 */
	public String findMenu() throws Exception {
		frontmenus1 = frontmenuService.findFrontmenuByIdANdId(0, 50);
		frontmenus = this.frontmenuService.findFrontmenus();
		return "success";
	}
/**
 *  更新菜单
 * @return
 * @throws Exception
 */
  public String updateMenu() throws Exception {
	  Frontmenu frontmenu = this.frontmenuService.findFrontmenuById(Integer
				.parseInt(this.frontMenuId.trim()));
		frontmenu.setName(this.menuname);
		frontmenu.setIsDisplay(Integer.parseInt(this.isDisplay));
		boolean flag = this.frontmenuService.updateFrontmenu(frontmenu);
		if (flag) {
			manager = (Manager) ActionContext.getContext().getSession()
					.get("manager");
			Oerationlog oerationlog = new Oerationlog();
			oerationlog.setManager(manager);
			oerationlog.setOerationCategory("updateMenu");
			oerationlog.setOerationChangeTime(this.timestamp);
			oerationlog.setOerationRemaks("更新菜单");
			oerationlog.setOrationIp(this.ip);
			boolean flag1 = this.oerationlogService
					.createOerationlog(oerationlog);
		}
		return "success";
  }
/**
 * 查找文章传至更新页面
 * @return
 * @throws Exception
 */
  public String findnew()
    throws Exception
  {
    List frontmenustonew = this.frontmenuService.findFrontmenuByisColumn();
    ActionContext.getContext().getSession().put("frontmenustonew", frontmenustonew);
    Latestnews latestnews = this.latestnewsService.findLatestnewsByLatestnewsId(Integer.valueOf(this.newsId));
    ActionContext.getContext().getSession().put("newtoupdate", latestnews);
    return "success";
  }
/**
 * 添加文章
 * @return
 * @throws Exception
 */
  public String addnew() throws Exception {
    Latestnews latestnews = new Latestnews();
//    System.out.println(this.frontMenuId);
//    System.out.println(this.ip);
//    System.out.println(this.importWord); 
//    System.out.println(this.synopsis);
//     System.err.println(this.url);
    Frontmenu frontmenu = this.frontmenuService.findFrontmenuById(Integer.valueOf(this.frontMenuId));
    String content1 = this.content.replace("%26", "&");
    //如果是幻灯片
    if(Integer.valueOf(this.frontMenuId)==46){
    	  latestnews.setTitle(this.title);
    	  
    	  latestnews.setUrl(this.url);
    	  latestnews.setFrontmenu(frontmenu);
    	  if ((this.photo==null) || ("".equals(this.photo)))
 	     {
 	       latestnews.setPhoto("0");
 	     }
 	    else
 	    {
 	      latestnews.setPhoto("/images/new/" + this.photo);
 	    }
    	 latestnews.setPublish(this.manager.getManagerName());
 	    latestnews.setPublishTime(this.timestamp);
    	
    }else{
    //不是幻灯片
	    latestnews.setContent(content1);
	    latestnews.setSynopsis(this.synopsis);
	    latestnews.setFrontmenu(frontmenu);
	    
	   
	    latestnews.setImportWord(this.importWord);
	    latestnews.setPhoto("0");
	
	    latestnews.setPublish(this.manager.getManagerName());
	    latestnews.setPublishTime(this.timestamp);
	    latestnews.setTitle(this.title);
  }
    boolean flag = this.latestnewsService.addLatestnews(latestnews);

    if (flag)
    {
      Oerationlog oerationlog = new Oerationlog();
      oerationlog.setManager(this.manager);
      oerationlog.setOerationCategory("addnew");
      oerationlog.setOerationChangeTime(this.timestamp);
      oerationlog.setOerationRemaks("添加文章");
      oerationlog.setOrationIp(this.ip);
      boolean flag1 = this.oerationlogService.createOerationlog(oerationlog);

      this.result = "success";
    } else {
      this.result = "error";
    }
    return "success";
  }
/**
 * 更新文章
 * @return
 * @throws Exception
 */
  public String updatenew() throws Exception
  {
	  
	  
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
    Latestnews latestnews = this.latestnewsService.findLatestnewsByLatestnewsId(Integer.valueOf(this.newsId));
    Frontmenu frontmenu = this.frontmenuService.findFrontmenuById(Integer.valueOf(this.frontMenuId));

    String content1 = this.content.replace("%26", "&");

    latestnews.setContent(content1);
    latestnews.setFrontmenu(frontmenu);
    latestnews.setImportWord(this.importWord);
    latestnews.setUrl(this.url);
    if ("0".equals(this.photo))
    {
      latestnews.setPhoto("0");
    }
    else if (this.photo.contains("/images/new/")) {
      latestnews.setPhoto(this.photo);
    }
    else
    {
      latestnews.setPhoto("/images/new/" + this.photo);
    }
    latestnews.setPublishTime(this.timestamp);
    latestnews.setSynopsis(this.synopsis);
    latestnews.setTitle(this.title);
    boolean flag = this.latestnewsService.updateLatestnews(latestnews);
    if (flag)
    {
      Oerationlog oerationlog = new Oerationlog();
      oerationlog.setManager(this.manager);
      oerationlog.setOerationCategory("updatenew");
      oerationlog.setOerationChangeTime(this.timestamp);
      oerationlog.setOrationIp(ip);
      oerationlog.setOerationRemaks("更新文章");

      boolean flag1 = this.oerationlogService.createOerationlog(oerationlog);
      this.result = "success";
    }
    else {
      this.result = "error";
    }
    return "success";
  }
/**
 * 删除文章
 * @return
 * @throws Exception
 */
  public String deletenew() throws Exception {
    Latestnews latestnews = this.latestnewsService.findLatestnewsByLatestnewsId(Integer.valueOf(this.newsId));
    boolean flag = this.latestnewsService.deleteLatestnews(latestnews);
    if (flag)
    {
      Oerationlog oerationlog = new Oerationlog();
      oerationlog.setManager(this.manager);
      oerationlog.setOerationCategory("deletenew");
      oerationlog.setOerationChangeTime(this.timestamp);
      oerationlog.setOerationRemaks("删除文章");
      oerationlog.setOrationIp(this.ip);
      boolean flag1 = this.oerationlogService.createOerationlog(oerationlog);
      return "success";
    }
    return "error";
  }
/**
 * 查找所有能发文章的菜单传至添加文章页面
 * @return
 * @throws Exception
 */
  public String findmenus() throws Exception
  {
    List frontmenustonew = this.frontmenuService.findFrontmenuByisColumn();
    ActionContext.getContext().getSession().put("frontmenustonew", frontmenustonew);
    return "success";
  }
/**
 * 查找所有文章并分页
 * @throws Exception
 */
  public void findnewsBymenu() throws Exception
  {
    List latestnews = this.latestnewsService.findLatestnewssByfrontMenuId(Integer.valueOf(this.frontMenuId).intValue());
    int totalRecord = latestnews.size();
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
    List<Latestnews> latestnewspage = this.latestnewsService.findLatestnewssByfrontMenuIdPage(Integer.valueOf(this.frontMenuId).intValue(), (this.currentPage - 1) * this.pageSize, this.pageSize);
    if(latestnewspage.size()!=0){
    StringBuffer str = new StringBuffer();
    str.append("{\"totalPage\":\"" + this.totalPage + "\",");
    str.append("\"currentPage\":\"" + this.currentPage + "\",");
    str.append("\"jsonRoot\":[");
    for (Latestnews latestnew : latestnewspage)
    {
      str.append("{\"id\":\"" + latestnew.getNewsId() + "\",");
      str.append("\"name\":\"" + latestnew.getFrontmenu().getName() + "\",");
      str.append("\"title\":\"" + latestnew.getTitle() + "\",");
      str.append("\"publish\":\"" + latestnew.getPublish() + "\",");
      str.append("\"time\":\"" + latestnew.getPublishTime() + "\"},");
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
    }else{
    	StringBuffer str = new StringBuffer();
        str.append("{\"totalPage\":\"" + this.totalPage + "\"}");
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("utf-8");
        try
        {
          response.getWriter().print(str);
        } catch (IOException e) {
          e.printStackTrace();
        }
    }
  }

  public void findnews()
    throws Exception
  {
//	  String username = new String(this.userNameQuery.getBytes("ISO-8859-1"),"UTF-8"); //本地需要转码
	  String username =this.userNameQuery;//服务器不需要转码就可以使用
	  String sql = "select * from Latestnews l where 1=1";
	  if(username != null && !"".equals(username)){
		  sql = sql + " and l.title like '%" +username +"%'";
	  }
	  if(this.startTimeQuery != null){
		  sql = sql + "  and timestampdiff(DAY,DATE_FORMAT('"
					+ this.startTimeQuery
					+ "','%Y-%m-%d'),DATE_FORMAT(l.publishTime,'%Y-%m-%d')) >=0";
	  }
	  if(this.endTimeQuery != null){
		  sql = sql+ " and timestampdiff(DAY,DATE_FORMAT(l.publishTime,'%Y-%m-%d'),DATE_FORMAT('"
					+ this.endTimeQuery + "','%Y-%m-%d')) >=0";
	  }  
	  
    List latestnews = this.latestnewsService.findLatestnewss(sql);
    int totalRecord = latestnews.size();
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
    sql = sql + " order by l.publishTime desc ";
    sql = sql + " limit " + (this.currentPage - 1) * this.pageSize + "," + this.pageSize;
    List<Latestnews> latestnewspage = this.latestnewsService.findLatestnewss(sql);

    StringBuffer str = new StringBuffer();
    str.append("{\"totalPage\":\"" + this.totalPage + "\",");
    str.append("\"currentPage\":\"" + this.currentPage + "\",");
    str.append("\"jsonRoot\":[");
    for (Latestnews latestnew : latestnewspage)
    {
      str.append("{\"id\":\"" + latestnew.getNewsId() + "\",");
      str.append("\"name\":\"" + latestnew.getFrontmenu().getName() + "\",");
      str.append("\"title\":\"" + latestnew.getTitle() + "\",");
      str.append("\"publish\":\"" + latestnew.getPublish() + "\",");
      str.append("\"time\":\"" + latestnew.getPublishTime() + "\"},");
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

  public String getNewsId()
  {
    return this.newsId;
  }
  public void setNewsId(String newsId) {
    this.newsId = newsId;
  }
  public String getTitle() {
    return this.title;
  }
  public void setTitle(String title) {
    this.title = title;
  }

  public String getImportWord() {
    return this.importWord;
  }
  public void setImportWord(String importWord) {
    this.importWord = importWord;
  }
  public String getSynopsis() {
    return this.synopsis;
  }
  public void setSynopsis(String synopsis) {
    this.synopsis = synopsis;
  }
  public String getPhoto() {
    return this.photo;
  }
  public void setPhoto(String photo) {
    this.photo = photo;
  }
  public String getContent() {
    return this.content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public String getPublishTime() {
    return this.publishTime;
  }
  public void setPublishTime(String publishTime) {
    this.publishTime = publishTime;
  }
  public String getFrontMenuId() {
    return this.frontMenuId;
  }
  public void setFrontMenuId(String frontMenuId) {
    this.frontMenuId = frontMenuId;
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
  public String getResult() {
    return this.result;
  }
  public void setResult(String result) {
    this.result = result;
  }
  public String getIp() {
    return this.ip;
  }
  public void setIp(String ip) {
    this.ip = ip;
  }
  public String getMenuname() {
    return this.menuname;
  }
  public void setMenuname(String menuname) {
    this.menuname = menuname;
  }
  public String getIsDisplay() {
    return this.isDisplay;
  }
  public void setIsDisplay(String isDisplay) {
    this.isDisplay = isDisplay;
  }
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}
public List<Frontmenu> getFrontmenus() {
	return frontmenus;
}
public void setFrontmenus(List<Frontmenu> frontmenus) {
	this.frontmenus = frontmenus;
}
public List<Frontmenu> getFrontmenus1() {
	return frontmenus1;
}
public void setFrontmenus1(List<Frontmenu> frontmenus1) {
	this.frontmenus1 = frontmenus1;
}
public Timestamp getStartTimeQuery() {
	return startTimeQuery;
}
public void setStartTimeQuery(Timestamp startTimeQuery) {
	this.startTimeQuery = startTimeQuery;
}
public Timestamp getEndTimeQuery() {
	return endTimeQuery;
}
public void setEndTimeQuery(Timestamp endTimeQuery) {
	this.endTimeQuery = endTimeQuery;
}
public String getUserNameQuery() {
	return userNameQuery;
}
public void setUserNameQuery(String userNameQuery) {
	this.userNameQuery = userNameQuery;
}
  
}