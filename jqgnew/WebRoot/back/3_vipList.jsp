<%@page import="com.jqg.service.impl.IPServiceImpl"%>
<%@page import="com.jqg.service.IPService"%>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
  <%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();if(path!=null && !path.equals("")){	if(path.contains("/")){		if(path.equals("/")){path = "";	}}else{	path += "/";}} 
String basePath = "";if(request.getServerPort()==80){basePath = request.getScheme()+"://"+request.getServerName()+path+"/";}else{basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";}    
IPService ipService = new IPServiceImpl();
String ip = ipService.getIpAddr(request);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>会员列表</title>
<link href="<%=path %>/back/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=path %>/back/css/mem.css" rel="stylesheet" type="text/css" />
<link href="<%=path %>/back/css/tabs.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path %>/back/js/jquery.js"></script>

<!--弹窗中的滑动js-->
<script type="text/javascript" src="<%=path %>/back/js/jquery.tabso_yeso.js"></script>
<script type="text/javascript">
function getJSONDatas(currentPage,pageSize) {
	var url = "<%=path %>/overall/findUsersByVip.action?currentPage="+currentPage+"&pageSize="+pageSize+"&ziduan=isApplyVIP"+"&temp=" +Math.random();
	$.getJSON(url,function(data){
		var cp = data.currentPage;
		var tp = data.totalPage;
		var $table = $ ("#table");
		$table.empty();
		str = '<thead><tr><th width="5%"  align="center" >ID</th><th width="6%"  align="center" >用户名</th><th width="6%"  align="center" >真实姓名</th><th width="6%"  align="center" >推荐人</th> <th width="8%"  align="center" >可用金额</th><th width="6%" align="center" >冻结金额</th><th width="6%" align="center" >待收金额</th><th width="13%" align="center" >VIP使用期限</th><th width="20%"  align="center" >操作</th></tr></thead>';
		var jsonRoot = data.jsonRoot;
		for(var i = 0; i < jsonRoot.length; i++) {
			str += '<tbody><tr><td>'+ jsonRoot[i].id+ '</td><td>'
				   + decodeURI(jsonRoot[i].name, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].realname, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].recomname, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].amoney, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].fmoney, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].dmoney, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].time, "utf-8") +"</td><td>"
				   +	  ' <a style="color:red;"href="javascript:void(0)" class="tablelink" onclick = "setUserId('+ jsonRoot[i].id+  ');">审核</a> '  +"</td></tr></tbody>";
		}
		$table.html(str);
	
	    if(tp !=1)
		{		
		document.getElementById("pages").style.display="";
	     $ ("#currentPages").text("第"+cp+"页");
         $ ("#totalPages").text("共"+tp+"页");
         $ ("#tpagesnone").val(tp);
	     $ ("#firstPages").attr("href","javascript:getJSONDatas(1,10)");
	     $ ("#prevPages").attr("href","javascript:getJSONDatas("+(parseInt(cp)-1)+",10)");
	     $ ("#nextPages").attr("href","javascript:getJSONDatas("+(parseInt(cp)+1)+",10)");
	     $ ("#lastPages").attr("href","javascript:getJSONDatas("+(parseInt(tp))+",10)");
		}
		else{
			document.getElementById("pages").style.display="none";
		}
	});
}

function tiaozhuans() {
    var size = $("#sizes").val();
    var totalpage = $("#tpagesnone").val();
  	if(size==null || size==""){
  		alert("请输入要跳转的页数！");
  		return false;
  	}
    if(parseInt(size)<1||parseInt(size)>parseInt(totalpage))
    {
    	alert("请输入正确的页数")
    	return false;
    }else{
    	getJSONDatas(size,10);  
    }
    
}


$(function() {  
	getJSONDatas(1,10);  
});

</script>
</head>
<body>
    <div class="rightinfo">
    <div>
    	<ul class="toolbar">
         <li ><span><img src="<%=path %>/back/images/t05.png" /></span>VIP会员申请</li>
         
        </ul>
     </div>
    <table class="tablelist" id="table">
    	<thead>
    	<tr>
        <th width="5%"  align="center" >ID</th>
        <th width="6%"  align="center" >用户名</th>
        <th width="6%"  align="center" >真实姓名</th>
        <th width="6%"  align="center" >推荐人</th>
        <th width="8%"  align="center" >可用金额</th>
        <th width="6%" align="center" >冻结金额</th>
        <th width="6%" align="center" >待收金额</th>
        <th width="13%" align="center" >VIP使用期限</th>
        <th width="20%"  align="center" >操作</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
<input type="text" id="tpagesnone" style="display: none"/>
       <div class="page" id="pages" style="display: none">
    <em id="currentPages">第页</em>&nbsp; &nbsp; 
  	<em id="totalPages">共页</em>&nbsp; &nbsp; 
  	<em ><a id="firstPages" href="">首页</a></em>&nbsp; &nbsp; 
  	<em ><a id="prevPages" href="">上一页</a></em>&nbsp; &nbsp; 
  	<em ><a id="nextPages" href="">下一页</a></em>&nbsp; &nbsp; 
  	<em ><a id="lastPages" href="">最后一页</a></em>&nbsp; &nbsp; 
  	<em class="skip">转到<input name="page" type="text" id="sizes" size="5" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>页</em><a href="#" id="tiaozhuans" onclick="tiaozhuans()">跳转</a>
    </div>
    </div>
    <!--  弹窗 -->
   <div id="light33" class="white_content">
       <div class="light_title">
          <span>审核结果</span> 
          <a href = "javascript:void(0)" onclick = "document.getElementById('light33').style.display='none';document.getElementById('fade').style.display='none';" class="close"><img src="<%=path %>/back/images/close1.png"/></a>
      </div>
      <div class="light_box">
        <ul class="tabbtn" id="normaltab1">
		   <li class="current">审核结果</li>
		 </ul>
         <!--tabbtn end-->
        <div class="tabcon" id="normalcon1">
         <div class="sublist">
             <form name="vip" id="vip" action="<%=path %>/overall/vipMember" method = "post" >
           <ul>
             <li>
               <label id="info_title">审核结果：</label>
                <p style="float:left; line-height:34px;">
  	                 <label> <input type="radio" name="radio" value="1" id="raidio1" checked="checked"/>通过</label>
                     <label> <input type="radio" name="radio" value="0" id="raidio2" /> 不通过</label>
               </p>
          </li>
          <li><input type="text" name="userId"  id="userId" style="display: none;"/></li>
		  <li><label id="info_title">说明：</label><textarea id="message"name="message" style="width:480px; height:100px; border:1px solid #ccc;"></textarea></li>
		  <li><input name="" type="button" style="margin-left:240px;" class="btn" value="确定" onclick="btn()"/></li>
		 <li>  <textarea name="ip" style="display: none;"><%=ip %></textarea></li>
       </ul>
       </form>
       </div> 
       </div> 
      </div>  
   </div>   
   <div id="fade" class="black_overlay"></div>        
   <!--  弹窗 -->   
   <script type="text/javascript">
   function btn(){
	var message = document.getElementById("message");
	if(message.value==""||message.value==null)
		{
		   alert("说明不能为空");
		   return;
		}
		document.getElementById("vip").submit();
		alert("审核完成！");
		}

function setUserId(userId){
input=  document.getElementById("userId");
    input.value=userId;
	  document.getElementById('light33').style.display='block';
	  document.getElementById('fade').style.display='block';
}
</script>
</body>
</html>
