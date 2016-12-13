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
<!--  <script type="text/javascript" src="<%=basePath %>/js/jquery-1.4.2.js"></script> -->
 <script type="text/javascript" src="<%=basePath %>/js/jquery-1.8.2.min.js"></script>

<script type="text/javascript" src="../back/js/ajaxfileupload.js"></script>

<script type="text/javascript">

function ajaxFileUpload(str)
{  var  $p=jQuery.noConflict();
   
	$p.ajaxFileUpload
	(
		{
			url:'<%=path %>/pic/toSave',//用于文件上传的服务器端请求地址
			secureuri:false,//一般设置为false
			fileElementId:str,//文件上传空间的id属性  <input type="file" id="file" name="file" />
			dataType: 'json',//返回值类型 一般设置为json
			success: function (data, status)  //服务器成功响应处理函数
			{
				 
				var phoneStr=$p("#phoneStr").val();
				var str= $p("#tpxsId").html();
				var str1=""; 
				var urls=data.urlStr;
				var let = new Array('a','b','c','d','e','f','g','h','i','g','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z');
				var random1 = Math.round(Math.random()*25)+0;
				var random2 = Math.round(Math.random()*25)+0;
				var random3 = Math.round(Math.random()*25)+0;
				var random4 = Math.round(Math.random()*25)+0;
				var random5 = Math.round(Math.random()*25)+0;
				var random=let[random1]+let[random2]+let[random3]+let[random4]+let[random5];
				var str1 =str+"<div class='pre1 pre1only'  id='pre1"+random+"'> "+                 
                 "<div class='pre1_t' ><img src='<%=basePath%>"+urls+"'/>"+
                 "<input type='hidden' id='urls"+random+"' value='"+urls+"'/>"+
                  " <input type='button' value='删除'  style='margin-top: 10px;cursor: pointer;'  id='pic"+random+"' onclick='deletePto(&quot;"+random+"&quot; )'/> </div> "+
                 "</div>";
				$p("#tpxsId").html(str1);
			
				if(phoneStr==""||phoneStr==null){
					$p("#phoneStr").val(urls);
				}else{
					$p("#phoneStr").val(phoneStr+","+urls);
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



function ajaxFileUpload2(str)
{  var  $p=jQuery.noConflict();
   
	$p.ajaxFileUpload
	(
		{
			url:'<%=path %>/pic/toSave',//用于文件上传的服务器端请求地址
			secureuri:false,//一般设置为false
			fileElementId:str,//文件上传空间的id属性  <input type="file" id="file" name="file" />
			dataType: 'json',//返回值类型 一般设置为json
			success: function (data, status)  //服务器成功响应处理函数
			{
				var urls=data.urlStr;
				var let = new Array('a','b','c','d','e','f','g','h','i','g','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z');
				var random1 = Math.round(Math.random()*25)+0;
				var random2 = Math.round(Math.random()*25)+0;
				var random3 = Math.round(Math.random()*25)+0;
				var random4 = Math.round(Math.random()*25)+0;
				var random5 = Math.round(Math.random()*25)+0;
				var random=let[random1]+let[random2]+let[random3]+let[random4]+let[random5];
				var str1 ="<div class='pre1 pre1only'  id='pre2"+random+"'> "+                 
                 "<div class='pre1_t' ><img src='<%=basePath%>"+urls+"'/>"+
                  " <input type='button' value='删除'  style='margin-top: 10px;cursor: pointer;'  id='pic"+random+"' onclick='deletePto2(&quot;"+random+"&quot; )'/> </div> "+
                 "</div>";
				$p("#tpxsId2").html(str1);
				document.getElementById('lineTop').style.marginTop ="270px";
				$p("#showImg").val(urls);
			},
			error: function (data, status, e)//服务器响应失败处理函数
			{
				alert(e);
			}
		}
	)
	
	return false;

}



function deletePto(getid)
{  

	 var  $p=jQuery.noConflict();
	 $p("#pre1"+getid).hide();
	 var phoneStr=$p("#phoneStr").val();
     var str=$p("#urls"+getid).val();
     var phone=phoneStr.split(",");
     var urlStr="";
	 for(var ii=0 ; ii<phone.length ; ii++){
    	if(phone[ii]!=str){
   		   if(urlStr==""||urlStr==null){
				urlStr=phone[ii];
			}else{
				urlStr=urlStr+","+phone[ii];
			}
    	}
    }
	$p("#phoneStr").val(urlStr);
	return false;
}
function deletePto2(getid)
{  

	 var  $p=jQuery.noConflict();
	 $p("#pre2"+getid).hide();
	  $p("#showImg").val("");
	document.getElementById('lineTop').style.marginTop ="30px";
	
	return false;
}

    function mitOne(queryFlag){
    	if(jQuery("#titleId").val()!=""&&
    	   jQuery("#returnwayId").val()!=""&&
    	   jQuery("#returnwayId").val()!="0"&&
    	   jQuery("#returnwayId1").val()!=""&&
    	   jQuery("#returnwayId1").val()!="0"&&
    	   jQuery("#borrowMoneyId").val()!=""&&
    	   jQuery("#moneyMinId").val()!="" && 
    	   jQuery("#totalUnit").val()!="" && 
    	   jQuery("#validtime").val()!="" &&
    	   jQuery("#rateId").val()!="" &&
    	   ((jQuery("#periodtimeId").val()!="" && jQuery("#periodtimeId").val()!="0")|| (jQuery("#perioddayId").val()!="" && jQuery("#perioddayId").val()!="0")) &&
    	   jQuery("#award").val()!="" &&
    	   jQuery("#copies").val()!="" &&
    	   jQuery("#totalUnit1").val()!="" ){
    		if(jQuery("#periodtimeId").val()!="0" || jQuery("#perioddayId").val()!="0"){
    			jQuery("#mitOnebutton").attr("disabled", true);
    			var form=document.getElementById("2_tabId1");
    			form.action="<%=path %>/borrow/saveFirmLssuing?updateFlag=1&queryFlag="+queryFlag;
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
 
	function count(){
	
		var  $p=jQuery.noConflict();
		var borrowMoney =$p("#borrowMoneyId").val()*1;
		var t = document.getElementById("moneyMinId"); 
		var moneyMin =t.options[t.selectedIndex].text;
		var moneyLet=moneyMin.split("元");
		var money=0;
		if(moneyLet.length>0){
			money=moneyLet[0]*1;
		}
		var count=borrowMoney/money;
		if(moneyLet=="-请选择金额-"){
			$p("#totalUnit").val(0);
		}else{
			$p("#totalUnit").val( Math.ceil(count));
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
                 <li class="normal" onclick="secBoard('hotnews_caption','list',2);">借款方资料</li>
                 <li class="normal" onclick="secBoard('hotnews_caption','list',3);">借款方图片资料</li>
            </ul>
         </div> 
    
       <div id="hotnews_content">
        <div class="current" id="list_1">
        <form action="" method="post" id="2_tabId1"> 
           <ul class="m_forminfo">
           <li><label class="m_info_title">是否推荐：</label>  

              <label><input type="radio" name="lssuing.recommend" value="1" id="RadioGroup1_0" <c:if test="${lssuing.isInvest==1}">checked</c:if> />  是</label>
              <label><input type="radio" name="lssuing.recommend" value="0" id="RadioGroup1_1"  <c:if test="${lssuing.isInvest==0}">checked</c:if>  checked="checked"/> 否</label>  
            </li>
<!--            <li><label class="m_info_title">是否允许自动投标：</label>   -->

<!--               <label><input type="radio" name="lssuing.isInvest" value="1" id="RadioGroup1_0" <c:if test="${lssuing.isInvest==1}">checked</c:if> /> 允许</label> -->
<!--               <label><input type="radio" name="lssuing.isInvest" value="0" id="RadioGroup1_1"  <c:if test="${lssuing.isInvest==0}">checked</c:if> /> 不允许</label>   -->
<!--             </li> -->
             <li><label class="m_info_title">借款标题：</label>  
                 <input name="lssuing.title" type="text" class="dfinput" value="" id="titleId" size="37"/>
               <i>*</i>
            </li>
            <li><label class="m_info_title">借款方式：</label>  
                <select onchange="" id="returnwayId" class="input" name="lssuing.returnway.returnWayId"  >
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
            <li><label class="m_info_title">借款人：</label>  
            	<select onchange="" id="returnwayId1" class="input" name="lssuing.uservip.userId"  >
	            <option value="0">--请选择--</option>
                <c:forEach var="fs" items="${uservipList}">
                	   <option value="${fs.userId }">${fs.userName}</option>
                </c:forEach> 
              </select>
               <i>*</i>
            </li>
            <li><label class="m_info_title">借款金额：</label>  
                <input name="lssuing.borrowMoney" onblur="count();"  onkeyup="value=value.replace(/[^\d\.]/g,'')" type="text" class="dfinput" id="borrowMoneyId" value="" size="37"/>
               <i>*</i>
            </li>
            <li>
            <label class="m_info_title">流转金额：</label>  
            <select name="lssuing.moneymin.moneyMinId" id="moneyMinId" onchange="count();" class="input">
	              	 <option value=""  selected="selected">-请选择金额-</option>
 	              	<c:forEach var="moneymin" items="${moneyminList}">
		             <option  value="${moneymin.moneyMinId}">${moneymin.moneyMinName}元</option>
					 </c:forEach> 
	              </select>  
            <i>*例如:50元</i>
            </li>
            <li>
            <label class="m_info_title">总流转份数：</label>  
            <input name="lssuing.totalUnit"  type="text" class="dfinput" id="totalUnit" value="" size="37"/>
            <i>*如果计算错误可以手动修改</i>
            </li>
<!--             <li><label class="m_info_title">有效时间：</label>   -->
<!--             	 <select name="lssuing.validtime.validTimeId" id="validtime" class="input" > -->
<!-- 	              	 <option value=""  selected="selected">-请选择时间-</option> -->
<!-- 	              	<c:forEach  var="validtime" items="${validtimeList}">	 -->
<!-- 		             	<option   value="${validtime.validTimeId}">${validtime.validTimeName}天</option> -->
<!-- 					 </c:forEach>  -->
<!--                 </select>   -->
<!--                <i>*</i> -->
<!--             </li> -->
            <li><label class="m_info_title">年利率：</label>  
                <input name="lssuing.rate" type="text"   onkeyup="value=value.replace(/[^\d\.]/g,'')"  class="dfinput" id="rateId" value="" size="37"/>
                <i>* 例如：13%</i>
            </li>
            
 			<li><label class="m_info_title">借款期限：</label>  
                <select name="lssuing.periodtime.periodTimeId" class="input"  id="periodtimeId">
	              	 <option value="0"  selected="selected">-请选择月数-</option>              	 
 	              	 <c:forEach var="periodtime" items="${periodtimeList}">
		                    <option  value="${periodtime.periodTimeId}">${periodtime.periodTimeName}</option>
					 </c:forEach> 	              	
	              </select>
<!-- 	              <input value="" id="periodtimeListId" type="hidden"/> -->
<!-- 	              <input value="" id="perioddayListId"  type="hidden"/> -->
	              <select name="lssuing.periodday.periodDayId" class="input"  id="perioddayId" style="display:none">
	              	 <option value="0"  selected="selected">-请选择天数-</option>              	 
 	              	 <c:forEach var="periodday" items="${perioddayList}">
		                    <option  value="${periodday.periodDayId}">${periodday.periodDayName}</option>
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
             <li>
            <label class="m_info_title">网站奖励：</label>  
            <input name="lssuing.award"  type="text" class="dfinput" id="award" value="" size="37"/>
            <i>%例如一个标借款期限6个月，你打算每月奖励0.01%，那么0.01*6=0.06，也就是应该在此处填写0.06</i>
            </li>
			 <li><label class="m_info_title">单人最大购买份数:</label><input name="lssuing.copies"  type="text" class="dfinput" id="copies" value="" size="37"/><i>0表示无限制</i></li>
             <li><label class="m_info_title">借款管理费:</label><input name="lssuing.manageMoney"  type="text" class="dfinput" id="totalUnit1" value="" size="37"/><i>*</i></li>
            <li><label class="m_info_title">&nbsp;</label><input name="" id="mitOnebutton" type="button" onclick="mitOne(${queryFlag})" class="btn" value="确定"/></li>
          </ul>
    	
       </div> 
    
    
         <div class="normal" id="list_2">
    	
    	<input  value="" name="lssuing.lssuingComp.showImg" id="showImg" type="hidden"/>
          <ul class="m_forminfo">

             <li> <label class="m_info_title">上传图片：</label>
                 <input type="file" id="file2" name="file2" onchange="ajaxFileUpload2('file2')" style="border:0;background:transparent;width:250px;"/>
                 <span id="fileTip"  style="margin-right: 500px ;float: left" ></span> 
			 </li>
			 <li><label class="m_info_title">图片展示：</label>
			 <div class="preview"  style="margin-left: 100px ;width: 1000px" id="tpxsId2">
			 	&nbsp;
			 </div>
			 </li>
			 <li id="lineTop">
			 <label class="m_info_title">借款方商业概述：</label>
			<textarea name="lssuing.lssuingComp.compInfo"  style="width:800px; height:100px; border:solid 1px #000000; border-radius:5px; resize:none;"></textarea>
			 </li>
			 <li>
			 <label class="m_info_title">借款方资产情况：</label>
			 <textarea name="lssuing.lssuingComp.compFund" style="width:800px; height:100px; border:solid 1px #000000; border-radius:5px; resize:none;"></textarea>
			 
			 </li>
			 <li>
			 <label class="m_info_title">借款方资金用途：</label>
			 <textarea name="lssuing.lssuingComp.borrowUse" style="width:800px; height:100px; border:solid 1px #000000; border-radius:5px; resize:none;"></textarea>
			 </li>
			 <li>
			 <label class="m_info_title">风险控制措施：</label>
			 <textarea name="lssuing.lssuingComp.compWinCon" style="width:800px; height:100px; border:solid 1px #000000; border-radius:5px; resize:none;"></textarea>
			 </li>
             <li style="float:left;"><label class="m_info_title">&nbsp;</label><input name="" id="mitTwobutton" type="button" onclick="mitOne(${queryFlag})" class="btn" value="确定"/></li>
          </ul>
   
           
        </div>  
        <div class="normal" id="list_3">
       
             <input type="hidden" name="phoneStr" id="phoneStr" value="">
             <ul class="m_forminfo">
              <li  style="margin-left: 50px ;float: left"><span style=" float: left">上传图片：
                 <input type="file" id="file" name="file" onchange="ajaxFileUpload('file')" style="border:0;background:transparent;width:250px;"/></span>
                 <span id="fileTip"  style="margin-right: 500px ;float: left" ></span> 
				 </li>
			  <li  style="margin-left: 50px;height: 10px"><br>图片展示：</li>
              <li>
            <div class="preview"  style="margin-left: 100px ;width: 1000px" id="tpxsId">
            
            <input id="imgUrlId" name="lssingphoto.photo" type="hidden"/>
            <input  value="${lssuing.lssuingId}" name="lssuingId" type="hidden"/>
            <div class="pre1" id="tpylId" style="display: none">                   
                 <div class="pre1_t"><img alt="" id="imgXSID" name="" src=""> <P></div> 
            </div>
            </div>
             </li>
             <li style="float:left;"><label class="m_info_title">&nbsp;</label><input name="" id="mitTwobutton" type="button" onclick="mitOne(${queryFlag})" class="btn" value="确定"/></li>
          </ul>
   			</form>
        </div>     
	</div> 
    
    </div>
    
 <script type="text/javascript" src="<%=basePath %>/js/ajaxfileupload.js"></script>
</body>

</html>
