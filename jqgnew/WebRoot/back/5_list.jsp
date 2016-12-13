<%@page import="com.jqg.service.impl.IPServiceImpl"%>
<%@page import="com.jqg.service.IPService"%>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
  <%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();if(path!=null && !path.equals("")){	if(path.contains("/")){		if(path.equals("/")){path = "";	}}else{	path += "/";}} 
String basePath = "";if(request.getServerPort()==80){basePath = request.getScheme()+"://"+request.getServerName()+path+"/";}else{basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";}    
IPService ipService = new IPServiceImpl();
String ip = ipService.getIpAddr(request);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="<%=path %>/back/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path %>/back/js/jquery.js"></script>
<script type="text/javascript" src="<%=path %>/back/js/top.js"></script>
<script type="text/javascript">
function getJSONDatas(menuId,currentPage,pageSize,num) {
    
	var url = "<%=path %>/overall/findnewsBymenu.action?currentPage="+currentPage+"&pageSize="+pageSize+"&frontMenuId="+menuId+"&random=" + num;
	document.getElementById("page").style.display="none";
	$.getJSON(url,function(data){
		var cp = data.currentPage;
		var tp = data.totalPage;
		var $table = $ ("#table");
		$table.empty();
		str = '<thead><tr><th>ID</th><th>文章标题</th><th>所属栏目</th> <th>发布人</th><th>添加时间</th><th>操作</th></tr></thead>';
		var jsonRoot = data.jsonRoot;
		if(tp!=1 && tp!=0){
			document.getElementById("pages").style.display="";
		}
		if(tp==0){
			str +="";
		}else{
		for(var i = 0; i < jsonRoot.length; i++) {
			str += '  <tbody><tr><td>'+ jsonRoot[i].id+ '</td><td>'
				   + decodeURI(jsonRoot[i].title, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].name, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].publish, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].time, "utf-8") +"</td><td>"
				   +'<c:if test="${sessionScope.powerss[44]==1}"><a href="<%=path %>/overall/findnew?newsId='+jsonRoot[i].id
				     + '">编辑</a></c:if><c:if test="${sessionScope.powerss[44]!=1}">--</c:if>|<c:if test="${sessionScope.powerss[43]==1}"><a href="<%=path %>/overall/deletenew?newsId=' +jsonRoot[i].id+"&ip=<%=ip%>" +'">删除</a></c:if><c:if test="${sessionScope.powerss[43]!=1}">--</c:if></td></tr>  </tbody>';
		 }
		}
		$table.html(str);
		document.getElementById("page").style.display="none";
		document.getElementById("pages").style.display="none";
	     $ ("#currentPages").text("第"+cp+"页");
         $ ("#totalPages").text("共"+tp+"页");
         $ ("#tpagesnone").val(tp);
         $ ("#menu").val(menuId);
	     $ ("#firstPages").attr("href","javascript:getJSONDatas("+menuId+","+ "1,10," +Math.random()+ ")");
	     $ ("#prevPages").attr("href","javascript:getJSONDatas("+menuId+","+(parseInt(cp)-1)+",10," +Math.random()+ ")");
	     $ ("#nextPages").attr("href","javascript:getJSONDatas("+menuId+","+(parseInt(cp)+1)+",10," +Math.random()+ ")");
	     $ ("#lastPages").attr("href","javascript:getJSONDatas("+menuId+","+(parseInt(tp))+",10," +Math.random()+ ")");
	});
}
function getJSONData(currentPage,pageSize,num) {

	var url = "<%=path %>/overall/findnews.action?currentPage="+currentPage+"&pageSize="+pageSize+"&userNameQuery="+$("#demo3").val()+"&startTimeQuery="+$("#demo1").val()+"&endTimeQuery="+$("#demo2").val()+"&random=" + num;
	$.getJSON(url,function(data){

		var cp = data.currentPage;
		var tp = data.totalPage;
		var $table = $ ("#table");
		$table.empty();
		str = '<thead><tr><th>ID</th><th>文章标题</th><th>所属栏目</th> <th>发布人</th><th>添加时间</th><th>操作</th></tr></thead>';
		var jsonRoot = data.jsonRoot;
	
		for(var i = 0; i < jsonRoot.length; i++) {
			str += '  <tbody><tr><td>'+ jsonRoot[i].id+ '</td><td>'
				   + decodeURI(jsonRoot[i].title, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].name, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].publish, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].time, "utf-8") +"</td><td>"
				   +'<c:if test="${sessionScope.powerss[44]==1}"><a href="<%=path %>/overall/findnew?newsId='+jsonRoot[i].id
				     + '">编辑</a></c:if><c:if test="${sessionScope.powerss[44]!=1}">--</c:if>|<c:if test="${sessionScope.powerss[43]==1}"><a href="<%=path %>/overall/deletenew?newsId=' +jsonRoot[i].id+"&ip=<%=ip%>" +'">删除</a></c:if><c:if test="${sessionScope.powerss[43]!=1}">--</c:if></td></tr>  </tbody>';
		}
		$table.html(str);
		document.getElementById("pages").style.display="none";
		if(tp !=1)
		{
		
			document.getElementById("page").style.display="";
		     $ ("#tpagenone").val(tp);
		     $ ("#currentPage").text("第"+cp+"页");
	         $ ("#totalPage").text("共"+tp+"页");
		     $ ("#firstPage").attr("href","javascript:getJSONData(1,10," +Math.random()+ ")");
		     $ ("#prevPage").attr("href","javascript:getJSONData("+(parseInt(cp)-1)+",10," +Math.random()+ ")");
		     $ ("#nextPage").attr("href","javascript:getJSONData("+(parseInt(cp)+1)+",10," +Math.random()+ ")");
		     $ ("#lastPage").attr("href","javascript:getJSONData("+(parseInt(tp))+",10," +Math.random()+ ")");
			
		
		}
	
	});
}

function getJSONData1(currentPage,pageSize,num) {
	var url = "<%=path %>/overall/findnews.action?currentPage="+currentPage+"&pageSize="+pageSize+"&userNameQuery="+$("#demo3").val()+"&startTimeQuery="+$("#demo1").val()+"&endTimeQuery="+$("#demo2").val()+"&random=" + num;
	$.getJSON(url,function(data){

		var cp = data.currentPage;
		var tp = data.totalPage;
		var $table = $ ("#table");
		$table.empty();
		str = '<thead><tr><th>ID</th><th>文章标题</th><th>所属栏目</th> <th>发布人</th><th>添加时间</th><th>操作</th></tr></thead>';
		var jsonRoot = data.jsonRoot;
	
		for(var i = 0; i < jsonRoot.length; i++) {
			str += '  <tbody><tr><td>'+ jsonRoot[i].id+ '</td><td>'
				   + decodeURI(jsonRoot[i].title, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].name, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].publish, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].time, "utf-8") +"</td><td>"
				   +'<c:if test="${sessionScope.powerss[44]==1}"><a href="<%=path %>/overall/findnew?newsId='+jsonRoot[i].id
				     + '">编辑</a></c:if><c:if test="${sessionScope.powerss[44]!=1}">--</c:if>|<c:if test="${sessionScope.powerss[43]==1}"><a href="<%=path %>/overall/deletenew?newsId=' +jsonRoot[i].id+"&ip=<%=ip%>" +'">删除</a></c:if><c:if test="${sessionScope.powerss[43]!=1}">--</c:if></td></tr>  </tbody>';
		}
		$table.html(str);
		document.getElementById("pages").style.display="none";
		document.getElementById("page").style.display="none";
		if(tp !=1)
		{
		
			document.getElementById("page1").style.display="";
		     $ ("#tpagenone").val(tp);
		     $ ("#currentPage1").text("第"+cp+"页");
	         $ ("#totalPage1").text("共"+tp+"页");
		     $ ("#firstPage1").attr("href","javascript:getJSONData1(1,10," +Math.random()+ ")");
		     $ ("#prevPage1").attr("href","javascript:getJSONData1("+(parseInt(cp)-1)+",10," +Math.random()+ ")");
		     $ ("#nextPage1").attr("href","javascript:getJSONData1("+(parseInt(cp)+1)+",10," +Math.random()+ ")");
		     $ ("#lastPage1").attr("href","javascript:getJSONData1("+(parseInt(tp))+",10," +Math.random()+ ")");
			
		
		}
	
	});
}

$(function() {  
	getJSONData(1,10,Math.random());  
});
function tiaozhuan() {
    var size = $("#size").val();
    var totalpage = $("#tpagenone").val();
    if(size==null || size==""){
  		alert("请输入要跳转的页数！");
  		return false;
  	}
    if(parseInt(size)<1||(parseInt(size))>(parseInt(totalpage)))
    {
    	alert("请输入正确的页数")
    	return false;
    }
    else{
    	 getJSONData(size,10,Math.random());  
    }
   
}
function tiaozhuans() {
    var size = $("#sizes").val();
    var totalpage = $("#tpagesnone").val();
  	if(size==null || size==""){
  		alert("请输入要跳转的页数！");
  		return false;
  	}
    if(parseInt(size)<1||parseInt(size)>parseInt(totalpage))
    {
    	alert("请输入正确的页数")
    	return false;
    }else{
    	getJSONDatas(size,10,Math.random());  
    }
    
}


	function addCheck(num) {
		if (num == 1) {
			$("#panel").show();
			$("#ID1").hide();
			$("#ID2").show();
		} else {
			$("#panel").hide();
			$("#ID1").show();
			$("#ID2").hide();
		}
	}
</script>
<!--日历插件 js-->
<script type="text/javascript" src="<%=basePath%>/back/laydate/laydate.js"></script>
</head>
<body>
<input type="text" id="menu" style="display: none"/>
<input type="text" id="tpagenone" style="display: none"/>
<input type="text" id="tpagesnone" style="display: none"/>
<input type="hidden" id="path" value="<%=path %>" />
    <div class="rightinfo">
    <div class="tools">

			<div id="panel">
				<div class="panel_title">搜索/筛选文章</div>
				<div class="panel_nr fn_cle">
					<form enctype="multipart/form-data" onsubmit="" action="" method="post">
						<dl class="line">
							<dt>文章标题：</dt>
							<dd>
								<input type="text" id="demo3" class="input" name="userNameQuery"
									value="${userNameQuery }" size="40"> <span>不填则不限制</span>
							</dd>
						</dl>
						<dl class="line">
							<dt>添加时间(开始)：</dt>
							<dd>
								<input class="laydate-icon" id="demo1" name="startTimeQuery"
									value="${startTimeQuery}"> <a style="color: #7d7d7d">只选开始时间则查询从开始时间往后所有</a>
							</dd>
						</dl>
						<dl class="line">
							<dt>添加时间(结束)：</dt>
							<dd>
								<input class="laydate-icon" id="demo2" name="endTimeQuery"
									value="${endTimeQuery}"> <a style="color: #7d7d7d">只选结束时间则查询从结束时间往前所有</a>
							</dd>
						</dl>
						<script>
							!function(){
							laydate.skin('default');//切换皮肤，请查看skins下面皮肤库
							laydate({elem: '#demo1'});
							laydate({elem: '#demo2'});//绑定元素
							}();
						</script> 
						<div class="page_btn">
							<input type="hidden" disabled="disabled" value="" id="fid"name="fid"> <input type="button" value="搜索" onclick="getJSONData1(1,10,Math.random());addCheck('2')"id="showwait" class="btn_b">
						</div>
					</form>
				</div>
			</div>




			<ul class="toolbar">
    	<c:if test="${sessionScope.powerss[42]==1}">
    	  <li  id="ID1" onclick="addCheck('1')"><span><img src="<%=basePath %>/back/images/t06.png" /></span>搜索筛选文章</li>
          <li  id="ID2" style="display:none" onclick="addCheck('2')"><span><img src="<%=basePath %>/back/images/t06.png" /></span>搜索完毕</li>
          <li><span><img src="<%=path %>/back/images/t01.png" /></span><a href="<%=path %>/overall/findmenus">添加文章</a></li>
        </c:if>
          <li><a href="#"onclick="getJSONDatas(3,1,10,Math.random()); ">网站公告</a></li>
          <li><a href="#"onclick="getJSONDatas(2,1,10,Math.random()); ">行业资讯</a></li>
          <li><a href="#"onclick="getJSONDatas(36,1,10,Math.random()); ">企业新闻 </a></li>
          <li><a href="#"onclick="getJSONDatas(42,1,10,Math.random()); ">政策法规</a></li>
          <li><a href="#"onclick="getJSONDatas(5,1,10,Math.random()); ">借款合同</a></li>
          <li><a href="#"onclick="getJSONDatas(8,1,10,Math.random()); ">新标预告</a></li>
          <li><a href="#"onclick="getJSONDatas(46,1,10,Math.random()); ">幻灯片</a></li>
          <li><a href="#"onclick="getJSONDatas(47,1,10,Math.random()); ">风控管理</a></li>
          <li><a href="#"onclick="getJSONDatas(48,1,10,Math.random()); ">担保企业</a></li>
        </ul>
    </div>
    <table class="tablelist" id="table">
    	<thead>
    	<tr>
            <th>ID</th>
            <th>文章标题</th>
            <th>所属栏目</th>
            <th>发布人</th>
            <th>添加时间</th>
            <th>操作</th>
        </tr>
        </thead>
    </table>
      <div class="page" id="page" style="display: none">
    <em id="currentPage">第页</em>&nbsp; &nbsp; 
  	<em id="totalPage">共页</em>&nbsp; &nbsp; 
  	<em ><a id="firstPage" href="">首页</a></em>&nbsp; &nbsp; 
  	<em ><a id="prevPage" href="">上一页</a></em>&nbsp; &nbsp; 
  	<em ><a id="nextPage" href="">下一页</a></em>&nbsp; &nbsp; 
  	<em ><a id="lastPage" href="">最后一页</a></em>&nbsp; &nbsp; 
  	<em class="skip">转到<input name="page" type="text" id="size" size="5" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>页</em><a href="#" id="tiaozhuan" onclick="tiaozhuan()">跳转</a>
    </div>
   <div class="page" id="pages" style="display: none">
    <em id="currentPages">第页</em>&nbsp; &nbsp; 
  	<em id="totalPages">共页</em>&nbsp; &nbsp; 
  	<em ><a id="firstPages" href="">首页</a></em>&nbsp; &nbsp; 
  	<em ><a id="prevPages" href="">上一页</a></em>&nbsp; &nbsp; 
  	<em ><a id="nextPages" href="">下一页</a></em>&nbsp; &nbsp; 
  	<em ><a id="lastPages" href="">最后一页</a></em>&nbsp; &nbsp; 
  	<em class="skip">转到<input name="page" type="text" id="sizes" size="5" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>页</em><a href="#" id="tiaozhuans" onclick="tiaozhuans()">跳转</a>
    </div>
    <div class="page" id="page1" style="display: none">
    <em id="currentPage1">第页</em>&nbsp; &nbsp; 
  	<em id="totalPage1">共页</em>&nbsp; &nbsp; 
  	<em ><a id="firstPage1" href="">首页</a></em>&nbsp; &nbsp; 
  	<em ><a id="prevPage1" href="">上一页</a></em>&nbsp; &nbsp; 
  	<em ><a id="nextPage1" href="">下一页</a></em>&nbsp; &nbsp; 
  	<em ><a id="lastPage1" href="">最后一页</a></em>&nbsp; &nbsp; 
  	<em class="skip">转到<input name="page" type="text" id="sizes" size="5" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>页</em><a href="#" id="tiaozhuans" onclick="tiaozhuans()">跳转</a>
    </div>
    </div>
</body>
</html>
