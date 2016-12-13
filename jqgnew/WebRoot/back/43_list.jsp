<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();if(path!=null && !path.equals("")){	if(path.contains("/")){		if(path.equals("/")){path = "";	}}else{	path += "/";}} 
String basePath = "";if(request.getServerPort()==80){basePath = request.getScheme()+"://"+request.getServerName()+path+"/";}else{basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";}    
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>提现管理</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<!-- <link href="css/select.css" rel="stylesheet" type="text/css" /> -->
<link href="css/tabs.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>

<!--弹窗中的滑动js-->
<script type="text/javascript" src="js/jquery.tabso_yeso.js"></script>
<script type="text/javascript">
$(document).ready(function($){
	
	//默认选项卡切换
	$("#normaltab1").tabso({
		cntSelect:"#normalcon1",
		tabEvent:"mouseover",
		tabStyle:"normal"
	});
	
	//默认选项卡切换
	$("#normaltab2").tabso({
		cntSelect:"#normalcon2",
		tabEvent:"mouseover",
		tabStyle:"normal"
	});
	
	//默认选项卡切换
	$("#normaltab3").tabso({
		cntSelect:"#normalcon3",
		tabEvent:"mouseover",
		tabStyle:"normal"
	});
	
});
</script>
<!--折叠js-->
<script type="text/javascript"> 

function addCheck(num){
	if(num==1){
		 $("#panel").show();
		 $("#panel1").hide();
		 $("#ID1").hide();
		 $("#ID2").show();
		  $("#ID4").hide();
		 $("#ID3").show();		 
		}else if(num==3){
		  $("#panel").hide();
		  $("#panel1").show();
		  $("#ID2").hide();
		 $("#ID1").show();	
		  $("#ID3").hide();
		 $("#ID4").show();	
		}else  {
		  $("#panel").hide();
		  $("#panel1").hide();
		   $("#ID1").show();
		 $("#ID3").show();	
		  $("#ID2").hide();
		 $("#ID4").hide();
		}
	}
 
</script>
<!--日历插件 js-->
<script type="text/javascript" src="laydate/laydate.js"></script>
<script type="text/javascript">
  function toMoneyPage(num){
	  var  $MP=jQuery.noConflict();
	  var page=Number($MP("#currentPageId").val());
	  var pageNum=Number($MP("#pageNumId").val());
	  var p=$MP("#pId").val();
	  var mark=$MP("#mark").val();
	  //六个值
      var startTime=$MP("#demo1").val();
	  var endTime=$MP("#demo2").val();
	  var dealUser=$MP("#dealUser").val();
	  var userName=$MP("#userName").val();
	  var state=$MP("#state").val();
	  var lessMoney=$MP("#lessMoney").val();
	  var mostMoney=$MP("#mostMoney").val();
	  if(num==1){
 		  	
		  $MP("#page1-1").attr("href","<%=path %>/back/translate3.action?mark="+mark+"&page=1&startTime="+startTime+"&endTime="+endTime+"&dealUser="+dealUser+"&userName="+userName+"&state="+state+"&lessMoney="+lessMoney+"&mostMoney="+mostMoney); 
	  }

	  if(num==3){
		  if(page<pageNum){
			  $MP("#page1-3").attr("href","<%=path %>/back/translate3.action?mark="+mark+"&page="+(Number(page)+1)+"&startTime="+startTime+"&endTime="+endTime+"&dealUser="+dealUser+"&userName="+userName+"&state="+state+"&lessMoney="+lessMoney+"&mostMoney="+mostMoney);
		  }else{
			  $MP("#page1-3").attr("href","<%=path %>/back/translate3.action?mark="+mark+"&page="+(Number(page))+"&startTime="+startTime+"&endTime="+endTime+"&dealUser="+dealUser+"&userName="+userName+"&state="+state+"&lessMoney="+lessMoney+"&mostMoney="+mostMoney);
		  }
	  }
	  if(num==2){
		  if(page>1){
			  $MP("#page1-2").attr("href","<%=path %>/back/translate3.action?mark="+mark+"&page="+(Number(page)-1)+"&startTime="+startTime+"&endTime="+endTime+"&dealUser="+dealUser+"&userName="+userName+"&state="+state+"&lessMoney="+lessMoney+"&mostMoney="+mostMoney);
		  }else{
			  $MP("#page1-2").attr("href","<%=path %>/back/translate3.action?mark="+mark+"&page="+(Number(page)+1)+"&startTime="+startTime+"&endTime="+endTime+"&dealUser="+dealUser+"&userName="+userName+"&state="+state+"&lessMoney="+lessMoney+"&mostMoney="+mostMoney);
		  }
	  }
	  if(num==4){
	  	 if(parseInt(p)< 1 || parseInt(p)> parseInt(pageNum)|| p==null || p==""){
		  		alert("请输入正确的页数！");
		  		return false;
		  }
		  if(p<1){
			  p=1;
		  }else if(p>pageNum){
			  p=pageNum;
		  }
	 
			  $MP("#page1-4").attr("href","<%=path %>/back/translate3.action?mark="+mark+"&page="+Number(p)+"&startTime="+startTime+"&endTime="+endTime+"&dealUser="+dealUser+"&userName="+userName+"&state="+state+"&lessMoney="+lessMoney+"&mostMoney="+mostMoney);
	  }
	  if(num==5){
 		  	
		  $MP("#page1-5").attr("href","<%=path %>/back/translate3.action?mark="+mark+"&page="+Number(pageNum)+"&startTime="+startTime+"&endTime="+endTime+"&dealUser="+dealUser+"&userName="+userName+"&state="+state+"&lessMoney="+lessMoney+"&mostMoney="+mostMoney); 
	  }
	  
  }
  function search(){
  	  var form=document.getElementById("search");
	  form.action="<%=path %>/back/translate3.action?mark=0";
	  form.submit();
  }
</script>

</head>


<body>

	<%-- <div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="javascript:void(0);" onclick="parent.frames.topFrame.overall();">首页</a></li>
    <li><a href="javascript:void(0);" onclick="parent.frames.topFrame.user();">充值提现</a></li>
    <li><a href="#">已提现</a></li>
    </ul>
    </div> --%>
    
    <div class="rightinfo">
    
    <div class="tools">
       <div id="panel">
              <div class="panel_title">搜索提现</div>
              <div class="panel_nr fn_cle">
  <form enctype="multipart/form-data" method="post" id="search">    
    <dl class="line">
      <dt>会员名：</dt>
      <dd>
        <input type="text" name="userName" id="userName" class="input" name="link_txt" size="40" value="${userName}">
        <span>不填则不限制</span>
      </dd>
    </dl>
	  <dl class="line">
      <dt>提现时间(开始)：</dt>
      <dd>
	  	<input class="laydate-icon" name="startTime" id="demo1"  value="${startTime}">
        <a style="color: #7d7d7d">时间段</a>
      </dd>
    </dl>
    <dl class="line">
      <dt>提现时间(结束)：</dt>
      <dd>
        <input  class="laydate-icon" name="endTime" id="demo2" value="${endTime}">
        <a style="color: #7d7d7d">时间段</a>
      </dd>
    </dl>
    <dl class="line">
      <dt>提现金额（大于）：</dt>
      <dd>
        <input type="text" name="lessMoney" id="lessMoney" class="input" name="link_href" size="40" value="${lessMoney}">
        <span>不填则不限制</span>
      </dd>
    </dl>
 	<dl class="line">
      <dt>提现金额（小于）：</dt>
      <dd>
        <input type="text" name="mostMoney" id="mostMoney" class="input" name="link_href" size="40" value="${mostMoney}">
        <span>不填则不限制</span>
      </dd>
    </dl>
    <dl class="line">
      <dt>提现状态：</dt>
      <dd>
          <div class="uew-select" style="float:left; line-height:34px;"><div class="uew-select-value ue-state-default" style="width: 200px;"><em class="uew-icon uew-icon-triangle-1-s"></em></div> 
                 <select name="state" id="state" class="input" style="width: 210px;">
                  <option value="">-请选择-</option> 
                  <option <c:if test="${state=='2'}">selected="selected"</c:if> value="2">已提现</option> 
                 </select>
           </div> 
               
        <span>不填则不限制</span> 
      </dd>
    </dl>
    <dl class="line">
      <dt>处理人：</dt>
      <dd>
        <input type="text" name="dealUser" id="dealUser" class="input" name="link_href" size="40" value="${dealUser}">
        <span>不填则不限制</span>
      </dd>
    </dl>
  
	
 <script>
!function(){
laydate.skin('default');//切换皮肤，请查看skins下面皮肤库
laydate({elem: '#demo1'});
laydate({elem: '#demo2'});//绑定元素
}();
</script>  	 
    <div class="page_btn">
	<input type="button" onclick="search();" id="showwait" class="btn_b" value="搜索">
    </div>
 </form>
          </div>
   </div>
   <c:if test="${sessionScope.powerss[40]==1}">
     <ul class="toolbar">
        <li  id="ID1" onclick="addCheck('1')"><span><img src="images/t06.png" /></span>搜索提现</li>
        <li  id="ID2" style="display:none" onclick="addCheck('2')"><span><img src="images/t06.png" /></span>搜索完毕</li>
<!--         <li> <a href="#">将当前条件下数据导出为Excel</a></li> -->
     </ul>
     </c:if>
   </div>
    <table class="tablelist">
    	<thead>
    	<tr>
        <th width="4%">ID</th>
        <th width="5%">会员名</th>
        <th width="6%">真实姓名</th>
        <th width="15%">银行信息</th>
        <th width="6%">提现金额</th>
        <th width="7%">提现手续费</th>
        <th width="7%">应到帐金额</th>
        <th width="10%">提现时间</th>
        <th width="6%">提现状态</th>
        <th width="5%">处理人</th>
        <th width="10%">处理时间</th>
        <th width="6%">处理说明</th>
        <th width="4%">操作</th>
        </tr>
        </thead>
        <c:forEach var="translate" items="${translates}">
        <thead>
        	<tr>
        	<td>${translate.translateId}</td>
        	<td>${translate.uservip.userName}</td>
        	<td>${translate.uservip.realName}</td>
        	<td>${translate.bankNum}</td>
        	<td>
        	<fmt:formatNumber value="${translate.affectMoney}" pattern="#0.00"/>
        	</td>
        	<td><fmt:formatNumber value="${translate.fee}" pattern="#0.00"/></td>
        	<td>
        	<fmt:formatNumber value="${translate.affectMoney-translate.fee}" pattern="#0.00"/>
        	</td>
        	<td><fmt:formatDate value="${translate.occurTime}" pattern="yyyy-MM-dd HH:mm" /></td>
        	<td>
        	         已提现
        	</td>
        	<td>${translate.dealUser}</td>
        	<td><fmt:formatDate value="${translate.dealTime}" pattern="yyyy-MM-dd HH:mm" /></td>
        	<td>${translate.texplain}</td>
        	<td>--</td>
        	</tr>      	
        </thead>
        </c:forEach>
    </table>
   <div id="light1" class="white_content">
       <div class="light_title">
          <span>davidpaul840905的资料</span> 
          <a href = "javascript:void(0)" onclick = "document.getElementById('light1').style.display='none';document.getElementById('fade').style.display='none'" class="close"><img src="images/close1.png"/></a>
      </div>
      <div class="light_box">
        <ul class="tabbtn" id="normaltab1">
		   <li class="current">会员资料</li>
		   <li>账户资金</li>
		   <li>借款投资</li>
		 </ul>
         <!--tabbtn end-->
        <div class="tabcon" id="normalcon1">
         <div class="sublist">
          <dl class="lineD">
             <dt>用户名：</dt><dd>dggghh <a href="" ><img title="1级" src="images/a_ico.png"></a></dd>
             <dt>认证情况：</dt>
              <dd> 
                 <i class="sm">实名</i>
                 <i class="tel yes">手机</i>
                 <i class="mail yes">邮箱</i>
                 <i class="pay yes">现场</i>
                 <i class="video yes">视频</i>
                 <i class="datum yes">资料</i></dd>
         </dl>
		  <dl class="lineD"><dt>是否冻结：</dt><dd>未冻结</dd><dt>客户类型：</dt><dd>普通借款者</dd></dl>
		  <dl class="lineD"><dt>所属客服：</dt><dd>金钱柜-诺米</dd><dt>真实姓名：</dt><dd>张乐</dd></dl>
		  <dl class="lineD"><dt>性别：</dt><dd>男</dd><dt>职业：</dt><dd>司机</dd></dl>
		  <dl class="lineD"><dt>邮箱：</dt><dd>373018898@qq.com</dd><dt>投资积分：</dt><dd>1295</dd></dl>
		  <dl class="lineD"><dt>手机号码：</dt><dd>13403919180</dd><dt>年龄：</dt><dd>0</dd></dl>
		  <dl class="lineD"><dt>婚姻状况：</dt><dd>已婚</dd><dt>学历：</dt><dd>本科</dd></dl>
		  <dl class="lineD"><dt>身份证号：</dt><dd>410327198506180037</dd><dt>月收入：</dt><dd>5000.00元</dd></dl>
		  <dl class="lineD"><dt>银行帐号：</dt><dd>6210985010003605893</dd><dt>银行名称：</dt><dd>中国邮政储蓄银行</dd></dl>
		  <dl class="lineD"><dt>银行开户行：</dt><div id="xwidth">河南焦作中国邮政储蓄股份有限公司河南省孟州市赵和镇邮政所</div></dl>
		  <dl class="lineD"><dt>籍贯：</dt><div id="xwidth"></div></dl>
		  <dl class="lineD"><dt>居住地：</dt><div id="xwidth"></div></dl>
		  <dl class="lineD"><dt>个人描述：</dt><div id="xwidth"></div></dl>
		  <dl class="lineD"><dt>身份证：</dt><div id="xwidth"><a target="_blank" href="#"><img height="100px" src="images/id.jpg"></a></div></dl>
    
       </div> 
      <div class="sublist">
    
            <dl class="lineD"><dt class="title">资金情况</dt><dd class="xwidth">&nbsp;</dd></dl>
	   	    <dl class="lineD"><dt>帐户总额：</dt><dd>￥5,000</dd><dt>待收金额：</dt><dd>￥0.00</dd></dl>
		    <dl class="lineD"><dt>可用余额：</dt><dd>￥5,000</dd><dt>冻结金额：</dt><dd>￥0.00</dd></dl>

		    <dl class="lineD"><dt class="title">充值提现</dt><dd class="xwidth">&nbsp;</dd></dl>
		    <dl class="lineD"><dt>充值成功次数：</dt><dd>1次</dd><dt>充值成功金额：</dt><dd>￥5,000.00</dd></dl>
		    <dl class="lineD"><dt>提现成功次数：</dt><dd>0次</dd><dt>提现成功金额：</dt><dd>￥0.00</dd></dl>

		    <dl class="lineD"><dt class="title">额度情况</dt><dd class="xwidth">&nbsp;</dd></dl>
		    <dl class="lineD"><dt>借款信用额度：</dt><dd>￥0.00</dd><dt>可用信用额度：</dt><dd>￥0.00</dd></dl>
      
     </div>  
      <div class="sublist">
            <dl class="lineD"><dt class="title">借款金额统计</dt><dd class="xwidth">&nbsp;</dd></dl>
		    <dl class="lineD"><dt>借款总额：</dt><dd>￥0.00</dd><dt>累计亏盈：</dt><dd>￥0</dd></dl>
		    <dl class="lineD"><dt>已还总额：</dt><dd>￥0</dd><dt>待还总额：</dt><dd>￥0</dd></dl>
		    <dl class="lineD"><dt class="title">借还款次数统计</dt><dd class="xwidth">&nbsp;</dd></dl>
		    <dl class="lineD"><dt>借款成功次数：</dt><dd>0次</dd><dt>正常还款次数：</dt><dd>0次</dd></dl>
		    <dl class="lineD"><dt>提前还款次数：</dt><dd>0次</dd><dt>逾期还款次数：</dt><dd>0次</dd></dl>
		    <dl class="lineD"><dt>迟还次数：</dt><dd>0次</dd><dt>网站代还次数：</dt><dd>0次</dd></dl>
		    <dl class="lineD"><dt class="title">投资统计</dt><dd class="xwidth">&nbsp;</dd></dl>
		    <dl class="lineD"><dt>投资总额：</dt><dd>￥0</dd><dt>投标奖励：</dt><dd>￥0</dd></dl>
		    <dl class="lineD"><dt>已收总额：</dt><dd>￥0</dd><dt>待收总额：</dt><dd>￥0</dd></dl>
		    <dl class="lineD"><dt>推广奖励：</dt><dd>￥0.00</dd><dt>线下冲值奖励：</dt><dd>￥0.00</dd></dl>
		    <dl class="lineD"><dt>续投奖励：</dt><dd>￥0</dd><dt>&nbsp;</dt><dd>&nbsp;</dd></dl> 
       </div>
       </div> 
      </div>  
      
   </div>
   
   <div id="light2" class="white_content">
       <div class="light_title">
          <span>davidpaul840905的资料</span> 
          <a href = "javascript:void(0)" onclick = "document.getElementById('light2').style.display='none';document.getElementById('fade').style.display='none'" class="close"><img src="images/close1.png"/></a>
      </div>
      <div class="light_box">
        <ul class="tabbtn" id="normaltab2">
		   <li class="current">会员资料</li>
		   <li>账户资金</li>
		   <li>借款投资</li>
		 </ul>
         <!--tabbtn end-->
        <div class="tabcon" id="normalcon2">
         <div class="sublist">
          <dl class="lineD">
             <dt>用户名：</dt><dd>dggghh <a href="" ><img title="1级" src="images/a_ico.png"></a></dd>
             <dt>认证情况：</dt>
              <dd> 
                 <i class="sm">实名</i>
                 <i class="tel yes">手机</i>
                 <i class="mail yes">邮箱</i>
                 <i class="pay yes">现场</i>
                 <i class="video yes">视频</i>
                 <i class="datum yes">资料</i></dd>
         </dl>
		  <dl class="lineD"><dt>是否冻结：</dt><dd>未冻结</dd><dt>客户类型：</dt><dd>普通借款者</dd></dl>
		  <dl class="lineD"><dt>所属客服：</dt><dd>金钱柜-诺米</dd><dt>真实姓名：</dt><dd>张乐</dd></dl>
		  <dl class="lineD"><dt>性别：</dt><dd>男</dd><dt>职业：</dt><dd>司机</dd></dl>
		  <dl class="lineD"><dt>邮箱：</dt><dd>373018898@qq.com</dd><dt>投资积分：</dt><dd>1295</dd></dl>
		  <dl class="lineD"><dt>手机号码：</dt><dd>13403919180</dd><dt>年龄：</dt><dd>0</dd></dl>
		  <dl class="lineD"><dt>婚姻状况：</dt><dd>已婚</dd><dt>学历：</dt><dd>本科</dd></dl>
		  <dl class="lineD"><dt>身份证号：</dt><dd>410327198506180037</dd><dt>月收入：</dt><dd>5000.00元</dd></dl>
		  <dl class="lineD"><dt>银行帐号：</dt><dd>6210985010003605893</dd><dt>银行名称：</dt><dd>中国邮政储蓄银行</dd></dl>
		  <dl class="lineD"><dt>银行开户行：</dt><div id="xwidth">河南焦作中国邮政储蓄股份有限公司河南省孟州市赵和镇邮政所</div></dl>
		  <dl class="lineD"><dt>籍贯：</dt><div id="xwidth"></div></dl>
		  <dl class="lineD"><dt>居住地：</dt><div id="xwidth"></div></dl>
		  <dl class="lineD"><dt>个人描述：</dt><div id="xwidth"></div></dl>
		  <dl class="lineD"><dt>身份证：</dt><div id="xwidth"><a target="_blank" href="#"><img height="100px" src="images/id.jpg"></a></div></dl>
    
       </div> 
      <div class="sublist">
    
            <dl class="lineD"><dt class="title">资金情况</dt><dd class="xwidth">&nbsp;</dd></dl>
	   	    <dl class="lineD"><dt>帐户总额：</dt><dd>￥5,000</dd><dt>待收金额：</dt><dd>￥0.00</dd></dl>
		    <dl class="lineD"><dt>可用余额：</dt><dd>￥5,000</dd><dt>冻结金额：</dt><dd>￥0.00</dd></dl>

		    <dl class="lineD"><dt class="title">充值提现</dt><dd class="xwidth">&nbsp;</dd></dl>
		    <dl class="lineD"><dt>充值成功次数：</dt><dd>1次</dd><dt>充值成功金额：</dt><dd>￥5,000.00</dd></dl>
		    <dl class="lineD"><dt>提现成功次数：</dt><dd>0次</dd><dt>提现成功金额：</dt><dd>￥0.00</dd></dl>

		    <dl class="lineD"><dt class="title">额度情况</dt><dd class="xwidth">&nbsp;</dd></dl>
		    <dl class="lineD"><dt>借款信用额度：</dt><dd>￥0.00</dd><dt>可用信用额度：</dt><dd>￥0.00</dd></dl>
      
     </div>  
      <div class="sublist">
            <dl class="lineD"><dt class="title">借款金额统计</dt><dd class="xwidth">&nbsp;</dd></dl>
		    <dl class="lineD"><dt>借款总额：</dt><dd>￥0.00</dd><dt>累计亏盈：</dt><dd>￥0</dd></dl>
		    <dl class="lineD"><dt>已还总额：</dt><dd>￥0</dd><dt>待还总额：</dt><dd>￥0</dd></dl>
		    <dl class="lineD"><dt class="title">借还款次数统计</dt><dd class="xwidth">&nbsp;</dd></dl>
		    <dl class="lineD"><dt>借款成功次数：</dt><dd>0次</dd><dt>正常还款次数：</dt><dd>0次</dd></dl>
		    <dl class="lineD"><dt>提前还款次数：</dt><dd>0次</dd><dt>逾期还款次数：</dt><dd>0次</dd></dl>
		    <dl class="lineD"><dt>迟还次数：</dt><dd>0次</dd><dt>网站代还次数：</dt><dd>0次</dd></dl>
		    <dl class="lineD"><dt class="title">投资统计</dt><dd class="xwidth">&nbsp;</dd></dl>
		    <dl class="lineD"><dt>投资总额：</dt><dd>￥0</dd><dt>投标奖励：</dt><dd>￥0</dd></dl>
		    <dl class="lineD"><dt>已收总额：</dt><dd>￥0</dd><dt>待收总额：</dt><dd>￥0</dd></dl>
		    <dl class="lineD"><dt>推广奖励：</dt><dd>￥0.00</dd><dt>线下冲值奖励：</dt><dd>￥0.00</dd></dl>
		    <dl class="lineD"><dt>续投奖励：</dt><dd>￥0</dd><dt>&nbsp;</dt><dd>&nbsp;</dd></dl> 
       </div>
       </div> 
      </div>  
      
   </div>
   
   <div id="light3" class="white_content">
       <div class="light_title">
          <span>davidpaul840905的资料</span> 
          <a href = "javascript:void(0)" onclick = "document.getElementById('light3').style.display='none';document.getElementById('fade').style.display='none'" class="close"><img src="images/close1.png"/></a>
      </div>
      <div class="light_box">
        <ul class="tabbtn" id="normaltab3">
		   <li class="current">会员资料</li>
		   <li>账户资金</li>
		   <li>借款投资</li>
		 </ul>
         <!--tabbtn end-->
        <div class="tabcon" id="normalcon3">
         <div class="sublist">
          <dl class="lineD">
             <dt>用户名：</dt><dd>dggghh <a href="" ><img title="1级" src="images/a_ico.png"></a></dd>
             <dt>认证情况：</dt>
              <dd> 
                 <i class="sm">实名</i>
                 <i class="tel yes">手机</i>
                 <i class="mail yes">邮箱</i>
                 <i class="pay yes">现场</i>
                 <i class="video yes">视频</i>
                 <i class="datum yes">资料</i></dd>
         </dl>
		  <dl class="lineD"><dt>是否冻结：</dt><dd>未冻结</dd><dt>客户类型：</dt><dd>普通借款者</dd></dl>
		  <dl class="lineD"><dt>所属客服：</dt><dd>金钱柜-诺米</dd><dt>真实姓名：</dt><dd>张乐</dd></dl>
		  <dl class="lineD"><dt>性别：</dt><dd>男</dd><dt>职业：</dt><dd>司机</dd></dl>
		  <dl class="lineD"><dt>邮箱：</dt><dd>373018898@qq.com</dd><dt>投资积分：</dt><dd>1295</dd></dl>
		  <dl class="lineD"><dt>手机号码：</dt><dd>13403919180</dd><dt>年龄：</dt><dd>0</dd></dl>
		  <dl class="lineD"><dt>婚姻状况：</dt><dd>已婚</dd><dt>学历：</dt><dd>本科</dd></dl>
		  <dl class="lineD"><dt>身份证号：</dt><dd>410327198506180037</dd><dt>月收入：</dt><dd>5000.00元</dd></dl>
		  <dl class="lineD"><dt>银行帐号：</dt><dd>6210985010003605893</dd><dt>银行名称：</dt><dd>中国邮政储蓄银行</dd></dl>
		  <dl class="lineD"><dt>银行开户行：</dt><div id="xwidth">河南焦作中国邮政储蓄股份有限公司河南省孟州市赵和镇邮政所</div></dl>
		  <dl class="lineD"><dt>籍贯：</dt><div id="xwidth"></div></dl>
		  <dl class="lineD"><dt>居住地：</dt><div id="xwidth"></div></dl>
		  <dl class="lineD"><dt>个人描述：</dt><div id="xwidth"></div></dl>
		  <dl class="lineD"><dt>身份证：</dt><div id="xwidth"><a target="_blank" href="#"><img height="100px" src="images/id.jpg"></a></div></dl>
    
       </div> 
      <div class="sublist">
    
            <dl class="lineD"><dt class="title">资金情况</dt><dd class="xwidth">&nbsp;</dd></dl>
	   	    <dl class="lineD"><dt>帐户总额：</dt><dd>￥5,000</dd><dt>待收金额：</dt><dd>￥0.00</dd></dl>
		    <dl class="lineD"><dt>可用余额：</dt><dd>￥5,000</dd><dt>冻结金额：</dt><dd>￥0.00</dd></dl>

		    <dl class="lineD"><dt class="title">充值提现</dt><dd class="xwidth">&nbsp;</dd></dl>
		    <dl class="lineD"><dt>充值成功次数：</dt><dd>1次</dd><dt>充值成功金额：</dt><dd>￥5,000.00</dd></dl>
		    <dl class="lineD"><dt>提现成功次数：</dt><dd>0次</dd><dt>提现成功金额：</dt><dd>￥0.00</dd></dl>

		    <dl class="lineD"><dt class="title">额度情况</dt><dd class="xwidth">&nbsp;</dd></dl>
		    <dl class="lineD"><dt>借款信用额度：</dt><dd>￥0.00</dd><dt>可用信用额度：</dt><dd>￥0.00</dd></dl>
      
     </div>  
      <div class="sublist">
            <dl class="lineD"><dt class="title">借款金额统计</dt><dd class="xwidth">&nbsp;</dd></dl>
		    <dl class="lineD"><dt>借款总额：</dt><dd>￥0.00</dd><dt>累计亏盈：</dt><dd>￥0</dd></dl>
		    <dl class="lineD"><dt>已还总额：</dt><dd>￥0</dd><dt>待还总额：</dt><dd>￥0</dd></dl>
		    <dl class="lineD"><dt class="title">借还款次数统计</dt><dd class="xwidth">&nbsp;</dd></dl>
		    <dl class="lineD"><dt>借款成功次数：</dt><dd>0次</dd><dt>正常还款次数：</dt><dd>0次</dd></dl>
		    <dl class="lineD"><dt>提前还款次数：</dt><dd>0次</dd><dt>逾期还款次数：</dt><dd>0次</dd></dl>
		    <dl class="lineD"><dt>迟还次数：</dt><dd>0次</dd><dt>网站代还次数：</dt><dd>0次</dd></dl>
		    <dl class="lineD"><dt class="title">投资统计</dt><dd class="xwidth">&nbsp;</dd></dl>
		    <dl class="lineD"><dt>投资总额：</dt><dd>￥0</dd><dt>投标奖励：</dt><dd>￥0</dd></dl>
		    <dl class="lineD"><dt>已收总额：</dt><dd>￥0</dd><dt>待收总额：</dt><dd>￥0</dd></dl>
		    <dl class="lineD"><dt>推广奖励：</dt><dd>￥0.00</dd><dt>线下冲值奖励：</dt><dd>￥0.00</dd></dl>
		    <dl class="lineD"><dt>续投奖励：</dt><dd>￥0</dd><dt>&nbsp;</dt><dd>&nbsp;</dd></dl> 
       </div>
       </div> 
      </div>  
      
   </div>
   <div class="pagin">
    	<div class="message">共<i class="blue">${total}</i>条记录，当前显示第&nbsp;<i class="blue">${page}&nbsp;</i>页 ,共${pageCount}页 </div>
        <ul class="paginList">
         <li class="paginItem"><a href="" style="width: 50px"  onclick="toMoneyPage('1')" id="page1-1">首页</a></li>
        <li class="paginItem"><a href="" style="width: 60px" onclick="toMoneyPage('2')" id="page1-2">上一页</a></li>
        <li class="paginItem"><a href="" style="width: 60px" onclick="toMoneyPage('3')" id="page1-3">下一页</a></li>
        <li class="paginItem"><span class="skip"><a href="#" style="width: 50px" onclick="toMoneyPage('4')" id="page1-4">转到</a>&nbsp;<input name="page" type="text" id="pId" value="" size="3" style="width:31px;height:28px; border:1px solid #DDD; text-align:center;line-height:30px ;color:#3399d5" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>页</span>
        </li>
        <li class="paginItem"><a href="" style="width: 60px;border-left:dashed;border:1px solid #DDD;" onclick="toMoneyPage('5')" id="page1-5">最后一页</a></li>
         </ul>
         <input name="currentPage" value="${page}" id="currentPageId" style=""/>
         <input name="total" value="${pageCount}" id="pageNumId" style="display: none"/>
         <input name="mark" value="${mark}" id="mark" style="display: none"/>
     </div>
   
   <div id="fade" class="black_overlay"></div>              
    
    </div>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>

</body>

</html>
