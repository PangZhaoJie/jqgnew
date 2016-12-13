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
	String path = request.getContextPath();if(path!=null && !path.equals("")){	if(path.contains("/")){		if(path.equals("/")){path = "";	}}else{	path += "/";}} 
String basePath = "";if(request.getServerPort()==80){basePath = request.getScheme()+"://"+request.getServerName()+path+"/";}else{basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";}  
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

<script>
	$(window).load(function() {
		$('.flexslider').flexslider({
			directionNav : false,
			pauseOnAction : false
		});
	});
</script>
<style type="text/css">
		*{margin: 0 auto;padding: 0;}
		#empower_box{width: 880px;height: 520px;background: url(images/empower.png) no-repeat center;margin-top:50px}
		.emp_text{ font-size:20px;font-family: "微软雅黑";color: #999;   line-height: 35px; padding: 218px 190px 0;text-indent: 55px;}
		.emp_text a,.emp_text a:hover{color: #55ADFF;}
	</style>
</head>
<body>
	<!--头部开始-->
	<div class="header">

		<jsp:include page="header.jsp" />
	</div>
	<!--头部结束-->
	<div id="empower_box" >
			<div class="emp_text">
				${address }您的授权已过期，请联系山东金钱柜网络科技有限<br/>公司（<a href="http://www.wangdaixitong.com">http://www.wangdaixitong.com/</a>）,</br>联系电话：0531-8888888
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
</body>
</html>