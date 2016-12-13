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
		<title>充值</title>
  </head>
  
  <body>
  <div data-role="page" class="wk_addcard">
	<div data-role="header" data-position="fixed">
    <jsp:include page="header.jsp" />
	</div>
   <div role="main" class="ui-content">
   		<p>
        温馨提示：最低充值金额50元。充值免手续费！充值资金可用于进行验证、投标、还款等。充值成功后资金会立刻划拨到您的帐户。
        </p>

        <form action="" id="form1" method="post">
        	<div class="main">
                <div class="ui-grid-a">
                        <div class="ui-block-a"><div class="ui-bar ui-bar-a">账户余额</div></div>
                        <div class="ui-block-b"><div class="ui-bar ui-bar-a">
                        	<input name="ssa" id="nameResult" type="hidden" size="24"  value="${uservip.userId }"/>
									${moneycount.availableMoney}元
						 
                            
                        </div></div>
                 </div><!-- /grid-a --> 
                 <div class="ui-grid-a">
                        <div class="ui-block-a"><div class="ui-bar ui-bar-a">充值金额</div></div>
                        <div class="ui-block-b"><div class="ui-bar ui-bar-a">
                            <input type="text"  name="chongzhiMoney" id="accountNum"  onkeyup="value=value.replace(/[^0-9.]/g,'')" > 
                        </div></div>
                 </div><!-- /grid-a --> 
                
            </div>
             <p >在您使用以上各家银行充值之前，请注意各个银行的支付金额的上限。</p>
            <div class="wk_invest_btn">  
    			<a onclick="toSubmit();" >确定充值</a>    
            </div> 
        </form>
   </div> 
   <div data-role="footer" data-position="fixed">  
    	
    </div>
</div>    
</body>  
  <script type="text/javascript">
  function toSubmit(){
  	var boo=true;
  	var nameResult=$("#nameResult").val();
  	var accountNum=$("#accountNum").val();
  	var accountAddress=$("#accountAddress").val();
  	var bankPid=$("#bankPid").val();
   if(accountNum==""||accountNum==null){
  		alert("充值金额不能为空");
  		boo=false;
 
  	}
  	if(boo==true){
  			var form = document.getElementById("form1");
	        form.action="<%=path %>/wap/waptrustRecharge";
 	        form.submit();
		 	document.activeElement.style.background = "gray";
		    document.activeElement.value="正在提交...";
		    document.activeElement.disabled=false;	
  	}
  	
  
  }
  
  </script>
</html>
