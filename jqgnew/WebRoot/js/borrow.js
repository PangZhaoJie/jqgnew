//显示与隐藏
function tbjl(sta){
	if(sta==1){
		document.getElementById("tbjlID").style.display="";
	}else if (sta==2){
		document.getElementById("tbjlID").style.display="none";
		 var radio=document.getElementsByName("RadioGroup3");
		 for(var i=0;i<radio.length;i++){ //对所有结果进行遍历，如果状态是被选中的，则将其选择取消  
		      radio[i].checked=false;  
		  } 
		document.getElementById("awardRateId").value="";
		document.getElementById("awardMoneyId").value="";
	}
	if (sta==3){
		document.getElementById("dsjeID").style.display="";
	}else if (sta==4){
		document.getElementById("dsjeID").style.display="none";
	}
	if (sta==5){
		document.getElementById("dxbID").style.display="";
	}else if (sta==6){
		document.getElementById("dxbID").style.display="none";
		document.getElementById("RadioGroup3_0").disabled=true;
		document.getElementById("RadioGroup3_1").disabled=true;
	}
}

//清除状态
function clearStatus(sta){
	if(sta==1){
		document.getElementById("awardMoneyId").value="";
	}else if(sta==2){
		document.getElementById("awardRateId").value="";
	}
}
var borrowMoneyFlag=false;
var rateFlag=false;
 var awardRateFlag=false;
var awardMoneyFlag=false;
var moneyLimitFlag=false;
var titleFlag=false;
var explainsFlag=false;
//借款金额
function borrowMoneyCheck(){
	var quota = document.getElementById("quota").value;
 	var borrowMoney=document.getElementById("borrowMoneyId").value;	 
 	var borrowMoneySpan=document.getElementById("borrowMoneySpan");
 	if(borrowMoney==""){
  		borrowMoneySpan.innerHTML="不能为空";
  		return false;
 	 
	}else{
		if(borrowMoney%50!=0){
			borrowMoneySpan.innerHTML="金额必须是50的倍数！";
	  		return false;
		}else if (parseInt(quota)<parseInt(borrowMoney)|| quota==null || quota==""){
			borrowMoneySpan.innerHTML="信用额度不足！";
	  		return false;
		}else{		
	 		borrowMoneySpan.innerHTML="*";	
	 		return borrowMoneyFlag=true;
		}
	}
}
//年利率
function rateCheck(){
 	
 	var rate=document.getElementById("rateId").value;	 
 	var rateSpan=document.getElementById("rateSpan");
   	if(rate==""){
 		rateSpan.innerHTML="不能为空";
	}else if(rate<0 || rate >24){		 
		rateSpan.innerHTML="利率超出范围"; 	
	}else{
 		var a=document.getElementById("rllId").innerHTML;
 		if(a=="年利率："){
			rateSpan.innerHTML="* 月利率为"+(rate/12).toFixed(3)+"%";
		}else{			
			rateSpan.innerHTML="*"; 
		}
		rateFlag=true;
	}
}
//按投标金额比例奖励
function awardRateDown(){
  document.getElementById("RadioGroup3_0").checked="checked";
  document.getElementById("RadioGroup3_1").checked=""; 
} 
//按投标金额比例奖励
function awardRateCheck(){
	document.getElementById("awardMoneySpan").innerHTML="";
 	var rate=document.getElementById("awardRateId").value;
 	var moneyuseSpan=document.getElementById("awardRateSpan");
 	if(document.getElementById("RadioGroup3_0").checked){
 	 	if(rate==""){
 	   		moneyuseSpan.innerHTML="不能为空"; 	
 		}else{
 	   		moneyuseSpan.innerHTML="";
 	   	    awardRateFlag=true;
 		}
 	}else{
 		moneyuseSpan.innerHTML=""; 
 		awardRateFlag=true;
 	}
  
}

// 按固定金额分摊奖励
function awardMoneyDown(){
  document.getElementById("RadioGroup3_0").checked="";
  document.getElementById("RadioGroup3_1").checked="checked";
 
} 
// 按固定金额分摊奖励
function awardMoneyCheck(){
	document.getElementById("awardRateSpan").innerHTML="";
 	var rate=document.getElementById("awardMoneyId").value;
 	var moneyuseSpan=document.getElementById("awardMoneySpan");
 	if(document.getElementById("RadioGroup3_1").checked){
	   	if(rate==""){
	   		moneyuseSpan.innerHTML="不能为空"; 	
		}else{
	   		moneyuseSpan.innerHTML=""; 
	   		awardMoneyFlag=true;
		}
 	}else{
 		moneyuseSpan.innerHTML="";
 		awardMoneyFlag=true;
	   	}
}
//待收金额限制：
function moneyLimitCheck(){
  	var rate=document.getElementById("moneyLimit").value;
 	var moneyuseSpan=document.getElementById("moneyLimitSpan");
	if(document.getElementById("RadioGroup2_0").checked){
	   	if(rate==""){
	   		moneyuseSpan.innerHTML="不能为空"; 	
		}else{
	   		moneyuseSpan.innerHTML=""; 
	   		moneyLimitFlag=true;
		}
 	}else{
 		moneyuseSpan.innerHTML=""; 	
 		moneyLimitFlag=true;
	   	}
 	 
}
//标题
function titleCheck(){
  	var rate=document.getElementById("titleId").value;
 	var moneyuseSpan=document.getElementById("titleSpan");
   	if(rate==""){
   		moneyuseSpan.innerHTML="不能为空"; 	
	}else{
   		moneyuseSpan.innerHTML=""; 
   		titleFlag=true;
	}	 
}
//借款说明
function explainsCheck(){
  	var rate=document.getElementById("explainsId").value;
 	var moneyuseSpan=document.getElementById("explainsSpan");
   	if(rate==""){
   		moneyuseSpan.innerHTML=""; 	
	}else{
   		moneyuseSpan.innerHTML="";
   		explainsFlag=true;
	}	 
}
function submitCheck(num){
	var boole=0;
	var $sc=jQuery.noConflict();
	$sc(".submit_bt").attr("disabled", "disabled"); //使按钮不能被点击
	var quota = document.getElementById("quota").value;
	var borrowMoney=document.getElementById("borrowMoneyId").value;	 
 	var borrowMoneySpan=document.getElementById("borrowMoneySpan");
 	var explainsId =document.getElementById("explainsId").value;
 	if(borrowMoney==""){
  		alert("借款金额不能为空");
  		boole=1;
	}else{
		if(borrowMoney%50!=0){
			alert("借款金额必须是50的倍数！");
			boole=1;
		}else if (parseInt(quota)<parseInt(borrowMoney) || quota==null || quota==""){
			alert("信用额度不足");
			boole=1;
		}else{
		 	var rate=document.getElementById("rateId").value;	 
		 	var rateSpan=document.getElementById("rateSpan");
		   	if(rate==""){
		 		alert("利率不能为空");
		 		boole=1;
			}else if(rate<0|| rate >24){		 
				alert("利率超出范围"); 
				boole=1;
			}else{		
					var num=document.getElementById("numId").value;
					if(num==""){
						alert("已超过每日最大发标量！");
						boole=1;
					}else{
					 rateCheck();
					borrowMoneyCheck();
					awardRateCheck();
					awardMoneyCheck();moneyLimitCheck(); titleCheck();explainsCheck();
					var a=false;
					var b=false; 
					if(
						document.getElementById("moneyUseId").value=="" ||
						document.getElementById("moneymin").value=="" ||
						document.getElementById("moneymax").value=="" ||
						document.getElementById("validtime").value=="" ||
						document.getElementById("returnway").value==""
							){
				 		a=false;
					}else{
						a=true;
					}
				 if(document.getElementById("periodtime").value=="0"&&document.getElementById("perioddayId").value=="0"
				 	){
					 a=false;
				 }
					if(document.getElementById("RadioGroup1_3").checked){
						if(document.getElementById("dxbID").value==""){
				 			b=false;
						}else{
							b=true;
						}
					}else{
						b=true;
					}
				 	if(borrowMoneyFlag==true&&
					 rateFlag==true&&
				 	 awardRateFlag==true&&
					 awardMoneyFlag==true&&
					 moneyLimitFlag==true&&
					 a==true&&
					 b==true&&
					 titleFlag==true&&
					 explainsFlag==true&&explainsId!=""){
				 		
				 		var form = document.getElementById("borrowFormId");
					      form.action="borrow/saveLssuing";
					      form.submit();
					} else{
						alert("内容填写不完整");
						boole=1;
					}
					} 
			    }
		        }
		if(boole==0){
			document.getElementById("submit_ck").style.background = "gray";
	        document.getElementById("submit_ck").value="正在提交...";
	        document.getElementById("submit_ck").style.disabled=false;
			}
		}
 	  $sc(".submit_bt").removeAttr("disabled"); //使按钮能够被点击
	} 
	

























