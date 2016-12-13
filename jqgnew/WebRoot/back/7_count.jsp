<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%    
	String path = request.getContextPath();if(path!=null && !path.equals("")){	if(path.contains("/")){		if(path.equals("/")){path = "";	}}else{	path += "/";}}    
	String basePath = "";if(request.getServerPort()==80){basePath = request.getScheme()+"://"+request.getServerName()+path+"/";}else{basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";}      
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="<%=basePath %>/back/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath %>/back/js/jquery.js"></script>
<!--折叠js-->
<script type="text/javascript"> 

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
<!--日历插件 js-->
<script type="text/javascript" src="<%=basePath %>/back/laydate/laydate.js"></script>
<style>
table{border-collapse: collapse;}
.zh_table{ line-height: 30px;  margin-top: 5px; float:left;}
.zh_table .th1 { font-weight: bold; padding-left: 30px; text-align: left; height:40px; font-size:15px;}
.zh_table th { background-color: #f9f9f9; border: 1px solid #ddd; font-weight: normal; text-align: center;}
.zh_table td { border: 1px solid #ddd; text-indent:100px;line-height:35px;}

</style>

</head>


<body >

	<%-- <div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="javascript:void(0);" onclick="parent.frames.topFrame.overall();">首页</a></li>
    <li><a href="javascript:void(0);" onclick="parent.frames.topFrame.fund();">资金统计</a></li>
    <li><a href="#">网站资金统计</a></li>
    </ul>
    </div> --%>
    
    <div class="rightinfo">
    
       <div class="tools">
       <div id="panel">
              <div class="panel_title">资金统计</div>
              <div class="panel_nr fn_cle">
                 <form enctype="multipart/form-data" onsubmit="" action="<%=path %>/money/toMoneyTotal" method="post">
   <dl class="line">
      <dt>借款时间(开始)：</dt>
      <dd>
	  	<input class="laydate-icon" id="demo1" value="${startTimeQuery }" name="startTimeQuery"/>
        <a style="color: #7d7d7d">只选开始时间则查询从开始时间往后所有</a>
      </dd>
    </dl>
    <dl class="line">
      <dt>借款时间(结束)：</dt>
      <dd>
        <input class="laydate-icon" id="demo2" value="${endTimeQuery }" name="endTimeQuery"/>
        <a style="color: #7d7d7d">只选结束时间则查询从结束时间往前所有</a>
      </dd>
    </dl>
     <script>
!function(){
laydate.skin('default');//切换皮肤，请查看skins下面皮肤库
laydate({elem: '#demo1'});
laydate({elem: '#demo2'});//绑定元素
}();
</script>  
	
    
    
		 
    <div class="page_btn">
	 
      <input type="submit" value="统计" onclick="" id="showwait" class="btn_b"  style="cursor: pointer;">
    </div>
	</form>
              </div>
         </div>
     <c:if test="${sessionScope.powerss[103]==1}">    
     <ul class="toolbar">
      <!--   <li  id="ID1" onclick="addCheck('1')"><span><img src="<%=basePath %>/back/images/t04.png" /></span>资金统计</li> -->
         <li  id="ID2" style="display:none" onclick="addCheck('2')"><span><img src="<%=basePath %>/back/images/t04.png" /></span>统计完毕</li>
      </ul>
      </c:if>
    
    </div>
    
     <div class="mem_table" >
      <table width="100%" cellspacing="0" cellpadding="0" class="zh_table"  >
                            <tbody>
                            <tr>
                                <th align="left" class="th1">会员统计</th>
                                <th align="left" class="th1">资金进出统计</th>
                                <th align="left" class="th1">借款统计</th>
                                <th align="left" class="th1">投资者收益统计</th>
                            </tr>
                            <tr>
                                <td>会员总数:${fn:length(uservipList)}人 </td>
                                <td>线上充值: ￥${money1 } </td>
                                <td>成功借出总额: ￥${borrow1} </td>
                                <td>成功借款利息总额:￥${invest1} </td>
                              
                               
                            </tr>
                            <tr>
                                <td>通过身份认证:${num1 }人 </td>
                                <td>线下充值:￥${money2 } </td>
                                <td>已还款总额: ￥${borrow2} </td>
                                <td>成功借款投标奖励总额:￥${invest2}</td>
                            </tr>
                            <tr>
                                <td> 通过手机认证:${num2 }人</td>
                                <td>成功提现:￥${money3 } </td>
                                <td>未还款总额:￥${borrow3} </td>
                                <td>邀请注册奖金总额:￥${invest3}</td>
                            </tr>
                            <tr>
                               
                                <td>通过现场认证:${num3 }人 </td>
                                <td>总计: ￥${money4 } </td>
                                <td>冻结中的保证金总额:￥${invest6 } </td>
                                <td>线下充值奖励: ￥${invest4}</td>
                            </tr>
                            <tr>
                               
                                <td> 通过视频认证:${num4 }人 </td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>总计: ￥${invest5} </td>
                            </tr>
                           
                            
                           
                      </tbody></table>
    
     </div>
     
      
    
    
    
       
    
    </div>
    
  
</body>

</html>
