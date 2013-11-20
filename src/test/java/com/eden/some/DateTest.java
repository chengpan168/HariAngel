package com.eden.some;

import java.text.DateFormatSymbols;
import java.util.Locale;

import org.junit.Test;

public class DateTest {
	@Test
	public void testShortWeek(){
		DateFormatSymbols dfs = new DateFormatSymbols(Locale.CHINA) ;
		Locale a = Locale.CHINA ;
		String[] w = dfs.getShortWeekdays() ;
		w = dfs.getWeekdays() ;
		w = dfs.getShortMonths() ;
		
		System.out.println(w) ;
	}
}
