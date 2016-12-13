<%@page import="com.jqg.service.TenderService"%>
<%@page import="com.jqg.service.impl.TenderServiceImpl"%>
<%@page import="com.jqg.pojo.Tender"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page import="com.jqg.pojo.Lssuing"%>
<%@page import="java.util.List"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="icon" href="<%=path%>/image/favicon/favicon.ico"
	type="image/x-icon" />
<title>${websiteIndex.homeTitle}</title>
<meta name="Keywords" content="${websiteIndex.webKeyword}" />
<meta name="descripton" content="${websiteIndex.webDeescription}" />
<link href="css/public2.css" type="text/css" rel="stylesheet" />
<link href="css/index.css" type="text/css" rel="stylesheet" />
<link href="<%=basePath%>/css/reglogin.css" type="text/css"
	rel="stylesheet" />
<!--导航下拉菜单 js-->
<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<!--banner js-->

<script type="text/javascript" src="js/jquery.flexslider-min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/login/login.js"></script>
<!--右侧浮动 js-->
<%-- <script type="text/javascript" src="js/jquery.service.js"></script> --%>
<%-- <script type="text/javascript">$(function(){$("#hhService").fix({float:'left',minStatue:false,skin:'green',durationTime:500})});</script> --%>
<script type="text/javascript" src="../js/login/register.js"></script>

<script type="text/javascript">
	function check() {
		var inputCode = $p("#get_yaz").val();
		if (inputCode.length <= 0) {
			$p("#yzmTip").html("<span style='font-size:12px;color:red'>请输入验证码</span>");
			return false;
		} else if (inputCode.toLowerCase() != code.toLowerCase()) {
			$p("#yzmTip").html("<span style='font-size:12px;color:red'>验证码输入错误</span>");
			return false;
		}else{
			$p("#yzmTip").html("<span style='font-size:12px;color:green'></span>");
			return true;
		}
	}
	
	 function checkMail() {
	 			
            	 var mail=$p("#moblie").val();
            	 if(mail.indexOf("@")!=-1){
            	 
                 if (!mail.match(/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/)) {
                        $p("#mailTip").html("<span style='font-size:12px;color:red'>请填写真实的电子邮件</span>");
                   }else{
                   $p("#mailTip").html("");//通过
                   //邮箱是否存在
	                   $p.ajax({
	             		  async:false,
	             		  url:"/user/findmail",
	             		  type:"GET",
	             		  data:"mail="+mail,
	             		  dataType:"json",
	             		  success:function(data){
	             		     var member=eval("("+data+")");
	             		     var result=member.result;
	             		     if(result=="1"){
	             		    	 $p("#mailTip").html("<span style='font-size:12px;color:red'></span>");
	             		    	 flag = "1";
	             		  	 }
	             		  	 if(result=="0"){
	             		  		 flag="2";
	             		  		 $p("#mailTip").html("<span style='font-size:12px;color:green'>请填写注册时的邮箱</span>");
	             		  	 }
	             		  }
	             	  });
                   
               }
               }else{
               
		var phoneNum = mail;
		if(phoneNum.length!=11){
	        $p("#mailTip").html("<span style='font-size:12px;color:red'>手机号长度必须为11位</span>");
		}else{
		$p("#mailTip").html("");//通过
            	    //手机号是否存在
	            	    $p.ajax({
	              		  async:false,
	              		  url:"/user/findphone",
	              		  type:"GET",
	              		  data:"phoneNum="+phoneNum,
	              		  dataType:"json",
	              		  success:function(data){
	              		     var member=eval("("+data+")");
	              		     var result=member.result;
	              		     if(result=="1"){
	              		    	 flag1="1";
	              		    	 $p("#mailTip").html("<span style='font-size:12px;color:red'></span>");
	              		  	 }
	              		  	 if(result=="0"){
	              		  		flag1="2";
	              		  		 $p("#mailTip").html("<span style='font-size:12px;color:red'>请填写注册时的手机号</span>");
	              		  	 }
	              		  }
	              	  });
		}
               }
 			}
 		function submitnext(){
 			var boo=0;
 			var inputCode = $p("#get_yaz").val();
 			var mail=$p("#moblie").val();
 			if((mail==null || mail=="")&& (inputCode==null || inputCode=="") ){
 				alert("请输入您绑定的手机号或邮箱");
 				return;
 			}
            	 if(mail.indexOf("@")!=-1){
            	 
                 if (!mail.match(/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/)) {
                        	alert("请填写真实的电子邮件");
                        	return;
                   }else{
                   //邮箱是否存在
	                   $p.ajax({
	             		  async:false,
	             		  url:"/user/findmail",
	             		  type:"GET",
	             		  data:"mail="+mail,
	             		  dataType:"json",
	             		  success:function(data){
	             		     var member=eval("("+data+")");
	             		     var result=member.result;
	             		     if(result=="1"){
	             		    	 flag = "1";
	             		  	 }
	             		  	 if(result=="0"){
	             		  		 flag="2";
	             		  		 alert("请填写注册时的邮箱");
	             		  		 boo=1;
	             		  		 return;
	             		  	 }
	             		  }
	             	  });
                   
               }
               }else{
               
		var phoneNum = mail;
		if(phoneNum.length!=11){
	        alert("手机号长度必须为11位");
	        return;
		}else{
            	    //手机号是否存在
	            	    $p.ajax({
	              		  async:false,
	              		  url:"/user/findphone",
	              		  type:"GET",
	              		  data:"phoneNum="+phoneNum,
	              		  dataType:"json",
	              		  success:function(data){
	              		     var member=eval("("+data+")");
	              		     var result=member.result;
	              		     if(result=="1"){
	              		    	 flag1="1";
	              		  	 }
	              		  	 if(result=="0"){
	              		  		flag1="2";
	              		  		 alert("请填写注册时的手机号");
	              		  		 boo=1;
	              		  		 return;
	              		  	 }
	              		  }
	              	  });
		}
               }
 		
		if (inputCode.length <= 0) {
			alert("请输入验证码");
			return false;
		} else if (inputCode.length!=4) {
			alert("请输入正确的验证码");
			return false;
		}
		if(boo==0){
		
			document.getElementById("form").submit();
		}
 			
 			
 		}
</script>
<style>
body {
	background: #F3F4F8;
}

.getpwd_box {
	margin: 40px auto 0;
	width: 1108px;
	border: 1px solid #E9EAEE;
	background: #fff;;
}

.title_name {
	line-height: 80px;
	border-bottom: 1px dashed #D9DADC;
	font-size: 22px;
	color: #191B2A;
	text-indent: 50px;
	font-family: "微软雅黑";
}

.base_style {
	height: 38px;
	line-height: 38px;
	border: 1px solid #DCDCDC;
	border-radius: 2px;
	font-size: 14px;
	color: #95A3A3;
	font-family: "微软雅黑";
	text-indent: 35px;
}

#moblie {
	width: 348px;
	margin-bottom: 15px;
	background: url("img/reg_min_pic.png") -10px -10px no-repeat;
}

#get_yaz {
	width: 200px;
	background: url("img/reg_min_pic.png") -10px -58px no-repeat;
}

.action_box {
	width: 355px;
	margin: 70px auto 0;
}

#next_btn {
	width: 38px;
	height: 38px;
	height: 40px;
	line-height: 40px;
	background: #DFC645;
	color: #fff;
	border: none;
	width: 350px;
	margin: 30px auto 148px;
	text-align: center;
	border-radius: 2px;
	font-size: 14px;
	font-family: "微软雅黑";
}

.action_box h3 {
	font-weight: normal;
	color: #1B2433;
	font-family: "微软雅黑";
	font-size: 18px;
	line-height: 90px;
	text-align: center;
}

#yzm_pic {
	width: 136px;
	height: 42px;
	left: 110px;
	position: relative;
	top: -40px;
	background: url(img/reg.fw.png) no-repeat;
}

#yzm_pic img {
	height: 38px;
	margin: 5.5px 0 0 2px;
}
#mailTip,#yzmTip{  display: block;left: -215px;position: relative;top: 20px;}
</style>
</head>
<body onload="createCode()">
	<!--头部开始-->
	<div class="header">

		<jsp:include page="header.jsp" />
	</div>
	<!--头部结束-->


	<div class="getpwd_box">
		<div class="title_name">找回密码</div>
		<div class="action_box">
			<h3>请输入您绑定**账号的手机号码或邮箱</h3>
			<form action="<%=path%>/user/send.action" id="form" >
				<input id="moblie" name="mobiles" class="base_style" onblur="checkMail();"
					placeholder="请输入注册时的手机号码或邮箱" /> 
					<input type="text" name="yanzhengma" class="base_style" placeholder="输入校验码" onBlur="check();"
					id="get_yaz" />
				<div id="yzm_pic">
<!--					<input type="text" onclick="createCode()"  readonly="readonly"-->
<!--						id="checkCode" class="unchanged"-->
<!--						style="text-align:center;font-size:20px;margin:5px;width: 77px;height:29px;background-color:#FFFFFF" />-->
						<em><a href="javascript:changeimg()"><img id="code" src="<%=basePath %>/login/makeCode.jsp" style="height:30px;"></a></em>
						</em>
						<span id="mailTip"></span>
						<span id="yzmTip"></span>
						
				</div>
				<input type="button" value="下一步"  id="next_btn"  onclick="submitnext();">
			</form>
		</div>
	</div>




	<!--footer 开始-->
	<div class="footer">
		<jsp:include page="footer.jsp" />
	</div>
	<!--footer 结束-->


	<!--右边漂浮 开始-->
	<!-- <div id="service"> -->
	<!-- 	<a href="<%=path%>/tool/borrow_tool.jsp" class="srvLog"   title="网贷计算器">网贷计算器</a> -->
	<!-- 	<a href="tencent://message/?uin=285772998&Site=企业网站&Menu=yes" class="srvCns" title="官方交流群">官方交流群</a> -->
	<!-- 	<a href="<%=path%>/tender/totenderList" class="srvDj"   title="投资理财">投资理财</a> -->
	<!-- 	<a class="goTop" id="goTop" title="返回顶部" style="display:none" >返回顶部</a> -->
	<!-- </div> -->
	<%-- <script type="text/javascript" src="js/service.js">	</script> --%>
	<!--右边漂浮 结束-->

	<!--在线客服代码 开始-->
	<!-- <div class="scrollsidebar" id="hhService"> -->
	<!-- 	<div class="side_content"> -->
	<!-- 		<div class="side_list"> -->
	<!-- 			<div class="side_title"> -->
	<!-- 				<a title="隐藏" class="close_btn"><span>关闭</span></a></div> -->
	<!-- 			<div class="side_center"> -->
	<!-- 				<div class="qqserver"> -->
	<!-- 				   <c:forEach var="kf" items="${customerqqs}"> -->
	<!-- 					<p> -->
	<!-- 					  <a title="点击这里给我发消息"  href="tencent://message/?uin=${kf.customerQqsnumber}&Site=qq&Menu=yes">   -->
	<!--  					<img src="http://wpa.qq.com/pa?p=1:${kf.customerQqsnumber}:51"/>${kf.customerQqsname}</a></p> -->
	<!-- 					</c:forEach>  -->

	<!-- 				</div> -->

	<!--                 <div class="msgserver"> -->
	<!-- 					<p>咨询热线：<br /><strong>0371-8888888</strong></p> -->
	<!-- 				</div> -->
	<!-- 			</div> -->
	<!-- 			<div class="side_bottom"> -->
	<!-- 			</div> -->
	<!-- 		</div> -->
	<!-- 	</div> -->
	<!-- 	<div class="show_btn"><span>在线客服</span></div> -->
	<!-- </div> -->
	<!-- 代码 结束 -->
<script type="text/javascript">
function changeimg(){
	var myimg = document.getElementById("code"); 
	now = new Date(); 
	myimg.src="<%=basePath %>/login/makeCode.jsp?code="+now.getTime();
} 
</script>
</body>
</html>