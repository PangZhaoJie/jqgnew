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
<style>
ul { padding:0; margin:0;}
#main1,#main2 { width:100px; height:28px; line-height:28px; padding-left:5px; background:#ccc; cursor:pointer; border-bottom:1px solid #fff;}
#child1,#child2 { width:105px; background:#eee;}
#child1 ul li,#child2 ul li { padding-left:5px; border-bottom:1px solid #fff; line-height:180%;}
#child1 ul li a,#child2 ul li a { color:#666;}
</style>
<script type="text/javascript" src="<%=path %>/back/js/jquery.js"></script>

</head>

<body >

	<%-- <div class="place">
    <span>位置：</span>
    <ul class="placeul">
 <li><a href="javascript:void(0);" onclick="parent.frames.topFrame.overall();">首页</a></li>

    <li><a href="#">菜单管理</a></li>

    </ul>
    </div> --%>
    
    <div class="rightinfo">
    
    
    
    
    
    <div class="list">
 
  <table cellspacing="0" cellpadding="0" width="100%" border="0" id="area_list" class="tablelist">
 
  
    <tr>

        <th width="20%" >ID</th>
        <th width="30%" >分类名称</th>
        <th width="30%" >分类nid</th>

        <th width="20%" >操作</th>
    </tr>
    <c:forEach var="frontmenu1" items="${frontmenus1}">
     <tr parentid="0" typeid="1" class="leve_1" id="list" overstyle="on">
        <td>${frontmenu1.frontMenuId}</td>
        <td><span data="son" class="typeson typeon"  onclick="document.all.list_${frontmenu1.frontMenuId}.style.display=(document.all.list_${frontmenu1.frontMenuId}.style.display =='none')?'':'none'" >&nbsp;</span>${frontmenu1.name}</td>
        <td>${frontmenu1.frontMenuCode}</td>
        <td>
        <c:if test="${sessionScope.powerss[46]==1}"> 
        <a href="<%=path %>/back/6_set.jsp?frontMenuId=${frontmenu1.frontMenuId}" class="tablelink">编辑</a>
        </c:if>
        <c:if test="${sessionScope.powerss[46]!=1}"><a>--</a>
        </c:if>
        </td>
      </tr>
               <tbody id="list_${frontmenu1.frontMenuId}" style="display: none;">
			<c:forEach var="frontmenu" items="${frontmenus}">
			  <c:if test="${frontmenu.fatherId==frontmenu1.frontMenuId }">
	             <tr parentid="1" typeid="12" class="leve_2" id="list"  >
					 <td>${frontmenu.frontMenuId}</td>
	                 <td>&nbsp;&nbsp;&nbsp;${frontmenu.name}</td>
	                 <td>${frontmenu.frontMenuCode}</td>
	                 <td>
	                 <c:if test="${sessionScope.powerss[46]==1}"> 
	                 <a href="<%=path %>/back/6_set.jsp?frontMenuId=${frontmenu.frontMenuId}" class="tablelink">编辑</a> 
	                 </c:if>
	                 <c:if test="${sessionScope.powerss[46]!=1}">
	                 <a>--</a>
	                 </c:if>
	                 </td>
				  </tr>
				  </c:if>
			</c:forEach>  
			  </tbody>
       </c:forEach> 
       </table>



  </div>
 
    
       
    
    </div>
    
  

</html>
