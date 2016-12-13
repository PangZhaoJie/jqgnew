
<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();if(path!=null && !path.equals("")){	if(path.contains("/")){		if(path.equals("/")){path = "";	}}else{	path += "/";}} 
String basePath = "";if(request.getServerPort()==80){basePath = request.getScheme()+"://"+request.getServerName()+path+"/";}else{basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";}  
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="icon" href="<%=path%>/image/favicon/favicon.ico"
	type="image/x-icon" />

 <meta http-equiv="refresh" content="${MyMessage.time};URL=<%=basePath%>${MyMessage.url}">

	
</head>
<body>

	<div class="showmessagediv">
	<div style="width: 600px; border: 1px solid rgb(204, 204, 204); margin: 90px auto;">
		<img src="<%=basePath%>/images/jump.jpg" width="600" height="20" />
		<div
			style="height: 40px; line-height: 40px; border-bottom: 2px solid #ccc; padding-left: 15px; font-family: '微软雅黑'; padding-left: 20px; font-weight: bold; font-size: 18px; color: #333;">提示信息</div>
		<div style="overflow: hidden; padding: 15px 0;">
			<div style="float:left; padding:5px 15px 0;">
			</div>
			<div style="float: left; font-family: '微软雅黑'; font-size: 14px;">
				<h3 style="color: red; padding: 15px 0; margin:0;">${MyMessage.msc
					}</h3>
				系统将在<span>${MyMessage.time }</span>秒后自动跳转，如果不想等待，直接点击<a
					href="<%=basePath%>${MyMessage.url}">跳转</a><br> 或者<a
					href="<%=basePath%>/back">返回后台首页</a>
			</div>

		</div>
		</div>
	</div>


</body>
</html>