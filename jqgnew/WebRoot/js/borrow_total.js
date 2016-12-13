function toMoneyPage(num,num2){
 	  var  $MP=jQuery.noConflict();
	  var page=$MP("#currentPageId"+num2).val();
 	  var pageNum=$MP("#pageNumId"+num2).val();
 	  var p=$MP("#pId"+num2).val();
	  if(num==1){
			  $MP("#page1-1"+num2).attr("href","../borrow/toLssuingList?userId=${session.uservip.userId}&page=1&queryFlag="+num2); 
	  }
	  if(num==3){
		  if(page<pageNum){
			  $MP("#page1-3"+num2).attr("href","../borrow/toLssuingList?userId=${session.uservip.userId}&page="+(Number(page)+1)+"&queryFlag="+num2);
		  }
	  }
	  if(num==2){
		  if(page>1){
			  $MP("#page1-2"+num2).attr("href","../borrow/toLssuingList?userId=${session.uservip.userId}&page="+(Number(page)-1)+"&queryFlag="+num2);
		  }
	  }
	  if(num==4){
		  if(p<1){
			  p=1;
		  }else if(p>pageNum){
			  p=pageNum;
		  }
	 
			  $MP("#page1-4"+num2).attr("href","../borrow/toLssuingList?userId=${session.uservip.userId}&page="+Number(p)+"&queryFlag="+num2);
	  }
	  
}