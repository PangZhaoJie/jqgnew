<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
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
<link href="<%=path %>/back/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path %>/back/js/jquery.js"></script>
<!--折叠js-->
<script type="text/javascript">
function getJSONDatas(currentPage,pageSize) {
    var a = $("#demo2").val();
    if(a=="-请选择时间-"){a="";}else{a= a}
    var b = $("#demo1").val();
    if(b=="-请选择时间-"){b="";}else{b= b}
	var url = "<%=path %>/overall/searchOerationlogs.action?currentPage="+currentPage+"&pageSize="+pageSize+"&name="+encodeURI(encodeURI($("#link_txt").val()))
	+"&ip="+$("#link_href").val()+"&starttime="+b+"&endtime="+a+"&temp=" +Math.random();
	document.getElementById("page").style.display="none";
	$.getJSON(url,function(data){
		var cp = data.currentPage;
		var tp = data.totalPage;
		var $table = $ ("#table");
		$table.empty();
		str = '<thead><tr><th>ID</th><th>操作者</th><th>操作类型</th><th>操作时间</th><th>操作者IP</th><th>备注信息</th></tr></thead>';
		var jsonRoot = data.jsonRoot;
		for(var i = 0; i < jsonRoot.length; i++) {
			str += '  <tbody><tr><td>'+ jsonRoot[i].id+ '</td><td>'
				   + decodeURI(jsonRoot[i].name, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].category, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].time, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].ip, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].remaks, "utf-8") +"</td></tr></tbody>";
		}
		$table.html(str);
	
			
		document.getElementById("pages").style.display="";
	     $ ("#currentPages").text("第"+cp+"页");
         $ ("#totalPages").text("共"+tp+"页");
         $ ("#tpagesnone").val(tp);
	     $ ("#firstPages").attr("href","javascript:getJSONDatas(1,10)");
	     $ ("#prevPages").attr("href","javascript:getJSONDatas("+(parseInt(cp)-1)+",10)");
	     $ ("#nextPages").attr("href","javascript:getJSONDatas("+(parseInt(cp)+1)+",10)");
	     $ ("#lastPages").attr("href","javascript:getJSONDatas("+(parseInt(tp))+",10)");

	});
}
function getJSONData(currentPage,pageSize) {

	var url = "<%=path %>/overall/findOerationlog.action?currentPage="+currentPage+"&pageSize="+pageSize+"&temp=" +Math.random();
	$.getJSON(url,function(data){
		
		var cp = data.currentPage;
		var tp = data.totalPage;
		var $table = $ ("#table");
		$table.empty();
		str = '<thead><tr><th>ID</th><th>操作者</th><th>操作类型</th><th>操作时间</th><th>操作者IP</th><th>备注信息</th></tr></thead>';
		var jsonRoot = data.jsonRoot;
		for(var i = 0; i < jsonRoot.length; i++) {
			str += '  <tbody><tr><td>'+ jsonRoot[i].id+ '</td><td>'
				   + decodeURI(jsonRoot[i].name, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].category, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].time, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].ip, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].remaks, "utf-8") +"</td></tr></tbody>";
		}
		$table.html(str);
		if(tp !=1)
		{
			
		document.getElementById("page").style.display="";
	     $ ("#tpagenone").val(tp);
	     $ ("#currentPage").text("第"+cp+"页");
         $ ("#totalPage").text("共"+tp+"页");
	     $ ("#firstPage").attr("href","javascript:getJSONData(1,10)");
	     $ ("#prevPage").attr("href","javascript:getJSONData("+(parseInt(cp)-1)+",10)");
	     $ ("#nextPage").attr("href","javascript:getJSONData("+(parseInt(cp)+1)+",10)");
	     $ ("#lastPage").attr("href","javascript:getJSONData("+(parseInt(tp))+",10)");

		}
	else{
		document.getElementById("page").style.display="none";
	}
	});
}

$(function() {  
	getJSONData(1,10);  
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
    	 getJSONData(size,10);  
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
    	getJSONDatas(size,10);  
    }
    
}
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

</script>
<!--日历插件 js-->
<script type="text/javascript" src="laydate/laydate.js"></script>


</head><body>
<input type="text" id="tpagenone" style="display: none"/>
<input type="text" id="tpagesnone" style="display: none"/>
	<%-- <div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="javascript:void(0);" onclick="parent.frames.topFrame.overall();">首页</a></li>
     <li><cite></cite><a href="1_log.jsp" target="rightFrame">后台操作日志</a><i></i></li>
    </ul>
    </div> --%>
    
    <div class="rightinfo">
    
    <div class="tools">
       <div id="panel">
              <div class="panel_title">搜索操作日志</div>
              <div class="panel_nr fn_cle">
 
    <dl class="line">
      <dt>管理员：</dt>
      <dd>
        <input type="text" value="" id="link_txt" class="input" name="name" size="40">
        <span>不填则不限制</span>
      </dd>
    </dl>
	
    <dl class="line">
      <dt>ip：</dt>
      <dd>
        <input type="text" value="" id="link_href" class="input" name="ip" size="40">
        <span>不填则不限制</span>
      </dd>
    </dl>
    <dl class="line">
      <dt>操作时间(开始)：</dt>
      <dd>
	  	<input class="laydate-icon" id="demo1" value="-请选择时间-" name="starttime">
        <span>只选开始时间则查询从开始时间往后所有</span>
      </dd>
    </dl>
    <dl class="line">
      <dt>操作时间(结束)：</dt>
      <dd>
        <input  class="laydate-icon" id="demo2" value="-请选择时间-" name="endtime">
        <span>只选结束时间则查询从结束时间往前所有</span>
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
	  <input type="hidden" disabled="disabled" value="" id="fid" name="fid">
	  
 		<input type="button" name="Submit" value="搜索" class="btn" onclick="getJSONDatas(1,10);addCheck('2')"/>
    </div>

              </div>
         </div>
    	<ul class="toolbar">
    	<c:if test="${sessionScope.powerss[7]==1 }">
         <li  id="ID1" onclick="addCheck('1')"><span><img src="images/t06.png" /></span>搜索操作日志</li>
         <li  id="ID2" style="display:none" onclick="addCheck('2')"><span><img src="images/t06.png" /></span>搜索完毕</li>
       	</c:if>
        </ul>
   </div>
    
    
    <table class="tablelist" id="table">
    	<thead>
    	<tr>
        <th>ID</th>
        <th>操作者</th>
        <th>操作类型</th>
        <th>操作时间</th>
        <th>操作者IP</th>
        <th>备注信息</th>
        
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
  	<em class="skip">转到<input name="page" type="text" id="size" size="5" />页</em><a href="#" id="tiaozhuan" onclick="tiaozhuan()">跳转</a>
    </div>
   <div class="page" id="pages" style="display: none">
    <em id="currentPages">第页</em>&nbsp; &nbsp; 
  	<em id="totalPages">共页</em>&nbsp; &nbsp; 
  	<em ><a id="firstPages" href="">首页</a></em>&nbsp; &nbsp; 
  	<em ><a id="prevPages" href="">上一页</a></em>&nbsp; &nbsp; 
  	<em ><a id="nextPages" href="">下一页</a></em>&nbsp; &nbsp; 
  	<em ><a id="lastPages" href="">最后一页</a></em>&nbsp; &nbsp; 
  	<em class="skip">转到<input name="page" type="text" id="sizes" size="5" />页</em><a href="#" id="tiaozhuans" onclick="tiaozhuans()">跳转</a>
    </div>
    </div>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>

</body>

</html>
