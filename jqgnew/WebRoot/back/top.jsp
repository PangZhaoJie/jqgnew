<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
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
<title>无标题文档</title>
<link href="<%=path %>/back/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path %>/back/js/jquery.js"></script>
<script language="JavaScript" src="<%=path %>/back/js/top.js"></script>
</head>

<body style="background:url(<%=path %>/back/images/topbg.gif) repeat;">
<input type="hidden" id="path" value="<%=path %>" />
	<div style="overflow: hidden;">
    <div class="topleft">
    <a href="<%=path %>" target="_blank"><img src="<%=path %>/back/images/logo.png" title="系统首页" /></a>
    </div>
        
    
    <input type="hidden" id="paht" value="<%=path %>"/>        
    <div class="topright">    
    <ul>
    <li><a href="<%=basePath %>" target="view_window">查看前台</a></li>
    <li><a href="<%=path %>/overall/out" target="_parent">注销用户</a></li>
    </ul>
     
    <div class="user">
    <span>${manager.managerName}</span>
    
    
    </div>    
    
    </div>
	</div>
	<div style="overflow: hidden;">
		<ul class="nav">

    <li><a href="#"  onclick="overall()" class="selected"><h2>全局</h2></a></li>
    <li><a href="<%=path %>/borrow/toBackLssuing?queryFlag=0&lssuingType=1" target="rightFrame" onclick="money()"><h2>借款管理</h2></a></li>
    <li><a href="#" onclick="loans();"><h2>会员管理</h2></a></li>
    <li><a href="<%=path %>/back/offline.action?flag=0"  target="rightFrame"onclick="user();"><h2>充值提现</h2></a></li>
    <li><a href="#" onclick="recharge();"><h2>文章管理</h2></a></li>
    <li><a href="#"  onclick="menu()"><h2>菜单管理</h2></a></li>
    <li><a href="<%=path %>/money/toUserMoney"  target="rightFrame"onclick="fund();"><h2>资金统计</h2></a></li>
    <li><a href="#" onclick="power();"><h2>管理员</h2></a></li>
    <li><a href="<%=path %>/overall/findlssh"  target="rightFrame"onclick="expand();"><h2>扩展管理</h2></a></li>

       

    </ul>
	</div>
</body>
</html>
