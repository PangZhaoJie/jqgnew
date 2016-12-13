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
		<div role="main" class="ui-content" >
			<div class="wk_about wk_contact">
				<a class="wk_about_btn">微信公众账号：金钱柜</a> <a
					class="wk_about_btn qq_btn">QQ群：291126674 </a> <a
					class="wk_about_btn last">新浪微博：@金钱柜信息技术有限公司</a> <a
					class="wk_about_btn customer" >客服热线：400-123-1234</a>
			</div>
		</div>
		<div data-role="footer" data-position="fixed">
			<jsp:include page="footer.jsp" />
		</div>
	</div>
</body>
</html>
