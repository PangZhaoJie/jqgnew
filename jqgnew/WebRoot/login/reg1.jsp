<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.sql.*" errorPage=""%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page import="java.util.List"%>
<%@page import="com.jqg.pojo.Manager"%>
<%@page import="com.jqg.service.ManagerService"%>
<%@page import="com.jqg.service.impl.ManagerServiceImpl"%>
<%
String path = request.getContextPath(); 
if(path!=null && !path.equals("")){	if(path.contains("/")){		if(path.equals("/")){path = "";	}}else{	path += "/";}} 
String basePath = "";if(request.getServerPort()==80){basePath = request.getScheme()+"://"+request.getServerName()+path+"/";}else{basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";}  
ManagerService managerService = new ManagerServiceImpl();
List<Manager> managers =managerService.findManagerBySql("select m.* from Manager m where m.isBan=1 and m.isCustomer=1 and m.display=1");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="icon" href="<%=path %>/image/favicon/favicon.ico" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册页面</title>
<link href="<%=path%>/css/public.css" type="text/css" rel="stylesheet"/>
<!-- <link href="<%=path%>/css/common.css" type="text/css" rel="stylesheet"/> -->
<link href="<%=path%>/css/reg.css" type="text/css" rel="stylesheet"/>
<!--导航下拉菜单 js-->
<script type="text/javascript" src="<%=path%>/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/login/register.js"></script>

<!-- <script type="text/javascript" src="<%=path%>/js/scroll.js"></script> -->
<!--经过小图显示大图-->
<!-- <script type="text/javascript" src="<%=path%>/js/main.js" ></script> -->


<%
 String userId=request.getParameter("long");
%>
</head>

<body>

<jsp:include page="../header.jsp" />

<!--注册开始-->
<div class="regmain">
	<div class="regbox">
    	<div class="reg_title"><h1>账户注册</h1></div>
      	<div class="reg_main">
      		<h2><img src="<%=path%>/images/reg1_03.jpg"  width="732" height="52" /></h2>
            <div class="pic_title">
            	<ul>
                	<li class="pic_1">填写注册信息</li>
                    <li class="pic_2">验证注册信息</li>
                    <li class="pic_3">注册成功</li>
                </ul>
                <div class="clear"></div>
            </div>
          <input id="basePathId" value="<%=path%>" type="hidden"/>
          <form name="form" id="register" method="post">
			<ul>
            	<li class="reg_l">用户名：</li>
                <li class="reg_r"><input name="userName" id="userName" type="text" class="reg_text" value=""" /><em>*</em><span id="aNum">请填写手机号</span><div></div></li>
                <li class="tips" id="user1" style="display:none;">用户名不能为空！</li>
                <li class="tips" id="user2" style="display:none;">请输入合法手机号！</li>
                <li class="tips" id="user3" style="display:none;">用户名已存在！</li>
                <li class="tips" id="user4" style="display:none;color:green;">用户名可以注册！</li>
            </ul>
            <div class="clear"></div>
            <ul>
            	<li class="reg_l">密码：</li>
                <li class="reg_r"><input name="password" id="password" type="password" class="reg_text" /></li>
                <li class="tips" id="pwd1" style="display:none;">密码不能为空！</li>
                <li class="tips" id="pwd2"style="display:none;">密码长度必须为6-16位！</li>
                <li class="tips" id="pwd3" style="display:none;color:green;">密码合法！</li>
            </ul>
            <div class="clear"></div>
            <ul>
            	<li class="reg_l">确认密码：</li>
                <li class="reg_r"><input name="confirmpwd" id="confirmpwd" type="password" class="reg_text" /></li>
                <li class="tips" id="cpwd1" style="display:none;">确认密码不能为空！</li>
                <li class="tips" id="cpwd2" style="display:none;">两次输入密码不一致！</li>
                <li class="tips" id="cpwd3" style="display:none;color:green;">确认密码合法！</li>
            </ul>
		
			    	<input type="hidden" name="kefuId" id="kefuser" value=""/>
						<strong class="blur" >绑定客服：</strong>
						<div class="img-scroll">
							
							<!--  span class="prev"><img src="../images/lizi_img011.jpg" /></span>
							<span class="next"><img src="../images/lizi_img012.jpg" /></span-->
							<div class="img-list" id="reg_photo">
								<ul >
								
	                               <%
	                              	for(Manager man:managers){ %>
	                              	  <li id="<%= man.getManagerId()%>" >
	                    	      		<img src="<%=path%><%=man.getPathMages()%>"  />
	                    	      		<span><%=man.getManagerName()%></span>
	                    	         </li>
	                              <% 	}
	                              %>
	                              </ul>
	                         </div>
                         </div>
            <div class="clear"></div>
            <ul style="clear:both;margin:90px 90px 0px 262px; ">
            	<li class="reg_l">验证码：</li>
                <li style="width:115px;"><input name="code" id="yanzhengma" type="text" class="reg_text width100" /></li>
                <li>
                <a href="javascript:changeimg()"><img id="code" src="<%=path %>/login/makeCode.jsp" style="height:26px;"></a>
<!--                 <input type="text" onclick="createCode()" readonly="readonly" id="checkCode" class="unchanged" style="color:#FFFFFF;border:1px solid #DDD;text-align:center;cursor:pointer;font-size:20px;width: 110px;height:26px;background-color:#921d23" /> -->
                </li>
            	<li class="tips" id="yzm1" style="display:none;margin-left:15px;">验证码不能为空！</li>
            	<li class="tips" id="yzm2" style="display:none;margin-left:15px;">验证码不合法！</li>
            	<li class="tips" id="yzm3" style="display:none;color:green;margin-left:15px;">验证码输入合法！</li>
            	<c:if test="${!empty session.error}">
                    <li class="tips" id="error" style="margin-left:15px;">${session.error}</li>
                </c:if>
            </ul>
            <div class="clear"></div>
            <ul>
            	<li class="reg_l">&nbsp;</li>
                <li><input name="" type="checkbox" id="checkbox" checked="checked" value="" />阅读并同意<a href="<%=path %>/news/new?flag=provision">《服务条款》</a></li>
                <li class="tips" id="xy" style="display:none;margin-left:52px;margin-top:-3px;">请同意协议！</li>
            </ul>
            <div class="clear"></div>
            <ul>
            	<li class="reg_l">&nbsp;</li>
                <li>
                <input name="" class="reg_btn" value="下一步" type="button" onclick="tijiao();" id="item"/>
                <% if(userId!=null){ %><input name="referId" type="hidden"  value="<%=userId %>" /><% } %>
                </li>
            </ul>
            </form>
            <div class="clear"></div>
            <ul>
            	<li class="reg_l">&nbsp;</li>
                <li>已有账号？<a href="login.jsp">立即登录</a></li>
            </ul>
            <div class="clear"></div>
   	  </div>
  </div>
</div>
<!--注册结束-->
<!--footer 开始-->

<div class="footer">
<jsp:include page="../footer.jsp" />
</div>
<script type="text/javascript">
function changeimg(){
	var myimg = document.getElementById("code"); 
	now = new Date(); 
	myimg.src="makeCode.jsp?code="+now.getTime();
} 
changeimg();

function scrollClick(wraper,prev,next,img,speed,or)
     {
	      var wraper = jQuery(wraper);
	      var prev = jQuery(prev);
	      var next = jQuery(next);
	      var img = jQuery(img).find('ul');
	      var w = img.find('li').outerWidth(true);
	      var s = speed;
      next.click(function()
           {
            img.animate({'margin-left':-w},function()
                      {
                       img.find('li').eq(0).appendTo(img);
                       img.css({'margin-left':0});
                       });
            });
      prev.click(function()
           {
            img.find('li:last').prependTo(img);
            img.css({'margin-left':-w});
            img.animate({'margin-left':0});
            });
      if (or == true)
      {
       ad = setInterval(function() { next.click();},s*1000);
       wraper.hover(function(){clearInterval(ad);},function(){ad = setInterval(function() { next.click();},s*1000);});

      }
     }
//scrollClick('.img-scroll','.prev','.next','.img-list',3,false);

function selectServer()
			{
				var Oreg_photo=document.getElementById('reg_photo').getElementsByTagName('li');
				for(var i=0;i<Oreg_photo.length;i++)
				{
					//Oreg_photo[0].className='bor_red';
					 
				//	document.getElementById('kefuser').value=(Oreg_photo[0].id);
					Oreg_photo[i].onclick=function()
					{
						
						for(var i=0;i<Oreg_photo.length;i++)
						{
							
							Oreg_photo[i].className='';
							this.className='bor_red';
							document.getElementById('kefuser').value=(this.id);
						}

					}
				}
				
			}

selectServer();
</script>
<!--footer 结束-->
</body>
</html>
