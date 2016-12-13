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
<%@ page import="com.jqg.util.getErrorInfo"%>
<%@page import="com.jqg.service.WebsiteService"%>
<%@page import="com.jqg.service.impl.WebsiteServiceImpl"%>
<%@page import="com.jqg.pojo.Website"%>
<%@page import="com.jqg.service.OnlinemodelService" %>
<%@page import="com.jqg.service.impl.OnlinemodelServiceImpl" %>
<%@page import="com.jqg.pojo.Onlinemodel" %>
<%@page import="com.jqg.util.MD5YiBao"%>

<%!	String formatString(String text){ 
			if(text == null) {
				return ""; 
			}
			return text;
		}
%>
<%
	Onlinemodel onlinemodel = onlinemodelService.findOnlinemodelByonlineId(3);
	
	String keyValue   = formatString(Configuration.getInstance().getValue("keyValue"));   // 商家密钥
	String r0_Cmd 	  = formatString(request.getParameter("r0_Cmd")); // 业务类型
	String p1_MerId   = formatString(Configuration.getInstance().getValue("p1_MerId"));   // 商户编号
	String r1_Code    = formatString(request.getParameter("r1_Code"));// 支付结果
	String r2_TrxId   = formatString(request.getParameter("r2_TrxId"));// 易宝支付交易流水号
	String r3_Amt     = formatString(request.getParameter("r3_Amt"));// 支付金额
	String r4_Cur     = formatString(request.getParameter("r4_Cur"));// 交易币种
	String r5_Pid     = new String(formatString(request.getParameter("r5_Pid")).getBytes("iso-8859-1"),"gbk");// 商品名称
	String r6_Order   = formatString(request.getParameter("r6_Order"));// 商户订单号
	String r7_Uid     = formatString(request.getParameter("r7_Uid"));// 易宝支付会员ID
	String r8_MP      = new String(formatString(request.getParameter("r8_MP")).getBytes("iso-8859-1"),"gbk");// 商户扩展信息
	String r9_BType   = formatString(request.getParameter("r9_BType"));// 交易结果返回类型
	String hmac       = formatString(request.getParameter("hmac"));// 签名数据
	boolean isOK = false;
	// 校验返回数据包
	isOK = MD5YiBao.verifyCallback(hmac,p1_MerId,r0_Cmd,r1_Code, 
			r2_TrxId,r3_Amt,r4_Cur,r5_Pid,r6_Order,r7_Uid,r8_MP,r9_BType,keyValue);
	if(isOK) {
		//在接收到支付结果通知后，判断是否进行过业务逻辑处理，不要重复进行业务逻辑处理
		if(r1_Code.equals("1")) {
		
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
		//线上充值成功资金记录处理
		MoneyhistorydetailService moneyhistorydetailService=new MoneyhistorydetailServiceImpl();
		Systemconf sysconf = systemconfService.findSystemconfByParname("con_online_recharge_fee");
		double recharge_fee = Double.valueOf(df.format(offlinerecharge.getRechargeAmount()*Double.valueOf(sysconf.getParvalue())));
		Moneyhistorydetail moneyhistorydetail= new Moneyhistorydetail();
		moneyhistorydetail.setUservip(offlinerecharge.getUservip());
		moneyhistorydetail.setAffectMoney(offlinerecharge.getRechargeAmount());
		moneyhistorydetail.setAvailableBalance(Double.valueOf(df.format(moneycount.getAvailableMoney()+offlinerecharge.getRechargeAmount())));
		moneyhistorydetail.setFrozenMoney(moneycount.getFrozenMoney());
		moneyhistorydetail.setCollectMoney(moneycount.getCollectTotalMoney());
		moneyhistorydetail.setIntroduction("易宝充值成功");
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
		}
		
		
		
		
		
		
		
		
			// 产品通用接口支付成功返回-浏览器重定向
			if(r9_BType.equals("1")) {
				out.println("callback方式:产品通用接口支付成功返回-浏览器重定向");
				// 产品通用接口支付成功返回-服务器点对点通讯
			} else if(r9_BType.equals("2")) {
				// 如果在发起交易请求时	设置使用应答机制时，必须应答以"success"开头的字符串，大小写不敏感
				out.println("SUCCESS");
			  // 产品通用接口支付成功返回-电话支付返回		
			}
			// 下面页面输出是测试时观察结果使用
			out.println("<br>交易成功!<br>商家订单号:" + r6_Order + "<br>支付金额:" + r3_Amt + "<br>易宝支付交易流水号:" + r2_TrxId);
		}
	} else {
		out.println("交易签名被篡改!");
	}
%>