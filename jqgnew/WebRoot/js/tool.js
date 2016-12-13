
var   $borrowtool= jQuery.noConflict();

function checkradio(o,x){
	document.getElementsByName("inptB")[x-1].checked=o.checked;
	
	}
function days(){
	var  ss = document.getElementById('return');
	ss[2].selected = true;//选中
}
function year(){
	var away =  $borrowtool("#awayId").val();
    $borrowtool("#awayId").attr("value",changeTwoDecimal(away*360));
	}


function day(){
	var away =  $borrowtool("#awayId").val();

	$borrowtool("#awayId").attr("value",changeTwoDecimal(away/360));
	}

function changeTwoDecimal(floatvar)
{
var f_x = parseFloat(floatvar);
if (isNaN(f_x))
{
alert('function:changeTwoDecimal->parameter error');
return false;
}
var f_x = Math.round(floatvar*100)/100;
return f_x;
}



function count(){
	var	money =parseInt($borrowtool("#money").val());
	var   timeday =  $borrowtool("input[name='inptA']:checked").val();
	var   awayrate = $borrowtool("input[name='inptB']:checked").val();

	var  away = $borrowtool("#away").val();
	var  awayId = changeTwoDecimal($borrowtool("#awayId").val());
	var   returnway =parseInt($borrowtool("#return").val());
	var   time =parseInt($borrowtool("#time").val());
	
	 var money1;
	 var rate;
	 if(timeday==0&&awayrate==0)
		 {
         money1 = money +money*time*awayId/12/100+money*away/100;
          rate = money1 - money;
         document.getElementById("resultnone").style.display="";
	     $borrowtool("#total").text("￥" +money1.toFixed(2));
	     $borrowtool("#rate").text("￥" +rate.toFixed(2));
		 }
	 if(timeday==0&&awayrate==1)
	 {
     money1 = money +money*time*awayId*360/12/100+money*away/100;
     rate = money1 - money;
     document.getElementById("resultnone").style.display="";
     $borrowtool("#total").text("￥" +money1.toFixed(2));
     $borrowtool("#rate").text("￥" +rate.toFixed(2));
	 }
	 if(timeday==1&&awayrate==0)
	 {
     money1 = money +money*time*awayId/360/100+money*away/100;
     rate = money1 - money;
     document.getElementById("resultnone").style.display="";
     $borrowtool("#total").text("￥" +money1.toFixed(2));
     $borrowtool("#rate").text("￥" +rate.toFixed(2));
	 }
	 if(timeday==1&&awayrate==1)
	 {
     money1 = money +money*time*awayId/100+money*away/100;
     rate = money1 - money;
     document.getElementById("resultnone").style.display="";
     $borrowtool("#total").text("￥" +money1.toFixed(2));
     $borrowtool("#rate").text("￥" +rate.toFixed(2));
	 }
	 if (returnway==1)
	 {
		 document.getElementById("counter_title").style.display="";
			var  $borrowtooltable =  $borrowtool("#table");
			 $borrowtooltable.empty();
			str ='<thead> <tr id="head-color" ><th>月份</th><th>月还款额</th><th>月还本金</th><th>月还利息</th><th>余额</th></tr></thead>'
				var  	 balance = money1;
				for(var i = 1; i < time+1; i++) {
		             timemoney = parseInt(money)/time+parseInt(rate )/time ;
		             balance = balance - timemoney;
				str += '<tr ><td>'+ i  + "</td><td>"
					   + timemoney.toFixed(2)  +"</td><td>"
					   +(parseInt(money)/time).toFixed(2)  + "</td><td>"
					   + (parseInt(rate )/time).toFixed(2)  + "</td><td>"
                      + balance.toFixed(2)+ "</td></tr>  ";
			
			}
			 $borrowtooltable.html(str);
				
	 }
	 if (returnway==2)
	 {
		 document.getElementById("counter_title").style.display="";
			var  $borrowtooltable =  $borrowtool("#table");
			 $borrowtooltable.empty();
			str ='<thead> <tr id="head-color" ><th>月份</th><th>月还款额</th><th>月还本金</th><th>月还利息</th><th>余额</th></tr></thead>'
				var  	 balance = money1;
				for(var i = 1; i < time; i++) {
		             
		             balance = balance - parseInt(rate )/time;
				str += '<tr ><td>'+ i  + "</td><td>"
					   +  (parseInt(rate )/time).toFixed(2) +"</td><td>"
					   +0  + "</td><td>"
					   + (parseInt(rate )/time ).toFixed(2) + "</td><td>"
                   + balance.toFixed(2)+ "</td></tr>  ";
			
			}
				var  		money2 = money+parseInt(rate )/time;
				str += '<tr ><td>'+ time  + "</td><td>"
				   +  money2.toFixed(2) +"</td><td>"
				   +money.toFixed(2) + "</td><td>"
				   + (parseInt(rate )/time).toFixed(2)  + "</td><td>"
               + 0+ "</td></tr>  ";
			 $borrowtooltable.html(str);
	 }
	 if (returnway==3)
	 {
		 document.getElementById("counter_title").style.display="none";
		
	 }
}