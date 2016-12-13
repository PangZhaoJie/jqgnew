<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();if(path!=null && !path.equals("")){	if(path.contains("/")){		if(path.equals("/")){path = "";	}}else{	path += "/";}} 
String basePath = "";if(request.getServerPort()==80){basePath = request.getScheme()+"://"+request.getServerName()+path+"/";}else{basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";}    
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="icon" href="<%=path %>/image/favicon/favicon.ico" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我要借款-投资详情</title>
<link href="../css/public.css" type="text/css" rel="stylesheet"/>
<link href="../css/invest.css" type="text/css" rel="stylesheet" />

<link href="../css/firm.css" type="text/css" rel="stylesheet" />
<!--导航下拉菜单 js-->
<script type="text/javascript" src="../js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="../js/comTender.js"></script>


<script type="text/javascript" src="../js/qiehuan.js"></script>


<script type="text/javascript" src="../js/scroll.js"></script>
<!--经过小图显示大图-->
<script type="text/javascript" src="../js/main.js" ></script>

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
							<span>项目名称：${sessionScope.lssuing.title}</span><strong>还款方式：${sessionScope.lssuing.returnway.returnWayName}</strong>
						</div>
						<div class="v_left_list">
							<ul>
								<li><p>年化利率</p><span>${sessionScope.lssuing.rate}%</span></li>
								<li style=" border-left:1px #dbdbdb solid; border-right:1px #dbdbdb solid;"><p>网站奖励</p><span>${sessionScope.lssuing.award}%</span></li>
								<li><p>借款期限</p><span style="color:#31c092;"><c:if test="${sessionScope.lssuing.periodtime!=null}">${sessionScope.lssuing.periodtime.periodTimeName}</c:if>
               <c:if test="${sessionScope.lssuing.periodday!=null}">${sessionScope.lssuing.periodday.periodDayName}</c:if></span></li>
							</ul>
							
						</div>
						<div class="v_con_all">
							<dl class="v_con_danbao">
								<dt>担保机构：<span>暂无担保机构</span></dt>
								<dd>100%本息保障</dd>
								<dd>起息方式：<span>即投即计息</span></dd>
								<dd>立即投资可获得<span><c:if test="${sessionScope.lssuing.periodtime!=null}">${sessionScope.lssuing.periodtime.periodTimeName}</c:if>
               						<c:if test="${sessionScope.lssuing.periodday!=null}">${sessionScope.lssuing.periodday.periodDayName}</c:if>收益</span></dd>
								<dd>每投资10万每天可获得利息<span><fmt:formatNumber type="number" value="${100000*(sessionScope.lssuing.rate/100/365) }" /> 元</span></dd>
							</dl>
							
							<ul class="v_con_rongzi">
								<li>融资金额：<fmt:formatNumber value="${sessionScope.lssuing.borrowMoney}" pattern="0.00"/>元</li>
								<li><em>融资进度：${sessionScope.bear}% </em></li>
								<li>已投金额：${ lssuing.borrowMoney -money }元</li>
								<li>还需金额：${money }元</li>
								<li>最大购买份数：<c:if test="${sessionScope.lssuing.copies ==0}">没有限制</c:if><c:if test="${sessionScope.lssuing.copies!=0}">${sessionScope.lssuing.copies}份</c:if> </li>
							</ul>
						</div>
						
						
					</div>
					<div class="v_con_right">
					<h3>我要投标</h3>
					<div class="v_con_congzhi">
						<c:if test="${uservip==null }">
						<p>您的可用余额:</p><span>0 元</span> <a href="<%=path %>/login/login.jsp">立即登录</a>
						<input type="hidden" id="isuserviplogin" value="<%=path %>/login/login.jsp" />
						</c:if>
						<c:if test="${uservip!=null}">
						<p>您的可用余额:</p><span><fmt:formatNumber type="number" value="${sessionScope.AvailableMoney}" /> 元</span><a href="<%=path%>/borrow/toChongzhi?userId=${session.uservip.userId}" style="color: blue">充值</a>
						</c:if>
					</div>
					<div class="v_con_touzi">
						<p>每份金额：${lssuing.moneymin.moneyMinName }元</p>
						<form class="v_list_touzi" action="<%=path %>/json/comAddtender.action" method="post" onsubmit="return T_PostData(${sessionScope.lssuing.lssuingId})">
							<div style="overflow:hidden;">
							<input class="v_touzi_btn" type="button" value="-" onclick="minus(${sessionScope.lssuing.lssuingId})"/>
							<input class="v_touzi_text" type="text" name="" id="tnum_${sessionScope.lssuing.lssuingId}" value="1份" />
							<input type="hidden" name="bugnum" id="buynum" value="1" />
							<input  type="hidden" name="lssuingId" value="${sessionScope.lssuing.lssuingId}" id="lssuingId"/>
							<input class="v_touzi_btnt" type="button" value="+" onclick="plus(${sessionScope.lssuing.lssuingId})" />
							</div>
							<div style="margin-top:10px;overflow:hidden;"><label class="title" style="line-height:33px;">支付密码</label> <input type="password"   size="25" name="paypwd" id="userPaypwd" /></div>
							<input id="path" type="hidden" value="<%=basePath%>" />
							<c:if test="${sessionScope.lssuing.copies ==0}"><input type="hidden" id="T_transfer_num" value="0"/></c:if>
							<c:if test="${sessionScope.lssuing.copies !=0}"><input type="hidden" id="T_transfer_num" value="${sessionScope.lssuing.copies}"/></c:if>
							<input type="hidden"  value="${sessionScope.lssuing.isOrient}"  id="passwordnone" ></input>
						    <input type="hidden"  value="${sessionScope.moneyMin}"  id="minIDnone" ></input>
						    <input type="hidden"  value="${sessionScope.moneyMax}"  id="maxIDnone" ></input>
						    <input type="hidden"  value="<fmt:formatNumber value="${sessionScope.AvailableMoney}" pattern="0.00"/> "  id="moneyIDnone" ></input>
						 	<input type="hidden"  value="${sessionScope.lssuing.orientPassword}"  id="opsdIDnone" ></input>
						 	<input type="hidden"  value="${sessionScope.money}"  id="balanceIdnone" ></input>
						  	<input type="hidden"  value="${sessionScope.moneyLimit}"  id="moneyLimitIdnone" ></input>
						  	<input type="hidden"  value="${sessionScope.lssuing.moneyLimit}"  id="lmoneyLimitIdnone" ></input>
						    <input type="hidden"  value="${sessionScope.lssuing.tenderLimit}"  id="tenderLimit" ></input>
							<input type="hidden"  value="${sessionScope.uservip}"  id="uservip" ></input>
						    <input type="hidden"  value="${sessionScope.lssuing.state}"  id="state" ></input>
							<input class="v_touzi_link" type="submit" id="v_touzi_link" value="立即投资" />
						</form>						
					</div>
					
				</div>
				</div>
				
				<div class="v_about">
					<ul>
						<li><a class="qiehuan" href="javascript:;">借款方商业概述</a></li>
						<li> <a class="qiehuan" href="javascript:;">借款方资产情况</a></li>
						<li><a class="qiehuan" href="javascript:;">借款方资金用途</a></li>
						<li><a class="qiehuan" href="javascript:;">风险控制措施</a></li>
						<li><a class="qiehuan" href="javascript:;">借款方相关资料</a></li>
					</ul>
					
					
					<div class="v_about_text">
					 <p>${lssuing.lssuingComp.compInfo} </p>
						
					</div>
					
						<div class="v_about_text">
						
						<p>${lssuing.lssuingComp.compFund} </p>
						
					</div>
					
						<div class="v_about_text">
						
						<p>${lssuing.lssuingComp.borrowUse} </p>
						
					</div>
					
					
						<div class="v_about_text">
						
						<p>${lssuing.lssuingComp.compWinCon} </p>
						
					</div>
					
					
						<div class="v_about_text">
						
						<p>
						
						</p>
						
					</div>
					
					
				</div>
			
				<div class="v_record">
					<p><a href="#">投资记录</a></p>
					
					<table border="0" align="center" cellspacing="0" cellpadding="0" id="listTable" class="tbjl_table" >
  	<tr bgcolor="#f5f5f5" height="55" style="font-size:16px;" class="header">
    <td >投标记录</td>
    <td>投标人</td>
    <td>当前利率</td>
    <td>投标金额</td>
    <td>投标时间</td>
    <td>投标状态</td>
  	</tr>
  </table>
  
 <div id="pageroot" class="page" style="display: none;" >
  	<span id="currentPage">第页</span>&nbsp; &nbsp; 
  	<span id="totalPage">共页</span>&nbsp; &nbsp; 
  	<span ><a id="firstPage" href="">首页</a></span>&nbsp; &nbsp; 
  	<span ><a id="prevPage" href="">上一页</a></span>&nbsp; &nbsp; 
  	<span ><a id="nextPage" href="">下一页</a></span>&nbsp; &nbsp; 
  	<span ><a id="lastPage" href="">最后一页</a></span>&nbsp; &nbsp; 
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