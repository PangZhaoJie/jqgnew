<%@page import="com.jqg.service.impl.IPServiceImpl"%>
<%@page import="com.jqg.service.IPService"%>
<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	if (path != null && !path.equals("")) {
		if (path.contains("/")) {
			if (path.equals("/")) {
				path = "";
			}
		} else {
			path += "/";
		}
	}
	String basePath = "";
	if (request.getServerPort() == 80) {
		basePath = request.getScheme() + "://"
				+ request.getServerName() + path + "/";
	} else {
		basePath = request.getScheme() + "://"
				+ request.getServerName() + ":"
				+ request.getServerPort() + path + "/";
	}
	IPService ipService = new IPServiceImpl();
	String ip = ipService.getIpAddr(request);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="<%=path%>/back/css/style.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="<%=path %>/back/js/jquery.js"></script>
<script type="text/javascript" src="<%=path %>/back/js/top.js"></script>
<script type="text/javascript">
 function toMoneyPage(num){
	  var  $MP=jQuery.noConflict();
	  var page=$MP("#currentPageId").val();
	  var pageNum=$MP("#pageNumId").val();
	  var p=$MP("#pId").val();
   	  if(num==1){
   		  	
			  $MP("#page1-1").attr("href","<%=path%>/overall/findPhotos.action?currentPage="+1); 
	  }
	  if(num==3){
		     if(Number(page)<Number(pageNum)){
			  $MP("#page1-3").attr("href","<%=path%>/overall/findPhotos.action?currentPage="+(Number(page)+1));
		     }else{
				  $MP("#page1-3").attr("href","<%=path%>/overall/findPhotos.action?currentPage="+Number(page)); 
		     }
	  }
	  if(num==2){
		  if(page>1){
			  $MP("#page1-2").attr("href","<%=path%>/overall/findPhotos.action?currentPage="+(Number(page)-1));
			  
		  }else{
			  $MP("#page1-2").attr("href","<%=path%>/overall/findPhotos.action?currentPage="+(Number(page)));
		  }
	  }
	  if(num==4){
	  	  if(p==null || p=="" || parseInt(p)< 1 || parseInt(p)> parseInt(pageNum)){
	  			alert("请输入正确的页数！");
	  			return false;
	  		}else{
			  $MP("#page1-4").attr("href","<%=path%>/overall/findPhotos.action?currentPage="+Number(p));
			  }
	  	}
	   if(num==5){
			  $MP("#page1-5").attr("href","<%=path%>/overall/findPhotos.action?currentPage="+Number(pageNum)); 
	  }
	  
  }
</script>
</head>
<body>
	<input type="text" id="menu" style="display: none" />
	<input type="text" id="tpagenone" style="display: none" />
	<input type="text" id="tpagesnone" style="display: none" />
	<input type="hidden" id="path" value="<%=path%>" />
	<input type="hidden" name="appId" value="${app.appId}"/>
	<div class="rightinfo">
		<div class="tools">
			<ul class="toolbar">
				<li><span><img src="<%=path%>/back/images/t01.png" />
				</span><a href="<%=path%>/back/photo_edit.jsp">添加图片</a></li>
			</ul>
		</div>
		<table class="tablelist" id="table">
			<thead>
				<tr>
					<th>ID</th>
					<th>图片标题</th>
					<th>图片类型</th>
					<th>发布人</th>
					<th>添加时间</th>
					<th>操作</th>
				</tr>
				<c:forEach var="app" items="${apppage}">
					<tr>
						<td>${app.appId}</td>
						<td>${app.title}</td>
						<td>${app.type}</td>
						<td>${app.userName}</td>
						<td>${app.publishTime}</td>
						<td><a href="<%=path %>/overall/findPhotoByAppId?appId=${app.appId}">编辑</a>|<a href="<%=path %>/overall/deletePhotoByApp?appId=${app.appId}">删除</a>
						</td>
					</tr>
				</c:forEach>
			</thead>
		</table>
		<div class="pagin">
			<div class="message">
				共<i class="blue">${total}</i>条记录，当前显示第&nbsp;<i class="blue">${currentPage}&nbsp;</i>页
				,共${totalPage}页
			</div>
			<ul class="paginList">
				<li class="paginItem"><a href="" style="width: 50px"
					onclick="toMoneyPage('1')" id="page1-1">首页</a>
				</li>
				<li class="paginItem"><a href="" style="width: 60px"
					onclick="toMoneyPage('2')" id="page1-2">上一页</a>
				</li>
				<li class="paginItem"><a href="" style="width: 60px"
					onclick="toMoneyPage('3')" id="page1-3">下一页</a>
				</li>
				<li class="paginItem"><span class="skip"><a href="#" style="width: 50px" onclick="toMoneyPage('4')" id="page1-4">转到</a>
				<input  name="page" type="text" id="pId" value="" size="3"
						style="width:31px;height:28px; border:1px solid #DDD; text-align:center;line-height:30px ;color:#3399d5"
						onkeyup="this.value=this.value.replace(/\D/g,'')"
						onafterpaste="this.value=this.value.replace(/\D/g,'')" />页</span></li>
				<li class="paginItem"><a href=""style="width: 60px;border-left:dashed;border:1px solid #DDD;"onclick="toMoneyPage('5')" id="page1-5">最后一页</a></li>
			</ul>
			<input name="currentPage" value="${currentPage}" id="currentPageId"style="display: none" />
			<input name="total" value="${totalPage}"id="pageNumId" style="display: none" />
		</div>
	</div>
</body>
</html>
