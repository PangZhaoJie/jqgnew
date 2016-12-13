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
<input type="hidden" id="mark" value="${mark}"/>
  <div class="conbox fn_cle">
   <jsp:include page="../user/left.jsp" />
   </div>
   <input id="path" type="hidden" value="<%=basePath%>">
     <div class="user_r">
         <div id="hotnews_caption">
            <ul id="h_menu">                  
                 <li class="current" onclick="secBoard('hotnews_caption','list',1);">可转债权</li>
                 <li class="normal" onclick="secBoard('hotnews_caption','list',2);">进行中债权</li>
                 <li class="normal" onclick="secBoard('hotnews_caption','list',3);">成功转让债权</li>
                 <li class="normal" onclick="secBoard('hotnews_caption','list',4);">已购买债权</li>
                 <li class="normal" onclick="secBoard('hotnews_caption','list',5);">已撤消债权</li>
            </ul>
         </div>
         
         <div id="hotnews_content">
            <div class="current" id="list_1">
                <table width="845" cellspacing="0" cellpadding="0" class="bm_table"  id="table5">
                      <tbody>
                            <tr class="th1">
                                <td width="120">借款标号</td>
                                <td width="110">借入人</td>
                                <td width="115">投标日期</td>
                                <td width="80">年/日利率</td>
                                <td width="80">借款期限</td>
                                <td width="120">我的投资金额</td>
                                <td width="110">预期本息</td>
                                <td width="110">操作</td>
                            </tr>
                           
                      </tbody>
                   </table>
                 <div class="page" id="page5"  style="display: none">
                 <span id="currentPage5">第页</span>&nbsp; &nbsp; 
  	          <span id="totalPage5">共页</span>&nbsp; &nbsp; 
  	<span ><a id="firstPage5" href="">首页</a></span>&nbsp; &nbsp; 
  	<span ><a id="prevPage5" href="">上一页</a></span>&nbsp; &nbsp; 
  	<span ><a id="nextPage5" href="">下一页</a></span>&nbsp; &nbsp; 
  	<span ><a id="lastPage5" href="">最后一页</a></span>&nbsp; &nbsp; 
              </div>
            </div>
         
            <div class="normal" id="list_2">
                <table width="845" cellspacing="0" cellpadding="0" class="bm_table"  id="table">
                      <tbody>
                            <tr class="th1">
                                <td width="120">借款标号</td>
                                <td width="110">转让价格</td>
                                <td width="115">转让时间</td>
<!--                                 <td width="110">借款金额</td> -->
                                <td width="80">年/日利率</td>
                                <td width="110">待收本息</td>
                                <td width="120">操作</td>
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
               <table width="845" cellspacing="0" cellpadding="0" class="bm_table"  id="table1">
                      <tbody>
                            <tr class="th1">
                                <td width="120">借款标号</td>
                                 <td width="80">年/日利率</td>
                                <td width="100">债权总值</td>
                                <td width="120">转让价格 </td>
                                <td width="200">转让时间</td>
                                <td width="100">购买人</td>
                                <td width="70">备注</td>
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
               <table width="845" cellspacing="0" cellpadding="0" class="bm_table"  id="table2">
                      <tbody>
                            <tr class="th1">
                                <td width="120">借款标号</td>
                                <td width="80">年/日利率</td>
                                <td width="100">债权总值</td>
                                <td width="120">转让价格 </td>
                                <td width="200">转让时间</td>
                                <td width="100">购买人</td>
                                <td width="70">备注</td>
                                
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
               <table width="845" cellspacing="0" cellpadding="0" class="bm_table"  id="table3">
                      <tbody>
                            <tr class="th1">
                              <td width="200">借款标号</td>
                              <td width="160">年/日利率 </td>
                               <td width="180">待收本息</td>
                               <td width="100">撤消次数</td>
                               <td width="180">撤消时间</td>
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
<script type="text/javascript" src="../js/transfer.js"></script>
<script type="text/javascript">
 var $page =  jQuery.noConflict();
 function getJSONData() {
 var mark = document.getElementById("mark").value;
 	if(mark=='1'){
 		secBoard('hotnews_caption','list',2);
 	}
 		$page("#ID1").hide();
 		$page("#ID3").show();
 }
 $page(function() {  
 	getJSONData();  
 });
 </script>
</body>
</html>