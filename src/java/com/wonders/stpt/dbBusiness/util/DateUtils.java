package com.wonders.stpt.dbBusiness.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

	public static String Date2String(Date date) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
			return sdf.format(date);
		}
		return "";
	}

	public static Date String2Date(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
		Date currentdate = null;
		try {
			currentdate = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return currentdate;
	}

	public static Date Date2Date(Date date) {
		return String2Date(Date2String(date));
	}

	public static String Date2String(Date date, String pattern) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.CHINA);
			return sdf.format(date);
		}
		return "";
	}

	public static Date String2Date(String date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.CHINA);
		Date currentdate = null;
		try {
			currentdate = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return currentdate;
	}

	public static Date Date2Date(Date date, String pattern) {
		return String2Date(Date2String(date, pattern), pattern);
	}

	public static boolean isValidDate(String s, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			return sdf.format(sdf.parse(s)).equals(s);
		} catch (Exception e) {
			return false;
		}
	}


	/**
	 * 获得今天指定的时间
	 * 
	 * @param hour
	 *            指定小时
	 * @param minute
	 *            指定分钟
	 * @param second
	 *            指定秒
	 */
	public static Date getSpecifiedDateOfToday(int hour, int minute, int second) {
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		calendar.setTime(date);
		int day = calendar.get(Calendar.DATE);
		calendar.set(Calendar.DATE, day);
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, second);
		Date specifiedDate = DateUtils.String2Date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime()));
		return specifiedDate;
	}

	/**
	 * 得到几天后的时间
	 * 
	 * @param d
	 * @param day
	 * @return
	 * @author mengjie
	 */
	public static Date getDateAfter(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return now.getTime();
	}

	/**
	 * 得到几天前的时间
	 */
	public static Date getDateBefor(Date d, int day){
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
		return now.getTime();
	}
	/**
	 * 判断某天是周几
	 * @param day
	 * @throws Exception
	 */
	public static int getWeek(String day) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(format.parse(day));
		int dayForWeek = 0;
		if (c.get(Calendar.DAY_OF_WEEK) == 1) {
			dayForWeek = 7;
			return dayForWeek;
		} else {
			dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
			return dayForWeek;
		}
	}
	
	public static String getDayString(String start, String end){
		long DAY = 24L * 60L * 60L * 1000L;  
		Date startDate = String2Date(start);
		Date endDate = String2Date(end);
		Long day = (endDate.getTime() - startDate.getTime())/DAY+1;
		return day.toString();
	}
	
	public static Long getDayLong(String start, String end){
		long DAY = 24L * 60L * 60L * 1000L;  
		Date startDate = String2Date(start);
		Date endDate = String2Date(end);
		Long day = (endDate.getTime() - startDate.getTime())/DAY+1;
		return day;
	}
}