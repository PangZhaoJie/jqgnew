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
<title>个人中心-站内信</title>
<link href="<%=basePath%>/css/public.css" type="text/css" rel="stylesheet"/>
<link href="<%=basePath%>/css/user/user_public.css" type="text/css" rel="stylesheet"/>
<link href="<%=basePath%>/css/user/user_password.css" type="text/css" rel="stylesheet"/>
<!--导航下拉菜单 js-->
<script type="text/javascript" src="<%=basePath%>/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/alert.js"></script>
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
			var url = "<%=path %>/user/inboxByIdPage.action?currentPage="+currentPage+"&pageSize="+pageSize+"&Num="+Num;
			$p.getJSON(url,function(data){
				var cp = data.currentPage;
				var tp = data.totalPage;
				var total=data.total;
// 				var read=data.read;
// 				var unread=data.unread;
				var $ptable = $p("#listTable");
				$ptable.empty();
 				str = "<tr><td width='80'><input type='checkbox' onclick='selectAll()' name='controlAll' style='controlAll' id='controlAll'>全选</td><td width='120'>标记</td><td width='160'>发件人</td><td width='300'>标题</td><td width='185'>发送时间</td></tr>";
				var jsonRoot = data.jsonRoot;
				for(var i = 0; i < jsonRoot.length; i++) {
					$p("#content"+(i+1)).text(jsonRoot[i].content);
					var inboxId=jsonRoot[i].inboxId;
					str += "<tr><td><input type='checkbox' name='selected' value='"+inboxId+"'></td><td><div id='"+inboxId+"'>"
					       + decodeURI(jsonRoot[i].status, "utf-8")+ "</div></td><td>"
						   + decodeURI(jsonRoot[i].sendName, "utf-8") +"</td><td><a href='javascript:show("+i+","+inboxId+");'>"
						   + decodeURI(jsonRoot[i].title, "utf-8") + "</a></td><td>"
						   + decodeURI(jsonRoot[i].receiveTime, "utf-8") + "</td><tr>";
				}
				$ptable.html(str);
				$p("#total").text(total);
				$p("#currentPage").text("第"+cp+"页");
				$p("#totalPage").text("共"+tp+"页");
				$p("#firstPage").attr("href","javascript:getJSONData(1,10)");
				$p("#prevPage").attr("href","javascript:getJSONData("+(parseInt(cp)-1)+",10)");
				$p("#nextPage").attr("href","javascript:getJSONData("+(parseInt(cp)+1)+",10)");
				$p("#lastPage").attr("href","javascript:getJSONData("+(parseInt(tp))+",10)");
			});
		}
		$p(function() {  
			getJSONData(1,10);  
		});
	</script>
	<script type="text/javascript">
	function show(i,inboxId){
		var total=$p("#total").text();
		  var flag1=true;
    	  $p.ajax({
    		  async:false,
    		  url:"<%=path %>/user/changeStatus",
    		  type:"GET",
    		  data:"inboxId="+inboxId+"&total="+total,
    		  dataType:"json",
    		  success:function(data){
    		     var member=eval("("+data+")");
    		     var result=member.result;
    		     if(result=="0"){
    		    	 flag1 = false;
    		  	 }
    		  }
    	  });
          if(!flag1){
        	  Alert("ajax请求失败");
          }
        
		document.getElementById('light'+(i+1)).style.display='block';
		document.getElementById('fade').style.display='block';
		$p("#"+inboxId).text("已读");
	}
	function selectAll(){
		 var checklist = document.getElementsByName ("selected");
		 if(document.getElementById("controlAll").checked){
		   for(var i=0;i<checklist.length;i++)
		   {
		      checklist[i].checked = 1;
		   } 
		 }else{
		  for(var j=0;j<checklist.length;j++)
		  {
		     checklist[j].checked = 0;
		  }
		}
	}
	function delet(){
		var str="";
		var chcs=document.getElementsByName("selected");
		for(var i=0;i<chcs.length;i++){
			if(chcs[i].checked){
				str+=chcs[i].value+",";
			}
		}
		if(str==""){
			Alert("请至少选择一个数据删除");
			return false;
		}
		var form=document.getElementById("delForm");
		form.action="<%=path %>/user/delInbox?ids="+str;
	    form.submit();
	}
	window.onload = function(e) {
		$p("#ID1").show();
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
                 <li class="current" onclick="secBoard('hotnews_caption','list',1);"><a href="#" >系统消息</a></li>
            </ul>
         </div>
         
         <div id="hotnews_content">
            <div class="current" id="list_1">
            <form method="post" id="delForm">
            <div class="count">你共收到<a href="#" id="total"></a>封信息，请你及时查看!</div>
             <table cellspacing="0" cellpadding="0" width="100%" border="0" style="margin-top:20px;" class="letter_table" id="listTable">
<!--                                  <tbody>  -->
                                <tr>
                                    <th width="80"><input type="checkbox">全选</th>
                                    <th width="120">标记</th>
                                    <th width="160">发件人</th>
                                    <th width="300">标题</th>
                                    <th width="185">发送时间</th>	
                                </tr>
<!--                                 <tr> -->
<!--                                     <td><input type="checkbox"></td> -->
<!--                                     <td>未读</td> -->
<!--                                     <td>admin</td> -->
<!--                                     <td><a href = "javascript:void(0)" onclick = "document.getElementById('light1').style.display='block';document.getElementById('fade').style.display='block'">删除自动投标</a></td> -->
<!--                                     <td>2012-07-16 15:48</td> -->
<!--                                 </tr> -->
<!--                                 <tr> -->
<!--                                     <td><input type="checkbox"></td> -->
<!--                                     <td>未读</td> -->
<!--                                     <td>admin</td> -->
<!--                                     <td><a href = "javascript:void(0)" onclick = "document.getElementById('light2').style.display='block';document.getElementById('fade').style.display='block'">删除自动投标</a></td> -->
<!--                                     <td>2012-07-16 15:48</td> -->
<!--                                 </tr> -->
<!--                                 <tr> -->
<!--                                     <td><input type="checkbox"></td> -->
<!--                                      <td class="red">已读</td> -->
<!--                                     <td>admin</td> -->
<!--                                     <td><a href = "javascript:void(0)" onclick = "document.getElementById('light3').style.display='block';document.getElementById('fade').style.display='block'">编辑自动投标</a></td> -->
<!--                                     <td>2012-07-16 15:48</td> -->
<!--                                 </tr> -->
<!--                                  <tr> -->
<!--                                     <td><input type="checkbox"></td> -->
<!--                                      <td class="red">已读</td> -->
<!--                                     <td>admin</td> -->
<!--                                     <td><a href = "javascript:void(0)" onclick = "document.getElementById('light4').style.display='block';document.getElementById('fade').style.display='block'">添加自动投标</a></td> -->
<!--                                     <td>2012-07-16 15:48</td> -->
<!--                                 </tr> -->
                                
<!--                                 <tr> -->
<!--                                     <td><input type="checkbox"></td> -->
<!--                                      <td class="red">已读</td> -->
<!--                                     <td>admin</td> -->
<!--                                     <td><a href = "javascript:void(0)" onclick = "document.getElementById('light5').style.display='block';document.getElementById('fade').style.display='block'">添加自动投标</a></td> -->
<!--                                     <td>2012-07-16 15:48</td> -->
<!--                                 </tr> -->
<!--                                  <tr> -->
<!--                                     <td><input type="checkbox"></td> -->
<!--                                     <td class="red">已读</td> -->
<!--                                     <td>admin</td> -->
<!--                                     <td><a href = "javascript:void(0)" onclick = "document.getElementById('light6').style.display='block';document.getElementById('fade').style.display='block'">您的VIP申请审核已通过</a></td> -->
<!--                                     <td>2012-07-16 15:48</td> -->
<!--                                 </tr> -->
<!--                                  <tr> -->
<!--                                     <td><input type="checkbox"></td> -->
<!--                                     <td>未读</td> -->
<!--                                     <td>admin</td> -->
<!--                                     <td><a href = "javascript:void(0)" onclick = "document.getElementById('light7').style.display='block';document.getElementById('fade').style.display='block'">恭喜您注册成功</a></td> -->
<!--                                     <td>2012-07-16 15:48</td> -->
<!--                                 </tr> -->
                                 
<!--                                  <tr> -->
<!--                                     <th colspan="5"> -->
<%--                       <div class="del"><a href="#">删除</a> </div> <div class="page"><span>6</span>条/页<a href="#">上一页</a><a href="#">下一页</a> <span class="skip">转到<input name="page" type="text" value="" size="3" style="height:26px; line-height:26px;" />页</span><a href="#">共5页</a> --%>
<!--           </div></th>	 -->
<!--                                 </tr> -->
<!--                             </tbody> -->
				</table>
           </div>
           <div id="pageroot" style="float:left; width:845px;">
           		<span style="float:left; padding: 0px 10px;"><input type="button" onclick="delet()" value="删除" style=" padding:0px 10px;"></span>
			 <div class="page" style="float:right;">
			  	<span id="currentPage">第1页</span>
			  	<span id="totalPage">共1页</span>
			  	<span><a id="firstPage" href="">首页</a></span>
			  	<span><a id="prevPage" href="">上一页</a></span>
			  	<span><a id="nextPage" href="">下一页</a></span>
			  	<span><a id="lastPage" href="">最后一页</a></span>
			 </div>
		   </div>
		   </form>
           <div id="light1" class="white_content">
            <div class="light_title"><span>查看信息</span> <a href = "javascript:void(0)" onclick = "document.getElementById('light1').style.display='none';document.getElementById('fade').style.display='none'" class="close"><img src="<%=basePath%>/images/close.png" /></a></div>
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
            <div id="light6" class="white_content">
            <div class="light_title"><span>查看信息</span> <a href = "javascript:void(0)" onclick = "document.getElementById('light6').style.display='none';document.getElementById('fade').style.display='none'" class="close"><img src="<%=basePath%>/images/close.png" /></a></div>
            <div class="letter_info">
             <div id="content6"></div>
            </div>
            
           </div>
            <div id="light7" class="white_content">
            <div class="light_title"><span>查看信息</span> <a href = "javascript:void(0)" onclick = "document.getElementById('light7').style.display='none';document.getElementById('fade').style.display='none'" class="close"><img src="<%=basePath%>/images/close.png" /></a></div>
            <div class="letter_info">
             <div id="content7"></div>
            </div>
            
           </div>
            <div id="light8" class="white_content">
            <div class="light_title"><span>查看信息</span> <a href = "javascript:void(0)" onclick = "document.getElementById('light8').style.display='none';document.getElementById('fade').style.display='none'" class="close"><img src="<%=basePath%>/images/close.png" /></a></div>
            <div class="letter_info">
             <div id="content8"></div>
            </div>
            
           </div>
            <div id="light9" class="white_content">
            <div class="light_title"><span>查看信息</span> <a href = "javascript:void(0)" onclick = "document.getElementById('light9').style.display='none';document.getElementById('fade').style.display='none'" class="close"><img src="<%=basePath%>/images/close.png" /></a></div>
            <div class="letter_info">
             <div id="content9"></div>
            </div>
            
           </div>
            <div id="light10" class="white_content">
            <div class="light_title"><span>查看信息</span> <a href = "javascript:void(0)" onclick = "document.getElementById('light10').style.display='none';document.getElementById('fade').style.display='none'" class="close"><img src="<%=basePath%>/images/close.png" /></a></div>
            <div class="letter_info">
             <div id="content10"></div>
            </div>
            
           </div>
           <div id="fade" class="black_overlay"></div> 
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