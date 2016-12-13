package com.jqg.struts;

import com.jqg.pojo.Lssuing;
import com.jqg.pojo.Tenderwords;
import com.jqg.pojo.Uservip;
import com.jqg.service.LssuingService;
import com.jqg.service.TenderwordsService;
import com.jqg.service.impl.LssuingServiceImpl;
import com.jqg.service.impl.TenderwordsServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

public class TenderWordsAction extends BaseAction
{
  private static final long serialVersionUID = 1L;
  private int currentPage;
  private int pageSize = 3;
  private int totalPage;
  private int current;
  private int total;
  private String lssuingId;
  private String message;
  private String result;
  private List<Lssuing> lssuings = new ArrayList();
  private List<Lssuing> lssuingss = new ArrayList();
  TenderwordsService tenderwordsService = new TenderwordsServiceImpl();
  LssuingService lssuingService = new LssuingServiceImpl();
  Uservip uservip = (Uservip)ActionContext.getContext().getSession().get("uservip");
/**
 * 查找标评论
 * @throws Exception
 */
  public void findTenderWords() throws Exception
  {
    Lssuing lssuing = (Lssuing)ActionContext.getContext().getSession().get("lssuing");
    List tenderwordss = this.tenderwordsService.findTenderwordsByState(lssuing.getLssuingId().intValue());
    int totalRecord = tenderwordss.size();
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

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    List<Tenderwords> tenderspage = this.tenderwordsService.findTenderwordsByStatePage(lssuing.getLssuingId().intValue(), (this.currentPage - 1) * this.pageSize, this.pageSize);

    StringBuffer str = new StringBuffer();
    str.append("{\"totalPage\":\"" + this.totalPage + "\",");
    str.append("\"currentPage\":\"" + this.currentPage + "\",");
    str.append("\"jsonRoots\":[");
    for (Tenderwords tenderwords : tenderspage) {
      str.append("{\"userName\":\"" + tenderwords.getUservip().getUserName() + "\",");
      try{
      str.append("\"time\":\"" + df.format(tenderwords.getTime()) + "\",");
      }catch (Exception e) {
			System.err.println(e);
		}
      str.append("\"message\":\"" + tenderwords.getMessage() + "\"},");
    	
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
 * 标留言添加
 * @return
 * @throws Exception
 */
  public String createTenderWords()
    throws Exception
  {
    Tenderwords tenderwords = new Tenderwords();
    tenderwords.setLssuing(this.lssuingService.findLssuingById(Integer.valueOf(this.lssuingId)));
    tenderwords.setMessage(this.message);
    tenderwords.setUservip(this.uservip);
    tenderwords.setState(0);
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    tenderwords.setTime(timestamp);
    boolean flag = this.tenderwordsService.addTenderwords(tenderwords);
    if (flag)
    {
      this.result = "success";
    }
    else
    {
      this.result = "error";
    }
    return "success";
  }
/**
 * 留言
 * @throws Exception
 */
  public void list() throws Exception {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Uservip uservip = (Uservip)ActionContext.getContext().getSession().get("uservip");

    List lssuings1 = this.lssuingService.findLssuingsByUserId(uservip.getUserId().intValue());
    List tenderwordss = null;
    for (int i = 0; i < lssuings1.size(); i++) {
      tenderwordss = this.tenderwordsService.findTenderwordsByLssuingId(((Lssuing)lssuings1.get(i)).getLssuingId().intValue());
      if ((tenderwordss != null) && (tenderwordss.size() > 0)) {
        this.lssuings.add((Lssuing)lssuings1.get(i));
      }
    }
    Uservip uservip1 = (Uservip)ActionContext.getContext().getSession().get("uservip");

    int totalRecord = this.lssuings.size();
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

    List<Lssuing> lssuingspage = this.lssuingService.findLssuingsByStatePage(uservip1.getUserId().intValue(), (this.currentPage - 1) * this.pageSize, this.pageSize);
    StringBuffer str = new StringBuffer();
    str.append("{\"totalPage\":\"" + this.totalPage + "\",");
    str.append("\"currentPage\":\"" + this.currentPage + "\",");
    str.append("\"jsonRoots\":[");
    for (Lssuing lssuing : lssuingspage) {
      str.append("{\"lssuingId\":\"" + lssuing.getLssuingId() + "\",");
      str.append("\"title\":\"" + lssuing.getTitle() + "\",");
      str.append("\"borrowTime\":\"" + df.format(lssuing.getBorrowTime()) + "\"},");
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
 * 根据编号查询我的留言
 * @throws Exception
 */
  public void message() throws Exception { SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Uservip uservip = (Uservip)ActionContext.getContext().getSession().get("uservip");

    List tenderwords = this.tenderwordsService.findTenderwordsByUserId(uservip.getUserId().intValue());
    int totalRecord = tenderwords.size();
    if (totalRecord % this.pageSize == 0)
      this.total = (totalRecord / this.pageSize);
    else {
      this.total = (totalRecord / this.pageSize + 1);
    }
    if (this.current >= this.total) {
      this.current = this.total;
    }
    if (this.current <= 1) {
      this.current = 1;
    }

    List<Tenderwords> tenderwordspage = this.tenderwordsService.findTenderwordsByPage(uservip.getUserId().intValue(), (this.current - 1) * this.pageSize, this.pageSize);
    StringBuffer str = new StringBuffer();
    str.append("{\"total\":\"" + this.total + "\",");
    str.append("\"current\":\"" + this.current + "\",");
    str.append("\"jsonRoots\":[");
    for (Tenderwords tenderwordss : tenderwordspage) {
      str.append("{\"tenderWordsId\":\"" + tenderwordss.getTenderWordsId() + "\",");
      str.append("\"title\":\"" + tenderwordss.getLssuing().getTitle() + "\",");
      str.append("\"message\":\"" + tenderwordss.getMessage() + "\",");
      str.append("\"state\":\"" + tenderwordss.getState() + "\",");
      str.append("\"time\":\"" + df.format(tenderwordss.getTime()) + "\"},");
    }
    str.deleteCharAt(str.lastIndexOf(","));
    str.append("]}");

    HttpServletResponse response = ServletActionContext.getResponse();
    response.setCharacterEncoding("utf-8");
    try {
      response.getWriter().print(str);
    } catch (IOException e) {
      e.printStackTrace();
    } }

  public String getMessage() {
    return this.message;
  }
  public void setMessage(String message) {
    this.message = message;
  }
  public String getLssuingId() {
    return this.lssuingId;
  }
  public void setLssuingId(String lssuingId) {
    this.lssuingId = lssuingId;
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
  public List<Lssuing> getLssuings() {
    return this.lssuings;
  }
  public void setLssuings(List<Lssuing> lssuings) {
    this.lssuings = lssuings;
  }
  public List<Lssuing> getLssuingss() {
    return this.lssuingss;
  }
  public void setLssuingss(List<Lssuing> lssuingss) {
    this.lssuingss = lssuingss;
  }
  public int getCurrent() {
    return this.current;
  }
  public void setCurrent(int current) {
    this.current = current;
  }
  public int getTotal() {
    return this.total;
  }
  public void setTotal(int total) {
    this.total = total;
  }
}