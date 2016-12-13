package com.jqg.app;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import cn.emay.mina.filter.reqres.Request;

import com.jqg.pojo.Basicinfor;
import com.jqg.pojo.Bonus;
import com.jqg.pojo.Inbox;
import com.jqg.pojo.Internallettermodel;
import com.jqg.pojo.Messagemodel;
import com.jqg.pojo.Moneycount;
import com.jqg.pojo.Phone;
import com.jqg.pojo.Promrewardbypeople;
import com.jqg.pojo.SmsSendLog;
import com.jqg.pojo.Systemconf;
import com.jqg.pojo.Uservip;
import com.jqg.pojo.Website;
import com.jqg.service.BasicinforService;
import com.jqg.service.BonusService;
import com.jqg.service.CreditlevelService;
import com.jqg.service.InboxService;
import com.jqg.service.InternallettermodelService;
import com.jqg.service.MessagemodelService;
import com.jqg.service.MoneycountService;
import com.jqg.service.PhoneService;
import com.jqg.service.PromrewardbypeopleService;
import com.jqg.service.SmsSendLogService;
import com.jqg.service.SystemconfService;
import com.jqg.service.UservipService;
import com.jqg.service.WebsiteService;
import com.jqg.service.impl.BasicinforServiceImpl;
import com.jqg.service.impl.BonusServiceImpl;
import com.jqg.service.impl.CreditlevelServiceImpl;
import com.jqg.service.impl.InboxServiceImpl;
import com.jqg.service.impl.InternallettermodelServiceImpl;
import com.jqg.service.impl.MessagemodelServiceImpl;
import com.jqg.service.impl.MoneycountServiceImpl;
import com.jqg.service.impl.PhoneServiceImpl;
import com.jqg.service.impl.PromrewardbypeopleServiceImpl;
import com.jqg.service.impl.SmsSendLogServiceImpl;
import com.jqg.service.impl.SystemconfServiceImpl;
import com.jqg.service.impl.UservipServiceImpl;
import com.jqg.service.impl.WebsiteServiceImpl;
import com.jqg.util.Client;
import com.jqg.util.MD5Util;
import com.opensymphony.xwork2.ActionContext;
import com.ruanwei.interfacej.SmsClientSend;

public class RegisterAction extends AppBaseAction {
	   private static final long serialVersionUID = 1L;
	   private HttpServletRequest request;//声明request
	   private HttpServletResponse response;//声明response
	   
	   private PhoneService phoneService=new PhoneServiceImpl();
	   private MessagemodelService messagemodelService=new MessagemodelServiceImpl();
	   private UservipService uservipservice=new UservipServiceImpl();
	   private PromrewardbypeopleService promrewardbypeopleService = new PromrewardbypeopleServiceImpl();
	   private BonusService bonusService = new BonusServiceImpl();
	   private CreditlevelService creditlevelService = new CreditlevelServiceImpl();
	   private MoneycountService moneycountService = new MoneycountServiceImpl();
	   private SystemconfService systemconfService = new SystemconfServiceImpl();
	   private BasicinforService basicinforService = new BasicinforServiceImpl();
	   private InternallettermodelService internallettermodelService = new InternallettermodelServiceImpl();
	   private WebsiteService websiteService = new WebsiteServiceImpl();
	   private InboxService inboxService = new InboxServiceImpl();
	   private Moneycount moneycount;
	   /**
		 * 用户注册
		 * @return
		 * @throws Exception
		 */
	   public void register()throws Exception
	   {
		   request=ServletActionContext.getRequest();
		   response=ServletActionContext.getResponse();
		   //获取用户名密码
		   String username=request.getParameter("username");
		   String password=request.getParameter("password");
		   String state="";//声明一个
		   Integer referId=0;//推荐人id
		   String phoneNum="";
		   Integer userId=0;
		   // 用户名唯一性验证 
		   String uservips= uservipservice.findUservipByUserName(username);
			if(uservips!=null && uservips!="0"){
				state ="1";//用户名已存在
			}
			else{
				//保存用户信息
				Uservip uservip = new Uservip();
				uservip.setUserName(username);
				uservip.setPassword(password);
				uservip.setPayPwd(password);
//			//判断是否有推荐人
//			if(referId != null) {
//					Uservip refer = uservipservice.findUservipByUserid(referId
//							.intValue());
//					uservip.setUservip(refer);
//					if (referId != null) {
//						String sql = "select * from Promrewardbypeople p";
//						System.out.println(sql);
//						List promrewardbypeopleList = promrewardbypeopleService
//								.findPromrewardbySql(sql);
//						Promrewardbypeople pp = new Promrewardbypeople();
//						if ((promrewardbypeopleList != null)
//								&& (promrewardbypeopleList.size() > 0)) {
//							pp = (Promrewardbypeople) promrewardbypeopleList.get(0);
//
//							sql = "select * from bonus b where b.userId="
//									+ referId
//									+ " and timestampdiff(DAY,DATE_FORMAT(b.bonusChangeTime,'%Y-%m-%d'),DATE_FORMAT(NOW(),'%Y-%m-%d')) >=0"
//									+ " and timestampdiff(DAY,DATE_FORMAT(NOW(),'%Y-%m-%d'),DATE_FORMAT(b.bonusChangeTime,'%Y-%m-%d')) >=0";
//							List bonusList = bonusService.findBonusBySql(sql);
//							System.out.println(sql);
//							Bonus bonus = new Bonus();
//							// �ƹ㽱��
//							if ((bonusList != null) && (bonusList.size() > 0)) {
//								bonus = (Bonus) bonusList.get(0);
//								bonus.setNum(Integer
//										.valueOf(bonus.getNum().intValue() + 1));
//								bonus.setBonusChangeTime(new Timestamp(new Date()
//										.getTime()));
//							} else {
//								bonus.setNum(Integer.valueOf(1));
//								bonus.setUservip(uservipservice
//										.findUservipByUserid(referId.intValue()));
//								bonus.setStatus("0");
//								bonus.setBonusChangeTime(new Timestamp(new Date()
//										.getTime()));
//								bonus.setBonusExamine("�ƹ㽱��");
//							}
//							for (int i = 0; i < promrewardbypeopleList.size(); i++) {
//								pp = (Promrewardbypeople) promrewardbypeopleList.get(i);
//								if (bonus.getNum() == pp.getPeopleNum()) {
//									String a = Double.toString(pp.getReward()
//											.doubleValue());
//									bonus.setRquestBonus(Integer.valueOf(Integer
//											.parseInt(a.substring(0, a.indexOf(".")))));
//								}
//							}
//							if (bonus.getBonusId() != null) {
//								bonusService.updateBonus(bonus);
//							} else {
//								bonusService.createBonus(bonus);
//							}
//						}
//					}
//				}
				uservip.setRegisterDate(new Timestamp(new Date().getTime()));
				uservip.setCreditlevel(creditlevelService
						.findCreditlevelBycreditLevelId(1));
				userId = uservipservice.createUservip(uservip);

				if (userId != null) {
					uservip.setUserId(userId);
					ActionContext.getContext().getSession().put("uservip", uservip);
					Moneycount mc = moneycountService.findMoneycountByuserId(userId.intValue());
					
					if (mc == null) {
						Moneycount moneycount1 = new Moneycount();
						moneycount1.setUservip(uservip);
						Date timedate = new Date();
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Systemconf syc = systemconfService.findSystemconfByParname("con_reg_reward_limittime");
						Date date2 = df.parse(syc.getParvalue());
						if (timedate.getTime() <= date2.getTime()) {
							Systemconf sycf = systemconfService.findSystemconfByParname("con_reg_reward");
							double experienceMoney = Double.parseDouble(sycf.getParvalue());
							moneycount1.setExperienceMoney(experienceMoney);
						}
						moneycountService.createMoneycount(moneycount1);
						moneycount = moneycountService
								.findMoneycountByuserId(userId.intValue());
					} else {
						moneycount = mc;
					}

					Basicinfor basicinfor1 = new Basicinfor();
					basicinfor1.setUservip(uservip);
					basicinfor1.setSex("0");
					// //判断邮箱
					// if("1".equals(flg)){
					// basicinfor1.setPhoneNum(moblie);
					// }
					basicinfor1.setPhoneNum(phoneNum);
					basicinfor1.setMarryStatus("0");
					basicinfor1.setHighestEdu("0");
					basicinfor1.setMonthIncome("0");
					basicinfor1.setRelationOne("0");
					basicinfor1.setRelationTwo("0");
					basicinfor1.setRelationThree("0");
					basicinfor1.setWorkYear("0");
					basicinfor1.setHouseCondition("0");
					basicinfor1.setIfByCar("0");
					basicinfor1.setContributionStatus("0");
					basicinfor1.setCosignerOneRelation("0");
					basicinfor1.setCosignerTwoRelation("0");
					basicinfor1.setCount1(Integer.valueOf(0));
					basicinfor1.setCount2(Integer.valueOf(0));
					basicinfor1.setCount3(Integer.valueOf(0));
					basicinfor1.setCount4(Integer.valueOf(0));
					basicinfor1.setCount5(Integer.valueOf(0));
					basicinfor1.setCount6(Integer.valueOf(0));
					basicinforService.createBasicinfor(basicinfor1);

					Internallettermodel internallettermodel = internallettermodelService
							.findInternallettermodelByinterModelId(1);
					if (internallettermodel.getIsOpen() == 1) {
						Inbox inbox = new Inbox();
						inbox.setUservip(uservip);
						inbox.setContent(internallettermodel.getInterModelContent()
								.replace("'#UserName#'", uservip.getUserName()));
						inbox.setReceiveTime(new Timestamp(new Date().getTime()));
						Website website = websiteService.findWebsiteBywebsiteId(1);
						inbox.setSendName(website.getWebName());
						inbox.setTitle("注册成功");
						state="2";//ע��ɹ�
						inbox.setStatus(Integer.valueOf(0));
						inboxService.createInbox(inbox);
					}
				}
			}
				/**
				 * 返回数据
				 */
			 StringBuffer str=new StringBuffer();
		     str.append("{\"register_state\":\""+state+"\",");//返回注册状态
		     str.append("\"userId\":\""+userId+"\",");
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
	    * 发送验证码
	    */
	   public void captcha() throws Exception{
		   request=ServletActionContext.getRequest();
		   response=ServletActionContext.getResponse();
		   
		   String sessionId = request.getSession().getId();
		   String phoneNumber=request.getParameter("phone_number");
		   Phone phone = phoneService.findPhoneByOpen();
	       String state="";
	       String code = "";
		   Messagemodel messagemodel = messagemodelService.findMessagemodelByintegralPid(9);
		   if(phoneNumber!=null && !"".equals(phoneNumber)){
			   Basicinfor basi=this.basicinforService.findBasicinforByPhone(phoneNumber);
			   if(basi!=null){
				   state = "3";//手机号已存在
			   }else{
				   
			   String contents = messagemodel.getMessModelContent();
			   if (contents.indexOf("#USERNAME#") != -1) {
					contents = contents.replaceAll("#USERNAME#",
							"");
				}
				if (contents.indexOf("#CODE#") != -1) {
					// 生成验证码
					while (code.length() < 6) {
						code = code + (int) (Math.random() * 10.0D);
					}
					contents = contents.replaceAll("#CODE#", code);
					ActionContext.getContext().getSession().put("code" + sessionId, code);
					System.err.println("pwdCode:" + code);
				}
				if (phone.getPhoneId().intValue() == 1) {
					Client client = new Client(phone.getPhoneName(),
							phone.getPhonePassword());
					String result_mt = client.mdSmsSend_u(phoneNumber,contents, "", "", "");
					if ((result_mt.startsWith("-")) || ("".equals(result_mt))) {
						System.out.print("发送失败" + result_mt+ "webservice");
						state = "0";//
					} else {
						System.out.print("发送成功" + result_mt);
						state = "1";
					}
				}
				if (phone.getPhoneId().intValue() == 2) {
					String url = "http://115.29.242.32:8888/sms.aspx";
					String sendSms =SmsClientSend.sendSms(url, phone.getPhoneuserId(), phone.getPhoneName(), phone.getPhonePassword(), phoneNumber, contents);
					if (sendSms.indexOf("Faild") != -1) {
						state = "0";//失败
					}else{  
						state = "1";//成功
					}
				}
				
					SmsSendLog ssl = new SmsSendLog();
					ssl.setContent(contents);
					ssl.setSendTime(new Timestamp(new Date().getTime()));
					ssl.setTophone(phoneNumber);
					ssl.setTitle("");
					ssl.setStatus(1);
					SmsSendLogService sslService = new SmsSendLogServiceImpl();
					sslService.createSmsSendLog(ssl);
			   }
		   }else{
			   state = "2";//手机号为空
		   }   
			
					StringBuffer str = new StringBuffer();
					str.append("{\"state\":\"" + state + "\",");//返回发送状态״̬
					str.append("\"captcha\":\"" + code + "\",");//验证码
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
	    * 手机验证 保存数据库
	    * @throws Exception
	    */
	   public void save()throws Exception{
		   request=ServletActionContext.getRequest();
		   response=ServletActionContext.getResponse();
		   String state="";
		   String sessionId = request.getSession().getId();
		   String code = (String) ActionContext.getContext().getSession().get("code" +sessionId);
		   String phone_number=request.getParameter("phone_number");
		   String captcha=request.getParameter("captcha");
			// 验证手机号
			Basicinfor basicinfor =new Basicinfor();
			basicinfor= this.basicinforService.findBasicinforByPhone(phone_number);
			Integer userId=Integer.parseInt(request.getParameter("userId"));
			if(code!=null && !"".equals(code)){
				if(phone_number!=null && captcha!=null &&!"".equals(phone_number) && !"".equals(captcha)){
					if(code.equals(captcha)){
						if (basicinfor!=null) {
							state ="1";// 手机号码已存在
						}else{
							Uservip uservip=uservipservice.findUservipByUserid(userId);
							uservip.setPhoneResult("1");
							boolean boo2=uservipservice.updateUservip(uservip);
							Basicinfor basi=this.basicinforService.findBasicinforByUserId(userId);
							basi.setPhoneNum(phone_number);
							boolean boo=basicinforService.updateBasicinfor(basi);
							if(boo && boo2){
								state="3";//保存成功
							}else{
								state="5";//保存失败
							}
						}
					}else{
						
						state="2";//验证码错误 
					}
					
				}else{
					state ="6";// 手机号或验证码不能为空
				}
			}else{
				state ="4";// 请生成手机验证码
			}
			 StringBuffer str=new StringBuffer();
		     str.append("{\"phone_state\":\""+state+"\",");//�ֻ����֤״̬
		     str.deleteCharAt(str.lastIndexOf(","));
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
	public Moneycount getMoneycount() {
		return moneycount;
	}
	public void setMoneycount(Moneycount moneycount) {
		this.moneycount = moneycount;
	}

}
