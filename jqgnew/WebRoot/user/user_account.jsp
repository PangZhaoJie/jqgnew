<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
<title>个人中心-账户详情</title>
<link href="<%=basePath %>/css/public.css" type="text/css" rel="stylesheet"/>
<link href="<%=basePath %>/css/user/user_public.css" type="text/css" rel="stylesheet"/>
<link href="<%=basePath %>/css/user/user_account.css" type="text/css" rel="stylesheet"/>
<!--导航下拉菜单 js-->
<script type="text/javascript" src="<%=basePath %>/js/jquery-1.8.2.min.js"></script>
 
 <script type="text/javascript">
 var $page =  jQuery.noConflict();
 function getJSONData() {
	 
 		$page("#ID1").hide();
 		$page("#ID2").show();
 
 }
 $page(function() {  
 	getJSONData();  
 });
 </script>
 
 
 <!--左侧折叠菜单-->
<script type="" src="<%=basePath %>/js/menu.js"></script>



</head>
<body> 
<!--头部开始-->
 <div class="header">
    <jsp:include page="../header.jsp" />
</div> 
<!--头部结束-->
<div class="maincontent">
  
  <div class="conbox fn_cle">
      <jsp:include page="../user/left.jsp" />
   </div>
   

   
     <div class="user_r">
         <div id="hotnews_caption">
            <ul id="h_menu">                  
                 <li class="current"onclick="secBoard('hotnews_caption','list',1);"><a href="#">资金统计</a></li>
            </ul>
         </div>
         
         <div id="hotnews_content">
           <div class="current" id="list_1">
              <P> 尊敬的用户，以下是您在线的资金统计，敬请仔细审阅 。</P>
              <table width="100%" cellspacing="0" cellpadding="0" class="zh_table"  >
                            <tbody>
                            <tr>
                                <th align="left" colspan="3" class="th1">资金存量</th>
                            </tr>
                            <tr>
                                <td class="fr">可用现金金额：</td>
                                <td class="fc blue">￥<fmt:formatNumber value="${moneycount.availableMoney}" pattern="0.00"/></td>
                                <td class="fl">（可以用来直接提现或投标的金额）</td>
                                
                            </tr>
                            <tr>
                                 <td class="fr">待收总额：</td>
                                 <td class="fc blue">￥
<!--                                  <fmt:formatNumber value="${moneycount.collectTotalMoney}" pattern="0.00"/> -->
                                 <fmt:formatNumber value="${moneycount.dueInMoney}" pattern="0.00"/>
                                 </td>
                                 <td class="fl">（已经借出，尚未回收的本金和利息总额，未扣除佣金）</td>
                               
                            </tr>
                            <tr>
                                 <td class="fr blue">冻结总额：</td>
                                 <td class="fc blue">￥<fmt:formatNumber value="${moneycount.frozenMoney}" pattern="0.00"/></td>
                                 <td class="fl blue">（包括投标冻结和提现冻结的资金总额）</td>
                               
                            </tr> 
                            <tr>
                                 <td class="fr blue">帐户资产总额：</td>
                                 <td class="fc blue">￥<fmt:formatNumber value="${moneycount.totalMoney}" pattern="0.00"/></td>
                                 <td class="fl blue">（您在平台上现有现金资产的总额）</td>
                               
                            </tr>
                             <tr>
                                <th align="left" colspan="3" class="th1">帐户资产总额 = 可用现金金额 + 待收总额 + 冻结总额</th>
                            </tr>
                            <tr height="35"></tr>
                            <tr>
                                <th align="left" colspan="3" class="th1">资金损益</th>
                            </tr>
                           <tr>
                                <td class="fr">净赚利息：</td>
                                <td class="fc blue">￥<fmt:formatNumber value="${moneycount.netEarnInterest}" pattern="0.00"/></td>
                                <td class="fl">（投资净赚的投资利息总和，已扣除佣金）</td>
                                
                            </tr>
                            <tr>
                                <td class="fr">净付利息：</td>
                                <td class="fc blue">￥<fmt:formatNumber value="${moneycount.netPayInterest}" pattern="0.00"/></td>
                                 <td class="fl">（借款支付的借款利息总和）</td>
                               
                            </tr>
                            <tr>
                                <td class="fr">支付会员认证费：</td>
                                <td class="fc blue">￥<fmt:formatNumber value="${moneycount.vipCertifiMoney}" pattern="0.00"/></td>
                                <td class="fl">（支付的从会员费及认证费用总和）</td>
                            </tr> 
                            <tr>
                                <td class="fr">借款管理费：</td>
                                <td class="fc blue">￥<fmt:formatNumber value="${moneycount.loanManageMoney}" pattern="0.00"/></td>
                                <td class="fl">（支付的从借款收取的管理费用总和）</td>
                            </tr> 
                            <tr>
                                <td class="fr">逾期及催收费用：</td>
                                <td class="fc blue">￥<fmt:formatNumber value="${moneycount.lateAndCollectMoney}" pattern="0.00"/></td>
                                <td class="fl">（支付的从借款逾期罚息及催收的费用总和）</td>
                            </tr> 
                            <tr>
                                <td class="fr">累计提现手续费：</td>
                                <td class="fc blue">￥<fmt:formatNumber value="${moneycount.accuWithdrawalMoney}" pattern="0.00"/></td>
                                <td class="fl">（支付的提现手续费总和）</td>
                            </tr> 
                            <tr>
                                <td class="fr">累计投标奖励：</td>
                                <td class="fc blue">￥<fmt:formatNumber value="${moneycount.accuBidReward}" pattern="0.00"/></td>
                                <td class="fl">（投标获得的奖励总和）</td>
                            </tr> 
                            <tr>
                                 <td class="fr">累计支付投标奖励：</td>
                               <td class="fc blue">￥<fmt:formatNumber value="${moneycount.accuPayBidReward}" pattern="0.00"/></td>
                                <td class="fl">（借款所支付的投标奖励总和）</td>
                            </tr> 
                            <tr>
                                <td class="fr">累计推广奖励：</td>
                               <td class="fc blue">￥<fmt:formatNumber value="${moneycount.accuPromoteReward}" pattern="0.00"/></td>
                                <td class="fl">（推广下线获得的奖励总和）</td>
                            </tr> 
                            <tr>
                                <td class="fr">累计线下充值奖励：</td>
                                <td class="fc blue">￥<fmt:formatNumber value="${moneycount.accuOfflineRechargeReward}" pattern="0.00"/></td>
                                <td class="fl">（线下充值获得的奖励总和）</td>
                            </tr> 
                             <tr>
                                <td class="fr">累计续投奖励：</td>
                                <td class="fc blue">￥<fmt:formatNumber value="${moneycount.accuContinueBidReward}" pattern="0.00"/></td>
                                <td class="fl">（投资回款后续投获得的奖金总和）</td>
                            </tr> 
                            
                             <tr>
                                <td class="fr">累计充值手续费：</td>
                                <td class="fc blue">￥<fmt:formatNumber value="${moneycount.accuRechargeFee}" pattern="0.00"/></td>
                                <td class="fl">（支付的充值手续费总和）</td>
                            </tr> 
                             <tr>
                                <td class="fr blue">累计盈亏总额：	</td>
                                <td class="fc blue">￥<fmt:formatNumber value="${moneycount.accuProfitAndLossMoney}" pattern="0.00"/></td>
                                <td class="fl blue">（您在平台上累计盈亏的总额）</td>
                            </tr> 
                             <tr>
                                <th align="left" colspan="3" class="th2">累计盈亏总额 = 净赚利息 – 净付利息 – 支付会员认证费 – 借款管理费 - 逾期及催收费用 - 提现手续费
– 充值手续费 + 投标奖励 - 支付投标奖励 + 推广奖励 + 线下充值奖励 + 续投奖励</th>
                            </tr>
                            <tr height="35"></tr>
                             <tr>
                                <th align="left" colspan="3" class="th1">资金流量</th>
                            </tr>
                            <tr>
                                <td class="fr">累计投资金额：</td>
                                <td class="fc blue">￥<fmt:formatNumber value="${moneycount.accuInvestMoney}" pattern="0.00"/></td>
                                <td class="fl">（注册至今，您账户借出资金总和）</td>
                                
                            </tr>
                            <tr>
                                 <td class="fr">累计借入金额：</td>
                                 <td class="fc blue">￥<fmt:formatNumber value="${moneycount.accuBorrowMoney}" pattern="0.00"/></td>
                                 <td class="fl">（注册至今，您账户借入资金总额）</td>
                               
                            </tr>
                            <tr>
                                 <td class="fr">累计充值金额：</td>
                                 <td class="fc blue">￥<fmt:formatNumber value="${moneycount.accuRechargeMoney}" pattern="0.00"/></td>
                                 <td class="fl">（注册至今，您账户累计充值总额）</td>
                               
                            </tr>
                            <tr>
                                 <td class="fr">累计提现金额：</td>
                                 <td class="fc blue">￥<fmt:formatNumber value="${moneycount.accuWidthdrawMoney}" pattern="0.00"/></td>
                                 <td class="fl">（注册至今，您账户累计提现总额）</td>
                               
                            </tr>
                            <tr>
                                 <td class="fr">累计支付佣金：</td>
                                 <td class="fc blue">￥<fmt:formatNumber value="${moneycount.accuPayCommission}" pattern="0.00"/></td>
                                 <td class="fl">（支付的佣金总和）</td>
                               
                            </tr>
                            <tr height="35"></tr>
                             <tr>
                                <th align="left" colspan="3" class="th1">资金预期</th>
                            </tr>
                            <tr>
                                <td class="fr">待收利息总额：</td>
                                <td class="fc blue">￥<fmt:formatNumber value="${moneycount.collectInterestTotalFee}" pattern="0.00"/></td>
                                <td class="fl">（已经借出，尚未回收的利息总额，未扣除佣金）</td>
                                
                            </tr>
                            <tr>
                                 <td class="fr">待付利息总额：</td>
                                 <td class="fc blue">￥<fmt:formatNumber value="${moneycount.payInterestTotalFee}" pattern="0.00"/></td>
                                 <td class="fl">（已经借入，尚未偿还的利息总额）</td>
                               
                            </tr>
                      </tbody>
                   </table>
           </div>
         
           
          
           
          <script type="text/javascript" src="<%=basePath %>/js/tabs.js" language="javascript"></script>
        </div>
        
   </div>
    
     </div>  
  </div>
</div> 

</div>
<!--footer 开始-->
 <div class="footer">
  <jsp:include page="../footer.jsp" />
</div>
<!--footer 结束-->


<!--右边漂浮 开始-->
<%-- <div id="service">
	<a href="<%=path%>/tool/borrow_tool.jsp" class="srvLog"   title="网贷计算器">网贷计算器</a>
	<a href="tencent://message/?uin=285772998&Site=企业网站&Menu=yes" class="srvCns" title="官方交流群">官方交流群</a>
	<a href="<%=path%>/tender/totenderList" class="srvDj"   title="投资理财">投资理财</a>
	<a class="goTop" id="goTop" title="返回顶部" style="display:none" >返回顶部</a>
</div>
<script type="text/javascript" src="<%=basePath %>/js/service.js">	</script> --%>
<!--右边漂浮 结束-->

</body>
</html>