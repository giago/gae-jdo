package com.giago.appengine.commons.util;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.junit.Test;

public class DateUtilsTest {
	
	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy MM dd HH mm ss SSS");
	
	@Test
	public void shouldGetBeginningOfDay() {
		Date date = DateUtils.getBeginningOfDay(1970, 1, 1);
		assertEquals("1970 01 01 00 00 00 000", formatter.format(date));
	}
	
	@Test
	public void shouldGetEndOfDay() {
		Date date = DateUtils.getEndOfDay(1970, 1, 1);
		assertEquals("1970 01 01 23 59 59 999", formatter.format(date));
	}
	
	@Test
	public void shouldGetEndOfMonth() {
		Date date = DateUtils.getEndOfMonth(1970, 1);
		assertEquals("1970 01 31 23 59 59 999", formatter.format(date));
	}
	
	@Test
	public void shouldGetEndOfMonthCorrectForFeb() {
		Date date = DateUtils.getEndOfMonth(1970, 2);
		assertEquals("1970 02 28 23 59 59 999", formatter.format(date));	
	}
	
	@Test
	public void shouldGetBeginningOfMonth() {
		Date date = DateUtils.getBeginningOfMonth(1970, 2);
		assertEquals("1970 02 01 00 00 00 000", formatter.format(date));	
	}
	
	@Test
	public void shoudlGetYearsFrom() {
		assertEquals(Arrays.asList("2011", "2010"), DateUtils.getYears(2010, 2011));
	}

}
