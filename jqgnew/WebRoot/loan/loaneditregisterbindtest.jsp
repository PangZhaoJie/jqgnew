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
<title>网贷注册绑定测试 - 乾多多</title>
</head>
<body>
	<form action="<%=basePath %>loan/testtestloanregisterbind2.action" method="post">
		注册类型<input id="RegisterType" name="RegisterType" value="${RegisterType}" type="text" />(1.全自动 2.半自动)
		<br/>
		平台标识<input id="PlatformMoneymoremore" name="PlatformMoneymoremore" value="${PlatformMoneymoremore}" type="text" />例:p1
		<br/>
		账户类型<input id="AccountType" name="AccountType" value="${AccountType}" type="text" />(空.个人 1.企业)
		<br/>
		手机号<input id="Mobile" name="Mobile" value="${Mobile}" type="text" />
		<br/>
		邮箱<input id="Email" name="Email" value="${Email}" type="text" />
		<br/>
		真实姓名<input id="RealName" name="RealName" value="${RealName}" type="text" />
		<br/>
		身份证号<input id="IdentificationNo" name="IdentificationNo" value="${IdentificationNo}" type="text" />
		<br/>
		平台账号<input id="LoanPlatformAccount" name="LoanPlatformAccount" value="${LoanPlatformAccount}" type="text" />
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
