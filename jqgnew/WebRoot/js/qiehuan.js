$(function(){
	
	$('.v_about_text').eq(0).show();
	$('.qiehuan').eq(0).addClass('v_overa');
	
	
	
	$('.qiehuan').bind("mouseover",function(){
		
		var f=$(this).parent().index();
		
		$('.v_about_text').not(':eq('+f+')').fadeOut("fast");
  		
  		$('.v_about_text').eq(f).fadeIn("slow");
  		
  		
  		$('.qiehuan').removeClass('v_overa');
  		
  		$(this).addClass('v_overa');
  		
	})
})
