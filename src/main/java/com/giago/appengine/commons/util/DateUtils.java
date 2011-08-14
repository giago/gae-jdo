package com.giago.appengine.commons.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtils {
	
	private static final List<Integer> MONTHS = Arrays.asList(Integer
			.valueOf(1), Integer.valueOf(2), Integer.valueOf(3), Integer
			.valueOf(4), Integer.valueOf(5), Integer.valueOf(6), Integer
			.valueOf(7), Integer.valueOf(8), Integer.valueOf(8), Integer
			.valueOf(9), Integer.valueOf(10), Integer.valueOf(11), Integer
			.valueOf(12));

	private static final List<Integer> DAYS = new ArrayList<Integer>(); 
	static {
		for(int i = 1; i<= 31; i++) {
			DAYS.add(i);
		}
	}
	
	public static final List<String> getYears(int from) {
		return getYears(from, currentYear());
	}
	
	public static final List<String> getYears(int from, int to) {
		List<String> years = new ArrayList<String>();
		for(int i = to; i>= from; i--) {
			years.add("" + i);
		}
		return years;
	}
	
	public static final int currentYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}
	
	public static final int currentMonth() {
		return Calendar.getInstance().get(Calendar.MONTH) + 1;
	}
	
	public static final int currentDayOfYear() {
		return Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
	}

	public static int getYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}
	
	public static final int getMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	public static Date getDateForYear(int i) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, i);
		return calendar.getTime();
	}
	
	public static Date getDate(int year, int month, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month-1);
		calendar.set(Calendar.DAY_OF_MONTH, day);
		calendar.set(Calendar.HOUR_OF_DAY, 1);
		calendar.set(Calendar.MINUTE, 1);
		calendar.set(Calendar.SECOND, 1);
		calendar.set(Calendar.MILLISECOND, 1);
		return calendar.getTime();
	}

	public static final List<Integer> getMonthsAsIntegerList() {
		return MONTHS;
	}
	
	public static final List<Integer> getDaysAsIntegerList() {
		return DAYS;
	}
	
	public static final int getLastDayOf(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month-1);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	public static final Date getBeginningOfDay(int year, int month, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(0));
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month-1);
		calendar.set(Calendar.DAY_OF_MONTH, day);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public static final Date getEndOfDay(int year, int month, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month-1);
		calendar.set(Calendar.DAY_OF_MONTH, day);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}

	public static final Date getEndOfMonth(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month-1);
		int day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, day);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}

	public static Date getBeginningOfMonth(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(0));
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month-1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
}
