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
<html>
<head>
<link rel="icon" href="<%=path %>/image/favicon/favicon.ico" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>个人中心-历史资金明细</title>
<link href="<%=basePath %>/css/public.css" type="text/css" rel="stylesheet"/>
<link href="<%=basePath %>/css/user/user_public.css" type="text/css" rel="stylesheet"/>
<link href="<%=basePath %>/css/user/user_account.css" type="text/css" rel="stylesheet"/>
<!--导航下拉菜单 js-->
<script type="text/javascript" src="<%=basePath %>/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/back/js/jquery.js"></script>
 <script type="text/javascript">
 var $page =  jQuery.noConflict();
 function getJSONData() {
	 
 		$page("#ID1").hide();
 		$page("#ID2").show();
 
 }
 $page(function() {  
 	getJSONData();  
 });
 </script>
<!--左侧折叠菜单-->
<script type="text/javascript" src="<%=basePath %>/js/menu.js"></script>
<script type="text/javascript">
  function toMoneyPage(num){
	  var  $MP=jQuery.noConflict();
	  var page=$MP("#currentPageId").val();
	  var pageNum=$MP("#pageNumId").val();
	  var p=$MP("#pId").val();
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
			  $MP("#page1-1").attr("href","<%=path %>/borrow/toAccountHistory?userId=${session.uservip.userId}&page=1"); 
	  }
	  if(num==3){
		  if(Number(page)<Number(pageNum)){
			  $MP("#page1-3").attr("href","<%=path %>/borrow/toAccountHistory?userId=${session.uservip.userId}&page="+(Number(page)+1));
		  }else{
		  	  $MP("#page1-3").attr("href","<%=path %>/borrow/toAccountHistory?userId=${session.uservip.userId}&page=1"); 
		  }
	  }
	  if(num==2){
		  if(page>1){
			  $MP("#page1-2").attr("href","<%=path %>/borrow/toAccountHistory?userId=${session.uservip.userId}&page="+(Number(page)-1));
		  }else{
		  	  $MP("#page1-2").attr("href","<%=path %>/borrow/toAccountHistory?userId=${session.uservip.userId}&page=1"); 
		  }
	  }
	  if(num==4){
	   if(parseInt(p)< 1 || parseInt(p)> parseInt(pageNum)|| p==null|| p==""){
	  	alert("请输入正确的页数！");
	  	
	  	return false;
	  }
		  if(p<1){
			  p=1;
		  }else if(Number(p)>Number(pageNum)){
			  p=pageNum
		  }
		 
			  $MP("#page1-4").attr("href","<%=path %>/borrow/toAccountHistory?userId=${session.uservip.userId}&page="+Number(p));
				
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
<div class="maincontent">
  
     <div class="conbox fn_cle">
      <jsp:include page="../user/left.jsp" />
   </div>
   
 
   
     <div class="user_r">
         <div id="hotnews_caption">
            <ul id="h_menu">                  
                 <li class="current"onclick="secBoard('hotnews_caption','list',1);"><a href="#">资金历史明细</a></li>
            </ul>
         </div>
         
         <div id="hotnews_content">
           <div class="current" id="list_1">
              <div class="a_tip1"> 尊敬的用户，为您记录和保存了您的资金历史明细.('可用余额','冻结金额','待收金额',都是指此次事件后相应的帐户余额)</div>
<!--               <div class="a_tip2">资金历史记录了您各种交易产生的支出和收入的明细，请选择事件类型和时间，然后点击"查看"按钮浏览。</div>-->
               
               <div class="a_detail">
                    <!--   <label class="title">事件类型：</label>
                       <div id="uboxstyle">
                         <select name="eventTypeId" id="style">
	                 	  <option value="0"  selected="selected">-请选择-</option>
	                 	  <c:forEach var="sj" items="${eventtypeList}">
		                  <option value="${sj.eventTypeId }" >${sj.eventName}</option>
		                  </c:forEach>
                       </select>
                     </div>
            
                   
                     <form action="" method=""  class="a_detail_title">
                
                        <label class="title">事件类型：</label>
                       <div id="uboxstyle">
                       <select name="style" id="style">
	                 	  <option value=""  selected="selected">-请选择-</option>
		                  <option value="" >会员充值</option>
		                  <option value="" >会员升级</option>
		                  <option value="" >投标成功代收利息</option>
		                  <option value="" >撤销提现</option>
                          <option value="" >投标冻结</option>
		                  <option value="" >管理员操作</option>
		                  <option value="" >流标返还</option>
		                  <option value="" >会员还款</option>
                       </select>
                     </div>
                     
                     <div class="date"> 起止日期：<input class="laydate-icon" id="demo1" value="-请选择时间-"> 至<input class="laydate-icon" id="demo2" value="-请选择时间-"> </div>
<script>
!function(){
laydate.skin('default');//切换皮肤，请查看skins下面皮肤库
laydate({elem: '#demo1'});
laydate({elem: '#demo2'});//绑定元素
}();
</script>                     
                     
                     <div class="search" >
                        <input type="submit" class="save_bt" value="查看" />
                        <a href="#" class="download">点击下载</a>
                     </div>
                      </form>-->

               </div>
               <table width="100%" cellspacing="0" cellpadding="0" class="zh_table"  >
                            <tbody>
                          
                            <tr class="th3">
                                <td width="100">发生日期</td>
                                <td width="140">影响金额</td>
                                 <td width="110">可用余额</td>
                                <td width="110">冻结金额</td>
                                <td width="110">待收金额</td>
                                <td width="140">说明</td>
                                
                            </tr>
                            <c:forEach var="gg" items="${moneyhistorydetailList }">
                                  <tr>
                                <td><fmt:formatDate value="${gg.occurTime}" pattern="yyyy-MM-dd" /></td>
                                <!--  <td>${gg.eventtype.eventName}</td>-->
                                <td >￥<fmt:formatNumber value="${gg.affectMoney }" pattern="0.00"/></td>
                                <td>￥<fmt:formatNumber value="${gg.availableBalance }" pattern="0.00"/></td>
                                <td>￥<fmt:formatNumber value="${gg.frozenMoney }" pattern="0.00"/></td>
                                <td>￥<fmt:formatNumber value="${gg.collectMoney }" pattern="0.00"/></td>
                                <td class="zfl">${gg.introduction}</td>
                            </tr>
                            </c:forEach>  
                            
                          </tbody>
                </table>
                
                <div class="page">
                <a href=""  onclick="toMoneyPage('1')" id="page1-1">首页</a>
                <a href=""  onclick="toMoneyPage('2')" id="page1-2">上一页</a>
                <a href=""  onclick="toMoneyPage('3')" id="page1-3">下一页</a> 
                <span class="skip"><a href="#"  onclick="toMoneyPage('4')" id="page1-4">转到</a><input name="page" type="text" id="pId" value="" size="3" style="height:26px; line-height:26px;" />页</span>
               				第${page}页/共${pageCount}页
         <input name="currentPage" value="${page}" id="currentPageId" style="display: none"/>
         <input name="total" value="${pageCount}" id="pageNumId" style="display: none"/>
          </div>
           </div>
         
           
          
           
          <script type="text/javascript" src="<%=basePath %>/js/tabs.js" language="javascript"></script>
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
<script type="text/javascript" src="<%=basePath %>/js/service.js">	</script> --%>
<!--右边漂浮 结束-->

</body>
</html>