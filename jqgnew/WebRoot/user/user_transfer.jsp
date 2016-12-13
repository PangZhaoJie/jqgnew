<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();if(path!=null && !path.equals("")){	if(path.contains("/")){		if(path.equals("/")){path = "";	}}else{	path += "/";}} 
String basePath = "";if(request.getServerPort()==80){basePath = request.getScheme()+"://"+request.getServerName()+path+"/";}else{basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";}    
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="icon" href="<%=path %>/image/favicon/favicon.ico" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>个人中心-债权转</title>
<link href="../css/public.css" type="text/css" rel="stylesheet"/>
<link href="../css/user/user_public.css" type="text/css" rel="stylesheet"/>
<link href="../css/user/user_cash.css" type="text/css" rel="stylesheet"/>
<!--导航下拉菜单 js-->
<script type="text/javascript" src="../js/jquery-1.8.2.min.js"></script>
<!--表单下拉列表框-->
<script type="text/javascript" src="../js/select2css.js"></script>
<!--左侧折叠菜单-->

<script type="text/javascript" src="../js/menu.js"></script>
</head>
<body> 
<!--头部开始-->
<div class="header">
<jsp:include page="../header.jsp" />
    
</div>
<!--头部结束-->
<div class="maincontent">
  
  <div class="conbox fn_cle">
       <jsp:include page="left.jsp" />
   
   </div>
   
     <div class="user_r">
         <div id="hotnews_caption">
            <ul id="h_menu">                  
                 <li class="current"onclick="secBoard('hotnews_caption','list',1);">债权转让</li>
            </ul>
         </div>
         
         <div id="hotnews_content">
           <div class="current" id="list_1">
           <form action="<%=path %>/transfer/addTransfer" name="form" id="form" method="post">
             <table width="825" cellspacing="0" cellpadding="0" class="pass_table"  >
                            <tbody>
                            <input type="hidden" name="tenderId" value="${tenderId}"/>
                            <tr>
                                <td class="td1">转让的债权：</td>
                                <td class="td2"><input id="money" name="name" type="text" size="32" readonly="readonly" value="${lssuingNum}"/>
                                <em id="moneyNone"  style="display: none;color:red;"> 请输入正确金额</em> 
                                <em id="moneyNone1"  style="display: none;color:red;"> 您输入的金额不能少于最低提现金额</em>
                                </td>
                             </tr>
                             <tr>
                                <td class="td1">最高转让价格：</td>
                                <td class="td2"><input id="money" name="name" type="text" size="32" readonly="readonly" value="${benxistr}"/>
                                <em id="moneyNone"  style="display: none;color:red;"> 请输入正确金额</em> 
                                <em id="moneyNone1"  style="display: none;color:red;"> 您输入的金额不能少于最低提现金额</em>
                                </td>
                             </tr>
                             <tr>
                                <td class="td1">转让手续费：</td>
                                <td class="td2"><input id="money" name="name" type="text" size="32" readonly="readonly" value="${fee}"/><a>转让金额的${parvalue}%</a>
                                <em id="moneyNone"  style="display: none;color:red;"> 请输入正确金额</em> 
                                <em id="moneyNone1"  style="display: none;color:red;"> 您输入的金额不能少于最低提现金额</em>
                                </td>
                             </tr>
                             <tr>
                                <td class="td1">转让价格：</td>
                                <td class="td2"><input id="money" name="money" type="text" size="32" />
                                <em id="moneyNone"  style="display: none;color:red;"> 请输入正确金额</em> 
                                <em id="moneyNone1"  style="display: none;color:red;"> 您输入的金额不能大于最高转让价格</em>
                                </td>
                             </tr>
                             <tr>
                                <td class="td1">支付密码：</td>
                                <td class="td2"><input name="payPwd"  type="password" size="32" id="payPwd"/><em id="pwdNone"  style="display: none;color:red;"> 请输入支付密码</em><em id="errorNone"  style="display: none;color:red;"> 支付密码不对，请重新输入</em></td>       
                             </tr>
                            <tr height="60">
                                <td colspan="2" align="center" ><div id="submit" > <input type="button" onclick="this.disabled=true;this.form.submit()" class="submit_bt" id="submit_bt" value="确认转让" /></div></th>
                            </tr>
                            
                      </tbody>
                    </table>
                  </form>
           </div>
          <script type="text/javascript" src="../js/tabs.js" language="javascript"></script>
        </div>
   </div>
  </div>
</div> 
</div>
<!--footer 开始-->
<div class="footer">
  <jsp:include page="../footer.jsp" />
</div>
<script type="text/javascript">
 var $page =  jQuery.noConflict();
 function getJSONData() {
 		$page("#ID1").hide();
 		$page("#ID3").show();
 }
 $page(function() {  
 	getJSONData();  
 });
 </script>
</body>
</html>