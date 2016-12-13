package com.jqg.struts;

import com.jqg.pojo.Creditlevel;
import com.jqg.pojo.Integraldetail;
import com.jqg.pojo.Uservip;
import com.jqg.service.CreditlevelService;
import com.jqg.service.IntegraldetailService;
import com.jqg.service.UservipService;
import com.jqg.service.impl.CreditlevelServiceImpl;
import com.jqg.service.impl.IntegraldetailServiceImpl;
import com.jqg.service.impl.UservipServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

public class IntegraldetailAction extends BaseAction
{
  private IntegraldetailService integraldetailService = new IntegraldetailServiceImpl();
  private CreditlevelService creditlevelService = new CreditlevelServiceImpl();
  private UservipService uservipService = new UservipServiceImpl();
  private List<Creditlevel> creditlevels;
  private List<Integraldetail> integraldetails;
  private Uservip uservip;
  private Boolean flag;
  private String result;
  private int currentPage;
  private int pageSize = 3;
  private int totalPage;

  public String createPhone()
    throws Exception
  {
    if (this.flag.booleanValue())
    {
      this.result = "success";
    }
    else
    {
      this.result = "error";
    }
    return "success";
  }
  public String updatePhone() throws Exception {
    if (this.flag.booleanValue())
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
   * 积分明细记录
   * @return
   * @throws Exception
   */
  public String search() throws Exception {
    Uservip user = (Uservip)ActionContext.getContext().getSession().get("uservip");
    this.uservip = this.uservipService.findUservipByUserid(user.getUserId().intValue());

    this.creditlevels = this.creditlevelService.findCreditlevels();
    return "success";
  }
  public void list() {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Uservip uservip = (Uservip)ActionContext.getContext().getSession().get("uservip");
    try {
      this.integraldetails = this.integraldetailService.findIntegraldetailsByUserId(uservip.getUserId().intValue());
    }
    catch (Exception e2) {
      e2.printStackTrace();
    }

    int totalRecord = this.integraldetails.size();
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

    String sql = "SELECT * FROM Integraldetail t WHERE t.userId = " + uservip.getUserId() + 
      " ORDER BY t.integralId DESC LIMIT " + (this.currentPage - 1) * this.pageSize + "," + this.pageSize;
    List<Integraldetail> integraldetailspage = null;
    try {
      integraldetailspage = this.integraldetailService.findIntegraldetailsByUserIdPage(sql);
    }
    catch (Exception e1) {
      e1.printStackTrace();
    }

    StringBuffer str = new StringBuffer();
    str.append("{\"totalPage\":\"" + this.totalPage + "\",");
    str.append("\"currentPage\":\"" + this.currentPage + "\",");
    str.append("\"jsonRoot\":[");
    for (Integraldetail integraldetail : integraldetailspage) {
      str.append("{\"integralId\":\"" + integraldetail.getIntegralId() + "\",");
      str.append("\"remainIntegral\":\"" + integraldetail.getRemainIntegral() + "\",");
      str.append("\"integralQuota\":\"" + integraldetail.getIntegralQuota() + "\",");
      str.append("\"integralTime\":\"" + df.format(integraldetail.getIntegralTime()) + "\",");
      str.append("\"integralReason\":\"" + integraldetail.getIntegralReason() + "\"},");
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

  public Boolean getFlag() { return this.flag; }

  public void setFlag(Boolean flag) {
    this.flag = flag;
  }
  public String getResult() {
    return this.result;
  }
  public void setResult(String result) {
    this.result = result;
  }
  public List<Creditlevel> getCreditlevels() {
    return this.creditlevels;
  }
  public void setCreditlevels(List<Creditlevel> creditlevels) {
    this.creditlevels = creditlevels;
  }
  public Uservip getUservip() {
    return this.uservip;
  }
  public void setUservip(Uservip uservip) {
    this.uservip = uservip;
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
}