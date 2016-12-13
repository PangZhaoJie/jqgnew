<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
 <meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="icon" href="<%=path%>/image/favicon/favicon.ico"
	type="image/x-icon" />
<title>${websiteIndex.homeTitle}</title>
<meta name="Keywords" content="${websiteIndex.webKeyword}" />
<meta name="descripton" content="${websiteIndex.webDeescription}" />

</head>

<body>

<div data-role="page" data-dom-cache＝"false">
<jsp:include page="header.jsp" />
<div role="main" class="ui-content">  
    <div class="bannerBox">
		<div class="bd slider">
			<ul>
				<c:forEach items="${lnList }" var="latestnewssIndex">
					<li><a target="_blank" href="http://${latestnewssIndex.url}"><img src="<%=path %>${latestnewssIndex.photo}" /></a></li>
				</c:forEach>
			</ul>
		</div>		    
    </div> 
   <!-- /bannerBox -->
    <div class="membersDate">
        已经有<a>${count }</a>位客户加入
    </div>
    <div class="recommendBox">
        <div class="head">
            <h2>新标预告</h2>
        </div>
        <div class="recommend">
            <div class="recommendIcon"></div>
            <div class="recommendContent">
                <div class="title">${lssuingherald.lssuingName }</div>
                <div class="recommendMain">
                    <div class="one">
                        <p>年化利率</p>
                        <a><span>${lssuingherald.rate}</span>%</a>
                    </div>
                    <div class="two">
                        <p>项目期限</p>
                        <a>${lssuingherald.time}</a>
                    </div>
                    
                </div>
            </div>
            
        </div>
    </div><!-- /recommendBox -->
    
    <div class="recommendBox hotBox">
        <div class="head">
            <h2>项目列表</h2>
        </div>
       <c:forEach items="${lList }" var="lssuing">
       <a href="<%=path %>/wap/toTender?lssuingId=${lssuing.lssuingId}" data-ajax="false"> 
        <div class="recommend hotItem">
            <div class="title">
<!--                 <p id="ico_ks">抵押标</p> -->
                <p>${lssuing.title}</p>
            </div>
            <div class="mainBox">
                <div class="one">
                    <p><fmt:formatNumber value="${lssuing.borrowMoney}" pattern="0.00" /></p>
                    <a>融资金额（元）</a>
                </div>
                <div class="two">
                <c:choose>
					<c:when test="${ lssuing.periodtime==null}"> 
				   		<p><fmt:formatNumber value="${lssuing.rates*365}" pattern="0.00"/></p>
				   </c:when>
					<c:otherwise>  
				   		<p><fmt:formatNumber value="${lssuing.rates}" pattern="0.00"/></p>
				   </c:otherwise>
				</c:choose>
                    <a>年化利率（%）</a>
                </div>
                <div class="three">
                    <p>
						<c:choose>
							<c:when test="${ lssuing.periodtime==null}"> 
						   		${lssuing.periodday.periodDayName }
						   </c:when>
							<c:otherwise>  
						   		${lssuing.periodtime.periodTimeName }
						   </c:otherwise>
						</c:choose>
					</p>
                    <a>项目期限</a>
                </div>
            </div>
            <p class="border1px"></p>
            <div class="progress">
                <div class="progressbar">
                    <div style="width:${lssuing.scale}%"></div> 
                </div>
                <a>进度：${lssuing.scale}%</a>
            </div>
        </div>
        </a>
        </c:forEach>
        
        
        
        <a href="<%=path %>/wap/tenderList" class="enterList" data-ajax="false">进入投资列表</a>  
    </div><!-- /hotBox -->
</div>    
<div data-role="footer">
   <jsp:include page="footer.jsp" />
</div>    
</div>    
<script type="text/javascript">
      $(".slider").yxMobileSlider({width:640,height:320,during:3000})
</script>  
</body>
</html>