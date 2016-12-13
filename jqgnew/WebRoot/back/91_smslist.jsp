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
<title>短信发送记录</title>
<link href="<%=basePath%>back/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>/css/user/user_repay.css" type="text/css" rel="stylesheet"/>
<!-- <link href="css/select.css" rel="stylesheet" type="text/css" /> -->
<link href="<%=basePath%>back/css/tabs.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>back/js/jquery.js"></script>

<!--弹窗中的滑动js-->
<script type="text/javascript" src="<%=basePath%>back/js/jquery.tabso_yeso.js"></script>
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
<script type="text/javascript" src="<%=basePath%>back/laydate/laydate.js"></script>
<script type="text/javascript">
  function toMoneyPage(num){
	  var  $MP=jQuery.noConflict();
	  var page=$MP("#currentPageId").val();
	  var pageNum=$MP("#pageNumId").val();
	  var p=$MP("#pId").val();
	  var flag=$MP("#flag").val();
	  var mark=$MP("#mark").val();
 	  var userName=$MP("#demo3").val();
	  var vipName=$MP("#demo4").val();
	  var recharge=$MP("#demo6").val();
	  var startTime=$MP("#demo1").val();
	  var endTime=$MP("#demo2").val();
	  var rechargeStatu=document.getElementsByName("rechargeStatus");
	  var rechargeStatus=-1;
	  for(var i=0;i<rechargeStatu.length;i++){
		  if(rechargeStatu[i].checked){
			 rechargeStatus=rechargeStatu[i].value; 
		  }
	  }
	  if(mark!=null){//搜索标志
		  if(recharge!=null && recharge!='undefined'){
		  recharge=encodeURI(encodeURI(recharge));
		  }
	  }
   	  if(num==1){
   		  	
			  $MP("#page1-1").attr("href","<%=path %>/extension/smsSendLog.action?&page=1"); 
	  }
	  if(num==3){
		     if(Number(page)<Number(pageNum)){
			  $MP("#page1-3").attr("href","<%=path %>/extension/smsSendLog.action?page="+(Number(page)+1));
		     }else{
				  $MP("#page1-3").attr("href","<%=path %>/extension/smsSendLog.action?&page="+(Number(page))); 
		     }
	  }
	  if(num==2){
		  if(page>1){
			  $MP("#page1-2").attr("href","<%=path %>/extension/smsSendLog.action?&page="+(Number(page)-1));
			  
		  }else{
			  $MP("#page1-2").attr("href","<%=path %>/extension/smsSendLog.action?&page="+(Number(page)));
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
			  p=pageNum;
		  }
	 
			  $MP("#page1-4").attr("href","<%=path %>/extension/smsSendLog.action?&page="+Number(p));
	  }
	  if(num==5){
   		  	
			  $MP("#page1-5").attr("href","<%=path %>/extension/smsSendLog.action?&page="+Number(pageNum)); 
	  }
  }
  function search(){
	  var flag=document.getElementById("flag").value;
	  var form=document.getElementById("search");
	  form.action="<%=path %>/extension/smsSendLog.action?flag="+flag+"&mark=0"
	  form.submit();
  }
  function startCheck(updown){
		if(updown==2){
			alert("导出成功");
		}
	}
</script>
<script language="JavaScript" src="<%=path %>/back/js/top.js"></script>
<style>
.mem_table{overflow:scroll; clear: both;}
.mem_table table{ width:2500px; text-align:left; }
.mem_table table th { padding:0 3px; text-align: left; font-weight:normal;}
.mem_table table td {/* border-bottom: 1px solid #e3e6eb;*/ padding:0 5px; text-indent:3px;}
</style>

</head>


<body onload="startCheck(${updown})">
<input type="hidden" id="path" value="<%=path %>" />
	<%-- <div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="javascript:void(0);" onclick="parent.frames.topFrame.overall();">首页</a></li>
    <li><a href="javascript:void(0);" onclick="parent.frames.topFrame.user();">充值提现</a></li>
    <c:if test="${flag=='0'}">
    <li><a href="#">线上充值</a></li>
    </c:if>
    <c:if test="${flag=='1'}">
    <li><a href="#">线下充值</a></li>
    </c:if>
    <c:if test="${flag=='2'}">
    <li><a href="#">充值记录总列表</a></li>
    </c:if>
    </ul>
    </div> --%>
    
    <div class="rightinfo">
    
       
    
    <table class="tablelist">
    	<thead>
    	<tr>
        <th width="5%">ID</th>
        <th width="6%">会员名</th>
        <th width="6%">手机号码</th>
        <th width="6%">发送内容</th>
        <th width="6%">发送时间</th>
        <th width="8%">操作</th>
       
        </tr>
        </thead>
        
        <c:forEach var="smsSendLog" items="${smsSendLogs}">
        <tbody>
          <tr>
          <td>${smsSendLog.logId}</td>
          <td>${smsSendLog.uservip.userName}</td>
          <td>${smsSendLog.tophone}</td>
          <td>${smsSendLog.content }</td>
          <td><fmt:formatDate value="${smsSendLog.sendTime}" pattern="yyyy-MM-dd HH:mm" />
          <td>
               
          </td></tr></tbody>
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
     <!--首先设置一个层:-->
                    
<!--      <div id="hide1" class="white_content" style="height: 220px;width:500px;"> -->
<%-- 	            <div class="light_title"><span>提示信息</span> <a href = "javascript:void(0)" onclick = "document.getElementById('hide1').style.display='none';document.getElementById('fade').style.display='none'" class="close"><img src="../images/close1.png" /></a></div> --%>
<!-- 	            <div class="letter_info">导出路径： -->
	                  <input type="hidden" value="<%=request.getRealPath("/")%>\uploadPhoto\充值信息.xls" id="urlId" style="width: 280px"/>
<!-- 	                   <input type="button" value="确定" class="sure" onclick="updownCheck()"  -->
<!-- 	                  style="margin-top: 30px" name=""/> -->
 		        	  
<!-- 	            </div> -->
            
<!--       </div>  -->
	<div id="fade" class="black_overlay"></div>
	 <div id="pop" class="pop" style="display:none">
         <div class="pop_head"><a class="close" href="javascript:void(0);" onclick="hide()"></a></div>
         <div class="pop_body" style="margin-top:-340px;margin-left:610px;"><img id="picture1" style="width:400px;"/></div>
     </div> 
      <script type="text/javascript">
      	function updownCheck(){extension
     	  var  $MP=jQuery.noConflict();
 		  var page=$MP("#currentPageId").val();
      	  var form=document.getElementById("search");
     	  form.action="<%=path %>/extension/smsSendLog.action?updown=1&flag=2&mark=0&page="+page+"&urlstr="+encodeURI(encodeURI($MP("#urlId").val()));
     	  form.submit();
    	}
      	function pic(path){
      	   input=document.getElementById("picture1");
      	   input.src=path;
      		 
        }
      </script>
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
<script type="text/javascript" src="<%=basePath%>back/js/pic.js"></script>
</body>

</html>
