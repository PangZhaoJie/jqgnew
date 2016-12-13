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
<title>无标题文档</title>
<link href="<%=basePath%>/back/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>/back/js/jquery.js"></script>
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
<style>
.tablelist td .input { color: #333333;padding: 0px 5px;  height: 28px; line-height: 28px; margin:8px 0 8px 10px; float:left;border-color: #a7b5bc #ced9df #ced9df #a7b5bc;  border-style: solid;  border-width: 1px;}
.tablelist td { border: 1px solid #ddd;}
</style>
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
<script type="text/javascript">
   function openLoad(open){
	   if(open=='1'){
		   addCheck('1'); 
	   }
   }
   function check(form){
	   var $m=jQuery.noConflict();
	   var accountName=$m("#accountName").val();
	   var accountNum=$m("#accountNum").val();
	   var accountAddress=$m("#accountAddress").val();
// 	   var bankId=document.getElementsByName("bankId");
	   if(accountName==""){
		   $m("#tip1").html("<span style='font-size:12px;color:red;float:left;'>开户户主不能为空</span>");
		   return false;
	   }else{
		   $m("#tip1").html("");
	   }
	   if(accountNum==""){
		   $m("#tip2").html("<span style='font-size:12px;color:red;float:left;'>账号不能为空</span>");
		   return false;
	   }else{
		   $m("#tip2").html("");
	   }
	   if(accountAddress==""){
		   $m("#tip3").html("<span style='font-size:12px;color:red;float:left;'>开户地主不能为空</span>");
		   return false;
	   }else{
		   $m("#tip3").html("");
	   }
	   document.getElementById("showwait").style.background = "gray";
   	   document.getElementById("showwait").value="正在提交...";
       document.getElementById("showwait").style.disabled=false;
	   alert("添加银行成功！");
// 	   var flag=-1;
// 	   for(var i=0;i<bankId.length;i++){
// 		   if(bankId[i].selected){
// 			  flag=i; 
// 		   }
// 	   }
// 	   if(flag<0){
// 		   $m("#tip4").html("<span style='font-size:12px;color:red;float:left; margin-top:0;'>请选择银行名称</span>"); 
// 		   return false;
// 	   }else{
// 		   $m("#tip4").html("");
// 	   }
   }
</script>

</head>


<body onload="openLoad(${open3});">

	<%-- <div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="javascript:void(0);" onclick="parent.frames.topFrame.overall();">首页</a></li>
    <li><a href="#" onclick="parent.frames[1].location.href='<%=path %>/back/9_left.jsp';  parent.frames[2].location.href='<%=path %>/overall/findlssh'; ">扩展管理</a></li>
    <li><a href="#">线下充值银行设置</a></li>
    </ul>
    </div> --%>
    
    <div class="rightinfo">
    
       <div class="tools">
       <div id="panel">
              <div class="panel_title">添加银行</div>
              <div class="panel_nr fn_cle">
                 <form enctype="multipart/form-data" onsubmit="return check(this);" action="<%=path %>/extension/createBank" method="post">
    <dl class="line">
      <dt>银行名称：</dt>
      <dd>
      <div class="uew-select" style="float:left; line-height:34px;"><div class="uew-select-value ue-state-default" style="width: 200px;"><em class="uew-icon uew-icon-triangle-1-s"></em></div> 
                 <select name="bankId" class="input" style="width: 278px;">
                  <c:forEach var="bankparameter" items="${bankparameters}">
                  	<option <c:if test="${bankId==bankparameter.bankPid}">selected="selected"</c:if> value="${bankparameter.bankPid }">${bankparameter.bankPname }</option> 
                  </c:forEach>
                 </select>
                 <span id="tip4" style=" margin-top:0;"></span>
       </div> 
<!--         <input type="text"  id="link_txt" class="input" name="link_txt" size="40"> -->
        
      </dd>
    </dl>
	
    <dl class="line">
      <dt>银行开户户主：</dt>
      <dd>
        <input type="text"  name="accountName" id="accountName" value="${accountName}" class="input"  size="40">
         <span id="tip1"></span>
      </dd>
    </dl>
    
    <dl class="line">
      <dt>银行账号：</dt>
      <dd>
        <input type="text" name="accountNum" id="accountNum" value="${accountNum}" class="input" size="40">
        <span id="tip2"></span>
      </dd>
    </dl>
     <dl class="line">
      <dt>开户行地址：</dt>
      <dd>
        <input type="text" name="accountAddress" value="${accountAddress}" id="accountAddress" class="input"  size="40">
       <span id="tip3"></span>
      </dd>
    </dl>
    
    
    <div class="page_btn">
      <input type="hidden" value="${companyBankInforId}" name="companyBankInforId"/>
	  <input type="hidden" disabled="disabled" value="" id="fid" name="fid">
      <input type="submit" value="添加" onclick="" id="showwait" class="btn_b">
    </div>
	</form>
              </div>
         </div>
          <c:if test="${sessionScope.powerss[78]==1}">
    	<ul class="toolbar">
         <li  id="ID1" onclick="addCheck('1')"><span><img src="<%=basePath%>/back/images/t01.png" /></span>添加银行</li>
         <li  id="ID2" style="display:none" onclick="addCheck('2')"><span><img src="<%=basePath%>/back/images/t01.png" /></span>添加完毕</li>
        
<%--          <li><span><img src="images/t03.png" /></span>删除银行</li> --%>
       
        </ul>
        </c:if>
        
        
       
    
    </div>
    
    
      <table class="tablelist">
    	<thead>
    	<tr>
        <th>序号</th>
        <th>银行名称</th>
        <th>收款人</th>
        <th>收款账号</th>
        <th>开户地址</th>
        <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="bank" items="${companybankinfors}">
        <tr>
        <td>${bank.companyBankInforId}</td>
        <td>${bank.bankparameter.bankPname}</td>
        <td>${bank.accountName}</td>
        <td>${bank.accountNum}</td>
        <td>${bank.accountAddress}</td>
        <td>
        <c:if test="${sessionScope.powerss[79]==1}">
        <a href="<%=path %>/extension/editBank?companyBankInforId=${bank.companyBankInforId}" class="tablelink">编辑</a>|
        </c:if>
        <c:if test="${sessionScope.powerss[79]!=1}">
        <a>--</a>|
        </c:if>
        <c:if test="${sessionScope.powerss[121]==1}">
         <a href="<%=path %>/extension/delBank?companyBankInforId=${bank.companyBankInforId}" class="tablelink"> 删除</a>
         </c:if>
         <c:if test="${sessionScope.powerss[121]!=1}">
         <a>--</a>
         </c:if>
        </td>
        </tr>
        </c:forEach> 
<!--          <tr> -->
<!--          <td colspan="2" >说明</td> -->
<!--          <td colspan="5"><textarea name="" style="width:630px; height:80px; border:1px solid #ccc; padding:8px; margin:20px 0; line-height:25px; color:#666; font-size:12px;">您转账成功后，请您保留银行转账电子回单或者流水号，提交给客服，方便为您快速处理到帐，谢谢您的支持！</textarea></td> -->
<!--         </tr> -->
        
        
         
        </tbody>
    </table>
    
<!--      <div class="page_btn" style="width:85%; padding-left:15%; padding-top:15px; margin-top:15px; height:30px;"> -->
<!-- 	  <input type="submit" value="保存设置" onclick="" id="showwait" class="btn_b"> -->
<!--     </div> -->
     </div>
    
   
</body>

</html>

