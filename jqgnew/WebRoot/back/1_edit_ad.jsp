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
<script type="text/javascript" src="<%=path %>/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="<%=path %>/ckeditor/ckeditor.js"></script>  
<script type="text/javascript" src="<%=path %>/ckfinder/ckfinder.js"></script>
<link href="<%=path %>/back/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" >

function btn(){
	var content = editor.document.getBody().getHtml().replace(/\&/g, "%26");
	var address = document.getElementById("address");
	 if(address.value==null||address.value=="")
	 {
	    alert("广告位置不能为空！");
	    return;
	 }
	 if(content==null||content=="")
	 {
	    alert("图片不能为空！");
	    return;
	 }
	document.getElementById("updateAvert").submit();
	}
</script>
</head>

<body>

	<%-- <div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="javascript:void(0);" onclick="parent.frames.topFrame.overall();">首页</a></li>
    <li><a href="<%=path %>/back/1_avert_set.jsp" target="rightFrame">图片设置</a></li>
     <li><a href="#">图片编辑</a></li>
    </ul>
    </div> --%>
    
    <div class="formbody">
    
    <div class="formtitle"><span>图片编辑</span></div>
    <form name="updateAvert" id="updateAvert" action="<%=path %>/overall/updateAvert" method = "post" >
    <ul class="forminfo">
      <li> <textarea name="avertId" style="display: none;">${avert.avertId}</textarea> <textarea name="ip" style="display: none;"><%=ip %></textarea></li>
    <li><label id="info_title1">广告位置：</label><input name="avertAdress" type="text" class="dfinput" value="${avert.avertAdress}" size="50"id="address"/> <i>*</i></li>
    <li><label id="info_title1">图片上传：</label> 
      <textarea id="textarea" name="avertContent" >${avert.avertContent}</textarea>
					    <script type="text/javascript" >
			               var editor = CKEDITOR.replace('textarea',{
				            });
			            </script>
     </li>
 
    <li><label>&nbsp;</label><input type="button" name="Submit" value="保存" class="btn" onclick="btn()"/></li>
    </ul>
    </form>
  
    </div>


</body>

</html>
