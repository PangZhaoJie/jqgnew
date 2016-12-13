<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();if(path!=null && !path.equals("")){	if(path.contains("/")){		if(path.equals("/")){path = "";	}}else{	path += "/";}}
String basePath = "";if(request.getServerPort()==80){basePath = request.getScheme()+"://"+request.getServerName()+path+"/";}else{basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";}  
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="icon" href="<%=path %>/image/favicon/favicon.ico" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>论坛中心-首页</title>
<link href="<%=basePath %>/css/public.css" type="text/css" rel="stylesheet"/>
<link href="<%=basePath %>/css/bbs/bbs_gonggao.css" type="text/css" rel="stylesheet" />
<!--导航下拉菜单 js-->
<script type="text/javascript" src="<%=basePath %>/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript">
		var  $bbs=jQuery.noConflict();
		function getJSONData(currentPage,pageSize,askTypeFlag) {
			var url = "<%=path %>/bbs/toBbsList.action?currentPage="+currentPage+"&pageSize="+pageSize+"&askTypeFlag="+askTypeFlag;
			var  $s=jQuery.noConflict();
 			$s.getJSON(url,function(data){
				var cp = data.currentPage;
				var tp = data.totalPage;
				var fg=data.askTypeFlag;
				var $ptable = $bbs("#listTable");
				$ptable.empty();
 				str = "";
				var jsonRoot = data.jsonRoot;
				for(var i = 0; i < jsonRoot.length; i++) {
					var a=decodeURI(jsonRoot[i].askDate, "utf-8");
					var b=a.substring(0,10) ;
  			        str +="<div class='text'><div class='textLeft'><img src='../images/tubiao_01.png'/>"+
                	"<a href='<%=path %>/bbs/toBbsView?askId="+decodeURI(jsonRoot[i].askId, "utf-8")+"'>"+
                	 decodeURI(jsonRoot[i].askTitle, "utf-8")+"</a></div><div class='textRight'>"+              
                    "<div class='textRightL'>0/0</div><div class='textRightL'>"+
                    "<div class='zuozhe'><a href=''>"+ decodeURI(jsonRoot[i].userName, "utf-8")+ "</a></div>"+
                    "<div class='time'>"+b
                    +"</div>"+
                    "</div></div></div>";
                    }
				$ptable.html(str);
				$bbs("#currentPage").text("第"+cp+"页");
				$bbs("#totalPage").text("共"+tp+"页");
				$bbs("#firstPage").attr("href","javascript:getJSONData(1,3,askTypeFlag)");
				$bbs("#prevPage").attr("href","javascript:getJSONData("+(parseInt(cp)-1)+",3,"+askTypeFlag+")");
				$bbs("#nextPage").attr("href","javascript:getJSONData("+(parseInt(cp)+1)+",3,"+askTypeFlag+")");
				$bbs("#lastPage").attr("href","javascript:getJSONData("+(parseInt(tp))+",3,"+askTypeFlag+")");
			});
		}
		$bbs(function() { 
			var url = document.location.href;
			var askTypeFlag=0;
			  if (url.indexOf("=")>0)
			    {
				  askTypeFlag = url.substring(url.indexOf("=")+1,url.length);
			    }
  			getJSONData(1,3,askTypeFlag);  
		});

		function submitBbs(){
			
			if($bbs("#askTitleId").val==null || $bbs("#askContentId").val==null){
				alert("信息不完整!");
			}else{
				var form=document.getElementById("bbsFormId");
				form.action="bbs/toBbsAdd";
				form.submit();
			}
		}
</script>
</head>
<body> 
<!--头部开始-->
<!--头部开始-->
<div class="header">
    <jsp:include page="../header.jsp" />
</div>
<!--头部结束-->


<div class="conbox pt10">
  <P class="guide">当前位置：<a href="#">网站首页</a> > <a href="<%=path %>/bbs/toBbsIndex">论坛中心</a> > 公告发布区</P>
  <div class="bbs">
  	<div class="bigBox">
    	<div class="menuBox">
        	<div class="menu">
            	<div><a href="">最新</a></div>
              <div><a href="">热门</a></div>
              <div><a href="">热帖</a></div>
              <div><a href="">精华</a></div>
              <div><a href="">其它</a></div>
            </div>
            <div class="menuText">回复/查看</div>
            <div class="menuText">作者</div>
        </div>
        <div class="neiRong" id="listTable">
        	 
        	 
        </div>
    </div>
    <div id="pageroot" style="float:right">
 			  	<span id="currentPage">第页</span>&nbsp; &nbsp; 
			  	<span id="totalPage">共页</span>&nbsp; &nbsp; 
			  	<span><a id="firstPage" href="">首页</a></span>&nbsp; &nbsp; 
			  	<span><a id="prevPage" href="">上一页</a></span>&nbsp; &nbsp; 
			  	<span><a id="nextPage" href="">下一页</a></span>&nbsp; &nbsp; 
			  	<span><a id="lastPage" href="">最后一页</a></span>&nbsp; &nbsp; 
	</div>
  	<div class="fatie">
   	  <div class="top"><div>快速发帖</div></div>
   	  <form action="" id="bbsFormId" method="post">
        <div class="topT">主题：<input class="biaoT" name="investstrategy.askTitle" id="askTitleId"/>可输入 100 个字符</div>
  	    <div class="topT">分类： 
            <select name="askTypeId" id="moneyUseId">
             	<c:forEach var="askTypeList" items="${askTypeList}">
            <option  value="${askTypeList.askTypeId}" >${askTypeList.askTypeName}</option>
		        </c:forEach> 
            </select>  	              
         </div>
  	    
  	    <div class="textBox">
        	<div class="textTop">
           <span class="topT">

           </span></div>
           
           <input name="userId" value="${session.uservip.userId}" style="display: none"/>
			<div class="textB">
            	<input type="text" class="text" name="investstrategy.askContent" id="askContentId"/>
            </div>
            <div class="tishi">先登录才能发帖<a href="">注册</a>/<a href="">登录</a></div>
        </div>
        <div class="fabiao"><img src="<%=basePath %>/images/fabiao_03.png" style="cursor: pointer;" onclick="submitBbs()"/>
        </div></form>
    </div>
  </div>
<span class="newsImge"></span>
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
</body>
</html>