package com.jqg.struts;

import java.io.InputStream;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.jqg.pojo.Customerphone;
import com.jqg.pojo.Friendlink;
import com.jqg.pojo.Lssuing;
import com.jqg.pojo.Lssuingherald;
import com.jqg.pojo.Moneycount;
import com.jqg.pojo.Rank;
import com.jqg.pojo.Tender;
import com.jqg.pojo.Uservip;
import com.jqg.pojo.Website;
import com.jqg.service.CustomerphoneService;
import com.jqg.service.CustomerqqsService;
import com.jqg.service.FriendlinkService;
import com.jqg.service.InveststrategyService;
import com.jqg.service.LatestnewsService;
import com.jqg.service.LssuingService;
import com.jqg.service.LssuingheraldService;
import com.jqg.service.MoneycountService;
import com.jqg.service.TenderService;
import com.jqg.service.UservipService;
import com.jqg.service.WebsiteService;
import com.jqg.service.impl.CustomerphoneServiceImpl;
import com.jqg.service.impl.CustomerqqsServiceImpl;
import com.jqg.service.impl.FriendlinkServiceImpl;
import com.jqg.service.impl.InveststrategyServiceImpl;
import com.jqg.service.impl.LatestnewsServiceImpl;
import com.jqg.service.impl.LssuingServiceImpl;
import com.jqg.service.impl.LssuingheraldServiceImpl;
import com.jqg.service.impl.MoneycountServiceImpl;
import com.jqg.service.impl.TenderServiceImpl;
import com.jqg.service.impl.UservipServiceImpl;
import com.jqg.service.impl.WebsiteServiceImpl;
import com.jqg.util.AccessValidation;
import com.jqg.util.DateUtil;
import com.jqg.util.SerialNumberUtil;
import com.opensymphony.xwork2.ActionContext;

/**
 * 系统加载时调用的action
 * 
 * @author Administrator
 * 
 */
public class IndexAction extends BaseAction {
	private static HttpServletRequest request;

	public static HttpServletRequest getRequest() {
		return request;
	}



	public static void setRequest(HttpServletRequest request) {
		IndexAction.request = request;
	}

	public String index() throws Exception {
		request=ServletActionContext.getRequest();
		//验证是否手机登录开始
		AccessValidation av=new AccessValidation();
		boolean boo=av.isMobileDevice(request);
		System.err.println("========:"+boo);
//		if(boo==true){
//			return "wap";
//		}
		//结束
		
		
		// 客服电话
		Customerphone cp = new Customerphone();
		CustomerphoneService cps = new CustomerphoneServiceImpl();
		cp = cps.findLastCustomerphone();
		ActionContext.getContext().getSession().put("customerphone", cp);

		FriendlinkService friendlinkService = new FriendlinkServiceImpl();
		// 根据索引查找连接并返回列表
		List<Friendlink> friendlinks = friendlinkService
				.findFriendlinksByIndex("pic");
		ActionContext.getContext().getSession().put("friendlink", friendlinks);

		
		List<Friendlink> confriendlinks = friendlinkService
				.findFriendlinksByIndex("con");
		ActionContext.getContext().getSession().put("confriendlinks", confriendlinks);
		
		
		
		String path = request.getContextPath();
		String address = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";

		ActionContext.getContext().getSession().put("address", address);
		
		// 加载配置文件
		// 生成输入流
		InputStream ins = IndexAction.class
				.getResourceAsStream("/config/config.properties");
		// 生成properties对象
		Properties p = new Properties();
		try {
			p.load(ins);
		} catch (Exception e) {
			e.printStackTrace();
		}

		boolean iskey = SerialNumberUtil.verificationkey(
				p.getProperty("clientname"), address,
				DateUtil.string2Date(p.getProperty("validstart")).getTime(),
				DateUtil.string2Date(p.getProperty("validend")).getTime(),
				p.getProperty("validday"), p.getProperty("key"),
				p.getProperty("key2"),p.getProperty("key3"));
//		if (!iskey) {
//			return "exp";
//		}
		
		
		
		
		
		
		

		WebsiteService websiteService = new WebsiteServiceImpl();

		MoneycountService moneycountService = new MoneycountServiceImpl();
		// 根据网站ID查询网站信息
		Website website = websiteService.findWebsiteBywebsiteId(1);
		LatestnewsService latestnewsService = new LatestnewsServiceImpl();
		// 根据图片查询最新的新闻列表
		List latestnewss = latestnewsService.findLatestnewssBypicture();
		InveststrategyService investstrategyService = new InveststrategyServiceImpl();
		LssuingService lssuingService = new LssuingServiceImpl();
		// 根据索引查询发布的信息列表
		
		String str="select * from Lssuing where  state=3 and lssuingType < 6 and recommend !='1'and recommend !='7' order by borrowTime desc";
		List<Lssuing> ls=lssuingService.findLssuingsIndex(str,2);
		str="select * from Lssuing where  state=2 and lssuingType < 6 and recommend !='1'and recommend !='7' order by borrowTime desc";
		List<Lssuing> lss= lssuingService.findLssuingsIndex(str,3);
		List<Lssuing> lssuingss=new ArrayList<Lssuing>();
		for(int i=0;i<lss.size();i++){
			lssuingss.add(lss.get(i));	
		}
		for(int i=0;i<ls.size();i++){
			lssuingss.add(ls.get(i));	
		}
		List<Lssuing> lssuings=new ArrayList<Lssuing>();
		for(Lssuing l:lssuingss){
			Double money=0.00;
			Set<Tender> set=l.getTenders();
			for(Tender t:set){
				money=money+t.getMoney();
			}
			int BorrowMoney = Integer.valueOf(l.getBorrowMoney());
	 		DecimalFormat df = new DecimalFormat();
	 		df.setMaximumFractionDigits(2);
	 		df.setMinimumFractionDigits(2);
	 		String k = df.format(money * 100.00 / BorrowMoney);
	 		double scale = Double.parseDouble(k);
			l.setScale(scale);
			l.setScales((int)scale);
			if(l.getPeriodday()!=null && l.getPeriodday().getPeriodDayId()!=null){
				l.setRates(Double.valueOf(l.getRate()*360));
			}else{
				l.setRates(l.getRate());
			}
			lssuings.add(l);

		}
		// 根据索引查询最新消息
		List latestnewss1 = latestnewsService
				.findLatestnewssByfrontMenuIdindex(36);
		List latestnewss2 = latestnewsService
				.findLatestnewssByfrontMenuIdindex(2);
		List latestnewss3 = latestnewsService
				.findLatestnewssByfrontMenuIdindex(5);
		List latestnewss4 = latestnewsService
				.findLatestnewssByfrontMenuIdindex(8);
				
		List latestnewss5 = latestnewsService
				.findLatestnewssByfrontMenuIdindex(3);
		// 查找网站的投资策略
		List investstrategies = investstrategyService
				.findInveststrategyByPage("SELECT * FROM investstrategy i ORDER BY i.askId DESC LIMIT 0,6");

		LssuingheraldService lssuingheraldService = new LssuingheraldServiceImpl();
		// 根据索引查询发布的预警信息
		Lssuingherald lssuingherald = lssuingheraldService
				.findLssuingheraldByindex();

		CustomerqqsService customerqqsService = new CustomerqqsServiceImpl();
		// 查询客户QQ列表
		List customerqqs = customerqqsService.findCustomerqqss();

		Uservip user = (Uservip) ActionContext.getContext().getSession()
				.get("uservip");

		// 排行榜
		// 日
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String date = df.format(new Date());
		String startDay = date + " 00:00:00";
		String endDay = date + " 23:59:59";
		// 周
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // 获取本周一的日期
		String startWeek = df.format(cal.getTime()) + " 00:00:00";
		// 这种输出的是上个星期周日的日期，因为老外那边把周日当成第一天
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		// 增加一个星期，才是我们中国人理解的本周日的日期
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		String endWeek = df.format(cal.getTime()) + " 23:59:59";
		// 月
		// 获取Calendar
		Calendar calendar = Calendar.getInstance();
		// 设置时间,当前时间不用设置
		// calendar.setTime(new Date());
		calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
		String startMonth = df.format(calendar.getTime()) + " 00:00:00";
		// 设置日期为本月最大日期
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		String endMonth = df.format(calendar.getTime()) + " 23:59:59";
		
		
		TenderService tenderService = new TenderServiceImpl();
		// 日排行榜
		List<Rank> dayList2 = tenderService.findTendersByDate(
				Timestamp.valueOf(startDay), Timestamp.valueOf(endDay));
		List<Rank> weekList2 = tenderService.findTendersByDate(
				Timestamp.valueOf(startWeek), Timestamp.valueOf(endWeek));
		List<Rank> monthList2 = tenderService.findTendersByDate(
				Timestamp.valueOf(startMonth), Timestamp.valueOf(endMonth));
		List<Rank> dayList =new ArrayList<Rank>();
		List<Rank> weekList =new ArrayList<Rank>();
		List<Rank> monthList =new ArrayList<Rank>();
		UservipService service=new UservipServiceImpl();
		for(int i=0;i<dayList2.size();i++){
			  Rank r=dayList2.get(i);
			  Uservip u=service.findUservipByUserid(Integer.parseInt(r.getId()));
			  String name=u.getUserName();
			  if (name.length() < 3)
		      {
				  name = name.substring(0, 1) + "***";
		      }
		      else
		      {
		    	  name = name.substring(0, 1) + "***" + name.substring(name.length() - 1, name.length());
		      }
			  r.setName(name);
			  dayList.add(r);
		}
		for(int i=0;i<weekList2.size();i++){
			  Rank r=weekList2.get(i);
			  Uservip u=service.findUservipByUserid(Integer.parseInt(r.getId()));
			  String name=u.getUserName();
			  if (name.length() < 3)
		      {
				  name = name.substring(0, 1) + "***";
		      }
		      else
		      {
		    	  name = name.substring(0, 1) + "***" + name.substring(name.length() - 1, name.length());
		      }
			  r.setName(name);
			  weekList.add(r);
		}
		for(int i=0;i<monthList2.size();i++){
			  Rank r=monthList2.get(i);
			  Uservip u=service.findUservipByUserid(Integer.parseInt(r.getId()));
			  String name=u.getUserName();
			  if (name.length() < 4)
		      {
				  name = name.substring(0, 1) + "***";
		      }
		      else
		      {
		    	  name = name.substring(0, 1) + "***" + name.substring(name.length() - 1, name.length());
		      }
			  r.setName(name);
			  monthList.add(r);
		}
		
		if (user != null && user.getUserId() > 0) {
			Moneycount moneycount = moneycountService
					.findMoneycountByuserId(user.getUserId().intValue());
			ActionContext.getContext().put("moneycount", moneycount);
		}
		String sql="SELECT count(*) as ucount FROM uservip";
		int usercount = service.findCount(sql);
		
		
		//新手标
	List<Lssuing> newLssing=new ArrayList<Lssuing>();
	List<Lssuing> newLssing1=new ArrayList<Lssuing>();
	String sqlStr="select * from lssuing where lssuingType='1' and state in (2,3) and recommend='1' ORDER BY borrowTime desc LIMIT 0,3";
	newLssing=lssuingService.findLssuingsBySearch(sqlStr);
	
	for(Lssuing l:newLssing){
		Double money=0.00;
		Set<Tender> set=l.getTenders();
		for(Tender t:set){
			money=money+t.getMoney();
		}
		int BorrowMoney = Integer.valueOf(l.getBorrowMoney());
 		DecimalFormat df1 = new DecimalFormat();
 		df1.setMaximumFractionDigits(2);
 		df1.setMinimumFractionDigits(2);
 		String k = df1.format(money * 100.00 / BorrowMoney);
 		double scale = Double.parseDouble(k);
		l.setScale(scale);
		l.setScales((int)scale);
		if(l.getPeriodday()!=null && l.getPeriodday().getPeriodDayId()!=null){
			l.setRates(Double.valueOf(l.getRate()*360));
		}else{
			l.setRates(l.getRate());
		}
		
		newLssing1.add(l);
	}	
	
	//医疗标
	List<Lssuing> ylLssing=new ArrayList<Lssuing>();
	List<Lssuing> ylLssing1=new ArrayList<Lssuing>();
	String sqlStr1="select * from lssuing where lssuingType='1' and state in (2,3) and recommend='7' ORDER BY borrowTime desc LIMIT 0,3";
	newLssing=lssuingService.findLssuingsBySearch(sqlStr1);
	for(Lssuing l:newLssing){
		Double money=0.00;
		Set<Tender> set=l.getTenders();
		for(Tender t:set){
			money=money+t.getMoney();
		}
		int BorrowMoney = Integer.valueOf(l.getBorrowMoney());
 		DecimalFormat df1 = new DecimalFormat();
 		df1.setMaximumFractionDigits(2);
 		df1.setMinimumFractionDigits(2);
 		String k = df1.format(money * 100.00 / BorrowMoney);
 		double scale = Double.parseDouble(k);
		l.setScale(scale);
		l.setScales((int)scale);
		if(l.getPeriodday()!=null && l.getPeriodday().getPeriodDayId()!=null){
			l.setRates(Double.valueOf(l.getRate()*360));
		}else{
			l.setRates(l.getRate());
		}
		
		ylLssing1.add(l);
	}		
	    ActionContext.getContext().put("UservipSize", usercount);
		ActionContext.getContext().put("websiteIndex", website);
		ActionContext.getContext().put("latestnewssIndex", latestnewss);
		ActionContext.getContext().put("userIndex", user);
		ActionContext.getContext().put("lssuingheraldIndex", lssuingherald);
		ActionContext.getContext().put("lssuingsIndex", lssuings);
		ActionContext.getContext().put("friendlinksIndex", friendlinks);
		ActionContext.getContext().put("latestnewss1Index", latestnewss1);
		ActionContext.getContext().put("latestnewss2Index", latestnewss2);
		ActionContext.getContext().put("latestnewss3Index", latestnewss3);
		ActionContext.getContext().put("latestnewss4Index", latestnewss4);
		ActionContext.getContext().put("latestnewss5Index", latestnewss5);
		ActionContext.getContext().put("investstrategiesIndex",
				investstrategies);
		ActionContext.getContext().put("customerqqs", customerqqs);

		ActionContext.getContext().put("dayList", dayList);
		ActionContext.getContext().put("weekList", weekList);
		ActionContext.getContext().put("monthList", monthList);
		
		ActionContext.getContext().put("newLssing", newLssing1);//新手标
		ActionContext.getContext().put("ylLssing", ylLssing1);//医疗标

		
		return "success";
	}
}