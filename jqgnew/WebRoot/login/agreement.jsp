<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%
String path = request.getContextPath(); 
String basePath = "";if(request.getServerPort()==80){basePath = request.getScheme()+"://"+request.getServerName()+path+"/";}else{basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";}  
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户登录页面</title>

<link href="../css/public.css" type="text/css" rel="stylesheet"/>

<link href="../css/reglogin.css" type="text/css" rel="stylesheet" />
<!--导航下拉菜单 js-->
<script type="text/javascript" src="../js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="../js/iepng.js"></script>
</head>
<body> 
<!--头部结束-->
<jsp:include page="../header.jsp" />
<!--服务条款 开始-->
<div class="agreement" style="width:1200px;margin:40px auto 10px auto;">
	<div class="ment">
    	<div class="menttop"></div>
        <div class="mentmiddle">
        	<h1>服务协议</h1>
            <p style="text-align: center">&nbsp;</p>
            <c:forEach var="latestnews" items="${latestnews}">
            <p>${latestnews.content}</p>
            </c:forEach>
       </div>
        <div class="mentfoot"></div>
    </div>
</div>
<!--服务条款 结束-->
 <div class="footer">
<jsp:include page="../footer.jsp" />
</div>
</body>
</html>