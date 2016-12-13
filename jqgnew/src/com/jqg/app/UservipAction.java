package com.jqg.app;

import java.io.IOException;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import sun.misc.BASE64Encoder;

import com.jqg.pojo.AppPhotos;
import com.jqg.pojo.Basicinfor;
import com.jqg.pojo.Messagemodel;
import com.jqg.pojo.Phone;
import com.jqg.pojo.SmsSendLog;
import com.jqg.pojo.UserLog;
import com.jqg.pojo.Uservip;
import com.jqg.service.AppPhotosService;
import com.jqg.service.BasicinforService;
import com.jqg.service.MessagemodelService;
import com.jqg.service.PhoneService;
import com.jqg.service.SmsSendLogService;
import com.jqg.service.UserLogService;
import com.jqg.service.UservipService;
import com.jqg.service.impl.AppPhotosServiceImpl;
import com.jqg.service.impl.BasicinforServiceImpl;
import com.jqg.service.impl.MessagemodelServiceImpl;
import com.jqg.service.impl.PhoneServiceImpl;
import com.jqg.service.impl.SmsSendLogServiceImpl;
import com.jqg.service.impl.UserLogServiceImpl;
import com.jqg.service.impl.UservipServiceImpl;
import com.jqg.util.Client;
import com.jqg.util.MD5Util;
import com.opensymphony.xwork2.ActionContext;
import com.ruanwei.interfacej.SmsClientSend;

public class UservipAction extends AppBaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2227771525185561501L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private PhoneService phoneService = new PhoneServiceImpl();
	private UservipService uservipService = new UservipServiceImpl();
	private MessagemodelService messagemodelService = new MessagemodelServiceImpl();
	private BasicinforService basicinforService = new BasicinforServiceImpl();
	private UservipService userService = new UservipServiceImpl();
	private UserLogService userLogService=new UserLogServiceImpl();
	private String sessionId;
	/**
	 * 用户登陆
	 * */
	public void login() throws Exception {
		this.request = ServletActionContext.getRequest();
		this.response = ServletActionContext.getResponse();
		String ip = request.getHeader("X-Forwarded-For"); 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("Proxy-Client-IP"); 
        } 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("WL-Proxy-Client-IP"); 
        } 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("HTTP_CLIENT_IP"); 
        } 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("HTTP_X_FORWARDED_FOR"); 
        } 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getRemoteAddr(); 
        } 
		sessionId = request.getSession().getId();
		String state;
		String key=null;
		Uservip uservip=null;
		String password = request.getParameter("password");//登陆密码
		String userName = request.getParameter("userName");//用户名
		if ((password == null) || (password.equals("")) || (userName == null)|| (userName.equals(""))) {
			state = "0";//用户名为空返回失败
		} else {
			 uservip = this.uservipService
					.findUservipByPasswordAndUserName(password, userName);
			if (uservip != null) {
				state = "1";//登陆成功
				MessageDigest md = MessageDigest.getInstance("SHA");
				BASE64Encoder base = new BASE64Encoder();
				String pwdAfter = base.encode(md.digest((uservip.getUserName()+"+"+uservip.getPassword()).getBytes()));
				key = MD5Util.md5(pwdAfter);
				ActionContext.getContext().getSession().put("key",key);
				ActionContext.getContext().getSession().put("uservip"+uservip.getUserId(), uservip);
//				myc.AddSession(request.getSession());
				
				UserLog userLog=new UserLog();
				userLog.setLoginTime(new Timestamp(new Date().getTime()));
				userLog.setUservip(uservip);
				userLog.setLoginIp(ip);
				userLog.setDetail("app登录成功");
				
				userLogService.addUserLog(userLog);
				
			} else {
				state = "0";//登陆失败
			}
		}
		StringBuffer str = new StringBuffer();
		str.append("{\"login_state\":\"" + state + "\",");
		str.append("\"key\":\"" + key + "\",");
		if(uservip!=null && !"".equals(uservip)){
			str.append("\"uservip\":\"" + "uservip"+uservip.getUserId() + "\",");
		}else{
			str.append("\"uservip\":\"" + "user用户不存在" + "\",");
		}
		str.append("\"sessionId\":\"" + sessionId + "\",");
		str.deleteCharAt(str.lastIndexOf(","));
		str.append("}");
		response.setCharacterEncoding("utf-8");
		try {
			response.getWriter().print(str);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/**
	 * 密码找回时发送验证码
	 * */
	public void sendCode() throws Exception{
		this.request = ServletActionContext.getRequest();
		this.response = ServletActionContext.getResponse();
		String phone1 = request.getParameter("phone");//手机号
		Basicinfor basicinfor = this.basicinforService.findBasicinforByPhone(phone1);//根据手机号查找是对像
		Phone phone = this.phoneService.findPhoneByOpen();
		String state="";//返回状态
		String code = "";//返回的验证码
		if(basicinfor==null || "".equals(basicinfor)){
			state="0";//手机号不存在
		}else{
			Uservip uservip=basicinfor.getUservip();//获取用户对像
			Messagemodel messagemodel = this.messagemodelService.findMessagemodelByintegralPid(15);//查找发送验证码短信的信息
			String contents = messagemodel.getMessModelContent();
			if (contents.indexOf("#USERNAME#") != -1) {//短信内容中#USERNAME#没有返回值时为-1，且把用户名替换#USERNAME#
				contents = contents.replaceAll("#USERNAME#",
						uservip.getUserName());
			}
			if (contents.indexOf("#CODE#") != -1) {//短信内容中#CODE#没有返回值时为-1，且把生成的code替换#CODE#
				// 生成验证吗
				while (code.length() < 6) {
					code = code + (int) (Math.random() * 10.0D);
				}
				contents = contents.replaceAll("#CODE#", code);
				System.err.println("pwdCode:" + code);
				ActionContext.getContext().getSession().put("appCode" + uservip.getUserId(), code);
			}
			if (phone.getPhoneId().intValue() == 1) {//发送短信时用漫道的
				Client client = new Client(phone.getPhoneName(),
						phone.getPhonePassword());
				String result_mt = client.mdSmsSend_u(phone1,contents, "", "", "");
				System.out.println(phone1);
				if ((result_mt.startsWith("-")) || ("".equals(result_mt))) {
					System.out.print("发送失败！返回值为：" + result_mt+ "请查看webservice返回值对照表");
					state = "2";
				} else {
					System.out.print("发送成功，返回值为：" + result_mt);
					state = "3";
				}
			}
			if (phone.getPhoneId().intValue() == 2) {//发送短信时用沃动的
				String url = "http://218.244.136.70:8888/sms.aspx";
				String sendSms =SmsClientSend.sendSms(url, phone.getPhoneuserId(), phone.getPhoneName(), phone.getPhonePassword(), phone1, contents);
				System.out.println(phone.getPhoneName()
						+ phone.getPhonePassword() + phone1
						+ contents);
				System.err.println(sendSms);
				if (sendSms.indexOf("Faild") != -1) {
					state = "2";//发送失败
				}else{
					state = "3";//发送成功
				}
			}
			SmsSendLog ssl = new SmsSendLog();
			ssl.setContent(contents);
			ssl.setSendTime(new Timestamp(new Date().getTime()));
			ssl.setTophone(phone1);
			ssl.setTitle("");
			ssl.setUservip(uservip);
			ssl.setStatus(1);
			SmsSendLogService sslService = new SmsSendLogServiceImpl();
			sslService.createSmsSendLog(ssl);
		}
		
		StringBuffer str = new StringBuffer();
		str.append("{\"update_password\":\"" + state + "\",");
		str.append("\"code\":\"" + code + "\",");
		str.deleteCharAt(str.lastIndexOf(","));
		str.append("}");
		response.setCharacterEncoding("utf-8");
		try {
			response.getWriter().print(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
/**
 * 找回密码时所更改的新密码
 * */
	public void updatePassword () throws Exception{
		this.request = ServletActionContext.getRequest();
		this.response = ServletActionContext.getResponse();
		String code1 = request.getParameter("code");//验证码
		String newPassword = request.getParameter("new_password");//用户填写的新密码
		String phone1 = request.getParameter("phone");//手机号
		String state = "";//返回的状态
		Basicinfor basicinfor = this.basicinforService.findBasicinforByPhone(phone1);//根据手机号获取用户id
		Uservip uservip = this.userService.findUservipByUserid(basicinfor.getUservip().getUserId());//根据用户id查找此用户对像
		String code = (String)ActionContext.getContext().getSession().get("appCode" + uservip.getUserId());
		if(!code1.equals(code)){
			state = "3";//验证码不正确
		}else{
			uservip.setPassword(newPassword);//设置新密码
			boolean boo = this.userService.updateUservip(uservip);
			if(boo==true){
				state = "1";//保存成功
			}else{
				state = "2";//保存失败
			}
		}
		StringBuffer str = new StringBuffer();
		str.append("{\"update_password\":\"" + state + "\",");
		str.deleteCharAt(str.lastIndexOf(","));
		str.append("}");
		response.setCharacterEncoding("utf-8");
		try {
			response.getWriter().print(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 根据app图片类型查找
	 * */
	public void findPhotosByType() throws Exception{
		this.request = ServletActionContext.getRequest();
		this.response = ServletActionContext.getResponse();
		AppPhotosService appPhotosService = new AppPhotosServiceImpl();
	     String path = request.getContextPath();
	     String basePath = "";
	     if(request.getServerPort()==80){
	    	 basePath = request.getScheme() + "://" + request.getServerName()+ path + "";
	     }else{
	    	 basePath = request.getScheme() + "://" + request.getServerName()+ ":" + request.getServerPort() + path + "";
	     }
		  
		List<AppPhotos> appphotos1 = appPhotosService.findPhotosByType(Integer.valueOf(1),Integer.valueOf(1),Integer.valueOf(1),Integer.valueOf(1));
		List<AppPhotos> appphotos2 = appPhotosService.findPhotosByType(Integer.valueOf(2),Integer.valueOf(3),Integer.valueOf(4),Integer.valueOf(5));
		List<AppPhotos> appphotos3 = appPhotosService.findPhotosByType(Integer.valueOf(6),Integer.valueOf(6),Integer.valueOf(6),Integer.valueOf(6));
		net.sf.json.JSONArray json = new net.sf.json.JSONArray();
		Map mapp = new HashMap();
		Map map1 = new HashMap();
		List list = new ArrayList();
		List list1 = new ArrayList();
		List list2 = new ArrayList();
		List list3 = new ArrayList();
		for(int i=0;i<appphotos1.size(); i++){
			Map map = new HashMap();
			map.put("title", appphotos1.get(i).getTitle());
			map.put("type", appphotos1.get(i).getType());
			if("0".equalsIgnoreCase(appphotos1.get(i).getPathImages())){
				map.put("pathImages", appphotos1.get(i).getPathImages());
			}else{
				map.put("pathImages", (basePath+appphotos1.get(i).getPathImages()));
			}
			map.put("url", appphotos1.get(i).getUrl());
			map.put("content", appphotos1.get(i).getContent());
			list1.add(map);
			
		}
		System.err.println(list1);
		map1.put("home_image", list1);
//		mapp.putAll(map1);
		list.add(map1);
		Map map2 = new HashMap();
		for(int i=0;i<appphotos2.size();i++){
			Map map = new HashMap();
			map.put("title", appphotos2.get(i).getTitle());
			map.put("type", appphotos2.get(i).getType());
			if("0".equalsIgnoreCase(appphotos2.get(i).getPathImages())){
				map.put("pathImages", appphotos2.get(i).getPathImages());
			}else{
				map.put("pathImages", (basePath+appphotos2.get(i).getPathImages()));
			}
			map.put("url",appphotos2.get(i).getUrl() );
			map.put("content",appphotos2.get(i).getContent() );
			list2.add(map);
		}
		System.err.println(list2);
		map2.put("guide_image", list2);
//		mapp.putAll(map2);
		System.err.println(mapp);
		list.add(map2);
		Map map3 = new HashMap();
		for(int i=0;i<appphotos3.size();i++){
			Map map = new HashMap();
			map.put("title",appphotos3.get(i).getTitle());
			map.put("type",appphotos3.get(i).getType());
			if("0".equalsIgnoreCase(appphotos3.get(i).getPathImages())){
				map.put("pathImages",appphotos3.get(i).getPathImages());
			}else{
				map.put("pathImages",(basePath+appphotos3.get(i).getPathImages()));
			}
			map.put("url",appphotos3.get(i).getUrl() );
			map.put("content",appphotos3.get(i).getContent());
			list3.add(map);
		}
		System.err.println(list3);
		map3.put("slide_image", list3);
		list.add(map3);
		System.err.println(mapp);
		json=json.fromObject(list);
		response.setCharacterEncoding("utf-8");
		try {
			response.getWriter().print(json);
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

}
