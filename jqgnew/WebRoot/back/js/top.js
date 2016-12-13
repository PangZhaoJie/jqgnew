
function overall(){
	var path = $("#path").val();
	  parent.frames[1].location.href=path+"/back/1_left.jsp"; 
	  parent.frames[2].location.href=path+"/overall/index";
	  $(".nav a").removeClass("selected");
	  $(".nav a:eq(0)").addClass("selected");
	  
}
function recharge(){
	var path = $("#path").val();
	  parent.frames[1].location.href=path+"/back/5_left.html"; 
	  parent.frames[2].location.href=path+"/back/5_list.jsp"; 
	  $(".nav a").removeClass("selected");
	  $(".nav a:eq(4)").addClass("selected");
}
function menu(){
	var path = $("#path").val();
	  parent.frames[1].location.href=path+"/back/6_left.jsp"; 
	  parent.frames[2].location.href=path+"/overall/findMenu";
	  $(".nav a").removeClass("selected");
	  $(".nav a:eq(5)").addClass("selected");
}
function money(){
	var path = $("#path").val();
	  parent.frames[1].location.href=path+"/back/2_left.jsp"; 
	  parent.frames[2].location.href=path+"/back/2_return1.jsp"; 
	  $(".nav a").removeClass("selected");
	  $(".nav a:eq(1)").addClass("selected");
}
function loans(){
	var path = $("#path").val();
	  parent.frames[1].location.href=path+"/back/3_left.jsp"; 
	  parent.frames[2].location.href=path+"/back/3_list.jsp";
	  $(".nav a").removeClass("selected");
	  $(".nav a:eq(2)").addClass("selected");
}
function fund(){
	var path = $("#path").val();
	  parent.frames[1].location.href=path+"/back/7_left.jsp"; 
	  parent.frames[2].location.href=path+"/back/7_list.jsp";
	  $(".nav a").removeClass("selected");
	  $(".nav a:eq(6)").addClass("selected");
}
function expand(){
	var path = $("#path").val();
	  parent.frames[1].location.href=path+"/back/9_left.jsp"; 
	  parent.frames[2].location.href=path+"/back/9_cs1.jsp";
	  $(".nav a").removeClass("selected");
	  $(".nav a:eq(8)").addClass("selected");
}
function power(){
	var path = $("#path").val();
	  parent.frames[1].location.href=path+"/back/8_left.jsp"; 
	  parent.frames[2].location.href=path+"/back/8_list.jsp"; 
	  $(".nav a").removeClass("selected");
	  $(".nav a:eq(7)").addClass("selected");
}
function user(){
	var path = $("#path").val();
	  parent.frames[1].location.href=path+"/back/4_left.jsp"; 
	  parent.frames[2].location.href=path+"/back/4_list.jsp";
	  $(".nav a").removeClass("selected");
	  $(".nav a:eq(3)").addClass("selected");
}


