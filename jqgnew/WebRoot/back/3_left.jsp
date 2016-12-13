<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
  <%@ taglib uri="/struts-tags" prefix="s"%>
  	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<%
String path = request.getContextPath();if(path!=null && !path.equals("")){	if(path.contains("/")){		if(path.equals("/")){path = "";	}}else{	path += "/";}} 
String basePath = "";if(request.getServerPort()==80){basePath = request.getScheme()+"://"+request.getServerName()+path+"/";}else{basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";}    
String key = request.getParameter("key");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>会员管理</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>

<script type="text/javascript">
$(function(){	
	//导航切换
	$(".menuson li").click(function(){
		$(".menuson li.active").removeClass("active")
		$(this).addClass("active");
	});
    <%if(key!=null ){ %>
    $('#title1').next('ul').slideUp();
    $('#title2').next('ul').slideDown();
    <%} %>
	
	
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

	<div class="lefttop"><span></span>会员管理</div>
    
    <dl class="leftmenu" >
        
    <dd>
    <div class="title"  id="title1"><span><img src="images/leftico01.png" /></span>会员管理</div>
     <ul class="menuson">
        <li class="active"><cite></cite><a href="3_list.jsp" target="rightFrame">会员列表</a><i></i></li>
      
     </ul>    
    </dd>
    
     <dd>
    <div class="title" id="title2"><span><img src="images/leftico01.png" /></span>申请管理</div>
     <ul class="menuson">
      <li ><cite></cite><a href="3_list2.jsp" target="rightFrame">邮箱未认证会员列表</a><i></i></li>
        <li ><cite></cite><a href="3_list1.jsp" target="rightFrame">手机未认证会员列表</a><i></i></li>
        <li
        <%if(key!=null&&key.equals("shipin")){ %>
        class="active"
        <%} %>
        ><cite></cite><a href="3_list3.jsp" target="rightFrame">视频认证申请</a><i></i></li>
        <li
              <%if(key!=null&&key.equals("xianchang")){ %>
        class="active"
        <%} %>
        ><cite></cite><a href="3_list4.jsp"target="rightFrame">现场认证申请</a><i></i></li>
        <li
              <%if(key!=null&&key.equals("shiming")){ %>
        class="active"
        <%} %>
        ><cite></cite><a href="3_namelist.jsp" target="rightFrame">实名认证申请</a><i></i></li>
        <li
              <%if(key!=null&&key.equals("edu")){ %>
        class="active"
        <%} %>
        ><cite></cite><a href="3_list5.jsp" target="rightFrame">信用额度申请审核</a><i></i></li>
        <li
              <%if(key!=null&&key.equals("vip")){ %>
        class="active"
        <%} %>
        ><cite></cite><a href="3_vipList.jsp" target="rightFrame">VIP会员申请</a><i></i></li>
        
        </ul>    
    </dd>
        
    
     
    
    
      
    
    
       
    
    </dl>
    
</body>
</html>
