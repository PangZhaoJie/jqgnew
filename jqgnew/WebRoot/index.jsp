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
<meta name="baidu-site-verification" content="0vnsvai7q3" />
<title>${websiteIndex.homeTitle}</title>
<!--<meta name="keywords" content="${websiteIndex.webKeyword}" />
<meta name="description"  content="${websiteIndex.webDeescription}" />-->
<meta name="keywords" content="投资理财,医疗贷款,小额贷款,骨科贷款，医疗P2P，医疗金融，医疗投资，医疗理财，互联网金融，投资医疗，上海交通大学互联网金融副会长单位" />
<meta name="description" content="招米贷是中国医疗金融理财开创者,首家专注于医疗行业网络金融与小额贷款的稳健高收益理财平台。具有最专业的的风控团队的上海交通大学互联网金融副会长单位。" />

<!--导航下拉菜单 js-->
<script type="text/javascript" src="home/js/jquery-1.8.2.min.js"></script>
<!--banner js-->
<script type="text/javascript" src="home/js/jquery.SuperSlide.2.1.1.js"></script>
<script src="<%=path %>/ZMD/js/index.js" type="text/javascript"
	charset="utf-8"></script>
<link rel="stylesheet" href="<%=path%>/ZMD/css/index.css" type="text/css" />
<link rel="stylesheet" href="<%=path%>/ZMD/css/base.css" type="text/css" />
<!-- 新加样式的css -->
<link rel="stylesheet" href="<%=path%>/ZMD/css/suspend.css" type="text/css" />

</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="wrap">
		<div class="content">
			<!--banner-->
			<div class="banner">
				<div class="full_banner">
					<div class="bd">
						<ul id="fontzoom">
							<c:forEach var="latestnewssIndex" items="${latestnewssIndex}">
								<li
									style="background:url(<%=path %>${latestnewssIndex.photo}) #56C1ED center center no-repeat;">
									<a target="_blank" href="http://${latestnewssIndex.url}"> </a>
								</li>
							</c:forEach>
						</ul>
					</div>
					<!-- 漂浮窗-->
					<div class="pd">
							<ul>
									<li>
										<a class="zhuce" href="<%=path %>/login/reg1.jsp?long">注册领红包</a>
									</li>
									<li>
									
									<c:if test="${empty uservip}">
										<a class="denglu" href="<%=path %>/login/login.jsp">立即登录</a>			
									</c:if>
									<c:if test="${!empty uservip}">
										<a class="denglu" href="<%=path %>/user/login">您好，${uservip.userName}</a>
									</c:if>
									
									</li>
							</ul>
					</div>
					
					<div class="hd">
						<ul></ul>
					</div>
				</div>
			</div>

			<!--优势-->
			<dl class="v_strength">
				<dt>
					<div class="bg1 v_strength_ewm">
						<p class="v_fl">
							<span>手机理财更方便</span> 投资有风险&nbsp;理财需谨慎 <b>扫一扫</b>
						</p>
						<img style="width:126px;heighe:127px;" class="v_fl" src="<%=path%>/ZMD/images/v_ewm2.png" alt="二维码" />
					</div>
					<div class="bg1 v_strength_gongao">
						<p>
							<span class="v_fl">公告</span><a class="v_fr"
								href="<%=path%>/news/new?flag=notice">更多</a>
						</p>
						<ul class="v_news_con2">
							<c:forEach var="latestnews5Index" items="${latestnewss5Index}"	end="0">
									<li>
										<a style="color: red;" 	href="<%=path%>/news/newdetail?newsId=${latestnews5Index.newsId}" title="${latestnews5Index.title}">${latestnews5Index.title}</a>
										<span><fmt:formatDate value="${latestnews5Index.publishTime}" pattern="yyyy-MM-dd" /></span>
									</li>
								</c:forEach>
							<c:forEach var="latestnews5Index" items="${latestnewss5Index}" begin="1"	end="2">
								<li>
									<a	href="<%=path%>/news/newdetail?newsId=${latestnews5Index.newsId}" title="${latestnews5Index.title}">${latestnews5Index.title}</a>
									<span><fmt:formatDate value="${latestnews5Index.publishTime}" pattern="yyyy-MM-dd" /></span>
								</li>
							</c:forEach>
						</ul>
					</div>
				</dt>
				<dd style="border-left: 1px solid #ddd;">
					<span>投资门槛低</span> <img src="<%=path%>/ZMD/images/v_duibi.png"
						alt="高收益  低门槛" />
					<p>100元起投&nbsp;最高10%预期年化收益率</p>
					<p>投资项目类型丰富流动性高</p>
				</dd>
				<dd style="border-top-color:#ff6803">
					<span>高收益</span> <img src="<%=path%>/ZMD/images/v_duibi_b.png"
						alt="高收益  低门槛" />
					<p>费率低&nbsp;额度高</p>
					<p>期限灵活&nbsp;放款快</p>
				</dd>
				<dd style="border-top-color:#77b716;">
					<span>安全保障</span> <img src="<%=path%>/ZMD/images/v_duibi_c.png"
						alt="安全保障" />
					<p>多种保障措施</p>
					<p>让您投资更安全</p>
				</dd>
			</dl>
			<!--数据统计-->
			<ul class="v_data">
				<li>投资人数 <%-- <p>${UservipSize}人</p> --%>
					<p>24128人</p></li>
				<li style="text-align: left;"><p style="padding: 0px;font-size: 20px;padding-left: 75px;">线上交易金额：￥1.02亿</p> <%-- <p>￥<fmt:formatNumber value="${websiteIndex.investment}" pattern="0.00" /> </p> --%>
					<p style="font-size: 20px;padding-left: 75px;">线下交易金额：￥13.01亿</p></li>
				<li>总待回收金额 <%-- <p>￥<fmt:formatNumber value="${websiteIndex.loan }" pattern="0.00" /></p> --%>
					<p>￥13.5275亿</p></li>
			</ul>
			<!--投资列表-->
			<div class="lanmu">
				<div class="lanmu-title clearfix">
					新手专项 <a href="<%=path%>/tender/totenderList">更多&gt;&gt;</a>
				</div>
				<div class="lanmubox clearfix">
					<div class="lanmuimg">
						<a class="clearfix" href="<%=path%>/tender/totenderList"> <span>新人专享</span>
							<span id="arrow"></span> </a>

						<p>专享收益</p>

						<p>短期稳赢</p>

						<p>快速跟进</p>

						<span class="lanmu01"></span>

						<div class="lanmubox-s"></div>
					</div>
					<ul class="lanmu-card clearfix">
					
						<c:forEach var="newLs" items="${newLssing}" begin="0" end="2">

							<li>
								<div class="lanmu-card-index">
									<div class="lanmu-card-index-title clearfix">
										<p>体验</p>

										<h3>
											<a target="_blank"
												href="<%=path%>/tender/totender.action?lssuingId=${newLs.lssuingId }">
												${newLs.title }</a>
										</h3>
									</div>
									<p class="lanmu-card-index-cont">新手首投专享</p>

									<p class="lanmu-card-index-zhu">新手专享，体验理财</p>
									<ul class="lanmu-card-index-numbox">
									<li>
										<div class="lanmu-card-index-baifenbi clearfix" style="margin-bottom: 0px;margin-top: 0px;">
										<div class="lanmu-card-index-baifenbibox">

											<p class="js-jidu" green=""
												style="width: ${newLs.scale }%;"></p>	
										</div>&nbsp&nbsp
									${newLs.scale
											}%
									</div>
									</li>
										<li class="clearfix"><span><em>投资金额(元)</em>
										</span>

											<h3>
												<fmt:formatNumber value="${newLs.borrowMoney}"
													pattern="0.00" />
											</h3></li>
										<li class="clearfix"><span>年化收益率</span>

											<h2>${newLs.rates}%</h2></li>
										<li class="clearfix"><span>投资期限</span>

											<div class="lanmu-card-index-numbox-day clearfix">
												<c:if test="${newLs.periodtime!=null}">
													<p>${newLs.periodtime.periodTimeName}</p>
												</c:if>
												<c:if test="${newLs.periodday!=null}">
													<p>${newLs.periodday.periodDayName}</p>
												</c:if>
											</div></li>
									</ul>



									<c:if test="${newLs.state == '2' }">

										<button
											onclick="javascript:window.location.href='<%=path%>/tender/totender.action?lssuingId=${newLs.lssuingId }'">立即投资</button>
									</c:if>
									<c:if test="${newLs.state == '3' }">

										<button
											onclick="javascript:window.location.href='<%=path%>/tender/totender.action?lssuingId=${newLs.lssuingId }'">还款中</button>
									</c:if>
									<div class="small-sign blue">新</div>


								</div></li>
						</c:forEach>
					
					<!-- -->
					
					
						<%-- <c:forEach var="newLs" items="${newLssing}" begin="2" end="2">


							<li>
								<div class="lanmu-card-index">
									<div class="lanmu-card-index-title clearfix">
										<p style="background-color: #ec5655;">高息</p>

										<h3>
											<a target="_blank"
												href="<%=path%>/tender/totender.action?lssuingId=${newLs.lssuingId }">
												${newLs.title }</a>
										</h3>
									</div>
									<p class="lanmu-card-index-cont" style="width: 140px;background-color: #ec5655;color: #fff;">双11投资领红包</p>

									<p class="lanmu-card-index-zhu">双11狂欢高息标</p>
									<ul class="lanmu-card-index-numbox">
									<li>
										<div class="lanmu-card-index-baifenbi clearfix" style="margin-bottom: 0px;margin-top: 0px;">
										<div class="lanmu-card-index-baifenbibox">

											<p class="js-jidu" green=""
												style="width: ${newLs.scale }%;"></p>	
										</div>&nbsp&nbsp
									${newLs.scale
											}%
									</div>
									</li>
										<li class="clearfix"><span><em>投资金额(元)</em>
										</span>

											<h3>
												<fmt:formatNumber value="${newLs.borrowMoney}"
													pattern="0.00" />
											</h3></li>
										<li class="clearfix"><span>年化收益率</span>

											<h2>${newLs.rates}%</h2></li>
										<li class="clearfix"><span>投资期限</span>

											<div class="lanmu-card-index-numbox-day clearfix">
												<c:if test="${newLs.periodtime!=null}">
													<p>${newLs.periodtime.periodTimeName}</p>
												</c:if>
												<c:if test="${newLs.periodday!=null}">
													<p>${newLs.periodday.periodDayName}</p>
												</c:if>
											</div></li>
									</ul>



									<c:if test="${newLs.state == '2' }">

										<button
											onclick="javascript:window.location.href='<%=path%>/tender/totender.action?lssuingId=${newLs.lssuingId }'">立即投资</button>
									</c:if>
									<c:if test="${newLs.state == '3' }">

										<button
											onclick="javascript:window.location.href='<%=path%>/tender/totender.action?lssuingId=${newLs.lssuingId }'">还款中</button>
									</c:if>
									  <!-- <div class="small-sign blue">双11</div>-->
									 <div class="small-sign1 blue">双11</div>
								</div></li>
						</c:forEach>
					--%>
					<!-- ------  -->
						

					</ul>
				</div>
				<div class="lanmu-title clearfix">
					理财通道 <a href="<%=path%>/tender/totenderList">更多&gt;&gt;</a>
				</div>
				<div class="lanmubox clearfix">
					<div class="lanmuimg licais">
						<a class="clearfix" href="<%=path%>/tender/totenderList"> <span>理财通道</span>
							<span id="arrow"></span> </a>

						<p>产品丰富</p>

						<p>期限多样</p>

						<p>透明公开</p>

						<span class="lanmu02"></span>
						<div class="lanmubox-s"></div>
					</div>
					<ul class="lanmu-card clearfix">
						<c:forEach var="lssuing" items="${lssuingsIndex }" end="2">
							<li>
								<div class="lanmu-card-index">
									<div class="lanmu-card-index-title clearfix">
										<h3>
											<a target="_blank" href="<%=path%>/tender/totender.action?lssuingId=${lssuing.lssuingId }">
												${lssuing.title} </a>
										</h3>
									</div>
									<p class="lanmu-card-index-zhu">${lssuing.returnway.returnWayName}</p>

									<div class="lanmu-card-index-baifenbi clearfix">
										<div class="lanmu-card-index-baifenbibox">

											<p class="js-jidu" green=""
												style="width: ${lssuing.scale }%;"></p>
										</div>
										<span data-progress="0.0" class="js-jidunum">${lssuing.scale
											}%</span>
										<p>投资金额：${lssuing.borrowMoney }元</p>
									</div>
									<ul class="lanmu-card-index-numbox">
										<li class="clearfix"><span>年化收益率（%）</span>

											<h2>${lssuing.rates }</h2>
										</li>
										<li class="clearfix"><span>还款期限</span>

											<h3>
												<c:choose>
													<c:when test="${ lssuing.periodtime==null}"> 
						   		${lssuing.periodday.periodDayName }
						    </c:when>
													<c:otherwise>  
						   		${lssuing.periodtime.periodTimeName }
						    </c:otherwise>
												</c:choose>
											</h3>
										</li>
									</ul>
									<c:if test="${lssuing.state == '2' }">

										<button
											onclick="javascript:window.location.href='<%=path%>/tender/totender.action?lssuingId=${lssuing.lssuingId }'">立即投资</button>
									</c:if>
									<c:if test="${lssuing.state == '3' }">

										<button
											onclick="javascript:window.location.href='<%=path%>/tender/totender.action?lssuingId=${lssuing.lssuingId }'">还款中</button>
									</c:if>
									<div class="small-sign re">火</div>
								</div></li>

						</c:forEach>
					</ul>
				</div>
				<!-- 新增标列表 -->
					<div class="lanmu-title clearfix">
					医疗专项 <a href="<%=path%>/tender/totenderList">更多&gt;&gt;</a>
				</div>
				<div class="lanmubox clearfix">
					<div class="lanmuimg" style="background-color: #058fa4;">
						<a class="clearfix" href="<%=path%>/tender/totenderList"> <span>医疗专享</span>
							<span id="arrow"></span> </a>

						<p>医疗保障</p>

						<p>医疗互助</p>

						<p>赠送保险</p>

						<p id="yiliao"></p>

						<div class="lanmubox-s" style="background-color: #058fa4;"></div>
					</div>
					<ul class="lanmu-card clearfix">
						<c:forEach var="newLs" items="${ylLssing}">


							<li>
								<div class="lanmu-card-index">
									<div class="lanmu-card-index-title clearfix">
										<p>体验</p>

										<h3>
											<a target="_blank"
												href="<%=path%>/tender/totender.action?lssuingId=${newLs.lssuingId }">
												${newLs.title }</a>
										</h3>
									</div>
									<p class="lanmu-card-index-cont">医疗理财专享</p>

									<p class="lanmu-card-index-zhu">医疗专享，体验理财</p>
									<ul class="lanmu-card-index-numbox">
									<li>
										<div class="lanmu-card-index-baifenbi clearfix" style="margin-bottom: 0px;margin-top: 0px;">
										<div class="lanmu-card-index-baifenbibox">

											<p class="js-jidu" green=""
												style="width: ${newLs.scale }%;"></p>	
										</div>&nbsp&nbsp
									${newLs.scale
											}%
									</div>
									</li>
										<li class="clearfix"><span><em>投资金额(元)</em>
										</span>

											<h3>
												<fmt:formatNumber value="${newLs.borrowMoney}"
													pattern="0.00" />
											</h3></li>
										<li class="clearfix"><span>年化收益率</span>

											<h2>${newLs.rates}%</h2></li>
										<li class="clearfix"><span>投资期限</span>

											<div class="lanmu-card-index-numbox-day clearfix">
												<c:if test="${newLs.periodtime!=null}">
													<p>${newLs.periodtime.periodTimeName}</p>
												</c:if>
												<c:if test="${newLs.periodday!=null}">
													<p>${newLs.periodday.periodDayName}</p>
												</c:if>
											</div></li>
									</ul>



									<c:if test="${newLs.state == '2' }">

										<button
											onclick="javascript:window.location.href='<%=path%>/tender/totender.action?lssuingId=${newLs.lssuingId }'">立即投资</button>
									</c:if>
									<c:if test="${newLs.state == '3' }">

										<button
											onclick="javascript:window.location.href='<%=path%>/tender/totender.action?lssuingId=${newLs.lssuingId }'">还款中</button>
									</c:if>
									<div class="small-sign blue">医</div>


								</div></li>
						</c:forEach>

					</ul>
				</div>
			</div>
			<!--新闻及排行榜-->
			<div class="v_news">
				<ul class="v_news_box">
					<li class="v_news_1 v_news_2 bg1">
						<p>
							<span class="v_news_tit">平台公告</span><a
								href="<%=path%>/news/new?flag=notice">更多</a>
						</p>
						<ul class="v_news_con v_news_con_qiehuan">
							<c:forEach var="latestnews5Index" items="${latestnewss5Index}"
								end="5">
								<li><a
									href="<%=path%>/news/newdetail?newsId=${latestnews5Index.newsId}">${latestnews5Index.title}</a>
									<span><fmt:formatDate
											value="${latestnews5Index.publishTime}" pattern="yyyy-MM-dd" />
								</span></li>
							</c:forEach>
						</ul></li>
					<li class="v_news_1 v_news_2 bg1">
						<p>
							<span>行业新闻</span><a href="<%=path%>/news/new?flag=industry">更多</a>
						</p>
						<ul class="v_news_con v_news_con_qiehuan">
							<c:forEach var="latestnews2Index" items="${latestnewss2Index}"
								end="5">
								<li><a
									href="<%=path%>/news/newdetail?newsId=${latestnews2Index.newsId}">${latestnews2Index.title}</a>
									<span><fmt:formatDate
											value="${latestnews2Index.publishTime}" pattern="yyyy-MM-dd" />
								</span></li>
							</c:forEach>
						</ul></li>
					<li class="v_news_r bg1">
						<h4>投资排行</h4>
						<ul class="v_ranking_tit">
							<li class="v_ranking">月</li>
							<li class="v_ranking">周</li>
							<li style="border-right:none" class="v_ranking">日</li>
						</ul>
						<p style="border-bottom:1px solid #ddd; height:46px"></p>
						<ul class="v_ranking_con">
							<c:forEach var="month" items="${monthList }">
								<li><span>${month.name }</span>
									<p>
										￥
										<fmt:formatNumber value="${month.money }" pattern="0.00" />
										元
									</p></li>
							</c:forEach>
						</ul>
						<ul class="v_ranking_con">
							<c:forEach var="week" items="${weekList }">
								<li><span>${week.name }</span>
									<p>
										￥
										<fmt:formatNumber value="${week.money }" pattern="0.00" />
										元
									</p></li>
							</c:forEach>
						</ul>
						<ul class="v_ranking_con">
							<c:forEach var="day" items="${dayList }">
								<li><span>${day.name }</span>
									<p>
										￥
										<fmt:formatNumber value="${day.money }" pattern="0.00" />
										元
									</p></li>
							</c:forEach>
						</ul></li>
				</ul>
				<!--合作机构-->
				<div class="v_partner">
					<h3>合作机构</h3>
					<ul class="bg1 v_partner_con">
						<c:forEach var="f" items="${friendlink }">
							<li><a target="_blank" href="${f.link}" title="${f.name}">
									<img src="<%=path %>${f.photo }" /> </a></li>
						</c:forEach>
					</ul>
				</div>
				<!--友情链接-->
				<div class="v_link_more">
					<h3>友情链接</h3>
					<ul class="bg1">
						<c:forEach var="c" items="${confriendlinks }">
							<li><a target="_blank" href="${c.link}">${c.name}</a></li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>