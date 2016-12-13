package com.jqg.struts;

import com.jqg.pojo.Inbox;
import com.jqg.pojo.Manager;
import com.jqg.pojo.Moneycount;
import com.jqg.pojo.Moneyhistorydetail;
import com.jqg.pojo.Oerationlog;
import com.jqg.pojo.Offlinerecharge;
import com.jqg.pojo.Uservip;
import com.jqg.pojo.Website;
import com.jqg.service.InboxService;
import com.jqg.service.MoneycountService;
import com.jqg.service.MoneyhistorydetailService;
import com.jqg.service.OerationlogService;
import com.jqg.service.OfflinerechargeService;
import com.jqg.service.UservipService;
import com.jqg.service.WebsiteService;
import com.jqg.service.impl.InboxServiceImpl;
import com.jqg.service.impl.MoneycountServiceImpl;
import com.jqg.service.impl.MoneyhistorydetailServiceImpl;
import com.jqg.service.impl.OerationlogServiceImpl;
import com.jqg.service.impl.OfflinerechargeServiceImpl;
import com.jqg.service.impl.UservipServiceImpl;
import com.jqg.service.impl.WebsiteServiceImpl;
import com.jqg.util.ExcelDownload;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

public class OfflinerechargeAction extends BaseAction
  implements ServletRequestAware, ServletResponseAware
{
  private Integer page = Integer.valueOf(0);
  private Integer pageSize = Integer.valueOf(10);
  private Integer pageCount;
  private String result;
  private OfflinerechargeService offlinerechargeService = new OfflinerechargeServiceImpl();
  private MoneycountService moneycountService = new MoneycountServiceImpl();
  private MoneyhistorydetailService moneyhistorydetailService = new MoneyhistorydetailServiceImpl();
  private WebsiteService websiteService = new WebsiteServiceImpl();
  private List<Offlinerecharge> offlinerecharges = new ArrayList();
  private OerationlogService oerationlogService = new OerationlogServiceImpl();
  private UservipService uservipService = new UservipServiceImpl();
  private InboxService inboxService = new InboxServiceImpl();
  private String flag;
  private String userName;
  private String vipName;
  private Integer rechargeStatus;
  private String startTime;
  private String endTime;
  private String recharge;
  private String mark;
  private String ok;
  private Integer offlineRechargeId;
  private Integer total;
  private Integer userId;
  private Integer updown = Integer.valueOf(3);
  private String urlstr;
  private HttpServletRequest request;
  private HttpServletResponse response;
/**
 *  充值管理
 * @return
 * @throws Exception
 */
  public String offlineList()
    throws Exception
  {
    Offlinerecharge of = null;
    
    if (this.ok != null) {
      of = this.offlinerechargeService.findOfflinerechargeById(this.offlineRechargeId.intValue());
      Manager mana = (Manager)ActionContext.getContext().getSession().get("manager");
      boolean key = true;
      if(of.getRechargeStatus().equals(1) || of.getRechargeStatus()==2){
    	  key = false;
      }
      
      if(key){
	      Oerationlog oerationlog = new Oerationlog();
	      oerationlog.setManager(mana);
	      oerationlog.setOerationChangeTime(new Timestamp(new Date().getTime()));
	      InetAddress in = InetAddress.getLocalHost();
	      oerationlog.setOrationIp(in.getHostAddress().toString());
	      if ("0".equals(this.ok)) {
	        of.setVipName(mana.getManagerName());
	        of.setRechargeStatus(Integer.valueOf(2));
	        this.offlinerechargeService.updateOfflinerecharge(of);
	
	        oerationlog.setOerationCategory("offError");
	        oerationlog.setOerationRemaks("线下充值失败");
	
	        Uservip use = this.uservipService.findUservipByUserid(this.userId.intValue());
	        Inbox inbox = new Inbox();
	        inbox.setUservip(use);
	        inbox.setContent("尊重的用户你好，你的线下充值失败，请重新充值,如有疑问，请联系客服！");
	        inbox.setReceiveTime(new Timestamp(new Date().getTime()));
	        inbox.setSendName(mana.getManagerName());
	        inbox.setTitle("线下充值失败");
	        inbox.setStatus(Integer.valueOf(0));
	        this.inboxService.createInbox(inbox);
	      } else if ("1".equals(this.ok)) {//充值成功，处理充值记录和资金记录
	        of.setVipName(mana.getManagerName());
	        of.setRechargeStatus(Integer.valueOf(1));
	
	        System.out.println("userId=" + this.userId);
	        Website web = this.websiteService.findWebsiteBywebsiteId(1);
	        Integer percent = web.getLineReward();
	        Double rechargeMoney = of.getRechargeAmount();
	        Double reward = Double.valueOf(rechargeMoney.doubleValue() * percent.intValue() / 1000.0D);
	        of.setOffReward(reward);
	        this.offlinerechargeService.updateOfflinerecharge(of);
	        Moneycount money = this.moneycountService.findMoneycountByuserId(this.userId.intValue());
	        //处理充值明细
	        Uservip uservip = this.uservipService.findUservipByUserid(this.userId.intValue());
	        Moneyhistorydetail moneys = new Moneyhistorydetail();
	        moneys.setAvailableBalance(Double.valueOf(money.getAvailableMoney()+rechargeMoney));//可用余额
	        moneys.setAffectMoney(rechargeMoney);//操作金额
	        moneys.setFrozenMoney(money.getFrozenMoney());//冻结
	        moneys.setCollectMoney(money.getCollectTotalMoney());//待收金额
	        moneys.setUservip(uservip);
	        moneys.setIntroduction("线下充值成功");
	        moneys.setOccurTime(new Timestamp(new Date()
			.getTime()));
	        this.moneyhistorydetailService.createMoneyhistorydetail(moneys);
	        
	        //线下充值奖励
	        Moneyhistorydetail moneys1 = new Moneyhistorydetail();
	        moneys1.setAvailableBalance(Double.valueOf(money.getAvailableMoney()+rechargeMoney+reward));//可用余额
	        moneys1.setAffectMoney(reward);//操作金额
	        moneys1.setFrozenMoney(money.getFrozenMoney());//冻结
	        moneys1.setCollectMoney(money.getCollectTotalMoney());//待收金额
	        moneys1.setUservip(uservip);
	        moneys1.setIntroduction("线下充值成功，发放充值奖励");
	        moneys1.setOccurTime(new Timestamp(new Date()
			.getTime()));
	        this.moneyhistorydetailService.createMoneyhistorydetail(moneys1);
	        
	        
	        
	        money.setAvailableMoney(Double.valueOf(money.getAvailableMoney().doubleValue() + rechargeMoney.doubleValue() + reward.doubleValue()));
	        money.setTotalMoney(Double.valueOf(money.getTotalMoney().doubleValue() + rechargeMoney.doubleValue() + reward.doubleValue()));
	        money.setAccuOfflineRechargeReward(Double.valueOf(money.getAccuOfflineRechargeReward().doubleValue() + reward.doubleValue()));
	        money.setAccuProfitAndLossMoney(Double.valueOf(money.getAccuProfitAndLossMoney().doubleValue()+reward.doubleValue()));
	        money.setAccuRechargeMoney(Double.valueOf(money.getAccuRechargeMoney().doubleValue() + rechargeMoney.doubleValue()));
	        this.moneycountService.updateMoneycount(money);
	        
	        oerationlog.setOerationCategory("offSuccess");
	        oerationlog.setOerationRemaks("线下充值成功，明细表和资金统计表已经更新");
	        Inbox inbox = new Inbox();
	        inbox.setUservip(uservip);
	        inbox.setContent("尊重的用户你好，恭喜你，你的线下充值已成功！");
	        inbox.setReceiveTime(new Timestamp(new Date().getTime()));
	        inbox.setSendName(mana.getManagerName());
	        inbox.setTitle("线下充值成功");
	        inbox.setStatus(Integer.valueOf(0));
	        this.inboxService.createInbox(inbox);
	      }
	      this.oerationlogService.createOerationlog(oerationlog);
      }
    }
    String sql = "";
    if ("0".equals(this.flag))
      sql = sql + "select distinct o.* from Offlinerecharge o,Uservip u  where  o.userId=u.userId and o.recharge!='线下充值'";
    else if ("1".equals(this.flag))
      sql = sql + "select distinct o.* from Offlinerecharge o,Uservip u  where  o.userId=u.userId and o.recharge='线下充值'";
    else if ("2".equals(this.flag)) {
      sql = sql + "select distinct o.* from Offlinerecharge o,Uservip u  where  o.userId=u.userId and 1=1";
      if(this.userId!=null && !"".equals(this.userId)){
    	  sql=sql + " and o.userId=" + this.userId ;
      }
    }
    if ((this.mark != null) && (!"".equals(this.mark.trim()))) {
      sql = "";
//      sql = sql + "select distinct o.* from Offlinerecharge o,Uservip u  where  o.userId=u.userId and 1=1";
      if ("0".equals(this.flag))
          sql = sql + "select distinct o.* from Offlinerecharge o,Uservip u  where  o.userId=u.userId and o.recharge!='线下充值'";
        else if ("1".equals(this.flag))
          sql = sql + "select distinct o.* from Offlinerecharge o,Uservip u  where  o.userId=u.userId and o.recharge='线下充值'";
        else if ("2".equals(this.flag)) {
          sql = sql + "select distinct o.* from Offlinerecharge o,Uservip u  where  o.userId=u.userId and 1=1";
        }

      if ((this.userName != null) && (!"".equals(this.userName.trim())) && (!"null".equals(this.userName.trim()))) {
        sql = sql + " and u.userName like '%" + this.userName + "%'";
      }
      System.out.println("o");
      if ((this.vipName != null) && (!"".equals(this.vipName.trim()))) {
        sql = sql + " and o.vipName like '%" + this.vipName + "%'";
      }
      if ((this.recharge != null) && (!"".equals(this.recharge))) {
        this.recharge = URLDecoder.decode(this.recharge, "UTF-8");
      }
      if ((this.recharge != null) && (!"-请选择-".equals(this.recharge)) && (!"".equals(this.recharge))) {
        sql = sql + " and o.recharge='" + this.recharge + "'";
      }
      if ((this.rechargeStatus != null) && (this.rechargeStatus.intValue() != -1)) {
        sql = sql + " and o.rechargeStatus=" + this.rechargeStatus;
      }
      if ((this.startTime != null) && (!"".equals(this.startTime))) {
        sql = sql + " and o.rechargeTime>='" + this.startTime + "'";
      }
      if ((this.endTime != null) && (!"".equals(this.endTime))) {
        sql = sql + " and o.rechargeTime<='" + this.endTime + " 24:00:00'";
      }
    }
    sql = sql + " order by o.offlineRechargeId desc ";
    List offlinerecharge = this.offlinerechargeService.findOfflinerechargeBySql(sql);
    this.total = Integer.valueOf(offlinerecharge.size());
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
    sql = sql + "LIMIT " + (this.page.intValue() - 1) * this.pageSize.intValue() + "," + this.pageSize;
    this.offlinerecharges = this.offlinerechargeService.findOfflinerechargeBySql(sql);
    if (this.updown.intValue() == 1)
    {
      String urlstr1 = URLDecoder.decode(this.urlstr, "UTF-8");
      upLoad(urlstr1, this.offlinerecharges);
      ExcelDownload.download(this.request, this.response, urlstr1, "userOffline.xls");
      this.updown = Integer.valueOf(2);
    }
    return "success";
  }
  public String upLoadOffline()throws Exception{
	  String sql = "";
	    if ("0".equals(this.flag))
	      sql = sql + "select distinct o.* from Offlinerecharge o,Uservip u  where  o.userId=u.userId and o.recharge!='线下充值'";
	    else if ("1".equals(this.flag))
	      sql = sql + "select distinct o.* from Offlinerecharge o,Uservip u  where  o.userId=u.userId and o.recharge='线下充值'";
	    else if ("2".equals(this.flag)) {
	      sql = sql + "select distinct o.* from Offlinerecharge o,Uservip u  where  o.userId=u.userId and 1=1";
	    }
	    if ((this.mark != null) && (!"".equals(this.mark.trim()))) {
	      sql = "";
	      sql = sql + "select distinct o.* from Offlinerecharge o,Uservip u  where  o.userId=u.userId and 1=1";

	      if ((this.userName != null) && (!"".equals(this.userName.trim())) && (!"null".equals(this.userName.trim()))) {
	        sql = sql + " and u.userName like '%" + this.userName + "%'";
	      }
	      System.out.println("o");
	      if ((this.vipName != null) && (!"".equals(this.vipName.trim()))) {
	        sql = sql + " and o.vipName like '%" + this.vipName + "%'";
	      }
	      if ((this.recharge != null) && (!"".equals(this.recharge))) {
	        this.recharge = URLDecoder.decode(this.recharge, "UTF-8");
	      }
	      if ((this.recharge != null) && (!"-请选择-".equals(this.recharge)) && (!"".equals(this.recharge))) {
	        sql = sql + " and o.recharge='" + this.recharge + "'";
	      }
	      if ((this.rechargeStatus != null) && (this.rechargeStatus.intValue() != -1)) {
	        sql = sql + " and o.rechargeStatus=" + this.rechargeStatus;
	      }
	      if ((this.startTime != null) && (!"".equals(this.startTime))) {
	        sql = sql + " and o.rechargeTime>='" + this.startTime + "'";
	      }
	      if ((this.endTime != null) && (!"".equals(this.endTime))) {
	        sql = sql + " and o.rechargeTime<='" + this.endTime + " 24:00:00'";
	      }
	    }
	    sql = sql + " order by o.offlineRechargeId desc ";
	    this.offlinerecharges = this.offlinerechargeService.findOfflinerechargeBySql(sql);
	    if (this.updown.intValue() == 1)
	    {
	      String urlstr1 = URLDecoder.decode(this.urlstr, "UTF-8");
	      upLoad(urlstr1, this.offlinerecharges);
	      ExcelDownload.download(this.request, this.response, urlstr1, "userOffline.xls");
	      this.updown = Integer.valueOf(2);
	    }
	    return "success";
  }
  
  
  
  public String getResult() {
    return this.result;
  }
  public void setResult(String result) {
    this.result = result;
  }
  public List<Offlinerecharge> getOfflinerecharges() {
    return this.offlinerecharges;
  }
  public void setOfflinerecharges(List<Offlinerecharge> offlinerecharges) {
    this.offlinerecharges = offlinerecharges;
  }
  public String getFlag() {
    return this.flag;
  }
  public void setFlag(String flag) {
    this.flag = flag;
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
  public String getUserName() {
    return this.userName;
  }
  public void setUserName(String userName) {
    this.userName = userName;
  }
  public String getVipName() {
    return this.vipName;
  }
  public void setVipName(String vipName) {
    this.vipName = vipName;
  }
  public Integer getRechargeStatus() {
    return this.rechargeStatus;
  }
  public void setRechargeStatus(Integer rechargeStatus) {
    this.rechargeStatus = rechargeStatus;
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
  public String getMark() {
    return this.mark;
  }
  public void setMark(String mark) {
    this.mark = mark;
  }
  public String getRecharge() {
    return this.recharge;
  }
  public void setRecharge(String recharge) {
    this.recharge = recharge;
  }
  public String getOk() {
    return this.ok;
  }
  public void setOk(String ok) {
    this.ok = ok;
  }
  public Integer getOfflineRechargeId() {
    return this.offlineRechargeId;
  }
  public void setOfflineRechargeId(Integer offlineRechargeId) {
    this.offlineRechargeId = offlineRechargeId;
  }
  public Integer getTotal() {
    return this.total;
  }
  public void setTotal(Integer total) {
    this.total = total;
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

  public int upLoad(String str, List<Offlinerecharge> offlineList) throws IOException, RowsExceededException, WriteException {
    OutputStream os = new FileOutputStream(str);

    WritableWorkbook workbook = Workbook.createWorkbook(os);
    WritableSheet sheet = workbook.createSheet("CreateExcel", 0);

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

    l = new Label(0, 0, "充值信息", headerFormat);
    sheet.addCell(l);

    int column = 0;
    sheet.addCell(new Label(column++, 2, "ID", titleFormat));
    sheet.addCell(new Label(column++, 2, "会员名", titleFormat));
    sheet.addCell(new Label(column++, 2, "充值方式", titleFormat));
    sheet.addCell(new Label(column++, 2, "充值金额", titleFormat));
    sheet.addCell(new Label(column++, 2, "充值奖励", titleFormat));
    sheet.addCell(new Label(column++, 2, "充值状态", titleFormat));
    sheet.addCell(new Label(column++, 2, "充值时间", titleFormat));
    sheet.addCell(new Label(column++, 2, "对账订单号", titleFormat));
    sheet.addCell(new Label(column++, 2, "处理人", titleFormat));
    for (int i = 0; i < offlineList.size(); i++) {
      Offlinerecharge offl = (Offlinerecharge)offlineList.get(i);
      column = 0;

      sheet.addCell(new Number(column++, i + 3, offl.getOfflineRechargeId().intValue(), detFormat));
      sheet.addCell(new Label(column++, i + 3, offl.getUservip().getUserName(), detFormat));
      sheet.addCell(new Label(column++, i + 3, offl.getRecharge(), detFormat));
      sheet.addCell(new Number(column++, i + 3, offl.getRechargeAmount().doubleValue(), detFormat));
      Double ofReward = Double.valueOf(0.0D);
      if (offl.getOffReward() != null) {
        ofReward = offl.getOffReward();
      }
      sheet.addCell(new Number(column++, i + 3, ofReward.doubleValue(), detFormat));
      String status = "";
      if (offl.getRechargeStatus().intValue() == 2)
        status = "充值失败";
      else if (offl.getRechargeStatus().intValue() == 1) {
        status = "充值成功";
      }
      sheet.addCell(new Label(column++, i + 3, status, detFormat));
      sheet.addCell(new DateTime(column++, i + 3, offl.getRechargeTime(), dateFormat));
      sheet.addCell(new Label(column++, i + 3, offl.getCheckOrderNum(), detFormat));
      sheet.addCell(new Label(column++, i + 3, offl.getVipName(), detFormat));
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
}