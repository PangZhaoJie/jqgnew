<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
  <%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();if(path!=null && !path.equals("")){	if(path.contains("/")){		if(path.equals("/")){path = "";	}}else{	path += "/";}} 
String basePath = "";if(request.getServerPort()==80){basePath = request.getScheme()+"://"+request.getServerName()+path+"/";}else{basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";}    
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
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

  
	 <%-- <div class="lefttop"><span></span>全局</div> --%>
     <dl class="leftmenu">
      <dd>
       <div class="title"><span><img src="images/leftico01.png" /></span>管理信息</div>
       <ul class="menuson">
        <li class="active"><cite></cite><a href="<%=path %>/overall/index" target="rightFrame">欢迎页</a><i></i></li>

        <li><cite></cite><a href="<%=path %>/overall/findWebsite" target="rightFrame">网站设置</a><i></i></li>
        <li><cite></cite><a href="1_link.jsp" target="rightFrame">友情链接</a><i></i></li>
        </ul>    
    </dd>
    <dd>
    <div class="title"> <span><img src="images/leftico02.png" /></span>操作日志</div>
      <ul class="menuson">
        <li><cite></cite><a href="1_log.jsp" target="rightFrame">后台操作日志</a><i></i></li>
       </ul>     
    </dd>
    
  </dl>


</body>
</html>
