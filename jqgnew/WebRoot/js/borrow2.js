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

var borrowMoneyFlag=false;
var rateFlag=false;
var titleFlag=false;
//借款金额
function borrowMoneyCheck(){
 	var borrowMoney=document.getElementById("borrowMoneyId").value;	 
 	if(borrowMoney==""){
  		alert("借款金额不能为空!");
  		return false;
 	 
	}else{
		if(borrowMoney%50!=0){
			alert("金额必须是50的倍数!");
	  		return false;
		}else{		
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
	}else if(rate<0 || rate==0){		 
		rateSpan.innerHTML="利率必需大于0"; 	
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
//标题
function titleCheck(){
  	var rate=document.getElementById("titleId").value;
   	if(rate==""){
   		alert("借款标题不能为空！");
   		return false;
	}else{
   		titleFlag=true;
	}	 
}
function submitCheck(){
	var boole=0;
	var $sc=jQuery.noConflict();
	var path = document.getElementById("path").value;
	$sc(".btn").attr("disabled", "disabled"); //使按钮不能被点击
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
		}else{
		 	var rate=document.getElementById("rateId").value;	 
		 	var rateSpan=document.getElementById("rateSpan");
		   	if(rate==""){
		 		alert("利率不能为空");
		 		boole=1;
			}else if(rate<0 || rate==0){		 
				alert("利率必需大于0"); 
				boole=1;
			}else{		
					var num=document.getElementById("numId").value;
					if(num==""){
						alert("已超过每日最大发标量！");
						boole=1;
					}else{
					rateCheck();
					borrowMoneyCheck();
					titleCheck();
					var a=false;
					if(
						document.getElementById("moneyUseId").value=="" ||
						document.getElementById("moneyMinId").value=="" ||
						document.getElementById("moneymaxId").value=="" ||
						document.getElementById("validtimeId").value=="" ||
						document.getElementById("returnwayId").value==""
							){
				 		a=false;
					}else{
						a=true;
					}
				 if(document.getElementById("periodtimeId").value=="0"&&document.getElementById("perioddayId").value=="0"
				 	){
					 a=false;
				 }
				 	if(borrowMoneyFlag==true&&
					 rateFlag==true&&
					 a==true&&
					 titleFlag==true&&
					 explainsId!=""){
				 		
				 		var form = document.getElementById("2_tabId1");
					      form.action=path+"borrow/savePracticeLssuing";
					      form.submit();
					} else{
						alert("内容填写不完整");
						boole=1;
					}
					} 
			    }
		        }
		if(boole==0){
			document.getElementById("mitOnebutton").style.background = "gray";
	        document.getElementById("mitOnebutton").value="正在提交...";
	        document.getElementById("mitOnebutton").style.disabled=false;
			}
		}
 	  $sc(".btn").removeAttr("disabled"); //使按钮能够被点击
	} 
	

























