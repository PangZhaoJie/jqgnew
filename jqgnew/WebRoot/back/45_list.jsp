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
<link href="../css/user/user_repay.css" type="text/css" rel="stylesheet"/>
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
	  var page=$MP("#currentPageId").val();
	  var pageNum=$MP("#pageNumId").val();
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
	  var userId = $MP("#userId").val();
	  if(num==1){
 		  	
		  $MP("#page1-1").attr("href","<%=path %>/back/translate5.action?mark="+mark+"&page=1&startTime="+startTime+"&endTime="+endTime+"&dealUser="+dealUser+"&userName="+userName+"&state="+state+"&lessMoney="+lessMoney+"&mostMoney="+mostMoney+"&userId="+userId); 
	  }
	  if(num==3){
		  if(page<pageNum){
			  $MP("#page1-3").attr("href","<%=path %>/back/translate5.action?mark="+mark+"&page="+(Number(page)+1)+"&startTime="+startTime+"&endTime="+endTime+"&dealUser="+dealUser+"&userName="+userName+"&state="+state+"&lessMoney="+lessMoney+"&mostMoney="+mostMoney+"&userId="+userId);
		  }else{
			  $MP("#page1-3").attr("href","<%=path %>/back/translate4.action?mark="+mark+"&page="+(Number(page))+"&startTime="+startTime+"&endTime="+endTime+"&dealUser="+dealUser+"&userName="+userName+"&state="+state+"&lessMoney="+lessMoney+"&mostMoney="+mostMoney+"&userId="+userId);
		  }
	  }
	  if(num==2){
		  if(page>1){
			  $MP("#page1-2").attr("href","<%=path %>/back/translate5.action?mark="+mark+"&page="+(Number(page)-1)+"&startTime="+startTime+"&endTime="+endTime+"&dealUser="+dealUser+"&userName="+userName+"&state="+state+"&lessMoney="+lessMoney+"&mostMoney="+mostMoney+"&userId="+userId);
		  }else{
			  $MP("#page1-2").attr("href","<%=path %>/back/translate4.action?mark="+mark+"&page="+(Number(page))+"&startTime="+startTime+"&endTime="+endTime+"&dealUser="+dealUser+"&userName="+userName+"&state="+state+"&lessMoney="+lessMoney+"&mostMoney="+mostMoney+"&userId="+userId);
		  }
	  }
	  if(num==4){
	  	  if(p==null || p==""|| parseInt(p)< 1 || parseInt(p)> parseInt(pageNum)){
	  			alert("请输入正确的页数！");
	  			return false;
	  		}
		  if(p<1){
			  p=1;
		  }else if(p>pageNum){
			  p=pageNum;
		  }
	 
			  $MP("#page1-4").attr("href","<%=path %>/back/translate5.action?mark="+mark+"&page="+Number(p)+"&startTime="+startTime+"&endTime="+endTime+"&dealUser="+dealUser+"&userName="+userName+"&state="+state+"&lessMoney="+lessMoney+"&mostMoney="+mostMoney+"&userId="+userId);
	  }
	  if(num==5){
 		  	
		  $MP("#page1-5").attr("href","<%=path %>/back/translate5.action?mark="+mark+"&page="+Number(pageNum)+"&startTime="+startTime+"&endTime="+endTime+"&dealUser="+dealUser+"&userName="+userName+"&state="+state+"&lessMoney="+lessMoney+"&mostMoney="+mostMoney+"&userId="+userId); 
	  }
	  
  }
  function search(){
	  var form=document.getElementById("search");
	  form.action="<%=path %>/back/translate5.action?mark=0";
	  form.submit();
  }
  function startCheck(updown){
		if(updown==2){
			alert("导出成功");
		}
	}
</script>
<style>
.mem_table{overflow:scroll; clear: both;}
.mem_table table{ width:2500px; text-align:left; }
.mem_table table th { padding:0 3px; text-align: left; font-weight:normal;}
.mem_table table td {/* border-bottom: 1px solid #e3e6eb;*/ padding:0 5px; text-indent:3px;}
</style>

</head>


<body onload="startCheck(${updown})">

	<%-- <div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="javascript:void(0);" onclick="parent.frames.topFrame.overall();">首页</a></li>
    <li><a href="javascript:void(0);" onclick="parent.frames.topFrame.user();">充值提现</a></li>
    <li><a href="#">提现申请总列表</a></li>
    </ul>
    </div> --%>
    
    <div class="rightinfo">
    
    <div class="tools">
       <div id="panel">
              <div class="panel_title">搜索提现</div>
              <div class="panel_nr fn_cle">
  <form enctype="multipart/form-data" method="post" id="search">
  <input type="hidden" id="userId" name="userId" value="${userId}"/>    
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
          <div class="uew-select" style="float:left; line-height:34px;margin-left: 10px"><div class="uew-select-value ue-state-default" style="width: 200px;"><em class="uew-icon uew-icon-triangle-1-s"></em></div> 
                 <select name="state" id="state" class="input" style="width: 210px;">
                  <option value="">-请选择-</option> 
                  <option <c:if test="${state=='0'}">selected="selected"</c:if> value="0">待审核</option> 
                  <option <c:if test="${state=='1'}">selected="selected"</c:if> value="1">审核通过，处理中</option> 
                  <option <c:if test="${state=='2'}">selected="selected"</c:if> value="2">已提现</option> 
                  <option <c:if test="${state=='3'}">selected="selected"</c:if> value="3">审核未通过</option> 
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
     <ul class="toolbar">
     <c:if test="${sessionScope.powerss[101]==1}">
        <li  id="ID1" onclick="addCheck('1')"><span><img src="images/t06.png" /></span>搜索提现</li>
        <li  id="ID2" style="display:none" onclick="addCheck('2')"><span><img src="images/t06.png" /></span>搜索完毕</li>
        </c:if>
        <c:if test="${sessionScope.powerss[52]==1}">
        <li> <a href = "javascript:void(0)" class="green" id="upId" onclick = "updownCheck();">将当前条件下数据导出为Excel</a></li>
     	</c:if>
     </ul>
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
        	    <c:if test="${translate.state==0}">待审核</c:if>
        	    <c:if test="${translate.state==1}">审核通过，处理中</c:if>
        	    <c:if test="${translate.state==2}">已提现</c:if>
        	    <c:if test="${translate.state==3}">审核未通过</c:if>
        	</td>
        	<td>${translate.dealUser}</td>
        	<td><fmt:formatDate value="${translate.dealTime}" pattern="yyyy-MM-dd HH:mm" /></td>
        	<td>${translate.texplain}</td>
        	</tr>      	
        </thead>
        </c:forEach>
    </table>
  
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
         <input name="mark" value="${mark}" id="mark" style="display: none"/>
     </div>
   
   <div id="fade" class="black_overlay"></div>                  
    
    </div>
<!--      <div id="hide1" class="white_content" style="height: 220px;width:500px;"> -->
<%-- 	            <div class="light_title"><span>提示信息</span> <a href = "javascript:void(0)" onclick = "document.getElementById('hide1').style.display='none';document.getElementById('fade').style.display='none'" class="close"><img src="../images/close1.png" /></a></div> --%>
<!-- 	            <div class="letter_info">导出路径： -->
	                  <input type="hidden" value="<%=request.getRealPath("/")%>\uploadPhoto\提现信息.xls" id="urlId" style="width: 280px"/>
<!-- 	                   <input type="button" value="确定" class="sure" onclick="updownCheck()"  -->
<!-- 	                  style="margin-top: 30px" name=""/> -->
 		        	  
<!-- 	            </div> -->
            
<!--       </div>  -->
       <script type="text/javascript">
      	function updownCheck(){
     	  var  $M=jQuery.noConflict();
 		  var page=$M("#currentPageId").val();
      	  var form=document.getElementById("search");
     	  form.action="<%=path %>/back/upLoadTranslate5.action?updown=1&mark=0&page="+page+"&urlstr="+encodeURI(encodeURI($M("#urlId").val()));
     	  form.submit();
    	}
      </script>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>

</body>

</html>
