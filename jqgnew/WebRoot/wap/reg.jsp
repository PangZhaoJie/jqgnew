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

<title>注册</title> 



</head>

<body>

<div data-role="page" data-position="fixed">

    <div data-role="header" data-position="fixed">
    <jsp:include page="header.jsp" />
</div>
    
    <div role="main" class="ui-content" >
       <form action=""  id="form1" method="post" >
				<div data-role="fieldcontain" class="wk_loginmain wk_reg">
                	<p>
                	<input type="text" id="userName"   name="userName"  class="user_back" placeholder="用户名"/>
<!-- 					<input type="text" id="userName"   name="userName"  class="user_back" placeholder="用户名"/> -->
<!-- 					<input type="password"  id="userPwd"  name="userPwd"    class="pass_back" placeholder="6-16位英文或数字"/>  -->
<!-- 					<input type="password"  id="userPwd2"  name="userPwd2"   class="pass_back pass_back_02" placeholder="确认密码"/>  -->
					<input type="text" id="referId"   name="referId"  class="user_back" placeholder="邀请码"/>
                   <span>
							<input type="text" class="pass_back pass_back_02"
							placeholder="验证码" id="code" name="code"/> <a>
							<a href="javascript:changeimgone()" data-ajax="false">
							<img id="code1" src="<%=basePath %>/login/makeCode.jsp" style="height:30px;margin-top:0.2em;">
						</a> </span>
                    </p>
                   
                    <input type="checkbox" name="checkbox-mini-0" id="checkbox-mini-0" data-mini="true" checked="checked">
                    <label for="checkbox-mini-0">我已阅读并同意<a href="<%=path %>/news/new?flag=provision" target="_blank" >《服务条款》</a></label>
                   
					<input type="button" onclick="toSubmit();"  value="下一步">
                       
				</div>	
			</form>
        
  	</div> 
    <div data-role="footer" data-position="fixed">  
    <jsp:include page="footer.jsp" />
  </div>
</div>    
</body>
<script type="text/javascript">

function toSubmit() {
		var inputCode =  document.getElementById("code").value;
		var inputName =  document.getElementById("userName").value;
// 		var inputPwd =  document.getElementById("userPwd").value;
// 		var inputPwd2 =  document.getElementById("userPwd2").value;
		var check=document.getElementById("checkbox-mini-0").checked;
		if(check==false){
			alert("请阅读并同意后再注册");
		}else if (inputCode.length <= 0) {
			alert("请输入验证码！");
			
		}else if(inputName.length <= 0){
			alert("用户名不能为空");
			
		}
// 		else if(inputPwd.length <= 0){
// 			alert("密码不能为空");
		
// 		}else if(inputPwd!=inputPwd2){
// 			alert("两次密码不一致");
// 		}
		else{
			
			
			
            	    //用户名是否存在
	            	    $.ajax({
	              		  async:false,
	              		  url:"<%=path%>/user/findusername",
	              		  type:"GET",
	              		  data:"userName="+inputName,
	              		  dataType:"json",
	              		  success:function(data){
	              		     var member=eval("("+data+")");
	              		     var result=member.result;
	              		     if(result=="1"){
	              		    	alert("用户名已存在！");
	              		  	 }
	              		  	 if(result=="0"){
	              		  		var form = document.getElementById("form1");
							        form.action="<%=path%>/wap/regJump";
						 	        form.submit();
								 	document.activeElement.style.background = "gray";
								    document.activeElement.value="正在提交...";
								    document.activeElement.disabled=false;	
	              		  	 }
	              		  }
	              	  });
            	
		
		
			
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
