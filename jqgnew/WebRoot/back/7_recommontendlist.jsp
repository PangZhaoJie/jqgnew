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
<title>会员资金变动记录</title>
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

	<%-- <div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="javascript:void(0);" onclick="parent.frames.topFrame.overall();">首页</a></li>
    <li><a href="javascript:void(0);" onclick="parent.frames.topFrame.fund();">资金统计</a></li>
    <li><a href="#">会员资金变动记录</a></li>
    </ul>
    </div> --%>
    
    <div class="rightinfo">
    
     <div class="tools">
       <div id="panel">
              <div class="panel_title">搜索/筛选会员</div>
              <div class="panel_nr fn_cle">
                 <form enctype="multipart/form-data" id="queryId" action="<%=path %>/back/recommondTenderList" method="post">
				    <dl class="line">
				      <dt>会员名：</dt>
				      <dd><input type="text" value="${userNameQuery }" id="demo3" class="input" name="userNameQuery"  size="40"/> <span>不填则不限</span> </dd></dl>
				     				 
				     <dl class="line">
				      <dt>开始时间：</dt>
				      <dd><input class="laydate-icon" id="demo1" value="${startTimeQuery }" name="startTimeQuery"/>
				        <span>只选开始时间则查询从开始时间往后所有</span>
				      </dd></dl>
				      
				      <dl class="line">
				      <dt>结束时间：</dt>
				      <dd><input class="laydate-icon" id="demo2" value="${endTimeQuery }" name="endTimeQuery"/>
				        <span>只选结束时间则查询从结束时间往前所有</span>
				      </dd> </dl>
				    <div class="page_btn">
					  <input type="hidden" disabled="disabled" value="" id="fid" name="fid"/>
				      <input type="submit" value="确定" onclick="" id="showwait" class="btn_b" style="cursor:pointer;"/>
				    </div>
				</form>
              </div>
         </div>
         <c:if test="${sessionScope.powerss[123]==1}">
    	<ul class="toolbar">
         <li  id="ID1" onclick="addCheck('1')"><span><img src="<%=basePath%>/back/images/t01.png" /></span>搜索/筛选会员</li>
         <li  id="ID2" style="display:none" onclick="addCheck('2')"><span><img src="<%=basePath%>/back/images/t01.png" /></span>搜索/筛选完毕</li>
        </ul>
        </c:if>
        
        
  <script>
!function(){
laydate.skin('default');//切换皮肤，请查看skins下面皮肤库
laydate({elem: '#demo1'});
laydate({elem: '#demo2'});//绑定元素
}();
</script>        
    
    </div>
    
    
    <table class="tablelist">
    	<thead>
    	<tr>
             <th>ID</th>
            <th>投资人</th>
            <th>推荐人</th>
            <th>投资标号</th>
            <th>投资金额</th>
            <th>投资时间</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="bd" items="${tenderlist }">
        <tr>
           <td>${bd.tenderId }</td>
           <td>${bd.uservip.userName}</td>
          <td>${bd.uservip.uservip.userName }</td>
          <td>${bd.lssuing.lssuingNum }</td>
          <td><fmt:formatNumber value="${bd.money }" pattern="0.00"/>元</td>
          <td><fmt:formatDate value="${bd.tenderTime}" pattern="yyyy-MM-dd HH-mm" /></td>
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
    
    </div>
  	<input type="hidden"  value="<%=request.getRealPath("/")%>\uploadPhoto\资金变动记录.xls" id="urlId"  style="width: 300px"/><br/>	  
             
  <script type="text/javascript">
   
   	  function updownCheck(){
    	 var  $M=jQuery.noConflict();
		 var page=$M("#currentPageId").val();
     	 var form=document.getElementById("queryId");
    	 form.action="<%=path %>/back/recommondTenderList?updown=1&page="+page+"&urlstr="+encodeURI(encodeURI($M("#urlId").val()));
    	 form.submit();
   	  }
   
	  function toMoneyPage(num){
			 
		  var  $MP=jQuery.noConflict();
		  var page=$MP("#currentPageId").val();
		  var pageNum=$MP("#pageNumId").val();
		  var startTimeQuery=$MP("#demo1").val();
		  var endTimeQuery=$MP("#demo2").val();
		  var userNameQuery=$MP("#demo3").val();
 		  var p=$MP("#pId").val();
  	   	  if(num==1){
				  $MP("#page1-1").attr("href","<%=path %>/back/recommondTenderList?endTimeQuery="+endTimeQuery+"&userNameQuery="+userNameQuery+"&startTimeQuery="+startTimeQuery+"&page=1"); 
		  }
		  if(num==3){
 	 		   if(Number(page)<Number(pageNum)){
  				  $MP("#page1-3").attr("href","<%=path %>/back/recommondTenderList?endTimeQuery="+endTimeQuery+"&userNameQuery="+userNameQuery+"&startTimeQuery="+startTimeQuery+"&page="+(Number(page)+1));
	 		   }else{
 	 			  $MP("#page1-3").attr("href","<%=path %>/back/recommondTenderList?endTimeQuery="+endTimeQuery+"&userNameQuery="+userNameQuery+"&startTimeQuery="+startTimeQuery+"&page="+page);
	 		   }
				 
		  }
		  if(num==2){  
			  if(page>1){
				  $MP("#page1-2").attr("href","<%=path %>/back/recommondTenderList?endTimeQuery="+endTimeQuery+"&userNameQuery="+userNameQuery+"&startTimeQuery="+startTimeQuery+"&page="+(Number(page)-1));
			  }else{
	 			  $MP("#page1-2").attr("href","<%=path %>/back/recommondTenderList?endTimeQuery="+endTimeQuery+"&userNameQuery="+userNameQuery+"&startTimeQuery="+startTimeQuery+"&page="+page);
 
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
 				  $MP("#page1-4").attr("href","<%=path %>/back/recommondTenderList?endTimeQuery="+endTimeQuery+"&userNameQuery="+userNameQuery+"&startTimeQuery="+startTimeQuery+"&page="+Number(p));
		  }
		  if(num==5){
				  $MP("#page1-5").attr("href","<%=path %>/back/recommondTenderList?endTimeQuery="+endTimeQuery+"&userNameQuery="+userNameQuery+"&startTimeQuery="+startTimeQuery+"&page="+Number(pageNum)); 
		  }
	  }

</script> 
  
</body>

</html>
 
