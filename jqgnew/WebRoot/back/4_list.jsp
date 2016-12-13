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
<title>充值提现</title>
<link href="<%=basePath%>back/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>/css/user/user_repay.css" type="text/css" rel="stylesheet"/>
<!-- <link href="css/select.css" rel="stylesheet" type="text/css" /> -->
<link href="<%=basePath%>back/css/tabs.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>back/js/jquery.js"></script>

<!--弹窗中的滑动js-->
<script type="text/javascript" src="<%=basePath%>back/js/jquery.tabso_yeso.js"></script>
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
<script type="text/javascript" src="<%=basePath%>back/laydate/laydate.js"></script>
<script type="text/javascript">
  function toMoneyPage(num){
	  var  $MP=jQuery.noConflict();
	  var page=$MP("#currentPageId").val();
	  var pageNum=$MP("#pageNumId").val();
	  var p=$MP("#pId").val();
	  var flag=$MP("#flag").val();
	  var mark=$MP("#mark").val();
 	  var userName=$MP("#demo3").val();
	  var vipName=$MP("#demo4").val();
	  var recharge=$MP("#demo6").val();
	  var startTime=$MP("#demo1").val();
	  var endTime=$MP("#demo2").val();
	  var rechargeStatu=document.getElementsByName("rechargeStatus");
	  var rechargeStatus=-1;
	  var userId = $MP("#userId").val();
	  for(var i=0;i<rechargeStatu.length;i++){
		  if(rechargeStatu[i].checked){
			 rechargeStatus=rechargeStatu[i].value; 
		  }
	  }
	  if(mark!=null){//搜索标志
		  if(recharge!=null && recharge!='undefined'){
		  recharge=encodeURI(encodeURI(recharge));
		  }
	  }
   	  if(num==1){
   		  	
			  $MP("#page1-1").attr("href","<%=path %>/back/offline.action?flag="+flag+"&mark="+mark+"&userName="+userName+"&vipName="+vipName+"&rechargeStatus="+rechargeStatus+"&startTime="+startTime+"&endTime="+endTime+"&recharge="+recharge+"&page=1"+"&userId="+userId); 
	  }
	  if(num==3){
		     if(Number(page)<Number(pageNum)){
			  $MP("#page1-3").attr("href","<%=path %>/back/offline.action?flag="+flag+"&mark="+mark+"&userName="+userName+"&vipName="+vipName+"&rechargeStatus="+rechargeStatus+"&startTime="+startTime+"&endTime="+endTime+"&recharge="+recharge+"&page="+(Number(page)+1)+"&userId="+userId);
		     }else{
				  $MP("#page1-3").attr("href","<%=path %>/back/offline.action?flag="+flag+"&mark="+mark+"&userName="+userName+"&vipName="+vipName+"&rechargeStatus="+rechargeStatus+"&startTime="+startTime+"&endTime="+endTime+"&recharge="+recharge+"&page="+(Number(page))+"&userId="+userId); 
		     }
	  }
	  if(num==2){
		  if(page>1){
			  $MP("#page1-2").attr("href","<%=path %>/back/offline.action?flag="+flag+"&mark="+mark+"&userName="+userName+"&vipName="+vipName+"&rechargeStatus="+rechargeStatus+"&startTime="+startTime+"&endTime="+endTime+"&recharge="+recharge+"&page="+(Number(page)-1)+"&userId="+userId);
			  
		  }else{
			  $MP("#page1-2").attr("href","<%=path %>/back/offline.action?flag="+flag+"&mark="+mark+"&userName="+userName+"&vipName="+vipName+"&rechargeStatus="+rechargeStatus+"&startTime="+startTime+"&endTime="+endTime+"&recharge="+recharge+"&page="+(Number(page))+"&userId="+userId);
		  }
	  }
	  if(num==4){
	  	  if(p==null || p=="" || parseInt(p)< 1 || parseInt(p)> parseInt(pageNum)){
	  			alert("请输入正确的页数！");
	  			return false;
	  		}
		  if(p<1){
			  p=1;
		  }else if(Number(p)>Number(pageNum)){
			  p=pageNum;
		  }
	 
			  $MP("#page1-4").attr("href","<%=path %>/back/offline.action?flag="+flag+"&mark="+mark+"&userName="+userName+"&vipName="+vipName+"&rechargeStatus="+rechargeStatus+"&startTime="+startTime+"&endTime="+endTime+"&recharge="+recharge+"&page="+Number(p)+"&userId="+userId);
	  }
	   if(num==5){
   		  	
			  $MP("#page1-5").attr("href","<%=path %>/back/offline.action?flag="+flag+"&mark="+mark+"&userName="+userName+"&vipName="+vipName+"&rechargeStatus="+rechargeStatus+"&startTime="+startTime+"&endTime="+endTime+"&recharge="+recharge+"&page="+Number(pageNum)+"&userId="+userId); 
	  }
	  
  }
  function search(){
	  var flag=document.getElementById("flag").value;
	  var form=document.getElementById("search");
	  form.action="<%=path %>/back/offline.action?flag="+flag+"&mark=0"
	  form.submit();
  }
  function startCheck(updown){
		if(updown==2){
			alert("导出成功");
		}
	}
</script>
<script language="JavaScript" src="<%=path %>/back/js/top.js"></script>
<style>
.mem_table{overflow:scroll; clear: both;}
.mem_table table{ width:2500px; text-align:left; }
.mem_table table th { padding:0 3px; text-align: left; font-weight:normal;}
.mem_table table td {/* border-bottom: 1px solid #e3e6eb;*/ padding:0 5px; text-indent:3px;}
</style>

</head>


<body onload="startCheck(${updown})">
	<input type="hidden" id="path" value="<%=path %>" />
	<%-- <div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="javascript:void(0);" onclick="parent.frames.topFrame.overall();">首页</a></li>
    <li><a href="javascript:void(0);" onclick="parent.frames.topFrame.user();">充值提现</a></li>
    <c:if test="${flag=='0'}">
    <li><a href="#">线上充值</a></li>
    </c:if>
    <c:if test="${flag=='1'}">
    <li><a href="#">线下充值</a></li>
    </c:if>
    <c:if test="${flag=='2'}">
    <li><a href="#">充值记录总列表</a></li>
    </c:if>
    </ul>
    </div> --%>
    
    <div class="rightinfo">
    
    <div class="tools">
       <div id="panel">
              <div class="panel_title">搜索充值</div>
              <div class="panel_nr fn_cle">
  <form enctype="multipart/form-data" method="post" id="search">   
  <input type="hidden" name="userId" id="userId" value="${userId}"/> 
    <dl class="line">
      <dt>会员名：</dt>
      <dd>
        <input type="text" name="userName" id="demo3" class="input" name="link_txt" size="40" value="${userName}">
        <span>不填则不限制</span>
      </dd>
    </dl>
	
    <dl class="line">
      <dt>处理人：</dt>
      <dd>
        <input type="text" name="vipName" id="demo4" class="input" name="link_href" size="40" value="${vipName}">
        <span>不填则不限制</span>
      </dd>
    </dl>
    <dl class="line">
      <dt>充值状态：</dt>
       <dd>
	  	 <p style="float:left; line-height:34px;">
	  	   <label><input type="radio" name="rechargeStatus"  id="RadioGroup1_1" <c:if test="${rechargeStatus==1}">checked="checked"</c:if> value="1" />充值成功 </label>
	  	  
<!-- 	  	   <label><input type="radio" name="rechargeStatus" value="4" id="RadioGroup1_2"/>签名不符</label> -->
	  	   
	  	   <label><input type="radio" name="rechargeStatus"  id="RadioGroup1_3" <c:if test="${rechargeStatus==2}">checked="checked"</c:if> value="2"/>充值失败 </label>
	  	  
	  	   </p>
           <span>不填则不限制</span>
       </dd>
    </dl>
    <dl class="line">
      <dt>充值方式：</dt>
      <dd>
          <div class="uew-select" style="float:left; line-height:34px;margin-left: 9px"><div class="uew-select-value ue-state-default" style="width: 200px;"><em class="uew-icon uew-icon-triangle-1-s"></em></div> 
                 <select name="recharge" class="input" style="width: 210px;" id="demo6">
                  <option value="">-请选择-</option> 
                  <option <c:if test="${recharge=='线下充值'}">selected="selected"</c:if> value="线下充值">线下充值</option> 
                  <option <c:if test="${recharge=='国付宝'}">selected="selected"</c:if> value="国付宝">国付宝</option> 
                  <option <c:if test="${recharge=='环迅支付'}">selected="selected"</c:if> value="环迅支付">环迅支付</option>
                  <option <c:if test="${recharge=='网银在线'}">selected="selected"</c:if> value="网银在线">网银在线</option> 
                  <option <c:if test="${recharge=='宝付'}">selected="selected"</c:if> value="宝付">宝付</option> 
                  <option <c:if test="${recharge=='盛付通'}">selected="selected"</c:if> value="盛付通">盛付通</option> 
                  <option <c:if test="${recharge=='财付通'}">selected="selected"</c:if> value="财付通">财付通</option> 
                  <option <c:if test="${recharge=='汇朝'}">selected="selected"</c:if> value="汇朝">汇朝</option> 
                  <option <c:if test="${recharge=='易生支付'}">selected="selected"</c:if> value="易生支付">易生支付</option> 
                  <option <c:if test="${recharge=='中国移动支付'}">selected="selected"</c:if> value="中国移动支付">中国移动支付</option>
                 </select>
           </div> 
               
        <span>不填则不限制</span> 
      </dd>
    </dl>
    <dl class="line">
      <dt>时间(开始)：</dt>
      <dd>
	  	<input class="laydate-icon" name="startTime" id="demo1" value="${startTime}">
        <a style="color: #7d7d7d">时间段</a>
      </dd>
    </dl>
    <dl class="line">
      <dt>时间(结束)：</dt>
      <dd>
        <input  class="laydate-icon" name="endTime" id="demo2" value="${endTime}">
        <a style="color: #7d7d7d">时间段</a>
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
     <ul class="toolbar">
     <c:if test="${sessionScope.powerss[100]==1}">
        <li  id="ID1" onclick="addCheck('1')"><span><img src="images/t06.png" /></span>搜索充值</li>
        <li  id="ID2" style="display:none" onclick="addCheck('2')"><span><img src="images/t06.png" /></span>搜索完毕</li>
     </c:if>
     <c:if test="${sessionScope.powerss[50]==1}">
        <c:if test="${flag==2}"><li><a href = "javascript:void(0)" class="green" id="upId" onclick = "updownCheck();">将当前条件下数据导出为Excel</a></li></c:if>
     </c:if>
     </ul>
   </div>
    
    
    <table class="tablelist">
    	<thead>
    	<tr>
        <th width="5%">ID</th>
        <th width="6%">会员名</th>
        <th width="6%">充值方式</th>
        <th width="6%">充值金额</th>
        <th width="6%">充值奖励</th>
        <th width="6%">充值状态</th>
        <th width="10%">充值时间</th>
        <c:if test="${flag=='0'}"><th width="10%">对账订单号</th></c:if>
        <c:if test="${flag=='1'}"><th width="10%">账单流水号</th></c:if>
        <c:if test="${flag=='2'}"><th width="12%">对账订单号（账单流水号）</th></c:if>
        <th width="6%">处理人</th>
        <th width="8%">操作</th>
       
        </tr>
        </thead>
        
        <c:forEach var="onlinerecharge" items="${offlinerecharges}">
        <tbody>
          <tr>
          <td>${onlinerecharge.offlineRechargeId}</td>
          <td>${onlinerecharge.uservip.userName}</td>
          <td>${onlinerecharge.recharge}</td>
          <td>
          <fmt:formatNumber value="${onlinerecharge.rechargeAmount}" pattern="#0.00"/>
<%--           ${onlinerecharge.rechargeAmount} --%>
          </td>
          <c:if test="${onlinerecharge.recharge=='线下充值'}">
          <td>
          <fmt:formatNumber value="${onlinerecharge.offReward}" pattern="#0.00"/>
          </td>
          </c:if>
          <c:if test="${onlinerecharge.recharge!='线下充值'}"><td>--</td></c:if>
          <td>
          	  <c:if test="${onlinerecharge.rechargeStatus==1}">
          		充值成功
          	  </c:if>
          	  <c:if test="${onlinerecharge.rechargeStatus==2}">
          		充值失败
          	  </c:if>
          </td>
          <td><fmt:formatDate value="${onlinerecharge.rechargeTime}" pattern="yyyy-MM-dd HH:mm" /></td>
          <td>
          <c:if test="${onlinerecharge.recharge=='线下充值'}">
          <c:if test="${onlinerecharge.billSerialNum==null}">
                               无
          </c:if>
          <c:if test="${onlinerecharge.billSerialNum!=null}">
          ${onlinerecharge.billSerialNum}
          </c:if>
          </c:if>
          <c:if test="${onlinerecharge.recharge!='线下充值'}">
          <c:if test="${onlinerecharge.checkOrderNum==null}">
                               无
          </c:if>
          <c:if test="${onlinerecharge.checkOrderNum!=null}">
          ${onlinerecharge.checkOrderNum}
          </c:if>
          </c:if>
          <c:if test="${onlinerecharge.recharge=='线下充值'}">
          <a href="javascript:void(0);"class="tablelink"  onclick="show();pic('<%=path %>${onlinerecharge.printProof}')">
                              凭证
          </a>
          </c:if>
          </td>
          <td>
          	<c:if test="${onlinerecharge.recharge=='线下充值'}">${onlinerecharge.vipName}</c:if>
          	<c:if test="${onlinerecharge.recharge!='线下充值'}">--</c:if>
          </td>
          <td>
               <c:if test="${onlinerecharge.recharge=='线下充值'}">
               	  <c:if test="${onlinerecharge.vipName==null || onlinerecharge.vipName==''}">
               	  <c:if test="${sessionScope.powerss[38]==1}">
               	  <a class="green" href="<%=path %>/back/offline.action?flag=${flag}&ok=1&offlineRechargeId=${onlinerecharge.offlineRechargeId}&userId=${onlinerecharge.uservip.userId}">充值成功</a> 
                  <a class="green" href="<%=path %>/back/offline.action?flag=${flag}&ok=0&offlineRechargeId=${onlinerecharge.offlineRechargeId}&userId=${onlinerecharge.uservip.userId}">充值失败</a> 
                  </c:if>
                  </c:if> 
               	  <c:if test="${onlinerecharge.vipName!=null && onlinerecharge.vipName!=''}">--</c:if>
               </c:if>
               <c:if test="${onlinerecharge.recharge!='线下充值'}">--</c:if>
          </td></tr></tbody>
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
        <li class="paginItem"><span class="skip"><a href="#" style="width: 50px" onclick="toMoneyPage('4')" id="page1-4">转到</a><input name="page" type="text" id="pId" value="" size="3" style="width:31px;height:28px; border:1px solid #DDD; text-align:center;line-height:30px ;color:#3399d5" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>页</span>
        </li>
        <li class="paginItem"><a href="" style="width: 60px;border-left:dashed;border:1px solid #DDD;" onclick="toMoneyPage('5')" id="page1-5">最后一页</a></li>
         </ul>
         <input name="currentPage" value="${page}" id="currentPageId" style="display: none"/>
         <input name="total" value="${pageCount}" id="pageNumId" style="display: none"/>
         <input name="flag" value="${flag}" id="flag" style="display: none"/>
         <input name="mark" value="${mark}" id="mark" style="display: none"/>
      </div>      
     <!--首先设置一个层:-->
                    
<!--      <div id="hide1" class="white_content" style="height: 220px;width:500px;"> -->
<%-- 	            <div class="light_title"><span>提示信息</span> <a href = "javascript:void(0)" onclick = "document.getElementById('hide1').style.display='none';document.getElementById('fade').style.display='none'" class="close"><img src="../images/close1.png" /></a></div> --%>
<!-- 	            <div class="letter_info">导出路径： -->
	                  <input type="hidden" value="<%=request.getRealPath("/")%>\uploadPhoto\充值信息.xls" id="urlId" style="width: 280px"/>
<!-- 	                   <input type="button" value="确定" class="sure" onclick="updownCheck()"  -->
<!-- 	                  style="margin-top: 30px" name=""/> -->
 		        	  
<!-- 	            </div> -->
            
<!--       </div>  -->
	<div id="fade" class="black_overlay"></div>
	 <div id="pop" class="pop" style="display:none">
         <div class="pop_head"><a class="close" href="javascript:void(0);" onclick="hide()"></a></div>
         <div class="pop_body" style="margin-top:-340px;margin-left:610px;"><img id="picture1" style="width:400px;"/></div>
     </div> 
      <script type="text/javascript">
      	function updownCheck(){
     	  var  $MP=jQuery.noConflict();
 		  var page=$MP("#currentPageId").val();
      	  var form=document.getElementById("search");
     	  form.action="<%=path %>/back/upLoadOffline.action?updown=1&flag=2&mark=0&page="+page+"&urlstr="+encodeURI(encodeURI($MP("#urlId").val()));
     	  form.submit();
    	}
      	function pic(path){
      	   input=document.getElementById("picture1");
      	   input.src=path;
      		 
        }
      </script>
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
	
<script type="text/javascript" src="<%=basePath%>back/js/pic.js"></script>
</body>

</html>
