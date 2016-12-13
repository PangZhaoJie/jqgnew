<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%    
	String path = request.getContextPath();if(path!=null && !path.equals("")){	if(path.contains("/")){		if(path.equals("/")){path = "";	}}else{	path += "/";}}    
	String basePath = "";if(request.getServerPort()==80){basePath = request.getScheme()+"://"+request.getServerName()+path+"/";}else{basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";}      
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人中心-VIP申请</title>
<link rel="icon" href="<%=path %>/image/favicon/favicon.ico" type="image/x-icon" />
<link href="<%=basePath%>/css/public.css" type="text/css" rel="stylesheet"/>
<link href="<%=basePath%>/css/user/user_public.css" type="text/css" rel="stylesheet"/>
<link href="<%=basePath%>/css/user/user_renzheng.css" type="text/css" rel="stylesheet"/>
<!--导航下拉菜单 js-->
<script type="text/javascript" src="<%=basePath%>/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/menu.js"></script>
<script type="text/javascript">
 var $page =  jQuery.noConflict();
 function getJSONData() {
 		$page("#ID1").hide();
 		$page("#ID5").show();
 }
 $page(function() {  
 	getJSONData();  
 });
 </script>
</head>
<body> 
<!--头部开始-->
<div class="header">
    <jsp:include page="../header.jsp" />
</div>
<!--头部结束-->
<div class="maincontent">
  	<input id="path" type="hidden" value="<%=basePath%>">
  <div class="conbox fn_cle">
    <jsp:include page="../user/left.jsp"/>
   </div>
     <div class="user_r">
         <div id="hotnews_caption">
            <ul id="h_menu">                  
                 <li class="current" onclick="secBoard('hotnews_caption','list',1);"><a href="#" >VIP申请</a></li>
            </ul>
         </div>
    <div id="hotnews_content">
           <div class="current" id="list_1">
           <c:if test='${ uservip.isApplyVIP==1}'>已经发出申请，请等待审核！</c:if>
            <!-- 如果不是vip的时候 -->
            <c:if test='${(uservip.isVIP=="0"||uservip.isVIP==null) && uservip.isApplyVIP!="1"}'>
       		 <form method="post" id="list1" action="<%=path%>/user/updateUservip?userId=${sessionScope.uservip.userId}">
                 <p class="zhuyi" style="line-height: 35px;"><img src="<%=basePath%>/images/tishi_03.png" style="margin-right:5px">请注意以下事项：</p>
                 <p class="zhuyi" style="line-height: 35px;">1、申请vip成功后可以享受vip特权。</p>
                 <p class="zhuyi" style="line-height: 35px;">2、申请VIP需要支付的费用每月   ${systemconf.parvalue }元</p>
                 
               	<div id="uboxstyle" class="info" style="margin-top:20px;overflow:hidden;">
				<label class="month" >申请时间：</label>
				<select name="months" id="months" style="width:auto;width:210px;height:34px; padding:0; ">
                   			<option value="1">1个月</option>
                   			<option value="2">2个月</option>
                   			<option value="3">3个月</option>
                   			<option value="4">4个月</option>
                   			<option value="5">5个月</option>
                   			<option value="6">6个月</option>
                   			<option value="7">7个月</option>
                   			<option value="8">8个月</option>
                   			<option value="9">9个月</option>
                   			<option value="10">10个月</option>
                   			<option value="11">11个月</option>
                   			<option value="12">12个月</option>
                 </select>
                  <input type="submit"  class="submit_bt" name="submit" value="VIP申请 "/>
                  <input type="hidden" name="parvalue" value=" ${systemconf.parvalue }">
                  </div>
               </form>
               </c:if>
               <!-- 如果是vip的时候 -->
	          <c:if test='${uservip.isVIP==1 && uservip.isApplyVIP!="1"}'>
	          <form method="post" id="list1" action="<%=path%>/user/updateUservip?userId=${sessionScope.uservip.userId}">
                 <p class="zhuyi" style="line-height: 35px;"><img src="<%=basePath%>/images/tishi_03.png" style="margin-right:5px">请注意以下事项：</p>
                 <p class="zhuyi" style="line-height: 35px;">1、申请vip成功后可以享受vip特权。</p>
                 <p class="zhuyi" style="line-height: 35px;">2、申请VIP需要支付的费用每月   ${systemconf.parvalue }元</p>
                 
                 <div id="uboxstyle" class="info" style="margin-top:20px;overflow:hidden;">
				  <label class="month" >VIP到期时间：</label>
				<fmt:formatDate value="${uservip.vipEndTime}" pattern="yyyy-MM-dd" />    </br>
				  <label class="month" >申请时间：</label>
				   <select name="months" id="months" style="width:auto;width:126px;height:34px; padding:0;margin-top:10px ;margin-left:11px;text-align: center;">
                   			<option value="">--请选择--</option>
                   			<option value="1">1个月</option>
                   			<option value="2">2个月</option>
                   			<option value="3">3个月</option>
                   			<option value="4">4个月</option>
                   			<option value="5">5个月</option>
                   			<option value="6">6个月</option>
                   			<option value="7">7个月</option>
                   			<option value="8">8个月</option>
                   			<option value="9">9个月</option>
                   			<option value="10">10个月</option>
                   			<option value="11">11个月</option>
                   			<option value="12">12个月</option>
                 </select>  </br>
                  <input type="submit"  class="submit_bt" name="submit" value="VIP申请 " style="margin-top:15px;margin-left:0px"/>
                  </div>
                  <input type="hidden" name="parvalue" value=" ${systemconf.parvalue }">
               </form>
	           </c:if>
          <script type="text/javascript" src="<%=basePath%>/js/tabs.js" language="javascript"></script>
        
   </div>
    
    </div>
  </div>
</div> 

</div>
<!--footer 开始-->
<div class="footer">
  <jsp:include page="../footer.jsp" />
</div>
<input type="hidden" name="userId" id="userId" value=${sessionScope.uservip.userId }></input>
<!--footer 结束-->

</body>
</html>