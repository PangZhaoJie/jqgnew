function checkpwd(){
	var  $pwd=jQuery.noConflict();
	var path=$pwd('#path').val();
	var password=$pwd('#password').val();
	var newpwd=$pwd('#newpwd').val();
	var conpwd=$pwd('#conpwd').val();
	if(password==""){
		$pwd('#pwdTip').html("<span style='font-size:12px;color:red'>原密码不能为空</span>");
		return;
	}else{
		$pwd('#pwdTip').html("");
	}
	if(newpwd=="" || newpwd=="请设置新密码"){
		$pwd('#npwdTip').html("<span style='font-size:12px;color:red'>新密码不能为空</span>");
		return;
	}else{
		$pwd('#npwdTip').html("");
	}
	if(conpwd=="" || conpwd=="请确认新密码"){
		$pwd('#cpwdTip').html("<span style='font-size:12px;color:red'>确认密码不能为空</span>");
		return;
	}else{
		$pwd('#cpwdTip').html("");
	}
	if(password.length<6){
		$pwd('#pwdTip').html("<span style='font-size:12px;color:red'>原密码长度不足六位</span>");
		return;
	}else{
		$pwd('#pwdTip').html("");
	}
	if(newpwd.length<6){
		$pwd('#npwdTip').html("<span style='font-size:12px;color:red'>新密码长度不足六位</span>");
		return;
	}else{
		$pwd('#npwdTip').html("");
	}
	if(conpwd.length<6){
		$pwd('#cpwdTip').html("<span style='font-size:12px;color:red'>确认密码不足六位</span>");
		return;
	}else{
		$pwd('#cpwdTip').html("");
	}
	
	if(password!='' && newpwd!=''){
		if(password==newpwd){
			$pwd('#npwdTip').html("<span style='font-size:12px;color:red'>原密码和新密码不能一样</span>");
			return;
		}else if(newpwd!=conpwd){
			$pwd('#cpwdTip').html("<span style='font-size:12px;color:red'>确认密码和新密码不一样</span>");
			return;
		}
	}
	var form=document.getElementById('pwd1');
	form.action=path+"user/updatepwd";
	form.submit();
}
function checkpwd2(){
	var  $p=jQuery.noConflict();
	var path=$p('#path').val();
	var payPwd=$p('#payPwd').val();
	var newPayPwd=$p('#newPayPwd').val();
	var conPayPwd=$p('#conPayPwd').val();
	if(payPwd==""){
		$p('#paypwdTip').html("<span style='font-size:12px;color:red'>原支付密码不能为空</span>");
		return;
	}else{
		$p('#paypwdTip').html("");
	}
	if(newPayPwd=="" || newPayPwd=="请设置新密码"){
		$p('#npaypwdTip').html("<span style='font-size:12px;color:red'>新支付密码不能为空</span>");
		return;
	}else{
		$p('#npaypwdTip').html("");
	}
	if(conPayPwd=="" || conPayPwd=="请确认新密码"){
		$p('#cpaypwdTip').html("<span style='font-size:12px;color:red'>确认密码不能为空</span>");
		return;
	}else{
		$p('#cpaypwdTip').html("");
	}
	
	if(payPwd.length<6){
		$p('#paypwdTip').html("<span style='font-size:12px;color:red'>原支付密码长度不能少于六位</span>");
		return;
	}else{
		$p('#paypwdTip').html("");
	}
	if(newPayPwd.length<6){
		$p('#npaypwdTip').html("<span style='font-size:12px;color:red'>新支付密码长度不能少于六位</span>");
		return;
	}else{
		$p('#npaypwdTip').html("");
	}
	if(conPayPwd.length<6){
		$p('#cpaypwdTip').html("<span style='font-size:12px;color:red'>确认密码长度不能少于六位</span>");
		return;
	}else{
		$p('#cpaypwdTip').html("");
	}
	if(payPwd!='' && newPayPwd!=''){
		if(payPwd==newPayPwd){
			$p('#npaypwdTip').html("<span style='font-size:12px;color:red'>原支付密码和新支付密码不能一样</span>");
			return;
		}else if(newPayPwd!=conPayPwd){
			$p('#cpaypwdTip').html("<span style='font-size:12px;color:red'>确认密码和新支付密码不一样</span>");
			return;
		}
	}
	var form=document.getElementById('pwd2');
	form.action=path+"user/updatepaypwd";
	form.submit();
}
var  $pwd=jQuery.noConflict();
$pwd(window).load(function(){
	  setTimeout("$pwd('#error').hide()",3000);
	  setTimeout("$pwd('#error2').hide()",3000);
});
window.onload = function(e) {
	var mark=document.getElementById("mark").value;
	var resultString=document.getElementById("resultString").value;
	var resultPay=document.getElementById("resultPay").value;
	var mark1=document.getElementById("mark1").value;
	if(mark1!=null && mark1!='null'){
		mark=mark1;
	}
	if(mark=='2'){
		secBoard('hotnews_caption','list',2);
		var pwd=document.getElementById("error");
		if(resultString=='0'){
			pwd.innerHTML="<span style='font-size:12px;color:red'>输入密码不正确</span>";
		}else if(resultString=='1'){
			pwd.innerHTML="<span style='font-size:12px;color:green'>密码修改成功</span>";
		}
	}else if(mark=='3'){
		secBoard('hotnews_caption','list',3);
		var pay=document.getElementById("error2");
		if(resultPay=='0'){
			pay.innerHTML="<span style='font-size:12px;color:red'>输入支付密码不正确</span>";
		}else if(resultPay=='1'){
			pay.innerHTML="<span style='font-size:12px;color:green'>支付密码修改成功</span>";
		}
	}
	$pwd("#ID1").show();
	
}
