<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
String path = request.getContextPath();if(path!=null && !path.equals("")){	if(path.contains("/")){		if(path.equals("/")){path = "";	}}else{	path += "/";}}
String basePath = "";if(request.getServerPort()==80){basePath = request.getScheme()+"://"+request.getServerName()+path+"/";}else{basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";}  
%>
<link rel="icon" href="<%=path %>/image/favicon/favicon.ico" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta name="Keywords" content="招米贷,个人应急贷款,小额应急贷款,骨科贷款" />
<meta name="description" content="招米贷是国米资产旗下的投资理财产品，专业提供个人应急贷款、小额应急贷款服务，是上海交通大学互联网金融副会长单位。" />


<title>招米贷,个人应急贷款,小额应急贷款</title>
<link href="<%=basePath %>/css/public.css" type="text/css" rel="stylesheet"/>
<link href="<%=basePath %>/css/bid.css" type="text/css" rel="stylesheet" />
<!--导航下拉菜单 js-->
<script type="text/javascript" src="<%=basePath %>/js/jquery-1.8.2.min.js"></script>
</head>
<script type="text/javascript">
   function toBorrow(mark){
	   var userType=document.getElementById("userTypeId").value;
	   var uservip=document.getElementById("uservipId").value;
	    if(uservip==""){
	    	var form = document.getElementById("topId");
		      form.action="<%=path %>/login/login.jsp";
	 	      form.submit();
	    }else if(userType!=1){
	    	alert("对不起，您的权限不足，请与客服联系。");
	    }  else{
	    	var form = document.getElementById("topId");
		      form.action="borrow/toLssuing?mark="+mark;
	 	      form.submit();
	 	document.activeElement.style.background = "gray";
	    document.activeElement.value="正在提交...";
	    document.activeElement.disabled=false;	
	    }	    
		   
   }
     
</script>
<body style="overflow-x:hidden;word-break:break-all;">

<!--头部开始-->
 <!--头部开始-->
<div class="header">
    <jsp:include page="../header.jsp" />
</div>
<!--头部结束-->
<form action="" method="post" id="topId">
<input value="${session.uservip.userType }" id="userTypeId" type="hidden"/>
<input value="${session.uservip }" id="uservipId" type="hidden"/>
<div class="maincontent">
  <div class="conbox pt20">
    <P class="guide">当前位置：<a href="#">网站首页</a> > <a href="#">我要借款</a></P>
    <div class="bid_title">借款标介绍</div>
    <div class="bid_kind fn_cle">
    
       <div class="bid01 ">
          <div class="bid01_img"><a href="#"><img src="<%=basePath %>/images/xin_bid.jpg" /><span>信用标</span></a></div>
          <div class="bid01_js">
              <P>信用借款标显示"信"字标记，是一种免抵押、免担保、纯信用，的小额个人信用贷款标，主要面向固定收入群体开放。如借款人到期还款出现困难，逾期约定时间由网站运营方垫付本金息还款，债权转让为网站运营方所有。 
另外逾期每天按约定收取罚息，本金利息及罚息全部为网站运营方收取。</P>
              <ul class="bid_info">
                  <li>贷款额度：<em>500000元</em></li>
                  <li>贷款利率：<em>5.6%-22%</em></li>
                  <li>贷款期限：<em>1个月-6个月</em></li>
                  <li>还款方式：<em>等额本息|按季还款|按月付息</em></li> 
                  <li>投标时间：<em>1-9天</em></li>
                  <li>审核时间：<em>1-3天</em></li>
             </ul>
          
          </div>
           <div class="pub_btn"><input type="submit" class="pub_sub" onclick="toBorrow('1')" value="立即发布"/></div>
       </div>
       
       <div class="bid01 ">
          <div class="bid01_img"><a href="#"><img src="<%=basePath %>/images/dan_bid.jpg" /><span>担保标</span></a></div>
          <div class="bid01_js">
              <P>机构担保借款标显示标记“担”，是有担保机构进行担保的借款，担保人和借款人之间协商并签订抵押担保手续，确保风险控制在合理的范围内。如借款人到期还款出现逾期，由担保机构垫付本息还款，债权转让为担保人所有。
              借款者可以提供担保公司或者由我们网站合作担保公司进行担保必须在我们网站工作人员在场签定好担保合同。由于有第三方担保公司进行担保相对来说此类标安全系数高。</P>
              <ul class="bid_info">
                  <li>贷款额度：<em>500000元</em></li>
                  <li>贷款利率：<em>5.6%-22%</em></li>
                  <li>贷款期限：<em>1个月-6个月</em></li>
                  <li>还款方式：<em>等额本息|按季还款|按月付息</em></li> 
                  <li>投标时间：<em>1-9天</em></li>
                  <li>审核时间：<em>1-3天</em></li>
             </ul>
          
          </div>
           <div class="pub_btn"><input type="button" class="pub_sub" onclick="toBorrow('2')"  value="立即发布" /></div>
       </div>
       
       <div class="bid01 ">
          <div class="bid01_img"><a href="#"><img src="<%=basePath %>/images/jing_bid.jpg" /><span>净值标</span></a></div>
          <div class="bid01_js">
              <P>净值借款标显示标记“净”，如果客户净资产大于借款金额，网站运营方允许发布净值借款标用于临时周转。他是一种相对安全系数很高的借款标，因此利率方面可能比较低，适合资金黄牛，用户可以借助此标放大自己的投资标。 净值借款标逾期后约定时间由网站先行垫付本息还款。 
 </P>
              <ul class="bid_info">
                  <li>贷款额度：<em>500000元</em></li>
                  <li>贷款利率：<em>5.6%-22%</em></li>
                  <li>贷款期限：<em>1个月-6个月</em></li>
                  <li>还款方式：<em>等额本息|按季还款|按月付息</em></li> 
                  <li>投标时间：<em>1-9天</em></li>
                  <li>审核时间：<em>1-3天</em></li>
             </ul>
          
          </div>
           <div class="pub_btn"><input type="button" class="pub_sub" onclick="toBorrow('4')" value="立即发布"/></div>
       </div>
       

       
       <div class="bid01 ">
          <div class="bid01_img"><a href="#"><img src="<%=basePath %>/images/di_bid.jpg" /><span>抵押标</span></a></div>
          <div class="bid01_js">
              <P>抵押借款标显示标记“抵”，是网站运营方经过线下严格核查借款人资产负债，抵押手续（不仅限）、有关政府以及商业银行推荐、优质资产和股权质押，确保风险控制在合理的范围内。抵押标借款者对象一般为地区优质中小微企业，是网站运营方重点发展对象。借款人到期还款出现困难，借款到期日当天由网站运营方垫付本金和利息还款，债权为网站运营方所有。
              抵押标逾期后，每天按约定收取罚息，本金利息及罚息全部为网站运营方收取.。</P>
              <ul class="bid_info">
                  <li>贷款额度：<em>500000元</em></li>
                  <li>贷款利率：<em>5.6%-22%</em></li>
                  <li>贷款期限：<em>1个月-6个月</em></li>
                  <li>还款方式：<em>等额本息|按季还款|按月付息</em></li> 
                  <li>投标时间：<em>1-9天</em></li>
                  <li>审核时间：<em>1-3天</em></li>
             </ul>
          
          </div>
           <div class="pub_btn"><input type="button" class="pub_sub" onclick="toBorrow('5')" value="立即发布" /></div>
       </div>
       
        <div class="bid01 ">
          <div class="bid01_img"><a href="#"><img src="<%=basePath %>/images/miao_bid.jpg" /><span>秒标</span></a></div>
          <div class="bid01_js">
              <P>秒还标显示标记“秒”，借款者发布秒还标，利息和管理费将被冻结，投标满后，系统自动审核通过，发标人瞬间送出利息和管理费，投资者则收回本金和利息。
              秒还标是一种娱乐庆祝送钱的标，可以很快提升发标者的积分，把快乐送给大家。</P>
              <ul class="bid_info">
                  <li>贷款额度：<em>500000元</em></li>
                  <li>贷款利率：<em>5.6%-22%</em></li>
                  <li>贷款期限：<em>1个月-6个月</em></li>
                  <li>还款方式：<em>等额本息|按季还款|按月付息</em></li> 
                  <li>投标时间：<em>1-9天</em></li>
                  <li>审核时间：<em>1-3天</em></li>
             </ul>
          
          </div>
           <div class="pub_btn"><input type="button" class="pub_sub" onclick="toBorrow('3')" value="立即发布" " /></div>
       
       </div>
      <div class="clear"></div>
    </div>
     
  </div>
 </div>
</form>
<!--footer 开始-->
 <div class="clear"></div>
 <div class="footer">
  <jsp:include page="../footer.jsp" />
</div>
<!--footer 结束-->

</body>
</html>