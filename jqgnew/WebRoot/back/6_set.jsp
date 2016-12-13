<%@page import="com.jqg.service.impl.FrontmenuServiceImpl"%>
<%@page import="com.jqg.service.FrontmenuService"%>
<%@page import="com.jqg.pojo.Frontmenu"%>
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
String id = request.getParameter("frontMenuId");
FrontmenuService frontmenuService = new FrontmenuServiceImpl();
Frontmenu frontmenu = frontmenuService.findFrontmenuById(Integer.valueOf(id));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/tab.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/Tabs.js"></script>
<script type="text/javascript" >

function btn(){
	  
	  var message = document.getElementById("name");
		if(message.value==""||message.value==null)
			{
			   alert("菜单名称不能为空");
			   return;
			}
	document.getElementById("updateMenu").submit();
	document.getElementById("submit_tj").style.background = "gray";
    document.getElementById("submit_tj").value="正在提交...";
    document.getElementById("submit_tj").style.disabled=false;
	alert("菜单修改成功");
	}
</script>
<style>
#list_1 li .select1 { color: #333333;padding-left: 5px;  height: 34px; line-height: 34px; margin-top:5px; float:left;border-color: #a7b5bc #ced9df #ced9df #a7b5bc;  border-style: solid;  border-width: 1px;}
</style>
</head>

<body>

	<%-- <div class="place">
    <span>位置：</span>
    <ul class="placeul">
 <li><a href="javascript:void(0);" onclick="parent.frames.topFrame.overall();">首页</a></li>

    <li><a href="6_list.jsp" target="rightFrame">菜单管理</a></li>
    <li><a href="#">编辑</a></li>
    </ul>
    </div> --%>
    
    <div class="formbody">
    
    
    <div id="usual1" class="usual"> 
    
     <div id="hotnews_caption">
            <ul id="h_menu">                  
                 <li class="current"onclick="secBoard('hotnews_caption','list',1);">基本设置</li>
                 
            </ul>
         </div> 
    
  	<div id="hotnews_content">
        <div class="current" id="list_1">
          <form name="updateMenu" id="updateMenu" action="<%=path %>/overall/updateMenu" method = "post" >
           <ul class="forminfo">
          
            <li><label id="info_title1">菜单名称：</label><input id="name"name="menuname" type="text" class="dfinput1" value="<%=frontmenu.getName() %>" size="45"/><span>*</span>
            <input style="display: none;" name="ip" type="text" value="<%=ip %>"/>
            <input style="display: none;" name="frontMenuId" type="text" value="<%=id %>"/></li>
            
            <li  
              <%if(frontmenu.getFatherId()==0){ %>
                  style="display: none"
                  <% }%>
            ><label id="info_title1">是否隐藏：</label>  
               
                  <p style="float:left;">
                  <label>  <input type="radio" name="isDisplay" value="1" id="0" 
                  <%if(frontmenu.getIsDisplay()==1){ %>
                  checked="checked"
                  <% }%>
                  />  是</label>
                  <label> <input type="radio" name="isDisplay" value="0" id="1" 
                  <%if(frontmenu.getIsDisplay()==0){ %>
                  checked="checked"
                  <% }%>/> 否</label>
                </p>
              
              <span> 此隐藏显示只用于网页尾部子菜单</span>
            </li>
            
            <li><label id="info_title1">&nbsp;</label><input type="button" name="Submit" value="修改" class="btn" onclick="btn()" id="submit_tj"/></li>
          </ul>
        </form>
       </div> 
    </div> 
 

    
    </div>

    </div>
</body>

</html>
