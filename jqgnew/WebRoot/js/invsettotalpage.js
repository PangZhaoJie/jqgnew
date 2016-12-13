
function getJSONData(currentPage,pageSize) {
	var path=document.getElementById("path").value;
	var url = path+"tender/tenderlist4.action?currentPage="+currentPage+"&pageSize="+pageSize+"&1=" +Math.random();
	$.ajax( {    
	    url:url,// 跳转到 action  
	    type:'post',    
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {
			
			var cp = data.currentPage;
			var tp = data.totalPage;
		
			var pagetable = jQuery("#table");
			pagetable.empty();
			str = '<tbody><tr class="th1"><td width="120">借款标号</td><td width="110">借入人</td><td width="115">投标日期</td><td width="110">借款金额</td><td width="80">年/日利率</td><td width="80">借款期限</td><td width="120">我的投资金额</td><td width="110">预期本息</td></tr>';
			var jsonRoot = data.jsonRoot;
			for(var i = 0; i < jsonRoot.length; i++) {
				str += '<tr><td><a href='+path+'/tender/totender.action?lssuingId='+jsonRoot[i].lssuingId+'>'+ jsonRoot[i].lssuingNum + '</a></td><td>'
					   + decodeURI(jsonRoot[i].userName, "utf-8") +"</td><td>"
					   + decodeURI(jsonRoot[i].time, "utf-8") +"</td><td>"
					   + decodeURI(jsonRoot[i].borrowMoney, "utf-8") +"</td><td>"
					   + decodeURI(jsonRoot[i].rate, "utf-8") +"%</td><td>"
					   + decodeURI(jsonRoot[i].perTime, "utf-8") +"</td><td>"
					   + decodeURI(jsonRoot[i].money, "utf-8") +"</td><td>"
					   + decodeURI(jsonRoot[i].benxi, "utf-8") +"</td></tr>";
			        	
			}
		
			pagetable.html(str+"</tbody>");
		
			if(tp !=1&&tp !=0)
			{
			
			document.getElementById("page4").style.display="";
		     jQuery("#currentPage4").text("第"+cp+"页");
	         jQuery("#totalPage4").text("共"+tp+"页");
		     jQuery("#firstPage4").attr("href","javascript:getJSONData(1,10)");
		     jQuery("#prevPage4").attr("href","javascript:getJSONData("+(parseInt(cp)-1)+",10)");
		     jQuery("#nextPage4").attr("href","javascript:getJSONData("+(parseInt(cp)+1)+",10)");
		     jQuery("#lastPage4").attr("href","javascript:getJSONData("+(parseInt(tp))+",10)");
			}

		},    
	     error : function() {  
	     }    
	});  
	
}
	getJSONData(1,10);  
function getJSONData1(currentPage,pageSize) {
	var path=document.getElementById("path").value;
	var url = path+"tender/tenderlist3.action?currentPage="+currentPage+"&pageSize="+pageSize;
	
	$.ajax( {    
	    url:url,// 跳转到 action  
	    type:'post',    
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {
			var cp = data.currentPage;
			var tp = data.totalPage;
		
			var pagetable = jQuery("#table1");
			pagetable.empty();
			str = '<tbody><tr class="th1"> <td width="120">借款标号</td><td width="100">借入人</td><td width="120">投资金额 </td><td width="120">已还本息</td><td width="80">年/日利率</td><td width="220">已还/总期数(还款期) </td><td width="80">备注</td></tr>';
			var jsonRoot = data.jsonRoot;
			if(tp !=1&&tp !=0)
			{
		
			document.getElementById("page3").style.display="";
		     jQuery("#currentPage3").text("第"+cp+"页");
	         jQuery("#totalPage3").text("共"+tp+"页");
		     jQuery("#firstPage3").attr("href","javascript:getJSONData1(1,10)");
		     jQuery("#prevPage3").attr("href","javascript:getJSONData1("+(parseInt(cp)-1)+",10)");
		     jQuery("#nextPage3").attr("href","javascript:getJSONData1("+(parseInt(cp)+1)+",10)");
		     jQuery("#lastPage3").attr("href","javascript:getJSONData1("+(parseInt(tp))+",10)");
			}
		    
			for(var i = 0; i < jsonRoot.length; i++) {
				str += '<tr><td><a href='+path+'/tender/totender.action?lssuingId='+jsonRoot[i].lssuingId+'>'+ jsonRoot[i].lssuingNum + '</a></td><td>'
					   + decodeURI(jsonRoot[i].userName, "utf-8") +"</td><td>"
					   + decodeURI(jsonRoot[i].money, "utf-8") +"</td><td>"
					   + decodeURI(jsonRoot[i].recordInterest , "utf-8") +"</td><td>"
					   + decodeURI(jsonRoot[i].rate, "utf-8") +"%</td><td>"
					   + decodeURI(jsonRoot[i].records, "utf-8") +"/"+ decodeURI(jsonRoot[i].records1, "utf-8") +"("+ decodeURI(jsonRoot[i].time, "utf-8") +")"+"<a onclick='secBoard(&quot;hotnews_caption&quot;,&quot;list&quot;,6);list("+decodeURI(jsonRoot[i].href, "utf-8") + ",1,10)'>（详情）</a>"+"</td><td>"
					   + decodeURI(jsonRoot[i].remark, "utf-8") +"</td></tr>";
			        	
			}
			pagetable.html(str+"</tbody>");
		
			
		},    
	     error : function() {  
	     }    
	});  
}

	getJSONData1(1,10); 
function getJSONData2(currentPage,pageSize) {
	var path=document.getElementById("path").value;
	var url = path+"tender/tenderlist2.action?currentPage="+currentPage+"&pageSize="+pageSize;
	
	$.ajax( {    
	    url:url,// 跳转到 action  
	    type:'post',    
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {
			var cp = data.currentPage;
			var tp = data.totalPage;
		
			var pagetable = jQuery("#table2");
			pagetable.empty();
			str = '<tbody><tr class="th1"><td width="160">借款标号</td><td width="160">借入人</td><td width="160">年/日利率 </td><td width="180">已收本金</td><td width="180">已收利息</td><td width="80">备注</td></tr>';
			var jsonRoot = data.jsonRoot;
			if(tp !=1&&tp !=0)
			{
			
			document.getElementById("page2").style.display="";
		     jQuery("#currentPage2").text("第"+cp+"页");
	         jQuery("#totalPage2").text("共"+tp+"页");
		     jQuery("#firstPage2").attr("href","javascript:getJSONData2(1,10)");
		     jQuery("#prevPage2").attr("href","javascript:getJSONData2("+(parseInt(cp)-1)+",10)");
		     jQuery("#nextPage2").attr("href","javascript:getJSONData2("+(parseInt(cp)+1)+",10)");
		     jQuery("#lastPage2").attr("href","javascript:getJSONData2("+(parseInt(tp))+",10)");
			}
		
			for(var i = 0; i < jsonRoot.length; i++) {
				str += '<tr><td><a href='+path+'/tender/totender.action?lssuingId='+jsonRoot[i].lssuingId+'>'+ jsonRoot[i].lssuingNum + '</a></td><td>'
					   + decodeURI(jsonRoot[i].userName, "utf-8") +"</td><td>"
					   + decodeURI(jsonRoot[i].rate, "utf-8") +"%</td><td>"
					   + decodeURI(jsonRoot[i].money, "utf-8") +"</td><td>"
				
					   + decodeURI(jsonRoot[i].lixi, "utf-8") +"</td><td>" 
					   +decodeURI(jsonRoot[i].remark, "utf-8") +"</td></tr>";
			        	
			}
			pagetable.html(str+"</tbody>");
			
		},    
	     error : function() {  
	     }    
	}); 
	
}

	getJSONData2(1,10); 
function getJSONData3(currentPage,pageSize) {
	var path=document.getElementById("path").value;
	var url = path+"tender/tenderlist1.action?currentPage="+currentPage+"&pageSize="+pageSize;
	
	$.ajax( {    
	    url:url,// 跳转到 action  
	    type:'post',    
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {
			var cp = data.currentPage;
			var tp = data.totalPage;
			var pagetable = jQuery("#table3");
			pagetable.empty();
			str = '<tr class="th1"><td width="160">借款标号</td><td width="160">借入人</td><td width="160">年/日利率 </td><td width="180">已收本金</td><td width="180">已收利息</td><td>备注</td></tr>';
			var jsonRoot = data.jsonRoot;
			if(tp !=1&&tp !=0)
			{
			
			document.getElementById("page1").style.display="";
		     jQuery("#currentPage1").text("第"+cp+"页");
	         jQuery("#totalPage1").text("共"+tp+"页");
		     jQuery("#firstPage1").attr("href","javascript:getJSONData3(1,10)");
		     jQuery("#prevPage1").attr("href","javascript:getJSONData3("+(parseInt(cp)-1)+",10)");
		     jQuery("#nextPage1").attr("href","javascript:getJSONData3("+(parseInt(cp)+1)+",10)");
		     jQuery("#lastPage1").attr("href","javascript:getJSONData3("+(parseInt(tp))+",10)");
			}
		
			for(var i = 0; i < jsonRoot.length; i++) {
				str += '<tr><td><a href='+path+'/tender/totender.action?lssuingId='+jsonRoot[i].lssuingId+'>'+ jsonRoot[i].lssuingNum + '</a></td><td>'
					   + decodeURI(jsonRoot[i].userName, "utf-8") +"</td><td>"
					   + decodeURI(jsonRoot[i].rate, "utf-8") +"%</td><td>"
					   + decodeURI(jsonRoot[i].money, "utf-8") +"</td><td>"
				
					   + decodeURI(jsonRoot[i].lixi, "utf-8") +"</td></td>" 
					   +decodeURI(jsonRoot[i].remark, "utf-8") +"</td></tr>";
			        	
			}

			pagetable.html(str);
			
		},    
	     error : function() {  
	     }    
	});
}
  
getJSONData3(1,10); 
function list(href,currentPage,pageSize) {
	document.getElementById("huankuan").style.display="";
	document.getElementById("list_6").style.display="";
	var path=document.getElementById("path").value;
	var url = path+"tender/recordlist.action?tenderId=" +href+ "&currentPage="+currentPage+"&pageSize="+pageSize;
	
	$.ajax( {    
	    url:url,// 跳转到 action  
	    type:'post',    
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {
			var cp = data.currentPage;
			var tp = data.totalPage;
		  
			var pagetable = jQuery("#table4");
			pagetable.empty();
			str = '<tr class="th1"><td width="160">应收日期</td><td width="160">应收本金</td><td width="160">应收利息 </td><td width="160">逾期天数</td><td width="160">逾期利息 </td><td width="180">实收本息</td><td width="180">收款状态</td><td width="180">当前/总（期）</td></tr>';
			var jsonRoot = data.jsonRoot;
			if(tp !=1&&tp !=0)
			{
			
			document.getElementById("page").style.display="";
		     jQuery("#currentPage").text("第"+cp+"页");
	         jQuery("#totalPage").text("共"+tp+"页");
		     jQuery("#firstPage").attr("href","javascript:list(1,10)");
		     jQuery("#prevPage").attr("href","javascript:list("+(parseInt(cp)-1)+",10)");
		     jQuery("#nextPage").attr("href","javascript:list("+(parseInt(cp)+1)+",10)");
		     jQuery("#lastPage").attr("href","javascript:list("+(parseInt(tp))+",10)");
			}
		
			for(var i = 0; i < jsonRoot.length; i++) {
				str += '<tr><td>'+ jsonRoot[i].time + '</td><td>'
					   + decodeURI(jsonRoot[i].RecordMoney, "utf-8") +"</td><td>"
					   + decodeURI(jsonRoot[i].rate, "utf-8") +"</td><td>"
					   + decodeURI(jsonRoot[i].overdays, "utf-8") +"</td><td>"
					   + decodeURI(jsonRoot[i].overInterest, "utf-8") +"</td><td>"
					   + decodeURI(jsonRoot[i].RecordInterest, "utf-8") +"</td><td>"
					   + decodeURI(jsonRoot[i].state, "utf-8") +"</td><td>"
					   + decodeURI(jsonRoot[i].size1, "utf-8") +"/"+ decodeURI(jsonRoot[i].size, "utf-8") +"</td></tr>";
			        	
			}
			pagetable.html(str);
			
		},    
	     error : function() {  
	     }    
	});
	
}