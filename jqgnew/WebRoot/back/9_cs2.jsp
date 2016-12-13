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
<title>信用级别管理</title>
<link href="<%=basePath%>/back/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>/back/css/mem.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>/back/css/tab1.css" rel="stylesheet" type="text/css" />
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
   function openLoad(open){
	   if(open=='1'){
		   addCheck('1'); 
	   }
   }
   function check(form){
	   var $m=jQuery.noConflict();
	   var creditLevelName=$m("#creditLevelName").val();
	   var creditLevelStart=$m("#creditLevelStart").val();
	   var creditLevelEnd=$m("#creditLevelEnd").val();
	   var creditLevelPicture=$m("#creditLevelPicture").val();
	   if(creditLevelName==""){
		   $m("#tip1").html("<span style='font-size:12px;color:red;float:left;'>级别名不能为空</span>");
		   return false;
	   }else{
		   $m("#tip1").html("");
	   }
	   if(creditLevelStart==""){
		   $m("#tip2").html("<span style='font-size:12px;color:red;float:left;'>开始积分不能为空</span>");
		   return false;
	   }else{
		   $m("#tip2").html("");
	   }
	   if(creditLevelEnd==""){
		   $m("#tip3").html("<span style='font-size:12px;color:red;float:left;'>结束积分不能为空</span>");
		   return false;
	   }else{
		   $m("#tip3").html("");
	   }
	   if(creditLevelPicture==""){
		   $m("#tip4").html("<span style='font-size:12px;color:red;float:left;'>级别图标名不能为空</span>");
		   return false;
	   }else{
		   $m("#tip4").html("");
	   }
	   document.getElementById("showwait").style.background="gray";
	   document.getElementById("showwait").value="正在提交....";
	   document.getElementById("showwait").style.disabled=false;
	   alert("添加成功");
   }
</script>

</head>


<body onload="openLoad(${open});">

	<%-- <div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="javascript:void(0);" onclick="parent.frames.topFrame.overall();">首页</a></li>
    <li><a href="javascript:void(0);" onclick="parent.frames.topFrame.expand();">扩展管理</a></li>
    <li><a href="#">信用级别管理</a></li>
    </ul>
    </div> --%>
    
    <div class="rightinfo">
    
         <div class="formtitle"><span>信用级别管理</span></div>
         <div class="tools">
       <div id="panel">
              <div class="panel_title">添加一个级别</div>
              <div class="panel_nr fn_cle">
                 <form enctype="multipart/form-data" onsubmit="return check(this);" action="<%=path %>/level/createClevel" method="post">
    <dl class="line">
      <dt>级别名：</dt>
      <dd>
        <input type="text" name="creditLevelName" id="creditLevelName" value="${creditLevelName}"  class="input"  size="40"/>
      	<span id="tip1"></span>
      </dd>
    </dl>
	
    <dl class="line">  <dt>级别开始积分：</dt>
      <dd>
        <input type="text" value="${creditLevelStart}" name="creditLevelStart" id="creditLevelStart" class="input"  size="40"/>
      	<span id="tip2"></span>
      </dd>
    </dl>
    
    <dl class="line">  <dt>级别结束积分：</dt>
      <dd>
        <input type="text" name="creditLevelEnd" value="${creditLevelEnd}" id="creditLevelEnd" class="input" size="40"/>
        <span id="tip3"></span>
      </dd>
    </dl>
     <dl class="line">  <dt>级别图标名称：</dt>
      <dd>
        <input type="text" value="${creditLevelPicture}" id="creditLevelPicture" class="input" name="creditLevelPicture" size="40"/>格式为“/images/credit/+图标名”，且图标名必须存在
        <span id="tip4"></span>
      </dd>
    </dl>
    
    <div class="page_btn">
	  <input type="hidden" name="creditLevelId" value="${creditLevelId}"/>
      <input type="submit" value="添加" onclick="" id="showwait" class="btn_b"/>
    </div>
	</form>
              </div>
         </div>
         <c:if test="${sessionScope.powerss[69]==1}">
    	<ul class="toolbar">
         <li  id="ID1" onclick="addCheck('1')"><span><img src="<%=basePath%>/back/images/t01.png" /></span>添加一个级别</li>
         <li  id="ID2" style="display:none" onclick="addCheck('2')"><span><img src="<%=basePath%>/back/images/t01.png" /></span>添加完毕</li>
         <span style="float:left; padding:8px 0px 0px 5px;">如需新增级别图标，请先将图片通过FTP上传到您服务器项目下的UF\images\credit目录下 </span>
        </ul>
        </c:if>
      </div> 
         
      <table class="tablelist">
    	<thead>
    	<tr>
           <th width="5%">序号</th>
           <th width="15%">级别名称</th>
           <th width="19%">级别开始积分</th>
           <th width="19%">级别结束积分</th>
           <th width="19%">级别图标名称(含路径)</th>
           <th width="9%">图标显示</th>
           <th width="13%">操作</th>
        </tr>
        </thead>
        
        <tbody>
        <c:forEach var="creditlevel" items="${creditlevels}">
        <tr>
             <td>${creditlevel.creditLevelId}</td>
             <td>${creditlevel.creditLevelName}</td>
             <td>${creditlevel.creditLevelStart}</td>
             <td>${creditlevel.creditLevelEnd}</td>
             <td>${creditlevel.creditLevelPicture}</td>
             <td><img src="<%=basePath%>${creditlevel.creditLevelPicture}" /></td>
           <td>
           <c:if test="${sessionScope.powerss[70]==1}">
           <a href="<%=path %>/level/editClevel?creditLevelId=${creditlevel.creditLevelId}" class="tablelink">编辑</a>     
           <a href="<%=path %>/level/delClevel?creditLevelId=${creditlevel.creditLevelId}" class="tablelink"> 删除</a> 
           </c:if>
           <c:if test="${sessionScope.powerss[70]!=1}">--</c:if>
           </td>
        </tr>
        </c:forEach>    
        </tbody> 
        
      </table>  
      
    </div>
  
</body>

</html>


