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
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="<%=basePath%>/back/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>/back/js/jquery.js"></script>
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
<%-- <script type="text/javascript" src="<%=basePath%>/back/laydate/laydate.js"></script> --%>

<script type="text/javascript">
 
</script>
</head>


<body  style="min-width: 1300px"">

    <div class="rightinfo">
    
    <div class="tools">
       <div id="panel">
              <div class="panel_title">搜索/筛选借款</div>
              <div class="panel_nr fn_cle">
  <form  onsubmit="" action="<%=path %>/transfer/findTransger2" method="post">
    <dl class="line">
      <dt>转让人：</dt>
      <dd>
        <input type="text"  id="demo3" class="input" name="userNameQuery" value="${userNameQuery }" size="40">
        <span>不填则不限制</span>
      </dd>
    </dl>
    
    <dl class="line">
      <dt>购买人：</dt>
      <dd>
        <input type="text"   id="demo4" class="input" name="userNameQuery2" value="${userNameQuery2 }" size="40">
        <span>不填则不限制</span>
      </dd>
    </dl>
    <dl class="line">
       <dt>借款标题：</dt>
      <dd>
        <input type="text"   id="demo2" class="input" name="title" value="${title }" size="40">
        <span>不填则不限制</span>
      </dd>
    </dl>
    <dl class="line">
       <dt>转让金额：</dt>
      <dd>
        <input type="text"   id="demo1" class="input" name="money" value="${money }" size="40">
        <span>不填则不限制</span>
      </dd>
    </dl>
	
 <script>
!function(){
laydate.skin('default');//切换皮肤，请查看skins下面皮肤库
laydate({elem: '#demo1'});
laydate({elem: '#demo2'});//绑定元素
}();
</script>  
	
    
		 
    <div class="page_btn">
<!-- 	  <input type="hidden" disabled="disabled" value="" id="fid" name="fid"> -->
	 
      	<input type="submit" value="搜索" onclick="" id="showwait" class="btn_b">
     
    </div>
	</form>
              </div>
         </div>
    	<ul class="toolbar">
         <li  id="ID1" onclick="addCheck('1')"><span><img src="<%=basePath %>/back/images/t06.png" /></span>搜索筛选借款</li>
         <li  id="ID2" style="display:none" onclick="addCheck('2')"><span><img src="<%=basePath %>/back/images/t06.png" /></span>搜索完毕</li>
        </ul>
    </div>
    <table class="tablelist">
    	<thead>
    	<tr>
        <th width="4%">ID</th>
        <th width="6%">转让人</th>
        <th width="10%">借款标题</th>
        <th width="4%">利率</th>
        <th width="5%">待收金额</th>
        <th width="5%">转让金额</th>
        <th width="5%">购买人</th>
        <th width="10%">提交时间</th>
        <th width="10%">审核时间</th> 
        <th width="10%">完成时间</th>
        <th width="4%">转让状态</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="tl" items="${tList }">
         <tr><td>${tl.transferId}</td>
        <td>
        <c:if test="${tl.tender1!=null }">${tl.tender1.uservip.userName }</c:if>
        </td>
        <td>
        	<a  target="_blank"  href="<%=path %>/tender/totender.action?lssuingId=${tl.tender1.lssuing.lssuingId}" >${tl.tender1.lssuing.title}</a>
        </td>
        <td>${tl.tender1.lssuing.rate} </td>
      
        <td><fmt:formatNumber value="${tl.tender1.money }" pattern="0.00"/></td>
        <td><fmt:formatNumber value="${tl.money }" pattern="0.00"/></td>
         <td>
        <c:if test="${tl.buyUser!=null }">${tl.buyUser.userName }</c:if>
        </td>
        <td><fmt:formatDate value="${tl.transferTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
        <td><fmt:formatDate value="${tl.checkTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
        <td><fmt:formatDate value="${tl.buyTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
        
         <td>
        <c:if test="${tl.isTransfer==0 }">待审核</c:if>
        <c:if test="${tl.isTransfer==1 }">进行中</c:if>
        <c:if test="${tl.isTransfer==2 }">已完成</c:if>
        <c:if test="${tl.isTransfer==3 }">已撤销</c:if>
        </td>
        

        </tr> 
  		</c:forEach>
        </tbody>
    </table>
    
   
    <div class="pagin">
    	<div class="message">共<i class="blue">${total}</i>条记录，当前显示第&nbsp;<i class="blue">${page}&nbsp;</i>页 ,共${pageCount}页 </div>
        <ul class="paginList">
         <li class="paginItem"><a href="" style="width: 50px"  onclick="toMoneyPage('1',${queryFlag})" id="page1-1">首页</a></li>
        <li class="paginItem"><a href="" style="width: 60px" onclick="toMoneyPage('2',${queryFlag})" id="page1-2">上一页</a></li>
        <li class="paginItem"><a href="" style="width: 60px" onclick="toMoneyPage('3',${queryFlag})" id="page1-3">下一页</a></li>
        <li class="paginItem"> 
        <a href="#" style="width: 50px" onclick="toMoneyPage('4',${queryFlag})" id="page1-4">转到</a>&nbsp;
        <input name="page" type="text" id="pId" value=""  style="width:31px;height:28px; border:1px solid #DDD; text-align:center;line-height:30px ;color:#3399d5" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>页
        </li>
         <li class="paginItem"><a href="" style="width: 60px;border-left:dashed;border:1px solid #DDD; " onclick="toMoneyPage('5',${queryFlag})" id="page1-5">最后一页</a></li>
         </ul>
         <input name="currentPage" value="${page}" id="currentPageId" style="display: none"/>
         <input name="total" value="${pageCount}" id="pageNumId" style="display: none"/>
      </div>
    
    
    
    
    
    </div>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	
	  function toMoneyPage(num,num1){
			 
		  var  $MP=jQuery.noConflict();
		  var page=$MP("#currentPageId").val();
		  var pageNum=$MP("#pageNumId").val();
		  var userNameQuery=$MP("#demo3").val();
		  var userNameQuery2=$MP("#demo4").val();
		  var title=$MP("#demo1").val();
		  var money=$MP("#demo2").val();
		  var p=$MP("#pId").val();
	   	  if(num==1){
				  $MP("#page1-1").attr("href","<%=path %>/transfer/findTransger2?userNameQuery="+userNameQuery+"&userNameQuery2="+userNameQuery2+"&title="+title+"&money="+money+"&page=1"); 
		  }
		  if(num==3){
			  if(Number(page)<Number(pageNum)){
				  $MP("#page1-3").attr("href","<%=path %>/transfer/findTransger2?userNameQuery="+userNameQuery+"&userNameQuery2="+userNameQuery2+"&title="+title+"&money="+money+"&page="+(Number(page)+1));
			  }else{
				  $MP("#page1-3").attr("href","<%=path %>/transfer/findTransger2?userNameQuery="+userNameQuery+"&userNameQuery2="+userNameQuery2+"&title="+title+"&money="+money+"&page="+page);
			  }
		  }
		  if(num==2){
			  if(page>1){
				  $MP("#page1-2").attr("href","<%=path %>/transfer/findTransger2?userNameQuery="+userNameQuery+"&userNameQuery2="+userNameQuery2+"&title="+title+"&money="+money+"&page="+(Number(page)-1));
			  }else{
				  $MP("#page1-2").attr("href","<%=path %>/transfer/findTransger2?userNameQuery="+userNameQuery+"&userNameQuery2="+userNameQuery2+"&title="+title+"&money="+money+"&page="+page);
			  }
		  }
		  if(num==4){
		  	  if(p==null || p=="" || parseInt(p)< 1 || parseInt(p)>parseInt(pageNum)){
	  			alert("请输入正确的页数！");
	  			return false;
	  		}
			  if(p<1){
				  p=1;
			  }else if(Number(p)>Number(pageNum)){
				  p=pageNum
			  }
		 
				  $MP("#page1-4").attr("href","<%=path %>/transfer/findTransger2?userNameQuery="+userNameQuery+"&userNameQuery2="+userNameQuery2+"&title="+title+"&money="+money+"&page="+Number(p));
		  }
		  if(num==5){
		  	$MP("#page1-5").attr("href","<%=path %>/transfer/findTransger2?userNameQuery="+userNameQuery+"&userNameQuery2="+userNameQuery2+"&title="+title+"&money="+money+"&page="+Number(pageNum)); 
		  }
		  
	  }
	</script>

</body>

</html>
