<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
<title>扩展管理</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>

<script type="text/javascript">
$(function(){	
	//导航切换
	$(".menuson li").click(function(){
		$(".menuson li.active").removeClass("active")
		$(this).addClass("active");
	});
	
	$('.title').click(function(){
		var $ul = $(this).next('ul');
		$('dd').find('ul').slideUp();
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
 <div class="lefttop"><span></span>扩展管理</div>
     <dl class="leftmenu" >
            <dd>
    <div class="title"><span><img src="images/leftico01.png" /></span>首页新标预告</div>
     <ul class="menuson">
        <li><cite></cite><a href="<%=path %>/overall/findlssh" target="rightFrame">首页新标预告</a><i></i></li>
  
     </ul>    
    </dd>
    <dd>
    <div class="title"><span><img src="images/leftico01.png" /></span>参数管理</div>
     <ul class="menuson">
        <li class="active"><cite></cite><a href="<%=path %>/use/searchAll" target="rightFrame">业务参数管理</a><i></i></li>
        <li><cite></cite><a href="<%=path %>/level/searchClevel" target="rightFrame">信用级别管理</a><i></i></li>
        <li><cite></cite><a href="<%=path %>/level/searchIlevel" target="rightFrame">投资级别管理</a><i></i></li>
     </ul>    
    </dd>
    
    <dd>
    <div class="title"><span><img src="images/leftico01.png" /></span>充值银行管理</div>
     <ul class="menuson">
        <li><cite></cite><a href="<%=path %>/back/getOnlineModel"  target="rightFrame">线上支付接口管理</a><i></i></li>
        <li><cite></cite><a href="<%=path %>/extension/searchBank" target="rightFrame">线下充值银行管理</a><i></i></li>
     </ul>    
    </dd>
    <dd>
     <div class="title"><span><img src="images/leftico01.png" /></span>在线客服管理</div>
     <ul class="menuson">
        <li><cite></cite><a href="<%=path %>/extension/searchManager" target="rightFrame">QQ客服管理</a><i></i></li>
        <li><cite></cite><a href="<%=path %>/extension/searchKf" target="rightFrame">QQ群管理</a><i></i></li>
        <li><cite></cite><a href="<%=path %>/extension/searchPhone" target="rightFrame">客服电话管理</a><i></i></li>
     </ul>    
    </dd>   
    <dd>
    <div class="title"><span><img src="images/leftico01.png" /></span>短信通知管理</div>
     <ul class="menuson">
        <li><cite></cite><a href="<%=path %>/extension/searchSmtp" target="rightFrame">短信通知接口管理</a><i></i></li>
        <li><cite></cite><a href="<%=path %>/extension/tab" target="rightFrame">短信通知模块管理</a><i></i></li>
        <li><cite></cite><a href="<%=path %>/extension/smsSendLog" target="rightFrame">短信发送记录</a><i></i></li>
        <li><cite></cite><a href="<%=path %>/extension/emailSendLog" target="rightFrame">邮件发送记录</a><i></i></li>
     </ul>    
    </dd>
     </dl>
</body>
</html>
