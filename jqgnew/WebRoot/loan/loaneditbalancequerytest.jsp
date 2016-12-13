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
<title>网贷余额查询测试 - 乾多多</title>
</head>
<body>
	<form action="<%=basePath %>loan/testtestloanbalancequery2.action" method="post">
		查询乾多多账户标识<input id="PlatformId" name="PlatformId" value="" type="text" />例:m1
		<br/>
		平台乾多多账户类型<input id="PlatformType" name="PlatformType" value="" type="text" />(1.托管账户 2.自有账户)当查询平台账户时必填
		<br/>
		平台标识<input id="PlatformMoneymoremore" name="PlatformMoneymoremore" value="${PlatformMoneymoremore}" type="text" />例:p1
		<br/>
		<input value="提交" type="submit" />
	</form>
</body>
</html>
