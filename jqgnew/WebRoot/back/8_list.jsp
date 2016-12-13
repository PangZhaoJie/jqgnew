<%@page import="com.jqg.service.impl.IPServiceImpl"%>
<%@page import="com.jqg.service.impl.*"%>
<%@page import="com.jqg.service.IPService"%>
<%@page import="com.jqg.service.*"%>
<%@page import="com.jqg.pojo.*"%>
<%@page import="java.util.*"%>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
  <%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath(); 
String basePath = "";if(request.getServerPort()==80){basePath = request.getScheme()+"://"+request.getServerName()+path+"/";}else{basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";}    
IPService ipService = new IPServiceImpl();
String ip = ipService.getIpAddr(request);

RoleService roleService=new RoleServiceImpl();
List<Role> roles=roleService.findRoles();
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />


<title>无标题文档</title>
<link href="<%=path %>/back/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=path %>/back/css/mem.css" rel="stylesheet" type="text/css" />
<link href="<%=path %>/back/css/tabs.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path %>/back/js/jquery.js"></script>
<script type="text/javascript" src="<%=path %>/back/js/jquery.tabso_yeso.js"></script>
<!--折叠js-->
<script type="text/javascript"> 
$(document).ready(function(){

	$("#add").click(function(){
	       if($("#managerName").val()==""||$("#managerName").val()==null)
       	{
       	document.getElementById("namesnone").style.display="";
       	   return;
       	}
       if($("#password").val()==""||$("#password").val()==null)
   	{
       	document.getElementById("pwdnone").style.display="";
   	   return;
   	}
       if($("#passwordWord").val()==""||$("#passwordWord").val()==null)
   	{
       	document.getElementById("pwdwnone").style.display="";
   	   return;
   	}
       //添加属性
       if($("#realName").val()==""||$("#realName").val()==null)
      	{
           document.getElementById("realNameTip").style.display="";
      	   return;
      	}
       if($("#telephone").val()==""||$("#telephone").val()==null)
     	{
          document.getElementById("telephoneTip").style.display="";
     	   return;
     	}
//        var is=document.getElementsByName("isBan");
//        var iss="";
//        for(var i=0;i<is.length;i++){
//     	   if(is[i].checked){
//     		   iss=i;
//     		   break;
//     	   }
//        }
	    document.getElementById("add").setAttribute("disabled", "disabled");
		//获取留言
		var data = "managerName="+$("#managerName").val()+"&password="+$("#password").val()+"&passwordWord="+$("#passwordWord").val()
		+"&roleId="+$("#roleId").val()+"&realName="+$("#realName").val()+"&qq="+$("#qq").val()+"&telephone="+$("#telephone").val();
 

		$.ajax({
			//使用json，/json是他的命名空间
			url:"<%=path%>/json/addmanager", 
			type:"post",
			data:data,//使用这个方法可以使表单里面的内容自动提交上去
			dataType:"json",//返回类型为json对象
			success:function(data){
				if(data.result=="success"){
					window.open("<%=path%>/back/8_list.jsp",'rightFrame');
				}
				if(data.result=="error"){
					document.getElementById("namenone").style.display="";
					document.getElementById("add").removeAttribute("disabled");
					return ;
				}
				document.getElementById("add").style.background="gray";
	  			document.getElementById("add").value="正在提交....";
	   		    document.getElementById("add").style.disabled=false;
			}
		});
	});
	$("#upt").click(function(){
	       if($("#mname").val()==""||$("#mname").val()==null)
    	{
    	document.getElementById("uptnamesnone").style.display="";
    	   return;
    	}
    if($("#pwd").val()==""||$("#pwd").val()==null)
	{
    	document.getElementById("uptpwdnone").style.display="";
	   return;
	}
    if($("#pwdw").val()==""||$("#pwdw").val()==null)
	{
    	document.getElementById("uptpwdwnone").style.display="";
	   return;
	}
    
  //添加属性
    if($("#realName1").val()==""||$("#realName1").val()==null)
   	{
        document.getElementById("realNameTip1").style.display="";
   	   return;
   	}
    if($("#telephone1").val()==""||$("#telephone1").val()==null)
  	{
       document.getElementById("telephoneTip1").style.display="";
  	   return;
  	}
//     var is1=document.getElementsByName("isBan1");
//     var iss1="";
//     for(var i=0;i<is1.length;i++){
//  	   if(is1[i].checked){
//  		   iss1=i;
//  		   break;
//  	   }
//     }
		//获取留言
		var data = "managerName="+$("#mname").val()+"&password="+$("#pwd").val()+"&passwordWord="+$("#pwdw").val()
		+"&roleId="+$("#roleIdput").val()+"&managerId="+$("#mId").val()+"&realName="+$("#realName1").val()+"&qq="+$("#qq1").val()+"&telephone="+$("#telephone1").val();


		$.ajax({
			//使用json，/json是他的命名空间
			url:"<%=path%>/json/updatemanager", 
			type:"post",
			data:data,//使用这个方法可以使表单里面的内容自动提交上去
			dataType:"json",//返回类型为json对象
			success:function(data){
				if(data.result=="success"){
					window.open("<%=path%>/back/8_list.jsp",'rightFrame');
				}
				if(data.result=="error"){
					document.getElementById("uptnamenone").style.display="";
				}
			}
		});
		
	});
});
function getJSONDatas(currentPage,pageSize,num) {
	  

	var url = "<%=path%>/overall/mangerlist.action?currentPage="+currentPage+"&pageSize="+pageSize+"&1=" + num;


	$.getJSON(url,function(data){
	
		var cp = data.currentPage;
		var tp = data.totalPage;
		var $table = $ ("#table");
		$table.empty();
		str = '<thead><tr><th>ID</th><th>用户名</th> <th>所属用户组</th><th>密码口令</th><th>真实姓名</th><th>qq</th><th>电话</th><th>操作</th></tr></thead>';
		var jsonRoot = data.jsonRoot;
		for(var i = 0; i < jsonRoot.length; i++) {
			var regS = new RegExp("'","gi");
			var strs=jsonRoot[i].strs.replace(regS,"&");
			str += '<tbody><tr><td>'+ jsonRoot[i].id+ '</td><td>'
	
				   + decodeURI(jsonRoot[i].name, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].rolename, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].password, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].realName, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].qq, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].telephone, "utf-8") +"</td><td>"
					+	  '<c:if test="${sessionScope.powerss[62]==1}"><a href="javascript:void(0)" style="float:left;text-indent:0;" class="tablelink" onclick = "setUserId('
							+ jsonRoot[i].id+",'"+jsonRoot[i].name+"'"+",'"+jsonRoot[i].password+"'"+",'"+jsonRoot[i].pwd+"'"+",'"+strs+"'"+",'"+jsonRoot[i].realName+"'"+",'"+jsonRoot[i].qq+"'"+",'"+jsonRoot[i].telephone+"'"+');">更新</a><span style="float:left;text-indent:0;" id="ma'+jsonRoot[i].id+'"></c:if><c:if test="${sessionScope.powerss[62]!=1}">--</c:if>|<c:if test="${sessionScope.powerss[61]==1}"><a  class="tablelink" onclick="hunakuan('+jsonRoot[i].id+')" href="<%=path%>/overall/deletemanager?managerId=' + jsonRoot[i].id +'&ip=<%=ip%>' +'">删除</a></c:if><c:if test="${sessionScope.powerss[61]!=1}">--</c:if></span></td></tr>  </tbody>';

		}
		$table.html(str);
	
	    if(tp !=1)
		{		
	    	res = Math.random(); 
		document.getElementById("pages").style.display="";
	     $ ("#currentPages").text("第"+cp+"页");
         $ ("#totalPages").text("共"+tp+"页");
         $ ("#tpagesnone").val(tp);
	     $ ("#firstPages").attr("href","javascript:getJSONDatas(1,10," +res+ ")");
	     $ ("#prevPages").attr("href","javascript:getJSONDatas("+(parseInt(cp)-1)+",10," +res+ ")");
	     $ ("#nextPages").attr("href","javascript:getJSONDatas("+(parseInt(cp)+1)+",10," +res+ ")");
	     $ ("#lastPages").attr("href","javascript:getJSONDatas("+(parseInt(tp))+",10," +res+ ")");
		}
		else{
			document.getElementById("pages").style.display="none";
		}
	});
}
function hunakuan(mark){
	var fm=document.getElementById("ma"+mark);
	fm.innerHTML="|<a onclick='hunakuan2()' class='green' href='javascript:void(0)' class='tablelink'>删除</a>";
 }
 function hunakuan2(){
      alert("正在操作中，请稍后...");
 }
function tiaozhuans() {
    var size = $("#sizes").val();
    var totalpage = $("#tpagesnone").val();
  
    if(parseInt(size)<1||parseInt(size)>parseInt(totalpage))
    {
    	alert("请输入正确的页数")
    	return false;
    }else{
    	getJSONDatas(size,10,Math.random());  
    }
    
}
window.onload = function(e) {

	getJSONDatas(1,10,Math.random());
	delCustomer();  
}


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
 function delCustomer(){
 	var mark = document.getElementById("mark").value;
 	if(mark=='1'){
 		alert("该角色正在被管理员使用，无法删除！");
 		return false;
 	}
 }
</script>


</head>


<body>
<input type="hidden" value="${mark}" id="mark"/>
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
 <li><a href="#" onclick="parent.frames[1].location.href='<%=path%>/back/1_left.jsp';  parent.frames[2].location.href='<%=path%>/overall/index'; ">首页</a></li>
 
    <li><a href="#" onclick="parent.frames[1].location.href='<%=basePath%>/back/8_left.html';  parent.frames[2].location.href='<%=basePath%>/back/8_list.jsp'; ">管理员管理</a></li>
    <li><a href="#">管理员列表</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    
       <div class="tools">
       <div id="panel">
              <div class="panel_title">添加管理员</div>
              <div class="panel_nr fn_cle">

    <dl class="line">
      <dt>管理员用户名：</dt>
      <dd>
        <input type="text" value="" id="managerName" class="input" name="managerName" size="40" onclick="document.getElementById('namesnone').style.display='none';document.getElementById('namenone').style.display='none';">
        <span>管理员登录时的用户中,一经创建，不可更改！</span>
       <div id="namenone" style="display: none;"><span style="color: red"> *管理员已存在，请换个用户名。</span></div>
       <div id="namesnone" style="display: none;"><span style="color: red">*管理员用户名不能为空</span></div>
      </dd>
    </dl>
	
    <dl class="line">
      <dt>管理员密码：</dt>
      <dd>
        <input type="password" value="" id="password" class="input" name="password" size="40" onclick="document.getElementById('pwdnone').style.display='none';">
        <span>登录员登录时的密码,密码不区分大小写</span>
       <div id="pwdnone" style="display: none;"><span style="color: red">*密码不能为空。</span></div>
      </dd>
    </dl>
    
   
   
	<dl class="line">
      <dt>密码口令：</dt>
      <dd>
        <input type="text" value="" id="passwordWord" class="input" name="passwordWord" size="40" onclick="document.getElementById('pwdwnone').style.display='none';">
        <span>管理员密码口令设置后，在后台登录时只有写对口令才能进入后台（可填写文字、字母或数字，如：客服1号）</span>
        <div id="pwdwnone" style="display: none;"><span style="color: red">*密码口令不能为空 </span></div>
      </dd>
    </dl>
    <!-- 添加内容真实姓名 -->
    <dl class="line">
      <dt>真实姓名：</dt>
      <dd>
        <input type="text" value="" id="realName" class="input" name="realName" size="40" onclick="document.getElementById('realNameTip').style.display='none';">
        <span>请你如实填写你的真实姓名!</span>
        <div id="realNameTip" style="display: none;"><span style="color: red">*真实姓名不能为空 </span></div>
      </dd>
    </dl>
    <dl class="line">
      <dt>QQ：</dt>
      <dd>
        <input type="text" value="" id="qq" class="input" name="qq" size="40" onclick="document.getElementById('qqTip').style.display='none';" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
        <span>请你如实填写你的QQ号!</span>
        <div id="qqTip" style="display: none;"><span style="color: red">可以不填 </span></div>
      </dd>
    </dl>
    <dl class="line">
      <dt>电话：</dt>
      <dd>
        <input type="text" value="" id="telephone" class="input" name="telephone" size="40" onclick="document.getElementById('telephoneTip').style.display='none';" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
        <span>请你如实填写你的手机号!</span>
        <div id="telephoneTip" style="display: none;"><span style="color: red">*手机号不能为空 </span></div>
      </dd>
    </dl>
    <!-- 添加内容 -->		 
    
   <dl class="line">
      <dt>所属用户组：</dt>
       <dd>
	  	<select id="roleId" class="input" name="roleId" style="width:277px;" >
	  		  	<%
 	  	if(roles!=null && roles.size()>0){
 		  	for(int i=0;i<roles.size();i++){
 		  		Role role=roles.get(i);
 		  		%>
 		  		<option value="<%=role.getRoleId() %>"> 
 		  		<%=role.getRoleName() %> 
 		  		</option>
 		  		<%
 		  	}
 	  	}
 	  	%> 
<!-- 	  	<option value="2">管理员</option>		 -->
<!-- 	  	<option value="1">超级管理员</option> -->
        
  
        </select>
        <span>不同的用户组对应不同的权限</span>
      </dd>
    </dl>
<!--     <dl class="line"> -->
<!--       <dt>是否禁用：</dt> -->
<!--       <dd> -->
<!--         <input type="radio" name="isBan" id="isBan1" value="0" checked="checked">&nbsp;是&nbsp;</input> -->
<!--         <input type="radio" name="isBan" id="isBan2" value="1">&nbsp;否&nbsp;</input> -->
<!--       </dd> -->
<!--     </dl> -->
    <div class="page_btn">
    		   <textarea name="ip" style="display: none;"id="ip"><%=ip %></textarea>
	<!--   <input type="hidden" disabled="disabled" value="" id="fid" name="fid"/> -->
     <input type="button" name="Submit" value="添加管理员" class="btn"  id="add"/>
    </div>

              </div>
         </div>
    	<ul class="toolbar" style="margin-bottom:8px;">
    	<c:if test="${sessionScope.powerss[60]==1}">
    	
         <li  id="ID1" onclick="addCheck('1')"><span><img src="<%=path %>/back/images/t01.png" /></span> 添加管理员 </li>
         <li  id="ID2" style="display:none" onclick="addCheck('2')"><span><img src="<%=path %>/back/images/t01.png" /></span>添加完毕</li>
		</c:if>
       
        </ul>  
      <table class="tablelist" id="table" style="width:100%;">
    	<thead>
    	<tr>
        <th>ID</th>
        <th>用户名</th> 
        <th>所属用户组</th>
        <th>密码口令</th>
        <th>真实姓名</th>
        <th>qq</th>
        <th>电话</th>
        <th>操作</th>
        </tr>
        </thead>
       
    </table>
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
    
       
    
    </div>
    
    <div id="light33" class="white_content">
       <div class="light_title">
          <span>更新管理员</span> 
          <a href = "javascript:void(0)" onclick = "document.getElementById('light33').style.display='none';document.getElementById('fade').style.display='none';" class="close"><img src="<%=path %>/back/images/close1.png"/></a>
      </div>
      <div class="light_box">
        <ul class="tabbtn" id="normaltab1">
		   <li class="current">更新管理员</li>
		  
		 </ul>
         <!--tabbtn end-->
        <div class="tabcon" id="normalcon1">
         <div class="sublist">
           
           <ul>
          <li><label id="info_title">管理员名字：</label><input type="text" name="managerName"  id="mname" readonly="readonly" style="float:left;height:32px; line-height:32px; width:267px; border:solid 1px #ced9df; background:url(<%=path %>/images/inputbg.gif) repeat-x; text-indent:10px;"onclick="document.getElementById('uptnamesnone').style.display='none';document.getElementById('uptnamenone').style.display='none';"/>       
          <div id="uptnamenone" style="float:left;display: none;"><span style="color: red"> *管理员已存在，请还个用户名。</span></div>
          <div id="uptnamesnone" style="float:left;display: none;"><span style="color: red">*管理员用户名不能为空</span></div></li>
          <li><label id="info_title">管理员密码：</label><input type="text" name="password"  id="pwd" style="float:left;height:32px; line-height:32px; width:267px; border:solid 1px #ced9df; background:url(<%=path %>/images/inputbg.gif) repeat-x; text-indent:10px;"onclick="document.getElementById('uptpwdnone').style.display='none';"/>
            <div id="uptpwdnone" style="float:left;display: none;"><span style="color: red">*密码不能为空。</span></div></li>
		  <li><label id="info_title">管理员口令：</label><input type="text" name="passwordWord"  id="pwdw" style="float:left;height:32px; line-height:32px; width:267px; border:solid 1px #ced9df; background:url(<%=path %>/images/inputbg.gif) repeat-x; text-indent:10px;"onclick="document.getElementById('uptpwdwnone').style.display='none';"/>
		      <div id="uptpwdwnone" style="float:left;display: none;"><span style="color: red">*密码口令不能为空 </span></div></li>
<!-- 		     添加内容  -->
          <li><label id="info_title">真实姓名：</label><input type="text" name="realName1"  id="realName1" style="float:left;height:32px; line-height:32px; width:267px; border:solid 1px #ced9df; background:url(<%=path %>/images/inputbg.gif) repeat-x; text-indent:10px;"onclick="document.getElementById('realNameTip1').style.display='none';"/>
		      <div id="realNameTip1" style="float:left;display: none;"><span style="color: red">*真实姓名不能为空 </span></div></li>
		  <li><label id="info_title">QQ：</label><input type="text" name="qq1"  id="qq1" style="float:left;height:32px; line-height:32px; width:267px; border:solid 1px #ced9df; background:url(<%=path %>/images/inputbg.gif) repeat-x; text-indent:10px;"onclick="document.getElementById('qqTip1').style.display='none';" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
		      <div id="qqTip1" style="float:left;display: none;"><span style="color: red">*QQ不能为空 </span></div></li>
		  <li><label id="info_title">电话：</label><input type="text" name="telephone1"  id="telephone1" style="float:left;height:32px; line-height:32px; width:267px; border:solid 1px #ced9df; background:url(<%=path %>/images/inputbg.gif) repeat-x; text-indent:10px;"onclick="document.getElementById('telephoneTip1').style.display='none';" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
		      <div id="telephoneTip1" style="float:left;display: none;"><span style="color: red">*电话不能为空 </span></div></li>
		  <li><label id="info_title">权限：</label>	
		  <span style="border:1px solid #ced9df;;"> 
		  <select id="roleIdput" class="input" name="roleId" style="width:260px;height:32px;" >
<!-- 	          <option value="2">管理员</option>		 -->
<!-- 	          <option value="1">超级管理员</option> -->
          </select>
          </span>
          </li>
<!--           <li> -->
<!--           <label id="info_title">是否禁用：</label> -->
<!--           <input type="radio" name="isBan1" id="isBan3" value="0">&nbsp;是&nbsp;</input> -->
<!--           <input type="radio" name="isBan1" id="isBan4" value="1">&nbsp;否&nbsp;</input> -->
<!--           </li> -->
		  <li><input name="" type="button" style="margin-left:110px;" class="btn" value="确定" id="upt"/></li>
		  <li><input type="text" name="managerId"  id="mId" style="display: none;"/></li>
		 <li>  <textarea name="ip" style="display: none;"><%=ip %></textarea></li>
       </ul>

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
		document.getElementById("vidio").submit();	
		}

function setUserId(id,mname,pwdw,pwd,rid,real,qq,pho){
	document.getElementById("light33").style.display="block";document.getElementById("fade").style.display="block";
   var input1=  document.getElementById("mId");
    input1.value=id;
    var input2=  document.getElementById("mname");
    input2.value=mname;
    var input3=  document.getElementById("pwdw");
    input3.value=pwdw;
    var input4=  document.getElementById("pwd");
    input4.value=pwd;
    
    //添加
    var input5=  document.getElementById("realName1");
    input5.value=real;
    var input6=  document.getElementById("qq1");
    input6.value=qq;
    var input7=  document.getElementById("telephone1");
    input7.value=pho;
//     var type = document.getElementsByName("isBan1");
//     for(var i = 0; i < type.length; i = i + 1){
//     	if(type[i].value == is){
// 	    	type[i].checked = "checked";
// 	    	break;
//     	}
//     }
//     var select = document.getElementById("link_types");  
//     for(var i=0; i<select.options.length; i++){  
//         if(select.options[i].innerHTML == rid){  
        
//             select.options[i].selected = true;  
//             break;  
//         }  
        
//     }  
    var regS = new RegExp("&","gi");
	var strs=rid.replace(regS,"'");
	$("#roleIdput").html(strs);

}
</script>
</body>

</html>
