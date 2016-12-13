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
<html>
<head>
<link rel="icon" href="<%=path %>/image/favicon/favicon.ico" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>论坛中心-首页</title>
<link href="<%=basePath %>/css/public.css" type="text/css" rel="stylesheet"/>
<link href="<%=basePath %>/css/bbs/bbs_text .css" type="text/css" rel="stylesheet" />
<!--导航下拉菜单 js-->
<script type="text/javascript" src="<%=basePath %>/js/jquery-1.8.2.min.js"></script>
 <script type="text/javascript">
	function submitBbs(){
 
			var form=document.getElementById("bbsviewFormId");
			form.action="bbs/toCommentAdd";
			form.submit();
  
	}
  function toPage(num){
	  var page=$("#currentPageId").val();
	  var pageNum=$("#pageNumId").val();
  	  if(num==1){
			  $("#page1-1").attr("href","<%=path %>/bbs/toBbsView?askId=${investstrategy.askId}&currentPage="+Number(pageNum)); 
	  }
	  if(num==2){
		  if(Number(page)<Number(pageNum)){
			  $("#page1-2").attr("href","<%=path %>/bbs/toBbsView?askId=${investstrategy.askId}&currentPage="+(Number(page)+1));
		  }
	  }
	  if(num==3){
		  if(page>1){
			  $("#page1-3").attr("href","<%=path %>/bbs/toBbsView?askId=${investstrategy.askId}&currentPage="+(Number(page)-1));
		  }
	  }
	  if(num==4){
	 
			  $("#page1-4").attr("href","<%=path %>/bbs/toBbsView?askId=${investstrategy.askId}&currentPage=1");
	  }
	  
  }
  
  function checkPage(){
	  if($("#currentPageId").val()!=1){
		  $("#listTable").hide();
	  }
  }
 
 </script>
</head>
<body onload="checkPage()"> 
<!--头部开始-->
<!--头部开始-->
<div class="header">
    <jsp:include page="../header.jsp" />
</div>
<!--头部结束-->


<div class="conbox pt10">
  <P class="guide">当前位置：<a href="#">网站首页</a> > <a href="<%=path %>/bbs/toBbsIndex">论坛中心</a> > ${investstrategy.askTitle}
  <div class="bbs">
  <div class="PBox">
     <div class="P">
         <div class="pText" style="width: 70px">
         <a href="<%=path %>/bbs/toType?askTypeFlag=0" >返回列表</a></div>
         <div class="pText"  style="width: 65px">
         <a href="" onclick="toPage('1')" id="page1-1">最后一页</a></div>
         <div class="pText"  style="width: 55px">
         <a href="" onclick="toPage('2')" id="page1-2">下一页</a></div>
         <div class="pText"  style="width: 55px"><a href="" onclick="toPage('3')" id="page1-3">上一页</a></div>
         <div class="pText"  style="width: 50px"><a href="" onclick="toPage('4')" id="page1-4">首页</a></div>
         <div class="pText"  style="width: 50px"><a href="">共${pageNum}页</a></div>
         <div class="pText"  style="width: 50px"><a href="">第1页</a></div>
         <input name="currentPage" value="${currentPage}" id="currentPageId" style="display: none"/>
         <input name="total" value="${pageNum}" id="pageNumId" style="display: none"/>
       </div>
	</div>
  
  	<div class="banzhu" id="listTable">
  	  <div class="leftBox">
   	    <div class="T">浏览：<span>360</span>&nbsp;|&nbsp;回复：<span>2</span></div>
            <div><img src="<%=basePath %>/images/touxiang_03.png" /></div>
            <div class="zuozhe"><a href="">${investstrategy.askUserName}</a></div>
        </div>
        <div class="rightBox">
        	<div class="biaoti">${investstrategy.askTitle}</div>
            <div class="xinxi">发表于<fmt:formatDate value="${investstrategy.askDate}" pattern="yyyy-MM-dd" /></div>
            <div class="text">
            	 <p>${investstrategy.askContent}</p>
            </div>
            
        </div>
    </div>
    <c:forEach var="gg" items="${usercommentList}">
    <div class="bigBox">
  		<div class="pinglun">
  		
  		  <div class="leftBox">
       	    <div class="touxiang"><img src="<%=basePath %>/images/touxiang_03.png" /></div>
           	<div class="zuozhe"><a href=""> ${gg.uservip.userName }</a></div>
       	  </div>
				<div class="rightBox">
        		<div class="biaoti">发表于 <fmt:formatDate value="${gg.commentDate}" pattern="yyyy-MM-dd HH:mm" /></div>
            	<div class="text">
            		<p> ${gg.content}</p>
            	</div>
         	</div>
   	  </div>
 
    </div></c:forEach>
    <div class="fatie">
    	<div class="top"><div>回复该帖</div></div>
 <form action="" id="bbsviewFormId" method="post">  	    
           
            <input name="userId" value="${session.uservip.userId}" style="display: none"/>
            <input name="askId" value="${investstrategy.askId}" style="display: none"/>
			<div class="textB">
            	<input type="text" class="text" name="usercomment.content" id="askContentId"/>
            </div>
         <div class="fabiao"><img src="<%=basePath %>/images/huitie_03.png" style="cursor: pointer;" onclick="submitBbs()"/>
        </div></form>
    </div>
  </div>
<span class="newsImge"></span>
</div>
<!--footer 开始-->
<div class="footer">
  <jsp:include page="../footer.jsp" />
</div>
<!--footer 结束-->


<!--右边漂浮 开始-->
<%-- <div id="service">
	<a href="#" class="srvLog" target="_blank" title="网贷计算器">网贷计算器</a>
	<a href="tencent://message/?uin=285772998&Site=企业网站&Menu=yes" class="srvCns" title="联系客服">联系客服</a>
	<a href="#" class="srvDj" target="_blank" title="在线充值">在线充值</a>
	<a class="goTop" id="goTop" title="返回顶部" style="display:none" >返回顶部</a>
</div>
<script type="text/javascript" src="<%=basePath %>/js/service.js">	</script> --%>
<!--右边漂浮 结束-->
</body>
</html>