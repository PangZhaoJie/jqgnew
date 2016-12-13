/**  
 * @Project: jqgnew
 * @Title: BaseAction.java
 * @Package com.jqg.struts
 * @date 2015-1-9 下午4:22:48
 * @Copyright: 2015 
 */
package com.jqg.struts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.jqg.pojo.Message;
import com.jqg.pojo.Uservip;
import com.jqg.pojo.Website;
import com.jqg.service.UservipService;
import com.jqg.service.WebsiteService;
import com.jqg.service.impl.UservipServiceImpl;
import com.jqg.service.impl.WebsiteServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * 类名：BaseAction
 * 功能：
 * 详细：
 * 作者：曹中德(caozhongde)
 * 版本：1.0
 * 日期：2015-1-9 下午4:22:48
 *
 */
public class BaseAction extends ActionSupport{
	public BaseAction(){
		UservipService userService = new UservipServiceImpl();
		WebsiteService websiteService = new WebsiteServiceImpl();
        
		try {
			Website website = websiteService.findWebsiteBywebsiteId(1);
			ActionContext.getContext().getSession().put("websiteinfo", website);
		
			Uservip use = (Uservip) ActionContext.getContext().getSession()
					.get("uservip");
			if(use!=null){
				Uservip uservip=new Uservip();
				try {
					uservip = userService.findUservipByUserid(use.getUserId()
							.intValue());
				} catch (Exception e) {
					System.err.println(e);
				}
				ActionContext.getContext().getSession().put("uservip", uservip);
			}
		} catch (Exception e1) {
			System.err.println(e1);
		}
		
	}
	
	private Message mes;

	public Message getMes() {
		return mes;
	}

	public void setMes(Message mes) {
		this.mes = mes;
	}
	
	public void setMessage(String msc,String url,String time){
		this.mes=new Message();
		mes.setMsc(msc);
		mes.setUrl(url);
		mes.setTime(time);
		ActionContext.getContext().put("MyMessage",mes);
	}
}
