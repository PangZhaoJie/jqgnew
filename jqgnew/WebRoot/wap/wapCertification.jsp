<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	if (path != null && !path.equals("")) {
		if (path.contains("/")) {
			if (path.equals("/")) {
				path = "";
			}
		} else {
			path += "/";
		}
	}
	String basePath = "";
	if (request.getServerPort() == 80) {
		basePath = request.getScheme() + "://"
				+ request.getServerName() + path + "/";
	} else {
		basePath = request.getScheme() + "://"
				+ request.getServerName() + ":"
				+ request.getServerPort() + path + "/";
	}
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/jquery.mobile.structure-1.4.5.min.css"/>
<link href="css/style.css" type="text/css" rel="stylesheet"/>
<link href="css/member.css" type="text/css" rel="stylesheet"/>
<script src="js/jquery.js"></script>
<script src="js/jquery.mobile-1.4.5.min.js"></script>
<title>资金管理</title> 
</head>

<body>

<div data-role="page" data-position="fixed" class="wk_fund wk_data_a">

    <div data-role="header" data-position="fixed">
    <jsp:include page="header.jsp" />
 
	</div> 
    <div role="main" class="ui-content">
		<div class="head">
        	<img src="images/membertx.png">
            <ul>
            	<li>
            		<div class="item_l">
            		<c:if test="${basicinfor.realName==null||basicinfor.realName==''||user.nameResult!=2}">
                    <p>尚未实名认证</p>
                    </c:if>
                    <c:if test="${!empty basicinfor.realName&&user.nameResult==2}">
                    <p>${basicinfor.realName}</p>
                    </c:if>
            		</div>
                 </li>
                 <li>
                 	<div class="item_l">
                	<c:if test="${basicinfor.phoneNum==null ||basicinfor.phoneNum==''||user.phoneResult!=1}">
                	<p>手机号未认证</p>
                    </c:if>
                    <c:if test="${!empty basicinfor.phoneNum&&user.phoneResult==1}">
                    <p>${basicinfor.phoneNum}</p>
                    </c:if>
					</div>
                 </li>
                    
        </div>
        <div class="main">
        		<div class="item" > 
                	<div class="item_l">真实姓名</div> 
                    <c:choose>
       				<c:when test="${user.nameResult==1}">
           			 <div class="item_r"><a>认证审核中</a></div>
       				</c:when>
       				<c:when test="${!empty basicinfor.realName&&user.nameResult==2}">
             		<div class="item_r"><a>${basicinfor.realName}</a></div>
       				</c:when>
       				<c:otherwise>
              		<div class="item_r"><a href="<%=basePath %>/wap/wapRealName.jsp" data-ajax="false">尚未实名认证</a></div>
       				</c:otherwise>
					</c:choose>
            	</div> 
                <div class="item item_02" > 
                	<div class="item_l">身份证号</div>
                	     <c:choose>
       				<c:when test="${user.nameResult==1}">
           			 <div class="item_r"><a>认证审核中</a></div>
       				</c:when>
       				<c:when test="${!empty basicinfor.idNum&&user.nameResult==2}">
             		<div class="item_r"><a>${basicinfor.idNum}</a></div>
       				</c:when>
       				<c:otherwise>
              		<div class="item_r"><a href="<%=basePath %>/wap/wapRealName.jsp" data-ajax="false">尚未实名认证</a></div>
       				</c:otherwise>
					</c:choose>
            	</div> 
                <div class="item item_03" > 
                	<div class="item_l">手机号码</div>
                	<c:if test="${basicinfor.phoneNum==null ||basicinfor.phoneNum==''||user.phoneResult!=1}">
                    <div class="item_r"><a href="<%=basePath %>/wap/wapPhoneCheck.jsp" data-ajax="false">手机号未认证</a></div>
                    </c:if>
                    <c:if test="${!empty basicinfor.phoneNum&&user.phoneResult==1}">
                    <div class="item_r"><a>${basicinfor.phoneNum}</a></div>
                    </c:if>
            	</div> 
                <div class="item item_04" > 
                	<div class="item_l">绑定邮箱</div>
                	<c:if test="${basicinfor.uservip.mail==null ||basicinfor.uservip.mail==''||user.enable!=1}">
                    <div class="item_r"><a href="<%=basePath %>/wap/wapMailCheck.jsp" data-ajax="false">邮箱未认证或未点击认证链接</a></div>
                    </c:if>
                    <c:if test="${!empty basicinfor.uservip.mail&&user.enable==1}">
                    <div class="item_r"><a>${basicinfor.uservip.mail}</a></div>
                    </c:if>
            	</div> 
        </div>
        
  	</div> 
  
  
</div>    
</body>
</html>
