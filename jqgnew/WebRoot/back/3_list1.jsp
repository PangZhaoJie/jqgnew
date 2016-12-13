<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
  <%@ taglib uri="/struts-tags" prefix="s"%>
  	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

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
function getJSONDatas(currentPage,pageSize,ziduan) {
    var a = $("#demo2").val();
    if(a=="-请选择时间-"){a="";}else{a= a}
    var b = $("#demo1").val();
    if(b=="-请选择时间-"){b="";}else{b= b}
  
	var url = "<%=path %>/overall/findUsersByemail.action?currentPage="+currentPage+"&pageSize="+pageSize+"&username="+
	encodeURI(encodeURI($("#link_txt").val()))+"&starttime="+b+"&endtime="+a+"&ziduan="+ziduan+"&temp=" +Math.random();


	$.getJSON(url,function(data){
	
		var cp = data.currentPage;
		var tp = data.totalPage;
		var $table = $ ("#table");
		$table.empty();
		str = '<thead><tr><th width="5%"  align="center" >ID</th><th width="6%"  align="center" >用户名</th><th width="6%"  align="center" >真实姓名</th> <th width="8%"  align="center" >可用金额</th><th width="6%" align="center" >冻结金额</th><th width="6%" align="center" >待收金额</th> <th width="13%" align="center" >注册时间</th><th width="20%"  align="center" >操作</th></tr></thead>';
		var jsonRoot = data.jsonRoot;
		for(var i = 0; i < jsonRoot.length; i++) {
			str += '<tbody><tr><td>'+ jsonRoot[i].id+ '</td><td>'
	
				   + decodeURI(jsonRoot[i].name, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].realname, "utf-8") +"</td><td>"
				  
				   + decodeURI(jsonRoot[i].amoney, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].fmoney, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].dmoney, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].time, "utf-8") +"</td><td>"
				   +	  '<c:if test="${sessionScope.powerss[21]==1}"><a href="<%=path%>/back/3_revise.jsp?id='+jsonRoot[i].id+' ">[调整余额]</a>   </c:if><c:if test="${sessionScope.powerss[22]==1}"><a href="<%=path%>/back/3_credit.jsp?id='+jsonRoot[i].id+' ">[调整授信]</a> </c:if>   <c:if test="${sessionScope.powerss[23]==1}"><a href="<%=path%>/back/3_edit.jsp?userType='+jsonRoot[i].userType+'&id='+jsonRoot[i].id+' ">[修改信息] </a> </c:if>'  +"</td></tr>  </tbody>";

		}
		$table.html(str);
	
		  if(tp !=1)
			{		
		document.getElementById("pages").style.display="";
	     $ ("#currentPages").text("第"+cp+"页");
         $ ("#totalPages").text("共"+tp+"页");
         $ ("#tpagesnone").val(tp);
	     $ ("#firstPages").attr("href","javascript:getJSONDatas(1,10,'phoneResult')");
	     $ ("#prevPages").attr("href","javascript:getJSONDatas("+(parseInt(cp)-1)+",10,'phoneResult')");
	     $ ("#nextPages").attr("href","javascript:getJSONDatas("+(parseInt(cp)+1)+",10,'phoneResult')");
	     $ ("#lastPages").attr("href","javascript:getJSONDatas("+(parseInt(tp))+",10,'phoneResult')");
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
    	getJSONDatas(size,10,'phoneResult');  
    }
    
}


$(function() {  
	getJSONDatas(1,10,'phoneResult');  
});
$(document).ready(function($){
	
	//默认选项卡切换
	$("#normaltab1").tabso({
		cntSelect:"#normalcon1",
		tabEvent:"mouseover",
		tabStyle:"normal"
	});
	
	//默认选项卡切换
	$("#normaltab2").tabso({
		cntSelect:"#normalcon2",
		tabEvent:"mouseover",
		tabStyle:"normal"
	});
	
	//默认选项卡切换
	$("#normaltab3").tabso({
		cntSelect:"#normalcon3",
		tabEvent:"mouseover",
		tabStyle:"normal"
	});
	
});
</script>
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
<script type="text/javascript" src="laydate/laydate.js"></script>


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
    
    <div class="tools">
       <div id="panel">
              <div class="panel_title">搜索/筛选会员</div>
              <div class="panel_nr fn_cle">
                 <form enctype="multipart/form-data" action="/Admin/global/addFriend.html" method="post">

    <dl class="line">
      <dt>会员名：</dt>
      <dd>
        <input type="text" value="" id="link_txt" class="input" name="link_txt" size="40">
        <span>不填则不限制</span>
      </dd>
    </dl>

    
 
    <dl class="line">
      <dt>注册时间(开始)：</dt>
      <dd>
	  	<input class="laydate-icon" id="demo1" value="-请选择时间-">
        <span>只选开始时间则查询从开始时间往后所有</span>
      </dd>
    </dl>
    <dl class="line">
      <dt>注册时间(结束)：</dt>
      <dd>
        <input  class="laydate-icon" id="demo2" value="-请选择时间-">
        <span>只选结束时间则查询从结束时间往前所有</span>
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
	  <input type="hidden" disabled="disabled" value="" id="fid" name="fid">
	   <input type="button" name="Submit" value="搜索" class="btn" onclick="getJSONDatas(1,10,'phoneResult');addCheck('2')"/>
    
    </div>
	</form>
              </div>
         </div>
    
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
  	<em class="skip">转到<input name="page" type="text" id="sizes" size="5" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>页</em><a href="#" id="tiaozhuans" onclick="tiaozhuans()">跳转</a>
    </div>
 

   
  
    
   

</body>

</html>
