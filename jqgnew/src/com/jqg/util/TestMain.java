/**  
 * @Project: jqgnew
 * @Title: TestMain.java
 * @Package com.jqg.util
 * @date 2015-1-12 ����10:29:36
 * @Copyright: 2015 
 */
package com.jqg.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * ������TestMain
 * ���ܣ�
 * ��ϸ��
 * ���ߣ����е�(caozhongde)
 * �汾��1.0
 * ���ڣ�2015-1-12 ����10:29:36
 *
 */
public class TestMain {
	 public static String getTheDate(){
		  SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		  String dateStr = sd.format(new Date());
		  print("*******�õ����������*******"+dateStr);
		  return dateStr;
		 }
		 
		 public static Map getWeekDay() {
		  Map<String,String> map = new HashMap<String,String>();
		  Calendar cal =Calendar.getInstance();
		        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); //��ȡ����һ������
		        map.put("mon", df.format(cal.getTime()));
		  print("********�õ�����һ������*******"+df.format(cal.getTime()));
		  //������������ϸ��������յ����ڣ���Ϊ�����Ǳ߰����յ��ɵ�һ��
		  cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		  //����һ�����ڣ����������й������ı����յ�����
		  cal.add(Calendar.WEEK_OF_YEAR, 1);
		  map.put("sun", df.format(cal.getTime()));
		  print("********�õ������������*******"+df.format(cal.getTime()));
		  return map;
		 }
		 
		 public static Map getMonthDate(){
		  Map<String,String> map = new HashMap<String,String>();
		  // ��ȡCalendar  
		  Calendar calendar = Calendar.getInstance(); 
		   DateFormat format = new SimpleDateFormat("yyyy-MM-dd 00:00:00");  
		  // ����ʱ��,��ǰʱ�䲻������  
		  // calendar.setTime(new Date());  
		   calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE)); 
		   map.put("monthF", format.format(calendar.getTime()));
		   print("*********�õ����µ���С����**********"+format.format(calendar.getTime()));
		 // ��������Ϊ�����������  
		   calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));  
		 // ��ӡ  
		   map.put("monthL", format.format(calendar.getTime()));
		   print("*********�õ����µ��������**********"+format.format(calendar.getTime()));
		   return map;
		 }
		 
		 private static void print(Object o){
		  System.out.println(o.toString());
		 }
		 
		 public static void main(String[] args) {
			
			getTheDate();
			getWeekDay();
			getMonthDate();
		}
}
