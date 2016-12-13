<%@page import="com.jqg.pojo.Uservip"%>
<%@page import="com.jqg.service.impl.UservipServiceImpl"%>
<%@page import="com.jqg.service.UservipService"%>
<%@page import="com.jqg.service.impl.IPServiceImpl"%>
<%@page import="com.jqg.service.IPService"%>
<%@page import="com.jqg.pojo.Basicinfor"%>
<%@page import="com.jqg.service.impl.BasicinforServiceImpl"%>
<%@page import="com.jqg.service.BasicinforService"%>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
  <%@ taglib uri="/struts-tags" prefix="s"%>
  	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<%
String path = request.getContextPath();if(path!=null && !path.equals("")){	if(path.contains("/")){		if(path.equals("/")){path = "";	}}else{	path += "/";}} 
String basePath = "";if(request.getServerPort()==80){basePath = request.getScheme()+"://"+request.getServerName()+path+"/";}else{basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";}    
String id = request.getParameter("id");
BasicinforService basicinforService = new BasicinforServiceImpl();
Basicinfor basicinfor = basicinforService.findBasicinforByUserId(Integer.valueOf(id));
UservipService uservipService = new UservipServiceImpl();
Uservip user = uservipService.findUservipByUserid(Integer.valueOf(id));
IPService ipService = new IPServiceImpl();
String ip = ipService.getIpAddr(request);

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改信息</title>
<link href="<%=basePath  %>/back/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath  %>/back/css/mem.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath  %>/back/css/tab.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath %>/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/back/js/tabs.js"></script>
<script type="text/javascript" >
function btn(){
	var userpwd = document.getElementById("userpwd");
	var pwd = document.getElementById("pwd");
	var upwd = document.getElementById("upwd");
	var qpwd = document.getElementById("qpwd");
	if (userpwd.value!=pwd.value) {
		alert("密码与确认密码不一样请重新输入！");
		userpwd.focus();
		return;
	   }
	if (upwd.value!=qpwd.value) {
		alert("支付密码与确认支付密码不同，请重新输入！");
		qpwd.focus();
		return;
	   }
	
	document.getElementById("uptpwd").submit();
	document.getElementById("Submit_btn").style.background = "gray";
   	document.getElementById("Submit_btn").value="正在提交...";
    document.getElementById("Submit_btn").style.disabled=false;
	alert("修改成功！");
	}
function btn2(){
//身份证号是否存在
		$m.ajax({
  		  async:false,
  		  url:"../user/findIdNum",
  		  type:"GET",
  		  data:"idNum="+idNum,
  		  dataType:"json",
  		  success:function(data){
  		     var member=eval("("+data+")");
  		     var result=member.result;
  		     if(result=="1"){
  		    	 
  		    	$m("#numTip").html("<span style='font-size:12px;color:red'>身份证号已经被实名认证</span>");
  		  	 }
  		  	 if(result=="0"){
  		  		$m("#numTip").html("");
	  		  	var form=document.getElementById('list3');
	  			form.action="/user/certification";
	  			form.submit();
  		  	 }
  		  }
  	  });
}
</script>


<body>

	<%-- <div class="place">
    <span>位置：</span>
    <ul class="placeul">
<li><a href="javascript:void(0);" onclick="parent.frames.topFrame.overall();">首页</a></li>
    <li><a href="3_list.jsp" target="rightFrame">会员列表</a></li>
    <li><a href="#">修改会员</a></li>
    </ul>
    </div> --%>
    
    <div class="rightinfo">
    
       <div id="hotnews_caption">
            <ul id="h_menu">                  
                 <li class="current" onclick="secBoard('hotnews_caption','list',1);">修改会员密码</li>
                 <li class="normal" onclick="secBoard('hotnews_caption','list',2);">基本资料</li>

            </ul>
         </div> 
    
       <div id="hotnews_content">
       <form name="uptpwd" id="uptpwd" action="<%=path %>/overall/uptpwd" method = "post" >
        <div class="current" id="list_1">
          
           <ul class="m_forminfo">
            <li><input name="ip" type="text" class="dfinput" value="<%=ip%>" size="37" style="display: none;"/></li>
            <li><input name="userId" type="text" class="dfinput" value="<%=id %>" size="37" style="display: none;"/></li>
            <li><label class="m_info_title">内部发标人:</label>
                         <label>  <input type="radio" name="userType" value="1" id="RadioGroup1_0"  <% if(user.getUserType()!=null&&user.getUserType()==1){%> checked<% } %> />  是</label>
               <label> <input type="radio" name="userType" value="0" id="RadioGroup1_1"  <% if(user.getUserType()==null||user.getUserType()==0){%> checked<% } %> /> 否</label>
            </li>
            <li><label class="m_info_title">新密码:</label><input name="password" type="text" class="dfinput" size="37" id="userpwd"/><i>如不修改则留空</i></li>
            <li><label class="m_info_title">确认新密码:</label><input name="" type="text" class="dfinput"  size="37"id="pwd"/><i>如不修改则留空</i></li>
            <li><label class="m_info_title">新支付密码:</label><input name="paypassword" type="text" class="dfinput"  size="37"id="upwd"/><i>如不修改则留空</i></li>
            
            <li><label class="m_info_title">确认新支付密码:</label><input name="" type="text" class="dfinput"  size="37"id="qpwd"/><i>如不修改则留空</i></li>
            <li><label id="info_title1">&nbsp;</label><input type="button" name="Submit" id="Submit_btn" value="修改" class="btn" onclick="btn()"/></li>
          </ul>
         
       </div> 
    
    
         <div class="normal" id="list_2">
           <%if(basicinfor!=null){ %> 
          <ul class="m_forminfo">
          	<input type="hidden" name="basicInforId" value="<%=basicinfor.getBasicInforId() %>"/>
            <li><label class="m_info_title">会员名:</label><%=basicinfor.getUservip().getUserName() %></li>
            <li><label class="m_info_title">真实姓名：</label><input name="realName" type="text" class="dfinput" value="<%if(basicinfor.getRealName()!=null){ %><%=basicinfor.getRealName()%><%} %>" size="37"/></li>
            <li><label class="m_info_title">身份证号：</label><input name="idNum" type="text" class="dfinput" value="<%if(basicinfor.getIdNum()!=null){ %><%=basicinfor.getIdNum()%><%} %>" size="37"/></li>
            <li><label class="m_info_title">手机号码：</label><input name="phoneNum" type="text" class="dfinput" value="<%if(basicinfor.getPhoneNum()!=null){ %><%=basicinfor.getPhoneNum()%><%} %>" size="37"/></li>
            <li><label class="m_info_title">会员邮箱：</label><input name="mail" type="text" class="dfinput" value="<%=basicinfor.getUservip().getMail()%>" size="37"/></li>
<!--             <li><label class="m_info_title">地址：</label><input name="" type="text" class="dfinput" value="<%if(basicinfor.getCurrentAddress()!=null){ %><%=basicinfor.getCurrentAddress()%><%} %>" size="37"/></li> -->
<!--             <li><label class="m_info_title">年龄：</label><input name="" type="text" class="dfinput" value="<%if(basicinfor.getAge()!=null){ %><%=basicinfor.getAge()%><%} %>" size="37"/></li> -->
<!--             <li><label class="m_info_title">职业：</label><input name="" type="text" class="dfinput" value="<%if(basicinfor.getJob()!=null){ %><%=basicinfor.getJob()%><%} %>" size="37"/></li> -->
          </ul>
       <%}else{ %> 
             <h3>该用户未填写个人资料</h3>
           <%} %>
        </div>  
      </form>
   </div>
    
    
</body>

</html>
