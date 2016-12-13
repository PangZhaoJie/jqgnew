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
<title>会员投标记录</title>
<link href="<%=basePath %>/back/css/style.css" rel="stylesheet" type="text/css" />
<link href="../css/user/user_public.css" type="text/css" rel="stylesheet"/>
<link href="../css/user/user_repay.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="<%=basePath %>/back/js/jquery.js"></script>
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
<script type="text/javascript" src="<%=basePath %>/back/laydate/laydate.js"></script>
</head>
<body>
 <div class="rightinfo">
     <div class="tools">
       <div id="panel">
              <div class="panel_title">搜索/筛选投标记录</div>
              <div class="panel_nr fn_cle">
                 <form enctype="multipart/form-data" id="queryId" action="<%=path %>/back/findRecord" method="post">
                 <input type="hidden" name="userId" id="userId" value="${userId}"/>
				    <dl class="line">
				      <dt>会员名：</dt>
				      <dd><input type="text" value="${userNameQuery }" id="demo1" class="input" name="userNameQuery"/> <span>不填则不限</span> </dd></dl>
				     <dl class="line">
				      <dt>投标项目名称：</dt>
				       <dd>
				         <input type="text" value="${title}" id="demo2" class="input" name="title"  style="margin-left:8px;" />
				        <span>不填则不限</span>
				      </dd>
					    </dl>
				 
				     <dl class="line">
				      <dt>投标时间：</dt>
				      <dd>
				      <input class="laydate-icon" id="demo3" value="${startTimeQuery }" name="startTimeQuery"/>
				      </dd>
				      </dl>
				      
				    <div class="page_btn">
					  <input type="hidden" disabled="disabled" value="" id="fid" name="fid"/>
				      <input type="submit" value="确定" onclick="" id="showwait" class="btn_b" style="cursor:pointer;"/>
				    </div>
				</form>
		<script>
		!function(){
		laydate.skin('default');//切换皮肤，请查看skins下面皮肤库
		laydate({elem: '#demo3'});//绑定元素
		}();
		</script>
              </div>
         </div>
    	<ul class="toolbar">
    	<c:if test="${sessionScope.powerss[102]==1}">
         <li  id="ID1" onclick="addCheck('1')"><span><img src="<%=basePath%>/back/images/t01.png" /></span>搜索/筛选投标记录</li>
         <li  id="ID2" style="display:none" onclick="addCheck('2')"><span><img src="<%=basePath%>/back/images/t01.png" /></span>搜索/筛选完毕</li>
        <%--  <li><span><img src="<%=basePath%>/back/images/t03.png" /></span> --%>
         </c:if>
        <%--  <c:if test="${sessionScope.powerss[54]==1}">
         <a href = "javascript:void(0)" class="green" id="upId" onclick = "updownCheck()"> 将当前条件下数据导出为Excel</a></li>
       	</c:if> --%>
        </ul>
        </div>
      <table class="tablelist">
    	<thead>
    	<tr>
            <th>投标记录</th>
            <th>投标名称</th>
            <th>投标人</th>
            <th>当前利率</th>
            <th>投标金额</th>
            <th>投标时间</th>
            <th>投标状态</th>
            <th>购买份数</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="record" items="${recordList }">
        <tr>
           <td>${record.tenderId}</td>
           <td>${record.lssuing.title}</td>
           <td>${record.uservip.userName}</td>
           <td>${record.lssuing.rate}%</td>
           <td>${record.money}元</td>
           <td><fmt:formatDate value="${record.tenderTime}" pattern="yyyy-MM-dd HH-mm" /></td>
            <td>
            <c:if test="${record.state==2}">
           		已完成
           </c:if>
           <c:if test="${record.state==1}">
           		还款中
           </c:if>
           </td>
           <td>${record.buynum}</td>
        </tr> 
  		</c:forEach>
        </tbody>
    </table>
   <div class="pagin">
    	<div class="message">共<i class="blue">${total}</i>条记录，当前显示第&nbsp;<i class="blue">${page}&nbsp;</i>页 ,共${pageCount}页 </div>
        <ul class="paginList">
         <li class="paginItem"><a href="" style="width: 50px"  onclick="toMoneyPage('1')" id="page1-1">首页</a></li>
        <li class="paginItem"><a href="" style="width: 60px" onclick="toMoneyPage('2')" id="page1-2">上一页</a></li>
        <li class="paginItem"><a href="" style="width: 60px" onclick="toMoneyPage('3')" id="page1-3">下一页</a></li>
        <li class="paginItem"> 
        <a href="#" style="width: 50px" onclick="toMoneyPage('4')" id="page1-4">转到</a>
        <input name="page" type="text" id="pId" value=""  style="width:31px;height:28px; border:1px solid #DDD; text-align:center;line-height:30px ;color:#3399d5" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>页
        </li>
        <li class="paginItem"><a href="" style="width: 60px;border-left:dashed;border:1px solid #DDD;" onclick="toMoneyPage('5')" id="page1-5">最后一页</a></li>
         </ul>
         <input name="currentPage" value="${page}" id="currentPageId" style="display: none"/>
         <input name="total" value="${pageCount}" id="pageNumId" style="display: none"/>
      </div>
<%--       	<input type="hidden"  value="<%=request.getRealPath("/")%>\uploadPhoto\投标记录.xls" id="urlId"  style="width: 300px"/><br/> --%>	  
</body>
<script type="text/javascript">
<%--  function updownCheck(){
    	 var  $M=jQuery.noConflict();
		 var page=$M("#currentPageId").val();
     	 var form=document.getElementById("queryId");
    	 form.action="<%=path %>/money/toExcel?updown=1&page="+page+"&urlstr="+encodeURI(encodeURI($M("#urlId").val()));
    	 form.submit();
   	  } --%>
   
 function toMoneyPage(num){
		  var  $MP=jQuery.noConflict();
		  var page=$MP("#currentPageId").val();
		  var pageNum=$MP("#pageNumId").val();
		  var userNameQuery=$MP("#demo1").val();
		  var title=$MP("#demo2").val();
		  var startTimeQuery=$MP("#demo3").val();
		  var mark=$MP("#demo4").val();
 		  var p=$MP("#pId").val();
 		  var userId = $MP("#userId").val();
  	   	  if(num==1){
				  $MP("#page1-1").attr("href","<%=path %>/back/findRecord?userNameQuery="+userNameQuery+"&moneyQuery="+title+"&startTimeQuery="+startTimeQuery+"&page=1"+"&userId="+userId); 
		  }
		  if(num==3){
 	 		   if(Number(page)<Number(pageNum)){
  				  $MP("#page1-3").attr("href","<%=path %>/back/findRecord?userNameQuery="+userNameQuery+"&moneyQuery="+title+"&startTimeQuery="+startTimeQuery+"&page="+(Number(page)+1)+"&userId="+userId);
	 		   }else{
 	 			  $MP("#page1-3").attr("href","<%=path %>/back/findRecord?userNameQuery="+userNameQuery+"&moneyQuery="+title+"&startTimeQuery="+startTimeQuery+"&page="+page+"&userId="+userId);
	 		   }
				 
		  }
		  if(num==2){  
			  if(page>1){
				  $MP("#page1-2").attr("href","<%=path %>/back/findRecord?userNameQuery="+userNameQuery+"&moneyQuery="+title+"&startTimeQuery="+startTimeQuery+"&page="+(Number(page)-1)+"&userId="+userId);
			  }else{
	 			  $MP("#page1-2").attr("href","<%=path %>/back/findRecord?userNameQuery="+userNameQuery+"&moneyQuery="+title+"&startTimeQuery="+startTimeQuery+"&page="+page+"&userId="+userId);
 
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
 				  $MP("#page1-4").attr("href","<%=path %>/back/findRecord?userNameQuery="+userNameQuery+"&moneyQuery="+title+"&startTimeQuery="+startTimeQuery+"&page="+Number(p)+"&userId="+userId);
		  }
		  if(num==5){
				  $MP("#page1-5").attr("href","<%=path %>/back/findRecord?userNameQuery="+userNameQuery+"&moneyQuery="+title+"&startTimeQuery="+startTimeQuery+"&page="+Number(pageNum)+"&userId="+userId); 
		  }
	  }
</script>

</html>
 
