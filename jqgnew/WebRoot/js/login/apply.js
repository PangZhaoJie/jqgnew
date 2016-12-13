var $m=jQuery.noConflict();
function apply(){
	var path=$m('#path').val();
	$m(".submit_bt").attr("disabled","disabled");
	var requestQuota=$m('#requestQuota').val();
	var applyExplain=$m('#applyExplain').val();
	if(requestQuota=="" || requestQuota==null){
		Alert("申请金额不能为空!");
		//$m('#applyTip').html("<span style='font-size:12px;color:red'>尚未输入金额</span>");
		return false;
	}else{
		$m('#applyTip').html("");
	}
	if(applyExplain=="" || applyExplain==null){
		Alert("申请说明不能为空!");
		return false;
	}
	var form=document.getElementById("apply");
	form.action=path+"user/apply";
	form.submit();
}