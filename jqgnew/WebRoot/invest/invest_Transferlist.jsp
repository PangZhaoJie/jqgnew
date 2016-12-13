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
<title>我要投资-债权转让项目</title>
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
    <P class="guide">当前位置：<a href="<%=path%>/index.action">网站首页</a> > <a href="<%=path%>/tender/totenderList">债权转让</a></P>
    <div class="choose fn_cle">
       <h3>筛选条件</h3>
       <div class="choose_nr">
          <div class="choose1"><h4>还款方式</h4> 
            <div id="uboxstyle">
                 <select name="returnway" id="time0">
	              	 <option value="0"  selected="selected">-请选择还款方式-</option>
		            <c:forEach var="returnway" items="${returnways}">
		             <option  value="${returnway.returnWayId}">${returnway.returnWayName}</option>
					</c:forEach> 	  
	              </select>
                   
             </div>
           </div>
          <div class="choose1"><h4>借款目的</h4>  
                     
          <div id="uboxstyle">
                  <select name="yongtu" id="yongtu">
	              	 <option value="0"  selected="selected">-请选择借款用途-</option>
		            <c:forEach var="moneyuse" items="${moneyuses}">
		             <option  value="${moneyuse.moneyUseId}">${moneyuse.moneyUseName}</option>
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
	              	 <option value="0"  selected="selected">-请选择-</option>
	              	  <option value="1" >进行中</option>
		              <option value="2" >复审中</option>
		             <option value="3" >还款中</option>
		             <option value="4" >已完成</option>
		            </select>
                    
                  </div>
                 
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
            <li class="current" onclick="secBoard('hotnews_caption','list',1);">债权转让列表</li>
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
  
    <td  style="width:250px;">项目名称</td><td>当前利率</td><td>转让价格</td><td>待收本息</td><td>状态</td>
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
  
    <td  style="width:250px;">项目名称</td><td>当前利率</td><td>转让价格</td><td>待收本息</td><td>状态</td>
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

<script type="text/javascript" >

function getJSONData(currentPage,pageSize) {
	 document.getElementById("pageroots").style.display="none";
	var url = "<%=path %>/transfer/transferlistPage.action?currentPage="+currentPage+"&pageSize="+pageSize+"&temp=" +Math.random();
	 jQuery.getJSON(url,function(data){
		var cp = data.currentPage;
		var tp = data.totalPage;
		var  $phpernotetable =  jQuery("#listTable");
		 $phpernotetable.empty();
		str = '<tr class="header">  <td  >项目名称</td><td style="width:140px;" >当前利率</td> <td>转让价格</td><td>待收本息</td><td>状态</td></tr>';
		var jsonRoot = data.jsonRoot;
		if(tp!=1&&tp!=0)
		{
	
    	 document.getElementById("pageroot").style.display="";
		}
		for(var i = 0; i < jsonRoot.length; i++) {
			if(decodeURI(jsonRoot[i].state, "utf-8")=="立即投资"){
			str += '<tr><td style="text-align: left; "><a href="' + decodeURI(jsonRoot[i].actions, "utf-8") + '" >'+ jsonRoot[i].title + "</a></td><td>"
				   + decodeURI(jsonRoot[i].rate, "utf-8") + "%</td><td>"
				   + decodeURI(jsonRoot[i].transferMoney, "utf-8") + "</td><td>"
				   + decodeURI(jsonRoot[i].benxi, "utf-8") + "</td><td>"
				   + '<a href="' +decodeURI(jsonRoot[i].actions, "utf-8") + '">'+decodeURI(jsonRoot[i].state, "utf-8") + "</a></td></tr>";
			}else{
				str += '<tr><td style="text-align: left; "><a href="' + decodeURI(jsonRoot[i].actions, "utf-8") + '" >'+ jsonRoot[i].title + "</a></td><td>"
				   + decodeURI(jsonRoot[i].rate, "utf-8") + "%</td><td>"
				   + decodeURI(jsonRoot[i].transferMoney, "utf-8") + "</td><td>"
				   + decodeURI(jsonRoot[i].benxi, "utf-8") + "</td><td>"
				   + decodeURI(jsonRoot[i].state, "utf-8") + "</td></tr>";
			}
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


	getJSONData(1,10);
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
			url = "<%=path %>/transfer/transferlistPage.action?currentPage="+currentPage+"&pageSize="+pageSize+"&returnWay="+ jQuery("#time0").val()+"&yongtu="+ jQuery("#yongtu").val()+"&periodday="
		     +jQuery("#perioddayId").val()+"&bid="+ jQuery("#bid").val()+"&startMoney="+ jQuery("#startMoney").val()+"&endmoney="+ jQuery("#endmoney").val()+"&temp=" +Math.random();
		}
	 else{
			url = "<%=path %>/transfer/transferlistPage.action?currentPage="+currentPage+"&pageSize="+pageSize+"&returnWay="+ jQuery("#time0").val()+"&yongtu="+ jQuery("#yongtu").val()+"&time="
		     + jQuery("#time1").val()+"&bid="+ jQuery("#bid").val()+"&startMoney="+ jQuery("#startMoney").val()+"&endmoney="+ jQuery("#endmoney").val()+"&temp=" +Math.random();
	 }
	
		
	     jQuery.getJSON(url,function(data){
			var cp = data.currentPage;
			var tp = data.totalPage;
			var  $phpernotetable =  jQuery("#listTables");
			 $phpernotetable.empty();
			str = '<tr class="header">  <td  >项目名称</td><td style="width:140px;" >当前利率</td> <td>转让价格</td><td>待收本息</td><td>状态</td></tr>';
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
			if(decodeURI(jsonRoot[i].state, "utf-8")=="立即投资"){
			str += '<tr><td style="text-align: left; "><a href="' + decodeURI(jsonRoot[i].actions, "utf-8") + '" >'+ jsonRoot[i].title + "</a></td><td>"
				   + decodeURI(jsonRoot[i].rate, "utf-8") + "%</td><td>"
				   + decodeURI(jsonRoot[i].transferMoney, "utf-8") + "</td><td>"
				   + decodeURI(jsonRoot[i].benxi, "utf-8") + "</td><td>"
				   + '<a href="' +decodeURI(jsonRoot[i].actions, "utf-8") + '">'+decodeURI(jsonRoot[i].state, "utf-8") + "</a></td></tr>";
			}else{
				str += '<tr><td style="text-align: left; "><a href="' + decodeURI(jsonRoot[i].actions, "utf-8") + '" >'+ jsonRoot[i].title + "</a></td><td>"
				   + decodeURI(jsonRoot[i].rate, "utf-8") + "%</td><td>"
				   + decodeURI(jsonRoot[i].transferMoney, "utf-8") + "</td><td>"
				   + decodeURI(jsonRoot[i].benxi, "utf-8") + "</td><td>"
				   + decodeURI(jsonRoot[i].state, "utf-8") + "</td></tr>";
			}
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