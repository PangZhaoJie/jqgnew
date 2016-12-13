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
                 <form enctype="multipart/form-data" id="queryId" action="<%=path %>/money/toMoneyhistorydetail" method="post">
                 <input type="hidden" name="userId" id="userId" value="${userId}"/>
				    <dl class="line">
				      <dt>会员名：</dt>
				      <dd><input type="text" value="${userNameQuery }" id="demo3" class="input" name="userNameQuery"  size="40"/> <span>不填则不限</span> </dd></dl>
				     <dl class="line">
					      <dt>影响金额：</dt>
					       <dd>
						  	<select onchange="" id="demo4" class="input" name="mark" style="width:118px;margin-left: 8px" >
					          <option value="0">--请选择--</option>
						  	  <option value="1" <c:if test="${mark==1 }">selected="selected"</c:if> >大于</option>
					          <option value="2" <c:if test="${mark==2 }">selected="selected"</c:if>  >等于</option>		
					          <option value="3" <c:if test="${mark==3 }">selected="selected"</c:if>  >小于</option>
					        
					        </select>
					         <input type="text" value="${moneyQuery}" id="demo5" class="input" name="moneyQuery" size="19" style="margin-left:8px;" />
					        <span>不填则不限</span>
					      </dd>
					    </dl>
				 
				     <dl class="line">
				      <dt>交易开始时间：</dt>
				      <dd><input class="laydate-icon" id="demo1" value="${startTimeQuery }" name="startTimeQuery"/>
				        <a style="color: #7d7d7d">只选开始时间则查询从开始时间往后所有</a>
				      </dd></dl>
				      
				      <dl class="line">
				      <dt>交易发生时间：</dt>
				      <dd><input class="laydate-icon" id="demo2" value="${endTimeQuery }" name="endTimeQuery"/>
				        <a style="color: #7d7d7d">只选结束时间则查询从结束时间往前所有</a>
				      </dd> </dl>
				    <div class="page_btn">
					  <input type="hidden" disabled="disabled" value="" id="fid" name="fid"/>
				      <input type="submit" value="确定" onclick="" id="showwait" class="btn_b" style="cursor:pointer;"/>
				    </div>
				</form>
              </div>
         </div>
    	<ul class="toolbar">
    	<c:if test="${sessionScope.powerss[102]==1}">
         <li  id="ID1" onclick="addCheck('1')"><span><img src="<%=basePath%>/back/images/t01.png" /></span>搜索/筛选会员</li>
         <li  id="ID2" style="display:none" onclick="addCheck('2')"><span><img src="<%=basePath%>/back/images/t01.png" /></span>搜索/筛选完毕</li>
         <li><span><img src="<%=basePath%>/back/images/t03.png" /></span>
         </c:if>
         <c:if test="${sessionScope.powerss[54]==1}">
         <a href = "javascript:void(0)" class="green" id="upId" onclick = "updownCheck()"> 将当前条件下数据导出为Excel</a></li>
       	</c:if>
        </ul>
        
        
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
            <th>用户名</th>
            <th>影响金额</th>
            <th>可用余额</th>
            <th>冻结金额</th>
            <th>待收金额</th>
            <th>交易时间</th>
            <th>备注</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="bd" items="${moneyhistorydetailList }">
        <tr>
           <td>${bd.moneyHistoryDetailId }</td>
           <td>${bd.uservip.userName}</td>
          <td><fmt:formatNumber value="${bd.affectMoney }" pattern="0.00"/>元</td>
          <td><fmt:formatNumber value="${bd.availableBalance }" pattern="0.00"/>元</td>
          <td><fmt:formatNumber value="${bd.frozenMoney }" pattern="0.00"/>元</td>
          <td><fmt:formatNumber value="${bd.collectMoney }" pattern="0.00"/>元</td>
           <td><fmt:formatDate value="${bd.occurTime}" pattern="yyyy-MM-dd HH-mm" /></td>
          <td>${bd.introduction}</td>
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
    	 form.action="<%=path %>/money/toExcel?updown=1&page="+page+"&urlstr="+encodeURI(encodeURI($M("#urlId").val()));
    	 form.submit();
   	  }
   
	  function toMoneyPage(num){
			 
		  var  $MP=jQuery.noConflict();
		  var page=$MP("#currentPageId").val();
		  var pageNum=$MP("#pageNumId").val();
		  var startTimeQuery=$MP("#demo1").val();
		  var endTimeQuery=$MP("#demo2").val();
		  var mark=$MP("#demo4").val();
		  var moneyQuery=$MP("#demo5").val();
		  var userNameQuery=$MP("#demo3").val();
 		  var p=$MP("#pId").val();
 		  var userId = $MP("#userId").val();
  	   	  if(num==1){
				  $MP("#page1-1").attr("href","<%=path %>/money/toMoneyhistorydetail?endTimeQuery="+endTimeQuery+"&userNameQuery="+userNameQuery+"&moneyQuery="+moneyQuery+"&startTimeQuery="+startTimeQuery+"&mark="+mark+"&page=1"+"&userId="+userId); 
		  }
		  if(num==3){
 	 		   if(Number(page)<Number(pageNum)){
  				  $MP("#page1-3").attr("href","<%=path %>/money/toMoneyhistorydetail?endTimeQuery="+endTimeQuery+"&userNameQuery="+userNameQuery+"&moneyQuery="+moneyQuery+"&startTimeQuery="+startTimeQuery+"&mark="+mark+"&page="+(Number(page)+1)+"&userId="+userId);
	 		   }else{
 	 			  $MP("#page1-3").attr("href","<%=path %>/money/toMoneyhistorydetail?endTimeQuery="+endTimeQuery+"&userNameQuery="+userNameQuery+"&moneyQuery="+moneyQuery+"&startTimeQuery="+startTimeQuery+"&mark="+mark+"&page="+page+"&userId="+userId);
	 		   }
				 
		  }
		  if(num==2){  
			  if(page>1){
				  $MP("#page1-2").attr("href","<%=path %>/money/toMoneyhistorydetail?endTimeQuery="+endTimeQuery+"&userNameQuery="+userNameQuery+"&moneyQuery="+moneyQuery+ "&startTimeQuery="+startTimeQuery+"&mark="+mark+"&page="+(Number(page)-1)+"&userId="+userId);
			  }else{
	 			  $MP("#page1-2").attr("href","<%=path %>/money/toMoneyhistorydetail?endTimeQuery="+endTimeQuery+"&userNameQuery="+userNameQuery+"&moneyQuery="+moneyQuery+"&startTimeQuery="+startTimeQuery+"&mark="+mark+"&page="+page+"&userId="+userId);
 
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
 				  $MP("#page1-4").attr("href","<%=path %>/money/toMoneyhistorydetail?endTimeQuery="+endTimeQuery+"&userNameQuery="+userNameQuery+"&moneyQuery="+moneyQuery+"&startTimeQuery="+startTimeQuery+"&mark="+mark+"&page="+Number(p)+"&userId="+userId);
		  }
		  if(num==5){
				  $MP("#page1-5").attr("href","<%=path %>/money/toMoneyhistorydetail?endTimeQuery="+endTimeQuery+"&userNameQuery="+userNameQuery+"&moneyQuery="+moneyQuery+"&startTimeQuery="+startTimeQuery+"&mark="+mark+"&page="+Number(pageNum)+"&userId="+userId); 
		  }
	  }

</script> 
  
</body>

</html>
 
