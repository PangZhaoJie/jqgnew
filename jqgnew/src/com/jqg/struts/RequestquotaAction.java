package com.jqg.struts;

import com.jqg.pojo.Requestquota;
import com.jqg.pojo.Uservip;
import com.jqg.service.RequestquotaService;
import com.jqg.service.UservipService;
import com.jqg.service.impl.RequestquotaServiceImpl;
import com.jqg.service.impl.UservipServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

public class RequestquotaAction extends BaseAction
{
  private UservipService uservipService = new UservipServiceImpl();
  private RequestquotaService requestquotaService = new RequestquotaServiceImpl();
  private Uservip uservip;
  private Boolean flag;
  private String result;
  private int currentPage;
  private int pageSize = 3;
  private int totalPage;
  private Integer requestQuotaId;
  private Integer requestQuota;
  private Integer examine;
  private Integer throughQuota;
  private String opinion;
  private Timestamp submitTime;
  private String applyType;
  private String applyExplain;
  private List<Requestquota> requestquotas;
/**
 *  额度申请
 * @return
 * @throws Exception
 */
  public String create()
    throws Exception
  {
    Requestquota requestquota = new Requestquota();
    requestquota.setApplyType("借款信用额度");
    requestquota.setRequestQuota(this.requestQuota);
    requestquota.setApplyExplain(this.applyExplain);
    requestquota.setExamine(Integer.valueOf(0));
    requestquota.setSubmitTime(new Timestamp(new Date().getTime()));
    Uservip uservip1 = (Uservip)ActionContext.getContext().getSession().get("uservip");
    requestquota.setUservip(uservip1);
    this.requestquotaService.createRequestquota(requestquota);
    return "success";
  }
  public String search() throws Exception {
    return "success";
  }
  /**
   * 根据编号查询申请
   */
  public void list() {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Uservip uservip = (Uservip)ActionContext.getContext().getSession().get("uservip");
    try {
      this.requestquotas = this.requestquotaService.findRequestquotasByUserId(uservip.getUserId().intValue());
    }
    catch (Exception e2) {
      e2.printStackTrace();
    }

    int totalRecord = this.requestquotas.size();
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

    String sql = "SELECT * FROM Requestquota t WHERE t.userId = " + uservip.getUserId() + 
      " ORDER BY t.requestQuotaId DESC LIMIT " + (this.currentPage - 1) * this.pageSize + "," + this.pageSize;
    List<Requestquota> requestquotaspage = null;
    try {
      requestquotaspage = this.requestquotaService.findRequestquotasByUserIdPage(sql);
    }
    catch (Exception e1) {
      e1.printStackTrace();
    }

    StringBuffer str = new StringBuffer();
    str.append("{\"totalPage\":\"" + this.totalPage + "\",");
    str.append("\"currentPage\":\"" + this.currentPage + "\",");
    str.append("\"jsonRoot\":[");
    for (Requestquota requestquota : requestquotaspage) {
      str.append("{\"requestQuotaId\":\"" + requestquota.getRequestQuotaId() + "\",");
      str.append("\"requestQuota\":\"" + requestquota.getRequestQuota() + "\",");
      str.append("\"examine\":\"" + requestquota.getExamine() + "\",");
      str.append("\"throughQuota\":\"" + requestquota.getThroughQuota() + "\",");
      str.append("\"opinion\":\"" + requestquota.getOpinion() + "\",");
      str.append("\"submitTime\":\"" + df.format(requestquota.getSubmitTime()) + "\",");
      str.append("\"applyType\":\"" + requestquota.getApplyType() + "\",");
      str.append("\"applyExplain\":\"" + requestquota.getApplyExplain() + "\"},");
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
  public Integer getRequestQuotaId() {
    return this.requestQuotaId;
  }
  public void setRequestQuotaId(Integer requestQuotaId) {
    this.requestQuotaId = requestQuotaId;
  }
  public Integer getExamine() {
    return this.examine;
  }
  public void setExamine(Integer examine) {
    this.examine = examine;
  }
  public Integer getThroughQuota() {
    return this.throughQuota;
  }
  public void setThroughQuota(Integer throughQuota) {
    this.throughQuota = throughQuota;
  }
  public String getOpinion() {
    return this.opinion;
  }
  public void setOpinion(String opinion) {
    this.opinion = opinion;
  }
  public Timestamp getSubmitTime() {
    return this.submitTime;
  }
  public void setSubmitTime(Timestamp submitTime) {
    this.submitTime = submitTime;
  }
  public String getApplyType() {
    return this.applyType;
  }
  public void setApplyType(String applyType) {
    this.applyType = applyType;
  }
  public String getApplyExplain() {
    return this.applyExplain;
  }
  public void setApplyExplain(String applyExplain) {
    this.applyExplain = applyExplain;
  }
  public Integer getRequestQuota() {
    return this.requestQuota;
  }
  public void setRequestQuota(Integer requestQuota) {
    this.requestQuota = requestQuota;
  }
}