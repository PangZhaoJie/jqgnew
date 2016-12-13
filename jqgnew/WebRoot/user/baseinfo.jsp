<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="/struts-tags" prefix="s"%>
<%    
	String path = request.getContextPath();if(path!=null && !path.equals("")){	if(path.contains("/")){		if(path.equals("/")){path = "";	}}else{	path += "/";}}    
	String basePath = "";if(request.getServerPort()==80){basePath = request.getScheme()+"://"+request.getServerName()+path+"/";}else{basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";}      
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="<%=path %>/image/favicon/favicon.ico" type="image/x-icon" />
<title>个人中心-基本资料</title>
<link href="<%=basePath%>/css/public.css" type="text/css" rel="stylesheet"/>
<link href="<%=basePath%>/css/user/user_public.css" type="text/css" rel="stylesheet"/>
<link href="<%=basePath%>/css/user/user_info.css" type="text/css" rel="stylesheet"/>
<!--导航下拉菜单 js-->
<script type="text/javascript" src="<%=basePath%>/js/jquery-1.8.2.min.js"></script>
<!-- 表单下拉列表框 -->
<%-- <script type="text/javascript" src="<%=basePath%>/js/select2css.js"></script> --%>

<!--左侧折叠菜单-->
<script type="text/javascript" src="../js/alert.js"></script>
<script type="text/javascript" src="../js/login/baseinfo.js"></script>
<script type="text/javascript" src="../js/menu.js"></script>
<script type="text/javascript">
var $page = jQuery.noConflict();
	function checkData(){
	var mark=document.getElementById("mark").value;
	if(mark==1){
		secBoard('hotnews_caption','list',1);
		}
	if(mark==2){
		secBoard('hotnews_caption','list',2);
		}
	if(mark==3){
		secBoard('hotnews_caption','list',3);
		}
	if(mark==4){
		secBoard('hotnews_caption','list',4);
		}
	if(mark==5){
		secBoard('hotnews_caption','list',5);
		}
	if(mark==6){
		secBoard('hotnews_caption','list',6);
		}
	}
$page(function(){
	checkData();
});

</script>
</head>
<body> 
<!--头部开始-->
<div class="header">
    <jsp:include page="../header.jsp" />
</div>
<!--头部结束-->
<div class="maincontent">
  <input type="hidden" id="path" value="<%=basePath%>"/>
    <div class="conbox fn_cle">
    <jsp:include page="left.jsp"/>
   </div>
   
   <div class="user_r">
         <div id="hotnews_caption">
            <ul id="h_menu">                  
                 <li class="current" onclick="secBoard('hotnews_caption','list',1);">个人资料</li>
                 <li class="normal" onclick="secBoard('hotnews_caption','list',2);">联系方式</li>
                 <li class="normal" onclick="secBoard('hotnews_caption','list',3);">单位资料</li>
                 <li class="normal" onclick="secBoard('hotnews_caption','list',4);">财务状况</li>
                 <li class="normal" onclick="secBoard('hotnews_caption','list',5);">房产资料</li>
                 <li class="normal" onclick="secBoard('hotnews_caption','list',6);">联保情况</li>
            </ul>
                   
          </div>
         <div id="hotnews_content">
      
   <div class="current" id="list_1">
   		<form name="form" method="post" id="list1">
            <c:if test="${sessionScope.uservip.realName==null || sessionScope.uservip.realName==''}">
            <div class="info"><label class="title">真实姓名：</label><input readonly="readonly" id="reaName" value="尚未实名验证" size="28" name="" /><label class="tip">您是初次驾临，请先去进行<a href="<%=path %>/user/create?mark=3">【实名认证】</a></label></div>
            </c:if>
            <c:if test="${!empty sessionScope.uservip.realName}">
            <div class="info"><label class="title">真实姓名：</label><input readonly="readonly" id="reaName" value="${sessionScope.uservip.realName}" size="28" name="" /></div>
            </c:if>
            <c:if test="${basicinfor.phoneNum==null || basicinfor.phoneNum==''}">
            <div class="info"><label class="title">手机号码：</label><input readonly="readonly" id="pNum" value="尚未手机验证" size="28" name="" /><label class="tip">您是初次驾临，请先去进行<a href="<%=path %>/user/create?mark=2">【手机认证】</a></label></div>
            </c:if>
            <c:if test="${!empty basicinfor.phoneNum}">
            <div class="info"><label class="title">手机号码：</label><input readonly="readonly" id="pNum" value="${basicinfor.phoneNum}" size="28" name="" /></div>
            </c:if>
           	<div class="info"><label class="title">当前居住城市：</label>
                <div class="add"><input name="currentLiveCity1" id="currentLiveCity1" type="text" size="15" value="${currentLiveCity1}"/><span>省</span> <input name="currentLiveCity2" id="currentLiveCity2" type="text" size="15" value="${currentLiveCity2}"/> <span>市</span> <input name="currentLiveCity3" id="currentLiveCity3" type="text" size="15" value="${currentLiveCity3}"/><span>区</span><span id="cityTip"></span></div>
            </div>
            <div class="info"><label class="title">性别：</label>
                <p style="font-size:14px;">
                   <label style="padding-right:15px;"> <input type="radio" name="basicinfor.sex" value="0" id="sex_0" style="border:0;" <c:if test="${basicinfor.sex=='0'}">checked="checked"</c:if> /><em>男</em></label>
                   <label> <input type="radio" name="basicinfor.sex" value="1" id="sex_1" style="border:0;" <c:if test="${basicinfor.sex=='1'}">checked="checked"</c:if>  /><em>女</em></label>
                </p>
             </div>
            <div class="info"><label class="title">婚姻状况：</label>
               <p style="font-size:14px;">
                   <label style="padding-right:15px;"> <input type="radio" name="basicinfor.marryStatus" value="0" id="marryStatus_0" style="border:0;" <c:if test="${basicinfor.marryStatus=='0'}">checked="checked"</c:if> /><em>未婚</em></label>
                   <label> <input type="radio" name="basicinfor.marryStatus" value="1" id="marryStatus_1" style="border:0;" <c:if test="${basicinfor.marryStatus=='1'}">checked="checked"</c:if> /><em>已婚</em></label>
                </p>
            </div>
            <div class="info"><label class="title">最高学历：</label>
               <p style="font-size:14px;">
                   <label style="padding-right:15px;"> <input type="radio" name="basicinfor.highestEdu" value="0" id="highestEdu_0" style="border:0;" <c:if test="${basicinfor.highestEdu=='0'}">checked="checked"</c:if>/><em>高中以下</em></label>
                   <label> <input type="radio" name="basicinfor.highestEdu" value="1" id="highestEdu_1" style="border:0;" <c:if test="${basicinfor.highestEdu=='1'}">checked="checked"</c:if> /><em>大专</em></label>
                   <label> <input type="radio" name="basicinfor.highestEdu" value="2" id="highestEdu_1" style="border:0;" <c:if test="${basicinfor.highestEdu=='2'}">checked="checked"</c:if> /><em>本科</em></label>
                   <label> <input type="radio" name="basicinfor.highestEdu" value="3" id="highestEdu_1" style="border:0;" <c:if test="${basicinfor.highestEdu=='3'}">checked="checked"</c:if> /><em>硕士</em></label>
                   <label> <input type="radio" name="basicinfor.highestEdu" value="4" id="highestEdu_2" style="border:0;" <c:if test="${basicinfor.highestEdu=='4'}">checked="checked"</c:if> /><em>硕士以上</em></label>
                   
                </p></div>
            <div class="info"><label class="title">收入状况：</label> 
            <p style="font-size:14px;">
                   <label style="padding-right:15px;"> <input type="radio" name="basicinfor.monthIncome" value="0" id="monthIncome_0" style="border:0;" <c:if test="${basicinfor.monthIncome=='0'}">checked="checked"</c:if> /><em>5000以下</em></label>
                   <label> <input type="radio" name="basicinfor.monthIncome" value="1" id="monthIncome_1" style="border:0;" <c:if test="${basicinfor.monthIncome=='1'}">checked="checked"</c:if> /><em>5000-10000</em></label>
                   <label> <input type="radio" name="basicinfor.monthIncome" value="2" id="monthIncome_2" style="border:0;" <c:if test="${basicinfor.monthIncome=='2'}">checked="checked"</c:if> /><em>10000-50000</em></label>
                   <label> <input type="radio" name="basicinfor.monthIncome" value="3" id="monthIncome_3" style="border:0;" <c:if test="${basicinfor.monthIncome=='3'}">checked="checked"</c:if> /><em>50000以上</em></label>
                </p></div>
            <div class="info"><label class="title">职业：</label><input name="basicinfor.job" id="job" type="text" size="30" value="${basicinfor.job}"/><span id="jobTip"></span></div>
            <div class="info"><label class="title">个人描述：</label><textarea name="basicinfor.personalProfile" id="personalProfile" class="person">${basicinfor.personalProfile}</textarea></div>
            <div id="savebtn" > <input type="button" class="save_bt"  value="保存且继续" onclick="submitCheck()" /></div>
            <div class="info"><input name="basicInforId" id="basicInforId" type="hidden" size="30" value="${basicInforId}" /></div>
            <div class="info"><input name="basicInfor" id="basicInfor" type="hidden" size="30" value="${basicInfor}" /></div>
           </form>
         </div>
         
 <div class="normal" id="list_2">
         <form name="form" method="post" id="list2">
            <div class="info"><label class="title2">现居住地址：</label><input name="basicinfor.currentAddress" id="currentAddress" type="text" size="30" value="${basicinfor.currentAddress}"/></div>
            <div class="info"><label class="title2">住宅电话：</label><input name="basicinfor.homePhone" id="homePhone" type="text" size="30" value="${basicinfor.homePhone}"/></div>
            <div class="info"><label class="title2">第一联系人：</label><input name="basicinfor.linkmanOne" id="linkmanOne" type="text" size="30" value="${basicinfor.linkmanOne}"/></div>
            <div class="info"><label class="title2">关系：</label>
              <p style="font-size:14px;">
                   <label style="padding-right:15px;"> <input type="radio" name="basicinfor.relationOne" id="relationOne_0" value="0" style="border:0;" <c:if test="${basicinfor.relationOne=='0'}">checked="checked"</c:if> /><em>家庭成员</em></label>
                   <label> <input type="radio" name="basicinfor.relationOne" value="1" id="relationOne_1" style="border:0;" <c:if test="${basicinfor.relationOne=='1'}">checked="checked"</c:if> /><em>朋友</em></label>
                   <label> <input type="radio" name="basicinfor.relationOne" value="2" id="relationOne_2" style="border:0;" <c:if test="${basicinfor.relationOne=='2'}">checked="checked"</c:if> /><em>商业伙伴</em></label>
               </p></div>
            <div class="info"><label class="title2">手机号码：</label><input name="basicinfor.phoneOne" id="phoneOne" type="text" size="30" value="${basicinfor.phoneOne}"/></div>
            <div class="info"><label class="title2">其他：</label><input name="basicinfor.otherOne" id="otherOne" type="text" size="30" value="${basicinfor.otherOne}"/></div>
            
            
            <div class="info"><label class="title2">第二联系人：</label><input name="basicinfor.linkmanTwo" id="linkmanTwo" type="text" size="30" value="${basicinfor.linkmanTwo}"/></div>
            <div class="info"><label class="title2">关系：</label>
              <p style="font-size:14px;">
                   <label style="padding-right:15px;"> <input type="radio" name="basicinfor.relationTwo" id="relationTwo_0" value="0"  style="border:0;" <c:if test="${basicinfor.relationTwo=='0'}">checked="checked"</c:if> /><em>家庭成员</em></label>
                   <label> <input type="radio" name="basicinfor.relationTwo" value="1" id="relationTwo_1" style="border:0;" <c:if test="${basicinfor.relationTwo=='1'}">checked="checked"</c:if> /><em>朋友</em></label>
                   <label> <input type="radio" name="basicinfor.relationTwo" value="2" id="relationTwo_2" style="border:0;" <c:if test="${basicinfor.relationTwo=='2'}">checked="checked"</c:if> /><em>商业伙伴</em></label>
                  
               </p></div>
            <div class="info"><label class="title2">手机号码：</label><input name="basicinfor.phoneTwo" id="phoneTwo" type="text" size="30" value="${basicinfor.phoneTwo}"/></div>
            <div class="info"><label class="title2">其他：</label><input name="basicinfor.otherTwo" id="otherTwo" type="text" size="30" value="${basicinfor.otherTwo}"/></div>
           
            <div class="info"><label class="title2">第三联系人：</label><input name="basicinfor.linkmanThree" id="linkmanThree" type="text" size="30" value="${basicinfor.linkmanThree}"/></div>
            <div class="info"><label class="title2">关系：</label>
              <p style="font-size:14px;">
                   <label style="padding-right:15px;"> <input type="radio" name="basicinfor.relationThree" value="0" id="relationThree_0" style="border:0;" <c:if test="${basicinfor.relationThree=='0'}">checked="checked"</c:if> /><em>家庭成员</em></label>
                   <label> <input type="radio" name="basicinfor.relationThree" value="1" id="relationThree_1" style="border:0;" <c:if test="${basicinfor.relationThree=='1'}">checked="checked"</c:if> /><em>朋友</em></label>
                   <label> <input type="radio" name="basicinfor.relationThree" value="2" id="relationThree_1" style="border:0;" <c:if test="${basicinfor.relationThree=='2'}">checked="checked"</c:if> /><em>商业伙伴</em></label>
                  
               </p></div>
            <div class="info"><label class="title2">手机号码：</label><input name="basicinfor.phoneThree" id="phoneThree" type="text" size="30" value="${basicinfor.phoneThree}"/></div>
            <div class="info"><label class="title2">其他：</label><input name="basicinfor.otherThree" id="otherThree" type="text" size="30" value="${basicinfor.otherThree}"/></div>
            <div id="savebtn" > <input type="button" class="save_bt" onclick="submitCheck2('0')" value="保存且继续" /> <input type="button" class="skip_bt" onclick="submitCheck2('1');secBoard('hotnews_caption','list',3);" value="跳过并继续" /></div>
<!--             <div class="info"><input name="userId" id="userId" type="hidden" size="30" /></div> -->
            <div class="info"><input name="basicInforId" id="basicInforId" type="hidden" size="30" value="${basicInforId}" /></div>
          </form>
         </div>
       
 <div class="normal" id="list_3">
            <form name="form" method="post" id="list3">
            <div class="info"><label class="title2">单位名称：</label><input name="basicinfor.companyName" id="companyName" type="text" size="30" value="${basicinfor.companyName}"/></div>
            <div class="info"><label class="title2">单位电话：</label><input name="basicinfor.companyPhone" id="companyPhone" type="text" size="30" value="${basicinfor.companyPhone}"/></div>
            <div class="info"><label class="title2">单位地址：</label><input name="basicinfor.companyAddress" id="companyAddress" type="text" size="30" value="${basicinfor.companyAddress}"/></div>
            <div class="info"><label class="title2">工作年限：</label>
              <p style="font-size:14px;">
                   <label style="padding-right:15px;"> <input type="radio" name="basicinfor.workYear" value="0" id="workYear_0" style="border:0;" <c:if test="${basicinfor.workYear=='0'}">checked="checked"</c:if>  /><em>1年以下</em></label>
                   <label> <input type="radio" name="basicinfor.workYear" value="1" id="workYear_1" style="border:0;" <c:if test="${basicinfor.workYear=='1'}">checked="checked"</c:if> /><em>1-3年</em></label>
                   <label> <input type="radio" name="basicinfor.workYear" value="2" id="workYear_1" style="border:0;" <c:if test="${basicinfor.workYear=='2'}">checked="checked"</c:if> /><em>3-5年</em></label>
                   <label> <input type="radio" name="basicinfor.workYear" value="3" id="workYear_1" style="border:0;" <c:if test="${basicinfor.workYear=='3'}">checked="checked"</c:if> /><em>5-10年</em></label>
                   <label> <input type="radio" name="basicinfor.workYear" value="4" id="workYear_1" style="border:0;" <c:if test="${basicinfor.workYear=='4'}">checked="checked"</c:if> /><em>10年以上</em></label>
               </p></div>
            <div class="info"><label class="title2">证明人：</label><input name="basicinfor.reference" id="reference" type="text" size="30" value="${basicinfor.reference}"/></div>
            <div class="info"><label class="title2">证明人手机：</label><input name="basicinfor.referPhone" id="referPhone" type="text" size="30" value="${basicinfor.referPhone}"/></div>
            
             <div id="savebtn" > <input type="button" class="save_bt" value="保存且继续" onclick="submitCheck3('0')"/><input type="button" class="skip_bt" onclick="submitCheck3('1');secBoard('hotnews_caption','list',4);" value="跳过并继续" /></div>
                         <div class="info"><input name="basicInforId" id="basicInforId" type="hidden" size="30" value="${basicInforId}" /></div>
         	</form>
         </div>
       
<div class="normal" id="list_4">
            <form name="form" method="post" id="list4">
            <div class="info"><label class="title">月均收入：</label><input name="basicinfor.monthAverEarn" id="monthAverEarn" type="text" size="30" value="${basicinfor.monthAverEarn}"/></div>
            <div class="info"><label class="title">收入构成描述：</label><textarea name="basicinfor.incomeDescribe" id="incomeDescribe" class="person">${basicinfor.incomeDescribe}</textarea></div>
            <div class="info"><label class="title">月均支出：</label><input name="basicinfor.monthAverPay" id="monthAverPay" type="text" size="30" value="${basicinfor.monthAverPay}"/></div>
            <div class="info"><label class="title">支出构成描述：</label><textarea name="basicinfor.payDescribe" id="payDescribe" class="person">${basicinfor.payDescribe}</textarea></div>
            <div class="info"><label class="title">住房条件：</label> 
               <p style="font-size:14px;">
                   <label style="padding-right:15px;"> <input type="radio" name="basicinfor.houseCondition" value="0" id="houseCondition_0" style="border:0;" <c:if test="${basicinfor.houseCondition=='0'}">checked="checked"</c:if> /><em>有商品房 </em></label>
                   <label> <input type="radio" name="basicinfor.houseCondition" value="1" id="houseCondition_1" style="border:0;" <c:if test="${basicinfor.houseCondition=='1'}">checked="checked"</c:if> /><em>其他房（非商品房）</em></label>
                   <label> <input type="radio" name="basicinfor.houseCondition" value="2" id="houseCondition_2" style="border:0;" <c:if test="${basicinfor.houseCondition=='2'}">checked="checked"</c:if> /><em>无房</em></label>
                  
               </p>
            </div>
            
            <div class="info"><label class="title">房产价值：</label><input name="basicinfor.houseValue" id="houseValue" type="text" size="30" value="${basicinfor.houseValue}"/></div>
            <div class="info"><label class="title">是否购车：</label> 
               <p style="font-size:14px;">
                   <label style="padding-right:15px;"> <input type="radio" name="basicinfor.ifByCar" value="0" id="ifByCar_0" style="border:0;" <c:if test="${basicinfor.ifByCar=='0'}">checked="checked"</c:if> /><em>是 </em></label>
                   <label> <input type="radio" name="basicinfor.ifByCar" value="1" id="ifByCar_1" style="border:0;" <c:if test="${basicinfor.ifByCar=='1'}">checked="checked"</c:if> /><em>否</em></label>
                </p>
            </div>
            <div class="info"><label class="title">车辆价值：</label><input name="basicinfor.carValue" id="carValue" type="text" size="30" value="${basicinfor.carValue}"/></div>
            <div class="info"><label class="title">参股企业名称：</label><input name="basicinfor.joinCompanyName" id="joinCompanyName" type="text" size="30" value="${basicinfor.joinCompanyName}"/></div>
            <div class="info"><label class="title">参股企业出资额：</label><input name="basicinfor.joinCompanyResource" id="joinCompanyResource" type="text" size="30" value="${basicinfor.joinCompanyResource}"/></div>
            <div class="info"><label class="title">其他资产描述：</label><textarea name="basicinfor.otherResourceDescribe" id="otherResourceDescribe" class="person">${basicinfor.otherResourceDescribe}</textarea></div>
            
            <div id="savebtn" > <input type="button" class="save_bt" onclick="submitCheck4('0')" value="保存且继续" /><input type="button" class="skip_bt" onclick="submitCheck4('1');secBoard('hotnews_caption','list',5);" value="跳过并继续" /></div>
            <div class="info"><input name="basicInforId" id="basicInforId" type="hidden" size="30" value="${basicInforId}" /></div>
         </form>
         </div>
       
 <div class="normal" id="list_5">
 		 <form name="form" method="post" id="list5">
            <div class="info"><label class="title">房产地址：</label><input name="basicinfor.houseAddress" id="houseAddress" type="text" size="30" value="${basicinfor.houseAddress}"/></div>
            <div class="info"><label class="title">建筑面积：</label><input name="basicinfor.buildingArea" id="buildingArea" type="text" size="30" value="${basicinfor.buildingArea}"/>平米</div>
            <div class="info"><label class="title">建筑年份：</label><input name="basicinfor.buildingYear" id="buildingYear" type="text" size="30" value="${basicinfor.buildingYear}"/></div>
            <div class="info"><label class="title">供款状况：</label>
               <p style="font-size:14px;">
                   <label style="padding-right:15px;"> <input type="radio" name="basicinfor.contributionStatus" value="0" id="contributionStatus_0" style="border:0;" <c:if test="${basicinfor.contributionStatus=='0'}">checked="checked"</c:if> /><em>按揭中</em></label>
                   <label> <input type="radio" name="basicinfor.contributionStatus" value="1" id="contributionStatus_1" style="border:0;" <c:if test="${basicinfor.contributionStatus=='1'}">checked="checked"</c:if> /><em>已供完还款</em></label>
                </p>
            </div>
            <div class="info"><label class="title">所有权人1：</label><input name="basicinfor.ownerOne" id="ownerOne" type="text" size="20" value="${basicinfor.ownerOne}"/><label class="tip">产权份额</label><input name="basicinfor.ownerOneShare" id="ownerOneShare" type="text" size="20" value="${basicinfor.ownerOneShare}"/>%</div>
            <div class="info"><label class="title">所有权人2：</label><input name="basicinfor.ownerTwo" id="ownerTwo" type="text" size="20" value="${basicinfor.ownerTwo}"/><label class="tip">产权份额</label><input name="basicinfor.ownerTwoShare" id="ownerTwoShare" type="text" size="20" value="${basicinfor.ownerTwoShare}"/>%</div>
            <div class="info"><label class="tip" style=" color:#ff5800;">若房产尚在按揭中, 请填写：</label></div>
            <div class="info"><label class="title">贷款年限：</label><input name="basicinfor.loanYear" id="loanYear" type="text" size="30" value="${basicinfor.loanYear}"/></div>
            <div class="info"><label class="title">每月供款：</label><input name="basicinfor.monthCont" id="monthCont" type="text" size="30" value="${basicinfor.monthCont}"/></div>
            <div class="info"><label class="title">尚欠贷款余额：</label><input name="basicinfor.oweLoanBala" id="oweLoanBala" type="text" size="30" value="${basicinfor.oweLoanBala}"/></div>
            <div class="info"><label class="title">按揭银行：</label><input name="basicinfor.mortgageContact" id="mortgageContact" type="text" size="30" value="${basicinfor.mortgageContact}"/></div>
            
            <div id="savebtn" > <input type="button" onclick="submitCheck5('0')" class="save_bt" value="保存且继续" /><input type="button" class="skip_bt" onclick="submitCheck5('1');secBoard('hotnews_caption','list',6);" value="跳过并继续" /></div>
            <div class="info"><input name="basicInforId" id="basicInforId" type="hidden" size="30" value="${basicInforId}" /></div>
           </form>
         </div>
       
<div class="normal" id="list_6">
		   <form name="form" method="post" id="list6">
            <div class="info"><label class="title2">第一联保人：</label><input name="basicinfor.cosignerOne" id="cosignerOne" type="text" size="30" value="${basicinfor.cosignerOne}"/><span id="cosignerOneTip"></span></div>
            <div class="info"><label class="title2">关系：</label>
                <p style="font-size:14px;">
                   <label style="padding-right:15px;"> <input type="radio" name="basicinfor.cosignerOneRelation" value="0" id="cosignerOneRelation_0" style="border:0;" <c:if test="${basicinfor.cosignerOneRelation=='0'}">checked="checked"</c:if> /><em>家庭成员</em></label>
                   <label> <input type="radio" name="basicinfor.cosignerOneRelation" value="1" id="cosignerOneRelation_1" style="border:0;" <c:if test="${basicinfor.cosignerOneRelation=='1'}">checked="checked"</c:if>/><em>朋友</em></label>
                   <label> <input type="radio" name="basicinfor.cosignerOneRelation" value="2" id="cosignerOneRelation_1" style="border:0;" <c:if test="${basicinfor.cosignerOneRelation=='2'}">checked="checked"</c:if> /><em>商业伙伴</em></label>
                  
               </p>
            </div>
            <div class="info"><label class="title2">手机号码：</label><input name="basicinfor.cosignerOnePhone" id="cosignerOnePhone" type="text" size="30" value="${basicinfor.cosignerOnePhone}"/><span id="cosignerOnePhoneTip"></span></div>
            <div class="info"><label class="title2">第二联保人：</label><input name="basicinfor.cosignerTwo" id="cosignerTwo" type="text" size="30" value="${basicinfor.cosignerTwo}"/><span id="cosignerTwoTip"></span></div>
            <div class="info"><label class="title2">关系：</label>
                <p style="font-size:14px;">
                   <label style="padding-right:15px;"> <input type="radio" name="basicinfor.cosignerTwoRelation" value="0" id="cosignerTwoRelation_0" style="border:0;" <c:if test="${basicinfor.cosignerTwoRelation=='0'}">checked="checked"</c:if> /><em>家庭成员</em></label>
                   <label> <input type="radio" name="basicinfor.cosignerTwoRelation" value="1" id="cosignerTwoRelation_1" style="border:0;" <c:if test="${basicinfor.cosignerTwoRelation=='1'}">checked="checked"</c:if> /><em>朋友</em></label>
                   <label> <input type="radio" name="basicinfor.cosignerTwoRelation" value="2" id="cosignerTwoRelation_2" style="border:0;" <c:if test="${basicinfor.cosignerTwoRelation=='2'}">checked="checked"</c:if> /><em>商业伙伴</em></label>
                  
               </p>
            </div>
           <div class="info"><label class="title2">手机号码：</label><input name="basicinfor.cosignerTwoPhone" id="cosignerTwoPhone" type="text" size="30" value="${basicinfor.cosignerTwoPhone}"/><span id="cosignerTwoPhoneTip"></span></div>
           <div id="savebtn" > <input type="button" class="save_bt" onclick="submitCheck6()" value="保存且继续" /></div>
           <div class="info"><input name="basicInforId" id="basicInforId" type="hidden" size="30" value="${basicInforId}" /></div>
           <div class="info"><input name="mark" id="mark" type="hidden" size="30" value="${mark}" /></div>
           </form>
         </div>
      </div>
      
      <script type="text/javascript" src="<%=basePath%>/js/tabs.js" language="javascript"></script>
              

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
<script type="text/javascript" src="../js/service.js">	</script> --%>
<!--右边漂浮 结束-->

</body>
</html>