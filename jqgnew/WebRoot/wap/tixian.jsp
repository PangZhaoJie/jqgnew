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
		<title>提现</title>
 
<script type="text/javascript" src="../js/jquery-1.8.2.min.js"></script>
 

 

<script type="text/javascript">
var $page =  jQuery.noConflict();
$page(document).ready(function(){
	$page("#submit_bt").click(function(){
    
	    if($page("#bankTable").val()==0){
	    	document.getElementById("bankTableNone").style.display="";
	    	return false;
	    }
	    if($page("#pbSize").val()==0){
	    	document.getElementById("bankNone").style.display="";
	    	document.getElementById("bankTableNone").style.display="none";
	    	return false;
	    }

	    if($page("#money").val()==null||$page("#money").val()==""){
	    	document.getElementById("moneyNone").style.display="";
	    	document.getElementById("bankNone").style.display="none";
	    	document.getElementById("bankTableNone").style.display="none";
	    	return false;
	    }
	    if(parseInt($page("#limit").val()!="0")){
	    if(parseInt($page("#money").val())>parseInt($page("#limit").val())||parseInt($page("#money").val())>parseInt($page("#aMoney").val())){
	    	document.getElementById("moneyNone").style.display="";
	    	document.getElementById("moneyNone").style.display="none";
	    	document.getElementById("bankNone").style.display="none";
	    	document.getElementById("bankTableNone").style.display="none";
	    	return false;
	    }}
	    else{
	    	   if(parseInt($page("#money").val())>parseInt($page("#aMoney").val())){
	   	    	document.getElementById("moneyNone").style.display="";
	   	    	document.getElementById("moneyNone").style.display="none";
	    	document.getElementById("moneyNone").style.display="none";
	    	document.getElementById("bankNone").style.display="none";
	    	document.getElementById("bankTableNone").style.display="none";
	   	    	return false;
	   	    }
	    }
	    if($page("#captcha").val()==null||$page("#captcha").val()==""){
	    	document.getElementById("captchaNone").style.display="";
	    	document.getElementById("moneyNone").style.display="none";
	   	    	document.getElementById("moneyNone").style.display="none";
	    	document.getElementById("moneyNone").style.display="none";
	    	document.getElementById("bankNone").style.display="none";
	    	document.getElementById("bankTableNone").style.display="none";
	    	return false;
	    }
	    if($page("#pwd").val()==null||$page("#pwd").val()==""){
	    	document.getElementById("pwdNone").style.display="";
	    	document.getElementById("captchaNone").style.display="none";
	    	document.getElementById("moneyNone").style.display="none";
	   	    	document.getElementById("moneyNone").style.display="none";
	    	document.getElementById("moneyNone").style.display="none";
	    	document.getElementById("bankNone").style.display="none";
	    	document.getElementById("bankTableNone").style.display="none";
	    	return false;
	    }
		//获取留言
		document.getElementById("pwdNone").style.display="none";
	    	document.getElementById("captchaNone").style.display="none";
	    	document.getElementById("moneyNone").style.display="none";
	   	    	document.getElementById("moneyNone").style.display="none";
	    	document.getElementById("moneyNone").style.display="none";
	    	document.getElementById("bankNone").style.display="none";
	    	document.getElementById("bankTableNone").style.display="none";
		var data = "payPwd="+$page("#pwd").val()+"&bankPid="+$page("#bankTable").val()+"&affectMoney="+$page("#money").val()+"&key="+$page("#captcha").val();
		
    	$page("#submit_bt").attr("disabled", "disabled");//防止重复提交   
    	
    	$page("#translateform").submit();
 
		
	});
});

function getCode(val){
		countdown();
		var data="mobiles="+val+"&isOpen=1&messModelId=14&userId=${uservip.userId}";
    	$page.ajax({
    		  async:false,
    		  url:"<%=path %>/phone/sendMessage",
    		  type:"POST",
    		  data:data,
    		  dataType:"json",
    		  success:function(data){
    		     if(data=="success"){
    		    	 $page('#phoneTip').html("验证码发送成功");
					}else{
					$page('#phoneTip').html("发送失败");
					}
					}
    	  });
    	
    }
var timeout=60;              
 function countdown(){
 document.getElementById("yanzheng").setAttribute("disabled","disabled");
	if (timeout == 0)
		{
		  //  alert("进1");
			document.getElementById("yanzheng").value = "重新发送";
		 	 document.getElementById("yanzheng").removeAttribute("disabled");;
			timeout=60;
		}
		else
		{
	//	alert("进"+document.getElementById("yanzheng").value);
		document.getElementById("yanzheng").value =  timeout+'s';
		setTimeout('countdown()',1000);
	    }
	timeout--;
}//验证码加倒计时   


</script>
 
  </head>
  
  <body>
  <div data-role="page" class="wk_addcard">
	<div data-role="header" data-position="fixed">
    <jsp:include page="header.jsp" />
	</div>
   <div role="main" class="ui-content">
   		<p>
               您可以将账户中的余额提取到银行卡中，敬请仔细操作。<br>
        1、提现操作涉及您的资金变动，请仔细核对您的提现信息。<br>2、无限额<br>3、涉及您的资金安全，请仔细操作。
        </p>
          <form method="post" id="translateform" data-ajax="false" action="<%=path%>/json/wapcreatetranslate" >
          <div class="main">
                <div class="ui-grid-a">
                        <div class="ui-block-a"><div class="ui-bar ui-bar-a">银行卡：<em id="bankNone"  style="display: none"> 请先添加银行卡<a  href=""  style="color: red;">现在添加</a></em>
                             <em id="bankTableNone"  style="display: none;color:red;"> 请选择银行卡</em></div></div>
                        <div class="ui-block-b"><div class="ui-bar ui-bar-a"><select id="bankTable" name="bankPid">
                                      <option value="0" >---请选择银行---</option>
                                      <c:forEach var="person" items="${BankList}">
                                      	<option value="${person.personalBankInforId}">${person.accountNums}</option>
                                      </c:forEach>
                               </select></div></div>
                 </div>
                 <div class="ui-grid-a">
                        <div class="ui-block-a"><div class="ui-bar ui-bar-a">余额：</div></div>
                        <div class="ui-block-b"><div class="ui-bar ui-bar-a">
                            ${moneycount.availableMoney}元 
                        </div></div>
                 </div> 
                  <div class="ui-grid-a">
                        <div class="ui-block-a"><div class="ui-bar ui-bar-a">提现金额：<em id="moneyNone"  style="display: none;color:red;"> 请输入正确金额</em></div></div>
                        <div class="ui-block-b"><div class="ui-bar ui-bar-a">
                            <input type="text"  id="money" name="affectMoney"  onkeyup="value=value.replace(/[^0-9.]/g,'')" > 
                        </div></div>
                 </div> 

                 <div class="ui-grid-a">
                        <div class="ui-block-a"><div class="ui-bar ui-bar-a">验证码：<em id="captchaNone"  style="display: none;color:red;"> 请输入正确的验证码</em> <em id="keyNone"  style="display: none;color:red;"> 验证码不对，请重新输入</em></div></div>
                        <div class="ui-block-b"><div class="ui-bar ui-bar-a">
                             <c:if test="${uservip.phoneResult==1}">
                                <input name="key"  type="text"  id="captcha"/><input  type="button" name="yanzheng" id="yanzheng" value="获取验证码" onclick="getCode(${bankbasicinfor.phoneNum})" />
                                 <span id="phoneTip"></span>
                               </c:if>
                               <c:if test="${uservip.phoneResult!=1}">
                               <em> 您还没有进行手机验证，请先进行手机验证 </em>
                               </c:if>
                        </div></div>
                        
                 </div>
                 
                  <div class="ui-grid-a">
                        <div class="ui-block-a"><div class="ui-bar ui-bar-a">支付密码：<em id="pwdNone"  style="display: none;color:red;"> 请输入支付密码</em><em id="errorNone"  style="display: none;color:red;"> 支付密码不对，请重新输入</em></div></div>
                        <div class="ui-block-b"><div class="ui-bar ui-bar-a">
                            <input name="payPwd"  type="password" id="pwd" > 
                        </div></div>
                 </div>  
                 <div class="wk_invest_btn">
                 <input  type="submit"  value="确定提现" style="background: #42a5e7 none repeat scroll 0 0;border-radius: 6px;color: #fff; display: block;font-size: 15px;line-height: 36px;margin: 7px auto 0;padding: 0; text-align: center; width: 96%;" />
                   <input type="hidden" name="userId"  value="${uservip.userId }" /> 
            </div>
                
            </div>
         
                      </form>
   </div>                    
   <div data-role="footer" data-position="fixed">  
    	
    </div>
</div>    
</body>  
 
</html>
