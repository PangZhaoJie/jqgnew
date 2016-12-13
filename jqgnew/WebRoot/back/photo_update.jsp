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

<!-- 百度编辑器 -->
 <script type="text/javascript" charset="utf-8" src="<%=path %>/back/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path %>/back/ueditor/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="<%=path %>/back/ueditor/lang/zh-cn/zh-cn.js"></script>

<script type="text/javascript">

    //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    var ue = UE.getEditor('editor');
	

</script>

<!-- 结束 -->

<script type="text/javascript">
$(document).ready(function(){
	   $(".btn").click(function(){
		   var message = document.getElementById("title");
			if(message.value==""||message.value==null)
				{
				   alert("标题不能为空");
				   return;
				}
		   	var ue = UE.getEditor('editor');
			var arr = [];
	        arr.push(UE.getEditor('editor').getContent());
	        var content =arr.join("\n");
	        
		   var data = "type="+$("#menuId").val()+"&photo="+$("#wpic").val()+"&title="+$("#title").val()+"&content="+content +"&url="+$("#urlAdress").val()+"&appId="+$("#appId").val();
		   $.ajax({
				//使用json，/json是他的命名空间
				url:"<%=path%>/json/updatePhoto", 
				type:"post",
				data:data,//使用这个方法可以使表单里面的内容自动提交上去
				dataType:"json",//返回类型为json对象
				
				success:function(data){
					if(data=="success"){
						document.getElementById("Submit_btn").style.background = "gray";
			            document.getElementById("Submit_btn").value="正在提交...";
			            document.getElementById("Submit_btn").style.disabled=false;
						alert("文章更新成功！");
						window.location.href="<%=path %>/overall/findPhotos";
					}else{
						alert("更新失败!");
						window.location.href="<%=path %>/back/photo_update.jsp";
					}
				}
			});
			
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
</script>
</head>

<body onload="setDisabled();">
    <div class="formbody">
    <div class="formtitle"><span>图片编辑</span></div>
    <ul class="forminfo">
     <li><label id="info_title2">图片标题：</label><input name="" type="text" class="dfinput" style="width:335px;" value="${appPhoto.title}" size="70" id="title"/> <i>*</i></li>
     <li><label id="info_title2">所属类型：</label>
          <select class="select1" style="width:335px;" id="menuId"  onchange="setDisabled();">
		             <option  value="0" <c:if test="${appPhoto.type==0}">selected="selected"</c:if>>--请选择--</option>
		             <option  value="1" <c:if test="${appPhoto.type==1}">selected="selected"</c:if>>桌面图片</option>
		             <option  value="2" <c:if test="${appPhoto.type==2}">selected="selected"</c:if>>第一张图片</option>
		             <option  value="3" <c:if test="${appPhoto.type==3}">selected="selected"</c:if>>第二张图片</option>
		             <option  value="4" <c:if test="${appPhoto.type==4}">selected="selected"</c:if>>第三张图片</option>
		             <option  value="5" <c:if test="${appPhoto.type==5}">selected="selected"</c:if>>第四张图片</option>
		             <option  value="6" <c:if test="${appPhoto.type==6}">selected="selected"</c:if>>幻灯片</option>
          </select>
            <i>*</i></li>
     <li><label id="info_title2">图片上传：</label> 
        <div id ="wpicture">
                    <input type="text"  id ="wpic" name ="WPicture" value ="${appPhoto.pathImages}" style="display: none"/>
                    <input type="text"  id ="appId" value ="${appPhoto.appId}" style="display: none"/>
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
								     <img id="npic" alt="" src="<%=path %>${appPhoto.pathImages} "height="135px" style="margin-left:130px"/>
								     <a href="#" id ="delPics">删除图片</a>
								</div>
						</div>
					</form>
     </li>
    <li><label id="info_title2">链接地址：</label>
    <input name="url" type="text" class="dfinput" style="width:335px;"  value="${appPhoto.url}" size="70" id="urlAdress"/></li>

      </ul>
      <div id="c" >
        <div style="margin-left:60px">
            文章内容：
       </div>
     <div  style="margin-left:130px">
	  <textarea id="editor" style="width:800px;height:300px;">${appPhoto.content}</textarea>
	   <div><input name="" id="Submit_btn"type="button" class="btn" value="更新"/></div>
      </div> 
   </div>
     <script type="text/javascript" >
			               var editor = CKEDITOR.replace('textarea',{
			             	   height : 400,
				           		width : 800,
			            	   filebrowserBrowseUrl :  '<%=path %>/ckfinder/ckfinder.html',
			            	      filebrowserImageBrowseUrl :  '<%=path %>/ckfinder/ckfinder.html?Type=Images',
			            	      filebrowserFlashBrowseUrl :  '<%=path %>/ckfinder/ckfinder.html?Type=Flash',
			            	      filebrowserUploadUrl :  '<%=path %>/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files',
			            	      filebrowserImageUploadUrl  :  '<%=path %>/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images',
			            	      filebrowserFlashUploadUrl  :  '<%=path %>/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash'
				            });
	</script>
    </div>
</body>
</html>
