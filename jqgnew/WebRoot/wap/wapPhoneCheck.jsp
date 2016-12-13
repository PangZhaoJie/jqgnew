<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="/struts-tags" prefix="s"%>
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
	String uservip = request.getParameter("uservip");
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>手机认证</title> 

</head>
<body>

<div data-role="page" data-position="fixed">

    <div data-role="header" data-position="fixed">
    <jsp:include page="header.jsp" />
</div>
         <div role="main" class="ui-content" >
        
       <form id="phone" method="post">
				<div data-role="fieldcontain" class="wk_loginmain wk_reg">
                	<p>
					<input type="text" id="phoneNum"   name="phoneNum"  class="user_back pass_back_02" placeholder="手机号"/>
					<span>
					<input type="text" id="code"  name="code"  class="user_back pass_back_02" placeholder="手机验证码"/>
					<a href="javascript:void(0);" onclick="phoneSubmit();" style="padding:10px 5px 11px 5px;color:#FFF;margin-top:-4px;border-radius:5px;border: 1px solid #cccccc;background: #4eaff0 none repeat scroll 0 0;">获取验证码</a>
					</span>

                    </p>
                   
					<input type="button" onclick="toSubmit();"  value="完成手机验证">
                       
				</div>	
			</form>
        
  	</div> 
    <div data-role="footer" data-position="fixed">  
    <jsp:include page="footer.jsp" />
  </div>
</div>    
</body>
<script type="text/javascript" src="<%=basePath%>/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript">
  /////////////////////////////////////2015/11/21  
    function toSubmit() {
    	var code = document.getElementById("code").value;
		var phoneNum=document.getElementById("phoneNum").value;
		if(phoneNum==null || phoneNum==""){
			alert("手机号不能为空");
				return false;
		}
		if(code==""||code==null){
				alert("手机验证码不能为空");
				return false;
		}
		if(phoneNum!=null && phoneNum!=""&&code!=""&&code!=null){

    	var data="phoneNum="+phoneNum+"&code="+code;
    		$.ajax({
      		  async:false,
      		  //根据手机号验证码认证手机
    		  url:"<%=path %>/wap/wapPhoneSet",
    		  type:"GET",
    		  data:data,
    		  dataType:"json",
    		  success:function(data){
    			  if(data=="success"){
    			  	  alert("认证成功！！");
    				  window.location.href="<%=path %>/wap/index";
    			  }
    			  if(data=="error"){
    				  alert("验证失败");
    			  }
    			   if(data=="repeat"){
    				  alert("手机已认证过不能重复认证！");
    			  }
    			  
    		  }
    		 
    	  });
		}
	}

function phoneSubmit(){
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
  		  url:"<%=path %>/wap/findphone",
  		  type:"GET",
  		  data:"phoneNum="+phoneNum,
  		  dataType:"json",
  		  success:function(data){
  		      if(data=="1"){
  		    	 alert("此手机号已被使用");
  		    	 boo=false;	
  		    	 return;
  		  	 }
  		  	 
  		  }
  	  });
	}
	if(boo==true){
		var data="mobiles="+phoneNum+"&isOpen=1&messModelId=9";
	$.ajax({
		  async:true,
		  url:"<%=path %>/wap/wapSendMessage",
		  type:"POST",
		  data:data,
		  dataType:"json",
		  success:function(data){
		     if(data=="success"){
		    	alert("验证码发送成功");
				}else{
					alert("发送失败");
				}
		  }
	  });
	}
}
</script>
</html>
