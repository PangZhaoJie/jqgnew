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
<script>
//	$(window).load(function() {
//		$('.flexslider').flexslider({
//			directionNav : false,
//			pauseOnAction : false
//		});
//	});
	function validate(){
		 var yzm=document.getElementById("yanzhengma").value;
        if(yzm.length <=0)   
       {   
           document.getElementById("number").style.display="";
           return;
       } 
	}
	function again(){
	countdown();
	 var  $n=jQuery.noConflict();
    document.getElementById("yanzheng").setAttribute("disabled", true);
    	var oldMobiles=document.getElementById("oldMobiles").value;
   		var imgCode=document.getElementById("imgCode").value;
    	if(imgCode==''||imgCode==null){
    		document.getElementById("imgNumber").style.display="";
    	}
    	var data="mobiles="+oldMobiles+"&yanzhengma="+imgCode+"&flg=1";
    	$n.ajax({
    		  async:false,
    		  url:"<%=path %>/user/send2",
    		  type:"GET",
    		  data:data,
    		  dataType:"json",
    		  success:function(data){
	    		     if(data=="success"){
	    		      document.getElementById("imgNumberError").style.display="none";
	    		      document.getElementById("timeout").style.display="none";
    		         }else if(data=="imgNumberError"){
    		         	document.getElementById("imgNumberError").style.display="";
    		         	document.getElementById("timeout").style.display="none";
    		         	changeimg();
    		         	return;
    		         }else if(data=="timeout"){
    		         	document.getElementById("imgNumberError").style.display="none";
    		         	document.getElementById("timeout").style.display="";
    		         	changeimg();
    		         	return;
    		         }else{
    		         	document.getElementById("yanzheng").removeAttribute("disabled");
    		         	changeimg();
    		         	return;
    		         }
					}
    	  });
    }
 var timeout=60;              
 function countdown(){
 document.getElementById("yanzheng").removeAttribute("disabled");
	if (timeout == 0)
		{
			document.getElementById("yanzheng").value = "重新发送";
		 	 document.getElementById("yanzheng").removeAttribute("disabled");;
			timeout=60;
		}
		else
		{
		document.getElementById("yanzheng").value =  timeout+'s';
		document.getElementById("yanzheng").setAttribute("disabled",true);
		setTimeout('countdown()',1000);
	}
	timeout--;
}
</script>
<style>
/*公用样式*/
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
	width: 400px;
	margin: 148px auto 0;
}

<!--#yanzhengma {-->
<!--	width: 215px;-->
<!--	margin: 15px auto;-->
<!--	background: url("img/eye_back.fw.png") 0 0 no-repeat;-->
<!--	float: left;-->
<!--}-->

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
</style>
</head>
<body >
	<!--头部开始-->
	<div class="header">

		<jsp:include page="header.jsp" />
	</div>
	<!--头部结束-->

	<div class="getpwd_box">

		<div class="title_name">找回密码</div>
		<input type="hidden" id="flag" value="2"/>
		<div class="action_boxs">
			<h3>
				系统已经发送一条短信到${newMobiles }<br />&nbsp;&nbsp;请输入短信验证码
			</h3>

			<form action="<%=path%>/user/check.action">
				<input type="hidden" name="oldMobiles" value="${oldMobiles }" id="oldMobiles"/> <input
					type="hidden" name="name" value="${name }" /> <input type="hidden"
					name="id" value="${id }" />
				<div>
					<input id="imgCode" name="imgCode" class="base_style" style="height:30px;width: 130px"
						placeholder="用于重新获取短信" />&nbsp;&nbsp;
						<img id="code222" onclick="changeimg();" src="<%=basePath %>/login/makeCode.jsp"  style="height:26px;width: 60px;">&nbsp;&nbsp;
						<input style="height:30px" type="button" id="yanzheng" value="重新发送" onclick="again()">
						<em id="imgNumber"  style="display: none;color:red;">请输入图片验证码!</em>
						<em id="imgNumberError"  style="display: none;color:red;">图片验证码不正确!</em>
						<em id="timeout"  style="display: none;color:red;">短信发送间隔不到60秒!</em>
					<div class="clear"></div>
				</div>
				<br />
				<div>
					<input id="yanzhengma" name="pwdCode" class="base_style" style="height:30px;width: 280px"
						placeholder="输入验证码" onblur="validate()"/><em id="number"  style="display: none;color:red;">请输入手机验证码!</em>
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
function changeimg(){
	var myimg = document.getElementById("code222"); 
	now = new Date(); 
	myimg.src="<%=basePath %>/login/makeCode.jsp?code="+now.getTime();
} 
</script>
</body>
</html>