function submitCheck(){
	var path=document.getElementById("path").value;
	var currentLiveCity1=document.getElementById("currentLiveCity1").value;
	var currentLiveCity2=document.getElementById("currentLiveCity2").value;
	var currentLiveCity3=document.getElementById("currentLiveCity3").value;
	var job=document.getElementById("job").value;
	var monthIncomes=document.getElementsByName("basicinfor.monthIncome");
	var monthIncome=-1;
	//0为5k以下，1为5k-10k,2为10k-50k，3为50k以上
	for(var p=0;p<monthIncomes.length;p++){
		if(monthIncomes[p].checked==true){
			monthIncome=p;
		}
	}
	
	var realName=document.getElementById("reaName").value;
	var pNum=document.getElementById("pNum").value;
	if(realName=="尚未实名验证"){
		Alert("尚未实名验证!");
		return;
	}
	if(pNum=="尚未手机验证"){
		Alert("尚未手机验证!");
		return;
	}
//	var cityTip=document.getElementById("cityTip");
//	if(currentLiveCity1=="" || currentLiveCity2=="" || currentLiveCity3==""){
//		cityTip.innerHTML="城市不能为空";
//		return;
//	}else{
//		cityTip.innerHTML="";
//	}
//	var jobTip=document.getElementById("jobTip");
//	if(job==""){
//		jobTip.innerHTML="工作不能为空";
//		return;
//	}else{
//		jobTip.innerHTML="";
//	}
	var personalProfile=document.getElementById("personalProfile").value;
	if(currentLiveCity1=="" || currentLiveCity2=="" || currentLiveCity3=="" || job=="" || personalProfile==""){
		Alert("信息填写不完整!");
		return;
	}
	var form=document.getElementById("list1");
    form.action=path+"user/list1";
	form.submit();
}
function submitCheck2(mark){
	var path=document.getElementById("path").value;
	var currentAddress=document.getElementById("currentAddress").value;
	var homePhone=document.getElementById("homePhone").value;
	var linkmanOne=document.getElementById("linkmanOne").value;
//	var relationOnes=document.getElementsByName("relationOne");
//	var relationOne=0;
//	for(var i=0;i<relationOnes.length;i++){
//		if(relationOnes[i].checked==true){
//			relationOne=i;
//		}
//	}
	var phoneOne=document.getElementById("phoneOne").value;
	var otherOne=document.getElementById("otherOne").value;
//	otherOne=encodeURI(encodeURI(otherOne));
	var linkmanTwo=document.getElementById("linkmanTwo").value;
	var phoneTwo=document.getElementById("phoneTwo").value;
	var otherTwo=document.getElementById("otherTwo").value;
	
	var linkmanThree=document.getElementById("linkmanThree").value;
	var phoneThree=document.getElementById("phoneThree").value;
	var otherThree=document.getElementById("otherThree").value;
//	linkmanTwo=encodeURI(encodeURI(linkmanTwo));
//	var relationTwos=document.getElementsByName("relationTwo");
//	var relationTwo=0;
//	for(var i=0;i<relationTwos.length;i++){
//		if(relationTwos[i].checked==true){
//			relationTwo=i;
//		}
//	}
	if(mark=='0'){
//		if(currentAddress=="" || homePhone=="" || linkmanOne=="" || phoneOne==""  || otherOne=="" || linkmanTwo=="" || phoneTwo==""||otherTwo==""||linkmanThree==""||phoneThree==""||otherThree==""){
//		Alert("内容信息填写不完整!");
//		return;
//		}
		if(currentAddress!="" && homePhone!="" &&(linkmanOne!="" && phoneOne!=""  && otherOne!="") || (linkmanTwo!="" && phoneTwo!="" && otherTwo!="")||(linkmanThree!="" && phoneThree!="" && otherThree!="")){
			var form=document.getElementById("list2");
			form.action=path+"user/list2?flag1="+mark;
			form.submit();
		}else{
			Alert("内容信息填写不完整!");
			return;
		}
	}
    
}
function submitCheck3(mark){
	var path=document.getElementById("path").value;
	var companyName=document.getElementById("companyName").value;
	var companyPhone=document.getElementById("companyPhone").value;
	var companyAddress=document.getElementById("companyAddress").value;
	var reference=document.getElementById("reference").value;
	var referPhone=document.getElementById("referPhone").value;
	if(mark=='0'){
		if(companyName=="" ||companyPhone=="" ||companyAddress==""||reference==""||referPhone==""){
		Alert("内容信息填写不完整!");
		return;
	}
	}
	  var form=document.getElementById("list3");
	  form.action=path+"user/list3?flag1="+mark;
	  form.submit();
}
function submitCheck4(mark){
	var path=document.getElementById("path").value;
	var monthAverEarn=document.getElementById("monthAverEarn").value;
	var incomeDescribe=document.getElementById("incomeDescribe").value;
	var monthAverPay=document.getElementById("monthAverPay").value;
	var payDescribe=document.getElementById("payDescribe").value;
	var houseValue=document.getElementById("houseValue").value;
	var carValue=document.getElementById("carValue").value;
    var joinCompanyName=document.getElementById("joinCompanyName").value;
    var joinCompanyResource=document.getElementById("joinCompanyResource").value;
    var otherResourceDescribe=document.getElementById("otherResourceDescribe").value;
    var houseCondition_2=document.getElementById("houseCondition_2").checked;
    var ifByCar_1=document.getElementById("ifByCar_1").checked;
    if(mark=='0'){
//		if(monthAverEarn==""||incomeDescribe==""||monthAverPay==""||payDescribe==""||houseValue==""||carValue==""||joinCompanyName==""||joinCompanyResource==""||otherResourceDescribe==""){
//			Alert("内容信息填写不完整!");
//		    return;
//		}
    	if(houseCondition_2==true && ifByCar_1==true){
    		if(monthAverEarn==""||incomeDescribe==""||monthAverPay==""||payDescribe==""||joinCompanyName==""||joinCompanyResource==""||otherResourceDescribe==""){
    			Alert("内容信息填写不完整!");
    		    return;
    		}
    	}else if(houseCondition_2==true){
    		if(monthAverEarn==""||incomeDescribe==""||monthAverPay==""||payDescribe==""|| carValue==""||joinCompanyName==""||joinCompanyResource==""||otherResourceDescribe==""){
    			Alert("内容信息填写不完整!");
    		    return;
    		}
    	}else if(ifByCar_1==true){
    		if(monthAverEarn==""||incomeDescribe==""||monthAverPay==""||payDescribe==""||houseValue==""||joinCompanyName==""||joinCompanyResource==""||otherResourceDescribe==""){
    			Alert("内容信息填写不完整!");
    		    return;
    		}
    	}else{
    		if(monthAverEarn==""||incomeDescribe==""||monthAverPay==""||payDescribe==""||houseValue==""||carValue==""||joinCompanyName==""||joinCompanyResource==""||otherResourceDescribe==""){
    			Alert("内容信息填写不完整!");
    		    return;
    		}
    	}
	}
	var form=document.getElementById("list4");
	form.action=path+"user/list4?flag1="+mark;
	form.submit();
}
function submitCheck5(mark){
	var path=document.getElementById("path").value;
	var houseAddress=document.getElementById("houseAddress").value;
	var buildingArea=document.getElementById("buildingArea").value;
	var buildingYear=document.getElementById("buildingYear").value;
	var ownerOne=document.getElementById("ownerOne").value;
	var ownerOneShare=document.getElementById("ownerOneShare").value;
	var ownerTwo=document.getElementById("ownerTwo").value;
    var ownerTwoShare=document.getElementById("ownerTwoShare").value;
	var loanYear=document.getElementById("loanYear").value;
	var monthCont=document.getElementById("monthCont").value;
	var oweLoanBala=document.getElementById("oweLoanBala").value;
	var mortgageContact=document.getElementById("mortgageContact").value;
	var contributionStatus_0=document.getElementById("contributionStatus_0");
	var contributionStatus_1=document.getElementById("contributionStatus_1");
	var boo1 = false;
	if(mark=='0'){
//		if( houseAddress=="" || buildingArea=="" || buildingYear=="" || loanYear=="" || monthCont=="" || oweLoanBala=="" || mortgageContact=="" ){
//			Alert("内容信息填写不完整!");
//		    return;
//		}else if ((ownerOneShare=="" && ownerOne=="") || (ownerTwoShare=="" && ownerTwo=="")){
//			Alert("内容信息填写不完整!");
//		    return;
//		}
//		if(){
//			
//		}
		if(contributionStatus_0.checked==true){
			if( houseAddress=="" || buildingArea=="" || buildingYear=="" || loanYear=="" || monthCont=="" || oweLoanBala=="" || mortgageContact=="" || (ownerTwoShare=="" && ownerOneShare=="") || (ownerTwo=="" && ownerOne=="")){
				Alert("内容信息填写不完整!");
			    return;
			}
		}
		if(contributionStatus_1.checked==true){
			if( houseAddress=="" || buildingArea=="" || buildingYear=="" || (ownerTwoShare=="" && ownerOneShare=="") || (ownerTwo=="" && ownerOne=="")){
				Alert("内容信息填写不完整!");
			    return;
			}
		}
	}
	var form=document.getElementById("list5");
	form.action=path+"user/list5?flag1="+mark;
	form.submit();
}
function submitCheck6(){
	var path=document.getElementById("path").value;
	var cosignerOne=document.getElementById("cosignerOne").value;
	var cosignerOneTip=document.getElementById("cosignerOneTip");
//	if(cosignerOne==""){
//		//cosignerOneTip.innerHTML="第一联保人不能为空";
//		Alert("内容信息填写不完整！");
//		return;
//	}else{
//		cosignerOneTip.innerHTML="";
//	}
	var cosignerOnePhone=document.getElementById("cosignerOnePhone").value;
	var cosignerOnePhoneTip=document.getElementById("cosignerOnePhoneTip");
//	if(cosignerOnePhone==""){
//		//cosignerOnePhoneTip.innerHTML="第一联保人电话不能为空";
//		Alert("内容信息填写不完整！");
//		return false;
//	}else{
//		cosignerOnePhoneTip.innerHTML="";
//	}
	var cosignerTwo=document.getElementById("cosignerTwo").value;
	var cosignerTwoTip=document.getElementById("cosignerTwoTip");
//	if(cosignerTwo==""){
//		//cosignerTwoTip.innerHTML="第二联保人不能为空";
//		Alert("内容信息填写不完整！");
//		return;
//	}else{
//		cosignerTwoTip.innerHTML="";
//	}
	var cosignerTwoPhone=document.getElementById("cosignerTwoPhone").value;
	var cosignerTwoPhoneTip=document.getElementById("cosignerTwoPhoneTip");
//	if(cosignerTwoPhone==""){
//		//cosignerTwoPhoneTip.innerHTML="第二联保人电话不能为空";
//		Alert("内容信息填写不完整！");
//		return false;
//	}else{
//		cosignerTwoPhoneTip.innerHTML="";
//	}
	if((cosignerOne!="" && cosignerOnePhone!="")||(cosignerTwo!="" && cosignerTwoPhone!="")){
		var form=document.getElementById("list6");
		form.action=path+"user/list6";
		form.submit();
	}else{
		Alert("内容信息填写不完整！");
		return false;
	}
	
}
window.onload = function(e) {
	var  $m=jQuery.noConflict();
	var mark=document.getElementById("mark").value;
	if(mark=='2'){
		secBoard('hotnews_caption','list',2);
	}else if(mark=='3'){
		secBoard('hotnews_caption','list',3);
	}else if(mark=='4'){
		secBoard('hotnews_caption','list',4);
	}else if(mark=='5'){
		secBoard('hotnews_caption','list',5);
	}else if(mark=='6'){
		secBoard('hotnews_caption','list',6);
	}else if(mark=='1'){
		secBoard('hotnews_caption','list',1);
	}
	$m("#ID1").show();
}

