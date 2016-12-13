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
		<title>我的银行卡</title>
  </head>
  
  <body>
    <div data-role="page" class="wk_mycard">
	<div data-role="header" data-position="fixed">
    <jsp:include page="header.jsp" />
	</div>
   <div role="main" class="ui-content">
   <c:forEach items="${pbList}" var="pb" >
   		<div class="current item">
        	<div class="card_title">
            	<p class="bank">${pb.bankparameter.bankPname }</p>
                <p class="tail">尾号：${pb.accountNums}</p>
            </div>
            <div class="card_main">
            	<p>开户银行:<span>${pb.accountAddress}</span></p>
                <p>审核状态:<span>申请成功</span></p>
            </div>
            <div class="card_title cardbottom">
            	<p class="selected bank"></p>
                <p class="delete tail"><a href="<%=path %>/wap/deleteCard?bankId=${pb.personalBankInforId}">移除</a></p>
            </div>
        </div>
       </c:forEach> 
   </div> 
   <div data-role="footer" data-position="fixed">  
    	<div class="confirmBt"><a href="<%=path %>/wap/jumpCard" data-ajax="false">添加银行卡</a></div>
    </div>
</div> 
  </body>
</html>
