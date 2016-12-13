<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page import="com.opensymphony.xwork2.Action"%>
<%@page import="com.jqg.pojo.Uservip"%>
<%@page import="com.jqg.pojo.Website"%>
<%@page import="com.jqg.pojo.Manager"%>
<%@page import="com.jqg.service.impl.WebsiteServiceImpl"%>
<%@page import="com.jqg.service.WebsiteService"%>
<%@page import="com.jqg.service.ManagerService"%>
<%@page import="com.jqg.service.impl.ManagerServiceImpl"%>
<%@page import="com.jqg.pojo.Customerqqs"%>
<%@page import="com.jqg.service.impl.CustomerqqsServiceImpl"%>
<%@page import="com.jqg.service.CustomerqqsService"%>
<%@page import="com.jqg.pojo.Frontmenu"%>
<%@page import="java.util.List"%>
<%@page import="com.jqg.service.impl.FrontmenuServiceImpl"%>
<%@page import="com.jqg.service.FrontmenuService"%>
<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();if(path!=null && !path.equals("")){	if(path.contains("/")){		if(path.equals("/")){path = "";	}}else{	path += "/";}} 
String basePath = "";if(request.getServerPort()==80){basePath = request.getScheme()+"://"+request.getServerName()+path+"/";}else{basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";}    
%>
<%
FrontmenuService frontmenuService = new FrontmenuServiceImpl();
List<Frontmenu> frontmenus1 = frontmenuService.findFrontmenuByIdANdId(0,55);
Uservip uservip = (Uservip)ActionContext.getContext().getSession().get("uservip");

ManagerService managerService = new ManagerServiceImpl();
List<Manager> managers = managerService.findManagerBySql("select m.* from Manager m where m.isCustomer=1 and display=1");

CustomerqqsService customerqqsService = new CustomerqqsServiceImpl();
List<Customerqqs> customerqqss = customerqqsService.findCustomerqqbydisplay(1);
%>
<title>招米贷-医疗小额贷款理财首选网站，安全可靠额互联网金融平台</title>
<meta name="keywords" content="投资理财,小额贷款,骨科贷款,医疗贷款,互联网金融,上海交通大学互联网金融副会长单位" />
<meta name="description" content="招米贷是中国医疗金融理财开创者,首家专注于医疗行业网络金融与小额贷款的稳健高收益理财平台。具有最专业的的风控团队的上海交通大学互联网金融副会长单位。" />

<link rel="stylesheet" type="text/css" href="<%=path %>/ZMD/css/style.css"/>
<link rel="stylesheet" type="text/css" href="<%=path %>/css/public.css"/>
<div class="warp">
	<div class="header">
		<div class="v_top">
			<div class="v_top_con" style="clear: both;">
				<ul class="v_top_l">
					<li><span>您好，欢迎来到招米贷！</span></li>
					<li><a href="#">[在线客服]</a>
						<ul class="v_top_qq">
						<%if(managers!=null && managers.size()>0){ %>
						<% for(Manager manager:managers){ %>
						<li>
							<a  href="http://wpa.qq.com/msgrd?v=3&uin=<%=manager.getQq() %>&site=qq&menu=yes"
								target="_blank" hidefocus="true"><%=manager.getManagerName() %></a>
						</li>
						<% } %>
						<%} %>
						</ul>
					</li>
					
					<li><a style="color: #44a1eb;" href="#">[官方交流群]</a>
						<ul class="v_top_qq">
						<%if(customerqqss!=null && customerqqss.size()>0){ %>
						<% for(Customerqqs customerqq:customerqqss){ %>
						<li>
							<a href="http://shang.qq.com/wpa/qunwpa?idkey=<%=customerqq.getCustomerQqsnumber() %>"
								target="_blank" hidefocus="true"><%=customerqq.getCustomerQqsname()%></a>
						</li>
						<% } %>
						<%} %>
						</ul>
					</li>
				</ul>
				<ul class="v_top_r">
					<li><img src="<%=path %>/ZMD/images/phone.png"/><a href="#">手机客户端</a>
					<ul class="v_top_qq">
						<img class="v_fl" src="<%=path %>/ZMD/images/v_ewm_footer.png"/>
						</ul>
					</li>
						
				<!-- 	<li><a href="<%=path %>/news/new?flag=contact">联系我们</a></li> -->
					<!-- 	<li><a href="<%=path %>/guide/invest_guide.jsp">新手指引</a></li> -->
				
					<li><a class="v_help" href="<%=path %>/guide/invest_guide.jsp">帮助中心</a></li>
				</ul>
			</div>
		</div>
		<!--logo及右侧导航-->
	<div class="v_nav">
			<a href="<%=path %>/index.action"><img src="<%=path %>/ZMD/images/logo268.png" alt="招米贷-医疗小额贷款理财首选网站，安全可靠额互联网金融平台" 
			title="招米贷-医疗小额贷款理财首选网站，安全可靠额互联网金融平台"/></a>
			<a href="<%=path %>/index.action"><img style="display:none " src="<%=path %>/ZMD/images/logo12175.jpg" alt="招米贷-医疗小额贷款理财首选网站，安全可靠额互联网金融平台" 
			title="招米贷-医疗小额贷款理财首选网站，安全可靠额互联网金融平台"/></a>
			<dl class="v_fr">
			<c:if test="${empty uservip}">
				<dt class="v_fl"><a href="<%=path %>/login/login.jsp" title="登录">登录</a></dt>
				<dd class="v_fr"><a href="<%=path %>/login/reg1.jsp?long" title="免费注册">免费注册</a></dd>
			</c:if>
			<c:if test="${!empty uservip}">
				<dt class="v_fl"><a href="<%=path %>/user/login">您好，${uservip.userName}</a></dt>
				<dd class="v_fr"><a href="<%=path %>/user/loginout">安全退出</a></dd>
			</c:if>
			</dl>
			<ul class="v_nav_con">
		<%-- 	 <% for(Frontmenu frontmenu1:frontmenus1) { %>
				<li>
					<a href="<%=path  +frontmenu1.getUrl()%>"><%= frontmenu1.getName()%></a>
				</li>	
			<%}%>  --%>
			
				<li><a id="v_home_01" href="<%=path %>/index.action" title="首页">首页</a></li>
				<li><a id="v_home_02" href="<%=path %>/tender/totenderList" title="我要投资">我要投资</a></li>
				<li><a id="v_home_03" href="<%=path %>/borrow/bid.jsp" title="我要借款">我要借款</a></li>
				<li><a id="v_home_06" href="<%=path %>/user/login" title="我的账户">我的账户</a></li>
				<li><a id="v_home_04" href="<%=path %>/guide/invest_guide.jsp" title="新手指引">新手指引</a></li>
				<li><a id="v_home_05" href="<%=path %>/news/new?flag=Introduction" title="关于我们">关于我们</a></li>
			</ul>
		</div>
	</div>
</div>