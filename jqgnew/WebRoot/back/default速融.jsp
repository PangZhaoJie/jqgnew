<%@page import="net.sf.json.JSONObject"%>
<%@page import="com.jqg.service.impl.IPServiceImpl"%>
<%@page import="com.jqg.service.IPService"%>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
  <%@ taglib uri="/struts-tags" prefix="s"%>
  <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();if(path!=null && !path.equals("")){	if(path.contains("/")){		if(path.equals("/")){path = "";	}}else{	path += "/";}} 
String basePath = "";if(request.getServerPort()==80){basePath = request.getScheme()+"://"+request.getServerName()+path+"/";}else{basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";}    
IPService ipService = new IPServiceImpl();
String ip = ipService.getIpAddr(request);
JSONObject jsonobj = (JSONObject)request.getAttribute("tenderlist");
JSONObject jsonobj1 = (JSONObject)request.getAttribute("zjtjlist");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="<%=path %>/back/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path %>/back/js/jquery.js"></script>
<script type="text/javascript" src="<%=path %>/back/js/main.js"></script>
<script type="text/javascript" src="http://cdn.hcharts.cn/jquery/jquery-1.8.2.min.js"></script>
<script src="<%=path %>/back/js/highcharts/js/highcharts.js"></script>
<script src="<%=path %>/back/js/highcharts/js/highcharts-more.js"></script>
<script src="<%=path %>/back/js/highcharts/js/modules/exporting.js"></script>

<script type="text/javascript">
	jQuery(function(){
		var ch = new  Highcharts.Chart({
			chart: {
				renderTo: 'mrtztj',
		        type: 'line',
		        backgroundColor:'',//背景颜色
		        inverted: false
		    },
		    credits: {
		    	enabled: false
		    },
			title:{ 
				text: '每日投资统计',
				x: -20 //center 
			}, 
			subtitle: { 
				text: '', 
				x: -20 
			}, 
			xAxis: { 
				categories: <%=jsonobj.get("xlist") %>
			}, 
			yAxis: { 
				title: { 
					text: '金额 (元)' 
				}, 
				plotLines: [{ 
					value: 0, width: 1, color: '#808080' 
				}] 
			}, 
			tooltip: { 
				valueSuffix: '元' 
			}, 
			legend: { 
				layout: 'vertical', 
				verticalAlign: 'middle', 
				borderWidth: 0
			}, 
			series: [{ 
				name: '每日投资统计', 
				data: <%=jsonobj.get("tendlist") %>
			}, { 
				name: '日发标额度统计', 
				data:<%=jsonobj.get("borrowlist") %>
			}] 
		});
		
		var ch1 = new  Highcharts.Chart({
			chart: {
				renderTo: 'zjtj',
		        type: 'column',
		        backgroundColor:'',//背景颜色
		        inverted: false
		    },
		    credits: {
		    	enabled: false
		    },
			title:{ 
				text: '资金统计',
				x: -20 //center 
			}, 
			subtitle: { 
				text: '', 
				x: -20 
			}, 
			xAxis: { 
				categories: ['线上充值','线下充值','提现','借出总额','已还总额']
			}, 
			yAxis: { 
				title: { 
					text: '金额 (元)' 
				}, 
				plotLines: [{ 
					value: 0, width: 1, color: '#808080' 
				}] 
			}, 
			tooltip: { 
				valueSuffix: '元' 
			}, 
			legend: { 
				layout: 'vertical', 
				verticalAlign: 'middle', 
				borderWidth: 0
			}, 
			series: [{ 
				name: '统计', 
				data: <%=jsonobj1.get("zjtjlist") %>
			}] 
		});
	});
</script>

</head>


<body>

	
    
    
    <div class="mainbox">
    <input type="hidden" id="path" value="<%=path %>"/>
    
    <div class="topinfo">
       <div class="hy"><img src="<%=path%>/back/images/i07.png" /></div>
        <div class="welinfo"> <b>${manager.managerName}，欢迎使用${websiteinfo.webName}管理系统</b></div>
        <div class="welinfo">
       
          <i>当前登录ip：<%=ip %></i> 
        </div>
    
    
    </div>
    
    
    <div class="botinfo">
    <div class="mainleft">
    <div class="leftinfo" style="height: 430px;">
    <div class="listtitle"><i><img src="<%=path%>/back/images/i099.png"/></i>每日投资统计</div>
        
    <div class="maintj" id="mrtztj">
    </div>
    
    </div>
    
    
    <div class="leftinfo" style="margin-top: 12px;">
    <div class="listtitle"><i><img src="<%=path%>/back/images/ico02.png"/></i>待审核信息</div>
        
    <div class="maintj">
    	<dl class="infolist">
    <dd>等待初审的标<a href="#"  onclick="chushen()">[${sessionScope.chushen}]</a>个</dd>
    <dd>额度申请等待审核的<a href="#"  onclick="edu()"> [${sessionScope.edushenqing}]</a>个</dd>
        <dd>等待复审的标<a href="#"  onclick="fushen()">[${sessionScope.fushen}]</a>个</dd>
    <dd>等待视频认证的<a href="#"  onclick="shipin()">[${sessionScope.shipinrenzheng}]</a>个</dd>
    
    <dd>等待审核提现<a href="#"  onclick="tixian()">[${sessionScope.tixianshenqing}]</a>个</dd>
<dd>等待现场认证的<a href="#" onclick="xianchang()">[${sessionScope.xianchangrenzheng}]</a>个</dd>
    <dd>等待充值审核的<a href="#"  onclick="chongzhi()">[${sessionScope.chongzhishenhe}]</a>个</dd>
    <dd>等待实名认证的<a href="#"  onclick="shiming()">[${sessionScope.shimingrenzheng}]</a>个</dd>

    </dl>  
    </div>
    
    </div>
    
   <%--  <div class="leftinfo">
    <div class="listtitle"><i><img src="<%=path%>/back/images/i099.png"/></i>平台公告</div>
        
    <div class="maintj">  
        <ul class="newlist">
        <c:forEach var="gonggao" items="${gonggaos}">
           <li><a href="#">${gonggao.title}</a><b><fmt:formatDate value="${gonggao.publishTime}" pattern="yyyy-MM-dd" /></b></li>
         </c:forEach>
   
    </ul> 
    </div>
    
    </div> --%>
    <!--leftinfo end-->
    
   
    
    <%-- <div class="leftinfos">
    
   
    <div class="infoleft" style=" width:68%;">
    
    <div class="listtitle"><i><img src="<%=path%>/back/images/d02.png"/></i>新标预告</div>    
    <ul class="newlist">
      <c:forEach var="yugao" items="${yugaos}">
           <li><a href="#">${yugao.title}</a><b><fmt:formatDate value="${yugao.publishTime}" pattern="yyyy-MM-dd" /></b></li>
         </c:forEach>
    </ul>   
    
    </div> --%>
    
    
    <%-- <div class="inforight">
    <div class="listtitle"><i><img src="<%=path%>/back/images/icon061.png"/></i>常用工具</div>
      
     <ul class="iconlist">
    
    <li><a href="<%=path %>/overall/findWebsite" target="rightFrame"><img src="<%=path%>/back/images/ico01.png" onclick="news()"/></a><p><a href="<%=path %>/overall/findWebsite" target="rightFrame">网站设置</a></p></li>
    <li><img src="<%=path%>/back/images/ico02.png"onclick="news()" /><p><a href="#" onclick="news()">发布文章</a></p></li>
    <li><img src="<%=path%>/back/images/ico03.png"onclick="parent.frames[1].location.href='<%=path %>/back/7_left.jsp';  parent.frames[2].location.href='<%=path %>/money/toUserMoney'; " /><p><a href="#" onclick="parent.frames[1].location.href='<%=path %>/back/7_left.jsp';  parent.frames[2].location.href='<%=path %>/money/toUserMoney'; ">资金统计</a></p></li>
   
   
            
    </ul>
    
    </div> --%>
    
    
    </div>
    </div>
    <!--mainleft end-->
    
    
    <div class="mainright">
    <div class="dflist" style="height: 430px;">
    <div class="listtitle"><i><img src="<%=path%>/back/images/i099.png"/></i>资金统计</div>
        
    <div class="maintj" id="zjtj">
    </div>
    
    </div>
    
    
    
    
    <div class="dflist1">
    <div class="listtitle"><i><img src="<%=path%>/back/images/ico03.png"/></i>系统信息</div>    
    <ul class="newlist1">

      <li>速融程序版本：java版</li>
      <li>操作系统：Windows NT</li>
      <li>服务器软件： Apache Tomcat/7.0.55</li>
      <li>MySQL 版本：5.5.19</li>
      <li>服务器协议：HTTP/1.1</li>
      <li>官方网站：www.surongkj.com</li>
    </ul>        
    </div>
    
    

    
    
    </div>
    <!--mainright end-->
    
    </div>
    </div>



</body>
<script type="text/javascript">
	setWidth();
	$(window).resize(function(){
		setWidth();	
	});
	function setWidth(){
		var width = ($('.leftinfos').width()-12)/2;
		$('.infoleft,.inforight').width(width);
	}
</script>
</html>
