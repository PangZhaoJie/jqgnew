<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
  <%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();if(path!=null && !path.equals("")){	if(path.contains("/")){		if(path.equals("/")){path = "";	}}else{	path += "/";}} 
String basePath = "";if(request.getServerPort()==80){basePath = request.getScheme()+"://"+request.getServerName()+path+"/";}else{basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";}    
%>
 
<script type="text/javascript" src="../js/menu.js"></script>
<P class="guide mt10">当前位置：<a href="<%=path%>/index.action">网站首页</a>><a href="<%=path %>/user/login">我的账户</a></P>
  
   <div class="conbox">
     <div class="user_l">
     <h2><a href="<%=path %>/user/login" style="color:#FFFFFF">我的账户</a></h2> 
     <ul class="menu">
      
      <li class="level1" ><a href="#"><i><img src="../images/user_ico01.png" /></i>基本设置</a>
        <ul class="level2"  id="ID1" style="display:block;">
          <li><a href="<%=path %>/user/list">我的资料</a></li>
          <li><a href="<%=path %>/user/toJspPage?flag=1">头像与密码</a></li>
          <li><a href="<%=path %>/user/toJspPage?flag=2">站内信</a></li>
          <li><a href="<%=path %>/user/create">安全认证</a></li>
          <li><a href="<%=path %>/user/searchKefu">专属客服</a></li>
         </ul>
      </li>
      <li class="level1" ><a href="#" class="cur"><i><img src="../images/user_ico02.png" /></i>资金管理</a>
        <ul class="level2" id="ID2">
          <li><a href="<%=path %>/borrow/toAccount?userId=${session.uservip.userId}">账户详情</a></li>
          <li><a href="<%=path %>/borrow/toAccountHistory?userId=${session.uservip.userId}">资金明细</a></li>
          <li><a href="<%=path %>/borrow/toChongzhi?userId=${session.uservip.userId}">我要充值</a></li>
          <li><a href="<%=path %>/userbank/mention">我要提现</a></li>

          <li><a href="<%=path %>/userbank/toUserBank">银行账户</a></li>
          <li><a href="<%=path %>/user/integral">积分记录</a></li>
        </ul>
      </li>
      
     <li class="level1"><a href="#"><i><img src="../images/user_ico03.png" /></i>投资管理</a>
        <ul class="level2" id="ID3">
          <li><a href="<%=path %>/tender/tenderlist">投资总表</a></li>
          
           <li><a href="<%=path %>/user/user_transfer_total.jsp">债权转让</a></li>
        </ul> 
      </li>  
       
      <li class="level1"><a href="#"><i><img src="../images/user_ico04.png" /></i>借款管理</a>
        <ul class="level2" id="ID4">
          <li><a href="<%=path %>/borrow/toBorrowTotal?userId=${session.uservip.userId}">借款总表</a></li>
          <li><a href="<%=path %>/borrow/toLssuingList?userId=${session.uservip.userId}&queryFlag=3&gotoFlag=1">我要还款</a></li>
          <li><a href="<%=path %>/user/toJspPage?flag=3">额度申请</a></li>
        </ul>
      </li>  
      
     <li class="level1"><a href="#"><i><img src="../images/user_ico05.png" /></i>其他管理</a>
        <ul class="level2" id="ID5">
<!--           <li><a href="<%=path %>/user/toJspPage?flag=4">我的留言</a></li> -->
          <li><a href="<%=path %>/user/toJspPage?flag=5">奖金记录</a></li>
<!--           <li><a href="<%=path %>/user/findUserInfo">VIP申请</a></li> -->
        </ul>
      </li>  
      
    </ul>
