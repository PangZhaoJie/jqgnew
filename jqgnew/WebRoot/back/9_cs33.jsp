<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();if(path!=null && !path.equals("")){	if(path.contains("/")){		if(path.equals("/")){path = "";	}}else{	path += "/";}} 
String basePath = "";if(request.getServerPort()==80){basePath = request.getScheme()+"://"+request.getServerName()+path+"/";}else{basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";}    
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>投资级别管理</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/mem.css" rel="stylesheet" type="text/css" />
<link href="css/tab1.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<%-- <script type="text/javascript" src="js/Tabs.js"></script> --%>
<!--折叠js-->
<script type="text/javascript"> 
   function check(form){
	   var $m=jQuery.noConflict();
	   var link_txt=$m("#link_txt").val();
	   var link_href=$m("#link_href").val();
	   var link_hrefend=$m("#link_hrefend").val();
	   var link_hrefpicture=$m("#link_hrefpicture").val();
	   if(link_txt==""){
		   $m("#tip1").html("<span style='font-size:12px;color:red;float:left;'>级别名不能为空</span>");
		   return false;
	   }else{
		   $m("#tip1").html("");
	   }
	   if(link_href==""){
		   $m("#tip2").html("<span style='font-size:12px;color:red;float:left;'>开始积分不能为空</span>");
		   return false;
	   }else{
		   $m("#tip2").html("");
	   }
	   if(link_hrefend==""){
		   $m("#tip3").html("<span style='font-size:12px;color:red;float:left;'>结束积分不能为空</span>");
		   return false;
	   }else{
		   $m("#tip3").html("");
	   }
	   if(link_hrefpicture==""){
		   $m("#tip4").html("<span style='font-size:12px;color:red;float:left;'>级别图标名不能为空</span>");
		   return false;
	   }else{
		   $m("#tip4").html("");
	   }
	  alert("添加成功！");
   }

function addCheck(num){
	if(num==1){
		 $("#panel").show();
		 $("#panel1").hide();
		 $("#ID1").hide();
		 $("#ID2").show();
		  $("#ID4").hide();
		 $("#ID3").show();		 
		}else if(num==3){
		  $("#panel").hide();
		  $("#panel1").show();
		  $("#ID2").hide();
		 $("#ID1").show();	
		  $("#ID3").hide();
		 $("#ID4").show();	
		}else  {
		  $("#panel").hide();
		  $("#panel1").hide();
		   $("#ID1").show();
		 $("#ID3").show();	
		  $("#ID2").hide();
		 $("#ID4").hide();
		}
	}
 
</script>

</head>


<body>

	<%-- <div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">扩展管理</a></li>
    <li><a href="#">投资级别管理</a></li>
    </ul>
    </div> --%>
    
    <div class="rightinfo">
    
         <div class="formtitle"><span>投资级别管理</span></div>
         <div class="tools">
       <div id="panel">
              <div class="panel_title">添加一个级别</div>
              <div class="panel_nr fn_cle">
                 <form enctype="multipart/form-data" onsubmit="return check(this);" action="<%=path %>/level/createIlevel" method="post">
    <dl class="line">
      <dt>级别名：</dt>
      <dd>
        <input type="text" value="" id="link_txt" class="input" name="link_txt" size="40"/>
        <span id="tip1"></span>
      </dd>
    </dl>
	
    <dl class="line">  <dt>级别开始积分：</dt>
      <dd>
        <input type="text" value="" id="link_href" class="input" name="link_href" size="40"/>
        <span id="tip2"></span>
      </dd>
    </dl>
    
    <dl class="line">  <dt>级别结束积分：</dt>
      <dd>
        <input type="text" value="" id="link_hrefend" class="input" name="link_href" size="40"/>
        <span id="tip3"></span>
      </dd>
    </dl>
     <dl class="line">  <dt>级别图标名称：</dt>
      <dd>
        <input type="text" value="" id="link_hrefpicture" class="input" name="link_href" size="40"/>
        <span id="tip4"></span>
      </dd>
    </dl>
    
    <div class="page_btn">
	  <input type="hidden" name="investmentLevelId" value="${investmentLevelId}"/>
      <input type="submit" value="添加" onclick="" id="showwait" class="btn_b"/>
    </div>
	</form>
              </div>
         </div>
    	<ul class="toolbar">
         <li  id="ID1" onclick="addCheck('1')"><span><img src="images/t01.png" /></span>添加一个级别</li>
         <li  id="ID2" style="display:none" onclick="addCheck('2')"><span><img src="images/t01.png" /></span>添加完毕</li>
         <span style="float:left; padding:8px 0px 0px 5px;">如需新增级别图标，请先将图片通过FTP上传到您服务器项目下的UF\leveico\目录下 </span>
       
        </ul>
      </div> 
         
      <table class="tablelist">
    	<thead>
    	<tr>
           <th width="3%"><input name="" type="checkbox" value="" checked="checked"/></th>
           <th width="3%">序号</th>
           <th width="15%">级别名称</th>
           <th width="19%">级别开始积分</th>
           <th width="19%">级别结束积分</th>
           <th width="19%">级别图标名称</th>
           <th width="9%">图标显示</th>
           <th width="13%">操作</th>
        </tr>
        </thead>
        
        <tbody>
        <tr>
             <td><input name="" type="checkbox" value="" /></td>
             <td>1</td>
             <td><input type="text" value="一级" class="input" name="" size="20"/></td>
             <td><input type="text" value="0" class="input" name="" size="30"/></td>
             <td><input type="text" value="499" class="input" name="" size="30"/></td>
             <td><input type="text" value="maque_s11.gif" class="input" name="" size="30"/></td>
             <td><img src="images/maque_s11.gif" /></td>
           <td><a href="#" class="tablelink">编辑</a>     <a href="#" class="tablelink"> 删除</a></td>
        </tr> 
        
       <tr>
             <td><input name="" type="checkbox" value="" /></td>
             <td>1</td>
             <td><input type="text" value="二级" class="input" name="" size="20"/></td>
             <td><input type="text" value="500" class="input" name="" size="30"/></td>
             <td><input type="text" value="1999" class="input" name="" size="30"/></td>
             <td><input type="text" value="maque_s12.gif" class="input" name="" size="30"/></td>
             <td><img src="images/maque_s12.gif" /></td>
           <td><a href="#" class="tablelink">编辑</a>     <a href="#" class="tablelink"> 删除</a></td>
        </tr> 
        
        <tr>
             <td><input name="" type="checkbox" value="" /></td>
             <td>1</td>
             <td><input type="text" value="三级" class="input" name="" size="20"/></td>
             <td><input type="text" value="2000" class="input" name="" size="30"/></td>
             <td><input type="text" value="9999" class="input" name="" size="30"/></td>
             <td><input type="text" value="maque_s13.gif" class="input" name="" size="30"/></td>
             <td><img src="images/maque_s13.gif" /></td>
           <td><a href="#" class="tablelink">编辑</a>     <a href="#" class="tablelink"> 删除</a></td>
        </tr> 
        
       <tr>
             <td><input name="" type="checkbox" value="" /></td>
             <td>1</td>
             <td><input type="text" value="四级" class="input" name="" size="20"/></td>
             <td><input type="text" value="10000" class="input" name="" size="30"/></td>
             <td><input type="text" value="49999" class="input" name="" size="30"/></td>
             <td><input type="text" value="maque_s21.gif" class="input" name="" size="30"/></td>
             <td><img src="images/maque_s21.gif" /></td>
           <td><a href="#" class="tablelink">编辑</a>     <a href="#" class="tablelink"> 删除</a></td>
        </tr> 
        
       <tr>
             <td><input name="" type="checkbox" value="" /></td>
             <td>1</td>
             <td><input type="text" value="五级" class="input" name="" size="20"/></td>
             <td><input type="text" value="50000" class="input" name="" size="30"/></td>
             <td><input type="text" value="199999" class="input" name="" size="30"/></td>
             <td><input type="text" value="maque_s22.gif" class="input" name="" size="30"/></td>
             <td><img src="images/maque_s22.gif" /></td>
           <td><a href="#" class="tablelink">编辑</a>     <a href="#" class="tablelink"> 删除</a></td>
        </tr> 
        
       <tr>
             <td><input name="" type="checkbox" value="" /></td>
             <td>1</td>
             <td><input type="text" value="六级" class="input" name="" size="20"/></td>
             <td><input type="text" value="200000" class="input" name="" size="30"/></td>
             <td><input type="text" value="999999" class="input" name="" size="30"/></td>
             <td><input type="text" value="maque_s23.gif" class="input" name="" size="30"/></td>
             <td><img src="images/maque_s23.gif" /></td>
           <td><a href="#" class="tablelink">编辑</a>     <a href="#" class="tablelink"> 删除</a></td>
        </tr> 
        
       <tr>
             <td><input name="" type="checkbox" value="" /></td>
             <td>1</td>
             <td><input type="text" value="七级" class="input" name="" size="20"/></td>
             <td><input type="text" value="1000000" class="input" name="" size="30"/></td>
             <td><input type="text" value="99999999999999" class="input" name="" size="30"/></td>
             <td><input type="text" value="maque_s31.gif" class="input" name="" size="30"/></td>
             <td><img src="images/maque_s31.gif" /></td>
           <td><a href="#" class="tablelink">编辑</a>     <a href="#" class="tablelink"> 删除</a></td>
        </tr> 
        
        </tbody> 
        
      </table>
        
        
      <div class="page_btn"> <input type="submit" value="确定" onclick="" id="showwait" class="btn_b"/></div>
      
      
    </div>

</body>

</html>


