<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
  <%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();if(path!=null && !path.equals("")){	if(path.contains("/")){		if(path.equals("/")){path = "";	}}else{	path += "/";}} 
String basePath = "";if(request.getServerPort()==80){basePath = request.getScheme()+"://"+request.getServerName()+path+"/";}else{basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";}    
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="icon" href="<%=path %>/image/favicon/favicon.ico" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>借款工具</title>
<link href="../css/public.css" type="text/css" rel="stylesheet"/>
<link href="../css/tool.css" type="text/css" rel="stylesheet" />
<!--导航下拉菜单 js-->
<script type="text/javascript" src="../js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="../js/service.js">	</script>
<!--表单下拉列表框-->
<script type="text/javascript" src="../js/tool.js"></script>
<script>

</script>
</head>
<body> 
<!--头部开始-->
<div class="header">
  <jsp:include page="../header.jsp" />
    
</div>
<!--头部结束-->

<div class="s_banner"><img src="../images/tool_banner.jpg" /></div>
 <div class="conbox pt10">
    <P class="guide">当前位置：<a href="<%=path %>/index.action">网站首页</a> > <a href="<%=path %>/tool/borrow_tool.jsp">网贷工具</a> > 借款计算器</P>
    <div class="tool_ct pt10 mr_b10 fn_cle">
        <div class="conbox_l">
           <dl>
              <dt><i><img src="../images/tool_ico.png" /></i>网贷工具</dt>
              <dd><a href="<%=path %>/tool/borrow_tool.jsp">借款计算器</a></dd>
              <dd><a href="<%=path %>/tool/invest_tool.jsp">投资计算器</a></dd>
           </dl>
        </div>
        
        <div class="conbox_r">
         <h3><span>借款计算器</span></h3>
          <div class="conbox_r_nr">
              <P>目前支持网站正在使用的三种还款方式：按月分期还款、按月还息到期还本、到期还本息。</P>
              
              <div class="borrow_counter">
                 <div class="counter_title">借款设置</div>
                 <div class="b_counter_nr">
                    <ul>
                     <li><label>还款方式：</label>
                       <div id="uboxstyle">
                       <select name="return" id="return">
		                  <option value="1"  selected="selected">按月分期付款</option>
		                  <option value="2">每月还息，到期还本</option>
		                  <option value="3" >到期还本息</option>
                       </select>
                      </div>
                     </li> 
                    <li><label>借款金额：</label><input name="name" id="money" type="text" size="25" value="100000"/><em>元</em></li>
      
                    
                       
                    <li><label>借款期限：</label><input name="name" type="text" size="25" id="time" value="3"/>
                        
                         <em>
                         <input name="inptA" type="radio" id="inptA1"   onclick="checkradio(this,1)"checked="checked"  value="0"/>月
                         </em>
                           <em> <input name="inptA" type="radio" id="inptA2" onclick="checkradio(this,2);days();" value="1"/>日</em>  
                     </li>   
                     <li><label>借款奖励：</label><input name="name" id="away" type="text" size="25"value="0.6"/><em>%(无奖励填0)</em></li>  
                     
                      
                      <li style="width: 500px"><label>借款利率：</label><input name="name" type="text" size="25"  id="awayId"value="18" /><em >%</em>   
                           <em  >      <input name="inptB" type="radio" id="inptB1"  value="0" onclick="year()" checked="checked" value="0">年利率</input></em> 
                             <em>    <input name="inptB" type="radio" id="inptB2" value="1" onclick="day()" value="1">日利率</input>
                        </em>
                      </li>
                    </ul>
                   
                 </div>
                 <div class="count_btn"><input type="submit" class="submit_bt" value="开始计算" onclick="count()"/></div>
                <div  style="display: none;" id="resultnone">
                 <div class="counter_title">计算结果</div>
                 <div class="result">
                     <div class="return" > 总需还款额<span id="total"></span></div> 
                     <div class="interest" >需付利息<span id="rate"></span></div> 
                 </div>
                 </div>
                 <div style="display: none;" id="counter_title">
                <div class="counter_title">偿还时间表</div>
                <table width="100%" cellspacing="0" cellpadding="0" class="return_table"  id="table">
                         <thead> 
                             <tr id="head-color" >
                                <th>月份</th>
                                <th>月还款额</th>
                                <th>月还本金</th>
                                <th>月还利息</th>
                                <th>本金余额</th>
                            </tr>
                          </thead>
                            </table>
           
              </div>
              </div>
          </div>
        </div>
    </div>
</div>
<!--footer 开始-->
<div class="footer">
     <jsp:include page="../footer.jsp" />
</div>
<!--footer 结束-->


<!--右边漂浮 开始-->
<%-- <div id="service">
	<a href="<%=path%>/tool/borrow_tool.jsp" class="srvLog"   title="网贷计算器">网贷计算器</a>
	<a href="tencent://message/?uin=285772998&Site=企业网站&Menu=yes" class="srvCns" title="官方交流群">官方交流群</a>
	<a href="<%=path%>/tender/totenderList" class="srvDj"   title="投资理财">投资理财</a>
	<a class="goTop" id="goTop" title="返回顶部" style="display:none" >返回顶部</a>
</div> --%>

<!--右边漂浮 结束-->
<script type="text/javascript" src="../js/select2css.js"></script>
</body>
</html>