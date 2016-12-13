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
		<title>债权详情</title>
  </head>
  
  <body>
    
<div data-role="page" data-position="fixed">
   
    <div data-role="header" data-position="fixed">
   <jsp:include page="header.jsp" />
</div>
<div role="main" class="ui-content" > 
  		 <div class="recommendBox hotBox">
        
      <c:forEach items="${transferList }" var="l">
       <a href="${l.explains}" data-ajax="false"> 
        <div class="recommend hotItem">
            <div class="title">
<!--                 <p id="ico_ks">抵押标</p> -->
<!-- 参考债权转让页面的属性  l.xxx -->
                <p>${l.title }</p>
            </div>
            <div class="mainBox">
                <div class="one">
                    <p>${l.borrowMoney }</p>
                    <a>待收本息（元）</a>
             	</div>
       			 <div class="one">
                    <p>${l.returnMoney }</p>
                    <a>转让价格（元）</a>
             	</div>
	   		 	<div class="two">
                   <p>${l.rate }</p>
                   <a>当前利率（%）</a>
               	</div>
               	<div class="three">
                   <p>${l.explainOne}</p>
                   <a>状态</a>
               	</div>

            </div>
            <!--  
            <p class="border1px"></p>
            <div class="progress">
                <div class="progressbar">
                    <div style="width:${l.scale}%"></div> 
                </div>
                <a>进度：${l.scale }%</a>
            </div>
            -->
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
