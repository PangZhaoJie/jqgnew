<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.sql.*" errorPage=""%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page import="java.util.List"%>
<%@page import="com.jqg.pojo.Manager"%>
<%@page import="com.jqg.service.ManagerService"%>
<%@page import="com.jqg.service.impl.ManagerServiceImpl"%>
<%
String path = request.getContextPath(); 
if(path!=null && !path.equals("")){	if(path.contains("/")){		if(path.equals("/")){path = "";	}}else{	path += "/";}} 
String basePath = "";if(request.getServerPort()==80){basePath = request.getScheme()+"://"+request.getServerName()+path+"/";}else{basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";}  
ManagerService managerService = new ManagerServiceImpl();
List<Manager> managers =managerService.findManagerBySql("select m.* from Manager m where m.isBan=1 and m.isCustomer=1 and m.display=1");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人中心-认证中心</title>
<link rel="icon" href="<%=path %>/image/favicon/favicon.ico" type="image/x-icon" />
<link href="<%=basePath%>/css/public.css" type="text/css" rel="stylesheet"/>
<link href="<%=basePath%>/css/user/user_public.css" type="text/css" rel="stylesheet"/>
<link href="<%=path%>/css/kefu.css" type="text/css" rel="stylesheet"/>
<!--导航下拉菜单 js-->
<script type="text/javascript" src="<%=basePath%>/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/ajaxfileupload.js"></script>
<!--左侧折叠菜单-->
<script type="text/javascript" src="<%=basePath%>/js/menu.js"></script>
</head>
<body> 
<!--头部开始-->
<div class="header">
    <jsp:include page="../header.jsp" />
</div>
<!--头部结束-->
<div class="maincontent">
  <input type="hidden" id="path" value="<%=basePath%>"/>
  <div class="conbox fn_cle">
    <jsp:include page="left.jsp"/>
   </div>
     <div class="user_r">
         <div id="hotnews_caption">
            <ul id="h_menu">                  
                 <li class="current"onclick="secBoard('hotnews_caption','list',1);"><a href="#" >修改专属客服</a></li>
            </ul>
         </div>
         <div id="hotnews_content">
           <div class="current" id="list_1">
           <form action="<%=basePath%>user/updateKefu" method="post" name="form">
           <input type="hidden" name="kefuId" id="kefuser" value=""/>
           <input type="hidden" name="kefuId1" id="kefuser1" value="${uservip1.manager.managerId}"/>
           
           		<div class="img-scroll" style="margin:0;width:100%">
						<div class="img-list" id="reg_photo" style="width:100%">
						<div id="reg_img">默认选中为当前用户所属客服，如需更改通过如下操作进行修改。</div>
						<div style="width:130px;font-size: 16px;float:left;padding-top:20px;"> 修改绑定客服： </div>
							<ul id="box">
							
	                               <%for(Manager man:managers){ %>
	                              	 <li id="<%= man.getManagerId()%>" >
	                    	      		<img src="<%=path%><%=man.getPathMages()%>"  />
	                    	      		<span><%=man.getManagerName()%></span>
	                    	         </li>
	                              <% } %>
	                        </ul>
	                    </div>
                  </div>
               <div style="clear:both;padding:50px  0 0  16% ">
               <input name="" class="reg_btn" value="提交" type="submit" style="background-color: #f59b3b;"/>
               </div>
            </form>
           </div>       
   </div>
  </div>
</div> 

</div>

</div>
<!--footer 开始-->
<div class="footer">
  <jsp:include page="../footer.jsp" />
</div>
<input type="hidden" name="userId" id="userId" value=${sessionScope.uservip.userId}></input>
<!--footer 结束-->
<script type="text/javascript">

	function selectServer()
			{
				var kefuser1 = document.getElementById('kefuser1').value;
				var Oreg_photo=document.getElementById('reg_photo').getElementsByTagName('li');
			    for(var i=0;i<Oreg_photo.length;i++)
				{
					if(kefuser1==Oreg_photo[i].id)
						{
						 
							Oreg_photo[i].className='bor_red';
						}
					
					Oreg_photo[i].onclick=function()
					{
						for(var i=0;i<Oreg_photo.length;i++)
						{
							Oreg_photo[i].className='';
							this.className='bor_red';
							document.getElementById('kefuser').value=(this.id);
						}

					}
				} 
				
			}

selectServer();

</script>
</body>
</html>