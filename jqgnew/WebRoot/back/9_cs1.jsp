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
<title>业务参数管理</title>
<link href="<%=basePath%>/back/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>/back/css/mem.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>/back/css/tab1.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>/back/js/jquery.js"></script>
<!--折叠js-->
<script type="text/javascript"> 

function addCheck(num){
	if(num==1){
		 $("#panel").show();
		 $("#ID1").hide();
		 $("#ID2").show();	 
		}else if(num==3){
		  $("#panel").hide();
		  $("#ID2").hide();
		 $("#ID1").show();	
		}else  {
		  $("#panel").hide();
		   $("#ID1").show();
		  $("#ID2").hide();
		}
	}
function addCheck1(num){
	if(num==1){
		 $("#panel1").show();
		 $("#ID11").hide();
		 $("#ID21").show();	 
		}else if(num==3){
		  $("#panel1").hide();
		  $("#ID21").hide();
		 $("#ID11").show();	
		}else  {
		  $("#panel1").hide();
		   $("#ID11").show();
		  $("#ID21").hide();
		}
	}
function addCheck2(num){
	if(num==1){
		 $("#panel2").show();
		 $("#ID12").hide();
		 $("#ID22").show();	 
		}else if(num==3){
		  $("#panel2").hide();
		  $("#ID22").hide();
		 $("#ID12").show();	
		}else  {
		  $("#panel2").hide();
		   $("#ID12").show();
		  $("#ID22").hide();
		}
	}
function addCheck3(num){
	if(num==1){
		 $("#panel3").show();
		 $("#ID13").hide();
		 $("#ID23").show();	 
		}else if(num==3){
		  $("#panel3").hide();
		  $("#ID23").hide();
		 $("#ID13").show();	
		}else  {
		  $("#panel3").hide();
		   $("#ID13").show();
		  $("#ID23").hide();
		}
	}
function addCheck4(num){
	if(num==1){
		 $("#panel4").show();
		 $("#ID14").hide();
		 $("#ID24").show();	 
		}else if(num==3){
		  $("#panel4").hide();
		  $("#ID24").hide();
		 $("#ID14").show();	
		}else  {
		  $("#panel4").hide();
		   $("#ID14").show();
		  $("#ID24").hide();
		}
	}
function addCheck5(num){
	if(num==1){
		 $("#panel5").show();
		 $("#ID15").hide();
		 $("#ID25").show();	 
		}else if(num==3){
		  $("#panel5").hide();
		  $("#ID25").hide();
		 $("#ID15").show();	
		}else  {
		  $("#panel5").hide();
		   $("#ID15").show();
		  $("#ID25").hide();
		}
	}
function addCheck6(num){
	if(num==1){
		 $("#panel6").show();
		 $("#ID16").hide();
		 $("#ID26").show();	 
		}else if(num==3){
		  $("#panel6").hide();
		  $("#ID26").hide();
		 $("#ID16").show();	
		}else  {
		  $("#panel6").hide();
		   $("#ID16").show();
		  $("#ID26").hide();
		}
	}
 
 function addCheck7(num){
	if(num==1){
		$("#panel7").show();
	}else if(num==3){
		$("#panel7").hide();
	}else  {
		$("#panel7").hide();
	}
}
</script>
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
   function check(form){
	   var $m=jQuery.noConflict();
	   var moneyUseName=$m("#moneyUseName").val();
	   if(moneyUseName==""){
		   $m("#tip").html("<span style='font-size:12px;color:red;float:left;'>参数名不能为空</span>");
		   return false;
	   }else{
		   $m("#tip").html("");
	   }
	   document.getElementById("showwait").style.background="gray";
	   document.getElementById("showwait").value="正在提交....";
	   document.getElementById("showwait").style.disabled=false;
	   alert("添加成功！");
   }
   function check1(form){
	   var $m=jQuery.noConflict();
	   var moneyMinName=$m("#moneyMinName").val();
	   if(moneyMinName==""){
		   $m("#tip1").html("<span style='font-size:12px;color:red;float:left;'>参数名不能为空</span>");
		   return false;
	   }else{
		   if(!(/^[-]?\d+$/.test(moneyMinName))){
			   $m("#tip1").html("<span style='font-size:12px;color:red;float:left;'>参数名不能为小数</span>");
			   return false;
		   }else if((parseInt(moneyMinName)%50)!=0){
			   $m("#tip1").html("<span style='font-size:12px;color:red;float:left;'>参数名必须为50的倍数</span>");
			   return false;
		   }else{
			   $m("#tip1").html("");
		   }
		   document.getElementById("showwait").style.background="gray";
	       document.getElementById("showwait").value="正在提交....";
	       document.getElementById("showwait").style.disabled=false;
		   alert("添加成功！");
	   }
   }
   function check2(form){
	   var $m=jQuery.noConflict();
	   var moneyMaxName=$m("#moneyMaxName").val();
	   if(moneyMaxName==""){
		   $m("#tip2").html("<span style='font-size:12px;color:red;float:left;'>参数名不能为空</span>");
		   return false;
	   }else{
		   if(!(/^[-]?\d+$/.test(moneyMaxName))){
			   $m("#tip2").html("<span style='font-size:12px;color:red;float:left;'>参数名不能为小数</span>");
			   return false;
		   }else if((parseInt(moneyMaxName)%50)!=0){
			   $m("#tip2").html("<span style='font-size:12px;color:red;float:left;'>参数名必须为50的倍数</span>");
			   return false;
		   }else{
			   $m("#tip2").html("");
		   }
		   $m("#tip2").html("");
		   document.getElementById("showwait").style.background="gray";
	       document.getElementById("showwait").value="正在提交....";
	       document.getElementById("showwait").style.disabled=false;
		   alert("添加成功！");
	   }
   }
   function check3(form){
	   var $m=jQuery.noConflict();
	   var validTimeName=$m("#validTimeName").val();
	   if(validTimeName==""){
		   $m("#tip3").html("<span style='font-size:12px;color:red;float:left;'>参数名不能为空</span>");
		   return false;
	   }else{
		   $m("#tip3").html("");
	   }
	   document.getElementById("showwait").style.background="gray";
	   document.getElementById("showwait").value="正在提交....";
	   document.getElementById("showwait").style.disabled=false;
	   alert("添加成功！");
   }
   function check4(form){
	   var $m=jQuery.noConflict();
	   var findMoneyName=$m("#findMoneyName").val();
	   var findMoneyNumber=$m("#findMoneyNumber").val();
	   if(findMoneyName==""){
		   $m("#tip4").html("<span style='font-size:12px;color:red;float:left;'>参数名不能为空</span>");
		   return false;
	   }else{
		   $m("#tip4").html("");
	   }
	   if(findMoneyNumber==""){
		   $m("#tip5").html("<span style='font-size:12px;color:red;float:left;'>参数值不能为空</span>");
		   return false;
	   }else{
		   $m("#tip5").html("");
	   }
	   document.getElementById("showwait").style.background="gray";
	   document.getElementById("showwait").value="正在提交....";
	   document.getElementById("showwait").style.disabled=false;
	   alert("添加成功！");
   }
   function check5(form){
	   var $m=jQuery.noConflict();
	   var bankPname=$m("#bankPname").val();
	   var bankPnumber=$m("#bankPnumber").val();
	   if(bankPname==""){
		   $m("#tip6").html("<span style='font-size:12px;color:red;float:left;'>参数名不能为空</span>");
		   return false;
	   }else{
		   $m("#tip6").html("");
	   }
	   if(bankPnumber==""){
		   $m("#tip7").html("<span style='font-size:12px;color:red;float:left;'>参数值不能为空</span>");
		   return false;
	   }else{
		   $m("#tip7").html("");
	   }
	   document.getElementById("showwait").style.background="gray";
	   document.getElementById("showwait").value="正在提交....";
	   document.getElementById("showwait").style.disabled=false;
	   alert("添加成功！");
   }
   function check6(form){
	   var $m=jQuery.noConflict();
	   var integralPname=$m("#integralPname").val();
	   var integralPnumber=$m("#integralPnumber").val();
	   var integralPdescription=$m("#integralPdescription").val();
	   if(integralPname==""){
		   $m("#tip8").html("<span style='font-size:12px;color:red;float:left;'>参数名不能为空</span>");
		   return false;
	   }else{
		   $m("#tip8").html("");
	   }
	   if(integralPnumber==""){
		   $m("#tip9").html("<span style='font-size:12px;color:red;float:left;'>积分不能为空</span>");
		   return false;
	   }else{
		   $m("#tip9").html("");
	   }
	   if(integralPdescription==""){
		   $m("#tip10").html("<span style='font-size:12px;color:red;float:left;'>描述不能为空</span>");
		   return false;
	   }else{
		   $m("#tip10").html("");
	   }
	   document.getElementById("showwait").style.background="gray";
	   document.getElementById("showwait").value="正在提交....";
	   document.getElementById("showwait").style.disabled=false;
	   alert("添加成功！");
   }
   
   function check7(form){
	   var $m=jQuery.noConflict();
	   var parname=$m("#parname").val();
	   var parvalue=$m("#parvalue").val();
	   var pardesc=$m("#pardesc").val();
	   if(parname==''){
		   $m("#tip11").html("<span style='font-size:12px;color:red;float:left;'>参数名不能为空</span>");
		   return false;
	   }else{
		   $m("#tip11").html("");
	   }
	   if(parvalue==""){
		   $m("#tip12").html("<span style='font-size:12px;color:red;float:left;'>参数值不能为空</span>");
		   return false;
	   }else{
		   $m("#tip12").html("");
	   }
	   if(pardesc==""){
		   $m("#tip13").html("<span style='font-size:12px;color:red;float:left;'>描述不能为空</span>");
		   return false;
	   }else{
		   $m("#tip13").html("");
	   }
   }
</script>
<script type="text/javascript">
  function openLoad(open,open1,open2,open3,open4,open5,open6,open7,mark){
	   if(mark=='2'){
		   secBoard('hotnews_caption','list',2);
	   }else if(mark=='3'){
		   secBoard('hotnews_caption','list',3);
	   }else if(mark=='4'){
		   secBoard('hotnews_caption','list',4);
	   }else if(mark=='5'){
		   secBoard('hotnews_caption','list',5);
       }else if(mark=='6'){
    	   secBoard('hotnews_caption','list',6);
       }else if(mark=='7'){
    	   secBoard('hotnews_caption','list',7);
       }else if(mark=='8'){
    	   secBoard('hotnews_caption','list',8);
       }
	   if(open=='1'){
		   addCheck('1'); 
	   }
	   if(open1=='1'){
		   addCheck1('1'); 
	   }
	   if(open2=='1'){
		   addCheck2('1'); 
	   }
	   if(open3=='1'){
		   addCheck3('1'); 
	   }
	   if(open4=='1'){
		   addCheck4('1'); 
	   }
	   if(open5=='1'){
		   addCheck5('1'); 
	   }
	   if(open6=='1'){
		   addCheck6('1'); 
	   }
	   if(open7=='1'){
		   addCheck7('1'); 
	   }
   }
</script>

</head>


<body onload="openLoad(${open},${open1},${open2},${open3},${open4},${open5},${open6},${open7},${mark});">

	<%-- <div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="javascript:void(0);" onclick="parent.frames.topFrame.overall();">首页</a></li>
    <li><a href="javascript:void(0);" onclick="parent.frames.topFrame.expand();"> 扩展管理</a></li>
    <li><a href="#">业务参数管理</a></li>
    </ul>
    </div> --%>
    
    <div class="rightinfo">
    
        <div id="hotnews_caption">
            <ul id="h_menu">                  
                 <li class="current" onclick="secBoard('hotnews_caption','list',1);">借款用途</li>
                 <li class="normal" onclick="secBoard('hotnews_caption','list',2);">最小金额</li>
                 <li class="normal" onclick="secBoard('hotnews_caption','list',3);">最大金额</li>
                 <li class="normal" onclick="secBoard('hotnews_caption','list',4);">募资时间</li>
                 <li class="normal" onclick="secBoard('hotnews_caption','list',5);">查询金额</li>
                 <li class="normal" onclick="secBoard('hotnews_caption','list',6);">提现银行</li>
                 <li class="normal" onclick="secBoard('hotnews_caption','list',7);">积分参数</li>
                 <li class="normal" onclick="secBoard('hotnews_caption','list',8);">费用参数</li>
            </ul>
         </div> 
    
       <div id="hotnews_content">
          <div class="current" id="list_1">
          
<!--              <div class="list_tip"> -->
<!--               <ul> -->
<!--                 <li>1、参数新增或者删除操作时，请记住点击下方的确认按钮来提交您的新数据！</li>    -->
<!--                 <li>2、所有参数的修改或者删除操作提交一次即可,修改后请清空数据缓存，以便新参数即时生效！</li> -->
<!--               </ul> -->
<!--            </div> -->
          <div class="tools">
       <div id="panel">
              <div class="panel_title">添加一个级别</div>
              <div class="panel_nr fn_cle">
    <form enctype="multipart/form-data" onsubmit="return check(this);" action="<%=path %>/use/createMu" method="post">
    <dl class="line">
      <dt>参数名：</dt>
      <dd>
        <input type="text" value="${moneyUseName}" id="moneyUseName" class="input" name="moneyUseName" size="40"/>
        <span id="tip"></span>
      </dd>
    </dl>
    
    
    <div class="page_btn">
	   <input type="hidden" name="moneyUseId" value="${moneyUseId}"/>
      <input type="submit" value="添加" onclick="" id="showwait" class="btn_b">
    </div>
	</form>
              </div>
         </div>
         <c:if test="${sessionScope.powerss[66]==1}">
    	<ul class="toolbar">
         <li  id="ID1" style="margin-left:10px;" onclick="addCheck('1')" ><span><img src="<%=basePath%>/back/images/t01.png" /></span>添加一个级别</li>
         <li  id="ID2" style="display:none" onclick="addCheck('2')"><span><img src="<%=basePath%>/back/images/t01.png" /></span>添加完毕</li>
        
<%--          <li><span><img src="images/t03.png" /></span>删除级别</li> --%>
        </ul>
        </c:if>
      </div> 
            
          <table class="tablelist">
    	<thead>
    	<tr>
           <th>序号</th>
           <th>参数名称</th>
           <th>操作</th>
        </tr>
        </thead>
        
        <tbody>
        <c:forEach var="moneyuse" items="${moneyuses}">
        <tr>
           <td>${moneyuse.moneyUseId}</td>
           <td>${moneyuse.moneyUseName}</td>
           <td>
           <c:if test="${sessionScope.powerss[67]==1}">
           <a href="<%=path %>/use/editMu?moneyUseId=${moneyuse.moneyUseId}" class="tablelink">编辑</a>
<%--            <a href="<%=path %>/use/delMu?moneyUseId=${moneyuse.moneyUseId}" class="tablelink"> 删除</a> --%>
           </c:if>
           <c:if test="${sessionScope.powerss[67]!=1}">--</c:if>
           </td>
        </tr>
        </c:forEach>     
        </tbody> </table>
    
       </div> 
       
      <div class="normal" id="list_2">
         
<!--          <div class="list_tip"> -->
<!--             <ul> -->
<!--               <li>1、参数新增或者删除操作时，请记住点击下方的确认按钮来提交您的新数据！</li>    -->
<!--               <li>2、所有参数的修改或者删除操作提交一次即可,修改后请清空数据缓存，以便新参数即时生效！</li> -->
<!--             </ul> -->
<!--          </div> -->

     <div class="tools">
       <div id="panel1">
              <div class="panel_title">添加一个级别</div>
              <div class="panel_nr fn_cle">
                 <form enctype="multipart/form-data" onsubmit="return check1(this);" action="<%=path %>/use/createMmin" method="post">
    <dl class="line">
      <dt>参数名：</dt>
      <dd>
        <input type="text" value="${moneyMinName}" id="moneyMinName" class="input" name="moneyMinName" size="40">
        <span id="tip1"></span>
      </dd>
    </dl>  
    <div class="page_btn">
	  <input type="hidden" value="${moneyMinId}" name="moneyMinId"/>
      <input type="submit" value="添加" onclick="" id="showwait" class="btn_b">
    </div>
	</form>
              </div>
         </div>
        <c:if test="${sessionScope.powerss[66]==1}">
    	<ul class="toolbar">
         <li  id="ID11" style="margin-left:10px;" onclick="addCheck1('1')" ><span><img src="<%=basePath%>/back/images/t01.png" /></span>添加一个级别</li>
         <li  id="ID21" style="display:none;"" onclick="addCheck1('2')"><span><img src="<%=basePath%>/back/images/t01.png" /></span>添加完毕</li>
        </ul>
        </c:if>
      </div> 
            
      <table class="tablelist">
    	<thead>
    	<tr>
           <th>序号</th>
           <th>参数名称</th>
           <th>操作</th>
        </tr>
        </thead>
        
        <tbody>
        <c:forEach var="moneymin" items="${moneymins}">
        <tr>
           <td>${moneymin.moneyMinId}</td>
           <td>${moneymin.moneyMinName}</td>
           <td>
           	 <c:if test="${sessionScope.powerss[67]==1}">	
           		<a href="<%=path %>/use/editMmin?moneyMinId=${moneymin.moneyMinId}" class="tablelink">编辑</a>
<%--            <a href="<%=path %>/use/delMmin?moneyMinId=${moneymin.moneyMinId}" class="tablelink"> 删除</a> --%>
			 </c:if>
			 <c:if test="${sessionScope.powerss[67]!=1}">--</c:if>
           </td>
        </tr> 
        </c:forEach>
        </tbody> </table>
      </div> 
      
      <div class="normal" id="list_3">
<!--          <div class="list_tip"> -->
<!--             <ul> -->
<!--               <li>1、参数新增或者删除操作时，请记住点击下方的确认按钮来提交您的新数据！</li>    -->
<!--               <li>2、所有参数的修改或者删除操作提交一次即可,修改后请清空数据缓存，以便新参数即时生效！</li> -->
<!--             </ul> -->
<!--          </div> -->
         
         
      <div class="tools">
       <div id="panel2" style="display:none">
              <div class="panel_title">添加一个级别</div>
              <div class="panel_nr fn_cle">
                 <form enctype="multipart/form-data" onsubmit="return check2(this);" action="<%=path %>/use/createMmax" method="post">
    <dl class="line">
      <dt>参数名：</dt>
      <dd>
        <input type="text" value="${moneyMaxName}" id="moneyMaxName" class="input" name="moneyMaxName" size="40">
        <span id="tip2"></span>
      </dd>
    </dl>
    <div class="page_btn">
	  <input type="hidden" value="${moneyMaxId}" name="moneyMaxId"/>
      <input type="submit" value="添加" onclick="" id="showwait" class="btn_b">
    </div>
	</form>
              </div>
         </div>
        <c:if test="${sessionScope.powerss[66]==1}">
    	<ul class="toolbar">
         <li  id="ID12" style="margin-left:10px;" onclick="addCheck2('1')" ><span><img src="<%=basePath%>/back/images/t01.png" /></span>添加一个级别</li>
         <li  id="ID22" style="display:none" onclick="addCheck2('2')"><span><img src="<%=basePath%>/back/images/t01.png" /></span>添加完毕</li>
        </ul>
        </c:if>
      </div> 
            
      <table class="tablelist">
    	<thead>
    	<tr>
           <th>序号</th>
           <th>参数名称</th>
           <th>操作</th>
        </tr>
        </thead>
        
        <tbody>
        <c:forEach var="moneymax" items="${moneymaxs}">
        <tr>
           <td>${moneymax.moneyMaxId}</td>
           <td>${moneymax.moneyMaxName}</td>
           <td>
           	<c:if test="${sessionScope.powerss[67]==1}">
           		<a href="<%=path %>/use/editMmax?moneyMaxId=${moneymax.moneyMaxId}" class="tablelink">编辑</a>     
<%--            <a href="<%=path %>/use/delMmax?moneyMaxId=${moneymax.moneyMaxId}" class="tablelink"> 删除</a> --%>
           </c:if>
           <c:if test="${sessionScope.powerss[67]!=1}">--</c:if>
           </td>
        </tr>  
        </c:forEach>
        </tbody> </table>
      </div>
      
      <div class="normal" id="list_4">
<!--          <div class="list_tip"> -->
<!--             <ul> -->
<!--               <li>1、参数新增或者删除操作时，请记住点击下方的确认按钮来提交您的新数据！</li>    -->
<!--               <li>2、所有参数的修改或者删除操作提交一次即可,修改后请清空数据缓存，以便新参数即时生效！</li> -->
<!--             </ul> -->
<!--          </div> -->
      
        <div class="tools">
       <div id="panel3" style="display:none">
              <div class="panel_title">添加一个级别</div>
              <div class="panel_nr fn_cle">
                 <form enctype="multipart/form-data" onsubmit="return check3(this);" action="<%=path %>/use/createVt" method="post">
    <dl class="line">
      <dt>参数名：</dt>
      <dd>
        <input type="text" value="${validTimeName}" id="validTimeName" class="input" name="validTimeName" size="40">
        <span id="tip3"></span>
      </dd>
    </dl>   
    <div class="page_btn">
	  <input type="hidden" value="${validTimeId}" name="validTimeId" />
      <input type="submit" value="添加" onclick="" id="showwait" class="btn_b">
    </div>
	</form>
              </div>
         </div>
        <c:if test="${sessionScope.powerss[66]==1}">
    	<ul class="toolbar">
         <li  id="ID13" style="margin-left:10px;" onclick="addCheck3('1')" ><span><img src="<%=basePath%>/back/images/t01.png" /></span>添加一个级别</li>
         <li  id="ID23" style="display:none" onclick="addCheck3('2')"><span><img src="<%=basePath%>/back/images/t01.png" /></span>添加完毕</li>
        </ul>
        </c:if>
      </div> 
            
       <table class="tablelist">
    	<thead>
    	<tr>
           <th>序号</th>
           <th>参数名称</th>
           <th>操作</th>
        </tr>
        </thead>
        
        <tbody>
        <c:forEach var="validtime" items="${validtimes}">
        <tr>
           <td>${validtime.validTimeId}</td>
           <td>${validtime.validTimeName}</td>
           <td>
           	<c:if test="${sessionScope.powerss[67]==1}">
          	 	<a href="<%=path %>/use/editVt?validTimeId=${validtime.validTimeId}" class="tablelink">编辑</a>     
<%--            <a href="<%=path %>/use/delVt?validTimeId=${validtime.validTimeId}" class="tablelink"> 删除</a> --%>
           </c:if>
           <c:if test="${sessionScope.powerss[67]!=1}">--</c:if>
           </td>
        </tr> 
        </c:forEach>
        </tbody>
    </table>
      </div>
       
      <div class="normal" id="list_5">
<!--          <div class="list_tip"> -->
<!--             <ul> -->
<!--               <li>1、参数新增或者删除操作时，请记住点击下方的确认按钮来提交您的新数据！</li>    -->
<!--               <li>2、所有参数的修改或者删除操作提交一次即可,修改后请清空数据缓存，以便新参数即时生效！</li> -->
<!--             </ul> -->
<!--          </div> -->
      
        <div class="tools">
       <div id="panel4" style="display:none">
              <div class="panel_title">添加一个级别</div>
              <div class="panel_nr fn_cle">
                 <form enctype="multipart/form-data" onsubmit="return check4(this);" action="<%=path %>/use/createFm" method="post">
    <dl class="line">
      <dt>参数名：</dt>
      <dd>
        <input type="text" value="${findMoneyName}" id="findMoneyName" class="input" name="findMoneyName" size="40">
        <span id="tip4"></span>
      </dd>
    </dl>
    <dl class="line">
      <dt>参数值：</dt>
      <dd>
        <input type="text" value="${findMoneyNumber}" id="findMoneyNumber" class="input" name="findMoneyNumber" size="40">
        <span id="tip5"></span>
      </dd>
    </dl>
    <div class="page_btn">
	  <input type="hidden" value="${findMoneyId}" name="findMoneyId" />
      <input type="submit" value="添加" onclick="" id="showwait" class="btn_b">
    </div>
	</form>
              </div>
         </div>
        <c:if test="${sessionScope.powerss[66]==1}">
    	<ul class="toolbar">
         <li  id="ID14" style="margin-left:10px;" onclick="addCheck4('1')" ><span><img src="<%=basePath%>/back/images/t01.png" /></span>添加一个级别</li>
         <li  id="ID24" style="display:none" onclick="addCheck4('2')"><span><img src="<%=basePath%>/back/images/t01.png" /></span>添加完毕</li>
        </ul>
        </c:if>
      </div> 
            
        <table class="tablelist">
    	<thead>
    	<tr>
           <th>序号</th>
           <th>参数名称</th>
           <th>参数值</th>
           <th>操作</th>
        </tr>
        </thead>
        
        <tbody>
        <c:forEach var="findmoney" items="${findmoneys}">
        <tr>
           <td>${findmoney.findMoneyId}</td>
           <td>${findmoney.findMoneyName}</td>
           <td>${findmoney.findMoneyNumber}</td>
           <td>
           	<c:if test="${sessionScope.powerss[67]==1}">
           		<a href="<%=path %>/use/editFm?findMoneyId=${findmoney.findMoneyId}" class="tablelink">编辑</a>     
<%--            <a href="<%=path %>/use/delFm?findMoneyId=${findmoney.findMoneyId}" class="tablelink"> 删除</a> --%>
           </c:if>
           <c:if test="${sessionScope.powerss[67]!=1}">--</c:if>
           </td>
        </tr> 
        </c:forEach>
        </tbody> </table>
      </div>
        
      <div class="normal" id="list_6">
<!--          <div class="list_tip"> -->
<!--             <ul> -->
<!--               <li>1、参数新增或者删除操作时，请记住点击下方的确认按钮来提交您的新数据！</li>    -->
<!--               <li>2、所有参数的修改或者删除操作提交一次即可,修改后请清空数据缓存，以便新参数即时生效！</li> -->
<!--             </ul> -->
<!--          </div> -->
         
        <div class="tools">
       <div id="panel5" style="display:none">
              <div class="panel_title">添加一个级别</div>
              <div class="panel_nr fn_cle">
                 <form enctype="multipart/form-data" onsubmit="return check5(this);" action="<%=path %>/use/createBp" method="post">
    <dl class="line">
      <dt>参数名：</dt>
      <dd>
        <input type="text" value="${bankPname}" id="bankPname" class="input" name="bankPname" size="40">
        <span id="tip6"></span>
      </dd>
    </dl>
	
    <dl class="line">  <dt>图片参数值：</dt>
      <dd>
        <input type="text" value="${bankPnumber}" id="bankPnumber" class="input" name="bankPnumber" size="40">格式如"/images/bank/+图片名"，并确保图片存在
        <span id="tip7"></span>
      </dd>
    </dl>
    
    
    
    <div class="page_btn">
	 	<input type="hidden" name="bankPid" value="${bankPid}"/>
      <input type="submit" value="添加" onclick="" id="showwait" class="btn_b">
    </div>
	</form>
              </div>
         </div>
        <c:if test="${sessionScope.powerss[66]==1}">
    	<ul class="toolbar">
         <li  id="ID15" style="margin-left:10px;" onclick="addCheck5('1')" ><span><img src="<%=basePath%>/back/images/t01.png" /></span>添加一个级别</li>
         <li  id="ID25" style="display:none" onclick="addCheck5('2')"><span><img src="<%=basePath%>/back/images/t01.png" /></span>添加完毕</li>
         <span style="float:left; padding:8px 0px 0px 5px;">如需新增图标，请先将图片通过FTP上传到您服务器项目下的UF\images\bank目录下 </span>
        </ul>
        </c:if>
      </div> 
            
        <table class="tablelist">
    	<thead>
    	<tr>
           <th>序号</th>
           <th>参数名称</th>
           <th>参数值</th>
           <th>操作</th>
        </tr>
        </thead>
        
        <tbody>
        <c:forEach var="bankparameter" items="${bankparameters}">
        <tr>
           <td>${bankparameter.bankPid}</td>
           <td>${bankparameter.bankPname}</td>
           <td><img src="<%=basePath %>${bankparameter.bankPnumber}"/></td>
           <td>
           	<c:if test="${sessionScope.powerss[67]==1}">
           		<a href="<%=path %>/use/editBp?bankPid=${bankparameter.bankPid}" class="tablelink">编辑</a>
<%--            <a href="<%=path %>/use/delBp?bankPid=${bankparameter.bankPid}" class="tablelink"> 删除</a> --%>
           </c:if>
           <c:if test="${sessionScope.powerss[67]!=1}">--</c:if>
           </td>
        </tr>
        </c:forEach> 
        </tbody> </table>
      
      </div>
         
     <div class="normal" id="list_7">
<!--         <div class="list_tip"> -->
<!--             <ul> -->
<!--               <li>1、参数新增或者删除操作时，请记住点击下方的确认按钮来提交您的新数据！</li>    -->
<!--               <li>2、所有参数的修改或者删除操作提交一次即可,修改后请清空数据缓存，以便新参数即时生效！</li> -->
<!--             </ul> -->
<!--          </div> -->
      <div class="tools">
       <div id="panel6" style="display:none">
              <div class="panel_title">编辑一个积分参数</div>
              <div class="panel_nr fn_cle">
                 <form enctype="multipart/form-data" onsubmit="return check6(this);" action="<%=path %>/use/createIp" method="post">
    <dl class="line">
      <dt>参数名：</dt>
      <dd>
        <input type="text" value="${integralPname}" id="integralPname" class="input" name="integralPname" size="40" readonly="readonly">
        <span id="tip8"></span>
      </dd>
    </dl>
	
    <dl class="line">  <dt>参数值：</dt>
      <dd>
        <input type="text" value="${integralPnumber}" id="integralPnumber" class="input" name="integralPnumber" size="40">
        <span id="tip9"></span>
      </dd>
    </dl>
      <dl class="line">  <dt>描述：</dt>
      <dd>
        <input type="text" value="${integralPdescription}" id="integralPdescription" class="input" name="integralPdescription" size="40" readonly="readonly">
        <span id="tip10"></span>
      </dd>
    </dl>
    
    
    
    <div class="page_btn">
	 <input type="hidden" name="integralPid" value="${integralPid}" />
      <input type="submit" value="确定" onclick="" id="showwait" class="btn_b">
    </div>
	</form>
              </div>
         </div>
    	<ul class="toolbar">
    	  <li style="margin-left:10px;" ><span></span>积分参数信息列表</li>
<%--          <li  id="ID16" style="margin-left:10px;" onclick="addCheck6('1')" ><span><img src="<%=basePath%>/back/images/t01.png" /></span>添加一个级别</li> --%>
<%--          <li  id="ID26" style="display:none" onclick="addCheck6('2')"><span><img src="<%=basePath%>/back/images/t01.png" /></span>添加完毕</li> --%>
       
        </ul>
      </div>
      <table class="tablelist">
    	<thead>
    	<tr>
           <th>序号</th>
           <th>参数名称</th>
           <th>积分</th>
           <th>描述</th>
           <th>操作</th>
        </tr>
        </thead>
        
        <tbody>
        <c:forEach var="inte" items="${integralparameters}">
        <tr>
           <td>${inte.integralPid}</td>
           <td>${inte.integralPname}</td>
           <td>${inte.integralPnumber}</td>
           <td>${inte.integralPdescription}</td>
           <td>
           <c:if test="${sessionScope.powerss[67]==1}">
           <a href="<%=path %>/use/editIp?integralPid=${inte.integralPid}" class="tablelink">编辑</a>     
<%--       <a href="<%=path %>/use/delIp?integralPid=${inte.integralPid}" class="tablelink"> 删除</a> --%>
           </c:if>
           <c:if test="${sessionScope.powerss[67]!=1}">--</c:if>
           </td>
        </tr>
        </c:forEach>  
        </tbody> </table>
      </div>
      <div class="normal" id="list_8">
<!--         <div class="list_tip"> -->
<!--             <ul> -->
<!--               <li>1、参数新增或者删除操作时，请记住点击下方的确认按钮来提交您的新数据！</li>    -->
<!--               <li>2、所有参数的修改或者删除操作提交一次即可,修改后请清空数据缓存，以便新参数即时生效！</li> -->
<!--             </ul> -->
<!--          </div> -->
      <div class="tools">
       <div id="panel7" style="display:none">
              <div class="panel_title">编辑一个费用参数</div>
              <div class="panel_nr fn_cle">
                 <form enctype="multipart/form-data" onsubmit="return check7(this);" action="<%=path %>/use/createSysconf" method="post">
    <dl class="line">
      <dt>参数标示名：</dt>
      <dd>
        <input type="text" value="${parname}" id="parname" class="input" name="parname" size="40" readonly="readonly">
        <span id="tip11"></span>
      </dd>
    </dl>
	
    <dl class="line">  <dt>参数值：</dt>
      <dd>
		<s:if test="%{1 == datatype}">
			<input type="text" value="${parvalue}" id="parvalue" class="input" name="parvalue" size="40">
		</s:if>
		<s:if test="%{2 == datatype}">
			<input type="radio" value="1" id="parvalue" class="input" <s:if test='%{parvalue=="1"}'>checked</s:if>  name="parvalue"><lable style="float:left;line-height:32px;padding:0 5px;">是</lable>
			<input type="radio" value="0" id="parvalue" class="input" <s:if test='%{parvalue=="0"}'>checked</s:if> name="parvalue"><lable style="float:left;line-height:32px;padding:0 5px;">否</lable>
		</s:if>
        
        <span id="tip12"></span>
      </dd>
    </dl>
      <dl class="line">  <dt>描述：</dt>
      <dd>
        <input type="text" value="${pardesc}" id="pardesc" class="input" name="pardesc" size="40" readonly="readonly">
        <span id="tip13"></span>
      </dd>
    </dl>
    
    
    
    <div class="page_btn">
	 <input type="hidden" name="sysId" value="${sysId}" />
      <input type="submit" value="确定" id="showwait" class="btn_b">
    </div>
	</form>
              </div>
         </div>
    	<ul class="toolbar">
    	  <li style="margin-left:10px;" ><span></span>参数列表</li>
<%--          <li  id="ID16" style="margin-left:10px;" onclick="addCheck6('1')" ><span><img src="<%=basePath%>/back/images/t01.png" /></span>添加一个级别</li> --%>
<%--          <li  id="ID26" style="display:none" onclick="addCheck6('2')"><span><img src="<%=basePath%>/back/images/t01.png" /></span>添加完毕</li> --%>
       
        </ul>
      </div>
      <table class="tablelist">
    	<thead>
    	<tr>
           <th>序号</th>
           <th>参数标示名</th>
           <th>参数值</th>
           <th>描述</th>
           <th>操作</th>
        </tr>
        </thead>
        
        <tbody>
        <c:forEach var="systemconf" items="${systemconfs}">
        <tr>
           <td>${systemconf.sysId}</td>
           <td>${systemconf.parname}</td>
           <c:if test="${systemconf.datatype==1}"><td>${systemconf.parvalue}</td></c:if>
		   <c:if test="${systemconf.datatype==2}"><td><c:if test='${systemconf.parvalue=="1"}'>是</c:if><c:if test='${systemconf.parvalue=="0"}'>否</c:if></td></c:if>
           <td>${systemconf.pardesc}</td>
           <td>
           <c:if test="${sessionScope.powerss[67]==1}">
           <a href="<%=path %>/use/editSysconf?sysId=${systemconf.sysId}" class="tablelink">编辑</a> 
           </c:if>
           <c:if test="${sessionScope.powerss[67]!=1}">--</c:if>
           </td>
        </tr>
        </c:forEach>  
        </tbody> </table>
      </div>
      
	  </div> 

      </div>
</body>

</html>

