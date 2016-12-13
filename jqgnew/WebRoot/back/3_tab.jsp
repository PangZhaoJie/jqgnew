<%@page import="com.jqg.pojo.Requestquota"%>
<%@page import="com.jqg.service.impl.RequestquotaServiceImpl"%>
<%@page import="com.jqg.service.RequestquotaService"%>
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
String id = request.getParameter("id");
RequestquotaService requestquotaService = new RequestquotaServiceImpl();
Requestquota requestquota = requestquotaService.findRequestquotaByrequestQuotaId(Integer.valueOf(id));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改信息</title>
<link href="<%=path %>/back/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=path %>/back/css/mem.css" rel="stylesheet" type="text/css" />
<link href="<%=path %>/back/css/tab.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path %>/back/js/jquery.js"></script>
<script type="text/javascript" src="<%=path %>/back/js/Tabs.js"></script>
<script type="text/javascript" >
function btn(){
	var money= document.getElementById("money");
	var message = document.getElementById("message");
	if(message.value==""||message.value==null)
		{
		   alert("说明不能为空");
		   return;
		}
	
	if (money.value==""||money.value==null || parseInt((money.value).length)>10) {
	    alert("请输入正确额度！")
	    money.focus();
        return;
	}
	   
	
	document.getElementById("quota").submit();
	alert("审核完成！");
	}

</script>


<style>
.m_info_title{ width:125px;line-height:34px; display:block; float:left; text-align:right; margin-right:10px;}

</style>
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
                 <li class="current" onclick="secBoard('hotnews_caption','list',1);">审核</li>
            </ul>
         </div> 
    
       <div id="hotnews_content">
          <div class="current" id="list_1">
          <form name="quota" id="quota" action="<%=path %>/overall/quota" method = "post" >
           <ul class="m_forminfo">
             <li><input name="ip" type="text" class="dfinput" value="<%=ip%>" size="37" style="display: none;"/>
              <input name="quotaId" type="text" class="dfinput" value="<%=id %>" size="37" style="display: none;"/>
            </li>
            <li><label class="m_info_title">申请金额：</label>  
                <label>￥<%=requestquota.getRequestQuota() %></label>
              
            </li>
            <li><label class="m_info_title">申请说明：</label>  
                <label><%=requestquota.getApplyExplain() %></label>
              
            </li>
            <li><label class="m_info_title">授权额度：</label>  
                <input name="amoney" type="text" class="dfinput" value="" size="39" id="money" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
        		<i>授权额度的位数最大为10位数</i>
            </li>
            <li><label class="m_info_title">审核结果:</label> 
                <p>
                  <label>  <input type="radio" name="radio" value="1" id="RadioGroup1_0"checked="checked" /> 审核通过 </label>
                  <label> <input type="radio" name="radio" value="2" id="RadioGroup1_1" /> 审核未通 </label>
               </p>
               <i>*</i>
            </li>
            <li><label class="m_info_title">处理说明:</label><textarea id="message" name="message" style="width:480px; height:100px; border:1px solid #ccc;"></textarea><i>*</i></li>
            <li><label class="m_info_title">&nbsp;</label><input name="" type="button" class="btn" value="确定"onclick="btn()"/></li>
          </ul>
    </form>
       </div> 
    
    
        
	  </div> 
 
      </div>
    

</body>

</html>
