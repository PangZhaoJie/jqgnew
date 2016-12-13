<%@page import="com.jqg.service.impl.InboxServiceImpl"%>
<%@page import="com.jqg.service.InboxService"%>
<%@page import="com.jqg.pojo.Inbox"%>
<%@page import="java.util.Date"%>
<%@page import="com.jqg.service.impl.MoneyhistorydetailServiceImpl"%>
<%@page import="com.jqg.service.MoneyhistorydetailService"%>
<%@page import="com.jqg.pojo.Moneyhistorydetail"%>
<%@page import="com.jqg.pojo.Moneycount"%>
<%@page import="com.jqg.service.impl.MoneycountServiceImpl"%>
<%@page import="com.jqg.service.MoneycountService"%>
<%@page import="com.jqg.pojo.Offlinerecharge"%>
<%@page import="com.jqg.pojo.Systemconf"%>
<%@page import="com.jqg.service.impl.OfflinerechargeServiceImpl"%>
<%@page import="com.jqg.service.OfflinerechargeService"%>
<%@page import="com.jqg.service.WebsiteService"%>
<%@page import="com.jqg.service.impl.WebsiteServiceImpl"%>
<%@page import="com.jqg.pojo.Website"%>
<%@page import="com.jqg.service.SystemconfService"%>
<%@page import="com.jqg.service.impl.SystemconfServiceImpl"%>
<%@page import="java.math.BigDecimal"%>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@page import="java.util.*"%>
<%@page import="java.text.*"%>
<%@ page import="com.jqg.util.getErrorInfo"%>
<%@page import="com.jqg.service.OnlinemodelService" %>
<%@page import="com.jqg.service.impl.OnlinemodelServiceImpl" %>
<%@page import="com.jqg.pojo.Onlinemodel" %>
<%@page import="com.jqg.pojo.Uservip" %>
<%@page import="com.jqg.service.UservipService" %>
<%@page import="com.jqg.service.impl.UservipServiceImpl" %>
<%@page import="com.jqg.pojo.Uservip" %>
<%@page import="java.util.ResourceBundle"%>
<%@page import="com.etonepay.b2c.utils.MD5"%>
<%
request.setCharacterEncoding("UTF-8");

//接收Server返回的支付结果
String transCode=request.getParameter("transCode");
String merchantId=request.getParameter("merchantId");
String respCode=request.getParameter("respCode");
String sysTraceNum=request.getParameter("sysTraceNum");
String merOrderNum=request.getParameter("merOrderNum");
String orderId=request.getParameter("orderId");
String bussId=request.getParameter("bussId");
String tranAmt=request.getParameter("tranAmt");
String orderAmt=request.getParameter("orderAmt");
String bankFeeAmt=request.getParameter("bankFeeAmt");
String integralAmt=request.getParameter("integralAmt");
String vaAmt=request.getParameter("vaAmt");
String bankAmt=request.getParameter("bankAmt");
String bankId=request.getParameter("bankId");
String integralSeq=request.getParameter("integralSeq");
String vaSeq=request.getParameter("vaSeq");
String bankSeq=request.getParameter("bankSeq");
String tranDateTime=request.getParameter("tranDateTime");
String payMentTime=request.getParameter("payMentTime");
String settleDate=request.getParameter("settleDate");
String currencyType=request.getParameter("currencyType");
String orderInfo=request.getParameter("orderInfo");
String userId=request.getParameter("userId");
String userIp=request.getParameter("userIp");
String reserver1=request.getParameter("reserver1");
String reserver2=request.getParameter("reserver2");
String reserver3=request.getParameter("reserver3");
String reserver4=request.getParameter("reserver4");
String signValue=request.getParameter("signValue");//返回的MD45签名
OnlinemodelService onlinemodelService = new OnlinemodelServiceImpl();
Onlinemodel onlinemodel1 = onlinemodelService.findOnlinemodelByonlineId(6);

String txnString =transCode+"|"+merchantId+"|"+respCode+"|"+sysTraceNum+"|"
	+merOrderNum+"|"+orderId+"|"+bussId+"|"+tranAmt+"|"+orderAmt+"|"
	+bankFeeAmt+"|"+integralAmt+"|"+vaAmt+"|"+bankAmt+"|"+bankId+"|"
	+integralSeq+"|"+vaSeq +"|"+bankSeq+"|"+tranDateTime+"|"
	+payMentTime+"|"+settleDate+"|"+currencyType+"|"+orderInfo+"|"+userId;
String datakey=onlinemodel1.getAccountPassword();
String MD5sign = MD5.instance.getMD5ofStr(txnString+datakey);
%>
<html>
<head><title>Payment Result</title></head>
<body>
<% if(MD5sign.equals(signValue)){ %>
<!-- MD5验证成功 -->
	<table width="728" border="0" cellspacing="0" cellpadding="0" align="center">
  		
    

<%	if("0000".equalsIgnoreCase(respCode)){
			DecimalFormat df = new DecimalFormat("##.00");
			OfflinerechargeService offlinerechargeService=new OfflinerechargeServiceImpl();
			SystemconfService systemconfService = new SystemconfServiceImpl();
			
			Offlinerecharge offlinerecharge=  offlinerechargeService.findOfflinerechargeById(Integer.parseInt(sysTraceNum));
			if(offlinerecharge.getRechargeStatus()!=1){
			offlinerecharge.setCheckOrderNum(merOrderNum);
			offlinerecharge.setRechargeStatus(1);
			offlinerechargeService.updateOfflinerecharge(offlinerecharge);
			
			MoneycountService moneycountService=new MoneycountServiceImpl();
			Moneycount moneycount=moneycountService.findMoneycountByuserId(offlinerecharge.getUservip().getUserId());
			UservipService uservipService = new UservipServiceImpl();
			Uservip uservip = uservipService.findUservipByUserid(offlinerecharge.getUservip().getUserId());
			//线上充值成功资金记录处理
			MoneyhistorydetailService moneyhistorydetailService=new MoneyhistorydetailServiceImpl();
			Systemconf sysconf = null;
			if("1".equals(uservip.getIsVIP())){
				sysconf = systemconfService.findSystemconfByParname("con_online_recharge_fee_2");
			}else{
				sysconf = systemconfService.findSystemconfByParname("con_online_recharge_fee");
			}
			double recharge_fee = Double.valueOf(df.format(offlinerecharge.getRechargeAmount()*Double.valueOf(sysconf.getParvalue())));
			Moneyhistorydetail moneyhistorydetail= new Moneyhistorydetail();
			moneyhistorydetail.setUservip(offlinerecharge.getUservip());
			moneyhistorydetail.setAffectMoney(offlinerecharge.getRechargeAmount());
			moneyhistorydetail.setAvailableBalance(Double.valueOf(df.format(moneycount.getAvailableMoney()+offlinerecharge.getRechargeAmount())));
			moneyhistorydetail.setFrozenMoney(moneycount.getFrozenMoney());
			moneyhistorydetail.setCollectMoney(moneycount.getDueInMoney());
			moneyhistorydetail.setIntroduction("易通充值成功");
			moneyhistorydetail.setOccurTime(new Timestamp(new Date().getTime()));
			moneyhistorydetailService.createMoneyhistorydetail(moneyhistorydetail);//资金历史明细
			
			moneyhistorydetail= new Moneyhistorydetail();
			moneyhistorydetail.setUservip(offlinerecharge.getUservip());
			moneyhistorydetail.setAffectMoney(recharge_fee);
			moneyhistorydetail.setAvailableBalance(Double.valueOf(df.format(moneycount.getAvailableMoney()+offlinerecharge.getRechargeAmount()-recharge_fee)));
			moneyhistorydetail.setFrozenMoney(moneycount.getFrozenMoney());
			moneyhistorydetail.setCollectMoney(moneycount.getDueInMoney());
			moneyhistorydetail.setIntroduction("扣除在线充值手续费");
			moneyhistorydetail.setOccurTime(new Timestamp(new Date().getTime()));
			moneyhistorydetailService.createMoneyhistorydetail(moneyhistorydetail);//资金历史明细
			
			
			moneycount.setAvailableMoney(moneyhistorydetail.getAvailableBalance());
			moneycount.setTotalMoney(Double.valueOf(df.format(moneycount.getTotalMoney()+offlinerecharge.getRechargeAmount()-recharge_fee)));
			moneycount.setAccuRechargeMoney(Double.valueOf(df.format(moneycount.getAccuRechargeMoney()+offlinerecharge.getRechargeAmount())));
			moneycount.setAccuRechargeFee(Double.valueOf(df.format(moneycount.getAccuRechargeFee()+recharge_fee)));	
			moneycountService.updateMoneycount(moneycount);
			
			WebsiteService websiteService = new WebsiteServiceImpl();
			Website website = websiteService.findWebsiteBywebsiteId(1);
			
  	  		InboxService inboxService=new InboxServiceImpl();
		 	Inbox inbox=new Inbox();
			inbox.setSendName(website.getWebName());
			inbox.setTitle("线上充值成功");
			inbox.setContent("您的资金发生变动，请注意查看");
			inbox.setStatus(0);
			inbox.setReceiveTime(new Timestamp(new Date().getTime()));
			inbox.setUservip(offlinerecharge.getUservip());
			inboxService.createInbox(inbox);
		}
		%>
		<tr>
			<td  align="left" valign="top" style="color:green;">支付成功！</td>
		</tr>
		<%
	}else{ %><!-- 提交支付信息失败 -->
 			<tr>
    			<td  align="center" valign="top" style="color:red;">提交支付信息失败</td>
			</tr>
		</table>
	<%}
}else{ %>
		<!-- MD5验证失败 -->
	<table width="728" border="0" cellspacing="0" cellpadding="0" align="center">
 		<tr>
    		<td  align="center" valign="top" style="color:red;">MD5加密验证失败</td>
		</tr>
	</table>
<% } %>

