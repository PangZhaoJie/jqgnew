<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="/struts-tags" prefix="s"%>
 <%
String path = request.getContextPath();if(path!=null && !path.equals("")){	if(path.contains("/")){		if(path.equals("/")){path = "";	}}else{	path += "/";}} 
String basePath = "";if(request.getServerPort()==80){basePath = request.getScheme()+"://"+request.getServerName()+path+"/";}else{basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";}    
%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="icon" href="<%=path %>/image/favicon/favicon.ico" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录页面</title>
<link href="<%=basePath%>/css/public.css" type="text/css" rel="stylesheet"/>
<link href="<%=basePath%>/css/reglogin.css" type="text/css" rel="stylesheet" />
<link href="<%=basePath%>/css/index.css" type="text/css" rel="stylesheet"/>
<!--导航下拉菜单 js-->
<script type="text/javascript" src="<%=basePath%>/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/login/login.js"></script>
</head>
<body onload="createCode()">
<!--头部开始-->
<div class="header">
    <jsp:include page="../header.jsp" />
</div>
<!--头部结束-->


<!--登录 开始-->
<div class="login_content" >
   <div class="login fn_cle">
       <div class="left_img"><img src="../images/login_left_img.png" /></div>
           <div class="login_info">
             <h3><img src="../images/login_title.png" /></h3>
          <div class="myFormWrap">
          <form name="form" action="<%=path %>/user/finduser" method="post" onsubmit="return check(this);">
             <p> <label>用户名：</label> <input name="userName" id="userName" type="text" size="30" style=" height:29px;"/><span id="userTip" ></span></p>
             <p> <label>密码：</label> <input name="password" id="password" type="password" size="30" style=" height:29px;"/><span id="pwdTip"></span></p>
             <p> <label>验证码：</label> <input name="code" id="yanzhengma" type="text" size="17" style=" height:29px;"/>
<!--             <em><input type="text" onclick="createCode()" readonly="readonly" id="checkCode" class="unchanged" style="text-align:center;font-size:20px;width: 77px;height:29px;background-color:#FFFFFF"  /></em><span id="yzmTip"></span></p>-->
             <em><a href="javascript:changeimg()"><img id="code" src="<%=path %>/login/makeCode.jsp" style="height:26px;"></a></em><span id="yzmTip"></span>
             </p>
             <c:if test="${!empty resultString}">
             <span id="error" style="font-size:12px;color:red;float:left;width:200px;line-height:30px; padding-left:90px; padding-top:10px;">${resultString}</span>
             </c:if>       
             <div class="login_bt">   <input type="submit" class="log_sub"  value="登&nbsp;&nbsp;录" id="denglu"/></div>
<!--              <div class="forget"> -->
<!--                 <div class="dl"><input id="checkbox" type="checkbox" value="1" name="kpcookie" style="float:left;"><em style="line-height:15px;">30天内免登录</em></div> -->
<!--                 <span><a href="#">忘记密码？</a></span> -->
<!--             </div> -->
            
           <span class="txzc"><a href="<%=path %>/r_pwd_1.jsp">忘记密码</a>|<a href="<%=basePath%>login/reg1.jsp?long">免费注册</a> </span> 
          </form>
        
          </div>
        
       </div>
    </div>
</div>
<!--登录 结束-->

<!-- footer 开始 -->
<div class="footer">
  <jsp:include page="../footer.jsp" />
</div>
<!-- footer 结束 -->


<!--右边漂浮 开始-->
<%-- <div id="service">
	<a href="<%=path%>/tool/borrow_tool.jsp" class="srvLog"   title="网贷计算器">网贷计算器</a>
	<a href="tencent://message/?uin=285772998&Site=企业网站&Menu=yes" class="srvCns" title="官方交流群">官方交流群</a>
	<a href="<%=path%>/tender/totenderList" class="srvDj"   title="投资理财">投资理财</a>
	<a class="goTop" id="goTop" title="返回顶部" style="display:none" >返回顶部</a>
</div>
<script type="text/javascript" src="../js/service.js">	</script> --%>
<!--右边漂浮 结束-->

<script type="text/javascript">
function changeimg()
				{
					var myimg = document.getElementById("code"); 
					var now = new Date().getTime(); 					
					myimg.src="<%=path%>/login/makeCode.jsp?code=" + now;
					
				}
				changeimg();


</script>
</body>
</html>