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
	 * ���Ҹ����ʻ������
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
				state="1";//δ��Ȩ
				message="δ��Ȩ";
			}else{
				state="2";
				message="����Ȩ";
			}
		}else{
			state="3";//δ��ͨ�й�
			message="δ��ͨ�й�";
		}
		//ʵ����֤��Ϣ
		if(user.getNameResult() == null || "".equals(user.getNameResult())){
			realnameState="1";//δʵ����֤
			realnameMessage="δʵ����֤";
		}else if ("2".equals(user.getNameResult())){
			realnameState="2";//��ʵ����֤
			realnameMessage="��ʵ����֤";
		}else{
			realnameState="3";
			realnameMessage="�����";
		}
		//�ֻ���֤��Ϣ
		if (user.getPhoneResult() == null ||"".equals(user.getPhoneResult())) {
			phoneState="1";
			phoneMessage="�ֻ�δ��֤";
		} else if ("1".equals(user.getPhoneResult())) {	
			phoneState="2";
			phoneMessage="�ֻ�����֤";
		} else {
			phoneState="1";
			phoneMessage="�ֻ�δ��֤";
		}
		//������֤��Ϣ
		if(uservip.getMail()!=null && !"".equals(uservip.getMail()) && uservip.getEnable()!=null){
			if(uservip.getEnable()==1){
				mailState="1";
				mailMessage="��������֤";
			}
		}else if(uservip.getMail()!=null&&uservip.getEnable()==null){
			//����֤�����ַ��δ������Ӽ���
			mailState="2";
			mailMessage="�뼤������";
		}else{
			mailState="3";
			mailMessage="����δ��֤";
		}
		StringBuffer str = new StringBuffer();
		str.append("{");
		str.append("\"availableMoney\":\"" + moneycount.getAvailableMoney() + "\",");//�ʻ����
		str.append("\"frozenMoney\":\"" + moneycount.getFrozenMoney() + "\",");//������
		str.append("\"collectTotalMoney\":\"" + moneycount.getCollectTotalMoney() + "\",");//���ս��
		str.append("\"quota\":\"" + user.getQuota() + "\",");//���ö��
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
	 * ������֤��Ϣ
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
		//ʵ����֤��Ϣ
		if (uservip.getNameResult() == null) {
			str.append("\"realName\":\"" + "" + "\",");
			str.append("\"IDNum\":\"" + "" + "\",");
			str.append("\"nameState\":\"" + "0" + "\",");
			this.message = "δʵ����֤";
		} else if ("2".equals(uservip.getNameResult())) {
			String IDNum = bas.getIdNum().substring(0, 2) + "*** *** *** ***" + bas.getIdNum().substring(bas.getIdNum().length() - 2, bas.getIdNum().length());
			str.append("\"realName\":\"" + uservip.getRealName() + "\",");
			str.append("\"IDNum\":\"" + IDNum + "\",");
			str.append("\"nameState\":\"" + "1" + "\",");
			this.message = "��ʵ����֤";
		} else {
			str.append("\"realName\":\"" +""+ "\",");
			str.append("\"IDNum\":\"" + "" + "\",");
			str.append("\"nameState\":\"" + "2" + "\",");
			this.message = "�����";
		}
		//�ֻ���֤��Ϣ
		if (uservip.getPhoneResult() == null) {
			str.append("\"phoneNum\":\"" + "" + "\",");
			str.append("\"phoneState\":\"" + "0" + "\",");
			this.message = "�ֻ�δ��֤";
		} else if ("1".equals(uservip.getPhoneResult())) {	
			str.append("\"phoneNum\":\"" + bas.getPhoneNum() + "\",");
			str.append("\"phoneState\":\"" + "1" + "\",");
			this.message = "�ֻ�����֤";
		} else {
			str.append("\"phoneNum\":\"" +""+ "\",");
			str.append("\"phoneState\":\"" + "0" + "\",");
			this.message = "�ֻ�δ��֤";
		}
		//������֤��Ϣ
		if(uservip.getMail()!=null && !"".equals(uservip.getMail()) && uservip.getEnable()!=null){
			if(uservip.getEnable()==1){
				str.append("\"mail\":\"" + uservip.getMail() + "\",");
				str.append("\"mailState\":\"" + "1" + "\",");
				this.message = "��������֤";
			}
		}else if(uservip.getMail()!=null&&uservip.getEnable()==null){
			//����֤�����ַ��δ������Ӽ���
			str.append("\"mail\":\"" +""+ "\",");
			str.append("\"mailState\":\"" + "2" + "\",");
			this.message = "�뼤�����䣡��";
		}else{
			str.append("\"mail\":\"" +""+ "\",");
			str.append("\"mailState\":\"" + "0" + "\",");
			this.message = "����δ��֤";
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
	 * �����ʽ��¼�б�
	 * */
	public void fundsRecordInfo()throws Exception{
		this.request = ServletActionContext.getRequest();
		this.response = ServletActionContext.getResponse();
		MySessionContext myc= MySessionContext.getInstance();
		HttpSession sess = myc.getSession(request.getParameter("sessionId"));
		Uservip uservip = (Uservip) sess.getAttribute(request.getParameter("uservip"));
		int currentPage = Integer.valueOf(request.getParameter("currentPage"));//��ǰҳ
	    int pageSize = Integer.valueOf(request.getParameter("pageSize"));//ÿҳ��������
		List<Moneyhistorydetail> list = moneyhistorydetailService.findMoneyhistorydetailBySql("select * from Moneyhistorydetail m where m.userId=" + uservip.getUserId().intValue()
				+" order by m.moneyHistoryDetailId desc LIMIT " +  (currentPage - 1) * pageSize + "," + pageSize);
		StringBuffer str = new StringBuffer();
		str.append("{");
		str.append("\"record_list\":");
		str.append("[");
		if(list.size()!=0){
		for(int i=0;i<list.size();i++){
			str.append("{\"affectMoney\":\"" + list.get(i).getAffectMoney() + "\",");//Ӱ����
			str.append("\"availableBalance\":\"" + list.get(i).getAvailableBalance() + "\",");//�������
			str.append("\"frozenMoney\":\"" + list.get(i).getFrozenMoney() + "\",");//������
			str.append("\"collectMoney\":\"" + list.get(i).getCollectMoney() + "\",");//���ս��
			if(i==(list.size()-1)){
				str.append("\"occurTime\":\"" + list.get(i).getOccurTime() + "\"}");//��������
			}else{
				str.append("\"occurTime\":\"" + list.get(i).getOccurTime() + "\"},");
			}
		}
			str.append("],");
			str.append(",\"state\":\"" + 1 + "\"");//���ʽ��¼
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
			str.append("\"state\":\"" + 0 + "\"");//û���ʽ��¼
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
 * ���Ҹ���Ͷ�ʼ�¼�б�        
 * */
	public void findInvestRecord()throws Exception{
		this.request = ServletActionContext.getRequest();
		this.response = ServletActionContext.getResponse();
		MySessionContext myc= MySessionContext.getInstance();
		HttpSession sess = myc.getSession(request.getParameter("sessionId"));
		Uservip uservip = (Uservip) sess.getAttribute(request.getParameter("uservip"));
		int currentPage = Integer.valueOf(request.getParameter("currentPage"));//��ǰҳ
	    int pageSize = Integer.valueOf(request.getParameter("pageSize"));//ÿҳ��������
		String sql = "select * from Tender t where t.userId="+uservip.getUserId().intValue()+" order by t.tenderId desc LIMIT " + (currentPage - 1) * pageSize + "," + pageSize;
		List<Tender> tender = tenderService.findTendersBylssuingIdPage(sql);
		StringBuffer str = new StringBuffer();
		str.append("{");
		str.append("\"record_list\":");
		str.append("[");
		if(tender.size()!=0){
			for(int i=0;i<tender.size();i++){
				Lssuing lssuing = lssuingService.findLssuingById(tender.get(i).getLssuing().getLssuingId());
				str.append("{\"title\":\"" + lssuing.getTitle() + "\",");//Ͷ����Ŀ����
				str.append("\"rate\":\"" + lssuing.getRate() + "\",");//��Ͷ����Ŀ������
				str.append("\"money\":\"" + tender.get(i).getMoney() + "\",");//��Ͷ�Ľ��
				if(i==(tender.size()-1)){
					str.append("\"tenderTime\":\"" + tender.get(i).getTenderTime() + "\"}");//Ͷ�ʵ�ʱ��
				}else{
					str.append("\"tenderTime\":\"" + tender.get(i).getTenderTime() + "\"},");
				}
			}
			str.append("],");
			str.append(",\"state\":\"" + 1 + "\"");//��Ͷ�ʼ�¼
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
			str.append("\"state\":\"" + 0 + "\"");//û��Ͷ�ʼ�¼
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
	 * ��ȡ�����п��б�
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
				str.append("{\"bankInforId\":\"" + personalbankinfors.get(i).getPersonalBankInforId() + "\",");//���п�id
				str.append("\"picture\":\"" + (basePath+personalbankinfors.get(i).getBankparameter().getBankPnumber()) + "\",");//���е�ͼ��
				if(i==(personalbankinfors.size()-1)){
					str.append("\"number\":\"" + number + "\"}");//���п���
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
			str.append("\"state\":\"" + 0 + "\"");//û�����п�
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
	 * ���������б�
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
		List<Bankparameter> bankparameters = this.bankparameterService.findBankparameters();//���������б�
		StringBuffer str = new StringBuffer();
		str.append("{");
		str.append("\"record_list\":");
		str.append("[");
		if(bankparameters.size()!=0){
			for(int i=0;i< bankparameters.size();i++){
				str.append("{\"bankPid\":\"" + bankparameters.get(i).getBankPid() + "\",");//����id
				str.append("\"bankPname\":\"" + bankparameters.get(i).getBankPname() + "\",");//��������
				if(i==(bankparameters.size()-1)){
					str.append("\"bankPnumber\":\"" + (basePath+bankparameters.get(i).getBankPnumber()) + "\"}");//����ͼ��
				}else{
					str.append("\"bankPnumber\":\"" + (basePath+bankparameters.get(i).getBankPnumber()) + "\"},");//����ͼ��
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
			str.append("\"state\":\"" + 0 + "\"");//û�����п�
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
	 * ��Ӹ������п�
	 * */
	public void addBankCardInList()throws Exception{
		this.request = ServletActionContext.getRequest();
		this.response = ServletActionContext.getResponse();
		MySessionContext myc= MySessionContext.getInstance();
		HttpSession sess = myc.getSession(request.getParameter("sessionId"));
		Uservip uservip = (Uservip) sess.getAttribute(request.getParameter("uservip"));
		Integer bankPid = Integer.valueOf(request.getParameter("bank_pid"));//��Ҫ��ӵ�����
		String bankPnumber = request.getParameter("bank_card_number");//���п���
		String accountAddress = request.getParameter("ban_address");//������ַ
		String state="";
		Basicinfor basicinfor = this.basicinforService.findBasicinforByUserId(uservip.getUserId().intValue());
		if("".equals(basicinfor.getRealName()) || basicinfor.getRealName()==null){
			state = "0";//û��ʵ����֤����������п�
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
				state = "1";//��ӳɹ�
			}else{
				state = "2";//���ʧ��
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
	 * ɾ�����п�
	 * */
	public void deleteBankCard()throws Exception{
		this.request = ServletActionContext.getRequest();
		this.response = ServletActionContext.getResponse();
		Integer bankId = Integer.valueOf(request.getParameter("bankInforId"));
		String state = "";
		Personalbankinfor personalbankinfor = this.personalbankinforService.findPersonalbankinforById(bankId);
		boolean flag = this.personalbankinforService.deletePersonalbankinfor(personalbankinfor);
		if(flag){
			state = "1";//ɾ���ɹ�
		}else{
			state = "2";//ɾ��ʧ��
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
	 * ����֧������
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
					state = "1";//�޸ĳɹ�
				}else{
					state = "2";//�޸�ʧ��
				}
			}else{
				state = "0";//����ľ����벻��ȷ���ص�ֵ
			}
		}else{
			state = "3";//�����ڴ��û�
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
	 * �޸ĵ�½����
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
					state = "1";//�޸ĳɹ�
				}else{
					state = "2";//�޸�ʧ��
				}
			}else{
				state = "0";//����ľ����벻��ȷ
			}
		}else{
			state = "3";//�����ڴ��û�
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
