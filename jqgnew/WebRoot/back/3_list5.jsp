<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
  <%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();if(path!=null && !path.equals("")){	if(path.contains("/")){		if(path.equals("/")){path = "";	}}else{	path += "/";}} 
String basePath = "";if(request.getServerPort()==80){basePath = request.getScheme()+"://"+request.getServerName()+path+"/";}else{basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";}    
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
  
  
	var url = "<%=path %>/overall/findquota?currentPage="+currentPage+"&pageSize="+pageSize+"&ziduan=sceneResult"+"&temp=" +Math.random();


	$.getJSON(url,function(data){
	
		var cp = data.currentPage;
		var tp = data.totalPage;
		var $table = $ ("#table");
		$table.empty();
		str = '<thead><tr><th width="5%"  align="center" >ID</th><th width="6%"  align="center" >用户名</th><th width="6%"  align="center" >邮箱认证</th><th width="6%"  align="center" >实名认证</th> <th width="8%"  align="center" >手机认证</th><th width="6%" align="center" >视频认证</th><th width="6%" align="center" >现场认证</th> <th width="13%" align="center" >注册时间</th><th width="20%"  align="center" >操作</th></tr></thead>';
		var jsonRoot = data.jsonRoot;
		for(var i = 0; i < jsonRoot.length; i++) {
			str += '<tbody><tr><td>'+ jsonRoot[i].id+ '</td><td>'
	
				   + decodeURI(jsonRoot[i].name, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].enable, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].nameresult, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].phoneresult, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].vodepresult, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].sceneresult, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].time, "utf-8") +"</td><td>"
					+	  ' <c:if test="${sessionScope.powerss[36]==1}"><a style="color:red;"href="<%=path%>/back/3_tab.jsp?id='+jsonRoot[i].id+  '">审核</a></c:if><c:if test="${sessionScope.powerss[36]!=1}"> --  </c:if> '  +"</td></tr>  </tbody>";

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

	<%-- <div class="place">
    <span>位置：</span>
    <ul class="placeul">
<li><a href="javascript:void(0);" onclick="parent.frames.topFrame.overall();">首页</a></li>
    <li><a href="3_list.jsp" target="rightFrame">会员列表</a></li>
    </ul>
    </div> --%>
    
    <div class="rightinfo">
   
	
    <div>
    	<ul class="toolbar">
         <li ><span><img src="<%=path %>/back/images/t05.png" /></span>信用额度申请</li>
         
        </ul>
     </div>
    
    
    <table class="tablelist" id="table">
    	<thead>
    	<tr>

        <th width="5%"  align="center" >ID</th>

        <th width="6%"  align="center" >用户名</th>
      <th width="6%"  align="center" >邮箱认证</th>
      <th width="6%"  align="center" >实名认证</th> 
      <th width="8%"  align="center" >手机认证</th>
      <th width="6%" align="center" >视频认证</th>
      <th width="6%" align="center" >现场认证</th> 
        <th width="13%" align="center" >注册时间</th>
        <th width="20%"  align="center" >操作</th>
        
        </tr>
        </thead>
        <tbody>
      
        
      
             
        </tbody>
    </table>
    <input type="text" id="tpagenone" style="display: none"/>
<input type="text" id="tpagesnone" style="display: none"/>
    
       <div class="page" id="pages" style="display: none">
    <em id="currentPages">第页</em>&nbsp; &nbsp; 
  	<em id="totalPages">共页</em>&nbsp; &nbsp; 
  	<em ><a id="firstPages" href="">首页</a></em>&nbsp; &nbsp; 
  	<em ><a id="prevPages" href="">上一页</a></em>&nbsp; &nbsp; 
  	<em ><a id="nextPages" href="">下一页</a></em>&nbsp; &nbsp; 
  	<em ><a id="lastPages" href="">最后一页</a></em>&nbsp; &nbsp; 
  	<em class="skip">转到<input name="page" type="text" id="sizes" size="5" />页</em><a href="#" id="tiaozhuans" onclick="tiaozhuans()">跳转</a>
    </div>
 


    </div>
   
 
</body>

</html>

