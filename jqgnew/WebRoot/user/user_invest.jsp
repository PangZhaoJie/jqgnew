<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="/struts-tags" prefix="s"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%    
	String path = request.getContextPath();if(path!=null && !path.equals("")){	if(path.contains("/")){		if(path.equals("/")){path = "";	}}else{	path += "/";}}    
	String basePath =  "";
	if(request.getServerPort()==80){
		basePath = request.getScheme()+"://"+request.getServerName()+path+"/"; 
	}else{
		basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
	}
	   
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="icon" href="<%=path %>/image/favicon/favicon.ico" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>个人中心-自动投标</title>
<link href="<%=basePath%>/css/public.css" type="text/css" rel="stylesheet"/>
<link href="<%=basePath%>/css/user/user_public.css" type="text/css" rel="stylesheet"/>
<link href="<%=basePath%>/css/user/user_invest.css" type="text/css" rel="stylesheet"/>
<!--导航下拉菜单 js-->
<script type="text/javascript" src="<%=basePath%>/js/jquery-1.8.2.min.js"></script>
<!--表单下拉列表框-->
 
<!--左侧折叠菜单-->
 

<!--页面验证及提交-->
  
<script type="text/javascript" src="<%=basePath%>/js/automaticbid.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/menu.js"></script>
 <script type="text/javascript">
 var $page =  jQuery.noConflict();
 function showupdatediv() {
 	 var fg=document.getElementById("isUpdateId").value;
	 if(fg==1){
		 secBoard('hotnews_caption','list',2);
	 }
 		$page("#ID1").hide();
 		$page("#ID3").show();
 }
 $page(function() {  
	 showupdatediv();  
 });
 </script>
   
</head>
<body> 
<input  value="3" id="userLeftFlagId" type="hidden"/>
<!--头部开始-->
<div class="header">
    <jsp:include page="../header.jsp" />
</div>
<!--头部结束-->
<div class="maincontent">
  <input type="hidden" id="path" value="<%=basePath%>"/>
  <div class="conbox fn_cle">
          <jsp:include page="../user/left.jsp" />
    
   
   </div>
   
     <div class="user_r">
         <div id="hotnews_caption">
            <ul id="h_menu">                  
                 <li class="current"onMouseOver="secBoard('hotnews_caption','list',1);">自动投标</li>
                 <li class="normal" onMouseOver="secBoard('hotnews_caption','list',2);">添加自动投标</li>
            </ul>
         </div>
         
         <div id="hotnews_content">
            <div class="current" id="list_1">
                <table width="845" cellspacing="0" cellpadding="0" class="zh_table"  >
                      <tbody>
                            <tr class="th1">
                                <td width="160">编号</td>
                                <td width="150">是否开启</td>
                                <td width="160">每次投标金额</td>
                                <td width="160">借款期限</td>
                                <td width="200">操作</td>
                            </tr>
                            
                            <c:forEach var="automaticbid" items="${automaticbidList}">
                                <tr><td>${automaticbid.automaticBidId}</td>
                                <c:if test="${automaticbid.enable==1}">
                                	<td class="red">已开启</td>
                                </c:if>
                                 <c:if test="${automaticbid.enable !=1}">
                                	<td  >未开启</td>
                                </c:if>
                                <td><fmt:formatNumber value="${automaticbid.automaticBidNumber}" pattern="0.00"/>
                                <c:if test="${automaticbid.isType == 1}">元</c:if>
                                <c:if test="${automaticbid.isType == 0}">%</c:if>
                                </td>
                                <c:if test="${empty automaticbid.endTime}">
                                      <td>不限</td>     
                                </c:if>
                                <c:if test="${!empty automaticbid.endTime}">
                                      <td>${automaticbid.endTime}</td>     
                                </c:if> 
                                
                                <td><a href="<%=path %>/borrow/toUpdateAutomaticbid?automaticBidId=${automaticbid.automaticBidId}&userId=${session.uservip.userId}">编辑</a>|<a href="<%=path %>/borrow/toDeleteInvest?automaticBidId=${automaticbid.automaticBidId}&userId=${session.uservip.userId}">删除</a></td> </tr> 
                             </c:forEach>
                                           
                      </tbody>
                   </table>
            </div>
           
           <form action="" method="post" id="addAutomaticbidFormId">
            <c:forEach var="automaticb" items="${automaticbidList}">
            		<c:if test="${automaticb.enable==1}">
             			<c:if test="${automaticb.automaticBidId==automaticbid.automaticBidId}">
            			      <input value="0" id="kqID"  style="display: none"/>
            			</c:if>
            			<c:if test="${automaticb.automaticBidId!=automaticbid.automaticBidId}">
            			      <input value="1" id="kqID" style="display: none" />
            			</c:if>
            		</c:if>
            </c:forEach>
           	 <input name="automaticbid.automaticBidId" value="${automaticbid.automaticBidId}"  style="display: none"/>
            <input name="userId" value="${session.uservip.userId}" style="display: none"/>
           <input  value="${isUpdate }" name="isUpdate" id="isUpdateId" style="display: none"/>
           
            <div class="normal" id="list_2">
                <div class="effect" >
                    <h3>生效状态</h3>
                    <div class="info"><label class="title">是否启用：</label>
                      <div class="single">
                        <c:if test="${isUpdate == 1 }">
	                        <label style="padding-right:15px;">
	 	                             <input <c:if test="${automaticbid.enable ==1}">checked="checked"</c:if>  type="radio" name="automaticbid.enable" value="1" id="RadioGroup1_0" onclick="enableCheck('1')" style="border:0; " /><em>是</em>
	                          </label>
	                        <label> 
	                         	    <input <c:if test="${automaticbid.enable ==0}">checked="checked"</c:if>  type="radio" name="automaticbid.enable" value="0" id="RadioGroup1_1" onclick="enableCheck('0')" style="border:0;"/><em>否</em>
	                        </label></c:if>
                        <c:if test="${isUpdate == 0 }">
	                        <label style="padding-right:15px;">
	 	                             <input type="radio" name="automaticbid.enable" value="1" id="RadioGroup1_0" onclick="enableCheck('1')" style="border:0; " /><em>是</em>
	                          </label>
	                        <label> 
	                         	    <input  type="radio" name="automaticbid.enable" value="0" id="RadioGroup1_1" onclick="enableCheck('0')" style="border:0; " checked/><em>否</em>
	                        </label></c:if>
                      </div>
                       <label class="tip" style="display: none" id="zdtbCountId">（共计${mark}人开启自动投标）</label>
                   </div>
                   
                  <div class="info" style="height: auto;"><label class="title">每次投标金额：</label>
                    <div class="single1">
                    <input type="radio" name="automaticBidNumberFlag" value="1"   id="radioMoneyId" style="border:0;display: none" checked/>
                    <input name="automaticbid.automaticBidNumber"  onkeyup="value=value.replace(/[^0-9]/g,'')" value="${automaticbid.automaticBidNumber}"  type="text" id="borrowMoneyId" onblur="borrowMoneyCheck()" size="15" style="float: left;"/>
			   <em style="padding-left:8px;line-height: 35px;float: left">(最少50元，必须是50的倍数)</em>
			   <p id="borrowMoneySpan"  style="color: #FF0000;width: 150px;float:left;padding-left: 10px;line-height: 35px;margin-top: 2px">*</p>
                    
                    <!--   <p>
                        <c:if test="${automaticbid.isType==1}">
	                     	<label> <input type="radio" name="automaticBidNumberFlag" value="1"   id="radioMoneyId" style="border:0;" checked/> <em>按金额投标：</em></label>
	                      	<input name="automaticbid.automaticBidNumber" id="automaticBidNumberMoneyId" onkeyup="value=value.replace(/[^0-9]/g,'')" value="${automaticbid.automaticBidNumber}" onmousedown="moneyCheck('1')" type="text" size="10"   /><em>元</em><em style="color: red">(最少50元)</em> 
                     	</c:if>
                     	<c:if test="${automaticbid.isType!=1}">
                     		<label> <input type="radio" name="automaticBidNumberFlag" value="1"   id="radioMoneyId" style="border:0;"/> <em>按金额投标：</em></label>
	                      	<input name="automaticbid.automaticBidNumber" id="automaticBidNumberMoneyId" onkeyup="value=value.replace(/[^0-9]/g,'')"  type="text" size="10" onmousedown="moneyCheck('1')"  /><em>元</em><em style="color: red">(最少50元)</em> 
                      	</c:if>
                     </p> 
                     <p>
                        <c:if test="${automaticbid.isType==0}">
	                     	<label> <input type="radio" name="automaticBidNumberFlag" value="0"  id="radioRateId" style="border:0;" checked/> <em>按比例投标：</em> </label>
	                      	<input name="automaticbid.automaticBidNumber" onclick="aytoRateCheck()" onkeyup="value=value.replace(/[^0-9]/g,'')" id="automaticBidNumberRateId"  value="${automaticbid.automaticBidNumber}" onmousedown="moneyCheck('2')" type="text" size="10" /><em>%(只能在1%~100%)</em>
                     	</c:if>
                     	<c:if test="${automaticbid.isType!=0}">
	                     	<label> <input type="radio" name="automaticBidNumberFlag" value="0"  id="radioRateId" style="border:0;"/> <em>按比例投标：</em> </label>
	                      	<input name="automaticbid.automaticBidNumber" onclick="aytoRateCheck()" onkeyup="value=value.replace(/[^0-9]/g,'')" id="automaticBidNumberRateId"   type="text" size="10" onmousedown="moneyCheck('2')" /><em>%(1%~100%)</em>
                      	</c:if>
                     </p> -->
                      
                      <ul class="tip2">
                        <li>1、当前规则满足时系统将为您自动投标的额度，投标金额和比例都只能为大于0的为整数。</li>
                        <li>2、如果超过标的的最大投标额度则以标的的最大额度为准，如果小于标的的最小投标额度则不投。</li>
                         <li>3、无论哪种方式自动投标，账户余额小于设定的投标金额或投标比例时，账户所剩的余额全部参与自动投标。</li>  
                     </ul>
                   
                   </div>
                </div>
            
            </div>
                
                <div class="b_limit">
                    <h3>标的信息限制</h3>
                    <div class="info">
                      <label class="title">还款方式：</label>
                       <div id="uboxstyle">
                      <select name="returnway" id="returnway" >
			              	 <option value="0" >-请选择还款方式-</option>
			              	<c:forEach var="returnway" items="${returnwayList}">
			              	   <c:if test="${isUpdate == 1 }">	
			              	   		<c:if test="${returnway.returnWayId == automaticbid.returnway.returnWayId}">
				             			<option  value="${returnway.returnWayId}" selected="selected">${returnway.returnWayName}</option>
							   		</c:if>
							   		<c:if test="${returnway.returnWayId != automaticbid.returnway.returnWayId}">
				             			<option  value="${returnway.returnWayId}"  >${returnway.returnWayName}</option>
							   		</c:if>
							   </c:if>
							   <c:if test="${isUpdate ==0 }">
							   		<option value="${returnway.returnWayId}">${returnway.returnWayName}</option>   
							   </c:if>	
							   
							 </c:forEach> 
		                </select> 
                     </div>
                  </div>
                  
                    <div class="info1">
                      <label class="title">借款期限：</label>
                      <div class="single2">
                        <c:if test="${empty automaticbid.endTime }">
	                        <div class="select1"> <label><input type="radio" checked name="RadioGroup3" value="单选"  id="RadioGroup3_0" style="border:0;" onclick="document.getElementById('month1').value=''"/> <em>不限定借款期限范围</em></label> </div> 
	                        <div class="select2"><label><input type="radio" name="RadioGroup3" value="单选"  id="RadioGroup3_0" style="border:0;"/> <em>借款期限按月范围必须在</em> </label>
                        </c:if> 
                        <c:if test="${!empty automaticbid.endTime }">
	                        <div class="select1"> <label><input type="radio" name="RadioGroup3" value="单选"  id="RadioGroup3_0" style="border:0;"/> <em>不限定借款期限范围</em></label> </div> 
	                        <div class="select2"><label><input type="radio" checked name="RadioGroup3" value="单选"  id="RadioGroup3_0" style="border:0;"/> <em>借款期限按月范围必须在</em> </label></div>
                        </c:if>  
                          <div id="uboxstyle1">
                            <select name="periodtime" id="month1" >
                            	<option value="">--请选择--</option>
	                 	       <c:forEach var="periodtime" items="${periodtimeList}">
			              	   <c:if test="${isUpdate == 1 }">	
			              	   		<c:if test="${periodtime.periodTimeId == automaticbid.periodtime.periodTimeId}">
				             			<option  value="${periodtime.periodTimeId}" selected="selected">${periodtime.periodTimeName}</option>
							   		</c:if>
							   		<c:if test="${periodtime.periodTimeId != automaticbid.periodtime.periodTimeId}">
				             			<option  value="${periodtime.periodTimeId}"  >${periodtime.periodTimeName}</option>
							   		</c:if>
							   </c:if>
							   <c:if test="${isUpdate ==0 }">
							   		<option  value="${periodtime.periodTimeId}"  >${periodtime.periodTimeName}</option>   
							   </c:if>	
							 </c:forEach> 
                            </select>
                           
                         </div>                
                        </div> 
                       </div>
                  </div>
                  
                    <div class="info">
                      <label class="title">利率范围选项：</label>
                       <input name="automaticbid.startMoneyRate" value="${automaticbid.startMoneyRate}" type="text" size="12" /><em>%</em><em>--</em><input name="automaticbid.endMoneyRate" value="${automaticbid.endMoneyRate}" type="text" size="12" /><em>%(如果不写，默认无限制)</em>                  
                     
                  </div>
                  
                    <div class="info">
                      <label class="title">其他要求：</label>
                      <span style="float:left;">必须为</span>
                      <div id="uboxstyle">
                       <select name="automaticbid.lssuingType" id="lssuingTypeId">
	                 	  <option value="" >-不选则不限制-</option>
		                  <option value="1" <c:if test="${automaticbid.lssuingType==1 }">selected="selected"</c:if> >信用标</option>
		                  <option value="2" <c:if test="${automaticbid.lssuingType==2 }">selected="selected"</c:if> >担保标</option>
		                  <option value="3" <c:if test="${automaticbid.lssuingType==3 }">selected="selected"</c:if> >抵押标</option>
		                  <option value="4" <c:if test="${automaticbid.lssuingType==4 }">selected="selected"</c:if> >净值标</option>
                          <option value="5" <c:if test="${automaticbid.lssuingType==5 }">selected="selected"</c:if> >秒标</option>
                      </select>
                    </div>
                  </div>
            </div>
            
            <div id="savebtn" > <input type="button" onclick="submitAutomaticbid()" class="save_bt" value="保存" />
            <input type="button" class="skip_bt" onclick="resCheck()" value="取消" /></div>
           </div>
      </form>
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