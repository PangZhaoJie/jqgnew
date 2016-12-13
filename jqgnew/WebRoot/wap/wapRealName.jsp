<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	if (path != null && !path.equals("")) {
		if (path.contains("/")) {
			if (path.equals("/")) {
				path = "";
			}
		} else {
			path += "/";
		}
	}
	String basePath = "";
	if (request.getServerPort() == 80) {
		basePath = request.getScheme() + "://"
				+ request.getServerName() + path + "/";
	} else {
		basePath = request.getScheme() + "://"
				+ request.getServerName() + ":"
				+ request.getServerPort() + path + "/";
	}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>实名认证</title> 



</head>

<body>

<div data-role="page" data-position="fixed">

    <div data-role="header" data-position="fixed">
    <jsp:include page="header.jsp" />
	</div>
  	<div role="main" class="ui-content" >
        
       <form id="list3" method="post" action="<%=basePath%>/wap/wapCertification">
				<div data-role="fieldcontain" class="wk_loginmain wk_reg">
                	<p>
					<input type="text" id="realName"   name="realName"  class="user_back pass_back_02" placeholder="真实姓名"/>
					<span id="nameTip"></span>
					<input type="text" id="idNum"   name="idNum"  class="user_back pass_back_02" placeholder="身份证号"/>
					<span id="numTip"><a style="color: red">
                        <c:if test='mark=="22"'>已经实名认证或已提交申请！</c:if>
                        <c:if test='mark=="11"'>身份证号已经被实名认证！</c:if></a></span>
                    </p>
                     
					<input type="button" value="完成实名认证" onclick="certification()">
                       
				</div>	
			</form>
        
  	</div> 
    <div data-role="footer" data-position="fixed">  
    <jsp:include page="footer.jsp" />
  </div>
</div>    
</body>
<script type="text/javascript" src="<%=basePath%>/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/ajaxfileupload.js"></script>
<script type="text/javascript">
var  $p=jQuery.noConflict();
var  $m=jQuery.noConflict();

	function ajaxFileUpload(mark,str)
	{
		$p.ajaxFileUpload
		(
			{
				url:'<%=basePath %>/wap/wapFileUploadAction',//用于文件上传的服务器端请求地址
				secureuri:false,//一般设置为false
				fileElementId:str,//文件上传空间的id属性  <input type="file" id="file" name="file" />
				dataType: 'json',//返回值类型 一般设置为json
				success: function (data, status)  //服务器成功响应处理函数
				{
					if(mark=='0'){
						if(data=="你已成功上传文件"){
							$p("#fileTip").html("<span style='font-size:12px;color:green'>"+data+"</span>");
						}else{
							$p("#fileTip").html("<span style='font-size:12px;color:red'>"+data+"</span>");
						}
					}else if(mark=='1'){
						if(data=="你已成功上传文件"){
							$p("#file2Tip").html("<span style='font-size:12px;color:green'>"+data+"</span>");
						}else{
							$p("#file2Tip").html("<span style='font-size:12px;color:red'>"+data+"</span>");
						}
					}
					//alert(data.message);//从服务器返回的json中取出message中的数据,其中message为在struts2中定义的成员变量
				},
				error: function (data, status, e)//服务器响应失败处理函数
				{
					alert(e);
				}
			}
		)
		
		return false;

	}
	function certification(){
	var path=$m('#path').val();
	var realName=$m('#realName').val();
	var idNum=$m('#idNum').val();
	if(realName==''){
		$m('#nameTip').html("<span style='font-size:12px;color:red'>真实姓名不能为空</span>");
		return false;
	}else{
		$m("#nameTip").html("");
	}
	if(idNum==''){
		$m('#numTip').html("<span style='font-size:12px;color:red'>身份证号不能为空</span>");
		return false;
	}else if(idNum.length!=18){
		$m('#numTip').html("<span style='font-size:12px;color:red'>身份证号长度为18位</span>");
		return false;
	}
		//是否已实名制
            $m.ajax({
      		  async:true,
      		  url:"<%=basePath%>/wap/wapCertification",
      		  type:"post",
      		  data:"realName="+realName+"&idNum="+idNum,
      		  dataType:"json",
      		  success:function(data){
      		     if(data='11'){
						alert("身份证已被用");
      		    	 	return;
      		  	 }else if(data='22'){
      		  		alert("实名制已提交");
      		    	 	return;
      		  	 }else if(data='3'){
      		  	 	alert("操作成功");
      		    	 	return;
      		  	 }
      		  }
      	  });
	document.getElementById('list3').submit();
}
</script>
</html>
