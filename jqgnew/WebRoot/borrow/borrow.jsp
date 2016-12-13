<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
  <%@ taglib uri="/struts-tags" prefix="s"%>
<%    
	String path = request.getContextPath();if(path!=null && !path.equals("")){	if(path.contains("/")){		if(path.equals("/")){path = "";	}}else{	path += "/";}}    
	String basePath = "";if(request.getServerPort()==80){basePath = request.getScheme()+"://"+request.getServerName()+path+"/";}else{basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";}      
%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="icon" href="<%=path %>/image/favicon/favicon.ico" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>我要借款-发布贷款</title>
<link href="<%=basePath %>css/public.css" type="text/css" rel="stylesheet"/>
<link href="<%=basePath %>css/bid.css" type="text/css" rel="stylesheet" />
<!--导航下拉菜单 js-->
<script type="text/javascript" src="<%=basePath %>/js/jquery-1.8.2.min.js"></script>
 
<script type="text/javascript" src="<%=basePath %>/js/borrow.js"></script>

<!--表单下拉列表框-->
<script type="text/javascript" src="<%=basePath %>/js/select2css.js"></script>
  
</head>
<body> 
<!--头部开始-->
<div class="header">
    <jsp:include page="../header.jsp" />
</div>
<!--头部结束-->

<div class="maincontent">
  <div class="conbox pt20">
    <P class="guide">当前位置：<a href="#">网站首页</a> > <a href="#">我要借款</a> > 发布贷款</P>
     <div class="borrow_info fn_cle">
       <div class="borrow_title"><h3 style="width: 190px">发布贷款 --
       <c:if test="${mark==1 }">信用标</c:if>  
       <c:if test="${mark==2 }">担保标</c:if> 
       <c:if test="${mark==5 }">抵押标</c:if> 
       <c:if test="${mark==4 }">净值标</c:if> 
       <c:if test="${mark==3 }">秒标</c:if> </h3></div>
       
       <div class="borrow_nr">
           <div class="info_w">
             <span>信息填写</span>
            <form name="" action="" method="post" id="borrowFormId"  >
                   <c:if test="${mark==3 }"><input value="1"  size="2" type="hidden" name="periodtime" id="periodtime" /></c:if>
            
             <input name="userId" value="${session.uservip.userId}" style="display: none"/>
             <input name="quota" type="hidden" value="${session.uservip.quota}" id="quota"/>
             <input name="mark" value="${mark}" style="display: none"/>
            <div  class="write_form" >
			   <div><label class="title">借款总额：</label> <input name="lssuing.borrowMoney"  onkeyup="value=value.replace(/[^0-9]/g,'')" type="text" id="borrowMoneyId" onblur="borrowMoneyCheck()" size="15" style="float: left;"/>
			   <em style="padding-left:8px;line-height: 35px;float: left">(必须是50的倍数)</em>
			   <p id="borrowMoneySpan"  style="color: #FF0000;width: 150px;float:right;padding-left: 10px;line-height: 35px;margin-top: 2px">*</p></div>
               <div><label class="title" id="rllId">年利率：</label> <input name="lssuing.rate" onkeyup="value=value.replace(/[^\d\.]/g,'')" id="rateId" onblur="rateCheck()" type="text" size="10" style="float: left;"/><em style="padding-left:8px;line-height: 35px;float: left">%（范围：0%-24%）</em>
               <p id="rateSpan" style="color: #FF0000;width: 130px;float:left;line-height: 35px;padding-left: 10px;margin-top: 2px">*</p>
                </div>
               <div><label class="title">借款用途：</label> 
                 <div id="uboxstyle" >
                    <select name="moneyUseId" id="moneyUseId" class="tag_select" >
	              	 <option value=""  selected="selected">-请选择借款用途-</option>
	              	<c:forEach var="moneyuse" items="${moneyuseList}">
		             <option  value="${moneyuse.moneyUseId}" >${moneyuse.moneyUseName}</option>
					 </c:forEach> 
	              </select>  
                  </div> 
                  <p id="moneyuseSpan" style="color: #FF0000;width: 100px;float:left;line-height: 35px;padding-left: 6px">*</p>
	              
              </div>
               <div ><label class="title">借款期限：</label>
	               	<c:if test="${mark!=3 }">
	 	               <div id="uboxstyle">                
		 	               <div id="periodmonthDivid" style="width: 200px; padding:0;"  >
			                    <select name="periodtime" id="periodtime" >
				              	 <option value="0"  >-请选择月数-</option>              	 
			 	              	 <c:forEach var="periodtime" items="${periodtimeList}">
					             <option  value="${periodtime.periodTimeId}">${periodtime.periodTimeName}</option>
								 </c:forEach> 	              	
				              </select> </div> 
			              <div id="periodDayNameDivId" style="display:none ;width: 200px;padding: 0">
			                    <select name="periodday" id="perioddayId" >
				              	 <option value="0"  selected="selected">-请选择天数-</option>              	 
			 	              	 <c:forEach var="periodday" items="${perioddayList}">
					             <option  value="${periodday.periodDayId}">${periodday.periodDayName}</option>
								 </c:forEach> 	              	
				              </select> </div> 
	                   </div>  
	                    <input type="checkbox" name="checkbox"  onclick="dayCheck()" id="dayCheckId" value="按天" style="float: left;margin-left:8px; " /><em style="padding-left:8px;line-height: 35px;float: left">按天</em> 
               		</c:if>
               		<c:if test="${mark==3 }"><div style="width: 85px;">标满立即还款 </div></c:if>
                </div>  
               
               <div><label class="title">最低投标金额：</label>                 
                   <div id="uboxstyle">
                    <select name="moneymin" id="moneymin" >
	              	 <option value=""  selected="selected">-请选择金额-</option>
 	              	<c:forEach var="moneymin" items="${moneyminList}">
		             <option  value="${moneymin.moneyMinId}">${moneymin.moneyMinName}元</option>
					 </c:forEach> 
	              </select>  
                  </div> 
                <p id="moneyminSpan" style="color: #FF0000;width: 100px;float:left;line-height: 35px;padding-left: 6px">*</p>                
                 </div>
               <div>
                  <label class="title">最多投标金额：</label> 	                
	               <div id="uboxstyle">
	                    <select name="moneymax" id="moneymax" >
			              	 <option value=""  selected="selected">-请选择金额-</option>
			              	<c:forEach  var="moneymax" items="${moneymaxList}">
			              		<option value="${moneymax.moneyMaxId}">
									<c:if test="${moneymax.moneyMaxName==0}">不限制</c:if>
									<c:if test="${moneymax.moneyMaxName!=0}">${moneymax.moneyMaxName}元</c:if>
								</option>
							 </c:forEach> 
		                </select>  
                  </div> 
	          <p id="moneymaxSpan" style="color: #FF0000;width: 100px;float:left;line-height: 35px;padding-left: 6px">*</p>                	            
              </div>
               <div><label class="title">有效时间：</label>
 
                 <div id="uboxstyle">
	                    <select name="validtime" id="validtime" >
			              	 <option value=""  selected="selected">-请选择时间-</option>
			              	<c:forEach  var="validtime" items="${validtimeList}">	
				             	<option   value="${validtime.validTimeId}">${validtime.validTimeName}天</option>
							 </c:forEach> 
		                </select>  
                  </div> 
            	<p id="validtimeSpan" style="color: #FF0000;width: 100px;float:left;line-height: 35px;padding-left: 6px">*</p>                	            
            
              </div>
               <div><label class="title">还款方式：</label> 
                   <c:if test="${mark!=3 }">
	               <div id="uboxstyle">
	                    <select name="returnway" id="returnway" >
			              	 <option value=""  selected="selected">-请选择还款方式-</option>
			              	<c:forEach var="returnway" items="${returnwayList}">	
				             	<option  value="${returnway.returnWayId}">${returnway.returnWayName}</option>
							 </c:forEach> 
		                </select>  
                  </div> 
	               <p id="returnwaySpan" style="color: #FF0000;width: 100px;float:left;line-height: 35px;padding-left: 6px">*</p>                	            
	               </c:if>
	                <c:if test="${mark==3 }"><div style="width: 90px;padding-bottom: 3px">一次性还款</div><input value="1" type="hidden"  id="returnway"  name="returnway"/> </c:if> 
                 </div>
               <div><label class="title">是否有投标奖励：</label> 
                  <p style="font-size:14px;">
                   <label style="padding-right:15px;"> 
                   <input type="radio" onclick="tbjl(1)"  name="lssuing.isAward" value="1" id="RadioGroup1_0" style="border:0;"/> 是</label>
                  
                   <label> 
                   <input type="radio" onclick="tbjl(2)" name="lssuing.isAward" value="0" id="RadioGroup1_1" style="border:0;" checked/> 否</label>
                  </p>
               </div>
               <div><label class="title">是否有投标待收限制：</label> 
                 <p style="font-size:14px;">
                   <label style="padding-right:15px;"> 
                   <input type="radio" onclick="tbjl(3)" name="lssuing.tenderLimit" value="1" id="RadioGroup2_0" style="border:0;"/> 是</label>                
                   <label>  
                   <input type="radio" onclick="tbjl(4)" name="lssuing.tenderLimit" value="0" id="RadioGroup2_1" style="border:0;" checked/>否</label>
                 </p>
               </div>
			</div>
                    
            <div class="reward_form" id="tbjlID" style="display:none">
             <span>投标奖励</span>
			    <p style="width: 330px">
			      <label>
			        <input type="radio" name="RadioGroup3" value="单选"  id="RadioGroup3_0" style="border:0;"/>
			        按投标金额比例奖励： 
                    </label>
                    <input name="lssuing.awardRate"   onkeyup="value=value.replace(/[^\d\.]/g,'')"  onblur="awardRateCheck()" onmousedown="awardRateDown();awardMoneyCheck()" id="awardRateId" type="text" size="24" />% 			   
			     </p> 
			     <p id="awardRateSpan" style="color: #FF0000;width: 60px;float:left;line-height: 35px;padding-left:3px"></p>                	            
			                      
                 <p style="width: 340px">
			      <label>
			        <input type="radio" name="RadioGroup3" value="单选"  id="RadioGroup3_1" style="border:0;"/>
			        按固定金额分摊奖励：
                   </label>
			      <input name="lssuing.awardMoney"   onkeyup="value=value.replace(/[^\d\.]/g,'')"  id="awardMoneyId" onblur="awardMoneyCheck()" onmousedown="awardMoneyDown();awardRateCheck()" type="text" size="24" />元			      
			    </p>
			    <p id="awardMoneySpan" style="color: #FF0000;width: 60px;float:left;line-height: 35px;padding-left:3px"></p>                	            
			                   
			</div>
            <div class="reward_form" id="dsjeID" style="display:none">
               <span>投标待收金额限制</span>
                 <p>
			      <label>
			        待收金额限制：
                   </label>
			      <input name="lssuing.moneyLimit"   onkeyup="value=value.replace(/[^\d\.]/g,'')"  type="text" id="moneyLimit" onblur="moneyLimitCheck()" size="25" />元
			    </p>
			    <p id="moneyLimitSpan" style="color: #FF0000;width: 60px;float:left;line-height: 35px;padding-left:3px"></p>                	            
			                   
			</div>
            
             <span>借款详情说明</span>
            <div  class="detail_form">
			   <div><label class="title">借款标题：</label> <input name="lssuing.title" onblur="titleCheck()" id="titleId" type="text" size="36" style="float: left;"/>
	               <p id="titleSpan" style="color: #FF0000;width: 100px;float:left;line-height: 35px;padding-left: 6px">*</p>                	            
			   
			   </div>
               <div><label class="title">是否定向标：</label>  
                  <p style="font-size:14px;">
	                   <label style="padding-right:15px;"> <input onclick="tbjl(5)" type="radio" name="lssuing.isOrient" value="1" id="RadioGroup1_3" style="border:0;"/> 是</label>
	                   <label> <input type="radio" onclick="tbjl(6)" name="lssuing.isOrient" value="0" id="RadioGroup1_4"style="border:0;" checked/>否</label>
	                    <input name="lssuing.orientPassword" type="text"  size="25" style="display: none" id="dxbID" />
				 </p>
               </div>
               <div><label class="title">借款说明：</label> <textarea name="lssuing.explains" id="explainsId" class="detail" onblur="explainsCheck()"></textarea>
                   	<p id="explainsSpan" style="color: #FF0000;width: 60px;line-height: 35px;padding-left: 6px"></p>                	                              
               </div>
  				<input value="${lssuing.lssuingNum}" name="lssuing.lssuingNum" id="numId" type="hidden"/>
			</div>
			<div id="submit" > <input type="button" onclick="submitCheck()" class="submit_bt" value="提&nbsp;&nbsp;交" id="submit_ck"/></div>
			
           </form>
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
	<a href="#" class="srvLog" target="_blank" title="网贷计算器">网贷计算器</a>
	<a href="tencent://message/?uin=285772998&Site=企业网站&Menu=yes" class="srvCns" title="联系客服">联系客服</a>
	<a href="#" class="srvDj" target="_blank" title="在线充值">在线充值</a>
	<a class="goTop" id="goTop" title="返回顶部" style="display:none" >返回顶部</a>
</div>
<script type="text/javascript" src="<%=basePath %>/js/service.js">	</script> --%>
<!--右边漂浮 结束-->

<script type="text/javascript" >
var   $phpernote = jQuery.noConflict();
 	
function text(){
	document.getElementById("compareId").style.display="none";
	 document.getElementById("positiveId").style.display="none";
}


 function dayCheck(){
 	 if(document.getElementById("dayCheckId").checked){
      		document.getElementById("rllId").innerHTML="日利率：";
      		
    		document.getElementById("periodtime").options[0].selected=true;
    	    		
    		document.getElementById("returnway").options[1].selected=true;
      		
       		document.getElementById("periodDayNameDivId").style.display="";
      		document.getElementById("rateSpan").innerHTML="*";
      		document.getElementById("periodDayNameDivId").style.display="";
 			document.getElementById("periodmonthDivid").style.display="none";
      		
        }else{
      		document.getElementById("rllId").innerHTML="年利率：";
      		document.getElementById("periodDayNameDivId").style.display="none";
      		
      		document.getElementById("perioddayId").options[0].selected=true;
 
       		var rate=document.getElementById("rateId").value;	 
      	 	var rateSpan=document.getElementById("rateSpan");
      	   	if(rate!=""){    		 
      	 		var a=document.getElementById("rllId").innerHTML;
      	 		if(a=="年利率："){
      				rateSpan.innerHTML="* 月利率为"+(rate/12).toFixed(3)+"%";
      			} 
      		}
      		document.getElementById("periodDayNameDivId").style.display="none";
 			document.getElementById("periodmonthDivid").style.display="";
        }
 	
 	}
dayCheck();
</script>
</body>
</html>
