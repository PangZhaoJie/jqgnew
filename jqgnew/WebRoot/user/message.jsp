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
<link rel="icon" href="<%=path %>/image/favicon/favicon.ico" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人中心-会员留言</title>
<link href="<%=basePath%>/css/public.css" type="text/css" rel="stylesheet"/>
<link href="<%=basePath%>/css/user/user_public.css" type="text/css" rel="stylesheet"/>
<link href="<%=basePath%>/css/user/user_message.css" type="text/css" rel="stylesheet"/>
<!--导航下拉菜单 js-->
<script type="text/javascript" src="<%=basePath%>/js/jquery-1.8.2.min.js"></script>
<!--表单下拉列表框--> 
<%-- <script type="text/javascript" src="<%=basePath%>/js/select2css.js"></script> --%>
<script type="text/javascript">
		var  $p=jQuery.noConflict();
		function getJSONData(currentPage,pageSize) {
			//6位随机数
			var Num=""; 
			for(var i=0;i<6;i++) 
			{ 
			Num+=Math.floor(Math.random()*10); 
			} 
			var url = "<%=path %>/user/lssuingByIdPage.action?currentPage="+currentPage+"&pageSize="+pageSize+"&Num="+Num;
			$p.getJSON(url,function(data){
				var cp = data.currentPage;
				var tp = data.totalPage;
				var $ptable = $p("#listTable");
				$ptable.empty();
 				str = "<tr><td width='100px' height='40px'>标题</td><td width='100px' height='40px'>借款时间</td></tr>";
				var jsonRoot = data.jsonRoots;
				for(var i = 0; i < jsonRoot.length; i++) {
					str += "<tr><td width='100px' height='40px'><a class='tablelink' href='<%=path %>/tender/totender.action?lssuingId="+jsonRoot[i].lssuingId+"'>"+ jsonRoot[i].title+"</a></td><td width='100px' height='40px'>"
						   + jsonRoot[i].borrowTime+"</td></tr>";
				}
				$ptable.html(str);
				$p("#currentPage").text("第"+cp+"页");
				$p("#totalPage").text("共"+tp+"页");
				$p("#firstPage").attr("href","javascript:getJSONData(1,5)");
				$p("#prevPage").attr("href","javascript:getJSONData("+(parseInt(cp)-1)+",5)");
				$p("#nextPage").attr("href","javascript:getJSONData("+(parseInt(cp)+1)+",5)");
				$p("#lastPage").attr("href","javascript:getJSONData("+(parseInt(tp))+",5)");
			});
		}
		
		function getJSONData1(currentPage,pageSize) {
			//6位随机数
			var Num=""; 
			for(var i=0;i<6;i++) 
			{ 
			Num+=Math.floor(Math.random()*10); 
			} 
			var url = "<%=path %>/user/myMessageByIdPage.action?current="+currentPage+"&pageSize="+pageSize+"&Num="+Num;
			$p.getJSON(url,function(data){
				var cp = data.current;
				var tp = data.total;
				var $ptable = $p("#listTable1");
				$ptable.empty();
 				str = "<tr><td width='100px' height='40px'>编号</td><td width='100px' height='40px'>标题</td><td width='100px' height='40px'>留言时间</td></tr>";
				var jsonRoot = data.jsonRoots;
				for(var i = 0; i < jsonRoot.length; i++) {
					$p("#content"+(i+1)).text(jsonRoot[i].message);
					str += "<tr><td width='100px' height='40px'>"+ jsonRoot[i].tenderWordsId+"</td><td width='100px' height='40px'><a href='javascript:show("+i+");'>"
					    + jsonRoot[i].title+"</td></a><td width='100px' height='40px'>"
						+ jsonRoot[i].time+"</td></tr>";
				}
				$ptable.html(str);
				$p("#current").text("第"+cp+"页");
				$p("#total").text("共"+tp+"页");
				$p("#first").attr("href","javascript:getJSONData1(1,5)");
				$p("#prev").attr("href","javascript:getJSONData1("+(parseInt(cp)-1)+",5)");
				$p("#next").attr("href","javascript:getJSONData1("+(parseInt(cp)+1)+",5)");
				$p("#last").attr("href","javascript:getJSONData1("+(parseInt(tp))+",5)");
			});
		}
		$p(function() {  
			getJSONData(1,5);
			getJSONData1(1,5);
		});
		function show(i,inboxId){
			document.getElementById('light'+(i+1)).style.display='block';
			document.getElementById('fade').style.display='block';
		}
		window.onload = function(e) {
			$p("#ID1").hide();
			$p("#ID5").show();
		}
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
    <jsp:include page="left.jsp"/>
   </div>
   
     <div class="user_r">
         <div id="hotnews_caption">
            <ul id="h_menu">                  
                 <li class="current" onclick="secBoard('hotnews_caption','list',1);">会员留言</li>
                 <li class="normal" onclick="secBoard('hotnews_caption','list',2);">我发出的留言</li>
                 
                 
            </ul>
         </div>
         
         <div id="hotnews_content">
            <div class="current" id="list_1">
               <table cellspacing="0" cellpadding="0" width="100%" border="0" style="margin-top:20px;" class="letter_table" id="listTable">
                                <tr>
                                    <th width="100">标题</th>
                                    <th width="100">借款时间</th>	
                                </tr>
<%--                                 <c:forEach var="lssuing" items="${lssuings}"> --%>
<!--                                 <tr> -->
<%--                                 <td>${lssuing.title}</td> --%>
<%--                                 <td>${lssuing.borrowTime}</td></tr> --%>
<%--                                 </c:forEach>   --%>
                </table>
                 <div class="page" style="float:right;">
			  	<span id="currentPage">第 1 页</span>
			  	<span id="totalPage">共 1 页</span>
			  	<span><a id="firstPage" href="">首页</a></span>
			  	<span><a id="prevPage" href="">上一页</a></span>
			  	<span><a id="nextPage" href="">下一页</a></span>
			  	<span><a id="lastPage" href="">最后一页</a></span>
			    </div>
           </div>
         
           <div class="normal" id="list_2">
               <table cellspacing="0" cellpadding="0" width="100%" border="0" style="margin-top:20px;" class="letter_table" id="listTable1">
                                <tr>
                                    <td width="100px" height="40px">编号</td>
                                    <td width="100px" height="40px">标题</td>
                                    <td width="100px" height="40px">留言时间</td>
                                </tr>
<%--                                 <c:forEach var="lssuing" items="${lssuingss}"> --%>
<!--                                 <tr> -->
<%--                                 <td>${lssuing.title}</td> --%>
<%--                                 <td>${lssuing.borrowTime}</td></tr> --%>
<%--                                 </c:forEach>  --%>
                         
                </table>
            <div id="light1" class="white_content">
            <div class="light_title"><span>查看留言</span> <a href = "javascript:void(0)" onclick = "document.getElementById('light1').style.display='none';document.getElementById('fade').style.display='none'" class="close"><img src="<%=basePath%>/images/close.png" /></a></div>
            <div class="letter_info">
             <div id="content1"></div>
            </div>
           </div> 
           
           <div id="light2" class="white_content">
            <div class="light_title"><span>查看信息</span> <a href = "javascript:void(0)" onclick = "document.getElementById('light2').style.display='none';document.getElementById('fade').style.display='none'" class="close"><img src="<%=basePath%>/images/close.png" /></a></div>
            <div class="letter_info">
             <div id="content2"></div>
            </div>
            
           </div>
           <div id="light3" class="white_content">
            <div class="light_title"><span>查看信息</span> <a href = "javascript:void(0)" onclick = "document.getElementById('light3').style.display='none';document.getElementById('fade').style.display='none'" class="close"><img src="<%=basePath%>/images/close.png" /></a></div>
            <div class="letter_info">
             <div id="content3"></div>
            </div>
            
           </div>
           <div id="light4" class="white_content">
            <div class="light_title"><span>查看信息</span> <a href = "javascript:void(0)" onclick = "document.getElementById('light4').style.display='none';document.getElementById('fade').style.display='none'" class="close"><img src="<%=basePath%>/images/close.png" /></a></div>
            <div class="letter_info">
             <div id="content4"></div>
            </div>
            
           </div>
           <div id="light5" class="white_content">
            <div class="light_title"><span>查看信息</span> <a href = "javascript:void(0)" onclick = "document.getElementById('light5').style.display='none';document.getElementById('fade').style.display='none'" class="close"><img src="<%=basePath%>/images/close.png" /></a></div>
            <div class="letter_info">
             <div id="content5"></div>
            </div>
            
           </div>
                <div class="page" style="float:right;">
			  	<span id="current">第 1 页</span>
			  	<span id="total">共 1 页</span>
			  	<span><a id="first" href="">首页</a></span>
			  	<span><a id="prev" href="">上一页</a></span>
			  	<span><a id="next" href="">下一页</a></span>
			  	<span><a id="last" href="">最后一页</a></span>
			    </div>
<!-- 			<div id="light1" class="white_content"> -->
<%--             <div class="light_title"><span>查看信息</span> <a href = "javascript:void(0)" onclick = "document.getElementById('light1').style.display='none';document.getElementById('fade').style.display='none'" class="close"><img src="<%=basePath%>/images/close.png" /></a></div> --%>
<!--             <div class="letter_info"> -->
<!--              <div id="content1"></div> -->
<!--             </div> -->
            
<!--            </div>  -->
           
<!--            <div id="light2" class="white_content"> -->
<%--             <div class="light_title"><span>查看信息</span> <a href = "javascript:void(0)" onclick = "document.getElementById('light2').style.display='none';document.getElementById('fade').style.display='none'" class="close"><img src="<%=basePath%>/images/close.png" /></a></div> --%>
<!--             <div class="letter_info"> -->
<!--              <div id="content2"></div> -->
<!--             </div> -->
            
<!--            </div> -->
           
<!--            <div id="light3" class="white_content"> -->
<%--             <div class="light_title"><span>查看信息</span> <a href = "javascript:void(0)" onclick = "document.getElementById('light3').style.display='none';document.getElementById('fade').style.display='none'" class="close"><img src="<%=basePath%>/images/close.png" /></a></div> --%>
<!--             <div class="letter_info"> -->
<!--              <div id="content3"></div> -->
<!--             </div> -->
<!--            <div id="light4" class="white_content"> -->
<%--             <div class="light_title"><span>查看信息</span> <a href = "javascript:void(0)" onclick = "document.getElementById('light4').style.display='none';document.getElementById('fade').style.display='none'" class="close"><img src="<%=basePath%>/images/close.png" /></a></div> --%>
<!--             <div class="letter_info"> -->
<!--              <div id="content4"></div> -->
<!--             </div> -->
<!--            <div id="light5" class="white_content"> -->
<%--             <div class="light_title"><span>查看信息</span> <a href = "javascript:void(0)" onclick = "document.getElementById('light5').style.display='none';document.getElementById('fade').style.display='none'" class="close"><img src="<%=basePath%>/images/close.png" /></a></div> --%>
<!--             <div class="letter_info"> -->
<!--              <div id="content5"></div> -->
<!--             </div> -->
                
<!--            </div> -->
               
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