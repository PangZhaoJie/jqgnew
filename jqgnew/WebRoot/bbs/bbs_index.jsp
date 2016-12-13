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
<link rel="icon" href="<%=path %>/image/favicon/favicon.ico" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>论坛中心-首页</title>
<link href="<%=basePath %>/css/public.css" type="text/css" rel="stylesheet"/>
<link href="<%=basePath %>/css/bbs/bbs.css" type="text/css" rel="stylesheet" />
<!--导航下拉菜单 js-->
<script type="text/javascript" src="<%=basePath %>/js/jquery-1.8.2.min.js"></script>
<script src="<%=basePath %>/js/jquery.flexslider-min.js"></script>
<script>
$(window).load(function() {
	$('.flexslider').flexslider({
		directionNav: false,
		pauseOnAction: false
	});
});
</script>

</head>
<body> 
<!--头部开始-->
 <!--头部开始-->
<div class="header">
    <jsp:include page="../header.jsp" />
</div>
<!--头部结束-->


<div class="conbox pt10">
    <P class="guide">当前位置：<a href="#">网站首页</a> > <a href="#">论坛中心</a> > 首页</P>
  <div class="bbs">
		<div class="newsBox" >
        	<div class="Box">
        		<div class="flexslider">
	               <ul class="slides">
	            	  <li><a href="#"><img src="<%=basePath %>/images/news_01.png"/></a></li>
	         	      <li><a href="#"><img src="<%=basePath %>/images/news_02.png" /></a></li>
	                  <li><a href="#"><img src="<%=basePath %>/images/news_03.png" /></a></li>
                  </ul>
                </div>
                <div class="newsImge" >
                	<div style="background-image:url(<%=basePath %>/images/tie_03.png);width:385px;height:50px;">
                        <div style="color:#06F;float:right;margin-right:20px;line-height:50px;"><a href="<%=path %>/bbs/toType?askTypeFlag=0">more></a></div>
                        <div style="margin-left:20px;line-height:50px;">最<span style="font-size:18px;color:#3CF;margin:0 5px;">新帖</span></div>
                    </div>
                    <div style="padding:10px 20px;line-height:30px;font-size:60px;">
                    	 <div>
                    	<c:forEach var="investstrategy" items="${investstrategyList}" >
                    	<a href="<%=path %>/bbs/toBbsView?askId=${investstrategy.askId}" style="float:left;line-height:30px;width: 180px ;text-overflow:ellipsis;overflow:hidden;white-space:nowrap;padding:2px">
                    		${investstrategy.askTitle}</a>
                    	<span style="float:right;color:#CCC;line-height:30px">		
                    		<fmt:formatDate value="${investstrategy.askDate}" pattern="yyyy-MM-dd" />
                    	</span> </c:forEach>
                    	</div>
                    </div>
            	</div>
                <div class="newsImge" style="margin-right:0;">
                	<div style="background-image:url(<%=basePath %>/images/tie_03.png);width:385px;height:50px;">
                        <div style="color:#06F;float:right;margin-right:20px;line-height:50px;"><a href="">more></a></div>
                        <div style="margin-left:20px;line-height:50px;"><span style="font-size:18px;color:#F90;margin:0 5px;">网站公告</span></div>
                    </div>
                    <div style="padding:10px 20px;line-height:30px;font-size:12px;">
                    	<div>
                    	<c:forEach var="investstrategy" items="${investstrategyList}" >
                    	<a href="" style="float:left;line-height:30px;width: 180px ;text-overflow:ellipsis;overflow:hidden;white-space:nowrap;padding:2px">
                    		${investstrategy.askTitle}</a>
                    	<span style="float:right;color:#CCC;line-height:30px">		
                    		<fmt:formatDate value="${investstrategy.askDate}" pattern="yyyy-MM-dd" />
                    	</span> </c:forEach>
                    	</div>
                    </div>
            	</div>
            </div>
            <div class="contentTop">
            
           	  <div class="contentOne"><div>客户服务区</div></div>
              <div class="contentBox">
              	<div class="content">
                	<div class="cont">
                    	<div class="contT">
                    		<img src="<%=basePath %>/images/tu_07.png" />
                        </div>
                        <div class="contT">
                        	<a href="<%=path %>/bbs/toType?askTypeFlag=1">广告发布区</a>
                        	<c:forEach var="gg" items="${typeList}"> 
	                        	<c:if test="${gg.typeFlag==1}">
		                        	<div style="color:#999;">帖数: ${gg.noteCount}</div>
		                            <div style="color:#999;margin-right:10px;">
			                            <a href="<%=path %>/bbs/toBbsView?askId=${gg.askId}" style="color:#999;margin-right:10px;">${gg.askTitle} &nbsp; ${gg.askUserName}
			                             &nbsp; <fmt:formatDate value="${gg.askDate}" pattern="yyyy-MM-dd" />
			                             </a>
		                            </div>
	                        	</c:if>
                        	</c:forEach>
                        </div>
                    </div>
                    <div class="cont">
                    	<div class="contT">
                    		<img src="<%=basePath %>/images/tu_07.png" />
                        </div>
                        <div class="contT">
                        	<a href="<%=path %>/bbs/toType?askTypeFlag=2">新手讨论区</a>
                        	<c:forEach var="gg" items="${typeList}"> 
	                        	<c:if test="${gg.typeFlag==2}">
		                        	<div style="color:#999;">帖数: ${gg.noteCount}</div>
		                            <div style="color:#999;margin-right:10px;">
			                            <a href="<%=path %>/bbs/toBbsView?askId=${gg.askId}" style="color:#999;margin-right:10px;">${gg.askTitle} &nbsp; ${gg.askUserName}
			                             &nbsp; <fmt:formatDate value="${gg.askDate}" pattern="yyyy-MM-dd" />
			                             </a>
		                            </div>
	                        	</c:if>
                        	</c:forEach>
                        </div>
                    </div>
                </div>
                <div class="content">
                	<div class="cont">
                    	<div class="contT">
                    		<img src="<%=basePath %>/images/tu_07.png" />
                        </div>
                        <div class="contT">
                        	<a href="<%=path %>/bbs/toType?askTypeFlag=3">经验分享区</a>
                        	<c:forEach var="gg" items="${typeList}"> 
	                        	<c:if test="${gg.typeFlag==3}">
		                        	<div style="color:#999;">帖数: ${gg.noteCount}</div>
		                            <div style="color:#999;margin-right:10px;">
			                            <a href="<%=path %>/bbs/toBbsView?askId=${gg.askId}" style="color:#999;margin-right:10px;">${gg.askTitle} &nbsp; ${gg.askUserName}
			                             &nbsp; <fmt:formatDate value="${gg.askDate}" pattern="yyyy-MM-dd" />
			                             </a>
		                            </div>
	                        	</c:if>
                        	</c:forEach>
                        </div>
                    </div>
                    <div class="cont">
                    	<div class="contT">
                    		<img src="<%=basePath %>/images/tu_07.png" />
                        </div>
                        <div class="contT">
                        	<a href="<%=path %>/bbs/toType?askTypeFlag=4">建议反馈区</a>
                        	<c:forEach var="gg" items="${typeList}"> 
	                        	<c:if test="${gg.typeFlag==4}">
		                        	<div style="color:#999;">帖数: ${gg.noteCount}</div>
		                            <div style="color:#999;margin-right:10px;">
			                            <a href="<%=path %>/bbs/toBbsView?askId=${gg.askId}" style="color:#999;margin-right:10px;">${gg.askTitle} &nbsp; ${gg.askUserName}
			                             &nbsp; <fmt:formatDate value="${gg.askDate}" pattern="yyyy-MM-dd" />
			                             </a>
		                            </div>
	                        	</c:if>
                        	</c:forEach>
                        </div>
                    </div>
                </div>
                <div class="content">
                	<div class="cont">
                    	<div class="contT">
                    		<img src="<%=basePath %>/images/tu_07.png" />
                        </div>
                        <div class="contT">
                        	<a href="<%=path %>/bbs/toType?askTypeFlag=5">回款续投奖励登记</a>
                        	<c:forEach var="gg" items="${typeList}"> 
	                        	<c:if test="${gg.typeFlag==5}">
		                        	<div style="color:#999;">帖数: ${gg.noteCount}</div>
		                            <div style="color:#999;margin-right:10px;">
			                            <a href="<%=path %>/bbs/toBbsView?askId=${gg.askId}" style="color:#999;margin-right:10px;">${gg.askTitle} &nbsp; ${gg.askUserName}
			                             &nbsp; <fmt:formatDate value="${gg.askDate}" pattern="yyyy-MM-dd" />
			                             </a>
		                            </div>
	                        	</c:if>
                        	</c:forEach>
                        </div>
                    </div>
                    <div class="cont">
                    	<div class="contT">
                    		<img src="<%=basePath %>/images/tu_07.png" />
                        </div>
                        <div class="contT">
                        	<a href ="<%=path %>/bbs/toType?askTypeFlag=6">数据公开区</a>
                        	<c:forEach var="gg" items="${typeList}"> 
	                        	<c:if test="${gg.typeFlag==6}">
		                        	<div style="color:#999;">帖数: ${gg.noteCount}</div>
		                           <div style="color:#999;margin-right:10px;">
			                            <a href="<%=path %>/bbs/toBbsView?askId=${gg.askId}" style="color:#999;margin-right:10px;">${gg.askTitle} &nbsp; ${gg.askUserName}
			                             &nbsp; <fmt:formatDate value="${gg.askDate}" pattern="yyyy-MM-dd" />
			                             </a>
		                            </div>
	                        	</c:if>
                        	</c:forEach>
                        </div>
                    </div>
                </div>
              </div>
            </div>
             <div class="contentTop">
           	  <div class="contentOne"><div>借贷交流区</div></div>
              <div class="contentBox">
              	<div class="content">
                	<div class="cont">
                    	<div class="contT">
                    		<img src="<%=basePath %>/images/tu_07.png" />
                        </div>
                        <div class="contT">
                        	<a href="<%=path %>/bbs/toType?askTypeFlag=7">借款人详情</a>
                        	<c:forEach var="gg" items="${typeList}"> 
	                        	<c:if test="${gg.typeFlag==7}">
		                        	<div style="color:#999;">帖数: ${gg.noteCount}</div>
		                            <div style="color:#999;margin-right:10px;">
			                            <a href="<%=path %>/bbs/toBbsView?askId=${gg.askId}" style="color:#999;margin-right:10px;">${gg.askTitle} &nbsp; ${gg.askUserName}
			                             &nbsp; <fmt:formatDate value="${gg.askDate}" pattern="yyyy-MM-dd" />
			                             </a>
		                            </div>
	                        	</c:if>
                        	</c:forEach>
                        </div>
                    </div>
                    <div class="cont">
                    	<div class="contT">
                    		<img src="<%=basePath %>/images/tu_07.png" />
                        </div>
                        <div class="contT">
                        	<a href="<%=path %>/bbs/toType?askTypeFlag=8">投资理财交流</a>
                        	<c:forEach var="gg" items="${typeList}"> 
	                        	<c:if test="${gg.typeFlag==8}">
		                        	<div style="color:#999;">帖数: ${gg.noteCount}</div>
		                           <div style="color:#999;margin-right:10px;">
			                            <a href="<%=path %>/bbs/toBbsView?askId=${gg.askId}" style="color:#999;margin-right:10px;">${gg.askTitle} &nbsp; ${gg.askUserName}
			                             &nbsp; <fmt:formatDate value="${gg.askDate}" pattern="yyyy-MM-dd" />
			                             </a>
		                            </div>
	                        	</c:if>
                        	</c:forEach>
                        </div>
                    </div>
                </div>
                <div class="content">
                	<div class="cont">
                    	<div class="contT">
                    		<img src="<%=basePath %>/images/tu_07.png" />
                        </div>
                        <div class="contT">
                        	<a href="<%=path %>/bbs/toType?askTypeFlag=9">借贷经验交流</a>
                        	<c:forEach var="gg" items="${typeList}"> 
	                        	<c:if test="${gg.typeFlag==9}">
		                        	<div style="color:#999;">帖数: ${gg.noteCount}</div>
		                            <div style="color:#999;margin-right:10px;">
			                            <a href="<%=path %>/bbs/toBbsView?askId=${gg.askId}" style="color:#999;margin-right:10px;">${gg.askTitle} &nbsp; ${gg.askUserName}
			                             &nbsp; <fmt:formatDate value="${gg.askDate}" pattern="yyyy-MM-dd" />
			                             </a>
		                            </div>
	                        	</c:if>
                        	</c:forEach>
                        </div>
                    </div>
                    <div class="cont">
                    	<div class="contT">
                    		<img src="<%=basePath %>/images/tu_07.png" />
                        </div>
                        <div class="contT">
                        	<a href="<%=path %>/bbs/toType?askTypeFlag=10">平台功能建议</a>
                        	<c:forEach var="gg" items="${typeList}"> 
	                        	<c:if test="${gg.typeFlag==10}">
		                        	<div style="color:#999;">帖数: ${gg.noteCount}</div>
		                            <div style="color:#999;margin-right:10px;">
			                            <a href="<%=path %>/bbs/toBbsView?askId=${gg.askId}" style="color:#999;margin-right:10px;">${gg.askTitle} &nbsp; ${gg.askUserName}
			                             &nbsp; <fmt:formatDate value="${gg.askDate}" pattern="yyyy-MM-dd" />
			                             </a>
		                            </div>
	                        	</c:if>
                        	</c:forEach>
                        </div>
                    </div>
                </div>
              </div>
            </div>
            <div class="contentTop">
           	  <div class="contentOne"><div>新闻杂谈区</div></div>
          
              <div class="contentBox">
              	<div class="content">
                	<div class="cont">
                    	<div class="contT">
                    		<img src="<%=basePath %>/images/tu_07.png" />
                        </div>
                        <div class="contT">
                        	<a href="<%=path %>/bbs/toType?askTypeFlag=11">行业财经</a>
                        <c:forEach var="gg" items="${typeList}"> 
	                        	<c:if test="${gg.typeFlag==11}">
		                        	<div style="color:#999;">帖数: ${gg.noteCount}</div>
		                            <div style="color:#999;margin-right:10px;">
			                            <a href="<%=path %>/bbs/toBbsView?askId=${gg.askId}" style="color:#999;margin-right:10px;">${gg.askTitle} &nbsp; ${gg.askUserName}
			                             &nbsp; <fmt:formatDate value="${gg.askDate}" pattern="yyyy-MM-dd" />
			                             </a>
		                            </div>
	                        	</c:if>
                        	</c:forEach>
                        </div>
                    </div>
                    <div class="cont">
                    	<div class="contT">
                    		<img src="<%=basePath %>/images/tu_07.png" />
                        </div>
                        <div class="contT">
                        	<a href="<%=path %>/bbs/toType?askTypeFlag=12">社会生活</a>
                        	<c:forEach var="gg" items="${typeList}"> 
	                        	<c:if test="${gg.typeFlag==12}">
		                        	<div style="color:#999;">帖数: ${gg.noteCount}</div>
		                            <div style="color:#999;margin-right:10px;">
			                            <a href="<%=path %>/bbs/toBbsView?askId=${gg.askId}" style="color:#999;margin-right:10px;">${gg.askTitle} &nbsp; ${gg.askUserName}
			                             &nbsp; <fmt:formatDate value="${gg.askDate}" pattern="yyyy-MM-dd" />
			                             </a>
		                            </div>
	                        	</c:if>
                        	</c:forEach>
                        </div>
                    </div>
                </div>
                <div class="content">
                	<div class="cont">
                    	<div class="contT">
                    		<img src="<%=basePath %>/images/tu_07.png" />
                        </div>
                        <div class="contT">
                        	<a href="<%=path %>/bbs/toType?askTypeFlag=13">休闲娱乐</a>
                        	<c:forEach var="gg" items="${typeList}"> 
	                        	<c:if test="${gg.typeFlag==11}">
		                        	<div style="color:#999;">帖数: ${gg.noteCount}</div>
		                            <div style="color:#999;margin-right:10px;">
			                            <a href="<%=path %>/bbs/toBbsView?askId=${gg.askId}" style="color:#999;margin-right:10px;">${gg.askTitle} &nbsp; ${gg.askUserName}
			                             &nbsp; <fmt:formatDate value="${gg.askDate}" pattern="yyyy-MM-dd" />
			                             </a>
		                            </div>
	                        	</c:if>
                        	</c:forEach>
                        </div>
                    </div>
                    <div class="cont">
                    	<div class="contT">
                    		<img src="<%=basePath %>/images/tu_07.png" />
                        </div>
                        <div class="contT">
                         	<div style="color:#999;margin-right:10px;">等待扩建中。。。</div>
                         </div>
                    </div>
                </div>
              </div>
            </div>
        </div>
   </div>
<span class="newsImge"></span></div>
<!--footer 开始-->
 <div class="footer">
  <jsp:include page="../footer.jsp" />
</div>
<!--footer 结束-->


<%-- <!--右边漂浮 开始-->
<div id="service">
	<a href="#" class="srvLog" target="_blank" title="网贷计算器">网贷计算器</a>
	<a href="tencent://message/?uin=285772998&Site=企业网站&Menu=yes" class="srvCns" title="联系客服">联系客服</a>
	<a href="#" class="srvDj" target="_blank" title="在线充值">在线充值</a>
	<a class="goTop" id="goTop" title="返回顶部" style="display:none" >返回顶部</a>
</div>
<script type="text/javascript" src="<%=basePath %>/js/service.js">	</script> --%>
<!--右边漂浮 结束-->
</body>
</html>