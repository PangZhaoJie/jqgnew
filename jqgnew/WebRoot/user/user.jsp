<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@taglib prefix="s" uri="/struts-tags" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%    
	String path = request.getContextPath();if(path!=null && !path.equals("")){	if(path.contains("/")){		if(path.equals("/")){path = "";	}}else{	path += "/";}}    
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";    
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="icon" href="<%=path %>/image/favicon/favicon.ico" type="image/x-icon" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>首页</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta http-equiv="X-UA-Compatible" content="IE=9" />
		
		<meta name="Keywords" content="招米贷,投资理财平台,靠谱的理财平台,骨科贷款" />
        <meta name="description" content="招米贷是国米资产旗下的投资理财平台，是上海交通大学互联网金融副会长单位，是靠谱的网络理财平台。" />
		
		<title>招米贷|投资理财平台|靠谱的理财平台</title>
		
		
		<link href="<%=path %>/home/css/bootstrap.min.css" rel="stylesheet"/>
		<link href="<%=path %>/home/css/bootstrap-responsive.min.css" rel="stylesheet"/>
		<link href="<%=path %>/home/css/style.css" rel="stylesheet"/>
		<link href="<%=basePath%>/css/public.css" type="text/css" rel="stylesheet"/>
		<link href="<%=basePath%>/css/user/user_index.css" type="text/css" rel="stylesheet"/>
		<link href="<%=basePath%>/css/user/user_public.css" type="text/css" rel="stylesheet"/>
		<!--导航下拉菜单 js-->
		<script type="text/javascript" src="<%=basePath%>/js/jquery-1.8.2.min.js"></script>
		<!--左侧折叠菜单-->
		<script type="text/javascript" src="<%=basePath%>/js/menu.js"></script>
		<script src="<%=path %>/home/js/jquery-1.8.3.min.js" type="text/javascript"></script>
		<script src="<%=path %>/home/js/bootstrap.min.js" type="text/javascript"></script>
	</head>
	<body class="account_bg">
		<!--头部开始-->
		<div class="header">
		    <jsp:include page="../header.jsp" />
		    
		</div>
		<!--头部结束-->
		<div class="account">
			<div class="warp">
			    <jsp:include page="left.jsp"/>
			    </div>
			   
				<div class="account_right">
					<div class="everyone" style="clear:both;">
						<div class="right_title">
							<span>我的账户</span>
						</div>
						<div class="every_left">
							<ul>
								<li>
									<div class="neirong">
										<p>账户余额：
											<s:if test='moneycount.availableMoney==null || moneycount.availableMoney==""'>0.00</s:if>
             								<s:else><fmt:formatNumber value="${moneycount.availableMoney}" pattern="0.00"/></s:else>
										元</p>
										<p>冻结金额：
											<s:if test='moneycount.frozenMoney==null || moneycount.frozenMoney==""'>0.00</s:if>
             								<s:else><fmt:formatNumber value="${moneycount.frozenMoney}" pattern="0.00"/></s:else>
										元</p>
										<div class="btngroup">
											<a  class="btn" href="<%=path %>/borrow/toChongzhi?userId=${session.uservip.userId}">充值</a>
											<a  class="btn" href="<%=path %>/userbank/mention">提现</a>
										</div>
									</div>
								</li>
								<li style="border:0px;">
									<div class="neirong">
										<p>用户名：${user.userName}</p>
										<p>信用等级：<img  src="<%=basePath %>${creditlevel.creditLevelPicture}"> 级</p>
										<p>信用额度：
										<s:if test='user.quota==null || user.quota==""'>0</s:if>
             							<s:else><fmt:formatNumber value="${user.quota}" pattern="0.00"/></s:else>
										元</p>
										<p>注册时间：<fmt:formatDate value="${user.registerDate }" pattern="yyyy年MM月dd日" /> </p>
									</div>
								</li>
							</ul>
						</div>
						<div class="every_right">
							<div class="touxiang">
								<s:if test='user.photoOne==null || user.photoOne==""'>
				                    <img width="133" height="100" src="<%=basePath%>/home/img/my_pic.png">
				              </s:if>
				              <s:else>
				                    <img width="133" height="100" src="<%=basePath%>${user.photoOne}">
				              </s:else>
<!-- 								<a href="<%=path %>/user/password.jsp?mark=1" class="btn">上传头像</a> -->
							</div>
						</div>
					</div>
				
	      <div ID="yanzhengzhongxin" class="safe">
            <h3>充值验证中心</h3>
            <ul class="fn_cle">
               <li><h4>实名认证：</h4>
               <s:if test='user.nameResult=="2"'>已验证</s:if>
               <s:if test='user.nameResult=="1"'>验证中</s:if>  
               <s:if test='user.nameResult=="0"'>
               <span>验证失败</span>  
               <a href="<%=path %>/user/create?mark=3"> [重新验证]</a></s:if> 
               <s:if test='user.nameResult==null || user.nameResult==""'><span>还未验证</span>  
               <a href="<%=path %>/user/create?mark=3"> [马上验证]</a></s:if></li>
			     <s:if test='user.phoneResult=="1"'>
               <li><h4>手机号码：</h4>已验证</li>
               </s:if>
               <s:else>
               <li><h4>手机号码：</h4> <span>还未验证</span>  <a href="<%=path %>/user/create?mark=1"> [马上验证]</a></li>
               </s:else>
			    <li><h4>电子邮箱：</h4> 
               <s:if test='user.enable=="1" && (user.mail!=null || user.mail!="")'>已验证</s:if>
               <s:if test='user.enable==null || user.enable=="" || user.mail==null || user.mail==""'><span>还未验证</span>
               <a href="<%=path %>/user/create?mark=2">[马上验证]</a>
               </s:if></li> 
			   <s:if test="user.trustStatus==0">
               <li><h4>账户托管：</h4> <span>未开通</span>  <a href="<%=path %>/user/registerbind"> [立刻开通]</a></li>
               </s:if>
               <s:else>
               <li><h4>账户托管：</h4> <span>已开通</span></li>
               </s:else>
               <s:if test="user.authorizeStatus==0">
               <li><h4>设置授权：</h4> <span>未开通</span>  <a href="<%=path %>/user/loanauthorize"> [立刻开通]</a></li>
               </s:if>
               <s:else>
               <li><h4>设置授权：</h4> <span>已开通</span></li>
               </s:else>
			  <!--  <s:if test="bankNum==null || bankNum==0">
               <li><h4>银行账号：</h4> <span>还未设置</span>  <a href="<%=path %>/user/user_bank_list.jsp"> [设置银行账号]</a></li>
               </s:if>
               <s:else>
               <li><h4>银行账号：</h4> <span>已设置</span></li>
               </s:else>-->
              
               
               <li><h4>登录密码：</h4><span>已设置</span>    <a style="color:#f59b3b;" href="<%=path %>/user/password.jsp?mark=2"> [马上修改]</a></li>
               <li><h4>支付密码：</h4>
               <s:if test='user.password!=user.payPwd'><span>已设置</span><a style="color:#f59b3b;" href="<%=path %>/user/password.jsp?mark=3"> [马上修改]</a></s:if> 
               <s:else><span>尚未设置</span>  <a style="color:#f59b3b;" href="<%=path %>/user/password.jsp?mark=3"> [马上设置]</a></s:else>
               </li>

             
               
              <!-- <s:if test='basicCheck==6'> 
               <li><h4>基本信息：</h4><span>已填写完整</span></li>
               </s:if>
               <s:else>
               <li><h4>基本信息：</h4><span>尚未填写完整</span><a href="<%=path %>/user/list?mark=1"> [马上填写]</a></li>
               </s:else>-->
              
              <li style="color:red;font-size: 15px;font-weight: bold;">*：红色为必填项(如没有，请忽略)</li>




            
           </ul>
		       <div class="fund">
		          <h3>账户详情</h3>
		          <table width="92%" cellspacing="0" cellpadding="0" class="zh_table"  >
		                          <tbody><tr>
		                              <th align="left" colspan="3" class="th1">资金信息:</th>
		                          </tr>
		                          <tr>
		                              <td>充值总额：￥
		                              <s:if test='moneycount.accuRechargeMoney==null || moneycount.accuRechargeMoney==""'>0.00</s:if>
		                              <s:else><fmt:formatNumber value="${moneycount.accuRechargeMoney}" pattern="0.00"/></s:else>
		                              </td>
		                              <td>可用余额：￥
		                              <s:if test='moneycount.availableMoney==null || moneycount.availableMoney==""'>0.00</s:if>
		                              <s:else><fmt:formatNumber value="${moneycount.availableMoney}" pattern="0.00"/></s:else>
		                              </td>
		                              <td>冻结金额：￥
		                              <s:if test='moneycount.frozenMoney==null || moneycount.frozenMoney==""'>0.00</s:if>
		                              <s:else><fmt:formatNumber value="${moneycount.frozenMoney}" pattern="0.00"/></s:else>
		                              </td>
		                          </tr>
		                          <tr>
		                              <td>待收总额：￥
<!-- 		                              <fmt:formatNumber value="${moneycount.collectTotalMoney}" pattern="0.00"/> -->
		                               <fmt:formatNumber value="${moneycount.dueInMoney}" pattern="0.00"/>
		                              </td>
		                              <td>待收利息：￥
		                              <s:if test='moneycount.collectInterestTotalFee==null || moneycount.collectInterestTotalFee==""'>0.00</s:if>
		                              <s:else><fmt:formatNumber value="${moneycount.collectInterestTotalFee}" pattern="0.00"/></s:else>
		                              </td>
		                              <td>提现总额：￥
		                              <s:if test='moneycount.accuWidthdrawMoney==null || moneycount.accuWidthdrawMoney==""'>0.00</s:if>
		                              <s:else><fmt:formatNumber value="${moneycount.accuWidthdrawMoney}" pattern="0.00"/></s:else>
		                             </td>
		                          </tr>
		                          <tr>
		                              <th align="left" colspan="3" class="th1">损益明细：</th>
		                          </tr>
		                          <tr>
		                              <td>净赚利息：￥
		                              <s:if test='moneycount.netEarnInterest==null || moneycount.netEarnInterest==""'>0.00</s:if>
		                              <s:else> <fmt:formatNumber value="${moneycount.netEarnInterest}" pattern="0.00"/></s:else>
		                              </td>
		                              <td>投标奖励：￥
		                              <s:if test='moneycount.accuBidReward==null || moneycount.accuBidReward==""'>0.00</s:if>
		                              <s:else> <fmt:formatNumber value="${moneycount.accuBidReward}" pattern="0.00"/></s:else>
		                              </td>
		                              <td>推广奖励：￥
		                              <s:if test='moneycount.accuPromoteReward==null || moneycount.accuPromoteReward==""'>0.00</s:if>
		                              <s:else> <fmt:formatNumber value="${moneycount.accuPromoteReward}" pattern="0.00"/></s:else>
		                              </td>
		                          </tr>
		                          <tr>
		                              <td>支付投标奖励：￥
		                              <s:if test='moneycount.accuPayBidReward==null || moneycount.accuPayBidReward==""'>0.00</s:if>
		                              <s:else> <fmt:formatNumber value="${moneycount.accuPayBidReward}" pattern="0.00"/></s:else>
		                              </td>
		                              <td>支付利息：￥
		                              <s:if test='moneycount.netPayInterest==null || moneycount.netPayInterest==""'>0.00</s:if>
		                              <s:else> <fmt:formatNumber value="${moneycount.netPayInterest}" pattern="0.00"/></s:else>
		                              </td>
		                              <td>提现手续费：￥
		                              <s:if test='moneycount.accuWithdrawalMoney==null || moneycount.accuWithdrawalMoney==""'>0.00</s:if>
		                              <s:else> <fmt:formatNumber value="${moneycount.accuWithdrawalMoney}" pattern="0.00"/></s:else>
		                              </td>
		                          </tr>
		                          <tr>
		                              <td>会员及认证费用：￥
		                              <s:if test='moneycount.vipCertifiMoney==null || moneycount.vipCertifiMoney==""'>0.00</s:if>
		                              <s:else> <fmt:formatNumber value="${moneycount.vipCertifiMoney}" pattern="0.00"/></s:else>
		                              </td>
		                              <td>续投奖励：￥
		                              <s:if test='moneycount.accuContinueBidReward==null || moneycount.accuContinueBidReward==""'>0.00</s:if>
		                              <s:else> <fmt:formatNumber value="${moneycount.accuContinueBidReward}" pattern="0.00"/></s:else>
		                              </td>
		                              <td>线下充值奖励：￥
		                              <s:if test='moneycount.accuOfflineRechargeReward==null || moneycount.accuOfflineRechargeReward==""'>0.00</s:if>
		                              <s:else> <fmt:formatNumber value="${moneycount.accuOfflineRechargeReward}" pattern="0.00"/></s:else>
		                              </td>
		                          </tr>
		                          <tr>
		                              <td>逾期及催收费用：￥
		                              <s:if test='moneycount.lateAndCollectMoney==null || moneycount.lateAndCollectMoney==""'>0.00</s:if>
		                              <s:else> <fmt:formatNumber value="${moneycount.lateAndCollectMoney}" pattern="0.00"/><</s:else>
		                              </td>
		                              <td>支出总和：￥
		                              <s:if test='moneycount.netPayInterest+moneycount.vipCertifiMoney+moneycount.loanManageMoney+moneycount.lateAndCollectMoney+moneycount.accuWithdrawalMoney+moneycount.accuRechargeFee+moneycount.accuPayBidReward==null || moneycount.netPayInterest+moneycount.vipCertifiMoney+moneycount.loanManageMoney+moneycount.lateAndCollectMoney+moneycount.accuWithdrawalMoney+moneycount.accuRechargeFee+moneycount.accuPayBidReward==""'>0.00</s:if>
		                              <s:else> <fmt:formatNumber value="${moneycount.netPayInterest+moneycount.vipCertifiMoney+moneycount.loanManageMoney+moneycount.lateAndCollectMoney+moneycount.accuWithdrawalMoney+moneycount.accuRechargeFee+moneycount.accuPayBidReward}" pattern="0.00"/></s:else>
		                              </td>
		                              <td>收入总和：￥
		                              <s:if test='moneycount.netEarnInterest+moneycount.accuBidReward+moneycount.accuPromoteReward+moneycount.accuOfflineRechargeReward+moneycount.accuContinueBidReward==null || moneycount.netEarnInterest+moneycount.accuBidReward+moneycount.accuPromoteReward+moneycount.accuOfflineRechargeReward+moneycount.accuContinueBidReward==""'>0.00</s:if>
		                              <s:else> <fmt:formatNumber value="${moneycount.netEarnInterest+moneycount.accuBidReward+moneycount.accuPromoteReward+moneycount.accuOfflineRechargeReward+moneycount.accuContinueBidReward}" pattern="0.00"/></s:else>
								</td>
		                          </tr>
		                    </tbody></table>
		       </div>
		       </div>
	      </div>
	      </div>
		<!--footer 开始-->
		<div class="footer">
		  <jsp:include page="../footer.jsp" />
		</div>
	 <input type="hidden" name="userId" id="userId" value=${sessionScope.uservip.userId }></input>
    </body>
</html>

