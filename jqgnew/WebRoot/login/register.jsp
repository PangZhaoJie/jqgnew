<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
String path = request.getContextPath();if(path!=null && !path.equals("")){	if(path.contains("/")){		if(path.equals("/")){path = "";	}}else{	path += "/";}} 
String basePath = "";if(request.getServerPort()==80){basePath = request.getScheme()+"://"+request.getServerName()+path+"/";}else{basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";}    
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="icon" href="<%=path %>/image/favicon/favicon.ico" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册页面</title>
<link href="../css/public.css" type="text/css" rel="stylesheet"/>
<link href="../css/reglogin.css" type="text/css" rel="stylesheet" />
<!--导航下拉菜单 js-->
<script type="text/javascript" src="../js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="../js/login/register.js"></script>
<%
 String userId=request.getParameter("long");
%>
<script type="text/javascript">
function submitreg(){
var mail=document.getElementById("mail").value;
var user=$p.trim($p("#userName").val());
var pwd=document.getElementById("password").value;
var inputCode=document.getElementById("yanzhengma").value;
var cpwd=document.getElementById("confirmpwd").value;
var result=document.getElementById("checkboxi").checked;
var checkCode = document.getElementById("checkCode").value;
var phoneNum = $p("#moblie").val();//手机号
var phoneCode=$p("#PhoneCode").val();//手机验证码
if((phoneNum==null&&mail==null)||(phoneNum==""&&mail=="")){
               		alert("手机号和邮箱必须填一个");
               		return false;
               }
		if(mail!=null&&mail!=""){
                if (!mail.match(/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/)){
						alert("请填写真实的电子邮件");
						return;
                   }
                   else{
                   //邮箱是否存在
	                   $p.ajax({
	             		  async:false,
	             		  url:"<%=path %>/user/findmail",
	             		  type:"GET",
	             		  data:"mail="+mail,
	             		  dataType:"json",
	             		  success:function(data){
	             		     var member=eval("("+data+")");
	             		     var result=member.result;
	             		     if(result=="1"){
									alert("该邮箱已经被注册");
	             		    	 	return;
	             		  	 }
	             		  }
	             	  });
               }
               
              }else  if(phoneNum!=null&&phoneNum!=""){
					if(phoneNum.length!=11){
				        alert("手机号长度必须为11位");
				        return false;
					}else{
	            	    //手机号是否存在
		            	    $p.ajax({
		              		  async:false,
		              		  url:"<%=path %>/user/findphone",
		              		  type:"GET",
		              		  data:"phoneNum="+phoneNum,
		              		  dataType:"json",
		              		  success:function(data){
		              		     var member=eval("("+data+")");
		              		     var result=member.result;
		              		      if(result=="1"){
		              		    	 alert("手机号已存在");
		              		    	 return;
		              		  	 }
		              		  	 
		              		  }
		              	  });
		              	  
		              	  if(phoneCode==null&&phoneCode==""){
		              	  	alert("手机验证码不能为空");
		              	  	return;
		              	  }
						}
              		} 
//               if(phoneNum==null&&mail==null&&phoneNum==""&&mail=="" && phoneCode==null&&phoneCode==""){
//               		alert("手机号和邮箱必须填一个");
//               		return false;
//               }
               
              var len = 0;
                 for (var i = 0; i < user.length; i++) {
                      var a = user.charAt(i);
                      if (a.match(/[^\x00-\xff]/ig) != null) 
                     {
                         len += 2;
                     }
                     else
                     {
                         len += 1;
                     }
                 }
            	
            	
            if(len<4 || len>15){
				alert("名字长度必须4-15位(汉字等于两位)");
			return;
            	}else{
            	    //用户名是否存在
	            	    $p.ajax({
	              		  async:false,
	              		  url:"<%=path %>/user/findusername",
	              		  type:"GET",
	              		  data:"userName="+user,
	              		  dataType:"json",
	              		  success:function(data){
	              		     var member=eval("("+data+")");
	              		     var result=member.result;
	              		     if(result=="1"){
	              		    	 alert("用户名已存在");
	              		    	 return;
	              		  	 }
	              		  }
	              	  });
            	}
            	if(!pwd.match( /^[a-zA-Z0-9_]{6,16}$/)){
            		alert("密码必须由字母数字下划线组成，6-16位");
            		return;
            	}
            		if(cpwd==""){
            			alert("确认密码不能为空");
            			return;}
	            	if(pwd!=cpwd){
	            	alert("密码不一致");
	            	return;
            		}
       if(inputCode.length <=0)   
       {   
           alert("请输入验证码");
           return;
       }   
       else if (inputCode.toLowerCase() != code.toLowerCase())  
       {  
        
          alert("验证码输入错误");
          return;
//          createCode();//刷新验证码   
       }
       if(!result){
		   alert("请同意协议");
		   return;
	   }   
	document.getElementById("form").submit();
}


function getPhoneNum(){
		var phoneNum = $p("#moblie").val();
		if(phoneNum.length!=11){
	        $p("#moblieTip").html("<span style='font-size:12px;color:red'>手机号长度必须为11位</span>");
		}else{
		$p("#moblieTip").html("");//通过
            	    //手机号是否存在
	            	    $p.ajax({
	              		  async:false,
	              		  url:"<%=path %>/user/findphone",
	              		  type:"GET",
	              		  data:"phoneNum="+phoneNum,
	              		  dataType:"json",
	              		  success:function(data){
	              		     var member=eval("("+data+")");
	              		     var result=member.result;
	              		      if(result=="1"){
	              		    	 $p("#moblieTip").html("<span style='font-size:12px;color:red'>手机号已存在</span>");
	              		  	 }
	              		  	 if(result=="0"){
	              		  		 $p("#moblieTip").html("<span style='font-size:12px;color:red'></span>");
							    	  
	              		  	 }
	              		  }
	              	  });
					}
			}

	function phonenum(str){
		var phoneNum = $p("#moblie").val();
		if(phoneNum.length!=11){
	        $p("#moblieTip").html("<span style='font-size:12px;color:red'>手机号长度必须为11位</span>");
		}else{
		$p("#moblieTip").html("");//通过
            	    //手机号是否存在
	            	    $p.ajax({
	              		  async:false,
	              		  url:"<%=path %>/user/findphone",
	              		  type:"GET",
	              		  data:"phoneNum="+phoneNum,
	              		  dataType:"json",
	              		  success:function(data){
	              		     var member=eval("("+data+")");
	              		     var result=member.result;
	              		     if(result=="1"){
	              		    	 $p("#moblieTip").html("<span style='font-size:12px;color:red'>手机号已存在</span>");
	              		  	 }
	              		  	 if(result=="0"){
	              		  		 $p("#moblieTip").html("<span style='font-size:12px;color:red'></span>");
							    	  getPhoneCode(str);
							    	  countdown();
	              		  	 }
	              		  }
	              	  });
					}
               }
 var timeout=60;              
 function countdown(){
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
           
           
           function getPhoneCode(val){
          		var phoneNum = $p("#moblie").val();
	           	var sessionid=<%=request.getSession().hashCode()%>
	 		  	var data="mobiles="+phoneNum+"&sessionId="+sessionid+"&isOpen=1&messModelId=9";
		    	$p.ajax({
		    		  async:false,
		    		  url:"<%=path %>/user/sendMessageZC",
		    		  type:"GET",
		    		  data:data,
		    		  dataType:"json",
		    		  success:function(data){
		    		     if(data.result=="success"){
		    		    	 $p('#moblieTip').html("验证码发送成功");
				 				countdown();
							}
		    		  }
		    	  });
           }
      
</script>
</head>
<body onload="createCode()">
<!--头部开始-->
<div class="header">
    <jsp:include page="../header.jsp" />
</div>
<!--头部结束-->

<!--注册 开始-->
<div class="reg_content">
   <div class="reg fn_cle">
       <div class="reg_info">
             <h3><img src="../images/reg_title.png" /></h3>
          <div class="myregwrap">
          <form name="form" id="form" action="<%=path %>/user/register" method="post"  onsubmit ="getElementById('submitInput').disabled=true;return true;">
             <input type="hidden" name="sessionId" value="<%=request.getSession().hashCode()%>">
             <p> <label>邮箱：</label> <input name="mail" id="mail" type="text" size="30" style=" height:29px;"/><span id="mailTip"></span></p>
             
             <p> <label>手机号：</label> <input  id="moblie" name="moblie" type="text" onblur="getPhoneNum()" size="30" style=" height:29px;"/><span id="moblieTip"></span></p>
             
             <p> <label>用户名：</label> <input name="userName" id="userName" type="text" size="30" style=" height:29px;"/><span id="userTip"></span></p>
             <p> <label>密码：</label> <input name="password" id="password" type="password" size="30" style=" height:29px;"/><span id="pwdTip"></span></p>
             <p> <label>确认密码：</label> <input name="confirmpwd" id="confirmpwd" type="password" size="30" style=" height:29px;"/><span id="cpwdTip"></span></p>
             
             <p> <label>手机验证码：</label> <input name="PhoneCode" id="PhoneCode" type="text" size="13" style=" height:29px;"/><em><input type="button" onclick="phonenum(this);"   style="width: 107px;height:29px;" style="height:30px" value="获取验证码"  id="yanzheng" /></em><span id="sjyzmTip"></span></p>
             
             <p> <label>验证码：</label> <input name="yanzhengma" id="yanzhengma" type="text" size="13" style=" height:29px;"/><em><input type="text" onclick="createCode()" readonly="readonly" id="checkCode" class="unchanged" style="text-align:center;font-size:20px;width: 107px;height:29px;background-color:#FFFFFF"  /></em><span id="yzmTip"></span></p>
             <div class="agree">
               <div class="select_a" style="width:200px;"> <input id="checkboxi" type="checkbox" checked=true value="1" name="kpcookie" ><span style="line-height:15px; padding-left:5px;">我已阅读并同意《服务条款》</span></div>
             <span id="xyTip"></span></div>
             <div class="reg_bt"><input type="button"  onclick="submitreg()" class="reg_sub" id="submitInput" value="立即注册"/></div>
             <span class="reg_tip">已有账号？<a href="login.jsp">立即登录</a> </span> 
             <div><input name="referId" type="hidden"  value="<%=userId %>" /></div>
          </form>
        
          </div>
        </div>
       <div class="reg_img"><img src="../images/reg_img.png" /></div>
   </div>
</div>
<!--注册 结束-->
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