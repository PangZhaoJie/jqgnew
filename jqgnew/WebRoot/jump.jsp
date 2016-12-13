<%@page import="com.jqg.service.TenderService"%>
<%@page import="com.jqg.service.impl.TenderServiceImpl"%>
<%@page import="com.jqg.pojo.Tender"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page import="com.jqg.pojo.Lssuing"%>
<%@page import="java.util.List"%>
<%@page import="java.text.DecimalFormat"%>
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
<title>${websiteIndex.homeTitle}</title>
<meta name="Keywords" content="${websiteIndex.webKeyword}" />
<meta name="descripton" content="${websiteIndex.webDeescription}" />
 <meta http-equiv="refresh" content="${MyMessage.time };URL=<%=basePath%>${MyMessage.url}">
<link href="<%=basePath%>css/public.css" type="text/css" rel="stylesheet" />
<link href="<%=basePath%>css/index.css" type="text/css" rel="stylesheet" />
<link href="<%=basePath%>/css/reglogin.css" type="text/css"
	rel="stylesheet" />
	<link href="../css/new.css" type="text/css" rel="stylesheet" />
<!--导航下拉菜单 js-->
<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.2.min.js"></script>
<!--banner js-->

<script type="text/javascript" src="<%=basePath%>js/jquery.flexslider-min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/login/login.js"></script>
<!--右侧浮动 js-->
<%-- <script type="text/javascript" src="js/jquery.service.js"></script> --%>
<%-- <script type="text/javascript">$(function(){$("#hhService").fix({float:'left',minStatue:false,skin:'green',durationTime:500})});</script> --%>


	
</head>
<body>
	<!--头部开始-->
	<div class="header">

		<jsp:include page="header.jsp" />
	</div>
	<!--头部结束-->
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
					href="<%=basePath%>">返回首页</a>
			</div>

		</div>
		</div>
	</div>
	<!--合作结束 开始-->

	<!--footer 开始-->
	<div class="footer">
		<jsp:include page="footer.jsp" />
	</div>
	<!--footer 结束-->

</body>
</html>