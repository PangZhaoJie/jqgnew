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
		<title>项目投资</title>
  </head>
  
  <body>
    
<div data-role="page" data-position="fixed" class="wk_invest">
   
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
          <form  id="form1"  method="post">
        	<div class="wk_money">
            	<input type="hidden" name="lssuingId" id="lssuingId" value="${lssuing.lssuingId }">
            	<div class="ui-grid-b">
                	<div class="ui-block-a"><div class="ui-bar ui-bar-a">投资金额</div></div>
                    <div class="ui-block-b"><div class="ui-bar ui-bar-a">
                    	<label for="textinput-4" class="ui-hidden-accessible">Text Input:</label>
                        <input type="text" name="investMoney" id="investMoney" placeholder="最小金额100元,最大金额200元" value="">
                    </div></div>
                    <div class="ui-block-c"><div class="ui-bar ui-bar-a">元</div></div>
                </div><!-- /grid-b --> 
                <div class="ui-grid-b" style="margin-top: 10px;">
                	<div class="ui-block-a"><div class="ui-bar ui-bar-a">支付密码</div></div>
                    <div class="ui-block-b"><div class="ui-bar ui-bar-a">
                    	<label for="textinput-4" class="ui-hidden-accessible">Text Input:</label>
                        <input type="text" name="paypwd" id="paypwd" placeholder="请输入支付密码" value="">
                    </div></div>
                    <div class="ui-block-c"><div class="ui-bar ui-bar-a"></div></div>
                </div><!-- /grid-b --> 
             </div>   
             
             <div class="wk_invest_btn">  
    			<a  onclick="toSubmit();" >确定投资</a>    
             </div>   
        </form>
        
    </div> 
   <div data-role="footer">  
   </div>
</div>    
</body>
<script type="text/javascript">
function toSubmit(){
	var investMoney=$("#investMoney").val();
	var paypwd=$("#paypwd").val();
	var lssuingId=$("#lssuingId").val();
	if(lssuingId==""||lssuingId==null){
		alert("标信息不正确");
	}else if(investMoney==""||investMoney==null){
		alert("投标金额不能为空");
	}else if(paypwd==""||paypwd==null){
		alert("支付密码不能为空");
	}else{
			var form = document.getElementById("form1");
	        form.action="wap/tender";
 	        form.submit();
		 	document.activeElement.style.background = "gray";
		    document.activeElement.value="正在提交...";
		    document.activeElement.disabled=false;	
	}
	
}

</script>
</html>
