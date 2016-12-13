<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
  <%@ taglib uri="/struts-tags" prefix="s"%>
  	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

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
<style type="text/CSS">
/*imgtable*/
.imgtable{width:100%;border:solid 1px #cbcbcb; }
.imgtable th{background:url(../images/th.gif) repeat-x; height:34px; line-height:34px; border-bottom:solid 1px #b6cad2; text-indent:21px; text-align:left;}
.imgtable td{line-height:20px; text-indent:21px; border-right: dotted 1px #c7c7c7;}
.imgtable td img{margin:10px 20px 10px 0;}
.imgtable td p{color:#919191;}
.imgtable td i{font-style:normal; color:#ea2020;}
.imgtd{text-indent:0;}
.imgtable tbody tr.odd{background:#f5f8fa;}
</style>
<script type="text/javascript" src="<%=path %>/back/js/jquery.js"></script>
<script language="javascript">
$(function(){	
	//导航切换
	$(".imglist li").click(function(){
		$(".imglist li.selected").removeClass("selected")
		$(this).addClass("selected");
	})	
})	

</script>

</head>


<body>

	<%-- <div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="javascript:void(0);" onclick="parent.frames.topFrame.overall();">首页</a></li>
    <li><a href="#">图片设置</a></li>
    </ul>
    </div> --%>
    
    <div class="rightinfo">
  
    
    
    <table class="imgtable">
    
    <thead>
    <tr>
     <th>ID</th>
    <th width="20%">缩略图</th>

    <th>广告位置</th>
    <th>更新时间</th>
    <th>操作</th>
    
   
    </tr>
    </thead>
    
    <tbody>
 <c:forEach var="avert" items="${averts}">
    <tr>
        <td>${avert.avertId}</td>
    <td class="imgtd">${avert.avertContent}</td>

    <td>${avert.avertAdress}</td>
    <td>${avert.avertTime}</td>
    <td><a href="<%=path %>/overall/findAvert?avertId=${avert.avertId}" class="tablelink">编辑</a></td>
    </tr>
  </c:forEach>
    
   
    </tbody>
    
    </table>

    </div>
    
</body>

</html>
