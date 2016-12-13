<%@page import="com.jqg.service.impl.IPServiceImpl"%>
<%@page import="com.jqg.service.IPService"%>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
  <%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath(); 
String basePath = "";if(request.getServerPort()==80){basePath = request.getScheme()+"://"+request.getServerName()+path+"/";}else{basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";}    
IPService ipService = new IPServiceImpl();
String ip = ipService.getIpAddr(request);
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../back/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery.js"></script>

<script>
function select_all(id){
	var  $m=jQuery.noConflict();
	var se = id+"_son";
	if($m("#"+id).attr('checked')){
		$m("input:[data="+se+"]").each(function(i,obj){
			$m(obj).attr('checked','true');
		
		});
	}else{
		$m("input:[data="+se+"]").each(function(i,obj){
			$m(obj).attr('checked','');
		
		});
	}

}

</script>
<script type="text/javascript" src="../js/alert.js"></script>
<script type="text/javascript">
	function tijiao(){
		var  $m=jQuery.noConflict();
		var roleName=$m("#roleName").val();
		var roleId=$m("#roleId").val();
		var flag1="0";
		if(roleName==''){
			Alert("请输入用户组名！");
			return false;
		}else{//ajax请求判断用户是否存在
			var data = "roleName="+roleName+"&roleId="+roleId;
			$m.ajax({
				async:false,
				url:"<%=path%>/json/checkRole", 
				type:"post",
				data:data,//使用这个方法可以使表单里面的内容自动提交上去
				dataType:"json",//返回类型为json对象
				success:function(data){
					if(data.result=="error"){
						Alert("该角色已经存在！");
						flag1="1";
					}else{
						flag1="2";
					}
				}
			});
		}
		if(flag1=="1"){
			return false;
		}
        //全局设置
		var globalss=document.getElementsByName("model[global][]");
		var globals="";
		for(var i=0;i<globalss.length;i++){
			if(globalss[i].checked){
				globals+="1"+",";
			}else{
				globals+="0"+",";
			}
		}
		//借款管理
		var borrows=document.getElementsByName("model[borrow][]");
		var borrow="";
		for(var i=0;i<borrows.length;i++){
			if(borrows[i].checked){
				borrow+="1"+",";
			}else{
				borrow+="0"+",";
			}
		}
		//会员管理
		var members=document.getElementsByName("model[members][]");
		var member="";
		for(var i=0;i<members.length;i++){
			if(members[i].checked){
				member+="1"+",";
			}else{
				member+="0"+",";
			}
		}
		//充值提现
		var paylogs=document.getElementsByName("model[paylog][]");
		var paylog="";
		for(var i=0;i<paylogs.length;i++){
			if(paylogs[i].checked){
				paylog+="1"+",";
			}else{
				paylog+="0"+",";
			}
		}
		//文章管理
		var articles=document.getElementsByName("model[article][]");
		var article="";
		for(var i=0;i<articles.length;i++){
			if(articles[i].checked){
				article+="1"+",";
			}else{
				article+="0"+",";
			}
		}
		//导航菜单管理管理
		var navigations=document.getElementsByName("model[navigation][]");
		var navigation="";
		for(var i=0;i<navigations.length;i++){
			if(navigations[i].checked){
				navigation+="1"+",";
			}else{
				navigation+="0"+",";
			}
		}
		//资金统计
		var capitalaccounts=document.getElementsByName("model[capitalaccount][]");
		var capitalaccount="";
		for(var i=0;i<capitalaccounts.length;i++){
			if(capitalaccounts[i].checked){
				capitalaccount+="1"+",";
			}else{
				capitalaccount+="0"+",";
			}
		}
		//权限管理
		var acls=document.getElementsByName("model[acl][]");
		var acl="";
		for(var i=0;i<acls.length;i++){
			if(acls[i].checked){
				acl+="1"+",";
			}else{
				acl+="0"+",";
			}
		}
		//管理员管理
		var adminusers=document.getElementsByName("model[adminuser][]");
		var adminuser="";
		for(var i=0;i<adminusers.length;i++){
			if(adminusers[i].checked){
				adminuser+="1"+",";
			}else{
				adminuser+="0"+",";
			}
		}
		//扩展管理
		var mfields=document.getElementsByName("model[mfields][]");
		var mfield="";
		for(var i=0;i<mfields.length;i++){
			if(mfields[i].checked){
				mfield+="1"+",";
			}else{
				mfield+="0"+",";
			}
		}
		document.getElementById("bt").setAttribute("disabled", "disabled");
		var form=document.getElementById("tijiao");
		form.action="<%=basePath%>/power/savePowers?globals="+globals+"&borrow="+borrow+"&member="+member+"&paylog="+paylog+"&article="+article+"&navigation="+navigation+"&capitalaccount="+capitalaccount+"&acl="+acl+"&adminuser="+adminuser+"&mfield="+mfield;
		form.submit();
		//按钮设置
		document.getElementById("bt").style.background = "gray";
   		document.getElementById("bt").value="正在提交...";
        document.getElementById("bt").style.disabled=false;
	}
</script>

<style>
.set_form{ padding:10px; float:left; }
.lineD input { color: #333333;padding: 5px; margin:0px 4px 0px 8px;}

</style>
</head>


<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#" onclick="parent.frames[1].location.href='<%=basePath%>/back/1_left.jsp';  parent.frames[2].location.href='<%=basePath%>/overall/index'; ">首页</a></li>
    <li><a href="#" onclick="parent.frames[1].location.href='<%=basePath%>/back/8_left.html';  parent.frames[2].location.href='<%=basePath%>/back/8_list.jsp'; ">管理员管理</a></li>
    <li><a href="#">用户级权限配置</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
      <div class="set">
       <form method="post" class="set_form" id="tijiao" style="float:none;'">
       
       <dl class="lineD" style="float:none">
          <dt>用户组名称：</dt>
	      <dd><input type="text" value="${roleName}" size="30" class="input" id="roleName" name="roleName"></dd>
          <div style="clear:both"></div>
       </dl> 
       <dl class="lineD" style="float:none">
          <dt><b>全局设置</b></dt>
	      <dd>请选择相关权限<input type="checkbox" id="fa0" onclick="select_all('fa0');"><label for="fa0">全选</label></dd>
	      <div style="clear:both"></div>
       </dl> 
       <dl class="lineD"  style="float:none">
		  <dt>网站设置：</dt>
		  <dd>
								<input data="fa0_son" style="DISPLAY:none" name="model[global][]" class="check" <c:if test="${powers[0]==1}">checked="checked"</c:if> value="at1" id="global1" type="checkbox"><label for="globalat1" style="DISPLAY:none">列表</label>
								<input data="fa0_son" name="model[global][]" class="check" <c:if test="${powers[1]==1}">checked="checked"</c:if> value="at4" id="global4" type="checkbox"><label for="globalat4">修改</label>
	      </dd>
	   </dl> 
       <dl class="lineD" style="float:none">
		  <dt>友情链接：</dt>
		  <dd>
								<input data="fa0_son" style="DISPLAY:none" name="model[global][]" class="check" <c:if test="${powers[2]==1}">checked="checked"</c:if> value="at5" id="globalat5" type="checkbox"><label for="globalat5" style="DISPLAY:none">列表</label>
								<input data="fa0_son" name="model[global][]" class="check" <c:if test="${powers[3]==1}">checked="checked"</c:if> value="at6" id="globalat6" type="checkbox"><label for="globalat6">增加</label>
								<input data="fa0_son" name="model[global][]" class="check" <c:if test="${powers[4]==1}">checked="checked"</c:if> value="at7" id="globalat7" type="checkbox"><label for="globalat7">删除</label>
								<input data="fa0_son" name="model[global][]" class="check" <c:if test="${powers[5]==1}">checked="checked"</c:if> value="at8" id="globalat8" type="checkbox"><label for="globalat8">修改</label>
		 </dd>
		</dl> 
		 <dl class="lineD" style="float:none">
		  <dt>公司信息：</dt>
		  <dd>
								<input data="fa0_son" name="model[global][]" class="check" <c:if test="${powers[115]==1}">checked="checked"</c:if> value="at25" id="globalat25" type="checkbox"><label for="globalat25">修改</label>
		 </dd>
		</dl> 
         <dl class="lineD" style="float:none">
		  <dt>后台操作日志：</dt>
		  <dd>
								<input data="fa0_son" style="DISPLAY:none" name="model[global][]" class="check" <c:if test="${powers[6]==1}">checked="checked"</c:if> value="at23" id="globalat23" type="checkbox"><label for="globalat23" style="DISPLAY:none">列表</label>
								<input data="fa0_son" name="model[global][]" class="check" <c:if test="${powers[7]==1}">checked="checked"</c:if> value="at24" id="globalat24" type="checkbox"><label for="globalat24">搜索</label>
		  </dd>
		</dl>
        <dl class="lineD" style="float:none">
          <dt><b>借款管理</b></dt>
	      <dd>请选择相关权限<input onClick="select_all('fa1');" id="fa1" type="checkbox"><label for="fa1">全选</label></dd>
        </dl>
        <dl class="lineD" style="float:none">
		  <dt>初审待审核借款：</dt>
		  <dd>
								<input data="fa1_son" style="DISPLAY:none" name="model[borrow][]" class="check" <c:if test="${powers[8]==1}">checked="checked"</c:if> value="br1" id="borrowbr1" type="checkbox"><label for="borrowbr1" style="DISPLAY:none">列表</label>
								<input data="fa1_son" name="model[borrow][]" class="check" <c:if test="${powers[9]==1}">checked="checked"</c:if> value="br2" id="borrowbr2" type="checkbox"><label for="borrowbr2">审核</label>
								<input data="fa1_son" name="model[borrow][]" class="check" <c:if test="${powers[89]==1}">checked="checked"</c:if> value="br2" id="borrowbr16" type="hidden">
		 </dd>
		</dl>
        <dl class="lineD" style="float:none">
		  <dt>复审待审核借款：</dt>
		  <dd>
								<input data="fa1_son" style="DISPLAY:none" name="model[borrow][]" class="check" <c:if test="${powers[10]==1}">checked="checked"</c:if> value="br3" id="borrowbr3" type="checkbox"><label for="borrowbr3" style="DISPLAY:none">列表</label>
								<input data="fa1_son" name="model[borrow][]" class="check" <c:if test="${powers[11]==1}">checked="checked"</c:if> value="br4" id="borrowbr4" type="checkbox"><label for="borrowbr4">审核</label>
								<input data="fa1_son" name="model[borrow][]" class="check" <c:if test="${powers[90]==1}">checked="checked"</c:if> value="br4" id="borrowbr17" type="hidden">
		 </dd>
		</dl> 
        <dl class="lineD" style="float:none">
		  <dt>招标中的借款：</dt>
		  <dd>
								<input data="fa1_son" style="DISPLAY:none" name="model[borrow][]" class="check" <c:if test="${powers[12]==1}">checked="checked"</c:if> value="br5" id="borrowbr5" type="checkbox"><label for="borrowbr5" style="DISPLAY:none">列表</label>
								<input data="fa1_son" name="model[borrow][]" class="check" <c:if test="${powers[13]==1}">checked="checked"</c:if> value="br6" id="borrowbr6" type="checkbox"><label for="borrowbr6">流标</label>
								<input data="fa1_son" name="model[borrow][]" class="check" <c:if test="${powers[91]==1}">checked="checked"</c:if> value="br4" id="borrowbr18" type="hidden">
								
		</dd>
		</dl>
        <dl class="lineD" style="float:none">
		  <dt>还款中的借款：</dt>
		  <dd>
								<input data="fa1_son" name="model[borrow][]" style="DISPLAY:none" class="check" <c:if test="${powers[14]==1}">checked="checked"</c:if> value="br7" id="borrowbr7" type="checkbox"><label for="borrowbr7" style="DISPLAY:none">列表</label>
<!-- 								<input data="fa1_son" name="model[borrow][]" class="check" value="br7" id="borrowbr7" type="checkbox"><label for="borrowbr8">一周内到期标</label> -->
								<input data="fa1_son" name="model[borrow][]" class="check" <c:if test="${powers[93]==1}">checked="checked"</c:if> value="br15" id="borrowbr15" type="checkbox"><label for="borrowbr15">操作</label>
								<input data="fa1_son" name="model[borrow][]" class="check" <c:if test="${powers[92]==1}">checked="checked"</c:if> value="br4" id="borrowbr20" type="hidden">
								<input data="fa1_son" name="model[borrow][]" class="check" <c:if test="${powers[15]==1}">checked="checked"</c:if> value="br4" id="borrowbr21" type="checkbox"><label for="borrowbr21">投资人记录</label>
								<input data="fa1_son" name="model[borrow][]" class="check" <c:if test="${powers[116]==1}">checked="checked"</c:if> value="br27" id="borrowbr27" type="checkbox"><label for="borrowbr27">到期还款提醒</label>
		</dd>
		</dl>
        <dl class="lineD" style="float:none">
		  <dt>已完成的借款：</dt>
		  <dd>
				<input data="fa1_son" name="model[borrow][]" class="check" <c:if test="${powers[16]==1}">checked="checked"</c:if> value="br9" id="borrowbr9" type="checkbox" style="DISPLAY:none"><label for="borrowbr9" style="DISPLAY:none">列表</label>
				<input data="fa1_son" name="model[borrow][]" class="check" <c:if test="${powers[94]==1}">checked="checked"</c:if> value="br9" id="borrowbr22" type="hidden">
				<input data="fa1_son" name="model[borrow][]" class="check" <c:if test="${powers[95]==1}">checked="checked"</c:if> value="br9" id="borrowbr23" type="checkbox"><label for="borrowbr23">投资人记录</label>
		 </dd>
		</dl>
        <dl class="lineD" style="float:none;display: none;">
		  <dt>初审未通过的借款：</dt>
		  <dd>
			<input data="fa1_son" name="model[borrow][]" class="check" style="DISPLAY:none" <c:if test="${powers[17]==1}">checked="checked"</c:if> value="br13" id="borrowbr13" type="checkbox"><label for="borrowbr13" style="DISPLAY:none">列表</label>
			<input data="fa1_son" name="model[borrow][]" class="check" <c:if test="${powers[96]==1}">checked="checked"</c:if> value="br13" id="borrowbr24" type="checkbox"><label for="borrowbr24">搜索</label>
		 </dd>
		</dl>
        <dl class="lineD" style="float:none;display: none;">
		  <dt>复审未通过的借款：</dt>
		  <dd>
					<input data="fa1_son" name="model[borrow][]" class="check" <c:if test="${powers[18]==1}">checked="checked"</c:if> value="br14" id="borrowbr14" type="checkbox" style="DISPLAY:none"><label for="borrowbr14" style="DISPLAY:none">列表</label>
					<input data="fa1_son" name="model[borrow][]" class="check" <c:if test="${powers[97]==1}">checked="checked"</c:if> value="br14" id="borrowbr25" type="checkbox"><label for="borrowbr25">搜索</label>
		  </dd>
		</dl> 
        <dl class="lineD" style="float:none">
		  <dt>逾期借款管理：</dt>
		  <dd>
								<input data="fa1_son" name="model[borrow][]" class="check" <c:if test="${powers[19]==1}">checked="checked"</c:if> value="yq1" id="borrowbr10" type="checkbox" style="DISPLAY:none"><label for="borrowbr10" style="DISPLAY:none">列表</label>
								<input data="fa1_son" name="model[borrow][]" class="check" <c:if test="${powers[98]==1}">checked="checked"</c:if> value="yq1" id="borrowbr26" type="checkbox"><label for="borrowbr26">搜索</label>
								<input data="fa1_son" name="model[borrow][]" class="check" <c:if test="${powers[117]==1}">checked="checked"</c:if> value="yq1" id="borrowbr28" type="checkbox"><label for="borrowbr28">操作</label>
<!-- 								<input data="fa1_son" name="model[borrow][]" class="check" value="yq2" id="borrowbr11" type="checkbox"><label for="borrowbr11">代还</label> -->
		</dd>
		</dl>
        <dl class="lineD" style="float:none">
            <dt><b>会员管理</b></dt>
	       <dd>请选择相关权限<input onClick="select_all('fa2');" id="fa2" type="checkbox"><label for="fa2">全选</label></dd>
        </dl>
        <dl class="lineD" style="float:none">
		  <dt>会员列表：</dt>
		  <dd>
								<input data="fa2_son" style="DISPLAY:none" name="model[members][]" class="check" <c:if test="${powers[20]==1}">checked="checked"</c:if> value="me1" id="membersme1" type="checkbox"><label for="membersme1" style="DISPLAY:none">列表</label>
								<input data="fa2_son" name="model[members][]" class="check" <c:if test="${powers[21]==1}">checked="checked"</c:if> value="mx2" id="membersmx2" type="checkbox"><label for="membersmx2">调整余额</label>
								<input data="fa2_son" name="model[members][]" class="check" <c:if test="${powers[22]==1}">checked="checked"</c:if> value="mx3" id="membersmx3" type="checkbox"><label for="membersmx3">调整授信</label>
								<input data="fa2_son" name="model[members][]" class="check" <c:if test="${powers[23]==1}">checked="checked"</c:if> value="mxw" id="membersmxw" type="checkbox"><label for="membersmxw">修改信息</label>
								<input data="fa2_son" name="model[members][]" class="check" <c:if test="${powers[24]==1}">checked="checked"</c:if> value="mxw" id="membersmxw" type="checkbox"><label for="membersmxw">搜索会员</label>
		 </dd>
		</dl>
		<dl class="lineD" style="DISPLAY:none;float:none;">
		<dt>邮箱未认证会员：</dt>
		  <dd>
								<input data="fa2_son" style="DISPLAY:none" name="model[members][]" class="check" <c:if test="${powers[25]==1}">checked="checked"</c:if> value="vphone1" id="verifyphonevphone1" type="checkbox"><label for="verifyphonevphone1" style="DISPLAY:none">列表</label>
								<input data="fa2_son" name="model[members][]" class="check" <c:if test="${powers[26]==1}">checked="checked"</c:if> value="vphone3" id="verifyphonevphone3" type="checkbox"><label for="verifyphonevphone3">审核</label>
						  </dd>
		</dl>
		<dl class="lineD" style="DISPLAY:none;float:none;">
		<dt>手机未认证会员：</dt>
		  <dd>
								<input data="fa2_son" name="model[members][]" class="check" <c:if test="${powers[27]==1}">checked="checked"</c:if> value="vphone1" id="verifyphonevphone1" type="checkbox" style="DISPLAY:none"><label for="verifyphonevphone1" style="DISPLAY:none">列表</label>
								<input data="fa2_son" name="model[members][]" class="check" <c:if test="${powers[28]==1}">checked="checked"</c:if> value="vphone3" id="verifyphonevphone3" type="checkbox"><label for="verifyphonevphone3">审核</label>
						  </dd>
		</dl>
		<dl class="lineD" style="float:none">
		  <dt>视频认证申请：</dt>
		  <dd>
								<input data="fa2_son" name="model[members][]" class="check" <c:if test="${powers[29]==1}">checked="checked"</c:if> value="vpv1" id="verifyvideovpv1" type="checkbox" style="DISPLAY:none"><label for="verifyvideovpv1" style="DISPLAY:none">列表</label>
								<input data="fa2_son" name="model[members][]" class="check" <c:if test="${powers[30]==1}">checked="checked"</c:if> value="vpv2" id="verifyvideovpv2" type="checkbox"><label for="verifyvideovpv2">审核</label>
						  </dd>
		</dl>
		<dl class="lineD" style="float:none">
		  <dt>现场认证申请：</dt>
		  <dd>
								<input data="fa2_son" name="model[members][]" class="check" <c:if test="${powers[31]==1}">checked="checked"</c:if> value="vface1" id="verifyfacevface1" type="checkbox" style="DISPLAY:none"><label for="verifyfacevface1" style="DISPLAY:none">列表</label>
								<input data="fa2_son" name="model[members][]" class="check" <c:if test="${powers[32]==1}">checked="checked"</c:if> value="vface2" id="verifyfacevface2" type="checkbox"><label for="verifyfacevface2">审核</label>
						  </dd>
		</dl>
		<dl class="lineD" style="float:none">
		  <dt>实名认证申请：</dt>
		  <dd>
								<input data="fa2_son" name="model[members][]" class="check" <c:if test="${powers[33]==1}">checked="checked"</c:if> value="vface1" id="verifyfacevface1" type="checkbox" style="DISPLAY:none"><label for="verifyfacevface1" style="DISPLAY:none">列表</label>
								<input data="fa2_son" name="model[members][]" class="check" <c:if test="${powers[34]==1}">checked="checked"</c:if> value="vface2" id="verifyfacevface2" type="checkbox"><label for="verifyfacevface2">审核</label>
						  </dd>
		</dl>
		<dl class="lineD" style="float:none">
		  <dt>信用额度申请：</dt>
		  <dd>
								<input data="fa2_son" name="model[members][]" class="check" <c:if test="${powers[35]==1}">checked="checked"</c:if> value="vface1" id="verifyfacevface1" type="checkbox" style="DISPLAY:none"><label for="verifyfacevface1" style="DISPLAY:none">列表</label>
								<input data="fa2_son" name="model[members][]" class="check" <c:if test="${powers[36]==1}">checked="checked"</c:if> value="vface2" id="verifyfacevface2" type="checkbox"><label for="verifyfacevface2">审核</label>
						  </dd>
		</dl>
		<dl class="lineD" style="float:none">
		  <dt>其他认证申请：</dt>
		  <dd>
								<input data="fa2_son" name="model[members][]" class="check" <c:if test="${powers[118]==1}">checked="checked"</c:if> value="vface2" id="verifyfacevface2" type="checkbox"><label for="verifyfacevface2">审核</label>
						  </dd>
		</dl>																		
		
    <dl class="lineD" style="float:none">
      <dt class="t"><b>充值提现</b></dt>
	  <dd>请选择相关权限<input onClick="select_all('fa4');" id="fa4" type="checkbox"><label for="fa4">全选</label></dd>
    </dl>
	
											<dl class="lineD" style="float:none">
		  <dt>充值记录：</dt>
		  <dd>
								<input data="fa4_son" name="model[paylog][]" class="check" <c:if test="${powers[37]==1}">checked="checked"</c:if> value="cz" id="paylogcz" type="checkbox" style="DISPLAY:none"><label for="paylogcz" style="DISPLAY:none">列表</label>
								<input data="fa4_son" name="model[paylog][]" class="check" <c:if test="${powers[38]==1}">checked="checked"</c:if> value="czgl" id="paylogczgl" type="checkbox"><label for="paylogczgl">充值处理</label>
						  </dd>
		</dl>
																									<dl class="lineD" style="float:none">
		  <dt>提现管理：</dt>
		  <dd>
								<input data="fa4_son" name="model[paylog][]" class="check" <c:if test="${powers[39]==1}">checked="checked"</c:if> value="cg2" id="withdrawlogcg2" type="checkbox" style="DISPLAY:none"><label for="withdrawlogcg2" style="DISPLAY:none">列表</label>
								<input data="fa4_son" name="model[paylog][]" class="check" <c:if test="${powers[40]==1}">checked="checked"</c:if> value="cg3" id="withdrawlogcg3" type="checkbox"><label for="withdrawlogcg3">审核</label>
						  </dd>
		</dl>	





<!-- 		<dl class="lineD" style="float:none"> -->
<!-- 		  <dt>线上充值：</dt> -->
<!-- 			  <dd> -->
<!-- 					<input data="fa4_son" name="model[paylog][]" class="check" <c:if test="${powers[122]==1}">checked="checked"</c:if> value="cz" id="paylogcz" type="checkbox" style="DISPLAY:none"><label for="paylogcz" style="DISPLAY:none">列表</label> -->
<!-- 					<input data="fa4_son" name="model[paylog][]" class="check" <c:if test="${powers[123]==1}">checked="checked"</c:if> value="czgl" id="paylogczgl" type="checkbox"><label for="paylogczgl">搜索</label> -->
<!-- 					<input data="fa4_son" name="model[paylog][]" class="check" <c:if test="${powers[127]==1}">checked="checked"</c:if> value="cg22" id="withdrawlogcg22" type="checkbox"><label for="withdrawlogcg8">导出</label> -->
<!-- 			  </dd> -->
<!-- 		</dl> -->
<!-- 		<dl class="lineD" style="float:none"> -->
<!-- 		  <dt>线下充值：</dt> -->
<!-- 			  <dd> -->
<!-- 					<input data="fa4_son" name="model[paylog][]" class="check" <c:if test="${powers[124]==1}">checked="checked"</c:if> value="cg2" id="withdrawlogcg2" type="checkbox" style="DISPLAY:none"><label for="withdrawlogcg2" style="DISPLAY:none">列表</label> -->
<!-- 					<input data="fa4_son" name="model[paylog][]" class="check" <c:if test="${powers[125]==1}">checked="checked"</c:if> value="cg4" id="withdrawlogcg5" type="checkbox"><label for="withdrawlogcg4">搜索</label> -->
<!-- 					<input data="fa4_son" name="model[paylog][]" class="check" <c:if test="${powers[126]==1}">checked="checked"</c:if> value="cg3" id="withdrawlogcg3" type="checkbox"><label for="withdrawlogcg3">充值操作</label> -->
<!-- 					<input data="fa4_son" name="model[paylog][]" class="check" <c:if test="${powers[127]==1}">checked="checked"</c:if> value="cg8" id="withdrawlogcg8" type="checkbox"><label for="withdrawlogcg8">导出</label> -->
<!-- 			  </dd> -->
<!-- 		</dl> -->
<!-- 		<dl class="lineD" style="float:none"> -->
<!-- 		  <dt>充值记录总表：</dt> -->
<!-- 			  <dd> -->
<!-- 					<input data="fa4_son" name="model[paylog][]" class="check" <c:if test="${powers[128]==1}">checked="checked"</c:if> value="cg5" id="withdrawlogcg5" type="checkbox" style="DISPLAY:none"><label for="withdrawlogcg5" style="DISPLAY:none">列表</label> -->
<!-- 					<input data="fa4_son" name="model[paylog][]" class="check" <c:if test="${powers[129]==1}">checked="checked"</c:if> value="cg7" id="withdrawlogcg7" type="checkbox"><label for="withdrawlogcg7">搜索</label> -->
<!-- 					<input data="fa4_son" name="model[paylog][]" class="check" <c:if test="${powers[130]==1}">checked="checked"</c:if> value="cg8" id="withdrawlogcg8" type="checkbox"><label for="withdrawlogcg8">导出</label> -->
<!-- 					<input data="fa4_son" name="model[paylog][]" class="check" <c:if test="${powers[131]==1}">checked="checked"</c:if> value="cg6" id="withdrawlogcg6" type="checkbox"><label for="withdrawlogcg6">充值操作</label> -->
<!-- 			  </dd> -->
<!-- 		</dl> -->
<!-- 		<dl class="lineD" style="float:none"> -->
<!-- 		  <dt>待审核提现：</dt> -->
<!-- 			  <dd> -->
<!-- 					<input data="fa4_son" name="model[paylog][]" class="check" <c:if test="${powers[132]==1}">checked="checked"</c:if> value="cg9" id="withdrawlogcg9" type="checkbox" style="DISPLAY:none"><label for="withdrawlogcg9" style="DISPLAY:none">列表</label> -->
<!-- 					<input data="fa4_son" name="model[paylog][]" class="check" <c:if test="${powers[133]==1}">checked="checked"</c:if> value="cg10" id="withdrawlogcg10" type="checkbox"><label for="withdrawlogcg10">搜索</label> -->
<!-- 					<input data="fa4_son" name="model[paylog][]" class="check" <c:if test="${powers[134]==1}">checked="checked"</c:if> value="cg11" id="withdrawlogcg11" type="checkbox"><label for="withdrawlogcg11">审核</label> -->
<!-- 			  </dd> -->
<!-- 		</dl> -->
<!-- 		<dl class="lineD" style="float:none"> -->
<!-- 		  <dt>审核通过，处理中：</dt> -->
<!-- 			  <dd> -->
<!-- 					<input data="fa4_son" name="model[paylog][]" class="check" <c:if test="${powers[135]==1}">checked="checked"</c:if> value="cg12" id="withdrawlogcg12" type="checkbox" style="DISPLAY:none"><label for="withdrawlogcg12" style="DISPLAY:none">列表</label> -->
<!-- 					<input data="fa4_son" name="model[paylog][]" class="check" <c:if test="${powers[136]==1}">checked="checked"</c:if> value="cg13" id="withdrawlogcg13" type="checkbox"><label for="withdrawlogcg13">搜索</label> -->
<!-- 					<input data="fa4_son" name="model[paylog][]" class="check" <c:if test="${powers[137]==1}">checked="checked"</c:if> value="cg14" id="withdrawlogcg14" type="checkbox"><label for="withdrawlogcg14">审核</label> -->
<!-- 			  </dd> -->
<!-- 		</dl> -->
<!-- 		<dl class="lineD" style="float:none"> -->
<!-- 		  <dt>已提现：</dt> -->
<!-- 			  <dd> -->
<!-- 					<input data="fa4_son" name="model[paylog][]" class="check" <c:if test="${powers[138]==1}">checked="checked"</c:if> value="cg15" id="withdrawlogcg15" type="checkbox" style="DISPLAY:none"><label for="withdrawlogcg15" style="DISPLAY:none">列表</label> -->
<!-- 					<input data="fa4_son" name="model[paylog][]" class="check" <c:if test="${powers[139]==1}">checked="checked"</c:if> value="cg16" id="withdrawlogcg16" type="checkbox"><label for="withdrawlogcg16">搜索</label> -->
					
<!-- 			  </dd> -->
<!-- 		</dl> -->
<!-- 		<dl class="lineD" style="float:none"> -->
<!-- 		  <dt>审核未通过：</dt> -->
<!-- 			  <dd> -->
<!-- 					<input data="fa4_son" name="model[paylog][]" class="check" <c:if test="${powers[140]==1}">checked="checked"</c:if> value="cg17" id="withdrawlogcg17" type="checkbox" style="DISPLAY:none"><label for="withdrawlogcg17" style="DISPLAY:none">列表</label> -->
<!-- 					<input data="fa4_son" name="model[paylog][]" class="check" <c:if test="${powers[141]==1}">checked="checked"</c:if> value="cg18" id="withdrawlogcg18" type="checkbox"><label for="withdrawlogcg18">搜索</label> -->
					
<!-- 			  </dd> -->
<!-- 		</dl> -->
<!-- 		<dl class="lineD" style="float:none"> -->
<!-- 		  <dt>提现申请总表：</dt> -->
<!-- 			  <dd> -->
<!-- 					<input data="fa4_son" name="model[paylog][]" class="check" <c:if test="${powers[142]==1}">checked="checked"</c:if> value="cg19" id="withdrawlogcg19" type="checkbox" style="DISPLAY:none"><label for="withdrawlogcg19" style="DISPLAY:none">列表</label> -->
<!-- 					<input data="fa4_son" name="model[paylog][]" class="check" <c:if test="${powers[143]==1}">checked="checked"</c:if> value="cg20" id="withdrawlogcg20" type="checkbox"><label for="withdrawlogcg20">搜索</label> -->
<!-- 					<input data="fa4_son" name="model[paylog][]" class="check" <c:if test="${powers[144]==1}">checked="checked"</c:if> value="cg21" id="withdrawlogcg21" type="checkbox"><label for="withdrawlogcg21">导出</label> -->
					
<!-- 			  </dd> -->
<!-- 		</dl>	 -->
																					
    <dl class="lineD" style="float:none">
      <dt class="t"><b>文章管理</b></dt>
	  <dd>请选择相关权限<input onClick="select_all('fa5');" id="fa5" type="checkbox"><label for="fa5">全选</label></dd>
    </dl>
	
											<dl class="lineD" style="float:none">
		  <dt>文章管理：</dt>
		  <dd>
								<input data="fa5_son" name="model[article][]" class="check" <c:if test="${powers[41]==1}">checked="checked"</c:if> value="at1" id="articleat1" type="checkbox" style="DISPLAY:none"><label for="articleat1" style="DISPLAY:none">列表</label>
								<input data="fa5_son" name="model[article][]" class="check" <c:if test="${powers[42]==1}">checked="checked"</c:if> value="at2" id="articleat2" type="checkbox"><label for="articleat2">添加</label>
								<input data="fa5_son" name="model[article][]" class="check" <c:if test="${powers[43]==1}">checked="checked"</c:if> value="at3" id="articleat3" type="checkbox"><label for="articleat3">删除</label>
								<input data="fa5_son" name="model[article][]" class="check" <c:if test="${powers[44]==1}">checked="checked"</c:if> value="at4" id="articleat4" type="checkbox"><label for="articleat4">修改</label>
								<input data="fa5_son" name="model[article][]" class="check" <c:if test="${powers[86]==1}">checked="checked"</c:if> value="at4" id="articleat5" type="checkbox"><label for="articleat4">搜索</label>
						  </dd>
		</dl>
																		
		
    <dl class="lineD" style="float:none">
      <dt class="t"><b>导航菜单管理</b></dt>
	  <dd>请选择相关权限<input onClick="select_all('fa6');" id="fa6" type="checkbox"><label for="fa6">全选</label></dd>
    </dl>
	
											<dl class="lineD" style="float:none">
		  <dt>导航菜单：</dt>
		  <dd>
								<input data="fa6_son" name="model[navigation][]" class="check" <c:if test="${powers[45]==1}">checked="checked"</c:if> value="nav1" id="navigationnav1" type="checkbox" style="DISPLAY:none"><label for="navigationnav1" style="DISPLAY:none">列表</label>
								<input data="fa6_son" name="model[navigation][]" class="check" <c:if test="${powers[46]==1}">checked="checked"</c:if> value="nav4" id="navigationnav4" type="checkbox"><label for="navigationnav4">修改</label>
								<input data="fa6_son" name="model[navigation][]" class="check" <c:if test="${powers[87]==1}">checked="checked"</c:if> value="nav4" id="navigationnav5" type="checkbox"><label for="navigationnav4">添加</label>
								<input data="fa6_son" name="model[navigation][]" class="check" <c:if test="${powers[88]==1}">checked="checked"</c:if> value="nav4" id="navigationnav6" type="checkbox"><label for="navigationnav4">删除</label>
						  </dd>
		</dl>
																	
    <dl class="lineD" style="float:none">
      <dt class="t"><b>资金统计</b></dt>
	  <dd>请选择相关权限<input onClick="select_all('fa8');" id="fa8" type="checkbox"><label for="fa8">全选</label></dd>
    </dl>
	
											<dl class="lineD" style="float:none">
		  <dt>会员帐户：</dt>
		  <dd>
								<input data="fa8_son" name="model[capitalaccount][]" class="check" <c:if test="${powers[47]==1}">checked="checked"</c:if> value="capital_1" id="capitalaccountcapital_1" type="checkbox" style="DISPLAY:none"><label for="capitalaccountcapital_1" style="DISPLAY:none">列表</label>
								<input data="fa8_son" name="model[capitalaccount][]" class="check" <c:if test="${powers[48]==1}">checked="checked"</c:if> value="capital_2" id="capitalaccountcapital_2" type="checkbox"><label for="capitalaccountcapital_2">导出</label>
								<input data="fa8_son" name="model[capitalaccount][]" class="check" <c:if test="${powers[99]==1}">checked="checked"</c:if> value="capital_10" id="capitalaccountcapital_10" type="checkbox"><label for="capitalaccountcapital_10">搜索</label>
						  </dd>
		</dl>
																									<dl class="lineD" style="float:none">
		  <dt>充值记录：</dt>
		  <dd>
								<input data="fa8_son" name="model[capitalaccount][]" class="check" <c:if test="${powers[49]==1}">checked="checked"</c:if> value="capital_3" id="capitalonlinecapital_3" type="checkbox" style="DISPLAY:none"><label for="capitalonlinecapital_3" style="DISPLAY:none">列表</label>
								<input data="fa8_son" name="model[capitalaccount][]" class="check" <c:if test="${powers[50]==1}">checked="checked"</c:if> value="capital_4" id="capitalonlinecapital_4" type="checkbox"><label for="capitalonlinecapital_4">导出</label>
								<input data="fa8_son" name="model[capitalaccount][]" class="check" <c:if test="${powers[100]==1}">checked="checked"</c:if> value="capital_11" id="capitalonlinecapital_11" type="checkbox"><label for="capitalonlinecapital_11">搜索</label>
						  </dd>
		</dl>
													<dl class="lineD" style="float:none">
		  <dt>提现记录：</dt>
		  <dd>
								<input data="fa8_son" name="model[capitalaccount][]" class="check" <c:if test="${powers[51]==1}">checked="checked"</c:if> value="capital_5" id="capitalonlinecapital_5" type="checkbox" style="DISPLAY:none"><label for="capitalonlinecapital_5" style="DISPLAY:none">列表</label>
								<input data="fa8_son" name="model[capitalaccount][]" class="check" <c:if test="${powers[52]==1}">checked="checked"</c:if> value="capital_6" id="capitalonlinecapital_6" type="checkbox"><label for="capitalonlinecapital_6">导出</label>
								<input data="fa8_son" name="model[capitalaccount][]" class="check" <c:if test="${powers[101]==1}">checked="checked"</c:if> value="capital_12" id="capitalonlinecapital_12" type="checkbox"><label for="capitalonlinecapital_12">搜索</label>
						  </dd>
		</dl>
																									<dl class="lineD" style="float:none">
		  <dt>会员资金记录：</dt>
		  <dd>
								<input data="fa8_son" name="model[capitalaccount][]" class="check" <c:if test="${powers[53]==1}">checked="checked"</c:if> value="capital_7" id="capitaldetailcapital_7" type="checkbox" style="DISPLAY:none"><label for="capitaldetailcapital_7" style="DISPLAY:none">列表</label>
								<input data="fa8_son" name="model[capitalaccount][]" class="check" <c:if test="${powers[54]==1}">checked="checked"</c:if> value="capital_8" id="capitaldetailcapital_8" type="checkbox"><label for="capitaldetailcapital_8">导出</label>
								<input data="fa8_son" name="model[capitalaccount][]" class="check" <c:if test="${powers[102]==1}">checked="checked"</c:if> value="capital_13" id="capitaldetailcapital_13" type="checkbox"><label for="capitaldetailcapital_13">搜索</label>
						  </dd>
		</dl>
																									<dl class="lineD" style="float:none">
		  <dt>网站资金统计：</dt>
		  <dd>
								<input data="fa8_son" name="model[capitalaccount][]" style="DISPLAY:none" class="check" <c:if test="${powers[55]==1}">checked="checked"</c:if> value="capital_9" id="capitalallcapital_9" type="checkbox"><label style="DISPLAY:none" for="capitalallcapital_9">查看</label>
								<input data="fa8_son" name="model[capitalaccount][]" class="check" <c:if test="${powers[103]==1}">checked="checked"</c:if> value="capital_14" id="capitalallcapital_14" type="checkbox"><label for="capitalallcapital_14">资金统计</label>
						  </dd>
		</dl>
		<dl class="lineD" style="float:none">
		  <dt>推荐投资记录：</dt>
		  <dd>
								<input data="fa8_son" name="model[capitalaccount][]" style="DISPLAY:none" class="check" <c:if test="${powers[122]==1}">checked="checked"</c:if> value="capital_15" id="capitalallcapital_5" type="checkbox"><label style="DISPLAY:none" for="capitalallcapital_5">查看</label>
								<input data="fa8_son" name="model[capitalaccount][]" class="check" <c:if test="${powers[123]==1}">checked="checked"</c:if> value="capital_16" id="capitalallcapital_16" type="checkbox"><label for="capitalallcapital_16">搜索</label>
						  </dd>
		</dl>																
		
    <dl class="lineD" style="DISPLAY:none;float:none">
      <dt class="t"><b>权限管理</b></dt>
	  <dd>请选择相关权限<input onClick="select_all('fa9');" id="fa9" type="checkbox"><label for="fa9">全选</label></dd>
    </dl>
	
											<dl class="lineD" style="DISPLAY:none;float:none">
		  <dt>权限管理：</dt>
		  <dd>
								<input data="fa9_son" name="model[acl][]" class="check" <c:if test="${powers[56]==1}">checked="checked"</c:if> value="at73" id="aclat73" type="checkbox" style="DISPLAY:none"><label for="aclat73" style="DISPLAY:none">列表</label>
								<input data="fa9_son" name="model[acl][]" class="check" <c:if test="${powers[57]==1}">checked="checked"</c:if> value="at74" id="aclat74" type="checkbox"><label for="aclat74">增加</label>
<!-- 							<input data="fa9_son" name="model[acl][]" class="check" value="at75" id="aclat75" type="checkbox"><label for="aclat75">删除</label> -->
								<input data="fa9_son" name="model[acl][]" class="check" <c:if test="${powers[58]==1}">checked="checked"</c:if> value="at76" id="aclat76" type="checkbox"><label for="aclat76">修改</label>
						  </dd>
		</dl>
																		
		
    <dl class="lineD" style="float:none">
      <dt class="t"><b>管理员管理</b></dt>
	  <dd>请选择相关权限<input onClick="select_all('fa10');" id="fa10" type="checkbox"><label for="fa10">全选</label></dd>
    </dl>
	
											<dl class="lineD" style="float:none">
		  <dt>管理员管理：</dt>
		  <dd>
								<input data="fa10_son" name="model[adminuser][]" class="check" <c:if test="${powers[59]==1}">checked="checked"</c:if> value="at77" id="adminuserat77" type="checkbox" style="DISPLAY:none"><label for="adminuserat77" style="DISPLAY:none">列表</label>
								<input data="fa10_son" name="model[adminuser][]" class="check" <c:if test="${powers[60]==1}">checked="checked"</c:if> value="at78" id="adminuserat78" type="checkbox"><label for="adminuserat78">增加</label>
								<input data="fa10_son" name="model[adminuser][]" class="check" <c:if test="${powers[61]==1}">checked="checked"</c:if> value="at79" id="adminuserat79" type="checkbox"><label for="adminuserat79">删除</label>
								<input data="fa10_son" name="model[adminuser][]" class="check" <c:if test="${powers[62]==1}">checked="checked"</c:if> value="at80" id="adminuserat80" type="checkbox"><label for="adminuserat80">修改</label>
						  </dd>
		</dl>
    <dl class="lineD" style="float:none">
      <dt class="t"><b>扩展管理</b></dt>
	  <dd>请选择相关权限<input onClick="select_all('fa13');" id="fa13" type="checkbox"><label for="fa13">全选</label></dd>
    </dl>																								<dl class="lineD" style="float:none">
		  <dt>首页新表预告：</dt>
		  <dd>
								<input data="fa13_son" name="model[mfields][]" class="check" <c:if test="${powers[63]==1}">checked="checked"</c:if> value="at82" id="mfieldsat82" type="checkbox" style="DISPLAY:none"><label for="mfieldsat82" style="DISPLAY:none">查看</label>
								<input data="fa13_son" name="model[mfields][]" class="check" <c:if test="${powers[64]==1}">checked="checked"</c:if> value="at83" id="mfieldsat83" type="checkbox"><label for="mfieldsat83">修改</label>
						  </dd>
		</dl>
																									<dl class="lineD" style="float:none">
		  <dt>业务参数管理：</dt>
		  <dd>
								<input data="fa13_son" name="model[mfields][]" class="check" <c:if test="${powers[65]==1}">checked="checked"</c:if> value="fb1" id="bconfigfb1" type="checkbox" style="DISPLAY:none"><label for="bconfigfb1" style="DISPLAY:none">查看</label>
								<input data="fa13_son" name="model[mfields][]" class="check" <c:if test="${powers[66]==1}">checked="checked"</c:if> value="fb1" id="bconfigfb1" type="checkbox"><label for="bconfigfb1">新增</label>
								<input data="fa13_son" name="model[mfields][]" class="check" <c:if test="${powers[67]==1}">checked="checked"</c:if> value="fb2" id="bconfigfb2" type="checkbox"><label for="bconfigfb2">修改</label>
						  </dd>
		</dl>
																									<dl class="lineD" style="float:none;display:none;">
		  <dt>信用级别管理：</dt>
		  <dd>
								<input data="fa13_son" name="model[mfields][]" class="check" <c:if test="${powers[68]==1}">checked="checked"</c:if> value="jb1" id="levejb1" type="checkbox" style="DISPLAY:none"><label for="levejb1" style="DISPLAY:none">查看</label>
								<input data="fa13_son" name="model[mfields][]" class="check" <c:if test="${powers[69]==1}">checked="checked"</c:if> value="jb1" id="levejb1" type="checkbox"><label for="levejb1">新增</label>
								<input data="fa13_son" name="model[mfields][]" class="check" <c:if test="${powers[70]==1}">checked="checked"</c:if> value="jb2" id="levejb2" type="checkbox"><label for="levejb2">修改</label>
						  </dd>
		</dl>
													<dl class="lineD" style="float:none">
		  <dt>投资级别管理：</dt>
		  <dd>
								<input data="fa13_son" name="model[mfields][]" class="check" <c:if test="${powers[71]==1}">checked="checked"</c:if> value="jb3" id="levejb3" type="checkbox" style="DISPLAY:none"><label for="levejb3" style="DISPLAY:none">查看</label>
								<input data="fa13_son" name="model[mfields][]" class="check" <c:if test="${powers[72]==1}">checked="checked"</c:if> value="jb3" id="levejb3" type="checkbox"><label for="levejb3">新增</label>
								<input data="fa13_son" name="model[mfields][]" class="check" <c:if test="${powers[73]==1}">checked="checked"</c:if> value="jb4" id="levejb4" type="checkbox"><label for="levejb4">修改</label>
						  </dd>
		</dl>
		<dl class="lineD" style="float:none">
		  <dt>QQ客服管理：</dt>
		  <dd>
								<input data="fa13_son" name="model[mfields][]" class="check" <c:if test="${powers[74]==1}">checked="checked"</c:if> value="qq5" id="qqqq5" type="checkbox" style="DISPLAY:none"><label for="qqqq5" style="DISPLAY:none">列表</label>
								<input data="fa13_son" name="model[mfields][]" class="check" <c:if test="${powers[75]==1}">checked="checked"</c:if> value="qq6" id="qqqq6" type="checkbox"><label for="qqqq6">增加</label>
								<input data="fa13_son" name="model[mfields][]" class="check" <c:if test="${powers[124]==1}">checked="checked"</c:if> value="qq6" id="qqqq8" type="checkbox"><label for="qqqq8">修改</label>
								<input data="fa13_son" name="model[mfields][]" class="check" <c:if test="${powers[76]==1}">checked="checked"</c:if> value="qq7" id="qqqq7" type="checkbox"><label for="qqqq7">删除</label>
						  </dd>
		</dl>
		
																									<dl class="lineD" style="float:none">
		  <dt>线下充值银行管理：</dt>
		  <dd>
								<input data="fa13_son" name="model[mfields][]" class="check" <c:if test="${powers[77]==1}">checked="checked"</c:if> value="offline1" id="payofflineoffline1" type="checkbox" style="DISPLAY:none"><label for="payofflineoffline1" style="DISPLAY:none">查看</label>
								<input data="fa13_son" name="model[mfields][]" class="check" <c:if test="${powers[78]==1}">checked="checked"</c:if> value="offline1" id="payofflineoffline1" type="checkbox"><label for="payofflineoffline1">新增</label>
								<input data="fa13_son" name="model[mfields][]" class="check" <c:if test="${powers[79]==1}">checked="checked"</c:if> value="offline2" id="payofflineoffline2" type="checkbox"><label for="payofflineoffline2">修改</label>
								<input data="fa13_son" name="model[mfields][]" class="check" <c:if test="${powers[121]==1}">checked="checked"</c:if> value="offline2" id="payofflineoffline2" type="checkbox"><label for="payofflineoffline2">删除</label>
						  </dd>
		</dl>
																									<dl class="lineD" style="float:none">
		  <dt>线上支付接口管理：</dt>
		  <dd>
								<input data="fa13_son" name="model[mfields][]" class="check" <c:if test="${powers[80]==1}">checked="checked"</c:if> value="jk1" id="payonlinejk1" type="checkbox" style="DISPLAY:none"><label for="payonlinejk1" style="DISPLAY:none">查看</label>
								<input data="fa13_son" name="model[mfields][]" class="check" <c:if test="${powers[81]==1}">checked="checked"</c:if> value="jk2" id="payonlinejk2" type="checkbox"><label for="payonlinejk2">修改</label>
						  </dd>
		</dl>
																									<dl class="lineD" style="float:none">
		  <dt>通知信息接口管理：</dt>
		  <dd>
								<input data="fa13_son" name="model[mfields][]" class="check" <c:if test="${powers[82]==1}">checked="checked"</c:if> value="jk3" id="msgonlinejk3" type="checkbox" style="DISPLAY:none"><label for="msgonlinejk3" style="DISPLAY:none">查看</label>
								<input data="fa13_son" name="model[mfields][]" class="check" <c:if test="${powers[83]==1}">checked="checked"</c:if> value="jk4" id="msgonlinejk4" type="checkbox"><label for="msgonlinejk4">修改</label>
						  </dd>
		</dl>
													<dl class="lineD" style="float:none">
		  <dt>通知信息模板管理：</dt>
		  <dd>
								<input data="fa13_son" name="model[mfields][]" class="check" <c:if test="${powers[84]==1}">checked="checked"</c:if> value="jk5" id="msgonlinejk5" type="checkbox" style="DISPLAY:none"><label for="msgonlinejk5" style="DISPLAY:none">查看</label>
								<input data="fa13_son" name="model[mfields][]" class="check" <c:if test="${powers[85]==1}">checked="checked"</c:if> value="jk6" id="msgonlinejk6" type="checkbox"><label for="msgonlinejk6">修改</label>
						  </dd>
						  
		</dl>
		<!-- 新增的模块 -->
		<dl class="lineD" style="float:none;display: none">
		  <dt>用户心声：</dt>
		  <dd>
								<input data="fa13_son" name="model[mfields][]" class="check" <c:if test="${powers[104]==1}">checked="checked"</c:if> value="jk7" id="msgonlinejk7" type="checkbox"><label for="msgonlinejk7">新增</label>
								<input data="fa13_son" name="model[mfields][]" class="check" <c:if test="${powers[105]==1}">checked="checked"</c:if> value="jk8" id="msgonlinejk8" type="checkbox"><label for="msgonlinejk8">修改</label>
								<input data="fa13_son" name="model[mfields][]" class="check" <c:if test="${powers[106]==1}">checked="checked"</c:if> value="jk9" id="msgonlinejk9" type="checkbox"><label for="msgonlinejk9">删除</label>
		  </dd>
						  
		</dl>
		<dl class="lineD" style="float:none;display: none">
		  <dt>积分管理：</dt>
		  <dd>
								<input data="fa13_son" name="model[mfields][]" class="check" <c:if test="${powers[107]==1}">checked="checked"</c:if> value="jk10" id="msgonlinejk10" type="checkbox"><label for="msgonlinejk10">修改</label>
		  </dd>
						  
		</dl>
		<dl class="lineD" style="float:none;display: none">
		  <dt>积分抽奖记录：</dt>
		  <dd>
								<input data="fa13_son" name="model[mfields][]" class="check" <c:if test="${powers[108]==1}">checked="checked"</c:if> value="jk11" id="msgonlinejk11" type="checkbox"><label for="msgonlinejk11">搜索</label>
								<input data="fa13_son" name="model[mfields][]" class="check" <c:if test="${powers[109]==1}">checked="checked"</c:if> value="jk12" id="msgonlinejk12" type="checkbox"><label for="msgonlinejk12">导出</label>
		  </dd>
						  
		</dl>
		<dl class="lineD" style="float:none;display: none">
		  <dt>奖品概率设置：</dt>
		  <dd>
								<input data="fa13_son" name="model[mfields][]" class="check" <c:if test="${powers[110]==1}">checked="checked"</c:if> value="jk13" id="msgonlinejk13" type="checkbox"><label for="msgonlinejk13">修改</label>
		  </dd>
						  
		</dl>
		<dl class="lineD" style="float:none;display: none">
		  <dt>积分抽奖奖品设置：</dt>
		  <dd>
								<input data="fa13_son" name="model[mfields][]" class="check" <c:if test="${powers[111]==1}">checked="checked"</c:if> value="jk14" id="msgonlinejk14" type="checkbox"><label for="msgonlinejk14">修改</label>
		  </dd>
						  
		</dl>
		<dl class="lineD" style="float:none;display: none">
		  <dt>积分兑换奖品设置：</dt>
		  <dd>
								<input data="fa13_son" name="model[mfields][]" class="check" <c:if test="${powers[112]==1}">checked="checked"</c:if> value="jk15" id="msgonlinejk15" type="checkbox"><label for="msgonlinejk15">新增</label>
								<input data="fa13_son" name="model[mfields][]" class="check" <c:if test="${powers[113]==1}">checked="checked"</c:if> value="jk16" id="msgonlinejk16" type="checkbox"><label for="msgonlinejk16">修改</label>
								<input data="fa13_son" name="model[mfields][]" class="check" <c:if test="${powers[114]==1}">checked="checked"</c:if> value="jk17" id="msgonlinejk17" type="checkbox"><label for="msgonlinejk17">删除</label>
		  </dd>
						  
		</dl>
		<dl class="lineD" style="float:none;display: none">
		  <dt>VIP金额设置：</dt>
		  <dd>
								<input data="fa13_son" name="model[mfields][]" class="check" <c:if test="${powers[119]==1}">checked="checked"</c:if> value="jk18" id="msgonlinejk18" type="checkbox"><label for="msgonlinejk18">修改</label>
		  </dd>
						  
		</dl>
		<dl class="lineD" style="float:none;display: none;">
		  <dt>收费标准设置：</dt>
		  <dd>
								<input data="fa13_son" name="model[mfields][]" class="check" <c:if test="${powers[120]==1}">checked="checked"</c:if> value="jk19" id="msgonlinejk19" type="checkbox"><label for="msgonlinejk19">修改</label>
		  </dd>
						  
		</dl>
		
		<dl class="lineD" style="float:none">
		  <dt>QQ群管理：</dt>
		  	<dd>
				<input data="fa13_son" name="model[mfields][]" class="check" <c:if test="${powers[125]==1}">checked="checked"</c:if> value="qun5" id="qun5" type="checkbox" style="DISPLAY:none"><label for="qun5" style="DISPLAY:none">列表</label>
				<input data="fa13_son" name="model[mfields][]" class="check" <c:if test="${powers[126]==1}">checked="checked"</c:if> value="qun6" id="qun6" type="checkbox"><label for="qun6">增加</label>
				<input data="fa13_son" name="model[mfields][]" class="check" <c:if test="${powers[127]==1}">checked="checked"</c:if> value="qun6" id="qun8" type="checkbox"><label for="qun8">修改</label>
				<input data="fa13_son" name="model[mfields][]" class="check" <c:if test="${powers[128]==1}">checked="checked"</c:if> value="qun7" id="qun7" type="checkbox"><label for="qun7">删除</label>
			</dd>
		</dl>
		<dl class="lineD" style="float:none">
		  <dt>客服电话管理：</dt>
		  	<dd>
				<input data="fa13_son" name="model[mfields][]" class="check" <c:if test="${powers[129]==1}">checked="checked"</c:if> value="phoen5" id="phoen5" type="checkbox" style="DISPLAY:none"><label for="phoen5" style="DISPLAY:none">列表</label>
				<input data="fa13_son" name="model[mfields][]" class="check" <c:if test="${powers[130]==1}">checked="checked"</c:if> value="phoen6" id="phoen6" type="checkbox"><label for="phoen6">增加</label>
				<input data="fa13_son" name="model[mfields][]" class="check" <c:if test="${powers[131]==1}">checked="checked"</c:if> value="phoen6" id="phoen8" type="checkbox"><label for="phoen8">修改</label>
				<input data="fa13_son" name="model[mfields][]" class="check" <c:if test="${powers[132]==1}">checked="checked"</c:if> value="phoen7" id="phoen7" type="checkbox"><label for="phoen7">删除</label>
			</dd>
		</dl>
		
		<input type="hidden" style="float:none;" name="roleId" id="roleId" value="${roleId}">																
	
    
    </form>
   
      </div>
      
    <div class="page_btn"> <input type="button" onclick="tijiao();" value="确定" class="btn_b" id="bt"> </div>
    </div>
    
    
</body>

</html>
