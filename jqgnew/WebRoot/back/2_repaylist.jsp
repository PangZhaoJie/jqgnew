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
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="<%=basePath%>/back/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>/back/js/jquery.js"></script>
<!--折叠js-->
<!--日历插件 js-->
<script type="text/javascript" src="<%=basePath%>/back/laydate/laydate.js"></script>

<script type="text/javascript">
 
</script>
</head>


<body onload="test()">
	<div><a href="<%=path %>/borrow/tobackFenMoney?lssuingId=${lssuingId}" style="color: red" target="_blank">还款（今天应还）</a></div>
    <div class="rightinfo">
    <table class="tablelist">
    	<thead>
    	<tr>
        <th width="10%">计划还款日期</th>
        <th width="7%">计划还款本金</th>
        <th width="7%">计划还款利息</th>
        <th width="7%">需还本息</th>
        <th width="18%">逾期天数</th>
        <th width="8%">逾期利息</th>
        <th width="8%">实还本息</th>
        <th width="8%">还款状态</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="mx" items="${repaynoteList}">
        	<tr>
                <td><fmt:formatDate value="${mx.repayDate}" pattern="yyyy-MM-dd" /></td>
                <td><fmt:formatNumber value="${mx.moneyOne}" pattern="0.00"/></td>
                <td><fmt:formatNumber value="${mx.moneyTwo}" pattern="0.00"/></td>
                <td><fmt:formatNumber value="${mx.moneyFour}" pattern="0.00"/></td>
                <td>${mx.overdays}</td>
                <td><fmt:formatNumber value="${mx.overInterest}" pattern="0.00"/></td>
                <td><fmt:formatNumber value="${mx.moneyThree}" pattern="0.00"/></td>
                
                <td><c:if test="${mx.repayState==0}">未还款</c:if>
                <c:if test="${mx.repayState==1}">还款成功</c:if>
                <c:if test="${mx.repayState==2}">平台垫付</c:if></td>

              </tr>
  		</c:forEach>
        </tbody>
    </table>
    </div>
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	
	</script>

</body>

</html>
