<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page import="com.jqg.pojo.Phone"%>
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
<title>短信通知接口管理</title>
<link href="<%=basePath%>/back/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>/back/css/mem.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>/back/css/tab.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>/back/js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>/back/js/Tabs.js"></script>
<!--折叠js-->
<script type="text/javascript"> 
function btn1(){

	var name = document.getElementById("name1");
	 if(name.value==null||name.value=="")
	 {
	    alert("账号不能为空！");
	    return;
	 }
	var pwd = document.getElementById("pwd1");
	if(pwd.value==null||pwd.value=="")
	{
		    alert("密码不能为空！");
		    return;
	 }
	document.getElementById("uptPhone1").submit();
	}
function btn2(){

	var name = document.getElementById("name2");
	 if(name.value==null||name.value=="")
	 {
	    alert("账号不能为空！");
	    return;
	 }
	var pwd = document.getElementById("pwd2");
	if(pwd.value==null||pwd.value=="")
	{
		    alert("密码不能为空！");
		    return;
	 }
	document.getElementById("uptPhone2").submit();
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
 
</script>
<!--日历插件 js-->
<script type="text/javascript" src="<%=basePath%>/back/laydate/laydate.js"></script>
<script type="text/javascript">
function secBoard(elementID,listName,n) {
	 var elem = document.getElementById(elementID);
	 var elemlist = elem.getElementsByTagName("li");
	 for (var i=0; i<elemlist.length; i++) {
	  elemlist[i].className = "normal";
	  var m = i+1;
	  document.getElementById(listName+"_"+m).className = "normal";
	 }
	  elemlist[n-1].className = "current";
	  document.getElementById(listName+"_"+n).className = "current";
	}
function tabLoad(mark){
	if(mark=='2'){
		secBoard('hotnews_caption','list',2);
	}
	var phoneId2 = document.getElementById("phoneId2").value;
	if(phoneId2==1){
		document.getElementById('1').style.display='';
		document.getElementById('2').style.display='none';
	}
	if(phoneId2==2){
		document.getElementById('2').style.display='';
		document.getElementById('1').style.display='none';
	}
}
</script>
<style>
.m_forminfo li{ width:100%;margin:6px 0;  line-height:34px; border-bottom:1px dashed #ddd; padding-bottom:10px;}
.m_forminfo li p{display:block;  }
#list_2 .list_tip { border: 1px dashed #b7cfe4;float: left; margin: 8px 0; padding: 10px 0; background: #e7f2fb;}
#list_2 .list_tip ul {margin: 0; padding: 0;}
#list_2 .list_tip ul li {float: left; line-height:25px; padding-left:35px; width:95%; }
.m_info_title1{ width:200px;line-height:34px; display:block; float:left; text-align:right; margin-right:10px;}

</style>

</head>


<body onload="tabLoad(${mark});">

	<%-- <div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="javascript:void(0);" onclick="parent.frames.topFrame.overall();">首页</a></li>
    <li><a href="#" onclick="parent.frames[1].location.href='<%=path %>/back/9_left.jsp';  parent.frames[2].location.href='<%=path %>/overall/findlssh'; ">扩展管理</a></li>
    <li><a href="#">短信通知接口管理</a></li>
    </ul>
    </div> --%>
    
    <div class="rightinfo">
    
        <div id="hotnews_caption">
            <ul id="h_menu">                  
                 <li class="current" onclick="secBoard('hotnews_caption','list',1);">邮箱信息</li>
                 <li class="normal" onclick="secBoard('hotnews_caption','list',2);">手机参数</li>
            </ul>
         </div> 
    
       <div id="hotnews_content">
          <div class="current" id="list_1">
          <form action="<%=path %>/extension/saveSmtp" method="post">
           <ul class="m_forminfo">
            
            <li><label class="m_info_title">SMTP用户名：</label>  
                <input name="smtp.smtpemail" type="text" class="dfinput" value="${smtp.smtpemail}" size="39"/>
                <i>%</i>
            </li>
             <li><label class="m_info_title">SMTP密码：</label>  
                <input name="smtp.smtppassword" type="text" class="dfinput" value="${smtp.smtppassword}" size="39"/>
                <i>*</i>
            </li>
             <li><label class="m_info_title">SMTP服务器：</label>  
                <input name="smtp.smtpsever" type="text" class="dfinput" value="${smtp.smtpsever}" size="39"/>
                <i>*</i>
            </li>
           
            <li><label class="m_info_title">&nbsp;</label>
            <c:if test="${sessionScope.powerss[83]==1 }">
            <input name="" type="submit" class="btn" value="确定"/>
            </c:if>
            </li>
          </ul>
          <input type="hidden" name="smtp.smtpid" value="${smtp.smtpid}"/>
          </form>
    
       </div> 
       
      <div class="normal" id="list_2">
         
         <div class="list_tip">
            <ul>  
              <li>2、漫道短信官网访问地址：http://www.zucp.net/  </li>
              <li>3、当您需要短信充值时，可直接去相应的短信提供商官网开通账户并充值！</li>
            </ul>
         </div>

         <ul class="m_forminfo">
             <li><label class="m_info_title1">短信提供商：</label> 
                <label class="red">${phone.phoneCategory}</label>
             </li>
           
             <li><label class="m_info_title1">请选择短信提供商：</label> 
                <input  type="hidden" value="${phone.phoneId}" id="phoneId2"/>
                  <label> <input type="radio" name="RadioGroup1" value="1"  style=" margin:0 3px 0 15px;"      onclick="document.getElementById('1').style.display='';document.getElementById('2').style.display='none';"
                  <c:if test="${phone.phoneId==1}">
		             checked="checked"
               
		             </c:if>
                  />漫道短信提供商 </label>
                  <label> <input type="radio" name="RadioGroup1" value="2" style=" margin:0 3px 0 15px;"   onclick="document.getElementById('2').style.display='';document.getElementById('1').style.display='none';"
                   <c:if test="${phone.phoneId==2}">
		             checked="checked"
                  
		             </c:if>
                  />沃动短信提供商  </label>
              
            </li>
  
       	    <form id="uptPhone1" action="<%=path %>/extension/uptPhone" method = "post" >
       	    <input type="text"  id ="ip" value ="<%=ip %>" style="display: none"/>
       	            <input name="phoneId" type="text" class="dfinput" value="1" style="display: none;" />
		   
			
			<div id="1" <c:if test="${phoneopen.phoneId!=1}"> style="display: none;" </c:if> >
            <li><label class="m_info_title1">当前剩余短信条数：</label> 
                <label id="num">${num1}条</label>
             </li>
            <li><label class="m_info_title1">账号：</label>  
      
                <input name="phonename" type="text" class="dfinput" value="${phone1.phoneName}" size="39" id="name1"/>
                <i>*</i>
            </li>
            <li><label class="m_info_title1">密码：</label>  
                <input name="phonepassword" type="password" class="dfinput" value="${phone1.phonePassword}" size="39"id="pwd1"/>
                <i>*</i>
            </li>
              <li><label class="m_info_title1">&nbsp;</label>
              <c:if test="${sessionScope.powerss[83]==1 }">
              <input name="" type="button" class="btn" value="确定" onclick="btn1()"/>
              </c:if>
              </li>
            </div>
           
              </form>
                <form id="uptPhone2" action="<%=path %>/extension/uptPhone" method = "post" >
                <input type="text"  id ="ip" value ="<%=ip %>" style="display: none"/>
                  <input name="phoneId" type="text" class="dfinput" value="2 "style="display: none;" />
                  
                  
          
          <div id="2" <c:if test="${phoneopen.phoneId!=2}"> style="display: none;" </c:if> > 
            <%-- <li><label class="m_info_title1">当前剩余短信条数：</label> 
                <label id="num">${num2}条</label>
             </li> --%>
         
            <li><label class="m_info_title1">用户ID：</label>  
              
                <input name="phoneuserId" type="text" class="dfinput" value="${phone2.phoneuserId}" size="39"id="name2" />
                <i>*</i>
            </li>
            <li><label class="m_info_title1">用户名：</label>  
              
                <input name="phonename" type="text" class="dfinput" value="${phone2.phoneName}" size="39"id="name2" />
                <i>*</i>
            </li>
            <li><label class="m_info_title1">密码：</label>  
                <input name="phonepassword" type="password" class="dfinput" value="${phone2.phonePassword}" size="39" id="pwd2"/>
                <i>*</i>
            </li>
              <li><label class="m_info_title1">&nbsp;</label>
               <c:if test="${sessionScope.powerss[83]==1 }">
              	<input name="" type="button" class="btn" value="确定" onclick="btn2()"/>
              </c:if>
              </li>
        
            </div>
             </form>
          </ul>
          
      </div> 
      
	  </div> 
 
      </div>
    
   
</body>

</html>
