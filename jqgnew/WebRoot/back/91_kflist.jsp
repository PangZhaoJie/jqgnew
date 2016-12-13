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
<title>无标题文档</title>
<link href="<%=path %>/back/css/style1.css" rel="stylesheet" type="text/css" />
<style>
.forminfo li .select1 { color: #333333;padding-left: 5px;  height: 34px; line-height: 34px; margin-top:5px; float:left;border-color: #a7b5bc #ced9df #ced9df #a7b5bc;  border-style: solid;  border-width: 1px;}
</style>
<script type="text/javascript" src="<%=path %>/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/jquery.form.js"></script>
<script type="text/javascript" src="<%=path %>/ckeditor/ckeditor.js"></script>  
<script type="text/javascript" src="<%=path %>/ckfinder/ckfinder.js"></script>
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
<script type="text/javascript">
   function openLoad(open){
	   if(open=='1'){
		   addCheck('1'); 
	   }
	var mark = document.getElementById("mark").value;
 	if(mark=='1'){
 		alert("该角色正在被管理员使用，无法删除！");
 		return false;
 	}
 	
 	$(".btn_b").click(function(){
 		if(check()){
 			getSex();
		   var data = "managerName="+$("#managerName").val()+"&password="+$("#password").val()+"&passwordWord="+$("#passwordWord").val()
		   +"&realName="+$("#realName").val()+"&telephone="+$("#telephone").val()+"&qq="+$("#qq").val()+"&display2="+$("#radioVar").val()+"&roleId="+$("#roleId").val()+"&pathMages="+$("#wpic").val()+"&managerId="+$("#managerId").val();
		   $.ajax({
				//使用json，/json是他的命名空间
				url:"<%=path%>/json/createManager", 
				type:"post",
				data:data,//使用这个方法可以使表单里面的内容自动提交上去
				dataType:"json",//返回类型为json对象
				success:function(data){
					if(data=="success"){
						window.location.href="<%=path %>/extension/searchManager";
					}
					if(data.result=="error"){
						alert("提交失败！");
					}
				}
			});
		}
		});
 		   //jquery 发送请求给FileUploadAction
		$("#file").change(function(){
			
			var option={
					url:"<%=path%>/json/upload",
					type:"post",
					dataType:"json",
					beforeSend:function(){
						$("#uploadloading").show();	
					},
					complete:function(){
						$("#uploadloading").hide();	
					},
					success:function(data){
						if(data.result=="success"){
							
			
							imgName=data.imageFileName;
					
							$("#wpic").val(imgName);
							//获取图片的高度和宽度
							var w=data.width;
							var h =data.height;
							
							if(w>=h){
								//横向图片
								if(w>200){
									h=(1-(w-200)/(w*1.0))*h;
									w=200;
								}
								
							}
							else if(w<h){
								//纵向图片
								if(h>200){
									w=(1-(h-200)/(h*1.0))*w;
									h=200;
								}
							
							}
							
							$(".img").show();
							$("#delPic").show();
							$("#delPics").hide();
	   						$(".imgs").hide();
							$(".img").prepend("<img width="+w+" height="+h+ " src='../images/new/"+data.imageFileName+" '/>");
							$("#filepic").hide();
					    }
						else{
							alert("图片格式只能为jpg、png或gif！");
							}
				}
			};
			
			$("#uploadForm").ajaxSubmit(option);
			return false;
		});
		$("#delPic").click(function(){
			$.ajax({
				url:"<%=path%>/json/delPic",
				type:"post",
				data:"FileName="+imgName,
				success:function(data){
					if(data.message=="yes"){
						$("#wpic").val("");
						$(".img").hide();
						$(".img").find("img").remove();
						$("#filepic").show();
					}
				}
			});
		});
		$("#delPics").click(function(){
	   		var name = $("#wpic").val();
	   		$.ajax({
	   			url:"<%=path%>/json/delPics",
	   			type:"post",
	   			data:"fileName="+name,
	   			success:function(data){
	   				if(data.message=="yes"){
	   					$("#npic").val("");
	   					$(".imgs").hide();
	   					$(".imgs").find("imgs").remove();
	   					$("#file").show();
	   				}
	   			}
	   		});
	     });
   }
   
function getSex(){
  var value="";
  var radio=document.getElementsByName("display2");
  for(var i=0;i<radio.length;i++){
	if(radio[i].checked==true){
	  value=radio[i].value;
	  break;
	}
  }
  $("#radioVar").val(value);
  return value;
}
   function check(){
	   var managerName=$("#managerName").val();
	   var managerId=$("#managerId").val();
	   var password=$("#password").val();
	   var passwordWord=$("#passwordWord").val();
	   var realName=$("#realName").val();
	   var qq=$("#qq").val();
	   var telephone=$("#telephone").val();
	   var display=document.getElementsByName("display2");
	   var flag1="0";
	   if(managerName=="" || managerName==null){
		   $("#tip1").html("<span style='font-size:12px;color:red;float:left;'>客服名字不能为空</span>");
		   return false;
	   }else{
		   $("#tip1").html("");
		   var data = "managerName="+managerName+"&managerId="+managerId;
			$.ajax({
				async:false,
				url:"<%=path%>/json/checkManager", 
				type:"post",
				data:data,//使用这个方法可以使表单里面的内容自动提交上去
				dataType:"json",//返回类型为json对象
				success:function(data){
					if(data.result=="error"){
						$("#tip1").html("<span style='font-size:12px;color:red;float:left;'>客服名字已存在</span>");
						flag1="1";
					}else{
						flag1="2";
					}
				}
			});
	   }
	   if(flag1!="2"){
		   return false;
	   }
	   if(password=="" || password==null){
		   $("#tip2").html("<span style='font-size:12px;color:red;float:left;'>密码不能为空</span>");
		   return false;
	   }else{
		   $("#tip2").html("");
	   }
	   if(passwordWord=="" || passwordWord==null){
		   $("#tip3").html("<span style='font-size:12px;color:red;float:left;'>口令不能为空</span>");
		   return false;
	   }else{
		   $("#tip3").html("");
	   }
	   if(realName=="" || realName==null){
		   $("#tip4").html("<span style='font-size:12px;color:red;float:left;'>真实姓名不能为空</span>");
		   return false;
	   }else{
		   $("#tip4").html("");
	   }
	   if(qq=="" || qq==null){
		   $("#tip5").html("<span style='font-size:12px;color:red;float:left;'>qq不能为空</span>");
		   return false;
	   }else{
		   $("#tip5").html("");
	   }
	   if(telephone=="" || telephone==null){
		   $("#tip6").html("<span style='font-size:12px;color:red;float:left;'>手机号不能为空</span>");
		   return false;
	   }else{
		   $("#tip6").html("");
	   }
	   var flag=-1;
	   for(var i=0;i<display.length;i++){
		   if(display[i].checked){
			  flag=i; 
		   }
	   }
	   if(flag<0){
		   $("#tip7").html("<span style='font-size:12px;color:red;float:left; margin-top:0;'>请选择是否显示</span>"); 
		   return false;
	   }else{
		   $("#tip7").html("");
	   }
	   return true;
   }
</script>
<style>
.tablelist td .input { color: #333333;padding: 0px 5px;  height: 28px; line-height: 28px; margin:8px 0 8px 10px; float:left;border-color: #a7b5bc #ced9df #ced9df #a7b5bc;  border-style: solid;  border-width: 1px;}
.tablelist td { }
</style>

</head>


<body onload="openLoad(${open2});">
	<input type="hidden" value="${mark}" id="mark"/>
	<input type="hidden" value="" id="radioVar"/>
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#" onclick="parent.frames[1].location.href='<%=basePath%>/back/1_left.jsp';  parent.frames[2].location.href='<%=basePath%>/overall/index'; ">首页</a></li>
    <li><a href="#" onclick="parent.frames[1].location.href='<%=basePath%>/back/9_left.jsp';  parent.frames[2].location.href='<%=basePath%>/overall/findlssh'; ">扩展管理</a></li>
    <li><a href="#">在线客服管理</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    
       <div class="tools">
       <div id="panel">
              <div class="panel_title">添加QQ在线客服</div>
              <div class="panel_nr fn_cle">
    <input id="basePathId" value="<%=basePath%>" type="hidden"/>
    <dl class="line">
      <dt>客服名字：</dt>
      <dd>
        <input name="managerName" type="text" value="${managerName}" id="managerName" class="input" name="link_txt" size="40">
         <span id="tip1"></span>
      </dd>
    </dl>
     <dl class="line">
      <dt>客服密码：</dt>
      <dd>
        <input name="password" type="text" value="${password}" id="password" class="input" name="link_txt" size="40">
         <span id="tip2"></span>
      </dd>
    </dl>
    <dl class="line">
      <dt>客服口令：</dt>
      <dd>
        <input name="passwordWord" type="text" value="${passwordWord}" id="passwordWord" class="input" name="link_txt" size="40">
         <span id="tip3"></span>
      </dd>
    </dl>
    <dl class="line">
      <dt>真实姓名：</dt>
      <dd>
        <input name="realName" type="text" value="${realName}" id="realName" class="input" name="link_txt" size="40">
         <span id="tip4"></span>
      </dd>
    </dl>
    <dl class="line">
      <dt>qq号：</span>
      <dd>
        <input name="qq" type="text" value="${qq}" id="qq" class="input" name="link_href" size="40" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
        <span id="tip5"></span>
      </dd>
    </dl>
    <dl class="line">
      <dt>电话：</span>
      <dd>
        <input name="telephone" type="text" value="${telephone}" id="telephone" class="input" name="link_href" size="40" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
        <span id="tip6"></span>
      </dd>
    </dl>
    <dl class="line">
      <dt>角色：</span>
      <dd>
        <select id="roleId" class="input" name="roleId" id="roleId" style="width:277px;">
        	<c:forEach var="role" items="${roles}">
        		<option value="${role.roleId}" <c:if test="${role.roleId ==roleId}">  selected="selected"  </c:if>>${role.roleName}</option>
        	</c:forEach>
        </select>
        <span id="tip8"></span>
      </dd>
    </dl>
     <dl class="line">
    <li><label id="info_title2" style="width: 170px">图片上传：</label> 
        <div id ="wpicture">
		                 <s:hidden id ="wpic" name ="WPicture" value =""></s:hidden>
                        </div>
					  
						<div class="showPic" style="font-size:20px;overflow:hidden">
						  <form enctype="multipart/form-data"  id="uploadForm">
								<div id="filepic"><input type="file" name="myFile"  id="file" style= "height:30px;width:250px;"/>图片大小2000*450</div>
									</form>
								<div id ="uploadloading" style="display: none;margin-left:60px">
									<img src ="<%=path %>/back/images/loading.gif" />正在上传请稍后......
								</div>									
								<div class="img" style="display: none;float: left;" >
										<a href="#" id ="delPic">删除图片</a>
								</div>
								<div class="imgs"    <s:if test="#session.cus.pathMages==null">  style="display: none;" </s:if>>
								     <img id="npic" alt="" src="<%=path %>${cus.pathMages} "height="135px" style="margin-left:130px"/>
								     <a href="#" id ="delPics">删除图片</a>
								</div>
						</div>
				
     </li>
    </dl>
     <dl class="line">
      <dt>是否显示：</dt>
      <dd>
          <p style="line-height:33px;">
                
                  <label style="float:left;">  <input type="radio" <c:if test="${display2==1}">checked="checked"</c:if> name="display2" value="1" id="RadioGroup1_0" />是</label>
                  <label style="float:left;"> <input type="radio"  <c:if test="${display2==0}">checked="checked"</c:if> name="display2" value="0" id="RadioGroup1_1" style=" margin:0 3px 0 15px;" />否 </label>
                  <span id="tip7" style=" margin-top:0;"></span>
               </p>
      
      </dd>
    </dl>
    
    
    <div class="page_btn">
      <input type="hidden" name="managerId" id="managerId" value="${managerId}"/>
	  <input type="hidden" disabled="disabled" value="" id="fid" name="fid">
      <input type="button" value="确定"  id="showwait" class="btn_b">
    </div>
              </div>
         </div>
    	<ul class="toolbar" style="margin-bottom:8px;">
    	<c:if test="${sessionScope.powerss[75]==1 }">
         <li  id="ID1" onclick="addCheck('1')"><span><img src="<%=basePath%>/back/images/t01.png" /></span>添加QQ在线客服</li>
         <li  id="ID2" style="display:none" onclick="addCheck('2')"><span><img src="<%=basePath%>/back/images/t01.png" /></span>添加完毕</li>
        </c:if>
<%--          <li><span><img src="<%=basePath%>/back/images/t03.png" /></span>删除客服</li> --%>
       
        </ul>
      
    
    
      <table class="tablelist" style="width:100%">
    	<thead>
    	<tr>
        <th>ID</th>
        <th>客服名字</th>
        <th>口令</th>
        <th>真实姓名</th>
        <th>qq号</th>
        <th>电话</th>
        <th>角色</th>
        <th>客户数量</th>
        <th>是否显示</th>
        <th>操作</th>
        </tr>
        </thead>

        <c:forEach var="customer" items="${man}">
            <tbody>
        	<tr>
            <td>${customer.managerId}</td> 
        	<td>${customer.managerName}</td>
        	<td>${customer.passwordWord}</td>
        	<td>${customer.realName}</td>
        	<td>${customer.qq}</td>
        	<td>${customer.telephone}</td>
        	<td>${customer.role.roleName}</td>
        	<td>${customer.count}</td>
        	<td>
        	<c:if test="${customer.display==0}">
        	不显示
        	</c:if>
        	<c:if test="${customer.display==1}">
        	显示
        	</c:if>
        	</td>
        	<td>
        	<c:if test="${sessionScope.powerss[124]==1 }">
        	<a href="<%=basePath%>/extension/editManager?managerId=${customer.managerId}" class="tablelink">编辑</a>
        	</c:if>
        	<c:if test="${sessionScope.powerss[124]!=1 }">
        	--
        	</c:if>
        	<c:if test="${sessionScope.powerss[76]==1 }">
        	<a href="<%=basePath%>/extension/delManager?managerId=${customer.managerId}" class="tablelink"> 删除</a>
        	</c:if>
        	<c:if test="${sessionScope.powerss[76]!=1 }">
        	--
        	</c:if>
        	</td>
        	</tr>
        	</tbody>
        </c:forEach>
         

    </table>
    
    </div>
       
    
    </div>
    
  
</body>

</html>
