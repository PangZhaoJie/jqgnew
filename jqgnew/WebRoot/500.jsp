<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>${websiteIndex.homeTitle}</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
@charset "utf-8";
/* CSS Document */
* {
	margin: 0;
	padding: 0;
	outline: 0;
	word-wrap: break-word;
	word-break: break-all;
}

body {
	font-family: "微软雅黑", Geneva, sans-serif;
}

img {
	border: none;
}

a {
	color: #449900;
	text-decoration: none;
}

.w960 {
	width: 960px;
	height: auto;
	margin: 0 auto;
}

.clear {
	clear: both;
	overflow: hidden;
}

.logo {
	margin: 15px 0;
	width: 134px;
	height: 60px;
}

.main {
	width: 100%;
	height: 435px;
	margin-top: 91px;
	background: url(http://style.ciwong.net/2013/images/404.png) 0 0
		no-repeat;
	color: #838383;
}

.main h1 {
	line-height: 144px;
	font-size: 26px;
	font-weight: 400;
	text-indent: 285px;
	color: #B7B6B6;
}

.main h4 {
	font-size: 20px;
	padding: 50px 0 30px 110px;
	font-weight: 400;
}

.main .erro {
	font-size: 20px;
	line-height: 40px;
}

.main .erro {
	padding-top: 50px;
	padding-left: 185px;
}

.main .erro a {
	padding: 0 10px;
}

.main .erro a:hover {
	color: #ff0000;
	text-decoration: underline;
}

.goout {
	padding: 30px 0 0 260px;
	display: block;
	height: 42px;
}

.goout a {
	width: 160px;
	height: 60px;
	line-height: 58px;
	font-size: 22px;
	text-align: center;
	display: inline-block;
	background: url(http://style.ciwong.net/2013/images/404.png) -160px
		-435px no-repeat;
	margin: 0 170px 0 0;
}

.goout a:hover {
	background: url(http://style.ciwong.net/2013/images/404.png) 0 -435px
		no-repeat;
}
</style>
</head>

<body>

	<div class="w960">


		<div class="main">
			<h1>很抱歉，此页面暂时不可用。请稍后重试…</h1>
			<div class="goout">
<!-- 				<a href="javascript:history.back(-1)" title="重试">重 试</a> -->
				<a  href="<%=path %>" title="返回首页">返回首页</a>
			</div>
			<p class="erro">
				-->页面暂时不可用。<br /> 
				-->如果您无法载入任何页面，请检查您计算机的网络连接。

			</p>
		</div>

	</div>
</body>
</html>
