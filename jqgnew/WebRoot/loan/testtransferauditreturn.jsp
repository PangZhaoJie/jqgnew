<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Pragma" content="no-cache" /> 
<meta http-equiv="Cache-Control" content="no-cache" /> 
<meta http-equiv="Expires" content="0" /> 
<title>网贷审核页面返回 - 乾多多</title>
</head>
<body>
	流水号列表:${LoanNoList}
	<br/>
	有问题的流水号列表:${LoanNoListFail}
	<br/>
	审核类型:${AuditType}
	<br/>
	平台标识:${PlatformMoneymoremore}
	<br/>
	随机时间戳:${RandomTimeStamp}
	<br/>
	备注1:${Remark1}
	<br/>
	备注2:${Remark2}
	<br/>
	备注3:${Remark3}
	<br/>
	返回码:${ResultCode}
	<br/>
	返回信息:${Message}
	<br/><br/>
	页面返回:${verifySignature}
</body>
</html>
