<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragrma", "no-cache");
	response.setDateHeader("Expires", 0);
	
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<title>网贷提现测试 - 乾多多</title>
</head>
<body>
	<form action="<%=basePath %>loan/testtestloanwithdraws2.action" method="post">
		提现人<input id="WithdrawMoneymoremore" name="WithdrawMoneymoremore" value="" type="text" />例:m1
		<br/>
		订单号<input id="OrderNo" name="OrderNo" value="${OrderNo}" type="text" />
		<br/>
		金额<input id="Amount" name="Amount" value="${Amount}" type="text" />
		<br/>
		平台承担手续费比例<input id="FeePercent" name="FeePercent" value="${FeePercent}" type="text" />[0.00-100.00]
		<br/>
		用户承担的最高手续费<input id="FeeMax" name="FeeMax" value="${FeeMax}" type="text" />
		<br/>
		上浮费率<input id="FeeRate" name="FeeRate" value="${FeeRate}" type="text" />例:0.0050
		<br/>
		卡号<input id="CardNo" name="CardNo" value="${CardNo}" type="text" />
		<br/>
		卡类型<input id="CardType" name="CardType" value="${CardType}" type="text" />(0.借记卡 1.信用卡)
		<br/>
		银行代码<input id="BankCode" name="BankCode" value="${BankCode}" type="text" />
		<br/>
		开户行名称<input id="BranchBankName" name="BranchBankName" value="${BranchBankName}" type="text" />
		<br/>
		开户行省份<input id="Province" name="Province" value="${Province}" type="text" />
		<br/>
		开户行城市<input id="City" name="City" value="${City}" type="text" />
		<br/>
		平台标识<input id="PlatformMoneymoremore" name="PlatformMoneymoremore" value="${PlatformMoneymoremore}" type="text" />例:p1
		<br/>
		备注1<input id="Remark1" name="Remark1" value="${Remark1}" type="text" />
		<br/>
		备注2<input id="Remark2" name="Remark2" value="${Remark2}" type="text" />
		<br/>
		备注3<input id="Remark3" name="Remark3" value="${Remark3}" type="text" />
		<br/>
		<input value="提交" type="submit" />
	</form>
</body>
</html>
