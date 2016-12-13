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
<link rel="icon" href="<%=path %>/image/favicon/favicon.ico" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>首页-${frontmenu.name}</title>
<link href="../css/public.css" type="text/css" rel="stylesheet"/>
<link href="../css/about.css" type="text/css" rel="stylesheet" />
<!--导航下拉菜单 js-->
<script type="text/javascript" src="../js/jquery-1.8.2.min.js"></script>


</head>
<body> 
<!--头部开始-->
<div class="header">
        <jsp:include page="../header.jsp" />
    
</div>
<!--头部结束-->

 <div class="conbox pt10">
 	<div style="width:100%; background:#ececec; padding-top:10px; padding-bottom:20px;">
<div class="mem9">
    <ul>
        <li class="mem_spec43"><a class="<c:if test="${frontmenufather.frontMenuId==27}">active</c:if>" href="<%=path%>/news/new?flag=Introduction">关于我们</a></li>
        <li class="mem_spec44"><a class="<c:if test="${frontmenufather.frontMenuId==49}">active</c:if>" href="<%=path%>/news/new?flag=control">风控保障</a></li>
        <li class="mem_spec45"><a class="<c:if test="${frontmenufather.frontMenuId==1}">active</c:if>" href="<%=path%>/news/new?flag=industry">网站新闻</a></li>
        <li class="mem_spec46"><a class="<c:if test="${frontmenu.frontMenuId==50}">active</c:if>" href="<%=path%>/news/new?flag=cooperative">合作伙伴</a></li>
        <li class="mem_spec47"><a class="<c:if test="${frontmenu.frontMenuId==45}">active</c:if>" href="<%=path%>/news/new?flag=contact">联系我们</a></li>    
   </ul>
</div>
<div style="clear:both;"></div>
<div class="mem10">
    <ul class="mem_spec48"> 
    
    <c:forEach  var="frontmenus" items="${frontmenus}">	
				     <li><a  class="<c:if test="${frontmenus.frontMenuCode==flag}">active</c:if>" href="<%=path%>${frontmenus.url}">${frontmenus.name}</a></li>
			   </c:forEach>  
		 <div style="clear:both;"></div>
        </ul>    
        <div class="mem10_content">
        <!-- 文章单页 -->
                    <div class="article_content">
                    <c:forEach  var="latestnews" items="${latestnews}">	
				           ${latestnews.content}
			 </c:forEach>
            </div>
    </div>
</div>
</div>
</div>
<!--footer 开始-->
<div class="footer">
<jsp:include page="../footer.jsp" />
</div>
<!--footer 结束-->


<!--右边漂浮 开始-->
<%-- <div id="service">
	<a href="<%=path%>/tool/borrow_tool.jsp" class="srvLog"   title="网贷计算器">网贷计算器</a>
	<a href="tencent://message/?uin=285772998&Site=企业网站&Menu=yes" class="srvCns" title="官方交流群">官方交流群</a>
	<a href="<%=path%>/tender/totenderList" class="srvDj"   title="投资理财">投资理财</a>
	<a class="goTop" id="goTop" title="返回顶部" style="display:none" >返回顶部</a>
</div>
<script type="text/javascript" src="../js/service.js">	</script> --%>
<!--右边漂浮 结束-->
</body>
</html>