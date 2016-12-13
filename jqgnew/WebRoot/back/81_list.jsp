<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath(); 
String basePath = "";if(request.getServerPort()==80){basePath = request.getScheme()+"://"+request.getServerName()+path+"/";}else{basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";}    
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户组权限管理</title>
<link href="<%=basePath%>back/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/user/user_repay.css" type="text/css" rel="stylesheet"/>
<link href="<%=basePath%>back/css/tabs.css" rel="stylesheet" type="text/css" />
<style>
.green{
	color:#093;
}
</style>
<script type="text/javascript" src="<%=basePath %>/js/jquery-1.8.2.min.js"></script>
<!--弹窗中的滑动js-->
<script type="text/javascript" src="../js/alert.js"></script>
<!--折叠js-->
<script type="text/javascript"> 
	function go(){
		window.location.href="<%=basePath%>/back/8_edit.jsp";
	}
	function dakai(mark){
		if(mark=="1"){
			Alert("该角色正在被管理员使用，无法删除！");
		}
	}
</script>

</head>

<body onload="dakai(${mark});">

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#" onclick="parent.frames[1].location.href='<%=basePath%>/back/1_left.jsp';  parent.frames[2].location.href='<%=basePath%>/overall/index'; ">首页</a></li>
    <li><a href="#" onclick="parent.frames[1].location.href='<%=basePath%>/back/8_left.html';  parent.frames[2].location.href='<%=basePath%>/back/8_list.jsp'; ">管理员管理</a></li>
    <li><a href="#">用户组权限管理</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    
    <div class="tools">
       <div id="panel">
              <div class="panel_title">搜索充值</div>
              <div class="panel_nr fn_cle">
       </div>
   </div>
   <c:if test="${sessionScope.powerss[60]==1}">
     <ul class="toolbar">
        <li  id="ID1" onclick="go();"><span><img src="<%=path %>/back/images/t01.png" /></span>添加用户组</li>
        <li  id="ID2" style="display:none" onclick="addCheck('2')"><span><img src="<%=path %>/back/images/t01.png" /></span>搜索完毕</li>
<!--         <li> <a href="#">将当前条件下数据导出为Excel</a></li> -->
     </ul>
    </c:if>
   </div>
    <table class="tablelist" style="width:100%;">
    	<thead>
    	<tr>
        <th  style="width: 30%;">ID</th>
        <th  style="width: 40%;">用户组名称</th>
        <th  style="width: 30%;">操作</th>
        </tr>
        </thead>
        <c:forEach var="role" items="${roles}">
        <thead>
        <tr>
        <td>${role.roleId}</td>
        <td>${role.roleName}</td>
        <td>
        <div id="role${role.roleId}">
        <c:if test="${sessionScope.powerss[62]==1}">
        <a class="green" href="<%=basePath%>/power/editPowers?roleId=${role.roleId}" class="tablelink">编辑</a>
        </c:if>
        <c:if test="${sessionScope.powerss[61]==1}">
        <a onclick="hunakuan(${role.roleId})" class="green" href="<%=basePath%>/power/delRole?roleId=${role.roleId}" class="tablelink">删除</a>
        </c:if> 
        </div>
        </td>
        </tr>
        </thead>
        </c:forEach>
    </table>
    <script type="text/javascript">
        function hunakuan(mark){
        	var fm=document.getElementById("role"+mark);
        	fm.innerHTML="<a class='green' href='<%=basePath%>/power/editPowers?roleId="+mark+"' class='tablelink'>编辑</a><a onclick='hunakuan2()' class='green' href='javascript:void(0)' class='tablelink'>删除</a> ";
         }
         function hunakuan2(){
              alert("正在操作中，请稍后...");
         }
   </script> 
   <div id="fade" class="black_overlay"></div>              
<!--     </div> -->
    
    <script type="text/javascript">
    	$('.tablelist tbody tr:odd').addClass('odd');
	</script>

</body>

</html>
