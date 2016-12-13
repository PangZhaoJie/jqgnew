<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
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
<title>个人中心-银行账户</title>
<link href="../css/public.css" type="text/css" rel="stylesheet"/>
<link href="../css/user/user_public.css" type="text/css" rel="stylesheet"/>
<link href="../css/user/user_cash.css" type="text/css" rel="stylesheet"/>
<!--导航下拉菜单 js-->
<script type="text/javascript" src="../js/jquery-1.8.2.min.js"></script>
<!--表单下拉列表框-->

<!--左侧折叠菜单-->

 <script type="text/javascript" src="../js/select2css.js"></script>
<script type="text/javascript" src="../js/menu.js"></script>
<script type="text/javascript">
var   $bank = jQuery.noConflict();
$bank(document).ready(function(){
 $bank(".submit_bt").click(function(){
	 if($bank("#realNameNone").val()=="2"){
		 
	 }
	 else{
		  document.getElementById("realName").style.display="";
		   return false;
	 }
   if($bank("#style").val()=="0"){
	   document.getElementById("styleIdNone").style.display="";

		return false;
	}

   if($bank("#accountNum").val()==""||$bank("#accountNum").val().size>30 || ($bank("#accountNum").val()).length<10){
	   document.getElementById("aNumNone").style.display="";
	   document.getElementById("aNum").style.display="none";
		return false;
	}
   if($bank("#accountNum").val()!=$bank("#accountNums").val()){
	   document.getElementById("aNumNones").style.display="";
	   document.getElementById("aNums").style.display="none";
		return false;
	}
   if($bank("#accountAddress").val()==""||$bank("#accountAddress").val()>100){
	   document.getElementById("aAddressNone").style.display="";
	   document.getElementById("aAddress").style.display="none";
		return false;
	}
       $bank(".submit_bt").attr("disabled","disabled");//防止重复提交
 		 var data = "accountName="+$bank("#accountName").val()+"&accountNum="+$bank("#accountNum").val()+"&bankPid="+$bank("#style").val()+"&accountAddress="+$bank("#accountAddress").val();

			$bank.ajax({
				//使用json，/json是他的命名空间
				url:"<%=path%>/json/createUserBank", 
				type:"post",
				data:data,//使用这个方法可以使表单里面的内容自动提交上去
				dataType:"json",//返回类型为json对象
				success:function(data){
					if(data=="success"){
						window.location.href =  "<%=path%>/user/user_bank_list.jsp";
					}
				}
			});
		});

});
</script>
<script type="text/javascript">
var $page =  jQuery.noConflict();
function getJSONData() {
	$page("#ID1").hide();
	$page("#ID2").show();
}
$page(function() {  
	getJSONData();  
});
</script>

</head>
<body> 
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
                 <li class="current"onclick="secBoard('hotnews_caption','list',1);"><a href="<%=path %>/userbank/toUserBank">银行账户</a></li>
            </ul>
         </div>
         
         <div id="hotnews_content">
           <div class="current" id="list_1">
              <P> 尊敬的用户，提现操作涉及您的资金安全，敬请仔细操作 。</P>
              <div class="b_tip">
                请用户尽量填写较大银行（如农行、工行、建行，中国银行等），避免填写那些少见的银行（如农村信用社、平安银行、恒丰银行等）。否则提现资金很容易被退票。<br />
                请您填写完整的联系方式，以便遇到问题，工作人员可以及时的联系到您。<br />
                  
             </div>   
              <table width="825" cellspacing="0" cellpadding="0" class="bank_table"  >
                            <tbody>
                       
                            <tr>
                                <td class="td1">银行名称：</td>
                                <td class="td2">
                                 <div id="uboxstyle">
                                  <select name="style" id="style">
	                 	            <option value="0"  >-请选择银行-</option>
		                                    <c:forEach var="userbanks" items="${userbanks}">
		                                         <option  value="${userbanks.bankPid}">${userbanks.bankPname}</option>
					                        </c:forEach> 	  
                             </select>
                                </div>
                                <em style="display:none;color: red;" id="styleIdNone" >请选择银行</em>
                             </td>
                            </tr>
                            <tr>
                                <td class="td1">您银行账户户主的姓名：</td>
                                <td class="td2">
                                <em>${bankbasicinfor.realName}</em><em>*</em>
                                <span id="aName">姓名必须和银行卡开户姓名一致</span> 
                                <em style="display:none;color: red;" id="aNameNone" >请输入正确的姓名</em>
                                 <input style="display:none;color: red;"  name="accountNums" id="realNameNone" type="text" size="24"  value="${uservip.nameResult}"/>
                                 <em style="display:none;color: red;" id="realName" >添加银行卡必须进行实名验证，<a href="<%=path%>/user/create?mark=3" style="color: red">立马验证</a>  </em> 
                                </td>
                             </tr>
                            <tr>
                                <td class="td1">输入您新的银行账号：</td>
                                <td class="td2"><input name="accountNum" id="accountNum" type="text" size="24" /><em>*</em><span id="aNum">信用卡账号无法提现，请不要误填。</span> <em style="display:none;color: red;" id="aNumNone" >请输入正确的银行卡号</em></td>
                            </tr>
                            <tr>
                                <td class="td1">确认您新的银行账号：</td>
                               <td class="td2"><input name="accountNums" id="accountNums" type="text" size="24" /><em>*</em><span id="aNums">请再次输入您添加的新银行账号</span> <em style="display:none;color: red;" id="aNumsNone" >银行卡号必须一致</em></td>
                            </tr> 
                     
                             <tr>
                                <td class="td1">开户行支行的名称：</td>
                                <td class="td2"><input name="accountAddress" id="accountAddress" type="text" size="24" /><em>*</em><span id="aAddress">如果不能确定，请拨打开户行客服热线咨询。</span> <em style="display:none;color: red;" id="aAddressNone" >开户行不能为空或超过100字</em></td>       
                             </tr>
                      
                            <tr height="60">
                                <td colspan="2" align="center" ><div id="submit" > <input type="button" class="submit_bt" value="确认提交" /></div></th>
                            </tr>
                            
                      </tbody></table>
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