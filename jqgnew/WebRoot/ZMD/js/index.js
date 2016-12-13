//------------------轮播图的切换
$(function(){
	

			$(".full_banner").slide({
				 titCell:".hd ul", 
				 mainCell:".bd #fontzoom", 
				 effect:"fold",  
				 
				 autoPlay:true, 
				 autoPage:true,
				 trigger:"click",
				 interTime:3500
			});
});

/***排行榜选项卡代码******/
$(function(){
	
	$('.v_ranking_con').eq(0).show();
	$('.v_ranking').eq(0).addClass('tianjia');
		
	$(".v_ranking").bind("mouseover",function(){
		
		var $index = $(this).index();
		
		$('.v_ranking_con').not(':eq('+$index+')').hide();
		
		if($('.v_ranking_con').eq($index).children().length!=0){
			$('.v_ranking_con').eq($index).show();
		}
		else{
			$('.v_ranking_con').eq($index).css( { height: "0" } );
		}		
		$('.v_ranking').removeClass('tianjia');
		$(this).addClass('tianjia');
	});	
	$(".v_ranking_con").find("li:lt(3)").css("color","#3db1fa");
	
	
});
