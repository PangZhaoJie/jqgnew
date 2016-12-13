<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
		<title>项目详情</title>
  </head>
  
  <body>
    
<div data-role="page" data-position="fixed">
   
    <div data-role="header" data-position="fixed">
   <jsp:include page="header.jsp" />
</div>
<div role="main" class="ui-content" > 
  		<div class="projectHead">
        	<div class="title">${lssuing.title }</div>
            <div class="projectTotal">${lssuing.borrowMoney }<span>借款总额（元）</span></div>
<!--             <div class="progress pro_progress"> -->
<!--                 <div class="progressbar"> -->
<!--                     <div></div>  -->
<!--                 </div> -->
<!--                 <a>进度：60%</a> -->
<!--             </div> -->
        </div>
        <div class="ui-grid-b projectData">
        	<div class="ui-block-a">
            	<div class="ui-bar ui-bar-a">
                	<p>${money }</p>
                    <a>可投金额（元）</a>
                </div>
            </div>
            <div class="ui-block-b">
            	<div class="ui-bar ui-bar-a">
            	<c:choose>
					<c:when test="${lssuing.periodtime==null}">
               		<p><fmt:formatNumber value="${lssuing.rate*365}" pattern="0.00"/></p>
               		</c:when>
					<c:otherwise>
					<p><fmt:formatNumber value="${lssuing.rate}" pattern="0.00"/></p>
					</c:otherwise>
				</c:choose> 
                    <a>年化利率（%）</a>
                </div>
            </div>
            <div class="ui-block-c">
            	<div class="ui-bar ui-bar-a">
              	    <p>
					<c:if test="${lssuing.periodtime!=null}">${lssuing.periodtime.periodTimeName}</c:if>
               		<c:if test="${lssuing.periodday!=null}">${lssuing.periodday.periodDayName}</c:if>
					</p>
                    <a>项目期限</a>
                </div>
            </div>
        </div><!-- /grid-b -->
        <div class="projectMain">
        	<div role="main" class="ui-content">
            	<div data-role="tabs" id="tabs">
                	<div data-role="navbar" class="navbar">
                    	<ul>
                        	<li><a href="#one" data-ajax="false">项目详情</a></li>
                            <li><a href="#two" data-ajax="false" class="two">信息纰漏</a></li>
                            <li><a href="#three" data-ajax="false">投资记录</a></li>
                        </ul>
                     </div>
                     <div id="one" class="ui-body-d ui-content tabMain">
                     	<h6>项目详情</h6>
                        <p><a>借款人&nbsp;&nbsp;&nbsp;&nbsp;</a><span>${lssuing.uservip.userName }</span></p>
                        <p><a>借款用途</a><span>${lssuing.moneyuse.moneyUseName }</span></p>
                        <p><a>还款方式</a><span>${lssuing.returnway.returnWayName }</span></p>
                        <p><a>借款状态</a><span>${state }</span></p>
                        <p><a>投标进度</a><span><fmt:formatNumber value="${bear }" pattern="0.00" />%</span></p>
                        <p><a>借款奖励</a><span>${away }</span></p>
                  </div>
                     <div id="two" class="ui-body-d ui-content tabMain">
                     	<h6>信息纰漏</h6>
                        <div style=" margin-bottom:10px;">
                        	<c:forEach var="lssList" items="${LPlist}">
                           			 <span><a href="<%=path%>${lssList.photo}" rel="<%=path%>${lssList.photo}" target="_blank" ><img src="<%=path%>${lssList.photo}" style="width:80%; height:auto; display:block; margin:0 auto;" /></a></span>
                           	</c:forEach>
                        
                        </div>
                     </div>
                     <div id="three" class="ui-body-d ui-content tabMain">
                     	<h6>投资记录</h6>
                        <div class="ui-grid-b wk_grey">
                        	<div class="ui-block-a"><div class="ui-bar ui-bar-a" >投标人</div></div>
                            <div class="ui-block-b"><div class="ui-bar ui-bar-a" >投资金额</div></div>
                            <div class="ui-block-c"><div class="ui-bar ui-bar-a" >投标时间</div></div>
                         </div><!-- /grid-b -->
                         <c:forEach items="${Tlist }" var="t" >
	                         <div class="ui-grid-b wk_white">
	                        	<div class="ui-block-a"><div class="ui-bar ui-bar-a" >${t.uservip.userNames }</div></div>
	                            <div class="ui-block-b"><div class="ui-bar ui-bar-a" ><fmt:formatNumber value="${t.money }" pattern="0.00" /></div></div>
	                            <div class="ui-block-c"><div class="ui-bar ui-bar-a" ><fmt:formatDate value="${t.tenderTime }" pattern="yyyy-MM-dd HH:mm:ss" /></div></div>
	                         </div><!-- /grid-b -->
                         </c:forEach>
                         
 
                     </div>
                </div>
            </div>
        </div>
    </div> 
<div data-role="footer" data-position="fixed">  
	<c:if test="${lssuing.state==1 }">
   	<div class="confirmBt"><a disabled="true"  data-ajax="false" class="ui-btn ui-corner-all ui-shadow ui-btn-inline">已满标</a></div>
   	</c:if>
   	<c:if test="${lssuing.state==2 }">
   	<div class="confirmBt"><a href="<%=path %>/wap/toTenderSubmit?lssuingId=${lssuing.lssuingId}" data-ajax="false" class="ui-btn ui-corner-all ui-shadow ui-btn-inline">正在招标</a></div>
   	</c:if>
   	<c:if test="${lssuing.state==3 }">
   	<div class="confirmBt"><a disabled="true" data-ajax="false" class="ui-btn ui-corner-all ui-shadow ui-btn-inline">还款中</a></div>
   	</c:if>
   	<c:if test="${lssuing.state==4 }">
   	<div class="confirmBt"><a disabled="true" data-ajax="false" class="ui-btn ui-corner-all ui-shadow ui-btn-inline">已完成</a></div>
   	</c:if>
</div>
</div>  
  </body>
</html>
