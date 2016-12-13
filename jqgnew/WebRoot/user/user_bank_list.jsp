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
<link rel="icon" href="<%=path %>/image/favicon/favicon.ico" type="image/x-icon" />
<title>个人中心-已绑定银行账户</title>
<link href="../css/public.css" type="text/css" rel="stylesheet"/>
<link href="../css/user/user_public.css" type="text/css" rel="stylesheet"/>
<link href="../css/user/user_cash.css" type="text/css" rel="stylesheet"/>
<!--导航下拉菜单 js-->
<script type="text/javascript" src="../js/jquery-1.8.2.min.js"></script>
<!--表单下拉列表框-->

<!--左侧折叠菜单-->
 <script type="text/javascript">
 var $menu=  jQuery.noConflict();
 window.onload=function (e){
	 $menu("#ID1").hide();
	 $menu("#ID2").show();
 }
 
 </script>
 <script type="text/javascript" src="../js/select2css.js"></script>
<script type="text/javascript" src="../js/menu.js"></script>
<script type="text/javascript" src="../js/page.js"></script>

</head>
<body> 
<!--头部开始-->
<div class="header">
<jsp:include page="../header.jsp" />
    
</div>
<!--头部结束-->
<div class="maincontent">
  
  <div class="conbox fn_cle">
       <jsp:include page="left.jsp" />
   
   </div>
   <input id="path" type="hidden" value="<%=basePath%>">
     <div class="user_r">
         <div id="hotnews_caption">
            <ul id="h_menu">                  
                 <li class="current"onclick="secBoard('hotnews_caption','list',1);"><a href="#">银行账户</a></li>
            </ul>
         </div>
         
         <div id="hotnews_content">
           <div class="current" id="list_1">
            <div class="list_1_top">
                 <span> 尊敬的用户，以下是您的银行账户，敬请仔细操作 。</span>
                  <a class="add_bt" href="<%=path%>/userbank/showBank">添加银行卡</a>
              </div>
              <div class="bank_list">
               <table width="825" cellspacing="0" cellpadding="0" class="bank_list"  id="banks">
                       <tr class="th1">
                                <td width="190">银行名称</td>
                                <td width="220">开户银行</td>
                                <td width="210">银行账号</td>
                                <td width="100">银行账户户主</td>
                                <td width="100">操作</td>
                               
                               
                            </tr>
                            
                           
               </table>
                <div id="pageroot" class="page" style="display: none;" >
  	<span id="currentPage">第页</span>&nbsp; &nbsp; 
  	<span id="totalPage">共页</span>&nbsp; &nbsp; 
  	<span ><a id="firstPage" href="">首页</a></span>&nbsp; &nbsp; 
  	<span ><a id="prevPage" href="">上一页</a></span>&nbsp; &nbsp; 
  	<span ><a id="nextPage" href="">下一页</a></span>&nbsp; &nbsp; 
  	<span ><a id="lastPage" href="">最后一页</a></span>&nbsp; &nbsp; 
  </div>
              </div>
           </div>
           <script type="text/javascript" src="../js/tabs.js" language="javascript"></script>
        </div>
        
   </div>
    
    
  </div>
</div> 

</div>
<!--footer 开始-->
<div class="footer">
 <jsp:include page="../footer.jsp" />
</div>
<!--footer 结束-->


<!--右边漂浮 开始-->
<%-- <div id="service">
	<a href="<%=path%>/tool/borrow_tool.jsp" class="srvLog"   title="网贷计算器">网贷计算器</a>
	<a href="tencent://message/?uin=285772998&Site=企业网站&Menu=yes" class="srvCns" title="官方交流群">官方交流群</a>
	<a href="<%=path%>/tender/totenderList" class="srvDj"   title="投资理财">投资理财</a>
	<a class="goTop" id="goTop" title="返回顶部" style="display:none" >返回顶部</a>
</div>
<script type="text/javascript" src="../js/service.js">	</script> --%>
<!--右边漂浮 结束-->

</body>
</html>