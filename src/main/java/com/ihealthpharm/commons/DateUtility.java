package com.ihealthpharm.commons;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtility {

	private static SimpleDateFormat DATE_TO_STRING_DAY_MONTH_YEAR_FORMAT = new SimpleDateFormat("E MMMMM YYYY HH:mm");

	public static String getDateString() {
		return DATE_TO_STRING_DAY_MONTH_YEAR_FORMAT.format(new Date());
	}
}
