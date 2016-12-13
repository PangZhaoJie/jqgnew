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
		<title>会员中心</title>
  </head>
  
  <body>
    
<div data-role="page" data-position="fixed">
   
    <div data-role="header" data-position="fixed">
   <jsp:include page="header.jsp" />
</div>
<div role="main" class="ui-content wk_member"> 
	<div class="wk_member_head">
    	<div class="head">
        	<img src="images/membertx.png">
            <p>${uservip.userName }</p>
        </div>
      <div class="ui-grid-a">
        	<div class="ui-block-a"><a href="<%=path %>/wap/tochongzhi" class="ui-bar ui-bar-a recharge" data-ajax="false">充值</a></div>
            <div class="ui-block-b"><a href="<%=path %>/wap/totixian" class="ui-bar ui-bar-a cash" data-ajax="false">提现</a></div>
         </div><!-- /grid-a -->
    </div>
    <div class="ui-grid-a wk_member_money">
    	<div class="ui-block-a"><div class="ui-bar ui-bar-a">
        	<a>可用余额（元）</a>
        	<p><fmt:formatNumber value="${moneycount.availableMoney}" pattern="0.00" /></p>
        </div></div>
        <div class="ui-block-b"><div class="ui-bar ui-bar-a">
            <a>冻结金额（元）</a>
        	<p><fmt:formatNumber value="${moneycount.frozenMoney}" pattern="0.00" /></p>
         </div></div>
    </div><!-- /grid-a -->
    <div class="wk_member_main">
        <div class="ui-grid-a">
            <div class="ui-block-a"><a href="<%=path %>/wap/toInverst" class="ui-bar ui-bar-a border_bottom border_right one" data-ajax="false">
                我的投资
            </a></div>
            <div class="ui-block-b"><a href="<%=path %>/wap/toBorrow" class="ui-bar ui-bar-a border_bottom two" data-ajax="false">
                我的借款
             </a></div>
         </div> 
         <div class="ui-grid-a">   
             <div class="ui-block-a"><a href="<%=path %>/wap/toAssets" class="ui-bar ui-bar-a border_right border_bottom three" data-ajax="false">
                资金管理
            </a></div>
            <div class="ui-block-b"><a href="<%=path %>/wap/user_updatePwd.jsp" class="ui-bar ui-bar-a border_bottom four" data-ajax="false">
                修改密码
             </a></div>
                    <div class="ui-block-b"><a href="<%=path %>/wap/searchBasic" class="ui-bar ui-bar-a border_right five" data-ajax="false">
                认证信息
             </a></div>
                    <div class="ui-block-b"><a href="<%=path %>/wap/authorize.jsp" class="ui-bar ui-bar-a six" data-ajax="false">
                开通授权
             </a></div>
        </div><!-- /grid-a -->
    </div> 
    
</div> 
<div data-role="footer" data-position="fixed">  
  <div class="confirmBt"><a  href="<%=path %>/wap/quit"  data-ajax="false" class="ui-btn ui-corner-all ui-shadow ui-btn-inline">退出登录</a></div>
</div>
</div>  
  </body>
</html>
