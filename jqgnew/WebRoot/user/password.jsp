<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@taglib prefix="s" uri="/struts-tags" %>
<% 
String path = request.getContextPath();if(path!=null && !path.equals("")){	if(path.contains("/")){		if(path.equals("/")){path = "";	}}else{	path += "/";}} 
String basePath = "";if(request.getServerPort()==80){basePath = request.getScheme()+"://"+request.getServerName()+path+"/";}else{basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";}    
String picUrl = request.getParameter("Picurl"); 
String  step = request.getParameter("step"); 
String defaultPic ="image/man.GIF";
if("3".equals(step))
  defaultPic = "User/UserHeadImage/"+picUrl;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="renderer" content="ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link rel="icon" href="<%=path %>/image/favicon/favicon.ico" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>个人中心-头像与密码</title>
<link href="<%=basePath%>/css/public.css" type="text/css" rel="stylesheet"/>
<link href="<%=basePath%>/css/user/user_public.css" type="text/css" rel="stylesheet"/>
<link href="<%=basePath%>/css/user/user_password.css" type="text/css" rel="stylesheet"/>
<!--导航下拉菜单 js-->

<link href="<%=path %>/css/main.css" type="text/css" rel="Stylesheet" />
    <script type="text/javascript" src="<%=path %>/js/jquery1.2.6.pack.js"></script>
    <script  type="text/javascript" src="<%=path %>/js/ui.core.packed.js" ></script>
    <script type="text/javascript" src="<%=path %>/js/ui.draggable.packed.js" ></script>
    <script type="text/javascript" src="<%=path %>/js/CutPic.js"></script>
    <script type="text/javascript">
    var  $pp=jQuery.noConflict();
        function Step1() {
            $pp("#Step2Container").hide();           
        }

        function Step2() {
            $pp("#CurruntPicContainer").hide();
        }
        function Step3() {
            $pp("#Step2Container").hide();          
       }
    </script>


<!--表单下拉列表框-->
<%--<script type="text/javascript" src="<%=basePath%>/js/select2css.js"></script>--%>
<script type="text/javascript" src="<%=basePath%>/js/login/password.js"></script>
<!--左侧折叠菜单-->
<script type="text/javascript" src="<%=basePath%>/js/menu.js"></script>
<%
 String mark=request.getParameter("mark");
%>
</head>
<body> 
<!--头部开始-->
<div class="header">
    <jsp:include page="../header.jsp" />
</div>
<!--头部结束-->
<div class="maincontent">
  <input type="hidden" id="path" value="<%=basePath%>"/>
   <div class="conbox fn_cle">
    <jsp:include page="left.jsp"/>
   </div>
   
     <div class="user_r">
         <div id="hotnews_caption">
            <ul id="h_menu">                  
                 <li class="current" onclick="secBoard('hotnews_caption','list',1);"><a href="#" >我的头像</a></li>
                 <li class="normal"  onclick="secBoard('hotnews_caption','list',2);"><a href="#" >修改登录密码</a></li>
                 <li class="normal"  onclick="secBoard('hotnews_caption','list',3);"><a href="#" >修改支付密码</a></li>
                 
            </ul>
         </div>
         
         <div id="hotnews_content">
            <div class="current" id="list_1">
              <P>您可以通过修改用户头像来展示自己 。</P>
              <div class="p_tip">
                 <ul>
                     <li>1、用户头像能直观的向其他用户展示自己，推荐使用真实照片以作为头像，也可以使用系统推荐头像。</li>
                     <li>2、使用上传图片时请不要小于200 * 200 像素，不要大于512KB。</li>
                 </ul>
               </div>
               <div class="upload_wapper">
         
           
           
             <jsp:include page="1.jsp" />
           
           
           
           
              
        <!--    	  <div class="showhead">
            	  <c:if test="${session.uservip.photoOne=='' || session.uservip.photoOne==null}" >
            	    <img src="<%=basePath%>/images/yh_tx.jpg"> 
            	  </c:if>
            	  <c:if test="${session.uservip.photoOne!='' && session.uservip.photoOne!=null}" ><img src="<%=basePath%><s:property value='#session.uservip.photoOne'/>"> </c:if>
            	  <span>当前头像</span></div>
				  <div class="upload">
                 

               <form id="ulform" action="<%=path %>/user/uploadPic" enctype="multipart/form-data" method="post">  
    					<input type="file" name="pic" id="file" value="选择图片" />  
    					<input type="submit" value="点击上传" />  
			   </form>  
                  </div>
                  
                  -->
                  
                  
              </div>
               
               
               
               
               
           </div>
         
           <div class="normal" id="list_2">
                
              <P>您可以通过经常性修改密码更好的保护您的账号安全，以避免您受到意外损失。</P>
              <div class="p_tip">
                 <ul>
                     <li>1、经常性修改密码能有效的保护您的帐号安全</li>
                     <li>2、涉及到您的资金安全，请勿设置简单的密码，不要设置和其它网站相同的密码</li>
                 </ul>
               </div>
               
               <table width="845" cellspacing="0" cellpadding="0" class="pass_table"  >
                            <form method="post" id="pwd1">
                            <tbody>
                            <tr>
                                <th align="left" colspan="2" class="th1">修改密码</th>
                            </tr>
                            <tr>
                                <td class="td1" width="150">用户名：</td>
                                <td class="td2" width="695"><s:property value="#session.uservip.userName"/></td>
                                
                            </tr>
                            <tr>
                                <td class="td1">旧密码：</td>
                                <td><input name="password" id="password" type="password" size="28" /><span id="pwdTip"></span></td>
                                
                            </tr>
                            <tr>
                                <td class="td1">设置新密码：</td>
                                <td><input name="newpwd" id="newpwd" onblur="if (value ==''){value='请设置新密码'}" onfocus="if (value =='请设置新密码'){value =''}" value="" type="password" size="28" /><span id="npwdTip"></span></td>
                                
                            </tr>
                            <tr>
                               
                                <td class="td1">确认新密码：</td>
                                <td><input name="conpwd" id="conpwd" onblur="if (value ==''){value='请确认新密码'}" onfocus="if (value =='请确认新密码'){value =''}" value="" type="password" size="28" /><span id="cpwdTip"></span><span id="error"></span></td>
                               
                            </tr>
                            <tr height="60">
                                <td colspan="2" align="center" ><div id="submit" > <input type="button" class="submit_bt" onclick="checkpwd();" value="提&nbsp;&nbsp;交" /></div>
                            </tr>
                            
                      </tbody></form></table>
                
           </div>
           <div class="normal" id="list_3">
           
              <P>您可以通过经常性修改支付密码更好的保护您的账号安全，以避免您受到意外损失 。</P>
              <div class="p_tip">
                 <ul>
                     <li>1、经常性修改密码能有效的保护您的帐号安全</li>
                     <li>2、涉及到您的资金安全，请勿设置简单的密码，不要设置和其它网站相同的密码</li>
                 </ul>
               </div>
               <table width="845" cellspacing="0" cellpadding="0" class="pass_table"  >
               			<form method="post" id="pwd2">
                            <tbody>
                            <tr>
                                <th align="left" colspan="2" class="th1">修改支付密码</th>
                            </tr>
                            <tr>
                                <td class="td1" width="150">用户名：</td>
                                <td class="td2" width="695"><s:property value="#session.uservip.userName"/>  </td>
                                
                            </tr>
                            <tr>
                                <td class="td1">旧密码：</td>
                                <td><input name="payPwd" id="payPwd" type="password" size="28" /><label>(如未修改过，则与原登陆密码相同)</label><span id="paypwdTip"></span></td>
                                
                            </tr>
                            <tr>
                                <td class="td1">设置新密码：</td>
                                <td><input name="newPayPwd" id="newPayPwd" onblur="if (value ==''){value='请设置新密码'}" onfocus="if (value =='请设置新密码'){value =''}" value="" type="password" size="28" /><span id="npaypwdTip"></span></td>
                                
                            </tr>
                            <tr>
                               
                                <td class="td1">确认新密码：</td>
                                <td><input name="conPayPwd" id="conPayPwd" onblur="if (value ==''){value='再输入一次您的新支付密码'}" onfocus="if (value =='再输入一次您的新支付密码'){value =''}" value="" type="password" size="28" /><span id="cpaypwdTip"></span><span id="error2"></span></td>
                               
                            </tr>
                            <tr height="60">
                                <td colspan="2" align="center" ><div id="submit" > <input type="button" class="submit_bt" onclick="checkpwd2();" value="提&nbsp;&nbsp;交" /></div></th>
                            </tr>
                            
                      </tbody></form></table>
                
           </div>
           <div>
           	<input name="mark1" id="mark1" type="hidden" value=<%=mark%>>
            <input name="mark" id="mark" type="hidden" value=${mark} >
		   </div>
           <div><input name="resultString" id="resultString" type="hidden" value=${resultString} /></div>
           <div><input name="resultPay" id="resultPay" type="hidden" value=${resultPay} /></div>
           <!--<div class="normal" id="list_4">
                <P> 请勾选您所需要设置的消息提醒.</P>
                <table width="100%" cellspacing="0" cellpadding="0" class="mess_table"  >
                            <tbody><tr>
                                <th align="left" colspan="4" class="th1">基本设置：</th>
                            </tr>
                            <tr>
                                <td  class="td1">修改密码</td>
                                <td><input type="checkbox" name="checkbox" value="" style="margin-left:10px;" /></td>
                                <td><input type="checkbox" name="checkbox" value="" style="margin-left:10px;" /></td>
                                <td><input type="checkbox" name="checkbox" value="" style="margin-left:10px;" /></td>
                            </tr>
                            <tr>
                                <td  class="td1">修改银行帐号</td>
                                <td><input type="checkbox" name="checkbox" value="" style="margin-left:10px;" /></td>
                                <td><input type="checkbox" name="checkbox" value="" style="margin-left:10px;" /></td>
                                <td><input type="checkbox" name="checkbox" value="" style="margin-left:10px;" /></td>
                            </tr>
                            <tr>
                                <td  class="td1">资金提现</td>
                                <td><input type="checkbox" name="checkbox" value="" style="margin-left:10px;" /></td>
                                <td><input type="checkbox" name="checkbox" value="" style="margin-left:10px;" /></td>
                                <td><input type="checkbox" name="checkbox" value="" style="margin-left:10px;" /></td>
                            </tr>
                            <tr>
                                <th align="left" colspan="4" class="th1">借款相关：</th>
                            </tr>
                            <tr>
                                <td  class="td1">借款标发标成功</td>
                                <td><input type="checkbox" name="checkbox" value="" style="margin-left:10px;" /></td>
                                <td><input type="checkbox" name="checkbox" value="" style="margin-left:10px;" /></td>
                                <td><input type="checkbox" name="checkbox" value="" style="margin-left:10px;" /></td>
                            </tr>
                            <tr>
                                <td  class="td1">借款标满标</td>
                                <td><input type="checkbox" name="checkbox" value="" style="margin-left:10px;" /></td>
                                <td><input type="checkbox" name="checkbox" value="" style="margin-left:10px;" /></td>
                                <td><input type="checkbox" name="checkbox" value="" style="margin-left:10px;" /></td>
                            </tr>
                            <tr>
                                <td  class="td1">借款标流标</td>
                                <td><input type="checkbox" name="checkbox" value="" style="margin-left:10px;" /></td>
                                <td><input type="checkbox" name="checkbox" value="" style="margin-left:10px;" /></td>
                                <td><input type="checkbox" name="checkbox" value="" style="margin-left:10px;" /></td>
                            </tr>
                            <tr>
                                <th align="left" colspan="4" class="th1">投资相关：</th>
                            </tr>
                            <tr>
                                <td  class="td1">自动投标借出完成</td>
                                <td><input type="checkbox" name="checkbox" value="" style="margin-left:10px;" /></td>
                                <td><input type="checkbox" name="checkbox" value="" style="margin-left:10px;" /></td>
                                <td><input type="checkbox" name="checkbox" value="" style="margin-left:10px;" /></td>
                            </tr>
                            <tr>
                                <td  class="td1">借出成功</td>
                                <td><input type="checkbox" name="checkbox" value="" style="margin-left:10px;" /></td>
                                <td><input type="checkbox" name="checkbox" value="" style="margin-left:10px;" /></td>
                                <td><input type="checkbox" name="checkbox" value="" style="margin-left:10px;" /></td>
                            </tr>
                            <tr>
                                <td  class="td1">借出流标</td>
                                <td><input type="checkbox" name="checkbox" value="" style="margin-left:10px;" /></td>
                                <td><input type="checkbox" name="checkbox" value="" style="margin-left:10px;" /></td>
                                <td><input type="checkbox" name="checkbox" value="" style="margin-left:10px;" /></td>
                            </tr>
                            <tr>
                                <td  class="td1">收到还款</td>
                                <td><input type="checkbox" name="checkbox" value="" style="margin-left:10px;" /></td>
                                <td><input type="checkbox" name="checkbox" value="" style="margin-left:10px;" /></td>
                                <td><input type="checkbox" name="checkbox" value="" style="margin-left:10px;" /></td>
                            </tr>
                            <tr>
                                <td  class="td1">代为偿还</td>
                                <td><input type="checkbox" name="checkbox" value="" style="margin-left:10px;" /></td>
                                <td><input type="checkbox" name="checkbox" value="" style="margin-left:10px;" /></td>
                                <td><input type="checkbox" name="checkbox" value="" style="margin-left:10px;" /></td>
                            </tr>
                      </tbody></table>
           </div>-->
           
          <script type="text/javascript" src="<%=basePath%>/js/tabs.js" language="javascript"></script>
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