<%@page import="com.jqg.service.impl.IPServiceImpl"%>
<%@page import="com.jqg.service.IPService"%>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
  <%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();if(path!=null && !path.equals("")){	if(path.contains("/")){		if(path.equals("/")){path = "";	}}else{	path += "/";}} 
String basePath = "";if(request.getServerPort()==80){basePath = request.getScheme()+"://"+request.getServerName()+path+"/";}else{basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";}    
IPService ipService = new IPServiceImpl();
String ip = ipService.getIpAddr(request);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="<%=path %>/back/css/style.css" rel="stylesheet" type="text/css" />
<style>
.forminfo li .select1 { color: #333333;padding-left: 5px;  height: 34px; line-height: 34px; margin-top:5px;  border-color: #a7b5bc #ced9df #ced9df #a7b5bc;  border-style: solid;  border-width: 1px;}
</style>
<script type="text/javascript" src="<%=path %>/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/jquery.form.js"></script>
<script type="text/javascript" src="<%=path %>/ckeditor/ckeditor.js"></script>  
<script type="text/javascript" src="<%=path %>/ckfinder/ckfinder.js"></script>
<!--日历插件 js-->
<script type="text/javascript" src="<%=path %>/back/laydate/laydate.js"></script>
<script type="text/javascript">
function btn(){

	var name = document.getElementById("lssuingName");
	 if(name.value==null||name.value=="")
	 {
	    alert("标名不能为空！");
	    return;
	 }
	var money = document.getElementById("money");
	if(money.value==null||money.value=="")
	{
		    alert("金额不能为空！");
		    return;
	 }
	var rate = document.getElementById("rate");
	if(rate.value==null||rate.value=="")
	{
		    alert("年利率不能为空！");
		    return;
	 }
	var awaed = document.getElementById("awaed");
	if(awaed.value==null||awaed.value=="")
	{
		    alert("投标奖励不能为空！");
		    return;
	 }
	
	var time = document.getElementById("time");
	if(time.value==null||time.value=="")
	{
		    alert("发标时间不能为空！");
		    return;
	 }
	document.getElementById("uptlssh").submit();
	document.getElementById("submit_btn").style.background = "gray";
    document.getElementById("submit_btn").value="正在提交...";
    document.getElementById("submit_btn").style.disabled=false;
	alert("修改成功！");
	}
</script>

</head>

<body>

	<%-- <div class="place">
    <span>位置：</span>
    <ul class="placeul">
       <li><a href="javascript:void(0);" onclick="parent.frames.topFrame.overall();">首页</a></li>
       <li><a href="#" onclick="parent.frames[1].location.href='<%=path %>/back/9_left.jsp';  parent.frames[2].location.href='<%=path %>/overall/findlssh'; ">文章管理</a></li>
       <li><a href="#">文章编辑</a></li>
    </ul>
    </div> --%>
    
    <div class="formbody">
    
    <div class="formtitle"><span>文章编辑</span></div>
       <form id="uptlssh" action="<%=path %>/overall/uptlssh" method = "post" >
        <input type="text"  id ="ip" value ="<%=ip %>" style="display: none"/>
    <ul class="forminfo">
      <li style="display: none;"><label >标名：</label><input name="lssuingHeraldId" id=""type="text" class="dfinput" style="width:335px;display: none;" value="${lssuingherald.lssuingHeraldId}" size="70" id="title"/> <i>*</i></li>
      <li><label id="info_title2">标名：</label><input name="lssuingName" id="lssuingName"type="text" class="dfinput" style="width:335px;" value="${lssuingherald.lssuingName}" size="70" id="title"/> <i>*</i></li>
      <li><label id="info_title2">金额：</label><input name="money" id="money"type="text" class="dfinput" style="width:335px;" value="${lssuingherald.money}" size="70" id="title"/> <i>*</i></li>
      <li><label id="info_title2">年利率：</label><input name="rate" id="rate"type="text" class="dfinput" style="width:335px;" value="${lssuingherald.rate}" size="70" id="title"/> <i>%*</i></li>
      <li><label id="info_title2">投资奖励：</label><input name="awaed"id="awaed" type="text" class="dfinput" style="width:335px;" value="${lssuingherald.awaed}" size="70" id="title"/> <i>%*无投标奖励填0</i></li>
     <li><label id="info_title2">借款期限：</label>
<!--          <select class="select1" style="width:335px;" name="periodtimeId">-->
<!--             	 <c:forEach var="periodtime" items="${periodtimes}">-->
<!--		             <option  value="${periodtime.periodTimeId}" -->
<!--		             <c:if test="${periodtime.periodTimeId==lssuingherald.periodtime.periodTimeId}">-->
<!--		             selected = "selected" -->
<!--		             </c:if>-->
<!--		             >${periodtime.periodTimeName}</option>-->
<!--				 </c:forEach> -->
<!--                -->
<!--                </select>-->
			<input class="select1" type="text" name="time" style="width:330px;" value="${lssuingherald.time }" />
               
            <i>*</i></li>
                <li><label id="info_title2">还款方式：</label>
          <select class="select1" style="width:335px;" name="returnwayId">
             	 <c:forEach var="returnway" items="${returnways}">
		             <option  value="${returnway.returnWayId}" 
		             <c:if test="${returnway.returnWayId==lssuingherald.returnway.returnWayId}">
		             selected = "selected" 
		             </c:if>
		             >${returnway.returnWayName}</option>
				 </c:forEach> 
                
                </select>
               
            <i>*</i></li>
                
     <li><label id="info_title2">发标时间：</label><input name="heraldTime" id="time"type="text" readonly="readonly" class="dfinput" style="width:335px;" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})"  value="${lssuingherald.heraldTime}" /> <i>按照2014-09-09 08:36:59格式</i></li>
<c:if test="${sessionScope.powerss[64]==1}">
    <li><label>&nbsp;</label><input id="submit_btn" type="button" class="btn" value="修改" onclick="btn()"/></li>
 </c:if>   
    </ul>
   </form>
    </div>


</body>

</html>
