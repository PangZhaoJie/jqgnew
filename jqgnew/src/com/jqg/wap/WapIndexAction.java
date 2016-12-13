/**  
 * @Project: longpei
 * @Title: WapIndexAction.java
 * @Package com.jqg.wap
 * @date 2015-7-21 ����2:48:48
 * @Copyright: 2015 
 */
package com.jqg.wap;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.jqg.pojo.Latestnews;
import com.jqg.pojo.Lssuing;
import com.jqg.pojo.Lssuingherald;
import com.jqg.pojo.Tender;
import com.jqg.service.LatestnewsService;
import com.jqg.service.LssuingService;
import com.jqg.service.LssuingheraldService;
import com.jqg.service.UservipService;
import com.jqg.service.impl.LatestnewsServiceImpl;
import com.jqg.service.impl.LssuingServiceImpl;
import com.jqg.service.impl.LssuingheraldServiceImpl;
import com.jqg.service.impl.UservipServiceImpl;
import com.jqg.struts.BaseAction;

/**
 * 
 * ������WapIndexAction
 * ���ܣ�
 * ��ϸ��
 * ���ߣ����е�(caozhongde)
 * �汾��1.0
 * ���ڣ�2015-7-21 ����2:48:48
 *
 */
public class WapIndexAction extends BaseAction{
	private static HttpServletRequest request;
	LatestnewsService latestnewsService=new LatestnewsServiceImpl();
	LssuingheraldService lssuingheraldService=new LssuingheraldServiceImpl();
	UservipService uservipService=new UservipServiceImpl();
	LssuingService lssuingService=new LssuingServiceImpl();
	
	public String index() throws Exception{
		request=ServletActionContext.getRequest();
		
		// ����������ѯ������Ϣ
		List<Latestnews> latestnews = latestnewsService.findLatestnewssBypicture();
		// �±�Ԥ��
		Lssuingherald lssuingherald = lssuingheraldService.findLssuingheraldByindex();
		//�û�����
		int count=uservipService.findUservips().size();
		//���� 3���б�2������
		String str="select * from Lssuing where  state=3 and lssuingType != 6 order by lssuingId desc";
		List<Lssuing> ls=lssuingService.findLssuingsIndex(str,2);
		str="select * from Lssuing where  state=2 and lssuingType != 6 order by lssuingId desc";
		List<Lssuing> lss= lssuingService.findLssuingsIndex(str,3);
		List<Lssuing> lssuingss=new ArrayList<Lssuing>();
		for(int i=0;i<lss.size();i++){
			lssuingss.add(lss.get(i));	
		}
		for(int i=0;i<ls.size();i++){
			lssuingss.add(ls.get(i));	
		}
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
				l.setRates(Double.valueOf(l.getRate()));
			}else{
				l.setRates(l.getRate());
			}
			
			lssuings.add(l);
		}
		
		
		request.setAttribute("lnList",latestnews );
		request.setAttribute("lssuingherald",lssuingherald );
		request.setAttribute("count", count);
		request.setAttribute("lList", lssuings);
		return "success";
	}
	
}
