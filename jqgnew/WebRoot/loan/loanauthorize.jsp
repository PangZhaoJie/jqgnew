<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Pragma" content="no-cache" /> 
<meta http-equiv="Cache-Control" content="no-cache" /> 
<meta http-equiv="Expires" content="0" /> 
<title>网贷授权- 乾多多</title>
<script type="text/javascript">
	window.onload=function(){document.form1.submit();}
</script>
</head>
<body>
	<form id="form1" name="form1" action="${SubmitURL}" method="post">
		<input id="MoneymoremoreId" name="MoneymoremoreId" value="${MoneymoremoreId}" type="hidden" />
		<input id="PlatformMoneymoremore" name="PlatformMoneymoremore" value="${PlatformMoneymoremore}" type="hidden" />
		<input id="AuthorizeTypeOpen" name="AuthorizeTypeOpen" value="${AuthorizeTypeOpen}" type="hidden" />
		<input id="AuthorizeTypeClose" name="AuthorizeTypeClose" value="${AuthorizeTypeClose}" type="hidden" />
		<input id="RandomTimeStamp" name="RandomTimeStamp" value="${RandomTimeStamp}" type="hidden" />
		<input id="Remark1" name="Remark1" value="${Remark1}" type="hidden" />
		<input id="Remark2" name="Remark2" value="${Remark2}" type="hidden" />
		<input id="Remark3" name="Remark3" value="${Remark3}" type="hidden" />
		<input id="ReturnURL" name="ReturnURL" value="${ReturnURL}" type="hidden" />
		<input id="NotifyURL" name="NotifyURL" value="${NotifyURL}" type="hidden" />
		<input id="SignInfo" name="SignInfo" value="${SignInfo}" type="hidden" />
	</form>
</body>
</html>
