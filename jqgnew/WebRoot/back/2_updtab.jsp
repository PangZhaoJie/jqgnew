<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
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
<title>修改信息</title>
<link href="<%=basePath%>/back/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>/back/css/mem.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>/back/css/tab.css" rel="stylesheet" type="text/css" />
 <script type="text/javascript" src="<%=basePath %>/js/jquery-1.8.2.min.js"></script>

<script type="text/javascript" src="<%=basePath %>/js/ajaxfileupload.js"></script>
<script type="text/javascript">

function ajaxFileUpload(str)
{  var  $p=jQuery.noConflict();
   var a=document.getElementById("lssuingId").value;
   
	$p.ajaxFileUpload
	(
		{
			url:'<%=path %>/pic/toSaveP?lssuingId='+a,//用于文件上传的服务器端请求地址
			secureuri:false,//一般设置为false
			fileElementId:str,//文件上传空间的id属性  <input type="file" id="file" name="file" />
			dataType: 'json',//返回值类型 一般设置为json
			success: function (data, status)  //服务器成功响应处理函数
			{
				 
				var urls=data.urls.split("-");
				var urlIds=data.urlIds.split("-");
				var str1=""; 
				for(var i=0;i<urls.length;i++){
					str1+="<div class='pre1'  id='pre1"+urlIds[i]+"'> "+                 
	                 "<div class='pre1_t' ><img src='<%=basePath%>"+urls[i]+"' />"+
	                 " <input type='button' value='删除'  style='margin-top: 10px;cursor: pointer;'  id='pic"+urlIds[i]+"' onclick='deletePto("+urlIds[i]+")'/> </div> "+
	                 "</div>";
				}
				if(data.message=="你已成功上传文件"){
					$p("#fileTip").html("<span style='font-size:12px;color:green'>"+data.message+"</span>");
					$p("#tpxsId").html(str1);
  				}else{
					$p("#fileTip").html("<span style='font-size:12px;color:red'>"+data.message+"</span>");

				}
			},
			error: function (data, status, e)//服务器响应失败处理函数
			{
				alert(e);
			}
		}
	)
	
	return false;

}

function deletePto(str)
{   var  $p=jQuery.noConflict();
    $p("#pre1"+str).hide();
 	$p.ajaxFileUpload
	(
		{
			url:'<%=path %>/pic/toDeleteP?photoId='+str,//用于文件上传的服务器端请求地址
			secureuri:false,//一般设置为false
			fileElementId:str,//文件上传空间的id属性  <input type="file" id="file" name="file" />
			dataType: 'json',//返回值类型 一般设置为json
			success: function (data, status)  //服务器成功响应处理函数
			{
				 
				 
				if(data.message=="你已成功删除"){
					$p("#fileTip").html("<span style='font-size:12px;color:green'>"+data.message+"</span>");
   				}else{
					$p("#fileTip").html("<span style='font-size:12px;color:red'>"+data.message+"</span>");

				}
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
   <script type="text/javascript">
    function mitOne(queryFlag){
    	if(jQuery("#titleId").val()!=""&&
    	   jQuery("#validtimeId").val()!=""&&
    	   jQuery("#moneymaxId").val()!=""&&
    	   jQuery("#rateId").val()!=""&&
    	   jQuery("#explainsId").val()!=""){
    		if(jQuery("#periodtimeId").val()!="0" || jQuery("#perioddayId").val()!="0"){
    			jQuery("#mitOnebutton").attr("disabled", true);
    			var form=document.getElementById("2_tabId1");
    			form.action="<%=path %>/borrow/toUpdateLssuing?updateFlag=1&queryFlag="+queryFlag;
    			form.submit();
    			document.getElementById("mitOnebutton")	.style.background='gray';
				document.getElementById("mitOnebutton").value='正在提交...';
				document.getElementById("mitOnebutton").disabled=false;
    		}else{
    			alert("信息不完整");
    		}
    	}else{
    		alert("信息不完整");
    	}
    }
    function mitTwo(queryFlag){
    	var lssuingType = document.getElementById("lssuingType").value;
    	if( 
    	   jQuery("#returnwayId").val()!=""&&
    	   jQuery("#borrowMoneyId").val()!=""&&
    	   jQuery("#explainOneId").val()!="" ){
    		if(document.getElementById("RadioGroup3_0").checked ||document.getElementById("RadioGroup3_1").checked){ 
    			jQuery("#mitTwobutton").attr("disabled", true);
    			var form=document.getElementById("2_tabId2");
    			if(lssuingType=='8'){
    				form.action="<%=path %>/borrow/toTiroLssuing?updateFlag=2&queryFlag=${queryFlag}";
    			}else{
    				form.action="<%=path %>/borrow/toUpdateLssuing?updateFlag=2&queryFlag=${queryFlag}";
    			}
    			form.submit();
    			document.getElementById("mitTwobutton")	.style.background='gray';	
				document.getElementById("mitTwobutton").value='正在提交...';
				document.getElementById("mitTwobutton").disabled=false;
     		}else{
    			alert("信息不完整");
    		}
    	}else{
    		alert("信息不完整");
    	}
    }
    
    function secBoard(elementID,listName,n) {
   	 var elem = document.getElementById(elementID);
   	 var elemlist = elem.getElementsByTagName("li");
   	 for (var i=0; i<elemlist.length; i++) {
   	  elemlist[i].className = "normal";
   	  var m = i+1;
   	  document.getElementById(listName+"_"+m).className = "normal";
   	 }
   	  elemlist[n-1].className = "current";
   	  document.getElementById(listName+"_"+n).className = "current";
   	}
    function tabTest(mark){
     	if(mark==2){
    		 secBoard('hotnews_caption','list',2);
    		 alert("秒还失败，余额不足,请及时充值！");
    	}else if(mark==3){
    		 secBoard('hotnews_caption','list',3);
    	}
    }
    
	jQuery('.tablelist tbody tr:odd').addClass('odd');
	
	function imgSubmit(){
		if(setImagePreview()==true){
			var form=document.getElementById("2_tab_id3");
			form.action="<%=path %>/borrow/toSavePhoto?mark=3";
			form.submit();
		}
	}
 
	
 
	</script>


</head>


<body onload="tabTest(${mark})">

	<%-- <div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">会员管理</a></li>
    <li><a href="#">修改会员</a></li>
    </ul>
    </div> --%>
    
    <div class="rightinfo">
    
       <div id="hotnews_caption">
            <ul id="h_menu">                  
                 <li class="current" onclick="secBoard('hotnews_caption','list',1);">基本信息</li>
<!--                  <li class="normal" onclick="secBoard('hotnews_caption','list',2);">审核信息</li> -->
                 <li class="normal" onclick="secBoard('hotnews_caption','list',2);">借款方图片资料</li>
            </ul>
         </div> 
    
       <div id="hotnews_content">
        <div class="current" id="list_1">
        <form action="" method="post" id="2_tabId1"> 
            <input  value="${lssuing.recommend}" name="lssuing.recommend" id="recommend" type="hidden" />
        	<input  value="${lssuing.lssuingId}" name="lssuingId" id="lssuingId" type="hidden"/>
        	<input  value="${lssuing.lssuingType}" name="lssuingType" id="lssuingType" type="hidden"/>
        	<input  type="hidden" name="result" value="1">
           <ul class="m_forminfo">
         <%--   <li><label class="m_info_title">是否推荐：</label>  

              <label><input type="radio" name="lssuing.recommend" value="1" id="RadioGroup1_0" <c:if test="${lssuing.recommend==1}">checked</c:if> />  是</label>
              <label><input type="radio" name="lssuing.recommend" value="0" id="RadioGroup1_1"  <c:if test="${lssuing.recommend==0}">checked</c:if>  /> 否</label>  
            </li> --%>
             <li><label class="m_info_title">借款标题：</label>  
                 <input name="lssuing.title" type="text" class="dfinput" value="${lssuing.title}" id="titleId" size="37"/>
               <i>*</i>
            </li>
            <li><label class="m_info_title">借款方式：</label>  
                <select onchange="" id="returnwayId" class="input" name="returnway"  >
	            <option value="0">--请选择--</option>
                <c:forEach var="fs" items="${returnwayList}">
                	<c:if test="${fs.returnWayId==lssuing.returnway.returnWayId }">
                	   <option value="${fs.returnWayId }" selected="selected">${fs.returnWayName}</option></c:if>	
                	<c:if test="${fs.returnWayId!=lssuing.returnway.returnWayId }">
                	   <option value="${fs.returnWayId }">${fs.returnWayName}</option></c:if>		
                </c:forEach> 
                
              </select>
               <i>*</i>
            </li>
            <li><label class="m_info_title">借款金额：</label>  
                <input name="lssuing.borrowMoney"   onkeyup="value=value.replace(/[^\d\.]/g,'')" type="text" class="dfinput" id="borrowMoneyId" value="${lssuing.borrowMoney}" size="37"/>
               <i>*</i>
            </li>
            <li><label class="m_info_title">年/日利率：</label>  
                <input name="lssuing.rate" type="text"   onkeyup="value=value.replace(/[^\d\.]/g,'')"  class="dfinput" id="rateId" value="${lssuing.rate}" size="37"/>
                <i>*</i>
            </li>
            
 			<li><label class="m_info_title">借款期限：</label>  
                <select name="periodtime" class="input"  id="periodtimeId">
	              	 <option value="0"  selected="selected">-请选择月数-</option>              	 
 	              	 <c:forEach var="periodtime" items="${periodtimeList}">
 	              	 	<c:if test="${lssuing.periodtime.periodTimeId==periodtime.periodTimeId}">
		                    <option selected="selected" value="${periodtime.periodTimeId}">${periodtime.periodTimeName}</option></c:if>
		                <c:if test="${lssuing.periodtime.periodTimeId!=periodtime.periodTimeId}">
		                    <option  value="${periodtime.periodTimeId}">${periodtime.periodTimeName}</option></c:if>
					 </c:forEach> 	              	
	              </select>
	              <input value="${lssuing.periodtime.periodTimeId}" id="periodtimeListId" type="hidden"/>
	              <input value="${lssuing.periodday.periodDayId}" id="perioddayListId"  type="hidden"/>
	              <select name="periodday" class="input"  id="perioddayId" style="display:none">
	              	 <option value="0"  selected="selected">-请选择天数-</option>              	 
 	              	 <c:forEach var="periodday" items="${perioddayList}">
 	              	     <c:if test="${lssuing.periodday.periodDayId==periodday.periodDayId }">
		                    <option selected="selected"  value="${periodday.periodDayId}">${periodday.periodDayName}</option></c:if>
		                 <c:if test="${lssuing.periodday.periodDayId!=periodday.periodDayId }">
		                    <option  value="${periodday.periodDayId}">${periodday.periodDayName}</option></c:if>
					 </c:forEach> 	              	
	              </select>
             <input type="checkbox" name="checkbox" onclick="dayCheck()" id="dayCheckId" value="按天" style="float: left;margin-left:8px;margin-top: 10px " />&nbsp;按天
               <i>*</i>
              <script type="text/javascript">
              (function(){
              	  if(jQuery("#perioddayListId").val()!=""){
            		document.getElementById("periodtimeId").style.display="none";
              		document.getElementById("perioddayId").style.display="";
              		document.getElementById("dayCheckId").checked=true;
            	  }
              })();
              function dayCheck(){
                  if(document.getElementById("dayCheckId").checked){
                 		document.getElementById("periodtimeId").style.display="none";
                		document.getElementById("perioddayId").style.display="";
                		document.getElementById("periodtimeId").options[0].selected=true;
                		 
 
                  }else{
                 		document.getElementById("periodtimeId").style.display="";
                		document.getElementById("perioddayId").style.display="none";
                		document.getElementById("perioddayId").options[0].selected=true;
                   }
            }
              </script> 
            </li> 
            <li><label class="m_info_title">最大投标金额：</label>  
                <select onchange="" id="moneymaxId" class="input" name="moneymax"  >
	            <option value="0">不限制</option>
                <c:forEach var="sj" items="${moneymaxList}">
                	<c:if test="${sj.moneyMaxId==lssuing.moneymax.moneyMaxId }">
                	   <option value="${sj.moneyMaxId }" selected="selected">${sj.moneyMaxName}</option></c:if>	
                	<c:if test="${sj.moneyMaxId!=lssuing.moneymax.moneyMaxId }">
                	   <option value="${sj.moneyMaxId }" >${sj.moneyMaxName}</option></c:if>			
                </c:forEach> 
                
               </select>
               <i>*</i>
            </li>
            <li><label class="m_info_title">投标待收金额限制：</label>
            <c:if test="${empty lssuing.moneyLimit }"><input name="lssuing.moneyLimit"   onkeyup="value=value.replace(/[^\d\.]/g,'')"  type="text" class="dfinput" value="0.00" size="37"/></c:if>
            <c:if test="${!empty lssuing.moneyLimit }"><input name="lssuing.moneyLimit"   onkeyup="value=value.replace(/[^\d\.]/g,'')"  type="text" class="dfinput" value="${lssuing.moneyLimit}" size="37"/></c:if>
            <i>0.00表示无限制</i></li>
			 <li><label class="m_info_title">借款说明:</label><textarea name="lssuing.explains" id="explainsId" style="width:400px; height:100px; border:1px solid #ccc; padding:8px;">${lssuing.explains}</textarea><i>*</i></li>
            <input name="managerId" value="${session.manager.managerId}" type="hidden"/>
            <li><label class="m_info_title">&nbsp;</label><input name="" id="mitOnebutton" type="button" onclick="mitOne(${queryFlag})" class="btn" value="确定"/></li>
          </ul>
    	</form>
       </div> 
     
    
<!--          <div class="normal" id="list_2"> -->
<!--     	<form action="" method="post" id="2_tabId2"> -->
<!--     	<input  value="${lssuing.lssuingId}" name="lssuingId" type="hidden"/> -->
<!--           <ul class="m_forminfo"> -->
<!--             <li><label class="m_info_title">是否允许自动投标：</label>   -->

<!--               <label><input type="radio" name="lssuing.isInvest" value="1" id="RadioGroup1_0" <c:if test="${lssuing.isInvest==1}">checked</c:if> />  是</label> -->
<!--               <label><input type="radio" name="lssuing.isInvest" value="0" id="RadioGroup1_1"  <c:if test="${lssuing.isInvest==0}">checked</c:if> /> 否</label>   -->
<!--             </li> -->
 
<!--             <li><label class="m_info_title">借款标分类：</label><label> -->

<!-- 			<c:if test="${lssuing.lssuingType==1}">信用标</c:if> -->
<!--         	<c:if test="${lssuing.lssuingType==2}">担保标</c:if> -->
<!--         	<c:if test="${lssuing.lssuingType==3}">秒标</c:if> -->
<!--         	<c:if test="${lssuing.lssuingType==4}">净值标</c:if> -->
<!--         	<c:if test="${lssuing.lssuingType==5}">抵押标</c:if> -->
<!--         	<c:if test="${lssuing.lssuingType==6}">企业直投</c:if> -->
<!--         	<c:if test="${lssuing.lssuingType==9}">理财宝</c:if> -->
<!--         	<c:if test="${lssuing.lssuingType==8}">体验标</c:if> -->
<!-- 			 </label></li> -->
          <!--  <li><label class="m_info_title">借款管理费：</label><input name="lssuing.manageMoney"   onkeyup="value=value.replace(/[^\d\.]/g,'')"  type="text" class="dfinput" id="manageMoneyId" value="${lssuing.manageMoney}" size="37"/><i>默认为按后台设置计算出来的，如果私下有协议可以改</i></li>  --> 
<!--             <li><label class="m_info_title">募集时间(天)：</label>   -->
<!--                 <select onchange="" id="validtimeId" class="input" name="validtime"  > -->
<!-- 	            <option value="0">--请选择--</option> -->
<!--                 <c:forEach var="sj" items="${validtimeList}"> -->
<!--                 	<c:if test="${sj.validTimeId==lssuing.validtime.validTimeId }"> -->
<!--                 	   <option value="${sj.validTimeId }" selected="selected">${sj.validTimeName}</option></c:if>	 -->
<!--                 	<c:if test="${sj.validTimeId!=lssuing.validtime.validTimeId }"> -->
<!--                 	   <option value="${sj.validTimeId }">${sj.validTimeName}</c:if>		 -->
<!--                 </c:forEach>  -->
                
<!--               </select> -->
<!--                <i>在前台展示天数，如在担心在设定时间内不能募集完成，可修改延长</i> -->
<!--             </li> -->
<!--              <li><label class="m_info_title">最大投标金额：</label>   -->
<!--                 <select onchange="" id="moneymaxId" class="input" name="moneymax"  > -->
<!-- 	            <option value="0">不限制</option> -->
<!--                 <c:forEach var="sj" items="${moneymaxList}"> -->
<!--                 	<c:if test="${sj.moneyMaxId==lssuing.moneymax.moneyMaxId }"> -->
<!--                 	   <option value="${sj.moneyMaxId }" selected="selected">${sj.moneyMaxName}</option></c:if>	 -->
<!--                 	<c:if test="${sj.moneyMaxId!=lssuing.moneymax.moneyMaxId }"> -->
<!--                 	   <option value="${sj.moneyMaxId }" >${sj.moneyMaxName}</option></c:if>			 -->
<!--                 </c:forEach>  -->
                
<!--                </select> -->
<!--                <i>*</i> -->
<!--             </li> -->
<!--             <li><label class="m_info_title">投标待收金额限制：</label> -->
<!--             <c:if test="${empty lssuing.moneyLimit }"><input name="lssuing.moneyLimit"   onkeyup="value=value.replace(/[^\d\.]/g,'')"  type="text" class="dfinput" value="0.00" size="37"/></c:if> -->
<!--             <c:if test="${!empty lssuing.moneyLimit }"><input name="lssuing.moneyLimit"   onkeyup="value=value.replace(/[^\d\.]/g,'')"  type="text" class="dfinput" value="${lssuing.moneyLimit}" size="37"/></c:if> -->
<!--             <i>0.00表示无限制</i></li> -->
<!--             <li><label class="m_info_title">是否通过：</label>  -->
<!--                   <label>   -->
<!--                   <c:if test="${lssuing.state==0 ||lssuing.state==2  }"> -->
<!--                   <input type="radio" name="lssuing.state"  <c:if test="${lssuing.state==0}">checked</c:if> value="2" id="RadioGroup3_0" /> 初审通过，借款中  -->
<!--                   <label> <input type="radio" name="lssuing.state" value="5" id="RadioGroup3_1" /> 初审未通过 </label> -->
<!--                   </c:if> -->
<!--                   <c:if test="${lssuing.state==1 }"><input type="radio" name="lssuing.state" value="3" id="RadioGroup3_0" /> 复审通过，还款中 -->
<!--                   <label> <input type="radio" name="lssuing.state" value="6" id="RadioGroup3_1" /> 复审未通过 </label> -->
<!--                    </c:if> -->
                  
<!--                   </label> -->
<!--              </li> -->
             
<!--             <c:if test="${lssuing.state==0 || lssuing.state==2 }"> -->
<!--              <li><label class="m_info_title">初审处理意见：</label><textarea name="lssuing.explainOne"  id="explainOneId"   style="width:300px; height:60px; border:1px solid #ccc; padding:8px;">${lssuing.explainOne}</textarea><i>*</i></li> -->
<!--             </c:if> -->
<!--              <c:if test="${lssuing.state==1 }"> -->
<!--              <li><label class="m_info_title">复审处理意见：</label><textarea name="lssuing.explianTwo"  id="explainOneId"   style="width:300px; height:60px; border:1px solid #ccc; padding:8px;"></textarea><i>*</i></li> -->
<!--             </c:if> -->
<!--              <li style="float:left;"><label class="m_info_title">&nbsp;</label><input name="" id="mitTwobutton" type="button" onclick="mitTwo(${queryFlag})" class="btn" value="确定"/></li> -->
<!--           </ul> -->
<!--     			<input name="managerId" value="${session.manager.managerId}" type="hidden"/> -->
<!--     	</form> -->
           
<!--         </div>   -->
        <div class="normal" id="list_2">
        <form action="<%=path %>/borrow/toSavePhoto" method="post" enctype="multipart/form-data" id="2_tab_id3">
            
             	
             <ul class="m_forminfo">
              <li  style="margin-left: 50px ;float: left"><span style=" float: left">上传图片：
                 <input type="file" id="file" name="file" onchange="ajaxFileUpload('file')" style="border:0;background:transparent;width:250px;"/></span>
                 <span id="fileTip"  style="margin-right: 500px ;float: left" ></span> 
				 </li>
			  <li  style="margin-left: 50px;height: 10px"><br>图片展示：</li>
              <li>
            <div class="preview"  style="margin-left: 100px ;width: 1550px" id="tpxsId">
       		<c:forEach var="tp" items="${lssuing.lssingphotos}">
            <div class="pre1" id="pre1${tp.photoId}">                   
                 <div class="pre1_t"><img src="<%=basePath%>${tp.photo}" /> 
                 <input value="删除" id="pic${tp.photoId}" style="margin-top: 10px;cursor: pointer;" onclick="deletePto('${tp.photoId}')" type="button"/>
                 </div> 
            </div>
            </c:forEach>
            <input id="imgUrlId" name="lssingphoto.photo" type="hidden"/>
            <input  value="${lssuing.lssuingId}" name="lssuingId" type="hidden"/>
            <div class="pre1" id="tpylId" style="display: none">                   
                 <div class="pre1_t"><img alt="" id="imgXSID" name="" src=""> <P></div> 
            </div>
            </div>
             </li>
          </ul>
   			</form>
        </div>     
	</div> 
    
    </div>
    
 
</body>

</html>
