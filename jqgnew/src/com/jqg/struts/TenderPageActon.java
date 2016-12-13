package com.jqg.struts;

import com.jqg.pojo.Creditlevel;
import com.jqg.pojo.Lssuing;
import com.jqg.pojo.Periodday;
import com.jqg.pojo.Periodtime;
import com.jqg.pojo.Tender;
import com.jqg.pojo.Uservip;
import com.jqg.service.LssuingService;
import com.jqg.service.TenderService;
import com.jqg.service.impl.LssuingServiceImpl;
import com.jqg.service.impl.TenderServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

public class TenderPageActon extends BaseAction
{
  private static final long serialVersionUID = 1L;
  private int currentPage;
  private int pageSize = 10;
  private int totalPage;
  private String returnWayId;
  private String moneyUseId;
  private String periodTimeId;
  private String periodDayId;
  private String award;
  private String satate;
  private String money1;
  private String money2;
  private String result;
  private int lussingType=0;
  LssuingService lssuingService = new LssuingServiceImpl();
  TenderService tenderService = new TenderServiceImpl();
/**
 * 根据条件查询标
 * @throws Exception
 */
  public void findlssuingBytender()
    throws Exception
  {
	  String sql = "";
	if(lussingType>=6){
		 sql = "SELECT * FROM lssuing l WHERE  l.state<5  and l.lssuingType =  "+lussingType;
	}else{
		sql = "SELECT * FROM lssuing l WHERE  l.state<5  and l.lssuingType < 6 ";
	}
    if (!"0".equals(this.returnWayId))
    {
      sql = sql + " AND  l.returnWayId = " + this.returnWayId;
    }

    if (!"0".equals(this.moneyUseId))
    {
      sql = sql + " AND l.moneyUseId = " + this.moneyUseId;
    }
    if (!"0".equals(this.periodTimeId))
    {
      sql = sql + " AND l.periodTimeId = " + this.periodTimeId;
    }
    if (!"0".equals(this.periodDayId))
    {
      sql = sql + " AND l.periodDayId = " + this.periodDayId;
    }
    if (!"2".equals(this.award))
    {
      sql = sql + " AND l.isAward =  " + this.award;
    }
    if (!"100".equals(this.satate))
    {
      sql = sql + " AND l.state=  " + this.satate;
    }
    if ((this.money1.length() != 0) && (this.money2.length() != 0))
    {
      sql = sql + " AND l.borrowMoney >= " + this.money1 + " AND l.borrowMoney <= " + this.money2;
    }

    List lssuings = this.lssuingService.findLssuingsBySearch(sql);

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

    String sqls = sql + " ORDER BY l.lssuingId DESC LIMIT " + (this.currentPage - 1) * this.pageSize + "," + this.pageSize;
    List<Lssuing> lssuingspage = this.lssuingService.findLssuingsBySearch(sqls);

    if (lssuingspage.size() != 0)
    {
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
        List<Tender> tenders = this.tenderService.findTendersBylssuingId(lssuing.getLssuingId().intValue());

        int money = 0;
        if (tenders.size() == 0)
        {
          money = 0;
        }
        else
        {
          for (Tender tender : tenders)
          {
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

        if (lssuing.getLssuingType().intValue() == 1)
        {
          picture = "<img src='../images/loantype/credit.gif' />" + picture;
        }
        else if (lssuing.getLssuingType().intValue() == 2)
        {
          picture = "<img src='../images/loantype/vouch.gif' />" + picture;
        }
        else if (lssuing.getLssuingType().intValue() == 3)
        {
          picture = "<img src='../images/loantype/second.gif' />" + picture;
        }
        else if (lssuing.getLssuingType().intValue() == 4)
        {
          picture = "<img src='../images/loantype/worth.gif' />" + picture;
        }
        else if (lssuing.getLssuingType().intValue() == 5)
        {
          picture = "<img src='../images/loantype/pawn.gif' />" + picture;
        }

        if (lssuing.getIsAward().intValue() == 1) {
          picture = picture + "<img src='../images/loantype/award.gif' />";
          if (lssuing.getAwardMoney() != null)
            wate = lssuing.getAwardMoney().toString();
          else if (lssuing.getAwardRate() != null)
            wate = lssuing.getAwardRate() + "%";
        }
        else if (lssuing.getIsAward().intValue() == 0) {
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
        }
        else if (lssuing.getState().intValue() == -1) {
          state = "逾期";
          stataclass = "off_bt";
        }
        if (lssuing.getPeriodtime() != null)
        {
          time = lssuing.getPeriodtime().getPeriodTimeName();

          day = "年利率：" + lssuing.getRate();
        }
        else if (lssuing.getPeriodday() != null)
        {
          picture = picture + "<img src='../images/loantype/day.gif' />";
          time = lssuing.getPeriodday().getPeriodDayName();
          day = "日利率：" + lssuing.getRate();
        }
        if (lssuing.getIsOrient().intValue() == 1)
        {
          picture = picture + "<img src='../images/loantype/lock.gif' />";
        }
        String title;
        if (lssuing.getTitle().length() > 20)
        {
          title = lssuing.getTitle().substring(0, 20);
        }
        else
        {
          title = lssuing.getTitle();
        }
        String actions = "totender.action?lssuingId=" + lssuing.getLssuingId();

        str.append("{\"title\":\"" + title + "\",");
        str.append("\"credit\":\"" + lssuing.getUservip().getCreditlevel().getCreditLevelPicture() + "\",");
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
      try
      {
        response.getWriter().print(str);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    else
    {
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

  public String getReturnWayId() {
    return this.returnWayId;
  }
  public void setReturnWayId(String returnWayId) {
    this.returnWayId = returnWayId;
  }
  public String getMoneyUseId() {
    return this.moneyUseId;
  }
  public void setMoneyUseId(String moneyUseId) {
    this.moneyUseId = moneyUseId;
  }
  public String getPeriodTimeId() {
    return this.periodTimeId;
  }
  public void setPeriodTimeId(String periodTimeId) {
    this.periodTimeId = periodTimeId;
  }
  public String getPeriodDayId() {
    return this.periodDayId;
  }
  public void setPeriodDayId(String periodDayId) {
    this.periodDayId = periodDayId;
  }
  public String getAward() {
    return this.award;
  }
  public void setAward(String award) {
    this.award = award;
  }
  public String getSatate() {
    return this.satate;
  }
  public void setSatate(String satate) {
    this.satate = satate;
  }
  public String getMoney1() {
    return this.money1;
  }
  public void setMoney1(String money1) {
    this.money1 = money1;
  }
  public String getMoney2() {
    return this.money2;
  }
  public void setMoney2(String money2) {
    this.money2 = money2;
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

public int getLussingType() {
	return lussingType;
}

public void setLussingType(int lussingType) {
	this.lussingType = lussingType;
}
  
  
}