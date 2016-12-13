package com.jqg.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;



/**
 * 
 * ������DateUtil
 * ���ܣ�ʱ���ʽ��
 * ��ϸ��
 * ���ߣ�LiuJincheng
 * �汾��1.0
 * ���ڣ�2013-4-12 ����1:55:18
 *
 */
public class DateUtil {
	/**
	 * Ĭ�� ����ʱ�� ��ʽ  yyyy-MM-dd HH:mm:ss
	 */
	public static final String PATTERN_STANDARD = "yyyy-MM-dd HH:mm:ss";
	/**
	 * Ĭ�� ���ڸ�ʽ  yyyy-MM-dd
	 */
	public static final String PATTERN_DATE = "yyyy-MM-dd";
	/**
	 * Ĭ�� ʱ���ʽ
	 */
	public static final String PATTERN_TIME = "HH:mm:ss";
	/**
	 * ÿ��1��
	 */
	public static final String PATTERN_MONTH = "yyyy-MM-01";
	/**
	 * �Զ�ƥ���ַ�����ʽ
	 */
	public  static String[] parsePatterns = { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", 
		"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm" };
	
	/**
	 * Timestamp ��ʽ�����ַ�����ʹ��Ĭ�ϸ�ʽ  yyyy-MM-dd HH:mm:ss
	 * @param timestamp
	 * @return
	 */
	public static String timestamp2String(Timestamp timestamp) {
		if (timestamp == null) {
			return null;
		}
		return DateFormatUtils.format(timestamp, PATTERN_STANDARD);
	}
	/**
	 * Timestamp ��ʽ�� �Զ����ʽ
	 * @param timestamp
	 * @param pattern
	 * @return
	 */
	public static String timestamp2String(Timestamp timestamp, String pattern) {
		if (timestamp == null) {
			return null;
		}
		return DateFormatUtils.format(timestamp, pattern);
	}
	/**
	 * Date ��ʽ�����ַ�����ʹ��Ĭ�ϸ�ʽ  yyyy-MM-dd 
	 * @param date
	 * @return
	 */
	public static String date2String(Date date) {
		if (date == null) {
			return null;
		}
		return DateFormatUtils.format(date, PATTERN_DATE);
	}
	/**
	 * Date ��ʽ�� �Զ����ʽ
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String date2String(Date date, String pattern) {
		if (date == null) {
			return null;
		}
		return DateFormatUtils.format(date, pattern);
	}
	/**
	 * ��ȡ��ǰʱ��
	 * @return
	 */
	public static Timestamp currentTimestamp() {
		return new Timestamp(new Date().getTime());
	}
	
	/**
	 * ��ȡ��ǰ����yyyy-MM-dd String
	 * @return
	 */
	public static String currentDateToString( ) {
		
		return date2String(new Date());
	}
	
	/**
	 * ��ȡ��ǰʱ�� HH:mm:ss String����
	 * @return 
	 */
	public static String currentTimeToString( ) {
		 
		return date2String(new Date(),PATTERN_TIME);
		 
	}
	/**
	 * ��ȡ��ǰ����yyyy-MM-dd  HH:mm:ss String
	 * @return
	 */
	public static String currentDateTimeToString( ) {
		 
		return date2String(new Date(),PATTERN_STANDARD);
	}
	/**
	 * ����ʱ�����
	 * @param firsttime
	 * @param secondtime
	 * @return
	 */
	public static long subtractTime(String firsttime ,String secondtime ) {
		 
		return string2Date(firsttime,PATTERN_TIME).getTime() -string2Date(secondtime,PATTERN_TIME).getTime();
		 
	}
	/**
	 * �ַ���ת��Ϊ Timestamp  �Զ�ƥ���ʽ
	 * @param strDateTime
	 * @return ��������ַ���Ϊnull�����߿��ַ������򷵻�null
	 */
	public static Timestamp string2Timestamp(String strDateTime) {
		
		return new Timestamp(string2Date(strDateTime).getTime());
	}
	/**
	 * �ַ��� ת��Ϊ Timestamp  �����ַ�����ʽ 
	 * @param strDateTime
	 * @param pattern
	 * @return
	 */
	public static Timestamp string2Timestamp(String strDateTime, String pattern) {
		
		return new Timestamp(string2Date(strDateTime, pattern).getTime());
	}
	/**
	 * �ַ���ת��Ϊ Date  �Զ�ƥ���ʽ
	 * @param strDate
	 * @return ��������ַ���Ϊnull�����߿��ַ������򷵻�null
	 */
	public static Date string2Date(String strDate) {
		if (StringUtils.isBlank(strDate)) {
			return null;
		}
		try {
			return DateUtils.parseDate(strDate.trim(),parsePatterns);
		} catch (ParseException e) {
			e.printStackTrace();
			System.err.println("��������ת������");
			
		}
		return null;
	}
	/**
	 * �ַ���ת��Ϊ Date  �����ַ�����ʽ
	 * @param strDate ʱ��
	 * @param pattern �ַ��� ��ʽ
	 * @return ��������ַ���Ϊnull�����߿��ַ������򷵻�null 
	 */
	public static Date string2Date(String strDate, String pattern) {
		
		if (StringUtils.isBlank(strDate)) {
			return null;
		}
		try {
			return DateUtils.parseDate(strDate.trim(),new String[]{pattern});
		} catch (ParseException e) {
			e.printStackTrace();
			System.err.println("��������ת������");
		}
		return null;
	}
	
	/**
	 * ǰ���ƶ�����
	 * @param date
	 * @param move -1:��ǰ�ƶ�һ�죬1:����ƶ�һ��
	 * @return
	 */
	public static Date moveDate(Date date,int move){
		
		 Calendar calendar = new GregorianCalendar();
		 calendar.setTime(date);
		 calendar.add(calendar.DATE,move);//��������������һ��.����������,������ǰ�ƶ�
		 return calendar.getTime(); //���ʱ����������ƶ�֮���ʱ��
		
	}
	/***
	 * ʱ���ַ���ת����long
	 * @param strDate
	 * @param pattern
	 * @return
	 */
	public static long string2long(String strDate, String pattern) {
		if (strDate == null || "".equals(strDate)) {
			return 0l;
		}
		if (pattern == null || "".equals(pattern)) {
			pattern = DateUtil.PATTERN_DATE;
		}
		Date d=string2Date(strDate,pattern);
		if(d==null){
			return 0L;
		}
		return d.getTime();
	}
	
	/**
	 * ��õ�ǰ�³����� yyyy-MM-01
	 * @return
	 */
	public static String currentMonthDateToString( ) {
	
		return date2String(new Date(), PATTERN_MONTH);
	}
	/**
	 * ��ȡ���쿪ʼʱ��
	 * @return
	 */
	public static String currentDayDateStartToString(String stime ) {
		//(stime.split(" ")[0], DateUtil.PATTERN_DATE);
		Date d= string2Date(stime.split(" ")[0], DateUtil.PATTERN_DATE);
		
		return date2String(d, "yyyy-MM-dd 00:00:01");
	}
	/**
	 * ��ȡ�������ʱ��
	 * @return
	 */
	public static String currentDayEndDateToString(String stime ) {
		//(stime.split(" ")[0], DateUtil.PATTERN_DATE);
		Date d= string2Date(stime.split(" ")[0], DateUtil.PATTERN_DATE);
		
		return date2String(d, "yyyy-MM-dd 23:59:59");
	}
	
	public static void main(String[] args){
		
		//System.out.println(moveDate(new Date(),1).toLocaleString());
		
		/*String t=date2String(new Date(), null);
		
		System.out.println(t);
		
		Timestamp end=string2Timestamp(t+" 23:59:59", null);
		
		System.out.println(end);
		
		System.out.println(string2Timestamp("2013-10-20 00:00:00", null).getTime());
	    
	
		System.out.println("=========="+Pattern.matches("^192.168.1.*$", "192.168.2.1"));
	
	
		SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DATE);
		System.out.println(sdf.format(0l));*/
	
		System.out.println(currentDayEndDateToString("2013-10-20 12:01:24"));
		
	}
	
	
}
