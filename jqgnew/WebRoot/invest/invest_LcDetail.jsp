<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="icon" href="<%=path%>/image/favicon/favicon.ico"
	type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我要借款-投资详情</title>
<link href="../css/public.css" type="text/css" rel="stylesheet" />
<link href="../css/invest.css" type="text/css" rel="stylesheet" />

<link href="../css/firm.css" type="text/css" rel="stylesheet" />
<!--导航下拉菜单 js-->
<script type="text/javascript" src="../js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="../js/comTender.js"></script>


<script type="text/javascript" src="../js/qiehuan.js"></script>


<script type="text/javascript" src="../js/scroll.js"></script>
<!--经过小图显示大图-->
<script type="text/javascript" src="../js/main.js"></script>

</head>
<body>
	<!--头部开始-->
	<div class="header">
		<jsp:include page="../header.jsp" />
	</div>
	<div class="wrap">
		<div class="content">
			<div class="v_con_box">
				<div class="v_con_left">
					<div class="v_left_title">
						<span>${sessionScope.picture}项目名称：${sessionScope.lssuing.title}</span><strong>还款方式：${sessionScope.lssuing.returnway.returnWayName}</strong>
					</div>
					<div class="v_left_list">
						<ul>
							<li><c:if test="${sessionScope.lssuing.periodtime!=null}">
									<p>年化利率</p>
									<span>${sessionScope.lssuing.rate}%</span>
								</c:if> <c:if test="${sessionScope.lssuing.periodday!=null}">
									<p>年化利率</p>
									<span> <fmt:formatNumber type="number"
											value="${360*sessionScope.lssuing.rate }" pattern="0.00" />%</span>
								</c:if></li>
							<li><p>借款期限</p> <span style="color:#31c092;"><c:if
										test="${sessionScope.lssuing.periodtime!=null}">${sessionScope.lssuing.periodtime.periodTimeName}</c:if>
									<c:if test="${sessionScope.lssuing.periodday!=null}">${sessionScope.lssuing.periodday.periodDayName}</c:if>
							</span></li>
						</ul>

					</div>
					<div class="v_con_all">
						<dl class="v_con_danbao">
							<dt>
								担保机构：<span>暂无担保机构</span>
							</dt>
							<dd>100%本息保障</dd>
							<dd>
								起息方式：<span>即投即计息</span>
							</dd>
							<dd>
								立即投资可获得<span><c:if
										test="${sessionScope.lssuing.periodtime!=null}">${sessionScope.lssuing.periodtime.periodTimeName}</c:if>
									<c:if test="${sessionScope.lssuing.periodday!=null}">${sessionScope.lssuing.periodday.periodDayName}</c:if>收益</span>
							</dd>
							<dd>

								<c:if test="${sessionScope.lssuing.periodtime!=null}">
							每投资10万共获得利息<span><fmt:formatNumber type="number"
											value="${100000*(sessionScope.lssuing.rate/100/12)*sessionScope.lssuing.periodtime.periodTimeId }"
											pattern="0.00" /> 元</span>
								</c:if>
								<c:if test="${sessionScope.lssuing.periodday!=null}">
							每投资10万共获得利息<span><fmt:formatNumber type="number"
											value="${100000*(sessionScope.lssuing.rate/100)*sessionScope.lssuing.periodday.periodDayId }"
											pattern="0.00" /> 元</span>
								</c:if>


							</dd>
						</dl>

						<ul class="v_con_rongzi">
							<li>融资金额：<fmt:formatNumber
									value="${sessionScope.lssuing.borrowMoney}" pattern="0.00" />元</li>
							<li><em>融资进度：${sessionScope.bear}% </em></li>
							<li>已投金额：${ lssuing.borrowMoney -money }元</li>
							<li>还需金额：${money }元</li>
							<li>最大购买份数：<c:if test="${sessionScope.lssuing.copies ==0}">没有限制</c:if>
								<c:if test="${sessionScope.lssuing.copies!=0}">${sessionScope.lssuing.copies}份</c:if>
							</li>
						</ul>
					</div>


				</div>
				<div class="v_con_right">
					<h3>我要投标</h3>

					<div class="v_con_touzi">

						<form action="<%=path%>/json/lcAddtender" method="post"
							name="tender"
							onsubmit="getElementById('invest_bt').disabled=true;return true;">
							<input type="text" style="display: none;"
								value="${sessionScope.lssuing.lssuingId}" id="lssuingId"
								name="lssuingId"></input>
							<div id="moneyState">
								<label class="title">剩余金额</label><span><fmt:formatNumber
										value="${sessionScope.money}" pattern="0.00" /> </span>元
							</div>

							<s:if
								test='#session.uservip.nameResult==null || #session.uservip.nameResult==""'>
								<div style="color:red;padding-left:60px">
									<span style="font-size:20px">还未验证</span> <a
										href="<%=path%>/user/create?mark=3" style="color:red">
										[马上验证]</a>
								</div>
							</s:if>
							<div id="textState"></div>
							<s:else>
								<div id="textState" style="margin-top:10px;">
									<label class="title">投标金额</label> <input type="text" value=""
										size="24" name="money" id="tip" onclick="text()"></input> </br> </br> <label
										class="title">支付密码</label> <input type="password" size="25"
										name="paypwd" id="userPaypwd" />
								</div>
							</s:else>

							<em id="opsdId" style="display: none;color: red;">投标密码不对</em> <em
								id="moneyLimitId" style="display: none;color: red;">您的待收金额不符合要求，请投其他标</em>
							<em id="moneyID" style="display: none;color: red;">对不起余额不足<a
								href="<%=path%>/borrow/toChongzhi?userId=${session.uservip.userId}"
								style="color: blue">[请充值]</a> </em> <em id="maxId"
								style="display: none;color: red;">您输入的金额超过最高投标金额</em> <em
								id="minId" style="display: none;color: red;">您输入的金额低于最低投标金额</em>
							<em id="balanceId" style="display: none;color: red;">您输入的金额高于剩余金额</em>
							<em id="tipId" style="display: none;color: red;">投标金额不能为空</em> <em
								id="tip50Id" style="display: none;color: red;">投标金额必须大于1元钱</em>
							<em id="login" style="display: none;color: red;">请您先登录 <a
								href="<%=path%>/login/login.jsp">立即登录</a> </em>
							<div style="display: none;" id="opsd">
								<label class="title">投标密码</label> <input type="text" value=""
									size="25" name="" id="psd"></input>
							</div>
							<div id="minmaxState" style="margin-top:10px;">
								<label>最低投标金额：<fmt:formatNumber
										value="${sessionScope.moneyMin}" pattern="0.00" />元
									最高投标金额：${sessionScope.moneyMax}元</label>
							</div>

							<div class="invest_btn">
								<input type="submit" class="invest_bt" id="invest_bt"
									value="我要投资" /> <input type="button" class="invest_bt"
									id="0State" value="初审中"
									style="display: none;background: #CCCCCC;" /> <input
									type="button" class="invest_bt" id="1State" value="复审中"
									style="display: none;background: #CCCCCC;" /> <input
									type="button" class="invest_bt" id="3State" value="还款中"
									style="display: none;background: #CCCCCC;" /> <input
									type="button" class="invest_bt" id="4State" value="已完成"
									style="display: none;background: #CCCCCC;" />
							</div>
							<input type="hidden" value="${sessionScope.lssuing.state}"
								id="state"></input>
						</form>
					</div>

				</div>
			</div>

			<div class="v_about">
				<ul>
					<!-- 					<li><a class="qiehuan" href="javascript:;">借款方商业概述</a> -->
					<!-- 					</li> -->
					<!-- 					<li><a class="qiehuan" href="javascript:;">借款方资产情况</a> -->
					<!-- 					</li> -->
					<!-- 					<li><a class="qiehuan" href="javascript:;">借款方资金用途</a> -->
					<!-- 					</li> -->
					<!-- 					<li><a class="qiehuan" href="javascript:;">风险控制措施</a> -->
					<!-- 					</li> -->
					<li><a class="qiehuan" href="javascript:;">借款方相关资料</a></li>
					<li><a class="qiehuan" href="javascript:;">借款说明</a></li>
				</ul>


				<!-- 				<div class="v_about_text"> -->

				<!-- 					<p>${lussing.lssuingComp.compInfo}</p> -->

				<!-- 				</div> -->

				<!-- 				<div class="v_about_text"> -->

				<!-- 					<p>${lussing.lssuingComp.compFund}</p> -->

				<!-- 				</div> -->

				<!-- 				<div class="v_about_text"> -->

				<!-- 					<p>${lussing.lssuingComp.borrowUse}</p> -->

				<!-- 				</div> -->

			
				<div class="v_about_text">
					<c:if test="${not empty uservip.userId}">
						<c:forEach var="lssingphoto" items="${lssingphoto}">
							<!-- 					<p>${lussing.lssuingComp.compWinCon}</p> -->
							<p>
								<img src="<%=basePath%>/${lssingphoto.photo}" alt="" />
							</p>
						</c:forEach>
					</c:if>
					<c:if test="${empty uservip.userId}">
						<p style="text-align: center;">请
							<a href="<%=path %>/login/login.jsp" class="top_login">登录</a>
							或
							<a href="<%=path %>/login/reg1.jsp?long" class="top_zhuce">注册</a>	
							后查看
						</p>
					</c:if>
				</div>
				

				<div class="v_about_text">

					<p>${lssuing.explains }</p>

				</div>


			</div>

			<div class="v_record">
				<p>
					<a href="#">投资记录</a>
				</p>

				<table border="0" align="center" cellspacing="0" cellpadding="0"
					id="listTable" class="tbjl_table" style="width:1200px;">
					<tr bgcolor="#f5f5f5" height="55" style="font-size:16px;"
						class="header">
						<td>投标记录</td>
						<td>投标人</td>
						<td>当前利率</td>
						<td>投标金额</td>
						<td>投标时间</td>
						<td>投标状态</td>
					</tr>
				</table>

				<div id="pageroot" class="page" style="display: none;">
					<span id="currentPage">第页</span>&nbsp; &nbsp; <span id="totalPage">共页</span>&nbsp;
					&nbsp; <span><a id="firstPage" href="">首页</a> </span>&nbsp; &nbsp;
					<span><a id="prevPage" href="">上一页</a> </span>&nbsp; &nbsp; <span><a
						id="nextPage" href="">下一页</a> </span>&nbsp; &nbsp; <span><a
						id="lastPage" href="">最后一页</a> </span>&nbsp; &nbsp;
				</div>
			</div>



		</div>
	</div>

	<!--footer 开始-->
	<div class="footer">
		<jsp:include page="../footer.jsp" />
	</div>
	<!--footer 结束-->


</body>
</html>