<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	if (path != null && !path.equals("")) {
		if (path.contains("/")) {
			if (path.equals("/")) {
				path = "";
			}
		} else {
			path += "/";
		}
	}
	String basePath = "";
	if (request.getServerPort() == 80) {
		basePath = request.getScheme() + "://"
				+ request.getServerName() + path + "/";
	} else {
		basePath = request.getScheme() + "://"
				+ request.getServerName() + ":"
				+ request.getServerPort() + path + "/";
	}
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>登录</title> 



</head>

<body>

<div data-role="page" data-position="fixed">

    <div data-role="header" data-position="fixed">
    <jsp:include page="header.jsp" />
</div>
    
    <div role="main" class="ui-content">
   		<div class="wk_logo"><img src="images/mLogo.png"></div>
        
       <form action="" method="post" id="form1">
					<div data-role="fieldcontain" class="wk_loginmain">
					<p>
						<input type="text" class="user_back" placeholder="用户名" id="userName" name="userName" /> <input
							type="password" class="pass_back" placeholder="密码"  id="userPwd" name="userPwd"/> <span>
							<input type="text" class="pass_back pass_back_02"
							placeholder="验证码" id="code" name="code" onblur="checkcode1();" /> <a>
							<a href="javascript:changeimgone()" data-ajax="false">
							<img id="code1" src="<%=basePath %>/login/makeCode.jsp" style="height:30px;margin-top:0.2em;">
						</a> </span>
					</p>
					<input type="button" value="登 录"  onclick="checkcode1();">
					<div>
<!-- 						<a href="forget.html" class="get_pwd" data-ajax="false">忘记密码?</a> -->
						<a href="<%=path %>/wap/reg.jsp" class="get_reg" data-ajax="false">新用户注册</a>
					</div>
				</div>
			</form>
        
  	</div> 
    <div data-role="footer" data-position="fixed">  
    <jsp:include page="footer.jsp" />
  </div>
</div>    
</body>
<script type="text/javascript">

function checkcode1() {
		var boo=false;
		var inputCode =  document.getElementById("code").value;
		var inputName =  document.getElementById("userName").value;
		var inputPwd =  document.getElementById("userPwd").value;
		if (inputCode.length <= 0) {
			alert("请输入验证码！");
			
		}else if(inputName.length <= 0){
			alert("用户名不能为空");
			
		}else if(inputPwd.length <= 0){
			alert("密码不能为空");
		
		}else{
			boo= true;
		}
		if(boo==true){
			var form = document.getElementById("form1");
	        form.action="wap/login";
 	        form.submit();
		 	document.activeElement.style.background = "gray";
		    document.activeElement.value="正在提交...";
		    document.activeElement.disabled=false;	
		}
	}
function changeimgone(){
	var myimg = document.getElementById("code1"); 
	now = new Date(); 
	myimg.src="<%=basePath %>/login/makeCode.jsp?code="+now.getTime();
} 

changeimgone();
</script>
</html>
