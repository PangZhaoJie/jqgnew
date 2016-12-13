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
   <title>资金记录</title> 
  </head>
  
  <body>
   
<div data-role="page" data-position="fixed" class="fundhistory">
  
    <div data-role="header" data-position="fixed">
    <jsp:include page="header.jsp" />
	</div>
   
    <div role="main" class=" wk_myinvestment">
    	<div data-role="tabs" id="tabs">
        	<div data-role="navbar" class="subnav">
            	
            </div>
            <div id="one" class="main">
                	<div class="ui-grid-c wk_grey">
                        	<div class="ui-block-a"><div class="ui-bar ui-bar-a" >总金额</div></div>
                            <div class="ui-block-b"><div class="ui-bar ui-bar-a" >冻结资金</div></div>
                            <div class="ui-block-c"><div class="ui-bar ui-bar-a" >待收金额</div></div>
                            <div class="ui-block-d"><div class="ui-bar ui-bar-a" >日期</div></div>
                         </div><!-- /grid-c -->
                         
                         <c:forEach items="${MHList }" var="gg" >
                         <div class="ui-grid-c wk_white">
                        	<div class="ui-block-a"><div class="ui-bar ui-bar-a" >￥<fmt:formatNumber value="${gg.availableBalance }" pattern="0.00"/></div></div>
                            <div class="ui-block-b"><div class="ui-bar ui-bar-a" >￥<fmt:formatNumber value="${gg.frozenMoney }" pattern="0.00"/></div></div>
                            <div class="ui-block-c"><div class="ui-bar ui-bar-a" >￥<fmt:formatNumber value="${gg.collectMoney }" pattern="0.00"/></div></div>
                            <div class="ui-block-d"><div class="ui-bar ui-bar-a" ><fmt:formatDate value="${gg.occurTime}" pattern="yyyy-MM-dd" /></div></div>
                         </div><!-- /grid-c -->
                         
                         </c:forEach>
                         

            </div>
       
	  </div>
        
  	</div> 
    <div data-role="footer" data-position="fixed">  
     <jsp:include page="footer.jsp" />
  </div>
</div>   
  </body>
</html>
