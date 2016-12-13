<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  <%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();if(path!=null && !path.equals("")){	if(path.contains("/")){		if(path.equals("/")){path = "";	}}else{	path += "/";}} 
String basePath = "";if(request.getServerPort()==80){basePath = request.getScheme()+"://"+request.getServerName()+path+"/";}else{basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";}    

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="icon" href="<%=path %>/image/favicon/favicon.ico" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>借款协议--${websiteinfo.webName}</title>
<!--左侧折叠菜单-->
<style>
  .Lending_Agreement {
  	width:100%;
  	height:auto;
  	margin:0px;
  	padding:0px;
  }
  .Lending_Agreement_center {
  	width:1024px;
  	height:auto;
  	margin:0 auto;
  	border:1px solid #EEEEEE;
  }
  .lending_ageeement_tit {
  	width:960px;
  	height:45px;
  	line-height:45px;
  	color:#666666;
  	font-family:Arial, Helvetica, sans-serif;
  	font-size:16px;
  	font-weight:bold;
  	text-align:center;
  	margin:0 auto;
  	margin-top:30px;
  	border-bottom:1px solid #EEEEEE;
  }
  .ageeement_content {
  	width:1000px;
  	height:auto;
  	margin:0 auto;
  	margin-top:30px;
  	font-size:14px;
  	font-weight:bold;
  	color:#666666;
  	overflow:auto;
  	font-family:Arial, Helvetica, sans-serif;
  }
  .ageeement_content h3 {
  	font-size:14px;
  	font-weight:bold;
  	color:#999999;
  	font-family:Arial, Helvetica, sans-serif;
  	padding-left:10px;
  }
  .font_lendig {
  	font-size:14px;
  	font-weight:bold;
  	color:#666666;
  	font-family:Arial, Helvetica, sans-serif;
  	padding-left:10px;
  	margin-bottom:10px;
  	display:block;
  	margin-top:20px;
  }
  .ageeement_content p {
  	line-height:26px;
  	font-family:Tahoma, Geneva, sans-serif;
  	font-size:14px;
  	color:#999999;
  	margin:0px;
  	font-weight:normal;
  	padding-left:10px;
  	margin-top:6px;
  }
  .ageement_tab {
  	height:40px;
  	line-height:40px;
  }
  .ageement_tab_ite {
  	height:40px;
  	line-height:40px;
  	font-weight:normal;
  }
  .lending_top_inf {
  	width:1000px;
  	height:auto;
  	margin-left:10px;
  }
  .lending_logo {
  	width:1000px;
  	height:100px;
  	border-bottom:2px solid #EEEEEE;
  }
  .lenging_left_logo {
  	width:252px;
  	height:100px;
  	float:left;
  	border-bottom:2px solid #00ADEE;
  }
  .name_inf {
  	width:400px;
  	height:65px;
  	float:left;
  	margin-left:40px;
  	margin-top:2px;
  }
  .lenging_right_inf {
  	width:260px;
  	height:50px;
  	float:left;
  	margin-top:9px;
  }
  .lenging_right_inf ul {
  	margin:0px;
  	padding:0px;
  }
  .lenging_right_inf ul li {
  	width:400px;
  	height:25px;
  	line-height:25px;
  	font-family:Arial, Helvetica, sans-serif;
  	font-size:14px;
  	color:#999999;
  	list-style:none;
  	float:left;
  	padding-left:0px;
  }
  .lenging_right_inf ul li a {
  	color:#999999;
  	text-decoration:none;
  }
  .lenging_right_inf ul li a:hover {
  	color:#FF9900;
  	text-decoration:none;
  }
  .Seal {
  	width:auto;
  	height:auto;
  	margin-right:20px;
  	float:right;
  	text-align:center;
  }
  .seal_text {
  	font-family:Arial, Helvetica, sans-serif;
  	font-size:13px;
  	color:#999999;
  	margin-top:6px;
  	margin-bottom:40px;
  	display:block;
  	font-weight:normal;
  }
  .Agreement_pic {
  	width:200px;
  	height:45px;
  	margin:0 auto;
  }
  a,a:visited{color: #E67714; text-decoration:none;}
</style>

<script language="javascript" type="text/javascript">
	function printht(){
		window.print();
	} 
</script>
</head>
<body> 
<!--头部开始-->
<!--头部结束-->
<div class="Lending_Agreement">
<div class="Lending_Agreement_center">
  <!--顶部信息开始-->
 
  <!--顶部信息结束-->
  <div class="lending_ageeement_tit"><span class="Agreement_pic">借款协议</span><span style="float:right; padding-right:10px;"><a target="_self" onclick="printht();" href="#">【打印合同】</a> </span></div>
  <div class="ageeement_content">
    <p align="left">合同编号：<u><strong>zmd${lssuing.lssuingNum}</strong></u> </p>
    <p align="left">甲方（投资人）：详见本协议第一条</p>
    <p align="left">乙方（借款人）：<u>${lssuing.uservip.userName}</u> </p>
    <p align="left">担保方（丙方）：<u>${websiteinfo.webName}</u> </p>
    <p align="left">生效日期：<u><fmt:formatDate value="${lssuing.verify_time}" pattern="yyyy年MM月dd日" /></u> </p>
    <p>&nbsp;</p>
    <p>借款人（乙方）通过由${websiteinfo.webName}创办的网络借贷中介平台&nbsp;&nbsp;&nbsp;（以下简称“本平台”），向本平台的注册会员借款，该借款由担保方（丙方）为借款人（乙方）提供担保。相关借款事项根据《中华人民共和国合同法》等相关法律法规的规定，甲乙丙三方达成如下协议：</p>
    <p>&nbsp;</p>
    <strong class="font_lendig">第一条&nbsp;&nbsp; 投资人投资信息</strong>
    <table width="980" cellspacing="0px" bordercolor="#EEEEEE" border="1px" style="border-collapse:collapse;margin-top:20px;margin-left:10px;">
      <tbody><tr class="ageement_tab">
        <td width="196" valign="middle" height="40" align="center">投资人</td>
        <td width="196" valign="middle" height="40" align="center">金额 </td>
        <td width="196" valign="middle" height="40" align="center">期限</td>
        <td width="196" valign="middle" height="40" align="center">年/日利率</td>
        <td width="196" valign="middle" height="40" align="center">开始计息日</td>
        <td width="196" valign="middle" height="40" align="center">还款方式</td>
        <td width="196" valign="middle" height="40" align="center">总本息</td>
      </tr>
      <tr class="ageement_tab_ite">
        <td width="196" valign="middle" height="40" align="center">${tender.uservip.userName}</td>
        <td width="196" valign="middle" height="40" align="center">${tender.money}</td>
        <td width="196" valign="middle" height="40" align="center">
		<c:if test="${!empty lssuing.periodday.periodDayName}">${lssuing.periodday.periodDayName}</c:if>
        <c:if test="${empty lssuing.periodday.periodDayName}">${lssuing.periodtime.periodTimeName}</c:if>
		</td>
		 <td width="196" valign="middle" height="40" align="center">${lssuing.rate}</td>
        <td width="196" valign="middle" height="40" align="center"><fmt:formatDate value="${lssuing.verify_time}" pattern="yyyy年MM月dd日" /></td>
		<td width="196" valign="middle" height="40" align="center">${lssuing.returnway.returnWayName}</td>
        <td width="196" valign="middle" height="40" align="center"> <fmt:formatNumber value="${sessionScope.totalMoney}" pattern="0.00"/></td>
      </tr>
    </tbody></table>
    <p>&nbsp;</p>
    <strong class="font_lendig">第二条&nbsp;&nbsp; 还款 </strong>
    <p align="left">1、还款计划：</p>
    <table width="980" cellspacing="0px" bordercolor="#EEEEEE" border="1px" style="border-collapse:collapse;margin-top:20px;margin-left:10px;">
      <tbody><tr class="ageement_tab">
        <td width="196" valign="middle" height="40" align="center">期数</td>
        <td width="196" valign="middle" height="40" align="center">金额 </td>
        <td width="196" valign="middle" height="40" align="center">本金</td>
        <td width="196" valign="middle" height="40" align="center">利息</td>
        <td width="196" valign="middle" height="40" align="center">还款日期</td>
      </tr>
	  <c:forEach var="record"  varStatus="status" items="${records }">
	  <tr class="ageement_tab_ite">
        <td width="196" valign="middle" height="40" align="center">第${ status.index + 1}/${sessionScope.recordsSize}期</td>
        <td width="196" valign="middle" height="40" align="center">
		<c:if test="${!empty record.recordInterest}">${record.recordInterest}</c:if>
		<c:if test="${empty record.recordInterest}"><fmt:formatNumber value="${record.recordMoney+record.recordRate}" pattern="0.00"/></c:if>
		</td>
        <td width="196" valign="middle" height="40" align="center">${record.recordMoney}</td>
        <td width="196" valign="middle" height="40" align="center">${record.recordRate}</td>
		<td width="196" valign="middle" height="40" align="center"><fmt:formatDate value="${record.recordDate}" pattern="yyyy年MM月dd日" /></td>
      </tr>
	  </c:forEach>
	  </tbody></table>
    <p>2、借款人承诺按照本协议以上约定的时间和金额，按期足额向投资者还款。</p>
    <p>&nbsp;</p>
    <strong class="font_lendig">第三条&nbsp;&nbsp; 借款的支付和还款方式 </strong>
    <p>&nbsp;1、甲方在同意向乙方出借相应款项时，已委托本平台在本借款协议生效时将该笔借款直接划付至借款人帐户。</p>
    <p>2、乙方在还款时已委托本平台将还款直接划付至甲方帐户。</p>
    <p>3、借款人和投资者均同意上述平台接受委托的行为所产生的法律后果均由相应委托方承担。</p>
    <p>&nbsp;</p>
    <strong class="font_lendig">第四条&nbsp;&nbsp; 担保 </strong>
    <p>1、本合同项下借款的担保方式为丙方承担连带责任的保证担保。</p>
    <p>2、丙方完全了解乙方的借款用途，为其提供连带责任的保证担保完全出于自愿，其在本合同项下的全部内容表示真实。</p>
    <p>3、保证担保的范围包括本合同项下的借款本金、利息、违约金、赔偿金、实现债权的费用（含律师费）和所有其他应付费用。</p>
    <p>4、保证期间为本合同确定的到期之次日起两年。分期还款的为本合同确定的最后一期还款期限到期之次日起两年。</p>
    <p>5、若借款人超过截止日仍未还款，则视为逾期。还款发生逾期时，每天的违约金是当天应还款项的千分之八。</p>
    <p>6、乙方逾期还款，丙方须在乙方逾期的当日内，按照本网站内借贷规则中的赔付方式对甲方（所有投资人）进行垫付本息，同时债权归丙方所有，丙方负责向乙方追讨本息、违约金等。</p>
    <p>7、丙方保证责任为独立责任，不因甲、乙方借款合同的无效而无效。</p>
    <p>&nbsp;</p>
    <strong class="font_lendig">第五条：乙方权利、义务</strong>
    <p>1、借款人可在还款截止日之前的任意时段进行提前还款。</p>
    <p>2、自觉接受甲方或丙方对本合同项下借款使用情况的调查、了解及监督。</p>
    <p>3、按本合同约定清偿本合同项下的本金、利息及违约金。</p>
    <p>4、乙方请求丙方作为担保人，为乙方基于本合同对甲方所负的全部债务承担连带责任。</p>
    <p>5、变更住所、通讯地址、号码应在变更后，应当立即书面通知甲方或丙方。</p>
    <p>6、如发生对其履行本合同项下还款义务产生重大影响的任何事件（包括但不限于离、结婚，对外投资，承担民事、行政、刑事责任等），应立即书面通知甲方或丙方。</p>
    <p>&nbsp;</p>
    <strong class="font_lendig">第六条：违约责任</strong>
    <p>1、如乙方未按本合同约定履行归还所借款项义务，丙方对逾期借款从垫付之日起按应还本金每日千分之八收取违约金，直到本息清偿为止。</p>
    <p>2、乙方有下列行为之一，甲方有权提前收回借款：</p>
    <p>（1）向甲方或者丙方提供虚假情况或者隐瞒重要事实;</p>
    <p>（2）不配合、拒绝接受甲方或者丙方的监督;</p>
    <p>（3）未经甲方或者丙方同意，转让、处分其资产;</p>
    <p>（4）其财产重要部分或全部被其他债权人占有、接管或其财产被扣押、冻结，可能使甲方或者丙方遭受严重损失的;</p>
    <p>（5）其他任何可能导致甲方或者丙方实现债权受到威胁或遭受严重损失的。</p>
    <p>&nbsp;</p>
    <strong class="font_lendig">第七条：特别条款</strong>
    <p>1、借款人不得将所借款项用于任何违法活动（包括但不限于赌博、吸毒、贩毒、卖淫嫖娼等）及一切高风险投资（如证券期货、彩票等），否则一经发现，投资者有权要求提前收回全部借款，投资者或本平台还将立即向公安等有关行政机关举报，追回此款并追究借款人的刑事责任。</p>
    <p>2、本平台仅作为该网站注册会员之间小额资金互助平台，反对一切利用本平台进行信用卡套现和其他洗钱等不正当交易行为。如发生此类现象，本平台有权向公安等有关行政机关举报，追究其相关法律责任。</p>
    <p>&nbsp;</p>
    <strong class="font_lendig">第八条：其他</strong>
    <p>1、本协议采用电子文本形式制成，并通过站内信的形式发送协议至本平台。</p>
    <p>2、本协议自借款人在本平台发布的借款标的审核成功之日起生效，借款人、投资者和担保方各执一份，并具同等法律效力。</p>
    <p>3、其他未尽事宜三方另行协商解决，协商不成，在丙方所在地人民法院通过诉讼解决。</p>
    <p>4、本平台拥有对本协议的最终解释权。</p>
	 <p>&nbsp;</p>
      <div class="Seal">
         居间方：
	  <img width="100px" height="100px" border="0" src="<%=path %>/img/gfyuy.jpg"> <br> 
        <span class="seal_text"><fmt:formatDate value="${lssuing.verify_time}" pattern="yyyy年MM月dd日" /></span> </div>
  </div>
</div>

</div>
</body>
</html>