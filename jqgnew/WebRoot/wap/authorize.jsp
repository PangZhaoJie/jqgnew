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
<title>开通授权</title>
</head>

<body>

	<div data-role="page" data-position="fixed">

		<div data-role="header" data-position="fixed">
			<jsp:include page="header.jsp" />
		</div>
		<div role="main" class="ui-content">
			<div class="wk_about">
 				<a class="wk_about_btn" href="<%=path %>/wap/registerbind" data-ajax="false">开通托管帐号</a>
				<a class="wk_about_btn last" href="<%=path %>/wap/loanauthorize" data-ajax="false">设置授权</a>
			</div>
		</div>
		<div data-role="footer" data-position="fixed">
			<jsp:include page="footer.jsp" />
		</div>
	</div>
</body>
</html>
