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
<title>网贷充值测试 - 乾多多</title>
</head>
<body>
	<form action="<%=basePath %>loan/testtestloanrecharge2.action" method="post">
		充值人<input id="RechargeMoneymoremore" name="RechargeMoneymoremore" value="" type="text" />例:m1
		<br/>
		订单号<input id="OrderNo" name="OrderNo" value="${OrderNo}" type="text" />
		<br/>
		金额<input id="Amount" name="Amount" value="${Amount}" type="text" />
		<br/>
		充值类型<input id="RechargeType" name="RechargeType" value="${RechargeType}" type="text" />(空.网银充值 1.代扣充值 2.快捷支付 3.汇款充值 4.企业网银)
		<br/>
		手续费类型<input id="FeeType" name="FeeType" value="${FeeType}" type="text" />(1.自付 2.代付 3.分开付)
		<br/>
		银行卡号<input id="CardNo" name="CardNo" value="${CardNo}" type="text" />
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
