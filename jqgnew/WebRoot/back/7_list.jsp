<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%    
	String path = request.getContextPath();if(path!=null && !path.equals("")){	if(path.contains("/")){		if(path.equals("/")){path = "";	}}else{	path += "/";}}    
	String basePath = "";if(request.getServerPort()==80){basePath = request.getScheme()+"://"+request.getServerName()+path+"/";}else{basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";}      
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
 <html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>会员账户</title>
<link href="<%=basePath%>/back/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>/css/user/user_public.css" type="text/css" rel="stylesheet"/>
<link href="<%=basePath%>/css/user/user_repay.css" type="text/css" rel="stylesheet"/>
 
<script type="text/javascript" src="<%=basePath%>/back/js/jquery.js"></script>
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
    <li><a href="<%=path %>/money/toUserMoney">资金统计</a></li>
    <li><a href="<%=path %>/money/toUserMoney">会员账户</a></li>
    </ul>
    </div> --%>
    
    <div class="rightinfo">
    
       <div class="tools">
       <div id="panel">
              <div class="panel_title">搜索/筛选会员</div>
              <div class="panel_nr fn_cle">
  <form enctype="multipart/form-data" onsubmit="" action="<%=path %>/money/toUserMoney" method="post" id="queryId">
    <dl class="line">
      <dt>会员名：</dt>
      <dd><input type="text" value="${userNameQuery}" id="demo3" class="input" name="userNameQuery" size="40"/> <span>不填则不限</span> </dd>
      
    </dl>
	
    <dl class="line">
      <dt>真实姓名：</dt>
      <dd><input type="text" value="${realName}" id="demo4" class="input" name="realName" size="40"/>  <span>不填则不限</span></dd>
       
    </dl>
    <dl class="line">
      <dt>余额：</dt>
       <dd>
	  	<select onchange="" id="demo1" class="input" name="mark" style="width:118px;margin-left: 8px" >
          <option value="0">--请选择--</option>
	  	  <option value="1" <c:if test="${mark==1 }">selected="selected"</c:if> >可用余额</option>
          <option value="2" <c:if test="${mark==2 }">selected="selected"</c:if>  >冻结金额</option>		
          <option value="3" <c:if test="${mark==3 }">selected="selected"</c:if>  >待收金额</option>
        
        </select>
         <input type="text" value="${moneyQuery}" id="demo2" class="input" name="moneyQuery" size="19" style="margin-left:8px;" />
        <span>不填则不限</span>
      </dd>
    </dl>
    
    
		 
    <div class="page_btn">
       <input type="submit" value="确定" onclick="" id="showwait" class="btn_b" style="cursor:pointer;"/>
    </div>
	</form>
              </div>
         </div>
         
    	<ul class="toolbar">
    	<c:if test="${sessionScope.powerss[99]==1}">
         <li  id="ID1" onclick="addCheck('1')"><span><img src="<%=basePath%>/back/images/t01.png" /></span>搜索/筛选会员</li>
         <li  id="ID2" style="display:none" onclick="addCheck('2')"><span><img src="<%=basePath%>/back/images/t01.png" /></span>搜索/筛选完毕</li>
         <li><span><img src="<%=basePath%>/back/images/t03.png" /></span>
         </c:if>
         <c:if test="${sessionScope.powerss[48]==1}">
         <a href = "javascript:void(0)" class="green" id="upId" onclick = "updownCheck()"> 将当前条件下数据导出为Excel</a></li>
        </c:if>
        </ul>
        
    </div>
    
     <div class="mem_table" >
      <table class="tablelist" width="2500" style="clear: both">
    	<thead>
    	<tr>
        
    <th >ID</th>
    <th >用户名</th>
    <th >真实姓名</th>
    <th >总资产</th>
    <th >可用余额</th>
    <th >冻结金额</th>

    <th >待收本息金额</th>
	<th >待收本金</th>
	<th >待收利息</th>
  	<th >待付利息</th>

 
	<th >累计提现手续费</th>
	<th >累计充值手续费</th>
    <th >累计提现金额</th>
    <th >累计充值金额</th>
    <th >累计支付佣金</th>
	<th >累计投标奖励</th>

	<th >累计推广奖励</th>
	<th >累计充值奖励</th>
	<th >累计续投奖励</th>

	<th>净赚利息</th>
	<th >净付利息</th>
   </tr>
        </thead>
        
        <c:forEach var="cd" items="${moneycountList}">
     <tr >
         <td>${cd.moneyCountId }</td>
        <td>${cd.uservip.userName}</td>
        <c:if test="${!empty cd.uservip.realName}"><td>${cd.uservip.realName}</td></c:if>
        <c:if test="${empty cd.uservip.realName}"><td>未实名认证</td></c:if>
        
        <td><fmt:formatNumber value="${cd.totalMoney}" pattern="0.00"/></td>
        <td><fmt:formatNumber value="${cd.availableMoney }" pattern="0.00"/></td>
        <td><fmt:formatNumber value="${cd.frozenMoney}" pattern="0.00"/></td>

	    <td><fmt:formatNumber value="${cd.collectTotalMoney }" pattern="0.00"/></td>
		<td><fmt:formatNumber value="${cd.dueInMoney}" pattern="0.00"/></td>
		<td><fmt:formatNumber value="${cd.collectInterestTotalFee}" pattern="0.00"/></td>
		
 		<td><fmt:formatNumber value="${cd.payInterestTotalFee }" pattern="0.00"/></td>
 
 
		
        <td><fmt:formatNumber value="${cd.accuWithdrawalMoney}" pattern="0.00"/></td>
        <td><fmt:formatNumber value="${cd.accuRechargeFee }" pattern="0.00"/></td>
		<td><fmt:formatNumber value="${cd.accuWidthdrawMoney }" pattern="0.00"/></td>
		<td><fmt:formatNumber value="${cd.accuRechargeMoney}" pattern="0.00"/></td>
		<td><fmt:formatNumber value="${cd.accuPayCommission }" pattern="0.00"/></td>
		<td><fmt:formatNumber value="${cd.accuBidReward }" pattern="0.00"/></td>

		<td><fmt:formatNumber value="${cd.accuPromoteReward  }" pattern="0.00"/></td>
		<td><fmt:formatNumber value="${cd.accuOfflineRechargeReward }" pattern="0.00"/></td>
		<td><fmt:formatNumber value="${cd.accuContinueBidReward  }" pattern="0.00"/></td>

		<td><fmt:formatNumber value="${cd.netEarnInterest  }" pattern="0.00"/></td>
		<td><fmt:formatNumber value="${cd.netPayInterest  }" pattern="0.00"/></td>
       </tr>
      
      </c:forEach>
 
     </table>
    
     </div>
     
 <div class="pagin">
    	<div class="message">共<i class="blue">${total}</i>条记录，当前显示第&nbsp;<i class="blue">${page}&nbsp;</i>页 ,共${pageCount}页 </div>
        <ul class="paginList">
         <li class="paginItem"><a href="" style="width: 50px"  onclick="toMoneyPage('1',${queryFlag})" id="page1-1">首页</a></li>
        <li class="paginItem"><a href="" style="width: 60px" onclick="toMoneyPage('2',${queryFlag})" id="page1-2">上一页</a></li>
        <li class="paginItem"><a href="" style="width: 60px" onclick="toMoneyPage('3',${queryFlag})" id="page1-3">下一页</a></li>
        <li class="paginItem"> 
        <a href="#" style="width: 50px" onclick="toMoneyPage('4',${queryFlag})" id="page1-4">转到</a>
        <input name="page" type="text" id="pId" value="" style="width:31px;height:28px; border:1px solid #DDD; text-align:center;line-height:30px ;color:#3399d5" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>页
        </li>
        <li class="paginItem"><a href="" style="width: 60px;border-left:dashed;border:1px solid #DDD;" onclick="toMoneyPage('5',${queryFlag})" id="page1-5">最后一页</a></li>
         </ul>
         <input name="currentPage" value="${page}" id="currentPageId" style="display: none"/>
         <input name="total" value="${pageCount}" id="pageNumId" style="display: none"/>
      </div>

    
       
    
    </div>
 	                  <input type="hidden" value="<%=request.getRealPath("/")%>\uploadPhoto\账户信息.xls" id="urlId" style="width: 300px"/><br/>
 		        	  
             
    <script type="text/javascript">
   
   	  function updownCheck(){
    	 var  $M=jQuery.noConflict();
		 var page=$M("#currentPageId").val();
     	 var form=document.getElementById("queryId");
    	 form.action="<%=path %>/money/upLoadUserMoney?updown=1&page="+page+"&urlstr="+encodeURI(encodeURI($M("#urlId").val()));
    	 form.submit();
   	  }
   
	  function toMoneyPage(num,num1){
			 
		  var  $MP=jQuery.noConflict();
		  var page=$MP("#currentPageId").val();
		  var pageNum=$MP("#pageNumId").val();
		  var userNameQuery=$MP("#demo3").val();
		  var moneyQuery=$MP("#demo2").val();
		  var mark=$MP("#demo1").val();
		  var realName=$MP("#demo4").val();
		  var p=$MP("#pId").val();
	   	  if(num==1){
 				  $MP("#page1-1").attr("href","<%=path %>/money/toUserMoney?userNameQuery="+userNameQuery+"&moneyQuery="+moneyQuery+"&realName="+realName+"&mark="+mark+"&page=1"); 
		  }
		  if(num==3){
 			  if(Number(page)<Number(pageNum)){
 				  $MP("#page1-3").attr("href","<%=path %>/money/toUserMoney?userNameQuery="+userNameQuery+"&moneyQuery="+moneyQuery+"&realName="+realName+"&mark="+mark+"&page="+(Number(page)+1));
			  }else{
 				  $MP("#page1-3").attr("href","<%=path %>/money/toUserMoney?userNameQuery="+userNameQuery+"&moneyQuery="+moneyQuery+"&realName="+realName+"&mark="+mark+"&page="+page);
			  }
		  }
		  if(num==2){
 			  if(page>1){
				  $MP("#page1-2").attr("href","<%=path %>/money/toUserMoney?userNameQuery="+userNameQuery+"&moneyQuery="+moneyQuery+ "&realName="+realName+"&mark="+mark+"&page="+(Number(page)-1));
			  }else{
 				  $MP("#page1-2").attr("href","<%=path %>/money/toUserMoney?userNameQuery="+userNameQuery+"&moneyQuery="+moneyQuery+"&realName="+realName+"&mark="+mark+"&page="+page);

			  }
		  }
		  if(num==4){
		  		if(p==null || p==""|| parseInt(p)< 1 || parseInt(p)> parseInt(pageNum)){
		  			alert("请输入正确的页数！");
		  			return false;
		  		}
			  if(p<1){
				  p=1;
			  }else if(Number(p)>Number(pageNum)){
				  p=pageNum ;
			  }
		 
				  $MP("#page1-4").attr("href","<%=path %>/money/toUserMoney?userNameQuery="+userNameQuery+"&moneyQuery="+moneyQuery+"&realName="+realName+"&mark="+mark+"&page="+Number(p));
		  }
		  if(num==5){
 				  $MP("#page1-5").attr("href","<%=path %>/money/toUserMoney?userNameQuery="+userNameQuery+"&moneyQuery="+moneyQuery+"&realName="+realName+"&mark="+mark+"&page="+Number(pageNum)); 
		  }
	  }

</script> 
  
</body>

</html>
