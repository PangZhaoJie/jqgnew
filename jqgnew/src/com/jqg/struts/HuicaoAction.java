package com.jqg.struts;

import com.jqg.pojo.Offlinerecharge;
import com.jqg.pojo.Onlinemodel;
import com.jqg.pojo.Uservip;
import com.jqg.service.OfflinerechargeService;
import com.jqg.service.OnlinemodelService;
import com.jqg.service.impl.OfflinerechargeServiceImpl;
import com.jqg.service.impl.OnlinemodelServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

public class HuicaoAction extends BaseAction
{
  private Offlinerecharge offlinerecharge;
  private String BillNo;
  private String Amount;
  private String ReturnURL;
  private String tradeAdd;
  private String SignInfo;
  private String AdviceURL = "";
  private OfflinerechargeService offlinerechargeService = new OfflinerechargeServiceImpl();
  OnlinemodelService onlinemodelService = new OnlinemodelServiceImpl();
  Uservip uservip = (Uservip)ActionContext.getContext().getSession().get("uservip");

  public String name() throws Exception { Onlinemodel onlinemodel = this.onlinemodelService.findOnlinemodelByonlineId(2);

    this.offlinerecharge = new Offlinerecharge();
    this.offlinerecharge.setRechargeAmount(Double.valueOf(this.Amount));
    this.offlinerecharge.setRechargeTime(new Timestamp(new Date().getTime()));
    this.offlinerecharge.setRechargeStatus(Integer.valueOf(2));
    this.offlinerecharge.setUservip(this.uservip);
    this.offlinerecharge.setRecharge("»ã³¯");
    this.offlinerechargeService.createOfflinerecharge(this.offlinerecharge);

    return "success"; }

  public OnlinemodelService getOnlinemodelService() {
    return this.onlinemodelService;
  }
  public void setOnlinemodelService(OnlinemodelService onlinemodelService) {
    this.onlinemodelService = onlinemodelService;
  }
  public Uservip getUservip() {
    return this.uservip;
  }
  public void setUservip(Uservip uservip) {
    this.uservip = uservip;
  }
}