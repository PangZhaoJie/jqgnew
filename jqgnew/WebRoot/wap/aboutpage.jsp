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
		<title>添加银行卡</title>
  </head>
  
  <body>
   <div data-role="page" class="wk_addcard">
	<div data-role="header" data-position="fixed">
    <jsp:include page="header.jsp" />
	</div>
   <div role="main" class="ui-content">
   		<p>
        金钱柜严禁信用卡充值、套现等行为，一经发现将予以处罚：限制收款、冻结账户、永久停止服务，并影响银行信用记录。
        </p>
        <form action="">
        	<div class="main">
                <div class="ui-grid-a">
                        <div class="ui-block-a"><div class="ui-bar ui-bar-a">开户人姓名</div></div>
                        <div class="ui-block-b"><div class="ui-bar ui-bar-a">
                            <input type="text" name="textinput-4" id="textinput-4" placeholder="" value="">
                        </div></div>
                 </div><!-- /grid-a --> 
                 <div class="ui-grid-a">
                        <div class="ui-block-a"><div class="ui-bar ui-bar-a">银行卡账号</div></div>
                        <div class="ui-block-b"><div class="ui-bar ui-bar-a">
                            <input type="text" name="textinput-4" id="textinput-4" placeholder="" value="">
                        </div></div>
                 </div><!-- /grid-a --> 
                 <div class="ui-grid-a">
                        <div class="ui-block-a"><div class="ui-bar ui-bar-a">开户银行</div></div>
                        <div class="ui-block-b"><div class="ui-bar ui-bar-a">
                            <input type="text" name="textinput-4" id="textinput-4" placeholder="" value="">
                        </div></div>
                 </div><!-- /grid-a --> 
                 <div class="ui-grid-a">
                        <div class="ui-block-a"><div class="ui-bar ui-bar-a">开户行名称</div></div>
                        <div class="ui-block-b"><div class="ui-bar ui-bar-a">
                            <input type="text" name="textinput-4" id="textinput-4" placeholder="" value="">
                        </div></div>
                 </div><!-- /grid-a --> 
            </div>
            <div class="wk_invest_btn">  
    			<a href="invest.html" >确定添加</a>    
            </div> 
        </form>
   </div> 
   <div data-role="footer" data-position="fixed">  
    	
    </div>
</div>    
  </body>
</html>
