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
<link href="<%=basePath%>css/public2.css" type="text/css"
	rel="stylesheet" />
<link href="<%=basePath%>css/index.css" type="text/css" rel="stylesheet" />
<link href="<%=basePath%>/css/reglogin.css" type="text/css"
	rel="stylesheet" />
<!--导航下拉菜单 js-->
<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.2.min.js"></script>
<!--banner js-->

<script type="text/javascript"
	src="<%=basePath%>js/jquery.flexslider-min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/login/login.js"></script>
<!--右侧浮动 js-->
<%-- <script type="text/javascript" src="js/jquery.service.js"></script> --%>
<%-- <script type="text/javascript">$(function(){$("#hhService").fix({float:'left',minStatue:false,skin:'green',durationTime:500})});</script> --%>
<script type="text/javascript" src="../js/login/login.js"></script>



<style>
/*公用样式*/
body {
	background: #F3F4F8;
}

.getpwd_box {
	margin: 40px auto 0;
	width: 1108px;
	border: 1px solid #E9EAEE;
	background: #fff;
	
}

.title_name {
	line-height: 80px;
	border-bottom: 1px dashed #D9DADC;
	font-size: 22px;
	color: #191B2A;
	text-indent: 50px;
	font-family: "微软雅黑";
}
#npwd,#npwd2,#npwd3,#npwd4{background:none;width:200px;margin-left:100px;display:none;color:red;margin-top:-10px;height:20px}
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
/*获取短信验证码样式*/
.action_boxs {
	width: 355px;
	margin: 148px auto 0;
}

#yanzhengma {
	width: 215px;
	margin: 15px auto;
	background: url("img/eye_back.fw.png") 0 0 no-repeat;
	float: left;
}

.action_boxs a {
	color: #a48900;
	font-family: "微软雅黑";
	font-size: 14px;
	left: 200px;
	position: relative;
	text-decoration: none;
	top: -130px;
}

.action_boxs h3 {
	font-weight: normal;
	color: #1B2433;
	font-family: "微软雅黑";
	font-size: 18px;
}

.action_boxs span {
	width: 35px;
	height: 42px;
	display: block;
	float: left;
	margin-top: 15px;
	background: #DFC645;
	border-radius: 2px;
	line-height: 42px;
	color: #fff;
}

.clear {
	clear: both;
}

#newpwd,#newpwd2,#newPayPwd,#newPayPwd2 {
    background: url("img/reg_min_pic.png") no-repeat scroll -10px -10px rgba(0, 0, 0, 0);
    margin-bottom: 15px;
    width: 250px;
    float:left;
}
.user_sty_box lable{  display: block;float: left; text-align: right; width: 100px;line-height: 38px;}
.back_title{text-align:center;font-weight:normal;line-height:40px;font-size:16px;margin-bottom:15px}
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
		<div class="action_boxs">

			<form action="<%=path%>/user/upwd.action" class="user_sty_box" method="post">
				<input type="hidden" name="oldMobiles" value="${oldMobiles }" id="username"/> 
				<input type="hidden" name="name" value="${name }" /> 
				<input type="hidden" name="id" value="${id }" />
				<div>
					<div>
						<lable>用户名：</lable>
						<lable>${name }</lable>
					</div>
					<div style="clear: both;">
						<lable>新密码：</lable>
						<lable><input type="password" id="newpwd" name="newpwd" class="base_style"  placeholder="请输入新密码" /></lable> 
					</div>
					<div style="clear: both;">
						<lable>确认新密码：</lable>
						<lable><input type="password" id="newpwd2" name="newpwd2" class="base_style" placeholder="请再次输入新密码" />
					</div>
					<div style="clear: both;">
						<lable>支付密码：</lable>
						<lable><input type="password" id="newPayPwd" name="newPayPwd" class="base_style"
						placeholder="请输入支付密码" /></lable>
					</div>
					<div style="clear: both;">
						<lable>确认支付密码：</lable>
						<lable><input type="password" id="newPayPwd2" name="newPayPwd2" class="base_style"
						placeholder="请再次输入支付密码" /></lable>
					</div>
					<span id="npwd"></span>
					<div class="clear"></div>
				</div>
				<input type="submit" value="下一步" id="next_btn">
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
 
(function()

	{

		var onewpwd=document.getElementById('newpwd');
		var onewpwd2=document.getElementById('newpwd2');
		var onewPayPwd=document.getElementById('newPayPwd');
		var onewPayPwd2=document.getElementById('newPayPwd2');
		var Omsg=document.getElementById('npwd');
		function pwdMsg(obj)
		{
			var Pwdvalue=obj.value;
			 Omsg.style.display='block';	 
			 if(Pwdvalue!='' && Pwdvalue.length>=4)
			 {
			 	Omsg.innerHTML='密码格式正确 ！';
			 }
			 else
			 {
			 	Omsg.innerHTML='密码格式错误！';
			 }
		}
		function secondPwd(obj)
		{
			 var Pwdvalue=obj.value;
			 Omsg.style.display='block';
			 if(Pwdvalue!='' && Pwdvalue==onewpwd.value)
			 {
			 	Omsg.innerHTML='密码格式正确 ！';
			 }
			 else
			 {
			 	Omsg.innerHTML='两次输入的密码不正确';
			 }
		}
		onewpwd.onblur=function()
		{
			pwdMsg(onewpwd);
		}
		onewpwd2.onblur=function()
		{
			secondPwd(onewpwd2);
		}
		onewPayPwd.onblur=function()
		{
			pwdMsg(onewPayPwd);
		}
		onewPayPwd2.onblur=function()
		{
			secondPwd(onewpwd2);
		}
	})
();
					
</script>
</body>
</html>