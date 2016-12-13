package com.jqg.struts;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.jqg.pojo.Area;
import com.jqg.pojo.Bankparameter;
import com.jqg.pojo.Basicinfor;
import com.jqg.pojo.Manager;
import com.jqg.pojo.Moneycount;
import com.jqg.pojo.Personalbankinfor;
import com.jqg.pojo.Systemconf;
import com.jqg.pojo.Translate;
import com.jqg.pojo.Uservip;
import com.jqg.pojo.Website;
import com.jqg.service.AreaService;
import com.jqg.service.BankparameterService;
import com.jqg.service.BasicinforService;
import com.jqg.service.MoneycountService;
import com.jqg.service.PersonalbankinforService;
import com.jqg.service.SystemconfService;
import com.jqg.service.TranslateService;
import com.jqg.service.UservipService;
import com.jqg.service.WebsiteService;
import com.jqg.service.impl.AreaServiceImpl;
import com.jqg.service.impl.BankparameterServiceImpl;
import com.jqg.service.impl.BasicinforServiceImpl;
import com.jqg.service.impl.MoneycountServiceImpl;
import com.jqg.service.impl.PersonalbankinforServiceImpl;
import com.jqg.service.impl.SystemconfServiceImpl;
import com.jqg.service.impl.TranslateServiceImpl;
import com.jqg.service.impl.UservipServiceImpl;
import com.jqg.service.impl.WebsiteServiceImpl;
import com.jqg.util.AccessValidation;
import com.jqg.util.LoanUtils;
import com.jqg.util.MD5Util;
import com.opensymphony.xwork2.ActionContext;

/**
 * 用户提现
 * 
 * @author Administrator
 * 
 */
public class UserBankAciton extends BaseAction {
	private static final long serialVersionUID = 1L;
	private int currentPage;
	private int pageSize = 10;
	private int totalPage;
	private String accountName;
	private String accountNum;
	private String accountAddress;
	private int bankPid;
	private int personalBankId;
	private String result;
	private Double affectMoney;
	private String payPwd;
	private String key;
	private Integer mark;
	private int parvalue;
	private Integer userId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	WebsiteService websiteService = new WebsiteServiceImpl();
	BankparameterService bankparameterService = new BankparameterServiceImpl();
	PersonalbankinforService personalbankinforService = new PersonalbankinforServiceImpl();
	Uservip uservip = (Uservip) ActionContext.getContext().getSession()
			.get("uservip");
	BasicinforService basicinforService = new BasicinforServiceImpl();
	MoneycountService moneycountService = new MoneycountServiceImpl();
	TranslateService translateService = new TranslateServiceImpl();
	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	UservipService uservipService = new UservipServiceImpl();
	Manager manager3 = (Manager) ActionContext.getContext().getSession()
			.get("manager");
	List<Personalbankinfor> BankList = new ArrayList<Personalbankinfor>();
	List<Translate> translates1 = new ArrayList<Translate>();
	private AreaService areaService = new AreaServiceImpl();
	private String postform;
	private int areaid;

	/**
	 * 跳转到用户银行列表
	 * 
	 * @return
	 */
	public String toUserBank() {
		return "success";
	}

	public String createtranslate() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		this.uservip = (Uservip) ActionContext.getContext().getSession()
				.get("uservip");
		AccessValidation av=new AccessValidation();
		boolean boo=av.isMobileDevice(request);  
		if (this.uservip == null) {
			if (this.userId != null && !this.userId.equals("")) {
				this.uservip = this.uservipService.findUservipByUserid(userId);
			}
		}
		if (this.uservip != null) {
			SystemconfService scService=new SystemconfServiceImpl();
			Systemconf sc=scService.findSystemconfByParname("con_money_lowest");
			String keys = (String) ActionContext.getContext().getSession()
					.get("key" + this.uservip.getUserId());

			Uservip uservip1 = this.uservipService
					.findUservipByPayPwdAndUserName(MD5Util.md5(this.payPwd),
							this.uservip.getUserName());
			if (uservip1 == null) {
				if(boo){
					this.setMessage("用户支付密码不正确","wap/totixian", "3");
				}else{
				this.setMessage("用户支付密码不正确",
						"/userbank/mention", "3");
				}
				return "jump";
			} else if (!keys.equals(this.key)) {
				if(boo){
					this.setMessage("验证码不正确","wap/totixian", "3");
				}else{
				this.setMessage("验证码不正确",
						"/userbank/mention", "3");
				}
				return "jump";
			} else if (Double.valueOf(String.valueOf(this.parvalue)) != 0
					&& Double.valueOf(String.valueOf(this.parvalue)) > Double
							.valueOf(this.affectMoney)) {
				if(boo){
					this.setMessage("提现金额输入错误，要小于可用余额","wap/totixian", "3");
				}else{
				this.setMessage("提现金额输入错误，要小于可用余额",
						"/userbank/mention", "3");
				}
				return "jump";
			}else if(sc.getParvalue()!=null&&!"".equals(sc.getParvalue())&&Double.valueOf(sc.getParvalue())>this.affectMoney){
						this.setMessage("提现金额小于最低体现金额",
								"/userbank/mention", "3");
						return "jump"; 
			} else {
				Personalbankinfor personalbankinfor = this.personalbankinforService
						.findPersonalbankinforById(this.bankPid);
				System.out.println(personalbankinfor == null);
				Moneycount moneycount = this.moneycountService
						.findMoneycountByuserId(this.uservip.getUserId()
								.intValue());
				Translate translate = new Translate();
				translate.setTranslateId(Integer.valueOf(1));
				translate.setUservip(this.uservip);
				translate.setAffectMoney(this.affectMoney);
				translate.setBankNum(personalbankinfor.getBankparameter()
						.getBankPname()
						+ ","
						+ personalbankinfor.getAccountAddress()
						+ ","
						+ personalbankinfor.getAccountNum());
				translate.setOccurTime(this.timestamp);
				translate.setAvailableBalance(Double.valueOf(moneycount
						.getAvailableMoney().doubleValue()
						- Double.valueOf(this.affectMoney.doubleValue())
								.doubleValue()));
				translate.setState(Integer.valueOf(0));
				translate.setBanktypeId(personalbankinfor.getBankparameter()
						.getBankPid());
				translate.setBankCityId(personalbankinfor.getArea().getAreaId());
				translate.setBankProvinceId(personalbankinfor.getArea()
						.getParentid());
				translate.setSerialnum("");

				boolean flag = this.translateService.addTranslate(translate);
				if (flag) {
					LoanUtils loanUtils = new LoanUtils();
					Website website = this.websiteService
							.findWebsiteBywebsiteId(1);
					Uservip cashuser = this.uservip;
					if(boo){
						cashuser.setUserNames("wap");
					}
					
					this.postform = loanUtils.loanWithdraws(request, website,
							translate, cashuser);
					return "loanjump";

				} else {
					this.result = "errors";
				}
			}
		}
		if(boo){
			this.setMessage("提现失败，请重新操作","wap/totixian", "3");
		}else{
		this.setMessage("提现失败，请重新操作",
				"/userbank/mention", "3");
		}
		System.err.println("======提现到这了====："+this.uservip);
		return "jump";

	}

	/**
	 * 提现
	 * 
	 * @return
	 * @throws Exception
	 */
	public String mention() throws Exception {

		List<Personalbankinfor> personalbankinfors = personalbankinforService
				.findPersonalbankinforsByuser(uservip.getUserId());
		for (Personalbankinfor person : personalbankinfors) {
			if (person.getAccountNum().length() > 8) {
				String number = person.getBankparameter().getBankPname()
						+ person.getAccountNum().substring(0, 4)
						+ "*** *** ***"
						+ person.getAccountNum().substring(
								person.getAccountNum().length() - 4,
								person.getAccountNum().length());
				person.setAccountNums(number);
			} else {
				person.setAccountNums(person.getAccountNum());
			}

			BankList.add(person);
		}

		Website website = this.websiteService.findWebsiteBywebsiteId(1);
		Basicinfor basicinfor = this.basicinforService
				.findBasicinforByUserId(this.uservip.getUserId().intValue());
		Moneycount moneycount = this.moneycountService
				.findMoneycountByuserId(this.uservip.getUserId().intValue());
		if (website.getTranslateLimit().intValue() == 0) {
			ActionContext.getContext().getSession()
					.put("translateLimit", "无限额");
		} else {
			ActionContext
					.getContext()
					.getSession()
					.put("translateLimit",
							"每日最高提现为" + website.getTranslateLimit() + "元");
		}
		SystemconfService systemconfService = new SystemconfServiceImpl();
		Systemconf latefee = systemconfService
				.findSystemconfByParname("con_money_lowest");// 最低提现金额
		ActionContext.getContext().getSession()
				.put("bankbasicinfor", basicinfor);
		ActionContext.getContext().getSession()
				.put("limit", website.getTranslateLimit());
		ActionContext.getContext().getSession()
				.put("bankAvailableMoney", moneycount.getAvailableMoney());
		ActionContext.getContext().put("latefee", latefee);
		return "success";
	}

	/**
	 * 显示审核
	 * 
	 * @throws Exception
	 */
	public String showTranslate() throws Exception {
		List<Personalbankinfor> personalbankinfors = personalbankinforService
				.findPersonalbankinforsByuser(uservip.getUserId());
		for (Personalbankinfor person : personalbankinfors) {
			String number = person.getBankparameter().getBankPname()
					+ person.getAccountNum().substring(0, 4)
					+ "*** *** ***"
					+ person.getAccountNum().substring(
							person.getAccountNum().length() - 4,
							person.getAccountNum().length());
			person.setAccountNums(number);
			BankList.add(person);
		}
		this.mark = null;
		List<Translate> translates = this.translateService
				.findTranslatesByuser(uservip.getUserId().intValue());
		int totalRecord = translates.size();
		if (totalRecord % this.pageSize == 0)
			this.totalPage = (totalRecord / this.pageSize);
		else {
			this.totalPage = (totalRecord / this.pageSize + 1);
		}
		if (this.currentPage >= this.totalPage) {
			this.currentPage = this.totalPage;
		}
		if (this.currentPage <= 1) {
			this.currentPage = 1;
		}

		translates1 = new ArrayList<Translate>();
		List<Translate> list = this.translateService.findTranslatesByuserPage(
				uservip.getUserId().intValue(), (this.currentPage - 1)
						* this.pageSize, this.pageSize);
		for (int i = 0; i < list.size(); i++) {
			Translate b = list.get(i);
			String num = b.getBankNum().substring(0, 4)
					+ "*** *** ***"
					+ b.getBankNum().substring(b.getBankNum().length() - 4,
							b.getBankNum().length());
			b.setBankNums(num);
			translates1.add(b);
		}
		this.mark = Integer.valueOf(1);
		return "success";
	}

	/**
	 * 个人银行卡添加
	 * 
	 * @return
	 * @throws Exception
	 */
	public String createUserBank() throws Exception {
		Basicinfor basicinfor = this.basicinforService
				.findBasicinforByUserId(this.uservip.getUserId().intValue());
		Area area = this.areaService.findAreaByAreaId(this.areaid);
		Bankparameter bankparameter = this.bankparameterService
				.findBankparameterBybankPid(this.bankPid);
		Personalbankinfor personalbankinfor = new Personalbankinfor();
		personalbankinfor.setAccountAddress(this.accountAddress);
		personalbankinfor.setAccountName(basicinfor.getRealName());
		personalbankinfor.setAccountNum(this.accountNum);
		personalbankinfor.setUservip(this.uservip);
		personalbankinfor.setBankparameter(bankparameter);
		personalbankinfor.setArea(area);
		boolean flag = this.personalbankinforService
				.createPersonalbankinfor(personalbankinfor);
		if (flag) {
			this.result = "success";
		} else {
			this.result = "error";
		}
		return "success";
	}

	/**
	 * 个人银行卡添加
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateUserBank() throws Exception {
		Basicinfor basicinfor = this.basicinforService
				.findBasicinforByUserId(this.uservip.getUserId().intValue());
		Area area = this.areaService.findAreaByAreaId(this.areaid);
		Bankparameter bankparameter = this.bankparameterService
				.findBankparameterBybankPid(this.bankPid);
		Personalbankinfor personalbankinfor = this.personalbankinforService
				.findPersonalbankinforById(this.personalBankId);
		personalbankinfor.setAccountAddress(this.accountAddress);
		personalbankinfor.setAccountName(basicinfor.getRealName());
		personalbankinfor.setAccountNum(this.accountNum);
		personalbankinfor.setUservip(this.uservip);
		personalbankinfor.setArea(area);
		personalbankinfor.setBankparameter(bankparameter);
		boolean flag = this.personalbankinforService
				.updatePersonalbankinfor(personalbankinfor);
		if (flag) {
			this.result = "success";
		} else {
			this.result = "error";
		}
		return "success";
	}

	/**
	 * 删除用户的银行
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deleteUserBank() throws Exception {
		Personalbankinfor personalbankinfor = this.personalbankinforService
				.findPersonalbankinforById(this.personalBankId);
		boolean flag = this.personalbankinforService
				.deletePersonalbankinfor(personalbankinfor);
		return "success";
	}

	/**
	 * 查找用户的银行
	 * 
	 * @return
	 * @throws Exception
	 */
	public String findUserBank() throws Exception {
		List bankparameters = this.bankparameterService.findBankparameters();
		ActionContext.getContext().put("userbanks", bankparameters);
		Personalbankinfor personalbankinfor = this.personalbankinforService
				.findPersonalbankinforById(this.personalBankId);

		Area bankarea = personalbankinfor.getArea();
		List<Area> cityareas = this.areaService.findAreasByParentid(bankarea
				.getParentid());
		List<Area> areaList = this.areaService.findAreasByParentid(0);
		ActionContext.getContext().put("areas", areaList);
		ActionContext.getContext().put("cityareas", cityareas);
		ActionContext.getContext().put("bankarea", bankarea);

		ActionContext.getContext().getSession()
				.put("userBank", personalbankinfor);
		return "success";
	}

	/**
	 * 用户的银行列表
	 * 
	 * @throws Exception
	 */
	public void showUserBank() throws Exception {
		List personalbankinfors = this.personalbankinforService
				.findPersonalbankinforsByuser(this.uservip.getUserId()
						.intValue());
		int totalRecord = personalbankinfors.size();
		if (totalRecord % this.pageSize == 0)
			this.totalPage = (totalRecord / this.pageSize);
		else {
			this.totalPage = (totalRecord / this.pageSize + 1);
		}
		if (this.currentPage >= this.totalPage) {
			this.currentPage = this.totalPage;
		}
		if (this.currentPage <= 1) {
			this.currentPage = 1;
		}

		List<Personalbankinfor> personalbankinforspage = this.personalbankinforService
				.findPersonalbankinforsByuserPage(this.uservip.getUserId()
						.intValue(), (this.currentPage - 1) * this.pageSize,
						this.pageSize);
		StringBuffer str = new StringBuffer();
		str.append("{\"totalPage\":\"" + this.totalPage + "\",");
		str.append("\"currentPage\":\"" + this.currentPage + "\",");
		str.append("\"jsonRoots\":[");
		for (Personalbankinfor person : personalbankinforspage) {
			String number = person.getAccountNum().substring(0, 4)
					+ "*** *** ***"
					+ person.getAccountNum().substring(
							person.getAccountNum().length() - 4,
							person.getAccountNum().length());
			str.append("{\"picture\":\""
					+ person.getBankparameter().getBankPnumber() + "\",");
			str.append("\"name\":\"" + person.getAccountName() + "\",");
			str.append("\"number\":\"" + number + "\",");
			str.append("\"id\":\"" + person.getPersonalBankInforId() + "\",");
			str.append("\"address\":\"" + person.getAccountAddress() + "\"},");
		}
		str.deleteCharAt(str.lastIndexOf(","));
		str.append("]}");

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {
			response.getWriter().print(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查找银行列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String showBank() throws Exception {
		List bankparameters = this.bankparameterService.findBankparameters();
		Basicinfor basicinfor = this.basicinforService
				.findBasicinforByUserId(this.uservip.getUserId().intValue());

		List<Area> areaList = this.areaService.findAreasByParentid(0);
		ActionContext.getContext().put("areas", areaList);

		ActionContext.getContext().put("bankbasicinfor", basicinfor);
		ActionContext.getContext().put("userbanks", bankparameters);
		return "success";
	}

	public void ajaxareas() throws Exception {
		List<Area> areaList = this.areaService.findAreasByParentid(this.areaid);
		String str = "<option value='0'>-请选择地区-</option>";
		if (areaList != null && areaList.size() > 0) {
			for (int i = 0; i < areaList.size(); i++) {
				Area area = areaList.get(i);
				str += "<option value='" + area.getAreaId() + "'>" + area.getName()
						+ "</option>";
			}
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(str);
		response.getWriter().flush();
		response.getWriter().close();
	}

	public String getAccountName() {
		return this.accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountNum() {
		return this.accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public String getAccountAddress() {
		return this.accountAddress;
	}

	public void setAccountAddress(String accountAddress) {
		this.accountAddress = accountAddress;
	}

	public int getBankPid() {
		return this.bankPid;
	}

	public void setBankPid(int bankPid) {
		this.bankPid = bankPid;
	}

	public int getPersonalBankId() {
		return this.personalBankId;
	}

	public void setPersonalBankId(int personalBankId) {
		this.personalBankId = personalBankId;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getCurrentPage() {
		return this.currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return this.totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public Double getAffectMoney() {
		return this.affectMoney;
	}

	public void setAffectMoney(Double affectMoney) {
		this.affectMoney = affectMoney;
	}

	public String getPayPwd() {
		return this.payPwd;
	}

	public void setPayPwd(String payPwd) {
		this.payPwd = payPwd;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<Personalbankinfor> getBankList() {
		return BankList;
	}

	public void setBankList(List<Personalbankinfor> bankList) {
		BankList = bankList;
	}

	public Uservip getUservip() {
		return uservip;
	}

	public void setUservip(Uservip uservip) {
		this.uservip = uservip;
	}

	public Integer getMark() {
		return mark;
	}

	public void setMark(Integer mark) {
		this.mark = mark;
	}

	public List<Translate> getTranslates1() {
		return translates1;
	}

	public void setTranslates1(List<Translate> translates1) {
		this.translates1 = translates1;
	}

	public int getParvalue() {
		return parvalue;
	}

	public void setParvalue(int parvalue) {
		this.parvalue = parvalue;
	}

	public String getPostform() {
		return postform;
	}

	public void setPostform(String postform) {
		this.postform = postform;
	}

	public int getAreaid() {
		return areaid;
	}

	public void setAreaid(int areaid) {
		this.areaid = areaid;
	}

}