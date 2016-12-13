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
        <form action="" id="form1" method="post">
        	<div class="main">
                <div class="ui-grid-a">
                        <div class="ui-block-a"><div class="ui-bar ui-bar-a">开户人姓名</div></div>
                        <div class="ui-block-b"><div class="ui-bar ui-bar-a">
                        	<input name="nameResult" id="nameResult" type="hidden" size="24"  value="${uservip.nameResult}"/>
                        	<c:choose>  
							   <c:when test="${uservip.nameResult==2}">    
									<input type="text" name="accountName" id="textinput-4" placeholder=""  value="${uservip.realName}" >
							   </c:when>  
							     
							   <c:otherwise>  
							   		未进行实名认证，请到PC端认证通过后再操作
							   </c:otherwise>  
							</c:choose>  
                            
                        </div></div>
                 </div><!-- /grid-a --> 
                 <div class="ui-grid-a">
                        <div class="ui-block-a"><div class="ui-bar ui-bar-a">银行卡账号</div></div>
                        <div class="ui-block-b"><div class="ui-bar ui-bar-a">
                            <input type="text"  name="accountNum" id="accountNum"  onkeyup="value=value.replace(/[^0-9]/g,'')" > 
                        </div></div>
                 </div><!-- /grid-a --> 
                 <div class="ui-grid-a">
                        <div class="ui-block-a"><div class="ui-bar ui-bar-a">开户银行</div></div>
                        <div class="ui-block-b"><div class="ui-bar ui-bar-a">
                        	<div class="ui-field-contain">
                           		 <label for="select-native-2"></label>
<!--                                 	<select name="select-native-2" id="select-native-2" data-mini="true"> -->
<!--                                     	<option value="1">请选择开户银行</option> -->
<!--                                         <option value="2">中国农业银行</option> -->
<!--                                         <option value="3">阿联酋银行</option> -->
<!--                                         <option value="4">荷兰郑州银行</option> -->
<!--                                     </select> -->
										<select name="bankPid" id="bankPid" data-mini="true">
										    <option value="0">-----请选择银行------</option>
		                                    <c:forEach var="bp" items="${bpList}">
		                                         <option  value="${bp.bankPid}">${bp.bankPname}</option>	
					                        </c:forEach> 
										</select>
                              </div>
                        </div></div>
                 </div><!-- /grid-a --> 
                 <div class="ui-grid-a">
                        <div class="ui-block-a"><div class="ui-bar ui-bar-a">开户支行名称</div></div>
                        <div class="ui-block-b"><div class="ui-bar ui-bar-a">
                            <input type="text"  name="accountAddress" id="accountAddress" placeholder="" value="">
                        </div></div>
                 </div><!-- /grid-a --> 
            </div>
            <div class="wk_invest_btn">  
    			<a onclick="toSubmit();" >确定添加</a>    
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
  	if(nameResult==""||nameResult==null){
  		alert("请实名认证后再操作");
  		boo=false;
  	}else if(accountNum==""||accountNum==null){
  		alert("银行卡号不能为空");
  		boo=false;
  	}else if(accountAddress==""||accountAddress==null){
  		alert("开户支行名称不能为空");
  		boo=false;
  	}else if(bankPid=="0"){
  		alert("请选择开户行");
  		boo=false;
  	}
  	if(boo==true){
  			var form = document.getElementById("form1");
	        form.action="wap/addCard";
 	        form.submit();
		 	document.activeElement.style.background = "gray";
		    document.activeElement.value="正在提交...";
		    document.activeElement.disabled=false;	
  	}
  	
  
  }
  
  </script>
</html>
