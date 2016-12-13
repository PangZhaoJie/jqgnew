   $(document).ready(function(){
		$("#myCont2 > div:not(:first)").hide();   
		     var myLi = $("#myTop2 > li");       
			
			var myDiv =$("#myCont2 > div");
		myLi.each(function(i){                         
		$(this).mouseover(function(){
		myLi.removeClass("current");
		$(this).addClass("current");
		myDiv.hide();
		myDiv.eq(i).show();                      
		})
		})
		})

  