<%@page import="java.util.ResourceBundle"%>
<%@ page contentType="text/html; charset=GBK"%>
<%
   request.setCharacterEncoding("GBK");
   String version = request.getParameter("version");
   String charset = request.getParameter("charset");
   String language = request.getParameter("language");
   String signType = request.getParameter("signType");
   String tranCode = request.getParameter("tranCode");
   String merchantID = request.getParameter("merchantID");
   String merOrderNum = request.getParameter("merOrderNum");
   String tranAmt = request.getParameter("tranAmt");
   String feeAmt = request.getParameter("feeAmt");
   String frontMerUrl = request.getParameter("frontMerUrl");
   String backgroundMerUrl = request.getParameter("backgroundMerUrl");
   String tranDateTime = request.getParameter("tranDateTime");
   String tranIP = request.getParameter("tranIP");
   String respCode = request.getParameter("respCode");
   String msgExt = request.getParameter("msgExt");
   String orderId = request.getParameter("orderId");
   String gopayOutOrderId = request.getParameter("gopayOutOrderId");
   String bankCode = request.getParameter("bankCode");
   String tranFinishTime = request.getParameter("tranFinishTime");
   String merRemark1 =  request.getParameter("merRemark1");
   String merRemark2 =  request.getParameter("merRemark2");
   String VerficationCode = ResourceBundle.getBundle("mpi").getString("VerficationCode");
   String signValueFromGopay = request.getParameter("signValue");

   // 组织加密明文
   String plain = "version=[" + version + "]tranCode=[" + tranCode + "]merchantID=[" + merchantID + "]merOrderNum=[" + merOrderNum + "]tranAmt=[" + tranAmt + "]feeAmt=[" + feeAmt+ "]tranDateTime=[" + tranDateTime + "]frontMerUrl=[" + frontMerUrl + "]backgroundMerUrl=[" + backgroundMerUrl + "]orderId=[" + orderId + "]gopayOutOrderId=[" + gopayOutOrderId + "]tranIP=[" + tranIP + "]respCode=[" + respCode + "]gopayServerTime=[]VerficationCode=[" + VerficationCode + "]";
%>

<%
	String signValue = GopayUtils.md5(plain);
	
	if(signValue.equals(signValueFromGopay)){
// 		response.getWriter().write("RespCode=0000|JumpURL=http://127.0.0.1:8080/gopayTest/true.jsp");
		if("0000".equals(RespCode)){
			DecimalFormat df = new DecimalFormat("##.00");
			
			
			String offlinerechargeId=merOrderNum;
			
			
			
			OfflinerechargeService offlinerechargeService=new OfflinerechargeServiceImpl();
			SystemconfService systemconfService = new SystemconfServiceImpl();
			
			Offlinerecharge offlinerecharge=  offlinerechargeService.findOfflinerechargeById(Integer.parseInt(offlinerechargeId));
			if(offlinerecharge.getRechargeStatus()!=1){
			offlinerecharge.setCheckOrderNum(TransID);
			offlinerecharge.setRechargeStatus(1);
			offlinerechargeService.updateOfflinerecharge(offlinerecharge);
			
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
			moneyhistorydetail.setIntroduction("国付宝充值成功");
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
			}
			}
	}
	else{
		response.getWriter().write("RespCode=9999|JumpURL=");
	}
%> 
