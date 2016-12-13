<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<title>易通支付跳转</title>
</head>
<body >
	<form name="easypay" action="http://cashier.etonepay.com/NetPay/BankSelect.action" method="post" id="easypay">
		<input type="hidden" name="version" value="1.0.0" readonly="readonly"/><a>版本</a><br>
		<input type="hidden" name="transCode" value="${transCode}"  readonly="readonly"/><a>交易代码</a><br>
		<input type="hidden" name="merchantId" value="${merchantId}" readonly="readonly"/><a>商户编号</a><br>
		<input type="hidden" name="merOrderNum" value="${merOrderNum}"  readonly="readonly"/><a>商户订单号</a><br>
		<input type="hidden" name="bussId" value="${bussId}" readonly="readonly"/><a>业务代码</a><br>
		<input type="hidden" name="tranAmt" value="${tranAmt}" readonly="readonly"/><a>交易金额</a><br>
		<input type="hidden" name="sysTraceNum" value="${sysTraceNum}"  readonly="readonly"/><a>商户请求流水号</a><br>
		<input type="hidden" name="tranDateTime" value="${tranDateTime}"  readonly="readonly"/><a>交易时间</a><br>
		
		<input type="hidden" name="merURL" value="${merURL}" readonly="readonly"/><a>商户返回页面</a><br>
		<input type="hidden" name="backURL" value="${backURL}" readonly="readonly"/><a>回调商户地址</a><br>
		<input type="hidden" name="orderInfo" value="${orderInfo}" readonly="readonly"/><a>订单信息</a><br>
		<input type="hidden" name="userId" value="${userId}"  readonly="readonly"/><a>用户ID</a><br>
		<input type="hidden" name="userIp" value="${userIp}"  readonly="readonly"/><a>订单用户IP</a><br>
		<input type="hidden" name="bankId" value="${bankId}" readonly="readonly"/><a>银行代码</a><br>
		<input type="hidden" name="stlmId" value="${stlmId}"  readonly="readonly"/><a>结算规则代码</a><br>
		<input type="hidden" name="currencyType" value="${currencyType}"  "/><a>贷币代码</a><br>
		<input type="hidden" name="reserver1" value="${reserver1}" readonly="readonly"/><a>保留域1</a><br>
		<input type="hidden" name="reserver2" value="${reserver2}"  readonly="readonly"/><a>保留域2</a><br>
		<input type="hidden" name="reserver3" value="${reserver3}"  readonly="readonly"/><a>保留域3</a><br>
		<input type="hidden" name="reserver4" value="${reserver4}"  readonly="readonly"/><a>保留域4</a><br>
		<input type="hidden" name="signValue" value="${signValue}"  readonly="readonly"/><a>数字签名</a><br>
<!-- 	    <input type="submit" value="确认付款"/> -->
	 </form>

</body>
<script type="text/javascript">
window.onload = function(){
	document.getElementById("easypay").submit();
}
</script>
</html>