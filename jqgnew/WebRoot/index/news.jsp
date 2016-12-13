<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
  <%@ taglib uri="/struts-tags" prefix="s"%>
  <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();if(path!=null && !path.equals("")){	if(path.contains("/")){		if(path.equals("/")){path = "";	}}else{	path += "/";}} 
String basePath = "";if(request.getServerPort()==80){basePath = request.getScheme()+"://"+request.getServerName()+path+"/";}else{basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";}    
String flag = request.getParameter("flag");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="icon" href="<%=path %>/image/favicon/favicon.ico" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>首页-${frontmenu.name}</title>
<meta name="Keywords" content="招米贷,互联网理财平台,骨科贷款,安全理财平台" />
<meta name="description" content="招米贷是国米资产旗下的互联网理财平台，是上海交通大学互联网金融副会长单位，值得信赖的安全理财平台。" />

<link href="../css/public.css" type="text/css" rel="stylesheet"/>
<link href="../css/about.css" type="text/css" rel="stylesheet" />
<!--导航下拉菜单 js-->
<script type="text/javascript" src="../js/jquery-1.8.2.min.js"></script>
<script type="text/javascript">
function getJSONData(currentPage,pageSize) {
	var url = "news/page.action?currentPage="+currentPage+"&pageSize="+pageSize +"&flag=<%=flag%>"+"&temp=" +Math.random() ;
	 $.getJSON(url,function(data){
		var cp = data.currentPage;
		var tp = data.totalPage;
		var  $ul =  $("#listnews");
		 $ul.empty();
		str = '  ';  
		var jsonRoot = data.jsonRoot;
		for(var i = 0; i < jsonRoot.length; i++) {
			str += '<li class="png"><span class="zh_spec78"><a href="<%=path%>/news/newdetail?newsId='+ jsonRoot[i].newsId + '"> '
                 + decodeURI(jsonRoot[i].title, "utf-8") + '</a></span><span class="zh_spec79">'
                 + jsonRoot[i].time+ '</span></li>  ';
		}         
		 $ul.html(str);
		 $("#currentPage").text("第"+cp+"页");
		 $("#totalPage").text("共"+tp+"页");
		 $("#firstPage").attr("href","javascript:getJSONData(1,10)");
		 $("#prevPage").attr("href","javascript:getJSONData("+(parseInt(cp)-1)+",10)");
		 $("#nextPage").attr("href","javascript:getJSONData("+(parseInt(cp)+1)+",10)");
		 $("#lastPage").attr("href","javascript:getJSONData("+(parseInt(tp))+",10)");
	});
}
 $(function() {  
	getJSONData(1,10);  
});
 

</script>

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
        
        
        <div class="mem10_content">
        <!-- 文章单页 -->
            <ul  id="listnews">
            </ul>
        <div class="mem13-3">
               <a  id="firstPage"  href="">首页</a> 
               <a id="prevPage" href="">上一页</a>       
               <a id="nextPage" href="">下一页</a> 
               <a id="lastPage" href="">尾页</a> 
               <span id="currentPage">第1页</span>/<span id="totalPage">共1页</span>
            </div>
        <!-- 文章单页 -->
                    <div class="gg_nr">
            <ul>        
				          
		
            </ul>
          </div>
    </div>
</div>
</div>
 
</div>
<!--footer 开始-->
</div>
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