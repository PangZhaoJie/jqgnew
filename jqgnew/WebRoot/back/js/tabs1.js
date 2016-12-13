 $(document).ready(function(){
		$("#myCont1 > div:not(:first)").hide();   
		     var myLi = $("#myTop1 > li");       
			
			var myDiv =$("#myCont1 > div");
		myLi.each(function(i){                         
		$(this).mouseover(function(){
		myLi.removeClass("current");
		$(this).addClass("current");
		myDiv.hide();
		myDiv.eq(i).show();                      
		})
		})
		})
