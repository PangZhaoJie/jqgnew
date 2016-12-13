package com.jqg.struts;

import com.jqg.pojo.Eventtype;
import com.jqg.pojo.Moneycount;
import com.jqg.pojo.Moneyhistorydetail;
import com.jqg.pojo.Uservip;
import com.jqg.service.EventtypeService;
import com.jqg.service.MoneycountService;
import com.jqg.service.MoneyhistorydetailService;
import com.jqg.service.impl.EventtypeServiceImpl;
import com.jqg.service.impl.MoneycountServiceImpl;
import com.jqg.service.impl.MoneyhistorydetailServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.write.DateTime;

import org.apache.struts2.ServletActionContext;

public class MoneyCountAction extends BaseAction
{
  private Integer userId;
  private Integer eventTypeId;
  private Moneycount moneycount;
  private List<Moneyhistorydetail> moneyhistorydetailList;
  private List<Eventtype> eventtypeList;
  private MoneycountService moneycountService = new MoneycountServiceImpl();
  private MoneyhistorydetailService moneyhistorydetailService = new MoneyhistorydetailServiceImpl();
  private EventtypeService eventtypeService = new EventtypeServiceImpl();
  private Integer page = Integer.valueOf(0);
  private Integer pageSize = Integer.valueOf(10);
  private Integer pageCount;
/**
 * 资金统计
 * @return
 * @throws Exception
 */
  public String toAccount()
    throws Exception
  {
	if(this.userId==null){
		Uservip uservip = (Uservip)ActionContext.getContext().getSession().get("uservip");
		this.userId = uservip.getUserId();
	}
    this.moneycount = this.moneycountService.findMoneycountByuserId(this.userId.intValue());
    return "success";
  }
  
  
  
  
  /**
 *  资金历史记录 
 * @return
 * @throws Exception
 */
  public String toAccountHistory() throws Exception
  {
	HttpServletRequest request = ServletActionContext.getRequest();
	String startTime = request.getParameter("");
	String endTime = request.getParameter("");
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String sql = "select * from Moneyhistorydetail m where m.userId=" +this.userId;
	if(startTime!=null && !"".equals(startTime) && endTime!=null && !"".equals(endTime)){
		sql = sql + " and m.occurTime <="+ df.parse(endTime).getTime() + " and m.occurTime >="+ df.parse(startTime).getTime();
	}
    List list = this.moneyhistorydetailService.findMoneyhistorydetailBySql(sql);
    Integer total = Integer.valueOf(list.size());
    if (this.page.intValue() == 0) {
      this.page = Integer.valueOf(1);
    }
    if (total.intValue() == 0)
      this.pageCount = Integer.valueOf(1);
    else if (total.intValue() % this.pageSize.intValue() == 0)
      this.pageCount = Integer.valueOf(total.intValue() / this.pageSize.intValue());
    else {
      this.pageCount = Integer.valueOf(total.intValue() / this.pageSize.intValue() + 1);
    }
    sql = sql + " order by m.moneyHistoryDetailId desc " + "LIMIT " + (this.page.intValue() - 1) * this.pageSize.intValue() + "," + this.pageSize;
    this.moneyhistorydetailList = this.moneyhistorydetailService.findMoneyhistorydetailBySql(sql);

    return "success";
  }

  public Integer getEventTypeId()
  {
    return this.eventTypeId;
  }

  public void setEventTypeId(Integer eventTypeId) {
    this.eventTypeId = eventTypeId;
  }

  public List<Eventtype> getEventtypeList() {
    return this.eventtypeList;
  }

  public void setEventtypeList(List<Eventtype> eventtypeList) {
    this.eventtypeList = eventtypeList;
  }

  public Integer getUserId() {
    return this.userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public Moneycount getMoneycount() {
    return this.moneycount;
  }

  public void setMoneycount(Moneycount moneycount) {
    this.moneycount = moneycount;
  }

  public List<Moneyhistorydetail> getMoneyhistorydetailList() {
    return this.moneyhistorydetailList;
  }

  public void setMoneyhistorydetailList(List<Moneyhistorydetail> moneyhistorydetailList)
  {
    this.moneyhistorydetailList = moneyhistorydetailList;
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
}