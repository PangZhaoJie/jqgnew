<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();if(path!=null && !path.equals("")){	if(path.contains("/")){		if(path.equals("/")){path = "";	}}else{	path += "/";}} 
String basePath = "";if(request.getServerPort()==80){basePath = request.getScheme()+"://"+request.getServerName()+path+"/";}else{basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";}    
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="icon" href="<%=path %>/image/favicon/favicon.ico" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>个人中心-我要提现</title>
<link href="../css/public.css" type="text/css" rel="stylesheet"/>
<link href="../css/user/user_public.css" type="text/css" rel="stylesheet"/>
<link href="../css/user/user_cash.css" type="text/css" rel="stylesheet"/>
<!--导航下拉菜单 js-->
<script type="text/javascript" src="../js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="../js/login/approve.js"></script>
<!--表单下拉列表框-->
<script type="text/javascript" src="../js/select2css.js"></script>
<!--左侧折叠菜单-->

<script type="text/javascript" src="../js/menu.js"></script>
<script type="text/javascript">
var $page =  jQuery.noConflict();
$page(document).ready(function(){
	$page("#submit_bt").click(function(){
	    if($page("#bankTable").val()==0){
	    	document.getElementById("bankTableNone").style.display="";
	    	return false;
	    }
	    if($page("#pbSize").val()==0){
	    	document.getElementById("bankNone").style.display="";
	    	return false;
	    }

	    if($page("#money").val()==null||$page("#money").val()==""){
	    	document.getElementById("moneyNone").style.display="";
	    	return false;
	    }
	    if(parseInt($page("#limit").val()!="0")){
	    if(parseInt($page("#money").val())>parseInt($page("#limit").val())||parseInt($page("#money").val())>parseInt($page("#aMoney").val())){
	    	document.getElementById("moneyNone").style.display="";
	    	return false;
	    }}
	    else{
	    	   if(parseInt($page("#money").val())>parseInt($page("#aMoney").val())){
	   	    	document.getElementById("moneyNone").style.display="";
	   	    	return false;
	   	    }
	    }
	    if($page("#captcha").val()==null||$page("#captcha").val()==""){
	    	document.getElementById("captchaNone").style.display="";
	    	return false;
	    }
	    if($page("#pwd").val()==null||$page("#pwd").val()==""){
	    	document.getElementById("pwdNone").style.display="";
	    	return false;
	    }
		//获取留言
		var data = "payPwd="+$page("#pwd").val()+"&bankPid="+$page("#bankTable").val()+"&affectMoney="+$page("#money").val()+"&key="+$page("#captcha").val();
		
    	$page("#submit_bt").attr("disabled", "disabled");//防止重复提交
    	
    	$page("#translateform").submit();
		<%-- $page.ajax({
			//使用json，/json是他的命名空间
			url:"<%=path%>/json/createtranslate", 
			type:"post",
			data:data,//使用这个方法可以使表单里面的内容自动提交上去
			dataType:"json",//返回类型为json对象
			success:function(data){
				if(data=="success"){
// 					date="&mes=提现成功！&href=<%=path%>/userbank/mention"
// 					window.location.href = "<%=path%>/jump.jsp"+date;
					alert("提交成功！");
					window.location.href = "<%=path%>/userbank/mention";
				}
				if(data=="error"){

					document.getElementById("errorNone").style.display="";
				}
				if(data=="key"){
					document.getElementById("keyNone").style.display="";
				
				}
				$page("#submit_bt").removeAttr("disabled");
			}
		}); --%>
		
	});
});

function getCode(val){
		countdown();
		var data="mobiles="+val+"&isOpen=1&messModelId=14";
    	$page.ajax({
    		  async:false,
    		  url:"<%=path %>/phone/sendMessage",
    		  type:"POST",
    		  data:data,
    		  dataType:"json",
    		  success:function(data){
    		     if(data=="success"){
    		    	 $page('#phoneTip').html("验证码发送成功");
					}else{
					$page('#phoneTip').html("发送失败");
					}
					}
    	  });
    	
    }
var timeout=60;              
 function countdown(){
 document.getElementById("yanzheng").setAttribute("disabled","disabled");
	if (timeout == 0)
		{
			document.getElementById("yanzheng").value = "重新发送";
		 	 document.getElementById("yanzheng").removeAttribute("disabled");;
			timeout=60;
		}
		else
		{
		document.getElementById("yanzheng").value =  timeout+'s';
		setTimeout('countdown()',1000);
	}
	timeout--;
}//验证码加倒计时

function toMoneyPage(num){
	  var page=$page("#currentPageId").val();
	  var pageNum=$page("#pageNumId").val();
	  var p=$page("#pId").val();
	    if(isNaN(parseInt(p))&&p!=""){
	      alert("请输入正确页数");
	    document.getElementById('pId').value='';
	   
	      return false;
	  }
	    if(parseInt(p)< 1 || parseInt(p)> parseInt(pageNum)){
	  	alert("请输入正确的页数！");
	  	document.getElementById('pId').value='';
	  	return false;
	  }
 	  if(num==1){
			  $page("#page1-1").attr("href","<%=path %>/userbank/showTranslate?currentPage=1"); 
	  }
	  if(num==3){
		  if(Number(page)<Number(pageNum)){
			  $page("#page1-3").attr("href","<%=path %>/userbank/showTranslate?currentPage="+(Number(page)+1));
		  }
	  }
	  if(num==2){
		  if(page>1){
			  $page("#page1-2").attr("href","<%=path %>/userbank/showTranslate?currentPage="+(Number(page)-1));
		  }
	  }
	  if(num==4){
		  if(p<1){
			  p=1;
		  }else if(Number(p)>Number(pageNum)){
			  p=pageNum;
		  }
	 
			  $page("#page1-4").attr("href","<%=path %>/userbank/showTranslate?currentPage="+Number(p));
	  }
	  
}
</script>
<script type="text/javascript">
 
 var $page =  jQuery.noConflict();
 function getJSONData() {
	var mark=document.getElementById("markId").value;
	$page("#ID1").hide();
	$page("#ID2").show();
  	if(mark==1){
 		secBoard('hotnews_caption','list',2);
 	}
 }
 $page(function() {  
 	getJSONData();  
 });
 </script>
</head>
<body> 
<input type="text" value="${sessionScope.limit}" id="limit" style="display: none"/>
<input type="text" <fmt:formatNumber value="${sessionScope.bankAvailableMoney }" pattern="0.00"/> id="aMoney" style="display: none"/>
<input type="text" value="${fn:length(BankList)}" id="pbSize" style="display: none"/>
<input value="${mark}" id="markId" type="hidden"/>
<!--头部开始-->
<div class="header">
<jsp:include page="../header.jsp" />
    
</div>
<!--头部结束-->
<div class="maincontent">
  
  <div class="conbox fn_cle">
       <jsp:include page="left.jsp" />
   
   </div>
   
     <div class="user_r">
         <div id="hotnews_caption">
            <ul id="h_menu">                  
                 <li class="current"onclick="secBoard('hotnews_caption','list',1);">马上提现</li>
                 <li class="normal"><a href="<%=path%>/userbank/showTranslate">提现记录</a></li>
            </ul>
         </div>
         
         <div id="hotnews_content">
           <div class="current" id="list_1">
             <P>尊敬的${bankbasicinfor.realName}，您可以将账户中的余额提取到银行卡中，敬请仔细操作。</P>
             <div class="c_tip">
                <span>1、尊敬的${bankbasicinfor.realName}，提现操作涉及您的资金变动，请仔细核对您的提现信息。</span>
                <span>2、${sessionScope.translateLimit}，最低提现金额为${latefee.parvalue }</span>
                <span>3、涉及您的资金安全，请仔细操作。</span>
             </div>
             <form method="post" action="<%=path%>/json/createtranslate" target="_blank" id="translateform">
             <table width="825" cellspacing="0" cellpadding="0" class="pass_table"  >
                            <tbody>
                           
                            <tr>
                                <td class="td1" >您的银行账号：</td>
                                <td class="td2" >
                                <div id="uboxstyle">
                                <select id="bankTable" name="bankPid">
                                      <option value="0" >---请选择银行---</option>
                                      <c:forEach var="person" items="${BankList}">
                                      	<option value="${person.personalBankInforId}">${person.accountNums}</option>
                                      </c:forEach>
                               </select>
                               
                             </div>
                             <em id="bankNone"  style="display: none"> 请先添加银行卡<a  href=""  style="color: red;">现在添加</a></em>
                             <em id="bankTableNone"  style="display: none;color:red;"> 请选择银行卡</em>
                             </td>
                             </tr>
                            <tr>
                                <td class="td1">可提现余额：</td>
                                <td class="td2" ><fmt:formatNumber value="${sessionScope.bankAvailableMoney}" pattern="0.00"/>元 </td>
                             </tr>
                             <tr>
                                <td class="td1">提现金额：</td>
                                <td class="td2"><input id="money" name="affectMoney" type="text" size="32" /><em id="moneyNone"  style="display: none;color:red;"> 请输入正确金额</em> </td>
                             </tr>
                             <tr>
                                <td class="td1">验证码：</td>
                                <td class="td2"><label>
                                <c:if test="${uservip.phoneResult==1}">
                                <input name="key"  type="text" size="32" id="captcha"/></label><input style="height:30px" type="button" id="yanzheng" value="获取验证码" onclick="getCode(${bankbasicinfor.phoneNum})">
<!--                                 <a href="<%=path %>/phone/sendMessage.action?mobiles=${bankbasicinfor.phoneNum}&isOpen=1&messModelId=1" style="color:red">立即获取</a> -->
                                 <em id="captchaNone"  style="display: none;color:red;"> 请输入正确的验证码</em> <em id="keyNone"  style="display: none;color:red;"> 验证码不对，请重新输入</em><span id="phoneTip"></span>
                               </c:if>
                               <c:if test="${uservip.phoneResult!=1}">
                               <em> 您还没有进行手机验证，请先进行手机验证，<a href="<%=path%>/user/create?mark=2">立即验证</a></em>
                               </c:if>
                                </td>
                             </tr>
                             <tr>
                                <td class="td1">支付密码：</td>
                                <td class="td2"><input name="payPwd"  type="password" size="32" id="pwd"/><em id="pwdNone"  style="display: none;color:red;"> 请输入支付密码</em><em id="errorNone"  style="display: none;color:red;"> 支付密码不对，请重新输入</em></td>       
                             </tr>
                            <tr height="60">
                                <td colspan="2" align="center" ><div id="submit" > <input type="button" class="submit_bt" id="submit_bt" value="确认提现" /></div></th>
                            </tr>
                            
                      </tbody></table>
                      </form>
             
           </div>
         
           <div class="normal" id="list_2">
              
              <P>尊敬的${bankbasicinfor.realName}，我们为您记录和保存了您的提现记录，敬请审阅。</P>
              <table width="100%" cellspacing="0" cellpadding="0" class="record_table" id="tranTable" >
                   <tbody>
                	<tr class="th3">
                		<td whidth="130">申请时间</td>
                		<td whidth="130">提现金额</td>
                		<td whidth="130">可用余额</td>
                		<td whidth="180">提现卡号</td>
                		<td whidth="290">说明</td>
                		<td whidth="100">状态</td>
                	</tr>
                	<c:forEach var="tran" items="${translates1}">
                		<tr>
                			<td>${tran.occurTime}</td>
                			<td>${tran.affectMoney}</td>
                			<td>${tran.availableBalance}</td>
                			<td>${tran.bankNums}</td>
                			<c:if test="${tran.texplain==null}"><td>--</td></c:if>
                			<c:if test="${tran.texplain!=null}"><td>${tran.texplain}</td></c:if>
                			<c:if test="${tran.state==0}"><td>审核中</td></c:if>
                			<c:if test="${tran.state==1}"><td>审核通过，处理中</td></c:if>
                			<c:if test="${tran.state==2}"><td>成功</td></c:if>
                			<c:if test="${tran.state==3}"><td>审核未通过</td></c:if>
                		</tr>
                	</c:forEach>
                   </tbody>
                </table>
                
                 <div class="page" style="float: left;font-size: 14px;height: 35px;margin-top: 20px;padding: 10px 0;text-align: center; width: 845px;">
                <a href=""   onclick="toMoneyPage('1')" id="page1-1" style="background: none repeat scroll 0 0 #f6f6f6;border-color: #dbdbdb;border-style: solid;border-width: 1px;color: #555;margin: 3px 7px;padding: 4px 9px;text-decoration: none;">首页</a>
                <a href=""  onclick="toMoneyPage('2')" id="page1-2" style="background: none repeat scroll 0 0 #f6f6f6;border-color: #dbdbdb;border-style: solid;border-width: 1px;color: #555;margin: 3px 7px;padding: 4px 9px;text-decoration: none;">上一页</a>
                <a href=""  onclick="toMoneyPage('3')" id="page1-3" style="background: none repeat scroll 0 0 #f6f6f6;border-color: #dbdbdb;border-style: solid;border-width: 1px;color: #555;margin: 3px 7px;padding: 4px 9px;text-decoration: none;">下一页</a> 
                <span class="skip"><a href="#"  onclick="return toMoneyPage('4')" id="page1-4" style="background: none repeat scroll 0 0 #f6f6f6;border-color: #dbdbdb;border-style: solid;border-width: 1px;color: #555;margin: 3px 7px;padding: 4px 9px;text-decoration: none;">
               	 转到</a><input name="page" type="text" id="pId" value="" size="3" style="height:26px; line-height:26px;" />页</span>
                	第${currentPage}页/共${totalPage}页
        	 	<input name="currentPage" value="${currentPage}" id="currentPageId" style="display: none"/>
         		<input name="total" value="${totalPage}" id="pageNumId" style="display: none"/>
          	    </div>
           </div>
          
           
          <script type="text/javascript" src="../js/tabs.js" language="javascript"></script>
        </div>
        
   </div>
    
    
  </div>
</div> 

</div>
<!--footer 开始-->
<div class="footer">
  <jsp:include page="../footer.jsp" />
</div>
<!--footer 结束-->


<!--右边漂浮 开始-->
<%-- <div id="service">
	<a href="<%=path%>/tool/borrow_tool.jsp" class="srvLog"   title="网贷计算器">网贷计算器</a>
	<a href="tencent://message/?uin=285772998&Site=企业网站&Menu=yes" class="srvCns" title="官方交流群">官方交流群</a>
	<a href="<%=path%>/tender/totenderList" class="srvDj"   title="投资理财">投资理财</a>
	<a class="goTop" id="goTop" title="返回顶部" style="display:none" >返回顶部</a>
</div>
<script type="text/javascript" src="../js/service.js">	</script> --%>
<!--右边漂浮 结束-->

</body>
</html>