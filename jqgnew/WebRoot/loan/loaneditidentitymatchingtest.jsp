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
<meta http-equiv="Cache-Control" content="no-cache,no-store,must-revalidate" />
<meta http-equiv="Expires" content="0" />
<title>网贷姓名匹配测试 - 乾多多</title>
</head>
<body>
	<form action="<%=basePath %>loan/testtestidentitymatching2.action" method="post">
		姓名1<input id="RealName" name="RealName" value="${RealName}" type="text" />
		<br/>
		身份证1<input id="IdentificationNo" name="IdentificationNo" value="${IdentificationNo}" type="text" />
		<br/>
		姓名2<input id="RealName2" name="RealName2" value="${RealName2}" type="text" />
		<br/>
		身份证2<input id="IdentificationNo2" name="IdentificationNo2" value="${IdentificationNo2}" type="text" />
		<br/>
		平台标识<input id="PlatformMoneymoremore" name="PlatformMoneymoremore" value="${PlatformMoneymoremore}" type="text" />例:p1
		<br/>
		<!--
		备注1<input id="Remark1" name="Remark1" value="${Remark1}" type="text" />
		<br/>
		备注2<input id="Remark2" name="Remark2" value="${Remark2}" type="text" />
		<br/>
		备注3<input id="Remark3" name="Remark3" value="${Remark3}" type="text" />
		<br/>
		-->
		<input value="提交" type="submit" />
	</form>
</body>
</html>
