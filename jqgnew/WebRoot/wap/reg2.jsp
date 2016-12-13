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
        
       <form  action="" id="form1" method="post">
				<div data-role="fieldcontain" class="wk_loginmain wk_reg">
                	<p>
                	<input type="hidden" id="userName"   name="userName"  value="${userName }" class="user_back" />
					<input type="hidden" id="referId"   name="referId"  value="${referId }" />
					<input type="text" id="phoneNum"   name="phoneNum"  class="user_back pass_back_02" placeholder="手机号（非必填）"/>
					<span>
					<input type="text" id="code"  name="code"  class="user_back pass_back_02" placeholder="手机验证码"/>
<!-- 					<a href="javascript:void(0);"><button style="margin-top:-6px;height:45px;" onclick="phoneSumit();">获取验证码</button></a> -->
					<a href="javascript:void(0);" onclick="phoneSumit();" style="padding:10px 5px 11px 5px;color:#FFF;margin-top:-4px;border-radius:5px;border: 1px solid #cccccc;background: #4eaff0 none repeat scroll 0 0;">获取验证码</a>
					</span>
					<input type="password"  id="userPwd"  name="password"    class="pass_back" placeholder="用户密码6-16位英文或数字"/> 
					<input type="password"  id="userPwd2"  name="userPwd2"   class="pass_back pass_back_02" placeholder="确认密码"/> 
					
<!--                     <span> -->
<!--                     <input type="text" id="code"  name="code"   class="pass_back pass_back_02" placeholder="验证码"/> -->
<!--                     <a><img src="img/yanzhengma .png"></a> -->
<!--                     </span> -->
                    </p>
                   
					<input type="button" onclick="toSubmit();"  value="注 册">
                       
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
		var inputPwd =  document.getElementById("userPwd").value;
		var inputPwd2 =  document.getElementById("userPwd2").value;
		var phoneNum=document.getElementById("phoneNum").value;
		if(phoneNum!=null && phoneNum!=""){
			if(inputCode==""||inputCode==null){
				alert("手机验证码不能为空");
				return false;
			}
		}
		if(6>inputPwd.length&&16<inputPwd.length){
			alert("密码为6-16位之间");
		}else  if(inputPwd.length <= 0){
			alert("密码不能为空");
		}else if(inputPwd!=inputPwd2){
			alert("两次密码不一致");
		}
		else{
		
			var form = document.getElementById("form1");
	        form.action="<%=path%>/wap/reg";
 	        form.submit();
		 	document.activeElement.style.background = "gray";
		    document.activeElement.value="正在提交...";
		    document.activeElement.disabled=false;	
		}
	}
// var timeout=60;              
//  function countdown(){
//  document.getElementById("yanzheng").setAttribute("disabled","disabled");
// 	if (timeout == 0)
// 		{
// 			document.getElementById("yanzheng").value = "重新发送";
// 		 	 document.getElementById("yanzheng").removeAttribute("disabled");;
// 			timeout=60;
// 		}
// 		else
// 		{
// 		document.getElementById("yanzheng").value =  timeout+'s';
// 		setTimeout('countdown()',1000);
// 	}
// 	timeout--;
// }	
function phoneSumit(){
	var phoneNum=document.getElementById("phoneNum").value;
	var a=/^1[3|4|5|7|8][0-9]\d{4,8}$/;
	var boo=true;
		if(phoneNum==null || phoneNum==""){
			alert("手机号不能为空");
			boo=false;
			return;
		}else if(phoneNum.length!=11 || !phoneNum.match(a)){
			alert("手机号格式不正确");
			boo=false;
			return;
		}else{
		$.ajax({
  		  async:false,
  		  url:"<%=path %>/user/findphone",
  		  type:"GET",
  		  data:"phoneNum="+phoneNum,
  		  dataType:"json",
  		  success:function(data){
  		     var member=eval("("+data+")");
  		     var result=member.result;
  		      if(result=="1"){
  		    	 alert("此手机号已被使用");
  		    	 boo=false;	
  		    	 return;
  		  	 }
  		  	 
  		  }
  	  });
	}
	if(boo==true){
// 		countdown();
		var data="mobiles="+phoneNum+"&isOpen=1&messModelId=9";
	$.ajax({
		  async:false,
		  url:"<%=path %>/user/sendMessageZC",
		  type:"POST",
		  data:data,
		  dataType:"json",
		  success:function(data){
		     if(data=="success"){
		    	alert("验证码发送成功");
				}else if(data=="ycz"){
					alert("手机号已存在");
				}else{
					alert("发送失败");
				}
		  }
	  });
	}
}
</script>
</html>
