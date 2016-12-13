<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
 <meta name="viewport" content="width=device-width, initial-scale=1">
<title>关于</title>
</head>

<body>

	<div data-role="page" data-position="fixed">

		<div data-role="header" data-position="fixed">
			<jsp:include page="header.jsp" />
		</div>
		<div role="main" class="ui-content">
			<div class="wk_logo">
				<img src="images/mLogo.png">
			</div>
			<div class="wk_about">
<!-- 				<a class="wk_about_btn" href="aboutpage.html" data-ajax="false">公司简介</a> -->
<!-- 				<a class="wk_about_btn" href="aboutpage.html" data-ajax="false">常见问题</a> -->
				<a class="wk_about_btn last" href="contact.jsp" data-ajax="false">联系我们</a>
			</div>
		</div>
		<div data-role="footer" data-position="fixed">
			<jsp:include page="footer.jsp" />
		</div>
	</div>
</body>
</html>
