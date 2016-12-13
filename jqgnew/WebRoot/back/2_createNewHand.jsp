<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%    
	String path = request.getContextPath();if(path!=null && !path.equals("")){	if(path.contains("/")){		if(path.equals("/")){path = "";	}}else{	path += "/";}}    
	String basePath = "";if(request.getServerPort()==80){basePath = request.getScheme()+"://"+request.getServerName()+path+"/";}else{basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";}      
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改信息</title>
<link href="<%=basePath%>/back/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>/back/css/mem.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>/back/css/tab.css" rel="stylesheet" type="text/css" />
<!--  <script type="text/javascript" src="<%=basePath %>/js/jquery-1.4.2.js"></script> -->
 <script type="text/javascript" src="<%=basePath %>/js/jquery-1.8.2.min.js"></script>

<script type="text/javascript" src="../back/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=basePath %>/js/borrow3.js"></script>
</head>

<body >
<input type="hidden" value="<%=basePath %>" id="path"/>
    <div class="rightinfo">
       <div id="hotnews_caption">
            <ul id="h_menu">                  
                 <li class="current" onclick="secBoard('hotnews_caption','list',1);">基本信息填写</li>
            </ul>
         </div> 
       <div id="hotnews_content">
        <div class="current" id="list_1">
        <form action="" method="post" id="2_tabId1"> 
           <ul class="m_forminfo">
           	<li><label class="m_info_title">借款金额：</label>  
                <input name="lssuing.borrowMoney" onblur="borrowMoneyCheck();"  onkeyup="value=value.replace(/[^\d\.]/g,'')" type="text" class="dfinput" id="borrowMoneyId" value="" size="37"/>
               <i>*</i>
            </li>
            <li><label class="m_info_title">借款人：</label>  
            	<select onchange="" id="userId" class="input" name="user"  >
	            <option value="0">--请选择--</option>
                <c:forEach var="fs" items="${uservipList}">
                	   <option value="${fs.userId }">${fs.userName}</option>
                </c:forEach> 
              </select>
               <i>*</i>
            </li>
            <li><label class="m_info_title" id="rllId">年利率：</label>  
                <input name="lssuing.rate" type="text"  onblur="rateCheck()" onkeyup="value=value.replace(/[^\d\.]/g,'')"  class="dfinput" id="rateId" value="" size="37" style="float: left"/><a style="padding-left:8px;line-height: 35px;float: left">%（大于0，保留小数点后四位）</a>
                <p id="rateSpan" style="color: #FF0000;width: 130px;float:left;line-height: 35px;padding-left: 10px;margin-top: 2px">*</p>
            </li>
            <li><label class="m_info_title">借款用途：</label>  
            	<select onchange="" id="moneyUseId" class="input" name="moneyUseId"  >
	            <option value="0">--请选择--</option>
                <c:forEach var="moneyuse" items="${moneyuseList}">
                	   <option value="${moneyuse.moneyUseId}">${moneyuse.moneyUseName}</option>
                </c:forEach> 
              </select>
               <i>*</i>
            </li>
            <li><label class="m_info_title">借款期限：</label>  
                <select name="periodtime" class="input"  id="periodtimeId">
	              	 <option value="0"  selected="selected">-请选择月数-</option>              	 
 	              	 <c:forEach var="periodtime" items="${periodtimeList}">
		                    <option  value="${periodtime.periodTimeId}">${periodtime.periodTimeName}</option>
					 </c:forEach> 	              	
	              </select>
	              <select name="periodday" class="input"  id="perioddayId" style="display:none">
	              	 <option value="0"  selected="selected">-请选择天数-</option>              	 
 	              	 <c:forEach var="periodday" items="${perioddayList}">
		                    <option  value="${periodday.periodDayId}">${periodday.periodDayName}</option>
					 </c:forEach> 	              	
	              </select>
             <input type="checkbox" name="checkbox" onclick="dayCheck()" id="dayCheckId" value="按天" style="float: left;margin-left:8px;margin-top: 10px " />&nbsp;按天
               <i>*</i>
              <script type="text/javascript">
              (function(){
              	  if(jQuery("#perioddayListId").val()!=""){
            		document.getElementById("periodtimeId").style.display="none";
              		document.getElementById("perioddayId").style.display="";
              		document.getElementById("dayCheckId").checked=true;
            	  }
              })();
              function dayCheck(){
                  if(document.getElementById("dayCheckId").checked){
                  		document.getElementById("rllId").innerHTML="日利率：";
                 		document.getElementById("periodtimeId").style.display="none";
                		document.getElementById("perioddayId").style.display="";
                		document.getElementById("periodtimeId").options[0].selected=true;
                		 
 
                  }else{
                  		document.getElementById("rllId").innerHTML="年利率：";
                 		document.getElementById("periodtimeId").style.display="";
                		document.getElementById("perioddayId").style.display="none";
                		document.getElementById("perioddayId").options[0].selected=true;
                   }
            }
              </script> 
            </li>
            <li>
            <label class="m_info_title">最低投标金额：</label>  
            <select name="moneymin" id="moneyMinId"  class="input">
	              	 <option value=""  selected="selected">-请选择金额-</option>
 	              	<c:forEach var="moneymin" items="${moneyminList}">
		             <option  value="${moneymin.moneyMinId}">${moneymin.moneyMinName}元</option>
					 </c:forEach> 
	              </select>  
            <i>*</i>
            </li>
            <li>
            <label class="m_info_title">最多投标金额：</label>  
            <select name="moneymax" id="moneymaxId"  class="input">
	              	 <option value=""  selected="selected">-请选择金额-</option>
 	              	<c:forEach var="moneymax" items="${moneymaxList}">
		             <option  value="${moneymax.moneyMaxId}">${moneymax.moneyMaxName}元</option>
					 </c:forEach> 
	              </select>  
            <i>*</i>
            </li>
            <li>
            	<label class="m_info_title">有效时间：</label>  
            	<select name="validtime" id="validtimeId"  class="input">
	              	 <option value="0"  selected="selected">-请选择时间-</option>
 	              <c:forEach var="validtime" items="${validtimeList}">
		             <option value="${validtime.validTimeId}">${validtime.validTimeName}天</option>
				  </c:forEach> 
	          	</select>  
            	<i>*</i>
            </li>
            <li>
            	<label class="m_info_title">还款方式：</label>  
            	<select name="returnway" id="returnwayId"  class="input">
	              	 <option value="0"  selected="selected">-请选择还款方式-</option>
 	              <c:forEach var="returnway" items="${returnwayList}">
		             <option  value="${returnway.returnWayId}">${returnway.returnWayName}</option>
				  </c:forEach> 
	          	</select>  
            	<i>*</i>
            </li>
            <li><span style="font-size:18px;color:blue">借款详情说明</span></li>
            <li><label class="m_info_title">借款标题：</label>  
                 <input name="lssuing.title" type="text" class="dfinput" value="" id="titleId" size="37" onblur="titleCheck()"/>
               <i>*</i>
            </li>
            <li>
			 <label class="m_info_title">借款说明：</label>
			 <textarea name="lssuing.explains" id="explainsId" style="width:400px; height:100px; border:solid 1px #000000; border-radius:5px; resize:none;"></textarea>
			 <i>*</i>
			</li>  
            <input value="${lssuing.lssuingNum}" name="lssuing.lssuingNum" id="numId" type="hidden"/>
            <li><label class="m_info_title">&nbsp;</label><input name="" id="mitOnebutton" type="button" onclick="submitCheck()" class="btn" value="确定"/></li>
          </ul>
    </form>	
       </div> 
	</div> 
    </div>
    
 <script type="text/javascript" src="<%=basePath %>/js/ajaxfileupload.js"></script>
</body>
</html>
