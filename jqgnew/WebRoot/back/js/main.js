/*var path = $("#path").val();
alert(path);*/
function index(){
	var path = $("#path").val();
	  parent.frames[1].location.href=path+"/back/1_left.jsp"; 
	  parent.frames[2].location.href=path+"/overall/index"; 
}

function chushen(){
	var path = $("#path").val();
	 parent.frames[1].location.href=path+"/back/2_left.jsp"; 
	  parent.frames[2].location.href=path+"/borrow/toBackLssuing?queryFlag=0"; 
}function edu(){
	var path = $("#path").val();
	  parent.frames[1].location.href=path+"/back/3_left.jsp?key=edu"; 
	  parent.frames[2].location.href=path+"/back/3_list5.jsp"; 
}function shipin(){
	var path = $("#path").val();
	  parent.frames[1].location.href=path+"/back/3_left.jsp?key=shipin"; 
	  parent.frames[2].location.href=path+"/back/3_list3.jsp"; 
}function xianchang(){
	var path = $("#path").val();
	  parent.frames[1].location.href=path+"/back/3_left.jsp?key=xianchang"; 
	  parent.frames[2].location.href=path+"/back/3_list4.jsp"; 
}

function tixian(){
	var path = $("#path").val();
	parent.frames[1].location.href=path+"/back/4_left.jsp?key=tixian"; 
	  parent.frames[2].location.href=path+"/back/translate1"; 
}
function shiming(){
	var path = $("#path").val();
	  parent.frames[1].location.href=path+"/back/3_left.jsp?key=shiming"; 
	  parent.frames[2].location.href=path+"/back/3_namelist.jsp"; 
}
function fushen(){
	var path = $("#path").val();
	 parent.frames[1].location.href=path+"/back/2_left.jsp?key=fushen"; 
	  parent.frames[2].location.href=path+"/borrow/toBackLssuing?queryFlag=1"; 
}function chongzhi(){
	var path = $("#path").val();
	parent.frames[1].location.href=path+"/back/4_left.jsp?key=chongzhi"; 
	  parent.frames[2].location.href=path+"/back/offline.action?flag=1"; 
}
function news(){
	var path = $("#path").val();
	  parent.frames[1].location.href=path+"/back/5_left.html"; 
	  parent.frames[2].location.href=path+"/overall/findmenus"; 
}
function tongji(){
	var path = $("#path").val();
	 parent.frames[1].location.href=path+"/back/7_left.jsp"; 
	 parent.frames[2].location.href=path+"/back/7_list.jsp"; 
}
function vip(){
	var path = $("#path").val();
	 parent.frames[1].location.href=path+"/back/3_left.jsp?key=vip"; 
	 parent.frames[2].location.href=path+"/back/3_vipList.jsp"; 
}

