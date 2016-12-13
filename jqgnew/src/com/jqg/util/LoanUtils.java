package com.jqg.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.jqg.pojo.Certification;
import com.jqg.pojo.Lssuing;
import com.jqg.pojo.Manager;
import com.jqg.pojo.Oerationlog;
import com.jqg.pojo.Offlinerecharge;
import com.jqg.pojo.Record;
import com.jqg.pojo.Repaynote;
import com.jqg.pojo.Systemconf;
import com.jqg.pojo.Tender;
import com.jqg.pojo.Transfer;
import com.jqg.pojo.Translate;
import com.jqg.pojo.Uservip;
import com.jqg.pojo.Website;
import com.loan.model.IDCardBean;
import com.loan.model.LoanInfoBean;
import com.loan.model.LoanInfoSecondaryBean;

/**
 * 托管工具类实现包括（转账接口组装form表单，复审、还款、回款续投等业务流程，组装成form表单）
 * 
 * @author Administrator
 * 
 */
public class LoanUtils {

	private String LoanJsonList = "";
	private String TransferAction = "";
	private String PlatformMoneymoremore = "";
	private String SubmitURL = "";
	private String SubmitURLPrefix = "http://218.4.234.150:88/main/";
	private String ReturnURL = "";
	private String NotifyURL = "";
	private String SignInfo = "";
	private String ResultCode = "";
	private String Message = "";
	private String ReturnTimes = "";
	private String verifySignature = "";
	private String RandomTimeStamp = "";
	private final int antistate = 0;
	private String Action = "";
	private String TransferType = "";
	private String NeedAudit = "";
	private String Remark1 = "";
	private String Remark2 = "";
	private String Remark3 = "";
	// 审核
	private String LoanNoList = "";
	private String LoanNoListFail = "";
	private String AuditType = "";

	// 充值
	private String RechargeMoneymoremore = "";
	private String OrderNo = "";
	private String Amount = "";
	private String FeePlatform = "";
	private String RechargeType = "";
	private String FeeType = "";
	private String CardNoList = "";

	// 提现
	private String WithdrawMoneymoremore = "";
	private String FeePercent = "";
	private String Fee = "";
	private String FreeLimit = "";
	private String FeeMax = "";
	private String FeeWithdraws = "";
	private String FeeRate = "";
	private String FeeSplitting = "";
	private String CardNo = "";
	private String CardType = "";
	private String BankCode = "";
	private String BranchBankName = "";
	private String Province = "";
	private String City = "";
	private boolean target = true;

	private boolean istest = false;// true ：测试，false:正式

	public LoanUtils() {

	}

	
	public String plat_transfer(Website website,Uservip uservip,double money,String remark,HttpServletRequest request){
		List<LoanInfoBean> listmlib = new ArrayList<LoanInfoBean>();
		String SecondaryJsonList = "";
		LoanInfoBean mlib = new LoanInfoBean();
		if(money>0){
			mlib.setLoanOutMoneymoremore(website.getTrustAccount());
			mlib.setLoanInMoneymoremore(uservip.getTrustAccount());
			mlib.setTransferName("plattoparty");
		}else{
			mlib.setLoanOutMoneymoremore(uservip.getTrustAccount());
			mlib.setLoanInMoneymoremore(website.getTrustAccount());
			mlib.setTransferName("partytoplat");
		}
		
		mlib.setOrderNo(uservip.getUserId().intValue() + "_"
				+ website.getTrustAccount() + "_"
				+ System.currentTimeMillis());
		mlib.setBatchNo(Common.getRandomNum(10));
		mlib.setExchangeBatchNo("");
		mlib.setAdvanceBatchNo("");
		mlib.setAmount(String.valueOf(Math.abs(money)));
		mlib.setFullAmount(String.valueOf(Math.abs(money)));
		
		mlib.setRemark(uservip.getUserId().toString());
		mlib.setSecondaryJsonList(SecondaryJsonList);
		listmlib.add(mlib);

		LoanJsonList = Common.JSONEncode(listmlib);

		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";

		if(istest){
			this.SubmitURL = "http://218.4.234.150:88/main/loan/loan.action";
		}else{
			this.SubmitURL = "https://transfer.moneymoremore.com/loan/loan.action";
		}
		
		
//		this.SubmitURL = SubmitURLPrefix + "loan/loan.action";
		ReturnURL = basePath + "loan/platTransferreturn.action";
		NotifyURL = basePath + "loan/platTransfernotify.action";

		PlatformMoneymoremore = website.getTrustAccount();

		String privatekey = website.getPrivateKey();// Common.privateKeyPKCS8;

		if (antistate == 1) {
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			RandomTimeStamp = Common.getRandomNum(2) + sdf.format(d);
		}
		TransferAction = "1";// 投标，2：还款，3：其他
		Action = "";

		this.target  = true;
		Action = "1";
		TransferType = "2";
		NeedAudit = "1";
		Remark1 = remark;

		String dataStr = LoanJsonList + PlatformMoneymoremore + TransferAction
				+ Action + TransferType + NeedAudit + RandomTimeStamp + Remark1
				+ Remark2 + Remark3 + ReturnURL + NotifyURL;
		
		System.out.println(dataStr);
		// 签名
		MD5Trust md5 = new MD5Trust();
		RsaHelper rsa = RsaHelper.getInstance();
		if (antistate == 1) {
			dataStr = md5.getMD5Info(dataStr);
		}
		SignInfo = rsa.signData(dataStr, privatekey);

		LoanJsonList = Common.UrlEncoder(LoanJsonList, "utf-8");
		if (Action.equals("2")) {// 自动付款，http提交
			String[] resultarr = this.traansferhttp();
			if (StringUtils.isNotBlank(resultarr[1])
					&& (resultarr[1].startsWith("[") || resultarr[1]
							.startsWith("{"))) {
				return resultarr[1];
			}
		} else {// 组装form表单
			return transferForm1();
		}

		return null;
		
	}
	
	
	public String nameaudit( Website website,Certification certification,HttpServletRequest request){
				
		List<Map<String, String>> listidentity = new ArrayList<Map<String, String>>();
		if (StringUtils.isNotBlank(certification.getRealName()) && StringUtils.isNotBlank(certification.getIdNum()))
		{
			Map<String, String> maptemp = new TreeMap<String, String>();
			maptemp.put("realName", certification.getRealName());
			maptemp.put("identificationNo", certification.getIdNum());
			listidentity.add(maptemp);
		}
		String IdentityJsonList = Common.JSONEncode(listidentity);
		
		PlatformMoneymoremore = website.getTrustAccount();
		
		if(istest){
			this.SubmitURL = "http://218.4.234.150:88/main/authentication/identityMatching.action";
		}else{
			this.SubmitURL = "https://loan.moneymoremore.com/authentication/identityMatching.action";
		}
		String privatekey = website.getPrivateKey();// Common.privateKeyPKCS8;
		
		
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";

		NotifyURL = basePath + "loan/nameAuditnotify.action";
		
		if (antistate == 1)
		{
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			RandomTimeStamp = Common.getRandomNum(2) + sdf.format(d);
		}
		
		String dataStr = PlatformMoneymoremore + IdentityJsonList +RandomTimeStamp + NotifyURL;
		
		System.out.println(dataStr);
		// 签名
		MD5Trust md5 = new MD5Trust();
		RsaHelper rsa = RsaHelper.getInstance();
		if (antistate == 1)
		{
			dataStr = md5.getMD5Info(dataStr);
		}
		SignInfo = rsa.signData(dataStr, privatekey);
		
		IdentityJsonList = Common.UrlEncoder(IdentityJsonList, "utf-8");
		
		Map<String, String> req = new TreeMap<String, String>();
		req.put("PlatformMoneymoremore", PlatformMoneymoremore);
		req.put("IdentityJsonList", IdentityJsonList);
		req.put("RandomTimeStamp", RandomTimeStamp);
		// req.put("Remark1", Remark1);
		// req.put("Remark2", Remark2);
		// req.put("Remark3", Remark3);
		req.put("NotifyURL", NotifyURL);
		req.put("SignInfo", SignInfo);
		
		System.out.println(req.toString());
		
		System.out.println(SubmitURL);
		String[] resultarr = HttpClientUtil.doPostQueryCmd(SubmitURL, req);
		System.out.println(resultarr[1]);
		
		
		return null;
	}
	
	
	public Map tgtender(Uservip tenderuser, Tender tender, Lssuing lssuing,
			Systemconf managerconf, Systemconf dealConf, Website website,
			HttpServletRequest request, boolean isauto, boolean isautoAudit) {

		Map map = new HashMap<String, Object>();

		List<LoanInfoSecondaryBean> listmlisb = new ArrayList<LoanInfoSecondaryBean>();
		DecimalFormat df1 = new DecimalFormat("0.00");
		// 投标奖励
		Double lsawardmoney = 0.0;
		if ((lssuing.getAwardRate() != null)
				&& (!"".equals(lssuing.getAwardRate()))) {
			lsawardmoney = Double.valueOf(Double.valueOf(tender.getMoney()
					* Double.valueOf(lssuing.getAwardRate()) / 100.0D));
		} else if ((lssuing.getAwardMoney() != null)
				&& (!"".equals(lssuing.getAwardMoney()))) {
			lsawardmoney = Double.valueOf(Double.valueOf(
					tender.getMoney().intValue()).doubleValue()
					/ Double.valueOf(lssuing.getBorrowMoney()).doubleValue()
					* Double.valueOf(lssuing.getAwardMoney()).doubleValue());
		}
		LoanInfoSecondaryBean mlisb = new LoanInfoSecondaryBean();
		if (lsawardmoney > 0) {
			if (lsawardmoney < 0.01) {
				lsawardmoney = 0.01;
			}
			mlisb = new LoanInfoSecondaryBean();
			mlisb.setLoanInMoneymoremore(tenderuser.getTrustAccount());
			mlisb.setAmount(String.valueOf(df1.format(lsawardmoney)));
			mlisb.setTransferName("tender_award");
			mlisb.setRemark("投标奖励");
			listmlisb.add(mlisb);
		}
		// 管理费
		Double parvalue = Double.valueOf(managerconf.getParvalue());
		double fee = parvalue * tender.getMoney() / 100.0;
		if (fee > 0) {
			mlisb = new LoanInfoSecondaryBean();
			mlisb.setLoanInMoneymoremore(website.getTrustAccount());
			mlisb.setAmount(String.valueOf(df1.format(fee)));
			mlisb.setTransferName("borrow_manager_fee");
			mlisb.setRemark("借款管理费");
			listmlisb.add(mlisb);
		}
		// 成交费
		parvalue = Double.valueOf(dealConf.getParvalue());
		fee = parvalue * tender.getMoney() / 100.0;
		if (fee > 0) {
			mlisb = new LoanInfoSecondaryBean();
			mlisb.setLoanInMoneymoremore(website.getTrustAccount());
			mlisb.setAmount(String.valueOf(df1.format(fee)));
			mlisb.setTransferName("borrow_deal_fee");
			mlisb.setRemark("借款成交费");
			listmlisb.add(mlisb);
		}
		String SecondaryJsonList = "";
		if (listmlisb.size() > 0) {
			SecondaryJsonList = Common.JSONEncode(listmlisb);
		}

		List<LoanInfoBean> listmlib = new ArrayList<LoanInfoBean>();

		LoanInfoBean mlib = new LoanInfoBean();

		mlib.setLoanOutMoneymoremore(tenderuser.getTrustAccount());
		mlib.setLoanInMoneymoremore(lssuing.getUservip().getTrustAccount());
		mlib.setOrderNo(tenderuser.getUserId().intValue() + "_"
				+ lssuing.getLssuingId().intValue() + "_"
				+ tender.getTenderTime().getTime());
		mlib.setBatchNo(lssuing.getLssuingNum());
		mlib.setExchangeBatchNo("");
		mlib.setAdvanceBatchNo("");
		mlib.setAmount(tender.getMoney().toString());
		mlib.setFullAmount(lssuing.getBorrowMoney());
		mlib.setTransferName("addtender");
		mlib.setRemark("用户投标" + lssuing.getTitle());
		mlib.setSecondaryJsonList(SecondaryJsonList);
		listmlib.add(mlib);

		LoanJsonList = Common.JSONEncode(listmlib);

		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";

		if (istest) {
			this.SubmitURL = "http://218.4.234.150:88/main/loan/loan.action";
		} else {
			this.SubmitURL = "https://transfer.moneymoremore.com/loan/loan.action";
		}

		// this.SubmitURL = SubmitURLPrefix + "loan/loan.action";
		ReturnURL = basePath + "loan/loantenderreturn.action";
		NotifyURL = basePath + "loan/loantendernotify.action";

		PlatformMoneymoremore = website.getTrustAccount();

		String privatekey = website.getPrivateKey();// Common.privateKeyPKCS8;

		if (antistate == 1) {
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			RandomTimeStamp = Common.getRandomNum(2) + sdf.format(d);
		}
		TransferAction = "1";// 投标，2：还款，3：其他
		Action = "";

		if (isauto) {
			Action = "2";
		} else {
			Action = "1";
		}
		TransferType = "2";
		NeedAudit = "";
		if (isautoAudit) {
			NeedAudit = "1";
		}
		Remark1 = tenderuser.getUserName() + "投资标号：" + lssuing.getLssuingNum();

		String dataStr = LoanJsonList + PlatformMoneymoremore + TransferAction
				+ Action + TransferType + NeedAudit + RandomTimeStamp + Remark1
				+ Remark2 + Remark3 + NotifyURL;

		System.out.println(dataStr);
		// 签名
		MD5Trust md5 = new MD5Trust();
		RsaHelper rsa = RsaHelper.getInstance();
		if (antistate == 1) {
			dataStr = md5.getMD5Info(dataStr);
		}
		SignInfo = rsa.signData(dataStr, privatekey);

		LoanJsonList = Common.UrlEncoder(LoanJsonList, "utf-8");

		map.put("LoanJsonList", LoanJsonList);
		map.put("PlatformMoneymoremore", PlatformMoneymoremore);
		map.put("TransferAction", TransferAction);
		map.put("Action", Action);
		map.put("TransferType", TransferType);
		map.put("NeedAudit", NeedAudit);
		map.put("Remark1", Remark1);
		map.put("Remark2", Remark2);
		map.put("Remark3", Remark3);
		map.put("NotifyURL", NotifyURL);
		map.put("SignInfo", SignInfo);

		return map;

	}

	public Map lctgtender(Uservip tenderuser, Tender tender, Lssuing lssuing,
			Systemconf managerconf, Systemconf dealConf, Website website,
			HttpServletRequest request, boolean isauto, boolean isautoAudit){
		Map map = new HashMap<String, Object>();
		


		List<LoanInfoSecondaryBean> listmlisb = new ArrayList<LoanInfoSecondaryBean>();
		DecimalFormat df1 = new DecimalFormat("0.00");

		String SecondaryJsonList = "";
		if (listmlisb.size() > 0) {
			SecondaryJsonList = Common.JSONEncode(listmlisb);
		}

		List<LoanInfoBean> listmlib = new ArrayList<LoanInfoBean>();

		LoanInfoBean mlib = new LoanInfoBean();
		PlatformMoneymoremore = website.getTrustAccount();
		mlib.setLoanOutMoneymoremore(tenderuser.getTrustAccount());
		mlib.setLoanInMoneymoremore(PlatformMoneymoremore);
		mlib.setOrderNo(tenderuser.getUserId().intValue() + "_"
				+ lssuing.getLssuingId().intValue() + "_"
				+ tender.getTenderTime().getTime());
		mlib.setBatchNo(lssuing.getLssuingNum());
		mlib.setExchangeBatchNo("");
		mlib.setAdvanceBatchNo("");
		mlib.setAmount(tender.getMoney().toString());
		mlib.setFullAmount(lssuing.getBorrowMoney());
		mlib.setTransferName("addlctender");
		mlib.setRemark("用户投资" + lssuing.getTitle());
		mlib.setSecondaryJsonList(SecondaryJsonList);
		listmlib.add(mlib);

		LoanJsonList = Common.JSONEncode(listmlib);

		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";

		if (istest) {
			this.SubmitURL = "http://218.4.234.150:88/main/loan/loan.action";
		} else {
			this.SubmitURL = "https://transfer.moneymoremore.com/loan/loan.action";
		}

		// this.SubmitURL = SubmitURLPrefix + "loan/loan.action"
		ReturnURL = basePath + "loan/loantenderreturn.action";
		NotifyURL = basePath + "loan/loantendernotify.action";

		String privatekey = website.getPrivateKey();// Common.privateKeyPKCS8;

		if (antistate == 1) {
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			RandomTimeStamp = Common.getRandomNum(2) + sdf.format(d);
		}
		TransferAction = "1";// 投标，2：还款，3：其他
		Action = "";

		if (isauto) {
			Action = "2";
		} else {
			Action = "1";
		}
		TransferType = "2";
		NeedAudit = "";
		if (isautoAudit) {
			NeedAudit = "1";
		}
		Remark1 = tenderuser.getUserName() + "投资标号：" + lssuing.getLssuingNum();

		String dataStr = LoanJsonList + PlatformMoneymoremore + TransferAction
				+ Action + TransferType + NeedAudit + RandomTimeStamp + Remark1
				+ Remark2 + Remark3 + NotifyURL;

		System.out.println(dataStr);
		// 签名
		MD5Trust md5 = new MD5Trust();
		RsaHelper rsa = RsaHelper.getInstance();
		if (antistate == 1) {
			dataStr = md5.getMD5Info(dataStr);
		}
		SignInfo = rsa.signData(dataStr, privatekey);

		LoanJsonList = Common.UrlEncoder(LoanJsonList, "utf-8");		
		map.put("LoanJsonList", LoanJsonList);
		map.put("PlatformMoneymoremore", PlatformMoneymoremore);
		map.put("TransferAction", TransferAction);
		map.put("Action", Action);
		map.put("TransferType", TransferType);
		map.put("NeedAudit", NeedAudit);
		map.put("Remark1", Remark1);
		map.put("Remark2", Remark2);
		map.put("Remark3", Remark3);
		map.put("NotifyURL", NotifyURL);
		map.put("SignInfo", SignInfo);

		return map;
	}
	
	
	public String lctender(Uservip tenderuser, Tender tender, Lssuing lssuing,
			Systemconf managerconf, Systemconf dealConf, Website website,
			HttpServletRequest request, boolean isauto, boolean isautoAudit) {

		List<LoanInfoSecondaryBean> listmlisb = new ArrayList<LoanInfoSecondaryBean>();
		DecimalFormat df1 = new DecimalFormat("0.00");

		String SecondaryJsonList = "";
		if (listmlisb.size() > 0) {
			SecondaryJsonList = Common.JSONEncode(listmlisb);
		}

		List<LoanInfoBean> listmlib = new ArrayList<LoanInfoBean>();

		LoanInfoBean mlib = new LoanInfoBean();
		PlatformMoneymoremore = website.getTrustAccount();
		mlib.setLoanOutMoneymoremore(tenderuser.getTrustAccount());
		mlib.setLoanInMoneymoremore(PlatformMoneymoremore);
		mlib.setOrderNo(tenderuser.getUserId().intValue() + "_"
				+ lssuing.getLssuingId().intValue() + "_"
				+ tender.getTenderTime().getTime());
		mlib.setBatchNo(lssuing.getLssuingNum());
		mlib.setExchangeBatchNo("");
		mlib.setAdvanceBatchNo("");
		mlib.setAmount(tender.getMoney().toString());
		mlib.setFullAmount(lssuing.getBorrowMoney());
		mlib.setTransferName("addlctender");
		mlib.setRemark("用户投资" + lssuing.getTitle());
		mlib.setSecondaryJsonList(SecondaryJsonList);
		listmlib.add(mlib);

		LoanJsonList = Common.JSONEncode(listmlib);

		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";

		if (istest) {
			this.SubmitURL = "http://218.4.234.150:88/main/loan/loan.action";
		} else {
			this.SubmitURL = "https://transfer.moneymoremore.com/loan/loan.action";
		}

		// this.SubmitURL = SubmitURLPrefix + "loan/loan.action"
		ReturnURL = basePath + "loan/loantenderreturn.action";
		NotifyURL = basePath + "loan/loantendernotify.action";

		String privatekey = website.getPrivateKey();// Common.privateKeyPKCS8;

		if (antistate == 1) {
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			RandomTimeStamp = Common.getRandomNum(2) + sdf.format(d);
		}
		TransferAction = "1";// 投标，2：还款，3：其他
		Action = "";

		if (isauto) {
			Action = "2";
		} else {
			Action = "1";
		}
		TransferType = "2";
		NeedAudit = "";
		if (isautoAudit) {
			NeedAudit = "1";
		}
		Remark1 = tenderuser.getUserName() + "投资标号：" + lssuing.getLssuingNum();
		Remark2= tenderuser.getUserNames();
		
		String dataStr = LoanJsonList + PlatformMoneymoremore + TransferAction
				+ Action + TransferType + NeedAudit + RandomTimeStamp + Remark1
				+ Remark2 + Remark3 + ReturnURL + NotifyURL;

		System.out.println(dataStr);
		// 签名
		MD5Trust md5 = new MD5Trust();
		RsaHelper rsa = RsaHelper.getInstance();
		if (antistate == 1) {
			dataStr = md5.getMD5Info(dataStr);
		}
		SignInfo = rsa.signData(dataStr, privatekey);

		LoanJsonList = Common.UrlEncoder(LoanJsonList, "utf-8");
		if (Action.equals("2")) {// 自动付款，http提交
			String[] resultarr = this.traansferhttp();
			if (StringUtils.isNotBlank(resultarr[1])
					&& (resultarr[1].startsWith("[") || resultarr[1]
							.startsWith("{"))) {
				return resultarr[1];
			}
		} else {// 组装form表单
			return transferForm();
		}

		return null;

	}
	/**
	 * 债权转让
	 * @param tenderuser
	 *			投标用户处理
	 * @param tender
	 * 		   	投标内容
	 * @param lssuing
	 * 			所投的标
	 * @param bugConf
	 * 			认购手续费
	 * @param website
	 * 			平台配置（托管配置信息）
	 * @param request
	 * 			请求获取返回路径等信息
	 * @param isauto
	 * 			是否自动投标
	 * @param isautoAudit
	 * 			是否自动审核
	 * @return
	 */
	public String transferBuy(Uservip tenderuser, Tender tender, Transfer transfer,Lssuing lssuing,
			 Systemconf bugConf, Website website,
			HttpServletRequest request, boolean isauto, boolean isautoAudit) {
		List<LoanInfoSecondaryBean> listmlisb = new ArrayList<LoanInfoSecondaryBean>();
		DecimalFormat df1 = new DecimalFormat("0.00");
	
		LoanInfoSecondaryBean mlisb = new LoanInfoSecondaryBean();

		// 成交费
		Double parvalue = Double.valueOf(bugConf.getParvalue());
		double fee = parvalue * tender.getMoney() / 100.0;
		if (fee > 0) {
			mlisb = new LoanInfoSecondaryBean();
			mlisb.setLoanInMoneymoremore(website.getTrustAccount());
			mlisb.setAmount(String.valueOf(df1.format(fee)));
			mlisb.setTransferName("con_bug_fee");
			mlisb.setRemark("认购手续费");
			listmlisb.add(mlisb);
		}
		String SecondaryJsonList = "";
		if (listmlisb.size() > 0) {
			SecondaryJsonList = Common.JSONEncode(listmlisb);
		}

		List<LoanInfoBean> listmlib = new ArrayList<LoanInfoBean>();

		LoanInfoBean mlib = new LoanInfoBean();
		mlib.setLoanOutMoneymoremore(tenderuser.getTrustAccount());
		mlib.setLoanInMoneymoremore(lssuing.getUservip().getTrustAccount());
		mlib.setOrderNo(tenderuser.getUserId().intValue() + "_"
				+ lssuing.getLssuingId().intValue() + "_"
				+ tender.getTenderId());
		mlib.setBatchNo(lssuing.getLssuingNum()+transfer.getTransferId());//新的标号
		mlib.setExchangeBatchNo("");
		mlib.setAdvanceBatchNo("");
//		mlib.setAmount(tender.getMoney().toString());
		mlib.setAmount(transfer.getMoney().toString());
		mlib.setFullAmount(lssuing.getBorrowMoney());
		mlib.setTransferName("transferBuy");
		mlib.setRemark("债权转让" + lssuing.getTitle());
		mlib.setSecondaryJsonList(SecondaryJsonList);
		listmlib.add(mlib);

		LoanJsonList = Common.JSONEncode(listmlib);

		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";

		if (istest) {
			this.SubmitURL = "http://218.4.234.150:88/main/loan/loan.action";
		} else {
			this.SubmitURL = "https://transfer.moneymoremore.com/loan/loan.action";
		}
	
		ReturnURL = basePath + "loan/loantenderreturn.action";
		NotifyURL = basePath + "loan/transferBuyNotify.action";
//		NotifyURL = basePath + "loan/loanauthorizenotify.action";
		PlatformMoneymoremore = website.getTrustAccount();

		String privatekey = website.getPrivateKey();// Common.privateKeyPKCS8;

		if (antistate == 1) {
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			RandomTimeStamp = Common.getRandomNum(2) + sdf.format(d);
		}
		TransferAction = "1";// 投标，2：还款，3：其他
		Action = "";

		if (isauto) {
			Action = "2";
		} else {
			Action = "1";
		}
		TransferType = "2";
		NeedAudit = "";
		if (isautoAudit) {
			NeedAudit = "1";
		}
		Remark1 = tenderuser.getUserName() + "投资标号：" + lssuing.getLssuingNum();
//		if("app".equals(tenderuser.getUserNames())){
//			this.Remark2="app";
//		}
		this.Remark2=tenderuser.getUserNames();
		String dataStr = LoanJsonList + PlatformMoneymoremore + TransferAction
				+ Action + TransferType + NeedAudit + RandomTimeStamp + Remark1
				+ Remark2 + Remark3 + ReturnURL + NotifyURL;
//		System.out.println("NotifyURL is !!"+NotifyURL);
//		System.out.println(dataStr);
		// 签名
		MD5Trust md5 = new MD5Trust();
		RsaHelper rsa = RsaHelper.getInstance();
		if (antistate == 1) {
			dataStr = md5.getMD5Info(dataStr);
		}
		SignInfo = rsa.signData(dataStr, privatekey);

		LoanJsonList = Common.UrlEncoder(LoanJsonList, "utf-8");
		if (Action.equals("2")) {// 自动付款，http提交
			String[] resultarr = this.traansferhttp();
			if (StringUtils.isNotBlank(resultarr[1])
					&& (resultarr[1].startsWith("[") || resultarr[1]
							.startsWith("{"))) {
				return resultarr[1];
			}
		} else {// 组装form表单
			String form = transferForm();
			System.out.println("form表格"+form);
			return transferForm();
		}

		return null;
	}
	/**
	 * 债权转让(手机端)
	 * @param tenderuser
	 *			投标用户处理
	 * @param tender
	 * 		   	投标内容
	 * @param lssuing
	 * 			所投的标
	 * @param bugConf
	 * 			认购手续费
	 * @param website
	 * 			平台配置（托管配置信息）
	 * @param request
	 * 			请求获取返回路径等信息
	 * @param isauto
	 * 			是否自动投标
	 * @param isautoAudit
	 * 			是否自动审核
	 * @return
	 */
	public Map transfertgBuy(Uservip tenderuser, Tender tender,Transfer transfer, Lssuing lssuing,
			 Systemconf bugConf, Website website,HttpServletRequest request, boolean isauto, boolean isautoAudit) {
		
		Map map = new HashMap<String, Object>();
		List<LoanInfoSecondaryBean> listmlisb = new ArrayList<LoanInfoSecondaryBean>();
		DecimalFormat df1 = new DecimalFormat("0.00");
	
		LoanInfoSecondaryBean mlisb = new LoanInfoSecondaryBean();

		// 成交费
		Double parvalue = Double.valueOf(bugConf.getParvalue());
		double fee = parvalue * tender.getMoney() / 100.0;
		if (fee > 0) {
			mlisb = new LoanInfoSecondaryBean();
			mlisb.setLoanInMoneymoremore(website.getTrustAccount());
			mlisb.setAmount(String.valueOf(df1.format(fee)));
			mlisb.setTransferName("con_bug_fee");
			mlisb.setRemark("认购手续费");
			listmlisb.add(mlisb);
		}
		String SecondaryJsonList = "";
		if (listmlisb.size() > 0) {
			SecondaryJsonList = Common.JSONEncode(listmlisb);
		}

		List<LoanInfoBean> listmlib = new ArrayList<LoanInfoBean>();

		LoanInfoBean mlib = new LoanInfoBean();

		mlib.setLoanOutMoneymoremore(tenderuser.getTrustAccount());
		mlib.setLoanInMoneymoremore(lssuing.getUservip().getTrustAccount());
		mlib.setOrderNo(tenderuser.getUserId().intValue() + "_"
				+ lssuing.getLssuingId().intValue() + "_"
				+ tender.getTenderId());
		mlib.setBatchNo(lssuing.getLssuingNum()+transfer.getTransferId());//新的标号
		mlib.setExchangeBatchNo("");
		mlib.setAdvanceBatchNo("");
		mlib.setAmount(transfer.getMoney().toString());
		mlib.setFullAmount(lssuing.getBorrowMoney());
		System.out.println("认购价格"+transfer.getMoney().toString()+"满标价格"+lssuing.getBorrowMoney());
		mlib.setTransferName("transferBuy");
		mlib.setRemark("债权转让" + lssuing.getTitle());
		mlib.setSecondaryJsonList(SecondaryJsonList);
		listmlib.add(mlib);
		
		
		LoanJsonList = Common.JSONEncode(listmlib);

		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";

		if (istest) {
			this.SubmitURL = "http://218.4.234.150:88/main/loan/loan.action";
		} else {
			this.SubmitURL = "https://transfer.moneymoremore.com/loan/loan.action";
		}
	
		ReturnURL = basePath + "loan/loantenderreturn.action";
		NotifyURL = basePath + "loan/transferBuyNotify.action";

		PlatformMoneymoremore = website.getTrustAccount();

		String privatekey = website.getPrivateKey();// Common.privateKeyPKCS8;

		if (antistate == 1) {
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			RandomTimeStamp = Common.getRandomNum(2) + sdf.format(d);
		}
		TransferAction = "1";// 投标，2：还款，3：其他
		Action = "";

		if (isauto) {
			Action = "2";
		} else {
			Action = "1";
		}
		TransferType = "2";
		NeedAudit = "";
		if (isautoAudit) {
			NeedAudit = "1";
		}
		Remark1 = tenderuser.getUserName() + "投资标号：" + lssuing.getLssuingNum();
		if("app".equals(tenderuser.getUserNames())){
			this.Remark2="app";
		}
		String dataStr = LoanJsonList + PlatformMoneymoremore + TransferAction
				+ Action + TransferType + NeedAudit + RandomTimeStamp + Remark1
				+ Remark2 + Remark3 + ReturnURL + NotifyURL;

		System.out.println(dataStr);
		// 签名
		MD5Trust md5 = new MD5Trust();
		RsaHelper rsa = RsaHelper.getInstance();
		if (antistate == 1) {
			dataStr = md5.getMD5Info(dataStr);
		}
		SignInfo = rsa.signData(dataStr, privatekey);

		LoanJsonList = Common.UrlEncoder(LoanJsonList, "utf-8");

		map.put("LoanJsonList", LoanJsonList);
		map.put("PlatformMoneymoremore", PlatformMoneymoremore);
		map.put("TransferAction", TransferAction);
		map.put("Action", Action);
		map.put("TransferType", TransferType);
		map.put("NeedAudit", NeedAudit);
		map.put("Remark1", Remark1);
		map.put("Remark2", Remark2);
		map.put("Remark3", Remark3);
		map.put("NotifyURL", NotifyURL);
		map.put("SignInfo", SignInfo);

		return map;
	}
	/**
	 * 投标处理
	 * 
	 * @param tenderuser
	 *            投标用户处理
	 * @param tender
	 *            投标内容
	 * @param lssuing
	 *            所投的标
	 * @param managerconf
	 *            管理费
	 * @param dealConf
	 *            成交费
	 * @param website
	 *            平台配置（托管配置信息）
	 * @param request
	 *            请求获取返回路径等信息
	 * @param isauto
	 *            是否自动投标
	 * @param isautoAudit
	 *            是否自动审核
	 * @return
	 */
	public String tender(Uservip tenderuser, Tender tender, Lssuing lssuing,
			Systemconf managerconf, Systemconf dealConf, Website website,
			HttpServletRequest request, boolean isauto, boolean isautoAudit) {
		List<LoanInfoSecondaryBean> listmlisb = new ArrayList<LoanInfoSecondaryBean>();
		DecimalFormat df1 = new DecimalFormat("0.00");
		// 投标奖励
		Double lsawardmoney = 0.0;
		if ((lssuing.getAwardRate() != null)
				&& (!"".equals(lssuing.getAwardRate()))) {
			lsawardmoney = Double.valueOf(Double.valueOf(tender.getMoney()
					* Double.valueOf(lssuing.getAwardRate()) / 100.0D));
		} else if ((lssuing.getAwardMoney() != null)
				&& (!"".equals(lssuing.getAwardMoney()))) {
			lsawardmoney = Double.valueOf(Double.valueOf(
					tender.getMoney().intValue()).doubleValue()
					/ Double.valueOf(lssuing.getBorrowMoney()).doubleValue()
					* Double.valueOf(lssuing.getAwardMoney()).doubleValue());
		}
		
		LoanInfoSecondaryBean mlisb = new LoanInfoSecondaryBean();
		if (lsawardmoney > 0) {
			
			if (lsawardmoney < 0.01) {
				lsawardmoney = 0.01;
			}
			mlisb = new LoanInfoSecondaryBean();
			mlisb.setLoanInMoneymoremore(tenderuser.getTrustAccount());
			mlisb.setAmount(String.valueOf(df1.format(lsawardmoney)));
			mlisb.setTransferName("tender_award");
			mlisb.setRemark("投标奖励");
			listmlisb.add(mlisb);
		}
		// 管理费
		Double parvalue = Double.valueOf(managerconf.getParvalue());
		double fee = parvalue * tender.getMoney() / 100.0;
		if (fee > 0) {
			mlisb = new LoanInfoSecondaryBean();
			mlisb.setLoanInMoneymoremore(website.getTrustAccount());
			mlisb.setAmount(String.valueOf(df1.format(fee)));
			mlisb.setTransferName("borrow_manager_fee");
			mlisb.setRemark("借款管理费");
			listmlisb.add(mlisb);
		}
		// 成交费
		parvalue = Double.valueOf(dealConf.getParvalue());
		fee = parvalue * tender.getMoney() / 100.0;
		if (fee > 0) {
			mlisb = new LoanInfoSecondaryBean();
			mlisb.setLoanInMoneymoremore(website.getTrustAccount());
			mlisb.setAmount(String.valueOf(df1.format(fee)));
			mlisb.setTransferName("borrow_deal_fee");
			mlisb.setRemark("借款成交费");
			listmlisb.add(mlisb);
		}
		String SecondaryJsonList = "";
		if (listmlisb.size() > 0) {
			SecondaryJsonList = Common.JSONEncode(listmlisb);
		}

		List<LoanInfoBean> listmlib = new ArrayList<LoanInfoBean>();

		LoanInfoBean mlib = new LoanInfoBean();

		mlib.setLoanOutMoneymoremore(tenderuser.getTrustAccount());
		mlib.setLoanInMoneymoremore(lssuing.getUservip().getTrustAccount());
		mlib.setOrderNo(tenderuser.getUserId().intValue() + "_"
				+ lssuing.getLssuingId().intValue() + "_"
				+ tender.getTenderTime().getTime());
		mlib.setBatchNo(lssuing.getLssuingNum());
		mlib.setExchangeBatchNo("");
		mlib.setAdvanceBatchNo("");
		mlib.setAmount(tender.getMoney().toString());
		mlib.setFullAmount(lssuing.getBorrowMoney());
		mlib.setTransferName("addtender");
		mlib.setRemark("用户投标" + lssuing.getTitle());
		mlib.setSecondaryJsonList(SecondaryJsonList);
		listmlib.add(mlib);

		LoanJsonList = Common.JSONEncode(listmlib);

		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";

		if (istest) {
			this.SubmitURL = "http://218.4.234.150:88/main/loan/loan.action";
		} else {
			this.SubmitURL = "https://transfer.moneymoremore.com/loan/loan.action";
		}

		// this.SubmitURL = SubmitURLPrefix + "loan/loan.action"
		if (lssuing.getLssuingType() == 9) {
			ReturnURL = basePath + "loan/loantenderlcreturn.action";
			NotifyURL = basePath + "loan/loantenderlcnotify.action";
		} else {
			ReturnURL = basePath + "loan/loantenderreturn.action";
			NotifyURL = basePath + "loan/loantendernotify.action";
		}
		PlatformMoneymoremore = website.getTrustAccount();

		String privatekey = website.getPrivateKey();// Common.privateKeyPKCS8;

		if (antistate == 1) {
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			RandomTimeStamp = Common.getRandomNum(2) + sdf.format(d);
		}
		TransferAction = "1";// 投标，2：还款，3：其他
		Action = "";

		if (isauto) {
			Action = "2";
		} else {
			Action = "1";
		}
		TransferType = "2";
		NeedAudit = "";
		if (isautoAudit) {
			NeedAudit = "1";
		}
		if(Remark3==null){
			Remark3="";
		}
		Remark1 = tenderuser.getUserName() + "投资标号：" + lssuing.getLssuingNum();
	
			this.Remark2=tenderuser.getUserNames();
			if(Remark2==null){
				Remark2="";
			}
			System.err.println("=======================Remark2:"+Remark2);
			System.err.println("=======================Remark3:"+Remark3);
		String dataStr = LoanJsonList + PlatformMoneymoremore + TransferAction
				+ Action + TransferType + NeedAudit + RandomTimeStamp + Remark1
				+ Remark2 + Remark3 + ReturnURL + NotifyURL;

		System.out.println(dataStr);
		// 签名
		MD5Trust md5 = new MD5Trust();
		RsaHelper rsa = RsaHelper.getInstance();
		if (antistate == 1) {
			dataStr = md5.getMD5Info(dataStr);
		}
		SignInfo = rsa.signData(dataStr, privatekey);

		LoanJsonList = Common.UrlEncoder(LoanJsonList, "utf-8");
		if (Action.equals("2")) {// 自动付款，http提交
			String[] resultarr = this.traansferhttp();
			if (StringUtils.isNotBlank(resultarr[1])
					&& (resultarr[1].startsWith("[") || resultarr[1]
							.startsWith("{"))) {
				return resultarr[1];
			}
		} else {// 组装form表单
			return transferForm();
		}

		return null;
	}

	/**
	 * 转账信息组装
	 * 
	 * @param request
	 * @param website
	 * @param tender
	 * @param traction
	 * @param action
	 * @param needaudit
	 * @param amount
	 * @param lssuing
	 * @param uservip
	 * @param transfername
	 * @param remark
	 * @param isauto
	 * @param isautoAudit
	 * @return
	 */
	public String loantraansfer(HttpServletRequest request, Website website,
			Tender tender, int traction, int action, String needaudit,
			double amount, Lssuing lssuing, Uservip uservip,
			String transfername, String remark) {

		String SecondaryJsonList = "";

		List<LoanInfoBean> listmlib = new ArrayList<LoanInfoBean>();

		LoanInfoBean mlib = new LoanInfoBean();

		mlib.setLoanOutMoneymoremore(website.getTrustAccount());
		mlib.setLoanInMoneymoremore(uservip.getTrustAccount());
		mlib.setOrderNo(uservip.getUserId() + "_"
				+ lssuing.getLssuingId().intValue() + "_"
				+ tender.getTenderId());
		mlib.setBatchNo(lssuing.getLssuingNum());
		mlib.setExchangeBatchNo("");
		mlib.setAdvanceBatchNo("");
		mlib.setAmount(String.valueOf(amount));
		mlib.setFullAmount(lssuing.getBorrowMoney());
		mlib.setTransferName(transfername);
		mlib.setRemark(remark);
		mlib.setSecondaryJsonList(SecondaryJsonList);
		listmlib.add(mlib);

		LoanJsonList = Common.JSONEncode(listmlib);

		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";

		if (istest) {
			this.SubmitURL = "http://218.4.234.150:88/main/loan/loan.action";
		} else {
			this.SubmitURL = "https://transfer.moneymoremore.com/loan/loan.action";
		}
		ReturnURL = basePath + "loan/loantraansferreturn.action";
		NotifyURL = basePath + "loan/loantraansfernotify.action";

		PlatformMoneymoremore = website.getTrustAccount();

		String privatekey = website.getPrivateKey();// Common.privateKeyPKCS8;

		if (antistate == 1) {
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			RandomTimeStamp = Common.getRandomNum(2) + sdf.format(d);
		}
		TransferAction = String.valueOf(traction);// 投标，2：还款，3：其他
		Action = String.valueOf(action);

		TransferType = "2";
		NeedAudit = needaudit;

		String dataStr = LoanJsonList + PlatformMoneymoremore + TransferAction
				+ Action + TransferType + NeedAudit + RandomTimeStamp + Remark1
				+ Remark2 + Remark3 + ReturnURL + NotifyURL;
		// 签名
		MD5Trust md5 = new MD5Trust();
		RsaHelper rsa = RsaHelper.getInstance();
		if (antistate == 1) {
			dataStr = md5.getMD5Info(dataStr);
		}
		SignInfo = rsa.signData(dataStr, privatekey);

		LoanJsonList = Common.UrlEncoder(LoanJsonList, "utf-8");
		if (Action.equals("2")) {// 自动付款，http提交
			String[] resultarr = this.traansferhttp();
			if (StringUtils.isNotBlank(resultarr[1])
					&& (resultarr[1].startsWith("[") || resultarr[1]
							.startsWith("{"))) {
				return resultarr[1];
			}
		} else {// 组装form表单
			return transferForm();
		}

		return null;

	}

	public String lcRepay(boolean isauto, Website website,
			List<Record> recordlist, HttpServletRequest request,
			Lssuing lssuing, Systemconf ratevipfeeconf, Systemconf ratefeeconf,List<Repaynote> repaylist) {

		List<LoanInfoBean> listmlib = new ArrayList<LoanInfoBean>();
		
		DecimalFormat df1 = new DecimalFormat("0.00");
		// 判断是否已经垫付
		for (int i = 0; i < recordlist.size(); i++) {
			Record record = recordlist.get(i);
			Uservip recordUser = record.getUservip();
			List<LoanInfoSecondaryBean> listmlisb = new ArrayList<LoanInfoSecondaryBean>();
			double recordmony = 0;
			if (record.getRecordInterest() != null
					&& record.getRecordInterest() > 0) {
				recordmony = record.getRecordInterest();
			} else {
				recordmony = record.getRecordMoney() + record.getRecordRate()
						+ record.getOverInterest();
			}

			if (record.getOverInterest() > 0) {
				recordmony += record.getOverInterest();
			}
			if (ratevipfeeconf != null && ratefeeconf != null) {
				if (recordUser.getIsVIP() != null
						&& recordUser.getIsVIP().equals("1")) {
					LoanInfoSecondaryBean mlisb = new LoanInfoSecondaryBean();
					double ratefee = record.getRecordRate().doubleValue()
							* Double.valueOf(ratevipfeeconf.getParvalue());
					ratefee = Double.valueOf(df1.format(ratefee));
					if (ratefee > 0) {
						mlisb.setLoanInMoneymoremore(website.getTrustAccount());
						mlisb.setAmount(df1.format(ratefee));
						mlisb.setTransferName("rate_manager_fee");
						mlisb.setRemark("利息管理费");
						listmlisb.add(mlisb);
					}
				} else {
					LoanInfoSecondaryBean mlisb = new LoanInfoSecondaryBean();
					double ratefee = record.getRecordRate().doubleValue()
							* Double.valueOf(ratefeeconf.getParvalue());
					ratefee = Double.valueOf(df1.format(ratefee));
					if (ratefee > 0) {
						mlisb.setLoanInMoneymoremore(website.getTrustAccount());
						mlisb.setAmount(String.valueOf(ratefee));
						mlisb.setTransferName("rate_manager_fee");
						mlisb.setRemark("利息管理费");
						listmlisb.add(mlisb);
					}
				}
			}
			String SecondaryJsonList = "";
			if (listmlisb.size() > 0) {
				SecondaryJsonList = Common.JSONEncode(listmlisb);
			}
			LoanInfoBean mlib = new LoanInfoBean();

			mlib.setLoanOutMoneymoremore(website.getTrustAccount());
			mlib.setLoanInMoneymoremore(recordUser.getTrustAccount());
			mlib.setOrderNo(lssuing.getLssuingId() +"_" + record.getRecordId());
			mlib.setBatchNo(lssuing.getLssuingNum());
			mlib.setExchangeBatchNo("");
			mlib.setAdvanceBatchNo("");
			mlib.setAmount(String.valueOf(recordmony));
			mlib.setFullAmount(lssuing.getBorrowMoney());
			mlib.setTransferName("lcrepay");
			mlib.setRemark("");
			mlib.setSecondaryJsonList(SecondaryJsonList);
			listmlib.add(mlib);
		}
		LoanJsonList = Common.JSONEncode(listmlib);
		
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";

		if (istest) {
			this.SubmitURL = "http://218.4.234.150:88/main/loan/loan.action";
		} else {
			this.SubmitURL = "https://transfer.moneymoremore.com/loan/loan.action";
		}
		ReturnURL = basePath + "loan/lcrepayreturn.action";
		NotifyURL = basePath + "loan/lcrepaynotify.action";

		PlatformMoneymoremore = website.getTrustAccount();

		String privatekey = website.getPrivateKey();// Common.privateKeyPKCS8;

		if (antistate == 1) {
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			RandomTimeStamp = Common.getRandomNum(2) + sdf.format(d);
		}
		TransferAction = "2";// 投标，2：还款，3：其他
		Action = "";

		if (isauto) {
			Action = "2";
		} else {
			Action = "1";
		}
		TransferType = "2";
		NeedAudit = "1";
		Remark1 = "理财产品还款标号：" + lssuing.getLssuingNum()+"标题："+lssuing.getTitle();
		
		String repaystrs = "";
		if(repaylist!=null && repaylist.size()>0){
			for(int i=0;i<repaylist.size();i++){
				Repaynote repay = repaylist.get(i);
				if(i==0){
					repaystrs += repay.getRepayNoteId().intValue();
				}else{
					repaystrs += "_"+repay.getRepayNoteId().intValue();
				}
			}
		}
		this.Remark2 = repaystrs;
		
		this.Remark3 = lssuing.getLssuingId().intValue() + "";
		String dataStr = LoanJsonList + PlatformMoneymoremore + TransferAction
				+ Action + TransferType + NeedAudit + RandomTimeStamp + Remark1
				+ Remark2 + Remark3 + ReturnURL + NotifyURL;
		
		System.out.println("加密前的理财还款"+dataStr);
		// 签名
		MD5Trust md5 = new MD5Trust();
		RsaHelper rsa = RsaHelper.getInstance();
		if (antistate == 1) {
			dataStr = md5.getMD5Info(dataStr);
		}
		SignInfo = rsa.signData(dataStr, privatekey);

		LoanJsonList = Common.UrlEncoder(LoanJsonList, "utf-8");
		if (Action.equals("2")) {// 自动付款，http提交
			String[] resultarr = this.traansferhttp();
			if (StringUtils.isNotBlank(resultarr[1])
					&& (resultarr[1].startsWith("[") || resultarr[1]
							.startsWith("{"))) {
				return resultarr[1];
			}
		} else {// 组装form表单
			return transferForm();
		}
		return null;

	}

	/**
	 * 借款标还款
	 * 
	 * @param repaynote
	 *            还款信息
	 * @param isauto
	 *            是否自动还款
	 * @return
	 */
	public String borrowRepay(Repaynote repaynote, boolean isauto,
			Website website, List<Record> recordlist,
			HttpServletRequest request, Lssuing lssuing,
			Systemconf ratevipfeeconf, Systemconf ratefeeconf) {
		List<LoanInfoBean> listmlib = new ArrayList<LoanInfoBean>();
		Uservip repayUser = repaynote.getUservip();

		// 判断是否已经垫付
		if (repaynote.getRepayState() == 2) {
			double repaymoney = repaynote.getMoneyFour();
			if (repaynote.getOverInterest() > 0) {
				repaymoney += repaynote.getOverInterest();
			}
			LoanInfoBean mlib = new LoanInfoBean();
			String SecondaryJsonList = "";
			mlib.setLoanOutMoneymoremore(repayUser.getTrustAccount());
			mlib.setLoanInMoneymoremore(website.getTrustAccount());
			mlib.setOrderNo(repayUser.getUserId() + "_"
					+ repaynote.getRepayNoteId());
			mlib.setBatchNo(lssuing.getLssuingNum());
			mlib.setExchangeBatchNo("");
			mlib.setAdvanceBatchNo("");
			mlib.setAmount(String.valueOf(repaymoney));
			mlib.setFullAmount(lssuing.getBorrowMoney());
			mlib.setTransferName("web_repay");
			mlib.setRemark("");
			mlib.setSecondaryJsonList(SecondaryJsonList);
			listmlib.add(mlib);
		} else {
			DecimalFormat df1 = new DecimalFormat("0.00");
			for (int i = 0; i < recordlist.size(); i++) {
				Record record = recordlist.get(i);
				Uservip recordUser = record.getUservip();
				List<LoanInfoSecondaryBean> listmlisb = new ArrayList<LoanInfoSecondaryBean>();
				double recordmony = 0;
				if (record.getRecordInterest() != null
						&& record.getRecordInterest() > 0) {
					recordmony = record.getRecordInterest();
				} else {
					recordmony = record.getRecordMoney()
							+ record.getRecordRate() + record.getOverInterest();
				}

				if (record.getOverInterest() > 0) {
					recordmony += record.getOverInterest();
				}
				if (ratevipfeeconf != null && ratefeeconf != null) {
					if (recordUser.getIsVIP() != null
							&& recordUser.getIsVIP().equals("1")) {
						LoanInfoSecondaryBean mlisb = new LoanInfoSecondaryBean();
						double ratefee = record.getRecordRate().doubleValue()
								* Double.valueOf(ratevipfeeconf.getParvalue());
						ratefee = Double.valueOf(df1.format(ratefee));
						if (ratefee > 0) {
							mlisb.setLoanInMoneymoremore(website
									.getTrustAccount());
							mlisb.setAmount(String.valueOf(ratefee));
							mlisb.setTransferName("rate_manager_fee");
							mlisb.setRemark("利息管理费");
							listmlisb.add(mlisb);
						}
					} else {
						LoanInfoSecondaryBean mlisb = new LoanInfoSecondaryBean();
						double ratefee = record.getRecordRate().doubleValue()
								* Double.valueOf(ratefeeconf.getParvalue());
						ratefee = Double.valueOf(df1.format(ratefee));
						if (ratefee > 0) {
							mlisb.setLoanInMoneymoremore(website
									.getTrustAccount());
							mlisb.setAmount(String.valueOf(ratefee));
							mlisb.setTransferName("rate_manager_fee");
							mlisb.setRemark("利息管理费");
							listmlisb.add(mlisb);
						}
					}
				}
				String SecondaryJsonList = "";
				if (listmlisb.size() > 0) {
					SecondaryJsonList = Common.JSONEncode(listmlisb);
				}
				LoanInfoBean mlib = new LoanInfoBean();

				mlib.setLoanOutMoneymoremore(repayUser.getTrustAccount());
				mlib.setLoanInMoneymoremore(recordUser.getTrustAccount());
				mlib.setOrderNo(repayUser.getUserId() + "_"
						+ repaynote.getRepayNoteId() + "_"
						+ record.getRecordId());
				mlib.setBatchNo(lssuing.getLssuingNum());
				mlib.setExchangeBatchNo("");
				mlib.setAdvanceBatchNo("");
				mlib.setAmount(String.valueOf(recordmony));
				mlib.setFullAmount(lssuing.getBorrowMoney());
				mlib.setTransferName("repay");
				mlib.setRemark("");
				mlib.setSecondaryJsonList(SecondaryJsonList);
				listmlib.add(mlib);
			}
		}

		LoanJsonList = Common.JSONEncode(listmlib);

		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";

		if (istest) {
			this.SubmitURL = "http://218.4.234.150:88/main/loan/loan.action";
		} else {
			this.SubmitURL = "https://transfer.moneymoremore.com/loan/loan.action";
		}
		ReturnURL = basePath + "loan/borrowrepayreturn.action";
		NotifyURL = basePath + "loan/borrowrepaynotify.action";

		PlatformMoneymoremore = website.getTrustAccount();

		String privatekey = website.getPrivateKey();// Common.privateKeyPKCS8;

		if (antistate == 1) {
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			RandomTimeStamp = Common.getRandomNum(2) + sdf.format(d);
		}
		TransferAction = "2";// 投标，2：还款，3：其他
		Action = "";

		if (isauto) {
			Action = "2";
		} else {
			Action = "1";
		}
		TransferType = "2";
		NeedAudit = "1";
		Remark1 = repayUser.getUserName() + "还款标号：" + lssuing.getLssuingNum();
		this.Remark2 = repaynote.getRepayNoteId().intValue() + "";
		this.Remark3 = lssuing.getLssuingId().intValue() + "";
		String dataStr = LoanJsonList + PlatformMoneymoremore + TransferAction
				+ Action + TransferType + NeedAudit + RandomTimeStamp + Remark1
				+ Remark2 + Remark3 + ReturnURL + NotifyURL;
		
		System.out.println("加密前的字符创"+dataStr);
		// 签名
		MD5Trust md5 = new MD5Trust();
		RsaHelper rsa = RsaHelper.getInstance();
		if (antistate == 1) {
			dataStr = md5.getMD5Info(dataStr);
		}
		SignInfo = rsa.signData(dataStr, privatekey);

		LoanJsonList = Common.UrlEncoder(LoanJsonList, "utf-8");
		if (Action.equals("2")) {// 自动付款，http提交
			String[] resultarr = this.traansferhttp();
			if (StringUtils.isNotBlank(resultarr[1])
					&& (resultarr[1].startsWith("[") || resultarr[1]
							.startsWith("{"))) {
				return resultarr[1];
			}
		} else {// 组装form表单
			return transferForm();
		}
		return null;
	}

	/**
	 * 借款标平台垫付处理
	 * 
	 * @param repaynote
	 *            还款信息
	 * @param isauto
	 *            是否自动还款
	 * @return
	 */
	public String borrowWebRepay(Repaynote repaynote, boolean isauto,
			Website website, List<Record> recordlist,
			HttpServletRequest request, Lssuing lssuing,
			Systemconf ratevipfeeconf, Systemconf ratefeeconf) {
		List<LoanInfoBean> listmlib = new ArrayList<LoanInfoBean>();
		Uservip repayUser = repaynote.getUservip();
		DecimalFormat df1 = new DecimalFormat("0.00");
		for (int i = 0; i < recordlist.size(); i++) {
			Record record = recordlist.get(i);
			Uservip recordUser = record.getUservip();
			List<LoanInfoSecondaryBean> listmlisb = new ArrayList<LoanInfoSecondaryBean>();
			double recordmony = 0;
			if (record.getRecordInterest() != null
					&& record.getRecordInterest() > 0) {
				recordmony = record.getRecordInterest();
			} else {
				recordmony = record.getRecordMoney() + record.getRecordRate()
						+ record.getOverInterest();
			}
			if (record.getOverInterest() > 0) {
				recordmony += record.getOverInterest();
			}
			String SecondaryJsonList = "";
			if (listmlisb.size() > 0) {
				SecondaryJsonList = Common.JSONEncode(listmlisb);
			}
			LoanInfoBean mlib = new LoanInfoBean();

			mlib.setLoanOutMoneymoremore(website.getTrustAccount());
			mlib.setLoanInMoneymoremore(recordUser.getTrustAccount());
			mlib.setOrderNo(repayUser.getUserId() + "_"
					+ repaynote.getRepayNoteId() + "_" + record.getRecordId());
			mlib.setBatchNo(lssuing.getLssuingNum());
			mlib.setExchangeBatchNo("");
			mlib.setAdvanceBatchNo("");
			
			mlib.setAmount(String.valueOf(df1.format(recordmony)));
			mlib.setFullAmount(lssuing.getBorrowMoney());
			mlib.setTransferName("web_advance_repay");
			mlib.setRemark("");
			mlib.setSecondaryJsonList(SecondaryJsonList);
			listmlib.add(mlib);
		}
		this.LoanJsonList = Common.JSONEncode(listmlib);

		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";

		if (istest) {
			this.SubmitURL = "http://218.4.234.150:88/main/loan/loan.action";
		} else {
			this.SubmitURL = "https://transfer.moneymoremore.com/loan/loan.action";
		}
		ReturnURL = basePath + "loan/borrowwebrepayreturn.action";
		NotifyURL = basePath + "loan/borrowwebrepaynotify.action";

		PlatformMoneymoremore = website.getTrustAccount();

		String privatekey = website.getPrivateKey();// Common.privateKeyPKCS8;

		if (antistate == 1) {
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			RandomTimeStamp = Common.getRandomNum(2) + sdf.format(d);
		}
		TransferAction = "2";// 投标，2：还款，3：其他
		Action = "";

		if (isauto) {
			Action = "2";
		} else {
			Action = "1";
		}
		TransferType = "2";
		NeedAudit = "1";
		Remark1 = "平台垫付还款标号：" + lssuing.getLssuingNum();
		this.Remark2 = repaynote.getRepayNoteId().intValue() + "";
		this.Remark3 = lssuing.getLssuingId().intValue() + "";
		String dataStr = LoanJsonList + PlatformMoneymoremore + TransferAction
				+ Action + TransferType + NeedAudit + RandomTimeStamp + Remark1
				+ Remark2 + Remark3 + ReturnURL + NotifyURL;
		// 签名
		MD5Trust md5 = new MD5Trust();
		RsaHelper rsa = RsaHelper.getInstance();
		if (antistate == 1) {
			dataStr = md5.getMD5Info(dataStr);
		}
		System.out.println(dataStr);
		
		SignInfo = rsa.signData(dataStr, privatekey);

		LoanJsonList = Common.UrlEncoder(LoanJsonList, "utf-8");
		if (Action.equals("2")) {// 自动付款，http提交
			String[] resultarr = this.traansferhttp();
			if (StringUtils.isNotBlank(resultarr[1])
					&& (resultarr[1].startsWith("[") || resultarr[1]
							.startsWith("{"))) {
				return resultarr[1];
			}
		} else {// 组装form表单
			return transferForm();
		}
		return null;
	}

	/**
	 * 自动转账接口HTTP方式提交，只有异步通知接口会处理
	 * 
	 * @return
	 */
	public String[] traansferhttp() {
		Map<String, String> req = new TreeMap<String, String>();
		req.put("LoanJsonList", LoanJsonList);
		req.put("PlatformMoneymoremore", PlatformMoneymoremore);
		req.put("TransferAction", TransferAction);
		req.put("Action", Action);
		req.put("TransferType", TransferType);
		req.put("NeedAudit", NeedAudit);
		req.put("RandomTimeStamp", RandomTimeStamp);
		req.put("Remark1", Remark1);
		req.put("Remark2", Remark2);
		req.put("Remark3", Remark3);
		req.put("ReturnURL", ReturnURL);
		req.put("NotifyURL", NotifyURL);
		req.put("SignInfo", SignInfo);

		String[] resultarr = HttpClientUtil.doPostQueryCmd(SubmitURL, req);
		return resultarr;
	}
	
	
	/**
	 * 手动转账接口通过form表单形式提交，returnurl和异步通知接口都会处理
	 * 
	 * @return
	 */
	public String transferForm1() {
		String result = "";
		result = "<form id='form1' name='form1' action='"
				+ SubmitURL
				+ "' method='post' target='_blank'>"
				+ "<input id='LoanJsonList' name='LoanJsonList' value='"
				+ LoanJsonList
				+ "' type='hidden' />"
				+ "<input id='PlatformMoneymoremore' name='PlatformMoneymoremore' value='"
				+ PlatformMoneymoremore + "' type='hidden' />"
				+ "<input id='TransferAction' name='TransferAction' value='"
				+ TransferAction + "' type='hidden' />"
				+ "<input id='Action' name='Action' value='" + Action
				+ "' type='hidden' />"
				+ "<input id='TransferType' name='TransferType' value='"
				+ TransferType + "' type='hidden' />"
				+ "<input id='NeedAudit' name='NeedAudit' value='" + NeedAudit
				+ "' type='hidden' />"
				+ "<input id='RandomTimeStamp' name='RandomTimeStamp' value='"
				+ RandomTimeStamp + "' type='hidden' />"
				+ "<input id='Remark1' name='Remark1' value='" + Remark1
				+ "' type='hidden' />"
				+ "<input id='Remark2' name='Remark2' value='" + Remark2
				+ "' type='hidden' />"
				+ "<input id='Remark3' name='Remark3' value='" + Remark3
				+ "' type='hidden' />"
				+ "<input id='ReturnURL' name='ReturnURL' value='" + ReturnURL
				+ "' type='hidden' />"
				+ "<input id='NotifyURL' name='NotifyURL' value='" + NotifyURL
				+ "' type='hidden' />"
				+ "<input id='SignInfo' name='SignInfo' value='" + SignInfo
				+ "' type='hidden' />"
//				+ "<input id='submit' name='submit' value='提交' type='submit' />"
				+ "</form>"
				+ "<script type='text/javascript'>"
				+ "window.onload=function(){document.form1.submit();}"
				+ "</script>";
		System.out.println(result);
		return result;
	}

	/**
	 * 手动转账接口通过form表单形式提交，returnurl和异步通知接口都会处理
	 * 
	 * @return
	 */
	public String transferForm() {
		String result = "";
		result = "<form id='form1' name='form1' action='"
				+ SubmitURL
				+ "' method='post'>"
				+ "<input id='LoanJsonList' name='LoanJsonList' value='"
				+ LoanJsonList
				+ "' type='hidden' />"
				+ "<input id='PlatformMoneymoremore' name='PlatformMoneymoremore' value='"
				+ PlatformMoneymoremore + "' type='hidden' />"
				+ "<input id='TransferAction' name='TransferAction' value='"
				+ TransferAction + "' type='hidden' />"
				+ "<input id='Action' name='Action' value='" + Action
				+ "' type='hidden' />"
				+ "<input id='TransferType' name='TransferType' value='"
				+ TransferType + "' type='hidden' />"
				+ "<input id='NeedAudit' name='NeedAudit' value='" + NeedAudit
				+ "' type='hidden' />"
				+ "<input id='RandomTimeStamp' name='RandomTimeStamp' value='"
				+ RandomTimeStamp + "' type='hidden' />"
				+ "<input id='Remark1' name='Remark1' value='" + Remark1
				+ "' type='hidden' />"
				+ "<input id='Remark2' name='Remark2' value='" + Remark2
				+ "' type='hidden' />"
				+ "<input id='Remark3' name='Remark3' value='" + Remark3
				+ "' type='hidden' />"
				+ "<input id='ReturnURL' name='ReturnURL' value='" + ReturnURL
				+ "' type='hidden' />"
				+ "<input id='NotifyURL' name='NotifyURL' value='" + NotifyURL
				+ "' type='hidden' />"
				+ "<input id='SignInfo' name='SignInfo' value='" + SignInfo
				+ "' type='hidden' />"
//				+ "<input id='submit' name='submit' value='提交' type='submit' />"
				+ "</form>"
				+ "<script type='text/javascript'>"
				+ "window.onload=function(){document.form1.submit();}"
				+ "</script>";
		System.out.println(result);
		return result;
	}

	/**
	 * 投标信息审核处理
	 * 
	 * @return
	 */
	public String tenderaudit(String orderlist, Website website,
			String auditType, HttpServletRequest request, Lssuing lssuing) {

		try {
			String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";

			if (istest) {
				this.SubmitURL = "http://218.4.234.150:88/main/loan/toloantransferaudit.action";
			} else {
				this.SubmitURL = "https://audit.moneymoremore.com/loan/toloantransferaudit.action";
			}

			// SubmitURL = SubmitURLPrefix + "loan/toloantransferaudit.action";
			ReturnURL = basePath + "loan/loantenderauditreturn.action";
			NotifyURL = basePath + "loan/loantenderauditnotify.action";

			String privatekey = website.getPrivateKey();
			if (antistate == 1) {
				Date d = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
				RandomTimeStamp = Common.getRandomNum(2) + sdf.format(d);
			}

			this.LoanNoList = orderlist;
			this.AuditType = auditType;
			this.PlatformMoneymoremore = website.getTrustAccount();

			this.Remark1 = "" + lssuing.getLssuingId().intValue();
			String dataStr = LoanNoList + PlatformMoneymoremore
					+ this.AuditType + RandomTimeStamp + Remark1 + Remark2
					+ Remark3 + ReturnURL + NotifyURL;
			// 签名
			MD5Trust md5 = new MD5Trust();
			RsaHelper rsa = RsaHelper.getInstance();
			if (antistate == 1) {
				dataStr = md5.getMD5Info(dataStr);
			}
			SignInfo = rsa.signData(dataStr, privatekey);

			return this.auditform();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 流标信息信息审核处理
	 * 
	 * @return
	 */
	public String tenderfailaudit(String orderlist, Website website,
			String auditType, HttpServletRequest request, Lssuing lssuing) {

		try {
			String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";

			if (istest) {
				this.SubmitURL = "http://218.4.234.150:88/main/loan/toloantransferaudit.action";
			} else {
				this.SubmitURL = "https://audit.moneymoremore.com/loan/toloantransferaudit.action";
			}

			// SubmitURL = SubmitURLPrefix + "loan/toloantransferaudit.action";
			ReturnURL = basePath + "loan/loanFailedreturn.action";
			NotifyURL = basePath + "loan/loanFailednotify.action";

			String privatekey = website.getPrivateKey();
			if (antistate == 1) {
				Date d = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
				RandomTimeStamp = Common.getRandomNum(2) + sdf.format(d);
			}

			this.LoanNoList = orderlist;
			this.AuditType = auditType;
			this.PlatformMoneymoremore = website.getTrustAccount();

			this.Remark1 = "" + lssuing.getLssuingId().intValue();
			String dataStr = LoanNoList + PlatformMoneymoremore
					+ this.AuditType + RandomTimeStamp + Remark1 + Remark2
					+ Remark3 + ReturnURL + NotifyURL;
			// 签名
			MD5Trust md5 = new MD5Trust();
			RsaHelper rsa = RsaHelper.getInstance();
			if (antistate == 1) {
				dataStr = md5.getMD5Info(dataStr);
			}
			SignInfo = rsa.signData(dataStr, privatekey);

			return this.auditform();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 组装审核表单信息
	 * 
	 * @return
	 */
	public String auditform() {
		String result = "";
		result = "<form id='form1' name='form1' action='"
				+ SubmitURL
				+ "' method='post' target='_blank'>"
				+ "<input id='LoanNoList' name='LoanNoList' value='"
				+ LoanNoList
				+ "' type='hidden' />"
				+ "<input id='PlatformMoneymoremore' name='PlatformMoneymoremore' value='"
				+ PlatformMoneymoremore + "' type='hidden' />"
				+ "<input id='AuditType' name='AuditType' value='" + AuditType
				+ "' type='hidden' />"
				+ "<input id='RandomTimeStamp' name='RandomTimeStamp' value='"
				+ RandomTimeStamp + "' type='hidden' />"
				+ "<input id='Remark1' name='Remark1' value='" + Remark1
				+ "' type='hidden' />"
				+ "<input id='Remark2' name='Remark2' value='" + Remark2
				+ "' type='hidden' />"
				+ "<input id='Remark3' name='Remark3' value='" + Remark3
				+ "' type='hidden' />"
				+ "<input id='ReturnURL' name='ReturnURL' value='" + ReturnURL
				+ "' type='hidden' />"
				+ "<input id='NotifyURL' name='NotifyURL' value='" + NotifyURL
				+ "' type='hidden' />"
				+ "<input id='SignInfo' name='SignInfo' value='" + SignInfo
				+ "' type='hidden' />"
//				+ "<input id='submit' name='submit' value='提交' type='submit' />" 
				+ "</form>"
				+ "<script type='text/javascript'>"
				+ "window.onload=function(){document.form1.submit();}"
				+ "</script>";
		System.out.println("审核表单======="+result);
		return result;
	}

	
public String[] querybalance(Website website,Uservip uservip){
		
		PlatformMoneymoremore = website.getTrustAccount();
		String PlatformId = uservip.getTrustAccount();
		Map<String, String> req = new TreeMap<String, String>();
		req.put("PlatformId", PlatformId);
		String PlatformType = "1";
		String dataStr = PlatformId + PlatformType + PlatformMoneymoremore;
		
		String privatekey = website.getPrivateKey();// Common.privateKeyPKCS8;


		System.out.println(dataStr);
		// 签名
		MD5Trust md5 = new MD5Trust();
		RsaHelper rsa = RsaHelper.getInstance();
		if (antistate == 1) {
			dataStr = md5.getMD5Info(dataStr);
		}
		SignInfo = rsa.signData(dataStr, privatekey);
		req.put("PlatformMoneymoremore", PlatformMoneymoremore);
		req.put("PlatformId", PlatformId);
		req.put("PlatformType", PlatformType);
		req.put("SignInfo", SignInfo);
		if(istest){
			this.SubmitURL = "http://218.4.234.150:88/main/loan/balancequery.action";
		}else{
			this.SubmitURL = "https://query.moneymoremore.com/loan/balancequery.action";
		}
		String[] resultarr = HttpClientUtil.doPostQueryCmd(SubmitURL, req);
		
		return resultarr;
	}
	
	/**
	 * 生成充值信息 第二步
	 */
	public Map appLoanRecharge(HttpServletRequest request, Website website,
			Uservip rechargeUser, Offlinerecharge recharge) {
		try {
			Map map = new HashMap<String, Object>();
			String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";

			// SubmitURL = SubmitURLPrefix + "loan/toloanrecharge.action";
			if (istest) {
				this.SubmitURL = "http://218.4.234.150:88/main/loan/toloanrecharge.action";
			} else {
				this.SubmitURL = "https://recharge.moneymoremore.com/loan/toloanrecharge.action";
			}

			ReturnURL = basePath + "loan/loanrechargereturn.action";
			NotifyURL = basePath + "loan/loanrechargenotify.action";

			String privatekey = website.getPrivateKey();
			String publickey = website.getPublicKey();
			RandomTimeStamp = "";
			if (antistate == 1) {
				Date d = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
				RandomTimeStamp = Common.getRandomNum(2) + sdf.format(d);
			}
			this.PlatformMoneymoremore = website.getTrustAccount();
			this.RechargeMoneymoremore = rechargeUser.getTrustAccount();
			this.OrderNo = rechargeUser.getUserId() + "_"
					+ recharge.getOfflineRechargeId();
			this.Amount = String.valueOf(recharge.getRechargeAmount());
			this.RechargeType = "2";
			FeeType = "2";
			String dataStr = RechargeMoneymoremore + PlatformMoneymoremore
					+ OrderNo + Amount + RechargeType + FeeType
					+ RandomTimeStamp + Remark1 + Remark2 + Remark3 + NotifyURL;

			System.out.println(dataStr);
			// 签名
			MD5Trust md5 = new MD5Trust();
			RsaHelper rsa = RsaHelper.getInstance();
			if (antistate == 1) {
				dataStr = md5.getMD5Info(dataStr);
			}
			SignInfo = rsa.signData(dataStr, privatekey);

			map.put("RechargeMoneymoremore", RechargeMoneymoremore);
			map.put("PlatformMoneymoremore", PlatformMoneymoremore);
			map.put("OrderNo", OrderNo);
			map.put("Amount", Amount);
			map.put("CardNo", CardNo);
			map.put("RechargeType", RechargeType);
			map.put("FeeType", FeeType);
			map.put("RandomTimeStamp", RandomTimeStamp);
			map.put("Remark1", Remark1);
			map.put("Remark2", Remark2);
			map.put("NotifyURL", NotifyURL);
			map.put("SignInfo", SignInfo);

			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 生成充值信息 第二步
	 */
	public String LoanRecharge(HttpServletRequest request, Website website,
			Uservip rechargeUser, Offlinerecharge recharge,String chargetype) {
		try {
			String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";

			// SubmitURL = SubmitURLPrefix + "loan/toloanrecharge.action";
			if (istest) {
				this.SubmitURL = "http://218.4.234.150:88/main/loan/toloanrecharge.action";
			} else {
				this.SubmitURL = "https://recharge.moneymoremore.com/loan/toloanrecharge.action";
			}

			ReturnURL = basePath + "loan/loanrechargereturn.action";
			NotifyURL = basePath + "loan/loanrechargenotify.action";

			String privatekey = website.getPrivateKey();
			String publickey = website.getPublicKey();
			if (antistate == 1) {
				Date d = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
				RandomTimeStamp = Common.getRandomNum(2) + sdf.format(d);
			}
			this.PlatformMoneymoremore = website.getTrustAccount();
			this.RechargeMoneymoremore = rechargeUser.getTrustAccount();
			this.OrderNo = rechargeUser.getUserId() + "_"
					+ recharge.getOfflineRechargeId();
			this.Amount = String.valueOf(recharge.getRechargeAmount());
			this.RechargeType = "";
			if(chargetype!=null && chargetype.equals("quick")){
				this.RechargeType = "2";
				this.FeeType = "4";
			}
			
			

			String dataStr = RechargeMoneymoremore + PlatformMoneymoremore
					+ OrderNo + Amount + RechargeType + FeeType + CardNo
					+ RandomTimeStamp + Remark1 + Remark2 + Remark3 +ReturnURL  + NotifyURL;
			// 签名
			MD5Trust md5 = new MD5Trust();
			RsaHelper rsa = RsaHelper.getInstance();
			if (antistate == 1) {
				dataStr = md5.getMD5Info(dataStr);
			}
			SignInfo = rsa.signData(dataStr, privatekey);

			if (StringUtils.isNotBlank(CardNo)) {
				CardNo = rsa.encryptData(CardNo, publickey);
			}

			return this.rechargeForm();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String rechargeForm() {
		String result = "";
		result = "<form id='form1' name='form1' action='"
				+ SubmitURL
				+ "' method='post'>"
				+ "<input id='RechargeMoneymoremore' name='RechargeMoneymoremore' value='"
				+ RechargeMoneymoremore
				+ "' type='hidden' />"
				+ "<input id='PlatformMoneymoremore' name='PlatformMoneymoremore' value='"
				+ PlatformMoneymoremore + "' type='hidden' />"
				+ "<input id='OrderNo' name='OrderNo' value='" + OrderNo
				+ "' type='hidden' />"
				+ "<input id='Amount' name='Amount' value='" + Amount
				+ "' type='hidden' />"
				+ "<input id='RechargeType' name='RechargeType' value='"
				+ RechargeType + "' type='hidden' />"
				+ "<input id='FeeType' name='FeeType' value='" + FeeType
				+ "' type='hidden' />"
				+ "<input id='CardNo' name='CardNo' value='" + CardNo
				+ "' type='hidden' />"
				+ "<input id='RandomTimeStamp' name='RandomTimeStamp' value='"
				+ RandomTimeStamp + "' type='hidden' />"
				+ "<input id='Remark1' name='Remark1' value='" + Remark1
				+ "' type='hidden' />"
				+ "<input id='Remark2' name='Remark2' value='" + Remark2
				+ "' type='hidden' />"
				+ "<input id='Remark3' name='Remark3' value='" + Remark3
				+ "' type='hidden' />"
				+ "<input id='ReturnURL' name='ReturnURL' value='" + ReturnURL
				+ "' type='hidden' />"
				+ "<input id='NotifyURL' name='NotifyURL' value='" + NotifyURL
				+ "' type='hidden' />"
				+ "<input id='SignInfo' name='SignInfo' value='" + SignInfo
				+ "' type='hidden' />" + "</form>"
				+ "<script type='text/javascript'>"
				+ "window.onload=function(){document.form1.submit();}"
				+ "</script>";
		return result;
	}

	/**
	 * 生成提现信息
	 */
	public Map apploanWithdraws(HttpServletRequest request, Website website,
			Translate translate, Uservip cashuser) {
		try {
			Map map = new HashMap<String, Object>();
			String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";

			if (istest) {
				this.SubmitURL = "http://218.4.234.150:88/main/loan/toloanwithdraws.action";
			} else {
				this.SubmitURL = "https://withdrawals.moneymoremore.com/loan/toloanwithdraws.action";
			}

			// SubmitURL = SubmitURLPrefix + "loan/toloanwithdraws.action";
			ReturnURL = basePath + "loan/loanwithdrawsreturn.action";
			NotifyURL = basePath + "loan/loanwithdrawsnotify.action";

			String privatekey = website.getPrivateKey();
			String publickey = website.getPublicKey();
			if (antistate == 1) {
				Date d = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
				RandomTimeStamp = Common.getRandomNum(2) + sdf.format(d);
			}

			this.WithdrawMoneymoremore = cashuser.getTrustAccount();
			this.PlatformMoneymoremore = website.getTrustAccount();
			this.OrderNo = translate.getTranslateId().intValue() + "_"
					+ cashuser.getUserId().intValue();
			this.Amount = String.valueOf(translate.getAffectMoney()
					.doubleValue());
			String bankinfo = translate.getBankNum();
			String[] bankinfos = bankinfo.split(",");
			this.CardNo = bankinfos[2];
			this.CardType = "0";
			this.BankCode = translate.getBanktypeId() + "";
			this.BranchBankName = bankinfos[1];
			this.Province = translate.getBankProvinceId() + "";
			this.City = translate.getBankCityId() + "";

			String cashfee = website.getWithdrawals();
			if(cashfee!=null && cashfee.equals("0")){//平台付费
				FeePercent = "100";
				FeeRate= "";
			}else if(cashfee!=null && !cashfee.equals("0")){//用户付款
				FeePercent = "0";
				FeeRate = cashfee;
			}
			
			
			String dataStr = WithdrawMoneymoremore + PlatformMoneymoremore
					+ OrderNo + Amount + FeePercent + FeeMax + FeeRate + CardNo
					+ CardType + BankCode + BranchBankName + Province + City
					+ RandomTimeStamp + Remark1 + Remark2 + Remark3 + NotifyURL;
			// 签名
			MD5Trust md5 = new MD5Trust();
			RsaHelper rsa = RsaHelper.getInstance();
			if (antistate == 1) {
				dataStr = md5.getMD5Info(dataStr);
			}
			SignInfo = rsa.signData(dataStr, privatekey);

			CardNo = rsa.encryptData(CardNo, publickey);

			map.put("WithdrawMoneymoremore", WithdrawMoneymoremore);
			map.put("PlatformMoneymoremore", PlatformMoneymoremore);
			map.put("OrderNo", OrderNo);
			map.put("Amount", Amount);
			map.put("FeePercent", FeePercent);
			map.put("FeeMax", FeeMax);
			map.put("FeeRate", FeeRate);
			map.put("CardNo", CardNo);
			map.put("CardType", CardType);
			map.put("BankCode", BankCode);
			map.put("BranchBankName", BranchBankName);
			map.put("Province", Province);
			map.put("City", City);
			map.put("RandomTimeStamp", RandomTimeStamp);
			map.put("Remark1", Remark1);
			map.put("Remark2", Remark2);
			map.put("Remark3", Remark3);
			map.put("NotifyURL", NotifyURL);
			map.put("SignInfo", SignInfo);

			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 生成提现信息
	 */
	public String loanWithdraws(HttpServletRequest request, Website website,
			Translate translate, Uservip cashuser) {
		try {

			String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";

			if (istest) {
				this.SubmitURL = "http://218.4.234.150:88/main/loan/toloanwithdraws.action";
			} else {
				this.SubmitURL = "https://withdrawals.moneymoremore.com/loan/toloanwithdraws.action";
			}

			// SubmitURL = SubmitURLPrefix + "loan/toloanwithdraws.action";
			ReturnURL = basePath + "loan/loanwithdrawsreturn.action";
			NotifyURL = basePath + "loan/loanwithdrawsnotify.action";

			String privatekey = website.getPrivateKey();
			String publickey = website.getPublicKey();
			if (antistate == 1) {
				Date d = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
				RandomTimeStamp = Common.getRandomNum(2) + sdf.format(d);
			}

			this.WithdrawMoneymoremore = cashuser.getTrustAccount();
			this.PlatformMoneymoremore = website.getTrustAccount();
			this.OrderNo = translate.getTranslateId().intValue() + "_"
					+ cashuser.getUserId().intValue();
			this.Amount = String.valueOf(translate.getAffectMoney()
					.doubleValue());
			String bankinfo = translate.getBankNum();
			String[] bankinfos = bankinfo.split(",");
			this.CardNo = bankinfos[2];
			this.CardType = "0";
			this.BankCode = translate.getBanktypeId() + "";
			this.BranchBankName = bankinfos[1];
			this.Province = translate.getBankProvinceId() + "";
			this.City = translate.getBankCityId() + "";
			
			String cashfee = website.getWithdrawals();
			if(cashfee!=null && cashfee.equals("0")){//平台付费
				FeePercent = "100";
				FeeRate= "";
			}else if(cashfee!=null && !cashfee.equals("0")){//用户付款
				FeePercent = "0";
				FeeRate = cashfee;
			}
			Remark2=cashuser.getUserNames();
			String dataStr = WithdrawMoneymoremore + PlatformMoneymoremore
					+ OrderNo + Amount + FeePercent + FeeMax + FeeRate + CardNo
					+ CardType + BankCode + BranchBankName + Province + City
					+ RandomTimeStamp + Remark1 + Remark2 + Remark3 + ReturnURL
					+ NotifyURL;
			// 签名
			MD5Trust md5 = new MD5Trust();
			RsaHelper rsa = RsaHelper.getInstance();
			if (antistate == 1) {
				dataStr = md5.getMD5Info(dataStr);
			}
			SignInfo = rsa.signData(dataStr, privatekey);

			CardNo = rsa.encryptData(CardNo, publickey);
			return this.withdrawsForm();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String withdrawsForm() {
		String result = "";

		result = "<form id='form1' name='form1' action='"
				+ SubmitURL
				+ "' method='post'>"
				+ "<input id='WithdrawMoneymoremore' name='WithdrawMoneymoremore' value='"
				+ WithdrawMoneymoremore
				+ "' type='hidden' />"
				+ "<input id='PlatformMoneymoremore' name='PlatformMoneymoremore' value='"
				+ PlatformMoneymoremore + "' type='hidden' />"
				+ "<input id='OrderNo' name='OrderNo' value='" + OrderNo
				+ "' type='hidden' />"
				+ "<input id='FeePercent' name='FeePercent' value='"
				+ FeePercent + "' type='hidden' />"
				+ "<input id='FeeMax' name='FeeMax' value='" + FeeMax
				+ "' type='hidden' />"
				+ "<input id='FeeRate' name='FeeRate' value='" + FeeRate
				+ "' type='hidden' />"
				+ "<input id='Amount' name='Amount' value='" + Amount
				+ "' type='hidden' />"
				+ "<input id='CardNo' name='CardNo' value='" + CardNo
				+ "' type='hidden' />"
				+ "<input id='CardType' name='CardType' value='" + CardType
				+ "' type='hidden' />"
				+ "<input id='BankCode' name='BankCode' value='" + BankCode
				+ "' type='hidden' />"
				+ "<input id='BranchBankName' name='BranchBankName' value='"
				+ BranchBankName + "' type='hidden' />"
				+ "<input id='Province' name='Province' value='" + Province
				+ "' type='hidden' />" + "<input id='City' name='City' value='"
				+ City + "' type='hidden' />"
				+ "<input id='RandomTimeStamp' name='RandomTimeStamp' value='"
				+ RandomTimeStamp + "' type='hidden' />"
				+ "<input id='Remark1' name='Remark1' value='" + Remark1
				+ "' type='hidden' />"
				+ "<input id='Remark2' name='Remark2' value='" + Remark2
				+ "' type='hidden' />"
				+ "<input id='Remark3' name='Remark3' value='" + Remark3
				+ "' type='hidden' />"
				+ "<input id='ReturnURL' name='ReturnURL' value='" + ReturnURL
				+ "' type='hidden' />"
				+ "<input id='NotifyURL' name='NotifyURL' value='" + NotifyURL
				+ "' type='hidden' />"
				+ "<input id='SignInfo' name='SignInfo' value='" + SignInfo
				+ "' type='hidden' />" + "</form>"
				+ "<script type='text/javascript'>"
				+ "window.onload=function(){document.form1.submit();}"
				+ "</script>";
		System.out.println(result);
		return result;
	}

	/**
	 * 提现信息审核
	 * 
	 * @return
	 */
	public String withdrawsAudit(String orderlist, Website website,
			String auditType, HttpServletRequest request, Translate translate,
			Manager manager, Oerationlog oerationlog) {

		try {
			String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";

			if (istest) {
				this.SubmitURL = "http://218.4.234.150:88/main/loan/toloantransferaudit.action";
			} else {
				this.SubmitURL = "https://audit.moneymoremore.com/loan/toloantransferaudit.action";
			}

			// SubmitURL = SubmitURLPrefix + "loan/toloantransferaudit.action";
			ReturnURL = basePath + "loan/withdrawsAuditreturn.action";
			NotifyURL = basePath + "loan/withdrawsAuditnotify.action";

			String privatekey = website.getPrivateKey();
			if (antistate == 1) {
				Date d = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
				RandomTimeStamp = Common.getRandomNum(2) + sdf.format(d);
			}

			this.LoanNoList = orderlist;
			this.AuditType = auditType;
			this.PlatformMoneymoremore = website.getTrustAccount();

			this.Remark1 = "" + translate.getTranslateId().intValue();
			this.Remark2 = translate.getTexplain();
			this.Remark3 = manager.getManagerId().intValue() + "_"
					+ oerationlog.getOrationIp();
			String dataStr = LoanNoList + PlatformMoneymoremore
					+ this.AuditType + RandomTimeStamp + Remark1 + Remark2
					+ Remark3 + ReturnURL + NotifyURL;
			// 签名
			MD5Trust md5 = new MD5Trust();
			RsaHelper rsa = RsaHelper.getInstance();
			if (antistate == 1) {
				dataStr = md5.getMD5Info(dataStr);
			}
			SignInfo = rsa.signData(dataStr, privatekey);
			return auditform();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public String transfer(Uservip uservip, HttpServletRequest request,
			Website website, String type, double amount, boolean isauto,
			boolean autoAudit) {

		String SecondaryJsonList = "";

		List<LoanInfoBean> listmlib = new ArrayList<LoanInfoBean>();

		LoanInfoBean mlib = new LoanInfoBean();

		mlib.setLoanOutMoneymoremore(uservip.getTrustAccount());
		mlib.setLoanInMoneymoremore(website.getTrustAccount());
		mlib.setOrderNo(uservip.getUserId() + "_" + Common.getRandomNum(7)
				+ "_" + type);
		mlib.setBatchNo(type + Common.getRandomNum(7));
		mlib.setExchangeBatchNo("");
		mlib.setAdvanceBatchNo("");
		mlib.setAmount(String.valueOf(amount));
		mlib.setFullAmount(String.valueOf(amount));
		mlib.setTransferName(type);
		mlib.setRemark("");
		mlib.setSecondaryJsonList(SecondaryJsonList);
		listmlib.add(mlib);

		LoanJsonList = Common.JSONEncode(listmlib);

		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";

		if (istest) {
			this.SubmitURL = "http://218.4.234.150:88/main/loan/loan.action";
		} else {
			this.SubmitURL = "https://transfer.moneymoremore.com/loan/loan.action";
		}

		// this.SubmitURL = SubmitURLPrefix + "loan/loan.action";
		ReturnURL = basePath + "loan/loantraansferreturn.action";
		NotifyURL = basePath + "loan/loantraansfernotify.action";

		PlatformMoneymoremore = website.getTrustAccount();

		String privatekey = website.getPrivateKey();// Common.privateKeyPKCS8;

		if (antistate == 1) {
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			RandomTimeStamp = Common.getRandomNum(2) + sdf.format(d);
		}
		TransferAction = "1";// 投标，2：还款，3：其他
		Action = "";
		if (isauto) {
			Action = "2";
		} else {
			Action = "1";
		}

		TransferType = "2";
		NeedAudit = "";
		if (autoAudit) {
			NeedAudit = "1";
		}

		String dataStr = LoanJsonList + PlatformMoneymoremore + TransferAction
				+ Action + TransferType + NeedAudit + RandomTimeStamp + Remark1
				+ Remark2 + Remark3 + ReturnURL + NotifyURL;
		// 签名
		MD5Trust md5 = new MD5Trust();
		RsaHelper rsa = RsaHelper.getInstance();
		if (antistate == 1) {
			dataStr = md5.getMD5Info(dataStr);
		}
		SignInfo = rsa.signData(dataStr, privatekey);

		LoanJsonList = Common.UrlEncoder(LoanJsonList, "utf-8");
		if (Action.equals("2")) {// 自动付款，http提交
			String[] resultarr = this.traansferhttp();
			if (StringUtils.isNotBlank(resultarr[1])
					&& (resultarr[1].startsWith("[") || resultarr[1]
							.startsWith("{"))) {
				return resultarr[1];
			}
		} else {// 组装form表单
			return transferForm();
		}

		return null;

	}

	public String getSubmitURLPrefix() {
		return SubmitURLPrefix;
	}

	public void setSubmitURLPrefix(String submitURLPrefix) {
		SubmitURLPrefix = submitURLPrefix;
	}

	public String getLoanJsonList() {
		return LoanJsonList;
	}

	public void setLoanJsonList(String loanJsonList) {
		LoanJsonList = loanJsonList;
	}

	public String getTransferAction() {
		return TransferAction;
	}

	public void setTransferAction(String transferAction) {
		TransferAction = transferAction;
	}

	public String getPlatformMoneymoremore() {
		return PlatformMoneymoremore;
	}

	public void setPlatformMoneymoremore(String platformMoneymoremore) {
		PlatformMoneymoremore = platformMoneymoremore;
	}

	public String getSubmitURL() {
		return SubmitURL;
	}

	public void setSubmitURL(String submitURL) {
		SubmitURL = submitURL;
	}

	public String getReturnURL() {
		return ReturnURL;
	}

	public void setReturnURL(String returnURL) {
		ReturnURL = returnURL;
	}

	public String getNotifyURL() {
		return NotifyURL;
	}

	public void setNotifyURL(String notifyURL) {
		NotifyURL = notifyURL;
	}

	public String getSignInfo() {
		return SignInfo;
	}

	public void setSignInfo(String signInfo) {
		SignInfo = signInfo;
	}

	public String getResultCode() {
		return ResultCode;
	}

	public void setResultCode(String resultCode) {
		ResultCode = resultCode;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public String getReturnTimes() {
		return ReturnTimes;
	}

	public void setReturnTimes(String returnTimes) {
		ReturnTimes = returnTimes;
	}

	public String getVerifySignature() {
		return verifySignature;
	}

	public void setVerifySignature(String verifySignature) {
		this.verifySignature = verifySignature;
	}

	public String getRandomTimeStamp() {
		return RandomTimeStamp;
	}

	public void setRandomTimeStamp(String randomTimeStamp) {
		RandomTimeStamp = randomTimeStamp;
	}

	public String getAction() {
		return Action;
	}

	public void setAction(String action) {
		Action = action;
	}

	public String getTransferType() {
		return TransferType;
	}

	public void setTransferType(String transferType) {
		TransferType = transferType;
	}

	public String getNeedAudit() {
		return NeedAudit;
	}

	public void setNeedAudit(String needAudit) {
		NeedAudit = needAudit;
	}

	public String getRemark1() {
		return Remark1;
	}

	public void setRemark1(String remark1) {
		Remark1 = remark1;
	}

	public String getRemark2() {
		return Remark2;
	}

	public void setRemark2(String remark2) {
		Remark2 = remark2;
	}

	public String getRemark3() {
		return Remark3;
	}

	public void setRemark3(String remark3) {
		Remark3 = remark3;
	}

	public int getAntistate() {
		return antistate;
	}

	public String getLoanNoList() {
		return LoanNoList;
	}

	public void setLoanNoList(String loanNoList) {
		LoanNoList = loanNoList;
	}

	public String getLoanNoListFail() {
		return LoanNoListFail;
	}

	public void setLoanNoListFail(String loanNoListFail) {
		LoanNoListFail = loanNoListFail;
	}

	public String getAuditType() {
		return AuditType;
	}

	public void setAuditType(String auditType) {
		AuditType = auditType;
	}

	public String getRechargeMoneymoremore() {
		return RechargeMoneymoremore;
	}

	public void setRechargeMoneymoremore(String rechargeMoneymoremore) {
		RechargeMoneymoremore = rechargeMoneymoremore;
	}

	public String getOrderNo() {
		return OrderNo;
	}

	public void setOrderNo(String orderNo) {
		OrderNo = orderNo;
	}

	public String getAmount() {
		return Amount;
	}

	public void setAmount(String amount) {
		Amount = amount;
	}

	public String getFeePlatform() {
		return FeePlatform;
	}

	public void setFeePlatform(String feePlatform) {
		FeePlatform = feePlatform;
	}

	public String getRechargeType() {
		return RechargeType;
	}

	public void setRechargeType(String rechargeType) {
		RechargeType = rechargeType;
	}

	public String getFeeType() {
		return FeeType;
	}

	public void setFeeType(String feeType) {
		FeeType = feeType;
	}

	public String getCardNoList() {
		return CardNoList;
	}

	public void setCardNoList(String cardNoList) {
		CardNoList = cardNoList;
	}

	public String getWithdrawMoneymoremore() {
		return WithdrawMoneymoremore;
	}

	public void setWithdrawMoneymoremore(String withdrawMoneymoremore) {
		WithdrawMoneymoremore = withdrawMoneymoremore;
	}

	public String getFeePercent() {
		return FeePercent;
	}

	public void setFeePercent(String feePercent) {
		FeePercent = feePercent;
	}

	public String getFee() {
		return Fee;
	}

	public void setFee(String fee) {
		Fee = fee;
	}

	public String getFreeLimit() {
		return FreeLimit;
	}

	public void setFreeLimit(String freeLimit) {
		FreeLimit = freeLimit;
	}

	public String getFeeMax() {
		return FeeMax;
	}

	public void setFeeMax(String feeMax) {
		FeeMax = feeMax;
	}

	public String getFeeWithdraws() {
		return FeeWithdraws;
	}

	public void setFeeWithdraws(String feeWithdraws) {
		FeeWithdraws = feeWithdraws;
	}

	public String getFeeRate() {
		return FeeRate;
	}

	public void setFeeRate(String feeRate) {
		FeeRate = feeRate;
	}

	public String getFeeSplitting() {
		return FeeSplitting;
	}

	public void setFeeSplitting(String feeSplitting) {
		FeeSplitting = feeSplitting;
	}

	public String getCardNo() {
		return CardNo;
	}

	public void setCardNo(String cardNo) {
		CardNo = cardNo;
	}

	public String getCardType() {
		return CardType;
	}

	public void setCardType(String cardType) {
		CardType = cardType;
	}

	public String getBankCode() {
		return BankCode;
	}

	public void setBankCode(String bankCode) {
		BankCode = bankCode;
	}

	public String getBranchBankName() {
		return BranchBankName;
	}

	public void setBranchBankName(String branchBankName) {
		BranchBankName = branchBankName;
	}

	public String getProvince() {
		return Province;
	}

	public void setProvince(String province) {
		Province = province;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

}
