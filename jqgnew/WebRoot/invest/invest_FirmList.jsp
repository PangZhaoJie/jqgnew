<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
  <%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();if(path!=null && !path.equals("")){	if(path.contains("/")){		if(path.equals("/")){path = "";	}}else{	path += "/";}} 
String basePath = "";if(request.getServerPort()==80){basePath = request.getScheme()+"://"+request.getServerName()+path+"/";}else{basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";}    
String satate = request.getParameter("state");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="icon" href="<%=path %>/image/favicon/favicon.ico" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我要投资-招标项目</title>
<link href="../css/public.css" type="text/css" rel="stylesheet"/>
<link href="../css/invest.css" type="text/css" rel="stylesheet" />
<!--导航下拉菜单 js-->
<script type="text/javascript" src="../js/jquery-1.8.2.min.js"></script>

<!--表单下拉列表框-->
<script type="text/javascript" src="../js/select2css.js"></script>
</head>
<body> 
<!--头部开始-->
<div class="header">
    <jsp:include page="../header.jsp" />
    
</div>
<!--头部结束-->

<div class="maincontent">
  <div class="conbox pt20">
    <P class="guide">当前位置：<a href="<%=path%>/index.action">网站首页</a> > <a href="<%=path%>/tender/totenderList?lussingType=6">企业直投</a></P>
    <div class="choose fn_cle">
       <h3>筛选条件</h3>
       <div class="choose_nr">
          <div class="choose1"><h4>还款方式</h4> 
            <div id="uboxstyle">
                 <select name="0" id="time0">
	              	 <option value="0"  selected="selected">-请选择还款方式-</option>
		            <c:forEach var="returnway" items="${returnways}">
		             <option  value="${returnway.returnWayId}">${returnway.returnWayName}</option>
					 </c:forEach> 	  
                    
	              </select>
                   
             </div>
           </div>
      
          
          <div class="choose1" ><h4>借款期限</h4> 
          <div id="time">
                 <div id="uboxstyle">
                    <select name="time" id="time1">
	              	 <option value="0"  selected="selected">-请选择时间-</option>
		              <c:forEach var="periodtime" items="${periodtimes}">
		             <option  value="${periodtime.periodTimeId}">${periodtime.periodTimeName}</option>
					 </c:forEach> 	
	              </select>
	              </div>
	      </div>
	      <div id="periodDayNameDivId" style="display:none ">
 	               <div id="uboxstyle">
                    <select name="periodday" id="perioddayId" >
	              	 <option value="0"  selected="selected">-请选择天数-</option>              	 
 	              	 <c:forEach var="periodday" items="${perioddays}">
		                <option  value="${periodday.periodDayId}">${periodday.periodDayName}</option>
					 </c:forEach> 	              	
	              </select>  
                  </div>       
              </div>
      <input type="checkbox" name="checkbox" onclick="dayCheck()"  id="dayCheckId" value="按天"  style="margin-left: 10px"/><em>按天</em> 
        </div>
       
          

          <div class="choose1"><h4>项目状态</h4> 
                 <div id="uboxstyle">
                    <select name="bid" id="bid">
	              	 <option value="100"  selected="selected">-请选择-</option>
		              <option value="2" >正在募集</option>
		             <option value="3" >正在回款</option>
		             <option value="4" >募集成功</option>
		            </select>
                    
                  </div>
                 
          </div>
          <div class="choose1"><h4>还款奖励</h4>  
           <p style="font-size:14px;">
                   <label style="padding-right:15px;"> <input type="radio" name="RadioGroup2" value="1" id="RadioGroup1_0" style="border:0;"/> <em>只显示奖励的</em></label>
                  
                   <label>  <input type="radio" name="RadioGroup2" value="0" id="RadioGroup1_1" style="border:0;"/><em>不显示奖励的</em></label>
          <label  style="display: none">  <input type="radio" name="RadioGroup2" value="2" id="RadioGroup1_2" style="border:0;" checked="checked"/><em>不显示奖励的</em></label>
           </p>
          
          </div>
          <div class="choose2"><h4>金额范围</h4>  
             <input name="startMoney" type="text" size="15"  id="startMoney" onclick="text()"/><em>元</em>
                <span>到</span>
             
             <input name="endMoney" type="text" size="15" id="endmoney" onclick="text()"/><em>元</em>
                  <em id="positiveId"  style="display: none;color: red;" >金额必须为正整数</em>
                  <em id="compareId"  style="display: none;color: red;" >结束金额必须大于开始金额</em>
          </div>
                 
          <div class="search_btn"><input type="button" class="search_bt"  onclick="getJSONDatas(1,10)" value="搜&nbsp;&nbsp;索" /></div>     
       </div>
    </div>
    
    
    
    <div class="invest_list fn_cle"  style="background-color: ;">
    
       <div id="hotnews_caption">
         <ul id="myTop1">                  
            <li class="current" onclick="secBoard('hotnews_caption','list',1);">正在募集</li>
<!--             <li class="normal" onclick="secBoard('hotnews_caption','list',2);" >即将上线</li> -->
<!--             <li class="normal" onclick="secBoard('hotnews_caption','list',3);">复审中的借款</li> -->
            <li class="normal" onclick="secBoard('hotnews_caption','list',2);">正在回款</li>
            <li class="normal" onclick="secBoard('hotnews_caption','list',3);">募集成功</li>
         </ul>
      </div>
     <div id="hotnews_content"> 
      <div id="myCont1">
         <div class="current" id="list_1">
        <div class="tender_list">
<div  id="lssuingsSerach" style="display: none">
 <div class="list_4_table">

     <table width="1140" border="0" align="center" cellspacing="0" cellpadding="0" class="tbjl_table" id="listTables">
  <tr  class="header">	          	          	              	  	        
  
    <td  style="width:250px;">项目名称</td><td>信用等级</td><td>当前利率</td> <td>投标奖励</td><td>借款总额</td><td>借款期限</td><td>进度</td><td>状态</td>
  	</tr>
  </table>
  </div>
  <div id="pageroots" class="page" style="display: none;">
  	<span id="currentPages">第页</span>&nbsp; &nbsp; 
  	<span id="totalPages">共页</span>&nbsp; &nbsp; 
  	<span><a id="firstPages" href="">首页</a></span>&nbsp; &nbsp; 
  	<span><a id="prevPages" href="">上一页</a></span>&nbsp; &nbsp; 
  	<span><a id="nextPages" href="">下一页</a></span>&nbsp; &nbsp; 
  	<span><a id="lastPages" href="">最后一页</a></span>&nbsp; &nbsp; 
  </div>
  </div>
  <div id="lssuings">
 <div class="list_4_table">

     <table width="1140" border="0" align="center" cellspacing="0" cellpadding="0" class="tbjl_table" id="listTable">
  <tr  class="header">	          	          	              	  	        
  
    <td  style="width:250px;">项目名称</td><td>信用等级</td><td>当前利率</td> <td>投标奖励</td><td>借款总额</td><td>借款期限</td><td>进度</td><td>状态</td>
  	</tr>
  </table>
  </div>
  <div id="pageroot" class="page" style="display: none;">
  	<span id="currentPage">第页</span>&nbsp; &nbsp; 
  	<span id="totalPage">共页</span>&nbsp; &nbsp; 
  	<span><a id="firstPage" href="">首页</a></span>&nbsp; &nbsp; 
  	<span><a id="prevPage" href="">上一页</a></span>&nbsp; &nbsp; 
  	<span><a id="nextPage" href="">下一页</a></span>&nbsp; &nbsp; 
  	<span><a id="lastPage" href="">最后一页</a></span>&nbsp; &nbsp; 
  </div>
  </div>
       </div>
      </div>
      
      
<div id="list_2"  class="normal">
 <div class="list_4_table">

     <table width="1140" border="0" align="center" cellspacing="0" cellpadding="0" class="tbjl_table" id="listTable1">
  		<tr  class="header">	          	          	              	  	        
    		<td  style="width:250px;">项目名称</td><td>信用等级</td><td>当前利率</td> <td>投标奖励</td><td>借款总额</td><td>借款期限</td><td>进度</td><td>状态</td>
  		</tr>
  	</table>
 </div>
  <div id="pageroot1" class="page" style="display: none;">
  	<span id="currentPage1">第页</span>&nbsp; &nbsp; 
  	<span id="totalPage1">共页</span>&nbsp; &nbsp; 
  	<span><a id="firstPage1" href="">首页</a></span>&nbsp; &nbsp; 
  	<span><a id="prevPage1" href="">上一页</a></span>&nbsp; &nbsp; 
  	<span><a id="nextPage1" href="">下一页</a></span>&nbsp; &nbsp; 
  	<span><a id="lastPage1" href="">最后一页</a></span>&nbsp; &nbsp; 
  </div>
</div>

<div id="list_3"  class="normal">
 <div class="list_4_table">

     <table width="1140" border="0" align="center" cellspacing="0" cellpadding="0" class="tbjl_table" id="listTable2">
  		<tr  class="header">	          	          	              	  	        
    		<td  style="width:250px;">项目名称</td><td>信用等级</td><td>当前利率</td> <td>投标奖励</td><td>借款总额</td><td>借款期限</td><td>进度</td><td>状态</td>
  		</tr>
  	</table>
 </div>
  <div id="pageroot2" class="page" style="display: none;">
  	<span id="currentPage2">第页</span>&nbsp; &nbsp; 
  	<span id="totalPage2">共页</span>&nbsp; &nbsp; 
  	<span><a id="firstPage2" href="">首页</a></span>&nbsp; &nbsp; 
  	<span><a id="prevPage2" href="">上一页</a></span>&nbsp; &nbsp; 
  	<span><a id="nextPage2" href="">下一页</a></span>&nbsp; &nbsp; 
  	<span><a id="lastPage2" href="">最后一页</a></span>&nbsp; &nbsp; 
  </div>
</div>

<div id="list_4"  class="normal">
 <div class="list_4_table">

     <table width="1140" border="0" align="center" cellspacing="0" cellpadding="0" class="tbjl_table" id="listTable3">
  		<tr  class="header">	          	          	              	  	        
    		<td  style="width:250px;">项目名称</td><td>信用等级</td><td>当前利率</td> <td>投标奖励</td><td>借款总额</td><td>借款期限</td><td>进度</td><td>状态</td>
  		</tr>
  	</table>
 </div>
  <div id="pageroot3" class="page" style="display: none;">
  	<span id="currentPage3">第页</span>&nbsp; &nbsp; 
  	<span id="totalPage3">共页</span>&nbsp; &nbsp; 
  	<span><a id="firstPage3" href="">首页</a></span>&nbsp; &nbsp; 
  	<span><a id="prevPage3" href="">上一页</a></span>&nbsp; &nbsp; 
  	<span><a id="nextPage3" href="">下一页</a></span>&nbsp; &nbsp; 
  	<span><a id="lastPage3" href="">最后一页</a></span>&nbsp; &nbsp; 
  </div>
</div>


<script type="text/javascript" src="../js/tabs.js" language="javascript"></script>
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

<script type="text/javascript" >

function getJSONData(currentPage,pageSize) {
	 document.getElementById("pageroots").style.display="none";
	var url = "tender/lssuinglistPage.action?currentPage="+currentPage+"&lussingType=6&pageSize="+pageSize+"&satate=" + 2+"&temp=" +Math.random();
	 jQuery.getJSON(url,function(data){
		var cp = data.currentPage;
		var tp = data.totalPage;
		var  $phpernotetable =  jQuery("#listTable");
		 $phpernotetable.empty();
		 
		str = '<tr class="header">  <td  >项目名称</td><td style="width:140px;" >当前利率</td> <td>投标奖励</td><td>借款总额</td><td>借款期限</td><td>进度</td><td>状态</td></tr>';
		var jsonRoot = data.jsonRoot;
		if(tp!=1&&tp!=0)
		{
	
    	 document.getElementById("pageroot").style.display="";
		}
		for(var i = 0; i < jsonRoot.length; i++) {
			str += '<tr><td style="text-align: left; "><a href="' + decodeURI(jsonRoot[i].actions, "utf-8") + '&lussingType=6" ><img src="../images/loantype/firm.gif" />'+ jsonRoot[i].picture+ jsonRoot[i].title + "</a></td><td>"
				   + decodeURI(jsonRoot[i].rate, "utf-8") + "%</td><td>"
				   + jsonRoot[i].wate+ "</td><td>"
				   + decodeURI(jsonRoot[i].money, "utf-8") + "</td><td>"
				   + decodeURI(jsonRoot[i].time, "utf-8") + '</td><td style="text-align: left; padding-left:20px;" ><div style="width:135px;margin:0 auto"><span class="pos_jdt" style="left:'
				 + decodeURI(jsonRoot[i].bear, "utf-8")/1.1 + '%">'
				 + decodeURI(jsonRoot[i].bear, "utf-8") + '%</span><div class="jdt_box"><span style="width:'
				 + decodeURI(jsonRoot[i].bear, "utf-8") + '%;" class="jdt_cont"></span></div></div></td><td> <a href="' + decodeURI(jsonRoot[i].action, "utf-8") + '&lussingType=6" class="' + decodeURI(jsonRoot[i].stataclass , "utf-8") +'">'
				   + jsonRoot[i].state + "</td></tr>";
			 
			}         
		
		 $phpernotetable.html(str);
		 jQuery("#currentPage").text("第"+cp+"页");
		 jQuery("#totalPage").text("共"+tp+"页");
		 jQuery("#firstPage").attr("href","javascript:getJSONData(1,10)");
		 jQuery("#prevPage").attr("href","javascript:getJSONData("+(parseInt(cp)-1)+",10)");
		 jQuery("#nextPage").attr("href","javascript:getJSONData("+(parseInt(cp)+1)+",10)");
		 jQuery("#lastPage").attr("href","javascript:getJSONData("+(parseInt(tp))+",10)");
	});
}

function getJSONData2(currentPage,pageSize) {
	 document.getElementById("pageroots").style.display="none";
	var url = "tender/lssuinglistPage.action?currentPage="+currentPage+"&lussingType=6&pageSize="+pageSize+"&satate=" + 3+"&temp=" +Math.random();
	 jQuery.getJSON(url,function(data){
		var cp = data.currentPage;
		var tp = data.totalPage;
		var  $phpernotetable =  jQuery("#listTable2");
		 $phpernotetable.empty();
		str = '<tr class="header">  <td  >项目名称</td><td style="width:140px;" >当前利率</td> <td>投标奖励</td><td>借款总额</td><td>借款期限</td><td>进度</td><td>状态</td></tr>';
		var jsonRoot = data.jsonRoot;
		if(tp !=1&&tp !=0)
		{
	
    	 document.getElementById("pageroot2").style.display="";
		}
		for(var i = 0; i < jsonRoot.length; i++) {
			str += '<tr><td style="text-align: left; "><a href="' + decodeURI(jsonRoot[i].actions, "utf-8") + '&lussingType=6" ><img src="../images/loantype/firm.gif" />'+ jsonRoot[i].picture+ jsonRoot[i].title + "</a></td><td>"
				   + decodeURI(jsonRoot[i].rate, "utf-8") + "%</td><td>"
				   + jsonRoot[i].wate+ "</td><td>"
				   + decodeURI(jsonRoot[i].money, "utf-8") + "</td><td>"
				   + decodeURI(jsonRoot[i].time, "utf-8") + '</td><td style="text-align: left; padding-left:20px;" ><div style="width:135px;margin:0 auto"><span class="pos_jdt" style="left:'
				 + decodeURI(jsonRoot[i].bear, "utf-8")/1.1 + '%">'
				 + decodeURI(jsonRoot[i].bear, "utf-8") + '%</span><div class="jdt_box"><span style="width:'
				 + decodeURI(jsonRoot[i].bear, "utf-8") + '%;" class="jdt_cont"></span></div></div></td><td> <a href="' + decodeURI(jsonRoot[i].action, "utf-8") + '&lussingType=6" class="' + decodeURI(jsonRoot[i].stataclass , "utf-8") +'">'
				   + jsonRoot[i].state + "</td></tr>";
			}	 
		
		 $phpernotetable.html(str);
		 jQuery("#currentPage2").text("第"+cp+"页");
		 jQuery("#totalPage2").text("共"+tp+"页");
		 jQuery("#firstPage2").attr("href","javascript:getJSONData2(1,10)");
		 jQuery("#prevPage2").attr("href","javascript:getJSONData2("+(parseInt(cp)-1)+",10)");
		 jQuery("#nextPage2").attr("href","javascript:getJSONData2("+(parseInt(cp)+1)+",10)");
		 jQuery("#lastPage2").attr("href","javascript:getJSONData2("+(parseInt(tp))+",10)");
	});
}
function getJSONData3(currentPage,pageSize) {
	 document.getElementById("pageroots").style.display="none";
	var url = "tender/lssuinglistPage.action?currentPage="+currentPage+"&lussingType=6&pageSize="+pageSize+"&satate=" + 4+"&temp=" +Math.random();
	 jQuery.getJSON(url,function(data){
		var cp = data.currentPage;
		var tp = data.totalPage;
		var  $phpernotetable =  jQuery("#listTable3");
		 $phpernotetable.empty();
		str = '<tr class="header">  <td  >项目名称</td><td style="width:140px;" >当前利率</td> <td>投标奖励</td><td>借款总额</td><td>借款期限</td><td>进度</td><td>状态</td></tr>';
		var jsonRoot = data.jsonRoot;
		if(tp !=1&&tp !=0)
		{
	
    	 document.getElementById("pageroot3").style.display="";
		}
		for(var i = 0; i < jsonRoot.length; i++) {
			str += '<tr><td style="text-align: left; "><a href="' + decodeURI(jsonRoot[i].actions, "utf-8") + '&lussingType=6" ><img src="../images/loantype/firm.gif" />'+ jsonRoot[i].picture+ jsonRoot[i].title + "</a></td><td>"
				   + decodeURI(jsonRoot[i].rate, "utf-8") + "%</td><td>"
				   + jsonRoot[i].wate+ "</td><td>"
				   + decodeURI(jsonRoot[i].money, "utf-8") + "</td><td>"
				   + decodeURI(jsonRoot[i].time, "utf-8") + '</td><td style="text-align: left; padding-left:20px;" ><div style="width:135px;margin:0 auto"><span class="pos_jdt" style="left:'
				 + decodeURI(jsonRoot[i].bear, "utf-8")/1.1 + '%">'
				 + decodeURI(jsonRoot[i].bear, "utf-8") + '%</span><div class="jdt_box"><span style="width:'
				 + decodeURI(jsonRoot[i].bear, "utf-8") + '%;" class="jdt_cont"></span></div></div></td><td> <a href="' + decodeURI(jsonRoot[i].action, "utf-8") + '&lussingType=6" class="' + decodeURI(jsonRoot[i].stataclass , "utf-8") +'">'
				   + jsonRoot[i].state + "</td></tr>";
		}         
		
		 $phpernotetable.html(str);
		 jQuery("#currentPage3").text("第"+cp+"页");
		 jQuery("#totalPage3").text("共"+tp+"页");
		 jQuery("#firstPage3").attr("href","javascript:getJSONData3(1,10)");
		 jQuery("#prevPage3").attr("href","javascript:getJSONData3("+(parseInt(cp)-1)+",10)");
		 jQuery("#nextPage3").attr("href","javascript:getJSONData3("+(parseInt(cp)+1)+",10)");
		 jQuery("#lastPage3").attr("href","javascript:getJSONData3("+(parseInt(tp))+",10)");
	});
}


	getJSONData(1,10);
	getJSONData2(1,10); 
	getJSONData3(1,10); 
 function getJSONDatas(currentPage,pageSize) {
   	 document.getElementById("pageroot").style.display="none";
	 var   type="^\\+?\\d*$";
     var   re   =   new   RegExp(type);
    if(jQuery("#startMoney").val().match(re)==null)
     {
    	 document.getElementById("positiveId").style.display="";
     return;
     }
    if(jQuery("#endmoney").val().match(re)==null)
    {
    	 document.getElementById("positiveId").style.display="";
    return;
    }
    if(parseInt(jQuery("#endmoney").val()) < parseInt(jQuery("#startMoney").val())){
    	document.getElementById("compareId").style.display="";
        return;
    }
	 document.getElementById("lssuingsSerach").style.display="";
	 document.getElementById("lssuings").style.display="none";
	 var url;
	 if(document.getElementById("dayCheckId").checked){
			url = "tender/findlssuingBytender.action?currentPage="+currentPage+"&lussingType=6&pageSize="+pageSize+"&returnWayId="+ jQuery("#time0").val()+"&moneyUseId="+ jQuery("#yongtu").val()+"&periodTimeId="
		     + "0"+"&periodDayId="+ jQuery("#perioddayId").val()+"&award="+ jQuery("input[name='RadioGroup2']:checked").val()
		     +"&money1="+ jQuery("#startMoney").val()+"&money2="+ jQuery("#endmoney").val()+"&satate="+ jQuery("#bid").val()+"&temp=" +Math.random();
		}
	 else{
			url = "tender/findlssuingBytender.action?currentPage="+currentPage+"&lussingType=6&pageSize="+pageSize+"&returnWayId="+ jQuery("#time0").val()+"&moneyUseId="+ jQuery("#yongtu").val()+"&periodTimeId="
		     + jQuery("#time1").val()+"&periodDayId="+  "0"+"&award="+ jQuery("input[name='RadioGroup2']:checked").val()
		     +"&money1="+ jQuery("#startMoney").val()+"&money2="+ jQuery("#endmoney").val()+"&satate="+ jQuery("#bid").val();;
	 }
	
		
	     jQuery.getJSON(url,function(data){
			var cp = data.currentPage;
			var tp = data.totalPage;
			var  $phpernotetable =  jQuery("#listTables");
			 $phpernotetable.empty();
			str = '<tr class="header">  <td style="width:250px;" >项目名称</td><td>信用等级</td><td style="width:140px;" >当前利率</td> <td>投标奖励</td><td>借款总额</td><td>借款期限</td><td>进度</td><td>状态</td></tr>';
			var jsonRoot = data.jsonRoot;
			if(tp!=1&&tp!=0)
				{
         
		    	 document.getElementById("pageroots").style.display="";
				}

		     if(tp==0)
		    	 {

		
		    	 str = str+"";
		    	 }
		     else
		    	 {
			for(var i = 0; i < jsonRoot.length; i++) {
				str += '<tr  style="background:#f7f7f7"><td style="text-align: left; "><a href="' + decodeURI(jsonRoot[i].actions, "utf-8") + '" >'+ jsonRoot[i].picture+ jsonRoot[i].title + "</td><td>"
				+'<img src="..'+ decodeURI(jsonRoot[i].credit, "utf-8") +'"  /></td><td>'
			
					   + decodeURI(jsonRoot[i].rate, "utf-8") + "%</td><td>"
					   + decodeURI(jsonRoot[i].wate, "utf-8") + "</td><td>"
					   + decodeURI(jsonRoot[i].money, "utf-8") + "</td><td>"
					   + decodeURI(jsonRoot[i].time, "utf-8") + '</td><td style="text-align: left; padding-left:20px;" ><img src="../images/jd/jd_' + decodeURI(jsonRoot[i].number, "utf-8") +'.png" />'
					 + decodeURI(jsonRoot[i].bear, "utf-8") + '%</td><td> <a href="' + decodeURI(jsonRoot[i].action, "utf-8") + '" class="' + decodeURI(jsonRoot[i].stataclass , "utf-8") +'">'
					   + jsonRoot[i].state + "</td></tr>";
		  	}         
		     }
			 $phpernotetable.html(str);
			 jQuery("#currentPages").text("第"+cp+"页");
			 jQuery("#totalPages").text("共"+tp+"页");
			 jQuery("#firstPages").attr("href","javascript:getJSONDatas(1,10)");
			 jQuery("#prevPages").attr("href","javascript:getJSONDatas("+(parseInt(cp)-1)+",10)");
			 jQuery("#nextPages").attr("href","javascript:getJSONDatas("+(parseInt(cp)+1)+",10)");
			 jQuery("#lastPages").attr("href","javascript:getJSONDatas("+(parseInt(tp))+",10)");
		
		});
	  
	}
	

 	
function text(){
	document.getElementById("compareId").style.display="none";
	 document.getElementById("positiveId").style.display="none";
}


 function dayCheck(){
  	if(document.getElementById("dayCheckId").checked==true){

 		document.getElementById("periodDayNameDivId").style.display="";
 		document.getElementById("time").style.display="none";
 	}else{

 		document.getElementById("periodDayNameDivId").style.display="none";
 		document.getElementById("time").style.display="";
 	}}

</script>
</body>
</html>