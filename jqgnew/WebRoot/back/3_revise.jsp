<%@page import="com.jqg.service.impl.IPServiceImpl"%>
<%@page import="com.jqg.service.IPService"%>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
  <%@ taglib uri="/struts-tags" prefix="s"%>
  	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<%
String path = request.getContextPath();if(path!=null && !path.equals("")){	if(path.contains("/")){		if(path.equals("/")){path = "";	}}else{	path += "/";}} 
String basePath = "";if(request.getServerPort()==80){basePath = request.getScheme()+"://"+request.getServerName()+path+"/";}else{basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";}    
String id = request.getParameter("id");
IPService ipService = new IPServiceImpl();
String ip = ipService.getIpAddr(request);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改信息</title>
<link href="<%=path %>/back/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=path %>/back/css/mem.css" rel="stylesheet" type="text/css" />
<link href="<%=path %>/back/css/tab.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" >
function btn(){
	var message = document.getElementById("message");
	if(message.value==""||message.value==null)
	{
	   alert("变动原因不能为空");
	   return;
	}
	var money= document.getElementById("money");
	if(parseInt((money.value).length)>10){
		alert("余额不能超过10位数");
		return false;
	}
    var uPwdRE=new RegExp("^[+-]?([0-9]*\.?[0-9]+|[0-9]+\.?[0-9]*)([eE][+-]?[0-9]+)?$");
	if (!uPwdRE.test(money.value)) {
	    alert("请输入正确金额！");
	    money.focus();
        return;
	}
	document.getElementById("Submit_btn").style.background = "gray";
   	document.getElementById("Submit_btn").value="正在提交...";
    document.getElementById("Submit_btn").style.disabled=false;
	document.getElementById("uptmoney").submit();
	alert("余额修改成功！");
	}

</script>
</head>


<body>

	<%-- <div class="place">
    <span>位置：</span>
    <ul class="placeul">
<li><a href="javascript:void(0);" onclick="parent.frames.topFrame.overall();">首页</a></li>
    <li><a href="3_list.jsp" target="rightFrame">会员列表</a></li>
    </ul>
    </div> --%>
    
    <div class="rightinfo">
    
       <div id="hotnews_caption">
            <ul id="h_menu">                  
                 <li class="current" onclick="secBoard('hotnews_caption','list',1);">调整余额</li>
             </ul>
         </div> 
    
       <div id="hotnews_content">
          <div class="current" id="list_1">
           <form name="uptmoney" id="uptmoney" action="<%=path %>/overall/uptmoney" method = "post" target="_blank" >
             <ul class="m_forminfo">
                <li><input name="ip" type="text" class="dfinput" value="<%=ip%>" size="37" style="display: none;"/></li>
                <li><input name="userId" type="text" class="dfinput" value="<%=id %>" size="37" style="display: none;"/></li>
                <li><label class="m_info_title">可用余额：</label><input name="amoney" id="money" type="text" class="dfinput" value="1000" size="37"/><i>可用余额的最大位数为10位，保留小数点后两位；如果是减少余额，请填负数，如'-1000'</i></li>
                
                <li><label class="m_info_title">变动原因：</label><textarea id="message" name="message" style="width:252px; height:60px;border-color: #a7b5bc #ced9df #ced9df #a7b5bc;  border-style: solid;  border-width: 1px; padding:8px;"></textarea><i>*</i></li>
                <li><label id="info_title1">&nbsp;</label><input type="button" name="Submit" id="Submit_btn" value="修改" class="btn" onclick="btn()"/></li>
          </ul>
          </form>
         </div> 
    
       </div> 
 
     </div>
    

</body>

</html>
