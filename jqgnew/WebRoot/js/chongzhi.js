function toQC(){
	document.getElementById("tsID").innerHTML="";
}
function toPay(){
	  var $toPay =  jQuery.noConflict();
	  var a=document.getElementById("testId");
 	  if(a.value!="0" && a.value!="0.0" && a.value!="0.00" &&a.value!=""){
 		 $toPay("#payID").submit();
		 	 
	  }else{
		  document.getElementById("tsID").innerHTML="充值金额必须大于0";
	  }
     
}

var rechargeAmountFlag=false;
function clickBank(){
 	 var radios = document.getElementsByName("RadioGroup1");
	    for (var i = 0; i < radios.length; i++) {
	        if (radios[i].checked) {
 	        	rechargeAmountFlag=true;
 	        } 
	    }
}

function onChongzhi(){
	var $sb =  jQuery.noConflict();
	var a=document.getElementById("rechargeAmountId").value;
	var b=document.getElementById("billSerialNumId").value;
	var c=document.getElementById("rechargeId").value;
	var d=document.getElementById("imgUrlId").value;
	clickBank();
 	if(rechargeAmountFlag==true){
			if(a!=""&&b!=""&&c!=""&&d!="" ){
				$sb(".submit_bt").attr("disabled","disabled");//防止重复提交
				var form=document.getElementById("saveOffLineId");
				form.action="borrow/saveOffLine";
				form.submit();
			}else{
				alert("信息不完整");
				return false;
			}
			
 
	}else{
		alert("信息不完整");
		return false;
	}
}
 
 