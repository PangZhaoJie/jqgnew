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
 * ������RegularTasks ���ܣ�������� ��ϸ�� ���ߣ����е�(caozhongde) �汾��1.0 ���ڣ�2015-4-21 ����2:03:23
 * 
 */
public class RegularTasks implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		// context����ʱ�����ٳ�ʼ������
	}

	public void contextInitialized(ServletContextEvent sce) {
		try {

			// ���ȣ�����Ҫȡ��һ��Scheduler������
			SchedulerFactory sf = new StdSchedulerFactory();
			Scheduler sched = sf.getScheduler();
			// jobs������scheduled��sched.start()����ǰ������

			// job 1��ÿ��20��ִ��һ��
			JobDetail job = newJob(TimJob.class).withIdentity("job1", "group1")
					.build();
			CronTrigger trigger = newTrigger()
					.withIdentity("trigger1", "group1")
					.withSchedule(cronSchedule("0 0 1 * * ?")).build();
			Date ft = sched.scheduleJob(job, trigger);
			SimpleDateFormat sdf = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss SSS");
			System.out.println(job.getKey() + " �ѱ�����ִ����: " + sdf.format(ft)
					+ "�������������ظ������ظ�ִ��: " + trigger.getCronExpression());

			// job 2��ÿ2����ִ��һ�Σ��ڸ÷��ӵĵ�15��)
			// job = newJob(myJob.class).withIdentity("job2", "group1").build();
			// trigger = newTrigger().withIdentity("trigger2",
			// "group1").withSchedule(cronSchedule("15 0/2 * * * ?")).build();
			// ft = sched.scheduleJob(job, trigger);
			// System.out.println(job.getKey() + " �ѱ�����ִ����: " + sdf.format(ft) +
			// "�������������ظ������ظ�ִ��: "+ trigger.getCronExpression());
			// ��ʼִ�У�start()���������ú󣬼�ʱ���Ϳ�ʼ��������ʱ�������������N��Job
			sched.start();
			// try {
			// //���̵߳ȴ�һ����
			// Thread.sleep(60L * 1000L);
			// } catch (Exception e) {}
			// //�رն�ʱ���ȣ���ʱ�����ٹ���
			// sched.shutdown(true);
		} catch (Exception e) {
			System.err.println(e);
		}
	}

}
