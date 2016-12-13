<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<%
String path = request.getContextPath();if(path!=null && !path.equals("")){	if(path.contains("/")){		if(path.equals("/")){path = "";	}}else{	path += "/";}} 
String basePath = "";if(request.getServerPort()==80){basePath = request.getScheme()+"://"+request.getServerName()+path+"/";}else{basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";}    
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link rel="stylesheet" href="<%=path %>/wap/css/jquery.mobile.structure-1.4.5.min.css"/>
<link href="<%=path %>/wap/css/style.css" type="text/css" rel="stylesheet"/>
<script src="<%=path %>/wap/js/jquery.js"></script>
<script src="<%=path %>/wap/js/jquery.mobile-1.4.5.min.js"></script>
<script src="<%=path %>/wap/js/yxMobileSlider.js"></script>
<link href="<%=path %>/wap/css/member.css" type="text/css" rel="stylesheet"/>


<style>
	/*.focus{   margin:0 auto; position:relative; overflow:hidden;   }
	.focus .hd{ width:100%; height:5px;  position:absolute; z-index:1; bottom:0; text-align:center;  }
	.focus .hd ul{ overflow:hidden; display:-moz-box; display:-webkit-box; display:box; height:5px; background-color:rgba(51,51,51,0.5);   }
	.focus .hd ul li{ -moz-box-flex:1; -webkit-box-flex:1; box-flex:1; }
	.focus .hd ul .on{ background:#FF4000;  }
	.focus .bd{ position:relative; z-index:0; }
	.focus .bd li img{ width:500px;  height:150px; }
	.focus .bd li a{ -webkit-tap-highlight-color:rgba(0, 0, 0, 0); /* 取消链接高亮 */ }*/
    .slider{display:none}/*用于获取更加体验*/
    .focus span{width:10px;height:10px;margin-right:10px;border-radius:50%;background:#666;font-size:0}
    .focus span.current{background:#fff}
</style>

<div data-role="header" data-position="fixed">
<!--     <div class="logoBox"> -->
<!--         <a href="login.html" class="appDownload" data-ajax="false">登录</a> -->
<!--     </div>/logoBox -->
    
     <div data-role="navbar" data-grid="c">
        <ul class="navBox">
            <li><a href="<%=path %>/wap/index" class="ui-btn-active" data-ajax="false">首页</a></li>
            <li><a href="<%=path %>/wap/tenderList" data-ajax="false">项目</a></li>
            <li><a href="<%=path %>/wap/findUsers" data-ajax="false">我的</a></li>
            <li><a href="<%=path %>/wap/about.jsp" data-ajax="false">关于</a></li>
        </ul>
    </div>
 <div data-role="navbar" data-grid="c">
        <ul class="navBox">
            <li><a href="<%=path %>/wap/transferlistPage" data-ajax="false">债权转让</a></li>
          	<li><a href="<%=path %>/wap/lssuinglist?lssuingType=9" data-ajax="false">理财</a></li>
            <li><a href="<%=path %>/wap/lssuinglist?lssuingType=6" data-ajax="false">企业</a></li>
        </ul>
    </div>
    <!-- /navbar -->
</div>
