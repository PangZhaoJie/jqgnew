<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();if(path!=null && !path.equals("")){	if(path.contains("/")){		if(path.equals("/")){path = "";	}}else{	path += "/";}} 
String basePath = "";if(request.getServerPort()==80){basePath = request.getScheme()+"://"+request.getServerName()+path+"/";}else{basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";}    
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>支付方式管理</title>
<link href="<%=basePath%>/back/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>/back/css/mem.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>/back/css/tab.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>/back/js/jquery.js"></script>
<%-- <script type="text/javascript" src="<%=basePath%>/back/js/Tabs.js"></script> --%>
<!--折叠js-->
<script type="text/javascript"> 

function addCheck(num){
	if(num==1){
		 $("#panel").show();
		 $("#panel1").hide();
		 $("#ID1").hide();
		 $("#ID2").show();
		  $("#ID4").hide();
		 $("#ID3").show();		 
		}else if(num==3){
		  $("#panel").hide();
		  $("#panel1").show();
		  $("#ID2").hide();
		 $("#ID1").show();	
		  $("#ID3").hide();
		 $("#ID4").show();	
		}else  {
		  $("#panel").hide();
		  $("#panel1").hide();
		   $("#ID1").show();
		 $("#ID3").show();	
		  $("#ID2").hide();
		 $("#ID4").hide();
		}
	}
 
</script>
<!--日历插件 js-->
<script type="text/javascript" src="laydate/laydate.js"></script>
<script type="text/javascript">
function secBoard(elementID,listName,n) {
 var elem = document.getElementById(elementID);
 var elemlist = elem.getElementsByTagName("li");
 for (var i=0; i<elemlist.length; i++) {
  elemlist[i].className = "normal";
  var m = i+1;
  document.getElementById(listName+"_"+m).className = "normal";
 }
  elemlist[n-1].className = "current";
  document.getElementById(listName+"_"+n).className = "current";
}
</script>
<script type="text/javascript" src="<%=basePath%>js/alert.js"></script>
<script type="text/javascript">
function check1(){
	var payPoundage=document.getElementById("payPoundage").value;
	var accountNumber=document.getElementById("accountNumber").value;
	var accountPassword=document.getElementById("accountPassword").value;
	if(payPoundage==null || payPoundage==""){
		Alert("手续费不能为空!");
		return false;
	}
	if(accountNumber==null || accountNumber==""){
		Alert("商户号不能为空!");
		return false;
	}
	if(accountPassword==null || accountPassword==""){
		Alert("支付密钥不能为空！");
		return false;
	}
	var form=document.getElementById("online1");
	form.action="<%=path %>/back/saveOnlineModel1";
	form.submit(); 
	document.getElementById("submit_btn1").style.background = "gray";
    document.getElementById("submit_btn1").value="正在提交...";
    document.getElementById("submit_btn1").style.disabled=false;
	alert("汇潮修改成功！");
}
function check2(){
	var payPoundage1=document.getElementById("payPoundage1").value;
	var accountNumber1=document.getElementById("accountNumber1").value;
	var other1=document.getElementById("other1").value;
	var accountPassword1=document.getElementById("accountPassword1").value;
	if(payPoundage1==null || payPoundage1==""){
		Alert("手续费不能为空!");
		return false;
	}
	if(accountNumber1==null || accountNumber1==""){
		Alert("商户号不能为空!");
		return false;
	}
	if(other1==null || other1==""){
		Alert("终端号不能为空！");
		return false;
	}
	if(accountPassword1==null || accountPassword1==""){
		Alert("商户证书不能为空！");
		return false;
	}
	var form=document.getElementById("online2");
	form.action="<%=path %>/back/saveOnlineModel2";
	form.submit();
	document.getElementById("submit_btn2").style.background = "gray";
    document.getElementById("submit_btn2").value="正在提交...";
    document.getElementById("submit_btn2").style.disabled=false;
	alert("宝付修改成功！");
}
function check3(){
	
	var payPoundage=document.getElementById("payPoundage2").value;
	var accountNumber=document.getElementById("accountNumber2").value;
	var accountPassword=document.getElementById("accountPassword2").value;
	if(payPoundage==null || payPoundage==""){
		Alert("手续费不能为空!");
		return false;
	}
	if(accountNumber==null || accountNumber==""){
		Alert("商户号不能为空!");
		return false;
	}
	if(accountPassword==null || accountPassword==""){
		Alert("商户密钥不能为空！");
		return false;
	}
	var form=document.getElementById("online3");
	form.action="<%=path %>/back/saveOnlineModel1";
	form.submit(); 
	document.getElementById("submit_btn3").style.background = "gray";
    document.getElementById("submit_btn3").value="正在提交...";
    document.getElementById("submit_btn3").style.disabled=false;
	alert("易宝修改成功！");
}
function check4(){
	var payPoundage=document.getElementById("payPoundage3").value;
	var accountNumber=document.getElementById("accountNumber3").value;
	var accountPassword=document.getElementById("accountPassword3").value;
	if(payPoundage==null || payPoundage==""){
		Alert("手续费不能为空!");
		return false;
	}
	if(accountNumber==null || accountNumber==""){
		Alert("商户代码不能为空!");
		return false;
	}
	if(accountPassword==null || accountPassword==""){
		Alert("商户识别码不能为空！");
		return false;
	}
	var form=document.getElementById("online4");
	form.action="<%=path %>/back/saveOnlineModel1";
	form.submit(); 
	document.getElementById("submit_btn4").style.background = "gray";
    document.getElementById("submit_btn4").value="正在提交...";
    document.getElementById("submit_btn4").style.disabled=false;
	alert("国付宝修改成功！");
}
function check6(){
	var payPoundage=0;
	var accountNumber=document.getElementById("accountPassword6").value;
	var accountPassword=document.getElementById("accountPassword6").value;
	var bussIdB2B=document.getElementById("bussIdB2B").value;
	var bussIdB2C=document.getElementById("bussIdB2C").value;
	if(accountNumber==null || accountNumber==""){
		Alert("商户号不能为空!");
		return false;
	}
	if(accountPassword==null || accountPassword==""){
		Alert("MD5 key不能为空!");
		return false;
	}
	if(bussIdB2B==null || bussIdB2B==""){
		Alert("B2B业务代码不能为空!");
		return false;
	}
// 	if(bussIdB2C==null || bussIdB2C==""){
// 		Alert("B2C业务代码不能为空!");
// 		return false;
// 	}
	var form=document.getElementById("online6");
	form.action="<%=path %>/back/saveOnlineModel3";
	form.submit(); 
	document.getElementById("submit_btn5").style.background = "gray";
    document.getElementById("submit_btn5").value="正在提交...";
    document.getElementById("submit_btn5").style.disabled=false;
	alert("易通支付修改成功！");
}
function upload(mark){
	if(mark=="2"){
		secBoard('hotnews_caption','list',2);
	}
}
</script>


</head>


<body onload="upload(${mark});">

	<%-- <div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="javascript:void(0);" onclick="parent.frames.topFrame.overall();">首页</a></li>
    <li><a href="#" onclick="parent.frames[1].location.href='<%=path %>/back/9_left.jsp';  parent.frames[2].location.href='<%=path %>/overall/findlssh'; ">扩展管理</a></li>
    <li><a href="#">线上支付接口管理</a></li>
    </ul>
    </div> --%>
    
    <div class="rightinfo">
    
        <div id="hotnews_caption">
            <ul id="h_menu">                  
                 <li class="current" onclick="secBoard('hotnews_caption','list',1);">汇潮支付</li>
                 <li class="normal" onclick="secBoard('hotnews_caption','list',2);">宝付支付</li>
<!--                  <li class="normal" onclick="secBoard('hotnews_caption','list',3);">易宝支付</li> -->
<!--                  <li class="normal" onclick="secBoard('hotnews_caption','list',4);">国服宝支付</li> -->
				<li class="normal" onclick="secBoard('hotnews_caption','list',5);">易通支付</li>
            </ul>
         </div> 
    
       <div id="hotnews_content">
          <div class="current" id="list_1">
           <form method="post" id="online1">
           <ul class="m_forminfo">
            <li><label class="m_info_title">是否启用:</label> 
                <input type="hidden" name="onlineId" id="onlineId"  value="${onlinemodel1.onlineId}" size="39"/>
                  <label> <input type="radio" name="enable" value="1" <c:if test="${onlinemodel1.enable==1}"> checked="checked"</c:if> id="RadioGroup1_0" /> 是 </label>
                  <label> <input type="radio" name="enable" value="0" <c:if test="${onlinemodel1.enable==0}"> checked="checked"</c:if> id="RadioGroup1_1" /> 否</label>
               
              
            </li>
            <li><label class="m_info_title">充值手续费：</label>  
                <input name="payPoundage" id="payPoundage" type="text" class="dfinput" value="${onlinemodel1.payPoundage}" size="39"/>
                <i>%</i>
            </li>
             <li><label class="m_info_title">商户号：</label>  
                <input name="accountNumber" id="accountNumber" type="text" class="dfinput" value="${onlinemodel1.accountNumber}" size="39"/>
                <i>*</i>
            </li>
             <li><label class="m_info_title">支付密钥：</label>  
                <input name="accountPassword" id="accountPassword" type="text" class="dfinput" value="${onlinemodel1.accountPassword}" size="39"/>
                <i>*</i>
            </li>
           <c:if test="${sessionScope.powerss[81]==1}">
            <li><label class="m_info_title">&nbsp;</label><input id="submit_btn1" type="button" class="btn" value="确定" onclick="check1();"/></li>
          </c:if>
          </ul>
          </form>
       </div> 
       
      <div class="normal" id="list_2">
      	<form method="post" id="online2">
         <ul class="m_forminfo">
            <li><label class="m_info_title">是否启用:</label> 
               
                  <label> <input type="radio" name="enable1" value="1" <c:if test="${onlinemodel2.enable==1}"> checked="checked"</c:if> id="RadioGroup1_0" /> 是 </label>
                  <label> <input type="radio" name="enable1" value="0" <c:if test="${onlinemodel2.enable==0}"> checked="checked"</c:if> id="RadioGroup1_1" /> 否</label>
               
               
            </li>
            <li><label class="m_info_title">充值手续费：</label>  
                <input name="payPoundage1" id="payPoundage1" type="text" class="dfinput" value="${onlinemodel2.payPoundage}" size="39"/>
                <i>%</i>
            </li>
             <li><label class="m_info_title">商户号：</label>  
                <input name="accountNumber1" id="accountNumber1" type="text" class="dfinput" value="${onlinemodel2.accountNumber}" size="39"/>
                <i>*</i>
            </li>
             <li><label class="m_info_title">终端号：</label>  
                <input name="other1" id="other1" type="text" class="dfinput" value="${onlinemodel2.other}" size="39"/>
                <i>*</i>
            </li>
             <li><label class="m_info_title">商户证书：</label>  
                <input name="accountPassword1" id="accountPassword1" type="text" class="dfinput" value="${onlinemodel2.accountPassword}" size="39"/>
                <i>*</i>
            </li>
           <c:if test="${sessionScope.powerss[81]==1}">
            <li><label class="m_info_title">&nbsp;</label><input id="submit_btn2" type="button" class="btn" value="确定" onclick="check2();"/></li>
          </c:if>
          </ul>
          </form>
      </div> 
     
      <div class="normal" id="list_3">
           <form method="post" id="online3">
           <ul class="m_forminfo">
            <li><label class="m_info_title">是否启用:</label> 
                	<input type="hidden" name="onlineId" id="onlineId"  value="${onlinemodel3.onlineId}" size="39"/>
                  <label> <input type="radio" name="enable" value="1" <c:if test="${onlinemodel3.enable==1}"> checked="checked"</c:if> id="RadioGroup1_0" /> 是 </label>
                  <label> <input type="radio" name="enable" value="0" <c:if test="${onlinemodel3.enable==0}"> checked="checked"</c:if> id="RadioGroup1_1" /> 否</label>
               
              
            </li>
            <li><label class="m_info_title">充值手续费：</label>  
                <input name="payPoundage" id="payPoundage2" type="text" class="dfinput" value="${onlinemodel3.payPoundage}" size="39"/>
                <i>%</i>
            </li>
             <li><label class="m_info_title">商户号：</label>  
                <input name="accountNumber" id="accountNumber2" type="text" class="dfinput" value="${onlinemodel3.accountNumber}" size="39"/>
                <i>*</i>
            </li>
             <li><label class="m_info_title">商户密钥：</label>  
                <input name="accountPassword" id="accountPassword2" type="text" class="dfinput" value="${onlinemodel3.accountPassword}" size="39"/>
                <i>*</i>
            </li>
           <c:if test="${sessionScope.powerss[81]==1}">
            <li><label class="m_info_title">&nbsp;</label><input id="submit_btn3" type="button" class="btn" value="确定" onclick="check3();"/></li>
          </c:if>
          </ul>
          </form>
       </div>  
     
     	<div class="normal" id="list_4">
           <form method="post" id="online4">
           <ul class="m_forminfo">
            <li><label class="m_info_title">是否启用:</label> 
                <input type="hidden" name="onlineId" id="onlineId"  value="${onlinemodel4.onlineId}" size="39"/>
                  <label> <input type="radio" name="enable" value="1" <c:if test="${onlinemodel4.enable==1}"> checked="checked"</c:if> id="RadioGroup1_0" /> 是 </label>
                  <label> <input type="radio" name="enable" value="0" <c:if test="${onlinemodel4.enable==0}"> checked="checked"</c:if> id="RadioGroup1_1" /> 否</label>
               
              
            </li>
            <li><label class="m_info_title">充值手续费：</label>  
                <input name="payPoundage" id="payPoundage3" type="text" class="dfinput" value="${onlinemodel4.payPoundage}" size="39"/>
                <i>%</i>
            </li>
             <li><label class="m_info_title">商户代码：</label>  
                <input name="accountNumber" id="accountNumber3" type="text" class="dfinput" value="${onlinemodel4.accountNumber}" size="39"/>
                <i>*</i>
            </li>
             <li><label class="m_info_title">商户识别码：</label>  
                <input name="accountPassword" id="accountPassword3" type="text" class="dfinput" value="${onlinemodel4.accountPassword}" size="39"/>
                <i>*</i>
            </li>
           <c:if test="${sessionScope.powerss[81]==1}">
            <li><label class="m_info_title">&nbsp;</label><input id="submit_btn4" type="button" class="btn" value="确定" onclick="check4();"/></li>
          </c:if>
          </ul>
          </form>
            </div>
       
       	  <div class="normal" id="list_5">
           <form method="post" id="online6">
           <ul class="m_forminfo">
            <li><label class="m_info_title">是否启用:</label> 
            	 <input type="hidden" name="mark" id="mark"  value="3"/>
                <input type="hidden" name="onlineId" id="onlineId"  value="${onlinemodel6.onlineId}" size="39"/>
                  <label> <input type="radio" name="enable" value="1" <c:if test="${onlinemodel6.enable==1}"> checked="checked"</c:if> id="RadioGroup1_0" /> 是 </label>
                  <label> <input type="radio" name="enable" value="0" <c:if test="${onlinemodel6.enable==0}"> checked="checked"</c:if> id="RadioGroup1_1" /> 否</label>
               
              
            </li>
            <li><label class="m_info_title">充值手续费：</label>  
                <input name="payPoundage" id="payPoundage" type="text" class="dfinput" value="${onlinemodel1.payPoundage}" size="39"/>
                <i>%</i>
            </li>
            <li><label class="m_info_title">B2B业务代码：</label>  
                <input name="bussIdB2B" id="bussIdB2B" type="text" class="dfinput" value="${onlinemodel6.bussIdB2B}" size="39"/>
                <i>*</i>
            </li>
            <li><label class="m_info_title">B2C业务代码：</label>  
                <input name="other1" id="bussIdB2C" type="text" class="dfinput" value="${onlinemodel6.other}" size="39"/>
                <i>*</i>
            </li>
            <li><label class="m_info_title">商户号：</label>  
                <input name="accountNumber" id="accountNumber6" type="text" class="dfinput" value="${onlinemodel6.accountNumber}" size="39"/>
                <i>*</i>
            </li>
             <li><label class="m_info_title">支付密钥：</label>  
                <input name="accountPassword" id="accountPassword6" type="text" class="dfinput" value="${onlinemodel6.accountPassword}" size="39"/>
                <i>*</i>
            </li>
           <c:if test="${sessionScope.powerss[81]==1}">
            <li><label class="m_info_title">&nbsp;</label><input id="submit_btn5" type="button" class="btn" value="确定" onclick="check6();"/></li>
          </c:if>
          </ul>
          </form>
       </div>  
     
	  </div> 

      </div>    
</body>

</html>
