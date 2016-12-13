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
<title>个人中心-推广奖励</title>
<link rel="icon" href="<%=path %>/image/favicon/favicon.ico" type="image/x-icon" />
<link href="<%=basePath%>/css/public.css" type="text/css" rel="stylesheet"/>
<link href="<%=basePath%>/css/user/user_public.css" type="text/css" rel="stylesheet"/>
<link href="<%=basePath%>/css/user/user_haoyou.css" type="text/css" rel="stylesheet"/>
<!--导航下拉菜单 js-->
<script type="text/javascript" src="<%=basePath%>/js/jquery-1.8.2.min.js"></script>
<!--表单下拉列表框-->
<%--<script type="text/javascript" src="<%=basePath%>/js/select2css.js"></script>--%>
<script type="text/javascript" src="<%=basePath%>/back/js/jquery.js"></script>
<!--左侧折叠菜单-->
<script type="text/javascript">
		function getJSONData(currentPage,pageSize) {
			var d = new Date();
            var str = d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate()+" "+d.getHours()+":"+d.getMinutes()+":"+d.getSeconds();
			
            $('#time').text(str);
			
			//6位随机数
			var Num=""; 
			for(var i=0;i<6;i++) 
			{ 
			Num+=Math.floor(Math.random()*10); 
			} 
			var url = "<%=path %>/user/userByIdPage.action?currentPage="+currentPage+"&pageSize="+pageSize+"&Num="+Num;
			$.getJSON(url,function(data){
				var cp = data.currentPage;
				var tp = data.totalPage;
 				str = "<tr><td width='400px'>会员名单</td><td width='400px'>注册时间</td></tr>";
				var jsonRoot = data.jsonRoots;
				for(var i = 0; i < jsonRoot.length; i++) {
					str += "<tr><td>"+ jsonRoot[i].userName+"</td><td>"
						+ jsonRoot[i].time+"</td></tr>";
				}
				$("#listTable").empty();
				$("#listTable").html(str);
				$("#currentPage").text("第"+cp+"页");
				$("#totalPage").text("共"+tp+"页");
				$("#firstPage").attr("href","javascript:getJSONData(1,5)");
				$("#prevPage").attr("href","javascript:getJSONData("+(parseInt(cp)-1)+",5)");
				$("#nextPage").attr("href","javascript:getJSONData("+(parseInt(cp)+1)+",5)");
				$("#lastPage").attr("href","javascript:getJSONData("+(parseInt(tp))+",5)");
			});
		}
		function getJSONData1(currentPage,pageSize) {
			//6位随机数
			var Num=""; 
			for(var i=0;i<6;i++) 
			{ 
			Num+=Math.floor(Math.random()*10); 
			} 
			var d = new Date();
            var str = d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate()+" "+d.getHours()+":"+d.getMinutes()+":"+d.getSeconds();
			$('#time').text(str);
			var url = "<%=path %>/user/rewardByIdPage.action?currentPage="+currentPage+"&pageSize="+pageSize+"&Num="+Num;
			$.getJSON(url,function(data){
				var cp = data.current;
				var tp = data.total;
				var totalMoney = data.totalMoney;
				var $table = $("#listTable1");
				$table.empty();
 				str = "<tr><td width='120px'>编号</td><td width='120px'>投资标号</td><td width='150px'>投资人</td><td width='120px'>投资金额</td><td width='120px'>投资时间</td></tr>";
				var jsonRoot = data.jsonRoots;
				for(var i = 0; i < jsonRoot.length; i++) {
					str += "<tr><td>"+ jsonRoot[i].tenderId+"</td><td>"
					    + jsonRoot[i].lssuingNum+"</td><td>"
					    + jsonRoot[i].username+"</td><td>"
					    + jsonRoot[i].money+"</td><td>"
						+ jsonRoot[i].time+"</td></tr>";
				}
				$table.html(str);
				$("#current").text("第"+cp+"页");
				$("#total").text("共"+tp+"页");
				$("#first").attr("href","javascript:getJSONData1(1,5)");
				$("#prev").attr("href","javascript:getJSONData1("+(parseInt(cp)-1)+",5)");
				$("#next").attr("href","javascript:getJSONData1("+(parseInt(cp)+1)+",5)");
				$("#last").attr("href","javascript:getJSONData1("+(parseInt(tp))+",5)");
			});
		}
 </script>
<script type="text/javascript">
 var $page =  jQuery.noConflict();
 function getJSONDatas() {
 		$page("#ID1").hide();
 		$page("#ID5").show();
 }
 $page(function() {  
 	getJSONDatas();  
 });
 </script>
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
                 <li class="current"onclick="secBoard('hotnews_caption','list',1);"><a href="#" >推广明细</a></li>
                 <li class="normal" onclick="secBoard('hotnews_caption','list',2);"><a href="#" >好友邀请</a></li>
                 <li class="normal" onclick="secBoard('hotnews_caption','list',3);"><a href="#" >推荐投资记录</a></li>
            </ul>
         </div>
         
         <div id="hotnews_content">
           <div class="current" id="list_1">
				<p class="tishi"><img src="<%=basePath%>/images/xing_03.png" />尊敬的会员，您可以通过邀请好友注册获得奖金。</p>
               <label>您的邀请链接：</label>
               <input type="text" size="58" id="fe_text" value="<%=basePath%>login/reg1.jsp?long=${sessionScope.uservip.userId}"/>
               <button id="d_clip_button" class="submit_bt" data-clipboard-target="fe_text">复制链接</button>
				
           </div>
           
           
		<div class="normal" id="list_2">
			
           <p class="tishi"><img src="<%=basePath%>/images/xing_03.png" />截止<span id="time"></span>	您成功邀请的会员有：</p>
                <table class="table" border="1" bordercolor="#CCCCCC" width="100%" id="listTable">
                	<tr>
                    	<td width="400px">会员名单</td>
                        <td width="400px">注册时间</td>
                    </tr>
                </table>
                 <div class="page" style="float:right;">
			  	<span id="currentPage" style="color:#555">第 1 页</span>
			  	<span id="totalPage" style="color:#555">共 1 页</span>
			  	<span><a id="firstPage" href="">首页</a></span>
			  	<span><a id="prevPage" href="">上一页</a></span>
			  	<span><a id="nextPage" href="">下一页</a></span>
			  	<span><a id="lastPage" href="">最后一页</a></span>
			    </div>
        </div>
           
           
       <div class="normal" id="list_3">
                <table class="table" border="1" bordercolor="#CCCCCC" width="100%" id="listTable1">
                	<tr>
                    	<td width="120px">编号</td>                    	
                    	<td width="120px">投资标号</td>
                    	<td width="120px">投资人</td>
                    	<td width="120px">投资金额</td>
                    	<td width="150px">投资日期</td>
                    </tr>
                </table>
                <div class="page" style="float:right;">
			  	<span id="current" style="color:#555">第 1 页</span>
			  	<span id="total" style="color:#555">共 1 页</span>
			  	<span><a id="first" href="">首页</a></span>
			  	<span><a id="prev" href="">上一页</a></span>
			  	<span><a id="next" href="">下一页</a></span>
			  	<span><a id="last" href="">最后一页</a></span>
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
<input type="hidden" name="userId" id="userId" value=${sessionScope.uservip.userId }></input>
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
<script type="text/javascript" src="<%=basePath%>/js/ZeroClipboard.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/alert.js"></script>
<script type="text/javascript">
 getJSONData(1,5);getJSONData1(1,5);
 </script>
<script type="text/javascript">
	
//  定义一个新的复制对象
var clip = new ZeroClipboard( document.getElementById("d_clip_button"), {
  moviePath: "<%=basePath%>/user/flash/ZeroClipboard.swf"
} );


// 复制内容到剪贴板成功后的操作
clip.on( 'complete', function(client, args) {
    Alert("恭喜你，复制成功!");
} );

</script>
</html>
