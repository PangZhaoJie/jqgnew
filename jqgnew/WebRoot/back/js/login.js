
function  checkName(a){
    var managerName=document.getElementById("managerName").value;
    var nameTip=document.getElementById("nameTip");
    if(managerName=="" || managerName==null || managerName=="用户名"){
    	nameTip.innerHTML="用户名不能为空";
    }else{
    	nameTip.innerHTML="";
    }
}
function  checkPwd(a){
    var password=document.getElementById("password").value;
    var pwdTip=document.getElementById("pwdTip");
    if(password=="" || password==null || password=="密码"){
    	pwdTip.innerHTML="密码不能为空";
    }else{
    	pwdTip.innerHTML="";
    }
}
function  checkPwdp(a){
    var passwordWord=document.getElementById("passwordWord").value;
    var wordTip=document.getElementById("wordTip");
    if(passwordWord=="" || passwordWord==null || passwordWord=="口令"){
    	wordTip.innerHTML="口令不能为空";
    }else{
    	wordTip.innerHTML="";
    }
}
	var code ; //在全局 定义验证码   
    function createCode()   
     {    
       code = "";   
       var codeLength = 4;//验证码的长度   
       var checkCode = document.getElementById("checkCode");   
       var selectChar = new Array(2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','P','Q','R','S','T','U','V','W','X','Y','Z',
    		                     'a','b','c','d','e','f','g','h','i','j','k','m','n','p','q','r','s','t','u','v','w','x','y','z');//所有候选组成验证码的字符，当然也可以用中文的   
            
       for(var i=0;i<codeLength;i++)   
       {   
          
            
       var charIndex = Math.floor(Math.random()*57);   
       code +=selectChar[charIndex];   
           
           
       }   
//       alert(code);   
       if(checkCode)   
       {   
         checkCode.className="code";   
         checkCode.value = code;   
       }   
           
     } 
    function validate ()   
    {   
      //var inputCode = document.getElementById("input1").value; 
      var inputCode=document.getElementById("code").value;
      var yzm=document.getElementById("yzmTip");
      if(inputCode.length <=0)   
      {   
          yzm.innerHTML="请输入验证码";
      }   
      else if(inputCode.toLowerCase() != code.toLowerCase() )   
      {   
          yzm.innerHTML="验证码错误";
         createCode();//刷新验证码   
      }   
      else   
      {  
          yzm.innerHTML="";
//        $p("#yzmTip").html("<span style='font-size:12px;color:green;float:left;width:200px;line-height:30px; padding-left:90px;'>输入正确</span>");  
      }   
          
      } 
    function  checkCodeDD(){
        var inputCode=document.getElementById("code").value;
        var yzm=document.getElementById("yzmTip");
        if(inputCode=="" || inputCode==null || inputCode=="验证码" || inputCode.length <=0){
        	yzm.innerHTML="验证码不能为空";
        }else if(inputCode.toLowerCase() != code.toLowerCase()){
        	yzm.innerHTML="验证码错误";
        }else{
        	yzm.innerHTML="";
        }
    }
    function login(){
        var inputCode=document.getElementById("code").value;
        var managerName=document.getElementById("managerName").value;
        var password=document.getElementById("password").value;
        var passwordWord=document.getElementById("passwordWord").value;
        var nameTip=document.getElementById("nameTip");
        var pwdTip=document.getElementById("pwdTip");
        var wordTip=document.getElementById("wordTip");
        var yzm=document.getElementById("yzmTip");
        if(managerName=="" || managerName==null){
        	nameTip.innerHTML="用户名不能为空";
        	return false;
        }else{
        	nameTip.innerHTML="";
        }
        if(password=="" || password==null){
        	pwdTip.innerHTML="密码不能为空";
        	return false;
        }else{
        	pwdTip.innerHTML="";
        }
        if(passwordWord=="" || passwordWord==null){
        	wordTip.innerHTML="口令不能为空";
        	return false;
        }else{
        	wordTip.innerHTML="";
        }
 		if(inputCode.length <=0){   
           yzm.innerHTML="请输入验证码";
 		   return false;
         }else if(inputCode.toLowerCase() != code.toLowerCase() ){   
           yzm.innerHTML="验证码错误";
           createCode();//刷新验证码   
           return false;
        }
 		var form=document.getElementById("login");
 		var path = $("#path").val();
 		form.action=path+"/back/login";
 		form.submit();
    }
    $(window).load(function(){
  	  setTimeout("$('#error').hide()",3000);
    });
    
      
     