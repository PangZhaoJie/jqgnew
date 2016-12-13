<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%    
	String path = request.getContextPath();if(path!=null && !path.equals("")){	if(path.contains("/")){		if(path.equals("/")){path = "";	}}else{	path += "/";}}    
	String basePath = "";if(request.getServerPort()==80){basePath = request.getScheme()+"://"+request.getServerName()+path+"/";}else{basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";}     
	String key = request.getParameter("key");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>充值提现</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>

<script type="text/javascript">
$(function(){	
	//导航切换
	$(".menuson li").click(function(){
		$(".menuson li.active").removeClass("active")
		$(this).addClass("active");
	});
    <%if(key!=null &&(!key.equals("chongzhi"))){ %>
    $('#title1').next('ul').slideUp();
    $('#title2').next('ul').slideDown();
    <%} %>
	$('.title').click(function(){
		var $ul = $(this).next('ul');
		$('#dd').find('ul').slideUp();
		if($ul.is(':visible')){
			$(this).next('ul').slideUp();
		}else{
			$(this).next('ul').slideDown();
		}
	});
	$('.title1').click(function(){
		var $ul = $(this).next('ul');
		$('#dd1').find('ul').slideUp();
		if($ul.is(':visible')){
			$(this).next('ul').slideUp();
		}else{
			$(this).next('ul').slideDown();
		}
	});
})	
</script>


</head>

<body style="background:#f0f9fd;">
	<div class="lefttop"><span></span>充值提现</div>
    <dl class="leftmenu">
    <dd id="dd">
    <div class="title" id="title1"><span><img src="images/leftico01.png" /></span>充值管理</div>
     <ul class="menuson">

        <li  
             <%if(key == null || key.length() <= 0){ %>
        class="active"
        <%} %>
        ><cite></cite><a href="<%=path %>/back/offline.action?flag=0" target="rightFrame">线上充值</a><i></i></li>
        <li
                <%if(key!=null&&key.equals("chongzhi")){ %>
        class="active"
        <%} %>
        ><cite></cite><a href="<%=path %>/back/offline.action?flag=1" target="rightFrame">线下充值</a><i></i></li>


        <li><cite></cite><a href="<%=path %>/back/offline.action?flag=2" target="rightFrame">充值记录总列表</a><i></i></li>
     </ul>
     </dd>
    <dd id="dd1">
    <div class="title" id="title2"><span><img src="images/leftico01.png" /></span>提现管理</div>
     <ul class="menuson">
        <li
                <%if(key!=null&&key.equals("tixian")){ %>
        class="active"
        <%} %>
        ><cite></cite><a href="<%=path %>/back/translate1" target="rightFrame">待审核提现</a><i></i></li>
        <li><cite></cite><a href="<%=path %>/back/translate2" target="rightFrame">审核通过，处理中</a><i></i></li>
        <li><cite></cite><a href="<%=path %>/back/translate3" target="rightFrame">已提现</a><i></i></li>
        <li><cite></cite><a href="<%=path %>/back/translate4" target="rightFrame">审核未通过</a><i></i></li>
        <li><cite></cite><a href="<%=path %>/back/translate5" target="rightFrame">提现申请总列表</a><i></i></li>
     </ul>     
	</dd>
   </dl>
    
</body>
</html>
