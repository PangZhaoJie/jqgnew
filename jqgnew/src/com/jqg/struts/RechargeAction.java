 package com.jqg.struts;
 
 import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.etonepay.b2c.utils.MD5;
import com.jqg.pojo.Companybankinfor;
import com.jqg.pojo.Moneycount;
import com.jqg.pojo.Offlinerecharge;
import com.jqg.pojo.Onlinemodel;
import com.jqg.pojo.Uservip;
import com.jqg.pojo.Website;
import com.jqg.service.CompanybankinforService;
import com.jqg.service.MoneycountService;
import com.jqg.service.OfflinerechargeService;
import com.jqg.service.OnlinemodelService;
import com.jqg.service.UservipService;
import com.jqg.service.WebsiteService;
import com.jqg.service.impl.CompanybankinforServiceImpl;
import com.jqg.service.impl.MoneycountServiceImpl;
import com.jqg.service.impl.OfflinerechargeServiceImpl;
import com.jqg.service.impl.OnlinemodelServiceImpl;
import com.jqg.service.impl.UservipServiceImpl;
import com.jqg.service.impl.WebsiteServiceImpl;
import com.jqg.util.GopayUtils;
import com.jqg.util.HexUtils;
import com.jqg.util.LoanUtils;
import com.jqg.util.MD5HuiChao;
import com.jqg.util.MD5YiBao;
import com.jqg.util.UploadPhoto;
import com.jqg.util.md5;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
 
 public class RechargeAction extends ActionSupport
   implements ServletRequestAware, ServletResponseAware
 {
   private Integer userId;
   private Integer companyBankInforId;
   private File file;
   private String fileFileName;
   private Offlinerecharge offlinerecharge;
   private List<Offlinerecharge> offlinerechargeList;
   private List<Companybankinfor> companybankinforList;
   private List<Onlinemodel> onlinemodelList;
   private Integer mark;
   private String successMoney = "0";
   private String fasleMoney = "0";
   private String otherMoney = "0";
   private Integer offlineRechargeId;
   private Double chongzhiMoney;
   private String TransID;
   private Double FactMoney;
   private Integer Result;
   private Integer page = Integer.valueOf(0);
   private Integer pageSize = Integer.valueOf(5);
   private Integer pageCount;
   private Double yeMoney;
   private OfflinerechargeService offlinerechargeService = new OfflinerechargeServiceImpl();
   private UservipService uservipService = new UservipServiceImpl();
   private CompanybankinforService companybankinforService = new CompanybankinforServiceImpl();
   private OnlinemodelService onlinemodelService = new OnlinemodelServiceImpl();
   private MoneycountService moneycountService = new MoneycountServiceImpl();
 
   private Timestamp timestamp = new Timestamp(System.currentTimeMillis());
   private String MerNo;
   private String MD5key;
   private String ReturnURL;
   private String tradeAdd;
   private String md5src;
   private String SignInfo;
   private String AdviceURL = "/huichao/payresult.jsp";
   private SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   private String defaultBankNumber;
   private String orderTime;
   private String products = "products info";
   private String ct;
   public String getCt() {
	return ct;
}
public void setCt(String ct) {
	this.ct = ct;
}
private String version = "2.1";
   private String charset = "2";
   private String language = "1";
   private String signType = "1";
   private String tranCode = "8888";
   private String merchantID;
   private String merOrderNum;
   private String tranAmt;
   private String feeAmt;
   private String currencyType = "156";
   private String frontMerUrl = "http://127.0.0.1:8080/jqgxuchang/guofubao/true.jsp";
   private String backgroundMerUrl = "http://127.0.0.1:8080/jqgxuchang/guofubao/res.jsp";
   private String tranDateTime;
   private String virCardNoIn = "0000000002000000058";
   private String tranIP;
   private String isRepeatSubmit = "1";
   private String goodsName;
   private String goodsDetail;
   private String buyerName;
   private String buyerContact;
   private String merRemark1;
   private String merRemark2;
   private String bankCode = "CCB";
   private String userType = "1";
   private String VerficationCode;
   private String gopayServerTime;
   private String signValue;
   private String urlStr;
   private String message;
   private String TradeDate;
   private String MemberID;
   private String TerminalID;
   private String PayID = "";
   private String OrderMoney;
   private String PageUrl = "http://192.168.1.13/baofoo/pageurl.jsp?offlinerechargeId=${offlinerecharge.offlineRechargeId}";
   private String ReturnUrl = "http://192.168.1.13/baofoo/returnurl.jsp?offlinerechargeId=${offlinerecharge.offlineRechargeId}";
   private String KeyType = "1";
   private String Signature;
   private String NoticeType = "1";
   private String InterfaceVersion = "4.0";
   private String payUrl = "http://tgw.baofoo.com/payindex";
   private HttpServletRequest request;
   private HttpServletResponse response;
   private String postform;
 
   public String toChongzhi()
     throws Exception
   {
     this.onlinemodelList = this.onlinemodelService.findOnlinemodelByAble();
     this.yeMoney = this.moneycountService.findMoneycountByuserId(this.userId.intValue()).getAvailableMoney();
     return "success";
   }
   public String toOffLine() throws Exception {
     this.companybankinforList = this.companybankinforService.findCompanybankinfors();
     if (this.mark == null) {
       this.mark = Integer.valueOf(-1);
     }
 
     return "success";
   }
   /**
    *  托管充值处理
 * @throws Exception 
    */
   public String trustRecharge() throws Exception{
	   
	   Uservip uservip = (Uservip) ActionContext.getContext().getSession().get("uservip");
	   uservip = this.uservipService.findUservipByUserid(uservip.getUserId().intValue());
	   this.offlinerecharge = new Offlinerecharge();
	   this.offlinerecharge.setRechargeAmount(this.chongzhiMoney);
	   this.offlinerecharge.setRechargeTime(new Timestamp(new Date().getTime()));
	   this.offlinerecharge.setRechargeStatus(2);
	   this.offlinerecharge.setUservip(uservip);
	   this.offlinerecharge.setRecharge("托管支付");
	   this.offlinerechargeService.createOfflinerecharge(this.offlinerecharge);
	   WebsiteService websiteService = new WebsiteServiceImpl();
	   Website website = websiteService.findWebsiteBywebsiteId(1);
	   LoanUtils loanutils = new LoanUtils();
	   request = ServletActionContext.getRequest();
	   if(this.ct!=null&&this.equals("quick")){
		   this.postform = loanutils.LoanRecharge(request, website, uservip, offlinerecharge,"quick");
	   }else{
	       this.postform = loanutils.LoanRecharge(request, website, uservip, offlinerecharge,"online");
	   }
	   return "success";
   }
   public String saveOffLine() throws Exception {
     this.offlinerecharge.setCompanybankinfor(this.companybankinforService.findCompanybankinforById(1));
     this.offlinerecharge.setRechargeTime(new Timestamp(new Date().getTime()));
     this.offlinerecharge.setRechargeStatus(Integer.valueOf(3));
     this.offlinerecharge.setUservip(this.uservipService.findUservipByUserid(this.userId.intValue()));
 
     this.offlinerecharge.setRecharge("线下充值");
     this.offlinerechargeService.createOfflinerecharge(this.offlinerecharge);
     return "success";
   }
 
   public String toSavePic() throws IOException
   {
     if ((".jpg".equals(this.fileFileName.substring(this.fileFileName.indexOf(".")))) || 
       (".bmp".equals(this.fileFileName.substring(this.fileFileName.indexOf(".")))) || 
       (".gif".equals(this.fileFileName.substring(this.fileFileName.indexOf(".")))) || 
       (".png".equals(this.fileFileName.substring(this.fileFileName.indexOf(".")))))
     {
       this.urlStr = UploadPhoto.LoadImageToServer(this.fileFileName, this.file);
       if ((this.urlStr != null) && (!"".equals(this.urlStr))) {
         this.message = "你已成功上传文件";
         return "success";
       }
       this.message = "上传文件失败";
       return "error";
     }
 
     this.message = "上传文件失败";
     return "error";
   }
 
   public String toNote()
     throws Exception
   {
     DecimalFormat df = new DecimalFormat("#.00");
     List list = this.offlinerechargeService
       .findOfflinerechargeBySql("select * from Offlinerecharge o where o.userId=" + this.userId);
     if (list.size() > 0) {
       for (int i = 0; i < list.size(); i++) {
         Offlinerecharge og = (Offlinerecharge)list.get(i);
         if (og.getRechargeStatus().intValue() == 1)
           this.successMoney = df.format(new BigDecimal(og.getRechargeAmount().doubleValue()).add(new BigDecimal(this.successMoney)));
         else if (og.getRechargeStatus().intValue() == 2)
           this.fasleMoney = df.format(new BigDecimal(og.getRechargeAmount().doubleValue()).add(new BigDecimal(this.fasleMoney)));
         else if (og.getRechargeStatus().intValue() == 3) {
           this.otherMoney = df.format(new BigDecimal(og.getRechargeAmount().doubleValue()).add(new BigDecimal(this.otherMoney)));
         }
       }
     }
 
     Integer total = Integer.valueOf(list.size());
     if (this.page.intValue() == 0) {
       this.page = Integer.valueOf(1);
     }
     if (total.intValue() == 0)
       this.pageCount = Integer.valueOf(1);
     else if (total.intValue() % this.pageSize.intValue() == 0)
       this.pageCount = Integer.valueOf(total.intValue() / this.pageSize.intValue());
     else {
       this.pageCount = Integer.valueOf(total.intValue() / this.pageSize.intValue() + 1);
     }
     String sql = "select * from Offlinerecharge o where o.userId=" + this.userId + " order by o.rechargeTime desc " + 
       "LIMIT " + (this.page.intValue() - 1) * this.pageSize.intValue() + "," + this.pageSize;
     this.offlinerechargeList = this.offlinerechargeService.findOfflinerechargeBySql(sql);
     this.mark = Integer.valueOf(-2);
 
     return "success";
   }
// 汇潮
   public String huichao() throws Exception {
     OnlinemodelService onlinemodelService = new OnlinemodelServiceImpl();
     Onlinemodel onlinemodel = onlinemodelService.findOnlinemodelByonlineId(2);

 	
     this.offlinerecharge = new Offlinerecharge();
     this.offlinerecharge.setRechargeAmount(this.chongzhiMoney);
     this.offlinerecharge.setRechargeTime(new Timestamp(new Date().getTime()));
     this.offlinerecharge.setRechargeStatus(Integer.valueOf(2));
     this.offlinerecharge.setUservip(this.uservipService.findUservipByUserid(this.userId.intValue()));
     this.offlinerecharge.setRecharge("汇朝");
     this.offlinerechargeService.createOfflinerecharge(this.offlinerecharge);
     this.MD5key = onlinemodel.getAccountPassword();
     this.MerNo = onlinemodel.getAccountNumber();
     this.request = ServletActionContext.getRequest();
     String path = request.getContextPath();
     String basePath = "";
     if(request.getServerPort()==80){
    	 basePath = request.getScheme() + "://" + request.getServerName()
    				+ path + "/";
     }else{
    	 basePath = request.getScheme() + "://" + request.getServerName()
			+ ":" + request.getServerPort() + path + "/";
     }
	  
     
     
     this.ReturnURL = basePath+"huichao/payresult.jsp";
     this.AdviceURL = basePath+"huichao/payresult.jsp";
     
     this.md5src = (this.MerNo  + "&"+offlinerecharge.getOfflineRechargeId() +"&"+ this.chongzhiMoney + "&" + this.ReturnURL + "&" + this.MD5key);
     MD5HuiChao md5 = new MD5HuiChao();
     this.orderTime = this.formatter1.format(this.timestamp);
     this.SignInfo = md5.getMD5ofStr(this.md5src);
     this.SignInfo = this.SignInfo.toUpperCase();
     System.out.println(this.md5src);
     return "success";
   }
// 宝付
   public String toBaofu()
     throws Exception
   {
     this.offlinerecharge = new Offlinerecharge();
     this.offlinerecharge.setRechargeAmount(this.chongzhiMoney);
     this.offlinerecharge.setRechargeTime(new Timestamp(new Date().getTime()));
     this.offlinerecharge.setRechargeStatus(Integer.valueOf(2));
     this.offlinerecharge.setUservip(this.uservipService.findUservipByUserid(this.userId.intValue()));
     this.offlinerecharge.setRecharge("宝付");
     this.offlinerechargeService.createOfflinerecharge(this.offlinerecharge);
 
     Date currTime = new Date();
 
     SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 
     SimpleDateFormat formatter2 = new SimpleDateFormat("yyyyMMddHHmmss");
 
     this.TradeDate = new String(formatter2.format(currTime));
     List onlinemodels = this.onlinemodelService.findOnlinemodelByAble();
     String Md5key = "";
     if ((onlinemodels != null) && (onlinemodels.size() > 0)) {
       for (int i = 0; i < onlinemodels.size(); i++) {
         Onlinemodel onlinemodel = (Onlinemodel)onlinemodels.get(i);
         if ("宝付支付".equals(onlinemodel.getPayCompany())) {
           this.MemberID = onlinemodel.getAccountNumber();
           Md5key = onlinemodel.getAccountPassword();
           this.TerminalID = onlinemodel.getOther();
         }
       }
     }
     this.TransID = this.TradeDate;
     this.OrderMoney = Double.toString(this.chongzhiMoney.doubleValue());
     if (!"".equals(this.OrderMoney))
     {
       BigDecimal a = new BigDecimal(this.OrderMoney).multiply(BigDecimal.valueOf(100L));
       this.OrderMoney = String.valueOf(a.setScale(0));
     } else {
       this.OrderMoney = "0";
     }
     
     this.request = ServletActionContext.getRequest();
     String path = request.getContextPath();
     String basePath = "";
     if(request.getServerPort()==80){
    	 basePath = request.getScheme() + "://" + request.getServerName()
    				+ path + "/";
     }else{
    	 basePath = request.getScheme() + "://" + request.getServerName()
			+ ":" + request.getServerPort() + path + "/";
     }
	 
	 
	 PageUrl = basePath+"baofoo/pageurl.jsp?offlinerechargeId="+offlinerecharge.getOfflineRechargeId();
	 ReturnUrl = basePath+"baofoo/returnurl.jsp?offlinerechargeId="+offlinerecharge.getOfflineRechargeId();
	 
	
     
     String MARK = "|";
     String md5Key = new String(this.MemberID + MARK + this.PayID + MARK + this.TradeDate + MARK + this.TransID + MARK + this.OrderMoney + MARK + this.PageUrl + MARK + this.ReturnUrl + MARK + this.NoticeType + MARK + Md5key);
     md5  md=new md5();
     this.Signature = md.getMD5ofStr(md5Key, "utf-8");
     Uservip uservip = (Uservip)ActionContext.getContext().getSession().get("uservip");
     ActionContext.getContext().getSession().put("OrderMoney"+uservip.getUserId(), this.OrderMoney);
     return "success";
   }
   public String toResultPage() throws Exception {
     this.offlinerecharge = this.offlinerechargeService.findOfflinerechargeById(this.offlineRechargeId.intValue());
     this.offlinerecharge.setCheckOrderNum(this.TransID);
     this.offlinerecharge.setRechargeAmount(this.FactMoney);
     if (this.Result.intValue() == 1) {
       this.offlinerecharge.setRechargeStatus(Integer.valueOf(1));
     }
     this.offlinerechargeService.updateOfflinerecharge(this.offlinerecharge);
     Moneycount moneycount = this.moneycountService.findMoneycountByuserId(this.userId.intValue());
     moneycount.setAvailableMoney(Double.valueOf(moneycount.getAvailableMoney().doubleValue() + this.FactMoney.doubleValue()));
     this.moneycountService.updateMoneycount(moneycount);
     return "success";
   }
 //国付宝
   public String guoFuBao() throws Exception {
     OnlinemodelService onlinemodelService = new OnlinemodelServiceImpl();
     Onlinemodel onlinemodel1 = onlinemodelService.findOnlinemodelByonlineId(4);
     this.offlinerecharge = new Offlinerecharge();
     this.offlinerecharge.setRechargeAmount(this.chongzhiMoney);
     this.offlinerecharge.setRechargeTime(new Timestamp(new Date().getTime()));
     this.offlinerecharge.setRechargeStatus(Integer.valueOf(2));
     this.offlinerecharge.setUservip(this.uservipService.findUservipByUserid(this.userId.intValue()));
     this.offlinerecharge.setRecharge("国付宝");
     this.offlinerechargeService.createOfflinerecharge(this.offlinerecharge);
//     商户id
     this.merchantID = onlinemodel1.getAccountNumber();
     this.VerficationCode = onlinemodel1.getAccountPassword();
//     交易金额
     this.tranAmt = ""+this.chongzhiMoney;
     this.feeAmt = ""+(this.chongzhiMoney*onlinemodel1.getPayPoundage());
 
     Date currTime = new Date();
 
     SimpleDateFormat formatter2 = new SimpleDateFormat("yyyyMMddHHmmss");
     this.tranDateTime = new String(formatter2.format(currTime));
     //订单号格式  20110922-00001
//     this.merOrderNum = this.tranDateTime+"-"+offlinerecharge.getOfflineRechargeId();
     this.merOrderNum =""+ offlinerecharge.getOfflineRechargeId();
     String ip = InetAddress.getLocalHost().getHostAddress();
     this.tranIP = ip;
 
//     ActionContext.getContext().put("chongzhiMoney", this.chongzhiMoney);
//     ActionContext.getContext().put("offlinerechargeId", this.offlinerecharge.getOfflineRechargeId());
//     ActionContext.getContext().put("userId", this.userId);
     
     
     this.request = ServletActionContext.getRequest();
     String path = request.getContextPath();
     String basePath = "";
     if(request.getServerPort()==80){
    	 basePath = request.getScheme() + "://" + request.getServerName()
    				+ path + "/";
     }else{
    	 basePath = request.getScheme() + "://" + request.getServerName()
			+ ":" + request.getServerPort() + path + "/";
     }
     
     this.frontMerUrl = basePath+"guofubao/true.jsp";
     this.backgroundMerUrl = basePath+"guofubao/res.jsp";
     this.gopayServerTime = GopayUtils.getGopayServerTime();

     String plain = "version=[" + this.version + "]tranCode=[" + this.tranCode +
    	"]merchantID=[" + this.merchantID + "]merOrderNum=[" + this.merOrderNum + 
    	"]tranAmt=[" + this.tranAmt + "]feeAmt=[" + this.feeAmt+ 
    	"]tranDateTime=[" + this.tranDateTime + "]frontMerUrl=[" + this.frontMerUrl + 
    	"]backgroundMerUrl=[" + this.backgroundMerUrl + "]orderId=[]gopayOutOrderId=[]tranIP=[" + 
    	this.tranIP + "]respCode=[]gopayServerTime=[" + this.gopayServerTime + "]VerficationCode=[" +this.VerficationCode + "]";

     this.signValue = GopayUtils.md5(plain);
     
 
     return "success";
   }
   
   
   public String toYiBao() throws Exception{
	   OnlinemodelService onlinemodelService = new OnlinemodelServiceImpl();
//	   3为易宝
	   Onlinemodel onlinemodel = onlinemodelService.findOnlinemodelByonlineId(3);
//	   保存数据
	     this.offlinerecharge = new Offlinerecharge();
	     this.offlinerecharge.setRechargeAmount(this.chongzhiMoney);
	     this.offlinerecharge.setRechargeTime(new Timestamp(new Date().getTime()));
	     this.offlinerecharge.setRechargeStatus(Integer.valueOf(2));
	     this.offlinerecharge.setUservip(this.uservipService.findUservipByUserid(this.userId.intValue()));
	     this.offlinerecharge.setRecharge("易宝");
	     this.offlinerechargeService.createOfflinerecharge(this.offlinerecharge);
//	     获取网站根目录
	     this.request = ServletActionContext.getRequest();
	     String path = request.getContextPath();
	     String basePath = "";
	     if(request.getServerPort()==80){
	    	 basePath = request.getScheme() + "://" + request.getServerName()
	    				+ path + "/";
	     }else{
	    	 basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";
	     }
		  
	     
	     
	  
		String keyValue=  onlinemodel.getAccountPassword();				// 商家密钥
		String nodeAuthorizationURL= "https://www.yeepay.com/app-merchant-proxy/node";  	// 交易请求地址
		// 商家设置用户购买商品的支付信息
		String    p0_Cmd= "Buy";                               									// 在线支付请求，固定值 ”Buy”
		String    p1_MerId= onlinemodel.getAccountNumber();			// 商户编号
		String    p2_Order= ""+offlinerecharge.getOfflineRechargeId();           					// 商户订单号
		String	  p3_Amt= ""+this.chongzhiMoney;      	   							// 支付金额
		String	  p4_Cur= "CNY";	   		   							// 交易币种
		String	  p5_Pid= "P2P在线充值";	       	   						// 商品名称
		String	  p6_Pcat= "";	       	   					// 商品种类
		String 	  p7_Pdesc= "";		   								// 商品描述
		String 	  p8_Url= basePath+"yibao/callback.jsp"; 		       						// 商户接收支付成功数据的地址
		String 	  p9_SAF= "0"; 			   							// 需要填写送货信息 0：不需要  1:需要
		String 	  pa_MP= "";         	   						// 商户扩展信息
		String    pd_FrpId="";       	   					// 支付通道编码
		// 银行编号必须大写
		pd_FrpId = pd_FrpId.toUpperCase();
		String 	  pr_NeedResponse= "1";    // 默认为"1"，需要应答机制
	  String 	  hmac= "";							               							// 交易签名串
	  MD5YiBao md5=new MD5YiBao(); 
	    // 获得MD5-HMAC签名
	    hmac = md5.getReqMd5HmacForOnlinePayment(p0_Cmd,
				p1_MerId,p2_Order,p3_Amt,p4_Cur,p5_Pid,p6_Pcat,p7_Pdesc,
				p8_Url,p9_SAF,pa_MP,pd_FrpId,pr_NeedResponse,keyValue);
	   
	    request.setAttribute("p0_Cmd", p0_Cmd);
	    request.setAttribute("p1_MerId",p1_MerId );
	    request.setAttribute("p2_Order",p2_Order );
	    request.setAttribute("p3_Amt", p3_Amt);
	    request.setAttribute("p4_Cur",p4_Cur );
	    request.setAttribute("p5_Pid",p5_Pid );
	    request.setAttribute("p6_Pcat",p6_Pcat );
	    request.setAttribute("p7_Pdesc",p7_Pdesc );
	    request.setAttribute("p8_Url",p8_Url );
	    request.setAttribute("p9_SAF",p9_SAF );
	    request.setAttribute("pa_MP",pa_MP );
	    request.setAttribute("pd_FrpId",pd_FrpId );
	    request.setAttribute("pr_NeedResponse",pr_NeedResponse );
	    request.setAttribute("keyValue",keyValue );
	   
	   return "success";
   }
   
   /**
    * 易通支付处理
    * @return
    * @throws Exception
    */
   public String easypay() throws Exception {
	     OnlinemodelService onlinemodelService = new OnlinemodelServiceImpl();
	     Onlinemodel onlinemodel1 = onlinemodelService.findOnlinemodelByonlineId(6);
	     Uservip uservip = this.uservipService.findUservipByUserid(this.userId.intValue());
	     this.offlinerecharge = new Offlinerecharge();
	     this.offlinerecharge.setRechargeAmount(this.chongzhiMoney);
	     this.offlinerecharge.setRechargeTime(new Timestamp(new Date().getTime()));
	     this.offlinerecharge.setRechargeStatus(Integer.valueOf(2));
	     this.offlinerecharge.setUservip(uservip);
	     this.offlinerecharge.setRecharge("易通支付");
	     this.offlinerechargeService.createOfflinerecharge(this.offlinerecharge);
	     
	     this.request = ServletActionContext.getRequest();
	     String path = request.getContextPath();
	     String basePath = "";
	     if(request.getServerPort()==80){
	    	 basePath = request.getScheme() + "://" + request.getServerName()
	    				+ path + "/";
	     }else{
	    	 basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";
	     }
	     
	     
	     
	     request.setCharacterEncoding("UTF-8"); 
	     
	     Date currTime = new Date();
	     SimpleDateFormat formatter2 = new SimpleDateFormat("yyyyMMddHHmmss");
	     this.tranDateTime = new String(formatter2.format(currTime));

	 	String datakey=onlinemodel1.getAccountPassword();
	 	String version="1.0.0";
	 	String transCode="8888";//交易代码
	 	String merchantId=onlinemodel1.getAccountNumber();
	 	String merOrderNum="FC"+tranDateTime+offlinerecharge.getOfflineRechargeId();//商户订单号
	 	String bussId="";//业务代码
//	 	if(1==uservip.getIsEnterprise()){
//	 		bussId=onlinemodel1.getBussIdB2B();
//	 	}else{
	 		bussId=onlinemodel1.getOther();
//	 	}
	 	Double money = this.chongzhiMoney*100;
	 	String tranAmt= ""+Integer.valueOf(money.intValue());//交易金额(单位： 分)
	 	System.err.println(tranAmt);
	 	String sysTraceNum=""+offlinerecharge.getOfflineRechargeId();//商户请求流水号
	 	String tranDateTime=this.tranDateTime;//交易时间
	 	String merURL=basePath+"easypay/res.jsp";//商户返回页面
	 	String backURL=basePath+"easypay/res.jsp";//回调商户地址
	 	System.err.println(basePath);
	 	String orderInfo = "";
	 	orderInfo = HexUtils.toHex(orderInfo.getBytes("UTF-8"));
	 	String userId="";//用户ID
	 	String userIp="";//订单用户IP
	 	String bankId="";//银行代码
	 	String stlmId="";//结算规则代码
	 	String currencyType="156";//贷币代码
	 	String reserver1="";
	 	String reserver2="";
	 	String reserver3="";//商户拓展业务字段，在v2.2.0版本之后才使用到的，用于开通分账等业务
	 	String reserver4="";
	 	String txnString=version+"|"+tranCode+"|"+merchantId+"|"+merOrderNum+"|"+bussId
			 			+"|"+tranAmt+"|"+sysTraceNum+"|"+tranDateTime+"|"+currencyType+"|"+
			 			merURL+"|"+backURL+"|"+orderInfo+"|"+userId;
	 	System.err.println(txnString);
	 	System.err.println(txnString+datakey);
//	 	MD5EasyPay md5 = new MD5EasyPay();
	 	String signValue=MD5.instance.getMD5ofStr(txnString+datakey);
	 	System.err.println(signValue);
	 	request.setAttribute("version",version );
	    request.setAttribute("transCode",transCode );
	    request.setAttribute("merchantId",merchantId );
	    request.setAttribute("merOrderNum", merOrderNum);
	    request.setAttribute("bussId",bussId );
	    request.setAttribute("tranAmt",tranAmt );
	    request.setAttribute("sysTraceNum",sysTraceNum );
	    request.setAttribute("tranDateTime",tranDateTime );
	    request.setAttribute("merURL",merURL );
	    request.setAttribute("backURL",backURL );
	    request.setAttribute("orderInfo",orderInfo );
	    request.setAttribute("userId",userId );
	    request.setAttribute("userIp",userIp );
	    request.setAttribute("bankId",bankId );
	    request.setAttribute("stlmId",stlmId );
	    request.setAttribute("currencyType",currencyType );
	    request.setAttribute("reserver1",reserver1 );
	    request.setAttribute("reserver2",reserver2 );
	    request.setAttribute("reserver3",reserver3 );
	    request.setAttribute("reserver4",reserver4 );
	    request.setAttribute("signValue",signValue );
	     return "success";
	   }
   
   public Double getChongzhiMoney() {
     return this.chongzhiMoney;
   }
   public String getInterfaceVersion() {
     return this.InterfaceVersion;
   }
   public void setInterfaceVersion(String interfaceVersion) {
     this.InterfaceVersion = interfaceVersion;
   }
   public String getTradeDate() {
     return this.TradeDate;
   }
   public void setTradeDate(String tradeDate) {
     this.TradeDate = tradeDate;
   }
   public String getMemberID() {
     return this.MemberID;
   }
   public void setMemberID(String memberID) {
     this.MemberID = memberID;
   }
   public String getTerminalID() {
     return this.TerminalID;
   }
   public void setTerminalID(String terminalID) {
     this.TerminalID = terminalID;
   }
   public String getPayID() {
     return this.PayID;
   }
   public void setPayID(String payID) {
     this.PayID = payID;
   }
   public String getOrderMoney() {
     return this.OrderMoney;
   }
   public void setOrderMoney(String orderMoney) {
     this.OrderMoney = orderMoney;
   }
   public String getPageUrl() {
     return this.PageUrl;
   }
   public void setPageUrl(String pageUrl) {
     this.PageUrl = pageUrl;
   }
   public String getReturnUrl() {
     return this.ReturnUrl;
   }
   public void setReturnUrl(String returnUrl) {
     this.ReturnUrl = returnUrl;
   }
   public String getKeyType() {
     return this.KeyType;
   }
   public void setKeyType(String keyType) {
     this.KeyType = keyType;
   }
   public String getSignature() {
     return this.Signature;
   }
   public void setSignature(String signature) {
     this.Signature = signature;
   }
   public String getNoticeType() {
     return this.NoticeType;
   }
   public void setNoticeType(String noticeType) {
     this.NoticeType = noticeType;
   }
   public String getPayUrl() {
     return this.payUrl;
   }
   public void setPayUrl(String payUrl) {
     this.payUrl = payUrl;
   }
   public Double getYeMoney() {
     return this.yeMoney;
   }
   public void setYeMoney(Double yeMoney) {
     this.yeMoney = yeMoney;
   }
   public void setChongzhiMoney(Double chongzhiMoney) {
     this.chongzhiMoney = chongzhiMoney;
   }
   public Integer getOfflineRechargeId() {
     return this.offlineRechargeId;
   }
   public void setOfflineRechargeId(Integer offlineRechargeId) {
     this.offlineRechargeId = offlineRechargeId;
   }
   public String getTransID() {
     return this.TransID;
   }
   public void setTransID(String transID) {
     this.TransID = transID;
   }
 
   public Double getFactMoney() {
     return this.FactMoney;
   }
   public void setFactMoney(Double factMoney) {
     this.FactMoney = factMoney;
   }
 
   public String getSignValue()
   {
     return this.signValue;
   }
   public void setSignValue(String signValue) {
     this.signValue = signValue;
   }
   public String getFasleMoney() {
     return this.fasleMoney;
   }
   public void setFasleMoney(String fasleMoney) {
     this.fasleMoney = fasleMoney;
   }
   public String getOtherMoney() {
     return this.otherMoney;
   }
   public void setOtherMoney(String otherMoney) {
     this.otherMoney = otherMoney;
   }
   public Integer getUserId() {
     return this.userId;
   }
   public void setUserId(Integer userId) {
     this.userId = userId;
   }
   public Offlinerecharge getOfflinerecharge() {
     return this.offlinerecharge;
   }
 
   public void setOfflinerecharge(Offlinerecharge offlinerecharge) {
     this.offlinerecharge = offlinerecharge;
   }
 
   public List<Offlinerecharge> getOfflinerechargeList() {
     return this.offlinerechargeList;
   }
 
   public void setOfflinerechargeList(List<Offlinerecharge> offlinerechargeList) {
     this.offlinerechargeList = offlinerechargeList;
   }
 
   public List<Companybankinfor> getCompanybankinforList() {
     return this.companybankinforList;
   }
 
   public void setCompanybankinforList(List<Companybankinfor> companybankinforList) {
     this.companybankinforList = companybankinforList;
   }
 
   public Integer getMark() {
     return this.mark;
   }
 
   public void setMark(Integer mark) {
     this.mark = mark;
   }
   public Integer getCompanyBankInforId() {
     return this.companyBankInforId;
   }
   public void setCompanyBankInforId(Integer companyBankInforId) {
     this.companyBankInforId = companyBankInforId;
   }
   public List<Onlinemodel> getOnlinemodelList() {
     return this.onlinemodelList;
   }
   public void setOnlinemodelList(List<Onlinemodel> onlinemodelList) {
     this.onlinemodelList = onlinemodelList;
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
 
   public File getFile() {
     return this.file;
   }
   public void setFile(File file) {
     this.file = file;
   }
   public String getFileFileName() {
     return this.fileFileName;
   }
   public void setFileFileName(String fileFileName) {
     this.fileFileName = fileFileName;
   }
   public Integer getResult() {
     return this.Result;
   }
   public void setResult(Integer result) {
     this.Result = result;
   }
 
   public String getSuccessMoney() {
     return this.successMoney;
   }
   public void setSuccessMoney(String successMoney) {
     this.successMoney = successMoney;
   }
 
   public void setServletResponse(HttpServletResponse response)
   {
     this.response = response;
   }
 
   public void setServletRequest(HttpServletRequest request)
   {
     this.request = request;
   }
 
   public String getOrderTime()
   {
     return this.orderTime;
   }
   public void setOrderTime(String orderTime) {
     this.orderTime = orderTime;
   }
 
   public String getMerNo() {
     return this.MerNo;
   }
   public void setMerNo(String merNo) {
     this.MerNo = merNo;
   }
   public String getMD5key() {
     return this.MD5key;
   }
   public void setMD5key(String mD5key) {
     this.MD5key = mD5key;
   }
   public String getReturnURL() {
     return this.ReturnURL;
   }
   public void setReturnURL(String returnURL) {
     this.ReturnURL = returnURL;
   }
   public String getTradeAdd() {
     return this.tradeAdd;
   }
   public void setTradeAdd(String tradeAdd) {
     this.tradeAdd = tradeAdd;
   }
   public String getMd5src() {
     return this.md5src;
   }
   public void setMd5src(String md5src) {
     this.md5src = md5src;
   }
   public String getSignInfo() {
     return this.SignInfo;
   }
   public void setSignInfo(String signInfo) {
     this.SignInfo = signInfo;
   }
   public String getAdviceURL() {
     return this.AdviceURL;
   }
   public void setAdviceURL(String adviceURL) {
     this.AdviceURL = adviceURL;
   }
   public String getDefaultBankNumber() {
     return this.defaultBankNumber;
   }
   public void setDefaultBankNumber(String defaultBankNumber) {
     this.defaultBankNumber = defaultBankNumber;
   }
   public String getProducts() {
     return this.products;
   }
   public void setProducts(String products) {
     this.products = products;
   }
   public String getMessage() {
     return this.message;
   }
   public void setMessage(String message) {
     this.message = message;
   }
   public String getUrlStr() {
     return this.urlStr;
   }
   public void setUrlStr(String urlStr) {
     this.urlStr = urlStr;
   }
   public String getVersion() {
     return this.version;
   }
   public void setVersion(String version) {
     this.version = version;
   }
   public String getCharset() {
     return this.charset;
   }
   public void setCharset(String charset) {
     this.charset = charset;
   }
   public String getLanguage() {
     return this.language;
   }
   public void setLanguage(String language) {
     this.language = language;
   }
   public String getSignType() {
     return this.signType;
   }
   public void setSignType(String signType) {
     this.signType = signType;
   }
   public String getTranCode() {
     return this.tranCode;
   }
   public void setTranCode(String tranCode) {
     this.tranCode = tranCode;
   }
   public String getMerchantID() {
     return this.merchantID;
   }
   public void setMerchantID(String merchantID) {
     this.merchantID = merchantID;
   }
   public String getMerOrderNum() {
     return this.merOrderNum;
   }
   public void setMerOrderNum(String merOrderNum) {
     this.merOrderNum = merOrderNum;
   }
   public String getTranAmt() {
     return this.tranAmt;
   }
   public void setTranAmt(String tranAmt) {
     this.tranAmt = tranAmt;
   }
   public String getFeeAmt() {
     return this.feeAmt;
   }
   public void setFeeAmt(String feeAmt) {
     this.feeAmt = feeAmt;
   }
   public String getCurrencyType() {
     return this.currencyType;
   }
   public void setCurrencyType(String currencyType) {
     this.currencyType = currencyType;
   }
   public String getFrontMerUrl() {
     return this.frontMerUrl;
   }
   public void setFrontMerUrl(String frontMerUrl) {
     this.frontMerUrl = frontMerUrl;
   }
   public String getBackgroundMerUrl() {
     return this.backgroundMerUrl;
   }
   public void setBackgroundMerUrl(String backgroundMerUrl) {
     this.backgroundMerUrl = backgroundMerUrl;
   }
   public String getTranDateTime() {
     return this.tranDateTime;
   }
   public void setTranDateTime(String tranDateTime) {
     this.tranDateTime = tranDateTime;
   }
   public String getVirCardNoIn() {
     return this.virCardNoIn;
   }
   public void setVirCardNoIn(String virCardNoIn) {
     this.virCardNoIn = virCardNoIn;
   }
   public String getTranIP() {
     return this.tranIP;
   }
   public void setTranIP(String tranIP) {
     this.tranIP = tranIP;
   }
   public String getIsRepeatSubmit() {
     return this.isRepeatSubmit;
   }
   public void setIsRepeatSubmit(String isRepeatSubmit) {
     this.isRepeatSubmit = isRepeatSubmit;
   }
   public String getGoodsName() {
     return this.goodsName;
   }
   public void setGoodsName(String goodsName) {
     this.goodsName = goodsName;
   }
   public String getGoodsDetail() {
     return this.goodsDetail;
   }
   public void setGoodsDetail(String goodsDetail) {
     this.goodsDetail = goodsDetail;
   }
   public String getBuyerName() {
     return this.buyerName;
   }
   public void setBuyerName(String buyerName) {
     this.buyerName = buyerName;
   }
   public String getBuyerContact() {
     return this.buyerContact;
   }
   public void setBuyerContact(String buyerContact) {
     this.buyerContact = buyerContact;
   }
   public String getMerRemark1() {
     return this.merRemark1;
   }
   public void setMerRemark1(String merRemark1) {
     this.merRemark1 = merRemark1;
   }
   public String getMerRemark2() {
     return this.merRemark2;
   }
   public void setMerRemark2(String merRemark2) {
     this.merRemark2 = merRemark2;
   }
   public String getBankCode() {
     return this.bankCode;
   }
   public void setBankCode(String bankCode) {
     this.bankCode = bankCode;
   }
   public String getUserType() {
     return this.userType;
   }
   public void setUserType(String userType) {
     this.userType = userType;
   }
   public String getVerficationCode() {
     return this.VerficationCode;
   }
   public void setVerficationCode(String verficationCode) {
     this.VerficationCode = verficationCode;
   }
   public String getGopayServerTime() {
     return this.gopayServerTime;
   }
   public void setGopayServerTime(String gopayServerTime) {
     this.gopayServerTime = gopayServerTime;
   }
	public String getPostform() {
		return postform;
	}
	public void setPostform(String postform) {
		this.postform = postform;
	}
   
 }

