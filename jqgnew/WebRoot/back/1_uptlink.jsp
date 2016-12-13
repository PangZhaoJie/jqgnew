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
	document.getElementById("updateFriendlink").submit();
		}
	document.getElementById("Submit_btn").style.background = "gray";
    document.getElementById("Submit_btn").value="正在提交...";
    document.getElementById("Submit_btn").style.disabled=false;
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
<li><a href="javascript:void(0);" onclick="parent.frames.topFrame.overall();">首页</a></li>
    <li><a href="#">友情链接</a></li>
    </ul>
    </div> --%>
    


    <div class="panel_title" style="margin: 5px">修改友情链接</div>
    <div class="panel_nr fn_cle">
  <form name="updateFriendlink" id="updateFriendlink" action="<%=path %>/overall/updateFriendlink" method = "post" >
    <textarea name="ip" style="display: none;"><%=ip %></textarea>
        <textarea name="friendLinkId" style="display: none;">${friendlinkone.friendLinkId}</textarea>
    <dl class="line">
      <dt>友情链接名称：</dt>
      <dd>
        <input type="text" value="${friendlinkone.name}" id="link_txt" class="input" name="name" size="40">
        <span>前台显示的链接文字</span>
      </dd>
    </dl>
	
    <dl class="line">
      <dt>链接地址：</dt>
      <dd>
        <input type="text" value="${friendlinkone.link}" id="link_href" class="input" name="link" size="40">
        <span>文字的网址</span>
      </dd>
    </dl>
    <dl class="line">
      <dt>显示顺序：</dt>
      <dd>
        <input type="text" value="${friendlinkone.place}" id="link_order" class="input" name="place" size="40">
        <span>数字越大顺序越靠前</span>
      </dd>
    </dl>
    
    <dl class="line">
    	<dt>上传图片：</dt>
    	<dd>
          	<div><input type="file" id="file" name="file" onchange="ajaxFileUpload('0','file')" style="border:0;background:transparent;width:250px;"/>
          	 (支持jpg格式文件，图片大小为220x80)
          	<span id="fileTip"></span></div>
			<div id="xsId" 
			<c:if test="friendlinkone.photo==null">
				style="display: none"
			</c:if>
			> 
			<img id="npic" alt="" src="<%=path %>${friendlinkone.photo} "height="135px" style="margin-left:130px"/> 
			</div>
			<input type="text" id="imgUrlId" name="photo" value="${friendlinkone.photo}" style="display: none">
    	</dd>
    </dl>
	
	
    <dl class="line">
      <dt>是否显示：</dt>
      <dd>
	  	<p style="float:left; line-height:30px;">
          <label>  <input type="radio" name="isDisplay" value="1" id="RadioGroup1_0" 
          	<s:if test="#session.friendlinkone.isDisplay==1">checked="checked" </s:if> />是</label>
           <label> <input type="radio" name="isDisplay" value="0" id="RadioGroup1_1"
           <s:if test="#session.friendlinkone.isDisplay==0">checked="checked" </s:if> />否</label>
         </p>
      </dd>
    </dl>
		 
    <div class="page_btn">
	  <input type="hidden" disabled="disabled" value="" id="fid" name="fid">
      <input type="button" name="Submit" id="Submit_btn" value="修改" class="btn" onclick="btn()"/>
    </div>
	</form>

    </div>


</body>

</html>
