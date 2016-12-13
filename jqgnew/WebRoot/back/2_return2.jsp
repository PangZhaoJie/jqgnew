<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
<title>无标题文档</title>
<link href="<%=basePath%>/back/css/style.css" rel="stylesheet" type="text/css" />
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
 
</script>
<!--日历插件 js-->
<script type="text/javascript" src="<%=basePath%>/back/laydate/laydate.js"></script>

<script type="text/javascript">
 
</script>
</head>


<body onload="test()">

	<%-- <div class="place">
    <span>位置：</span>
    <ul class="placeul">
<li><a href="javascript:void(0);" onclick="parent.frames.topFrame.overall();">首页</a></li>
    <li><a style="cursor: pointer;" onclick="parent.frames.topFrame.money();">借款管理</a></li>
    <c:if test="${queryFlag==0 }"> <li><a href="<%=path %>/borrow/toBackLssuing?queryFlag=${queryFlag}">初审待审核借款</a></li></c:if>
    <c:if test="${queryFlag==1 }"> <li><a href="<%=path %>/borrow/toBackLssuing?queryFlag=${queryFlag}">复审待审核借款</a></li></c:if>
    <c:if test="${queryFlag==2}"> <li><a href="<%=path %>/borrow/toBackLssuing?queryFlag=${queryFlag}">招标中借款</a></li></c:if>
    <c:if test="${queryFlag==3}"> <li><a href="<%=path %>/borrow/toBackLssuing?queryFlag=${queryFlag}">还款中借款</a></li></c:if>
    <c:if test="${queryFlag==4}"> <li><a href="<%=path %>/borrow/toBackLssuing?queryFlag=${queryFlag}">已完成中借款</a></li></c:if>
    <c:if test="${queryFlag==5}"> <li><a href="<%=path %>/borrow/toBackLssuing?queryFlag=${queryFlag}">初审未通过借款</a></li></c:if>
    <c:if test="${queryFlag==6}"> <li><a href="<%=path %>/borrow/toBackLssuing?queryFlag=${queryFlag}">复审未通过借款</a></li></c:if>
    <c:if test="${queryFlag==-1}"> <li><a href="<%=path %>/borrow/toBackLssuing?queryFlag=${queryFlag}">已逾期借款</a></li></c:if>
   
    </ul>
    </div>  --%>
    
    <div class="rightinfo">
    
    <div class="tools">
       <div id="panel">
              <div class="panel_title">搜索/筛选借款</div>
              <div class="panel_nr fn_cle">
  <form enctype="multipart/form-data" onsubmit="" action="<%=path %>/borrow/toBackLssuing?queryFlag=${queryFlag}" method="post">
    <dl class="line">
      <dt>会员名：</dt>
      <dd>
        <input type="text"  id="demo3" class="input" name="userNameQuery" value="${userNameQuery }" size="40">
        <span>不填则不限制</span>
      </dd>
    </dl>
    
    <dl class="line">
      <dt>借款金额：</dt>
      <dd>
        <input type="text"   id="demo4" class="input" name="moneyQuery" value="${moneyQuery }" size="40">
        <span>不填则不限制</span>
      </dd>
    </dl>
    <dl class="line">
      <dt>借款时间(开始)：</dt>
      <dd>
	  	<input class="laydate-icon" id="demo1"   name="startTimeQuery" value="${startTimeQuery }">
        <a style="color: #7d7d7d">只选开始时间则查询从开始时间往后所有</a>
      </dd>
    </dl>
    <dl class="line">
      <dt>借款时间(结束)：</dt>
      <dd>
        <input  class="laydate-icon" id="demo2"   name="endTimeQuery" value="${endTimeQuery }">
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
      <input type="submit" value="添加" onclick="" id="showwait" class="btn_b">
    </div>
	</form>
              </div>
         </div>
        <c:if test="${sessionScope.powerss[98]==1 }">
    	<ul class="toolbar">
         <li  id="ID1" onclick="addCheck('1')"><span><img src="<%=basePath %>/back/images/t06.png" /></span>搜索筛选借款</li>
         <li  id="ID2" style="display:none" onclick="addCheck('2')"><span><img src="<%=basePath %>/back/images/t06.png" /></span>搜索完毕</li>
        </ul>
        </c:if>
    </div>
    <table class="tablelist">
    	<thead>
    	<tr>
        <th>标号</th>
        <th>借款人</th>
        <th>借款种类</th>
        <th>标题</th>
        <th>借款金额</th>
        <th>还款方式</th>
        <th>借款期限</th>
      <!--   <th width="6%">借款管理费</th> -->
      	<th>应还时间</th>
      	<th>还款本金</th>
      	<th>还款利息</th>
      	<th>逾期天数</th>
      	<th>逾期计息</th>
      	<th>应还总额</th>
        
        <th>操作</th>
        
        </tr>
        </thead>
        <tbody>
        
        <c:forEach var="repaynote" items="${repaynoteList }">
         <tr><td>${repaynote.lssuing.lssuingNum}</td>
        <td>${repaynote.lssuing.uservip.userName}</td>
        <td>
        	<c:if test="${repaynote.lssuing.lssuingType==1}">信用标</c:if>
        	<c:if test="${repaynote.lssuing.lssuingType==2}">担保标</c:if>
        	<c:if test="${repaynote.lssuing.lssuingType==3}">秒标</c:if>
        	<c:if test="${repaynote.lssuing.lssuingType==4}">净值标</c:if>
        	<c:if test="${repaynote.lssuing.lssuingType==5}">抵押标</c:if>
        </td>
        <td>${repaynote.lssuing.title}</td>
        <td>${repaynote.lssuing.borrowMoney }</td>
        <td>${repaynote.lssuing.returnway.returnWayName}</td>
        <c:if test="${!empty repaynote.lssuing.periodday.periodDayName}"><td>${repaynote.lssuing.periodday.periodDayName}</td></c:if>
        <c:if test="${empty repaynote.lssuing.periodday.periodDayName}"><td>${repaynote.lssuing.periodtime.periodTimeName}</td></c:if>
       
        <td><fmt:formatDate value="${repaynote.repayDate}" pattern="yyyy-MM-dd" /></td>
        
        <td>${repaynote.moneyOne}</td>
        <td>${repaynote.moneyTwo}</td>
        <td>${repaynote.overdays}</td>
        <td>${repaynote.overInterest}</td>
        <td>${repaynote.moneyFour}</td>
        <c:if test="${sessionScope.powerss[117]==1 }">
        <td>
        <c:if test="${repaynote.lssuing.state==0 || repaynote.lssuing.state==1 }">
        <a  href="<%=path %>/borrow/toBackLssuingView?lssuingId=${repaynote.lssuing.lssuingId}&queryFlag=${queryFlag}" target="rightFrame" class="tablelink">审核</a>
        </c:if>
<!--         <c:if test="${repaynote.lssuing.state==3 || repaynote.lssuing.state==4 }"> -->
<!--         <a  href="<%=path %>/borrow/investList?lssuingId=${repaynote.lssuing.lssuingId}&queryFlag=${queryFlag}" target="rightFrame" class="tablelink">投资人列表</a> -->
<!--         </c:if> -->
		<c:if test="${repaynote.lssuing.lssuingType==9}">
	        <c:if test="${repaynote.lssuing.state==3}">
	        <a  href="<%=path %>/borrow/toFenMoney?lssuingId=${repaynote.lssuing.lssuingId}&repayNoteId=${repaynote.repayNoteId}" target="rightFrame" class="tablelink">马上还款</a>
	        </c:if>
	    </c:if>
        <c:if test="${repaynote.lssuing.state ==5 || repaynote.lssuing.state==6 || repaynote.lssuing.state==-1 || repaynote.lssuing.state==2 }">
        -------
        </c:if>
        <c:if test="${repaynote.lssuing.lssuingType!=9}">
	        <c:if test="${repaynote.lssuing.state==3 || repaynote.lssuing.state==4 }">
	        <a  href="<%=path %>/borrow/investList?lssuingId=${repaynote.lssuing.lssuingId}&queryFlag=${queryFlag}" target="rightFrame" class="tablelink">投资人列表</a>
	        </c:if>
	        <a href="<%=path %>/borrow/webAdvance?repaynoteId=${repaynote.repayNoteId}&managerId=${session.manager.managerId}">网站垫付</a>
        </c:if>
        </td>
        </c:if>
        <c:if test="${sessionScope.powerss[117]!=1 }"><td>--</td></c:if>
        </tr> 
  		</c:forEach>
        </tbody>
    </table>
    
   
    <div class="pagin">
    	<div class="message">共<i class="blue">${total}</i>条记录，当前显示第&nbsp;<i class="blue">${page}&nbsp;</i>页 ,共${pageCount}页 </div>
        <ul class="paginList">
         <li class="paginItem"><a href="" style="width: 50px"  onclick="toMoneyPage('1',${queryFlag})" id="page1-1">首页</a></li>
        <li class="paginItem"><a href="" style="width: 60px" onclick="toMoneyPage('2',${queryFlag})" id="page1-2">上一页</a></li>
        <li class="paginItem"><a href="" style="width: 60px" onclick="toMoneyPage('3',${queryFlag})" id="page1-3">下一页</a></li>
        <li class="paginItem"> 
        <a href="#" style="width: 50px" onclick="toMoneyPage('4',${queryFlag})" id="page1-4">转到</a>&nbsp;
        <input name="page" type="text" id="pId" value=""  style="width:31px;height:28px; border:1px solid #DDD; text-align:center;line-height:30px ;color:#3399d5" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>页
        </li>
        <li class="paginItem"><a href="" style="width: 60px;border-left:dashed;border:1px solid #DDD; " onclick="toMoneyPage('5',${queryFlag})" id="page1-5">最后一页</a></li>
         </ul>
         <input name="currentPage" value="${page}" id="currentPageId" style="display: none"/>
         <input name="total" value="${pageCount}" id="pageNumId" style="display: none"/>
      </div>
    
    
    
    
    
    </div>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	
	  function toMoneyPage(num,num1){
			 
		  var  $MP=jQuery.noConflict();
		  var page=$MP("#currentPageId").val();
		  var pageNum=$MP("#pageNumId").val();
		  var userNameQuery=$MP("#demo3").val();
		  var moneyQuery=$MP("#demo4").val();
		  var startTimeQuery=$MP("#demo1").val();
		  var endTimeQuery=$MP("#demo2").val();
		  var p=$MP("#pId").val();
	   	  if(num==1){
				  $MP("#page1-1").attr("href","<%=path %>/borrow/overdueLassuing?userNameQuery="+userNameQuery+"&moneyQuery="+moneyQuery+"&startTimeQuery="+startTimeQuery+"&endTimeQuery="+endTimeQuery+"&queryFlag="+num1+"&page=1"); 
		  }
		  if(num==3){
			  if(Number(page)<Number(pageNum)){
				  $MP("#page1-3").attr("href","<%=path %>/borrow/overdueLassuing?userNameQuery="+userNameQuery+"&moneyQuery="+moneyQuery+"&startTimeQuery="+startTimeQuery+"&endTimeQuery="+endTimeQuery+"&queryFlag="+num1+"&page="+(Number(page)+1));
			  }else{
				  $MP("#page1-3").attr("href","<%=path %>/borrow/overdueLassuing?userNameQuery="+userNameQuery+"&moneyQuery="+moneyQuery+"&startTimeQuery="+startTimeQuery+"&endTimeQuery="+endTimeQuery+"&queryFlag="+num1+"&page="+page);
			  }
		  }
		  if(num==2){
			  if(page>1){
				  $MP("#page1-2").attr("href","<%=path %>/borrow/overdueLassuing?userNameQuery="+userNameQuery+"&moneyQuery="+moneyQuery+"&startTimeQuery="+startTimeQuery+"&endTimeQuery="+endTimeQuery+"&queryFlag="+num1+"&page="+(Number(page)-1));
			  }else{
				  $MP("#page1-2").attr("href","<%=path %>/borrow/overdueLassuing?userNameQuery="+userNameQuery+"&moneyQuery="+moneyQuery+"&startTimeQuery="+startTimeQuery+"&endTimeQuery="+endTimeQuery+"&queryFlag="+num1+"&page="+page);
			  }
		  }
		  if(num==4){
	  		if(p==null || p=="" || parseInt(p)< 1 || parseInt(p) > parseInt(pageNum)){
	  		alert("请输入正确的页数！");
	  		return false;
	  		}
			  if(p<1){
				  p=1;
			  }else if(Number(p)>Number(pageNum)){
				  p=pageNum
			  }
		 
				  $MP("#page1-4").attr("href","<%=path %>/borrow/overdueLassuing?userNameQuery="+userNameQuery+"&moneyQuery="+moneyQuery+"&startTimeQuery="+startTimeQuery+"&endTimeQuery="+endTimeQuery+"&queryFlag="+num1+"&page="+Number(p));
		  }
		  if(num==5){
				  $MP("#page1-5").attr("href","<%=path %>/borrow/overdueLassuing?userNameQuery="+userNameQuery+"&moneyQuery="+moneyQuery+"&startTimeQuery="+startTimeQuery+"&endTimeQuery="+endTimeQuery+"&queryFlag="+num1+"&page="+Number(pageNum)); 
		  }
	  }
	</script>

</body>

</html>
