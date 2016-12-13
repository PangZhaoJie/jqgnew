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
<meta http-equiv="Content-Type" content="text/plain; charset=utf-8" />
<title>无标题文档</title>
<link href="<%=path %>/back/css/style1.css" rel="stylesheet" type="text/css" />
<style>
.forminfo li .select1 { color: #333333;padding-left: 5px;  height: 34px; line-height: 34px; margin-top:5px; float:left;border-color: #a7b5bc #ced9df #ced9df #a7b5bc;  border-style: solid;  border-width: 1px;}
</style>
<script type="text/javascript" src="<%=path %>/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/jquery.form.js"></script>
	<link rel="stylesheet" href="<%=path %>/kindeditor/plugins/code/prettify.css" />
	<script charset="utf-8" src="<%=path %>/kindeditor/kindeditor.js"></script>
	<script charset="utf-8" src="<%=path %>/kindeditor/lang/zh-CN.js"></script>
	<script charset="utf-8" src="<%=path %>/kindeditor/plugins/code/prettify.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	   $(".btn").click(function(){
		   var message = document.getElementById("title");
			if(message.value==""||message.value==null)
				{
				   alert("标题不能为空");
				   return;
				}
		   editor.sync();
		   var content = $("#textarea").val();
           content =content.replace(/\+/g, "%2B");
		   content =content.replace(/\%/g, "%25");
		   var data = "frontMenuId="+$("#menuId").val()+"&importWord="+$("#importWord").val()+"&photo="+$("#wpic").val()+"&title="+$("#title").val()
		   +"&content="+content +"&synopsis="+$("#synopsis").val()+"&ip="+$("#ip").val()+"&newsId="+$("#newsId").val()+"&url="+$("#urlAdress").val();
 
		   $.ajax({
				//使用json，/json是他的命名空间
				url:"<%=path%>/json/updatenew", 
				type:"post",
				data:data,//使用这个方法可以使表单里面的内容自动提交上去
				dataType:"json",//返回类型为json对象
				
				success:function(data){
					if(data.result=="success"){
				
						$(".img").hide();
						$(".img").find("img").remove();
						$("#file").show();
						$(".showPic").hide();
						$("#npic").hide();	
						$("#wpic").val("");
						window.open("<%=path %>/back/5_list.jsp",'rightFrame');
					}
					if(data.result=="error"){
											
					}
				}
			});
			document.getElementById("Submit_btn").style.background = "gray";
            document.getElementById("Submit_btn").value="正在提交...";
            document.getElementById("Submit_btn").style.disabled=false;
			alert("文章更新成功！");
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
	   				$("#npic").hide();	
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
	   					$("file").hide();
	   					document.getElementById('c').style.marginTop ='30px';
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
	   		data:"fileName="+imgName,
	   		success:function(data){
	   			if(data.message=="yes"){
	   				$("#wpic").val("");
	   				$(".img").hide();
	   				$(".img").find("img").remove();
	   				$("#file").show();
	   				document.getElementById('c').style.marginTop ='0px';
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
	   					document.getElementById('c').style.marginTop ='0px';
	   				}
	   			}
	   		});
	     });
	   });
	
	  function setDisabled(){
			var menuId=document.getElementById("menuId");
		    var id=menuId.options[menuId.selectedIndex].value;
			if(id==46){
				document.getElementById("importWord").disabled=true;
				document.getElementById("synopsis").disabled=true;
				document.getElementById("file").disabled=false;
				document.getElementById("urlAdress").disabled=false;
				document.getElementById("textarea").disabled=true;
			
			}else{
				document.getElementById("importWord").disabled=false;
				document.getElementById("synopsis").disabled=false;
				document.getElementById("file").disabled=true;
				document.getElementById("urlAdress").disabled=true;
				document.getElementById("textarea").disabled=false;
			}
	}
</script>
</head>

<body onload="setDisabled();">

	<%-- <div class="place">
    <span>位置：</span>
    <ul class="placeul">
 <li><a href="javascript:void(0);" onclick="parent.frames.topFrame.overall();">首页</a></li>

       <li><a href="5_list.jsp" target="rightFrame">文章列表</a></li>
    </ul>
    </div> --%>
    
    <div class="formbody">
    
    <div class="formtitle"><span>文章编辑</span></div>
    
    <ul class="forminfo">
    
     <li><label id="info_title2">文章标题：</label><input name="" type="text" class="dfinput" style="width:335px;" value="${newtoupdate.title}" size="70" id="title"/> <i>*</i></li>
     <li><label id="info_title2">所属栏目：</label>
          <select class="select1" style="width:335px;" id="menuId" onchange="setDisabled();">
             	 <c:forEach var="frontmenu" items="${frontmenustonew}">
		             <option  value="${frontmenu.frontMenuId}" 
		             <c:if test="${frontmenu.frontMenuId==sessionScope.newtoupdate.frontmenu.frontMenuId}">
		             selected = "selected" 
		             </c:if>
		             >${frontmenu.name}</option>
				 </c:forEach> 
                
                </select>
               
            <i>*</i></li>
     <li><label id="info_title2">文章关键字：</label><input name="" type="text" class="dfinput" style="width:335px;"  value="${newtoupdate. importWord}" id="importWord"/> <i>SEO元素  用，隔开</i></li>
     <li><label id="info_title2">文章简介：</label><textarea name="article" style="width:332px; height:80px; border:1px solid #ccc;"id="synopsis" >${newtoupdate.synopsis}</textarea></li>
     <li><label id="info_title2">图片上传：</label> 
        <div id ="wpicture">
                    <input type="text"  id ="wpic" name ="WPicture" value ="${newtoupdate.photo}" style="display: none"/>
		            <input type="text"  id ="newsId" value ="${newtoupdate.newsId}" style="display: none"/>
		             <input type="text"  id ="ip" value ="<%=ip %>" style="display: none"/>
                        </div>
					    <form enctype="multipart/form-data"  id="uploadForm">
						<div class="showPic" style="font-size:20px;">
								<div id="filepic"><input type="file" name="myFile"  id="file" style= "height:30px;width:250px;"/>图片大小2000*450</div>
								<div id ="uploadloading" style="display: none;margin-left:60px">
									<img src ="<%=path %>/back/images/loading.gif" />正在上传请稍后......
								</div>									
								<div class="img" style="display: none;margin-left:130px">
										<a href="#" id ="delPic">删除图片</a>
								</div>
							    <div class="imgs"  
							        <s:if test="#session.newtoupdate.photo==null">
		                                style="display: none;"
		                             </s:if>
							    >
								     <img id="npic" alt="" src="<%=path %>${newtoupdate.photo} "height="135px" style="margin-left:130px"/>
								     <a href="#" id ="delPics">删除图片</a>
								</div>
						</div>
					</form>
     </li>
    <li><label id="info_title2">链接地址：</label>
    <input name="url" type="text" class="dfinput" style="width:335px;"  value="${newtoupdate.url}" size="70" id="urlAdress"/></li>

      </ul>
      <div id="c" >
        <div style="margin-left:60px">
            文章内容：
       </div>
     <div  style="margin-left:130px">
  	 <textarea id="textarea" rows="5" cols="80" style="width:700px;height:500px;">${newtoupdate.content}</textarea>
	   <div><input name="" id="Submit_btn"type="button" class="btn" value="更新"/></div>
      </div> 
 
   </div>
 
   
   <script>
   		var editor;
		KindEditor.ready(function(K) {
			editor = K.create('textarea[id="textarea"]', {
				cssPath : '<%=path %>/kindeditor/plugins/code/prettify.css',
				uploadJson : '<%=path %>/kindeditor/jsp/upload_json.jsp',
				fileManagerJson : '<%=path %>/kindeditor/jsp/file_manager_json.jsp',
				allowFileManager : true,
				allowUpload : true,
				afterCreate : function() {
					var self = this;
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
				}
			});
			prettyPrint();
		});
	</script>
    </div>


</body>

</html>
