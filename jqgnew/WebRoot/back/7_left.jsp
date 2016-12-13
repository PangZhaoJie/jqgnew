<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%    
	String path = request.getContextPath();if(path!=null && !path.equals("")){	if(path.contains("/")){		if(path.equals("/")){path = "";	}}else{	path += "/";}}    
	String basePath = "";if(request.getServerPort()==80){basePath = request.getScheme()+"://"+request.getServerName()+path+"/";}else{basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";}      
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>会员管理</title>
<link href="<%=basePath %>/back/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="<%=basePath %>/back/js/jquery.js"></script>

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
 <div class="lefttop"><span></span>资金统计</div>
   <dl class="leftmenu" >
     <dd>
       <div class="title"><span><img src="<%=basePath %>/back/images/leftico01.png" /></span>会员账户</div>
       <ul class="menuson">
          <li><cite></cite><a href="<%=path %>/money/toUserMoney" target="rightFrame">会员账户</a><i></i></li>
       </ul>
     </dd>
    
     <dd>
      <div class="title"><span><img src="<%=basePath %>/back/images/leftico01.png" /></span>充值提现</div>
       <ul class="menuson">
        <li><cite></cite><a href="<%=path %>/back/offline.action?flag=2" target="rightFrame">充值记录总表</a><i></i></li>
        <li><cite></cite><a href="<%=path %>/back/translate5" target="rightFrame">提现记录总表</a><i></i></li>
       </ul>    
    </dd>
    
     <dd>
       <div class="title"><span><img src="<%=basePath %>/back/images/leftico01.png" /></span>会员资金变动记录</div>
       <ul class="menuson">
          <li><cite></cite><a href="<%=path %>/money/toMoneyhistorydetail" target="rightFrame">会员资金变动记录</a><i></i></li>
		  <li><cite></cite><a href="<%=path %>/back/recommondTenderList" target="rightFrame">推荐投资记录</a><i></i></li>
		  <li><cite></cite><a href="<%=path %>/back/findRecord" target="rightFrame">投标记录</a></li>
       </ul>
     </dd>
      <dd>
       <div class="title"><span><img src="<%=basePath %>/back/images/leftico01.png" /></span>网站资金统计</div>
       <ul class="menuson">
          <li><cite></cite><a href="<%=path %>/money/toMoneyTotal" target="rightFrame">网站资金统计</a><i></i></li>
       </ul>
     </dd>
    </dl>
    
</body>
</html>
