<%@page import="java.util.List" %>
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
<%@page import="com.jqg.service.SystemconfService"%>
<%@page import="com.jqg.service.impl.SystemconfServiceImpl"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.sql.Timestamp" %>
<%@page import="java.util.Date" %>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@page import="java.util.*"%>
<%@page import="java.text.*"%>
<%@ page import="com.jqg.util.MD5HuiChao"%>
<%@ page import="com.jqg.util.getErrorInfo"%>
<%@page import="com.jqg.service.WebsiteService"%>
<%@page import="com.jqg.service.impl.WebsiteServiceImpl"%>
<%@page import="com.jqg.pojo.Website"%>
<%@page import="com.jqg.service.OnlinemodelService" %>
<%@page import="com.jqg.service.impl.OnlinemodelServiceImpl" %>
<%@page import="com.jqg.pojo.Onlinemodel" %>
<%@page import="com.jqg.service.UservipService" %>
<%@page import="com.jqg.service.impl.UservipServiceImpl" %>
<%@page import="com.jqg.pojo.Uservip" %>
<%-- <%@page import="com.jqg.service.RecordService" %> --%>
<%-- <%@page import="com.jqg.service.impl.RecordServiceImpl" %> --%>
<%-- <%@page import="com.jqg.pojo.Record" %> --%>

<%
	
	 OnlinemodelService onlinemodelService = new OnlinemodelServiceImpl();
	 Onlinemodel onlinemodel = onlinemodelService.findOnlinemodelByonlineId(2);
	 String MD5key = onlinemodel.getAccountPassword();
	 String MerNo = onlinemodel.getAccountNumber();
	
	String path = request.getContextPath();if(path!=null && !path.equals("")){	if(path.contains("/")){		if(path.equals("/")){path = "";	}}else{	path += "/";}} 
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;	
	
	String address=basePath+request.getServletPath();
    //字符编码
    String CharacterEncoding = "UTF-8";
    request.setCharacterEncoding(CharacterEncoding);
    String BillNo = request.getParameter("BillNo");
    String Amount = request.getParameter("Amount");
    String tradeOrder = request.getParameter("tradeOrder");
    String Succeed = request.getParameter("Succeed");
    String Result = request.getParameter("Result");
    String SignMD5info = request.getParameter("SignMD5info");
   
    MD5HuiChao md5 = new MD5HuiChao();
    String md5src = BillNo+"&"+Amount+"&"+Succeed+"&"+MD5key;
    
    String md5sign; //MD5加密后的字符串
    md5sign = md5.getMD5ofStr(md5src);//MD5检验结果
   
%>

<html>
<head><title>Payment Result</title></head>

<body>
<!-- 请加上你们网站的框架。就是你们网站的头部top，左部left等。还有字体等你们都要做调整。 -->

 <%
 if (md5sign.equals(SignMD5info)){
 %>
 <!-- MD5验证成功 -->
	<table width="728" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td  align="right" valign="top" width="200">Your order number is：</td>
    <td  align="left" valign="top"><%= BillNo%></td>
  </tr>
    <tr>
    <td  align="right" valign="top">Amount：</td>
    <td  align="left" valign="top"><%= Amount%></td>
  </tr>
    <tr>
    <td  align="right" valign="top">Payment result：</td>
	<%if ( "88".equals(Succeed) ) 
	{
	
		
		
		OfflinerechargeService offlinerechargeService=new OfflinerechargeServiceImpl();//充值记录
		
		
		Offlinerecharge offlinerecharge = offlinerechargeService.findOfflinerechargeById(Integer.valueOf(BillNo));
		if(offlinerecharge.getRechargeStatus()!=1){
		
		offlinerecharge.setRechargeStatus(1);
		boolean flag = offlinerechargeService.updateOfflinerecharge(offlinerecharge);
		
		
		
		DecimalFormat df = new DecimalFormat("##.00");
		String offlinerechargeId=request.getParameter("offlinerechargeId");		
		SystemconfService systemconfService = new SystemconfServiceImpl();
		
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
		moneyhistorydetail.setCollectMoney(moneycount.getCollectTotalMoney());
		moneyhistorydetail.setIntroduction("汇潮充值成功");
		moneyhistorydetail.setOccurTime(new Timestamp(new Date()
		.getTime()));
		moneyhistorydetailService.createMoneyhistorydetail(moneyhistorydetail);//资金历史明细
		
		moneyhistorydetail= new Moneyhistorydetail();
		moneyhistorydetail.setUservip(offlinerecharge.getUservip());
		moneyhistorydetail.setAffectMoney(recharge_fee);
		moneyhistorydetail.setAvailableBalance(Double.valueOf(df.format(moneycount.getAvailableMoney()+offlinerecharge.getRechargeAmount()-recharge_fee)));
		moneyhistorydetail.setFrozenMoney(moneycount.getFrozenMoney());
		moneyhistorydetail.setCollectMoney(moneycount.getCollectTotalMoney());
		moneyhistorydetail.setIntroduction("扣除在线充值手续费");
		moneyhistorydetail.setOccurTime(new Timestamp(new Date()
		.getTime()));
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
		 
		
// 		RecordService recordService=new RecordServiceImpl();
// 		List<Record> recordList2 = recordService.findRecordByRecordId("select * from Record r where r.userId="
// 									+ offlinerecharge.getUservip().getUserId()
// 									+ " and recordState=1 order by repaymentDate desc");
// 		if((recordList2 != null) && (recordList2.size() > 0)){
// 			Record record2 = (Record) recordList2.get(0);
// 			record2.setIsState(0);
// 			recordService.updateRecord(record2);
// 		}
		
		}
		
	%><!-- 可修改订单状态为正在付款中 -->
	<!-- 提交支付信息成功，返回绿色的提示信息 -->
	<td  align="left" valign="top" style="color:green;"><%= Result%></td>
	<%
	}
	else
	{
	%><!-- 提交支付信息失败，返回红色的提示信息 -->
    <td  align="left" valign="top" style="color:red;"><%= Result%>&nbsp;&nbsp;&nbsp;&nbsp;<%= Succeed%></td>
	<%
	}%>
  </tr>
  
</table>
<%
}
else 
{
%>
 <!-- MD5验证失败 -->
<table width="728" border="0" cellspacing="0" cellpadding="0" align="center">
 <tr>
    <td  align="center" valign="top" style="color:red;">MD5加密验证失败</td>
	</tr>
	</table>
<%	
}
 %>
</body>

</html>

