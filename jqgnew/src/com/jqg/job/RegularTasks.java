package com.jqg.job;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 
 * 类名：RegularTasks 功能：任务调度 详细： 作者：曹中德(caozhongde) 版本：1.0 日期：2015-4-21 下午2:03:23
 * 
 */
public class RegularTasks implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		// context销毁时，销毁初始化数据
	}

	public void contextInitialized(ServletContextEvent sce) {
		try {

			// 首先，必需要取得一个Scheduler的引用
			SchedulerFactory sf = new StdSchedulerFactory();
			Scheduler sched = sf.getScheduler();
			// jobs可以在scheduled的sched.start()方法前被调用

			// job 1将每隔20秒执行一次
			JobDetail job = newJob(TimJob.class).withIdentity("job1", "group1")
					.build();
			CronTrigger trigger = newTrigger()
					.withIdentity("trigger1", "group1")
					.withSchedule(cronSchedule("0 0 1 * * ?")).build();
			Date ft = sched.scheduleJob(job, trigger);
			SimpleDateFormat sdf = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss SSS");
			System.out.println(job.getKey() + " 已被安排执行于: " + sdf.format(ft)
					+ "，并且以如下重复规则重复执行: " + trigger.getCronExpression());

			// job 2将每2分钟执行一次（在该分钟的第15秒)
			// job = newJob(myJob.class).withIdentity("job2", "group1").build();
			// trigger = newTrigger().withIdentity("trigger2",
			// "group1").withSchedule(cronSchedule("15 0/2 * * * ?")).build();
			// ft = sched.scheduleJob(job, trigger);
			// System.out.println(job.getKey() + " 已被安排执行于: " + sdf.format(ft) +
			// "，并且以如下重复规则重复执行: "+ trigger.getCronExpression());
			// 开始执行，start()方法被调用后，计时器就开始工作，计时调度中允许放入N个Job
			sched.start();
			// try {
			// //主线程等待一分钟
			// Thread.sleep(60L * 1000L);
			// } catch (Exception e) {}
			// //关闭定时调度，定时器不再工作
			// sched.shutdown(true);
		} catch (Exception e) {
			System.err.println(e);
		}
	}

}
