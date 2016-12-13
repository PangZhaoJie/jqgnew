
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<script type="text/javascript" src="../js/jquery-1.8.2.min.js"></script>
<script type="text/javascript">
var $page =  jQuery.noConflict();
function getJSONData() {
	document.getElementById("form").submit();
}
$page(function() {  
	getJSONData();  
});
</script>
<head><title>支付</title></head>

<body >
<div>
<!-- <div> -->
<form action="https://pay.ecpss.com/sslpayment" method="post" id="form"name="E_FORM">
  <table align="center">
    <tr>
      <td></td>
      <td><input  name="MerNo" value="${MerNo}"></td>
    </tr>
    <tr>
      <td></td>
      <td><input  name="BillNo" value="${offlinerecharge.offlineRechargeId}"></td>
    </tr>
    <tr>
      <td></td>
      <td><input  name="Amount" value="${chongzhiMoney}"></td>
    </tr>

    <tr>
      <td></td>
      <td><input  name="ReturnURL" value="${ReturnURL}"></td>
    </tr>
    
	 <tr>
      <td></td>
      <td><input name="AdviceURL" value="${AdviceURL}" ></td>
    </tr>
    <tr>
      <td></td>
      <td><input  name="SignInfo" value="${SignInfo}"></td>
    </tr>
    
	 <tr>
      <td></td>
      <td><input  name="defaultBankNumber" value="${defaultBankNumber}" ></td>
    </tr>
	 <tr>
      <td></td>
      <td><input  name="orderTime" value="${orderTime}" ></td>
    </tr>
    <tr>
      <td></td>
      <td><input  name="products" value="${products}" ></td>
    </tr>
  </table>
  <p align="center">
    <input type="submit" name="b1" value="Payment">
  </p>
</form>


</div>
</body>
</html>
