function plus(id){
	var cnum = parseInt($("#tnum_"+id).val());
	
	cnum++;
	if(!cnum){
		cnum = 1;
	}
	$("#tnum_"+id).val(cnum+'份');
}
function minus(id){
	var cnum = parseInt($("#tnum_"+id).val());
	cnum=(cnum-1)>0?(cnum-1):1;
	$("#tnum_"+id).val(cnum+'份');
}

function round2(floatData,i){
	var i=i+1;
	var floatStr = (floatData)+"";
	var index = floatStr.indexOf(".");
	if(index!=-1){
			return floatStr.substring(0,(index+i));	
	}
	else
		return floatStr;
}


/*企业直投流程*/

var T_transfer_num = 0;
var T_month_min = 0;
var T_month_max = 0;

function sumTMoney(obj){
	obj.value=obj.value.replace(/[^0-9]/g,'');
	var tnum = parseInt($("#transfer_invest_num").val());
	var per = parseInt($("#per_transfer").val());
	var total = tnum*per;
		total = isNaN(total)?0:total;
	$("#total_transfer_money").html(total);
}

function showTMoney(rate,reward_rate,increase_rate,month1){
	var tnum = parseInt($("#transfer_invest_num").val());
	var per = parseInt($("#per_transfer").val());
	var month = parseInt($("#transfer_invest_month").val());
		month = isNaN(month)?0:month;
	var total = tnum*per;
		total = isNaN(total)?0:total;
	
	var interest_rate = parseFloat(rate)+month*parseFloat(increase_rate);
	var interest = parseFloat(interest_rate)*total*month/(12*100);
	var reward = parseFloat(reward_rate)*total/100;
	$("#year_interest").html(interest_rate);
	$("#except_income").html("￥"+round2((interest+reward),2));
	$("#interest_income").html("￥"+round2(interest,2));
	$("#reward_income").html("￥"+round2(reward,2));
}

function T_PostData(id) {
	
	if($("#isuserviplogin").length>0){
		alert("请登录后投标");
		 window.location.href=$("#isuserviplogin").val(); 
		return false;
	}
	var cnum = parseInt($("#tnum_"+id).val());
	tnum = isNaN(cnum)?0:cnum;
	$("#buynum").attr("value",tnum);
	
	alert($("#buynum").val());
	if(tnum<1){
		alert("购买份数必须大于等于1份！");  
		return false;
	}
	var total = tnum*per;
		tendValue = isNaN(total)?0:total;
  var pin = $("#userPaypwd").val();
  var borrow_id = $("#lssuingId").val();
  if(pin==""){
	alert("请输入支付密码");  
	return false;
  }
  var T_transfer_num = $("#T_transfer_num").val();
  if(T_transfer_num!=0 && tnum>T_transfer_num){
	  alert("本标还能认购最大份数为"+T_transfer_num+"份，请重新输入认购份数");  
	return false;
  }
}

function ischarge(d){
	if(d===true) window.location.href="/member/charge#fragment-1";
}
function isinvest(d){
	if(d===true) document.forms.investForm.submit();
}




function getJSONData(currentPage,pageSize) {
	var url = "tender/tenderByIdPage.action?currentPage="+currentPage+"&pageSize="+pageSize+"&1=" +Math.random();
	$.getJSON(url,function(data){
		var cp = data.currentPage;
		var tp = data.totalPage;
		var $table = $("#listTable");
		$table.empty();
		str = '<tr class="header">  <td >投标记录</td><td>投标人</td><td>当前利率</td> <td>投标金额</td><td>投标时间</td><td>投标状态</td></tr>';
		var jsonRoot = data.jsonRoot;
		for(var i = 0; i < jsonRoot.length; i++) {
			str += "<tr><td>"+ jsonRoot[i].tenderId + "</td><td>"
				   + decodeURI(jsonRoot[i].userName, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].rate, "utf-8") + "%</td><td>"
				   + decodeURI(jsonRoot[i].money, "utf-8") + "</td><td>"
				   + decodeURI(jsonRoot[i].time, "utf-8") + "</td><td>"
				   + jsonRoot[i].state + "</td></tr>";
		}
		$table.html(str);
		if(tp !=1)
		{
			
		document.getElementById("pageroot").style.display="";
	     $("#currentPage").text("第"+cp+"页");
         $("#totalPage").text("共"+tp+"页");
	     $("#firstPage").attr("href","javascript:getJSONData(1,10)");
	     $("#prevPage").attr("href","javascript:getJSONData("+(parseInt(cp)-1)+",10)");
	     $("#nextPage").attr("href","javascript:getJSONData("+(parseInt(cp)+1)+",10)");
	     $("#lastPage").attr("href","javascript:getJSONData("+(parseInt(tp))+",10)");
		}
	else{
		document.getElementById("pageroot").style.display="none";
	}
	});
}
$(function() {  
	getJSONData(1,10);  
});
$(document).ready(function(){
	var state = $("#state").val();
	  if ( state == 0){
		  	document.getElementById("invest_bt").style.display="none";
			document.getElementById("0State").style.display="";
		}if (state == 1){
			document.getElementById("invest_bt").style.display="none";
			document.getElementById("1State").style.display="";
		}if (state == 3){
			document.getElementById("invest_bt").style.display="none";
			document.getElementById("3State").style.display="";
		}if (state == 4){
			document.getElementById("invest_bt").style.display="none";
			document.getElementById("4State").style.display="";
		}
	
});
function text(){
	var tip=$("#tip").val();
	if(tip==null || tip==""){
		document.getElementById("tipId").style.display="";
	}
}
