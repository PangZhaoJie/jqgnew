<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@taglib prefix="s" uri="/struts-tags" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%    
	String path = request.getContextPath();if(path!=null && !path.equals("")){	if(path.contains("/")){		if(path.equals("/")){path = "";	}}else{	path += "/";}}    
	String basePath = "";if(request.getServerPort()==80){basePath = request.getScheme()+"://"+request.getServerName()+path+"/";}else{basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";}      
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="icon" href="<%=path %>/image/favicon/favicon.ico" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<link href="<%=basePath%>/css/public.css" type="text/css" rel="stylesheet"/>
<link href="<%=basePath%>/css/user/user_index.css" type="text/css" rel="stylesheet"/>
<link href="<%=basePath%>/css/user/user_public.css" type="text/css" rel="stylesheet"/>
<!--导航下拉菜单 js-->
<script type="text/javascript" src="<%=basePath%>/js/jquery-1.8.2.min.js"></script>
<!--左侧折叠菜单-->
<script type="text/javascript" src="<%=basePath%>/js/menu.js"></script>

</head>
<body>
<!--头部开始-->
<div class="header">
    <jsp:include page="../header.jsp" />
</div>

<!--头部结束-->
<div class="maincontent">
  
   <div class="conbox fn_cle">
    <jsp:include page="left.jsp"/>
   </div>
   
   <div class="user_r">
         
         <div class="grzl fn_cle">
          
          <div class="grzl_top">
          
            <div class="mc_face" >
              <a href="#"> 
              <s:if test='user.photoOne==null || user.photoOne==""'>
                    <img width="100" height="100" src="<%=basePath%>/images/yh_tx.jpg">
              </s:if>
              <s:else>
                    <img width="100" height="100" src="<%=basePath%>${user.photoOne}">
              </s:else>

              </a>
              <div class="change_btn" style="width:107px;height:28px;"> 
              <a href="<%=path %>/user/password.jsp?mark=1" class="change_bt" style="display:block;text-align:center;color:#000000">
                                          更改头像
<!--               <input type="submit" class="change_bt" value="更改头像" /> -->
              </a></div>
            </div>
          
            <div class="user_info">
             <p><span>用户名：</span>${user.userName}
             <s:if test='user.isVIP=="1"'>
              <img width="20" height="20" src="<%=path %>/home/img/vip.png"></s:if>
             </p>      
             <p><span>信用等级：</span>
             <img  src="<%=basePath %>${creditlevel.creditLevelPicture}"> 级</p>
             <div class="rzxx"> <span>认证信息：</span>
                 <a href="<%=path %>/user/create?mark=3">
                 <s:if test='user.nameResult=="2"'><i class="sm yes">实名</i></s:if>
                 <s:else><i class="sm">实名</i></s:else>
                 </a>
                 <a href="<%=path %>/user/create?mark=2">
                 <s:if test='user.phoneResult=="1"'><i class="tel yes">手机</i></s:if>
                 <s:else><i class="tel">手机</i></s:else>
                 </a>
                 <a href="<%=path %>/user/create?mark=111">
                 <s:if test='user.enable==1'><i class="mail yes">邮箱</i></s:if>
                 <s:else><i class="mail">邮箱</i></s:else>
                 </a>
                 <a href="<%=path %>/user/create?mark=4">
                 <s:if test='user.sceneResult=="1"'><i class="pay yes">现场</i></s:if>
                 <s:else><i class="pay">现场</i></s:else>
                 </a>
                 <a href="<%=path %>/user/create?mark=5">
                 <s:if test='user.videoResult=="1"'><i class="video yes">视频</i></s:if>
                 <s:else><i class="mail">视频</i></s:else>
                 </a>
                 <s:if test="user.manager!=null">
                 <a href="http://wpa.qq.com/msgrd?v=3&uin=${user.manager.qq}&site=qq&menu=yes" target="_blank" hidefocus="true" class="deposit_bt only_server" style=" border-radius: 10px;color: #ffffff; display: block; float: left; height: 26px; line-height: 26px;padding: 0 10px; text-align: center;"> 联系专属客服</a>
                 </s:if>
             </div>     
             
          </div>
          
          </div> 
          
          <div class="money"> 
          
            <P>账户余额：
             <s:if test='moneycount.availableMoney==null || moneycount.availableMoney==""'>0.00</s:if>
             <s:else><fmt:formatNumber value="${moneycount.availableMoney}" pattern="0.00"/></s:else>
                                    元可用,冻结金额
             <s:if test='moneycount.frozenMoney==null || moneycount.frozenMoney==""'>0.00</s:if>
             <s:else><fmt:formatNumber value="${moneycount.frozenMoney}" pattern="0.00"/></s:else>
                                   元，信用额度：
        	 <s:if test='user.quota==null || user.quota==""'>0</s:if>
             <s:else><fmt:formatNumber value="${user.quota}" pattern="0.00"/></s:else>
        	元</P>
            <div class="m_btn">
              <div class="recharge_btn" > <a href="<%=path %>/borrow/toChongzhi?userId=${session.uservip.userId}" class="recharge_bt" style="display:block;text-align:center;color:#FFFFFF">
                                           充&nbsp;&nbsp;值
<!--               <input type="submit" class="recharge_bt" value="充&nbsp;&nbsp;值" /> -->
              </a></div>
              <div class="deposit_btn" > <a href="<%=path %>/userbank/mention" class="deposit_bt" style="display:block;text-align:center;color:#FFFFFF">
                                          提&nbsp;&nbsp;现
<!--               <input type="submit" class="deposit_bt" value="提&nbsp;&nbsp;现" /> -->
              </a></div>
            </div>
          </div>
          
          <div class="myhome_tips ">
                        <dl class="fn_cle">
                            <dt>温馨提示：</dt>
                            <dd>未读站内信
                            <a class="fred" href="<%=path %>/user/toJspPage?flag=2">
                            <s:if test='unreadInbox==null || unreadInbox==""'>0</s:if>
                            <s:else>${unreadInbox}</s:else>
                            </a>封</dd>
                            <dd>正在招标中的借款<a class="fred" href="<%=path %>/borrow/toBorrowTotal?userId=${sessionScope.uservip.userId}">
                            <s:if test='waitCount==null || waitCount==""'>0</s:if>
                            <s:else>${waitCount}</s:else>
                            </a>个</dd>
                            <dd>本月待还款<a class="fred" href="<%=path %>/borrow/toBorrowTotal?userId=${sessionScope.uservip.userId}">
                            <s:if test='currentCount==null || currentCount==""'>0</s:if>
                            <s:else>${currentCount}</s:else>
                            </a>个</dd>
                            <dd>本月待收款<a class="fred" href="<%=path %>/borrow/toAccount?userId=${sessionScope.uservip.userId}">
                            <s:if test='currentGetCount==null || currentGetCount==""'>0</s:if>
                            <s:else> ${currentGetCount}</s:else>
                            </a>个</dd>
                            <dd>逾期待还款<a class="fred" href="<%=path %>/borrow/toAccount?userId=${sessionScope.uservip.userId}">
                            <s:if test='overTimePay==null || overTimePay==""'>0</s:if>
                            <s:else> ${overTimePay}</s:else>
                            </a>个</dd>
                        </dl>
                    </div>
          
          
         
         </div>     
       
         <div class="safe">
           <h3>安全中心</h3>
           <ul class="fn_cle">
               <li><h4>实名认证：</h4>
               <s:if test='user.nameResult=="2"'>已验证</s:if>
               <s:if test='user.nameResult=="1"'>验证中</s:if>  
               <s:if test='user.nameResult=="0"'>
               <span>验证失败</span>  
               <a href="<%=path %>/user/create?mark=3"> [重新验证]</a></s:if> 
               <s:if test='user.nameResult==null || user.nameResult==""'><span>还未验证</span>  
               <a href="<%=path %>/user/create?mark=3"> [马上验证]</a></s:if></li>
               <li><h4>登录密码：</h4><span>已设置</span>    <a href="<%=path %>/user/password.jsp?mark=2"> [马上修改]</a></li>
               <li><h4>支付密码：</h4>
               <s:if test='user.password!=user.payPwd'><span>已设置</span><a href="<%=path %>/user/password.jsp?mark=3"> [马上修改]</a></s:if> 
               <s:else><span>尚未设置</span>  <a href="<%=path %>/user/password.jsp?mark=3"> [马上设置]</a></s:else>
               </li>
<!--               <li><h4>安全问题：</h4>-->
<!--               <s:if test='user.questionresult==1'>已设置</s:if> -->
<!--               <s:else><span>尚未设置</span>  <a href="<%=path %>/user/create?mark=6"> [马上设置]</a></s:else>-->
<!--               </li>-->
               <s:if test='user.phoneResult=="1"'>
               <li><h4>手机号码：</h4>已验证</li>
               </s:if>
               <s:else>
               <li><h4>手机号码：</h4> <span>还未验证</span>  <a href="<%=path %>/user/create?mark=2"> [马上验证]</a></li>
               </s:else>
               <li><h4>电子邮箱：</h4> 
               <s:if test='user.enable=="1" && (user.mail!=null || user.mail!="")'>已验证</s:if>
               <s:if test='user.enable==null || user.enable=="" || user.mail==null || user.mail==""'><span>还未验证</span>
               <a href="<%=path %>/user/create?mark=111">[马上验证]</a>
               </s:if></li>
               <s:if test='basicCheck==6'> 
               <li><h4>基本信息：</h4><span>已填写完整</span></li>
               </s:if>
               <s:else>
               <li><h4>基本信息：</h4><span>尚未填写完整</span><a href="<%=path %>/user/list?mark=1"> [马上填写]</a></li>
               </s:else>
               <s:if test="bankNum==null || bankNum==0">
               <li><h4>银行账号：</h4> <span>还未设置</span>  <a href="<%=path %>/user/user_bank_list.jsp"> [设置银行账号]</a></li>
               </s:if>
               <s:else>
               <li><h4>银行账号：</h4> <span>已设置</span></li>
               </s:else>
               
           </ul>
         </div>
         
         <div class="fund">
            <h3>账户详情</h3>
            <table width="100%" cellspacing="0" cellpadding="0" class="zh_table"  >
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
                                <s:if test='moneycount.dueInMoney==null || moneycount.dueInMoney==""'>0.00</s:if>
                                <s:else><fmt:formatNumber value="${moneycount.dueInMoney}" pattern="0.00"/></s:else>
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
</div>
<!--footer 开始-->
<div class="footer">
  <jsp:include page="../footer.jsp" />
</div>
<input type="hidden" name="userId" id="userId" value=${sessionScope.uservip.userId}></input>
<!--footer 结束-->


<!--右边漂浮 开始-->
<%-- <div id="service">
	<a href="<%=path%>/tool/borrow_tool.jsp" class="srvLog"   title="网贷计算器">网贷计算器</a>
	<a href="tencent://message/?uin=285772998&Site=企业网站&Menu=yes" class="srvCns" title="官方交流群">官方交流群</a>
	<a href="<%=path%>/tender/totenderList" class="srvDj"   title="投资理财">投资理财</a>
	<a class="goTop" id="goTop" title="返回顶部" style="display:none" >返回顶部</a>
</div>
<script type="text/javascript" src="js/service.js">	</script> --%>
<!--右边漂浮 结束-->
</body>
</html>