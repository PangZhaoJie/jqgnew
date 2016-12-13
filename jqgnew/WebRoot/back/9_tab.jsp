<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();if(path!=null && !path.equals("")){	if(path.contains("/")){		if(path.equals("/")){path = "";	}}else{	path += "/";}} 
String basePath = "";if(request.getServerPort()==80){basePath = request.getScheme()+"://"+request.getServerName()+path+"/";}else{basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";}    
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>短信通知模块管理</title>
<link href="<%=basePath%>/back/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>/back/css/mem.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>/back/css/tab.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>/back/js/jquery.js"></script>
<!--折叠js-->
<script type="text/javascript"> 

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
</script>
<script type="text/javascript">
function tabLoad(mark){
	if(mark=='1'){
		secBoard('hotnews_caption','list',1);
	}else if(mark=='2'){
		secBoard('hotnews_caption','list',2);
	}else if(mark=='3'){
		secBoard('hotnews_caption','list',3);
	}
	
}
</script> 
<style>

.m_forminfo li i{color:#f00;padding:20px 0px 0px 6px;  font-style:normal; }
.m_forminfo li span{display:block; padding:20px 0px 0px 6px; display:block;  }
.kz_area{width:400px; height:60px;  border:1px solid #ccc; padding:6px; line-height:21px; color:#666; font-size:12px; }
</style>

</head>


<body onload="tabLoad(${mark});">

	<%-- <div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="javascript:void(0);" onclick="parent.frames.topFrame.overall();">首页</a></li>
    <li><a href="#" onclick="parent.frames[1].location.href='<%=path %>/back/9_left.jsp';  parent.frames[2].location.href='<%=path %>/overall/findlssh'; ">扩展管理</a></li>
    <li><a href="#">短信通知模块管理</a></li>
    </ul>
    </div> --%>
    
    <div class="rightinfo">
    
        <div id="hotnews_caption">
            <ul id="h_menu">                  
                 <li class="current" onclick="secBoard('hotnews_caption','list',1);">邮件模板</li>
                 <li class="normal" onclick="secBoard('hotnews_caption','list',2);">短信模板</li>
                 <li class="normal" onclick="secBoard('hotnews_caption','list',3);">站内信模板</li>
            </ul>
         </div> 
    
       <div id="hotnews_content">
          <div class="current" id="list_1">
           <form method="post" action="<%=path %>/extension/save1">
           <ul class="m_forminfo">

            <li><label class="m_info_title">用户注册成功：</label> 
                <textarea name="email1"  class="kz_area">${email1}</textarea>
              <i>#LINK#表示激活链接，只在此邮件下有用</i>
            </li>
<!--              <li><label class="m_info_title">安全中心更改安全问题：</label>  -->
<%--                 <textarea name="email2" class="kz_area">${email2}</textarea> --%>
<!--                  <i>#CODE#表示验证码</i> -->
<!--             </li> -->
<!--              <li><label class="m_info_title">安全中心新手机安全码：</label>  -->
<%--                 <textarea name="email3" class="kz_area" >${email3}</textarea> --%>
<!--                  <i>#CODE#表示验证码</i> -->
<!--             </li> -->
              <li><label class="m_info_title">借款申请审核通过：</label> 
                <textarea name="email4"class="kz_area"  >${email4}</textarea>
              </li>
<!--              <li><label class="m_info_title">密码找回提示：</label>  -->
<%--                 <textarea name="email5" class="kz_area" >${email5}</textarea> --%>
<!--                  <i>#LINK#表示密码找回链接，只在此邮件下有用</i> -->
<!--             </li> -->
              <li><label class="m_info_title">到期还款提醒：</label> 
                <textarea name="email6" class="kz_area" >${email6}</textarea>
                  <i>管理员可在还款中的借款查询即将到期的会员信息，并给会员以邮件提醒</i>
              </li>
<!--               <li><label class="m_info_title">支付密码找回提示： -->
<!--                   </label>  -->
<%--                 <textarea name="email7" class="kz_area" >${email7}</textarea> --%>
<!--                  <i>#LINK#表示支付密码找回链接，只在此邮件下有用</i> -->
<!--             </li> -->
            <li><label class="m_info_title">&nbsp;</label>
             <c:if test="${sessionScope.powerss[85]==1 }">
            <input name="" type="submit" class="btn" value="确定" />
            </c:if>
            </li>
 
          </ul>
             </form>
       </div> 
       
      <div class="normal" id="list_2">
      	<form action="<%=path %>/extension/save2" method="post">
        <ul class="m_forminfo">
            <li><label class="m_info_title">线上充值成功：</label> 
                <textarea name="messagemodel1.messModelContent"  class="kz_area">${messagemodel1.messModelContent}</textarea>
              <i>#USERANEM#表示用户名,#MONEY#表示充值金额</i>
            </li>
            
             <li><label class="m_info_title">线下充值成功：</label> 
                <textarea name="messagemodel2.messModelContent" class="kz_area">${messagemodel2.messModelContent}</textarea>
                 <i>#USERANEM#表示用户名,#MONEY#表示充值金额</i>
            </li>
            
             <li><label class="m_info_title">还款到帐：</label> 
                <textarea name="messagemodel3.messModelContent" class="kz_area" >${messagemodel3.messModelContent}</textarea>
                 <i>#ID#表示标号,#ORDER#表示期数码</i>
            </li>
            
             <li><label class="m_info_title">提现成功：</label> 
                <textarea name="messagemodel4.messModelContent"class="kz_area"  >${messagemodel4.messModelContent}</textarea>
               <i> #USERANEM#表示用户名,#MONEY#表示提现金额</i>   
             </li>
              <li><label class="m_info_title">Vip认证通过：</label>  
                <textarea name="messagemodel5.messModelContent" class="kz_area" >${messagemodel5.messModelContent}</textarea> 
                 <i>#USERANEM#表示用户名</i>
              </li> 
              <li><label class="m_info_title">发标初审通过：</label> 
                <textarea name="messagemodel6.messModelContent" class="kz_area" >${messagemodel6.messModelContent}</textarea>
                  <i>#USERANEM#表示用户名,#ID#表示标号</i>
            </li>
            <li><label class="m_info_title">发标流标： </label>  
                <textarea name="messagemodel7.messModelContent" class="kz_area" >${messagemodel7.messModelContent}</textarea> 
                <i>#USERANEM#表示用户名,#ID#表示标号</i> 
           </li> 
            
              <li><label class="m_info_title">发标复审通过： </label> 
                <textarea name="messagemodel8.messModelContent" class="kz_area" >${messagemodel8.messModelContent}</textarea>
                 <i>#USERANEM#表示用户名,#ID#表示标号</i>
            </li>
            
          <li><label class="m_info_title">手机验时发送验证码： </label>  
          <textarea name="messagemodel9.messModelContent" class="kz_area" >${messagemodel9.messModelContent}</textarea> 
         <i>#CODE#表示验证码</i> 
             </li> 
            
              <li><label class="m_info_title">安全中心更改安全问题： </label> 
                 <textarea name="messagemodel10.messModelContent" class="kz_area" >${messagemodel10.messModelContent}</textarea> 
                  <i>#CODE#表示验证码</i>
             </li> 
            
            <li><label class="m_info_title">安全中心更改手机号码：</label>  
                 <textarea name="messagemodel11.messModelContent" class="kz_area" >${messagemodel11.messModelContent} </textarea>
                 <i>#CODE#表示验证码</i>
             </li> 
            
            <li><label class="m_info_title">安全中心新手机验证码： </label>  
                <textarea name="messagemodel12.messModelContent" class="kz_area" >${messagemodel12.messModelContent}</textarea> 
                 <i>#CODE#表示验证码</i> 
            </li> 
            
           <li><label class="m_info_title">会员设置的新标提醒： </label>  
                <textarea name="messagemodel13.messModelContent" class="kz_area" >${messagemodel13.messModelContent}</textarea> 
                 <i>这种群发式短信中不能使用##这种的动态变量</i> 
           </li>
           
           <li><label class="m_info_title">提现验证码： </label>  
                <textarea name="messagemodel14.messModelContent" class="kz_area" >${messagemodel14.messModelContent}</textarea> 
                 <i>这种群发式短信中不能使用##这种的动态变量</i> 
           </li>
            <li><label class="m_info_title">密码找回： </label>  
                <textarea name="messagemodel15.messModelContent" class="kz_area" >${messagemodel15.messModelContent}</textarea> 
                 <i>这种群发式短信中不能使用##这种的动态变量</i> 
           </li>
           
            <li><label class="m_info_title">&nbsp;</label>
             <c:if test="${sessionScope.powerss[85]==1 }">
            <input name="" type="submit" class="btn" value="确定"/>
            </c:if>
            </li>
          </ul>
          </form>
      </div> 
      
      <div class="normal" id="list_3">
       <form method="post" action="<%=path %>/extension/save3">
       <ul class="m_forminfo">

            <li><label class="m_info_title">用户注册成功：</label> 
                <textarea name="interletter1"  class="kz_area">${interletter1}</textarea>
             
            </li>
             <li><label class="m_info_title">借款申请审核通过：</label> 
                <textarea name="interletter2" class="kz_area">${interletter2}</textarea>
            </li>
            <li><label class="m_info_title">资金变动提醒：</label> 
                <textarea name="interletter3" class="kz_area" >${interletter3}</textarea>
                  <i>#USERANEM#表示用户名</i>
            </li>
            <li><label class="m_info_title">视频认证通过：</label> 
                <textarea name="interletter4" class="kz_area" >${interletter4}</textarea>
                  <i>#USERANEM#表示用户名</i>
            </li>
              <li><label class="m_info_title">现场认证通过：</label> 
                <textarea name="interletter5" class="kz_area" >${interletter5}</textarea>
                  <i>#USERANEM#表示用户名</i>
            </li>
              <li><label class="m_info_title">安全问题设置：</label> 
                <textarea name="interletter6" class="kz_area" >${interletter6}</textarea>
                  <i>#USERANEM#表示用户名</i>
            </li>
             <li><label class="m_info_title">手机认证通过：</label> 
                <textarea name="interletter7" class="kz_area" >${interletter7}</textarea>
                  <i>#USERANEM#表示用户名</i>
            </li>
            <li><label class="m_info_title">&nbsp;</label>
            <c:if test="${sessionScope.powerss[85]==1 }">
            <input name="" type="submit" class="btn" value="确定"/>
            </c:if>
            </li>
           
          </ul>
          </form>
      </div>
      
	  </div> 
 
      </div>
    
 
</body>

</html>
