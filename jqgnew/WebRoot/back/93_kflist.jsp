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
<script type="text/javascript">
   function openLoad(open1){
	   if(open1=='1'){
		   addCheck('1'); 
	   }
   }
   function check(form){
	   var $m=jQuery.noConflict();
	   var customerphoneName=$m("#customerphoneName").val();
	   var customerphoneNumber=$m("#customerphoneNumber").val();
	   var display1=document.getElementsByName("display1");
	   if(customerphoneName==""){
		   $m("#tip1").html("<span style='font-size:12px;color:red;float:left;'>客服电话标题不能为空</span>");
		   return false;
	   }else{
		   $m("#tip1").html("");
	   }
	   if(customerphoneNumber==""){
		   $m("#tip2").html("<span style='font-size:12px;color:red;float:left;'>客服电话不能为空</span>");
		   return false;
	   }else{
		   $m("#tip2").html("");
	   }
	   var flag=-1;
	   for(var i=0;i<display1.length;i++){
		   if(display1[i].checked){
			  flag=i; 
		   }
	   }
	   if(flag<0){
		   $m("#tip3").html("<span style='font-size:12px;color:red;float:left; margin-top:0;'>请选择是否显示</span>"); 
		   return false;
	   }else{
		   $m("#tip3").html("");
	   }  
	   document.getElementById("showwait").style.background = "gray";
   	   document.getElementById("showwait").value="正在提交...";
       document.getElementById("showwait").style.disabled=false;
	   alert("客服电话添加成功！");
	   
   }
</script>
<style>
.tablelist td .input { color: #333333;padding: 0px 5px;  height: 28px; line-height: 28px; margin:8px 0 8px 10px; float:left;border-color: #a7b5bc #ced9df #ced9df #a7b5bc;  border-style: solid;  border-width: 1px;}
.tablelist td { }
</style>

</head>


<body onload="openLoad(${open1});">

	<%-- <div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="javascript:void(0);" onclick="parent.frames.topFrame.overall();">首页</a></li>
    <li><a href="#" onclick="parent.frames[1].location.href='<%=path %>/back/9_left.jsp';  parent.frames[2].location.href='<%=path %>/overall/findlssh'; ">扩展管理</a></li>
    <li><a href="#">在线客服管理</a></li>
    </ul>
    </div> --%>
    
    <div class="rightinfo">
    
       <div class="tools">
       <div id="panel">
              <div class="panel_title">客服电话</div>
              <div class="panel_nr fn_cle">
    <form enctype="multipart/form-data" onsubmit="return check(this);" action="<%=path %>/extension/createPhone" method="post">
    <dl class="line">
      <dt>客服电话标题：</dt>
      <dd>
        <input name="customerphoneName" type="text" value="${customerphoneName}" id="customerphoneName" class="input" name="link_txt" size="40">
         <span id="tip1"></span>
      </dd>
    </dl>
	
    <dl class="line">
      <dt>客服电话：</span>
      <dd>
        <input name="customerphoneNumber" type="text" value="${customerphoneNumber}" id="customerphoneNumber" class="input" name="link_href" size="40">
        <span id="tip2"></span>
      </dd>
    </dl>
     <dl class="line">
      <dt>是否显示：</dt>
      <dd>
          <p style="line-height:33px;">
                
                  <label  style="float:left;">  <input type="radio" <c:if test="${display1==1}">checked="checked"</c:if> name="display1" value="1" id="RadioGroup1_0" />是</label>
                  <label  style="float:left;">  <input type="radio"  <c:if test="${display1==0}">checked="checked"</c:if> name="display1" value="0" id="RadioGroup1_1" style=" margin:0 3px 0 15px;"/>否 </label>
                  <span id="tip3" style=" margin-top:0;">默认最后一个是否显示为显示的客服电话</span>
                  
               </p>
      
      </dd>
    </dl>
    
    
    <div class="page_btn">
      <input type="hidden" name="customerphoneId" value="${customerphoneId}"/>
	  <input type="hidden" disabled="disabled" value="" id="fid" name="fid">
      <input type="submit" value="添加" id="showwait" class="btn_b">
    </div>
	</form>
              </div>
         </div>
    	<ul class="toolbar">
    	<c:if test="${sessionScope.powerss[130]==1 }">
         <li  id="ID1" onclick="addCheck('1')"><span><img src="<%=basePath%>/back/images/t01.png" /></span>添加客服电话</li>
         <li  id="ID2" style="display:none" onclick="addCheck('2')"><span><img src="<%=basePath%>/back/images/t01.png" /></span>添加完毕</li>
        </c:if>
<%--          <li><span><img src="<%=basePath%>/back/images/t03.png" /></span>删除客服</li> --%>
       
        </ul>
      </div>
    
    
      <table class="tablelist">
    	<thead>
    	<tr>
        <th>ID</th>
        <th>客服电话标题</th>
        <th>客服电话号码</th>
        <th>是否显示</th>
        <th>操作</th>
        </tr>
        </thead>

        <c:forEach var="customer" items="${customerphones}">
            <tbody>
        	<tr>
            <td>${customer.customerphoneId}</td> 
        	<td>${customer.customerphoneName}</td>
        	<td>${customer.customerphoneNumber}</td>
        	<td>
        	<c:if test="${customer.display==0}">
        	不显示
        	</c:if>
        	<c:if test="${customer.display==1}">
        	显示
        	</c:if>
        	</td>
        	<td>
        	<c:if test="${sessionScope.powerss[132]==1 }">
        	<a href="<%=path %>/extension/editPhone?customerphoneId=${customer.customerphoneId}" class="tablelink">编辑</a>
        	</c:if>
        	<c:if test="${sessionScope.powerss[132]!=1 }">
        	--
        	</c:if>
        	<c:if test="${sessionScope.powerss[131]==1 }">
        	<a href="<%=path %>/extension/delPhone?customerphoneId=${customer.customerphoneId}" class="tablelink"> 删除</a>
        	</c:if>
        	<c:if test="${sessionScope.powerss[131]!=1 }">
        	--
        	</c:if>
        	</td>
        	</tr>
        	</tbody>
        </c:forEach>
         

    </table>
    
    
       
    
    </div>
    
  
</body>

</html>
