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
<title>网贷三合一测试 - 乾多多</title>
</head>
<body>
	<form action="<%=basePath %>loan/testtestloanfastpay2.action" method="post">
		乾多多标识<input id="MoneymoremoreId" name="MoneymoremoreId" value="${MoneymoremoreId}" type="text" />
		<br/>
		平台标识<input id="PlatformMoneymoremore" name="PlatformMoneymoremore" value="${PlatformMoneymoremore}" type="text" />例:p1
		<br/>
		操作类型<input id="Action" name="Action" value="${Action}" type="text" />(1.用户认证 2.银行卡绑定 3.代扣授权 4.取消代扣授权 5.汇款绑定银行卡确认)
		<br/><br/>
		
		汇款绑定银行卡确认、代扣取消
		<br/>
		银行卡号<input id="CardNo" name="CardNo" value="${CardNo}" type="text" />
		<br/><br/>
		
		代扣
		<br/>
		代扣开始日期<input id="WithholdBeginDate" name="WithholdBeginDate" value="${WithholdBeginDate}" type="text" />
		<br/>
		代扣结束日期<input id="WithholdEndDate" name="WithholdEndDate" value="${WithholdEndDate}" type="text" />
		<br/>
		单笔代扣限额<input id="SingleWithholdLimit" name="SingleWithholdLimit" value="${SingleWithholdLimit}" type="text" />
		<br/>
		代扣总限额<input id="TotalWithholdLimit" name="TotalWithholdLimit" value="${TotalWithholdLimit}" type="text" />
		<br/><br/>
		
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
