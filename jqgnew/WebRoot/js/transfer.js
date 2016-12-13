var $p =jQuery.noConflict();
function getJSONData5(currentPage,pageSize) {
	var path=document.getElementById("path").value;
	var url = path+"transfer/transferlist.action?currentPage="+currentPage+"&pageSize="+pageSize+"&1=" +Math.random();
	$p.ajax( {    
	    url:url,// 跳转到 action  
	    type:'post',    
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {
			
			var cp = data.currentPage;
			var tp = data.totalPage;
			var pagetable = jQuery("#table5");
			pagetable.empty();
			str = '<tbody><tr class="th1"><td width="120">借款标号</td><td width="110">借入人</td><td width="115">投标日期</td><td width="80">年/日利率</td><td width="80">借款期限</td><td width="120">我的投资金额</td><td width="110">预期本息</td><td width="110">操作</td></tr>';
			var jsonRoot = data.jsonRoot;
			for(var i = 0; i < jsonRoot.length; i++) {
				str += '<tr><td>'+ jsonRoot[i].lssuingNum + '</td><td>'
					   + decodeURI(jsonRoot[i].userName, "utf-8") +"</td><td>"
					   + decodeURI(jsonRoot[i].time, "utf-8") +"</td><td>"
					   + decodeURI(jsonRoot[i].rate, "utf-8") +"%</td><td>"
					   + decodeURI(jsonRoot[i].perTime, "utf-8") +"</td><td>"
					   + decodeURI(jsonRoot[i].money, "utf-8") +"</td><td>"
					   + decodeURI(jsonRoot[i].benxi, "utf-8") +"</td><td>"
					   + '<a href='+path+'/transfer/transferJump?tenderId='+jsonRoot[i].tenderId+'>转让</a>' +"</td></tr>";
			        	
			}
			pagetable.html(str+"</tbody>");
		
			if(tp !=1&&tp !=0)
			{
			
			document.getElementById("page5").style.display="";
			$p("#currentPage5").text("第"+cp+"页");
			$p("#totalPage5").text("共"+tp+"页");
			$p("#firstPage5").attr("href","javascript:getJSONData5(1,10)");
			$p("#prevPage5").attr("href","javascript:getJSONData5("+(parseInt(cp)-1)+",10)");
			$p("#nextPage5").attr("href","javascript:getJSONData5("+(parseInt(cp)+1)+",10)");
			$p("#lastPage5").attr("href","javascript:getJSONData5("+(parseInt(tp))+",10)");
			}

		},    
	     error : function() {  
	     }    
	});  
	
}
	getJSONData5(1,10); 



function getJSONData1(currentPage,pageSize) {
	var path=document.getElementById("path").value;
	var url = path+"transfer/transferlist1.action?currentPage="+currentPage+"&pageSize="+pageSize+"&1=" +Math.random();
	$p.ajax( {    
	    url:url,// 跳转到 action  
	    type:'post',    
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {
			
			var cp = data.currentPage;
			var tp = data.totalPage;
		
			var pagetable = jQuery("#table");
			pagetable.empty();
			str = '<tbody><tr class="th1"><td width="120">借款标号</td><td width="115">转让价格</td><td width="110">转让时间</td><td width="80">年/日利率</td><td width="110">待收本息</td><td width="110">操作</td></tr>';
			var jsonRoot = data.jsonRoot;
			for(var i = 0; i < jsonRoot.length; i++) {
				if(decodeURI(jsonRoot[i].isTransfer, "utf-8")=='0'){
				str += '<tr><td>'+ jsonRoot[i].lssuingNum + '</td><td>'
					   + decodeURI(jsonRoot[i].transferMoney, "utf-8") +"</td><td>"
					   + decodeURI(jsonRoot[i].transferTime, "utf-8") +"</td><td>"
					   + decodeURI(jsonRoot[i].rate, "utf-8") +"%</td><td>"
					   + decodeURI(jsonRoot[i].benxi, "utf-8") +"</td><td>"
					   + '<a>审核中</a>'+"</td></tr>";
				}else{
					str += '<tr><td>'+ jsonRoot[i].lssuingNum + '</td><td>'
					   + decodeURI(jsonRoot[i].transferMoney, "utf-8") +"</td><td>"
					   + decodeURI(jsonRoot[i].transferTime, "utf-8") +"</td><td>"
					   + decodeURI(jsonRoot[i].rate, "utf-8") +"%</td><td>"
					   + decodeURI(jsonRoot[i].benxi, "utf-8") +"</td><td>"
					   + '<a href="'+path+'/transfer/cancelTransfer?tenderId='+jsonRoot[i].tenderId+'">撤消</a>'+"</td></tr>";
				}
			        	
			}
		
			pagetable.html(str+"</tbody>");
		
			if(tp !=1&&tp !=0)
			{
			
			document.getElementById("page4").style.display="";
			$p("#currentPage4").text("第"+cp+"页");
			$p("#totalPage4").text("共"+tp+"页");
			$p("#firstPage4").attr("href","javascript:getJSONData1(1,10)");
			$p("#prevPage4").attr("href","javascript:getJSONData1("+(parseInt(cp)-1)+",10)");
			$p("#nextPage4").attr("href","javascript:getJSONData1("+(parseInt(cp)+1)+",10)");
			$p("#lastPage4").attr("href","javascript:getJSONData1("+(parseInt(tp))+",10)");
			}

		},    
	     error : function() {  
	     }    
	});  
	
}
	getJSONData1(1,10);
	
/**
 * 成功转让债权
 * @param currentPage
 * @param pageSize
 */
function getJSONData2(currentPage,pageSize) {
	var path=document.getElementById("path").value;
	var url = path+"transfer/transferlist2.action?currentPage="+currentPage+"&pageSize="+pageSize+"&1=" +Math.random();
	$p.ajax( {    
	    url:url,// 跳转到 action  
	    type:'post',    
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {
			var cp = data.currentPage;
			var tp = data.totalPage;
		
			var pagetable = jQuery("#table1");
			pagetable.empty();
			str = '<tbody><tr class="th1"> <td width="120">借款标号</td><td width="80">年/日利率</td><td width="120">债权总值 </td><td width="100">转让价格</td><td width="200">转让时间</td><td width="100">购买人</td><td width="70">备注</td></tr>';
			var jsonRoot = data.jsonRoot;
			if(tp !=1&&tp !=0)
			{
		
			document.getElementById("page3").style.display="";
			$p("#currentPage3").text("第"+cp+"页");
			$p("#totalPage3").text("共"+tp+"页");
			$p("#firstPage3").attr("href","javascript:getJSONData2(1,10)");
			$p("#prevPage3").attr("href","javascript:getJSONData2("+(parseInt(cp)-1)+",10)");
			$p("#nextPage3").attr("href","javascript:getJSONData2("+(parseInt(cp)+1)+",10)");
			$p("#lastPage3").attr("href","javascript:getJSONData2("+(parseInt(tp))+",10)");
			}
		    
			for(var i = 0; i < jsonRoot.length; i++) {
				str += '<tr><td>'+ jsonRoot[i].lssuingNum + '</td><td>'
					   + decodeURI(jsonRoot[i].rate, "utf-8") +"%</td><td>"
					   + decodeURI(jsonRoot[i].benxi, "utf-8") +"</td><td>"
					   + decodeURI(jsonRoot[i].transferMoney, "utf-8") +"</td><td>"
					   + decodeURI(jsonRoot[i].transferTime , "utf-8") +"</td><td>"
					   + decodeURI(jsonRoot[i].userName , "utf-8") +"</td><td>"
					   + decodeURI(jsonRoot[i].remark, "utf-8") +"</td></tr>";
			        	
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
	var url = path+"transfer/transferlist3.action?currentPage="+currentPage+"&pageSize="+pageSize+"&1=" +Math.random();
	$p.ajax( {    
	    url:url,// 跳转到 action  
	    type:'post',    
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {
			var cp = data.currentPage;
			var tp = data.totalPage;
		
			var pagetable = jQuery("#table2");
			pagetable.empty();
			str = '<tbody><tr class="th1"> <td width="120">借款标号</td><td width="80">年/日利率</td><td width="120">债权总值 </td><td width="100">购买价格</td><td width="200">购买时间</td><td width="100">转让人</td><td width="70">备注</td></tr>';
			var jsonRoot = data.jsonRoot;
			if(tp !=1&&tp !=0)
			{
			
			document.getElementById("page2").style.display="";
			$p("#currentPage2").text("第"+cp+"页");
			$p("#totalPage2").text("共"+tp+"页");
			$p("#firstPage2").attr("href","javascript:getJSONData3(1,10)");
			$p("#prevPage2").attr("href","javascript:getJSONData3("+(parseInt(cp)-1)+",10)");
			$p("#nextPage2").attr("href","javascript:getJSONData3("+(parseInt(cp)+1)+",10)");
			$p("#lastPage2").attr("href","javascript:getJSONData3("+(parseInt(tp))+",10)");
			}
		
			for(var i = 0; i < jsonRoot.length; i++) {
				str += '<tr><td>'+ jsonRoot[i].lssuingNum + '</td><td>'
					   + decodeURI(jsonRoot[i].rate, "utf-8") +"%</td><td>"
					   + decodeURI(jsonRoot[i].benxi, "utf-8") +"</td><td>"
					   + decodeURI(jsonRoot[i].transferMoney, "utf-8") +"</td><td>"
					   + decodeURI(jsonRoot[i].buyferTime, "utf-8") +"</td><td>"
					   
					   + decodeURI(jsonRoot[i].userName, "utf-8") +"</td><td>" 
					   +decodeURI(jsonRoot[i].remark, "utf-8") +"</td></tr>";
			        	
			}
			pagetable.html(str+"</tbody>");
			
		},    
	     error : function() {  
	     }    
	}); 
	
}

	getJSONData3(1,10); 
function getJSONData4(currentPage,pageSize) {
	var path=document.getElementById("path").value;
	var url = path+"transfer/transferlist4.action?currentPage="+currentPage+"&pageSize="+pageSize+"&1=" +Math.random();
	
	$p.ajax( {    
	    url:url,// 跳转到 action  
	    type:'post',    
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {
			var cp = data.currentPage;
			var tp = data.totalPage;
			var pagetable = jQuery("#table3");
			pagetable.empty();
			str = '<tr class="th1"><td width="200">借款标号</td><td width="160">年/日利率 </td><td width="180">待收本息</td><td width="100">撤消次数</td><td width="180">撤消时间</td><td width="70">备注</td></tr>';
			var jsonRoot = data.jsonRoot;
			if(tp !=1&&tp !=0)
			{
			
			document.getElementById("page1").style.display="";
			$p("#currentPage1").text("第"+cp+"页");
			$p("#totalPage1").text("共"+tp+"页");
			$p("#firstPage1").attr("href","javascript:getJSONData4(1,10)");
			$p("#prevPage1").attr("href","javascript:getJSONData4("+(parseInt(cp)-1)+",10)");
			$p("#nextPage1").attr("href","javascript:getJSONData4("+(parseInt(cp)+1)+",10)");
			$p("#lastPage1").attr("href","javascript:getJSONData4("+(parseInt(tp))+",10)");
			}
		
			for(var i = 0; i < jsonRoot.length; i++) {
				str += '<tr><td>'+ jsonRoot[i].lssuingNum + '</td><td>'
					   + decodeURI(jsonRoot[i].rate, "utf-8") +"%</td><td>"
					   + decodeURI(jsonRoot[i].benxi, "utf-8") +"</td><td>"
					   + decodeURI(jsonRoot[i].cancelNum, "utf-8") +"</td><td>"
					   + decodeURI(jsonRoot[i].cancelTime, "utf-8") +"</td><td>" 
					   +decodeURI(jsonRoot[i].remark, "utf-8") +"</td></tr>";
			        	
			}

			pagetable.html(str+"</tbody>");
			
		},    
	     error : function() {  
	     }    
	});
}
  
getJSONData4(1,10); 
