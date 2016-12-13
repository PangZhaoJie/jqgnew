$(function () {
	 $("#userName").blur(function(){
     	var userName=$("#userName").val();
     	if(userName!=""){
     		$("#userTip").html("");//通过
     	}
     });
     $("#password").blur(function(){
     	var pwd=$("#password").val();
     	if(pwd!=""){
     		$("#pwdTip").html("");//通过
     	}
     });
     $("#yanzhengma").blur(function(){
        var yzm=$("#yanzhengma").val();
//        if(yzm!=""){
//            validate();
//        }
        if(yzm=="" || yzm==null){
        	$("#yzmTip").html("<span style='font-size:12px;color:red;float:left;width:200px;line-height:30px; padding-left:90px;'>请输入验证码</span>");
        }else if(yzm.length!=4){
        	$("#yzmTip").html("<span style='font-size:12px;color:red;float:left;width:200px;line-height:30px; padding-left:90px;'>请输入正确的验证码</span>");
        }else{
        	$("#yzmTip").html("");
        }
     });
});
//var code ; //在全局 定义验证码   
//    function createCode()   
//     {    
//       code = "";   
//       var codeLength = 4;//验证码的长度   
//       var checkCode = document.getElementById("checkCode");   
//       var selectChar = new Array(2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','P','Q','R','S','T','U','V','W','X','Y','Z',
//    		                     'a','b','c','d','e','f','g','h','i','j','k','m','n','p','q','r','s','t','u','v','w','x','y','z');//所有候选组成验证码的字符，当然也可以用中文的   
//            
//       for(var i=0;i<codeLength;i++)   
//       {   
//          
//            
//       var charIndex = Math.floor(Math.random()*57);   
//       code +=selectChar[charIndex];   
//           
//           
//       }   
////       alert(code);   
//       if(checkCode)   
//       {   
//         checkCode.className="code";   
//         checkCode.value = code;   
//       }   
//           
//     }   
         
//      function validate ()   
//     {   
//       //var inputCode = document.getElementById("input1").value; 
//       var inputCode=$("#yanzhengma").val();
//       if(inputCode.length <=0)   
//       {   
//           $("#yzmTip").html("<span style='font-size:12px;color:red;float:left;width:200px;line-height:30px; padding-left:90px;'>请输入验证码</span>");
//       }   
//       else if(inputCode.toLowerCase() != code.toLowerCase() )   
//       {   
//          $("#yzmTip").html("<span style='font-size:12px;color:red;float:left;width:200px;line-height:30px; padding-left:90px;'>验证码错误</span>");
////          createCode();//刷新验证码   
//       }   
//       else   
//       {  
//    	   $("#yzmTip").html("");
////         $("#yzmTip").html("<span style='font-size:12px;color:green;float:left;width:200px;line-height:30px; padding-left:90px;'>输入正确</span>");  
//       }   
//           
//       } 
      function check(form){
    	var user=$("#userName").val();
    	 if(user == ""){
    		  $("#userTip").html("<span style='font-size:12px;color:red;float:left;width:200px;line-height:30px; padding-left:90px;'>用户名不能为空</span>");
    		  return false;
    	  }
    	 var pwd=$("#password").val();
    	  if(pwd==""){
    		  $("#pwdTip").html("<span style='font-size:12px;color:red;float:left;width:200px;line-height:30px; padding-left:90px;'>密码不能为空</span>");
    		  return false;
    	  }
    	var inputCode=$("#yanzhengma").val();
       		  if(inputCode.length <=0)   
       {   
           $("#yzmTip").html("<span style='font-size:12px;color:red;float:left;width:200px;line-height:30px; padding-left:90px;'>请输入验证码</span>");
           return false;
       }   
       else if(inputCode.length!=4)   
       {   
          $("#yzmTip").html("<span style='font-size:12px;color:red;float:left;width:200px;line-height:30px; padding-left:90px;'>请输入正确的验证码</span>");
//          createCode();//刷新验证码   
          return false;
       }
       		document.getElementById("denglu").style.background = 'gray';
       		document.getElementById("denglu").value='登陆中...';
       	
      }
      $(window).load(function(){
    	  setTimeout("$('#error').hide()",3000);
      });
      
     