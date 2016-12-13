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
<title>无标题文档</title>
<link href="<%=path %>/back/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path %>/back/js/jquery.js"></script>

<script type="text/javascript">
	function btn(){
	document.getElementById("updateWebsite").submit();
	document.getElementById("Submit_btn").style.background = "gray";
    document.getElementById("Submit_btn").value="正在提交...";
    document.getElementById("Submit_btn").style.disabled=false;
	}
	jQuery(document).ready(function(){
		  jQuery(".click").click(function(){
		 	 jQuery(".tip").fadeIn(200);
		  });
		  
		  jQuery(".tiptop a").click(function(){
		 	 jQuery(".tip").fadeOut(200);
			});
		
		  jQuery(".sure").click(function(){
			  jQuery(".tip").fadeOut(100);
			});
		
		  jQuery(".cancel").click(function(){
			  jQuery(".tip").fadeOut(100);
			});
	
	});

</script>


</head>


<body onkeydown="if(event.keyCode==116)history.go(0);">

	 <%-- <div class="place">
    <span>位置：</span>
    <ul class="placeul">
<li><a href="javascript:void(0);" onclick="parent.frames.topFrame.overall();">首页</a></li>
    <li><a href="#">网站设置</a></li>
    </ul>
    </div> --%>
    
    <div class="rightinfo">
    
    
    
    <div class="set">
      
     <form name="updateWebsite" id="updateWebsite" action="<%=path %>/overall/updateWebsite" method = "post" >
	 <textarea name="websiteId" style="display: none;">${website.websiteId}</textarea>
	 	 <textarea name="ip" style="display: none;"><%=ip %></textarea>
    <dl id="line_100" class="lineD">
     <dt> 网站名称：</dt>
      <dd>
	     <input type="text" value="${website.webName}" class="input" name="webName" size="50"/>		
         <span> 出现在每个页面的title后面(web_name)</span>   
      </dd>
       <dt> 首页title：</dt>
      <dd>
	     <input type="text" value="${website.homeTitle}" class="input" name="homeTitle" size="50"/>		
         <span> 首页标题(index_title)</span>   
      </dd>
       <dt> 网站关键字：</dt>
      <dd>
	     <input type="text" value="${website.webKeyword}" class="input" name="webKeyword" size="50"/>		
         <span> 在首页的keywords中显示(web_keywords)</span>   
      </dd>
       <dt>网站描述：</dt>
      <dd>
	     <input type="text" value="${website.webDeescription}" class="input" name="webDeescription" size="50"/>		
         <span> 	在网站首页的描述中显示(web_descript)</span>   
      </dd>
	  <dt> 网站底部：</dt>
      <dd>
	     <input type="text" value="${website.webfoot}" class="input" name="webfoot" size="50"/>		
         <span> 网站底部的版权和联系信息(bottom)</span>   
      </dd>
    <!--  <dt> 借款管理费：</dt>
      <dd>
	     <input type="text" value="${website.beoverdueCost}" class="input" name="beoverdueCost" size="50">		
         <span> 以0.1|1|0.2|4的形式填入，表示按天时每天收取借款总额0.1%的管理费，按月时在借款期限小于等于4个月时收取借款总额1%的管理费，借款期限大于4个月时(收获取借款总额1%的管理费+超过4个月的时间里每月收取借款总额0.2%的管理费)(fee_borrow_manage)</span>   
      </dd>   --> 
      <dt> 提现手续费：</dt>
      <dd>
	     <input type="text" value="${website.withdrawals}" class="input" name="withdrawals" size="50"/>		
         <span>0：平台支付手续费，0.0050，是用户支付，要保留四位小数，必须大于等于双乾那边的提现手续费</span>   
      </dd>
      <dt> 回款续投奖励：</dt>
      <dd>
	     <input type="text" value="${website.backAward}" class="input" name="backAward" size="50"/>		
         <span> 以1|1.5|2的形式填入，表示回款续投一月标奖励1% , 回款续投二月标奖励1.5% ,回款续投三月标及以上奖励2%，投标金额必须大于回款本金</span>   
      </dd>
      <dt>线下充值奖励：</dt>
      <dd>
	     <input type="text" value="${website.lineReward}" class="input" name="lineReward" size="50"/>		
         <span> 如2，则收取千分之二奖励，0无奖励</span>   
      </dd>
        <dt> 投资积分设置：</dt>
      <dd>
	     <input type="text" value="${website.investIntegral}" class="input" name="investIntegral" size="50"/>		
         <span> 表示每1000元借出1天加1个投资积分，投标积分计算公式为：投资金额*天数/1000=投资积分，例如：投资天标1万，积分10000*1/1000=10分</span>   
      </dd>
       <dt> 借款积分设置：</dt>
      <dd>
	     <input type="text" value="${website.borrowIntegral}" class="input" name="borrowIntegral" size="50"/>		
         <span> 表示每1000元借出1天加1个投资积分，投标积分计算公式为：投资金额*天数/1000=投资积分，例如：投资天标1万，积分10000*1/1000=10分</span>   
      </dd>
           <dt> 投资总额：</dt>
      <dd>
	     <input type="text" value="0" class="input" readonly="readonly" style="background-color:#CCCCCC;" name="investment" size="50"/>		
         <span>从填的数据开始相加，例如10000就从10000开始，0为真实数据</span>   
      </dd>
           <dt>已还总额：</dt>
      <dd>
	     <input type="text" value="0" class="input"  readonly="readonly" style="background-color:#CCCCCC;"   name="loan" size="50"/>		
          <span>从填的数据开始相加，例如10000就从10000开始，0为真实数据</span>   
      </dd>
       <dt> 提现限制：</dt>
      <dd>
	     <input type="text" value="${website.translateLimit}" class="input" name="translateLimit" size="50"/>		
         <span>0，无限额，50000，每笔限额50000</span>   
      </dd>
      <dt>托管账号：</dt>
      <dd>
      	<input type="text" value="${website.trustAccount}" class="input" name="trustAccount" size="50"/>		
         <span>托管平台账号：例如p160001</span>   
      </dd>
      <dt>托管公钥：</dt>
      <dd>
      	<textarea name="publicKey" cols="80" style="border:1px solid;border-color:#a7b5bc #ced9df #ced9df #a7b5bc;" rows="5" >${website.publicKey}</textarea>
      	
         <span>托管平台账号公钥</span>   
      </dd>
      <dt>托管私钥：</dt>
      <dd>
      	<textarea name=privateKey cols="80"  style="border:1px solid;border-color:#a7b5bc #ced9df #ced9df #a7b5bc;" rows="5" >${website.privateKey}</textarea>		
         <span>托管平台账号私钥</span>   
      </dd>
      
<!--         <dt> 后台登录路径：</dt> -->
<!--       <dd> -->
<!-- 	     <input type="text" value="${website.line}" class="input" name="line" size="50" readonly="readonly"  style="background-color:#CCCCCC;"/>		 -->
<!--          <span> 可修改后台登陆路径,格式为任意字母组合。例如：如填写admin，访问路径即为：【http://www.您的域名.com/admin 】 (admin_url)</span>    -->
<!--       </dd> -->
      
    </dl>
    
    <div class="page_btm">
     <c:if test="${sessionScope.powerss[1]==1}">
      	<input type="button" name="Submit" id="Submit_btn" value="保存" class="btn" onclick="btn()"/>
      </c:if>
    </div>
    </form>
	
  
    
    </div>
    
   

    
    </div>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>

</body>

</html>
