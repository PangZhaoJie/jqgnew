<%@page import="com.jqg.pojo.Website"%>
<%@page import="com.jqg.service.impl.WebsiteServiceImpl"%>
<%@page import="com.jqg.service.WebsiteService"%>
<%@page import="com.jqg.pojo.Frontmenu"%>
<%@page import="com.jqg.pojo.Customerqqs"%>
<%@page import="com.jqg.service.CustomerqqsService"%>
<%@page import="com.jqg.service.impl.CustomerqqsServiceImpl"%>
<%@page import="java.util.List"%>
<%@page import="com.jqg.pojo.Manager"%>
<%@page import="com.jqg.service.impl.FrontmenuServiceImpl"%>
<%@page import="com.jqg.service.FrontmenuService"%>
<%@page import="com.jqg.service.ManagerService"%>
<%@page import="com.jqg.service.impl.ManagerServiceImpl"%>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%
String path = request.getContextPath();if(path!=null && !path.equals("")){	if(path.contains("/")){		if(path.equals("/")){path = "";	}}else{	path += "/";}} 
String basePath = "";if(request.getServerPort()==80){basePath = request.getScheme()+"://"+request.getServerName()+path+"/";}else{basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";}    
%>
<%
FrontmenuService frontmenuService = new FrontmenuServiceImpl();
List<Frontmenu> frontmenus1 = frontmenuService.findFrontmenuByIdANdId(0,28);
List<Frontmenu> frontmenus2 = frontmenuService.findFrontmenuByIdANdId(26,38);
WebsiteService websiteService = new WebsiteServiceImpl();
Website website = websiteService.findWebsiteBywebsiteId(1);
CustomerqqsService customerqqsService = new CustomerqqsServiceImpl();
List<Customerqqs> customerqqss = customerqqsService.findCustomerqqbydisplay(1);

ManagerService managerService = new ManagerServiceImpl();
List<Manager> managers = managerService.findManagerBySql("select m.* from Manager m where m.isCustomer=1 and display=1");

%>
<div class="wrap">
	<div class="footer">
		<div class="v_footer">
			<div class="v_footer_box">
				<dl class="v_footer_top">
					<dt>
						<div class="v_fl v_footer_kefu">
							<p>客服专线:<b>${customerphone.customerphoneNumber}</b></p>
							<p>客服服务时间：9:00-21:00</p>
							<!--<em>关注我们：<a href="#"><img src="<%=path %>/ZMD/images/weixin.png" /></a><a href="#"><img src="<%=path %>/ZMD/images/weibo.png" /></a></em>	-->								
						</div>
						<!--  手机app扫描下载区域隐藏-->
						<div class="footerimg">
							<img src="<%=path %>/ZMD/images/v_ewm_footer.png"/><br/>
							<p><span>下载招米贷APP</span><br/><span>随时随地理财</span></p>
						</div>
						<div class="footerimg">
							<img src="<%=path %>/ZMD/images/erweima.png"/><br/>
							<p ><span>微信关注招米贷</span><br/><span>随时随地理财</span></p>
						</div>
					</dt>
					<dd>
						<p>我要投资</p>
						<a href="http://chinamibank.com/news/newdetail?newsId=184">关于提现</a>
						<a href="http://chinamibank.com/login/login.jsp">自动投标</a>
						<a href="http://chinamibank.com/news/newdetail?newsId=183">常见问题</a>
						<a href="http://chinamibank.com/guide/invest_guide.jsp">新手指引</a>
					</dd>
					<dd>
						<p>安全保障</p>
						<a href="http://chinamibank.com/news/new?flag=natural">公司资质</a>
						<a href="http://chinamibank.com/news/new?flag=control">风控保障</a>
						<a href="http://chinamibank.com/news/new?flag=policies">政策法规</a>
						<a href="http://chinamibank.com/news/newdetail?newsId=189">阿里云金融</a>
					</dd>
					<dd>
						<p>关于平台</p>
						<a href="http://chinamibank.com/news/new?flag=Introduction">公司简介</a>
						<a href="http://chinamibank.com/news/new?flag=ambient">企业文化</a>
						<a href="http://chinamibank.com/news/new?flag=recruit">平台招聘</a>
						<a href="http://chinamibank.com/news/new?flag=contact">联系我们</a>
					</dd>							
				</dl>						
			</div>
			<div class="v_beian">
				<%=website.getWebfoot()  %>
				<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1259414644'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s95.cnzz.com/z_stat.php%3Fid%3D1259414644%26show%3Dpic2' type='text/javascript'%3E%3C/script%3E"));</script>
        <div style="text-align: center;">
             <a  key ="578740d5efbfb05002f29613"  logo_size="124x47"  logo_type="business"  href="http://www.anquan.org" ><script src="http://static.anquan.org/static/outer/js/aq_auth.js"></script></a>
		<a href="http://218.242.124.22:8081/businessCheck/verifKey.do?showType=extShow&serial=9031000020160406234751000000530255-SAIC_SHOW_310000-20160714142512197091&signData=MEUCIQDF2pXwv58Hlo8QfK/daVzeWJeDL4Fw+WCCnq/w/GmxCQIgG7cSp14ax+1KwB92lr5ZiZ05oo8BD73aiiSWHjmnwtw=" target="_blank"><img alt="" src="<%=path %>/ZMD/images/lz2.jpg"></a>
		<!--<a href="http://webscan.360.cn/index/checkwebsite/url/chinamibank.com"><img border="0" src="http://img.webscan.360.cn/status/pai/hash/c68a9573666b46a377a678cf5617a7d9"/></a>-->
		<a href="http://webscan.360.cn/index/checkwebsite/url/www.chinamibank.com"><img border="0" src="<%=path%>/img/360.png"></img></a>
		</div>
			</div>
		</div>
	</div>
	<!--右边漂浮 开始-->
<ul class="float-rig">
    <li class="l0">
    	<b class="amn2 b0 png"></b>
        <div class="box amn3">
        	
        	<div><h1>在线客服</h1>
        	<ul class="ull">
        	<%if(managers!=null && managers.size()>0){ %>
						<% for(Manager manager:managers){ %>
						<li>
							<a  href="http://wpa.qq.com/msgrd?v=3&uin=<%=manager.getQq() %>&site=qq&menu=yes"
								target="_blank" hidefocus="true"><%=manager.getManagerName() %></a>
						</li>
						<% } %>
						<%} %>
						</ul>
        	</div>
            <p></p>
        </div>
    </li>
    <li class="l1">
    	<b class="amn2 b2 png"></b>
        <div class="box amn3">
        	<div><img width="90" src="<%=path %>/ZMD/images/v_ewm_footer.png"><h1>请扫二维码<br>下载${websiteinfo.webName}</h1></div>
            <p></p>
        </div>
    </li>
    <li class="l2">
    	<b class="amn2 b1 png"></b>
        <div class="box amn3">
        	<div><h1>微信扫一扫</h1><img width="90" src="<%=path %>/ZMD/images/erweima.png">投资理财<br>咨询微信号</div>
            <p></p>
        </div>
    </li>
    <li class="l4 amn3"  id="goTop" style="display: none;"><b class="amn2 b4 png"></b></li>
</ul>
<script type="text/javascript" src="<%=path %>/home/js/service.js"></script><!--右边漂浮 结束-->
</div>