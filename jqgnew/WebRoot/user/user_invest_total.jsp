<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
  <%@ taglib uri="/struts-tags" prefix="s"%>
  <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();if(path!=null && !path.equals("")){	if(path.contains("/")){		if(path.equals("/")){path = "";	}}else{	path += "/";}} 
String basePath =  "";
if(request.getServerPort()==80){
	basePath = request.getScheme()+"://"+request.getServerName()+path+"/"; 
}else{
	basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="icon" href="<%=path %>/image/favicon/favicon.ico" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>个人中心-投资总表</title>
<link href="../css/public.css" type="text/css" rel="stylesheet"/>
<link href="../css/user/user_public.css" type="text/css" rel="stylesheet"/>
<link href="../css/user/user_invest_total.css" type="text/css" rel="stylesheet"/>
<!--导航下拉菜单 js-->
<script type="text/javascript" src="../js/jquery-1.8.2.min.js"></script>
<!--左侧折叠菜单-->
<script type="text/javascript" src="../js/menu.js"></script>
<style>
#table3,#table2{
	overflow:hidden;
}
#table3 tr td,#table2 tr td{
	line-height:25px;
	height:25px;
}
</style>
</head>
<body> 
<!--头部开始-->
<div class="header">
   <jsp:include page="../header.jsp" />
</div>
<!--头部结束-->
<div class="maincontent">
  
  <div class="conbox fn_cle">
   <jsp:include page="../user/left.jsp" />
   </div>
   <input id="path" type="hidden" value="<%=basePath%>">
     <div class="user_r">
         <div id="hotnews_caption">
            <ul id="h_menu">                  
                 <li class="current" onclick="secBoard('hotnews_caption','list',1);">投资总表</li>
                 <li class="normal" onclick="secBoard('hotnews_caption','list',2);">竞标中的投资</li>
                 <li class="normal" onclick="secBoard('hotnews_caption','list',3);">回收中的投资</li>
                 <li class="normal" onclick="secBoard('hotnews_caption','list',4);">已回收的投资</li>
                 <li class="normal" onclick="secBoard('hotnews_caption','list',5);">逾期的投资</li>
                 <li class="normal" id="huankuan" onclick="secBoard('hotnews_caption','list',6);"style="display: none">还款详细</li>
            </ul>
         </div>
         
         <div id="hotnews_content">
            <div class="current" id="list_1">
                <P>尊敬的用户，以下是您的投资总表，敬请仔细审阅 </P>
                <table width="845" cellspacing="0" cellpadding="0" class="zh_table"  >
                      <tbody>
                            <tr class="th1">
                                <td width="220">项目</td>
                                <td width="180" >金额</td>
                                <td width="440">说明</td>
                               
                            </tr>
                            <tr>
                                <td class="fc">竞标投资</td>
                                <td><em>￥<fmt:formatNumber value="${sessionScope.totalMoney}" pattern="0.00"/>/${sessionScope.totalSize}</em>标 <a href="#" onclick="secBoard('hotnews_caption','list',2);">[列表]</a></td>
                                <td class="fl">（目前正在投标中，尚未确定投资的金额）</td>
                            </tr>
                            <tr>
                                <td class="fc">回收中投资</td>
                                <td><em>￥<fmt:formatNumber value="${sessionScope.totalMoney1}" pattern="0.00"/>/${sessionScope.totalSize1}</em>标 <a href="#" onclick="secBoard('hotnews_caption','list',3);">[列表]</a></td>
                                <td class="fl">（目前已经投资，正在回收中的普通标投资总额）</td>
                            </tr>
                             <tr>
                                <td class="fc">已完成投资</td>
                                <td><em>￥<fmt:formatNumber value="${sessionScope.totalMoney2}" pattern="0.00"/>/${sessionScope.totalSize2}</em>标 <a href="#" onclick="secBoard('hotnews_caption','list',4);">[列表]</a></td>
                                <td class="fl">（已经全部收回的普通标投资的本金总额）</td>
                            </tr>
                             <tr>
                                <td class="fc">逾期的投资</td>
                                <td><em>￥<fmt:formatNumber value="${sessionScope.totalMoney3}" pattern="0.00"/>/${sessionScope.totalSize3}</em>标 <a href="#"onclick="secBoard('hotnews_caption','list',5);">[列表]</a></td>
                                <td class="fl">（已经投资并逾期未收回的）</td>
                            </tr>
                             <tr>
                                <td class="fc">累计投资金额</td>
                                <td><em>￥<fmt:formatNumber value="${sessionScope.total}" pattern="0.00"/>/${sessionScope.totalSize4}</em>标</td>
                                <td class="fl">（注册至今，您累计投资的总额）</td>
                            </tr>
                      </tbody>
                   </table>
            </div>
         
            <div class="normal" id="list_2">
                <P>	尊敬的用户，以下是您普通标竞标中投资  </P>
                <div class="tip">您目前竞标中的投资总额是：￥<fmt:formatNumber value="${sessionScope.totalMoney}" pattern="0.00"/>，共${sessionScope.totalSize}笔投标。 </div>
                <table width="845" cellspacing="0" cellpadding="0" class="bm_table"  id="table">
                      <tbody>
                            <tr class="th1">
                                <td width="120">借款标号</td>
                                <td width="110">借入人</td>
                                <td width="115">投标日期</td>
                                <td width="110">借款金额</td>
                                <td width="80">年/日利率</td>
                                <td width="80">借款期限</td>
                                <td width="120">我的投资金额</td>
                                <td width="110">预期本息</td>
                                
                            </tr>
                           
                      </tbody>
                   </table>
                 <div class="page" id="page4"  style="display: none">
                 <span id="currentPage4">第页</span>&nbsp; &nbsp; 
  	          <span id="totalPage4">共页</span>&nbsp; &nbsp; 
  	<span ><a id="firstPage4" href="">首页</a></span>&nbsp; &nbsp; 
  	<span ><a id="prevPage4" href="">上一页</a></span>&nbsp; &nbsp; 
  	<span ><a id="nextPage4" href="">下一页</a></span>&nbsp; &nbsp; 
  	<span ><a id="lastPage4" href="">最后一页</a></span>&nbsp; &nbsp; 
              </div>
            </div>
           
            <div class="normal" id="list_3">
               <P>尊敬的用户，以下是您回收中的普通标投资  </P>
               <table width="845" cellspacing="0" cellpadding="0" class="bm_table"  id="table1">
                      <tbody>
                            <tr class="th1">
                                <td width="120">借款标号</td>
                                <td width="100">借入人</td>
                                <td width="120">投资金额 </td>
                                <td width="120">已还本息</td>
                                <td width="80">年/日利率</td>
                                <td width="220">已还/总期数(还款期) </td>
                                <td width="80">备注</td>
                                
                            </tr>
                           
                      </tbody>
                   </table>
                              <div class="page" id="page3"style="display: none">
                 <span id="currentPage3">第页</span>&nbsp; &nbsp; 
  	          <span id="totalPage3">共页</span>&nbsp; &nbsp; 
  	<span ><a id="firstPage3" href="">首页</a></span>&nbsp; &nbsp; 
  	<span ><a id="prevPage3" href="">上一页</a></span>&nbsp; &nbsp; 
  	<span ><a id="nextPage3" href="">下一页</a></span>&nbsp; &nbsp; 
  	<span ><a id="lastPage3" href="">最后一页</a></span>&nbsp; &nbsp; 
  	</div> 
            </div>
           
            <div class="normal" id="list_4">
               <P>尊敬的用户，以下是您已回收的普通标投资列表  </P>
               <div class="tip">您目前已回收的投资总额是：￥<fmt:formatNumber value="${sessionScope.totalMoney2}" pattern="0.00"/>，共${sessionScope.totalSize2}笔投标。  </div>
               <table width="845" cellspacing="0" cellpadding="0" class="bm_table"  id="table2">
                      <tbody>
                            <tr class="th1">
                                <td width="160">借款标号</td>
                                <td width="160">借入人</td>
                                <td width="160">年/日利率 </td>
                                <td width="180">已收本金</td>
                                <td width="180">已收利息</td>
                                <td width="80">备注</td>
                                
                            </tr>
                         
                        
                      </tbody>
                   </table>
                               <div class="page" id="page2" style="display: none">
                 <span id="currentPage2">第页</span>&nbsp; &nbsp; 
  	          <span id="totalPage2">共页</span>&nbsp; &nbsp; 
  	<span ><a id="firstPage2" href="">首页</a></span>&nbsp; &nbsp; 
  	<span ><a id="prevPage2" href="">上一页</a></span>&nbsp; &nbsp; 
  	<span ><a id="nextPage2" href="">下一页</a></span>&nbsp; &nbsp; 
  	<span ><a id="lastPage2" href="">最后一页</a></span>&nbsp; &nbsp; 
  	</div>
            </div>
           
            <div class="normal" id="list_5">
               <P> 尊敬的用户，以下是的逾期普通标投资列表  </P>
               <table width="845" cellspacing="0" cellpadding="0" class="bm_table"  id="table3">
                      <tbody>
                            <tr class="th1">
                              <td width="160">借款标号</td>
                                <td width="160">借入人</td>
                                <td width="160">年/日利率 </td>
                                <td width="180">已收本金</td>
                                <td width="180">已收利息</td>
                                <td width="80">备注</td>
                            </tr>
                            
                      </tbody>
                   </table>
                               <div class="page" id="page1" style="display: none">
                 <span id="currentPage1">第页</span>&nbsp; &nbsp; 
  	          <span id="totalPage1">共页</span>&nbsp; &nbsp; 
  	<span ><a id="firstPage1" href="">首页</a></span>&nbsp; &nbsp; 
  	<span ><a id="prevPage1" href="">上一页</a></span>&nbsp; &nbsp; 
  	<span ><a id="nextPage1" href="">下一页</a></span>&nbsp; &nbsp; 
  	<span ><a id="lastPage1" href="">最后一页</a></span>&nbsp; &nbsp; 
  	</div>
            </div>
                   <div class="normal" id="list_6" style="display: none">
               <P> 尊敬的用户，以下是您的逾期普通标投资列表  </P>
               <table width="845" cellspacing="0" cellpadding="0" class="bm_table"  id="table4">
                      <tbody>
                         <tr class="th1">
                         <td width="160">应收日期</td>
                         <td width="160">应收本金</td>
                         <td width="160">应收利息 </td>
                         <td width="160">逾期天数</td>
                         <td width="160">逾期利息</td>
                         <td width="180">实收本息</td>
                         <td width="180">收款状态</td>
                         <td width="180">当前/总（期）</td></tr>
                            
                      </tbody>
                   </table>
                               <div class="page" id="page" style="display: none">
                 <span id="currentPage">第页</span>&nbsp; &nbsp; 
  	          <span id="totalPage">共页</span>&nbsp; &nbsp; 
  	<span ><a id="firstPage" href="">首页</a></span>&nbsp; &nbsp; 
  	<span ><a id="prevPage" href="">上一页</a></span>&nbsp; &nbsp; 
  	<span ><a id="nextPage" href="">下一页</a></span>&nbsp; &nbsp; 
  	<span ><a id="lastPage" href="">最后一页</a></span>&nbsp; &nbsp; 
  	</div>
            </div>
           <script type="text/javascript" src="../js/tabs.js" language="javascript"></script>
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
<script type="text/javascript" src="../js/invsettotalpage.js"></script>
<script type="text/javascript">
 function getJSONData() {
 		$("#ID1").hide();
 		$("#ID3").show();
 }
 $(function() {  
 	getJSONData();  
 });
 </script>
</body>
</html>