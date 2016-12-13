/**  
 * @Project: longpei
 * @Title: WapLssuingtAction.java
 * @Package com.jqg.wap
 * @date 2015-7-22 ����11:27:52
 * @Copyright: 2015 
 */
package com.jqg.wap;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.jqg.pojo.Inbox;
import com.jqg.pojo.Lssingphoto;
import com.jqg.pojo.Lssuing;
import com.jqg.pojo.Moneycount;
import com.jqg.pojo.Moneyhistorydetail;
import com.jqg.pojo.Record;
import com.jqg.pojo.Systemconf;
import com.jqg.pojo.Tender;
import com.jqg.pojo.Transfer;
import com.jqg.pojo.Uservip;
import com.jqg.pojo.Website;
import com.jqg.service.InboxService;
import com.jqg.service.LssingphotoService;
import com.jqg.service.LssuingService;
import com.jqg.service.MoneycountService;
import com.jqg.service.MoneyhistorydetailService;
import com.jqg.service.RecordService;
import com.jqg.service.SystemconfService;
import com.jqg.service.TenderService;
import com.jqg.service.TransferService;
import com.jqg.service.UservipService;
import com.jqg.service.WebsiteService;
import com.jqg.service.impl.InboxServiceImpl;
import com.jqg.service.impl.LssingphotoServiceImpl;
import com.jqg.service.impl.LssuingServiceImpl;
import com.jqg.service.impl.MoneycountServiceImpl;
import com.jqg.service.impl.MoneyhistorydetailServiceImpl;
import com.jqg.service.impl.RecordServiceImpl;
import com.jqg.service.impl.SystemconfServiceImpl;
import com.jqg.service.impl.TenderServiceImpl;
import com.jqg.service.impl.TransferServiceImpl;
import com.jqg.service.impl.UservipServiceImpl;
import com.jqg.service.impl.WebsiteServiceImpl;
import com.jqg.struts.BaseAction;
import com.jqg.util.LoanUtils;
import com.jqg.util.MD5Util;
import com.opensymphony.xwork2.ActionContext;
/**
 * 
 * ������WapLssuingtAction
 * ���ܣ�
 * ��ϸ��
 * ���ߣ����е�(caozhongde)
 * �汾��1.0
 * ���ڣ�2015-7-22 ����11:27:52
 *
 */
public class WapLssuingtAction extends BaseAction{
	private static HttpServletRequest request;
	private String sessionId = "";
	TenderService tenderService=new TenderServiceImpl();
	LssuingService lssuingService=new LssuingServiceImpl();
	LssingphotoService lssingphotoService=new LssingphotoServiceImpl();
	RecordService recordService=new RecordServiceImpl();
	UservipService uservipService=new UservipServiceImpl();
	MoneycountService moneycountService=new MoneycountServiceImpl();
	MoneyhistorydetailService moneyhistorydetailService=new MoneyhistorydetailServiceImpl();
	private SystemconfService systemconfService = new SystemconfServiceImpl();
	private WebsiteService websiteService = new WebsiteServiceImpl();
	private TransferService transferService = new TransferServiceImpl();
//	private InboxService inboxService = new InboxServiceImpl();
	private String postform;
	
	public String toInverst() throws Exception{
		request = ServletActionContext.getRequest();
		this.sessionId = request.getSession().getId();
		Uservip uservip = (Uservip) ActionContext.getContext().getSession()
				.get("uservip" + sessionId);
		//�б��еĽ��
		List<Tender> tender2 = this.tenderService.findTendersByuserIdCategoryPage(uservip.getUserId().intValue(), 2, 0,0);
		//�����еĽ��
		List<Tender> tender3 = this.tenderService.findTendersByuserIdCategoryPage(uservip.getUserId().intValue(), 3, 0,0);
		//����ɵĽ��
		List<Tender> tender4 = this.tenderService.findTendersByuserIdCategoryPage(uservip.getUserId().intValue(), 4, 0,0);
		
		request.setAttribute("tender2", tender2);
		request.setAttribute("tender3", tender3);
		request.setAttribute("tender4", tender4);
		return "success";
	}
	
	public String toBorrow() throws Exception{
		request = ServletActionContext.getRequest();
		this.sessionId = request.getSession().getId();
		Uservip uservip = (Uservip) ActionContext.getContext().getSession().get("uservip" + sessionId);
		
		String sql = "select * from Lssuing l where  l.userId=" + uservip.getUserId()
				+ " and l.state=2 order by borrowTime desc";
		List<Lssuing> lList2=lssuingService.findLssuingsBySearch(sql);
		sql = "select * from Lssuing l where  l.userId=" + uservip.getUserId()
				+ " and l.state=3 order by borrowTime desc";
		List<Lssuing> lList3=lssuingService.findLssuingsBySearch(sql);
		sql = "select * from Lssuing l where  l.userId=" + uservip.getUserId()
				+ " and l.state=4 order by borrowTime desc";
		List<Lssuing> lList4=lssuingService.findLssuingsBySearch(sql);
		
		
		request.setAttribute("lList2", lList2);
		request.setAttribute("lList3", lList3);
		request.setAttribute("lList4", lList4);
		return "success";
	}
	
	public String toTender() throws NumberFormatException, Exception{
		request = ServletActionContext.getRequest();
		this.sessionId = request.getSession().getId();
//		Uservip uservip = (Uservip) ActionContext.getContext().getSession().get("uservip" + sessionId);
		String lssuingId=request.getParameter("lssuingId");
		if(lssuingId!=null&&!"".equals(lssuingId)){
			Lssuing lssuing=lssuingService.findLssuingById(Integer.parseInt(lssuingId));
			String state="";
			int money=0;
			String away="";
			if (lssuing.getState().intValue() == 0)
				state = "�����";
			else if (lssuing.getState().intValue() == 1)
				state = "������";
			else if (lssuing.getState().intValue() == 2)
				state = "Ͷ����";
			else if (lssuing.getState().intValue() == 3)
				state = "������";
			else if (lssuing.getState().intValue() == 4)
				state = "�����";
			else if (lssuing.getState().intValue() == -1) {
				state = "����";
			}
			List<Tender> tenders = this.tenderService
					.findTendersBylssuingId(lssuing.getLssuingId().intValue());
			if (tenders.size() == 0) {
				money = 0;
			} else {
				for (Tender tender : tenders) {
					money = tender.getMoney().intValue() + money;
				}
			}
			if (lssuing.getIsAward().intValue() == 0) {
				away = "�޽���";
			} else {
				if (lssuing.getAwardRate() != null) {
					away = "��Ͷ�����������" + lssuing.getAwardRate() + "%";
				} else if (lssuing.getAwardMoney() != null) {
					away = "���̶�����̯����" + lssuing.getAwardMoney();
				}

			}
			
			List<Lssingphoto> LPlist=this.lssingphotoService.findLssingphotosBylssuing(lssuing.getLssuingId());
			String sql = "SELECT * FROM tender t WHERE t.lssuingId = "+ lssuing.getLssuingId() + " ORDER BY t.tenderId DESC ";
			List<Tender> Tlist=tenderService.findTendersBylssuingIdPage(sql);
			for(Tender t:Tlist){
				Uservip uservip=t.getUservip();
				String name=uservip.getUserName();
				name = name.substring(0, 1) + "***";
				uservip.setUserNames(name);
				t.setUservip(uservip);
			}
			
			String k =""+( money * 100.0 / Integer.parseInt(lssuing.getBorrowMoney()));
			double bear = Double.parseDouble(k);
			money=Integer.parseInt(lssuing.getBorrowMoney())-money;
			request.setAttribute("lssuing", lssuing);
			request.setAttribute("state", state);
			request.setAttribute("LPlist", LPlist);
			request.setAttribute("money", money);
	 		request.setAttribute("Tlist", Tlist);
	 		request.setAttribute("bear", bear);
	 		request.setAttribute("away", away);
		}
		return "success";
	}
	public String toTenderSubmit() throws NumberFormatException, Exception{
		request = ServletActionContext.getRequest();
		this.sessionId = request.getSession().getId();
		String lssuingId=request.getParameter("lssuingId");
		Lssuing lssuing=null;
		if(lssuingId!=null&&!"".equals(lssuingId)){
			lssuing=lssuingService.findLssuingById(Integer.parseInt(lssuingId));
			int money=0;
			List<Tender> tenders = this.tenderService
					.findTendersBylssuingId(lssuing.getLssuingId().intValue());
			if (tenders.size() == 0) {
				money = 0;
			} else {
				for (Tender tender : tenders) {
					money = tender.getMoney().intValue() + money;
				}
			}
			money=Integer.parseInt(lssuing.getBorrowMoney())-money;
			request.setAttribute("lssuing", lssuing);
			request.setAttribute("money", money);
		}
			return "success";
	}
	
	/**
	 * Ͷ��
	 * 
	 * @return
	 * @throws Exception
	 */
	public String tender() throws Exception {
		
		request = ServletActionContext.getRequest();
		sessionId = request.getSession().getId();

		Uservip uservip = (Uservip) ActionContext.getContext().getSession()
				.get("uservip" + sessionId);
		String paypwd=request.getParameter("paypwd");
		String lssuingId=request.getParameter("lssuingId");
		String investMoney=request.getParameter("investMoney");
		
		if (paypwd == null || "".equals(paypwd)) {
			this.setMessage("������֧������", "/wap/toTenderSubmit?lssuingId="
					+ lssuingId, "3");
			return "success";
		}
		if (!uservip.getPayPwd().equals(MD5Util.md5(paypwd))) {
			this.setMessage("�������֧�����벻��ȷ��",
					"/wap/toTenderSubmit?lssuingId=" + lssuingId, "3");
			return "success";
		}
		Uservip user = uservipService.findUservipByPayPwdAndUserID(
				MD5Util.md5(paypwd), uservip.getUserId());
		if (user == null) {
			this.setMessage("�������֧�����벻��ȷ��",
					"/wap/toTenderSubmit?lssuingId=" + lssuingId, "3");
			return "success";
		}

		Moneycount moneycount = moneycountService
				.findMoneycountByuserId(uservip.getUserId().intValue());
		if ((moneycount.getAvailableMoney().doubleValue() - Double.valueOf(investMoney).doubleValue()) < 0) {
			this.setMessage("�˻����С��Ͷ����", "/wap/toTenderSubmit?lssuingId="+ lssuingId, "3");
			return "success";
		}
//		if(uservip.getTrustStatus()==1 && uservip.getTrustAccount()!=null && !uservip.getTrustAccount().equals("")){
//			
//		}else{
//			this.setMessage("���ȿ�ͨ�й��˺��ٽ��в���", "/wap/toTenderSubmit?lssuingId="+ lssuingId, "3");
//			return "success";
//		}
//		
//		if(uservip.getAuthorizeStatus()==1){
//			
//		}else{
//			this.setMessage("���ȿ�ͨ��Ȩ�ٽ��в���", "/wap/toTenderSubmit?lssuingId="+ lssuingId, "3");
//			return "success";
//		}
		Tender tender = new Tender();
		Lssuing lssuing=this.lssuingService.findLssuingById(Integer.valueOf(lssuingId));
		
		
		if(lssuing.getLssuingType()==5){
			String sql="select * from tender where userId="+uservip.getUserId();
			List<Tender> tends=this.tenderService.findTenderBySql(sql);
			if(tends.size()>0 && tends!=null){
				this.setMessage("���ֱ�ֻ��Ͷһ��", "/wap/toTenderSubmit?lssuingId="+ lssuingId, "3");
				return "success";
			}
		}
		
		if(lssuing.getLssuingType()==8){
			this.setMessage("�ݲ�֧�������Ͷ��", "/wap/toTenderSubmit?lssuingId="+ lssuingId, "3");
			return "success";
		}
		
		tender.setLssuing(lssuing);
		tender.setUservip(uservip);
		tender.setMoney(Integer.valueOf(investMoney));

		List<Tender> tenderss = this.tenderService
				.findTendersBylssuingId(Integer.valueOf(lssuingId));
		int mon = 0;
		if (tenderss.size() == 0) {
			mon = 0;
		} else {
			for (Tender ten : tenderss) {
				mon = ten.getMoney().intValue() + mon;
			}
		}
		if (Integer.valueOf(Integer.valueOf(
				tender.getLssuing().getBorrowMoney()).intValue()
				- mon) < Integer.parseInt(investMoney)) {
			this.setMessage("������Ľ�����ʣ���",
					"/wap/toTenderSubmit?lssuingId=" + lssuingId, "3");
			return "success";
		}
		
		
		
		uservip.setUserNames("app");
		LoanUtils loanUtils = new LoanUtils();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		tender.setTenderTime(timestamp);
		Systemconf managerconf = null;
		Systemconf dealConf = null;
		Website website = this.websiteService.findWebsiteBywebsiteId(1);
		uservip.setUserNames("wap");
		if(lssuing.getLssuingType()!=9){
			if("1".equals(lssuing.getUservip().getIsVIP())){
				managerconf = this.systemconfService.findSystemconfByParname("con_borrow_manage_fee_2");
				
			}else{
				managerconf = this.systemconfService.findSystemconfByParname("con_borrow_manage_fee");
			}
			
			if("1".equals(lssuing.getUservip().getIsVIP())){
				dealConf = this.systemconfService.findSystemconfByParname("con_borrow_success_fee_2");
			}else{
				dealConf = this.systemconfService.findSystemconfByParname("con_borrow_success_fee");
			}
			postform = loanUtils.tender(uservip, tender, lssuing, managerconf, dealConf, website, request, false, false);
		}else{
			postform = loanUtils.lctender(uservip, tender, lssuing, managerconf, dealConf, website, request, false, true);
		}
		
		return "tranjump";
//		int tMon = Integer.valueOf(investMoney).intValue();
//		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//		tender.setTenderTime(timestamp);
//		tender.setState(0);
//		boolean flag = this.tenderService.addTender(tender);
//		if (flag) {
//			List<Tender> tenders = this.tenderService
//					.findTendersBylssuingId(Integer.valueOf(lssuingId)
//							.intValue());
//			int money = 0;
//			if (tenders.size() > 0) {
//				for (Tender tender3 : tenders) {
//					money = tender3.getMoney().intValue() + money;
//				}
//			}
//			Lssuing lssuing3 = this.lssuingService.findLssuingById(Integer
//					.valueOf(lssuingId));
//			int BorrowMoney = Integer.valueOf(lssuing3.getBorrowMoney())
//					.intValue();
//			if (BorrowMoney - money == 0) {
//				lssuing3.setState(Integer.valueOf(1));
//				this.lssuingService.updateLssuing(lssuing3);
//			}
//			Moneyhistorydetail moneyhistorydetail = new Moneyhistorydetail();
//			moneyhistorydetail.setAvailableBalance(Double.valueOf(moneycount
//					.getAvailableMoney().doubleValue()
//					- Double.valueOf(tMon).doubleValue()));
//			moneyhistorydetail.setFrozenMoney(Double.valueOf(moneycount
//					.getFrozenMoney().doubleValue()
//					+ Double.valueOf(tMon).doubleValue()));
//			moneyhistorydetail.setAffectMoney(Double.valueOf(tMon));
//			moneyhistorydetail.setCollectMoney(moneycount.getCollectTotalMoney());
//			moneyhistorydetail.setIntroduction("Ͷ��ɹ�������Ͷ����");
//			moneyhistorydetail.setUservip(uservip);
//			moneyhistorydetail
//					.setOccurTime(new Timestamp(new Date().getTime()));
////			moneyhistorydetail.setType(1);
//			moneyhistorydetailService
//					.createMoneyhistorydetail(moneyhistorydetail);
//
//			moneycount.setAvailableMoney(Double.valueOf(moneycount
//					.getAvailableMoney().doubleValue()
//					- Double.valueOf(tMon).doubleValue()));
//			moneycount.setFrozenMoney(Double.valueOf(moneycount
//					.getFrozenMoney().doubleValue()
//					+ Double.valueOf(tMon).doubleValue()));
//			boolean flag2 = this.moneycountService.updateMoneycount(moneycount);
//
//			if (flag2) {
//				this.setMessage("Ͷ��ɹ�", "/wap/toTender?lssuingId="
//						+ lssuingId, "3");
//				return "success";
//			}
//
//		} else {
//			this.setMessage("Ͷ��ʧ��", "/wap/toTenderSubmit?lssuingId="
//					+ lssuingId, "3");
//			return "success";
//		}
//		return "success";
	}
	
	public String tenderList() throws Exception{
		request=ServletActionContext.getRequest();
		String lssuingType=request.getParameter("lssuingType");
		
		String sql=" select * from lssuing l where  l.state in(1,2,3,4)";
		if(lssuingType!=null && !"".equals(lssuingType)){
			sql=sql+" and l.lssuingType="+lssuingType;
		}else{
			sql=sql+" and l.lssuingType<6";
		}
		sql=sql+" order by lssuingId  desc";
		List<Lssuing> lssuingss=lssuingService.findLssuingsBySearch(sql);
		
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
			if(l.getPeriodday()!=null && l.getPeriodday().getPeriodDayId()!=null){
				l.setRates(Double.valueOf(l.getRate()*360));
			}else{
				l.setRates(l.getRate());
			}
			
			lssuings.add(l);
		}
		
		ActionContext.getContext().put("lList", lssuings);
		return "success";
	}
	/**
	 * Ͷ���ҳ
	 * ��Ʊ�lussingType==9
	 * ��ҵ��lussingType==6
	 * @throws Exception
	 */
	public String lssuinglist() throws Exception{
		request = ServletActionContext.getRequest();
		String lussingType = request.getParameter("lssuingType");
		System.out.println("lussingType:"+lussingType);
		String sql = "SELECT * FROM lssuing l where l.lssuingType = "+lussingType;
		List<Lssuing> lssuingss=lssuingService.findLssuingsBySearch(sql);
		
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
			l.setScale(scale);//������
			if(l.getPeriodday()!=null && l.getPeriodday().getPeriodDayId()!=null){
				l.setRates(Double.valueOf(l.getRate()*360));
			}else{
				l.setRates(l.getRate());
			}
			
			lssuings.add(l);
		}
		
		ActionContext.getContext().put("lList", lssuings);
		return "success";
	}
	/**
	 * ��ת��ծȨ�б�
	 * 
	 * @throws Exception
	 */
	public String transferlistPage() throws Exception {
		request = ServletActionContext.getRequest();
		String sql = "from Tender t where (t.transfer=5 or t.transfer=3)";	
		List<Tender> tenders3 = this.tenderService.findTenderByHql(sql,-1,-1);
		List<Lssuing> transferList=new ArrayList<Lssuing>();
		String path = request.getContextPath();
		DecimalFormat df1 = new DecimalFormat("0.00");
 
		for (Tender tender : tenders3) {
			double benxi;
			String lilv = "";
			if (tender.getLssuing().getPeriodday() != null) {
				lilv = df1.format(tender.getLssuing().getRate());
			} else {
				lilv = df1.format(tender.getLssuing().getRate());
			}
			String actions = path+"/wap/findTransferByID.action?tenderId="+ tender.getTenderId();
			String state = "";
			if(tender.getTransfer()==3){
				state = "�����";
			}else{
				state = "����Ͷ��";
			}
			Lssuing lss = new Lssuing();
			Transfer transfer = this.transferService.findTransferByTenderId(tender.getTenderId());
			Double totalAccount = transfer.getProfit();
			lss.setTitle(tender.getLssuing().getTitle());//���
			lss.setRate(Double.parseDouble(lilv));//����
			lss.setBorrowMoney(totalAccount.toString());//���ձ�Ϣ��ծȨ��ֵ��
			lss.setReturnMoney(transfer.getMoney());//ת�ü۸�
			lss.setExplainOne(state);//״̬
			lss.setExplains(actions);//��ת����
			transferList.add(lss);
		}

		ActionContext.getContext().put("transferList", transferList);
		return "success";
	}
	/**
	 * ծȨת�õ���ϸҳ��
	 * 
	 * @return
	 * @throws Exception
	 */
	public String findTransferByID() throws Exception {
		request = ServletActionContext.getRequest();
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
		request.setAttribute("benxistr", benxistr);//��Ϣ
		request.setAttribute("transferMoney", transfer.getMoney());//�Ϲ��۸�
		request.setAttribute("username", uservip1.getUserName());//������
		request.setAttribute("tenderId", tender1.getTenderId());//Ͷ��Id
		request.setAttribute("transfer", tender1.getTransfer());
		
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
		List<Lssuing> lssuings = this.lssuingService.findLssuingsByUserId(lssuing.getUservip().getUserId().intValue());
		List lssuings1 = this.lssuingService.findLssuingsByUserIdAndByState(lssuing.getUservip().getUserId().intValue(), 3);

		request.setAttribute("lssuingTdener", Integer.valueOf(lssuings.size()));

		double zonge = 0.0D;
		for (Lssuing lssuing2 : lssuings) {
			zonge = Double.valueOf(lssuing2.getBorrowMoney()).doubleValue()+ zonge;
		}
		String zongenum = df1.format(zonge);
		request.setAttribute("zonge", zongenum);//�ܶ�
		request.setAttribute("lssuingTdener", Integer.valueOf(lssuings.size()));
		request.setAttribute("lssuingTdenerDai", Integer.valueOf(lssuings1.size()));

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
		request.setAttribute("daihuan", daihuannum);

		List lssuings2 = this.lssuingService.findLssuingsByUserIdAndByState(lssuing.getUservip().getUserId().intValue(), -1);
		request.setAttribute("yuqishu", Integer.valueOf(lssuings2.size()));

		List lssuings3 = this.lssuingService.findLssuingsByUserIdAndByState(
				lssuing.getUservip().getUserId().intValue(), 4);
		request.setAttribute("yiwancheng", Integer.valueOf(lssuings3.size()));
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
		this.sessionId = request.getSession().getId();
		Uservip uservip = (Uservip) ActionContext.getContext().getSession().get("uservip" + sessionId);
		if (uservip != null) {

			System.err.println(uservip.getUserId());
			Moneycount moneycount = this.moneycountService.findMoneycountByuserId(uservip.getUserId());
			request.setAttribute("AvailableMoney", moneycount.getAvailableMoney());
			request.setAttribute("moneycount", moneycount);
			if (lssuing.getTenderLimit().intValue() == 1) {
				request.setAttribute("moneyLimit", moneycount.getCollectTotalMoney());
			} else {
				ActionContext.getContext().getSession()
						.put("moneyLimit", Integer.valueOf(0));
			}
		}
		request.setAttribute("money",Integer.valueOf(Integer.valueOf(lssuing.getBorrowMoney()).intValue()- money));
		request.setAttribute("interest", df.format(interest));//���Ϣ
		request.setAttribute("picture", picture);
		request.setAttribute("away", away);//����
		request.setAttribute("lssuing", lssuing);
		request.setAttribute("number", number);
		request.setAttribute("bear", Double.valueOf(bear));//����
		request.setAttribute("day", day);
		request.setAttribute("moneyMin", moneyMin);
		request.setAttribute("moneyMax", moneyMax);
		request.setAttribute("lssList", lssList);
		return "success";
	}
	/**
	 * ծȨ����(�й�)
	 * */
		public String transferBuy()throws Exception{
			request = ServletActionContext.getRequest();
			WebsiteService websiteService = new WebsiteServiceImpl();
			String tenderId = request.getParameter("tenderId");//ת���˵�Ͷ�ʼ�¼id
			String payPwd = request.getParameter("payPwd");//֧������
			String lssuingId = request.getParameter("lssuingId");
			this.sessionId = request.getSession().getId();
			System.out.println("sessionId"+sessionId+"������"+payPwd+"tenderId"+tenderId);
			Uservip uservip = (Uservip) ActionContext.getContext().getSession().get("uservip" + sessionId);
			Lssuing lssuing1 = this.lssuingService.findLssuingById(Integer.valueOf(lssuingId));
			if(!uservip.getPayPwd().equals(MD5Util.md5(payPwd))){
				this.setMessage("�������֧�����벻��ȷ��", "/wap/findTransferByID.action?tenderId="+tenderId, "3");
				return "error";
			}else{
				
				Transfer transfer  = this.transferService.findTransferByTenderId1(Integer.valueOf(tenderId));//���ݹ���id���Ҷ���
				Lssuing lssuing = transfer.getTender1().getLssuing();
				
				if(transfer.getIsTransfer()==2){
					this.setMessage("�Ѿ�ת�ó�ȥ�����ܹ���", "/wap/findTransferByID.action?tenderId="+tenderId, "3");
					return "error";
				}
				
				if(transfer.getIsTransfer()==3){
					this.setMessage("�û��Ѿ����������ܹ���", "/wap/findTransferByID.action?tenderId="+tenderId, "3");
					return "error";
				}
				
				if(transfer.getIsTransfer()==4){
					this.setMessage("�Ѿ�ʧЧ�����ܹ���", "/wap/findTransferByID.action?tenderId="+tenderId, "3");
					return "error";
				}
				
				Moneycount moneycount1 = this.moneycountService.findMoneycountByuserId(uservip.getUserId());//����id����
				
				if(moneycount1.getAvailableMoney()<transfer.getMoney()){
					this.setMessage("���㣬���ֵ����", "/wap/findTransferByID.action?tenderId="+tenderId, "3");
					return "error";
				}
				Tender tender = this.tenderService.findTenderById(Integer.valueOf(tenderId));
				if(uservip.getUserId()==tender.getUservip().getUserId()){
					this.setMessage("�Լ����ܹ����Լ���ծȨ", "/wap/findTransferByID.action?tenderId="+tenderId, "3");
					return "error";
				}
				if(uservip.getTrustStatus()==1 && uservip.getTrustAccount()!=null && !uservip.getTrustAccount().equals("")){
					
				}else{
					this.setMessage("���ȿ�ͨ�й��˺��ٽ��в���", "/wap/findTransferByID.action?tenderId="+tenderId, "3");
					return "error";
				}
				
				if(uservip.getAuthorizeStatus()==1){
					
				}else{
					this.setMessage("���ȿ�ͨ��Ȩ�ٽ��в���", "/wap/findTransferByID.action?tenderId="+tenderId, "3");
					return "error";
				}
				uservip.setUserNames("wap");
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
	public String getPostform() {
		return postform;
	}

	public void setPostform(String postform) {
		this.postform = postform;
	}
	
	
	
}
