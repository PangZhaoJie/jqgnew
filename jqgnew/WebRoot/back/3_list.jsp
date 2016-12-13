<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
  <%@ taglib uri="/struts-tags" prefix="s"%>
  	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<%
String path = request.getContextPath();if(path!=null && !path.equals("")){	if(path.contains("/")){		if(path.equals("/")){path = "";	}}else{	path += "/";}} 
String basePath = "";if(request.getServerPort()==80){basePath = request.getScheme()+"://"+request.getServerName()+path+"/";}else{basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";}    
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>会员列表</title>
<link href="<%=path %>/back/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=path %>/back/css/mem.css" rel="stylesheet" type="text/css" />
<link href="<%=path %>/back/css/tabs.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path %>/back/js/jquery.js"></script>
<!--弹窗中的滑动js-->
<script type="text/javascript" src="<%=path %>/back/js/jquery.tabso_yeso.js"></script>
<script type="text/javascript" src="<%=path %>/back/js/pic.js"></script>
<script type="text/javascript">
function getJSONDatas(currentPage,pageSize) {
    var a = $("#demo2").val();
    if(a=="-请选择时间-"){a="";}else{a= a}
    var b = $("#demo1").val();
    if(b=="-请选择时间-"){b="";}else{b= b}
  
	var url = "<%=path %>/overall/searchUsers.action?currentPage="+currentPage+"&pageSize="+pageSize+"&username="+
	encodeURI(encodeURI($("#link_txt").val()))+"&starttime="+b+"&endtime="+a+"&managerName="+$("#managerName").val()+"&vipRadio="+$("#vipRadio").val()+"&temp=" +Math.random();


	$.getJSON(url,function(data){
	
		var cp = data.currentPage;
		var tp = data.totalPage;
		var $table = $ ("#table");
		$table.empty();
		str = '<thead><tr><th width="50px"  align="center" >ID</th><th width="80px"  align="center" >用户名</th><th width="60px"  align="center" >内部发标人</th><th width="80px"  align="center" >真实姓名</th><th width="50px"  align="center" >身份证号</th><th width="125px" align="center" >身份证照片</th></th><th width="80px"  align="center" >推荐人</th> <th width="80px"  align="center" >可用金额</th><th width="80px" align="center" >冻结金额</th><th width="80px" align="center" >待收金额</th><th width="80px" align="center" >信用额度</th> <th width="80px" align="center" >专属客服</th><th width="60px" align="center" >是(否)VIP</th> <th width="130px" align="center" >VIP到期时间</th><th width="130px" align="center" >注册时间</th><th width="320px"  align="center" >操作</th></tr></thead>';
		var jsonRoot = data.jsonRoot;
		for(var i = 0; i < jsonRoot.length; i++) {
			str += '<tbody><tr><td>'+ jsonRoot[i].id+ '</td><td>'
	
				   + decodeURI(jsonRoot[i].name, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].userType, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].realname, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].idnum, "utf-8") +"</td><td>"
				   +' <a href="javascript:void(0);"class="tablelink"  onclick="show();pic('+"'<%=path %>"+jsonRoot[i].fimage+"'" +');">[正面查看]</a> <a href="javascript:void(0);" class="tablelink" onclick="show1();pic2('+"'<%=path %>"+jsonRoot[i].nimage+"'" +');">[反面查看]</a>'  
				   +"</td><td>"
				   + decodeURI(jsonRoot[i].recomname, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].amoney, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].fmoney, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].dmoney, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].question, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].managerName, "utf-8")+"</td><td>"
				   + decodeURI(jsonRoot[i].isVip, "utf-8")+"</td><td>"
				   + decodeURI(jsonRoot[i].endTime, "utf-8")+"</td><td>"
				   + decodeURI(jsonRoot[i].time, "utf-8") +"</td><td>"
				   +'<a href="<%=path %>/back/3_revise.jsp?id='+jsonRoot[i].id+' ">[调整余额]</a><a href="<%=path %>/back/3_credit.jsp?id='+jsonRoot[i].id+' ">[调整授信]</a>  <a href="<%=path %>/back/3_edit.jsp?userType='+jsonRoot[i].userType+'&id='+jsonRoot[i].id+' ">[修改信息] </a><a href="<%=path%>/back/offline.action?flag='+2+'&userId='+jsonRoot[i].id+' ">[充值记录]</a><a href="<%=path%>/back/translate5?userId='+jsonRoot[i].id+' ">[提现记录]</a><a href="<%=path%>/money/toMoneyhistorydetail?userId='+jsonRoot[i].id+' ">[资金记录]</a><a href="<%=path%>/back/findRecord?userId='+jsonRoot[i].id+' ">[投标记录]</a>'  +"</td></tr>  </tbody>";

		}
		$table.html(str);
		document.getElementById("page").style.display="none";
		  if(tp !=1)
			{		
		document.getElementById("pages").style.display="";
	     $ ("#currentPages").text("第"+cp+"页");
         $ ("#totalPages").text("共"+tp+"页");
         $ ("#tpagesnone").val(tp);
	     $ ("#firstPages").attr("href","javascript:getJSONDatas(1,10)");
	     $ ("#prevPages").attr("href","javascript:getJSONDatas("+(parseInt(cp)-1)+",10)");
	     $ ("#nextPages").attr("href","javascript:getJSONDatas("+(parseInt(cp)+1)+",10)");
	     $ ("#lastPages").attr("href","javascript:getJSONDatas("+(parseInt(tp))+",10)");
			}
			else{
				document.getElementById("pages").style.display="none";
			}
	});
}
function getSex(){
  var value="";
  var radio=document.getElementsByName("radio");
  for(var i=0;i<radio.length;i++){
	if(radio[i].checked==true){
	  value=radio[i].value;
	  break;
	}
  }
 jQuery("#vipRadio").val(value);
}
function tiaozhuan() {
    var size = $("#size").val();
    var totalpage = $("#tpagenone").val();
    if(size==null || size==""){
  		alert("请输入要跳转的页数！");
  		return false;
  	}
    if(parseInt(size)<1||(parseInt(size))>(parseInt(totalpage)))
    {
    	alert("请输入正确的页数");
    	return false;
    }
    else{
    	 getJSONData(size,10);  
    }
   
}
function tiaozhuans() {
    var size = $("#sizes").val();
    var totalpage = $("#tpagesnone").val();
  	if(size==null || size==""){
  		alert("请输入要跳转的页数！");
  		return false;
  	}
    if(parseInt(size)<1||parseInt(size)>parseInt(totalpage))
    {
    	alert("请输入正确的页数")
    	return false;
    }else{
    	getJSONDatas(size,10);  
    }
    
}

function getJSONData(currentPage,pageSize) {

	var url = "<%=path %>/overall/findUsers.action?currentPage="+currentPage+"&pageSize="+pageSize+"&temp=" +Math.random();

	$.getJSON(url,function(data){
	
		var cp = data.currentPage;
		var tp = data.totalPage;
		var $table = $ ("#table");
		$table.empty();
		str = '<thead><tr><th width="50px"  align="center" >ID</th><th width="80px"  align="center" >用户名</th><th width="60px"  align="center" >内部发标人</th><th width="80px"  align="center" >真实姓名</th><th width="50px"  align="center" >身份证号</th><th width="100px" align="center" >身份证照片</th></th><th width="80px"  align="center" >推荐人</th> <th width="80px"  align="center" >可用金额</th><th width="80px" align="center" >冻结金额</th><th width="80px" align="center" >待收金额</th><th width="80px" align="center" >信用额度</th> <th width="80px" align="center" >专属客服</th><th width="60px" align="center" >是(否)VIP</th><th width="120px" align="center" >VIP到期时间</th><th width="120px" align="center" >注册时间</th><th width="320px"  align="center" >操作</th></tr></thead>';
		var jsonRoot = data.jsonRoot;
		for(var i = 0; i < jsonRoot.length; i++) {
		if(jsonRoot[i].fimage.length>0 && jsonRoot[i].nimage.length>0 ){
			str += '<tbody><tr><td>'+ jsonRoot[i].id+ '</td><td>'
	
				   + decodeURI(jsonRoot[i].name, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].userType, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].realname, "utf-8") +"</td><td>"
				   + jsonRoot[i].idnum +"</td><td>"
				   //+'<c:if test="${jsonRoot[i].fimage.length >0}"><a href="javascript:void(0);"class="tablelink"  onclick="show();pic('+"'<%=path %>"+jsonRoot[i].fimage+"'" +');">[正面查看]</a></c:if><c:if test="${jsonRoot[i].nimage.length>0}"><a href="javascript:void(0);" class="tablelink" onclick="show1();pic2('+"'<%=path %>"+jsonRoot[i].nimage+"'" +');">[反面查看]</a></c:if>'
				   +'<a href="javascript:void(0);"class="tablelink"  onclick="show();pic('+"'<%=path %>"+jsonRoot[i].fimage+"'" +');">[正面查看]</a><a href="javascript:void(0);" class="tablelink" onclick="show1();pic2('+"'<%=path %>"+jsonRoot[i].nimage+"'" +');">[反面查看]</a>'
				   +"</td><td>"
				   + decodeURI(jsonRoot[i].recomname, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].amoney, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].fmoney, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].dmoney, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].question, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].managerName, "utf-8")+"</td><td>"
				   + decodeURI(jsonRoot[i].isVip, "utf-8")+"</td><td>"
				   + decodeURI(jsonRoot[i].endTime, "utf-8")+"</td><td>"
				   + decodeURI(jsonRoot[i].time, "utf-8") +"</td><td>"
				   +'<c:if test="${sessionScope.powerss[21]==1}"><a href="<%=path%>/back/3_revise.jsp?id='+jsonRoot[i].id+' ">[调整余额]</a>   </c:if><c:if test="${sessionScope.powerss[22]==1}"><a href="<%=path%>/back/3_credit.jsp?id='+jsonRoot[i].id+' ">[调整授信]</a> </c:if>   <c:if test="${sessionScope.powerss[23]==1}"><a href="<%=path%>/back/3_edit.jsp?userType='+jsonRoot[i].userType+'&id='+jsonRoot[i].id+' ">[修改信息] </a> </c:if> <a href="<%=path%>/back/offline.action?flag='+2+'&userId='+jsonRoot[i].id+' ">[充值记录]</a><a href="<%=path%>/back/translate5?userId='+jsonRoot[i].id+' ">[提现记录]</a><a href="<%=path%>/money/toMoneyhistorydetail?userId='+jsonRoot[i].id+' ">[资金记录]</a><a href="<%=path%>/back/findRecord?userId='+jsonRoot[i].id+' ">[投标记录]</a>'  +"</td></tr>  </tbody>";
     }else{
               str += '<tbody><tr><td>'+ jsonRoot[i].id+ '</td><td>'
				   + decodeURI(jsonRoot[i].name, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].userType, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].realname, "utf-8") +"</td><td>"
				   + jsonRoot[i].idnum +"</td><td>"
				   +"</td><td>"
				   + decodeURI(jsonRoot[i].recomname, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].amoney, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].fmoney, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].dmoney, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].question, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].managerName, "utf-8")+"</td><td>"
				   + decodeURI(jsonRoot[i].isVip, "utf-8")+"</td><td>"
				   + decodeURI(jsonRoot[i].endTime, "utf-8")+"</td><td>"
				   + decodeURI(jsonRoot[i].time, "utf-8") +"</td><td>"
				   +'<c:if test="${sessionScope.powerss[21]==1}"><a href="<%=path%>/back/3_revise.jsp?id='+jsonRoot[i].id+' ">[调整余额]</a>   </c:if><c:if test="${sessionScope.powerss[22]==1}"><a href="<%=path%>/back/3_credit.jsp?id='+jsonRoot[i].id+' ">[调整授信]</a> </c:if>   <c:if test="${sessionScope.powerss[23]==1}"><a href="<%=path%>/back/3_edit.jsp?userType='+jsonRoot[i].userType+'&id='+jsonRoot[i].id+' ">[修改信息] </a> </c:if><a href="<%=path%>/back/offline.action?flag='+2+'&userId='+jsonRoot[i].id+' ">[充值记录]</a><a href="<%=path%>/back/translate5?userId='+jsonRoot[i].id+' ">[提现记录]</a><a href="<%=path%>/money/toMoneyhistorydetail?userId='+jsonRoot[i].id+' ">[资金记录]</a><a href="<%=path%>/back/findRecord?userId='+jsonRoot[i].id+' ">[投标记录]</a>'  +"</td></tr>  </tbody>";
     
     }
		}
		$table.html(str);
		document.getElementById("pages").style.display="none";
		if(tp !=1)
		{
			document.getElementById("page").style.display="";
		     $ ("#tpagenone").val(tp);
		     $ ("#currentPage").text("第"+cp+"页");
	         $ ("#totalPage").text("共"+tp+"页");
		     $ ("#firstPage").attr("href","javascript:getJSONData(1,10)");
		     $ ("#prevPage").attr("href","javascript:getJSONData("+(parseInt(cp)-1)+",10)");
		     $ ("#nextPage").attr("href","javascript:getJSONData("+(parseInt(cp)+1)+",10)");
		     $ ("#lastPage").attr("href","javascript:getJSONData("+(parseInt(tp))+",10)");

		}
	else{
		document.getElementById("page").style.display="none";
	}
	});
}

$(function() {  
	getJSONData(1,10);  
});
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


</head>


<body  style="min-width: 3000px">

	<%-- <div class="place">
    <span>位置：</span>
    <ul class="placeul">
<li><a href="javascript:void(0);" onclick="parent.frames.topFrame.overall();">首页</a></li>
    <li><a href="3_list.jsp" target="rightFrame">会员列表</a></li>
    </ul>
    </div> --%>
    
    <div class="rightinfo">
    
    <div class="tools">
       <div id="panel">
              <div class="panel_title">搜索/筛选会员</div>
              <div class="panel_nr fn_cle">
                 <form enctype="multipart/form-data" action="/Admin/global/addFriend.html" method="post">

    <dl class="line">
      <dt>会员名：</dt>
      <dd>
        <input type="text" value="" id="link_txt" class="input" name="link_txt" size="40">
        <span>不填则不限制</span>
      </dd>
    </dl>
	<dl class="line">
      <dt>专属客服：</dt>
      <dd>
        <input type="text" value="" id="managerName" class="input" name="managerName" size="40">
        <span>不填则不限制</span>
      </dd>
    </dl>
    <dl class="line">
      <dt>是(否)VIP：</dt>
      <dd>
      	<label> <input type="radio" name="radio" value="1" id="raidio1" />是</label>
        <label> <input type="radio" name="radio" value="0" id="raidio2" />否</label>
        <a style="color: #7d7d7d">不选则不限制</a>
      </dd>
    </dl>
 
    <dl class="line">
      <dt>注册时间(开始)：</dt>
      <dd>
	  	<input class="laydate-icon" id="demo1" value="-请选择时间-">
        <a style="color: #7d7d7d">只选开始时间则查询从开始时间往后所有</a>
      </dd>
    </dl>
    <dl class="line">
      <dt>注册时间(结束)：</dt>
      <dd>
        <input  class="laydate-icon" id="demo2" value="-请选择时间-">
        <a style="color: #7d7d7d">只选结束时间则查询从结束时间往前所有</a>
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
	  <input type="hidden" disabled="disabled" value="" id="fid" name="fid">
	  <input type="text" value="" id="vipRadio" name="vipRadio"/>
	 
	   <input type="button" name="Submit" value="搜索" class="btn" onclick="getSex();getJSONDatas(1,10);addCheck('2')"/>
     
    </div>
	</form>
              </div>
         </div>
    	<ul class="toolbar">
    	 <c:if test="${sessionScope.powerss[24]==1 }">
         <li  id="ID1" onclick="addCheck('1')"><span><img src="<%=path %>/back/images/t06.png" /></span>搜索/筛选会员</li>
         <li  id="ID2" style="display:none" onclick="addCheck('2')"><span><img src="<%=path %>/back/images/t06.png" /></span>搜索/筛选完毕</li>
         <li><a href = "<%=path %>/overall/findUsersExcel.action?urlstr=<%=request.getRealPath("/")%>\uploadPhoto\会员信息.xls" class="green" id="upId" >将当前条件下数据导出为Excel</a></li>
         </c:if>
        </ul>
     </div>
    
    
    <table class="tablelist" id="table">
    	<thead>
	    	<tr>
	        </tr>
        </thead>
        <tbody>
   </tbody>
    </table>
    <input type="text" id="tpagenone" style="display: none"/>
<input type="text" id="tpagesnone" style="display: none"/>
     <div class="page" id="page" style="display: none">
    <em id="currentPage">第页</em>&nbsp; &nbsp; 
  	<em id="totalPage">共页</em>&nbsp; &nbsp; 
  	<em ><a id="firstPage" href="">首页</a></em>&nbsp; &nbsp; 
  	<em ><a id="prevPage" href="">上一页</a></em>&nbsp; &nbsp; 
  	<em ><a id="nextPage" href="">下一页</a></em>&nbsp; &nbsp; 
  	<em ><a id="lastPage" href="">最后一页</a></em>&nbsp; &nbsp; 
  	<em class="skip">转到<input name="page" type="text" id="size" size="5" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>页</em><a href="#" id="tiaozhuan" onclick="tiaozhuan()">跳转</a>
    </div>
       <div class="page" id="pages" style="display: none">
    <em id="currentPages">第页</em>&nbsp; &nbsp; 
  	<em id="totalPages">共页</em>&nbsp; &nbsp; 
  	<em ><a id="firstPages" href="">首页</a></em>&nbsp; &nbsp; 
  	<em ><a id="prevPages" href="">上一页</a></em>&nbsp; &nbsp; 
  	<em ><a id="nextPages" href="">下一页</a></em>&nbsp; &nbsp; 
  	<em ><a id="lastPages" href="">最后一页</a></em>&nbsp; &nbsp; 
  	<em class="skip">转到<input name="page" type="text" id="sizes" size="5" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>页</em><a href="#" id="tiaozhuans" onclick="tiaozhuans()">跳转</a>
    </div>
  <!--  弹窗 -->
   <div id="light1" class="white_content">
       <div class="light_title">
          <span>jiep的资料</span> 
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
          <span>jiep的资料</span> 
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
          <span>jiep的资料</span> 
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
    <div id="fade" class="black_overlay"></div>        
     <!--首先设置一个层:-->
  <div id="pop" class="pop" style="display:none;margin-left:500px;margin-top:-20px;">
    <div class="pop_head"><a class="close" href="javascript:void(0);" onclick="hide()"></a></div>
    <div class="pop_body"><img id="picture1" style="width:400px;"/></div>
    
   </div>
    <div id="pop1" class="pop" style="display:none;margin-left:500px;margin-top:-20px;">
    <div class="pop_head"><a class="close" href="javascript:void(0);" onclick="hide1()"></a></div>
    <div class="pop_body"><img id="picture2" style="width:400px;"/></div>
  </div>
  <script type="text/javascript">
   function pic(path){
	input=  document.getElementById("picture1");
	    input.src=path;
	 
	}
   function pic2(path){
	input=  document.getElementById("picture2");
	    input.src=path;
	 
	}
</script>
</body>

</html>
