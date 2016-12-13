<%@page import="com.jqg.pojo.Lssuing"%>
<%@page import="java.util.List"%>
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
<link rel="icon" href="<%=path %>/image/favicon/favicon.ico" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>个人中心-我要还款</title>
<link href="../css/public.css" type="text/css" rel="stylesheet"/>
<link href="../css/user/user_public.css" type="text/css" rel="stylesheet"/>
<link href="../css/user/user_repay.css" type="text/css" rel="stylesheet"/>
<!--导航下拉菜单 js-->
<script type="text/javascript" src="../js/jquery-1.8.2.min.js"></script>
<script type="text/javascript">
var $page =  jQuery.noConflict();

$page(function() {  
	getJSONData();  
});
function getJSONData(){
	$page("#ID1").hide();
	$page("#ID4").show();
  	if($page("#markId").val()==2){
		 secBoard('hotnews_caption','list',2);
 	}else if($page("#markId").val()==22){
 		secBoard('hotnews_caption','list',2);
 		alert("还款成功");
 	}else if($page("#markId").val()==222){
 		secBoard('hotnews_caption','list',2);
 		alert("余额不足，无法还款");
 	}
}

</script> 
 
<!--左侧折叠菜单-->
<script type="text/javascript" src="../js/menu.js"></script>
<script type="text/javascript" >
function toMoneyPage(num,num2){
	  var  $MP=jQuery.noConflict();
	  var page=$MP("#currentPageId"+num2).val();
	  var pageNum=$MP("#pageNumId"+num2).val();
	  var p=$MP("#pId"+num2).val();
	  if(isNaN(parseInt(p))&&p!=""){
	      alert("请输入正确页数");
	    document.getElementById('pId').value='';
	   
	      return false;
	  }
	    if(parseInt(p)< 1 || parseInt(p)> parseInt(pageNum)){
	  	alert("请输入正确的页数！");
	  	document.getElementById('pId').value='';
	  	return false;
	  }
	  if(num==1){
			  $MP("#page1-1"+num2).attr("href","<%=path %>/borrow/toLssuingList?userId=${session.uservip.userId}&gotoFlag=1&page=1&queryFlag="+num2); 
	  }
	  if(num==3){
		  if(Number(page)<Number(pageNum)){
			  $MP("#page1-3"+num2).attr("href","<%=path %>/borrow/toLssuingList?userId=${session.uservip.userId}&gotoFlag=1&page="+(Number(page)+1)+"&queryFlag="+num2);
		  }
	  }
	  if(num==2){
		  if(page>1){
			  $MP("#page1-2"+num2).attr("href","<%=path %>/borrow/toLssuingList?userId=${session.uservip.userId}&gotoFlag=1&page="+(Number(page)-1)+"&queryFlag="+num2);
		  }
	  }
	  if(num==4){
		  if(p<1){
			  p=1;
		  }else if(Number(p)>Number(pageNum)){
			  p=pageNum;
		  }
	 
			  $MP("#page1-4"+num2).attr("href","<%=path %>/borrow/toLssuingList?userId=${session.uservip.userId}&gotoFlag=1&page="+Number(p)+"&queryFlag="+num2);
	  }
	  
}
</script>


</head>
<body> 
<!--头部开始-->
<div class="header">
    <jsp:include page="../header.jsp" />
</div> 
<!--头部结束-->
<input value="${mark}" id="markId" type="hidden"/>
<div class="maincontent">
  
     <div class="conbox fn_cle">
      <jsp:include page="../user/left.jsp" />
   </div>
   
   
     <div class="user_r">
         <div id="hotnews_caption">
            <ul id="h_menu">                  
                 <li class="current"><a href="<%=path %>/borrow/toLssuingList?userId=${session.uservip.userId}&queryFlag=3&gotoFlag=1">偿还中借款</a></li>
                 <li class="normal" onclick="secBoard('hotnews_caption','list',2);"  ><a href="#">借款详细</a></li>
                 
                 
            </ul> 
         </div>
         
         <div id="hotnews_content">
            <div class="current" id="list_1">
                <P>复审通过，正在还款的借款  </P>
               <table width="845" cellspacing="0" cellpadding="0" class="bm_table"  >
                      <tbody>
                            <tr class="th1">
                                <td width="100">借款标号</td>
                                <td width="160">还款方式 </td>
                                <td width="100">借款金额 </td>
                                <td width="100">已还金额</td>
                                <td width="90" >年/日利率</td>
                                <td width="90">还款期限 </td>
                                <td width="110">即将还款时间</td>
                                <td width="100"><a href="#">我要还款</a></td>
                            </tr>
                               <c:forEach var="hk" items="${lssuingList}">
                            <tr>
                                <td>${hk.lssuingNum}</td>
                                <td>${hk.returnway.returnWayName}</td>    
                                <td><fmt:formatNumber value="${hk.borrowMoney}" pattern="0.00"/></td>
                                 <td><fmt:formatNumber value="${hk.returnMoney}" pattern="0.00"/></td>
                                 <td>${hk.rate}%</td>
                                 <c:if test="${!empty hk.periodday.periodDayName}"><td>${hk.periodday.periodDayName}</td></c:if>
                                 <c:if test="${empty hk.periodday.periodDayName}"><td>${hk.periodtime.periodTimeName}</td></c:if>
                                
                                <td><fmt:formatDate value="${hk.everyReturnTime}" pattern="yyyy-MM-dd" /></td>
                                <td><a href="<%=path %>/borrow/toRePayList?lssuingId=${hk.lssuingId}&userId=${session.uservip.userId}" style="color: red">马上还款</a></td>
                            </tr></c:forEach>                           
                      </tbody>
                   </table>
                   <div class="page" >
                <a href=""   onclick="toMoneyPage('1','3')" id="page1-13" >第一页</a>
                <a href=""  onclick="toMoneyPage('2','3')" id="page1-23" >上一页</a>
                <a href=""  onclick="toMoneyPage('3','3')" id="page1-33">下一页</a> 
                <span class="skip"><a href=""  onclick="return toMoneyPage('4','3')" id="page1-43" >
                                        转到</a><input name="page" type="text" id="pId3" value="" size="3" style="height:26px; line-height:26px;" />页</span>
                第${page}页/共${pageCount}页 
                <input name="currentPage" value="${page}" id="currentPageId3" style="display: none"/>
                <input name="total" value="${pageCount}" id="pageNumId3" style="display: none"/>
              </div>
            </div>
         
            <div class="normal" id="list_2">
                <P>${result}</P>
                
                <table width="845" cellspacing="0" cellpadding="0" class="bm_table"  >
                      <tbody>
                           <tr class="th1">
                                <td width="120">计划还款日期</td>
                                <td width="120">计划还款本金</td>
                                <td width="120">计划还款利息</td>
                                <td width="120">需还本息</td>
                                <td width="120">逾期天数</td>
                                <td width="120">逾期利息</td>
                                <td width="120">实还本息</td>
                                <td width="120">还款状态</td>
                                <td width="120">还款</td>
                                
                            </tr>
                                
                            <c:forEach var="mx" items="${repaynoteList}">
                            <tr>
                                <td><fmt:formatDate value="${mx.repayDate}" pattern="yyyy-MM-dd" /></td>
                                <td><fmt:formatNumber value="${mx.moneyOne}" pattern="0.00"/></td>
                                <td><fmt:formatNumber value="${mx.moneyTwo}" pattern="0.00"/></td>
                                <td><fmt:formatNumber value="${mx.moneyFour}" pattern="0.00"/></td>
                                <td>${mx.overdays}</td>
                                <td><fmt:formatNumber value="${mx.overInterest}" pattern="0.00"/></td>
                                <td><fmt:formatNumber value="${mx.moneyThree}" pattern="0.00"/></td>
                                
                                <td><c:if test="${mx.repayState==0}">未还款</c:if>
                                <c:if test="${mx.repayState==1}">还款成功</c:if>
                                <c:if test="${mx.repayState==2}">平台垫付</c:if></td>
 
                                <td><c:if test="${mx.repayState==0}"><a href="<%=path %>/borrow/toFenMoney?repayNoteId=${mx.repayNoteId}&lssuingId=${mx.lssuing.lssuingId}&userId=${session.uservip.userId}" style="color: red">马上还款</a></c:if>
                                <c:if test="${mx.repayState==1}">----</c:if> 
                                <c:if test="${mx.repayState==2}"><a href="<%=path %>/borrow/toFenMoney?repayNoteId=${mx.repayNoteId}&lssuingId=${mx.lssuing.lssuingId}&userId=${session.uservip.userId}" style="color: red">还款</a></c:if></td>
                                
                            </tr>
                            </c:forEach>
                      </tbody>
                   </table>
                
            </div>
           
            <div id="light1" class="white_content" >
            <div class="light_title"><span>提示信息</span> <a href = "javascript:void(0)" onclick = "document.getElementById('light1').style.display='none';document.getElementById('fade').style.display='none'" class="close"><img src="../images/close1.png" /></a></div>
            <div class="letter_info">
              <P>确定要还款吗？</P>
              <span>如果是请点击确定按钮 ，否则请点取消。 </span>
              <div class="tipbtn">
        <input type="button" value="确定" class="sure" name="">
        <input type="button" value="取消" class="cancel" name="">
        </div>
            </div>
            
           </div> 
 
           <div id="fade" class="black_overlay"></div> 
           <script type="text/javascript" src="../js/tabs.js" language="javascript"></script>
            
            
            
           <script type="text/javascript" src="../js/tabs.js" language="javascript"></script>
        </div>
        
        
   </div>
    
   </div> 
  </div>
</div> 

</div>
<!--footer 开始-->
 <div class="footer">
  <jsp:include page="../footer.jsp" />
</div>
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