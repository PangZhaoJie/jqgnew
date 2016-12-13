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
<title>会员列表</title>
<link href="<%=path %>/back/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=path %>/back/css/mem.css" rel="stylesheet" type="text/css" />
<link href="<%=path %>/back/css/tabs.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path %>/back/js/jquery.js"></script>
<script type="text/javascript" src="<%=path %>/back/js/jquery.tabso_yeso.js"></script>
<!--弹窗中的滑动js-->
<script type="text/javascript" src="<%=path %>/back/js/jquery.tabso_yeso.js"></script>
<script type="text/javascript">
function getJSONDatas(currentPage,pageSize) {

  
	var url = "<%=path %>/overall/findUsersByvidio.action?currentPage="+currentPage+"&pageSize="+pageSize+"&ziduan=videoResult"+"&temp=" +Math.random();


	$.getJSON(url,function(data){
	
		var cp = data.currentPage;
		var tp = data.totalPage;
		var $table = $ ("#table");
		$table.empty();
		str = '<thead><tr><th width="5%"  align="center" >ID</th><th width="6%"  align="center" >用户名</th><th width="6%"  align="center" >真实姓名</th> <th width="8%"  align="center" >可用金额</th><th width="6%" align="center" >冻结金额</th><th width="6%" align="center" >待收金额</th> <th width="13%" align="center" >注册时间</th><th width="10%" align="center" >审核状态</th><th width="20%"  align="center" >操作</th></tr></thead>';
		var jsonRoot = data.jsonRoot;
		for(var i = 0; i < jsonRoot.length; i++) {
			str += '<tbody><tr><td>'+ jsonRoot[i].id+ '</td><td>'
	
				   + decodeURI(jsonRoot[i].name, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].realname, "utf-8") +"</td><td>"
				  
				   + decodeURI(jsonRoot[i].amoney, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].fmoney, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].dmoney, "utf-8") +"</td><td>"
				   
				   + decodeURI(jsonRoot[i].time, "utf-8") +"</td><td>"
				   +"未审核</td><td>"
				   +' <c:if test="${sessionScope.powerss[30]==1}"><a style="color:red;"href="#" class="tablelink" onclick = "setUserId('+ jsonRoot[i].id+  ');">审核</a>   </c:if><c:if test="${sessionScope.powerss[30]!=1}"> --  </c:if> '
			    +"</td></tr>  </tbody>";

		}
		$table.html(str);
	
	    if(tp !=1)
		{		
		document.getElementById("pages").style.display="";
	     $ ("#currentPages").text("第"+cp+"页");
         $ ("#totalPages").text("共"+tp+"页");
         $ ("#tpagesnone").val(tp);
	     $ ("#firstPages").attr("href","javascript:getJSONDatas(1,10)");
	     $ ("#prevPages").attr("href","javascript:getJSONDatas("+(parseInt(cp)-1)+",10)");
	     $ ("#nextPages").attr("href","javascript:getJSONDatas("+(parseInt(cp)+1)+",10)");
	     $ ("#lastPages").attr("href","javascript:getJSONDatas("+(parseInt(tp))+",10)");
		}
		else{
			document.getElementById("pages").style.display="none";
		}
	});
}

function tiaozhuans() {
    var size = $("#sizes").val();
    var totalpage = $("#tpagesnone").val();
  	if(size==null || size==""){
  		alert("请输入要跳转的页数！");
  		return false;
  	}
    if(parseInt(size)<1||parseInt(size)>parseInt(totalpage))
    {
    	alert("请输入正确的页数")
    	return false;
    }else{
    	getJSONDatas(size,10);  
    }
    
}


$(function() {  
	getJSONDatas(1,10);  
});

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
   
	
    <div>
    	<ul class="toolbar">
         <li ><span><img src="<%=path %>/back/images/t05.png" /></span>视频认证</li>
         
        </ul>
     </div>
    
    
    <table class="tablelist" id="table">
    	<thead>
    	<tr>

        <th width="5%"  align="center" >ID</th>

        <th width="6%"  align="center" >用户名</th>
        <th width="6%"  align="center" >真实姓名</th>
        <th width="6%"  align="center" >推荐人</th>
 
        <th width="8%"  align="center" >可用金额</th>
        <th width="6%" align="center" >冻结金额</th>
        <th width="6%" align="center" >待收金额</th>
        <th width="13%" align="center" >注册时间</th>
        <th width="20%"  align="center" >操作</th>
        
        </tr>
        </thead>
        <tbody>
      
        
      
             
        </tbody>
    </table>
    <input type="text" id="tpagenone" style="display: none"/>
<input type="text" id="tpagesnone" style="display: none"/>
    
       <div class="page" id="pages" style="display: none">
    <em id="currentPages">第页</em>&nbsp; &nbsp; 
  	<em id="totalPages">共页</em>&nbsp; &nbsp; 
  	<em ><a id="firstPages" href="">首页</a></em>&nbsp; &nbsp; 
  	<em ><a id="prevPages" href="">上一页</a></em>&nbsp; &nbsp; 
  	<em ><a id="nextPages" href="">下一页</a></em>&nbsp; &nbsp; 
  	<em ><a id="lastPages" href="">最后一页</a></em>&nbsp; &nbsp; 
  	<em class="skip">转到<input name="page" type="text" id="sizes" size="5" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>页</em><a href="#" id="tiaozhuans" onclick="tiaozhuans()">跳转</a>
    </div>
 


    </div>
   
  
    <!--  弹窗 -->

   <div id="light3" class="white_content">
       <div class="light_title">
          <span>jiep的资料</span> 
          <a href = "javascript:void(0)" onclick = "document.getElementById('light3').style.display='none';document.getElementById('fade').style.display='none'" class="close"><img src="images/close1.png"/></a>
      </div>
      <div class="light_box">
        <ul class="tabbtn" id="normaltab3">
		   <li class="current">会员资料</li>
		   <li>账户资金</li>
		   <li>借款投资</li>
		 </ul>
         <!--tabbtn end-->
        <div class="tabcon" id="normalcon3">
         <div class="sublist">
          <dl class="lineD">
             <dt>用户名：</dt><dd>dggghh <a href="" ><img title="1级" src="images/a_ico.png"></a></dd>
             <dt>认证情况：</dt>
              <dd> 
                 <i class="sm">实名</i>
                 <i class="tel yes">手机</i>
                 <i class="mail yes">邮箱</i>
                 <i class="pay yes">现场</i>
                 <i class="video yes">视频</i>
                 <i class="datum yes">资料</i></dd>
         </dl>
		  <dl class="lineD"><dt>是否冻结：</dt><dd>未冻结</dd><dt>客户类型：</dt><dd>普通借款者</dd></dl>
		  <dl class="lineD"><dt>所属客服：</dt><dd>金钱柜-诺米</dd><dt>真实姓名：</dt><dd>张乐</dd></dl>
		  <dl class="lineD"><dt>性别：</dt><dd>男</dd><dt>职业：</dt><dd>司机</dd></dl>
		  <dl class="lineD"><dt>邮箱：</dt><dd>373018898@qq.com</dd><dt>投资积分：</dt><dd>1295</dd></dl>
		  <dl class="lineD"><dt>手机号码：</dt><dd>13403919180</dd><dt>年龄：</dt><dd>0</dd></dl>
		  <dl class="lineD"><dt>婚姻状况：</dt><dd>已婚</dd><dt>学历：</dt><dd>本科</dd></dl>
		  <dl class="lineD"><dt>身份证号：</dt><dd>410327198506180037</dd><dt>月收入：</dt><dd>5000.00元</dd></dl>
		  <dl class="lineD"><dt>银行帐号：</dt><dd>6210985010003605893</dd><dt>银行名称：</dt><dd>中国邮政储蓄银行</dd></dl>
		  <dl class="lineD"><dt>银行开户行：</dt><div id="xwidth">河南焦作中国邮政储蓄股份有限公司河南省孟州市赵和镇邮政所</div></dl>
		  <dl class="lineD"><dt>籍贯：</dt><div id="xwidth"></div></dl>
		  <dl class="lineD"><dt>居住地：</dt><div id="xwidth"></div></dl>
		  <dl class="lineD"><dt>个人描述：</dt><div id="xwidth"></div></dl>
		  <dl class="lineD"><dt>身份证：</dt><div id="xwidth"><a target="_blank" href="#"><img height="100px" src="images/id.jpg"></a></div></dl>
    
       </div> 
      <div class="sublist">
    
            <dl class="lineD"><dt class="title">资金情况</dt><dd class="xwidth">&nbsp;</dd></dl>
	   	    <dl class="lineD"><dt>帐户总额：</dt><dd>￥5,000</dd><dt>待收金额：</dt><dd>￥0.00</dd></dl>
		    <dl class="lineD"><dt>可用余额：</dt><dd>￥5,000</dd><dt>冻结金额：</dt><dd>￥0.00</dd></dl>

		    <dl class="lineD"><dt class="title">充值提现</dt><dd class="xwidth">&nbsp;</dd></dl>
		    <dl class="lineD"><dt>充值成功次数：</dt><dd>1次</dd><dt>充值成功金额：</dt><dd>￥5,000.00</dd></dl>
		    <dl class="lineD"><dt>提现成功次数：</dt><dd>0次</dd><dt>提现成功金额：</dt><dd>￥0.00</dd></dl>

		    <dl class="lineD"><dt class="title">额度情况</dt><dd class="xwidth">&nbsp;</dd></dl>
		    <dl class="lineD"><dt>借款信用额度：</dt><dd>￥0.00</dd><dt>可用信用额度：</dt><dd>￥0.00</dd></dl>
      
     </div>  
     
      <div class="sublist">
            <dl class="lineD"><dt class="title">借款金额统计</dt><dd class="xwidth">&nbsp;</dd></dl>
		    <dl class="lineD"><dt>借款总额：</dt><dd>￥0.00</dd><dt>累计亏盈：</dt><dd>￥0</dd></dl>
		    <dl class="lineD"><dt>已还总额：</dt><dd>￥0</dd><dt>待还总额：</dt><dd>￥0</dd></dl>
		    <dl class="lineD"><dt class="title">借还款次数统计</dt><dd class="xwidth">&nbsp;</dd></dl>
		    <dl class="lineD"><dt>借款成功次数：</dt><dd>0次</dd><dt>正常还款次数：</dt><dd>0次</dd></dl>
		    <dl class="lineD"><dt>提前还款次数：</dt><dd>0次</dd><dt>逾期还款次数：</dt><dd>0次</dd></dl>
		    <dl class="lineD"><dt>迟还次数：</dt><dd>0次</dd><dt>网站代还次数：</dt><dd>0次</dd></dl>
		    <dl class="lineD"><dt class="title">投资统计</dt><dd class="xwidth">&nbsp;</dd></dl>
		    <dl class="lineD"><dt>投资总额：</dt><dd>￥0</dd><dt>投标奖励：</dt><dd>￥0</dd></dl>
		    <dl class="lineD"><dt>已收总额：</dt><dd>￥0</dd><dt>待收总额：</dt><dd>￥0</dd></dl>
		    <dl class="lineD"><dt>推广奖励：</dt><dd>￥0.00</dd><dt>线下冲值奖励：</dt><dd>￥0.00</dd></dl>
		    <dl class="lineD"><dt>续投奖励：</dt><dd>￥0</dd><dt>&nbsp;</dt><dd>&nbsp;</dd></dl> 
       </div>
       </div> 
      </div>  
      
   </div>
   
   <div id="light33" class="white_content">
       <div class="light_title">
          <span>审核结果</span> 
          <a href = "javascript:void(0)" onclick = "document.getElementById('light33').style.display='none';document.getElementById('fade').style.display='none';" class="close"><img src="<%=path %>/back/images/close1.png"/></a>
      </div>
      <div class="light_box">
        <ul class="tabbtn" id="normaltab1">
		   <li class="current">审核结果</li>
		  
		 </ul>
         <!--tabbtn end-->
        <div class="tabcon" id="normalcon1">
         <div class="sublist">
             <form name="scene" id="scene" action="<%=path %>/overall/vidio" method = "post" >
           <ul>
             <li>
               <label id="info_title">审核结果：</label>
                <p style="float:left; line-height:34px;">
  	                 <label> <input type="radio" name="radio" value="1" id="raidio1" checked="checked"/>通过</label>
                     <label> <input type="radio" name="radio" value="2" id="raidio2" /> 不通过</label>

               </p>
          </li>
          <li><input type="text" name="userId"  id="userId" style="display: none;"/></li>
		  <li><label id="info_title">说明：</label><textarea id="message"name="message" style="width:480px; height:100px; border:1px solid #ccc;"></textarea></li>
		  <li><input name="" type="button" style="margin-left:240px;" class="btn" value="确定" onclick="btn()"/></li>
		  	 <li>  <textarea name="ip" style="display: none;"><%=ip %></textarea></li>
       </ul>
       </form>
       </div> 
        
      
       </div> 
      </div>  
      
   </div>   
   <div id="fade" class="black_overlay"></div>        
   <!--  弹窗 -->   
   <script type="text/javascript">
   function btn(){
	   
	var message = document.getElementById("message");
	if(message.value==""||message.value==null)
		{
		   alert("说明不能为空");
		   return;
		}
		document.getElementById("scene").submit();
		alert("审核完成！");
		}

function setUserId(userId){
input=  document.getElementById("userId");
    input.value=userId;
	  document.getElementById('light33').style.display='block';
	  document.getElementById('fade').style.display='block';
}
</script>
</body>

</html>
