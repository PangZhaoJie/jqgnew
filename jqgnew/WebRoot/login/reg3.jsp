<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
    <%
String path = request.getContextPath(); 
if(path!=null && !path.equals("")){	if(path.contains("/")){		if(path.equals("/")){path = "";	}}else{	path += "/";}} 
String basePath = "";if(request.getServerPort()==80){basePath = request.getScheme()+"://"+request.getServerName()+path+"/";}else{basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";}  
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="icon" href="<%=path %>/image/favicon/favicon.ico" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册页面</title>
<link href="<%=path%>/css/public.css" type="text/css" rel="stylesheet"/>
<link href="<%=path%>/css/reg.css" type="text/css" rel="stylesheet"/>
<!--导航下拉菜单 js-->
<script type="text/javascript" src="<%=path%>/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/login/register.js"></script>
</head>
<body>
<jsp:include page="../header.jsp" />
<!--注册开始-->
<div class="regmain">
	<div class="regbox">
    	<div class="reg_title"><h1>账户注册</h1></div>
      	<div class="reg_main">
      		<h2><img src="<%=path%>/images/reg3_03.jpg" width="732" height="52" /></h2>
            <div class="pic_title">
            	<ul>
                	<li class="pic_1">填写注册信息</li>
                    <li class="pic_2">验证注册信息</li>
                    <li class="pic_3">注册成功</li>
                </ul>
                <div class="clear"></div>
            </div>
            <div class="reg_complete">
            	<span>恭喜您！已完成注册</span><br /><a href="<%=path %>/index.action">返回首页</a>&nbsp;进入&nbsp;<a href="<%=path %>/user/login">我的账户</a>
            </div>
            <br><br><br><br><br><br>
   	  </div>
  </div>
</div>
<!--注册结束-->
<!--footer 开始-->
   <div class="footer">
<jsp:include page="../footer.jsp" />
</div>
<!--footer 结束-->
</body>
</html>
