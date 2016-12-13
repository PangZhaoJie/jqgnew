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
<title>个人中心-额度申请</title>
<link rel="icon" href="<%=path %>/image/favicon/favicon.ico" type="image/x-icon" />
<link href="<%=basePath%>/css/public.css" type="text/css" rel="stylesheet"/>
<link href="<%=basePath%>/css/user/user_public.css" type="text/css" rel="stylesheet"/>
<link href="<%=basePath%>/css/user/user_apply.css" type="text/css" rel="stylesheet"/>
<!--导航下拉菜单 js-->
<script type="text/javascript" src="<%=basePath%>/js/jquery-1.8.2.min.js"></script>
<%--<!--表单下拉列表框-->--%>
<%--<script type="text/javascript" src="<%=basePath%>/js/select2css.js"></script>--%>

<script type="text/javascript" src="<%=basePath%>/js/alert.js"></script>
<script type="text/javascript" src="<%=basePath %>/js/login/apply.js"></script>
<script type="text/javascript">
		var  $p=jQuery.noConflict();
		function getJSONData(currentPage,pageSize) {
			//6位随机数
			var Num=""; 
			for(var i=0;i<6;i++) 
			{ 
			Num+=Math.floor(Math.random()*10); 
			} 
			var url = "<%=path %>/user/applyByIdPage.action?currentPage="+currentPage+"&pageSize="+pageSize+"&Num="+Num;
			$p.getJSON(url,function(data){
				var cp = data.currentPage;
				var tp = data.totalPage;
				var $ptable = $p("#listTable");
				$ptable.empty();
 				str = "<tr><th>提交时间</th><th>申请类型</th><th>申请金额</th><th>审核状态</th><th>处理意见</th><th>授信额度</th></tr>";
				var jsonRoot = data.jsonRoot;
				for(var i = 0; i < jsonRoot.length; i++) {
					var examine=jsonRoot[i].examine;
					var status="待审核";
					if(examine==2){
						status="未通过";
					}else if(examine==1){
						status="通过";
					}
					var opinion=jsonRoot[i].opinion=='null'?"":jsonRoot[i].opinion;
					var throughQuota=jsonRoot[i].throughQuota=='null'?"":jsonRoot[i].throughQuota;
					str += "<tr><td>"+ decodeURI(jsonRoot[i].submitTime, "utf-8")+"</td><td>"
						   + decodeURI(jsonRoot[i].applyType, "utf-8")+"</td><td>"
						   + decodeURI(jsonRoot[i].requestQuota, "utf-8")+"</td><td>"
						   + status+"</td><td>"
						   + opinion+"</td><td>"
						   + throughQuota+"</td></tr>";
				}
				$ptable.html(str);
				$p("#currentPage").text("第"+cp+"页");
				$p("#totalPage").text("共"+tp+"页");
				$p("#firstPage").attr("href","javascript:getJSONData(1,3)");
				$p("#prevPage").attr("href","javascript:getJSONData("+(parseInt(cp)-1)+",3)");
				$p("#nextPage").attr("href","javascript:getJSONData("+(parseInt(cp)+1)+",3)");
				$p("#lastPage").attr("href","javascript:getJSONData("+(parseInt(tp))+",3)");
			});
		}
		$p(function() {
			$p("#ID1").hide();
			$p("#ID4").show();
			getJSONData(1,3);  
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
  	<input id="path" type="hidden" value="<%=basePath%>">
  <div class="conbox fn_cle">
    <jsp:include page="../user/left.jsp"/>
   </div>
   
     <div class="user_r">
         <div id="hotnews_caption">
            <ul id="h_menu">                  
                 <li class="current"onclick="secBoard('hotnews_caption','list',1);"><a href="#" >额度申请</a></li>
            </ul>
         </div>
         
         <div id="hotnews_content">
           <div class="current" id="list_1">
           		<form method="post" id="apply">
					<div class="app">申请类型：<input type="radio" checked="checked"/>借款信用额度</div>
					<div class="app">申请金额：<input type="text" name="requestQuota" id="requestQuota"/>&nbsp;元<span id="applyTip"></span></div>
					<div class="app"><div class="shuoming">申请说明：</div><textarea name="applyExplain" id="applyExplain"></textarea></div>
	                <div id="button">
	                	<input type="button"  class="submit_bt" value="申请审核" onclick="apply();"/>
	                </div>
        		</form>
				<div>详细信息：</div>
                <table class="table" border="1" bordercolor="#CCCCCC" width="850px" id="listTable">
                	<tr>
                    	<th>提交时间</th>
                        <th>申请类型</th>
                        <th>申请金额</th>
                        <th>审核状态</th>
                        <th>处理意见</th>
                        <th>授信额度</th>
                    </tr>
                </table>
              <div class="page" style="float:right;">
			  	<span id="currentPage">第1页</span>
			  	<span id="totalPage">共1页</span>
			  	<span><a id="firstPage" href="">首页</a></span>
			  	<span><a id="prevPage" href="">上一页</a></span>
			  	<span><a id="nextPage" href="">下一页</a></span>
			  	<span><a id="lastPage" href="">最后一页</a></span>
			 </div>
           </div>
          <script type="text/javascript" src="<%=basePath%>/js/tabs.js" language="javascript"></script>
        
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
<script type="text/javascript" src="<%=basePath%>/js/service.js">	</script> --%>
<!--右边漂浮 结束-->

</body>
</html>