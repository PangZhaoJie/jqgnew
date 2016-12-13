<%@page import="java.math.BigDecimal"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="java.util.*"%>
<%@page import="java.text.*"%>
<%@ page import="com.jqg.util.md5"%>
<%
	String path = request.getContextPath();if(path!=null && !path.equals("")){	if(path.contains("/")){		if(path.equals("/")){path = "";	}}else{	path += "/";}}    
	String basePath = "";if(request.getServerPort()==80){basePath = request.getScheme()+"://"+request.getServerName()+path+"/";}else{basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";}  
%>
<jsp:useBean id='oMD5' scope='request' class='com.jqg.util.md5'/>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>充值接口-提交信息处理</title>
</head>

<body  onload="document.pay.submit()">
<h1>正在跳转至支付页面</h1>
<form method="post" name="pay" id="pay" action="https://gw.baofoo.com/payindex">
<TABLE style="display: none">
<TR>
	<TD>
	<input name='MemberID' type="hidden" value= "${MemberID }"/>
	<input name='TerminalID'  type="hidden"   value= "${ TerminalID}"/>
	<input name='InterfaceVersion'   type="hidden"  value= "${ InterfaceVersion}"/>
	<input name='KeyType'  type="hidden"   value= "${ KeyType}"/>
	<input name='PayID'  type="hidden"  value= "${ PayID}"/>		
	<input name='TradeDate'  type="hidden"  value= "${ TradeDate}" />
	<input name='TransID'  type="hidden"  value= "${ TransID}" />
	<input name='OrderMoney'  type="hidden"  value= "${ OrderMoney}"/>
	<input name='ProductName'  type="hidden"  value= "${ ProductName}"/>
	<input name='Amount'  type="hidden"  value= "${ Amount}"/>
	<input name='Username'  type="hidden"  value= "${ Username}"/>
	<input name='AdditionalInfo'  type="hidden"  value= "${ AdditionalInfo}"/>
	<input name='PageUrl'  type="hidden"  value= "${PageUrl}"/>
	<input name='ReturnUrl'  type="hidden"  value= "${ReturnUrl}"/>	
	<input name='Signature'  type="hidden"  value="${ Signature}"/>
	<input name='NoticeType'  type="hidden"  value= "${ NoticeType}"/>
	</TD>
</TR>
</TABLE>
	
</form>	

</body>
</html>
