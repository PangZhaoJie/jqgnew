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
<script type="text/javascript" src="<%=basePath %>/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/js/ajaxfileupload.js"></script>
<!--折叠js-->
<script type="text/javascript"> 
var rechargeAmountFlag=false;
function clickBank(){
 	 var radios = document.getElementsByName("isDisplay");
	    for (var i = 0; i < radios.length; i++) {
	        if (radios[i].checked) {
 	        	rechargeAmountFlag=true;
 	        } 
	    }
}
function btn(){
	clickBank();
	if(rechargeAmountFlag==true){
	document.getElementById("addFriendlink").submit();
	document.getElementById("Submit_btn").style.background = "gray";
    document.getElementById("Submit_btn").value="正在提交...";
    document.getElementById("Submit_btn").style.disabled=false;
		}
	}
function getJSONData(currentPage,pageSize) {
	var url = "<%=path %>/overall/findFriendlink.action?currentPage="+currentPage+"&pageSize="+pageSize+"&temp=" +Math.random();
	$.getJSON(url,function(data){
		var cp = data.currentPage;
		var tp = data.totalPage;
		var $table = $ ("#table");
		$table.empty();
		str = '  	<thead><tr><th>ID</th><th>链接文字</th><th>链接地址</th><th>是否显示</th><th>排序</th><th>操作</th></tr></thead>';
		var jsonRoot = data.jsonRoot;
		for(var i = 0; i < jsonRoot.length; i++) {
			str += '  <tbody><tr><td>'+ jsonRoot[i].id+ '</td><td>'
				   + decodeURI(jsonRoot[i].name, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].link, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].display, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].place, "utf-8") +"</td><td>"
				  +'<c:if test="${sessionScope.powerss[5]==1}"><a href="<%=path%>/overall/findFriendlinkOne?friendLinkId='+jsonRoot[i].id
		     + '">编辑</a>  </c:if><c:if test="${sessionScope.powerss[5]!=1}">--</c:if>|<c:if test="${sessionScope.powerss[4]==1}"><a href="<%=path%>/overall/deleteFriendlink?friendLinkId=' +jsonRoot[i].id+"&ip=<%=ip%>" +'">删除</a></c:if><c:if test="${sessionScope.powerss[4]!=1}">--</c:if></td></tr>  </tbody>';
		}
		$table.html(str);
		if(tp !=1)
		{
			
		document.getElementById("page").style.display="";
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
 
 
 function ajaxFileUpload(mark,str)
{
var  $p=jQuery.noConflict();
	$p.ajaxFileUpload
	(
		{
			url:'<%=path %>/pic/toSavePic', //用于文件上传的服务器端请求地址
			secureuri:false,//一般设置为false
			fileElementId:str,//文件上传空间的id属性  <input type="file" id="file" name="file" />
			dataType: 'json',//返回值类型 一般设置为json
			success: function (data, status)  //服务器成功响应处理函数
			{
				if(data.message=="你已成功上传文件"){
					$p("#fileTip").html("<span style='font-size:12px;color:green'>"+data.message+"</span>");
					$p("#xsId").html("<img src='<%=basePath %>"+data.urlStr+"' name='photo' style='width: 200px;height: 150px' />");
					document.getElementById("imgUrlId").value=data.urlStr;
 				}else{
					$p("#fileTip").html("<span style='font-size:12px;color:red'>"+data.message+"</span>");

				}
				//alert(data.message);//从服务器返回的json中取出message中的数据,其中message为在struts2中定义的成员变量
			},
			error: function (data, status, e)//服务器响应失败处理函数
			{
				alert(e);
			}
		}
	);
	
	return false;

}
</script>

</head>


<body>

	 <%-- <div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="javascript:void(0);" onclick="parent.frames.topFrame.overall(); ">首页</a></li>
    <li><a href="#">友情链接</a></li>
    </ul>
    </div> --%>
    
    <div class="rightinfo">
    
    <div class="tools">
         <div id="panel">
              <div class="panel_title">添加友情链接</div>
              <div class="panel_nr fn_cle">
      <form name="addFriendlink" id="addFriendlink" action="<%=path %>/overall/addFriendlink" method = "post" >
    <textarea name="ip" style="display: none;"><%=ip %></textarea>
    <dl class="line">
      <dt>友情链接名称：</dt>
      <dd>
        <input type="text" value="" id="link_txt" class="input" name="name" size="40">
        <span>前台显示的链接文字</span>
      </dd>
    </dl>
	
    <dl class="line">
      <dt>链接地址：</dt>
      <dd>
        <input type="text" value="http://" id="link_href" class="input" name="link" size="40">
        <span>文字的网址</span>
      </dd>
    </dl>
    <dl class="line">
      <dt>显示顺序：</dt>
      <dd>
        <input type="text" value="0" id="link_order" class="input" name="place" size="40">
        <span>数字越大顺序越靠前</span>
      </dd>
    </dl>
    
    <dl class="line">
    	<dt>上传图片：</dt>
    	<dd>
          	<div><input type="file" id="file" name="file" onchange="ajaxFileUpload('0','file')" style="border:0;background:transparent;width:250px;"/>
          	 (支持jpg格式文件，图片大小为220x80)
          	<span id="fileTip"></span></div>
			<div id="xsId" style="display: ">  
			</div>
			<input id="imgUrlId" name="photo" value="1" style="display: none">
    	</dd>
    </dl>
	
	
    <dl class="line">
      <dt>是否显示：</dt>
      <dd>
	  	<p style="float:left; line-height:30px;">
          <label>  <input type="radio" name="isDisplay" value="1" id="RadioGroup1_0" checked="checked"/>是</label>
           <label> <input type="radio" name="isDisplay" value="0" id="RadioGroup1_1" />否</label>
         </p>
      </dd>
    </dl>
		 
    <div class="page_btn">
	  <input type="hidden" disabled="disabled" value="" id="fid" name="fid">
	  
      	<input type="button" name="Submit" id="Submit_btn" value="添加" class="btn" onclick="btn()"/>
    </div>

              </div>
         </div>
         
    	<ul class="toolbar">
        <c:if test="${sessionScope.powerss[3]==1 }">
        <li  id="ID1" onclick="addCheck('1')"><span><img src="<%=path %>/back/images/t01.png" /></span>添加友情链接</li>
        <li  id="ID2" style="display:none" onclick="addCheck('2')"><span><img src="<%=path %>/back/images/t01.png" /></span>添加完毕</li>
		</c:if>

        </ul>
        
        
       
    
    </div>
    
    
    <table class="tablelist" id="table">
    	<thead>
    	<tr>
        <th>ID</th>
        <th>链接文字</th>
        <th>链接地址</th>
        <th>链接图片</th>
        <th>显示位置</th>
        <th>是否显示</th>
        <th>排序</th>
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
    </div>
    
    
    
    
    
    
    
    </div>
    
   <!-- <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>-->

</body>

</html>
