var $page =  jQuery.noConflict();
function getJSONData(currentPage,pageSize) {
	$page("#ID1").hide();
	$page("#ID2").show();
	var path=$page('#path').val();
	var url = path+"userbank/userBanks.action?currentPage="+currentPage+"&pageSize="+pageSize+"&1=" +Math.random();
	$page.getJSON(url,function(data){
		var cp = data.currentPage;
		var tp = data.totalPage;
		var $pagetable = $page ("#banks");
		$pagetable.empty();
		str = '<tr class="th1"><td width="190">银行名称</td><td width="320">开户银行</td><td width="210">银行账号</td><td width="100">操作</td> </tr>';
		var jsonRoot = data.jsonRoots;
		for(var i = 0; i < jsonRoot.length; i++) {
			str += '<tr><td><img src="'+path+ jsonRoot[i].picture + '" alt="银行名称"/></td><td>'
				   + decodeURI(jsonRoot[i].address, "utf-8") +"</td><td>"
				   + decodeURI(jsonRoot[i].number, "utf-8")  + '</td><td> <a href="'+path+'userbank/findUserBank?personalBankId='
			+	jsonRoot[i].id   + '">编辑</a>|<a href="'+path+'userbank/deleteUserBank?personalBankId=' +jsonRoot[i].id +'">删除</a></td></tr>';
		}
		$pagetable.html(str);
		if(tp !=1)
		{
			
		document.getElementById("pageroot").style.display="";
	     $page ("#currentPage").text("第"+cp+"页");
         $page ("#totalPage").text("共"+tp+"页");
	     $page ("#firstPage").attr("href","javascript:getJSONData(1,10)");
	     $page ("#prevPage").attr("href","javascript:getJSONData("+(parseInt(cp)-1)+",10)");
	     $page ("#nextPage").attr("href","javascript:getJSONData("+(parseInt(cp)+1)+",10)");
	     $page ("#lastPage").attr("href","javascript:getJSONData("+(parseInt(tp))+",10)");
		}
	else{
		document.getElementById("pageroot").style.display="none";
	}
	});
}
$page (function() {  
	getJSONData(1,10);  
});