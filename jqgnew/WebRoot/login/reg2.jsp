<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%
String path = request.getContextPath(); 
if(path!=null && !path.equals("")){	if(path.contains("/")){		if(path.equals("/")){path = "";	}}else{	path += "/";}} 
String basePath = "";if(request.getServerPort()==80){basePath = request.getScheme()+"://"+request.getServerName()+path+"/";}else{basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";}  
String phoneNum=request.getParameter("phoneNum");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="icon" href="<%=path %>/image/favicon/favicon.ico"
	type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册页面</title>
<link href="<%=path%>/css/public.css" type="text/css" rel="stylesheet" />
<link href="<%=path%>/css/reg.css" type="text/css" rel="stylesheet" />
<!--导航下拉菜单 js-->
<script type="text/javascript" src="<%=path%>/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript">
var $q =  jQuery.noConflict();
function getCode(val){
	var phoneNum=$q('#phone').val();
	var code=$q('#code222').val();
	//var a = /^((\(\d{3}\))|(\d{3}\-))?13\d{9}|14[57]\d{8}|15\d{9}|18\d{9}$/;
	var a=/^1[3|4|5|7|8][0-9]\d{4,8}$/;
	if(phoneNum.length!=11 || !phoneNum.match(a)){
		$q("#phone2").show();
		$q("#phone1").hide();
		$q("#phone3").hide();
		$q("#phone4").hide();
		$q("#phone5").hide();
		return false;
	}else if(code==''){
		$q("#yzCode1").show();
		$q("#yzCode2").hide();
		$q("#yzCode3").hide();
		return false;
	}else{
		$q("#phone2").hide();
		$q("#phone1").hide();
		$q("#phone3").hide();
		$q("#phone4").hide();
		$q("#phone5").hide();
		$q("#yzCode1").hide();
		$q("#yzCode2").hide();
		$q("#yzCode3").hide();
		countdown();
	}
	var data="mobiles="+phoneNum+"&code="+code+"&isOpen=1&messModelId=9";
	$q.ajax({
		  async:false,
		  url:"<%=path %>/user/sendMessageZC",
		  type:"POST",
		  data:data,
		  dataType:"json",
		  success:function(data){
		     if(data=="success"){
		    	$q("#phone3").show();
		 		$q("#phone1").hide();
		 		$q("#phone2").hide();
		 		$q("#phone4").hide();
		 		$q("#phone5").hide();
		 		changeimg();
				}else if(data=="ycz"){
					$q("#phone5").show();
			 		$q("#phone1").hide();
			 		$q("#phone2").hide();
			 		$q("#phone3").hide();
			 		$q("#phone4").hide();
			 		changeimg();
				}else if(data=="errorCode"){
					$q("#yzCode2").show();
			 		$q("#yzCode1").hide();
			 		$q("#yzCode3").hide();
			 		changeimg();
				}else if(data=="timeout"){
					$q("#yzCode3").show();
			 		$q("#yzCode1").hide();
			 		$q("#yzCode2").hide();
			 		changeimg();
				}else{
					$q("#phone4").show();
					$q("#phone5").hide();
			 		$q("#phone1").hide();
			 		$q("#phone2").hide();
			 		$q("#phone3").hide();
			 		changeimg();
				}
		  }
	  });
}

var timeout=60;              
 function countdown(){
 document.getElementById("yanzheng").setAttribute("disabled","disabled");
	if (timeout == 0)
		{
			document.getElementById("yanzheng").value = "重新发送";
		 	 document.getElementById("yanzheng").removeAttribute("disabled");;
			timeout=60;
		}
		else
		{
		document.getElementById("yanzheng").value =  timeout+'s';
		setTimeout('countdown()',1000);
	}
	timeout--;
}
function phoneSumit(){
    $q('#phone').removeAttr("disabled"); 
	var phoneNum=$q('#phone').val();
	var a=/^1[3|4|5|7|8][0-9]\d{4,8}$/;
	if(phoneNum.length!=11 || !phoneNum.match(a)){
		$q("#phone2").show();
		$q("#phone1").hide();
		$q("#phone3").hide();
		$q("#phone4").hide();
		return false;
	}else{
//		$q("#phone2").hide();
//		$q("#phone1").hide();
//		$q("#phone3").hide();
//		$q("#phone4").hide();
var data="mobiles="+phoneNum+"&isOpen=1&messModelId=9";
	$q.ajax({
  		  async:false,
  		  url:"<%=path %>/user/findphone",
  		  type:"GET",
  		  data:"phoneNum="+phoneNum,
  		  dataType:"json",
  		  success:function(data){
  		     var member=eval("("+data+")");
  		     var result=member.result;
  		      if(result=="1"){
  		    	 	$q("#phone5").show();
			 		$q("#phone1").hide();
			 		$q("#phone2").hide();
			 		$q("#phone3").hide();
			 		$q("#phone4").hide();
  		    	 return false;
  		  	 }
  		  	 
  		  }
  	  });
	}
	var code=$q('#code').val();
	if(code==null || code==""){
		 $q("#yzm1").show();
		 $q("#yzm2").hide();
		 $q("#yzm3").hide();
		 return false;
	}else{
		 $q("#yzm1").hide();
		 $q("#yzm2").hide();
		 $q("#yzm3").hide();
	}
	var userName=$q('#userName').val();
	var password=$q('#password').val();
	var referId=$q('#referId').val();
	var kefuId = $q('#kefuId').val();
	document.getElementById("bt").setAttribute("disabled","disabled");
	var data="phoneNum="+phoneNum+"&code="+code+"&userName="+userName+"&password="+password+"&referId="+referId+"&kefuId="+kefuId;
		$q.ajax({
  		  async:false,
		  url:"<%=path %>/user/register",
		  type:"POST",
		  data:data,
		  dataType:"json",
		  success:function(data){
			  if(data=="success"){
				  $q("#yzm3").show();
				  $q("#yzm1").hide();
				  $q("#yzm2").hide();
				  window.location.href="<%=path %>/user/search.action?mark=#";
			  }else if(data=="userError"){
			  	  alert("用户名已存在！");
			  	  window.location.href="<%=path %>/login/reg1.jsp";
			  }else{
				  $q("#yzm2").show();
				  $q("#yzm1").hide();
				  $q("#yzm3").hide();
				  document.getElementById("bt").removeAttribute("disabled");
			  }
		     
		  }
		 
	  });
}
function jumpSubmit(){
	var userName=$q('#userName').val();
	var password=$q('#password').val();
	var referId=$q('#referId').val();
	var kefuId = $q('#kefuId').val();
	document.getElementById("bt1").setAttribute("disabled","disabled");
	var data="&userName="+userName+"&password="+password+"&referId="+referId+"&kefuId="+kefuId;
		$q.ajax({
  		  async:false,
		  url:"<%=path %>/user/register",
		  type:"POST",
		  data:data,
		  dataType:"json",
		  success:function(data){
			  if(data=="success"){
				  window.location.href="<%=path %>/login/reg3.jsp";
			  }else if(data=="userError"){
			  	alert("用户名已存在！");
			  	  window.location.href="<%=path %>/login/reg1.jsp";
			  }else
				  document.getElementById("bt1").removeAttribute("disabled");
		  }
		 
	  });

}
</script>
</head>
<body>
	<jsp:include page="../header.jsp" />
	<!--注册开始-->
	<div class="regmain">
		<div class="regbox">
			<div class="reg_title">
				<h1>账户注册</h1>
			</div>
			<div class="reg_main">
				<h2>
					<img src="<%=path%>/images/reg2_03.jpg" width="732" height="52" />
				</h2>
				<div class="pic_title">
					<ul>
						<li class="pic_1">填写注册信息</li>
						<li class="pic_2">验证注册信息</li>
						<li class="pic_3">注册成功</li>
					</ul>
					<div class="clear"></div>
				</div>

				<form action="" method="post" id="tj">
					<input type="hidden" value="${userName}" name="userName"
						id="userName" /> <input type="hidden" value="${password }"
						name="password" id="password" /> <input type="hidden"
						value="${referId }" name="referId" id="referId" /> <input
						type="hidden" value="${kefuId }" name="kefuId" id="kefuId" />
					<ul>
						<li class="reg_l">手机号码：</li>
						<li class="reg_r"><input name="phoneNum" id="phone"
							type="text" value="<%=phoneNum %>" style="width: 220px"
							class="reg_text" disabled="disabled"
							onkeyup="this.value=this.value.replace(/\D/g,'')"
							onafterpaste="this.value=this.value.replace(/\D/g,'')" />
						</li>
						<li class="tips" id="phone1" style="display:none;margin-left:5px;">手机号不能为空！</li>
						<li class="tips" id="phone2" style="display:none;margin-left:5px;">手机号输入不合法！</li>
						<li class="tips" id="phone3"
							style="display:none;color:green;margin-left:5px;">验证码发送成功！</li>
						<li class="tips" id="phone4" style="display:none;margin-left:5px;">验证码发送失败！</li>
						<li class="tips" id="phone5" style="display:none;margin-left:5px;">手机号已存在！</li>
					</ul>
					<div class="clear"></div>
					<ul>
						<li class="reg_l">图片验证码：</li>
						<li class="reg_r"><input name="code222" id="code222"
							type="text" class="reg_text" placeholder="用于重新获取短信验证码"
							style="width: 156px" />
						</li>
						<li class="tips"><a href="javascript:changeimg();"><img
								id="code333" style="height:26px;width: 60px;margin-left: -54px;">
						</a>
						</li>
						<li class="tips">&nbsp;&nbsp;<input name="" type="button"
							id="yanzheng" class="phone_code" value="获取验证码"
							onclick="getCode(this)" />
						</li>
						<li class="tips" id="yzCode1" style="display:none;">图片验证码不能为空！</li>
						<li class="tips" id="yzCode2" style="display:none;">图片验证码不正确！</li>
						<li class="tips" id="yzCode3" style="display:none;">短信发送间隔不到60秒！</li>
					</ul>
					<div class="clear"></div>
					<ul>
						<li class="reg_l">手机验证码：</li>
						<li class="reg_r"><input name="code" id="code" type="text"
							class="reg_text" style="width: 220px" placeholder="收到的短信验证码"
							onkeyup="this.value=this.value.replace(/\D/g,'')"
							onafterpaste="this.value=this.value.replace(/\D/g,'')" />
						</li>
						<li class="tips" id="yzm1" style="display:none;">手机验证码不能为空！</li>
						<li class="tips" id="yzm2" style="display:none;">手机验证码不正确！</li>
						<li class="tips" id="yzm3" style="display:none;color:green;">手机验证码输入正确！</li>
					</ul>
					<div class="clear"></div>


					<ul>
						<li class="reg_l">&nbsp;</li>
						<li><input name="" type="button" class="reg_btn" value="下一步"
							onclick="phoneSumit();" id="bt" />&nbsp;&nbsp; <!--    	<input name="" type="button" class="reg_btn" value="跳过并继续" onclick="jumpSubmit();" id="bt1"/> -->
						</li>
					</ul>
					<br>
					<br>
					<br>
					<br>
					<br>
					<br>
				</form>
				<div class="clear"></div>
			</div>
		</div>
	</div>
	<!--注册结束-->
	<!--footer 开始-->
	<div class="footer">
		<jsp:include page="../footer.jsp" />
	</div>
	<!--footer 结束-->
	<script type="text/javascript">
function changeimg(){
	var myimg = document.getElementById("code333"); 
	now = new Date(); 
	myimg.src="../login/makeCode.jsp?code="+now.getTime();
} 
changeimg();
</script>
</body>
</html>
