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
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>邮箱认证</title> 
<script type="text/javascript" src="<%=basePath%>/js/jquery-1.8.2.min.js"></script>
</head>
<body>
<div data-role="page" data-position="fixed">
    <div data-role="header" data-position="fixed">
    <jsp:include page="header.jsp" />
	</div>
    <div role="main" class="ui-content" > 
       <form id="mail" method="post">
				<div data-role="fieldcontain" class="wk_loginmain wk_reg">
                	<p>
					<input type="text" id="address"   name="address"  class="user_back pass_back_02" placeholder="邮箱地址"/>
                    </p>           
					<input type="button" onclick="sendMail('1');"  value="邮箱发送激活邮件" id="sendmail" />
                     <span id="tip"></span>
				</div>	
		</form>
  	</div>         
    <div data-role="footer" data-position="fixed">  
    <jsp:include page="footer.jsp" />
  </div>
</div>    
</body>
<script type="text/javascript">
  /////////////////////////////////////2015/11/21  
    var  $m=jQuery.noConflict();
    function sendMail(str){
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
      		  async:true,
      		  url:"<%=path %>/wap/findmail",
      		  type:"GET",
      		  data:"mail="+address,
      		  dataType:"json",
      		  success:function(data){
      		     if(data=="1"){
						alert("该邮箱已经被注册");
						document.getElementById("sendmail").removeAttribute("disabled");
      		    	 	return;
      		  	 }else{
      		  	    alert("发送邮件");
      		  		form.action="<%=path %>/wap/wapSendMail?key="+str;
      		  		form.submit();
      		  	 }
      		  }
      	  });
		}
	}
}
</script>


</html>
