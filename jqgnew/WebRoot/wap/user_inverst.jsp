<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>我的投资</title> 

  </head>
  
  <body>
   


<div data-role="page" data-position="fixed">

    <div data-role="header" data-position="fixed">
	 <jsp:include page="header.jsp" />

</div>
   
    <div role="main" class="wk_myinvestment">
    	<div data-role="tabs" id="tabs">
        	<div data-role="navbar" class="subnav">
            	<ul>
                	<li><a href="#one" data-ajax="false">进行中</a></li>
                    <li><a href="#two" data-ajax="false" class="second">还款中</a></li>
                    <li><a href="#three" data-ajax="false">已完成</a></li>
                </ul>
            </div>
            <div id="one" class="main">
                	<div class="ui-grid-c wk_grey">
                        	<div class="ui-block-a"><div class="ui-bar ui-bar-a" >项目</div></div>
                            <div class="ui-block-b"><div class="ui-bar ui-bar-a" >利率(%)</div></div>
                            <div class="ui-block-c"><div class="ui-bar ui-bar-a" >金额(元)</div></div>
                            <div class="ui-block-d"><div class="ui-bar ui-bar-a" >日期</div></div>
                         </div><!-- /grid-c -->
                         <c:forEach items="${tender2 }" var="tender" >
	                         <div class="ui-grid-c wk_white">
	                        	<div class="ui-block-a"><div class="ui-bar ui-bar-a" >${tender.lssuing.title}</div></div>
	                            <div class="ui-block-b"><div class="ui-bar ui-bar-a" >${tender.lssuing.rate}</div></div>
	                            <div class="ui-block-c"><div class="ui-bar ui-bar-a" ><fmt:formatNumber value="${tender.money}" pattern="0.00" /></div></div>
	                            <div class="ui-block-d"><div class="ui-bar ui-bar-a" >
								<c:choose>
									<c:when test="${ tender.lssuing.periodtime==null}"> 
								   		${tender.lssuing.periodday.periodDayName }
								   </c:when>
									<c:otherwise>  
								   		${tender.lssuing.periodtime.periodTimeName }
								   </c:otherwise>
								</c:choose>
								</div></div>
	                         </div><!-- /grid-c -->
                         </c:forEach>
                       
                         <c:if test="${fn:length(tender2)==0}">
                         <div class="empty">
			                <img src="images/empty.png">
			                <p>暂无记录</p>
			               </div>
                         </c:if>

            </div>
            <div id="two" class="main">
            	<div class="ui-grid-c wk_grey">
                        	<div class="ui-block-a"><div class="ui-bar ui-bar-a" >项目</div></div>
                            <div class="ui-block-b"><div class="ui-bar ui-bar-a" >利率(%)</div></div>
                            <div class="ui-block-c"><div class="ui-bar ui-bar-a" >金额(元)</div></div>
                            <div class="ui-block-d"><div class="ui-bar ui-bar-a" >日期</div></div>
                         </div><!-- /grid-c -->
                         
                        <c:forEach items="${tender3 }" var="tender" >
	                         <div class="ui-grid-c wk_white">
	                        	<div class="ui-block-a"><div class="ui-bar ui-bar-a" >${tender.lssuing.title}</div></div>
	                            <div class="ui-block-b"><div class="ui-bar ui-bar-a" >${tender.lssuing.rate}</div></div>
	                            <div class="ui-block-c"><div class="ui-bar ui-bar-a" ><fmt:formatNumber value="${tender.money}" pattern="0.00" /></div></div>
	                            <div class="ui-block-d"><div class="ui-bar ui-bar-a" >
								<c:choose>
									<c:when test="${ tender.lssuing.periodtime==null}"> 
								   		${tender.lssuing.periodday.periodDayName }
								   </c:when>
									<c:otherwise>  
								   		${tender.lssuing.periodtime.periodTimeName }
								   </c:otherwise>
								</c:choose>
								</div></div>
	                         </div><!-- /grid-c -->
                         </c:forEach>
                         <c:if test="${fn:length(tender3)==0}">
                         <div class="empty">
			                <img src="images/empty.png">
			                <p>暂无记录</p>
			               </div>
                         </c:if>
            </div>
            <div id="three" class="main">
            	<div class="ui-grid-c wk_grey">
                        	<div class="ui-block-a"><div class="ui-bar ui-bar-a" >项目</div></div>
                            <div class="ui-block-b"><div class="ui-bar ui-bar-a" >利率(%)</div></div>
                            <div class="ui-block-c"><div class="ui-bar ui-bar-a" >金额(元)</div></div>
                            <div class="ui-block-d"><div class="ui-bar ui-bar-a" >日期</div></div>
                         </div><!-- /grid-c -->
                         
                        <c:forEach items="${tender4 }" var="tender" >
	                         <div class="ui-grid-c wk_white">
	                        	<div class="ui-block-a"><div class="ui-bar ui-bar-a" >${tender.lssuing.title}</div></div>
	                            <div class="ui-block-b"><div class="ui-bar ui-bar-a" >${tender.lssuing.rate}</div></div>
	                            <div class="ui-block-c"><div class="ui-bar ui-bar-a" ><fmt:formatNumber value="${tender.money}" pattern="0.00" /></div></div>
	                            <div class="ui-block-d"><div class="ui-bar ui-bar-a" >
								<c:choose>
									<c:when test="${ tender.lssuing.periodtime==null}"> 
								   		${tender.lssuing.periodday.periodDayName }
								   </c:when>
									<c:otherwise>  
								   		${tender.lssuing.periodtime.periodTimeName }
								   </c:otherwise>
								</c:choose>
								</div></div>
	                         </div><!-- /grid-c -->
                         </c:forEach>
                         <c:if test="${fn:length(tender4)==0}">
                         <div class="empty">
			                <img src="images/empty.png">
			                <p>暂无记录</p>
			               </div>
                         </c:if>
            </div>
	  </div>
        
  	</div> 
    <div data-role="footer" data-position="fixed">  
    <jsp:include page="footer.jsp" />
  </div>
</div>  
  </body>
</html>
