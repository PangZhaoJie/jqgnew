<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="/struts-tags" prefix="s"%>
<%    
	String path = request.getContextPath();if(path!=null && !path.equals("")){	if(path.contains("/")){		if(path.equals("/")){path = "";	}}else{	path += "/";}}    
	String basePath = "";if(request.getServerPort()==80){basePath = request.getScheme()+"://"+request.getServerName()+path+"/";}else{basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";}      
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人中心-积分记录</title>
<link rel="icon" href="<%=path %>/image/favicon/favicon.ico" type="image/x-icon" />
<link href="<%=basePath%>/css/public.css" type="text/css" rel="stylesheet"/>
<link href="<%=basePath%>/css/user/user_public.css" type="text/css" rel="stylesheet"/>
<link href="<%=basePath%>/css/user/user_jifen.css" type="text/css" rel="stylesheet"/>
<!--导航下拉菜单 js-->
<script type="text/javascript" src="<%=basePath%>/js/jquery-1.8.2.min.js"></script>
<!--表单下拉列表框-->
<%--<script type="text/javascript" src="<%=basePath%>/js/select2css.js"></script>--%>


<script type="text/javascript">
		var  $p=jQuery.noConflict();
		function getJSONData(currentPage,pageSize) {
			//6位随机数
			var Num=""; 
			for(var i=0;i<6;i++) 
			{ 
			Num+=Math.floor(Math.random()*10); 
			} 
			var url = "<%=path %>/user/integralByIdPage.action?currentPage="+currentPage+"&pageSize="+pageSize+"&Num="+Num;
			$p.getJSON(url,function(data){
				var cp = data.currentPage;
				var tp = data.totalPage;
				var $ptable = $p("#listTable");
				$ptable.empty();
 				str = "<tr><td width='160'>发生日期</td><td width='160'>影响积分</td><td width='160'>剩余积分</td><td width='120'>说明</td></tr>";
				var jsonRoot = data.jsonRoot;
				for(var i = 0; i < jsonRoot.length; i++) {
					str += "<tr><td>"+ decodeURI(jsonRoot[i].integralTime, "utf-8")+"</td><td>"
						   + decodeURI(jsonRoot[i].integralQuota, "utf-8")+"</td><td>"
						   + decodeURI(jsonRoot[i].remainIntegral, "utf-8")+"</td><td>"
						   + decodeURI(jsonRoot[i].integralReason, "utf-8")+"</td></tr>";
				}
				$ptable.html(str);
				document.getElementById("page").style.display="";
				$p("#currentPage").text("第"+cp+"页");
				$p("#totalPage").text("共"+tp+"页");
				$p("#firstPage").attr("href","javascript:getJSONData(1,3)");
				$p("#prevPage").attr("href","javascript:getJSONData("+(parseInt(cp)-1)+",3)");
				$p("#nextPage").attr("href","javascript:getJSONData("+(parseInt(cp)+1)+",3)");
				$p("#lastPage").attr("href","javascript:getJSONData("+(parseInt(tp))+",3)");
			});
		}
		$p(function() { 
			getJSONData(1,3);  
		});
</script>
 <script type="text/javascript">
 var $page =  jQuery.noConflict();
 function getJSONDatas() {
 
 		$page("#ID1").hide();
 		$page("#ID2").show();
 
 }
 $page(function() {  
 	getJSONDatas();  
 });
 </script>
<!--左侧折叠菜单-->
<script type="text/javascript" src="<%=basePath%>/js/menu.js"></script>
</head>
<body> 
<!--头部开始-->
<div class="header">
    <jsp:include page="../header.jsp" />
</div>
<!--头部结束-->
<div class="maincontent">
   <div class="conbox fn_cle">
    <jsp:include page="../user/left.jsp"/>
   </div>
   
     <div class="user_r">
         <div id="hotnews_caption">
            <ul id="h_menu">                  
                 <li class="current"onclick="secBoard('hotnews_caption','list',1);"><a href="#" >认证积分</a></li>
                 <li class="normal" onclick="secBoard('hotnews_caption','list',2);"><a href="#" >积分记录</a></li>
                 <li class="normal" onclick="secBoard('hotnews_caption','list',3);"><a href="#" >总积分</a></li>
            </ul>
         </div>
         
         <div id="hotnews_content">
           <div class="current" id="list_1">
				<p class="tishi"><img src="<%=basePath%>/images/xing_03.png" />以下为积分介绍</p>
                <table class="table" border="1" bordercolor="#CCCCCC" width="845px">
                	<tr>
                    	<th>图标</th>
                        <th>级别</th>
                        <th>分数范围</th>
                    </tr>
                    <c:forEach var="creditlevel" items="${creditlevels}">
                    	<tr><td><img src="<%=basePath %>${creditlevel.creditLevelPicture}"/></td>
                    	<td>${creditlevel.creditLevelName}</td>
                    	<td>${creditlevel.creditLevelStart}-${creditlevel.creditLevelEnd}分</td></tr>
                    </c:forEach>
                  
                </table>
                <p class="p"><img src="<%=basePath%>/images/biao_06.png" />您的认证积分是：<span>
                <c:if test="${uservip.itegral==null}">0</c:if>
                <c:if test="${uservip.itegral!=null}">${uservip.itegral}</c:if>
                </span>分。<p>
           </div>
           
           
		<div class="normal" id="list_2">
		    <p><img src="<%=basePath%>/images/xing_03.png" />尊敬的用户，系统为您记录和保存了您的积分历史明细</p>
                <table class="table" border="1" bordercolor="#CCCCCC" width="845px" id="listTable">
                	<tr>
                    	<th width="160px">发生日期</th>
                        <th width="160px">影响积分</th>
                        <th width="160px">剩余积分</th>
                        <th width="120px">说明</th>
                    </tr>
                </table>
                <p class="p"><img src="<%=basePath%>/images/biao_06.png" />您的投资积分是：<span>
                <c:if test="${uservip.investItegral==null}">0</c:if>
                <c:if test="${uservip.investItegral!=null}">${uservip.investItegral}</c:if>
                </span>分。<p>
              <div id="pageroot" style="float:left; width:845px;">
			 <div class="page" style="float:right;display: none" id="page">
			  	<span id="currentPage">第 1 页</span>
			  	<span id="totalPage">共 1 页</span>
			  	<span><a id="firstPage" href="">首页</a></span>
			  	<span><a id="prevPage" href="">上一页</a></span>
			  	<span><a id="nextPage" href="">下一页</a></span>
			  	<span><a id="lastPage" href="">最后一页</a></span>
			 </div>
		   </div>
           </div>
          
           
           <div class="normal" id="list_3">
				<p class="p"><img src="<%=basePath %>/images/xing_03.png" />您的认证积分是：<span>
				<c:if test="${uservip.itegral==null}">0</c:if>
                <c:if test="${uservip.itegral!=null}">${uservip.itegral}</c:if>
				</span>分。</p>
                <p class="p"><img src="<%=basePath %>/images/xing_03.png" />您的投资积分是：<span>
                <c:if test="${uservip.investItegral==null}">0</c:if>
                <c:if test="${uservip.investItegral!=null}">${uservip.investItegral}</c:if>
                </span>分。<p>
                <p class="p"><img src="<%=basePath %>/images/biao_06.png" />您的总积分是：<span>${uservip.itegral+uservip.investItegral}</span>分。<p>
           </div>
        
          <script type="text/javascript" src="<%=basePath %>/js/tabs.js" language="javascript"></script>
        
   </div>
    
    
  </div>
</div> 

</div>

</div>
<!--footer 开始-->
<div class="footer">
  <jsp:include page="../footer.jsp" />
</div>
<input type="hidden" name="userId" id="userId" value=${sessionScope.uservip.userId}></input>
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