<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
		<title>项目详情</title>
  </head>
  
  <body>
    
<div data-role="page" data-position="fixed">
   
    <div data-role="header" data-position="fixed">
   <jsp:include page="header.jsp" />
</div>
<div role="main" class="ui-content" > 
  		 <div class="recommendBox hotBox">
        
      <c:forEach items="${lList }" var="l">
       <a href="<%=path %>/wap/toTender?lssuingId=${l.lssuingId}" data-ajax="false"> 
        <div class="recommend hotItem">
            <div class="title">
<!--                 <p id="ico_ks">抵押标</p> -->
                <p>${l.title }</p>
            </div>
            <div class="mainBox">
                <div class="one">
                    <p>${l.borrowMoney }</p>
                    <a>融资金额（元）</a>
                </div>
                
                <c:choose>
					<c:when test="${ l.periodtime==null}"> 
				   		 <div class="two">
		                    <p>
		                    <fmt:formatNumber value="${l.rate*365}" pattern="0.00"/>
		                    </p>
		                    <a>年化利率（%）</a>
<!-- 		                    <a>天化利率（%）</a> -->
		                </div>
		                <div class="three">
		                    <p>${l.periodday.periodDayName  }</p>
		                    <a>项目期限（天）</a>
		                </div>
				   		
				   </c:when>
					<c:otherwise>  
				   		 <div class="two">
		                    <p>${l.rate }</p>
		                    <a>年化利率（%）</a>
		                </div>
		                <div class="three">
		                    <p>${l.periodtime.periodTimeName }</p>
		                    <a>项目期限（月）</a>
		                </div>
				   		
				   </c:otherwise>
				</c:choose>
                
                
            </div>
            <p class="border1px"></p>
            <div class="progress">
                <div class="progressbar">
                    <div style="width:${l.scale }%"></div> 
                </div>
                <a>进度：${l.scale }%</a>
            </div>
        </div>
        </a>
        </c:forEach>
        
    </div><!-- /hotBox -->
    </div> 
<div data-role="footer" data-position="fixed">  
   <jsp:include page="footer.jsp" />
</div>
</div>  
  </body>
</html>
