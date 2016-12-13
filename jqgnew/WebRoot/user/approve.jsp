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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人中心-认证中心</title>
<link rel="icon" href="<%=path %>/image/favicon/favicon.ico" type="image/x-icon" />
<link href="<%=basePath%>/css/public.css" type="text/css" rel="stylesheet"/>
<link href="<%=basePath%>/css/user/user_public.css" type="text/css" rel="stylesheet"/>
<link href="<%=basePath%>/css/user/user_renzheng.css" type="text/css" rel="stylesheet"/>
<!--导航下拉菜单 js-->
<script type="text/javascript" src="<%=basePath%>/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/ajaxfileupload.js"></script>
<!--表单下拉列表框-->
<%-- <script type="text/javascript" src="<%=basePath%>/js/select2css.js"></script> --%>

<!--左侧折叠菜单-->


<script type="text/javascript">
var  $p=jQuery.noConflict();
	function ajaxFileUpload(mark,str)
	{
		var boo=0;
		if(mark=='0'){
			if(document.querySelector("input[id=file]").value==''){
				$p("#fileTip").html("<span style='font-size:12px;color:red'>请上传正面图片</span>");
			}else{
				boo=1;
			}
		}else{
			if(document.querySelector("input[id=file2]").value==''){
				$p("#file2Tip").html("<span style='font-size:12px;color:red'>请上传反面图片</span>");
			}else{
				boo=1;
			}
		}
		if(boo==1){
		$p.ajaxFileUpload
		(
			{
				url:'<%=path %>/user/fileUploadAction',//用于文件上传的服务器端请求地址
				secureuri:false,//一般设置为false
				fileElementId:str,//文件上传空间的id属性  <input type="file" id="file" name="file" />
				dataType: 'json',//返回值类型 一般设置为json
				success: function (data, status)  //服务器成功响应处理函数
				{
					if(mark=='0'){
						if(data.message=="你已成功上传文件"){
							$p("#fileTip").html("<span style='font-size:12px;color:green'>"+data.message+"</span>");
							$p("#file1").val("1");
						}else{
							$p("#fileTip").html("<span style='font-size:12px;color:red'>"+data.message+"</span>");
						}
					}else if(mark=='1'){
						if(data.message=="你已成功上传文件"){
							$p("#file2Tip").html("<span style='font-size:12px;color:green'>"+data.message+"</span>");
							$p("#file3").val("1");
						}else{
							$p("#file2Tip").html("<span style='font-size:12px;color:red'>"+data.message+"</span>");
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
		}
		return false;

	}
</script>
<script type="text/javascript" src="../js/login/approve.js"></script>
<SCRIPT type="text/javascript">
    var  $m=jQuery.noConflict();
    var timeout=60;
    function getCode(val){
    document.getElementById("yanzheng").setAttribute("disabled", true);
    	var phoneNum=$m('#phoneNum').val();
    	//var a = /^((\(\d{3}\))|(\d{3}\-))?13\d{9}|14[57]\d{8}|15\d{9}|18\d{9}$/;
    	var a=/^1[3|4|5|7|8][0-9]\d{4,8}$/;
//    	if(phoneNum.length!=11 || !phoneNum.match(a)){
//    		document.getElementById("number").style.display="";
//    		return false;
//    		document.getElementById("yanzheng").removeAttribute("disabled");
//    	}
		if(phoneNum.length!=11 || !phoneNum.match(a)){
		var msg = "<span style='font-size:12px;color:red'>请输入正确的手机号</span>";
        $m("#phoneTip").html(msg);
    		return false;
    	}
    	var data="mobiles="+phoneNum+"&isOpen=1&messModelId=9";
    	$m.ajax({
    		  async:false,
    		  url:"<%=path %>/user/sendMessage1",
    		  type:"GET",
    		  data:data,
    		  dataType:"json",
    		  success:function(data){
    		     if(data.result=="success"){
    		    	 $m('#phoneTip').html("验证码发送成功");
							countdown();
    		         }
    		         if(data.result=="error"){
    		         document.getElementById("yanzheng").removeAttribute("disabled");
    		         }
					}
//    		  }
    	  });
    	
    }
    
    
    
    function countdown(){
    
		if (timeout == 0)
		{
			document.getElementById("yanzheng").value = "重新发送";
		 	 document.getElementById("yanzheng").removeAttribute("disabled");
			timeout=60;
		}
		else
		{
		document.getElementById("yanzheng").value =  timeout+'s';
		document.getElementById("yanzheng").setAttribute("disabled", true);
		setTimeout('countdown()',1000);
	}
	timeout--;
}
    
    
    
    function phoneSumit(){
    	var phoneNum=$m('#phoneNum').val();
    //	var a = /^((\(\d{3}\))|(\d{3}\-))?13\d{9}|14[57]\d{8}|15\d{9}|18\d{9}$/;
    	var a=/^1[3|4|5|7|8][0-9]\d{4,8}$/;
//    	if(phoneNum.length!=11 || !phoneNum.match(a)){
//    		document.getElementById("number").style.display="";
//    		return false;
//    	}else{
//    		document.getElementById("number").style.display="none";
//    	}
		if(phoneNum.length!=11 || !phoneNum.match(a)){
		var msg = "<span style='font-size:12px;color:red'>请输入正确的手机号</span>";
        $m("#phoneTip").html(msg);
    		return false;
    	}
    	var code=$m('#code').val();
    	var data="phoneNum="+phoneNum+"&code="+code;
    	var phone=document.getElementById("phone");
    		$m.ajax({
      		  async:false,
    		  url:"<%=path %>/user/phoneCheck",
    		  type:"GET",
    		  data:data,
    		  dataType:"json",
    		  success:function(data){
    			  if(data.result=="success"){
    				  window.location.href="<%=path %>/user/create?mark=3";
    			  }
    			  if(data.result=="error"){
    				  document.getElementById("keyNone").style.display="";
    			  }
    			   if(data.result=="repeat"){
    				  alert("手机已认证过不能重复认证！");
    			  }
    			  
    		  }
    		 
    	  });
    }
     function checkPhoneNum(){
		var phoneNum = $m("#phoneNum").val();
	//	var a = /^((\(\d{3}\))|(\d{3}\-))?13\d{9}|14[57]\d{8}|15\d{9}|18\d{9}$/;
		var a=/^1[3|4|5|7|8][0-9]\d{4,8}$/;
		if(phoneNum.length!=11 || !phoneNum.match(a)){
		var msg = "<span style='font-size:12px;color:red'>请输入正确的手机号</span>";
        $m("#phoneTip").html(msg);
    		return false;
		}else{
		$m("#phoneTip").html("");//通过
            	    //手机号是否存在
	            	    $m.ajax({
	              		  async:false,
	              		  url:"<%=path %>/user/findphone",
	              		  type:"GET",
	              		  data:"phoneNum="+phoneNum,
	              		  dataType:"json",
	              		  success:function(data){
	              		     var member=eval("("+data+")");
	              		     var result=member.result;
	              		     if(result=="1"){
	              		    	 $m("#phoneTip").html("<span style='font-size:12px;color:red'>手机号已存在</span>");
	              		  	 }
	              		  	 if(result=="0"){
	              		  		 $m("#phoneTip").html("<span style='font-size:12px;color:green'>该手机号可以用</span>");
	              		  	 }
	              		  }
	              	  });
		}
    }
    
    function phonenum(str){
		var phoneNum = $m("#phoneNum").val();
		//var a = /^((\(\d{3}\))|(\d{3}\-))?13\d{9}|14[57]\d{8}|15\d{9}|18\d{9}$/;
			var a=/^1[3|4|5|7|8][0-9]\d{4,8}$/;
		if(phoneNum.length!=11 || !phoneNum.match(a)){
		var msg = "<span style='font-size:12px;color:red'>请输入正确的手机号</span>";
        $m("#phoneTip").html(msg);
    		return false;
		}else{
		$m("#phoneTip").html("");//通过
            	    //手机号是否存在
	            	    $m.ajax({
	              		  async:false,
	              		  url:"<%=basePath%>/user/findphone",
	              		  type:"GET",
	              		  data:"phoneNum="+phoneNum,
	              		  dataType:"json",
	              		  success:function(data){
	              		     var member=eval("("+data+")");
	              		     var result=member.result;
	              		     if(result=="1"){
	              		    	 $m("#phoneTip").html("<span style='font-size:12px;color:red'>手机号已存在</span>");
	              		  	 }
	              		  	 if(result=="0"){
	              		  		 $m("#phoneTip").html("<span style='font-size:12px;color:green'>该手机号可以用</span>");
	              		  		 countdown();
	              		  		 getCode(str);
	              		  	 }
	              		  }
	              	  });
		}
    }
    function getMark(){
    	var mark=document.getElementById("mark").value;
    	if(mark==3 || mark==11){
    		secBoard('hotnews_caption','list',3);
    	}
    	if(mark==4){
    		secBoard('hotnews_caption','list',4);
    	}
    	if(mark==5){
    		secBoard('hotnews_caption','list',5);
    	}
    }
    $m(function(){
    	getMark();
    });
    
    function sendMail(str){
	var path=$m('#path').val();
	var form=document.getElementById('mail');
	var email=$m('#address').val();
	if(str=='1'){//发送新邮件
		var address=$m('#address').val();
		if(address==null || address==''){
			$m('#tip').html("<span style='font-size:12px;color:red'>输入不能为空</span>");
			return false;
		}else if(!address.match(/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/)){
			$m('#tip').html("<span style='font-size:12px;color:red'>请输入正确的邮箱地址</span>");
			return false;
		}else{
			document.getElementById("sendmail").setAttribute("disabled", "disabled");
			//邮箱是否存在
            $m.ajax({
      		  async:false,
      		  url:path+"user/findmail",
      		  type:"GET",
      		  data:"mail="+email,
      		  dataType:"json",
      		  success:function(data){
      		     var member=eval("("+data+")");
      		     var result=member.result;
      		     if(result=="1"){
						alert("该邮箱已经被注册");
						document.getElementById("sendmail").removeAttribute("disabled");
      		    	 	return;
      		  	 }else{
      		  		form.action=path+"user/sendMail?key="+str;
      		  		form.submit();
      		  	 }
      		  }
      	  });
		}
	}
}
</SCRIPT>
<script type="text/javascript" src="<%=basePath%>/js/menu.js"></script>
</head>
<body> 
<!--头部开始-->
<div class="header">
    <jsp:include page="../header.jsp" />
</div>
<!--头部结束-->
<div class="maincontent">
  <input type="hidden" id="path" value="<%=basePath%>"/>
  <input type="hidden" id="mark" value="${mark}"/>
  <div class="conbox fn_cle">
    <jsp:include page="left.jsp"/>
   </div>
   
     <div class="user_r">
         <div id="hotnews_caption">
            <ul id="h_menu">                  
                 
                 <li class="current" onclick="secBoard('hotnews_caption','list',1);"><a href="#" >手机认证</a></li>
                 <li class="normal"onclick="secBoard('hotnews_caption','list',2);"><a href="#" >邮箱认证</a></li>
                 <li class="normal" onclick="secBoard('hotnews_caption','list',3);"><a href="#" >实名认证</a></li>
<!--                  <li class="normal" onclick="secBoard('hotnews_caption','list',4);"><a href="#" >现场认证</a></li> -->
<!--                  <li class="normal" onclick="secBoard('hotnews_caption','list',5);"><a href="#" >视频认证</a></li> -->
<!--                 <li class="normal" onclick="secBoard('hotnews_caption','list',6);"><a href="#" >安全问题设置</a></li>-->
            </ul>
         </div>
         
         <div id="hotnews_content">
           <div class="normal" id="list_2">
           <c:if test="${user.enable==null}">
           		<div class="xinxi"><img src="<%=basePath%>/images/youxiang_03.png" /></div>
                <div class="xinxi">
                    <form method="post" id="mail">
                    <div class="tishi">
                    	<p><img src="<%=basePath%>/images/tishi_03.png" />请注意以下事项：</p>
                        <p>1、如果收件箱里没有收到该邮件，请查看垃圾箱，以免被误判为垃圾邮件。</p>
<!--                        <p>2、如果您的邮箱长时间没有收到我们的激活邮件，请点击重新发送激活邮件：</p>-->
<!--                        <p class="fasong"><input type="button" class="submit_bt" onclick="sendMail('0');" value="重新发送激活邮件"/></p>-->
                        <p>2、请输入你的邮箱进行激活认证。</p>
                        <p class="fasong"><input type="text" id="address" name="address" value=""/>
	                        <input type="button" class="submit_bt" onclick="sendMail('1');" value="邮箱发送激活邮件" id="sendmail"/>
	                        <span id="tip"></span>
                        </p>
                        <p>3、如果您在验证过程中，出现任何问题，请联系客服。</p>
                        <p style="color: red;">4、邮箱认证完成之后，请进行实名认证！</p>
                  
                        <c:if test="${mark==1}"><p style="color: red">邮件已发送，请注意查收。</p></c:if>
                        <c:if test="${mark==12}"><p style="color: red">此邮箱已被认证过。</p></c:if>
                        <c:if test="${mark==13}"><p style="color: red">邮箱已认证过，不能重复认证。</p></c:if>
                    </div>
                    </form>
                </div></c:if>
            <c:if test="${user.enable==1}">
            	亲爱的用户，您好，您已通过邮箱认证,如需修改邮箱，请联系网站管理员！
            </c:if>
           </div>       
           <div class="current" id="list_1">
                <c:if test="${user.phoneResult!='1'}">
            	<div class="shouji"><img src="<%=basePath%>/images/shouji_03.png" /></div>
                <div class="shouji">
                	<p>您在平台的充值提现等重要操作，都需要通过手机动态验证码！</p>
                    <p>请填写您真实的手机号码，完成手机验证。</p>
                    <form method="post" id="phone">
                    <div class="sjH">
                    	<div class="M">手机号：</div>
                        <div class="M"><input style="height:30px" name="phoneNum"  id="phoneNum" type="text"  id="captcha" onblur="checkPhoneNum()"/><input style="height:30px" type="button" id="yanzheng" value="获取验证码" onclick="phonenum(this)"> <em id="number"  style="display: none;color:red;">请输入正确的手机号!</em><span id="phoneTip"></span></div>
                    </div>
                    <div class="yzM">
                    	<div>验证码：</div>
                        <div><input type="text" style="height:30px" name="code" id="code" /><em id="captchaNone"  style="display: none;color:red;"> 请输入正确的验证码!</em> <em id="keyNone"  style="display: none;color:red;"> 验证码不对，请重新输入!</em></div>
                    </div>
                    <div class="wancheng"><input type="button"  onclick="phoneSumit(this);"/></div>
                    </form>
                    <p class="zhuyi"><img src="<%=basePath%>/images/tishi_03.png" />请注意以下事项：</p>
                    <p class="zhuyi">1、根据省份、城市、地区不同，一般会在5秒-5分钟内收到验证码。</p>
                    <p class="zhuyi">2、如果您在验证过程中，出现任何问题，请联系网站客服。</p>
                </div>
                </c:if>
                <c:if test="${user.phoneResult=='1'}">恭喜你，手机认证已通过！<span style="color: red;">请完成其余两项认证！（邮箱认证和实名认证）</span></c:if>
           </div>
           
           
           <div class="normal" id="list_3">
           		<s:if test='user.nameResult==null || user.nameResult=="" || user.nameResult=="0"'>
            	<div class="shouji"><img src="<%=basePath%>/images/touxiang_03.png" /></div>
                <form method="post" id="list3" action="<%=path%>/user/certification">
                <div class="shouji">
                	<p>您必须填写您的真实姓名和身份证号码，</p>
                    <p>虚假的身份信息，是不能通过验证的。</p>
                    <div class="sjH">
                    	<div>真实姓名：</div>
                        <div><input type="text" name="realName"  id="realName"/><span id="nameTip"></span></div>
                    </div>
                    <div class="sjH">
                    	<div>身份证号：</div>
                        <div><input type="text" name="idNum" id="idNum" /><span id="numTip"><a style="color: red">
                        <s:if test='mark=="22"'>已经实名认证或已提交申请！</s:if>
                        <s:if test='mark=="11"'>身份证号已经被实名认证！</s:if></a></span></div>
                    </div>                   
                    
                    <!--  <div class="sjH">
                    	<div>身份证正面图片：</div>
                        <div><input type="file" id="file" name="file"  style="border:0;background:transparent;width:250px;"/><input type="button" value="上传" onclick="return ajaxFileUpload('0','file');" style="border:0;width:70px;color:#fff;background-color:#11a9e1"><span id="fileTip"></span>
                        	<input type="hidden" id="file1" name="file1" value="0" />
                        </div>
                    </div>
                    <div class="sjH">
                    	<div>身份证反面图片：</div>
                        <div><input type="file" id="file2" name="file2"  style="border:0;background:transparent;width:250px;" /><input type="button" value="上传" onclick="return ajaxFileUpload('1','file2');" style="border:0;width:70px;color:#fff;background-color:#11a9e1"><span id="file2Tip"></span>
                        	<input type="hidden" id="file3" name="file3" value="0" />
                        </div>
                    </div>-->             
                    <div><input type="button" class="tj_bt" value="完成实名认证" onclick="certification()"></div>
                    <p class="zhuyi"><img src="<%=basePath%>/images/tishi_03.png" />请注意以下事项：</p>
                    <p class="zhuyi">1、我们将通过全国公民身份信息中心（NCIIC）系统对您提交的身份进行审核。</p>
                    <p class="zhuyi">2、实名认证是您绑定银行卡和使用平台核心功能的必要条件,提现时的银行帐</p>
                    <p class="zhuyi">户名以您提交的认证信息为准。</p>
                    <p class="zhuyi">3、如果您在验证过程中，出现任何问题，请联系网站客服。</p>
                </div>
                </form>
<!--                 <div class="shouji"> -->
<!--                 	<div>正面：</div><div class="shangchuan"></div> -->
<!--                     <div>反面：</div><div class="shangchuan"></div> -->
<!--                 </div> -->
				</s:if>
				<s:if test='user.nameResult=="1"'>已经发出实名认证申请！</s:if>
            	<s:if test='user.nameResult=="2"'>恭喜你，实名认证已通过！</s:if>
<!--             	<s:if test='mark=="11"'>身份证号已经被实名认证！</s:if> -->
<%--            <s:if test='user.nameResult==0'>实名认证不通过！</s:if> --%>
           </div>
           
           
<!--            <div class="normal" id="list_4"> -->
<!--            		<s:if test='user.sceneResult==null || user.sceneResult==""'> -->
<!--             	<img src="<%=basePath%>/images/renzheng_03.png" /><a href="<%=path %>/user/sceneSet">确定点击申请现场认证</a> -->
<!--             	</s:if> -->
<!--             	<s:if test='user.sceneResult=="0"'>已经发出现场认证申请！</s:if> -->
<!--             	<s:if test='user.sceneResult=="1"'>恭喜你，现场认证申请通过！</s:if> -->
<!--             	<s:if test='user.sceneResult=="2"'>很抱歉，现场认证申请不通过！ -->
<!--             	<img src="<%=basePath%>/images/renzheng_03.png" /><a href="<%=path %>/user/sceneSet">点击重新申请现场认证</a></s:if> -->
<!--            </div> -->
           
           
<!--            <div class="normal" id="list_5"> -->
<!--                 <s:if test='user.videoResult==null || user.videoResult==""'> -->
<!--             	<img src="<%=basePath%>/images/renzhengS_03.png"/><a href="<%=path %>/user/videoSet">确定点击申请视频认证</a> -->
<!--             	</s:if> -->
<!--             	<s:if test='user.videoResult=="0"'>已经发出视频认证申请！</s:if> -->
<!--             	<s:if test='user.videoResult=="1"'>恭喜你，视频认证申请通过！</s:if> -->
<!--             	<s:if test='user.videoResult=="2"'>很抱歉，视频认证申请不通过！ -->
<!--             	<img src="<%=basePath%>/images/renzhengS_03.png"/><a href="<%=path %>/user/videoSet">点击重新申请视频认证</a> -->
<!--             	</s:if> -->
<!--            </div> -->
           
           
           <div class="normal" id="list_6">
                <c:if test='${user.questionresult==null}'>
            	<div class="shouji"><img src="<%=basePath%>/images/anquan_03.png" /></div>
                <div class="shouji">
                <form method="post" id="list6">
                	<div class="info">
                     	<label class="title">问题：</label>
                     	<div id="uboxstyle">                    
                     		<select name="questionIdOne" id="questionIdOne">
                    			<c:forEach var="question" items="${questions}">
                    			<option value="${question.questionId}">${question.content}</option>
                    			</c:forEach>
                    		</select>
                    	</div>
                    </div>
                    <div class="daan" style="clear:both;">答案：<input type="text" name="answerOne" id="answerOne"/><span id="oneTip"></span></div>
                	<div class="info">
                     	<label class="title">问题：</label>
                     	<div id="uboxstyle">                    
                     		<select name="questionIdTwo" id="questionIdTwo">
                    			<c:forEach var="question1" items="${questions}">
                    			<option value="${question1.questionId}">${question1.content}</option>
                    			</c:forEach>
                    		</select>
                    	</div>
                    </div>
                   <div class="daan" style="clear:both;">答案：<input type="text" name="answerTwo" id="answerTwo"/><span id="twoTip"></span><span id="error" style="color:red;">${tip}</span></div>
                   <div><input type="button" class="tj_bt" onclick="questionSet()" value="验证问题提交"></div>
                   </form>
                   <p class="zhuyi"><img src="<%=basePath%>/images/tishi_03.png" />请注意以下事项：
                   	<br />1、安全问题是您修改手机号码，变更邮箱，找回密码和修改银行账号的必备信息。
                    <br />2、如果您在验证过程中，出现任何问题，请联系网站客服。
                   </p>

                </div>
                </c:if><c:if test='${user.questionresult!=null}'>恭喜你，已经提交安全问题！</c:if>
                <div><input name="mark" id="mark" type="hidden" size="30" value="${mark}"/></div>
       
          <script type="text/javascript" src="<%=basePath%>/js/tabs.js" language="javascript"></script>
          </div>
        
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
<script>
var  $m=jQuery.noConflict();
function sendMail(str){
	var path=$m('#path').val();
	var form=document.getElementById('mail');
	var email=$m('#address').val();
	if(str=='1'){//发送新邮件
		var address=$m('#address').val();
		if(address==null || address==''){
			$m('#tip').html("<span style='font-size:12px;color:red'>输入不能为空</span>");
			return false;
		}else if(!address.match(/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/)){
			$m('#tip').html("<span style='font-size:12px;color:red'>请输入正确的邮箱地址</span>");
			return false;
		}else{
			document.getElementById("sendmail").setAttribute("disabled", "disabled");
			//邮箱是否存在
            $m.ajax({
      		  async:false,
      		  url:path+"user/findmail",
      		  type:"GET",
      		  data:"mail="+email,
      		  dataType:"json",
      		  success:function(data){
      		     var member=eval("("+data+")");
      		     var result=member.result;
	      		     if(result=="1"){
	      		    	 
							alert("该邮箱已经被注册");
							document.getElementById("sendmail").removeAttribute("disabled");
	      		    	 	return;
	      		  	 }
	      		     else{
	      		  		
	      		  		form.action=path+"user/sendMail?key="+str;
	      		  		form.submit();
	      		  	
	      		  	 }
      		  }
      	  });
		}
	}
}

</script>

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