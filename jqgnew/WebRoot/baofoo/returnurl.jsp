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
<%@ page import="com.jqg.util.md5"%>
<%@ page import="com.jqg.util.getErrorInfo"%>
<%@page import="com.jqg.service.OnlinemodelService" %>
<%@page import="com.jqg.service.impl.OnlinemodelServiceImpl" %>
<%@page import="com.jqg.pojo.Onlinemodel" %>
<%@page import="com.jqg.pojo.Uservip" %>
<%@page import="com.jqg.service.UservipService" %>
<%@page import="com.jqg.service.impl.UservipServiceImpl" %>
<%@page import="com.jqg.pojo.Uservip" %>
<%@page import="java.util.List" %>
<%-- <%@page import="com.jqg.service.RecordService" %> --%>
<%-- <%@page import="com.jqg.service.impl.RecordServiceImpl" %> --%>
<%-- <%@page import="com.jqg.pojo.Record" %> --%>

<jsp:useBean id='oMD5' scope='request' class='com.jqg.util.md5'/>
<jsp:useBean id='oInfo' scope='request' class='com.jqg.util.getErrorInfo'/>
<%  
	OnlinemodelService onlinemodelService = new OnlinemodelServiceImpl();
	 Onlinemodel onlinemodel = onlinemodelService.findOnlinemodelByonlineId(1);
	String key = onlinemodel.getAccountPassword();
	 
	 String MerNo = onlinemodel.getAccountNumber();
	String MemberID = request.getParameter("MemberID");//商户号
	String TerminalID = request.getParameter("TerminalID");//商户终端号
	String TransID = request.getParameter("TransID");//商户流水号
	String Result = request.getParameter("Result");//支付结果
	String ResultDesc = request.getParameter("ResultDesc");//支付结果描述
	String factMoney = request.getParameter("FactMoney");//实际成功金额，以分为单位
	String a = new BigDecimal(factMoney).divide(BigDecimal.valueOf(100)).setScale(2).toString(); //使用元显示
	String FactMoney = a;
	String AdditionalInfo = request.getParameter("AdditionalInfo");//订单附加消息	
	String SuccTime = request.getParameter("SuccTime");//支付完成时间
	String Md5Sign = request.getParameter("Md5Sign");//MD5签名
	 
	String MARK = "~|~";
	String md5 = "MemberID=" + MemberID + MARK + "TerminalID=" + TerminalID + MARK + "TransID=" + TransID + MARK + "Result=" + Result + MARK + "ResultDesc=" + ResultDesc + MARK
			+ "FactMoney=" + factMoney + MARK + "AdditionalInfo=" + AdditionalInfo + MARK + "SuccTime=" + SuccTime
			+ MARK + "Md5Sign=" + key;
	String WaitSign = oMD5.getMD5ofStr(md5);//计算MD5值
	String lbresultDesc = oInfo.getErrorInfo(Result,ResultDesc);//支付结果文字描述
// 	Uservip uservip = (Uservip)ActionContext.getContext().getSession().get("uservip");
// 	String OrderMoney=(String)ActionContext.getContext().getSession().get("OrderMoney"+uservip.getUserId());//获取提交金额的Session
// 	int money= Integer.parseInt(factMoney);
// 	int b = money/100; //使用元显示
// 	String lbOrderMoney=String.valueOf(b);
	if(WaitSign.equals(Md5Sign)){
		System.out.println("OK");
		//校验通过开始处理订单		
		out.println("OK");//全部正确了输出OK
		System.out.println(Result);	
		if("1".equals(Result)){
			DecimalFormat df = new DecimalFormat("##.00");
			String offlinerechargeId=request.getParameter("offlinerechargeId");
			OfflinerechargeService offlinerechargeService=new OfflinerechargeServiceImpl();
			SystemconfService systemconfService = new SystemconfServiceImpl();
			
			Offlinerecharge offlinerecharge=  offlinerechargeService.findOfflinerechargeById(Integer.parseInt(offlinerechargeId));
			if(offlinerecharge.getRechargeStatus()!=1){
			offlinerecharge.setCheckOrderNum(TransID);
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
			moneyhistorydetail.setCollectMoney(moneycount.getCollectTotalMoney());
			moneyhistorydetail.setIntroduction("宝付充值成功");
			moneyhistorydetail.setOccurTime(new Timestamp(new Date().getTime()));
			moneyhistorydetailService.createMoneyhistorydetail(moneyhistorydetail);//资金历史明细
			
			moneyhistorydetail= new Moneyhistorydetail();
			moneyhistorydetail.setUservip(offlinerecharge.getUservip());
			moneyhistorydetail.setAffectMoney(recharge_fee);
			moneyhistorydetail.setAvailableBalance(Double.valueOf(df.format(moneycount.getAvailableMoney()+offlinerecharge.getRechargeAmount()-recharge_fee)));
			moneyhistorydetail.setFrozenMoney(moneycount.getFrozenMoney());
			moneyhistorydetail.setCollectMoney(moneycount.getCollectTotalMoney());
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
			
// 			RecordService recordService=new RecordServiceImpl();
// 			List<Record> recordList2 = recordService.findRecordByRecordId("select * from Record r where r.userId="
// 										+ offlinerecharge.getUservip().getUserId()
// 										+ " and recordState=1 order by repaymentDate desc");
// 			if((recordList2 != null) && (recordList2.size() > 0)){
// 				Record record2 = (Record) recordList2.get(0);
// 				record2.setIsState(0);
// 				recordService.updateRecord(record2);
// 			}
			}
			
		}	
	}else{
		System.out.println("Md5CheckFail");
		out.println("Md5CheckFail'");//MD5校验失败

	}
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- TemplateBeginEditable name="doctitle" -->
<title>充值接口-服务器返回结果</title>

</head>

<body >

    
</body>
</html>
