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
<link rel="icon" href="<%=path %>/image/favicon/favicon.ico" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎登录后台管理系统</title>
<link href="<%=basePath%>/back/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="<%=basePath %>/back/js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/back/js/login.js"></script>

<script language="javascript">
	$(function(){
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
	$(window).resize(function(){  
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
    })  
});  




</script> 


<script type="text/javascript">
//注册键盘事件
document.onkeydown = function(e) {
    //捕捉回车事件
    var ev = (typeof event!= 'undefined') ? window.event : e;
    if(ev.keyCode == 13) {
        login();
    }
}
</script>

</head>

<body style="background-color:#0066CB;background-image:url(<%=basePath%>/back/images/bj.jpg); background-repeat:no-repeat; background-position:center top; overflow:hidden;" onload="createCode()">
  
<input type="hidden" id="path" value="<%=path%>"/>
	<div class="loginsybg"></div>
    
    <div class="loginbody">
    
    <span class="systemlogo"></span> 
       
    <form id="login" method="post">
    <div class="logindiv">
    <div class="loginbox">
    
     <ul style="float:left;">
      <li><label class="text_title">用户名：</label><input name="managerName" id="managerName" type="text" class="loginuser user_ico" value="用户名" style="width: 200px;float: left;margin-left: 0px;" onclick="JavaScript:this.value=''" onblur="checkName(this);"/>
      <span id="nameTip" style="width: 120px;float: left;margin-left: 8px;margin-top: 5px;color:red" ></span></li>
      <li><label class="text_title">密&nbsp;&nbsp;&nbsp;码： </label><input name="password" id="password" type="password" style="width: 200px;float: left;margin-left: 0px;" class="loginpwd pwd_ico" value=""  onclick="JavaScript:this.value=''" onblur="checkPwd(this);"/><span id="pwdTip" style="width: 120px;float: left;margin-left: 8px;margin-top: 5px;color:red"></span></li>
      <li><label class="text_title">口&nbsp;&nbsp;&nbsp;令： </label><input name="passwordWord" id="passwordWord" type="text" class="loginkl kl_ico" style="width: 200px;float: left;margin-left: 0px;" value="口令" onclick="JavaScript:this.value=''" onblur="checkPwdp(this);"/><span id="wordTip" style="width: 120px;float: left;margin-left: 8px;margin-top: 5px;color:red"></span></li>
      <li><label class="text_title">验证码：</label><input name="code" id="code" type="text" class="logincode code_ico" value="验证码" style="float:left;width:80px"  onclick="JavaScript:this.value=''"  onblur="checkCodeDD()"  /><input type="text" onclick="createCode()" readonly="readonly" id="checkCode" class="unchanged" style="float:left;text-align:center;font-size:20px;width: 120px;margin-left:10px;height:35px;line-height:35px;background-color:green"/><span id="yzmTip" style="width: 120px;float: left;margin-left: 8px;margin-top: 5px;color:red"></span><p id="error" style="font-size:12px;color:red">${error}</p></li>
      <li style="padding-left:50px;"><input name="" type="button" class="loginbtn" value="登录"  onclick="login()"  />
<!--       <label><input name="" type="checkbox" value="" checked="checked" />记住密码</label> -->
<!--       <label><a href="#">忘记密码？</a></label> -->
      </li>
    </ul>
    </div>
    </div>
    </form>
    </div>
    
    
    
    
	
    
</body>

</html>
