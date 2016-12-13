<%@page import="com.jqg.pojo.Website"%>
<%@page import="com.jqg.service.WebsiteService"%>
<%@page import="com.jqg.service.impl.WebsiteServiceImpl"%>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
  <%@ taglib uri="/struts-tags" prefix="s"%>
  <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();if(path!=null && !path.equals("")){	if(path.contains("/")){		if(path.equals("/")){path = "";	}}else{	path += "/";}} 
String basePath = "";if(request.getServerPort()==80){basePath = request.getScheme()+"://"+request.getServerName()+path+"/";}else{basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";}  
WebsiteService websiteService = new WebsiteServiceImpl();
Website website = websiteService.findWebsiteBywebsiteId(1);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="icon" href="<%=path %>/image/favicon/favicon.ico" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>首页-如何借款新手指引</title>
<link href="../css/public.css" type="text/css" rel="stylesheet"/>
<link href="../css/guide.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" href="../css/lightbox.css" type="text/css" media="screen" />
<!--导航下拉菜单 js-->
<script type="text/javascript" src="../js/jquery-1.8.2.min.js"></script>

<!--Lightbox JS-->
<script type="text/javascript" src="../js/prototype.js"></script>
<script type="text/javascript" src="../js/scriptaculous.js?load=effects,builder"></script>
<script type="text/javascript" src="../js/lightbox.js"></script>

<style>
.con{width:600px;height:430px;position:relative;background: url(../images/yuanli_img.jpg) no-repeat;margin:20px auto;}
.con div{position:absolute;}
.con div.l1{width:164px;height:114px;top:10px;left:220px;background: url(../images/yl_img_01.jpg) no-repeat;}
.con div.l2{width:81px;height:107px;top:170px;left:6px;background:url(../images/yl_img_02.jpg) no-repeat;}
.con div.l3{width:82px;height:107px;top:170px;left:516px;background:url(../images/yl_img_03.jpg) no-repeat;}
.con div.l4{width:160px;height:120px;top:300px;left:220px;background:url(../images/yl_img_04.jpg) no-repeat;}
</style>

</head>
<body> 
<!--头部开始-->
<div class="header">
      <jsp:include page="../header.jsp" />
</div>
<!--头部结束-->
 <div class="conbox pt10">
 <input id="path" type="hidden" value="<%=basePath%>">
    <P class="guide">当前位置：<a href="#">网站首页</a> > <a href="#">新手指引</a> > 我要借款</P>
    <div class="content_ct pt10 mr_b10 fn_cle">
        <div class="conbox_l">
           <dl>
              <dt><i><img src="../images/guide_ico.png" /></i>新手指引</dt>
              <dd><a href="<%=path+"/guide/invest_guide.jsp"%>">我要投资</a></dd>
               <dd><a href="<%=path+"/guide/borrow_guide.jsp"%>">我要借款</a></dd>
              
           </dl>
        </div>
        
        <div class="conbox_r">
         <h3><span>如何借款</span></h3>
          <div class="conbox_r_nr">
             <div class="invest_step">
                 <div class="step_title1"><span>借款流程</span></div>
                 <div class="step_nr">
                    <ul>
                        <li><a href="../images/i_step01.jpg" rel="lightbox[roadtrip]"><img src="../images/b_step01.png" /><span>1、会员注册</span></a></li>
                        <li><a href="../images/i_step02.jpg" rel="lightbox[roadtrip]"><img src="../images/b_step02.png" /><span>2、填写个人资料</span></a></li>
                        <li><a href="../images/i_step03.jpg" rel="lightbox[roadtrip]"><img src="../images/b_step03.png" /><span>3、填写认证信息</span></a></li>
                        <li><a href="../images/i_step04.jpg" rel="lightbox[roadtrip]"><img src="../images/b_step04.png" /><span>4、上传认证资料</span></a></li>
                        <li><a href="../images/i_step05.jpg" rel="lightbox[roadtrip]"><img src="../images/b_step05.png" /><span>5、发布借款信息</span></a></li>
                    </ul>
                 </div>
                 
             </div>
             
             <div class="invest_step">
               <div class="yuanli_title1"><span>平台原理</span></div>
               <div class="yuanli_nr">
                   <div class="con">
                     <a href =  "<%=path %>/borrow/bid.jsp" ><div class="l1"></div></a>
                     <a href = "javascript:void(0)" onclick = "document.getElementById('light2').style.display='block';document.getElementById('fade').style.display='block'"  ><div class="l2"></div></a>
                     <a href = "javascript:void(0)" onclick = "document.getElementById('light3').style.display='block';document.getElementById('fade').style.display='block'"  ><div class="l3"></div></a>
                     <a href = "<%=path %>/tender/totenderList"  ><div class="l4"></div></a>
                  </div>
           
                  
                 <P>理财平台作为国内先进网络借贷平台，为有资金需求和理财需求的用户提供了一个快速、高效、公平、透明的网络借贷信息服务平台。用户可以在平台上发布借款请求；也可以把自己的闲余资金通过平台出借给信用良好有资金需求的用户，在获得良好的资金回报率的同时帮助了他人。专业政府出资的担保公司和小贷公司对于投资者本金全程担保，而且还引入了借款人保险机制，大大降低投资者的借出风险。</P>
           
           
           <div id="light2" class="white_content">
            <div class="light_title"><a href = "javascript:void(0)" onclick = "document.getElementById('light2').style.display='none';document.getElementById('fade').style.display='none'" class="close"><img src="../images/close.png" /></a></div>
            <div class="letter_info">
             <span> 借款人已还总额<%=website.getInvestment() %>，期待您的加入！</span>
            </div>
            
           </div>
           
           <div id="light3" class="white_content">
            <div class="light_title"><a href = "javascript:void(0)" onclick = "document.getElementById('light3').style.display='none';document.getElementById('fade').style.display='none'" class="close"><img src="../images/close.png" /></a></div>
            <div class="letter_info">
             <span>用户投资总额<%=website.getLoan() %></span>
            </div>
            
           </div>
           
           
            
           </div>
           <div id="fade" class="black_overlay"></div> 
               </div>
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
<script type="text/javascript" src="../js/service.js">	</script> --%>
<!--右边漂浮 结束-->
</body>
</html>