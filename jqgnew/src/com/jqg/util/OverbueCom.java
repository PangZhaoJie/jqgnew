package com.jqg.util;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;

import com.jqg.pojo.Record;
import com.jqg.pojo.Repaynote;



/**
 * 
 * 类名：OverbueCom
 * 功能：逾期利息计算
 * 详细：
 * 作者：tengzhanqing
 * 版本：1.0
 * 日期：2015-1-19 下午1:55:18
 *
 */
public class OverbueCom {
	/**
	 * 默认 日期时间 格式  yyyy-MM-dd HH:mm:ss
	 */
	public static final String PATTERN_STANDARD = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 默认 日期格式  yyyy-MM-dd
	 */
	public static final String PATTERN_DATE = "yyyy-MM-dd";
	/**
	 * 默认 时间格式
	 */
	public static final String PATTERN_TIME = "HH:mm:ss";
	/**
	 * 每月1日
	 */
	public static final String PATTERN_MONTH = "yyyy-MM-01";
	/**
	 * 自动匹配字符串格式
	 */
	public  static String[] parsePatterns = { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", 
		"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm" };
	
	
	/**
	 * 逾期计息计算
	 * @return
	 * @throws ParseException 
	 */
	public static Repaynote comoverInterest(Repaynote repaynote,double overfee1,double overfee2,double overfee3,double overfee4) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DATE);
		DecimalFormat df = new DecimalFormat("##.00");
		String nowdate = sdf.format(new Date());
		String repaydate = sdf.format(new Date(repaynote.getRepayDate().getTime()));
		//String nowtime = timedf.format(new Date());
		
		System.out.println(repaydate+"============"+nowdate);
		if(repaydate.equals(nowdate)){
			repaynote.setOverdays(1);
			repaynote.setOverInterest(Double.valueOf(df.format(repaynote.getMoneyFour()*overfee1/100.0D)));
		}else{
			long DAY = 24L * 60L * 60L * 1000L;
			long repaydatetime = sdf.parse(repaydate).getTime();
			long nowdatetime = sdf.parse(nowdate).getTime();
			System.out.println(new Timestamp(repaydatetime)+"============"+new Timestamp(nowdatetime));
			int overdays = (int) ((nowdatetime-repaydatetime)/DAY);
			repaynote.setOverdays(overdays);
			if(overdays>=1 && overdays<=3){
				repaynote.setOverInterest(Double.valueOf(df.format(repaynote.getMoneyFour()*overfee1*overdays/100.0D)));
			}else if(overdays>3&&overdays<=30){
				repaynote.setOverInterest(Double.valueOf(df.format(repaynote.getMoneyFour()*overfee2*overdays/100.0D)));
			}else if(overdays>30 && overdays<=90){
				repaynote.setOverInterest(Double.valueOf(df.format(repaynote.getMoneyFour()*overfee3*overdays/100.0D)));
			}else{
				repaynote.setOverInterest(Double.valueOf(df.format(repaynote.getMoneyFour()*overfee4*overdays/100.0D)));
			}
		}
		return repaynote;
	}
	
	
	public static Record comOverInterest(Record record,double overfee1,double overfee2,double overfee3,double overfee4) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DATE);
		DecimalFormat df = new DecimalFormat("##.00");
		String nowdate = sdf.format(new Date());
		String repaydate = sdf.format(new Date(record.getRecordDate().getTime()));
		//String nowtime = timedf.format(new Date());
		System.out.println(repaydate+"============"+nowdate);
		if(repaydate.equals(nowdate)){
			record.setOverdays(1);
			record.setOverInterest(Double.valueOf(df.format((record.getRecordMoney()+record.getRecordRate())*overfee1/100.0D)));
		}else{
			long DAY = 24L * 60L * 60L * 1000L;
			long repaydatetime = sdf.parse(repaydate).getTime();
			long nowdatetime = sdf.parse(nowdate).getTime();
			System.out.println(new Timestamp(repaydatetime)+"============"+new Timestamp(nowdatetime));
			int overdays = (int) ((nowdatetime-repaydatetime)/DAY);
			record.setOverdays(overdays);
			if(overdays>=1 && overdays<3){
				record.setOverInterest(Double.valueOf(df.format((record.getRecordMoney()+record.getRecordRate())*overfee1*overdays/100.0D)));
			}else if(overdays>3&&overdays<=30){
				record.setOverInterest(Double.valueOf(df.format((record.getRecordMoney()+record.getRecordRate())*overfee2*overdays/100.0D)));
			}else if(overdays>30 && overdays<=90){
				record.setOverInterest(Double.valueOf(df.format((record.getRecordMoney()+record.getRecordRate())*overfee3*overdays/100.0D)));
			}else{
				record.setOverInterest(Double.valueOf(df.format((record.getRecordMoney()+record.getRecordRate())*overfee4*overdays/100.0D)));
			}
		}
		return record;
	}
	
	public static void main(String[] args) throws ParseException{
		SimpleDateFormat df = new SimpleDateFormat(PATTERN_DATE);
		SimpleDateFormat timedf = new SimpleDateFormat(PATTERN_STANDARD);
		String overtimestu="21:00:00";
		String nowdate = df.format(new Date());
		Timestamp nowtime = new Timestamp(new Date().getTime());
		Timestamp overtime = new Timestamp(timedf.parse(nowdate+" "+overtimestu).getTime());
		System.out.println(nowtime+"==========="+overtime+"========="+nowtime.after(overtime));
	}
	
}
