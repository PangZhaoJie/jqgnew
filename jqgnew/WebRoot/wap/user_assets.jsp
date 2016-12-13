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
<title>会员中心</title>
</head>

<body>

	<div data-role="page" data-position="fixed" class="wk_fund">
		<div data-role="header" data-position="fixed">
			<jsp:include page="header.jsp" />
		</div>
		<div role="main" class="ui-content" >
			<div class="head">
				<div class="top">
					<a>总资产（元）</a>
					<p>${moneycount.totalMoney }</p>
				</div>
				<div class="ui-grid-a">
					<div class="ui-block-a">
						<div class="ui-bar ui-bar-a">
							<a>可用余额（元）</a>
							<p><fmt:formatNumber value="${moneycount.availableMoney}" pattern="0.00" /></p>
						</div>
					</div>
					<div class="ui-block-b">
						<div class="ui-bar ui-bar-a">
							<a>冻结金额（元）</a>
							<p><fmt:formatNumber value="${moneycount.frozenMoney}" pattern="0.00" /></p>
						</div>
					</div>
				</div>
				<!-- /grid-a -->
			</div>
			<div class="main">
				<a href="<%=path %>/wap/fundhistory" class="one" data-ajax="false">资金记录</a> <a
					href="<%=path %>/wap/myCard" class="two" data-ajax="false">我的银行卡</a>
			</div>

		</div>
		
	</div>
</body>
</html>
