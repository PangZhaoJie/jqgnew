 
function content(){
	  document.getElementById("logins").style.display="none";
	  document.getElementById("content").style.display="none";
	  document.getElementById("contentSize").style.display="none";
}
$(document).ready(function(){
	 var path=$("#path").val();
	 var uservip = $("#uservip").val();	 
	$("#f_btn").click(function(){
		$("#f_btn").attr("disabled", "disabled"); //使按钮不能被点击 
		var content = $("#textarea").val();
		//获取留言
		var data = "lssuingId="+$("#lssuingId").val()+"&message="+$("#textarea").val();
		 if(uservip == null || uservip == ""){
			   document.getElementById("logins").style.display="";
			   $("#f_btn").removeAttr("disabled");
			   return false;
		   }
		 if(content == null || content == ""){
			 document.getElementById("content").style.display="";
			 $("#f_btn").removeAttr("disabled");
			   return false;
		 }
		 if(content.length > 200){
			 document.getElementById("contentSize").style.display="";
			 $("#f_btn").removeAttr("disabled");
			   return false;
		 }
     
     
		$.ajax({
			//使用json，/json是他的命名空间
			url:path+"json/createTenderWords", 
			type:"post",
			data:data,//使用这个方法可以使表单里面的内容自动提交上去
			dataType:"json",//返回类型为json对象
			success:function(data){
				if(data.result=="success"){
					getJSONDatas(1,3);  
				}
				$("#f_btn").removeAttr("disabled"); //使按钮能够被点击
			}
		});
	});
	
	  var state = $("#state").val();	 
	   var passwordnone = $("#passwordnone").val();	 
	if (passwordnone == 1 && state == 2){
		document.getElementById("opsd").style.display="";
	}
	
  if ( state == 0){
		document.getElementById("moneyState").style.display="none";
		document.getElementById("textState").style.display="none";
		document.getElementById("minmaxState").style.display="none";
		document.getElementById("invest_bt").style.display="none";
		document.getElementById("0State").style.display="";
	}if (state == 1){
		document.getElementById("moneyState").style.display="none";
		document.getElementById("textState").style.display="none";
		document.getElementById("minmaxState").style.display="none";
		document.getElementById("invest_bt").style.display="none";
		document.getElementById("1State").style.display="";
	}if (state == 3){
		document.getElementById("moneyState").style.display="none";
		document.getElementById("textState").style.display="none";
		document.getElementById("invest_bt").style.display="none";
		document.getElementById("3State").style.display="";
	}if (state == 4){
		document.getElementById("moneyState").style.display="none";
		document.getElementById("textState").style.display="none";
		document.getElementById("invest_bt").style.display="none";
		document.getElementById("minmaxState").style.display="none";
		document.getElementById("4State").style.display="";
	}if (state == 6){
		document.getElementById("moneyState").style.display="none";
		document.getElementById("textState").style.display="none";
		document.getElementById("invest_bt").style.display="none";
		document.getElementById("minmaxState").style.display="none";
		document.getElementById("5State").style.display="";
	}
	 
	$("#invest_bt").click(function(){
		   var path=$("#path").val();
		   var tip = $("#tip").val();	 //投标金额
		   var minIDnone = $("#minIDnone").val();	 
		   var maxIDnone = $("#maxIDnone").val();	 
		   var moneyIDnone = $("#moneyIDnone").val();	 
		   var balanceIdnone = $("#balanceIdnone").val();	
		   var opsdIDnone = $("#opsdIDnone").val();	 
		   var psd = $("#psd").val();	 
		  
		   var moneyLimitIdnone = $("#moneyLimitIdnone").val();	
		   var lmoneyLimitIdnone = $("#lmoneyLimitIdnone").val();	 
		   var tenderLimit= $("#tenderLimit").val();
		   if(uservip == null || uservip == ""){
			   document.getElementById("login").style.display="";
			   return false;
		   }
			if (passwordnone == 1){
		 		if (psd != opsdIDnone)
		 			{
		 			    document.getElementById("opsdId").style.display="";
		 			    return false;
		 			}
		 	}
			if (tenderLimit == 1){
		 		if (lmoneyLimitIdnone*1  > moneyLimitIdnone*1)
		 			{
		 			    document.getElementById("moneyLimitId").style.display="";
		 			    return false;
		 			}
		 	}
			 if(tip == null || tip == ""){
				   document.getElementById("tipId").style.display="";
				   return false;
			   }
			 if(tip % 1 != 0 )
				 {
				     document.getElementById("tip50Id").style.display="";
				     return false;
				 }
			 if(parseInt(balanceIdnone)> parseInt(tip)){
				 if(parseInt(minIDnone)>parseInt(tip))
				 	{
			     		document.getElementById("minId").style.display="";
			     		return false;
				 	}
			 }
		    if(maxIDnone!='0')
		    	{
		    	
		    	if (parseInt(tip) > parseInt(maxIDnone))
				{
			     	document.getElementById("maxId").style.display="";
				    return false;
				}
		    	}
		
			if (parseInt(tip) >parseInt(balanceIdnone))
			{
		     	document.getElementById("balanceId").style.display="";
			    return false;
			}
			if (parseInt(tip) > parseInt(moneyIDnone))
			{
		     	document.getElementById("moneyID").style.display="";
			    return false;
			}
		});
});

function getJSONDatas(currentPage,pageSize) {
	var url = "tender/findTenderWords?currentPage="+currentPage+"&pageSize="+pageSize+"&1=" +Math.random() ;
	$.getJSON(url,function(data){
		var cp = data.currentPage;
		var tp = data.totalPage;
		var $li= $("#tenderMessage");
		$li.empty();
		 str='  ';
		var jsonRoots = data.jsonRoots;
		for(var i = 0; i < jsonRoots.length; i++) {
			str += '<h5 class="name"><a href="/" title="" target="_blank">'+decodeURI(jsonRoots[i].userName, "utf-8")  +
	     	'</a> <span class="fr time">' +decodeURI(jsonRoots[i].time, "utf-8")+
			'</span></h5><div class="cont">' +decodeURI(jsonRoots[i].message, "utf-8")+ '</div>';
		
		}
		$li.html(str);
	
		if(tp != 1)
			{
		
			document.getElementById("messagePage").style.display="";
		     $("#currentPages").text("第"+cp+"页");
	         $("#totalPages").text("共"+tp+"页");
		     $("#firstPages").attr("href","javascript:getJSONDatas(1,3)");
		     $("#prevPages").attr("href","javascript:getJSONDatas("+(parseInt(cp)-1)+",3)");
		     $("#nextPages").attr("href","javascript:getJSONDatas("+(parseInt(cp)+1)+",3)");
		     $("#lastPages").attr("href","javascript:getJSONDatas("+(parseInt(tp))+",3)");
			}
		else{
			document.getElementById("messagePage").style.display="none";
		} 
	});
}
$(function() {  
	getJSONDatas(1,3);  
});

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

$(function(){
	$(".select").each(function(){
		var s=$(this);
		var z=parseInt(s.css("z-index"));
		var dt=$(this).children("dt");
		var dd=$(this).children("dd");
		var _show=function(){dd.slideDown(200);dt.addClass("cur");s.css("z-index",z+1);};   //展开效果
		var _hide=function(){dd.slideUp(200);dt.removeClass("cur");s.css("z-index",z);};    //关闭效果
		dt.click(function(){dd.is(":hidden")?_show():_hide();});
		dd.find("a").click(function(){dt.html($(this).html());_hide();});     //选择效果（如需要传值，可自定义参数，在此处返回对应的"value"值 ）
		$("body").click(function(i){ !$(i.target).parents(".select").first().is(s) ? _hide():"";});
	});
});
function text(){
	$("#tip").attr("value",'');
	document.getElementById("login").style.display="none";
	document.getElementById("opsdId").style.display="none";
	document.getElementById("moneyLimitId").style.display="none";
	   document.getElementById("tipId").style.display="none";
	   document.getElementById("tip50Id").style.display="none";
	   document.getElementById("minId").style.display="none";
	   document.getElementById("maxId").style.display="none";
	   document.getElementById("balanceId").style.display="none";
	   document.getElementById("moneyID").style.display="none";
}