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
<%-- <script type="text/javascript" src="<%=path %>/ckeditor/ckeditor.js"></script>   --%>
<%-- <script type="text/javascript" src="<%=path %>/ckfinder/ckfinder.js"></script> --%>
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
		
		var ue = UE.getEditor('editor');
		var arr = [];
        arr.push(UE.getEditor('editor').getContent());
        var content =arr.join("\n");
		   var data = "type="+$("#menuId").val()+"&photo="+$("#wpic").val()+"&title="+$("#title").val()+"&content="+content +"&url="+$("#urlAdress").val();
			var message = document.getElementById("title");

			if(message.value==""||message.value==null)
				{
				   alert("标题不能为空");
				   return;
				}
		   $.ajax({
				//使用json，/json是他的命名空间
				url:"<%=path%>/json/addAppPhoto", 
				type:"post",
				data:data,//使用这个方法可以使表单里面的内容自动提交上去
				dataType:"json",//返回类型为json对象
				success:function(data){
					if(data=="success"){
						window.location.href="<%=path %>/overall/findPhotos";
					}else{
						alert("保存失败");
						window.location.href="<%=path %>/back/photo_edit.jsp";
					}
				}
			});
			document.getElementById("Submit_btn").style.background = "gray";
            document.getElementById("Submit_btn").value="正在提交...";
            document.getElementById("Submit_btn").style.disabled=false;
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
							$(".img").prepend("<img width="+w+" height="+h+ " src='../images/new/"+data.imageFileName+" '/>");
							$("#filepic").hide();
							document.getElementById('c').style.marginTop ="70px";
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
						document.getElementById('c').style.marginTop ='0px';
					}
				}
			});
		});
	});
	
</script>
</head>

<body>
<input type="text"  id ="ip" value ="<%=ip %>" style="display: none"/>
    <div class="formbody">
    <div class="formtitle"><span>图片编辑</span></div>
    <ul class="forminfo">
     <li><label id="info_title2">图片标题：</label><input name=""  type="text" class="dfinput" style="width:335px;" value="" size="70" id="title"/> <i>*</i></li>
     <li><label id="info_title2">所属类型：</label>
          <select class="select1" style="width:335px;" id="menuId"  onchange="setDisabled();">
		             <option  value="0">--请选择--</option>
		             <option  value="1">桌面图片</option>
		             <option  value="2">第一张图片</option>
		             <option  value="3">第二张图片</option>
		             <option  value="4">第三张图片</option>
		             <option  value="5">第四张图片</option>
		             <option  value="6">幻灯片</option>
          </select>
            <i>*</i></li>
     <li><label id="info_title2">图片上传：</label> 
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
						</div>
     </li>
     <li><label id="info_title2">链接地址：</label><input name="url"  type="text" class="dfinput"  style="width:335px;" value="" size="70" id="urlAdress"/></li>
      </ul>
      <div id="c" >
        <div style="margin-left:60px">
            文章内容：
       </div>
     <div  style="margin-left:130px">
<!-- 	  <textarea id="textarea" rows="5" cols="80"></textarea> -->
	  <textarea id="editor" style="width:800px;height:300px;"></textarea>
	   <div><input name="" id="Submit_btn" type="button" class="btn" value="添加"/></div>
      </div> 
   </div>
    
    </div>
</body>
</html>
