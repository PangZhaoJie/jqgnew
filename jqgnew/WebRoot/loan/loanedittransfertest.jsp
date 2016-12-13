<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragrma", "no-cache");
	response.setDateHeader("Expires", 0);
	
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<title>网贷转账测试 - 乾多多</title>
</head>
<body>
	<form action="<%=basePath %>loan/testtestloantransfer2.action" method="post">
		投标
		<br/>
		付款人<input id="LoanOutMoneymoremore1" name="LoanOutMoneymoremore1" value="" type="text" />例:m1
		<br/>
		收款人<input id="LoanInMoneymoremore1" name="LoanInMoneymoremore1" value="" type="text" />例:m2
		<br/>
		平台订单号<input id="OrderNo1" name="OrderNo1" value="${OrderNo1}" type="text" />
		<br/>
		平台标号<input id="BatchNo1" name="BatchNo1" value="${BatchNo1}" type="text" />
		<br/>
		流转标标号<input id="ExchangeBatchNo1" name="ExchangeBatchNo1" value="${ExchangeBatchNo1}" type="text" />
		<br/>
		垫资标号<input id="AdvanceBatchNo1" name="AdvanceBatchNo1" value="${AdvanceBatchNo1}" type="text" />
		<br/>
		金额<input id="Amount1" name="Amount1" value="${Amount1}" type="text" />
		<br/>
		满标标额<input id="FullAmount1" name="FullAmount1" value="${FullAmount1}" type="text" />
		<br/>
		用途<input id="TransferName1" name="TransferName1" value="${TransferName1}" type="text" />
		<br/>
		备注<input id="MainRemark1" name="MainRemark1" value="${MainRemark1}" type="text" />
		<br/><br/>
		二次分配1
		<br/>
		收款人<input id="SLoanInMoneymoremore1" name="SLoanInMoneymoremore1" value="${SLoanInMoneymoremore1}" type="text" />例:m3
		<br/>
		金额<input id="SAmount1" name="SAmount1" value="${SAmount1}" type="text" />
		<br/>
		用途<input id="STransferName1" name="STransferName1" value="${STransferName1}" type="text" />
		<br/>
		备注<input id="SRemark1" name="SRemark1" value="${SRemark1}" type="text" />
		<br/><br/>
		二次分配2
		<br/>
		收款人<input id="SLoanInMoneymoremore2" name="SLoanInMoneymoremore2" value="${SLoanInMoneymoremore2}" type="text" />例:m3
		<br/>
		金额<input id="SAmount2" name="SAmount2" value="${SAmount2}" type="text" />
		<br/>
		用途<input id="STransferName2" name="STransferName2" value="${STransferName2}" type="text" />
		<br/>
		备注<input id="SRemark2" name="SRemark2" value="${SRemark2}" type="text" />
		<br/><br/>
		
		手续费
		<br/>
		收款人<input id="LoanInMoneymoremore2" name="LoanInMoneymoremore2" value="${LoanInMoneymoremore2}" type="text" />
		<br/>
		平台订单号<input id="OrderNo2" name="OrderNo2" value="${OrderNo2}" type="text" />
		<br/>
		平台标号<input id="BatchNo2" name="BatchNo2" value="${BatchNo2}" type="text" />
		<br/>
		金额<input id="Amount2" name="Amount2" value="${Amount2}" type="text" />
		<br/>
		满标标额<input id="FullAmount2" name="FullAmount2" value="${FullAmount2}" type="text" />
		<br/>
		用途<input id="TransferName2" name="TransferName2" value="${TransferName2}" type="text" />
		<br/>
		备注<input id="MainRemark2" name="MainRemark2" value="${MainRemark2}" type="text" />
		<br/><br/>
		
		红包
		<br/>
		收款人<input id="LoanInMoneymoremore3" name="LoanInMoneymoremore3" value="" type="text" />例:m2
		<br/>
		平台订单号<input id="OrderNo3" name="OrderNo3" value="${OrderNo3}" type="text" />
		<br/>
		平台标号<input id="BatchNo3" name="BatchNo3" value="${BatchNo3}" type="text" />
		<br/>
		金额<input id="Amount3" name="Amount3" value="${Amount3}" type="text" />
		<br/>
		满标标额<input id="FullAmount3" name="FullAmount3" value="${FullAmount3}" type="text" />
		<br/>
		用途<input id="TransferName3" name="TransferName3" value="${TransferName3}" type="text" />
		<br/>
		备注<input id="MainRemark3" name="MainRemark3" value="${MainRemark3}" type="text" />
		<br/><br/>
		
		其他信息
		<br/>
		转账类型<input id="TransferAction" name="TransferAction" value="${TransferAction}" type="text" />(1.投标 2.还款 3.其他)
		<br/>
		操作类型<input id="Action" name="Action" value="${Action}" type="text" />(1.手动 2.自动)
		<br/>
		转账方式<input id="TransferType" name="TransferType" value="${TransferType}" type="text" />(1.桥连 2.直连)
		<br/>
		是否需要托管账户审核<input id="NeedAudit" name="NeedAudit" value="${NeedAudit}" type="text" />(空.需要审核 1.自动通过)
		<br/>
		平台标识<input id="PlatformMoneymoremore" name="PlatformMoneymoremore" value="${PlatformMoneymoremore}" type="text" />例:p1
		<br/>
		备注1<input id="Remark1" name="Remark1" value="${Remark1}" type="text" />
		<br/>
		备注2<input id="Remark2" name="Remark2" value="${Remark2}" type="text" />
		<br/>
		备注3<input id="Remark3" name="Remark3" value="${Remark3}" type="text" />
		<br/>
		<input value="提交" type="submit" />
	</form>
</body>
</html>
