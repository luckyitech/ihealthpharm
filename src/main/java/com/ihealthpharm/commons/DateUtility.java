package com.ihealthpharm.commons;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtility {

	private static SimpleDateFormat DATE_TO_STRING_DAY_MONTH_YEAR_FORMAT = new SimpleDateFormat("EEEE dd MMMMM YYYY");

	private static String[] DATE_FORMATS = new String[] {""};

	
	public static String getDateString() {
		return DATE_TO_STRING_DAY_MONTH_YEAR_FORMAT.format(new Date());
	}
	
	public static String formatDate(String date,String format) {
		return DATE_TO_STRING_DAY_MONTH_YEAR_FORMAT.format(new Date());
	}
	
	public static Date getDateStringwithouKnowingInputFormat(String date) throws ParseException {
	    for (String format : DATE_FORMATS) {
	        DateFormat df = new SimpleDateFormat(format);
	        try {
	            return df.parse(date);
	        } catch (ParseException e) {}
	    }
	    throw new ParseException(
	            "This date does not conform to any known format", 0);
	}
}
