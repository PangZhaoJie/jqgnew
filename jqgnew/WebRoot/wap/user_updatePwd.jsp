<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
 <meta name="viewport" content="width=device-width, initial-scale=1">
<title>我的银行卡</title>
</head>

<body>
	<div data-role="page" class="wk_mycard">
		<div data-role="header" data-position="fixed">
			<jsp:include page="header.jsp" />
		</div>
		<style>
.wk_psd .one {
	border-bottom: 1px solid #ddd;
}
</style>
		<div role="main" class="ui-content" >
			<form action="" id="form1">
				<div data-role="fieldcontain" class="wk_loginmain wk_reg wk_psd">
					<p>
						<input type="password" id="oldPwd"  name="oldPwd" class="pass_back one" placeholder="当前密码" />
						<input type="password" id="newPwd1" name="newPwd1" class="pass_back one" placeholder="新密码" />
						<input type="password" id="newPwd2" name="newPwd2" class="pass_back" placeholder="确认新密码" />
					</p>
					<input type="button" onclick="toSubmit();" value="确 定">

				</div>
			</form>
		</div>
		<div data-role="footer" data-position="fixed">  
   <jsp:include page="footer.jsp" />
</div>
	</div>
</body>
<script type="text/javascript">
function toSubmit(){
	var oldPwd=$("#oldPwd").val();
	var newPwd1=$("#newPwd1").val();
	var newPwd2=$("#newPwd2").val();
	
	if(oldPwd==""||oldPwd==null){
		alert("旧密码不能为空");
	}else if(newPwd1==""||newPwd1==null){
		alert("新密码不能为空");
	}else if(newPwd2==""||newPwd2==null){
		alert("确认密码不能为空");
	}else if(newPwd1!=newPwd2){
		alert("新密码和确认密码不一致");
	}else{
			var form = document.getElementById("form1");
	        form.action="wap/updatePwd";
 	        form.submit();
		 	document.activeElement.style.background = "gray";
		    document.activeElement.value="正在提交...";
		    document.activeElement.disabled=false;
	}

}

</script>

</html>
