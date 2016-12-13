<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%    
	String path = request.getContextPath();if(path!=null && !path.equals("")){	if(path.contains("/")){		if(path.equals("/")){path = "";	}}else{	path += "/";}}    
	String basePath = "";if(request.getServerPort()==80){basePath = request.getScheme()+"://"+request.getServerName()+path+"/";}else{basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";}      

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>个人中心-我要充值</title>
<link href="<%=basePath %>/css/public.css" type="text/css"
	rel="stylesheet" />
<link href="<%=basePath %>/css/user/user_public.css" type="text/css"
	rel="stylesheet" />
<link href="<%=basePath %>/css/user/user_chongzhi.css" type="text/css"
	rel="stylesheet" />
<link rel="icon" href="<%=path %>/image/favicon/favicon.ico"
	type="image/x-icon" />

<!--  js-->
<script type="text/javascript"
	src="<%=basePath %>/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/js/ajaxfileupload.js"></script>
<script type="text/javascript">

function ajaxFileUpload(mark,str)
{var  $p=jQuery.noConflict();
	$p.ajaxFileUpload
	(
		{
			url:'<%=path %>/pic/toSavePic',//用于文件上传的服务器端请求地址
			secureuri:false,//一般设置为false
			fileElementId:str,//文件上传空间的id属性  <input type="file" id="file" name="file" />
			dataType: 'json',//返回值类型 一般设置为json
			success: function (data, status)  //服务器成功响应处理函数
			{
				if(data.message=="你已成功上传文件"){
					$p("#fileTip").html("<span style='font-size:12px;color:green'>"+data.message+"</span>");
					$p("#xsId").html("<img src='<%=basePath %>"+data.urlStr+"' name='offlinerecharge.printProof' style='width: 200px;height: 150px' />");
					document.getElementById("imgUrlId").value=data.urlStr;
 				}else{
					$p("#fileTip").html("<span style='font-size:12px;color:red'>"+data.message+"</span>");

				}
				//alert(data.message);//从服务器返回的json中取出message中的数据,其中message为在struts2中定义的成员变量
			},
			error: function (data, status, e)//服务器响应失败处理函数
			{
				alert(e);
			}
		}
	)
	
	return false;

}


</script>
<!--左侧折叠菜单-->

<!-- 验证 -->
<script type="text/javascript" src="<%=basePath %>/js/chongzhi.js"></script>
<script type="text/javascript" src="<%=basePath %>/js/menu.js"></script>
<script type="text/javascript">  
function setImagePreview() {          
    var docObj=document.getElementById("upFileId"); 
    var vau=docObj.value.substring(docObj.value.lastIndexOf("."));
     if(vau!=".jpg"&&vau!=".png"&&vau!=".gif"&&vau!=".bmp"){
    	alert("图片格式错误！");
    	docObj.value="";
    	return false;
    }else{
    	return true;
    }
  }

 function toMoneyPage(num){
	 
	  var  $MP=jQuery.noConflict();
	  var page=$MP("#currentPageId").val();
	  var pageNum=$MP("#pageNumId").val();
	  var p=$MP("#pId").val();
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
			  $MP("#page1-1").attr("href","<%=path %>/borrow/toNote?userId=${session.uservip.userId}&page=1"); 
	  }
	  if(num==3){
		  if(Number(page)<Number(pageNum)){
			  $MP("#page1-3").attr("href","<%=path %>/borrow/toNote?userId=${session.uservip.userId}&page="+(Number(page)+1));
		  }
	  }
	  if(num==2){
		  if(page>1){
			  $MP("#page1-2").attr("href","<%=path %>/borrow/toNote?userId=${session.uservip.userId}&page="+(Number(page)-1));
		  }
	  }
	  if(num==4){
		  if(p<1){
			  p=1;
		  }else if(Number(p)>Number(pageNum)){
			  p=pageNum;
		  }
	 
			  $MP("#page1-4").attr("href","<%=path %>/borrow/toNote?userId=${session.uservip.userId}&page="+Number(p));
	  }
	  
}
   
</script>
<script type="text/javascript">
function load(){
var nameResult=${session.uservip.nameResult}+7;
var phoneResult=${session.uservip.phoneResult}+7;
var enable=${session.uservip.enable}+7;
var trustStatus=${session.uservip.trustStatus}+7;
var authorizeStatus=${session.uservip.authorizeStatus}+7;
if(nameResult==9&&phoneResult==8&&enable==8&&trustStatus==8&&authorizeStatus==8){
}else
{
document.getElementById("firstbtn1").style.cssText = "background-color:#CCC;cursor:default;";
document.getElementById("firstbtn1").disabled=true;
document.getElementById("tishiyanzheng").innerHTML="请进入"+"<a id='yanzhena' href="+"'"+"<%=path %>/user/login?#yanzhengzhongxin"+"'"+">充值验证中心</a>"+"完成验证";
}
}
</script>
<script type="text/javascript">
 
 var $page =  jQuery.noConflict();
 function getJSONData() {
	var mark=document.getElementById("markId").value;
	var userLeftFlag=document.getElementById("userLeftFlagId").value;
  	if(mark==-1){
 		secBoard('hotnews_caption','list',2);
 	}if(mark==-2){
 		secBoard('hotnews_caption','list',2);
 	}
 	if(userLeftFlag==2){
 		$page("#ID1").hide();
 		$page("#ID2").show();
  	}
 }
 
 $page(function() {  
 	getJSONData();  
 });
 </script>


</head>
<body onload="load()">
	<input value="2" id="userLeftFlagId" type="hidden" />
	<!--头部开始-->
	<div class="header">
		<jsp:include page="../header.jsp" />
	</div>
	<!--头部结束-->
	<input value="${mark}" id="markId" type="hidden" />
	<div class="maincontent">

		<div class="conbox fn_cle">
			<jsp:include page="../user/left.jsp" />
		</div>



		<div class="user_r">
			<div id="hotnews_caption">
				<ul id="h_menu">
					<li class="current"><a
						href="<%=path %>/borrow/toChongzhi?userId=${session.uservip.userId}">在线充值</a>
					</li>
					<%--<li>${session.uservip.nameResult }</li>-->
                 <%-- <li class="normal"  ><a href="<%=path %>/borrow/toOffLine" >线下充值</a></li> --%>
					<li class="normal"><a
						href="<%=path %>/borrow/toNote?userId=${session.uservip.userId}">充值记录</a>
					</li>
				</ul>
			</div>

			<div id="hotnews_content">
				<div class="current" id="list_1">
					<form action="<%=path %>/borrow/trustRecharge" method="post"
						id="payID" target="_blank">
						<p class="tishi">温馨提示：最低充值金额50元。充值免手续费！充值资金可用于进行验证、投标、还款等。充值成功后资金会立刻划拨到您的帐户。</p>
						<div class="xBox">
							<div class="top">
								<p>
									账户余额：<span><fmt:formatNumber value="${yeMoney}"
											pattern="0.00" /> </span>&nbsp;元
								</p>
								<p>
									充值金额：<input type="text" name="chongzhiMoney"
										onmousedown="toQC()" id="testId" value="0.00" />元 <span
										id="tsID" style="color:red;"></span>
								</p>
							</div>
							<input style="" id="basePathID" value="<%=basePath %>"
								type="hidden" />
							<!--   <div class="bottom">
                        <div id="button">
                        	<input id="firstbtn"  disabled=true type="button" class="submit_bt" onclick="toPay()" value="快捷支付" />
                        </div>
                    </div>-->

							<div class="bottom">
								<div id="button">
									<input id="firstbtn1" type="button" class="submit_bt"	onclick="toPay()" value="快捷支付" />
								</div><span id="tishiyanzheng"></span>
							</div>

						</div>
						<!--  <input type="button" onclick="disableElement1()" value="取消按钮"></input> -->
						<p>
							<img src="<%=basePath %>/images/biao_06.png" />在您使用以上各家银行充值之前，请注意各个银行的支付金额的上限。
						</p>
					</form>
				</div>


				<%-- <div class="normal" id="list_2">
		    <p><img src="<%=basePath %>/images/xing_03.png" /> 请准确填写您的充值金额和帐单流水号</p>
		    <form action="" id="saveOffLineId" method="post" enctype="multipart/form-data"> 
		    <input name="userId" value="${session.uservip.userId}" style="display: none"/>
            <input name="mark" value="${mark}" style="display: none"/>
                <table class="table" border="1" bordercolor="#CCCCCC" width="850px">
                   <c:forEach var="gg" items="${companybankinforList}">
                	<tr>
                    	<td class="fr"><input type="radio" name="RadioGroup1"  value="${gg.companyBankInforId}" />${gg.bankparameter.bankPname}</td>
                        <td>开户名：${gg.accountName } 账户号：${gg.accountNum } 开户地址：${gg.accountAddress } 
                   		
                    		
                    </tr>
                   </c:forEach> 
                     <tr>
                    	<td class="fr">充值金额：</td>
                        <td><input  name="offlinerecharge.rechargeAmount" id="rechargeAmountId"   onkeyup="value=value.replace(/[^0-9]/g,'')"  class="zhi" type="text" />(如：1千元填1000)</td>
                    </tr>
                    <tr>
                    	<td class="fr">账单流水号：</td>
                        <td><input  name="offlinerecharge.billSerialNum" id="billSerialNumId"  class="zhi" type="text" /></td>
                    </tr>
                    <tr>
                    	<td class="fr">充值方式：</td>
                        <td><input  name="offlinerecharge.recharge" id="rechargeId"  class="zhi" type="text" />(如网银转帐，柜台汇款,汇款人等)</td>
                    </tr>
                    <tr>
                    	<td class="fr">打款凭证：</td>
                        <td>
                        	<div><input type="file" id="file" name="file" onchange="ajaxFileUpload('0','file')" style="border:0;background:transparent;width:250px;"/>
                        	 (支持.jpg.bmp.gif.png格式文件)
                        <!-- 	<input type="button"  value="上传" onclick="return ajaxFileUpload('0','file');" style="border:0;width:70px;"> -->
                        	<span id="fileTip"></span></div>
							<div id="xsId" style="display: ">  
							</div>
							    <input id="imgUrlId" name="offlinerecharge.printProof" value="1" style="display: none">
                        </td>
                    </tr>
                    <tr>
                    	<td>线下充值备注：</td>
                        <td>您转账成功后，请您保留银行转账电子回单或者流水号，提交给客服，方便为您快速处理到帐，谢谢您的支持！</td>
                    </tr>
                </table>
				<div id="submit" >
                 <input type="button" class="submit_bt" onclick="onChongzhi()" value="提&nbsp;&nbsp;交" />
                 </div></form>
           </div> --%>


				<div class="normal" id="list_2">
					<p>
						<img src="<%=basePath %>/images/xing_03.png" /> 请准确填写您的充值金额和帐单流水号
					</p>
					<p class="tishi">
						截止<span><%=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date())%>
						</span>您的充值成功金额是：<span> ￥${successMoney }</span>, 充值失败金额是：<span>
							￥${fasleMoney }</span>, 审核金额是：<span> ￥${otherMoney }</span>。
					</p>
					<table class="table1" border="1" bordercolor="#CCCCCC"
						width="850px;">
						<tr>
							<th>编号</th>
							<th>充值时间</th>
							<th>充值金额</th>
							<th>充值状态</th>
						</tr>
						<c:forEach var="yy" items="${offlinerechargeList}">
							<tr>
								<td>${yy.offlineRechargeId}</td>
								<td><fmt:formatDate value="${yy.rechargeTime}"
										pattern="yyyy-MM-dd HH:mm" /></td>
								<!--                        <td>￥${yy.rechargeAmount}</td>-->
								<td>￥<fmt:formatNumber type="currency"
										value="${yy.rechargeAmount}" pattern="0.00" /></td>
								<c:if test="${yy.rechargeStatus==1 }">
									<td>充值成功</td>
								</c:if>
								<c:if test="${yy.rechargeStatus==2 }">
									<td>充值失败</td>
								</c:if>
								<c:if test="${yy.rechargeStatus==3 }">
									<td>审核中</td>
								</c:if>

							</tr>
						</c:forEach>


					</table>
					<div class="page"
						style="float: left;font-size: 14px;height: 35px;margin-top: 20px;padding: 10px 0;text-align: center; width: 845px;">
						<a href="" onclick="toMoneyPage('1')" id="page1-1"
							style="background: none repeat scroll 0 0 #f6f6f6;border-color: #dbdbdb;border-style: solid;border-width: 1px;color: #555;margin: 3px 7px;padding: 4px 9px;text-decoration: none;">首页</a>
						<a href="" onclick="toMoneyPage('2')" id="page1-2"
							style="background: none repeat scroll 0 0 #f6f6f6;border-color: #dbdbdb;border-style: solid;border-width: 1px;color: #555;margin: 3px 7px;padding: 4px 9px;text-decoration: none;">上一页</a>
						<a href="" onclick="toMoneyPage('3')" id="page1-3"
							style="background: none repeat scroll 0 0 #f6f6f6;border-color: #dbdbdb;border-style: solid;border-width: 1px;color: #555;margin: 3px 7px;padding: 4px 9px;text-decoration: none;">下一页</a>
						<span class="skip"><a href="#"
							onclick="return toMoneyPage('4')" id="page1-4"
							style="background: none repeat scroll 0 0 #f6f6f6;border-color: #dbdbdb;border-style: solid;border-width: 1px;color: #555;margin: 3px 7px;padding: 4px 9px;text-decoration: none;">
								转到</a><input name="page" type="text" id="pId" value="" size="3"
							style="height:26px; line-height:26px;" />页</span>
						第${page}页/共${pageCount}页 <input name="currentPage" value="${page}"
							id="currentPageId" style="display: none" /> <input name="total"
							value="${pageCount}" id="pageNumId" style="display: none" />
					</div>
				</div>

				<script type="text/javascript" src="<%=basePath %>/js/tabs.js"
					language="javascript"></script>

			</div>


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
</div> --%>
	<!--<script type="text/javascript" src="<%=basePath %>/js/service.js">	</script>-->
	<!--右边漂浮 结束-->

</body>
</html>