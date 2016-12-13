package com.jqg.struts;

import java.io.IOException;
import java.net.InetAddress;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.jqg.pojo.Basicinfor;
import com.jqg.pojo.Inbox;
import com.jqg.pojo.Lssuing;
import com.jqg.pojo.Manager;
import com.jqg.pojo.Moneycount;
import com.jqg.pojo.Moneyhistorydetail;
import com.jqg.pojo.Oerationlog;
import com.jqg.pojo.Record;
import com.jqg.pojo.Systemconf;
import com.jqg.pojo.Tender;
import com.jqg.pojo.Transfer;
import com.jqg.pojo.Uservip;
import com.jqg.pojo.Website;
import com.jqg.service.BasicinforService;
import com.jqg.service.InboxService;
import com.jqg.service.LssingphotoService;
import com.jqg.service.LssuingService;
import com.jqg.service.ManagerService;
import com.jqg.service.MoneycountService;
import com.jqg.service.MoneyhistorydetailService;
import com.jqg.service.OerationlogService;
import com.jqg.service.RecordService;
import com.jqg.service.SystemconfService;
import com.jqg.service.TenderService;
import com.jqg.service.TransferService;
import com.jqg.service.UservipService;
import com.jqg.service.WebsiteService;
import com.jqg.service.impl.BasicinforServiceImpl;
import com.jqg.service.impl.InboxServiceImpl;
import com.jqg.service.impl.LssingphotoServiceImpl;
import com.jqg.service.impl.LssuingServiceImpl;
import com.jqg.service.impl.ManagerServiceImpl;
import com.jqg.service.impl.MoneycountServiceImpl;
import com.jqg.service.impl.MoneyhistorydetailServiceImpl;
import com.jqg.service.impl.OerationlogServiceImpl;
import com.jqg.service.impl.RecordServiceImpl;
import com.jqg.service.impl.SystemconfServiceImpl;
import com.jqg.service.impl.TenderServiceImpl;
import com.jqg.service.impl.TransferServiceImpl;
import com.jqg.service.impl.UservipServiceImpl;
import com.jqg.service.impl.WebsiteServiceImpl;
import com.jqg.util.LoanUtils;
import com.jqg.util.MD5Util;
import com.opensymphony.xwork2.ActionContext;

public class TransferAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7783024509384156903L;
	private int managerId;
	private int mark;
	private Integer page = 0;
	private Integer pageSize =10;
	private int currentPage;
	private int totalPage;
	private Integer pageCount;
	private Integer total =0;
	private String message;
	private String postform;
	private UservipService uservipService = new UservipServiceImpl();
	private RecordService recordService = new RecordServiceImpl();
	private MoneycountService moneycountService = new MoneycountServiceImpl();
	private TenderService tenderService = new TenderServiceImpl();
	private ManagerService managerService = new ManagerServiceImpl();
	private InboxService inboxService = new InboxServiceImpl();
	private SystemconfService systemconfService = new SystemconfServiceImpl();
	private TransferService transferService = new TransferServiceImpl();
	private LssuingService lssuingService = new LssuingServiceImpl();
	private LssingphotoService lssingphotoService = new LssingphotoServiceImpl();
	private BasicinforService basicinforService = new BasicinforServiceImpl();
	private OerationlogService oerationlogService=new OerationlogServiceImpl();
	private MoneyhistorydetailService moneyhistorydetailService=new MoneyhistorydetailServiceImpl();
	
	HttpServletRequest request = ServletActionContext.getRequest();


	private void addInbox(String c, String a, Integer b) throws Exception {
		Website website = (Website) ActionContext.getContext().getSession()
				.get("websiteinfo");
		Inbox inbox = new Inbox();
		inbox.setSendName(website.getWebName());
		inbox.setTitle(c);
		inbox.setContent(a);
		inbox.setStatus(Integer.valueOf(0));
		inbox.setReceiveTime(new Timestamp(new Date().getTime()));
		inbox.setUservip(this.uservipService.findUservipByUserid(b.intValue()));
		this.inboxService.createInbox(inbox);
	}
/**
 * ծȨת��
 * */
	public String addTransfer()throws Exception{
		
		String money = request.getParameter("money");//ת�ý��
		String payPwd = request.getParameter("payPwd");//֧������
		String tenderId = request.getParameter("tenderId");//ת���˵�Ͷ�ʼ�¼id
		Uservip uservip = (Uservip)ActionContext.getContext().getSession().get("uservip");
		if(!uservip.getPayPwd().equals(MD5Util.md5(payPwd))){
			this.setMessage("֧���������", "/transfer/transferJump?tenderId="+tenderId, "3");
			return "error";
		}
			Tender tender=tenderService.findTenderById(Integer.parseInt(tenderId));
			
			String  sql="select * from Record where recordState=0 and tenderId="+tender.getTenderId();
			List<Record> record = this.recordService.findRecordByRecordId(sql);
			double profit=0.0;
			for(Record re:record){
				profit=profit+re.getRecordMoney()+re.getRecordRate();
			}
			Transfer transfer = new Transfer();
			transfer.setProfit(profit);
			transfer.setMoney(Double.valueOf(money));
			transfer.setTender1(tender);
			transfer.setIsTransfer(Integer.valueOf(0));
			transfer.setTransferTime(new Timestamp(new Date().getTime()));//ת��ʱ��
			this.transferService.addTransfer(transfer);
			
			tender.setTransfer(1);//ת����
			boolean flag=tenderService.updateTender(tender);
			
			if(flag){
				this.setMessage("�ɹ�", "/transfer/transferJump?tenderId="+tenderId, "3");
				return "success";
			}else{
				return "error";
			}
	}
///**
// * ծȨ����
// * */
//	public String transferBuy()throws Exception{
//		
//		String tenderId = request.getParameter("tenderId");//ת���˵�Ͷ�ʼ�¼id
//		String payPwd = request.getParameter("payPwd");//֧������
//		Uservip uservip = (Uservip)ActionContext.getContext().getSession().get("uservip");
//		if(!uservip.getPayPwd().equals(MD5Util.md5(payPwd))){
//			this.mark = 2;
//		}else{
//			
//			Transfer transfer  = this.transferService.findTransferByTenderId1(Integer.valueOf(tenderId));//���ݹ���id���Ҷ���
//			
//			
//			if(transfer.getIsTransfer()==2){
//				this.setMessage("�Ѿ�ת�ó�ȥ�����ܹ���", "/transfer/findTransferByID.action?tenderId="+tenderId, "3");
//				return "error";
//			}
//			
//			if(transfer.getIsTransfer()==3){
//				this.setMessage("�û��Ѿ����������ܹ���", "/transfer/findTransferByID.action?tenderId="+tenderId, "3");
//				return "error";
//			}
//			
//			if(transfer.getIsTransfer()==4){
//				this.setMessage("�Ѿ�ʧЧ�����ܹ���", "/transfer/findTransferByID.action?tenderId="+tenderId, "3");
//				return "error";
//			}
//			
//			Moneycount moneycount1 = this.moneycountService.findMoneycountByuserId(uservip.getUserId());//����id����
//			
//			if(moneycount1.getAvailableMoney()<transfer.getMoney()){
//				this.setMessage("���㣬���ֵ����", "/transfer/findTransferByID.action?tenderId="+tenderId, "3");
//				return "error";
//			}
//			
//			
//			
//			
//			Tender tender = this.tenderService.findTenderById(Integer.valueOf(tenderId));
//			if(uservip.getUserId()==tender.getUservip().getUserId()){
//				this.setMessage("�Լ����ܹ����Լ���ծȨ", "/transfer/findTransferByID.action?tenderId="+tenderId, "3");
//				return "error";
//			}
//			tender.setState(2);
//			tender.setTransfer(Integer.valueOf(3));//��ת��
//			this.tenderService.updateTender(tender);
//			
//			
//			
//			
//			double money = transfer.getProfit();
//			
//			
//			
//			//ת����
//			Systemconf conf = systemconfService.findSystemconfByParname("con_bug_fee");//�Ϲ�������
//			Moneycount moneycount = this.moneycountService.findMoneycountByuserId(tender.getUservip().getUserId());//����ת����id����
//			
//			//������
//			double zr_fee = transfer.getMoney()*Double.valueOf(conf.getParvalue())/100.0D;
//			Uservip changeUser = uservipService.findUservipByUserid(tender.getUservip().getUserId());
//			
//			
//			String  sql="select * from Record where recordState=0 and tenderId="+tender.getTenderId();
//			List<Record> record = this.recordService.findRecordByRecordId(sql);
//			double benjin=0.0;
//			double lixi = 0.0;
//			for(Record re:record){
//				benjin=benjin+re.getRecordMoney();
//				lixi=lixi+re.getRecordRate();
//			}
//			
//			moneycount.setDueInMoney(Double.valueOf(moneycount
//					.getDueInMoney().doubleValue()
//					- benjin));
//
//			moneycount.setCollectInterestTotalFee(moneycount
//					.getCollectInterestTotalFee().doubleValue()
//					- lixi);
//			
//			moneycount.setCollectTotalMoney(moneycount.getCollectTotalMoney()-money);//ת���˵Ĵ��ս��
//						
//			
//			
//
//			
//			Moneyhistorydetail 	moneyhistorydetail = new Moneyhistorydetail();// �ʽ���ʷ��ϸ
//			moneyhistorydetail.setAffectMoney(transfer.getMoney());// Ӱ����
//			moneyhistorydetail
//					.setOccurTime(new Timestamp(new Date().getTime()));// �޸�ʱ��
//			moneyhistorydetail.setAvailableBalance(moneycount.getAvailableMoney()+transfer.getMoney());// �������
//			moneyhistorydetail.setCollectMoney(moneycount.getCollectTotalMoney());// ���ս��
//			moneyhistorydetail.setFrozenMoney(moneycount.getFrozenMoney());// ������
//			moneyhistorydetail.setUservip(changeUser);// �����
//			
//			moneyhistorydetail.setIntroduction("��ţ�" + tender.getLssuing().getLssuingNum()
//					+ "ծȨת�ó��۳ɹ�");
//			this.moneyhistorydetailService
//					.createMoneyhistorydetail(moneyhistorydetail);// �����ʽ���ʷ��ϸ
//			
//			
//			Moneyhistorydetail 	mhd = new Moneyhistorydetail();// �ʽ���ʷ��ϸ
//			mhd.setAffectMoney(zr_fee);// Ӱ����
//			mhd.setOccurTime(new Timestamp(new Date().getTime()));// �޸�ʱ��
//			mhd.setAvailableBalance(moneycount.getAvailableMoney()+transfer.getMoney()-zr_fee);// �������
//			mhd.setCollectMoney(moneycount.getCollectTotalMoney());// ���ս��
//			mhd.setFrozenMoney(moneycount.getFrozenMoney());// ������
//			mhd.setUservip(changeUser);// �����
//			
//			mhd.setIntroduction("��ţ�" + tender.getLssuing().getLssuingNum()
//					+ "ծȨת�ó��۳ɹ����Ϲ������ѿ۳�");
//			this.moneyhistorydetailService
//					.createMoneyhistorydetail(mhd);// �����ʽ���ʷ��ϸ
//			
//			moneycount.setAvailableMoney(moneycount.getAvailableMoney()+transfer.getMoney()-zr_fee);//ת���˵����
//			this.moneycountService.updateMoneycount(moneycount);
//			
//			addInbox("ծȨת��", "�����ʽ����䶯����ע��鿴", tender.getUservip().getUserId());
//			//������
//			
//			moneycount1.setAvailableMoney(moneycount1.getAvailableMoney()-transfer.getMoney());//���
//			moneycount1.setCollectTotalMoney(moneycount1.getCollectTotalMoney()+money);//���ս��
//			addInbox("ծȨת��", "�����ʽ����䶯����ע��鿴", uservip.getUserId());
//			
//			
//			Moneyhistorydetail 	mh = new Moneyhistorydetail();// �ʽ���ʷ��ϸ
//			mh.setAffectMoney(transfer.getMoney());// Ӱ����
//			mh.setOccurTime(new Timestamp(new Date().getTime()));// �޸�ʱ��
//			mh.setAvailableBalance(moneycount1.getAvailableMoney());// �������
//			mh.setCollectMoney(moneycount1.getCollectTotalMoney());// ���ս��
//			mh.setFrozenMoney(moneycount1.getFrozenMoney());// ������
//			mh.setUservip(uservip);// ������
//			
//			mh.setIntroduction("��ţ�" + tender.getLssuing().getLssuingNum()
//					+ "ծȨ����ɹ�");
//			this.moneyhistorydetailService
//					.createMoneyhistorydetail(mh);// �����ʽ���ʷ��ϸ
//			transfer.setBuyUser(uservip);
//			transfer.setBuyTime(new Timestamp(new Date().getTime()));
//			transfer.setIsTransfer(Integer.valueOf(2));
//			this.transferService.updateTransfer(transfer);
//			
//		}
//		return "success";
//	}
	/**
	 * ծȨ����(�й�)
	 * */
		public String transferBuy()throws Exception{
			WebsiteService websiteService = new WebsiteServiceImpl();
			String tenderId = request.getParameter("tenderId");//ת���˵�Ͷ�ʼ�¼id
			String payPwd = request.getParameter("payPwd");//֧������
			String lssuingId = request.getParameter("lssuingId");
			Uservip uservip = (Uservip)ActionContext.getContext().getSession().get("uservip");
			Lssuing lssuing1 = this.lssuingService.findLssuingById(Integer.valueOf(lssuingId));
			if(!uservip.getPayPwd().equals(MD5Util.md5(payPwd))){
				this.setMessage("�������֧�����벻��ȷ��", "/transfer/findTransferByID.action?tenderId="+tenderId, "3");
				return "error";
			}else{
				
				Transfer transfer  = this.transferService.findTransferByTenderId1(Integer.valueOf(tenderId));//���ݹ���id���Ҷ���
				Lssuing lssuing = transfer.getTender1().getLssuing();
				
				if(transfer.getIsTransfer()==2){
					this.setMessage("�Ѿ�ת�ó�ȥ�����ܹ���", "/transfer/findTransferByID.action?tenderId="+tenderId, "3");
					return "error";
				}
				
				if(transfer.getIsTransfer()==3){
					this.setMessage("�û��Ѿ����������ܹ���", "/transfer/findTransferByID.action?tenderId="+tenderId, "3");
					return "error";
				}
				
				if(transfer.getIsTransfer()==4){
					this.setMessage("�Ѿ�ʧЧ�����ܹ���", "/transfer/findTransferByID.action?tenderId="+tenderId, "3");
					return "error";
				}
				
				Moneycount moneycount1 = this.moneycountService.findMoneycountByuserId(uservip.getUserId());//����id����
				
				if(moneycount1.getAvailableMoney()<transfer.getMoney()){
					this.setMessage("���㣬���ֵ����", "/transfer/findTransferByID.action?tenderId="+tenderId, "3");
					return "error";
				}
				Tender tender = this.tenderService.findTenderById(Integer.valueOf(tenderId));
				if(uservip.getUserId()==tender.getUservip().getUserId()){
					this.setMessage("�Լ����ܹ����Լ���ծȨ", "/transfer/findTransferByID.action?tenderId="+tenderId, "3");
					return "error";
				}
				if(uservip.getTrustStatus()==1 && uservip.getTrustAccount()!=null && !uservip.getTrustAccount().equals("")){
					
				}else{
					this.setMessage("���ȿ�ͨ�й��˺��ٽ��в���", "/user/loginSearch.action", "3");
					return "error";
				}
				
				if(uservip.getAuthorizeStatus()==1){
					
				}else{
					this.setMessage("���ȿ�ͨ��Ȩ�ٽ��в���", "/user/loginSearch.action", "3");
					return "error";
				}
				LoanUtils loanUtils = new LoanUtils();
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				tender.setTenderTime(timestamp);
				Systemconf bugConf = null;
	
				bugConf = this.systemconfService.findSystemconfByParname("con_bug_fee");//�Ϲ�������
				
				Website website = websiteService.findWebsiteBywebsiteId(1);
				postform = loanUtils.transferBuy(uservip, tender, transfer,lssuing1,bugConf,website, request,false,false);		
			}
			return "tranjump";
		}
	/**
	 * ��תծȨ
	 * 
	 * @throws Exception
	 */
	public void transferlist() throws Exception {
		this.request=ServletActionContext.getRequest();
		int currentPage=Integer.valueOf(request.getParameter("currentPage"));
		Uservip uservip = (Uservip) ActionContext.getContext().getSession().get("uservip");
		String sql ="select t.* from Tender t ,uservip u,lssuing l where t.userId=u.userId and u.userId ="
				+uservip.getUserId()+" and t.lssuingId=l.lssuingId and l.state=3 and (t.transfer=0 or t.transfer=4)";
		List tenders = this.tenderService.findTenderBySql(sql);
		int totalRecord = tenders.size();
		if (totalRecord % this.pageSize == 0)
			this.totalPage = (totalRecord / this.pageSize);
		else {
			this.totalPage = (totalRecord / this.pageSize + 1);
		}
		if (currentPage >= this.totalPage) {
			currentPage = this.totalPage;
		}
		if (currentPage <= 1) {
			currentPage = 1;
		}
		sql = sql +" ORDER BY t.tenderId DESC LIMIT " +(currentPage - 1) * this.pageSize + "," + this.pageSize;
		List<Tender> tendersPage = this.tenderService.findTenderBySql(sql);
		StringBuffer str = new StringBuffer();
		str.append("{\"totalPage\":\"" + this.totalPage + "\",");
		str.append("\"currentPage\":\"" + currentPage + "\",");
		str.append("\"jsonRoot\":[");

		for (Tender tender : tendersPage) {
			String userName = tender.getLssuing().getUservip().getUserName();
			if (userName.length() < 4) {
				userName = userName.substring(0, 1)
						+ "****"
						+ userName.substring(userName.length() - 1,
								userName.length());
			} else {
				userName = userName.substring(0, 2)
						+ "****"
						+ userName.substring(userName.length() - 2,
								userName.length());
			}
			String perTime = "";
			String lilv = "";
			DecimalFormat df1 = new DecimalFormat("0.00");
			double benxi;
			if (tender.getLssuing().getPeriodday() != null) {
				perTime = tender.getLssuing().getPeriodday().getPeriodDayName();
				benxi = tender.getMoney().intValue()
						* tender.getLssuing().getRate().doubleValue()
						* tender.getLssuing().getPeriodday().getPeriodDayId()
								.intValue() / 100.0D;
				lilv = df1.format(tender.getLssuing().getRate() * 360);
			} else {
				perTime = tender.getLssuing().getPeriodtime()
						.getPeriodTimeName();
				benxi = tender.getMoney().intValue()
						* tender.getLssuing().getRate().doubleValue()
						* tender.getLssuing().getPeriodtime().getPeriodTimeId()
								.intValue() / 12.0D / 100.0D;
				lilv = df1.format(tender.getLssuing().getRate());
			}

			String benxistr = df1.format(benxi+tender.getMoney().doubleValue());
			str.append("{\"lssuingNum\":\""+ tender.getLssuing().getLssuingNum() + "\",");
			str.append("\"userName\":\"" + userName + "\",");
			str.append("\"rate\":\"" + lilv + "\",");
			str.append("\"money\":\"" + tender.getMoney() + "\",");
			str.append("\"time\":\""+ tender.getTenderTime().toString().substring(0, 10)+ "\",");
			str.append("\"perTime\":\"" + perTime + "\",");
			str.append("\"benxi\":\"" + benxistr + "\",");
			str.append("\"tenderId\":\"" + tender.getTenderId() + "\"},");
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
 * ��תת��ҳ��
 * */	
	public String transferJump()throws Exception{
		
		String tenderId = request.getParameter("tenderId");
		Tender tender = this.tenderService.findTenderById(Integer.valueOf(tenderId));
		Systemconf conf = systemconfService.findSystemconfByParname("con_bug_fee");//�Ϲ�������
		DecimalFormat df1 = new DecimalFormat("0.00");
		
		
		String  sql="select * from Record where recordState=0 and tenderId="+tender.getTenderId();
		List<Record> record = this.recordService.findRecordByRecordId(sql);
		double money=0.0;
		for(Record re:record){
			money=money+re.getRecordMoney()+re.getRecordRate();
		}
		System.out.println("���ת�ü۸񣡣�"+money);
		String benxistr = df1.format(money);//��Ϣ�������ת�ü۸�
		String lssuingNum = tender.getLssuing().getLssuingNum();//ת�ñ��
		String fee =  df1.format(Double.valueOf(conf.getParvalue())/ 100.0D*(money));//ת��������
		String parvalue = conf.getParvalue();//ת�������ѵİٷֱ�ȡֵ
		ActionContext.getContext().put("benxistr", benxistr);
		ActionContext.getContext().put("lssuingNum", lssuingNum);
		ActionContext.getContext().put("fee", fee);
		ActionContext.getContext().put("parvalue", parvalue);
		ActionContext.getContext().put("tenderId", tender.getTenderId());
		return "success";
	}

	/**
	 *�����е�ծȨ
	 * 
	 * @throws Exception
	 */
	public void transferlist1() throws Exception {
		Uservip uservip = (Uservip) ActionContext.getContext().getSession().get("uservip");
		String sql ="select t.* from Tender t ,uservip u,lssuing l where t.userId=u.userId and u.userId ="
					+uservip.getUserId()+" and t.lssuingId=l.lssuingId and l.state=3 and t.transfer=5";
		List tenders = this.tenderService.findTenderBySql(sql);
		
		int totalRecord = tenders.size();
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
		sql = sql +" ORDER BY t.tenderId DESC LIMIT " +(this.currentPage - 1) * this.pageSize + "," + this.pageSize;;
		List<Tender> tendersPage = this.tenderService.findTenderBySql(sql);
		StringBuffer str = new StringBuffer();
		str.append("{\"totalPage\":\"" + this.totalPage + "\",");
		str.append("\"currentPage\":\"" + this.currentPage + "\",");
		str.append("\"jsonRoot\":[");

		for (Tender tender : tendersPage) {
			String lilv = "";
			DecimalFormat df1 = new DecimalFormat("0.00");
			double benxi;
			if (tender.getLssuing().getPeriodday() != null) {
				benxi = tender.getMoney().intValue()
						* tender.getLssuing().getRate().doubleValue()
						* tender.getLssuing().getPeriodday().getPeriodDayId()
								.intValue() / 100.0D;
				lilv = df1.format(tender.getLssuing().getRate() * 360);
			} else {
				benxi = tender.getMoney().intValue()
						* tender.getLssuing().getRate().doubleValue()
						* tender.getLssuing().getPeriodtime().getPeriodTimeId()
								.intValue() / 12.0D / 100.0D;
				lilv = df1.format(tender.getLssuing().getRate());
			}
			Transfer transfer = this.transferService.findTransferByTenderId(tender.getTenderId());
			String benxistr = df1.format(benxi+tender.getMoney().doubleValue());
			str.append("{\"lssuingNum\":\""+ tender.getLssuing().getLssuingNum() + "\",");//���
			str.append("\"transferMoney\":\"" + transfer.getMoney() + "\",");//ת�ü۸�
			str.append("\"transferTime\":\"" + transfer.getTransferTime().toString().substring(0, 10) + "\",");//ת��ʱ��
			str.append("\"rate\":\"" + lilv + "\",");//����
			str.append("\"benxi\":\"" + benxistr + "\",");//���ձ�Ϣ
			str.append("\"isTransfer\":\"" + transfer.getIsTransfer() + "\",");//
			str.append("\"tenderId\":\"" + tender.getTenderId() + "\"},");
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
	 * �ɹ�ת�õ�ծȨ
	 * 
	 * @throws Exception
	 */
	public void transferlist2() throws Exception {
		Uservip uservip = (Uservip) ActionContext.getContext().getSession().get("uservip");
		String sql ="select t.* from Tender t ,uservip u,lssuing l where t.userId=u.userId and u.userId ="
				+uservip.getUserId()+" and t.lssuingId=l.lssuingId and t.transfer=3";
		List tenders3 = this.tenderService.findTenderBySql(sql);
		int totalRecord = tenders3.size();
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
		sql = sql +" ORDER BY t.tenderId DESC LIMIT " +(this.currentPage - 1) * this.pageSize + "," + this.pageSize;;
		List<Tender> tendersPage3 = this.tenderService.findTenderBySql(sql);
		
		String path = request.getContextPath();
		StringBuffer str1 = new StringBuffer();
		str1.append("{\"totalPage\":\"" + this.totalPage + "\",");
		str1.append("\"currentPage\":\"" + this.currentPage + "\",");
		str1.append("\"jsonRoot\":[");
		DecimalFormat df1 = new DecimalFormat("0.00");
		for (Tender tender : tendersPage3) {
			double benxi;
			String lilv = "";
			if (tender.getLssuing().getPeriodday() != null) {
				benxi = tender.getMoney().intValue()
						* tender.getLssuing().getRate().doubleValue()
						* tender.getLssuing().getPeriodday().getPeriodDayId()
								.intValue() / 100.0D;
				lilv = df1.format(tender.getLssuing().getRate() * 360);
			} else {
				benxi = tender.getMoney().intValue()
						* tender.getLssuing().getRate().doubleValue()
						* tender.getLssuing().getPeriodtime().getPeriodTimeId()
								.intValue() / 12.0D / 100.0D;
				lilv = df1.format(tender.getLssuing().getRate());
			}
			Transfer transfer = this.transferService.findTransferByTenderId(tender.getTenderId());
			String userName = transfer.getBuyUser().getUserName();
			if (userName.length() < 4) {
				userName = userName.substring(0, 1)
						+ "****"
						+ userName.substring(userName.length() - 1,
								userName.length());
			} else {
				userName = userName.substring(0, 2)
						+ "****"
						+ userName.substring(userName.length() - 2,
								userName.length());
			}
			String benxistr = df1.format(benxi+tender.getMoney().doubleValue());

			str1.append("{\"lssuingNum\":\""+ tender.getLssuing().getLssuingNum() + "\",");//���
			str1.append("\"rate\":\"" + lilv + "\",");//����
			str1.append("\"transferMoney\":\"" + transfer.getMoney() + "\",");//ת�ü۸�
			str1.append("\"transferTime\":\"" + transfer.getTransferTime().toString().substring(0, 10) + "\",");//ת��ʱ��
			str1.append("\"benxi\":\"" + benxistr + "\",");//���ձ�Ϣ��ծȨ��ֵ��
			str1.append("\"userName\":\"" + userName + "\",");//������
			str1.append("\"remark\":\"" + "<a href='" + path
					+ "/tender/investContract?tenderId=" + tender.getTenderId()
					+ "' target='_blank'>��ͬ</a>" + "\"},");
		}
		str1.deleteCharAt(str1.lastIndexOf(","));
		str1.append("]}");

		HttpServletResponse response1 = ServletActionContext.getResponse();
		response1.setCharacterEncoding("utf-8");
		try {
			response1.getWriter().print(str1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * �ѹ����ծȨ
	 * 
	 * @throws Exception
	 */
	public void transferlist3() throws Exception {
		Uservip uservip = (Uservip) ActionContext.getContext().getSession().get("uservip");
		String sql ="select t.* from Transfer t ,uservip u where t.buyUserId=u.userId and u.userId ="
				+uservip.getUserId()+" and t.isTransfer=2";
		List<Transfer> tenders3 = this.transferService.findTransferListBySql(sql);
		int totalRecord = tenders3.size();
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
		sql = sql +" ORDER BY t.transferId DESC LIMIT " +(this.currentPage - 1) * this.pageSize + "," + this.pageSize;;
		List<Transfer> tendersPage3 = this.transferService.findTransferListBySql(sql);
		
		String path = request.getContextPath();
		StringBuffer str1 = new StringBuffer();
		str1.append("{\"totalPage\":\"" + this.totalPage + "\",");
		str1.append("\"currentPage\":\"" + this.currentPage + "\",");
		str1.append("\"jsonRoot\":[");
		DecimalFormat df1 = new DecimalFormat("0.00");
		for (Transfer transfer : tendersPage3) {
			Lssuing lssuing = transfer.getTender1().getLssuing();
			
			String userName = transfer.getTender1().getUservip().getUserName();
			if (userName.length() < 4) {
				userName = userName.substring(0, 1)
						+ "****"
						+ userName.substring(userName.length() - 1,
								userName.length());
			} else {
				userName = userName.substring(0, 2)
						+ "****"
						+ userName.substring(userName.length() - 2,
								userName.length());
			}
			
			String lilv = "";
			if (lssuing.getPeriodday() != null) {
				lilv = df1.format(lssuing.getRate() * 360);
			} else {
				lilv = df1.format(lssuing.getRate());
			}
			
			String buyferTime="";
			if(transfer.getBuyTime()!=null){
				buyferTime = transfer.getBuyTime().toString().substring(0, 10);
			}
			str1.append("{\"lssuingNum\":\""+ lssuing.getLssuingNum() + "\",");//���
			str1.append("\"rate\":\"" + lilv + "\",");//����
			str1.append("\"transferMoney\":\"" + transfer.getMoney() + "\",");//����۸�
			str1.append("\"buyferTime\":\"" + buyferTime + "\",");//����ʱ��
			str1.append("\"benxi\":\"" + transfer.getProfit() + "\",");//���ձ�Ϣ��ծȨ��ֵ��
			str1.append("\"userName\":\"" + userName + "\",");//������
			str1.append("\"remark\":\"" + "<a href='" + path
					+ "/transfer/investContract?transferId=" + transfer.getTransferId()
					+ "' target='_blank'>��ͬ</a>" + "\"},");
		}
		str1.deleteCharAt(str1.lastIndexOf(","));
		str1.append("]}");

		HttpServletResponse response1 = ServletActionContext.getResponse();
		response1.setCharacterEncoding("utf-8");
		try {
			response1.getWriter().print(str1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * �ѳ�����ծȨ
	 * 
	 * @throws Exception
	 */
	public void transferlist4() throws Exception {
		Uservip uservip = (Uservip) ActionContext.getContext().getSession().get("uservip");
		String sql ="select t.* from Tender t ,uservip u,lssuing l where t.userId=u.userId and u.userId ="
				+uservip.getUserId()+" and t.lssuingId=l.lssuingId and l.state=3 and t.transfer=4";
		List tenders3 = this.tenderService.findTenderBySql(sql);
		int totalRecord = tenders3.size();
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
		sql = sql +" ORDER BY t.tenderId DESC LIMIT " +(this.currentPage - 1) * this.pageSize + "," + this.pageSize;;
		List<Tender> tendersPage3 = this.tenderService.findTenderBySql(sql);
		
		String path = request.getContextPath();
		StringBuffer str1 = new StringBuffer();
		str1.append("{\"totalPage\":\"" + this.totalPage + "\",");
		str1.append("\"currentPage\":\"" + this.currentPage + "\",");
		str1.append("\"jsonRoot\":[");
		DecimalFormat df1 = new DecimalFormat("0.00");
		for (Tender tender : tendersPage3) {
			double benxi;
			String lilv = "";
			if (tender.getLssuing().getPeriodday() != null) {
				benxi = tender.getMoney().intValue()
						* tender.getLssuing().getRate().doubleValue()
						* tender.getLssuing().getPeriodday().getPeriodDayId()
								.intValue() / 100.0D;
				lilv = df1.format(tender.getLssuing().getRate() * 360);
			} else {
				benxi = tender.getMoney().intValue()
						* tender.getLssuing().getRate().doubleValue()
						* tender.getLssuing().getPeriodtime().getPeriodTimeId()
								.intValue() / 12.0D / 100.0D;
				lilv = df1.format(tender.getLssuing().getRate());
			}
			Transfer transfer = this.transferService.findTransferByTenderId3(tender.getTenderId());
			String benxistr = df1.format(benxi+tender.getMoney().doubleValue());
			String cancelTime="";
			if(transfer.getCancelTime()!=null){
				cancelTime = transfer.getCancelTime().toString().substring(0, 10);
			}
			str1.append("{\"lssuingNum\":\""+ tender.getLssuing().getLssuingNum() + "\",");//���
			str1.append("\"rate\":\"" + lilv + "\",");//����
			str1.append("\"benxi\":\"" + benxistr + "\",");//���ձ�Ϣ��ծȨ��ֵ��
			str1.append("\"cancelNum\":\"" + transfer.getCancelNum() + "\",");//��������
			str1.append("\"cancelTime\":\"" + cancelTime + "\",");//����ʱ��
			str1.append("\"remark\":\"" + "<a href='" + path
					+ "/tender/investContract?tenderId=" + tender.getTenderId()
					+ "' target='_blank'>��ͬ</a>" + "\"},");
		}
		str1.deleteCharAt(str1.lastIndexOf(","));
		str1.append("]}");

		HttpServletResponse response1 = ServletActionContext.getResponse();
		response1.setCharacterEncoding("utf-8");
		try {
			response1.getWriter().print(str1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * ��ת��ծȨ�б�
	 * 
	 * @throws Exception
	 */
	public void transferlistPage() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String returnWay = request.getParameter("returnway");//���ʽ
		String yongtu = request.getParameter("yongtu");//�����;
		String time = request.getParameter("time");//������ޣ��£�
		String periodday = request.getParameter("periodday");//������ޣ��죩
		String bid = request.getParameter("bid");//��Ŀ״̬
		String startMoney = request.getParameter("startMoney");//�����ʼֵ
		String endMoney = request.getParameter("endMoney");//������ֵ
		this.currentPage = Integer.valueOf(request.getParameter("currentPage")).intValue();//�����ʼֵ
		this.pageSize = Integer.valueOf(request.getParameter("pageSize")).intValue();//������ֵ
//		String sql ="select t.* from Tender t ,uservip u,lssuing l where t.userId=u.userId and t.lssuingId=l.lssuingId and l.state=3 and t.transfer=1";
		String sql = "from Tender t where (t.transfer=5 or t.transfer=3)";
		if (!"0".equals(returnWay) && returnWay!=null)
	    {
	      sql = sql + " AND  t.lssuing.returnWay.returnWayId = " + returnWay;
	    }

	    if (!"0".equals(yongtu) && yongtu!=null)
	    {
	      sql = sql + " AND t.lssuing.moneyuse.moneyUseId = " + yongtu;
	    }
	    if (!"0".equals(time) && time!=null)
	    {
	      sql = sql + " AND t.lssuing.periodtime.periodTimeId = " + time;
	    }
	    if (!"0".equals(periodday) && periodday!=null)
	    {
	      sql = sql + " AND t.lssuing.periodday.periodDayId = " + periodday;
	    }
	    if(!"0".equals(bid) && bid!=null){
	    	sql = sql + " AND t.lssuing.state ="+bid;
	    }
		if(startMoney!=null && endMoney!=null){
			sql = sql + " AND t.lssuing.borrowMoney< " + endMoney + " and t.lssuing.borrowMoney>startMoney";
		}
		
		List tenders3 = this.tenderService.findTenderByHql(sql,-1,-1);
		int totalRecord = tenders3.size();
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
//		sql = sql +" ORDER BY t.tenderId DESC LIMIT " +(this.currentPage - 1) * this.pageSize + "," + this.pageSize;;
		List<Tender> tendersPage3 = this.tenderService.findTenderByHql(sql,(this.currentPage - 1) * this.pageSize,this.pageSize);
		String path = request.getContextPath();
		StringBuffer str1 = new StringBuffer();
		str1.append("{\"totalPage\":\"" + this.totalPage + "\",");
		str1.append("\"currentPage\":\"" + this.currentPage + "\",");
		str1.append("\"jsonRoot\":[");
		DecimalFormat df1 = new DecimalFormat("0.00");
		for (Tender tender : tendersPage3) {
			double benxi;
			String lilv = "";
			if (tender.getLssuing().getPeriodday() != null) {
				lilv = df1.format(tender.getLssuing().getRate() * 360);
			} else {
				lilv = df1.format(tender.getLssuing().getRate());
			}
			String actions = path+"/transfer/findTransferByID.action?tenderId="+ tender.getTenderId();
			String state = "";
			if(tender.getTransfer()==3){
				state = "�����";
			}else{
				state = "����Ͷ��";
			}
			System.out.println(tender.getTenderId());
			Transfer transfer = this.transferService.findTransferByTenderId(tender.getTenderId());
			str1.append("{\"title\":\""+ tender.getLssuing().getTitle() + "\",");//���
			str1.append("\"rate\":\"" + lilv + "\",");//����
			str1.append("\"benxi\":\"" + transfer.getProfit() + "\",");//���ձ�Ϣ��ծȨ��ֵ��
			str1.append("\"transferMoney\":\"" + transfer.getMoney() + "\",");//ת�ü۸�
			str1.append("\"state\":\"" + state + "\",");//
			str1.append("\"actions\":\"" + actions + "\"},");
		}
		str1.deleteCharAt(str1.lastIndexOf(","));
		str1.append("]}");

		HttpServletResponse response1 = ServletActionContext.getResponse();
		response1.setCharacterEncoding("utf-8");
		try {
			response1.getWriter().print(str1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ծȨת�õ���ϸҳ��
	 * 
	 * @return
	 * @throws Exception
	 */
	public String findTransferByID() throws Exception {
		
		String tenderId = request.getParameter("tenderId");
		Tender tender1 = this.tenderService.findTenderById(Integer.valueOf(tenderId));
		Transfer transfer = this.transferService.findTransferByTenderId1(Integer.valueOf(tenderId));
		Uservip uservip1 =this.uservipService.findUservipByUserid(tender1.getUservip().getUserId());
		double benxi;
		DecimalFormat df1 = new DecimalFormat("0.00");
		if (tender1.getLssuing().getPeriodday() != null) {
			benxi = tender1.getMoney().intValue()
					* tender1.getLssuing().getRate().doubleValue()
					* tender1.getLssuing().getPeriodday().getPeriodDayId()
							.intValue() / 100.0D;
		} else {
			benxi = tender1.getMoney().intValue()
					* tender1.getLssuing().getRate().doubleValue()
					* tender1.getLssuing().getPeriodtime().getPeriodTimeId()
							.intValue() / 12.0D / 100.0D;
		}
		String benxistr = df1.format(benxi+tender1.getMoney().doubleValue());
		ActionContext.getContext().put("benxistr", benxistr);//��Ϣ
		ActionContext.getContext().put("transferMoney", transfer.getMoney());//�Ϲ����
		ActionContext.getContext().put("username", uservip1.getUserName());//������
		ActionContext.getContext().put("tenderId", tender1.getTenderId());//������
		ActionContext.getContext().put("transfer", tender1.getTransfer());
		
		Lssuing lssuing = this.lssuingService.findLssuingById(tender1.getLssuing().getLssuingId());

		List lssingphotos = this.lssingphotoService.findLssingphotosBylssuing(lssuing.getLssuingId().intValue());

		int totalRecord = lssingphotos.size();
		int size;
		List lssList = new ArrayList();
		if (totalRecord % 3 == 0)
			size = totalRecord / 3;
		else {
			size = totalRecord / 3 + 1;
		}
		for (int i = 0; i < size; i++) {
			List lssingphotos1 = this.lssingphotoService
					.findLssingphotosBylssuingPage(lssuing.getLssuingId()
					.intValue(), i * 3, 3);
			lssList.add(lssingphotos1);
		}
		Basicinfor basicinfor = this.basicinforService.findBasicinforByUserId(lssuing.getUservip().getUserId().intValue());

		ActionContext.getContext().getSession().put("basicinforTdener", basicinfor);
		List<Lssuing> lssuings = this.lssuingService.findLssuingsByUserId(lssuing.getUservip().getUserId().intValue());
		List lssuings1 = this.lssuingService.findLssuingsByUserIdAndByState(lssuing.getUservip().getUserId().intValue(), 3);

		ActionContext.getContext().getSession().put("lssuingTdener", Integer.valueOf(lssuings.size()));

		double zonge = 0.0D;
		for (Lssuing lssuing2 : lssuings) {
			zonge = Double.valueOf(lssuing2.getBorrowMoney()).doubleValue()+ zonge;
		}
		String zongenum = df1.format(zonge);
		ActionContext.getContext().getSession().put("zonge", zongenum);
		ActionContext.getContext().getSession().put("lssuingTdener", Integer.valueOf(lssuings.size()));
		ActionContext.getContext().getSession().put("lssuingTdenerDai", Integer.valueOf(lssuings1.size()));

		double daihuan = 0.0D;

		for (Lssuing lssuing2 : lssuings) {
			if (lssuing2.getState().intValue() == 4) {

				if (lssuing2.getPeriodtime() != null) {
					daihuan = Integer.valueOf(lssuing2.getBorrowMoney())
							.intValue()
							+ Integer.valueOf(lssuing2.getBorrowMoney())
									.intValue()
							* lssuing2.getRate().doubleValue()
							/ 100.0D
							/ 12.0D
							* lssuing2.getPeriodtime().getPeriodTimeId()
									.intValue()
							+ daihuan
							- (Integer.valueOf(lssuing2.getBorrowMoney())
									.intValue() + Integer.valueOf(
									lssuing2.getBorrowMoney()).intValue()
									* lssuing2.getRate().doubleValue()
									/ 100.0D
									/ 12.0D
									* lssuing2.getPeriodtime()
											.getPeriodTimeId().intValue());
				} else if (lssuing2.getPeriodday() != null) {
					daihuan = Integer.valueOf(lssuing2.getBorrowMoney())
							.intValue()
							+ Integer.valueOf(lssuing2.getBorrowMoney())
									.intValue()
							* lssuing2.getRate().doubleValue()
							* lssuing2.getPeriodday().getPeriodDayId()
									.intValue()
							/ 100.00D
							+ daihuan
							- (Integer.valueOf(lssuing2.getBorrowMoney())
									.intValue() + Integer.valueOf(
									lssuing2.getBorrowMoney()).intValue()
									* lssuing2.getRate().doubleValue()
									* lssuing2.getPeriodday().getPeriodDayId()
											.intValue() / 100.00D);
				}
			} else {
				if (lssuing2.getPeriodtime() != null) {
					daihuan = Integer.valueOf(lssuing2.getBorrowMoney())
							.intValue()
							+ Integer.valueOf(lssuing2.getBorrowMoney())
									.intValue()
							* lssuing2.getRate().doubleValue()
							/ 100.0D
							/ 12.0D
							* lssuing2.getPeriodtime().getPeriodTimeId()
									.intValue() + daihuan;
				} else if (lssuing2.getPeriodday() != null) {
					daihuan = Integer.valueOf(lssuing2.getBorrowMoney())
							.intValue()
							+ Integer.valueOf(lssuing2.getBorrowMoney())
									.intValue()
							* lssuing2.getRate().doubleValue()
							* lssuing2.getPeriodday().getPeriodDayId()
									.intValue() / 100.00D + daihuan;
				}
			}
		}

		String daihuannum = df1.format(daihuan);
		ActionContext.getContext().getSession().put("daihuan", daihuannum);

		List lssuings2 = this.lssuingService.findLssuingsByUserIdAndByState(lssuing.getUservip().getUserId().intValue(), -1);
		ActionContext.getContext().getSession().put("yuqishu", Integer.valueOf(lssuings2.size()));

		List lssuings3 = this.lssuingService.findLssuingsByUserIdAndByState(
				lssuing.getUservip().getUserId().intValue(), 4);
		ActionContext.getContext().getSession().put("yiwancheng", Integer.valueOf(lssuings3.size()));
		List<Tender> tenders = this.tenderService
				.findTendersBylssuingId(lssuing.getLssuingId().intValue());

		double interest = 0.0D;
		String picture = " ";
		String away = null;
		String state = null;
		String number = null;
		String day = "";

		String moneyMin = lssuing.getMoneymin().getMoneyMinName().toString();
		String moneyMax = "";
		int money = 0;
		if (tenders.size() == 0) {
			money = 0;
		} else {
			for (Tender tender : tenders) {
				money = tender.getMoney().intValue() + money;
			}
		}
		int BorrowMoney = Integer.valueOf(lssuing.getBorrowMoney()).intValue();
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		df.setMinimumFractionDigits(2);
		String k = df.format(money * 100.0D / BorrowMoney);
		double bear = Double.parseDouble(k);

		if (tenders.size() == 0)
			number = "0";
		else if (bear < 10.0D)
			number = "1";
		else if ((10.0D <= bear) && (bear < 20.0D))
			number = "2";
		else if ((20.0D <= bear) && (bear < 30.0D))
			number = "3";
		else if ((30.0D <= bear) && (bear < 40.0D))
			number = "4";
		else if ((40.0D <= bear) && (bear < 50.0D))
			number = "5";
		else if ((50.0D <= bear) && (bear < 60.0D))
			number = "6";
		else if ((60.0D <= bear) && (bear < 70.0D))
			number = "7";
		else if ((70.0D <= bear) && (bear < 80.0D))
			number = "8";
		else if ((80.0D <= bear) && (bear < 90.0D))
			number = "9";
		else if ((90.0D <= bear) && (bear < 100.0D))
			number = "10";
		else {
			number = "11";
		}

		if (lssuing.getIsAward().intValue() == 0) {
			away = "�޽���";
		} else {
			picture = "<img src='../images/loantype/award.gif' />" + picture;
			if (lssuing.getAwardRate() != null) {
				away = "��Ͷ�����������" + lssuing.getAwardRate() + "%";
			} else if (lssuing.getAwardMoney() != null) {
				away = "���̶�����̯����" + lssuing.getAwardMoney();
			}

		}

		if (lssuing.getPeriodtime() != null) {
			interest = Integer.valueOf(lssuing.getBorrowMoney()).intValue()
					+ Integer.valueOf(lssuing.getBorrowMoney()).intValue()
					* lssuing.getRate().doubleValue() / 100.0D / 12.0D
					* lssuing.getPeriodtime().getPeriodTimeId().intValue();
			day = "��";
		} else if (lssuing.getPeriodday() != null) {
			interest = Integer.valueOf(lssuing.getBorrowMoney()).intValue()
					+ Integer.valueOf(lssuing.getBorrowMoney()).intValue()
					* lssuing.getRate().doubleValue()
					* lssuing.getPeriodday().getPeriodDayId().intValue()
					/ 100.00D;
			picture = "<img src='../images/loantype/day.gif' />" + picture;
			day = "��";
		}
		if (lssuing.getMoneymax() != null)
			moneyMax = lssuing.getMoneymax().getMoneyMaxName().toString();
		else {
			moneyMax = "������";
		}
		Uservip uservip = (Uservip) ActionContext.getContext().getSession().get("uservip");
		if (uservip != null) {

			System.err.println(uservip.getUserId());
			Moneycount moneycount = this.moneycountService.findMoneycountByuserId(uservip.getUserId());
			ActionContext.getContext().getSession().put("AvailableMoney", moneycount.getAvailableMoney());
			ActionContext.getContext().put("moneycount", moneycount);
			if (lssuing.getTenderLimit().intValue() == 1) {
				ActionContext.getContext().getSession()
						.put("moneyLimit", moneycount.getCollectTotalMoney());
			} else {
				ActionContext.getContext().getSession()
						.put("moneyLimit", Integer.valueOf(0));
			}
		}
		ActionContext.getContext().getSession().put("money",
						Integer.valueOf(Integer.valueOf(lssuing.getBorrowMoney()).intValue()- money));
		ActionContext.getContext().getSession().put("interest", df.format(interest));
		ActionContext.getContext().getSession().put("picture", picture);
		ActionContext.getContext().getSession().put("away", away);
		ActionContext.getContext().getSession().put("lssuing", lssuing);
		ActionContext.getContext().getSession().put("number", number);
		ActionContext.getContext().getSession().put("bear", Double.valueOf(bear));
		ActionContext.getContext().getSession().put("day", day);
		ActionContext.getContext().getSession().put("moneyMin", moneyMin);
		ActionContext.getContext().getSession().put("moneyMax", moneyMax);
		ActionContext.getContext().getSession().put("lssList", lssList);
		return "success";
	}
	
	
	/**
	 * ת�ô���˲�ѯ
	 * @return
	 * @throws Exception
	 */
	public String findTransger1() throws Exception{
		
		String userNameQuery=(String) request.getParameter("userNameQuery");
		String title= (String) request.getParameter("title");
		String money= (String) request.getParameter("money");
		String getPage= (String) request.getParameter("page");
		if(getPage!=null&&!"".equals(getPage)){
			this.page=Integer.parseInt(getPage);
		}
		
		String sql = " from Transfer t  where  t.isTransfer=0  ";
		
		if ((userNameQuery != null) && (!"".equals(userNameQuery))) {
			sql = sql + " and  t.tender1.uservip.userName like '%" + userNameQuery + "%' ";
		}
		
		if (title != null &&!"".equals(title)) {
			sql = sql + "  and  t.tender1.lssuing.title  like  '%"+title + "%' ";
		}
		if (money != null &&!"".equals(money)) {
			sql = sql
					+ "  and t.money="+money;
		}
		
		List<Transfer> list=transferService.findTransferListByHql(sql,-1,-1);
		
		this.total = Integer.valueOf(list.size());
		
		if (this.page.intValue() == 0) {
			this.page = Integer.valueOf(1);
		}
		if (this.total.intValue() == 0)
			this.pageCount = Integer.valueOf(1);
		else if (this.total.intValue() % this.pageSize.intValue() == 0)
			this.pageCount = Integer.valueOf(this.total.intValue()
					/ this.pageSize.intValue());
		else {
			this.pageCount = Integer.valueOf(this.total.intValue()
					/ this.pageSize.intValue() + 1);
		}
		
		List<Transfer> tList=transferService.findTransferListByHql(sql,(this.page-1)*this.pageSize,this.pageSize);
		
		request.setAttribute("tList", tList);
		request.setAttribute("userNameQuery", userNameQuery);
		request.setAttribute("title", title);
		request.setAttribute("money", money);
		return "success";
	}
	/**
	 * ת�ü�¼��ѯ
	 * @return
	 * @throws Exception
	 */
	public String findTransger2() throws Exception{
		
		String userNameQuery=(String) request.getParameter("userNameQuery");
		String userNameQuery2=(String) request.getParameter("userNameQuery2");
		String title= (String) request.getParameter("title");
		String money= (String) request.getParameter("money");
		String getPage= (String) request.getParameter("page");
		if(getPage!=null&&!"".equals(getPage)){
			this.page=Integer.parseInt(getPage);
		}
		
		String sql = " from Transfer t  where  1=1  ";
		
		if ((userNameQuery != null) && (!"".equals(userNameQuery))) {
			sql = sql + " and  t.tender1.uservip.userName like '%" + userNameQuery + "%' ";
		}
		if ((userNameQuery2 != null) && (!"".equals(userNameQuery2))) {
			sql = sql + " and  t.buyUser.userName like '%" + userNameQuery2 + "%' ";
		}
		if (title != null &&!"".equals(title)) {
			sql = sql + "  and  t.tender1.lssuing.title  like  '%"+title + "%' ";
		}
		if (money != null &&!"".equals(money)) {
			sql = sql
					+ "  and t.money="+money;
		}
		
		List<Transfer> list=transferService.findTransferListByHql(sql,-1,-1);
		
		this.total = Integer.valueOf(list.size());
		
		if (this.page.intValue() == 0) {
			this.page = Integer.valueOf(1);
		}
		if (this.total.intValue() == 0)
			this.pageCount = Integer.valueOf(1);
		else if (this.total.intValue() % this.pageSize.intValue() == 0)
			this.pageCount = Integer.valueOf(this.total.intValue()
					/ this.pageSize.intValue());
		else {
			this.pageCount = Integer.valueOf(this.total.intValue()
					/ this.pageSize.intValue() + 1);
		}
		
	
		List<Transfer> tList=transferService.findTransferListByHql(sql,(this.page-1)*this.pageSize,this.pageSize);
		
		
		request.setAttribute("tList", tList);
		request.setAttribute("userNameQuery", userNameQuery);
		request.setAttribute("userNameQuery2", userNameQuery2);
		request.setAttribute("title", title);
		request.setAttribute("money", money);
		return "success";
	}
	/**
	 * ���
	 * @return
	 * @throws Exception
	 */
	public String updateTransger() throws  Exception{
		
		String radio= request.getParameter("radio");
		String message=request.getParameter("message");
		String transferId=request.getParameter("transferId");
		
		Transfer transfer=transferService.findTransferByTransferId(Integer.parseInt(transferId));
		if("1".equals(radio)){//ͨ��
			Tender tender=this.tenderService.findTenderById(transfer.getTender1().getTenderId());
			tender.setTransfer(Integer.valueOf(5));//ծȨת�ú�̨���ͨ��
			this.tenderService.updateTender(tender);
			transfer.setIsTransfer(1);
			addInbox("ծȨת�����", "����ծȨת�����ͨ������ע��鿴", transfer.getTender1().getUservip().getUserId());
			
		}else{
			transfer.setIsTransfer(0);
			addInbox("ծȨת�����", "����ծȨת�����δͨ����ԭ��"+message, transfer.getTender1().getUservip().getUserId());
		}
		
		// ���̨ϵͳ��־
		Oerationlog oerationlog = new Oerationlog();
		oerationlog.setManager(this.managerService
				.findManagerByManagerId(Integer.valueOf(this.managerId)));
		oerationlog.setOerationCategory("���ת������");
		oerationlog.setOerationChangeTime(new Timestamp(new Date().getTime()));
		oerationlog.setOrationIp(InetAddress.getLocalHost().getHostAddress());
		oerationlog.setOerationRemaks("���ת�����룬ID��"+transfer.getTransferId());
		this.oerationlogService.createOerationlog(oerationlog);
		
		transferService.updateTransfer(transfer);
		return "success";
	}
/**
 * ����ծȨת��
 * */	
	public String cancelTransfer()throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		String tenderId = request.getParameter("tenderId");
		Tender tender = this.tenderService.findTenderById(Integer.valueOf(tenderId));
		tender.setTransfer(Integer.valueOf(4));//����ת��
		this.tenderService.updateTender(tender);
		Transfer transfer = this.transferService.findTransferByTenderId1(Integer.valueOf(tenderId));
		transfer.setCancelNum(transfer.getCancelNum()+1);
		transfer.setCancelTime(new Timestamp(new Date().getTime()));
		transfer.setIsTransfer(Integer.valueOf(3));//����ת��
		this.mark =1 ;
		return "success";
	}

	
	
	
	
	public int getManagerId() {
		return this.managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public int getMark() {
		return this.mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public Integer getPage() {
		return this.page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageCount() {
		return this.pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getTotal() {
		return this.total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}


	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public String getPostform() {
		return postform;
	}
	public void setPostform(String postform) {
		this.postform = postform;
	}
	
}