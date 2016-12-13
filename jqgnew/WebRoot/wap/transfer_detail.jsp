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
    
<div data-role="page" data-position="fixed" class="wk_invest">
   
    <div data-role="header" data-position="fixed" >
   <jsp:include page="header.jsp" />
</div>
<div role="main" class="ui-content" > 
  		<div class="projectHead">
        	<div class="title">${lssuing.title }</div>
        </div>
        <div class="ui-grid-b projectData">
        	<div class="ui-block-a">
            	<div class="ui-bar ui-bar-a">
                	<p>${lssuing.borrowMoney}</p>
                    <a>借款总额（元）</a>
                </div>
            </div>
            <div class="ui-block-b">
            	<div class="ui-bar ui-bar-a">
               	<c:choose>
					<c:when test="${lssuing.periodtime==null}">
               		<p><fmt:formatNumber value="${lssuing.rate}" pattern="0.00"/></p>
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
                        	<li><a href="#one" data-ajax="false">债权详情</a></li>
                        </ul>
                     </div>
                     <div id="one" class="ui-body-d ui-content tabMain">
                        <p><a>债权持有人</a><span>${username}</span></p>
                        <p><a>借款用途</a><span>${lssuing.moneyuse.moneyUseName }</span></p>
                        <p><a>还款方式</a><span>${lssuing.returnway.returnWayName }</span></p>
                        <p><a>投标进度</a><span><fmt:formatNumber value="${bear}" pattern="0.00" />%</span></p>
                        <p><a>借款奖励</a><span>${away }</span></p>
                        <p><a>借款期限</a><span><c:if test="${lssuing.periodtime!=null}">${lssuing.periodtime.periodTimeName}</c:if>
                        <c:if test="${lssuing.periodday!=null}">${lssuing.periodday.periodDayName}</c:if></span></p>
						<p><a>收益金额</a><span>${benxistr}</span></p>
						<p><a>认购价格</a><span>${transferMoney}</span></p>
						<p><a>还款本息</a><span>${interest}</span></p>
                  </div>
                </div>
            </div>
        </div>
    </div> 
<div data-role="footer" data-position="fixed">  
	<c:if test="${transfer==3}">
   	<div class="confirmBt"><a disabled="true"  data-ajax="false" class="ui-btn ui-corner-all ui-shadow ui-btn-inline">已认购完</a></div>
   	</c:if>
   	<c:if test="${transfer!=3}">
   	      <form  id="form1"  method="post">
        	<div class="wk_money">
            	<input type="hidden" name="tenderId" id="tenderId" value="${tenderId}">
            	<input type="hidden" name="lssuingId" id="lssuingId" value="${lssuing.lssuingId}">
                <div class="ui-grid-b" style="margin-top: 10px;">
                	<div class="ui-block-a"><div class="ui-bar ui-bar-a">支付密码</div></div>
                    <div class="ui-block-b"><div class="ui-bar ui-bar-a">
                    	<label for="textinput-4" class="ui-hidden-accessible">Text Input:</label>
                        <input type="text" name="payPwd" id="payPwd" placeholder="请输入支付密码" value="">
                    </div></div>
                    <div class="ui-block-c"><div class="ui-bar ui-bar-a"></div></div>
                </div>
             </div>   
        </form>
   	<div class="confirmBt"><a onclick="toSubmit();" data-ajax="false" class="ui-btn ui-corner-all ui-shadow ui-btn-inline">立即认购</a></div>
   	</c:if>
</div>
</div>  
  </body>
  <script type="text/javascript">
function toSubmit(){
	var paypwd=$("#payPwd").val();
	var tenderId=$("#tenderId").val();
	var lssuingId = $("#lssuingId").val();
	if(tenderId==""||tenderId==null){
		alert("债权信息不正确");
	}else if(paypwd==""||paypwd==null){
		alert("支付密码不能为空");
	}else{
			var form = document.getElementById("form1");
	        form.action="wap/transferBuy";
 	        form.submit();
		 	document.activeElement.style.background = "gray";
		    document.activeElement.value="正在提交...";
		    document.activeElement.disabled=false;	
	}
	
}

</script>
</html>
