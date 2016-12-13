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
<title>个人中心-借款总表</title>
<link href="<%=basePath %>/css/public.css" type="text/css" rel="stylesheet"/>
<link href="<%=basePath %>/css/user/user_public.css" type="text/css" rel="stylesheet"/>
<link href="<%=basePath %>/css/user/user_borrow_total.css" type="text/css" rel="stylesheet"/>
<!--导航下拉菜单 js-->
<script type="text/javascript" src="<%=basePath %>/js/jquery-1.8.2.min.js"></script>
<!--表单下拉列表框-->
 <script type="text/javascript">
 	 
 function toMoneyPage(num,num2){
 	  var  $MP=jQuery.noConflict();
	  var page=$MP("#currentPageId"+num2).val();
 	  var pageNum=$MP("#pageNumId"+num2).val();
 	  var p=$MP("#pId"+num2).val();
 	  
	  if(num==1){
			  $MP("#page1-1"+num2).attr("href","<%=path %>/borrow/toLssuingList?userId=${session.uservip.userId}&page=1&queryFlag="+num2); 
	  }
	  if(num==3){
		  if(Number(page)<Number(pageNum)){
			  $MP("#page1-3"+num2).attr("href","<%=path %>/borrow/toLssuingList?userId=${session.uservip.userId}&page="+(Number(page)+1)+"&queryFlag="+num2);
		  }
	  }
	  if(num==2){
		  if(page>1){
			  $MP("#page1-2"+num2).attr("href","<%=path %>/borrow/toLssuingList?userId=${session.uservip.userId}&page="+(Number(page)-1)+"&queryFlag="+num2);
		  }
	  }
	  if(num==4){
		  if(parseInt(p)< 1 || parseInt(p)> parseInt(pageNum)|| p==""){
		  	alert("请输入正确的页数！");
		  	document.getElementById('pId').value='';
		  	return false;
		  }
// 		  if(p<1){
// 			  p=1;
// 		  }else if(Number(p)>Number(pageNum)){
// 			  p=pageNum;
// 		  }
	 
			  $MP("#page1-4"+num2).attr("href","<%=path %>/borrow/toLssuingList?userId=${session.uservip.userId}&page="+Number(p)+"&queryFlag="+num2);
	  }
	  
}
 </script>
<!--左侧折叠菜单-->
<script type="text/javascript" src="<%=basePath %>/js/menu.js"></script>


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
                 <li class="normal" ><a href="<%=path %>/borrow/toBorrowTotal?userId=${session.uservip.userId}">借入总表</a></li>
                 <li class="normal" ><a href="<%=path %>/borrow/toLssuingList?userId=${session.uservip.userId}&queryFlag=2">发标中借款</a></li>
                 <li class="normal"  ><a href="<%=path %>/borrow/toLssuingList?userId=${session.uservip.userId}&queryFlag=3">偿还中借款</a></li>
                  <li class="normal" ><a href="<%=path %>/borrow/toLssuingList?userId=${session.uservip.userId}&queryFlag=4">初审未通过</a></li>
                 <li class="normal"  ><a href="<%=path %>/borrow/toLssuingList?userId=${session.uservip.userId}&queryFlag=5">复审未通过</a></li>
                 <li class="normal"  ><a href="<%=path %>/borrow/toLssuingList?userId=${session.uservip.userId}&queryFlag=6">已还清的借款</a></li>
            </ul> 
         </div>
         
         <div id="hotnews_content">
            <div class="current" id="list_1">
                <P>尊敬的用户，以下是您的借入总表，敬请仔细审阅  </P>
                <table width="845" cellspacing="0" cellpadding="0" class="zh_table"  >
                      <tbody>
                            <tr class="th1">
                                <td width="220">项目</td>
                                <td width="220">金额</td>
                                <td width="400">说明</td>
                               
                            </tr>
                            <tr>
                                <td class="fc">发标中借入</td>
                                <td><em>￥${oneMon }</em>标 <a href="<%=path %>/borrow/toLssuingList?userId=${session.uservip.userId}&queryFlag=2">[列表]</a></td>
                                <td class="fl">（目前正在投标中，尚未确定借入的金额） </td>
                            </tr>
                            <tr>
                                <td class="fc">偿还中借入</td>
                                <td><em>￥${twoMon }</em>标 <a href="<%=path %>/borrow/toLssuingList?userId=${session.uservip.userId}&queryFlag=3">[列表]</a></td>
                                <td class="fl">（目前已经借入，正在偿还的借款）</td>
                            </tr>
                             <tr>
                                <td class="fc">还清的借入</td>
                                <td><em>￥${threeMon }</em>标 <a href="<%=path %>/borrow/toLssuingList?userId=${session.uservip.userId}&queryFlag=6">[列表]</a></td>
                                <td class="fl">（已经成功借入并按时完成还款的借款）</td>
                            </tr>
                             
                             <tr>
                                <td class="fc">累计借入金额</td>
                                <td><em>￥${fourMon }</em></td>
                                <td class="fl">（注册至今，您累计借入的总额） </td>
                            </tr>
                      </tbody>
                   </table>
            </div>
         
            <div class="normal" id="list_2">
                <P>	 尊敬的用户，以下是正在发标中的借款，在初审前您还可以撤消，初审以后不可以再撤消  </P>
                
                <table width="845" cellspacing="0" cellpadding="0" class="bm_table"  >
                      <tbody>
                           <tr class="th1">
                                <td width="100">借款标号</td>
                                <td width="180">还款方式</td>
                                <td width="140">借款金额</td>
                                <td width="140">借款进度</td>
                                <td width="140">借款时间</td>                                                               
                            </tr>
                           <c:forEach var="hk" items="${lssuingList}">
	                            <tr>
	                                <td>${hk.lssuingNum}</td>
	                                <td>${hk.returnway.returnWayName }</td>
	                                <td><fmt:formatNumber value="${hk.borrowMoney}" pattern="0.00"/></td>
	                                <td>发标中</td>
	                                <td><fmt:formatDate value="${hk.borrowTime}" pattern="yyyy-MM-dd HH:mm" /></td>	                               
	                            </tr>
                            </c:forEach>
                            
                            
                      </tbody>
                   </table>
					<div class="page" >
                <a href=""   onclick="toMoneyPage('1','2')" id="page1-12" >第一页</a>
                <a href=""  onclick="toMoneyPage('2','2')" id="page1-22" >上一页</a>
                <a href=""  onclick="toMoneyPage('3','2')" id="page1-32">下一页</a> 
                <span class="skip"><a href=""  onclick="return toMoneyPage('4','2')" id="page1-42" >
                                        转到</a><input name="page" type="text" id="pId2" value="" size="3" style="height:26px; line-height:26px;" />页</span>
               				第${page}页/共${pageCount}页 
                <input name="currentPage" value="${page}" id="currentPageId2" style="display: none"/>
                <input name="total" value="${pageCount}" id="pageNumId2" style="display: none"/>
              </div>
            </div>
           
            <div class="normal" id="list_3">
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
                                <td width="100">我要还款</td>
                             </tr>
                             <c:forEach var="hk" items="${lssuings}">
                            <tr>
                                <td>${hk.lssuingNum}</td>
                                <td>${hk.returnway.returnWayName}</td>    
                                <td><fmt:formatNumber value="${hk.borrowMoney}" pattern="0.00"/></td>
                                 <td><fmt:formatNumber value="${hk.returnMoney}" pattern="0.00"/></td>
                                 <td>${hk.rate}%</td>
                                 <c:if test="${!empty hk.periodday.periodDayName}"><td>${hk.periodday.periodDayName}</td></c:if>
                                 <c:if test="${empty hk.periodday.periodDayName}"><td>${hk.periodtime.periodTimeName}</td></c:if>
                                
                                <td width="110"><fmt:formatDate value="${hk.everyReturnTime}" pattern="yyyy-MM-dd" /></td>
                                <td><a href="<%=path %>/borrow/toRePayList?lssuingId=${hk.lssuingId}" style="color: red">马上还款</a></td>
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
           
             
            
            <div class="normal" id="list_4">
               <P> 尊敬的用户，以下是初审未通过的  </P>
                <table width="845" cellspacing="0" cellpadding="0" class="bm_table"  >
                      <tbody>
                            <tr class="th1">
                                <td >借款标号</td>
                                <td  width="160">还款方式 </td>
                                <td >借款金额 </td>
                                <td >初审说明</td>
                                <td >借款时间 </td>               
                            </tr>
                            <c:forEach var="hk" items="${lssuingList}">
                            <tr>
                                <td>${hk.lssuingNum}</td>
                                <td>${hk.returnway.returnWayName}</td>
                                <td><fmt:formatNumber value="${hk.borrowMoney}" pattern="0.00"/></td>
                                <td>初审未通过</td>
                                <td><fmt:formatDate value="${hk.borrowTime}" pattern="yyyy-MM-dd" /></td>
                            </tr>
 						   </c:forEach>
                      </tbody>
                   </table>
                  <div class="page" >
                <a href=""   onclick="toMoneyPage('1','4')" id="page1-14" >第一页</a>
                <a href=""  onclick="toMoneyPage('2','4')" id="page1-24" >上一页</a>
                <a href=""  onclick="toMoneyPage('3','4')" id="page1-34">下一页</a> 
                <span class="skip"><a href=""  onclick="return toMoneyPage('4','4')" id="page1-44" >
                                        转到</a><input name="page" type="text" id="pId4" value="" size="3" style="height:26px; line-height:26px;" />页</span>
                    第${page}页/共${pageCount}页
                <input name="currentPage" value="${page}" id="currentPageId4" style="display: none"/>
                <input name="total" value="${pageCount}" id="pageNumId4" style="display: none"/>
              </div> 
            </div>
            
            <div class="normal" id="list_5">
               <P> 尊敬的用户，以下是复审未通过的  </P>
                <table width="845" cellspacing="0" cellpadding="0" class="bm_table"  >
                      <tbody>
                            <tr class="th1">
                                <td >借款标号</td>
                                <td  width="160">还款方式 </td>
                                <td >借款金额 </td>
                                <td >复审说明</td>
                                <td >借款时间</td>
                                
                              
                            </tr>
                            <c:forEach var="hk" items="${lssuingList}">
                            <tr>
                                <td>${hk.lssuingNum}</td>
                                <td>${hk.returnway.returnWayName}</td>
                                <td><fmt:formatNumber value="${hk.borrowMoney}" pattern="0.00"/></td>
                                <td>复审未通过</td>
                                <td><fmt:formatDate value="${hk.borrowTime}" pattern="yyyy-MM-dd" /></td>
                            </tr></c:forEach>
                          
                      </tbody>
                   </table>
                   <div class="page" >
                <a href=""   onclick="toMoneyPage('1','5')" id="page1-15" >第一页</a>
                <a href=""  onclick="toMoneyPage('2','5')" id="page1-25" >上一页</a>
                <a href=""  onclick="toMoneyPage('3','5')" id="page1-35">下一页</a> 
                <span class="skip"><a href=""  onclick="return toMoneyPage('4','5')" id="page1-45" >
                                        转到</a><input name="page" type="text" id="pId5" value="" size="3" style="height:26px; line-height:26px;" />页</span>
                第${page}页/共${pageCount}页
                <input name="currentPage" value="${page}" id="currentPageId5" style="display: none"/>
                <input name="total" value="${pageCount}" id="pageNumId5" style="display: none"/>
              </div>
            </div>
            
            <div class="normal" id="list_6">
               <P> 尊敬的用户，以下是您在逾期普通标投资列表  </P>
               <table width="845" cellspacing="0" cellpadding="0" class="bm_table"  >
                      <tbody>
                            <tr class="th1">
                                <td width="100" >借款标号</td>
                                <td  width="160">还款方式</td>
                                <td >借款金额</td>
                                <td width="80">借款期限</td>
                                <td width="100" >借款时间</td>
                                <td width="80" >已还本息 </td>
                                <td >还款明细 </td>
                                
                            </tr> 
                            <c:forEach var="hk" items="${lssuingList}">
                            <tr>
                                <td>${hk.lssuingNum}</td>
                                <td>${hk.returnway.returnWayName}</td>
                                <td><fmt:formatNumber value="${hk.borrowMoney}" pattern="0.00"/></td>
                                <c:if test="${!empty hk.periodday.periodDayName}"><td>${hk.periodday.periodDayName}</td></c:if>
                                <c:if test="${empty hk.periodday.periodDayName}"><td>${hk.periodtime.periodTimeName}</td></c:if>
                                <td><fmt:formatDate value="${hk.borrowTime}" pattern="yyyy-MM-dd" /></td>
                                <td>${hk.returnMoney}</td>
                                <td ><a href="<%=path %>/borrow/toRePayList?lssuingId=${hk.lssuingId}">查看明细</a></td>
                            </tr> </c:forEach>                          
                      </tbody>
                   </table>
              <div class="page" >
                <a href=""   onclick="toMoneyPage('1','6')" id="page1-16" >第一页</a>
                <a href=""  onclick="toMoneyPage('2','6')" id="page1-26" >上一页</a>
                <a href=""  onclick="toMoneyPage('3','6')" id="page1-36">下一页</a> 
                <span class="skip"><a href=""  onclick="return toMoneyPage('4','6')" id="page1-46" >
                                        转到</a><input name="page" type="text" id="pId6" value="" size="3" style="height:26px; line-height:26px;" />页</span>
                第${page}页/共${pageCount}页
                <input name="currentPage" value="${page}" id="currentPageId6" style="display: none"/>
                <input name="total" value="${pageCount}" id="pageNumId6" style="display: none"/>
              </div>
            </div>
             
           <script type="text/javascript" src="<%=basePath %>/js/tabs.js" language="javascript"></script>
        </div>
        
   </div>
    
    </div>  
  </div>
</div> 

</div>
<script type="">
	var mark=document.getElementById("markId").value;
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
	 
	 jQuery("#ID1").hide();
	 jQuery("#ID4").show();
</script>
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
<script type="text/javascript" src="<%=basePath %>/js/service.js">	</script> --%>
<!--右边漂浮 结束-->

</body>
</html>