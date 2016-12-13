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
<title>网贷对账测试 - 乾多多</title>
</head>
<body>
	<form action="<%=basePath %>loan/testtestloanorderquery2.action" method="post">
		平台标识<input id="PlatformMoneymoremore" name="PlatformMoneymoremore" value="${PlatformMoneymoremore}" type="text" />例:p1
		<br/>
		查询类型<input id="Action" name="Action" value="" type="text" size="30" />(空.转账 1.充值 2.提现)
		<br/>
		乾多多流水号<input id="LoanNo" name="LoanNo" value="" type="text" size="30" />
		<br/>
		平台订单号<input id="OrderNo" name="OrderNo" value="" type="text" size="30" />
		<br/>
		平台标号<input id="BatchNo" name="BatchNo" value="" type="text" size="30" />
		<br/>
		开始时间<input id="BeginTime" name="BeginTime" value="${BeginTime}" type="text" />
		<br/>
		结束时间<input id="EndTime" name="EndTime" value="${EndTime}" type="text" />
		<br/>
		<input value="提交" type="submit" />
	</form>
</body>
</html>
