package com.jqg.app;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.jqg.app.session.MySessionContext;
import com.jqg.pojo.Bankparameter;
import com.jqg.pojo.Basicinfor;
import com.jqg.pojo.Lssuing;
import com.jqg.pojo.Moneycount;
import com.jqg.pojo.Moneyhistorydetail;
import com.jqg.pojo.Personalbankinfor;
import com.jqg.pojo.Tender;
import com.jqg.pojo.Uservip;
import com.jqg.service.BankparameterService;
import com.jqg.service.BasicinforService;
import com.jqg.service.LssuingService;
import com.jqg.service.MoneycountService;
import com.jqg.service.MoneyhistorydetailService;
import com.jqg.service.PersonalbankinforService;
import com.jqg.service.TenderService;
import com.jqg.service.UservipService;
import com.jqg.service.impl.BankparameterServiceImpl;
import com.jqg.service.impl.BasicinforServiceImpl;
import com.jqg.service.impl.LssuingServiceImpl;
import com.jqg.service.impl.MoneycountServiceImpl;
import com.jqg.service.impl.MoneyhistorydetailServiceImpl;
import com.jqg.service.impl.PersonalbankinforServiceImpl;
import com.jqg.service.impl.TenderServiceImpl;
import com.jqg.service.impl.UservipServiceImpl;
import com.jqg.util.MD5Util;
import com.opensymphony.xwork2.ActionContext;

public class UserCenterAction extends AppBaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7324566391036532031L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private MoneycountService moneycountService = new MoneycountServiceImpl();
	private UservipService userService = new UservipServiceImpl();
	private MoneyhistorydetailService moneyhistorydetailService = new MoneyhistorydetailServiceImpl();
	private TenderService tenderService = new TenderServiceImpl();
	private LssuingService lssuingService = new LssuingServiceImpl();
	private PersonalbankinforService personalbankinforService = new PersonalbankinforServiceImpl();
	private BankparameterService bankparameterService = new BankparameterServiceImpl();
	private BasicinforService basicinforService = new BasicinforServiceImpl();
	private String message;
	/**
	 * 查找个人帐户的余额
	 * */
	public void getAccountInfo()throws Exception{
		this.request = ServletActionContext.getRequest();
		this.response = ServletActionContext.getResponse();
		MySessionContext myc= MySessionContext.getInstance();
		HttpSession sess = myc.getSession(request.getParameter("sessionId"));
		Uservip uservip = (Uservip) sess.getAttribute(request.getParameter("uservip"));
		Moneycount moneycount=this.moneycountService.findMoneycountByuserId(uservip.getUserId().intValue());
		Uservip user = userService.findUservipByUserid(uservip.getUserId().intValue());
		ActionContext.getContext().put("moneycount", moneycount);
		String state="";
		String message="";
		String realnameState="";
		String realnameMessage="";
		String phoneState="";
		String phoneMessage="";
		String mailState="";
		String mailMessage="";
		if(user.getTrustStatus() == 1 && user.getTrustAccount() != null && !user.getTrustAccount().equals("")){
			if(user.getAuthorizeStatus()==0){
				state="1";//未授权
				message="未授权";
			}else{
				state="2";
				message="已授权";
			}
		}else{
			state="3";//未开通托管
			message="未开通托管";
		}
		//实名认证信息
		if(user.getNameResult() == null || "".equals(user.getNameResult())){
			realnameState="1";//未实名认证
			realnameMessage="未实名认证";
		}else if ("2".equals(user.getNameResult())){
			realnameState="2";//已实名认证
			realnameMessage="已实名认证";
		}else{
			realnameState="3";
			realnameMessage="审核中";
		}
		//手机认证信息
		if (user.getPhoneResult() == null ||"".equals(user.getPhoneResult())) {
			phoneState="1";
			phoneMessage="手机未认证";
		} else if ("1".equals(user.getPhoneResult())) {	
			phoneState="2";
			phoneMessage="手机已认证";
		} else {
			phoneState="1";
			phoneMessage="手机未认证";
		}
		//邮箱认证信息
		if(uservip.getMail()!=null && !"".equals(uservip.getMail()) && uservip.getEnable()!=null){
			if(uservip.getEnable()==1){
				mailState="1";
				mailMessage="邮箱已认证";
			}
		}else if(uservip.getMail()!=null&&uservip.getEnable()==null){
			//已认证邮箱地址但未点击链接激活
			mailState="2";
			mailMessage="请激活邮箱";
		}else{
			mailState="3";
			mailMessage="邮箱未认证";
		}
		StringBuffer str = new StringBuffer();
		str.append("{");
		str.append("\"availableMoney\":\"" + moneycount.getAvailableMoney() + "\",");//帐户余额
		str.append("\"frozenMoney\":\"" + moneycount.getFrozenMoney() + "\",");//冻结金额
		str.append("\"collectTotalMoney\":\"" + moneycount.getCollectTotalMoney() + "\",");//待收金额
		str.append("\"quota\":\"" + user.getQuota() + "\",");//信用额度
		str.append("\"state\":\"" + state + "\",");
		str.append("\"message\":\"" + message + "\",");
		str.append("\"realnameState\":\"" + realnameState + "\",");
		str.append("\"realnameMessage\":\"" + realnameMessage + "\",");
		str.append("\"phoneState\":\"" + phoneState + "\",");
		str.append("\"phoneMessage\":\"" + phoneMessage + "\",");
		str.append("\"mailState\":\"" + mailState + "\",");
		str.append("\"mailMessage\":\"" + mailMessage + "\",");
		str.deleteCharAt(str.lastIndexOf(","));
		str.append("}");
		response.setContentType("text/json;charset=UTF-8");
		try {
			response.getWriter().print(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 个人认证信息
	 */
	public void certificationInfo(){
		this.request = ServletActionContext.getRequest();
		this.response = ServletActionContext.getResponse();
		MySessionContext myc= MySessionContext.getInstance();
		HttpSession sess = myc.getSession(request.getParameter("sessionId"));
		Uservip uservip = (Uservip) sess.getAttribute(request.getParameter("uservip"));
		try {
		Basicinfor bas = this.basicinforService.findBasicinforByUserId(uservip.getUserId());
		StringBuffer str = new StringBuffer();
		str.append("{");
		//实名认证信息
		if (uservip.getNameResult() == null) {
			str.append("\"realName\":\"" + "" + "\",");
			str.append("\"IDNum\":\"" + "" + "\",");
			str.append("\"nameState\":\"" + "0" + "\",");
			this.message = "未实名认证";
		} else if ("2".equals(uservip.getNameResult())) {
			String IDNum = bas.getIdNum().substring(0, 2) + "*** *** *** ***" + bas.getIdNum().substring(bas.getIdNum().length() - 2, bas.getIdNum().length());
			str.append("\"realName\":\"" + uservip.getRealName() + "\",");
			str.append("\"IDNum\":\"" + IDNum + "\",");
			str.append("\"nameState\":\"" + "1" + "\",");
			this.message = "已实名认证";
		} else {
			str.append("\"realName\":\"" +""+ "\",");
			str.append("\"IDNum\":\"" + "" + "\",");
			str.append("\"nameState\":\"" + "2" + "\",");
			this.message = "审核中";
		}
		//手机认证信息
		if (uservip.getPhoneResult() == null) {
			str.append("\"phoneNum\":\"" + "" + "\",");
			str.append("\"phoneState\":\"" + "0" + "\",");
			this.message = "手机未认证";
		} else if ("1".equals(uservip.getPhoneResult())) {	
			str.append("\"phoneNum\":\"" + bas.getPhoneNum() + "\",");
			str.append("\"phoneState\":\"" + "1" + "\",");
			this.message = "手机已认证";
		} else {
			str.append("\"phoneNum\":\"" +""+ "\",");
			str.append("\"phoneState\":\"" + "0" + "\",");
			this.message = "手机未认证";
		}
		//邮箱认证信息
		if(uservip.getMail()!=null && !"".equals(uservip.getMail()) && uservip.getEnable()!=null){
			if(uservip.getEnable()==1){
				str.append("\"mail\":\"" + uservip.getMail() + "\",");
				str.append("\"mailState\":\"" + "1" + "\",");
				this.message = "邮箱已认证";
			}
		}else if(uservip.getMail()!=null&&uservip.getEnable()==null){
			//已认证邮箱地址但未点击链接激活
			str.append("\"mail\":\"" +""+ "\",");
			str.append("\"mailState\":\"" + "2" + "\",");
			this.message = "请激活邮箱！！";
		}else{
			str.append("\"mail\":\"" +""+ "\",");
			str.append("\"mailState\":\"" + "0" + "\",");
			this.message = "邮箱未认证";
		}
		
		str.deleteCharAt(str.lastIndexOf(","));
		str.append("}");
		response.setContentType("text/json;charset=UTF-8");
		
			response.getWriter().print(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 查找资金记录列表
	 * */
	public void fundsRecordInfo()throws Exception{
		this.request = ServletActionContext.getRequest();
		this.response = ServletActionContext.getResponse();
		MySessionContext myc= MySessionContext.getInstance();
		HttpSession sess = myc.getSession(request.getParameter("sessionId"));
		Uservip uservip = (Uservip) sess.getAttribute(request.getParameter("uservip"));
		int currentPage = Integer.valueOf(request.getParameter("currentPage"));//当前页
	    int pageSize = Integer.valueOf(request.getParameter("pageSize"));//每页的条数据
		List<Moneyhistorydetail> list = moneyhistorydetailService.findMoneyhistorydetailBySql("select * from Moneyhistorydetail m where m.userId=" + uservip.getUserId().intValue()
				+" order by m.moneyHistoryDetailId desc LIMIT " +  (currentPage - 1) * pageSize + "," + pageSize);
		StringBuffer str = new StringBuffer();
		str.append("{");
		str.append("\"record_list\":");
		str.append("[");
		if(list.size()!=0){
		for(int i=0;i<list.size();i++){
			str.append("{\"affectMoney\":\"" + list.get(i).getAffectMoney() + "\",");//影响金额
			str.append("\"availableBalance\":\"" + list.get(i).getAvailableBalance() + "\",");//可用余额
			str.append("\"frozenMoney\":\"" + list.get(i).getFrozenMoney() + "\",");//冻结金额
			str.append("\"collectMoney\":\"" + list.get(i).getCollectMoney() + "\",");//待收金额
			if(i==(list.size()-1)){
				str.append("\"occurTime\":\"" + list.get(i).getOccurTime() + "\"}");//发生日期
			}else{
				str.append("\"occurTime\":\"" + list.get(i).getOccurTime() + "\"},");
			}
		}
			str.append("],");
			str.append(",\"state\":\"" + 1 + "\"");//有资金记录
			str.deleteCharAt(str.lastIndexOf(","));
			str.append("}");
			response.setContentType("text/json;charset=UTF-8");
			try {
				response.getWriter().print(str);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			str.append("],");
			str.append("\"state\":\"" + 0 + "\"");//没有资金记录
			str.append("}");
			response.setContentType("text/json;charset=UTF-8");
			try {
				response.getWriter().print(str);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		 
	}
/**
 * 查找个人投资记录列表        
 * */
	public void findInvestRecord()throws Exception{
		this.request = ServletActionContext.getRequest();
		this.response = ServletActionContext.getResponse();
		MySessionContext myc= MySessionContext.getInstance();
		HttpSession sess = myc.getSession(request.getParameter("sessionId"));
		Uservip uservip = (Uservip) sess.getAttribute(request.getParameter("uservip"));
		int currentPage = Integer.valueOf(request.getParameter("currentPage"));//当前页
	    int pageSize = Integer.valueOf(request.getParameter("pageSize"));//每页的条数据
		String sql = "select * from Tender t where t.userId="+uservip.getUserId().intValue()+" order by t.tenderId desc LIMIT " + (currentPage - 1) * pageSize + "," + pageSize;
		List<Tender> tender = tenderService.findTendersBylssuingIdPage(sql);
		StringBuffer str = new StringBuffer();
		str.append("{");
		str.append("\"record_list\":");
		str.append("[");
		if(tender.size()!=0){
			for(int i=0;i<tender.size();i++){
				Lssuing lssuing = lssuingService.findLssuingById(tender.get(i).getLssuing().getLssuingId());
				str.append("{\"title\":\"" + lssuing.getTitle() + "\",");//投资项目名称
				str.append("\"rate\":\"" + lssuing.getRate() + "\",");//所投资项目的利率
				str.append("\"money\":\"" + tender.get(i).getMoney() + "\",");//所投的金额
				if(i==(tender.size()-1)){
					str.append("\"tenderTime\":\"" + tender.get(i).getTenderTime() + "\"}");//投资的时间
				}else{
					str.append("\"tenderTime\":\"" + tender.get(i).getTenderTime() + "\"},");
				}
			}
			str.append("],");
			str.append(",\"state\":\"" + 1 + "\"");//有投资记录
			str.deleteCharAt(str.lastIndexOf(","));
			str.append("}");
			response.setContentType("text/json;charset=UTF-8");
			try {
				response.getWriter().print(str);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			str.append("],");
			str.append("\"state\":\"" + 0 + "\"");//没有投资记录
			str.append("}");
			response.setContentType("text/json;charset=UTF-8");
			try {
				response.getWriter().print(str);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 获取绑定银行卡列表
	 * */
	public void getBankCardList()throws Exception{
		this.request = ServletActionContext.getRequest();
		this.response = ServletActionContext.getResponse();
		MySessionContext myc= MySessionContext.getInstance();
		HttpSession sess = myc.getSession(request.getParameter("sessionId"));
		Uservip uservip = (Uservip) sess.getAttribute(request.getParameter("uservip"));
		String path = request.getContextPath();
	     String basePath = "";
	     if(request.getServerPort()==80){
	    	 basePath = request.getScheme() + "://" + request.getServerName()+ path + "/";
	     }else{
	    	 basePath = request.getScheme() + "://" + request.getServerName()+ ":" + request.getServerPort() + path + "/";
	     }
		List<Personalbankinfor> personalbankinfors = this.personalbankinforService.findPersonalbankinforsByuser(uservip.getUserId().intValue());
		StringBuffer str = new StringBuffer();
		str.append("{");
		str.append("\"record_list\":");
		str.append("[");
		if(personalbankinfors.size()!=0){
			for(int i=0;i<personalbankinfors.size();i++){
				String number = personalbankinfors.get(i).getAccountNum().substring(0, 4) + "*** *** ***" + personalbankinfors.get(i).getAccountNum().substring(personalbankinfors.get(i).getAccountNum().length() - 4, personalbankinfors.get(i).getAccountNum().length());
				str.append("{\"bankInforId\":\"" + personalbankinfors.get(i).getPersonalBankInforId() + "\",");//银行卡id
				str.append("\"picture\":\"" + (basePath+personalbankinfors.get(i).getBankparameter().getBankPnumber()) + "\",");//银行的图标
				if(i==(personalbankinfors.size()-1)){
					str.append("\"number\":\"" + number + "\"}");//银行卡号
				}else{
					str.append("\"number\":\"" + number + "\"},");
				}
			}
			str.append("],");
			str.append("\"state\":\"" + 1 + "\"");
			str.append("}");
			response.setContentType("text/json;charset=UTF-8");
			try {
				response.getWriter().print(str);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			str.append("],");
			str.append("\"state\":\"" + 0 + "\"");//没有银行卡
			str.append("}");
			response.setContentType("text/json;charset=UTF-8");
			try {
				response.getWriter().print(str);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 查找银行列表
	 * */
	public void showBank()throws Exception{
		this.request = ServletActionContext.getRequest();
		this.response = ServletActionContext.getResponse();
		String path = request.getContextPath();
	     String basePath = "";
	     if(request.getServerPort()==80){
	    	 basePath = request.getScheme() + "://" + request.getServerName()+ path + "/";
	     }else{
	    	 basePath = request.getScheme() + "://" + request.getServerName()+ ":" + request.getServerPort() + path + "/";
	     }
		List<Bankparameter> bankparameters = this.bankparameterService.findBankparameters();//查找银行列表
		StringBuffer str = new StringBuffer();
		str.append("{");
		str.append("\"record_list\":");
		str.append("[");
		if(bankparameters.size()!=0){
			for(int i=0;i< bankparameters.size();i++){
				str.append("{\"bankPid\":\"" + bankparameters.get(i).getBankPid() + "\",");//银行id
				str.append("\"bankPname\":\"" + bankparameters.get(i).getBankPname() + "\",");//银行名称
				if(i==(bankparameters.size()-1)){
					str.append("\"bankPnumber\":\"" + (basePath+bankparameters.get(i).getBankPnumber()) + "\"}");//银行图标
				}else{
					str.append("\"bankPnumber\":\"" + (basePath+bankparameters.get(i).getBankPnumber()) + "\"},");//银行图标
				}
			}
			str.append("],");
			str.append("\"state\":\"" + 1 + "\"");
			str.append("}");
			response.setContentType("text/json;charset=UTF-8");
			try {
				response.getWriter().print(str);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			str.append("],");
			str.append("\"state\":\"" + 0 + "\"");//没有银行卡
			str.append("}");
			response.setContentType("text/json;charset=UTF-8");
			try {
				response.getWriter().print(str);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * 添加个人银行卡
	 * */
	public void addBankCardInList()throws Exception{
		this.request = ServletActionContext.getRequest();
		this.response = ServletActionContext.getResponse();
		MySessionContext myc= MySessionContext.getInstance();
		HttpSession sess = myc.getSession(request.getParameter("sessionId"));
		Uservip uservip = (Uservip) sess.getAttribute(request.getParameter("uservip"));
		Integer bankPid = Integer.valueOf(request.getParameter("bank_pid"));//所要添加的银行
		String bankPnumber = request.getParameter("bank_card_number");//银行卡号
		String accountAddress = request.getParameter("ban_address");//开户地址
		String state="";
		Basicinfor basicinfor = this.basicinforService.findBasicinforByUserId(uservip.getUserId().intValue());
		if("".equals(basicinfor.getRealName()) || basicinfor.getRealName()==null){
			state = "0";//没有实名认证不能添加银行卡
		}else{
			Bankparameter bankparameter = this.bankparameterService.findBankparameterBybankPid(bankPid);
			Personalbankinfor personalbankinfor = new Personalbankinfor();
			personalbankinfor.setAccountAddress(accountAddress);
		    personalbankinfor.setAccountName(basicinfor.getRealName());
		    personalbankinfor.setAccountNum(bankPnumber);
		    personalbankinfor.setUservip(uservip);
		    personalbankinfor.setBankparameter(bankparameter);
		    boolean flag = this.personalbankinforService.createPersonalbankinfor(personalbankinfor);
			if(flag){
				state = "1";//添加成功
			}else{
				state = "2";//添加失败
			}
		}
		StringBuffer str = new StringBuffer();
		str.append("{");
		str.append("\"state\":\"" + state + "\"");
		str.append("}");
		response.setContentType("text/json;charset=UTF-8");
		try {
			response.getWriter().print(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 删除银行卡
	 * */
	public void deleteBankCard()throws Exception{
		this.request = ServletActionContext.getRequest();
		this.response = ServletActionContext.getResponse();
		Integer bankId = Integer.valueOf(request.getParameter("bankInforId"));
		String state = "";
		Personalbankinfor personalbankinfor = this.personalbankinforService.findPersonalbankinforById(bankId);
		boolean flag = this.personalbankinforService.deletePersonalbankinfor(personalbankinfor);
		if(flag){
			state = "1";//删除成功
		}else{
			state = "2";//删除失败
 		}
		StringBuffer str = new StringBuffer();
		str.append("{");
		str.append("\"state\":\"" + state + "\"");
		str.append("}");
		response.setContentType("text/json;charset=UTF-8");
		try {
			response.getWriter().print(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 设置支付密码
	 * */
	public void addPayPassword()throws Exception{
		this.request = ServletActionContext.getRequest();
		this.response = ServletActionContext.getResponse();
		String oldPayPwd = request.getParameter("oldPayPwd");
		String newPayPwd = request.getParameter("newPayPwd");
		String state;
		MySessionContext myc= MySessionContext.getInstance();
		HttpSession sess = myc.getSession(request.getParameter("sessionId"));
		Uservip uservip = (Uservip) sess.getAttribute(request.getParameter("uservip"));
		Uservip uservip1 = this.userService.findUservipByUserid(uservip.getUserId().intValue());
		if(uservip1!=null || !"".equals(uservip1)){
			if((oldPayPwd).equals(uservip1.getPassword()) || (oldPayPwd).equals(uservip1.getPayPwd())){
				uservip1.setPayPwd(newPayPwd);
				boolean boo = this.userService.updateUservip(uservip1);
				if(boo){
					state = "1";//修改成功
				}else{
					state = "2";//修改失败
				}
			}else{
				state = "0";//输入的旧密码不正确返回的值
			}
		}else{
			state = "3";//不存在此用户
		}
		StringBuffer str = new StringBuffer();
		str.append("{");
		str.append("\"state\":\"" + state + "\"");
		str.append("}");
		response.setContentType("text/json;charset=UTF-8");
		try {
			response.getWriter().print(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改登陆密码
	 * */
	public void updatePwd()throws Exception{
		this.request = ServletActionContext.getRequest();
		this.response = ServletActionContext.getResponse();
		String state;
		String oldPwd = request.getParameter("oldPassword");
		String newPwd = request.getParameter("newPassword");
		MySessionContext myc= MySessionContext.getInstance();
		HttpSession sess = myc.getSession(request.getParameter("sessionId"));
		Uservip uservip = (Uservip) sess.getAttribute(request.getParameter("uservip"));
		Uservip uservip1 = this.userService.findUservipByUserid(uservip.getUserId().intValue());
		if(uservip1!=null || !"".equals(uservip1)){
			if(oldPwd.equals(uservip1.getPassword())){
				uservip1.setPassword(newPwd);
				boolean boo = this.userService.updateUservip(uservip1);
				if(boo){
					state = "1";//修改成功
				}else{
					state = "2";//修改失败
				}
			}else{
				state = "0";//输入的旧密码不正确
			}
		}else{
			state = "3";//不存在此用户
		}
		StringBuffer str = new StringBuffer();
		str.append("{");
		str.append("\"state\":\"" + state + "\"");
		str.append("}");
		response.setContentType("text/json;charset=UTF-8");
		try {
			response.getWriter().print(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
