<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 

<%    
	String path = request.getContextPath();if(path!=null && !path.equals("")){	if(path.contains("/")){		if(path.equals("/")){path = "";	}}else{	path += "/";}}    
	String basePath = "";if(request.getServerPort()==80){basePath = request.getScheme()+"://"+request.getServerName()+path+"/";}else{basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";}      
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="<%=basePath %>/back/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>/back/css/tabs.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath %>/back/js/jquery.js"></script>
<!--弹窗中的滑动js-->
<script type="text/javascript" src="<%=basePath %>/back/js/jquery.tabso_yeso.js"></script>
<script type="text/javascript">
jQuery(document).ready(function($){
	
	//默认选项卡切换
	jQuery("#normaltab1").tabso({
		cntSelect:"#normalcon1",
		tabEvent:"mouseover",
		tabStyle:"normal"
	});
	
	//默认选项卡切换
	jQuery("#normaltab2").tabso({
		cntSelect:"#normalcon2",
		tabEvent:"mouseover",
		tabStyle:"normal"
	});
	
	//默认选项卡切换
	jQuery("#normaltab3").tabso({
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
		 jQuery("#panel").show();
		 jQuery("#panel1").hide();
		 jQuery("#ID1").hide();
		 jQuery("#ID2").show();
		  jQuery("#ID4").hide();
		 jQuery("#ID3").show();		 
		}else if(num==3){
		  jQuery("#panel").hide();
		  jQuery("#panel1").show();
		  jQuery("#ID2").hide();
		 jQuery("#ID1").show();	
		  jQuery("#ID3").hide();
		 jQuery("#ID4").show();	
		}else  {
		  jQuery("#panel").hide();
		  jQuery("#panel1").hide();
		   jQuery("#ID1").show();
		 jQuery("#ID3").show();	
		  jQuery("#ID2").hide();
		 jQuery("#ID4").hide();
		}
	}
 
</script>
<!--日历插件 js-->
<script type="text/javascript" src="laydate/laydate.js"></script>


</head>


<body>

	<%-- <div class="place">
    <span>位置：</span>
    <ul class="placeul">
 <li><a href="#" onclick="overall();">首页</a></li>
    <li><a href="<%=path %>/borrow/toBackLssuing?queryFlag=0">借款管理</a></li>
     <c:if test="${queryFlag==3}"> <li><a href="<%=path %>/borrow/toBackLssuing?queryFlag=${queryFlag}">还款中借款</a></li></c:if>
    <c:if test="${queryFlag==4}"> <li><a href="<%=path %>/borrow/toBackLssuing?queryFlag=${queryFlag}">已完成中借款</a></li></c:if>
     <c:if test="${queryFlag==-1}"> <li><a href="<%=path %>/borrow/toBackLssuing?queryFlag=${queryFlag}">已逾期借款</a></li></c:if>
    <li><a href="#">投资人列表</a></li>
    </ul>
    </div> --%>
    
 
    <div class="rightinfo"> 
 <script>
!function(){
laydate.skin('default');//切换皮肤，请查看skins下面皮肤库
laydate({elem: '#demo1'});
laydate({elem: '#demo2'});//绑定元素
}();
</script>  
 
    	<ul class="toolbar">
         <li  id="ID1" onclick="addCheck('1')"><a href="<%=path %>/borrow/toBackLssuing?queryFlag=${queryFlag}">返回列表</a></li>
         </ul>
  <div  style="float: right;margin-right: 10px;margin-top: 5px">  
    共${fn:length(tenderList)}条记录 
 </div>
    
    
    <table class="tablelist">
    	<thead>
    	<tr>
         <th width="5%">标号</th>
        <th width="5%">用户名</th>
         <th width="14%">标题</th>
        <th width="9%">投资金额</th>
         <th width="5%">投资期限</th>
        <th width="6%">还款方式</th>
        <th width="9%">标种类型</th>
        
        </tr>
        </thead>
        <c:forEach var="tz" items="${tenderList }">
        <tr>
           <td>${tz.lssuing.lssuingNum}</td>
           <td>${tz.uservip.userName } </td>
           <td>${tz.lssuing.title} </td>
           <td>￥${tz.money} </td>
           <td>${tz.lssuing.periodday.periodDayName} ${tz.lssuing.periodtime.periodTimeName} </td>
           <td>${tz.lssuing.returnway.returnWayName}</td>
           <td> 
             <c:if test="${tz.lssuing.lssuingType==1}">信用标</c:if>
        	<c:if test="${tz.lssuing.lssuingType==2}">担保标</c:if>
        	<c:if test="${tz.lssuing.lssuingType==3}">秒标</c:if>
        	<c:if test="${tz.lssuing.lssuingType==4}">净值标</c:if>
        	<c:if test="${tz.lssuing.lssuingType==5}">抵押标</c:if> 
           
           
           </td>
 
                                
           </tr>
        
        </c:forEach>
        <tbody>

        </tbody>
    </table>

 </div>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>

</body>

</html>
