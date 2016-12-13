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
<!--导航下拉菜单 js-->
<script type="text/javascript" src="../js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="../js/tender.js"></script>

<script type="text/javascript" src="../js/scroll.js"></script>
<!--经过小图显示大图-->
<script type="text/javascript" src="../js/main.js" ></script>

</head>
<body> 
<!--头部开始-->
<div class="header">
     <jsp:include page="../header.jsp" />
</div>
<div class="maincontent">
	<input id="path" type="hidden" value="<%=basePath%>"></input>
    <input type="text"  style="display: none;" value="${sessionScope.lssuing.isOrient}"  id="passwordnone" ></input>
    <input type="text"  style="display: none;" value="${sessionScope.moneyMin}"  id="minIDnone" ></input>
    <input type="text"  style="display: none;" value="${sessionScope.moneyMax}"  id="maxIDnone" ></input>
    <input type="text"  style="display: none;" value="<fmt:formatNumber value="${moneycount.experienceMoney}" pattern="0.00"/> "  id="moneyIDnone" ></input>
    <input type="text"  style="display: none;" value="${sessionScope.lssuing.orientPassword}"  id="opsdIDnone" ></input>
    <input type="text"  style="display: none;" value="${sessionScope.money}"  id="balanceIdnone" ></input>
    <input type="text"  style="display: none;" value="${sessionScope.moneyLimit}"  id="moneyLimitIdnone" ></input>
    <input type="text"  style="display: none;" value="${sessionScope.lssuing.moneyLimit}"  id="lmoneyLimitIdnone" ></input>
    <input type="text"  style="display: none;" value="${sessionScope.lssuing.tenderLimit}"  id="tenderLimit" ></input>
 
    <input type="text"  style="display: none;" value="${sessionScope.uservip}"  id="uservip" ></input>
    <input type="text"  style="display: none;" value="${sessionScope.lssuing.state}"  id="state" ></input>
    <div class="conbox pt20">
     <p class="guide">当前位置：<a href="<%=path%>/index.action">网站首页</a> > <a href="<%=path%>/tender/totenderList">我要投资</a> > 投资详情</p>
     <div class="tend_nr fn_cle">
       <div class="tend_title"><em> ${sessionScope.picture}</em><h3>${sessionScope.lssuing.title}</h3><span></span></div>
       <div class="tend_info fn_cle">
          <div class="tend_l">
            <ul>
			   <li>借款金额：<span><fmt:formatNumber value="${sessionScope.lssuing.borrowMoney}" pattern="0.00"/></span>元 </li>
               <li>${sessionScope.day}利率：  <span>${sessionScope.lssuing.rate}%</span></li> 
               <li>借款期限：<span><c:if test="${sessionScope.lssuing.periodtime!=null}">${sessionScope.lssuing.periodtime.periodTimeName}</c:if>
               <c:if test="${sessionScope.lssuing.periodday!=null}">${sessionScope.lssuing.periodday.periodDayName}</c:if>
               </span></li>                            
               <li>还款方式：<span>${sessionScope.lssuing.returnway.returnWayName}</span></li> 
               <li>借款状态：<span>${sessionScope.state}</span> </li>                   
               <li>投标进度：<span>${sessionScope.bear}%</span></li>       
               <li>借款奖励：<span>${sessionScope.away}</span></li>         
               <li>还款本息：<span>${sessionScope.interest}</span>元</li>
               <li>借款用途：<span>${sessionScope.lssuing.moneyuse.moneyUseName}</span></li>                 
               <li>借款时间：<span> <fmt:formatDate value="${sessionScope.lssuing.borrowTime}" pattern="yyyy-MM-dd" /></span></li> 

            </ul>
          </div>
          
    <div class="tend_r">
      <div class="tend_form">
       <form action="<%=path%>/json/tender?lussingType=8" method="post" name="tender" onsubmit="getElementById('invest_bt').disabled=true;return true;">
       <input type="text"  style="display: none;" value="${sessionScope.lssuing.lssuingId}"  id="lssuingId" name="lssuingId"></input>
			   <div id="moneyState"><label class="title">剩余金额</label><span><fmt:formatNumber value="${sessionScope.money}" pattern="0.00"/></span>元</div>
			   <div id="moneyState2"><label class="title">体验金额</label><span><fmt:formatNumber value="${moneycount.experienceMoney}" pattern="0.00"/></span>元</div>
               
            <s:if test='#session.uservip.nameResult==null || #session.uservip.nameResult==""'><div  style="color:red;padding-left:60px"><span style="font-size:20px">还未验证</span>  
            <a href="<%=path %>/user/create?mark=3" style="color:red"> [马上验证]</a></div></s:if>
            <div id="textState"></div>
            <s:else>
            <div id="textState">
               <label class="title">投标金额</label> <input type="text"  value="" size="24" name="money" id="tip" onclick="text()"></input> </br> </br>
               <label class="title">支付密码</label> <input type="password"   size="25" name="paypwd" id="userPaypwd" />
            </div>
            </s:else>
               
               <em id="opsdId"  style="display: none;color: red;" >投标密码不对</em>
               <em id="moneyLimitId" style="display: none;color: red;" >您的待收金额不符合要求，请投其他标</em>
               <em id="moneyID" style="display: none;color: red;" >对不起体验余额不足</em>
               <em id="maxId" style="display: none;color: red;" >您输入的金额超过最高投标金额</em>
               <em id="minId" style="display: none;color: red;" >您输入的金额低于最低投标金额</em>
               <em id="balanceId" style="display: none;color: red;" >您输入的金额高于剩余金额</em>
               <em id="tipId" style="display: none;color: red;" >投标金额不能为空</em>
               <em id="tip50Id" style="display: none;color: red;" >投标金额必须大于1元钱</em>
               <em id="login" style="display: none;color: red;" >请您先登录 <a href="<%=path %>/login/login.jsp">立即登录</a></em>
               <div style="display: none;" id="opsd"><label class="title" >投标密码</label> <input type="text"  value="" size="25" name="" id="psd"  ></input></div>
               <div id="minmaxState"><label >最低投标金额：<fmt:formatNumber value="${sessionScope.moneyMin}" pattern="0.00"/>元 最高投标金额：${sessionScope.moneyMax}元</label></div>
             
             <div class="invest_btn"  >
                   <input type="submit" class="invest_bt" id="invest_bt" value="我要投资" />
                   <input type="button" class="invest_bt" id="0State" value="初审中" style="display: none;"/>
                   <input type="button" class="invest_bt" id="1State" value="复审中" style="display: none;"/>
                   <input type="button" class="invest_bt"id="3State"  value="还款中" style="display: none;"/>
                   <input type="button" class="invest_bt" id="4State" value="已完成" style="display: none;"/>
             </div>
             </form>  
             </div>
            </div>
       </div>
   </div>
     <div class="invest_list fn_cle">
         <div id="hotnews_caption">
           <ul id="myTop2">                  
            <li class="current"><a href="#">基本信息</a></li>
            <li class="normal"><a href="#">投标记录</a></li>
          </ul>
         </div>
      
      <div id="myCont2">
         <div class="current" id="list_3">
            <div class="personal_infor">
              <div class="tend_left">
                  <div class="cle"></div>
                  <div class="mt30">
                      <h3>借款详细介绍</h3>
                       <div class="tend_con">${sessionScope.lssuing.explains}</div>
                        <div class="image_text">
                      <!-- 图片滚动-->
                         <div class="Div1">
                              <b class="Div1_prev Div1_prev1" ><img src="../images/lizi_img011.jpg" title="上一页" /></b>
                              <b class="Div1_next Div1_next1" ><img src="../images/lizi_img012.jpg" title="下一页"/></b>
                           <div class="Div1_main">
                           <c:forEach var="lssList1" items="${lssList}">
                           	<div>
                           		<c:forEach var="lssList" items="${lssList1}">
                           			 <span><a href="<%=path%>${lssList.photo}" rel="<%=path%>${lssList.photo}" target="_blank" class="Div1_main_a1 preview"><img src="<%=path%>${lssList.photo}" /></a></span>
                           		</c:forEach>
                           	</div>
                           </c:forEach>
                     </div>
                    </div>
                    <input type="text" value="${fn:length(lssList)}" id="index" style="display: none"/>
                      <!-- 图片滚动-->
                       </div>
                  </div>
              </div>

             <div class="tend_right">
                   <div class="per_infor_detai">
                  <h3>用户信息</h3>
			   <ul>
				 <li>
				    <span class="basic_label">性别</span>
					<span class="basic_value">
	                 <s:if test="#session.basicinforTdener.sex==null"> 暂无信息</s:if>
					 <s:if test="#session.basicinforTdener.sex==0"> 男</s:if>
                     <s:if test="#session.basicinforTdener.sex==1"> 女</s:if>
                     
					</span>
					<span class="basic_label">月收入</span>
					<span class="basic_value">
					<s:if test="#session.basicinforTdener.monthIncome==null">暂无信息</s:if>
				       <s:if test="#session.basicinforTdener.monthIncome==0">5000元以下</s:if>
                       <s:if test="#session.basicinforTdener.monthIncome==1"> 5000-10000元</s:if>
                       <s:if test="#session.basicinforTdener.monthIncome==2"> 10000-50000元</s:if>
                       <s:if test="#session.basicinforTdener.monthIncome==3"> 50000元以上</s:if>
					</span>
				 </li>
				 <li>
				    <span class="basic_label">年龄</span>
				    <span class="basic_value">
				    <s:if test="#session.basicinforTdener.age==null"> 暂无信息</s:if>
				    ${sessionScope.basicinforTdener.age}</span>
				    <span class="basic_label">是否结婚</span>
					<span class="basic_value">
					<s:if test="#session.basicinforTdener.marryStatus==null"> 暂无信息</s:if>
					 <s:if test="#session.basicinforTdener.marryStatus==0"> 未婚</s:if>
                     <s:if test="#session.basicinforTdener.marryStatus==1">已婚</s:if>
					</span>
				 </li>
			
				 <li>
				  
					<span class="basic_label">工作年限</span>
					<span class="basic_value">
					<s:if test="#session.basicinforTdener.workYear==null">暂无信息</s:if>
					 <s:if test="#session.basicinforTdener.workYear==0">1年以下</s:if>
                       <s:if test="#session.basicinforTdener.workYear==1"> 1-3年</s:if>
                       <s:if test="#session.basicinforTdener.workYear==2"> 3-5年</s:if>
                       <s:if test="#session.basicinforTdener.workYear==3"> 5-10年</s:if>
                       <s:if test="#session.basicinforTdener.workYear==4"> 10年以上</s:if>
					</span>
                    <span class="basic_label">职业</span>
					<span class="basic_value">
					<s:if test="#session.basicinforTdener.job==null">暂无信息</s:if>
					${sessionScope.basicinforTdener.job} 
					</span>
				 </li>
				 <li>
				   
					<span class="basic_label">住房条件</span>
					<span class="basic_value">
					 <s:if test="#session.basicinforTdener.houseCondition==null"> 暂无信息</s:if>
					 <s:if test="#session.basicinforTdener.houseCondition==0"> 商品房</s:if>
                     <s:if test="#session.basicinforTdener.houseCondition==1">其他</s:if>
                     <s:if test="#session.basicinforTdener.houseCondition==2">无房</s:if>
					</span>
                    <span class="basic_label">有无购车</span>
					<span class="basic_value">
						<s:if test="#session.basicinforTdener.ifByCar==null">暂无信息</s:if>
                    <s:if test="#session.basicinforTdener.ifByCar=='0'"> 有车</s:if>
                     <s:if test="#session.basicinforTdener.ifByCar=='1'">无车</s:if>
					</span>
				
				 </li>       
				 	 <li>
				
					<span class="basic_label">学历</span>
					<span class="basic_value">
					<s:if test="#session.basicinforTdener.highestEdu==null">暂无信息</s:if>
					      <s:if test="#session.basicinforTdener.highestEdu==0">高中</s:if>
                       <s:if test="#session.basicinforTdener.highestEdu==1"> 大专</s:if>
                       <s:if test="#session.basicinforTdener.highestEdu==2"> 本科</s:if>
                       <s:if test="#session.basicinforTdener.highestEdu==3"> 硕士</s:if>
                       <s:if test="#session.basicinforTdener.highestEdu==4"> 博士</s:if>
                       <s:if test="#session.basicinforTdener.highestEdu==5">博士以上</s:if>
					</span>
				 </li>
			   </ul>
			 </div>
			  <h3>认证信息</h3>
			   <div class="borrow_infor">
					 <div class="borrow_infor_tit_lf">
						<a href="javascript:void(0);" title="deayou22"><img src="<%=path %>${sessionScope.lssuing.uservip.photoOne}" alt=""/></a>
				     </div>
					 <div class="borrow_infor_tit_rig">
						<p class="user_name">${sessionScope.lssuing.uservip.userName}</p>
					    <ul class="user_name_ul">
					    <s:if test="#session.lssuing.uservip.nameResult==2"> 
					         <li class="sm yes">实名</li>
					    </s:if>
					    <s:else>
					        <li class="sm">实名</li>
					    </s:else>
                      <s:if test="#session.lssuing.uservip.phoneResult==1"> 
					       <li class="tel yes">手机</li>
					    </s:if>
					    <s:else>
					       <li class="tel">手机</li>
					    </s:else>
                   <s:if test="#session.lssuing.uservip.enable==1"> 
					     <li class="mail yes">邮箱</li>
					    </s:if>
					    <s:else>
					       <li class="mail">邮箱</li>
					    </s:else>
                              <s:if test="#session.lssuing.uservip.sceneResult==1"> 
					      <li class="pay yes">现场</li>
					    </s:if>
					    <s:else>
					      <li class="pay">现场</li>
					    </s:else>
                                   <s:if test="#session.lssuing.uservip.videoResult==1"> 
					      <li class="video yes">视频</li>
					    </s:if>
					    <s:else>
					         <li class="video">视频</li>
					    </s:else>
                           
                          
                            
					    </ul>
					</div>	
				 <div class="cle"></div>
			    <ul class="mt10">
				   <li><span>发布借款：${sessionScope.lssuingTdener} 笔</span><span>成功借款：${sessionScope.yiwancheng}笔</span></li>
				  <li><span>待还笔数：${sessionScope.lssuingTdenerDai} 笔</span><span>逾期笔数：${sessionScope.yuqishu}笔</span></li>
				  <li><span>总借入：￥<fmt:formatNumber value="${sessionScope.zonge}" pattern="0.00"/> </span><span>待还：￥<fmt:formatNumber value="${sessionScope.daihuan}" pattern="0.00"/> </span></li>
				</ul>
			 </div>
            
            
		</div>
		 </div>
            
       </div>
       <div class="normal" id="list_4">
           <div class="list_4_table">
				 <table width="1140" border="0" align="center" cellspacing="0" cellpadding="0" id="listTable" class="tbjl_table">
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
      </div>
      
      <script type="text/javascript" src="../js/tabs2.js" language="javascript"></script>
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