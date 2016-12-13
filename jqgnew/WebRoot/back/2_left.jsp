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
<title>借款管理</title>
<link href="<%=basePath%>/back/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="<%=basePath%>/back/js/jquery.js"></script>

<script type="text/javascript">
$(function(){	
	//导航切换
	$(".menuson li").click(function(){
		$(".menuson li.active").removeClass("active");
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
	<div class="lefttop"><span></span>借款管理</div>
    <dl class="leftmenu">
    <dd>
    <div class="title"><span><img src="<%=basePath%>/back/images/leftico01.png" /></span>散户借款列表</div>
     <ul class="menuson">
        <li  
        <%if(key == null || key.length() <= 0){ %>
        class="active"
        <%} %>
        ><cite></cite><a href="<%=path %>/borrow/toBackLssuing?queryFlag=0&lssuingType=1" id="csdsID" target="rightFrame">初审待审核借款</a><i></i></li>
        <li
            <%if(key!=null&&key.equals("fushen")){ %>
        class="active"
        <%} %>
        ><cite></cite><a href="<%=path %>/borrow/toBackLssuing?queryFlag=1&lssuingType=1" target="rightFrame">复审待审核借款</a><i></i></li>
        <li><cite></cite><a href="<%=path %>/borrow/toBackLssuing?queryFlag=2&lssuingType=1" target="rightFrame">招标中借款</a><i></i></li>
        <li><cite></cite><a href="<%=path %>/borrow/toBackLssuing?queryFlag=3&lssuingType=1" target="rightFrame">还款中借款</a><i></i></li>
        <li><cite></cite><a href="<%=path %>/borrow/toBackLssuing?queryFlag=4&lssuingType=1" target="rightFrame">已完成借款</a><i></i></li>
         <li><cite></cite><a href="<%=path %>/borrow/toBackLssuing?queryFlag=5&lssuingType=1" target="rightFrame">初审未通过借款</a><i></i></li>
        <li><cite></cite><a href="<%=path %>/borrow/toBackLssuing?queryFlag=6&lssuingType=1" target="rightFrame">复审未通过借款</a><i></i></li>
        <li><cite></cite><a href="<%=path %>/borrow/overdueLassuing" target="rightFrame">已逾期借款</a><i></i></li>
        </ul>    
    </dd>
    
<!--      <dd> -->
<!--     <div class="title" id="title2"><span><img src="images/leftico01.png" /></span>企业直投</div> -->
<!--      <ul class="menuson"> -->
<!--        <li><cite></cite><a href="<%=path %>/borrow/toFirmView" target="rightFrame">添加企业直投</a><i></i></li> -->
<!--         <li><cite></cite><a href="<%=path %>/borrow/toBackLssuing?queryFlag=2&lssuingType=6" target="rightFrame">招标中借款</a><i></i></li> -->
<!--         <li><cite></cite><a href="<%=path %>/borrow/toBackLssuing?queryFlag=3&lssuingType=6" target="rightFrame">还款中借款</a><i></i></li> -->
<!--         <li><cite></cite><a href="<%=path %>/borrow/toBackLssuing?queryFlag=4&lssuingType=6" target="rightFrame">已完成借款</a><i></i></li> -->
<!--         </ul></dd> -->
<!--       <dd> -->
<!--     <div class="title" id="title3"><span><img src="images/leftico01.png" /></span>理财宝</div> -->
<!--      <ul class="menuson"> -->
<!--        <li><cite></cite><a href="<%=path %>/borrow/toLcView" target="rightFrame">添加理财宝</a><i></i></li> -->
<!--         <li><cite></cite><a href="<%=path %>/borrow/toBackLssuing?queryFlag=2&lssuingType=9" target="rightFrame">认购中的理财宝</a><i></i></li> -->
<!--         <li><cite></cite><a href="<%=path %>/borrow/toBackLssuing?queryFlag=3&lssuingType=9" target="rightFrame">还款中的理财宝</a><i></i></li> -->
<!--         <li><cite></cite><a href="<%=path %>/borrow/toBackLssuing?queryFlag=4&lssuingType=9" target="rightFrame">已完成的理财宝</a><i></i></li> -->
<!--      </ul></dd> -->
<!--      <dd> -->
<!--      <div class="title" id="title4"><span><img src="<%=basePath%>/back/images/leftico01.png" /></span>体验标</div> -->
<!--      <ul class="menuson"> -->
<!--        <li><cite></cite><a href="<%=path %>/borrow/toLssuing2" target="rightFrame">发布体验标</a><i></i></li> -->
<!--        <li><cite></cite><a href="<%=path %>/borrow/toBackLssuing?queryFlag=1&lssuingType=8" target="rightFrame">复审待审核体验标</a><i></i></li> -->
<!--        <li><cite></cite><a href="<%=path %>/borrow/toBackLssuing?queryFlag=2&lssuingType=8" target="rightFrame">招标中的体验标</a><i></i></li> -->
<!--        <li><cite></cite><a href="<%=path %>/borrow/toBackLssuing?queryFlag=3&lssuingType=8" target="rightFrame">还款中的体验标</a><i></i></li> -->
<!--        <li><cite></cite><a href="<%=path %>/borrow/toBackLssuing?queryFlag=4&lssuingType=8" target="rightFrame">已完成的体验标</a><i></i></li> -->
<!--      </ul></dd> -->
 <dd>
<%--     <div class="title" id="title3"><span><img src="images/leftico01.png" /></span>新手标</div> --%>
     <ul class="menuson">
        <li><cite></cite><a href="<%=path %>/borrow/toLssuingNew" target="rightFrame">发布新手标</a><i></i></li>
       <li><cite></cite><a href="<%=path %>/borrow/toBackLssuing?queryFlag=0&lssuingType=7" target="rightFrame">初审待审核新手标</a><i></i></li>
       <li><cite></cite><a href="<%=path %>/borrow/toBackLssuing?queryFlag=1&lssuingType=7" target="rightFrame">复审待审核新手标</a><i></i></li>
       <li><cite></cite><a href="<%=path %>/borrow/toBackLssuing?queryFlag=2&lssuingType=7" target="rightFrame">招标中的新手标</a><i></i></li>
       <li><cite></cite><a href="<%=path %>/borrow/toBackLssuing?queryFlag=3&lssuingType=7" target="rightFrame">还款中的新手标</a><i></i></li>
       <li><cite></cite><a href="<%=path %>/borrow/toBackLssuing?queryFlag=4&lssuingType=7" target="rightFrame">已完成的新手标</a><i></i></li>
     </ul>
 </dd>
      <dd> 
 		<div class="title" id="title5"><span><img src="images/leftico01.png" /></span>债权转让</div>  
         <ul class="menuson">  
      	 <li><cite></cite><a href="<%=path %>/transfer/findTransger1" target="rightFrame">转让申请列表</a><i></i></li> 
         <li><cite></cite><a href="<%=path %>/transfer/findTransger2" target="rightFrame">转让记录</a><i></i></li>  
         </ul></dd>  
   </dl>
    
</body>
</html>
