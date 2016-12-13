package com.jqg.job;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.jqg.pojo.Uservip;
import com.jqg.service.UservipService;
import com.jqg.service.impl.UservipServiceImpl;

public class TimJob implements Job{
	
	UservipService uservipService=new UservipServiceImpl();
	
	
	public void execute(JobExecutionContext arg0) throws JobExecutionException { 
//		检查vip是否到期   开始
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		List<Uservip> uservipList=new ArrayList<Uservip>();
		try {
			uservipList=uservipService.findVIPJob();
			for(Uservip user:uservipList){
				
				Date endTime=user.getVipEndTime();
				boolean boo=new Date().after(endTime);
				if(boo){
					user.setIsVIP(0);
					user.setVipMonthe(0);
					uservipService.updateUservipJob(user);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
//		检查vip是否到期   结束
//		数据库备份开始
//		SimpleDateFormat sd = new SimpleDateFormat("yyyy年M月dd日"); 
//		DbBackupRes.backupDatabase(sd.format(new Date())+".sql");
//		数据库备份结束
        System.out.println("定时任务执行完毕时间：："+sdf.format(new Date())); 
    } 
}
